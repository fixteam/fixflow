package com.founder.fix.fixflow.test.engine.manager;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class TaskManagerTest extends AbstractFixFlowTestCase{
	
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testSaveTask(){
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

}
