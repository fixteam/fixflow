package com.founder.fix.fixflow.editor.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.founder.fix.fixflow.editor.service.PluginService;

public class PluginServiceImpl implements PluginService {

	public void getPluginXml(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		InputStream pluginStream = this.getClass().getClassLoader().getResourceAsStream("plugins.xml");
		Document document = null;  
		resp.setContentType("text/xml;charset=utf-8");   
		PrintWriter out = resp.getWriter();
		SAXReader reader = new SAXReader(); 
		try {
			document = reader.read(pluginStream);
			out.print(document.asXML());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
