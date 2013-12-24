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
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.IncludeExclusion;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class IdentityManagerTest extends AbstractFixFlowTestCase{

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testSaveIdentityLink(){
		String identityLinkId = GuidUtil.CreateGuid();
		IdentityLinkEntity identityLinkEntity = new IdentityLinkEntity();
		identityLinkEntity.setId(identityLinkId);
		identityLinkEntity.setGroupId("groupId");
		identityLinkEntity.setUserId("userId");
		identityLinkEntity.setGroupType("groupType");
		identityLinkEntity.setType(IdentityLinkType.candidate);
		identityLinkEntity.setIncludeExclusion(IncludeExclusion.EXCLUSION);
		
		Context.getCommandContext().getIdentityLinkManager().saveIdentityLink(identityLinkEntity);
		IdentityLinkEntity identityLinkEntity2 = Context.getCommandContext().getIdentityLinkManager().selectIdentifyLinkById(identityLinkId);
		assertEquals(identityLinkId, identityLinkEntity2.getId());
		assertEquals("groupId",identityLinkEntity2.getGroupId());
		assertEquals("userId", identityLinkEntity2.getUserId());
		assertEquals("groupType", identityLinkEntity2.getGroupType());
		assertEquals(IdentityLinkType.candidate, identityLinkEntity2.getType());
		assertEquals(IncludeExclusion.EXCLUSION, identityLinkEntity2.getIncludeExclusion());
	}
}
