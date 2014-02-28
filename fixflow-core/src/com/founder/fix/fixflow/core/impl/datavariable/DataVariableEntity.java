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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.DataVariableBehavior;
import com.founder.fix.fixflow.core.impl.command.QueryVariablesCommand;
import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.expression.ExpressionMgmt;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;


public class DataVariableEntity extends AbstractPersistentObject<DataVariableEntity>{

	
	
	
	public final static String QUERY_DATA_KEY="queryBizVariable";

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3906878781132663222L;
	
	// 静态字段 //////////////////////////////////////////////////////////////
	

	public static final String RULE_GET_VARIABLE_PERSISTENT_STATE = "getVariablePersistentState";

	public static final String RULE_GET_VARIABLE_PERSISTENT_DBMAP = "getVariablePersistentDbMap";

	public static final String RULE_VARIABLE_CLONE = "variableClone";


	// 需要持久化的字段 //////////////////////////////////////////////////////////
	
	protected String processInstanceId;

	protected String variableKey;

	protected byte[] variableValue;

	protected String variableClassName;

	protected String taskInstanceId;

	protected String tokenId;

	protected String nodeId;

	protected String variableType;

	protected String bizData;
	
	protected boolean isPersistence;
	
	protected Date archiveTime;
	
	// get set //////////////////////////////////////////////////////////
	
	/**
	 * 获取变量编号
	 */
	public String getId() {

		return this.variableKey;
	}

	
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getVariableKey() {
		return variableKey;
	}

	public void setVariableKey(String variableKey) {
		this.variableKey = variableKey;
	}

	public byte[] getVariableValue() {
		return variableValue;
	}
	
	public Object getVariableObject() {
		
		
		return bytesToObject(variableValue);
	}

	public void setVariableValue(byte[] variableValue) {
		this.variableValue = variableValue;
	}

	public String getVariableClassName() {
		return variableClassName;
	}

	public void setVariableClassName(String variableClassName) {
		this.variableClassName = variableClassName;
	}

	public String getTaskInstanceId() {
		return taskInstanceId;
	}

	public void setTaskInstanceId(String taskInstanceId) {
		this.taskInstanceId = taskInstanceId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getVariableType() {
		return variableType;
	}

	public void setVariableType(String variableType) {
		this.variableType = variableType;
	}

	public String getBizData() {
		return bizData;
	}

	public void setBizData(String bizData) {
		this.bizData = bizData;
	}
	
	
	public Date getArchiveTime() {
		return archiveTime;
	}


	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	
	
	// 对象化元素 //////////////////////////////////////////////////////////

	protected DataVariableBehavior dataVariableBehavior;

	protected DataVariableMgmtInstance dataVariableMgmtInstance;
	
	public DataVariableEntity () {
	
	}


	public DataVariableEntity (DataVariableBehavior dataVariableBehavior, DataVariableMgmtInstance dataVariableMgmtInstance) {
	
		
		
		
		this.dataVariableBehavior = dataVariableBehavior;
		this.isPersistence=dataVariableBehavior.isPersistence();
		this.variableKey=dataVariableBehavior.getId();
		this.dataVariableMgmtInstance = dataVariableMgmtInstance;
		this.processInstanceId=dataVariableMgmtInstance.getProcessInstance().getId();
		this.variableType=dataVariableBehavior.getBizType();

	}
	
	/**
	 * 是否为持久化变量
	 * @return
	 */
	public boolean isPersistence() {
		return this.isPersistence;
	}
	public void setPersistence(boolean isPersistence) {
		this.isPersistence = isPersistence;
	}

	
	
	

	public Object getExpressionValue() {
		Object object = ExpressionMgmt.getVariable(getId());
		return object;
	}

	public void setExpressionValue(Object expressionValue) {
		ExpressionMgmt.setVariable(getId(), expressionValue);
	}


	

	

	
	public void executeExpression(ExecutionContext executionContext) {

		// 对于需要持久化的数据变量的处理
		if (this.isPersistence()) {

			String processInstanceId = this.dataVariableMgmtInstance.getProcessInstance().getId();
			List<String> variableNames = new ArrayList<String>();
			String variableName = this.dataVariableBehavior.getId();
			variableNames.add(variableName);
			
			QueryVariablesCommand queryVariablesCommand=new QueryVariablesCommand();
			queryVariablesCommand.setVariableNames(variableNames);
			queryVariablesCommand.setProcessInstanceId(processInstanceId);

		

			Map<String, Object> returnMap = Context.getCommandContext().getVariableManager().queryVariable(queryVariablesCommand);
			if (returnMap != null && returnMap.containsKey(variableName)) {
				this.setAdd(false);
				ExpressionMgmt.setVariable(getId(), returnMap.get(variableName));

			} else {
				Object object = null;
				if (dataVariableBehavior.getExpression() != null) {
					object = ExpressionMgmt.execute(dataVariableBehavior.getExpression(), executionContext);
				}

				ExpressionMgmt.setVariable(getId(), object);
			}

		} else {
			// 不需要持久化的数据变量的处理
			Object object = null;
			if (dataVariableBehavior.getExpression() != null) {
				object = ExpressionMgmt.execute(dataVariableBehavior.getExpression(), executionContext);
			}

			ExpressionMgmt.setVariable(getId(), object);

		}

	}


	@Override
	public String getCloneRuleId() {
		// TODO Auto-generated method stub
		return RULE_VARIABLE_CLONE;
	}


	@Override
	public String getPersistentDbMapRuleId() {
		// TODO Auto-generated method stub
		return RULE_GET_VARIABLE_PERSISTENT_DBMAP;
	}


	@Override
	public String getPersistentStateRuleId() {
		// TODO Auto-generated method stub
		return RULE_GET_VARIABLE_PERSISTENT_STATE;
	}
	
	
	/**
	 * byte[] to long
	 * 
	 * @param b
	 * @return
	 */
	public static Object bytesToObject(byte[] b) {

		if (b.length > 0) {
			ObjectInput in = null;
			try {
				ByteArrayInputStream byteIn = new ByteArrayInputStream(b);
				in = new ObjectInputStream(byteIn);
				Object obj = in.readObject();

				if (obj != null) {
					return obj;
				}

			} catch (IOException e) {

			} catch (ClassNotFoundException e) {

			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {

					}
				}
			}

			return null;
		} else {

			return null;
		}
	}
	
	
	public static byte[] ObjectToBytes(Object obj) {

		ObjectOutput out = null;
		try {
			ByteArrayOutputStream byteout = new ByteArrayOutputStream();
			out = new ObjectOutputStream(byteout);
			out.writeObject(obj);
			byte[] buf = byteout.toByteArray();

			return buf;
		} catch (IOException e) {
			return null;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {

				}
			}
		}
	}

}
