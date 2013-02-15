package com.founder.fix.fixflow.core.impl.job;

import java.sql.Connection;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.founder.fix.bpmn2extensions.coreconfig.DataBase;
import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.RuntimeService;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.ExternalContent;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
/**
 * 流程启动测试类
 * @author kenshin
 *
 */
public class FlowStart {

	/**
	 * 启动流程 事务由外部控制
	 * @param processDefinitionKey 流程编号
	 * @param processDefinitionId 流程唯一标识
	 * @param connection 数据库连接
	 * @return 流程实例对象
	 */
	public static ProcessInstance start(String processDefinitionKey, String processDefinitionId, Connection connection) {

		System.out.println("=====定时任务启动 " + new Date() + " =====");

		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		// 如果是找最新流程启动就用这个
		if (processDefinitionKey != null || processDefinitionId != null) {
			if (processDefinitionKey != null) {
				startProcessInstanceCommand.setProcessDefinitionKey(processDefinitionKey);
			} else {
				startProcessInstanceCommand.setProcessDefinitionId(processDefinitionId);
			}
		} else {
			throw new FixFlowException("processDefinitionKey和processDefinitionId不能都为空!");
		}
		
		//startProcessInstanceCommand.setBusinessKey(businessKey);
		startProcessInstanceCommand.setStartAuthor(Authentication.getAdminId());

		// 瞬态变量
		Map<String, Object> transientVariableMap = new HashMap<String, Object>();
		// 设定瞬态变量
		startProcessInstanceCommand.setTransientVariables(transientVariableMap);

		// 获取流程引擎
		ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();
		
		
		

		// 获取流程当前配置的数据库
		DataBase dataBase = processEngine.getProcessEngineConfiguration().getSelectedDatabase();

		//创建流程线程副本对象
		ExternalContent externalContent = new ExternalContent();
		//设置操作人
		externalContent.setAuthenticatedUserId(Authentication.getAdminId());
		//设置数据库连接
		externalContent.setConnection(connection);
		//将线程副本对象放置在引擎内
		processEngine.setExternalContent(externalContent);
		//获取运行时操作对象
		RuntimeService runtimeService = processEngine.getRuntimeService();
		//启动流程实例
		ProcessInstance processInstance=runtimeService.noneStartProcessInstance(startProcessInstanceCommand);


		System.out.println("定时任务启动成功");
		
		return processInstance;

	}

}
