package com.founder.fix.fixflow.core;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
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
	
	/**
	 * 获取流程图(SVG格式)
	 */
	String getFlowGraphicsSvg(String processDefinitionId);
	
	
	/**
	 * 获取流程定义的默认表单
	 * @param processDefinitionId 流程唯一编号
	 * @return 流程默认表单
	 */
	String getDefaultFromUri(String processDefinitionId);
	
	/**
	 * 获取流程定义
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
}
  