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
package com.founder.fix.fixflow.core.impl.identity;

import java.util.Map;

import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.internationalization.FixFlowResources;

public class UserTo {

	/**
	 * 用户的编号
	 */
	protected String userId;

	/**
	 * 组的名称
	 */
	protected String userName;

	/**
	 * 扩展属性集合
	 */
	protected Map<String, Object> propertyMap;

	/**
	 * 创建一个用于用户
	 * 
	 * @param userId
	 *            用户的编号
	 * @param userName
	 *            用户的名称
	 */
	public UserTo(String userId, String userName, Map<String, Object> propertyMap) {
		this.userId = userId;
		this.userName = userName;
		this.propertyMap = propertyMap;
	}

	/**
	 * 获取用户编号
	 * 
	 * @return 用户编号
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取用户名称
	 * 
	 * @return
	 */
	public String getUserName() {
		
		
		
		try {
			Boolean booleanTemp = StringUtil.getBoolean(ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getInternationalizationConfig().getIsEnable());

			//用户名称国际化处理
			if (booleanTemp) {

				FixFlowResources fixFlowResources = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getFixFlowResources();

				String nameTemp = fixFlowResources.getResourceName(FixFlowResources.OrganizationResource, "FixFlow_user_Name_Key");
				if (nameTemp == null || nameTemp.equals("")) {
					return userName;
				} else {
					Object otherName = this.getPropertyValue(nameTemp);
					if (otherName == null || otherName.equals("")) {
						return userName;
					} else {
						return StringUtil.getString(otherName);
					}
				}

			} else {
				return userName;
			}
		} catch (Exception e) {
			return userName;
		}
		

	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<String, Object> getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(Map<String, Object> propertyMap) {
		this.propertyMap = propertyMap;
	}

	/**
	 * 获取用户信息的扩展字段
	 * 
	 * @param propertyName
	 *            字段名称(区分大小写)
	 * @return 扩展字段值
	 */
	public Object getPropertyValue(String propertyName) {
		if (this.propertyMap == null || propertyName == null || propertyName.equals("")) {
			return null;
		}
		Object propertyValue = this.propertyMap.get(propertyName);
		return propertyValue;
	}

}
