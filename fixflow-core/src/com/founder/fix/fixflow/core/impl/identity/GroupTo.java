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

/**
 * 组,用于组的数据传递
 * @author kenshin
 *
 */
public class GroupTo {
	
	/**
	 * 组的编号
	 */
	protected String groupId;
	
	/**
	 * 组的名称
	 */
	protected String groupName;
	
	

	

	/**
	 * 扩展属性集合
	 */
	protected Map<String, Object> propertyMap;



	/**
	 * 组的分类
	 */
	protected String groupType;
	
	
	/**
	 * 创建一个用于组
	 * @param groupId 组的编号
	 * @param groupType 组的类型编号
	 */
	public GroupTo(String groupId,String groupType)
	{
		this.groupId=groupId;
		this.groupType=groupType;
	}
	
	/**
	 * 创建一个用于组
	 * @param groupId 组的编号
	 * @param groupName 组的名称
	 * @param groupType 组的类型编号
	 */
	public GroupTo(String groupId,String groupName,String groupType,Map<String, Object> propertyMap)
	{
		this.groupId=groupId;
		this.groupType=groupType;
		this.groupName=groupName;
		this.propertyMap=propertyMap;

	}
	
	
	
	
	/**
	 * 获取组编号
	 * @return 组编号
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 获取组类型编号
	 * @return 组类型编号
	 */
	public String getGroupType() {
		return groupType;
	}
	
	/**
	 * 获取组的名称
	 * @return
	 */
	public String getGroupName() {
		
		try {
			boolean booleanTemp = StringUtil.getBoolean(ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getInternationalizationConfig().getIsEnable());

			//用户名称国际化处理
			if (booleanTemp) {

				FixFlowResources fixFlowResources = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getFixFlowResources();

				String nameTemp = fixFlowResources.getResourceName(FixFlowResources.OrganizationResource, "FixFlow_"+groupType+"_Name_Key");
				if (nameTemp == null || nameTemp.equals("")) {
					return groupName;
				} else {
					Object otherName = this.getPropertyValue(nameTemp);
					if (otherName == null || otherName.equals("")) {
						return groupName;
					} else {
						return StringUtil.getString(otherName);
					}
				}

			} else {
				return groupName;
			}
		} catch (Exception e) {
			return groupName;
		}
		
		
		
	
	}


	
	public int getLevelNum() {
		
		
		return StringUtil.getInt(getPropertyValue("LEVELNUM"));
	}


	public Map<String, Object> getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(Map<String, Object> propertyMap) {
		this.propertyMap = propertyMap;
	}
	
	/**
	 * 获取组信息的扩展字段
	 * @param propertyName 字段名称(区分大小写)
	 * @return 扩展字段值
	 */
	public Object getPropertyValue(String propertyName){
		if(this.propertyMap==null||propertyName==null||propertyName.equals("")){
			return null;
		}
		Object propertyValue=this.propertyMap.get(propertyName);
		return propertyValue;
	}


	
}
