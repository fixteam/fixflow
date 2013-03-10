package com.founder.fix.fixflow.core.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Stack;


import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.impl.cache.CacheObject;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.scriptlanguage.AbstractScriptLanguageMgmt;

/**
 * @author kenshin
 */
public class Context {

	protected static ThreadLocal<Stack<Connection>> dbConnectionThreadLocal = new ThreadLocal<Stack<Connection>>();
	protected static ThreadLocal<Stack<CacheObject>> cacheObjectThreadLocal = new ThreadLocal<Stack<CacheObject>>();

	protected static ThreadLocal<Stack<CommandContext>> commandContextThreadLocal = new ThreadLocal<Stack<CommandContext>>();
	protected static ThreadLocal<Stack<ProcessEngineConfigurationImpl>> processEngineConfigurationStackThreadLocal = new ThreadLocal<Stack<ProcessEngineConfigurationImpl>>();
	
	//languageType
	protected static ThreadLocal<Stack<String>> languageTypeThreadLocal = new ThreadLocal<Stack<String>>();

	
	protected static ThreadLocal<Stack<Boolean>> isQuartzTransactionAutoThreadLocal = new ThreadLocal<Stack<Boolean>>();

	
	protected static ThreadLocal<Stack<AbstractScriptLanguageMgmt>> abstractScriptLanguageMgmtThreadLocal = new ThreadLocal<Stack<AbstractScriptLanguageMgmt>>();
	
	protected static ThreadLocal<Stack<Connection>> quartzCloseConnectionThreadLocal = new ThreadLocal<Stack<Connection>>();
	
	protected static ThreadLocal<Stack<Connection>> quartzCommitConnectionThreadLocal = new ThreadLocal<Stack<Connection>>();
	
	protected static ThreadLocal<Stack<Connection>> quartzRollbackConnectionThreadLocal = new ThreadLocal<Stack<Connection>>();
	
	
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
	
	public static AbstractScriptLanguageMgmt getAbstractScriptLanguageMgmt() {
		Stack<AbstractScriptLanguageMgmt> stack = getStack(abstractScriptLanguageMgmtThreadLocal);
		if (stack.isEmpty()) {
			return null;
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

		Stack<Connection> stack = getStack(dbConnectionThreadLocal);
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}

	public static void setDbConnection(Connection dbConnection) {
		getStack(dbConnectionThreadLocal).push(dbConnection);
	}
	
	
	public static Connection getQuartzCloseConnection() {

		Stack<Connection> stack = getStack(quartzCloseConnectionThreadLocal);
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}

	public static void setQuartzCloseConnection(Connection dbConnection) {
		getStack(quartzCloseConnectionThreadLocal).push(dbConnection);
	}
	
	public static void closeQuartzConnection(){
		Stack<Connection> connections = getStack(quartzCloseConnectionThreadLocal);

		for (int i = 0; i < connections.size(); i++) {
			if(connections.get(i)!=null){
				try {
					connections.get(i).close();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					throw new FixFlowDbException("quartz transaction close failure", e);
				}
			}
			
		}
	}
	
	
	public static Connection getQuartzCommitConnection() {

		Stack<Connection> stack = getStack(quartzCommitConnectionThreadLocal);
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}

	public static void setQuartzCommitConnection(Connection dbConnection) {
		getStack(quartzCommitConnectionThreadLocal).push(dbConnection);
	}
	
	public static void commitQuartzConnection(){
		Stack<Connection> connections = getStack(quartzCommitConnectionThreadLocal);

		for (int i = 0; i < connections.size(); i++) {
			if(connections.get(i)!=null){
				try {
					connections.get(i).commit();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					throw new FixFlowDbException("quartz transaction submit failure", e);
				}
			}
			
		}
	}
	
	public static Connection getQuartzRollbackConnection() {

		Stack<Connection> stack = getStack(quartzRollbackConnectionThreadLocal);
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}

	public static void setQuartzRollbackConnection(Connection dbConnection) {
		getStack(quartzRollbackConnectionThreadLocal).push(dbConnection);
	}

	
	public static void rollbackQuartzConnection(){
		Stack<Connection> connections = getStack(quartzRollbackConnectionThreadLocal);

		for (int i = 0; i < connections.size(); i++) {
			if(connections.get(i)!=null){
				try {
					connections.get(i).rollback();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					throw new FixFlowDbException("quartz transaction rollback failure", e);
				}
			}
			
		}
	}
	
	public static void removeQuartzCloseConnection() {
		getStack(quartzCloseConnectionThreadLocal).clear();
	}
	public static void removeQuartzCommitConnection() {
		getStack(quartzCommitConnectionThreadLocal).clear();
	}
	public static void removeQuartzRollbackConnection() {
		getStack(quartzRollbackConnectionThreadLocal).clear();
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
			return null;
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
