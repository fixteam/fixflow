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
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstanceEntity);
		
		TaskInstanceEntity taskInstanceEntity2 = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		assertEquals(taskId, taskInstanceEntity2.getId());
	}

}
