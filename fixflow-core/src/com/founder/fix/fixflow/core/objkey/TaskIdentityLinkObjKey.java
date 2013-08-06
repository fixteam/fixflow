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
package com.founder.fix.fixflow.core.objkey;

public class TaskIdentityLinkObjKey {
	
	/**
	 * 任务候选人表名
	 * @return
	 */
	public static String TaskIdentityLinkTableName(){
		return "FIXFLOW_RUN_TASKIDENTITYLINK";
	}
	
	/**
	 * 任务候选人归档表名
	 * @return
	 */
	public static String TaskIdentityLinkHisTableName(){
		return "FIXFLOW_HIS_TASKIDENTITYLINK";
	}
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
	
	/**
	 * 归档时间
	 * @return
	 */
	public static FlowKeyArchiveTime ArchiveTime(){
		return new TaskIdentityLinkObjKey().new FlowKeyArchiveTime();
	}
	
	public class FlowKeyArchiveTime implements ObjKeyInterface{

		public String EntityKey() {
			// TODO Auto-generated method stub
			return "archive_time";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "ARCHIVE_TIME";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "archiveTime";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "归档时间";
		}

	}

}
