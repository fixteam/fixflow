package com.founder.fix.fixflow.designer.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.HumanPerformer;
import org.eclipse.bpmn2.LoopCharacteristics;
import org.eclipse.bpmn2.MultiInstanceLoopCharacteristics;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.jface.dialogs.MessageDialog;

import com.founder.fix.bpmn2extensions.fixflow.FormUri;
import com.founder.fix.bpmn2extensions.fixflow.TaskCommand;
import com.founder.fix.bpmn2extensions.fixflow.TaskSubject;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;

public class VerificationUtil {
	private static StringBuffer sb;

	/**
	 * 验证总方法
	 * 
	 * @return
	 */
	public static boolean verifyAll() {
		sb = new StringBuffer();

		List<FlowElement> flowElements = SectionBpmnElement.process.getFlowElements();

		// 先验证主流程信息
		verificationProc(flowElements);
		isSubmitNodeHasUserCommand(flowElements);
		processDefaultTitleAndDefaultForm(SectionBpmnElement.process);

		boolean yzForm = true;

		if (SectionBpmnElement.process.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : SectionBpmnElement.process.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof FormUri) {
						FormUri formUri = (FormUri) entry.getValue();

						if (formUri.getExpression().getValue() == null || formUri.getExpression().getValue().equals("")) {
							yzForm = true;
						} else {
							yzForm = false;
						}

						break;
					}

				}

			}

		}
		if (yzForm) {
			verifyFormIsNull(flowElements);
		}

		// 循环主流程所有节点

		for (FlowElement flowElement : SectionBpmnElement.process.getFlowElements()) {
			// 如果发现是子流程则进行递归
			if (flowElement instanceof SubProcess) {
				getSubProcessElement((SubProcess) flowElement, yzForm);
			}
		}

		if (sb.length() > 0 && !sb.toString().equals("\n子流程验证信息：\n")) {
			MessageDialog.openInformation(null, "提示", sb.toString());
			return true;
		}
		return false;
	}

	private static void processDefaultTitleAndDefaultForm(Process process) {
		// TODO Auto-generated method stub
		boolean formUriYZ = false;
		boolean taskSubjectYZ = false;
		if (process.getExtensionValues().size() > 0) {

			for (ExtensionAttributeValue extensionAttributeValue : process.getExtensionValues()) {

				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof FormUri) {
						FormUri formUri = (FormUri) entry.getValue();
						if (formUri.getExpression().getValue() != null && !formUri.getExpression().getValue().equals("")) {
							formUriYZ = true;
						}

					}

					if (entry.getValue() instanceof TaskSubject) {
						TaskSubject taskSubject = (TaskSubject) entry.getValue();
						if (taskSubject.getExpression().getValue() != null && !taskSubject.getExpression().getValue().equals("")) {
							taskSubjectYZ = true;
						}

					}

				}

			}

		}
		if(formUriYZ&&taskSubjectYZ){
			return;
		}
		else{
			if(!formUriYZ){
				sb.append("流程定义上默认表单不能为空." + "\n");
			}
			if(!taskSubjectYZ){
				sb.append("流程定义上必默认任务主题不能为空." + "\n");
			}
			
		}
		return;

	}

	/**
	 * 验证子流程方法
	 * 
	 * @param flowElements
	 */
	public static void verificationProc(List<FlowElement> flowElements) {

		processHasEndEvent(flowElements);

		nodeHasPoint2StartEvent(flowElements);

		isStartEventHasMutiUserTaskNode(flowElements);

		endEventHasIncoming(flowElements);

		allUserTaskNodeHaveTaskCommand(flowElements);

		allUserTaskNodeHaveTaskAssignment(flowElements);

		taskAssignmentHasOneDuzhan(flowElements);

	}

	/**
	 * 子流程的验证方法
	 * 
	 * @param subProcess
	 * @return
	 */
	public static BaseElement getSubProcessElement(SubProcess subProcess, boolean yzForm) {
		
		String oldinfo=sb.toString();
		//String tiinfo="\n子流程 "+subProcess.getId()+" 验证信息：\n";
		sb.append("\n子流程 "+subProcess.getId()+" 验证信息：\n");
		
		String tiinfo=sb.toString();
		
		verificationProc(subProcess.getFlowElements());
		if (yzForm) {
			verifyFormIsNull(subProcess.getFlowElements());
		}

		for (FlowElement flowElementSub : subProcess.getFlowElements()) {

			if (flowElementSub instanceof SubProcess) {
				getSubProcessElement((SubProcess) flowElementSub, yzForm);
			}

		}
		
		
		
		if(sb.toString().equals(tiinfo)){
			sb = new StringBuffer();
			sb.append(oldinfo);
		}
		return null;

	}

	/**
	 * 判断UserTask节点是否挂上表单
	 * 
	 * @param flowElements
	 */
	public static void verifyFormIsNull(List<FlowElement> flowElements) {
		for (FlowElement flowElement : flowElements) {
			verifyFormIsNull(flowElement);
		}
	}

	/**
	 * 判断开始节点后是否有多个人工任务节点
	 * 
	 * @param flowElements
	 */
	public static void isStartEventHasMutiUserTaskNode(List<FlowElement> flowElements) {
		for (FlowElement flowElement : flowElements) {
			if (flowElement instanceof StartEvent) {
				if (((StartEvent) flowElement).getOutgoing().size() == 0) {
					sb.append("开始节点后面必须含有连接线." + "\n");
					return;
				}
				if (((StartEvent) flowElement).getOutgoing().size() > 1) {
					sb.append("开始节点后面请保证只含有一个人工任务节点用做提交." + "\n");
					return;
				}
				return;

			}
		}
		sb.append("必须含有一个开始节点." + "\n");
	}

	/**
	 * 判断提交节点是否带 提交处理命令的
	 * 
	 * @param flowElements
	 */
	public static void isSubmitNodeHasUserCommand(List<FlowElement> flowElements) {
		
		
		
		List<TaskCommand> taskCommands = new ArrayList<TaskCommand>();
		for (FlowElement flowElement : flowElements) {
			if (flowElement instanceof StartEvent) {
				if (((StartEvent) flowElement).getOutgoing().size() == 1) {
					SequenceFlow sequenceFlow = (((StartEvent) flowElement).getOutgoing()).get(0);
					if (sequenceFlow.getTargetRef() instanceof UserTask) {
						UserTask userTask = (UserTask) sequenceFlow.getTargetRef();
						for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {
							FeatureMap extensionElements = extensionAttributeValue.getValue();
							for (Entry entry : extensionElements) {
								if (entry.getValue() instanceof TaskCommand) {
									TaskCommand taskCommand = (TaskCommand) entry.getValue();
									taskCommands.add(taskCommand);
								}
							}
						}
					}
				}
			}
		}

		for (TaskCommand taskCommand : taskCommands) {
			if (taskCommands.size() > 0 && taskCommand != null) {
				if( taskCommand.getCommandType().equals("submit")||taskCommand.getCommandType().equals("startandsubmit")){
					return;
				}
				
			}
		}

		sb.append("提交节点至少含有一个(高级-提交)处理命令" + "\n");
	}

	/**
	 * 判断所有人工任务节点都有处理命令
	 * 
	 * @param flowElements
	 */
	public static void allUserTaskNodeHaveTaskCommand(List<FlowElement> flowElements) {
		for (FlowElement flowElement : flowElements) {
			allUserTaskNodeHaveTaskCommand(flowElement);
		}
	}

	/**
	 * 判断所有人工任务节点都有任务分配
	 * 
	 * @param flowElements
	 */
	public static void allUserTaskNodeHaveTaskAssignment(List<FlowElement> flowElements) {
		for (FlowElement flowElement : flowElements) {
			if (flowElement instanceof UserTask) {
				UserTask userTask = (UserTask) flowElement;
				if (userTask.getResources().size() < 1) {
					sb.append(userTask.getId() + "节点没有设置任务分配" + "\n");
				}
				LoopCharacteristics loopCharacteristics = userTask.getLoopCharacteristics();

				if (loopCharacteristics instanceof MultiInstanceLoopCharacteristics) {

					for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {
						FeatureMap extensionElements = extensionAttributeValue.getValue();

						for (Entry entry : extensionElements) {
							if (entry.getValue() instanceof TaskCommand) {
								TaskCommand taskCommand = (TaskCommand) entry.getValue();
								if (taskCommand.getCommandType().equals("rollBack")) {
									sb.append(((UserTask) flowElement).getId() + " 含有多实例的节点不能做退回处理" + "\n");
								}

							}
						}
					}

				}
			}
		}
	}

	/**
	 * 判断独占类型是否有多个
	 * 
	 * @param flowElements
	 */
	public static void taskAssignmentHasOneDuzhan(List<FlowElement> flowElements) {
		List<HumanPerformer> humanPerformers = new ArrayList<HumanPerformer>();
		for (FlowElement flowElement : flowElements) {
			if (flowElement instanceof UserTask) {
				UserTask userTask = (UserTask) flowElement;
				for (ResourceRole resourceRole : userTask.getResources()) {
					if (resourceRole.getClass().getSimpleName().equals("HumanPerformerImpl")) {
						HumanPerformer humanPerformer = (HumanPerformer) resourceRole;
						humanPerformers.add(humanPerformer);
					}
				}
				if (humanPerformers.size() > 1) {
					sb.append("分配任务中独占类型有且只能存在一个");
				}
				humanPerformers.clear();
			}
		}
	}

	/**
	 * 判断是否含有结束节点
	 * 
	 * @param flowElements
	 */
	public static void processHasEndEvent(List<FlowElement> flowElements) {
		List<EndEvent> endEvents = new ArrayList<EndEvent>();
		for (FlowElement flowElement : flowElements) {
			if (flowElement instanceof EndEvent) {
				EndEvent endEvent = (EndEvent) flowElement;
				endEvents.add(endEvent);
			}
		}
		if (endEvents.size() > 0) {

		} else {
			sb.append("流程没有设置结束节点" + "\n");
		}
	}

	/**
	 * 判断连接线是否连回开始节点
	 * 
	 * @param flowElements
	 */
	public static void nodeHasPoint2StartEvent(List<FlowElement> flowElements) {
		for (FlowElement flowElement : flowElements) {
			if (flowElement instanceof Task) {
				Task task = (Task) flowElement;
				for (SequenceFlow sequenceFlow : task.getOutgoing()) {
					if (sequenceFlow.getTargetRef() instanceof StartEvent) {
						sb.append("连接线不允许指向开始节点");
					}
				}
			}
		}
	}

	/**
	 * 判断结束节点上有没有连接线
	 * 
	 * @param flowElements
	 */
	public static void endEventHasIncoming(List<FlowElement> flowElements) {
		for (FlowElement flowElement : flowElements) {
			if (flowElement instanceof EndEvent) {
				EndEvent endEvent = (EndEvent) flowElement;
				if (endEvent.getIncoming().size() < 1) {
					sb.append("没有连接线指向结束节点\n");
				}
			}
		}
	}

	/**
	 * 判断表单是否空
	 * 
	 * @param flowElement
	 */
	private static void verifyFormIsNull(FlowElement flowElement) {
		if (flowElement instanceof UserTask) {
			UserTask userTask = (UserTask) flowElement;

			for (ExtensionAttributeValue extensionAttributeValue : userTask.getExtensionValues()) {
				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof FormUri) {
						FormUri formUri = (FormUri) entry.getValue();
						if (formUri.getExpression().getValue() != null || !(formUri.getExpression().getValue().equals(""))) {
							return;
						}
					}

				}
			}
			sb.append(userTask.getId() + "节点没有设置表单" + "\n");
		}
	}

	/**
	 * 验证所有人工节点含有处理命令
	 * 
	 * @param flowElement
	 */
	private static void allUserTaskNodeHaveTaskCommand(FlowElement flowElement) {
		if (flowElement instanceof UserTask) {

			for (ExtensionAttributeValue extensionAttributeValue : ((UserTask) flowElement).getExtensionValues()) {
				FeatureMap extensionElements = extensionAttributeValue.getValue();

				for (Entry entry : extensionElements) {
					if (entry.getValue() instanceof TaskCommand) {
						TaskCommand taskCommand = (TaskCommand) entry.getValue();
						if (taskCommand != null) {
							return;
						}
					}
				}
			}

			sb.append(((UserTask) flowElement).getId() + "节点没有设置处理命令" + "\n");
		}
	}

}
