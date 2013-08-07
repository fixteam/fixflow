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
	  * "userId" 用户编号;<br>
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return Map<String,Object>    返回类型
	  * @throws
	  */
	public Map<String,Object> queryMyTaskNotEnd(Map<String,String> map)
		throws SQLException;

	/**
	  * queryMyTaskEnded(这里用一句话描述这个方法的作用)
	
	  * @Title: queryMyTaskEnded
	  * @Description: 获取某人的所有已完结的任务
	  * @param @param map
	  * "userId" 用户编号;<br>
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return Map<String,Object>    返回类型
	  * @throws
	  */
	public Map<String,Object> queryMyTaskEnded(Map<String,String> map)
		throws SQLException;

	/**
	  * queryStartProcess
	
	  * @Title: queryStartProcess
	  * @Description: 获取某人所有可发起的任务
	  * @param @param s
	  * "userId" 用户编号;<br>
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return List<Map<String,String>>    返回类型
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
	  * "userId" 用户编号;<br>
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return Map<String,Object>    返回类型
	  * @throws
	  */
	public Map<String,Object> queryTaskParticipants(Map<String,String> map)
		throws SQLException;

	/**
	  * queryTaskInitiator
	
	  * @Title: queryTaskInitiator
	  * @Description: 获取所有某人发起的任务
	  * @param @param map
	  * "userId" 用户编号;<br>
	  * @param @return
	  * @param @throws SQLException    设定文件
	  * @return Map<String,Object>    返回类型
	  * @throws
	  */
	public Map<String,Object> queryTaskInitiator(Map<String,String> map)
		throws SQLException;
}
