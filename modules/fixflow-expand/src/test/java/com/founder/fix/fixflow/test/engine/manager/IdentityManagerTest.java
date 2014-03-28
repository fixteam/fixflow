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

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.task.IdentityLinkEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.IncludeExclusion;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

/**
 * identityLinkManager的测试类
 * @author ych
 *
 */
public class IdentityManagerTest extends AbstractFixFlowTestCase{

	/**
	 * 测试保存identityLinkEntity
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testSaveIdentityLink(){
		
		//创建任务候选人实体
		String identityLinkId = GuidUtil.CreateGuid();
		IdentityLinkEntity identityLinkEntity = new IdentityLinkEntity();
		identityLinkEntity.setId(identityLinkId);
		identityLinkEntity.setGroupId("groupId");
		identityLinkEntity.setUserId("userId");
		identityLinkEntity.setGroupType("groupType");
		identityLinkEntity.setType(IdentityLinkType.candidate);
		identityLinkEntity.setIncludeExclusion(IncludeExclusion.EXCLUSION);
		identityLinkEntity.setTaskId("taskId");
		
		//保存identityLinkEntity
		Context.getCommandContext().getIdentityLinkManager().saveIdentityLink(identityLinkEntity);
		//根据编号查询保存的identityLinkEntity
		IdentityLinkEntity identityLinkEntity2 = Context.getCommandContext().getIdentityLinkManager().selectIdentifyLinkById(identityLinkId);
		//验证查询出来的实体属性正确
		assertEquals(identityLinkId, identityLinkEntity2.getId());
		assertEquals("groupId",identityLinkEntity2.getGroupId());
		assertEquals("userId", identityLinkEntity2.getUserId());
		assertEquals("groupType", identityLinkEntity2.getGroupType());
		assertEquals("taskId", identityLinkEntity2.getTaskId());
		assertEquals(IdentityLinkType.candidate, identityLinkEntity2.getType());
		assertEquals(IncludeExclusion.EXCLUSION, identityLinkEntity2.getIncludeExclusion());
	}
	
	/**
	 * 测试根据流程实例编号删除任务候选人
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testDeleteIdentityLinkByProcessInstanceId(){
		//创建任务候选人实体
		String identityLinkId = GuidUtil.CreateGuid();
		IdentityLinkEntity identityLinkEntity = new IdentityLinkEntity();
		identityLinkEntity.setId(identityLinkId);
		identityLinkEntity.setTaskId("taskId");
		//保存实体
		Context.getCommandContext().getIdentityLinkManager().saveIdentityLink(identityLinkEntity);
		
		//根据编号查询实体
		IdentityLinkEntity identityLinkEntity2 = Context.getCommandContext().getIdentityLinkManager().selectIdentifyLinkById(identityLinkId);
		//验证保存成功
		assertEquals(identityLinkId, identityLinkEntity2.getId());
		//创建任务实例
		TaskInstanceEntity taskInstanceEntity = new TaskInstanceEntity();
		//将任务实体与identityLink关联
		taskInstanceEntity.setId("taskId");
		taskInstanceEntity.setName("name");
		//将任务实体与processInstanceId关联
		taskInstanceEntity.setProcessInstanceId("processInstanceId");
		//保存任务实体
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(taskInstanceEntity);
		
		//根据processInstanceId删除任务候选人
		Context.getCommandContext().getIdentityLinkManager().deleteIdentityLinkByProcessInstanceId("processInstanceId");
		
		//查询人物候选人
		identityLinkEntity2 = Context.getCommandContext().getIdentityLinkManager().selectIdentifyLinkById(identityLinkId);
		//验证查询结果为null,表示删除成功
		assertNull(identityLinkEntity2);
	}
}
