package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.impl.UserTaskImpl;
import org.eclipse.emf.ecore.util.FeatureMap;

import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.bpmn2extensions.fixflow.SkipAssignee;
import com.founder.fix.bpmn2extensions.fixflow.SkipComment;
import com.founder.fix.bpmn2extensions.fixflow.SkipStrategy;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.factory.ProcessObjectFactory;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.runtime.TokenEntity;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.EMFExtensionUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.TaskDefinition;
import com.founder.fix.fixflow.core.task.TaskInstanceType;
import com.founder.fix.fixflow.core.task.TaskMgmtInstance;

public class UserTaskBehavior extends UserTaskImpl {
	
	protected String formUri;
	
	protected String formUriView;
	
	
	protected TaskSubject taskSubject;
	
	protected List<TaskCommandInst>  taskCommands;
	
	protected String taskPriority;




	

	public String getFormUri() {
		
		if(this.formUri==null){
			List<FeatureMap.Entry> entryList = EMFExtensionUtil.getExtensionElements(this, "formUri");
			if (entryList.size() > 0) {

				FeatureMap.Entry expressionEntry = EMFExtensionUtil.getExtensionElementsInEntry(entryList.get(0), "expression").get(0);
				String expressionValue = EMFExtensionUtil.getExtensionElementValue(expressionEntry);

				this.formUri = expressionValue;

			}

			
		}
		return this.formUri;

	}
	
	public String getFormUriView() {
		
		
		if(this.formUriView==null){
			List<FeatureMap.Entry> entryList = EMFExtensionUtil.getExtensionElements(this, "formUriView");
			if (entryList.size() > 0) {
	
				FeatureMap.Entry expressionEntry = EMFExtensionUtil.getExtensionElementsInEntry(entryList.get(0), "expression").get(0);
				String expressionValue = EMFExtensionUtil.getExtensionElementValue(expressionEntry);
	
				this.formUriView = expressionValue;
	
			}
		}
		return formUriView;
	}

	public TaskSubject getTaskSubject() {
		
		
		if(this.taskSubject==null){
			List<FeatureMap.Entry> entryList = EMFExtensionUtil.getExtensionElements(this, "taskSubject");
			if (entryList.size() > 0) {
				this.taskSubject= new TaskSubject(entryList.get(0));
			} 
		}
		
		return this.taskSubject;
	}
	
	public String getTaskPriority() {
		
		if(this.taskPriority==null){
			List<FeatureMap.Entry> entryList = EMFExtensionUtil.getExtensionElements(this, "taskPriority");
			if (entryList.size() > 0) {
	
				FeatureMap.Entry expressionEntry = EMFExtensionUtil.getExtensionElementsInEntry(entryList.get(0), "expression").get(0);
				String expressionValue = EMFExtensionUtil.getExtensionElementValue(expressionEntry);
	
				this.taskPriority = expressionValue;
	
			}
		}
		return taskPriority;
		
	

	}

	// ////////////////////////任务处理////////////////////////////////////////////

	protected String assignmentActionClassName = null;

	/**
	 * 任务定义
	 */
	TaskDefinition taskDefinition = null;

	;

	// getters and setters
	// //////////////////////////////////////////////////////

	protected Map<String, TaskCommandInst> taskCommandsMap;

	public List<TaskCommandInst> getTaskCommands() {
		
		if(taskCommands==null){
			taskCommands =new ArrayList<TaskCommandInst>();
			List<FeatureMap.Entry> entryList = EMFExtensionUtil.getExtensionElements(this, "taskCommand");
			for (FeatureMap.Entry entry : entryList) {
				taskCommands.add(new TaskCommandInst(entry,this));
			}
		}
		
		
		return taskCommands;

	}

	public Map<String, TaskCommandInst> getTaskCommandsMap() {
		if (taskCommandsMap == null) {
			taskCommandsMap = new HashMap<String, TaskCommandInst>();

			for (TaskCommandInst taskCommand : getTaskCommands()) {
				taskCommandsMap.put(taskCommand.getId(), taskCommand);
			}

		}

		return taskCommandsMap;

	}

	// getters and setters
	// //////////////////////////////////////////////////////

	public String getAssignmentActionClassName() {

		if (assignmentActionClassName == null) {
			this.assignmentActionClassName = EMFExtensionUtil.getAnyAttributeValue(this, "assignAction");
		}

		return assignmentActionClassName;
	}

	public void setAssignmentActionClassName(String className) {

		this.assignmentActionClassName = className;

	}

	public TaskDefinition getTaskDefinition() {

		if (taskDefinition == null) {
			taskDefinition = ProcessObjectFactory.FACTORYINSTANCE.createTaskDefinition(this);
		}

		return taskDefinition;
	}

	public void setTaskDefinition(TaskDefinition taskDefinition) {
		this.taskDefinition = taskDefinition;
	}



	// 覆写父类的节点执行方法
	public void execute(ExecutionContext executionContext) {
		
		
		
		
		
		

		TaskMgmtInstance tmi = getTaskMgmtInstance(executionContext.getToken());

		// 是否启用验证
		if (executionContext.getProcessDefinition().isVerification()) {
			//这里是验证的设计阶段有没有配置处理者
			if (!checkResources()) {
				throw new FixFlowException("节点: " + this.getId() + " 没有定义处理者,请重新检查节点定义!");
			}
		}

		// 创建并分配任务
		TaskInstanceEntity taskInstance = null;
		
		if(executionContext.getGroupID()!=null) {
			taskInstance = tmi.createTaskInstanceEntity(getTaskDefinition(), executionContext, executionContext.getGroupID());
		}else {
			taskInstance = tmi.createTaskInstanceEntity(getTaskDefinition(), executionContext);
		}

		// 是否启用验证
		if (!executionContext.getProcessDefinition().isVerification()) {
			return;
		}

		// 当独占任务和候选任务都没有的时候则直接弹出异常消息.
		if (taskInstance.getAssignee() == null && taskInstance.getTaskIdentityLinks().size() == 0) {
			throw new FixFlowException("节点: " + this.getId() + " 无对应处理者,请重新检查节点定义!");
		} else {
			// 检查分配的用户或者组是否存在,不存在则抛出异常
			if (taskInstance.getAssignee() != null) {
				if (taskInstance.getAssignee().equals("fixflow_allusers")) {
					throw new FixFlowException("独占处理者不能是所有人");
					//return;
				}
				UserTo userTo = Authentication.findUserInfoByUserId(taskInstance.getAssignee());
				if (userTo == null) {
					throw new FixFlowException("节点: " + this.getId() + " 指定的处理者 " + taskInstance.getAssignee() + " 不存在,请重新检查节点定义!");
				}
			}

			if (taskInstance.getTaskIdentityLinks().size() > 0) {
				for (IdentityLink identityLink : taskInstance.getTaskIdentityLinks()) {

					if (identityLink.getUserId() != null) {
						if (identityLink.getUserId().equals("fixflow_allusers")) {
							return;
						}
						UserTo userTo = Authentication.findUserInfoByUserId(identityLink.getUserId());
						if (userTo == null) {
							// throw new
							// FixFlowException("节点: "+this.getId()+" 指定的处理者 "+
							// identityLink.getUserId() +" 不存在,请重新检查节点定义!");
						} else {
							return;
						}
					} else {
						String groupIdString = identityLink.getGroupId();
						String groupTypeString = identityLink.getGroupType();
						GroupTo groupTo = Authentication.findGroupByGroupIdAndType(groupIdString, groupTypeString);
						if (groupTo == null) {

						} else {
							return;
						}
						// identityLink.getGroupId()
					}

				}

				throw new FixFlowException("节点: " + this.getId() + " 您所指定的应处理者都不存在,请重新检查节点定义!");

			}

		}

	}
	
	//覆写父类的跳过执行方法
	protected void skipExecute(ExecutionContext executionContext){
		
		
		SkipStrategy skipStrategy=executionContext.getSkipStrategy();
		SkipAssignee skipAssignee = skipStrategy.getSkipAssignee();
		SkipComment skipComment = skipStrategy.getSkipComment();

		String skipAssigneeString = null;
		String skipCommentString = null;

		if (skipAssignee != null && skipAssignee.getExpression() != null) {
			if (skipAssignee.getExpression().getValue() != null && !skipAssignee.getExpression().getValue().equals("")) {
				try {
					skipAssigneeString = StringUtil.getString(ExpressionMgmt.execute(skipAssignee.getExpression().getValue(),
							executionContext));

				} catch (Exception e) {

					throw new FixFlowException("节点 " + this.getId() + " " + this.getName() + " 的跳过策略出错请检查流程配置！", e);
				}
			}
		}

		if (skipComment != null && skipComment.getExpression() != null) {
			if (skipComment.getExpression().getValue() != null && !skipComment.getExpression().getValue().equals("")) {
				try {
					skipCommentString = StringUtil.getString(ExpressionMgmt.execute(skipComment.getExpression().getValue(),
							executionContext));

				} catch (Exception e) {

					throw new FixFlowException("节点 " + this.getId() + " " + this.getName() + " 的跳过策略出错请检查流程配置！", e);
				}
			}
		}
		
		TokenEntity token=executionContext.getToken();
		
		TaskInstanceEntity newTask = TaskInstanceEntity.create();
		newTask.setId(GuidUtil.CreateGuid());
		newTask.setCreateTime(new Date());
		newTask.setNodeId(this.getId());
		
		if(skipAssigneeString!=null&&!skipAssigneeString.equals("")){
			newTask.setAssignee(skipAssigneeString);
		}

		newTask.setDraft(false);
		
		Date date=new Date();
		
		
		Calendar cal=Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.MILLISECOND,1);
	    date=cal.getTime();
		
		
		newTask.setEndTime(date);
		newTask.setPriority(50);
		
		ProcessDefinitionBehavior processDefinition=token.getProcessInstance().getProcessDefinition();
		String processDefinitionId=processDefinition.getProcessDefinitionId();
		newTask.setProcessDefinitionId(processDefinitionId);
		newTask.setProcessDefinitionKeyWithoutCascade(processDefinition.getProcessDefinitionKey());
		newTask.setName(this.getName());
		newTask.setNodeName(this.getName());
		newTask.setProcessInstanceId(token.getProcessInstance().getId());
		newTask.setTokenId(token.getId());
		newTask.setProcessDefinitionName(processDefinition.getName());
		newTask.setTaskInstanceType(TaskInstanceType.FIXBPMTASK);
		String bizKey=token.getProcessInstance().getBizKey();
		newTask.setBizKey(bizKey);
		newTask.setCommandType("skipNode");
		
		
		TaskCommandDef taskCommandDef=Context.getProcessEngineConfiguration().getTaskCommandDefMap().get("skipNode");
		
		
		
		
		
		if(taskCommandDef.getName()!=null){
			newTask.setCommandMessage(taskCommandDef.getName());
		}
		
		if(skipCommentString!=null&&!skipCommentString.equals("")){
			newTask.setTaskComment(skipCommentString);
		}
		
		
	
		
		Context.getCommandContext().getTaskManager().saveTaskInstanceEntity(newTask);
		

	}


	private boolean checkResources() {
		if (this.getResources().size() == 0 && this.getAssignmentActionClassName() == null) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * 离开节点的时候需要清理的数据. 每个子类需要自己实现.
	 */
	public void leaveClearData(ExecutionContext executionContext) {
		// 移除没有完成任务 多用在会签的时候
		removeTaskInstanceSynchronization(executionContext.getToken());
	}


	private void removeTaskInstanceSynchronization(TokenEntity token) {
		// TODO Auto-generated method stub
		TaskMgmtInstance tmi = getTaskMgmtInstance(token);
		for (TaskInstanceEntity taskInstance : tmi.getTaskInstanceEntitys(token)) {
			if (!taskInstance.hasEnded()) {
				taskInstance.customEnd(null,null,null,null);
			}
		}
	}

	TaskMgmtInstance getTaskMgmtInstance(TokenEntity token) {
		return (TaskMgmtInstance) token.getProcessInstance().getTaskMgmtInstance();
	}

}
