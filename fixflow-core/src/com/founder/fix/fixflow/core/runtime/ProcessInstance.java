package com.founder.fix.fixflow.core.runtime;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.bpmn.behavior.ProcessDefinitionBehavior;
import com.founder.fix.fixflow.core.impl.db.PersistentObject;


public interface ProcessInstance extends PersistentObject,Serializable {

	
	/**
	 * 判断流程实例是否结束
	 * 
	 * @return
	 */
	boolean hasEnded();

	/**
	 * 获取业务关联键
	 * 
	 * @return
	 */
	String getBizKey();

	/**
	 * 获取流程实例的版本号
	 * 
	 * @return
	 */
	int getVersion();

	/**
	 * 获取任务提交人
	 * 
	 * @return
	 */
	String getInitiator();

	/**
	 * <!-- 开始-用户-文档 --> 
	 * 获取流程定义编号 
	 * <!-- 结束-用户-文档 -->
	 * 
	 * @return
	 */
	String getProcessDefinitionId();

	/**
	 * 获取流程定义Key
	 * @return
	 */
	String getProcessDefinitionKey();

	/**
	 * <!-- 开始-用户-文档 --> 
	 * 获取流程实例开始时间 
	 * <!-- 结束-用户-文档 -->
	 * 
	 * @return
	 */
	Date getStartTime();

	/**
	 * <!-- 开始-用户-文档 --> 
	 * 获取流程实例结束时间 
	 * <!-- 结束-用户-文档 -->
	 * 
	 * @return
	 */
	Date getEndTime();

	/**
	 * <!-- 开始-用户-文档 --> 
	 * 获取流程实例的根令牌编号 
	 * <!-- 结束-用户-文档 -->
	 * 
	 * @return 令牌对象
	 */
	String getRootTokenId();

	/**
	 * <!-- 开始-用户-文档 --> 
	 * 获取流程实例令牌集合中的令牌List 
	 * <!-- 结束-用户-文档 -->
	 * 
	 * @return
	 */
	List<String> getTokenIdList();

	/**
	 * 是否暂停
	 * 
	 * @return
	 */
	boolean isSuspended();
	
	
	/**
	 * 获取父流程实例编号
	 * @return
	 */
	String getParentProcessInstanceId();
	
	/**
	 * 获取父流程实例令牌编号
	 * @return 父流程实例令牌编号
	 */
	String getParentProcessInstanceTokenId();
	
	/**
	 * 获取流程实例相关的持久化数据变量
	 * @return
	 */
	Map<String, Object> getDataVariable();

	 /**
	  * 获取流程实例主题
	  * @return
	  */
	 String getSubject();
	 
	 /**
	  * 获取流程启动人
	  * @return
	  */
	 String getStartAuthor();

	
	 ProcessDefinitionBehavior getProcessDefinition();

}
