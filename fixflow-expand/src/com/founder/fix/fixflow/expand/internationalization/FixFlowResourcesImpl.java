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
package com.founder.fix.fixflow.expand.internationalization;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.internationalization.FixFlowResources;


public class FixFlowResourcesImpl implements FixFlowResources {

	public String getResourceName(String processId, String resourceKey) {
		
		//FixResourceCore.setNowLanguage(Context.getLanguageType());
		
		String resourceName=FixResourceCore.getResource(processId, resourceKey);
		
		return resourceName;
	}

	public String getResourceName(String processId, String resourceKey,
			String languageType) {
		
		//FixResourceCore.setNowLanguage(languageType);
		
		String resourceName=FixResourceCore.getResource(processId, resourceKey);
		
		return resourceName;
	}

	public void systemInit(Connection connection) {
		
		
		
		
		SqlCommand sqlCommand =new SqlCommand(connection);
		
		List<Map<String, Object>> ListMap =sqlCommand.queryForList("SELECT PROCESS_KEY,PROCESS_ID,VERSION FROM FIXFLOW_DEF_PROCESSDEFINITION");
		
		for (Map<String, Object> map : ListMap) {
			
			String processKey=map.get("PROCESS_KEY").toString();
			String processId=map.get("PROCESS_ID").toString();
			String version=map.get("VERSION").toString();
			String guid=processId.substring(processId.lastIndexOf(":")+1, processId.length());
			try {
				FixResourceCore.loadAllLanguage(processKey+"/" + processKey + "_" + version + "_" + guid + ".properties",processId);
				
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				throw new FixFlowException("国际化资源文件加载错误!", e);
			}
			//
			

		}
		try {
			//加载系统处理命令国际化文件
			FixResourceCore.loadAllLanguage("FixFlowConfigResource/SystemTaskComandResource.properties",FixFlowResources.TaskComandResource);
			//加载异常国际化文件
			FixResourceCore.loadAllLanguage("FixFlowConfigResource/FixFlowExceptionResource.properties", FixFlowResources.ExceptionResource);
			//加载组织结构国际化文件
			FixResourceCore.loadAllLanguage("FixFlowConfigResource/FixFlow_OrganizationResource.properties", FixFlowResources.OrganizationResource);
			
			
			//加载系统资源文件
			FixResourceCore.loadAllLanguage("FixFlowConfigResource/FixFlow_SystemResource.properties", FixFlowResources.SystemResource);
			
			//加载系统资源文件
			FixResourceCore.loadAllLanguage("FixFlowConfigResource/FixFlow_FlowNameResource.properties", FixFlowResources.FlowNameResource);

			
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new FixFlowException("国际化资源文件加载错误!", e);
		}
		//loadAllLanguage

		
		
	}

	public String getNowLanguage() {
		return FixResourceCore.getNowLanguage();
	}

	public void setNowLanguage(String languageType) {
		// TODO Auto-generated method stub
		FixResourceCore.setNowLanguage(languageType);
	}




}
