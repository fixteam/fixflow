package com.founder.fix.fixflow.test.engine.api.runtime;

import java.util.List;

import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

/**
 * 启动流程实例测试
 * 
 * @author jiang_nan
 * 
 */
public class RunTimeServiceTest extends AbstractFixFlowTestCase {

	public static String processDefinitionId;

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/StartProcessInstanceTest.bpmn" })
	/**
	 * 从一个流程key启动一个最新版本的流程实例
	 */
	public void testStartProcessInstanceByKey() {

		// 创建流程启动参数对象
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		// 设置流程启动所需要的流程Key(一个流程的key可能有多个不同的流程版本)
		startProcessInstanceCommand.setProcessDefinitionKey("Process_StartProcessInstanceTest");
		// 设置流程的业务关联值
		startProcessInstanceCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
		// 设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		// 启动流程,按照这个流程Key的最新版本
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		// 获取流程实例所使用的流程唯一编号
		processDefinitionId = processInstance.getProcessDefinitionId();

		// 验证流程实例是否为空
		assertNotNull(processInstance);

	}

	public void testStartProcessInstanceById() {

		// 创建流程启动参数对象
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		// 设置流程启动所需要的唯一流程ID
		startProcessInstanceCommand.setProcessDefinitionId(processDefinitionId);
		// 设置流程的业务关联值
		startProcessInstanceCommand.setBusinessKey("BK_testStartProcessInstanceById");
		// 设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		// 启动流程,按照这个通过唯一编号指定的流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		// 验证流程实例是否为空
		assertNotNull(processInstance);

	}

	/**
	 * 查询流程实例的数量
	 */
	public void testProcessInstanceCountQuery() {

		// 创建流程启动参数对象
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		// 设置流程启动所需要的唯一流程ID
		startProcessInstanceCommand.setProcessDefinitionId(processDefinitionId);
		// 设置流程的业务关联值
		startProcessInstanceCommand.setBusinessKey("testProcessInstanceQuery_1");
		// 设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		// 启动流程,按照这个通过唯一编号指定的流程
		ProcessInstance processInstance_1 = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		assertNotNull(processInstance_1);
		startProcessInstanceCommand.setBusinessKey("testProcessInstanceQuery_2");
		// 启动流程,按照这个通过唯一编号指定的流程
		ProcessInstance processInstance_2 = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		assertNotNull(processInstance_2);
		// 通过指定的流程唯一编号查询流程实例的数量
		long processInstanceCount = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinitionId).count();
		// 总共启动了两个流程实例
		assertEquals(processInstanceCount, 2);

		// 通过流程定义Key查询
		processInstanceCount = runtimeService.createProcessInstanceQuery().processDefinitionKey("Process_StartProcessInstanceTest").count();
		// 总共启动了两个流程实例
		assertEquals(processInstanceCount, 2);

	}

	/**
	 * 查询流程实例对象
	 */
	public void testProcessInstanceQuery() {

		// 创建流程启动参数对象
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		// 设置流程启动所需要的唯一流程ID
		startProcessInstanceCommand.setProcessDefinitionId(processDefinitionId);
		// 设置流程的业务关联值
		startProcessInstanceCommand.setBusinessKey("testProcessInstanceQuery_1");
		// 设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		// 启动流程,按照这个通过唯一编号指定的流程
		ProcessInstance processInstance_1 = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		assertNotNull(processInstance_1);
		startProcessInstanceCommand.setBusinessKey("testProcessInstanceQuery_2");
		// 启动流程,按照这个通过唯一编号指定的流程
		ProcessInstance processInstance_2 = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		assertNotNull(processInstance_2);

		// 指定流程唯一编号查询
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinitionId);
		// 再之前的基础之上再通过业务关联键查询
		processInstanceQuery.processInstanceBusinessKey("testProcessInstanceQuery_1");
		// 执行查询返回结果集
		List<ProcessInstance> processInstanceQueryTos = processInstanceQuery.list();
		assertEquals(processInstanceQueryTos.size(), 1);
	}

	/**
	 * 分页查询流程实例对象
	 */
	public void testProcessInstancePaginationQuery() {

		// 创建流程启动参数对象
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		// 设置流程启动所需要的唯一流程ID
		startProcessInstanceCommand.setProcessDefinitionId(processDefinitionId);
		// 设置流程的业务关联值
		startProcessInstanceCommand.setBusinessKey("testProcessInstanceQuery_1");
		// 设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		// 启动流程,按照这个通过唯一编号指定的流程
		ProcessInstance processInstance_1 = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		assertNotNull(processInstance_1);
		startProcessInstanceCommand.setBusinessKey("testProcessInstanceQuery_2");
		// 启动流程,按照这个通过唯一编号指定的流程
		ProcessInstance processInstance_2 = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		assertNotNull(processInstance_2);

		// 指定流程唯一编号查询
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinitionId);
		// 执行分页查询返回结果集
		List<ProcessInstance> processInstanceQueryTos = processInstanceQuery.listPage(1, 2);
		assertEquals(processInstanceQueryTos.size(), 2);

	}

}
