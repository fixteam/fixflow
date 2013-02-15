package com.founder.fix.fixflow.core.objkey;

public class TaskIdentityLinkObjKey {
	

	/**
	 * 编号
	 * @return
	 */
	public static FlowKeyId Id(){
		return new TaskIdentityLinkObjKey().new FlowKeyId();
	}
	
	public class FlowKeyId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "id";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "id";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "编号";
		}

	}
	
	
	
	/**
	 * 分配类型
	 * @return
	 */
	public static FlowKeyType Type(){
		return new TaskIdentityLinkObjKey().new FlowKeyType();
	}
	
	public class FlowKeyType implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "type";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "TYPE";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "type";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "分配类型";
		}

	}
	
	
	/**
	 * 用户编号
	 * @return
	 */
	public static FlowKeyUserId UserId(){
		return new TaskIdentityLinkObjKey().new FlowKeyUserId();
	}
	
	public class FlowKeyUserId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "userId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "USER_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "userId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "用户编号";
		}

	}
	
	
	
	
	/**
	 * 组编号
	 * @return
	 */
	public static FlowKeyGroupId GroupId(){
		return new TaskIdentityLinkObjKey().new FlowKeyGroupId();
	}
	
	public class FlowKeyGroupId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "groupId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "GROUP_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "groupId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "组编号";
		}

	}
	
	
	
	
	/**
	 * 组类型
	 * @return
	 */
	public static FlowKeyGroupType GroupType(){
		return new TaskIdentityLinkObjKey().new FlowKeyGroupType();
	}
	
	public class FlowKeyGroupType implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "groupType";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "GROUP_TYPE";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "groupType";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "组类型";
		}

	}
	
	
	/**
	 * 任务实例编号
	 * @return
	 */
	public static FlowKeyTaskInstanceId TaskInstanceId(){
		return new TaskIdentityLinkObjKey().new FlowKeyTaskInstanceId();
	}
	
	public class FlowKeyTaskInstanceId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "taskId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "TASKINSTANCE_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "taskInstanceId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "任务实例编号";
		}

	}
	
	
	
	/**
	 * 包含排除
	 * @return
	 */
	public static FlowKeyIncludeExclusion IncludeExclusion(){
		return new TaskIdentityLinkObjKey().new FlowKeyIncludeExclusion();
	}
	
	public class FlowKeyIncludeExclusion implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "includeExclusion";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "INCLUDE_EXCLUSION";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "includeExclusion";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "包含排除";
		}

	}
	
	

}
