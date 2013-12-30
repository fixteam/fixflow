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
