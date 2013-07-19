package com.founder.fix.fixflow.core.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguage;
import com.founder.fix.bpmn2extensions.coreconfig.ScriptLanguageConfig;
import com.founder.fix.fixflow.core.ConnectionManagement;
import com.founder.fix.fixflow.core.impl.cache.CacheObject;
import com.founder.fix.fixflow.core.impl.db.FixConnectionResult;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.scriptlanguage.AbstractScriptLanguageMgmt;

/**
 * @author kenshin
 */
public class Context {

	protected static ThreadLocal<Stack<Map<String, FixConnectionResult>>> dbConnectionThreadLocal = new ThreadLocal<Stack<Map<String, FixConnectionResult>>>();
	protected static ThreadLocal<Stack<CacheObject>> cacheObjectThreadLocal = new ThreadLocal<Stack<CacheObject>>();

	protected static ThreadLocal<Stack<CommandContext>> commandContextThreadLocal = new ThreadLocal<Stack<CommandContext>>();
	protected static ThreadLocal<Stack<ProcessEngineConfigurationImpl>> processEngineConfigurationStackThreadLocal = new ThreadLocal<Stack<ProcessEngineConfigurationImpl>>();
	
	//languageType
	protected static ThreadLocal<Stack<String>> languageTypeThreadLocal = new ThreadLocal<Stack<String>>();

	
	
	protected static ThreadLocal<Stack<AbstractScriptLanguageMgmt>> abstractScriptLanguageMgmtThreadLocal = new ThreadLocal<Stack<AbstractScriptLanguageMgmt>>();
	
	protected static ThreadLocal<Stack<Boolean>> isQuartzTransactionAutoThreadLocal = new ThreadLocal<Stack<Boolean>>();

	
	
	//protected static ThreadLocal<Stack<Interpreter>> bshInterpreterThreadLocal = new ThreadLocal<Stack<Interpreter>>();
	public static void setLanguageType(String languageType) {
		getStack(languageTypeThreadLocal).push(languageType);
	}
	
	public static String getLanguageType() {

		Stack<String> stack = getStack(languageTypeThreadLocal);
		if (stack.isEmpty()) {
			return "defauld";
		}
	
		String languageTypeTemp=stack.peek();
		if(languageTypeTemp==null||languageTypeTemp.equals("")){
			return "defauld";
		}else{
			return stack.peek();
			
		}
		
	}
	
	
	public static void removeLanguageType() {

		getStack(languageTypeThreadLocal).clear();
	}
	
	
	public static void removeQuartzTransactionAutoThreadLocal() {

		getStack(isQuartzTransactionAutoThreadLocal).clear();
	}
	
	public static AbstractScriptLanguageMgmt getAbstractScriptLanguageMgmt() {
		Stack<AbstractScriptLanguageMgmt> stack = getStack(abstractScriptLanguageMgmtThreadLocal);
		if (stack.isEmpty()) {
			

			AbstractScriptLanguageMgmt abstractScriptLanguageMgmt=null;
			ScriptLanguageConfig scriptLanguageConfig=getProcessEngineConfiguration().getScriptLanguageConfig();
			for (ScriptLanguage scriptLanguage : scriptLanguageConfig.getScriptLanguage()) {
				if(scriptLanguage.getId().equals(scriptLanguageConfig.getSelected())){
					abstractScriptLanguageMgmt= (AbstractScriptLanguageMgmt)ReflectUtil.instantiate(scriptLanguage.getClassImpl());
					break;
				}
			}
			
			
			
			Context.setAbstractScriptLanguageMgmt(abstractScriptLanguageMgmt.init());

			return abstractScriptLanguageMgmt;
		}
		return stack.peek();
	}

	public static void setAbstractScriptLanguageMgmt(AbstractScriptLanguageMgmt abstractScriptLanguageMgmt) {
		getStack(abstractScriptLanguageMgmtThreadLocal).push(abstractScriptLanguageMgmt);
	}

	public static CacheObject getCacheObject() {
		Stack<CacheObject> stack = getStack(cacheObjectThreadLocal);
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}

	public static void setCacheObject(CacheObject cacheObject) {
		getStack(cacheObjectThreadLocal).push(cacheObject);
	}
	

	public static void setQuartzTransactionAuto(boolean isAuto) {
		
		
		getStack(isQuartzTransactionAutoThreadLocal).push(isAuto);
	}
	public static boolean isQuartzTransactionAuto() {

		Stack<Boolean> stack = getStack(isQuartzTransactionAutoThreadLocal);
		if (stack.isEmpty()) {
			return true;
		}
		return stack.peek();
	}
	


	public static Connection getDbConnection() {
		
		String dbID=ConnectionManagement.defaultDataBaseId;
		
		return getDbConnection(dbID);

	}
	
	
	public static Connection getDbConnection(String dbId) {

		Stack<Map<String, FixConnectionResult>> stack = getStack(dbConnectionThreadLocal);
		if (stack.isEmpty()) {
			
			Map<String, FixConnectionResult> connectioMap=new HashMap<String, FixConnectionResult>();
			
		
			
			FixConnectionResult fixConnectionResult=ConnectionManagement.INSTANCE().getFixConnectionResult(dbId);
			
			connectioMap.put(dbId, fixConnectionResult);
			
			getStack(dbConnectionThreadLocal).push(connectioMap);
			
			fixConnectionResult.openConnection();

			return fixConnectionResult.getConnection();
			
		}

		Map<String, FixConnectionResult> connMap= stack.peek();
		if(connMap==null){
			Map<String, FixConnectionResult> connectioMap=new HashMap<String, FixConnectionResult>();
			
		
			
			FixConnectionResult fixConnectionResult=ConnectionManagement.INSTANCE().getFixConnectionResult(dbId);
			
			connectioMap.put(dbId, fixConnectionResult);
			
			getStack(dbConnectionThreadLocal).push(connectioMap);
			
			fixConnectionResult.openConnection();

			return fixConnectionResult.getConnection();
		}
		else{
			
		
			FixConnectionResult fixConnectionResult=connMap.get(dbId);
			
			if(fixConnectionResult==null){
				
				FixConnectionResult fixConnectionResultObj=ConnectionManagement.INSTANCE().getFixConnectionResult(dbId);
				
				connMap.put(dbId, fixConnectionResultObj);

				fixConnectionResultObj.openConnection();
				
				return fixConnectionResultObj.getConnection();
				
			}
			else{
				
				//fixConnectionResult.openConnection();

				return fixConnectionResult.getConnection();
			}
		}
		
		
	}
	
	public static Map<String, FixConnectionResult> getDbConnectionMap() {

		Stack<Map<String, FixConnectionResult>> stack = getStack(dbConnectionThreadLocal);
		if (stack.isEmpty()) {
			return null;
		}

		Map<String, FixConnectionResult> connMap= stack.peek();
		if(connMap==null){
			return null;
		}
		else{
			return connMap;
		}
		
		
	}

	public static void setFixConnectionResult(String dbID,FixConnectionResult fixConnectionResult) {
		
		
		
		Stack<Map<String, FixConnectionResult>> stack = getStack(dbConnectionThreadLocal);
		if (stack.isEmpty()) {
			Map<String, FixConnectionResult> conMap=new HashMap<String, FixConnectionResult>();
			conMap.put(dbID, fixConnectionResult);
			
			getStack(dbConnectionThreadLocal).push(conMap);
		}else{
			Map<String, FixConnectionResult> connMap= stack.peek();
			if(connMap==null){
				Map<String, FixConnectionResult> conMap=new HashMap<String, FixConnectionResult>();
				conMap.put(dbID, fixConnectionResult);
				
				getStack(dbConnectionThreadLocal).push(conMap);
			}else{
				connMap.put(dbID, fixConnectionResult);
			}
		}

	}
	
	
	public static void setFixConnectionResult(FixConnectionResult fixConnectionResult) {
		
	
		String dbID=ConnectionManagement.defaultDataBaseId;
		
		
		setFixConnectionResult(dbID, fixConnectionResult);

	}
	
	
	

	
	

	public static void removeDbConnection() {

		getStack(dbConnectionThreadLocal).clear();

	}

	public static CommandContext getCommandContext() {
		Stack<CommandContext> stack = getStack(commandContextThreadLocal);
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}
	
	

	public static void setCommandContext(CommandContext commandContext) {
		getStack(commandContextThreadLocal).push(commandContext);
	}

	public static void removeCommandContext() {
		getStack(commandContextThreadLocal).clear();
	}

	public static void removeAbstractScriptLanguageMgmt() {

		Stack<AbstractScriptLanguageMgmt> abstractScriptLanguageMgmts = getStack(abstractScriptLanguageMgmtThreadLocal);

		for (int i = 0; i < abstractScriptLanguageMgmts.size(); i++) {
			abstractScriptLanguageMgmts.get(i).close();
		}
		abstractScriptLanguageMgmts.clear();
	}

	public static ProcessEngineConfigurationImpl getProcessEngineConfiguration() {
		Stack<ProcessEngineConfigurationImpl> stack = getStack(processEngineConfigurationStackThreadLocal);
		if (stack.isEmpty()) {
			
			//setProcessEngineConfiguration(ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration());
			
			return null;//ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration();
		}
		return stack.peek();
	}

	public static void setProcessEngineConfiguration(ProcessEngineConfigurationImpl processEngineConfiguration) {
		getStack(processEngineConfigurationStackThreadLocal).push(processEngineConfiguration);
	}

	public static void removeProcessEngineConfiguration() {
		getStack(processEngineConfigurationStackThreadLocal).clear();
	}

	protected static <T> Stack<T> getStack(ThreadLocal<Stack<T>> threadLocal) {
		Stack<T> stack = threadLocal.get();
		if (stack == null) {
			stack = new Stack<T>();
			threadLocal.set(stack);
		}
		return stack;
	}
}
