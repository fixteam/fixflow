package com.founder.fix.fixflow.explorer.service;

import java.io.InputStream;
import java.util.Map;


public interface FlowExplorerService {

	/**
	 * 获取流程版本信息
	 * @param fileName
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	 public String getProcessVersionInfo(String fileName,String userId) throws Exception;
	 
	 /**
	  * 发布或更新流程
	  * @param fileInputStream
	  * @param deploymentId
	  * @param userId
	  * @throws Exception
	  */
	 public void deploy(Map<String, InputStream> fileInputStream,String deploymentId,String userId) throws Exception;
    
}
