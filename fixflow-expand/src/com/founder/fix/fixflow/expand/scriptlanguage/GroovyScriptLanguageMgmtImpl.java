/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.expand.scriptlanguage;

import groovy.lang.GroovyShell;

import java.util.List;
import java.util.Map;

import com.founder.fix.bpmn2extensions.sqlmappingconfig.Rule;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DataVariableBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.scriptlanguage.AbstractScriptLanguageMgmt;

public class GroovyScriptLanguageMgmtImpl extends AbstractScriptLanguageMgmt {

	private GroovyShell groovyShell;

	@Override
	public AbstractScriptLanguageMgmt init() {

		groovyShell = new GroovyShell();

		return this;
	}

	@Override
	public void close() {
		groovyShell = null;

	}

	@Override
	public Object execute(String scriptText, ProcessDefinitionBehavior processDefinition) {

		List<String> dvList = getDataVariableList(scriptText);

		if (dvList.size() > 0) {

			for (String expressionId : dvList) {

				List<DataVariableBehavior> dataVariableBehaviors = processDefinition.getDataVariableMgmtDefinition()
						.getDataVariableBehaviorsByProcess();
				for (DataVariableBehavior dataVariableBehavior : dataVariableBehaviors) {

					if (dataVariableBehavior.getId().equals(expressionId)) {

						Object object = null;
						if (dataVariableBehavior.getExpression() != null) {
							object = ExpressionMgmt.execute(dataVariableBehavior.getExpression(), processDefinition);
						}

						ExpressionMgmt.setVariable(dataVariableBehavior.getId(), object);

					}

				}

			}

		}

		String scriptTextTemp = getExpressionAll(scriptText);

		Object resultObj;
		resultObj = groovyShell.evaluate(scriptTextTemp);

		return resultObj;
	}

	@Override
	public void setVariable(String variableName, Object variableObj) {

		groovyShell.setVariable(variableName, variableObj);

	}

	@Override
	public void setVariable(String variableName, Object variableObj, ExecutionContext executionContext) {
		dataVariableCalculate(variableName, executionContext);
		String scriptText = getExpressionAll(variableName);

		groovyShell.setVariable(scriptText, variableObj);

	}

	@Override
	public Object getVariable(String variableName) {

		return groovyShell.getVariable(variableName);

	}

	@Override
	public Object execute(String scriptText, ExecutionContext executionContext) {
		if (scriptText == null) {
			return null;
		}

		dataVariableCalculate(scriptText, executionContext);

		Object resultObj = false;

		groovyShell.setVariable("bizData", Context.getProcessEngineConfiguration().getBizData());

		// 绑定变量
		if (executionContext != null) {
			groovyShell.setVariable("processInfo", executionContext);
		}
		String scriptTextTemp = getExpressionAll(scriptText);
		resultObj = groovyShell.evaluate(scriptTextTemp);

		return resultObj;
	}

	@Override
	public Object execute(String scriptText) {
		 return groovyShell.evaluate(scriptText);
	}

	@Override
	public <T> T executeBusinessRules(String ruleId, Object parameter,T classReturn) {

		return executeBusinessRules(ruleId,parameter,classReturn,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T executeBusinessRules(String ruleId, Object parameter,T classReturn, Map<String, Object> configMap) {
		
		ProcessEngineConfigurationImpl processEngineConfiguration=Context.getProcessEngineConfiguration();
		groovyShell.setVariable("sysRulesConfig", Context.getProcessEngineConfiguration());
		groovyShell.setVariable("parameter", parameter);
		groovyShell.setVariable("sqlCommand", new SqlCommand(Context.getDbConnection()));
		if(configMap!=null){
			
			for (String mapKey : configMap.keySet()) {
				groovyShell.setVariable(mapKey, configMap.get(mapKey));
			}
			
		}
		Rule rule = processEngineConfiguration.getRule(ruleId);
		T returnObj =  (T)groovyShell.evaluate(rule.getSqlValue());
		return returnObj;
	}

	@Override
	public Object executeBusinessRules(String ruleId, Object parameter) {
		return executeBusinessRules(ruleId,parameter);
	}

	@Override
	public Object executeBusinessRules(String ruleId, Object parameter, Map<String, Object> configMap) {
		ProcessEngineConfigurationImpl processEngineConfiguration=Context.getProcessEngineConfiguration();
		groovyShell.setVariable("sysRulesConfig", Context.getProcessEngineConfiguration());
		groovyShell.setVariable("parameter", parameter);
		groovyShell.setVariable("sqlCommand", new SqlCommand(Context.getDbConnection()));
		if(configMap!=null){
			
			for (String mapKey : configMap.keySet()) {
				groovyShell.setVariable(mapKey, configMap.get(mapKey));
			}
			
		}
		Rule rule = processEngineConfiguration.getRule(ruleId);
		Object returnObj =  groovyShell.evaluate(rule.getSqlValue());
		return returnObj;
	}

}
