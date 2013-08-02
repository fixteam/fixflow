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
package com.founder.fix.fixflow.expand.connector.JavaExecutor;


import java.lang.reflect.Method;

import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class JavaExecutor implements ConnectorHandler {

	private java.lang.String targetMethod;

	private boolean isStatic;

	private Object[] Params;
	
	private String targetClass;
	
	private Object result;

	public void execute(ExecutionContext executionContext) throws Exception {
		Class clazz= null;
		clazz = Class.forName(targetClass);
		
		int paramLength = Params==null?0:Params.length;
		Class[] paramCs = new Class[paramLength];
		for(int i=0;i<paramLength;i++){
			paramCs[i]=Params[i].getClass();
		}
		//获取到特定的方法，这里考虑到参数重载的可能
		Method method = clazz.getMethod(targetMethod, paramCs);
		Object obj = null;
		if(!isStatic){
			obj = clazz.newInstance();
		}
		result = method.invoke(null, Params);
	}
	
	public void setTargetClass(String tclass){
		targetClass = tclass;
	}

	public void  setTargetMethod(java.lang.String targetMethod){
		this.targetMethod = targetMethod;
	}

	public void  setIsStatic(boolean isStaic){
		this.isStatic = isStaic;
	}

	public void  setParams(Object[] Params){
		this.Params = Params;
	}

	public java.lang.Object  getOut(){
		return result ;
	}

}