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
package com.founder.fix.fixflow;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.shell.DBConnFactory;
import com.founder.fix.fixflow.util.MD5;
import com.founder.fix.fixflow.util.SpringConfigLoadHelper;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static boolean isCreated = false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void init(){
		if(!SpringConfigLoadHelper.isCreated()){ 
			WebApplicationContext wac = (WebApplicationContext) this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
			SpringConfigLoadHelper.buildApplicationContext(wac);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnFactory dbcf = (DBConnFactory)SpringConfigLoadHelper.getBean("DB_FIX_BIZ_BASE");
		try {
			String context = request.getContextPath();
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			List<Object> list= new ArrayList<Object>();
			list.add(userName);
			password = MD5.getMD5(password.getBytes());
			list.add(password);
			
			StringBuffer sb = new StringBuffer();
			sb.append("select USERID,USERNAME from AU_USERINFO where LOGINID=? and PASSWORD=?");
			Connection connection = dbcf.createConnection();
			SqlCommand sqlcommand = new SqlCommand(connection);
			List<Map<String, Object>> list2 = sqlcommand.queryForList(sb.toString(),list);
			if(list2!=null && list2.size()>0){
				request.getSession().setAttribute(FlowCenterService.LOGIN_USER_ID, list2.get(0).get("USERID"));
				request.getSession().setAttribute(FlowCenterService.LOGIN_USER_NAME, list2.get(0).get("USERNAME"));
				
				String loginType = request.getParameter("loginType");
				if(loginType !=null && loginType.equals("on")){
					request.setAttribute("action", "processDefinitionList");
					response.sendRedirect(context+"/FlowManager?action=processDefinitionList");
				}else{
					request.setAttribute("action", "getMyTask");
					response.sendRedirect(context+"/FlowCenter?action=getMyTask");
				}
				
			}else{
				response.sendRedirect(context+"/fixflow/login.jsp");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
