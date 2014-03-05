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


public class JobObjKey {
	
	/**
	 * 编号
	 * @return
	 */
	public static FlowKeyId Id(){
		return new JobObjKey().new FlowKeyId();
	}
	
	public class FlowKeyId implements ObjKeyInterface{


		public String EntityKey() {
			return "jobId";
		}

		public String DataBaseKey() {
			return "JOB_ID";
		}
		
		public String FullKey() {
			return "jobId";
		}

		public String KeyName() {
			return "任务编号";
		}

	}
	
	
	/**
	 * 任务名称
	 * @return
	 */
	public static FlowKeyJobName JobName(){
		return new JobObjKey().new FlowKeyJobName();
	}
	
	public class FlowKeyJobName implements ObjKeyInterface{


		public String EntityKey() {
			return "jobName";
		}

		public String DataBaseKey() {
			return "JOB_NAME";
		}
		
		public String FullKey() {
			return "jobName";
		}

		public String KeyName() {
			return "任务名称";
		}

	}
	
	
	
	/**
	 * 业务类型
	 * @return
	 */
	public static FlowKeyBizType BizType(){
		return new JobObjKey().new FlowKeyBizType();
	}
	
	public class FlowKeyBizType implements ObjKeyInterface{


		public String EntityKey() {
			return "bizType";
		}

		public String DataBaseKey() {
			return "BIZ_TYPE";
		}
		
		public String FullKey() {
			return "bizType";
		}

		public String KeyName() {
			return "业务类型";
		}

	}
	
	
	/**
	 * 业务值
	 * @return
	 */
	public static FlowKeyBizValue BizValue(){
		return new JobObjKey().new FlowKeyBizValue();
	}
	
	public class FlowKeyBizValue implements ObjKeyInterface{


		public String EntityKey() {
			return "bizValue";
		}

		public String DataBaseKey() {
			return "BIZ_VALUE";
		}
		
		public String FullKey() {
			return "bizValue";
		}

		public String KeyName() {
			return "业务值";
		}

	}
	
	
	/**
	 * quarzt类型
	 * @return
	 */
	public static FlowKeyQuarztType QuarztType(){
		return new JobObjKey().new FlowKeyQuarztType();
	}
	
	public class FlowKeyQuarztType implements ObjKeyInterface{


		public String EntityKey() {
			return "quarztType";
		}

		public String DataBaseKey() {
			return "QUARTZ_TYPE";
		}
		
		public String FullKey() {
			return "quarztType";
		}

		public String KeyName() {
			return "quarzt类型";
		}

	}
	
	/**
	 * quarzt时间
	 * @return
	 */
	public static FlowKeyQuarztTime QuarztTime(){
		return new JobObjKey().new FlowKeyQuarztTime();
	}
	
	public class FlowKeyQuarztTime implements ObjKeyInterface{


		public String EntityKey() {
			return "quarztTime";
		}

		public String DataBaseKey() {
			return "QUARTZ_TIME";
		}
		
		public String FullKey() {
			return "quarztTime";
		}

		public String KeyName() {
			return "quarzt时间";
		}

	}
	
	
	/**
	 * quarzt表达式
	 * @return
	 */
	public static FlowKeyQuarztCron QuarztCron(){
		return new JobObjKey().new FlowKeyQuarztCron();
	}
	
	public class FlowKeyQuarztCron implements ObjKeyInterface{


		public String EntityKey() {
			return "quarztCron";
		}

		public String DataBaseKey() {
			return "QUARTZ_CRON";
		}
		
		public String FullKey() {
			return "quarztCron";
		}

		public String KeyName() {
			return "quarzt表达式";
		}

	}
	
	
	/**
	 * 任务状态 0:未注册 1:已注册 2:已经完成 3:执行失败
	 * @return
	 */
	public static FlowKeyJobState JobState(){
		return new JobObjKey().new FlowKeyJobState();
	}
	
	public class FlowKeyJobState implements ObjKeyInterface{


		public String EntityKey() {
			return "jobState";
		}

		public String DataBaseKey() {
			return "JOB_STATE";
		}
		
		public String FullKey() {
			return "jobState";
		}

		public String KeyName() {
			return "任务状态";
		}

	}
	
	/**
	 * 完成后是否删除
	 * @return
	 */
	public static FlowKeyIsDelete IsDelete(){
		return new JobObjKey().new FlowKeyIsDelete();
	}
	
	
	public class FlowKeyIsDelete implements ObjKeyInterface{


		public String EntityKey() {
			return "isDelete";
		}

		public String DataBaseKey() {
			return "IS_DELETE";
		}
		
		public String FullKey() {
			return "isDelete";
		}

		public String KeyName() {
			return "完成后是否删除";
		}

	}
	
	
	/**
	 * 执行器类名
	 * @return
	 */
	public static FlowKeyJobClassName JobClassName(){
		return new JobObjKey().new FlowKeyJobClassName();
	}
	
	
	public class FlowKeyJobClassName implements ObjKeyInterface{


		public String EntityKey() {
			return "jobClassName";
		}

		public String DataBaseKey() {
			return "JOB_CLASS_NAME";
		}
		
		public String FullKey() {
			return "jobClassName";
		}

		public String KeyName() {
			return "执行器类名";
		}

	}

}
