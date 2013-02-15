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