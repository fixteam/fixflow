/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
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
