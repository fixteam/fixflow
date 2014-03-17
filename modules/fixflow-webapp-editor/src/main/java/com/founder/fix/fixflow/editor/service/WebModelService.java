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
package com.founder.fix.fixflow.editor.service;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * 处理流程模型
 * @author Administrator
 *
 */
public interface WebModelService {

	/**
	 * 加载模型JSON
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loadBPMNJson();
	
	/**
	 * 保存模型
	 * @throws ServletException
	 * @throws IOException
	 */
	public void modelSave();
	
	/**
	 * 二次请求模型信息，用户前台保存前组合JSON
	 * @throws ServletException
	 * @throws IOException
	 */
	public void reTryModelInfo();
	
}
