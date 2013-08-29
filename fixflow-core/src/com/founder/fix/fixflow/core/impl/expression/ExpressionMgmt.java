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
package com.founder.fix.fixflow.core.impl.expression;


import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;



public class ExpressionMgmt {

	
	public static Object execute(String scriptText) {

		return Context.getAbstractScriptLanguageMgmt().execute(scriptText);
		
	}

	public static Object execute(String scriptText, ExecutionContext executionContext) {

		return Context.getAbstractScriptLanguageMgmt().execute(scriptText, executionContext);
		
	}

	public static Object getVariable(String variableName) {
		return Context.getAbstractScriptLanguageMgmt().getVariable(variableName);
		
	}
	
	public static void setVariable(String variableName, Object variableObj,ExecutionContext executionContext) {

		Context.getAbstractScriptLanguageMgmt().setVariable(variableName,variableObj,executionContext);
		
	}
	

	public static void setVariable(String variableName, Object variableObj) {


		Context.getAbstractScriptLanguageMgmt().setVariable(variableName,variableObj);
		
	}

	public static Object execute(String scriptText, ProcessDefinitionBehavior processDefinition) {
		return Context.getAbstractScriptLanguageMgmt().execute(scriptText,processDefinition);
		
	}

	

}
