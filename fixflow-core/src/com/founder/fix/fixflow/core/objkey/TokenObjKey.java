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

import com.founder.fix.fixflow.core.runtime.QueryLocation;


public class TokenObjKey {
	
	/**
	 * 查询类型
	 * @param tableType 0或null查运行表，1查历史表 2查历史和run表
	 * @return
	 */
	public static String getTableName(QueryLocation queryLocation){
		String tableName = "";
		if(QueryLocation.HIS.equals(queryLocation)){
			tableName =  TokenHisTableName();
		}else if(QueryLocation.RUN_HIS.equals(queryLocation)){
			tableName = "(select * from "+TokenTableName()+" union all select * from "+TokenHisTableName()+")";
		}else{
			tableName = TokenTableName();
		}
		return tableName;
	}
	
	/**
	 * 令牌表名
	 * @return
	 */
	public static String TokenTableName(){
		return "FIXFLOW_RUN_TOKEN";
	}
	
	/**
	 * 令牌归档表名
	 * @return
	 */
	public static String TokenHisTableName(){
		return "FIXFLOW_HIS_TOKEN";
	}
	
	/**
	 * 编号
	 * @return
	 */
	public static FlowKeyTokenId TokenId(){
		return new TokenObjKey().new FlowKeyTokenId();
	}
	
	public class FlowKeyTokenId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "id";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "TOKEN_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "tokenId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "编号";
		}

	}
	
	
	/**
	 * 名称
	 * @return
	 */
	public static FlowKeyName Name(){
		return new TokenObjKey().new FlowKeyName();
	}
	
	public class FlowKeyName implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "name";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "NAME";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "name";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "名称";
		}

	}
	
	
	
	/**
	 * 启动时间
	 * @return
	 */
	public static FlowKeyStartTime StartTime(){
		return new TokenObjKey().new FlowKeyStartTime();
	}
	
	public class FlowKeyStartTime implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "startTime";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "START_TIME";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "startTime";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "启动时间";
		}

	}
	
	
	
	

	/**
	 * 结束时间
	 * @return
	 */
	public static FlowKeyEndTime EndTime(){
		return new TokenObjKey().new FlowKeyEndTime();
	}
	
	public class FlowKeyEndTime implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "endTime";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "END_TIME";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "endTime";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "结束时间";
		}

	}
	
	
	

	/**
	 * 令牌进入节点的时间
	 * @return
	 */
	public static FlowKeyNodeEnterTime NodeEnterTime(){
		return new TokenObjKey().new FlowKeyNodeEnterTime();
	}
	
	public class FlowKeyNodeEnterTime implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "nodeEnterTime";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "NODEENTER_TIME";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "nodeEnterTime";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "令牌进入节点的时间";
		}

	}
	
	
	
	/**
	 * 在创建分支令牌为true,当子令牌到达join是会被设为false.
	 * @return
	 */
	public static FlowKeyIsAbleToReactivateParent IsAbleToReactivateParent(){
		return new TokenObjKey().new FlowKeyIsAbleToReactivateParent();
	}
	
	public class FlowKeyIsAbleToReactivateParent implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "isAbleToReactivateParent";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "ISABLETOREACTIVATEPARENT";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "isAbleToReactivateParent";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "在创建分支令牌为true,当子令牌到达join是会被设为false.";
		}

	}
	
	
	
	
	/**
	 * 是否停止
	 * @return
	 */
	public static FlowKeyIsSuspended IsSuspended(){
		return new TokenObjKey().new FlowKeyIsSuspended();
	}
	
	public class FlowKeyIsSuspended implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "isSuspended";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "ISSUSPENDED";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "isSuspended";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "是否停止";
		}

	}
	
	
	
	
	
	/**
	 * 令牌是否被锁定
	 * @return
	 */
	public static FlowKeyIsLocked IsLocked(){
		return new TokenObjKey().new FlowKeyIsLocked();
	}
	
	public class FlowKeyIsLocked implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "isLocked";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "TOKEN_LOCK";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "isLocked";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "令牌是否被锁定";
		}

	}
	
	
	
	
	/**
	 * 节点编号
	 * @return
	 */
	public static FlowKeyNodeId NodeId(){
		return new TokenObjKey().new FlowKeyNodeId();
	}
	
	public class FlowKeyNodeId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "nodeId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "NODE_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "nodeId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "节点编号";
		}

	}
	
	
	
	
	/**
	 * 流程实例编号
	 * @return
	 */
	public static FlowKeyProcessInstanceId ProcessInstanceId(){
		return new TokenObjKey().new FlowKeyProcessInstanceId();
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
	 * 父令牌编号
	 * @return
	 */
	public static FlowKeyParentTokenId ParentTokenId(){
		return new TokenObjKey().new FlowKeyParentTokenId();
	}
	
	public class FlowKeyParentTokenId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "parentTokenId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "PARENT_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "parentTokenId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "父令牌编号";
		}

	}
	

	
	
	/**
	 * 是否是子流程根令牌
	 * @return
	 */
	public static FlowKeyIsSubProcessRootToken IsSubProcessRootToken(){
		return new TokenObjKey().new FlowKeyIsSubProcessRootToken();
	}
	
	public class FlowKeyIsSubProcessRootToken implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "isSubProcessRootToken";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "ISSUBPROCESSROOTTOKEN";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "isSubProcessRootToken";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "是否是子流程根令牌";
		}

	}
	
	
	
	/**
	 * 是否为自由令牌
	 * @return
	 */
	public static FlowKeyFreeToken FreeToken(){
		return new TokenObjKey().new FlowKeyFreeToken();
	}
	
	public class FlowKeyFreeToken implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "freeToken";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "FREETOKEN";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "freeToken";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "是否为自由令牌";
		}

	}
	
	
	
	/**
	 * 父自由令牌编号
	 * @return
	 */
	public static FlowKeyParentFreeTokenId ParentFreeTokenId(){
		return new TokenObjKey().new FlowKeyParentFreeTokenId();
	}
	
	public class FlowKeyParentFreeTokenId implements ObjKeyInterface{


		public String EntityKey() {
			// TODO Auto-generated method stub
			return "parentFreeTokenId";
		}

		public String DataBaseKey() {
			// TODO Auto-generated method stub
			return "PARENT_FREETOKEN_ID";
		}
		
		public String FullKey() {
			// TODO Auto-generated method stub
			return "parentFreeTokenId";
		}

		public String KeyName() {
			// TODO Auto-generated method stub
			return "父自由令牌编号";
		}

	}
	
	/**
	 * 归档时间
	 * @return
	 */
	public static FlowKeyArchiveTime ArchiveTime(){
		return new TokenObjKey().new FlowKeyArchiveTime();
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
