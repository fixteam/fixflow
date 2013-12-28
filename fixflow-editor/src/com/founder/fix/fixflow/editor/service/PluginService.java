package com.founder.fix.fixflow.editor.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PluginService {
	
	public void getPluginXml(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException;
}
