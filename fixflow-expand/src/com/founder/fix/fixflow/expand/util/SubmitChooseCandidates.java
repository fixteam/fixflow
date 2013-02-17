package com.founder.fix.fixflow.expand.util;

import java.sql.Connection;
import java.util.List;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;

public class SubmitChooseCandidates {

	/**
	 * 获取当前任务的处理之后的下异步任务. 这个方法需要在执行完任务处理方法之后执行,要在数据库链接commit之前。
	 * 使用这个方法之前还需要已经将数据库链接放置在线程副本中。
	 * 
	 * @param taskId
	 *            当前的任务编号
	 * @return 下一步的任务列表
	 */
	public static List<TaskInstance> getNextStepTasks(String taskId) {

		// 从线程副本中获取数据库链接
		Connection connection = Context.getDbConnection();

		if (connection == null) {

			throw new FixFlowException("获取下一步链接的时候数据库链接不能为空");

		}

		ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();

		TaskService taskService = processEngine.getTaskService();

		TaskQuery taskQuery = taskService.createTaskQuery();

		TaskInstance taskInstance=taskQuery.taskId(taskId).singleResult();
		
		     

		return null;

	}

}
