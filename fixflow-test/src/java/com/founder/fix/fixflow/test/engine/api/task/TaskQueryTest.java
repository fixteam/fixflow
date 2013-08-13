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
package com.founder.fix.fixflow.test.engine.api.task;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.task.QueryExpandTo;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class TaskQueryTest extends AbstractFixFlowTestCase {

	/**
	 * 测试任务实例扩展查询
	 * @throws InterruptedException 
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testTaskInstanceQueryExpandQuery(){
		//创建一个通用命令
		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		//设置流程名
		expandTaskCommand.setProcessDefinitionKey("TaskServiceNewTest");
		//设置流程的业务关联键
		expandTaskCommand.setBusinessKey("BK_testStartProcessInstanceByKey");
		//命令类型，可以从流程引擎配置中查询   启动并提交为startandsubmit
		expandTaskCommand.setCommandType("startandsubmit");
		//设置提交人
		expandTaskCommand.setInitiator("1200119390");
		//设置命令的id,需和节点上配置的按钮编号对应，会执行按钮中的脚本。
		expandTaskCommand.setUserCommandId("HandleCommand_2");
		//执行这个启动并提交的命令，返回启动的流程实例
		ProcessInstance processInstance = (ProcessInstance)taskService.expandTaskComplete(expandTaskCommand, null);
		String processInstanceId = processInstance.getId();
		//验证是否成功启动
		assertNotNull(processInstanceId);
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		/**********************本例子通过扩展查询的方法获取TaskServiceNewTest流程的nodeid = UserTask_1的任务实例***************************/
		QueryExpandTo queryExpandTo = new QueryExpandTo();
		//常见扩展查询的参数列表
		List<Object> whereSqlObj = new ArrayList<Object>();
		//添加扩展wheresql语句    (本例子扩展方法查询正在运行的流程实例)
		queryExpandTo.setWhereSql("NODE_ID = ?");
		//增加扩展wheresql的参数
		whereSqlObj.add("UserTask_1");
		queryExpandTo.setWhereSqlObj(whereSqlObj);
		//增加扩展查询
		taskQuery.queryExpandTo(queryExpandTo);
		//查询TaskServiceNewTest经过扩展查询后的结果
		taskQuery.processDefinitionKey("TaskServiceNewTest");
		List<TaskInstance> list = taskQuery.list();
		//验证是否为1个
		assertEquals(1, list.size());
		//获取得到的任务实例
		TaskInstance taskInstance = list.get(0);
		//验证得到的任务实例nodeid是否正确
		assertEquals("UserTask_1", taskInstance.getNodeId());
		
		/**********************本例子通过扩展查询的方法获取流程定义表中的procecss_name和resource_name字段***************************/
		//重置流程实例查询
		taskQuery = taskService.createTaskQuery();
		//创建扩展查询
		queryExpandTo = new QueryExpandTo();
		//添加扩展的字段
		queryExpandTo.setFieldSql("fixflow_def_processdefinition.PROCESS_NAME,fixflow_def_processdefinition.RESOURCE_NAME");
		//增加扩展查询的left join语句（taskinstance持久化层的别名是“T”所以连接时需要加上，否则可能出现“未明确定义列”）
		queryExpandTo.setLeftJoinSql("left join fixflow_def_processdefinition on T.processdefinition_id = fixflow_def_processdefinition.process_id");
		//增加扩展查询
		taskQuery.queryExpandTo(queryExpandTo);
		//查询TaskServiceNewTest经过扩展查询后的结果
		taskQuery.processDefinitionKey("TaskServiceNewTest");
		list = taskQuery.list();
		//获取第一个流程实例结果
		taskInstance = list.get(0);
		//获取扩展查询的字段
		String process_name = (String)taskInstance.getExtensionField("PROCESS_NAME");
		String RESOURCE_NAME = (String)taskInstance.getExtensionField("RESOURCE_NAME");
		//验证获得的扩展字段是否正确
		assertEquals("流程名称", process_name);
		assertEquals("com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn", RESOURCE_NAME);
	}
}
