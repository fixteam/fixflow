package com.founder.fix.fixflow.core.objkey;


public class VariableObjKey {

	/**
	 * 编号
	 * @return
	 */
	public static FlowKeyProcessInstanceId ProcessInstanceId(){
		return new VariableObjKey().new FlowKeyProcessInstanceId();
	}
	
	
	public class FlowKeyProcessInstanceId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "processInstanceId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "PROCESSINSTANCE_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "processInstanceId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "流程实例编号";
		}

	}
	
	
	/**
	 * 变量key
	 * @return
	 */
	public static FlowKeyVariableKey VariableKey(){
		return new VariableObjKey().new FlowKeyVariableKey();
	}
	
	public class FlowKeyVariableKey implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "variableKey";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "VARIABLE_KEY";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "variableKey";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "变量key";
		}

	}
	
	
	/**
	 * 变量值
	 * @return
	 */
	public static FlowKeyVariableValue VariableValue(){
		return new VariableObjKey().new FlowKeyVariableValue();
	}
	
	public class FlowKeyVariableValue implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "variableValue";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "VARIABLE_VALUE";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "variableValue";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "变量值";
		}

	}
	
	/**
	 * 变量类全名
	 * @return
	 */
	public static FlowKeyVariableClassName VariableClassName(){
		return new VariableObjKey().new FlowKeyVariableClassName();
	}
	
	public class FlowKeyVariableClassName implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "variableClassName";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "VARIABLE_CLASSNAME";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "variableClassName";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "变量类全名";
		}

	}
	
	/**
	 * 令牌号
	 * @return
	 */
	public static FlowKeyTokenId TokenId(){
		return new VariableObjKey().new FlowKeyTokenId();
	}
	
	public class FlowKeyTokenId implements ObjKeyInterface{

		public String EntityKey() {
			return "tokenId";
		}
		
	
		public String DataBaseKey() {
			return "TOKEN_ID";
		}
		
		public String FullKey() {
			return "tokenId";
		}

		public String KeyName() {
			return "令牌号";
		}

	}
	
	/**
	 * 节点编号
	 * @return
	 */
	public static FlowKeyNodeId NodeId(){
		return new VariableObjKey().new FlowKeyNodeId();
	}
	
	public class FlowKeyNodeId implements ObjKeyInterface{

		public String EntityKey() {
			return "nodeId";
		}
		
	
		public String DataBaseKey() {
			return "NODE_ID";
		}
		
		public String FullKey() {
			return "nodeId";
		}

		public String KeyName() {
			return "节点编号";
		}

	}
	
	
	/**
	 * 任务编号
	 * @return
	 */
	public static FlowKeyTaskInstanceId TaskInstanceId(){
		return new VariableObjKey().new FlowKeyTaskInstanceId();
	}
	
	public class FlowKeyTaskInstanceId implements ObjKeyInterface{

		public String EntityKey() {
			return "taskInstanceId";
		}
		
	
		public String DataBaseKey() {
			return "TASKINSTANCE_ID";
		}
		
		public String FullKey() {
			return "taskInstanceId";
		}

		public String KeyName() {
			return "任务编号";
		}

	}
	
}
