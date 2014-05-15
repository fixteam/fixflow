package com.founder.fix.fixflow.test.bpmn.bpmndiagram;

import java.util.List;

import org.eclipse.bpmn2.di.BPMNDiagram;


import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;
import com.founder.fix.fixflow.test.Deployment;


public class BPMNDiagramQueryTest  extends AbstractFixFlowTestCase {
	@Override
	protected void setUp() throws Exception {
		// 初始化测试方法
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// 测试完毕清理方法
		super.tearDown();
	}

	@Deployment(resources = { "com/founder/fix/fixflow/test/bpmn/bpmndiagram/bpmndiagram.bpmn" })
	@SuppressWarnings("unused")
	public void testProcessDefinitionQuery() {
		
		ProcessDefinitionBehavior processDefinition = modelService.createProcessDefinitionQuery().processDefinitionKey("bpmndiagramTest")
				.singleResult();

		List<BPMNDiagram> BPMNDiagramList=processDefinition.getDefinitions().getDiagrams();
		assertNotNull(processDefinition);
	}

}
