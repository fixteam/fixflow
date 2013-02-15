package com.founder.fix.fixflow.expand.scriptlanguage;

import groovy.lang.GroovyShell;

import java.util.List;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DataVariableBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
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

}
