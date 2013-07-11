package com.founder.fix.fixflow.core.impl.connector;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.exception.FixFlowConnectorException;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;


public class ConnectorDefinition {

	protected String connectorId;
	protected String packageName;
	protected String className;
	protected String connectorInstanceId;
	protected String connectorInstanceName;
	protected String eventType;
	protected String documentation;
	protected String errorHandling;
	protected String errorCode;
	protected List<ConnectorParameterInputs> connectorParameterInputs;
	protected List<ConnectorParameterOutputs> connectorParameterOutputs;
	
	protected boolean isTimeExecute=false;

	protected String timeExpression;

	

	protected String skipExpression;
	
	
	
	public String getConnectorId() {
		return connectorId;
	}

	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getConnectorInstanceId() {
		return connectorInstanceId;
	}

	public void setConnectorInstanceId(String connectorInstanceId) {
		this.connectorInstanceId = connectorInstanceId;
	}

	public String getConnectorInstanceName() {
		return connectorInstanceName;
	}

	public void setConnectorInstanceName(String connectorInstanceName) {
		this.connectorInstanceName = connectorInstanceName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public String getErrorHandling() {
		return errorHandling;
	}

	public void setErrorHandling(String errorHandling) {
		this.errorHandling = errorHandling;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getSkipExpression() {
		return skipExpression;
	}

	public void setSkipExpression(String skipExpression) {
		this.skipExpression = skipExpression;
	}
	
	public boolean isTimeExecute() {
		return isTimeExecute;
	}

	public void setTimeExecute(boolean isTimeExecute) {
		this.isTimeExecute = isTimeExecute;
	}
	
	public String getTimeExpression() {
		return timeExpression;
	}

	public void setTimeExpression(String timeExpression) {
		this.timeExpression = timeExpression;
	}


	public List<ConnectorParameterInputs> getConnectorParameterInputs() {

		if (this.connectorParameterInputs == null) {
			this.connectorParameterInputs = new ArrayList<ConnectorParameterInputs>();
		}

		return this.connectorParameterInputs;
	}

	public List<ConnectorParameterOutputs> getConnectorParameterOutputs() {

		if (this.connectorParameterOutputs == null) {
			this.connectorParameterOutputs = new ArrayList<ConnectorParameterOutputs>();
		}
		return connectorParameterOutputs;
	}

	public void execute(ExecutionContext executionContext) {
		// TODO Auto-generated method stub

		try {
			
			if(this.skipExpression!=null&&!this.skipExpression.equals("")){
				Object skipExpressionObj=ExpressionMgmt.execute(skipExpression, executionContext);
				if(StringUtil.getBoolean(skipExpressionObj)){
					return ;
				}
			}
			
			
			
			
			
			
			
			String classNameObj = packageName +"."+ className;
			Class<?> connectorHandlerClass = Class.forName(classNameObj);
			ConnectorHandler connectorInstance = (ConnectorHandler) connectorHandlerClass.newInstance();
			
			
			
			
	
			
			for (ConnectorParameterInputs connectorParameterInputs : this.getConnectorParameterInputs()) {
				
				Class<?> ptypes[] = new Class[1];
			
				ptypes[0] = Class.forName(connectorParameterInputs.getDataType());
		
				
				String parameterInputsId=connectorParameterInputs.getId();
				
				String methodString="set"+parameterInputsId.substring(0, 1).toUpperCase()+parameterInputsId.substring(1, parameterInputsId.length());
				Method m = connectorHandlerClass.getMethod(methodString, ptypes);
				
				
				String scriptString=connectorParameterInputs.getExpressionText();
				if(scriptString!=null){
					Object arg[] = new Object[1];
					arg[0] =ExpressionMgmt.execute(scriptString, executionContext);
					m.invoke(connectorInstance, arg);
				}
				
				
			}
			
			connectorInstance.execute(executionContext);
			
			
			
			for (ConnectorParameterOutputs connectorParameterOutputs : this.getConnectorParameterOutputs()) {
						
				
				String parameterOutputsId=connectorParameterOutputs.getExpressionText();
				
				String methodString="get"+parameterOutputsId.substring(0, 1).toUpperCase()+parameterOutputsId.substring(1, parameterOutputsId.length());
				Method m = connectorHandlerClass.getMethod(methodString);
				
				
				String variableTarget=connectorParameterOutputs.getVariableTarget();
				//Object arg[] = new Object[1];
				//arg[0] =Context.getBshInterpreter().eval(scriptString);
				
				
				
				
				Object objectValue=m.invoke(connectorInstance);
				
				
			
		
				ExpressionMgmt.setVariable(variableTarget, objectValue,executionContext);
				
			}
			
			
		
			
		} catch (Exception e) {
			
			
				throw new FixFlowConnectorException(e.getMessage(), e);
			
			
			
			
		}

	}
	
	
	

}
