package com.founder.fix.fixflow.core.impl.bpmn.behavior;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpmn2.UserTask;

import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.bpmn2extensions.fixflow.TaskCommand;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.UserCommandQueryTo;

public class TaskCommandInst implements UserCommandQueryTo{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3240434310337515303L;

	protected String id;

	Boolean booleanInternationalization=false;

	protected String name;

	protected String expression;
	
	protected String expressionParam;
	
	protected String taskCommandType;
	
	protected boolean isAdmin=false;

	
	protected UserTask userTask;
	
	
	boolean isVerification=true;
	boolean isSaveData=true;
	boolean isSimulationRun=false;

	

	




	public TaskCommandInst(String id,String name,String expression,String taskCommandType,boolean isAdmin){
		this.id=id;
		this.name=name;
		this.expression=expression;
		this.taskCommandType=taskCommandType;
		this.isAdmin=isAdmin;
	}
	
	public String getExpressionParam() {
		return expressionParam;
	}
	
	
	protected Map<String, Object> map=new HashMap<String, Object>();
	/**
	 * 获取处理命令参数表达式
	 * @return
	 */
	public Map<String, Object> getParamMap(){
		return map;
	}
	
	public void clearParamMap(){
		map.clear();
	}
	
	@SuppressWarnings("unchecked")
	public void execExpressionParam(ExecutionContext executionContext,ProcessDefinitionBehavior processDefinitionBehavior){
		
		
		
		if(getExpressionParam()!=null&&!getExpressionParam().equals("")){
			
			Map<String, Object> mapObj=null;
			
			
			if(executionContext!=null){
				mapObj= (Map<String, Object>)ExpressionMgmt.execute(this.getExpressionParam(),executionContext);
			}else{
				if(executionContext==null&&processDefinitionBehavior!=null){
					mapObj= (Map<String, Object>)ExpressionMgmt.execute(this.getExpressionParam(),processDefinitionBehavior);
				}else{
					mapObj= (Map<String, Object>)ExpressionMgmt.execute(this.getExpressionParam());
				}
			}
			
		
			if(mapObj!=null&&mapObj instanceof Map){
				map=mapObj;
			}
		}
		
	}
	

	public TaskCommandInst(TaskCommand taskCommand,UserTask userTask) {

		

		this.userTask=userTask;
		
		if(taskCommand.getExpression()!=null){
			this.expression=taskCommand.getExpression().getValue();
		}
		if(taskCommand.getParameterExpression()!=null){
			this.expressionParam=taskCommand.getParameterExpression().getValue();
		}
		
		this.id=taskCommand.getId();
		this.name=taskCommand.getName();
		
		
		Object isVerificationObject=taskCommand.getIsVerification();
		Object isSaveDataObject=taskCommand.getIsSaveData();
		Object isSimulationRunObject=taskCommand.getIsSimulationRun();
		
		
		
		TaskCommandDef taskCommandDef=Context.getProcessEngineConfiguration().getTaskCommandDefMap().get(taskCommandType);
		if(taskCommandDef!=null){
			isVerification=StringUtil.getBoolean(taskCommandDef.getIsEnabled());
			isSaveData=StringUtil.getBoolean(taskCommandDef.getIsSaveData());
			isSimulationRun=StringUtil.getBoolean(taskCommandDef.getIsSimulationRun());
		}
		
		
		
		
		if(isVerificationObject!=null&&!isVerificationObject.equals("")){
			isVerification=StringUtil.getBoolean(isVerificationObject);
		}
		if(isSaveDataObject!=null&&!isSaveDataObject.equals("")){
			isSaveData=StringUtil.getBoolean(isSaveDataObject);
		}
		if(isSimulationRunObject!=null&&!isSimulationRunObject.equals("")){
			isSimulationRun=StringUtil.getBoolean(isSimulationRunObject);
		}
		
		
		
		this.taskCommandType=taskCommand.getCommandType();
		
		booleanInternationalization=StringUtil.getBoolean(Context.getProcessEngineConfiguration().getInternationalizationConfig().getIsEnable());
    	
    	
    	

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
		
		
		if(booleanInternationalization){
    		DefinitionsBehavior definitionsBehavior=(DefinitionsBehavior) userTask.eResource().getContents().get(0).eContents().get(0);
        	String processId=definitionsBehavior.getProcessId();
        	
        	String nameTemp=Context.getProcessEngineConfiguration().getFixFlowResources().getResourceName(processId, userTask.getId()+"_"+id);
        	if(nameTemp==null){
        		
        	}
        	else{
        		this.name= nameTemp;
        	}
        	
    	}
		return name;
	}

	public String getExpression() {
		return expression;
	}
	
	public String getTaskCommandType() {
		return taskCommandType;
	}
	
	
	public boolean isVerification() {
		return isVerification;
	}


	public boolean isSaveData() {
		return isSaveData;
	}


	public boolean isSimulationRun() {
		return isSimulationRun;
	}

	public Map<String, Object> getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id", this.id);
		persistentState.put("name", getName());
		persistentState.put("type", this.taskCommandType);
		persistentState.put("isAdmin", this.isAdmin);
		persistentState.put("isVerification", this.isVerification);
		persistentState.put("isSaveData", this.isSaveData);
		persistentState.put("isSimulationRun", this.isSimulationRun);
		
		if( this.userTask!=null){
			persistentState.put("nodeId", this.userTask.getId());
			persistentState.put("nodeName", this.userTask.getName());
		}
		
		if(map.keySet().size()>0){
			for (String mapKey : map.keySet()) {
				if(map.get(mapKey)!=null){ 
					persistentState.put("fixParam_"+mapKey, map.get(mapKey));
				}
			}
		}
		
		return persistentState;
	}

	

}
