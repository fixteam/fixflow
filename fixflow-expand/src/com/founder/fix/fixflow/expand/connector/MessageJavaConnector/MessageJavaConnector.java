package com.founder.fix.fixflow.expand.connector.MessageJavaConnector;


import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.founder.fix.apputil.exception.ExceptionFactory;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;

public class MessageJavaConnector implements ConnectorHandler {

	private java.lang.String javaClass;

	private java.lang.String javaMethod;

	private Object[] args;
	
	private String processId;
	
	private String taskId;
	
	private Timestamp beginDate;
	
	private Timestamp endDate;

	public void execute(ExecutionContext executionContext) throws Exception {
		beginDate = new Timestamp(new Date().getTime());
		taskId = executionContext.getToken().getFlowNode().getId();
		processId = executionContext.getProcessInstance().getId();
		executeMethod();
		endDate = new Timestamp(new Date().getTime());
		processInfo(executionContext.getSqlCommand());
	}

	public void  setJavaClass(java.lang.String javaClass){
		this.javaClass = javaClass;
	}

	public void  setJavaMethod(java.lang.String javaMethod){
		this.javaMethod = javaMethod;
	}

	public void  setArgs(Object[] args){
		this.args = args;
	}
	
	public void processInfo(SqlCommand sqlcommond){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("GUID", GuidUtil.CreateGuid());
		map.put("PROCESS_ID", processId);
		map.put("TASK_ID", taskId);
		map.put("BEGIN_DATE", beginDate);
		map.put("END_DATE", endDate);
		sqlcommond.insert("FIXFLOW_WORKDATE_TASK", map);
	}
	
	public void executeMethod() throws Exception{
		Object obj = null;
		//实例化需要的对象，并获取到特定的类对象。
		Class clazz= null;
		try{
			clazz = Class.forName(javaClass);
			obj = clazz.newInstance();
		}catch(ClassNotFoundException e){
			throw ExceptionFactory.createSystemFixException("20017",javaClass);
		}
		
		int paramLength = args==null?0:args.length;
		Class[] paramCs = new Class[paramLength];
		for(int i=0;i<paramLength;i++){
			paramCs[i]=args[i].getClass();
		}
		//获取到特定的方法，这里考虑到参数重载的可能
		Method method = clazz.getMethod(javaMethod, paramCs);
		Object[] params=new Object[paramLength];
		//反射调用方法，并将结果返回。
		method.invoke(obj, params);
	}

}