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

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.ProcessInstanceQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;
/**
 * ProcessInstanceQuery的测试类
 */
public class ProcessInstanceQueryTest extends AbstractFixFlowTestCase {

	/**
	 * 测试流程实例查询
	 * @throws InterruptedException 
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceTest.bpmn"})
	public void testProcessInstanceQuery() throws InterruptedException{
		
		Date date = null;
		for(int i = 0;i<10;i++){
			//创建一个通用命令
			ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
			//设置流程名
			expandTaskCommand.setProcessDefinitionKey("Process_TaskServiceTest");
			//设置流程的业务关联键
			expandTaskCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
			//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
			expandTaskCommand.setCommandType("startandsubmit");
			//设置提交人
			expandTaskCommand.setInitiator("1200119390");
			//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
			expandTaskCommand.setUserCommandId("HandleCommand_2");
			
			
			Map<String, Object> mapVariables = new HashMap<String, Object>();
			//设置变量，流程线条上用到，amount<300时走独占任务，否则都共享任务
			int amount = 280+i;
			mapVariables.put("amount", amount);
			expandTaskCommand.setTransientVariables(mapVariables);
			//执行这个启动并提交的命令，返回启动的流程实例
			ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
			String processInstanceId = processInstance.getId();
			//取得第六个流程启动时的时间，用来验证大于或小于开始时间的查询
			if(i == 6){
				date = processInstance.getStartTime();
			}
			//验证是否成功启动
			assertNotNull(processInstanceId);
			//暂停1000毫秒，用来验证order by 
			Thread.sleep(1000);
		}
		//流程实例查询
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//根据流程定义key查询
		List<ProcessInstance> processInstances = processInstanceQuery.processDefinitionKey("Process_TaskServiceTest").list();
		//验证是10条流程实例
		assertEquals(10, processInstances.size());
		
		//重置流程实例查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//流程实例key的集合
		List<String> definitionKeys = new ArrayList<String>();
		definitionKeys.add("Process_TaskServiceTest");
		//根据key的集合查询实例
		processInstances = processInstanceQuery.processDefinitionKey(definitionKeys).list();
		//验证10条查询结果
		assertEquals(10, processInstances.size());
		
		//根据开始时间降序查询
		processInstanceQuery.orderByStartTime().desc();
		//获取第一页的0-5条结果
		processInstances = processInstanceQuery.listPage(0, 5);
		//获取第二页5-10条结果
		List<ProcessInstance> tmpProcessInstances = processInstanceQuery.listPage(5, 10);
		//取得第一条结果
		Date firstStartTime = processInstances.get(0).getStartTime();
		//验证比本页所有的时间都大，表示降序排序正确
		for(int i = 1;i < processInstances.size();i++){
			Date tmpStartTime = processInstances.get(i).getStartTime();
			assertTrue(firstStartTime.after(tmpStartTime));
		}
		//验证比第二页所有的时间都大，表示降序排序正确
		for(int i = 0;i<tmpProcessInstances.size();i++){
			Date tmpStartTime = tmpProcessInstances.get(i).getStartTime();
			assertTrue(firstStartTime.after(tmpStartTime));
		}
		
		//需要重置查询，因为order by 有叠加效果
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstanceQuery.processDefinitionKey(definitionKeys);
		//根据开始时间升序排序
		processInstanceQuery.orderByStartTime().asc();
		//获取第一页的0-5条结果
		processInstances = processInstanceQuery.listPage(0, 5);
		//获取第二页5-10条结果
		tmpProcessInstances = processInstanceQuery.listPage(5, 10);
		//取得第一条结果
		firstStartTime = processInstances.get(0).getStartTime();
		//验证比本页所有的时间都小，表示升序正确
		for(int i = 1;i < processInstances.size();i++){
			Date tmpStartTime = processInstances.get(i).getStartTime();
			assertTrue(firstStartTime.before(tmpStartTime));
		}
		//验证比第二页所有的时间都小，表示升序正确
		for(int i = 0;i<tmpProcessInstances.size();i++){
			Date tmpStartTime = tmpProcessInstances.get(i).getStartTime();
			assertTrue(firstStartTime.before(tmpStartTime));
		}
		
		
		//需要重置查询，因为order by 有叠加效果
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstanceQuery.processDefinitionKey(definitionKeys);
		//根据更新时间升序排序
		processInstanceQuery.orderByUpdateTime().asc();
		//获取第一页的0-5条结果
		processInstances = processInstanceQuery.listPage(0, 5);
		//获取第二页5-10条结果
		tmpProcessInstances = processInstanceQuery.listPage(5, 10);
		//取得第一条结果
		firstStartTime = processInstances.get(0).getUpdateTime();
		//验证比本页所有的时间都小，表示升序正确
		for(int i = 1;i < processInstances.size();i++){
			Date tmpStartTime = processInstances.get(i).getUpdateTime();
			assertTrue(firstStartTime.before(tmpStartTime));
		}
		//验证比第二页所有的时间都小，表示升序正确
		for(int i = 0;i<tmpProcessInstances.size();i++){
			Date tmpStartTime = tmpProcessInstances.get(i).getUpdateTime();
			assertTrue(firstStartTime.before(tmpStartTime));
		}
		
		//需要重置查询，因为order by 有叠加效果
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		processInstanceQuery.processDefinitionKey(definitionKeys);
		//根据更新时间降序序排序
		processInstanceQuery.orderByUpdateTime().desc();
		//获取第一页的0-5条结果
		processInstances = processInstanceQuery.listPage(0, 5);
		//获取第二页5-10条结果
		tmpProcessInstances = processInstanceQuery.listPage(5, 10);
		//取得第一条结果
		firstStartTime = processInstances.get(0).getUpdateTime();
		//验证比本页所有的时间都小，表示降序正确
		for(int i = 1;i < processInstances.size();i++){
			Date tmpStartTime = processInstances.get(i).getUpdateTime();
			assertTrue(firstStartTime.after(tmpStartTime));
		}
		//验证比第二页所有的时间都小，表示降序正确
		for(int i = 0;i<tmpProcessInstances.size();i++){
			Date tmpStartTime = tmpProcessInstances.get(i).getUpdateTime();
			assertTrue(firstStartTime.after(tmpStartTime));
		}
		
		
		//验证小于开始时间的查询
		//重置查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询开始时间小于某个时间的流程实例（时间在方法开始时定义）
		processInstances = processInstanceQuery.processDefinitionKey("Process_TaskServiceTest").startTimeBefore(date).list();
		//验证是否有4个
		assertEquals(4, processInstances.size());
		
		//验证大于开始时间的查询
		//重置查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询开始时间大于某个时间的流程实例（时间在方法开始时定义）
		processInstances = processInstanceQuery.processDefinitionKey("Process_TaskServiceTest").startTimeAfter(date).list();
		//验证是否有6个
		assertEquals(7, processInstances.size());
		
		//重置查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询未结束的流程实例
		processInstances = processInstanceQuery.processDefinitionKey("Process_TaskServiceTest").notEnd().list();
		//验证是否有10个
		assertEquals(10, processInstances.size());
		
		//重置查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询已结束的流程实例
		processInstances = processInstanceQuery.processDefinitionKey("Process_TaskServiceTest").isEnd().list();
		//验证是否有0个
		assertEquals(0, processInstances.size());
		
		
		//重置查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询对应bizKey的流程实例
		processInstances = processInstanceQuery.processDefinitionKey("Process_TaskServiceTest").processInstanceBusinessKey("BK_testStartProcessInstanceByKey").list();
		//验证是否有10个
		assertEquals(10, processInstances.size());
		
		//重置查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询对应bizKey的流程实例(方法2)
		processInstances = processInstanceQuery.processInstanceBusinessKey("BK_testStartProcessInstanceByKey","Process_TaskServiceTest").list();
		//验证是否有10个
		assertEquals(10, processInstances.size());
		
		//重置查询
		processInstanceQuery = runtimeService.createProcessInstanceQuery();
		//查询某人发起的流程实例
		processInstances = processInstanceQuery.processDefinitionKey("Process_TaskServiceTest").initiator("1200119390").list();
		//验证是否有10个
		assertEquals(10, processInstances.size());
		
	}

}
