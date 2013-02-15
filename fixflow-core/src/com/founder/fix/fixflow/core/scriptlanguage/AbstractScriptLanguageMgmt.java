package com.founder.fix.fixflow.core.scriptlanguage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.DataVariableBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableInstance;
import com.founder.fix.fixflow.core.impl.datavariable.DataVariableMgmtInstance;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public abstract class AbstractScriptLanguageMgmt {
	
	
	
	
	
	
	
	/**
	 * 执行表达式,对于没有流程上下文环境的时候,执行表达式的时候需要传入流程定义。
	 * @param scriptText 表达式文本
	 * @param processDefinition 流程定义
	 * @return
	 */
	public abstract Object execute(String scriptText, ProcessDefinitionBehavior processDefinition);
	
	/**
	 * 向表达式环境中放入变量
	 * @param variableName 变量名称
	 * @param variableObj 变量值
	 */
	public abstract void setVariable(String variableName, Object variableObj);
	
	/**
	 * 向表达式环境中放入变量,这个变量将是流程变量${var}
	 * @param variableName 变量名称
	 * @param variableObj 变量值
	 * @param executionContext 流程上下文
	 */
	public abstract void setVariable(String variableName, Object variableObj,ExecutionContext executionContext);
	
	/**
	 * 获取变量值
	 * @param variableName 变量名称
	 * @return
	 */
	public abstract Object getVariable(String variableName);
	
	/**
	 * 执行表达式
	 * @param scriptText 表达式字符串
	 * @param executionContext 流程上下文
	 * @return
	 */
	public abstract Object execute(String scriptText, ExecutionContext executionContext);
	
	/**
	 * 脚本管理器初始化方法
	 */
	public abstract AbstractScriptLanguageMgmt init();
	
	public abstract void close();
	
	public static List<String> getDataVariableList(String scriptText) {
		String inexp = scriptText;
		// ${test} afdfs ${test1}erewr ${test3}
		String regex = "\\$\\{[^}{]+\\}";
		Pattern regexExpType = Pattern.compile(regex);
		Matcher mType = regexExpType.matcher(inexp);
		String expType = inexp;
		List<String> list = new ArrayList<String>();
		while (mType.find()) {
			expType = mType.group();
			expType = expType.substring(2, expType.length() - 1);
			list.add(expType);
		}
		return list;
	}

	private static String __REGEX_SIGNS = "\\$\\{[^}{]+\\}";

	public static String getExpressionAll(String inexp) {
		String str = null;
		String regex = __REGEX_SIGNS;
		Pattern regexExpType = Pattern.compile(regex);
		Matcher mType = regexExpType.matcher(inexp);
		String expType = inexp;
		StringBuffer sb = new StringBuffer();
		while (mType.find()) {
			expType = mType.group();
			String dist = expType.substring(2, expType.length() - 1); // StringUtil.getString(getExpressionValue(dataView,expType));
			mType.appendReplacement(sb, dist);
		}
		mType.appendTail(sb);
		str = sb.toString();
		return str;
	}
	
	
	public static void dataVariableCalculate(String scriptText, ExecutionContext executionContext){
		DataVariableMgmtInstance dataVariableMgmtInstance = executionContext.getProcessInstance().getDataVariableMgmtInstance();

		List<String> dataVariableList = getDataVariableList(scriptText);

		for (String expressionId : dataVariableList) {

			if (dataVariableMgmtInstance.getDataVariableByExpressionId(expressionId) == null) {

				List<DataVariableBehavior> dataVariableBehaviors = dataVariableMgmtInstance.getProcessInstance().getProcessDefinition().getDataVariableMgmtDefinition()
						.getDataVariableBehaviorsByProcess();
				for (DataVariableBehavior dataVariableBehavior : dataVariableBehaviors) {

					if (dataVariableBehavior.getId().equals(expressionId)) {

						DataVariableInstance dataVariableInstance = dataVariableMgmtInstance.createDataVariableInstance(dataVariableBehavior);
						dataVariableInstance.executeExpression(executionContext);

					}

				}

			}

		}

	}
	

	
	

}
