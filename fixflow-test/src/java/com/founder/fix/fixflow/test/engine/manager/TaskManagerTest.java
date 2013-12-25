/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author ych
 */
package com.founder.fix.fixflow.test.engine.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.task.DelegationState;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskInstanceType;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

/**
 * TaskManager测试类
 * @author ych
 *
 */
public class TaskManagerTest extends AbstractFixFlowTestCase{
	
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testSaveTask() throws ParseException{
		String taskId = GuidUtil.CreateGuid();
		
		//创建任务实例
		TaskInstanceEntity taskInstanceEntity = new TaskInstanceEntity();
		taskInstanceEntity.setId(taskId);
		taskInstanceEntity.setName("name");
		taskInstanceEntity.setDescription("description");
		taskInstanceEntity.setProcessInstanceId("processInstanceId");
		taskInstanceEntity.setProcessDefinitionId("processDefinitionId");
		taskInstanceEntity.setProcessDefinitionKey("processDefinitionKey");
		taskInstanceEntity.setVersion(0);
		taskInstanceEntity.setTokenId("tokenId");
		taskInstanceEntity.setNodeId("nodeId");
		taskInstanceEntity.setNodeName("nodeName");
		taskInstanceEntity.setParentTaskInstanceId("parentTaskInstanceId");
		taskInstanceEntity.setAssignee("assignee");
		taskInstanceEntity.setClaimTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-25"));
		taskInstanceEntity.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-25"));
		taskInstanceEntity.setStartTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-25"));
		taskInstanceEntity.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-25"));
		taskInstanceEntity.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-25"));
		taskInstanceEntity.setArchiveTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-25"));
		taskInstanceEntity.setBlocking(true);
		taskInstanceEntity.setPriority(2);
		taskInstanceEntity.setCategory("category");
		taskInstanceEntity.setOwner("owner");
		taskInstanceEntity.setDelegationState(DelegationState.PENDING);
		taskInstanceEntity.setBizKey("bizKey");
		taskInstanceEntity.setTaskComment("taskComment");
		taskInstanceEntity.setFormUri("formUri");
		taskInstanceEntity.setFormUriView("formUriView");
		taskInstanceEntity.setTaskGroup("taskGroup");
		taskInstanceEntity.setTaskInstanceType(TaskInstanceType.FIXBPMTASK);
		taskInstanceEntity.setCancelled(true);
		taskInstanceEntity.setSuspended(true);
		taskInstanceEntity.setOpen(false);
		taskInstanceEntity.setDraft(true);
		taskInstanceEntity.setExpectedExecutionTime(3);
		taskInstanceEntity.setAgent("agent");
		taskInstanceEntity.setAdmin("admin");
		taskInstanceEntity.setCallActivityInstanceId("callActivitiProcessInstanceId");
		taskInstanceEntity.setPendingTaskId("pendingTaskId");
		taskInstanceEntity.setCommandId("commandId");
		taskInstanceEntity.setCommandType("commandType");
		taskInstanceEntity.setCommandMessage("commandMessage");
		////////测试保存方法
		//保存任务实例
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstanceEntity);
		
		//查询任务实例
		TaskInstanceEntity taskInstanceEntity2 = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		//验证查询结果正确
		assertEquals(taskId, taskInstanceEntity2.getId());
		assertEquals("name", taskInstanceEntity2.getName());
		assertEquals("description", taskInstanceEntity2.getDescription());
		assertEquals("processInstanceId", taskInstanceEntity2.getProcessInstanceId());
		assertEquals("processDefinitionId", taskInstanceEntity2.getProcessDefinitionId());
		assertEquals("processDefinitionKey", taskInstanceEntity2.getProcessDefinitionKey());
		assertEquals(0, taskInstanceEntity2.getVersion());
		assertEquals("tokenId", taskInstanceEntity2.getTokenId());
		assertEquals("nodeId",taskInstanceEntity2.getNodeId());
		assertEquals("nodeName", taskInstanceEntity2.getNodeName());
		
		//根据processInstanceId删除保存的任务实例
		Context.getCommandContext().getTaskManager().deleteTaskByProcessInstanceId("processInstanceId");
		//查询任务实例
		taskInstanceEntity2 = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		//验证任务已经被删除
		assertNull(taskInstanceEntity2);
		
		//重新保存任务实例
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstanceEntity);
		//查询任务实例
		taskInstanceEntity2 = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		//验证查询结果正确。验证保存成功
		assertEquals(taskId, taskInstanceEntity2.getId());
		//根据任务编号删除任务实例
		Context.getCommandContext().getTaskManager().deleteTaskById(taskId, false);
		//查询任务实例
		taskInstanceEntity2 = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		//验证任务已经被删除
		assertNull(taskInstanceEntity2);
		
		//重新保存任务实例
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstanceEntity);
		//查询任务实例
		taskInstanceEntity2 = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		//验证查询结果正确。验证保存成功
		assertEquals(taskId, taskInstanceEntity2.getId());
		//根据任务编号级联删除任务实例
		Context.getCommandContext().getTaskManager().deleteTaskById(taskId, true);
		//查询任务实例
		taskInstanceEntity2 = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		//验证任务已经被删除
		assertNull(taskInstanceEntity2);
		
	}
	
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testDeleteTaskByProcessInstanceId(){
		String taskId = GuidUtil.CreateGuid();
		//创建任务实例
		TaskInstanceEntity taskInstanceEntity = new TaskInstanceEntity();
		taskInstanceEntity.setId(taskId);
		taskInstanceEntity.setProcessInstanceId("processInstanceId");
		//保存任务实例
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstanceEntity);
		//查询任务实例
		TaskInstanceEntity taskInstanceEntity2 = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		assertEquals(taskId, taskInstanceEntity2.getId());
		//根据processInstanceId删除保存的任务实例
		Context.getCommandContext().getTaskManager().deleteTaskByProcessInstanceId("processInstanceId");
		//查询任务实例
		taskInstanceEntity2 = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		//验证任务已经被删除
		assertNull(taskInstanceEntity2);
				
	}
	
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testUpdate(){
		String taskId = GuidUtil.CreateGuid();
		//创建任务实例
		TaskInstanceEntity taskInstanceEntity = new TaskInstanceEntity();
		taskInstanceEntity.setId(taskId);
		taskInstanceEntity.setName("name");
		taskInstanceEntity.setDescription("description");
		taskInstanceEntity.setProcessInstanceId("processInstanceId");
		taskInstanceEntity.setProcessDefinitionId("processDefinitionId");
		taskInstanceEntity.setProcessDefinitionKey("processDefinitionKey");
		taskInstanceEntity.setVersion(0);
		taskInstanceEntity.setTokenId("tokenId");
		taskInstanceEntity.setNodeId("nodeId");
		//保存任务实例
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstanceEntity);
		
		//查询任务实例
		TaskInstanceEntity taskInstanceEntity2 = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		//验证查询结果正确
		assertEquals(taskId, taskInstanceEntity2.getId());
	}

}
