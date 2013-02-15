package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.ecore.util.FeatureMap;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.util.EMFExtensionUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.task.UserCommandQueryTo;

public class TaskCommandInst implements UserCommandQueryTo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3240434310337515303L;

	protected String id;

	

	protected String name;

	protected String expression;
	
	protected String taskCommandType;
	
	protected boolean isAdmin=false;

	
	protected UserTask userTask;

	

	

	public TaskCommandInst(String id,String name,String expression,String taskCommandType,boolean isAdmin){
		this.id=id;
		this.name=name;
		this.expression=expression;
		this.taskCommandType=taskCommandType;
		this.isAdmin=isAdmin;
	}
	

	public TaskCommandInst(FeatureMap.Entry entry,UserTask userTask) {


		
		List<FeatureMap.Entry> expressionEntry=EMFExtensionUtil.getExtensionElementsInEntry(entry, "expression");
		String expressionValue=null;
		if(expressionEntry!=null&&expressionEntry.size()>0){
			expressionValue=EMFExtensionUtil.getExtensionElementValue(expressionEntry.get(0));
		}
		
		this.userTask=userTask;
		

		this.expression=expressionValue;
		this.id=EMFExtensionUtil.getExtensionElementAttributeValue(entry, "id");
		this.name=EMFExtensionUtil.getExtensionElementAttributeValue(entry, "name");
		
		
		this.taskCommandType=EMFExtensionUtil.getExtensionElementAttributeValue(entry, "commandType");
		
		Boolean booleanTemp=StringUtil.getBoolean(Context.getProcessEngineConfiguration().getInternationalizationConfig().getIsEnable());
    	
    	
    	if(booleanTemp){
    		DefinitionsBehavior definitionsBehavior=(DefinitionsBehavior) userTask.eResource().getContents().get(0).eContents().get(0);
        	String processId=definitionsBehavior.getProcessId();
        	
        	String nameTemp=Context.getProcessEngineConfiguration().getFixFlowResources().getResourceName(processId, userTask.getId()+"_"+id);
        	if(nameTemp==null){
        		
        	}
        	else{
        		this.name= nameTemp;
        	}
        	
    	}

	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	public UserTask getUserTask() {
		return userTask;
	}


	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getExpression() {
		return expression;
	}
	
	public String getTaskCommandType() {
		return taskCommandType;
	}
	
	
	

	public Map<String, Object> getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id", this.id);
		persistentState.put("name", this.name);
		persistentState.put("type", this.taskCommandType);
		persistentState.put("isAdmin", this.isAdmin);
		if( this.userTask!=null){
			persistentState.put("nodeId", this.userTask.getId());
			persistentState.put("nodeName", this.userTask.getName());
		}
	
		return persistentState;
	}

	

}
