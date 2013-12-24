package com.founder.fix.fixflow.test.engine.manager;

import java.text.SimpleDateFormat;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class TokenManagerTest extends AbstractFixFlowTestCase{

	@Deployment(resources = { "com/founder/fix/fixflow/test/engine/api/task/TaskServiceNewTest.bpmn"})
	public void testSaveToken() throws Exception{
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
		
		Context.getCommandContext().getTokenManager().saveToken(tokenEntity);
		
		TokenEntity token = Context.getCommandContext().getTokenManager().findTokenById(guid);
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
}
