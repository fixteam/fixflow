package com.founder.fix.fixflow.expand.internationalization;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.internationalization.FixFlowResources;
import com.founder.fix.fl.core.FixResourceCore;

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

	public void systemInit() {
		
		
		
		
		SqlCommand sqlCommand =new SqlCommand(Context.getDbConnection());
		
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




}
