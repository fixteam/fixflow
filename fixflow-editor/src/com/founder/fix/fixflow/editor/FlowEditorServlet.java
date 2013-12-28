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
	 * 
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
