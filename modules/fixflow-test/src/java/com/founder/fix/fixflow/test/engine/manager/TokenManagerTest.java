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

import java.text.SimpleDateFormat;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

/**
 * 令牌manager的测试类
 * @author ych
 *
 */
public class TokenManagerTest extends AbstractFixFlowTestCase{

	/**
	 * 测试保存令牌
	 * @throws Exception
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testSaveToken() throws Exception{
		//创建实体
		String guid = GuidUtil.CreateGuid();
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setId(guid);
		tokenEntity.setName("测试令牌");
		tokenEntity.setProcessInstanceId("流程实例编号");
		tokenEntity.setNodeId("节点编号");
		tokenEntity.setParentTokenId("父令牌编号");
		tokenEntity.setParentFreeTokenId("父自由令牌编号");
		tokenEntity.setStartTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-25"));
		tokenEntity.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-30"));
		tokenEntity.setNodeEnterTime(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-26"));
		tokenEntity.setLockedString("true");
		tokenEntity.setSuspendedString("true");
		tokenEntity.setSubProcessRootTokenString("true");
		tokenEntity.setAbleToReactivateParentString("true");
		tokenEntity.setFreeTokenString("true");
		
		//保存实体
		Context.getCommandContext().getTokenManager().saveToken(tokenEntity);
		
		//查询实体
		TokenEntity token = Context.getCommandContext().getTokenManager().findTokenById(guid);
		
		//验证属性正确
		assertEquals(guid, token.getId());
		assertEquals("测试令牌", token.getName());
		assertEquals("流程实例编号", token.getProcessInstanceId());
		assertEquals("节点编号", token.getNodeId());
		assertEquals("父令牌编号", token.getParentTokenId());
		assertEquals("父自由令牌编号", token.getParentFreeTokenId());
		assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-25"), token.getStartTime());
		assertTrue(token.getlock());
		assertTrue(token.isSuspended());
		assertTrue(token.isSubProcessRootToken());
		assertTrue(token.isAbleToReactivateParent());
		assertTrue(token.isFreeToken());
	}
	
	/**
	 * 测试根据流程编号删除令牌
	 */
	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testDeleteTokenByProcessInstanceId(){
		//创建实体
		String guid = GuidUtil.CreateGuid();
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setId(guid);
		tokenEntity.setName("测试令牌");
		//设置processinstanceId的关联
		tokenEntity.setProcessInstanceId("processInstanceId");
		//保存实体
		Context.getCommandContext().getTokenManager().saveToken(tokenEntity);
		//查询实体
		TokenEntity token = Context.getCommandContext().getTokenManager().findTokenById(guid);
		//验证保存成功
		assertEquals(guid, token.getId());
		
		//根据processInstanceId删除实体
		Context.getCommandContext().getTokenManager().deleteTokenByProcessInstanceId("processInstanceId");
		
		//查询实体
		token = Context.getCommandContext().getTokenManager().findTokenById(guid);
		//验证已经删除成功
		assertNull(token);
		
	}
}
