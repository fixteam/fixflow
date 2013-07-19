package com.founder.fix.fixflow.test.engine.api.runtime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.exception.FixFlowException;
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
	
	/**
	 * 测试手工启动流程
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/StartProcessInstanceTest.bpmn" })
	public void testNoneStartProcessInstance(){
		//创建一个启动命令
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		//设置需要启动的流程的KEY
		startProcessInstanceCommand.setProcessDefinitionKey("Process_StartProcessInstanceTest");
		//设置业务关联键
		startProcessInstanceCommand.setBusinessKey("bk_StartProcessInstanceTest");
		//设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		//启动流程，返回流程实例
		ProcessInstance processInstance = runtimeService.noneStartProcessInstance(startProcessInstanceCommand);
		//验证流程是否启动成功
		assertNotNull(processInstance);
	}
	/**
	 * 测试手工启动流程
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/StartProcessInstanceTest.bpmn" })
	public void testDeleteProcessInstance(){
		//创建一个启动命令
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		//设置需要启动的流程的KEY
		startProcessInstanceCommand.setProcessDefinitionKey("Process_StartProcessInstanceTest");
		//设置业务关联键
		startProcessInstanceCommand.setBusinessKey("bk_StartProcessInstanceTest");
		//设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		//启动流程，返回流程实例
		ProcessInstance processInstance = runtimeService.noneStartProcessInstance(startProcessInstanceCommand);
		//验证流程是否启动成功
		assertNotNull(processInstance);
		
		//获得流程实例号
		String processInstanceId = processInstance.getId();
		//删除流程实例
		runtimeService.deleteProcessInstance(processInstanceId, true);
		//删除后再查询此流程实例，则抛出异常，说明流程实例已经被删除
		try{
			processInstance = runtimeService.getProcessInstance(processInstanceId);
			fail();
		}catch(Exception ex){
			//抛出FixFlowException异常
			assertTrue(ex instanceof FixFlowException);
		}
	}
	
	/**
	 * 测试手工启动流程
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/StartProcessInstanceTest.bpmn" })
	public void testGetProcessInstance(){
		try{
			//传一个不存的流程实例ID，则会抛出FixFlowException异常
			ProcessInstance processInstance = runtimeService.getProcessInstance("anbccbdbbddcdd");
			fail();
		}catch(Exception ex){
			//抛出FixFlowException异常
			assertTrue(ex instanceof FixFlowException);
		}
		
		//创建一个启动命令
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		//设置需要启动的流程的KEY
		startProcessInstanceCommand.setProcessDefinitionKey("Process_StartProcessInstanceTest");
		//设置业务关联键
		startProcessInstanceCommand.setBusinessKey("bk_StartProcessInstanceTest");
		//设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		//启动流程，返回流程实例
		ProcessInstance processInstance = runtimeService.noneStartProcessInstance(startProcessInstanceCommand);
		//验证流程是否启动成功
		assertNotNull(processInstance);
		
		//获得流程实例号
		String processInstanceId = processInstance.getId();
		//根据实例号查询到此流程实例
		processInstance=runtimeService.getProcessInstance(processInstanceId);
		//验证是否查询到此流程实例
		assertNotNull(processInstance);
	}
	
	/**
	 * 测试创建流程查询实例
	 */
	public void testCreateProcessInstanceQuery(){
		//创建查询实例
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//验证是否创建成功
		assertNotNull(processInstanceQuery);
	}
	/**
	 * 测试更新流程关联键,----此方法暂未实现
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/StartProcessInstanceTest.bpmn" })
	public void testUpdateProcessInstanceBusinessKey(){
		//创建一个启动命令
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		//设置需要启动的流程的KEY
		startProcessInstanceCommand.setProcessDefinitionKey("Process_StartProcessInstanceTest");
		//设置业务关联键
		startProcessInstanceCommand.setBusinessKey("bk_StartProcessInstanceTest");
		//设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		//启动流程，返回流程实例
		ProcessInstance processInstance = runtimeService.noneStartProcessInstance(startProcessInstanceCommand);
		//验证流程是否启动成功
		assertNotNull(processInstance);
		
		//获得流程实例号
		String processInstanceId = processInstance.getId();
		//将此流程实例的业务关联键更新为"bk_newbk"此方法暂未实现
		runtimeService.updateProcessInstanceBusinessKey(processInstanceId, "bk_newbk");
		processInstance = runtimeService.getProcessInstance(processInstanceId);
		//此方法暂未实现
		//assertEquals(processInstance.getBizKey(), "bk_newbk");
	}
	/**
	 * 测试增加流程实例持久化变量
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/StartProcessInstanceTest.bpmn" })
	public void testSetAndGetProcessInstanceVariable(){
		//创建一个启动命令
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		//设置需要启动的流程的KEY
		startProcessInstanceCommand.setProcessDefinitionKey("Process_StartProcessInstanceTest");
		//设置业务关联键
		startProcessInstanceCommand.setBusinessKey("bk_StartProcessInstanceTest");
		//设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		//启动流程，返回流程实例
		ProcessInstance processInstance = runtimeService.noneStartProcessInstance(startProcessInstanceCommand);
		//验证流程是否启动成功
		assertNotNull(processInstance);
		
		//获得流程实例号
		String processInstanceId = processInstance.getId();
		//给流程实例添加一个名为“流程变量名称”值为“测试流程变量值”
		runtimeService.setProcessInstanceVariable(processInstanceId, "流程变量名称", "测试流程变量值");
		//获取“流程变量名称”这个持久化变量的值
		String returnValue = (String)runtimeService.getProcessInstanceVariable(processInstanceId, "流程变量名称");
		//验证是否是刚添加的值
		assertEquals("测试流程变量值", returnValue);
	}
	
	/**
	 * 测试增加流程实例持久化变量map
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/runtime/StartProcessInstanceTest.bpmn" })
	public void testSetAndGetProcessInstanceVariables(){
		//创建一个启动命令
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		//设置需要启动的流程的KEY
		startProcessInstanceCommand.setProcessDefinitionKey("Process_StartProcessInstanceTest");
		//设置业务关联键
		startProcessInstanceCommand.setBusinessKey("bk_StartProcessInstanceTest");
		//设置流程的启动人
		startProcessInstanceCommand.setStartAuthor("1200119390");
		//启动流程，返回流程实例
		ProcessInstance processInstance = runtimeService.noneStartProcessInstance(startProcessInstanceCommand);
		//验证流程是否启动成功
		assertNotNull(processInstance);
		
		//获得流程实例号
		String processInstanceId = processInstance.getId();
		//新建一个变量的map
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("变量1", "变量1的值");
		paramsMap.put("变量2", "变量2的值");
		runtimeService.setProcessInstanceVariables(processInstanceId, paramsMap);
		//获取这个持久化变量的值map
		Map<String,Object> returnMap = (Map<String,Object>)runtimeService.getProcessInstanceVariables(processInstanceId);
		//验证是否是刚添加的值
		assertEquals(2,returnMap.keySet().size());
		assertEquals("变量1的值", returnMap.get("变量1"));
		assertEquals("变量2的值", returnMap.get("变量2"));
	}
}
