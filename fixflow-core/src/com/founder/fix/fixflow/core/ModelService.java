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
package com.founder.fix.fixflow.core;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;


import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.persistence.definition.DeploymentEntity;
import com.founder.fix.fixflow.core.impl.persistence.definition.ResourceEntity;
import com.founder.fix.fixflow.core.model.DeploymentBuilder;
import com.founder.fix.fixflow.core.model.DeploymentQuery;
import com.founder.fix.fixflow.core.model.ProcessDefinitionQuery;

public interface ModelService extends ProcessService{
	  
	/** 开始创建一个新的定义部署 */
	DeploymentBuilder createDeployment();
	  

	/**
	 * 删除定义部署
	 * @param deploymentId 定义部署的编号, 不能为空.
	 */
	void deleteDeployment(String deploymentId);


	/**
	 * 删除指定的定义部署,并级联删除流程实例，
	 * 历史实例实例和定时任务。
	 * @param deploymentId 定义部署的编号, 不能为空.
	 * @param cascade 级联删除
	 */
	void deleteDeployment(String deploymentId, boolean cascade);

	/**
	 * 检索一个指定的定义部署清单资源，
     * 按字母顺序排列。
	 * @param deploymentId 定义部署的编号, 不能为空.
	 */
	List<String> getDeploymentResourceNames(String deploymentId);
	  
	/**
	 * 通过字节流访问定义部署资源。
	 * @param deploymentId 定义部署的编号, 不能为空.
	 * @param resourceName 资源名称, 不能为空.
	 */
	InputStream getResourceAsStream(String deploymentId, String resourceName);
	
	
	/**
	 * 通过字节流访问定义部署资源。
	 * @param resourceId 资源编号, 不能为空.
	 */
	ResourceEntity getResourceAsStream(String resourceId);
	
	 /**
	   * 更新资源
	   * @param resourceId 资源编号
	   * @param inputStream 流
	   * @return
	   */
	void updateResource(String resourceId,InputStream inputStream);
	

	/** 
	 * 查询流程定义  {@link ProcessDefinition}
	 */
	ProcessDefinitionQuery createProcessDefinitionQuery();
	  
	/** 
	 * 查询定义部署 {@link DeploymentQuery}
	 */
	DeploymentQuery createDeploymentQuery();
	
	@Deprecated
	/**
	 * 获取流程图(SVG格式)
	 */
	String getFlowGraphicsSvg(String processDefinitionId);
	
	@Deprecated
	/**
	 * 获取流程图(SVG格式)
	 */
	String getFlowGraphicsSvgByDefKey(String processDefinitionKey);
	
	
	/**
	 * 获取流程定义的默认表单
	 * @param processDefinitionId 流程唯一编号
	 * @return 流程默认表单
	 */
	String getDefaultFromUri(String processDefinitionId);
	
	/**
	 * 获取流程定义(内置缓存)
	 * @param processDefinitionId 流程唯一编号
	 * @return 获取流程定义
	 */
	ProcessDefinitionBehavior getProcessDefinition(String processDefinitionId);
	
	/**
	 * 获取根据ProcessKey分组过后的流程信息
	 * List为行,Map 的 Key:
	 * PROCESS_KEY 流程编号,PROCESS_NAME 流程名称,CATEGORY 流程分类
	 * @return
	 */
	List<Map<String, Object>> selectProcessDefinitionGroupKey();
	
	@Deprecated
	/**
	 * 获取流程图图片地址
	 * @param processDefinitionId 流程唯一编号
	 * @return 图片地址
	 */
	String GetFlowGraphicsImgPath(String processDefinitionId);
	
	/**
	 * 获取流程图节点信息
	 * @param processDefinitionId  流程唯一编号
	 * @return key为节点编号,value为 Map<String,Object> Key(height,width,x,y) (height="36.0" width="36.0" x="100.0" y="100.0")
	 */
	Map<String, Map<String, Object>> GetFlowGraphicsElementPosition(String processDefinitionId);
	
	
	Map<String, Map<String, Object>> GetFlowGraphicsElementPositionByKey(String processDefinitionKey);
	/**
	 * 获取资源国际化信息
	 * @param resourcesType 资源的类型 (FixFlowResources.FlowNameResource)
	 * @param resourceKey 资源编号
	 * @return
	 */
	String getModelInternationalizationResources(String resourcesType,String resourceKey);
	
	
	/**
	 * 获取流程名称资源国际化信息
	 * @param resourceKey 流程编号
	 * @return
	 */
	String getFlowNameInternationalizationResources(String resourceKey);
	
	
	

	/**
	 * 获取用户可以发起的流程集合
	 * @param userId 用户编号
	 * @return
	 * "processDefinitionId" 流程唯一号;<br>
	 * "processDefinitionName" 流程名称;<br>
	 * "processDefinitionKey" 流程定义号;<br>
	 * "category" 分类;<br>
	 * "version" 版本号;<br>
	 * "resourceName", 流程定义资源名称;<br>
	 * "resourceId" 流程定义资源编号;<br>
	 * "deploymentId" 资源定义发布号;<br>
	 * "diagramResourceName" 流程图名称;<br>
	 * "startFormKey" 启动表单;<br>
	 */
	List<Map<String, String>> getStartProcessByUserId(String userId);
	
	/**
	 * 获取用户最新提交的流程记录
	 * @param userId 用户编号
	 * @param number 最近使用的流程记录数
	 * @return
	 * "processDefinitionId" 流程唯一号;<br>
	 * "processDefinitionName" 流程名称;<br>
	 * "processDefinitionKey" 流程定义号;<br>
	 * "category" 分类;<br>
	 * "version" 版本号;<br>
	 * "resourceName", 流程定义资源名称;<br>
	 * "resourceId" 流程定义资源编号;<br>
	 * "deploymentId" 资源定义发布号;<br>
	 * "diagramResourceName" 流程图名称;<br>
	 * "startFormKey" 启动表单;<br>
	 */
	List<Map<String, String>> getUserSubmitProcess(String userId,int number);
	
	
	
	/**
	 * 获取流程图图片Stream
	 * @param processDefinitionId 流程唯一编号
	 * @return 图片Stream
	 */
	InputStream GetFlowGraphicsImgStreamByDefId(String processDefinitionId);
	
	/**
	 * 获取流程图图片Stream
	 * @param processDefinitionKey 流程编号
	 * @return 图片Stream
	 */
	InputStream GetFlowGraphicsImgStreamByDefKey(String processDefinitionKey);
	
	/**
	 * 通过zip文件发布流程
	 * @param path zip文件路径
	 * @return 发布号
	 */
	String deploymentByZip(String path);
	
	/**
	 * 通过zip文件发布流程
	 * @param path zip文件路径
	 * @return 发布号
	 */
	String deploymentByZip(ZipInputStream zipInputStream);
	
	/**
	 * 通过流发布流程定义，必须要bpmn流和png的流，其他inputStream会发布到FIXFLOW_DEF_BYTEARRAY表中 
	 * @param inputStreamMap key:filename value inputsteam
	 * @return
	 */
	String deploymentByStream(Map<String, InputStream> inputStreamMap);
	
	/**
	 * 通过通过流更新流程定义，必须要bpmn流和png的流，其他inputStream会发布到FIXFLOW_DEF_BYTEARRAY表中 
	 * @param fileInputStreamMap inputStreamMap key:filename value inputsteam
	 * @param deploymentId 发布号
	 * @return
	 */
	public String updateDeploymentByStream(Map<String, InputStream> fileInputStreamMap,String deploymentId);
	
	/**
	 * 通过zip文件发布流程
	 * @param path zip文件路径
	 * @param deploymentId 发布号
	 * @return 发布号
	 */
	String updateDeploymentByZip(ZipInputStream zipInputStream,String deploymentId);
	
	/**
	 * 通过zip文件发布流程
	 * @param path zip文件路径
	 * @param deploymentId 发布号
	 * @return 发布号
	 */
	String updateDeploymentByZip(String pth,String deploymentId);
	
	/**
	 * 获取发布实体
	 * @param deploymentId 发布号
	 * @return 发布号
	 */
	DeploymentEntity getDeploymentEntity(String deploymentId);
	
	
	
}
  