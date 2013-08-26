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
 * @author shao
 */
package com.founder.fix.fixflow.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.runtime.ProcessInstance;

/**
  * @ClassName: FlowCenterService
  * @Description: 流程任务中心处理类
  * @author shao
  *
  */
public interface FlowCenterService {
	public static final String LOGIN_USER_ID = "LOGIN_USER_ID";
	
	public static final String LOGIN_USER_NAME = "LOGIN_USER_NAME";
	
	public void setConnection(Connection connection);
	
	public Connection getConnection();
	

	/**
	  * queryMyTaskNotEnd
	  * @Title: queryMyTaskNotEnd
	  * @Description: 获取某人的所有的未完结的任务
	  * @param map<br>
	  * "userId" 用户编号<br>
	  * "pdkey" 流程编号(可选)<br>
	  * "pageIndex" 第几页(可选)<br>
	  * "rowNum" 有几行(可选)<br>
	  * "agentUserId" 有几行(可选)<br>
	  * "agentType" 0我代理被人，1别人委托给我(可选)<br>
	  * "title" 查询主题(可选)<br>
	  * "processVeriy" 查询变量(可选)<br>
	  * "arrivalTimeS" 到达时间开始(可选)<br>
	  * "arrivalTimeE" 到达时间结束(可选)<br>
	  * "initor" 发起人(可选)<br>
	  * @return Map<String,Object><br>
	  * "dataList" 数据列表<br>
	  * "pageNumber" 总行数<br>
	  * "agentUsers" 代理用户<br>
	  * "agentToUsers" 委托用户<br>
	  * @throws SQLException
	  */
	public Map<String,Object> queryMyTaskNotEnd(Map<String,Object> map)
		throws SQLException;

	/**
	  * queryMyTaskNotEnd
	  * @Title: queryMyTaskNotEnd
	  * @Description: 获取某人的所有的未完结的任务
	  * @param map<br>
	  * "userId" 用户编号<br>
	  * "pdkey" 流程编号(可选)<br>
	  * "pageIndex" 第几页(可选)<br>
	  * "rowNum" 有几行(可选)<br>
	  * "agentUserId" 代理人或委托人id(可选)<br>
	  * "agentType" 0我代理被人，1别人委托给我(可选)<br>
	  * "title" 查询主题(可选)<br>
	  * "processVeriy" 查询变量(可选)<br>
	  * "arrivalTimeS" 到达时间开始(可选)<br>
	  * "arrivalTimeE" 到达时间结束(可选)<br>
	  * "initor" 发起人(可选)<br>
	  * @return Map<String,Object><br>
	  * "dataList" 数据列表<br>
	  * "pageNumber" 总行数<br>
	  * "agentUsers" 代理用户<br>
	  * "agentToUsers" 委托用户<br>
	  * @throws SQLException
	  */
	public Map<String,Object> queryMyTaskEnded(Map<String,Object> map)
		throws SQLException;

	/**
	  * queryStartProcess
	  * @Title: queryStartProcess
	  * @Description: 获取某人所有可发起的任务
	  * @param s<br>
	  * "userId" 用户编号<br>
	  * "pdkey" 流程编号(可选)<br>
	  * "pageIndex" 第几页(可选)<br>
	  * "rowNum" 有几行(可选)<br>
	  * "agentUserId" 代理人或委托人id(可选)<br>
	  * "agentType" 0我代理被人，1别人委托给我(可选)<br>
	  * "title" 查询主题(可选)<br>
	  * "processVeriy" 查询变量(可选)<br>
	  * "arrivalTimeS" 到达时间开始(可选)<br>
	  * "arrivalTimeE" 到达时间结束(可选)<br>
	  * "initor" 发起人(可选)<br>
	  * @return List<Map<String,String>><br>
	  * "dataList" 数据列表<br>
	  * "pageNumber" 总行数<br>
	  * @throws SQLException
	  */
	public List<Map<String, String>> queryStartProcess(String s)
		throws SQLException;


	/**
	  * queryStartProcessImage
	  * @Title: queryStartProcessImage
	  * @Description: TODO
	  * @param
	  * @return InputStream
	  * @throws
	  */
	public InputStream queryStartProcessImage(String s)
		throws SQLException;

	/**
	  * queryTaskParticipants
	  * @Title: queryTaskParticipants
	  * @Description: 获取所有某人参与的任务
	  * @param map<br>
	  * "userId" 用户编号<br>
	  * "pdkey" 流程编号(可选)<br>
	  * "pageIndex" 第几页(可选)<br>
	  * "rowNum" 有几行(可选)<br>
	  * "agentUserId" 代理人或委托人id(可选)<br>
	  * "agentType" 0我代理被人，1别人委托给我(可选)<br>
	  * "title" 查询主题(可选)<br>
	  * "processVeriy" 查询变量(可选)<br>
	  * "arrivalTimeS" 到达时间开始(可选)<br>
	  * "arrivalTimeE" 到达时间结束(可选)<br>
	  * "initor" 发起人(可选)<br>
	  * @return Map<String,Object><br>
	  * "dataList" 数据列表<br>
	  * "pageNumber" 总行数<br>
	  * @throws SQLException
	  */
	public Map<String,Object> queryTaskParticipants(Map<String,Object> map)
		throws SQLException;

	/**
	  * queryTaskInitiator
	  * @Title: queryTaskInitiator
	  * @Description: 获取所有某人发起的任务
	  * @param map<br>
	  * "userId" 用户编号<br>
	  * "pdkey" 流程编号(可选)<br>
	  * "pageIndex" 第几页(可选)<br>
	  * "rowNum" 有几行(可选)<br>
	  * "agentUserId" 代理人或委托人id(可选)<br>
	  * "agentType" 0我代理被人，1别人委托给我(可选)<br>
	  * "title" 查询主题(可选)<br>
	  * "processVeriy" 查询变量(可选)<br>
	  * "arrivalTimeS" 到达时间开始(可选)<br>
	  * "arrivalTimeE" 到达时间结束(可选)<br>
	  * "initor" 发起人(可选)<br>
	  * @return Map<String,Object><br>
	  * "dataList" 数据列表<br>
	  * "pageNumber" 总行数<br>
	  * @throws SQLException
	  */
	public Map<String,Object> queryTaskInitiator(Map<String,Object> map)
		throws SQLException;
	
	/**
	  * queryTaskInitiator
	  * @Title: queryTaskInitiator
	  * @Description: 获取所有某人发起的任务
	  * @param map<br>
	  * "userId" 用户编号<br>
	  * "pdkey" 流程编号(可选)<br>
	  * "pageIndex" 第几页(可选)<br>
	  * "rowNum" 有几行(可选)<br>
	  * "agentUserId" 代理人或委托人id(可选)<br>
	  * "agentType" 0我代理被人，1别人委托给我(可选)<br>
	  * "title" 查询主题(可选)<br>
	  * "processVeriy" 查询变量(可选)<br>
	  * "arrivalTimeS" 到达时间开始(可选)<br>
	  * "arrivalTimeE" 到达时间结束(可选)<br>
	  * "initor" 发起人(可选)<br>
	  * @return Map<String,Object><br>
	  * "dataList" 数据列表<br>
	  * "pageNumber" 总行数<br>
	  * @throws SQLException
	  */
	public Map<String,Object> queryPlaceOnFile(Map<String,Object> map)
		throws SQLException;
	
	/**
	  * getTaskDetailInfo
	  * @Title: getTaskDetailInfo
	  * @Description: 获取某流程的信息
	  * @param filter<br>
	  * "userId" 用户编号<br>
	  * "processInstanceId" 流程实例编号<br>
	  * @return Map<String,Object>
	  * "dataList" 数据列表<br>
	  * @throws SQLException
	  */
	public Map<String,Object> getTaskDetailInfo(Map<String,Object> filter) throws SQLException;

	/**
	  * getFlowGraph
	  * @Title: getFlowGraph
	  * @Description: 获取某流程的流程图信息
	  * @param filter
	  * "userId" 用户编号<br>
	  * "processDefinitionId" 流程定义编号,与流程唯一标识任选一个(可选)<br>
	  * "processDefinitionKey" 流程定义唯一标识,与流程定义编号任选一个(可选)<br>
	  * @return InputStream
	  * @throws SQLException
	  */
	public InputStream getFlowGraph(Map<String,Object> filter) throws SQLException;
	
	/**
	  * getUserInfo
	
	  * @Title: getUserInfo
	  * @Description: 获取某用户的信息
	  * @param map<br>
	  * "userId" 用户编号<br>
	  * "path" 头像文件相对路径<br>
	  * @return
	  * "user" 用户信息<br>
	  * "icon" 头像链接<br>
	  * @throws SQLException
	  * @throws IOException
	  */
	public Map<String,Object> getUserInfo(Map<String,Object> map) throws SQLException, IOException;
	
	/**
	  * saveUserIcon
	
	  * @Title: saveUserIcon
	  * @Description: 保存用户头像
	  * @param filter
	  * "userId" 用户编号<br>
	  * "icon" 用户头像流<br>
	  * @throws IOException
	  */
	public void saveUserIcon(Map<String,Object> filter) throws IOException;
	

	/**
	  * GetFlowRefInfo
	
	  * @Title: GetFlowRefInfo
	  * @Description: 获取流程相关信息
	  * @param filter<br>
	  * "userId" 用户编号<br>
	  * "taskId" 任务编号，当任务在非提交状态时，应该传这个参数(可选)<br>
	  * "processDefinitionKey" 任务定义编号，当任务处于提交状态时应使用这个参数(可选)<br>
	  * @return Map<String,Object><br>
	  * "commandList" 流程命令按钮<br>
	  * "processInstance" 流程实例对象<br>
	  * @throws SQLException
	  */
	public Map<String,Object> GetFlowRefInfo(Map<String,Object> filter) throws SQLException;
	
	/**
	  * completeTask
	
	  * @Title: completeTask
	  * @Description: 完成任务，任何步骤都可以调用这个api
	  * @param params<br>
	  * "userId" 用户编号<br>
	  * "commandType" 命令类型<br>
	  * "commandId" 命令编号<br>
	  * "taskId" 任务编号，当任务在非提交状态时，应该传这个参数(可选)<br>
	  * "processDefinitionKey" 任务定义编号，当任务处于提交状态时应使用这个参数(可选)<br>
	  * "businessKey" 业务数据唯一主键，当任务处于提交状态时应使用这个参数(可选)<br>
	  * @return ProcessInstance
	  * @throws SQLException
	  */
	public ProcessInstance completeTask(Map<String,Object> params) throws SQLException;
	
	public void cutUserIcon(Map<String,Object> params) throws IOException;
}
