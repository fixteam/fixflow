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

import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.IncludeExclusion;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskInstanceType;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

public class ExternalTasksTest extends AbstractFixFlowTestCase {

	// 添加一个任务
	public void testAddExternalTask() {

		// 创建一个guid
		String taskIdString = GuidUtil.CreateGuid();
		// 创建一个任务(创建任务的时候createTime时间已经被自动设定)
		TaskInstance taskInstance = taskService.newTask(taskIdString);
		// 设置任务的独占处理者
		taskInstance.setAssignee("testAddExternalTask_admin");
		// 设置任务的表单
		taskInstance.setFormUri("http://www.google.com");
		// 设置任务关联键值
		taskInstance.setBizKey("1234567890");
		// 设置任务的主题
		taskInstance.setDescription("XXX系统的代办任务");
		// 设置任务的优先级
		taskInstance.setPriority(TaskInstance.PRIORITY_NORMAL);
		// 设置任务的类型,其他系统的异构流程引擎
		taskInstance.setTaskInstanceType(TaskInstanceType.OTHERBPMTASK);
		// 保存新创建的任务
		taskService.saveTask(taskInstance);

		TaskQuery taskQuery = taskService.createTaskQuery();
		
		taskQuery.taskAssignee("testAddExternalTask_admin");
		taskQuery.addTaskType(TaskInstanceType.OTHERBPMTASK);

		TaskInstance taskInstanceReturn = taskQuery.singleResult();

		assertNotNull(taskInstanceReturn);

	}

	// 任务持久化变量测试
	public void testTaskVariable() {

		// 创建一个guid
		String taskIdString = GuidUtil.CreateGuid();
		// 创建一个任务(创建任务的时候createTime时间已经被自动设定)
		TaskInstance taskInstance = taskService.newTask(taskIdString);
		// 设置任务的独占处理者
		taskInstance.setAssignee("testAddExternalTask_admin");
		// 设置任务的表单
		taskInstance.setFormUri("http://www.google.com");
		// 设置任务关联键值
		taskInstance.setBizKey("1234567890");
		// 设置任务的主题
		taskInstance.setDescription("XXX系统的代办任务");
		// 设置任务的优先级
		taskInstance.setPriority(TaskInstance.PRIORITY_NORMAL);
		// 设置任务的类型,其他系统的异构流程引擎
		taskInstance.setTaskInstanceType(TaskInstanceType.OTHERBPMTASK);

		// 保存新创建的任务
		taskService.saveTask(taskInstance);

		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.taskAssignee("testAddExternalTask_admin");
		taskQuery.addTaskType(TaskInstanceType.OTHERBPMTASK);

		TaskInstance taskInstanceReturn = taskQuery.singleResult();

		assertNotNull(taskInstanceReturn);

		/* 任务变量 */

		// 为任务创建变量
		taskService.setVariable(taskInstanceReturn.getId(), "变量", "变量值");
		// 查询任务的变量
		Object returnValue = taskService.getVariable(taskInstanceReturn.getId(), "变量");
		// 将取回的变量值比较
		assertEquals(returnValue, "变量值");

		/* 流程实例变量 */

	}

	/**
	 * 删除任务
	 */
	public void testDeleteExternalTask() {
		// 创建一个guid
		String taskIdString = GuidUtil.CreateGuid();
		// 创建一个任务(创建任务的时候createTime时间已经被自动设定)
		TaskInstance taskInstance = taskService.newTask(taskIdString);
		// 设置任务的独占处理者
		taskInstance.setAssignee("testAddExternalTask_admin");
		// 设置任务的表单
		taskInstance.setFormUri("http://www.google.com");
		// 设置任务关联键值
		taskInstance.setBizKey("1234567890");
		// 设置任务的主题
		taskInstance.setDescription("XXX系统的代办任务");
		// 设置任务的优先级
		taskInstance.setPriority(TaskInstance.PRIORITY_NORMAL);
		// 设置任务的类型,其他系统的异构流程引擎
		taskInstance.setTaskInstanceType(TaskInstanceType.OTHERBPMTASK);

		// 保存新创建的任务
		taskService.saveTask(taskInstance);

		// 查询任务
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.taskAssignee("testAddExternalTask_admin");
		taskQuery.addTaskType(TaskInstanceType.OTHERBPMTASK);

		TaskInstance taskInstanceReturn = taskQuery.singleResult();

		assertNotNull(taskInstanceReturn);

		// 级联删除任务信息
		taskService.deleteTask(taskInstanceReturn.getId(), true);

		// 再次查询任务

		// 查询任务
		taskQuery = taskService.createTaskQuery();
		taskQuery.taskAssignee("testAddExternalTask_admin");
		taskQuery.addTaskType(TaskInstanceType.OTHERBPMTASK);
		long rowNum = taskQuery.count();
		// 任务没有了
		assertEquals(rowNum, 0);
	}

	/**
	 * 更新任务
	 */
	public void testUpadteExternalTask() {

		// 创建一个guid
		String taskIdString = GuidUtil.CreateGuid();
		// 创建一个任务(创建任务的时候createTime时间已经被自动设定)
		TaskInstance taskInstance = taskService.newTask(taskIdString);
		// 设置任务的独占处理者
		taskInstance.setAssignee("testAddExternalTask_admin");
		// 设置任务的表单
		taskInstance.setFormUri("http://www.google.com");
		// 设置任务关联键值
		taskInstance.setBizKey("1234567890");
		// 设置任务的主题
		taskInstance.setDescription("XXX系统的代办任务");
		// 设置任务的优先级
		taskInstance.setPriority(TaskInstance.PRIORITY_NORMAL);
		// 设置任务的类型,其他系统的异构流程引擎
		taskInstance.setTaskInstanceType(TaskInstanceType.OTHERBPMTASK);

		// 保存新创建的任务
		taskService.saveTask(taskInstance);

		// 查询任务
		TaskQuery taskQuery = taskService.createTaskQuery();
		taskQuery.taskAssignee("testAddExternalTask_admin");
		taskQuery.addTaskType(TaskInstanceType.OTHERBPMTASK);

		TaskInstance taskInstanceReturn = taskQuery.singleResult();

		assertNotNull(taskInstanceReturn);

		// 将任务处理者换为testAddExternalTask_test
		taskInstanceReturn.setAssignee("testAddExternalTask_test");
		taskService.saveTask(taskInstanceReturn);

		// 再次查询任务
		taskQuery = taskService.createTaskQuery();
		taskQuery.taskAssignee("testAddExternalTask_test");
		taskQuery.addTaskType(TaskInstanceType.OTHERBPMTASK);

		taskInstanceReturn = taskQuery.singleResult();
		// 任务处理者的确被更新了
		assertNotNull(taskInstanceReturn);
	}

	/**
	 * 添加候选身份
	 */
	public void testAddIdentityLink() {

		// 创建一个guid
		String taskIdString = GuidUtil.CreateGuid();
		// 创建一个任务(创建任务的时候createTime时间已经被自动设定)
		TaskInstance taskInstance = taskService.newTask(taskIdString);

		// 设置任务的表单
		taskInstance.setFormUri("http://www.google.com");
		// 设置任务关联键值
		taskInstance.setBizKey("1234567890");
		// 设置任务的主题
		taskInstance.setDescription("XXX系统的代办任务");
		// 设置任务的优先级
		taskInstance.setPriority(TaskInstance.PRIORITY_NORMAL);
		// 设置任务的类型,其他系统的异构流程引擎
		taskInstance.setTaskInstanceType(TaskInstanceType.OTHERBPMTASK);

		// 保存新创建的任务
		taskService.saveTask(taskInstance);

		// 创建候选用户链接
		IdentityLink identityLink = taskService.newIdentityLink();
		// 设置任务编号
		identityLink.setTaskId(taskIdString);
		// 设置候选用户编号
		identityLink.setUserId("testAddExternalTask_admin");
		// 设置候选 包含 、排除类型
		identityLink.setIncludeExclusion(IncludeExclusion.INCLUDE);
		// 设置候选类型
		identityLink.setType(IdentityLinkType.candidate);
		// 保存候选身份
		taskService.saveIdentityLink(identityLink);

		// 查询任务
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 查询候选任务
		taskQuery.taskCandidateUser("testAddExternalTask_admin");
		taskQuery.addTaskType(TaskInstanceType.OTHERBPMTASK);

		TaskInstance taskInstanceReturn = taskQuery.singleResult();

		assertNotNull(taskInstanceReturn);

	}

}
