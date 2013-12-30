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
package com.founder.fix.fixflow.editor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.fix.fixflow.editor.impl.PluginServiceImpl;
import com.founder.fix.fixflow.editor.impl.StencilsetServiceImpl;
import com.founder.fix.fixflow.editor.impl.WebModelServiceImpl;
import com.founder.fix.fixflow.editor.service.PluginService;
import com.founder.fix.fixflow.editor.service.StencilsetService;
import com.founder.fix.fixflow.editor.service.WebModelService;

public class FlowEditorServlet extends HttpServlet {

	/**
	 * web设计器的分发servlet
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		if("getPlugin".equals(action)){
			PluginService pluginService = new PluginServiceImpl();
			pluginService.getPluginXml(req, resp);
		}else if("getStencilset".equals(action)){
			StencilsetService stencilsetService = new StencilsetServiceImpl();
			stencilsetService.getStencilsetJson(req, resp);
		}else if("loadBPMNWeb".equals(action)){
			WebModelService webModelService = new WebModelServiceImpl(req, resp);
			webModelService.loadBPMNJson();
		}else if("modelSave".equals(action)){
			WebModelService webModelService = new WebModelServiceImpl(req, resp);
			webModelService.modelSave();
		}else if("reTryModelInfo".equals(action)){
			WebModelService webModelService = new WebModelServiceImpl(req, resp);
			webModelService.reTryModelInfo();
		}
	}
}
