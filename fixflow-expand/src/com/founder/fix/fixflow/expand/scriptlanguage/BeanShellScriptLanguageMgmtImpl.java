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

import java.util.List;
import java.util.Map;

import bsh.EvalError;
import bsh.Interpreter;

import com.founder.fix.bpmn2extensions.sqlmappingconfig.Rule;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DataVariableBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.scriptlanguage.AbstractScriptLanguageMgmt;

public class BeanShellScriptLanguageMgmtImpl extends AbstractScriptLanguageMgmt {

	private Interpreter interpreter;
	
	@Override
	public AbstractScriptLanguageMgmt init() {
		// TODO 自动生成的方法存根
		this.interpreter=new Interpreter();
		return this;
	}

	@Override
	public void close() {
		// TODO 自动生成的方法存根
		interpreter.setExitOnEOF(true);
	}
	
	@Override
	public Object execute(String scriptText,
			ProcessDefinitionBehavior processDefinition) {
		

		List<String> dvList = getDataVariableList(scriptText);

		if (dvList.size() > 0) {

			for (String expressionId : dvList) {

				List<DataVariableBehavior> dataVariableBehaviors = processDefinition
						.getDataVariableMgmtDefinition()
						.getDataVariableBehaviorsByProcess();
				for (DataVariableBehavior dataVariableBehavior : dataVariableBehaviors) {

					if (dataVariableBehavior.getId().equals(expressionId)) {

						Object object = null;
						if (dataVariableBehavior.getExpression() != null) {
							object = ExpressionMgmt.execute(
									dataVariableBehavior.getExpression(),
									processDefinition);
						}

						ExpressionMgmt.setVariable(
								dataVariableBehavior.getId(), object);

					}

				}

			}

		}

		String scriptTextTemp = getExpressionAll(scriptText);

		Object resultObj;
		try {
			resultObj = interpreter.eval(scriptTextTemp);
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new FixFlowException("表达式计算错误! 错误信息: " + e.getErrorText(), e);
		}
		return resultObj;
	}

	@Override
	public void setVariable(String variableName, Object variableObj) {

		
		try {
			interpreter.set(variableName, variableObj);
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	@Override
	public void setVariable(String variableName, Object variableObj,
			ExecutionContext executionContext) {
		dataVariableCalculate(variableName, executionContext);
		String scriptText = getExpressionAll(variableName);

		
		try {
			interpreter.set(scriptText, variableObj);
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object getVariable(String variableName) {
		
		try {
			return interpreter.get(variableName);
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object execute(String scriptText, ExecutionContext executionContext) {
		if (scriptText == null) {
			return null;
		}

		dataVariableCalculate(scriptText, executionContext);

		
		Object resultObj = false;
		
		try {
			// 绑定变量
			interpreter.set("bizData", Context.getProcessEngineConfiguration().getBizData());
			if (executionContext != null) {
				interpreter.set("processInfo", executionContext);
			}

			String scriptTextTemp = getExpressionAll(scriptText);
			resultObj = interpreter.eval(scriptTextTemp);
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FixFlowException("表达式计算错误! 错误信息: " + e.getErrorText(), e);
		}
		return resultObj;
	}

	@Override
	public Object execute(String scriptText) {
		
		try {
			return interpreter.eval(scriptText);
		} catch (EvalError e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new FixFlowException("表达式计算错误! 错误信息: " + e.getErrorText(), e);
		}

	}

	@Override
	public <T> T executeBusinessRules(String ruleId, Object parameter,T classReturn) {
		// TODO Auto-generated method stub
		
		return executeBusinessRules(ruleId,parameter,classReturn,null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T executeBusinessRules(String ruleId, Object parameter,T classReturn, Map<String, Object> configMap) {
		
		
		try {
			ProcessEngineConfigurationImpl processEngineConfiguration=Context.getProcessEngineConfiguration();
			interpreter.set("sysRulesConfig", Context.getProcessEngineConfiguration());
			interpreter.set("parameter", parameter);
			interpreter.set("sqlCommand", new SqlCommand(Context.getDbConnection()));
			if(configMap!=null){
				
				for (String mapKey : configMap.keySet()) {
					interpreter.set(mapKey, configMap.get(mapKey));
				}
				
			}
			Rule rule = processEngineConfiguration.getRule(ruleId);
			T returnObj =  (T)interpreter.eval(rule.getSqlValue());
			return returnObj;
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FixFlowException("表达式计算错误! 错误信息: " + e.getErrorText(), e);
		}
		
	}
	
	@Override
	public Object executeBusinessRules(String ruleId, Object parameter) {
		return executeBusinessRules(ruleId,parameter);
	}

	@Override
	public Object executeBusinessRules(String ruleId, Object parameter, Map<String, Object> configMap) {
		try {
			ProcessEngineConfigurationImpl processEngineConfiguration=Context.getProcessEngineConfiguration();
			interpreter.set("sysRulesConfig", Context.getProcessEngineConfiguration());
			interpreter.set("parameter", parameter);
			interpreter.set("sqlCommand", new SqlCommand(Context.getDbConnection()));
			if(configMap!=null){
				
				for (String mapKey : configMap.keySet()) {
					interpreter.set(mapKey, configMap.get(mapKey));
				}
				
			}
			Rule rule = processEngineConfiguration.getRule(ruleId);
			Object returnObj =  (Object)interpreter.eval(rule.getSqlValue());
			return returnObj;
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FixFlowException("表达式计算错误! 错误信息: " + e.getErrorText(), e);
		}
	}

}
