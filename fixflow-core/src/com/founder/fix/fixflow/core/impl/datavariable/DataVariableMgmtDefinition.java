package com.founder.fix.fixflow.core.impl.datavariable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.DataVariableBehavior;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;

public class DataVariableMgmtDefinition implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 105484354790821227L;
	
	protected List<DataVariableBehavior> dataVariableBehaviors=new ArrayList<DataVariableBehavior>();
	
	
	

	protected ProcessDefinitionBehavior processDefinition;

	public DataVariableMgmtDefinition(ProcessDefinitionBehavior processDefinition){
		this.processDefinition=processDefinition;
	}

	public ProcessDefinitionBehavior getProcessDefinition() {
		return processDefinition;
	}

	public void addDataVariableBehavior(DataVariableBehavior dataVariableBehavior) {
		this.dataVariableBehaviors.add(dataVariableBehavior);
	}
	
	/**
	 * 获取流程定义的全局变量
	 * @return
	 */
	public List<DataVariableBehavior> getDataVariableBehaviorsByProcess(){
		List<DataVariableBehavior> dataVariableBehaviorsTemp=new ArrayList<DataVariableBehavior>();
		for (DataVariableBehavior dataVariableBehavior : this.dataVariableBehaviors) {
			if(dataVariableBehavior.isPubilc()){
				dataVariableBehaviorsTemp.add(dataVariableBehavior);
			}
		}
		return dataVariableBehaviorsTemp;
	}
	
	/**
	 * 获取指定节点的数据变量
	 * @param nodeId 节点编号
	 * @return
	 */
	public List<DataVariableBehavior> getDataVariableBehaviorsByNodeId(String nodeId){
		List<DataVariableBehavior> dataVariableBehaviorsTemp=new ArrayList<DataVariableBehavior>();
		for (DataVariableBehavior dataVariableBehavior : this.dataVariableBehaviors) {
			if(dataVariableBehavior.getNodeId().equals(nodeId)){
				dataVariableBehaviorsTemp.add(dataVariableBehavior);
			}
		}
		return dataVariableBehaviorsTemp;
	}

	public List<DataVariableBehavior> getDataVariableBehaviors() {
		return dataVariableBehaviors;
	}

	

}
