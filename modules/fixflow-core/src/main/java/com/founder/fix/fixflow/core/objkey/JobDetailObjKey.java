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


public class JobDetailObjKey {
	
	
	/**
	 * 编号
	 * @return
	 */
	public static FlowKeyId Id(){
		return new JobDetailObjKey().new FlowKeyId();
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
	 * 业务键
	 * @return
	 */
	public static FlowKeyBizKey BizKey(){
		return new JobDetailObjKey().new FlowKeyBizKey();
	}
	
	public class FlowKeyBizKey implements ObjKeyInterface{


		public String EntityKey() {
			return "bizKey";
		}

		public String DataBaseKey() {
			return "BIZ_KEY";
		}
		
		public String FullKey() {
			return "bizKey";
		}

		public String KeyName() {
			return "业务键";
		}

	}
	
	
	
	/**
	 * 业务值
	 * @return
	 */
	public static FlowKeyBizValue BizValue(){
		return new JobDetailObjKey().new FlowKeyBizValue();
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
	 * 业务持久对象
	 * @return
	 */
	public static FlowKeyBizObj BizObj(){
		return new JobDetailObjKey().new FlowKeyBizObj();
	}
	
	public class FlowKeyBizObj implements ObjKeyInterface{


		public String EntityKey() {
			return "bizObj";
		}

		public String DataBaseKey() {
			return "BIZ_OBJ";
		}
		
		public String FullKey() {
			return "bizObj";
		}

		public String KeyName() {
			return "业务持久对象";
		}

	}
}
