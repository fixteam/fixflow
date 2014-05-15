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
 * @author yangchenhui
 */
package com.founder.fix.fixflow.test.engine;


import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.ProcessEngineManagement;


import junit.framework.TestCase;

public class ProcessEngineTest extends TestCase {

	/**
	 * 根据默认配置文件创建一个processEngine 
	 * 当是第一次获取的时候会创建一个processEngine
	 * 第二次的时候直接拿到创建好的processEngine
	 */
	public void testCreateProcessEngine() {
		ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();
		assertNotNull(processEngine);
	}

	/**
	 * 给获取来的processEngine设置一个外部内容构造器 
	 * 外部内容构造器中可以设置一些外部传递给流程引擎的内容
	 */
	public void testCreateExternalContent() {
//		// 获取一个processEngine
//		ProcessEngine processEngine = ProcessEngineManagement.getDefaultProcessEngine();
//		// 创建外部内容构造器
//		ExternalContent externalContent = new ExternalContent();
//		Connection connection = null;
//		try {
//			connection = createConnection();
//			// 当流程引擎需要从外部传入数据库链接的时候需要在外部内容构造器中放入connection
//			externalContent.setConnection(connection);
//			// 设置当前的操作人
//			externalContent.setAuthenticatedUserId("admin");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				// 当操作完成之后需要在外部关闭数据库链接!流程引擎本身不负责关闭
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

//		assertNotNull(processEngine);
//		assertNotNull(externalContent.getAuthenticatedUserId());
//		assertNotNull(externalContent.getConnection());
	}


}
