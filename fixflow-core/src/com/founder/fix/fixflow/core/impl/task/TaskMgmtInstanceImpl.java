package com.founder.fix.fixflow.core.impl.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.bpmn2.impl.FlowNodeImpl;

import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy;
import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig;
import com.founder.fix.bpmn2extensions.fixflow.AssignPolicyType;
import com.founder.fix.fixflow.core.action.AssignmentHandler;
import com.founder.fix.fixflow.core.event.BaseElementEvent;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.UserTaskBehavior;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.runtime.ProcessInstance;
import com.founder.fix.fixflow.core.runtime.Token;
import com.founder.fix.fixflow.core.task.Assignable;
import com.founder.fix.fixflow.core.task.IdentityLinkType;
import com.founder.fix.fixflow.core.task.TaskDefinition;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;

public class TaskMgmtInstanceImpl implements TaskMgmtInstance {

	Set<TaskInstanceEntity> taskInstances = new HashSet<TaskInstanceEntity>();

	public TaskMgmtInstanceImpl() {
	}

	public TaskInstanceEntity createTaskInstanceEntity(TaskDefinition taskDefinition, ExecutionContext executionContext) {
		return createTaskInstanceEntity(taskDefinition, executionContext, null);
	}

	public TaskInstanceEntity createTaskInstanceEntity(TaskDefinition taskDefinition, ExecutionContext executionContext, String taskGroup) {
		// TODO Auto-generated method stub

		// 创建任务实例
		TaskInstanceEntity taskInstance = instantiateNewTaskInstanceEntity();
		// 将任务实例添加任务管理的任务实例集合中
		addTaskInstanceEntity(taskInstance);
		// 如果任务定义不为空,则将任务的任务定义赋值.
		if (taskDefinition != null) {
			taskInstance.setTaskDefinition(taskDefinition);
		}

		if (executionContext != null) {
			TokenEntity token = executionContext.getToken();
			taskInstance.setToken(token);

			// taskInstance.initializeVariables();

			try {
				// update the executionContext
				executionContext.setTaskDefinition(taskDefinition);
				executionContext.setTaskInstance(taskInstance);
				// executionContext.setEventSource(task);
				// 设置会签分组
				if (taskGroup != null) {
					taskInstance.setTaskGroup(taskGroup);
				}

				// 通过表达式获取任务主题
				// String description = null;
				if (taskDefinition != null && taskDefinition.getDescriptionExpression() != null) {

					Object result = ExpressionMgmt.execute(taskDefinition.getDescriptionExpression(), executionContext);
					if (result != null) {
						taskInstance.setDescription(result.toString());
					} else {
						taskInstance.setDescription(token.getFlowNode().getName());
					}
				} else {

					// ProcessDefinitionBehavior processDefinitionBehavior =
					// token.getProcessInstance().getProcessDefinition();

					if (token.getProcessInstance().getSubject() != null && !token.getProcessInstance().getSubject().equals("")) {
						taskInstance.setDescription(token.getProcessInstance().getSubject());
					} else {
						taskInstance.setDescription(token.getFlowNode().getName());
					}

					/*
					 * if (processDefinitionBehavior.getTaskSubject() != null &&
					 * processDefinitionBehavior
					 * .getTaskSubject().getExpressionValue() != null) {
					 * 
					 * Object result =
					 * ExpressionMgmt.execute(token.getProcessInstance
					 * ().getProcessDefinition
					 * ().getTaskSubject().getExpressionValue(),
					 * executionContext);
					 * 
					 * if (result != null) {
					 * taskInstance.setDescription(result.toString()); } } else
					 * {
					 * taskInstance.setDescription(token.getFlowNode().getName(
					 * )); }
					 */

				}

				// 设置任务预计执行时间

				taskInstance.setExpectedExecutionTime(taskDefinition.getExpectedExecutionTimeValue());

				UserTaskBehavior userTask = (UserTaskBehavior) taskDefinition.getUserTaskNode();

				// 获取操作表单
				String formUri = userTask.getFormUri();
				// 获取浏览表单
				String formUriView = userTask.getFormUriView();

				if (formUri != null && !formUri.equals("")) {

					Object returnObject = ExpressionMgmt.execute(formUri, executionContext);
					if (returnObject != null) {
						taskInstance.setFormUri(returnObject.toString());
					}

				} else {

					String defaultFormUri = token.getProcessInstance().getProcessDefinition().getDefaultFormUri();
					if (defaultFormUri != null && !defaultFormUri.equals("")) {

						taskInstance.setFormUri(defaultFormUri);

					} else {
						throw new FixFlowException(userTask.getId() + " 节点没有指定表单,请检查流程配置!");
					}
				}
				if (taskInstance.getFormUri() == null || taskInstance.getFormUri().equals("")) {
					throw new FixFlowException(userTask.getId() + " 节点没有指定表单,请检查流程配置!");
				}

				if (formUriView != null && !formUriView.equals("")) {

					Object returnObject = ExpressionMgmt.execute(formUriView, executionContext);
					if (returnObject != null) {
						taskInstance.setFormUriView(returnObject.toString());
					}

				} else {

					taskInstance.setFormUriView(taskInstance.getFormUri());

				}
				if (taskInstance.getFormUriView() == null || taskInstance.getFormUriView().equals("")) {
					throw new FixFlowException(userTask.getId() + " 节点没有指定添加、浏览表单,请检查流程配置!");
				}

				String taskPriorityExpression = userTask.getTaskPriority();

				if (taskPriorityExpression != null && !taskPriorityExpression.equals("")) {

					Object returnObject = ExpressionMgmt.execute(taskPriorityExpression, executionContext);
					if (returnObject != null) {
						int taskPriority = StringUtil.getInt(returnObject);
						taskInstance.setPriority(taskPriority);
					}

				}

				// 创建任务实例
				taskInstance.create(executionContext);

				// 对已经分配的任务进行分配
				if (taskDefinition != null) {
					// 如果为退回任务的话则读取退回任务的处理者
					if (executionContext.getRollBackAssignee() != null) {
						taskInstance.setAssignee(executionContext.getRollBackAssignee());
					} else {
						taskInstance.assign(executionContext);
					}

					// 任务分配事件
					((FlowNodeImpl) token.getFlowNode()).fireEvent(BaseElementEvent.EVENTTYPE_TASK_ASSIGN, executionContext, taskInstance);

				}

			} catch (Exception e) {
				throw new FixFlowException("任务分配出错", e);
			} finally {
				// 清楚上下文变量
				executionContext.setTaskDefinition(null);
				executionContext.setTaskInstance(null);
				executionContext.setRollBackAssignee(null);
				// executionContext.setEventSource(null);
			}

		}

		return taskInstance;

	}

	private TaskInstanceEntity instantiateNewTaskInstanceEntity() {
		TaskInstanceEntity newTaskInstance = null;

		String taskInstanceId = GuidUtil.CreateGuid();
		newTaskInstance = new TaskInstanceEntity(taskInstanceId);

		return newTaskInstance;
	}

	public void addTaskInstanceEntity(TaskInstanceEntity taskInstance) {
		if (taskInstances == null)
			taskInstances = new HashSet<TaskInstanceEntity>();
		taskInstances.add(taskInstance);
		taskInstance.setTaskMgmtInstance(this);
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		// TODO Auto-generated method stub

	}

	public void performAssignment(TaskDefinition taskDefinition, Assignable assignable, ExecutionContext executionContext) {
		// TODO Auto-generated method stub

		if (taskDefinition != null && taskDefinition.getAssignAction() != null) {
			performAssignmentHandler(taskDefinition.getAssignAction(), assignable, executionContext);
		} else {
			performAssignmentResource(taskDefinition, assignable, executionContext);
		}

	}

	/**
	 * 将任务分配交给用户自己实现的分配类去实现
	 * 
	 * @param assignmentHandler
	 *            任务分配接口
	 * @param assignable
	 *            分配接口
	 * @param executionContext
	 *            执行内容对象
	 * @throws Exception
	 */
	void performAssignmentHandler(AssignmentHandler assignmentHandler, Assignable assignable, ExecutionContext executionContext) {
		assignmentHandler.assign(assignable, executionContext);
	}

	/**
	 * 通过配置的表达式分配任务
	 * 
	 * @param taskDefinition
	 *            任务定义,里边包含任务表达式。
	 * @param assignable
	 *            分配接口
	 * @param executionContext
	 *            执行内容对象
	 * @throws Exception
	 */
	void performAssignmentResource(TaskDefinition taskDefinition, Assignable assignable, ExecutionContext executionContext) {

		// 根据任务定义上不同的策略调用不同的策略分配类
		// 为了兼容老的系统,当没有找到 assignPolicyType 类型的时候 就默认按照
		// bpmn2:potentialOwner bpmn2:humanPerformer 两种类型来分配任务
		// taskDefinition

		AssignPolicyType assignPolicyType = taskDefinition.getAssignPolicyType();
		if (assignPolicyType == null) {
			List<TaskAssigneeDefinitionTo> taskAssigneeDefinitionTos = taskDefinition.getTaskAssigneeDefinitionTos();

			for (TaskAssigneeDefinitionTo taskAssigneeDefinitionTo : taskAssigneeDefinitionTos) {

				if (taskAssigneeDefinitionTo.getUserIdExpression() != null) {
					List<String> resultList = executionExpression(taskAssigneeDefinitionTo.getUserIdExpression(), executionContext);

					if (resultList.size() > 0) {
						for (String userId : resultList) {

							if (userId != null && !userId.equals("")) {

								if (taskAssigneeDefinitionTo.getIdentityLinkType().toString().equals(IdentityLinkType.assignee.toString())) {
									// assignable.addAssigneeUser(userId,
									// taskAssigneeDefinitionTo.getIncludeExclusion());
									if (userId.equals("fixflow_allusers")) {
										throw new FixFlowException("任务分配给所有人的时候不能采用独占方式分配,请重新修改节点 " + ((TaskInstance) assignable).getNodeId()
												+ " 的任务分配定义");
									}
									assignable.setAssignee(userId);
								} else {
									assignable.addCandidateUser(userId, taskAssigneeDefinitionTo.getIncludeExclusion());
								}
							}
						}
					}
				}

				if (taskAssigneeDefinitionTo.getGroupIdExpression() != null) {
					List<String> resultList = executionExpression(taskAssigneeDefinitionTo.getGroupIdExpression(), executionContext);

					if (resultList.size() > 0) {
						for (String groupId : resultList) {
							if (groupId != null && !groupId.equals("")) {

								if (taskAssigneeDefinitionTo.isContainsSub) {
									List<GroupTo> groupTos = null;
									try {
										groupTos = Authentication.findGroupChildMembersIncludeByGroupId(groupId,
												taskAssigneeDefinitionTo.getGroupTypeExpression());

									} catch (Exception e) {
										throw new FixFlowException("节点 " + ((TaskInstance) assignable).getNodeId() + " 的任务获取子分配组出错！", e);
									}

									if (groupTos != null) {

										for (GroupTo groupTo : groupTos) {
											GroupTo groupToObj = new GroupTo(groupTo.getGroupId(), taskAssigneeDefinitionTo.getGroupTypeExpression());

											assignable.addCandidateGroup(groupToObj, taskAssigneeDefinitionTo.getIncludeExclusion());
										}

									} else {
										throw new FixFlowException("节点 " + ((TaskInstance) assignable).getNodeId() + " 的任务获取子分配组出错！");
									}

								} else {
									GroupTo groupTo = new GroupTo(groupId, taskAssigneeDefinitionTo.getGroupTypeExpression());

									assignable.addCandidateGroup(groupTo, taskAssigneeDefinitionTo.getIncludeExclusion());
								}

							}

						}
					}
				}

			}
		} else {
			String typeId = assignPolicyType.getId();
			AssignPolicyConfig assignPolicyConfig = Context.getProcessEngineConfiguration().getAssignPolicyConfig();
			AssignPolicy assignPolicy = null;
			for (AssignPolicy assignPolicyObj : assignPolicyConfig.getAssignPolicy()) {
				if (assignPolicyObj.getId().equals(typeId)) {
					assignPolicy = assignPolicyObj;
				}
			}
			if (assignPolicy.getClassImpl() == null || assignPolicy.getClassImpl().equals("")) {
				throw new FixFlowException("任务的分配策略没有实现类,请检查!");
			} else {
				String classImpl = assignPolicy.getClassImpl();
				
		
				AssignmentHandler assignmentHandler = ProcessObjectFactory.FACTORYINSTANCE.createAssignmentHandler(classImpl);
				assignmentHandler.assign(assignable, executionContext);
			}

		}

	}

	private List<String> executionExpression(String expression, ExecutionContext executionContext) {

		List<String> resultList = new ArrayList<String>();

		Object result = ExpressionMgmt.execute(expression, executionContext);
		if (result != null) {

			if (result instanceof String) {

				String[] dddStrings = result.toString().split(",");

				for (int i = 0; i < dddStrings.length; i++) {
					resultList.add(dddStrings[i]);
				}

			}

			if (result instanceof Integer) {
				resultList.add(result.toString());
			}

			if (result instanceof List<?>) {
				List<?> resultTempList = (List<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

			if (result instanceof Set<?>) {
				Set<?> resultTempList = (Set<?>) result;
				for (Object resultObj : resultTempList) {
					resultList.add(resultObj.toString());
				}
			}

		}

		return resultList;
	}

	/**
	 * 暂停这个令牌下的所有任务实例
	 */
	public void suspend(Token token) {
		if (token == null) {
			throw new FixFlowException("暂停任务实例的时候令牌不能为空!");
		}
		if (taskInstances != null) {
			for (TaskInstanceEntity taskInstance : taskInstances) {
				if (token.equals(taskInstance.getToken())) {
					taskInstance.suspend();
				}
			}
		}
	}

	/**
	 * 恢复这个令牌下的所有任务实例
	 */
	public void resume(Token token) {
		if (token == null) {
			throw new FixFlowException("恢复任务实例的时候令牌不能为空!");
		}
		if (taskInstances != null) {
			for (TaskInstanceEntity taskInstance : taskInstances) {
				if (token.equals(taskInstance.getToken())) {
					taskInstance.resume();
				}
			}
		}
	}

	public Set<TaskInstanceEntity> getUnfinishedTasks(Token token) {
		Set<TaskInstanceEntity> unfinishedTasks = new HashSet<TaskInstanceEntity>();
		if (taskInstances != null) {

			for (TaskInstanceEntity taskInstance : taskInstances) {

				if ((!taskInstance.hasEnded()) && (token != null) && (token.getId().equals(taskInstance.getToken().getId()))) {
					unfinishedTasks.add(taskInstance);
				}
			}

		}
		return unfinishedTasks;
	}

	public Set<TaskInstanceEntity> getTaskInstanceEntitys(Token token) {

		Set<TaskInstanceEntity> taskInstancesTemp = new HashSet<TaskInstanceEntity>();

		for (TaskInstanceEntity taskInstance : taskInstances) {
			if (taskInstance.getToken().getId().equals(token.getId())) {
				taskInstancesTemp.add(taskInstance);
			}
		}

		return taskInstancesTemp;
	}

	public Set<TaskInstanceEntity> getTaskInstanceEntitys() {
		return taskInstances;
	}

}
