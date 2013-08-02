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
 * @author yangchenhui
 */
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
 */
public class RunTimeServiceTest extends AbstractFixFlowTestCase {

	public static String processDefinitionId;
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
	 * 测试删除流程实例
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
	 * 测试获取流程实例
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
