package com.founder.fix.fixflow.test.engine.api.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

public class MyTest  extends AbstractFixFlowTestCase{
	
	//zhuliucheng

	// 添加一个任务
		@SuppressWarnings("unused")
		public void testAddExternalTask() {
			
			
			//jiangnantest
			
			
			// 用户自己创建的处理命令都以这种方式来执行,首先先创建一个扩展命令处理参数对象
			ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
			// 设置用户点击的处理命令号
			expandTaskCommand.setUserCommandId("Advance_1");
			
			expandTaskCommand.setProcessDefinitionKey("jiangnantest");
			
			expandTaskCommand.setInitiator("1200119390");
			// 处理意见
			expandTaskCommand.setTaskComment("我把这个任务退回给我选中的一个任务了！");
			// 处理命令类型(这里的类型请在流程配置里的处理命令扩展里查看那)
			expandTaskCommand.setCommandType("startandsubmit");

			
			// 执行处理命令
			taskService.expandTaskComplete(expandTaskCommand,null);
			
			
			List<TaskInstance> taskInstances=taskService.createTaskQuery().taskAssignee("1200119390").taskCandidateUser("1200119390").taskNotEnd().list();
			
			
			
			//Advance_ProcessStatus

		String jiangna ="";
			
		}
}
