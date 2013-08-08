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

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FlowCenterService {
	public static final String LOGIN_USER_ID = "LOGIN_USER_ID";

	/**
	  * queryMyTaskNotEnd
	
	  * @Title: queryMyTaskNotEnd
	  * @Description: 获取某人的所有的未完结的任务
	  * @param @param map
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
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return Map<String,Object>    返回类型
	  * "dataList" 数据列表<br>
	  * "pageNumber" 总行数<br>
	  * "agentUsers" 代理用户<br>
	  * "agentToUsers" 委托用户<br>
	  * @throws
	  */
	public Map<String,Object> queryMyTaskNotEnd(Map<String,Object> map)
		throws SQLException;

	/**
	  * queryMyTaskNotEnd
	
	  * @Title: queryMyTaskNotEnd
	  * @Description: 获取某人的所有的未完结的任务
	  * @param @param map
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
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return Map<String,Object>    返回类型
	  * "dataList" 数据列表<br>
	  * "pageNumber" 总行数<br>
	  * "agentUsers" 代理用户<br>
	  * "agentToUsers" 委托用户<br>
	  * @throws
	  */
	public Map<String,Object> queryMyTaskEnded(Map<String,Object> map)
		throws SQLException;

	/**
	  * queryStartProcess
	
	  * @Title: queryStartProcess
	  * @Description: 获取某人所有可发起的任务
	  * @param @param s
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
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return List<Map<String,String>>    返回类型
	  * "dataList" 数据列表<br>
	  * "pageNumber" 总行数<br>
	  * @throws
	  */
	public List<Map<String, String>> queryStartProcess(String s)
		throws SQLException;

	public InputStream queryStartProcessImage(String s)
		throws SQLException;

	/**
	  * queryTaskParticipants
	
	  * @Title: queryTaskParticipants
	  * @Description: 获取所有某人参与的任务
	  * @param @param map
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
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return Map<String,Object>    返回类型
	  * "dataList" 数据列表<br>
	  * "pageNumber" 总行数<br>
	  * @throws
	  */
	public Map<String,Object> queryTaskParticipants(Map<String,Object> map)
		throws SQLException;

	/**
	  * queryTaskInitiator
	
	  * @Title: queryTaskInitiator
	  * @Description: 获取所有某人发起的任务
	  * @param @param map
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
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return Map<String,Object>    返回类型
	  * "dataList" 数据列表<br>
	  * "pageNumber" 总行数<br>
	  * @throws
	  */
	public Map<String,Object> queryTaskInitiator(Map<String,Object> map)
		throws SQLException;
	
	/**
	  * getTaskDetailInfo
	
	  * @Title: getTaskDetailInfo
	  * @Description: 获取某流程的信息
	  * @param @param filter
	  * "userId" 用户编号<br>
	  * "processInstanceId" 流程实例编号<br>
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return Map<String,Object>    返回类型
	  * "dataList" 数据列表<br>
	  * @throws
	  */
	public Map<String,Object> getTaskDetailInfo(Map<String,Object> filter) throws SQLException;

	/**
	  * getFlowGraph
	
	  * @Title: getFlowGraph
	  * @Description: 获取某流程的流程图信息
	  * @param @param filter
	  * "userId" 用户编号<br>
	  * "processDefinitionId" 流程定义编号,与流程唯一标识传任意一个(可选)<br>
	  * "processDefinitionKey" 流程定义唯一标识(可选)<br>
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return InputStream    返回类型
	  * @throws
	  */
	public InputStream getFlowGraph(Map<String,Object> filter) throws SQLException;
}
