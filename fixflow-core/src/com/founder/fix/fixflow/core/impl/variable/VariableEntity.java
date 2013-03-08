package com.founder.fix.fixflow.core.impl.variable;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.impl.db.PersistentObject;

public abstract class VariableEntity  implements PersistentObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1397214384935667249L;
	
	protected List<VariableFlowTypeEntity> variableFlowTypeEntities=new ArrayList<VariableFlowTypeEntity>();
	
	public void addVariableFlowType(VariableFlowTypeEntity variableFlowType){
		if(variableFlowType==null){
			return;
		}
		for (VariableFlowTypeEntity variableFlowTypeEntity : variableFlowTypeEntities) {
			if(variableFlowTypeEntity.getVariableFlowType().equals(variableFlowType.getVariableFlowType())){
				variableFlowTypeEntity.setTypeValue(variableFlowType.getTypeValue());
				return;
			}
			
		}
		variableFlowTypeEntities.add(variableFlowType);
	}
	
	public List<VariableFlowTypeEntity> getVariableFlowTypeEntities() {
		return variableFlowTypeEntities;
	}

}
