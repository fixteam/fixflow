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
package com.founder.fix.fixflow.core.impl.job;

import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.objkey.JobDetailObjKey;

/**
 * 定时任务明细数据类
 * 
 * @author kenshin
 * 
 */
public class JobDetailEntity extends AbstractPersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8883973998303474204L;
	
	
	public JobDetailEntity(){
		
	}

	protected String id;

	protected String bizKey;

	protected String bizValue;

	protected Object bizObj;

	protected void setId(String id) {
		this.id = id;
	}

	public String getId() {

		return this.id;
	}

	public String getBizKey() {
		return bizKey;
	}

	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}

	public String getBizValue() {
		return bizValue;
	}

	public void setBizValue(String bizValue) {
		this.bizValue = bizValue;
	}

	public Object getBizObj() {
		return bizObj;
	}

	public void setBizObj(Object bizObj) {
		this.bizObj = bizObj;
	}

	public JobDetailEntity(Map<String, Object> entityMap) {
		persistentInit(entityMap);
	}

	public Map<String, Object> getPersistentState() {

		Map<String, Object> objectParam = new HashMap<String, Object>();

		objectParam.put(JobDetailObjKey.Id().FullKey(), this.getId());

		objectParam.put(JobDetailObjKey.BizKey().FullKey(), this.bizKey);

		objectParam.put(JobDetailObjKey.BizValue().FullKey(), this.bizValue);

		objectParam.put(JobDetailObjKey.BizObj().FullKey(), this.bizObj);

		return objectParam;
	}

	@Override
	public void persistentInit(Map<String, Object> entityMap) {
		for (String dataKey : entityMap.keySet()) {

			if (dataKey.equals(JobDetailObjKey.Id().DataBaseKey())) {
				this.id = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(JobDetailObjKey.BizKey().DataBaseKey())) {
				this.bizKey=StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(JobDetailObjKey.BizValue().DataBaseKey())) {
				this.bizValue=StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals(JobDetailObjKey.BizObj().DataBaseKey())) {
				this.bizObj=StringUtil.getString(entityMap.get(dataKey));
				continue;
			}	
		}
	}

	@Override
	public Map<String, Object> getPersistentDbMap() {
		// 构建查询参数
		Map<String, Object> objectParam = new HashMap<String, Object>();


		objectParam.put(JobDetailObjKey.Id().DataBaseKey(), this.getId());
	
		objectParam.put(JobDetailObjKey.BizKey().DataBaseKey(), this.getBizKey());
	
		objectParam.put(JobDetailObjKey.BizValue().DataBaseKey(), this.getBizValue());

		objectParam.put(JobDetailObjKey.BizObj().DataBaseKey(), this.getBizObj());
	
		return objectParam;
	}

}
