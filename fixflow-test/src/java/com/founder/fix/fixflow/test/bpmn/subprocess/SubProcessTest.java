package com.founder.fix.fixflow.test.bpmn.subprocess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.command.StartProcessInstanceCommand;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;

public class SubProcessTest extends AbstractFixFlowTestCase {
	
	@Deployment(resources = { "com/founder/fix/fixflow/test/bpmn/subprocess/SubProcessTest.bpmn" })
	public void testSubProcess() {
		
		
		
		ProcessDefinitionBehavior processDefinition = modelService.createProcessDefinitionQuery().processDefinitionKey("_XlI4sNOrEeCFP6kuJqC8Tw")
				.singleResult();
		assertNotNull(processDefinition);
		
		
		
		
		
		
		// 瞬态
		Map<String, Object> transientVariables = new HashMap<String, Object>();
		// 持久化
		//Map<String, Object> Variables = new HashMap<String, Object>();
		

		

		transientVariables.put("amount",500);
		//启动测试流程
		StartProcessInstanceCommand startProcessInstanceCommand = new StartProcessInstanceCommand();
		startProcessInstanceCommand.setProcessDefinitionKey("_XlI4sNOrEeCFP6kuJqC8Tw");
		startProcessInstanceCommand.setBusinessKey("1234567890");
		startProcessInstanceCommand.setStartAuthor("1200119390");
		startProcessInstanceCommand.setTransientVariables(transientVariables);   
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(startProcessInstanceCommand);
		assertNotNull(processInstance);
		
		
		List<Token> tokenQueryToList=runtimeService.createTokenQuery().processInstanceId(processInstance.getId()).list();
		
		assertEquals(tokenQueryToList.size(), 1);
		assertEquals(tokenQueryToList.get(0).getNodeId(),"UserTask4");
		
	}

}