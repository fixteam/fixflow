/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author ych
 */
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
