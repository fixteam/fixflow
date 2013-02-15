package com.founder.fix.fixflow.core.impl.datavariable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.DataVariableBehavior;
import com.founder.fix.fixflow.core.impl.runtime.ProcessInstanceEntity;


public class DataVariableMgmtInstance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3342152041332527028L;

	protected DataVariableMgmtDefinition dataVariableMgmtDefinition;
	
	protected ProcessInstanceEntity processInstance;
	
	
	protected List<DataVariableInstance> dataVariableInstances=new ArrayList<DataVariableInstance>();
	
	public List<DataVariableInstance> getDataVariableInstances() {
		return dataVariableInstances;
	}
	
	
	public DataVariableInstance getDataVariableById(String id){
		
		for (DataVariableInstance dataVariableInstance : dataVariableInstances) {
			if(dataVariableInstance.getId().equals(id)){
				return dataVariableInstance;
			}
		}
		return null;
		
	}
	
	public DataVariableInstance getDataVariableByExpressionId(String expressionId){
		for (DataVariableInstance dataVariableInstance : dataVariableInstances) {
			if(dataVariableInstance.getId().equals(expressionId)){
				return dataVariableInstance;
			}
		}
		return null;
	}


	public DataVariableInstance createDataVariableInstance(DataVariableBehavior dataVariableBehavior){
		DataVariableInstance dataVariableInstance=new DataVariableInstance(dataVariableBehavior,this);
		dataVariableInstances.add(dataVariableInstance);
		return dataVariableInstance;
	}
	

	public ProcessInstanceEntity getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(ProcessInstanceEntity processInstance) {

		this.dataVariableMgmtDefinition=processInstance.getProcessDefinition().getDataVariableMgmtDefinition();
		
		this.processInstance = processInstance;
		this.processInstance.setDataVariableMgmtInstance(this);
		
	}
	


	
}
