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
import com.founder.fix.fixflow.core.impl.util.StringUtil;
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
		Connection connection = null;
		
		try {
			String context = request.getContextPath();
			//从登录的口获取到用户名和密码
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			List<Object> list= new ArrayList<Object>();
			//该接口同时也是登出的口，当发现有特殊参数时则做登出操作。
			String logout = request.getParameter("doLogOut");
			if(StringUtil.isNotEmpty(logout)){
				request.getSession().invalidate();
				response.sendRedirect(context+"/fixflow/login.jsp");
				return;
			}
			//这里进行用户名密码验证，可以任意修改这里的代码。
			list.add(userName);
			password = MD5.getMD5(password.getBytes());
			list.add(password);
			
			boolean skip = false;
			if(userName.equals("fixadmin")){
				skip=true;
			}
			StringBuffer sb = new StringBuffer();
			sb.append("select USERID,USERNAME,ISADMIN from AU_USERINFO where LOGINID=? and PASSWORD=?");
			connection = dbcf.createConnection();
			//这里是自带的数据库操作方式。
			SqlCommand sqlcommand = new SqlCommand(connection);
			List<Map<String, Object>> list2 = sqlcommand.queryForList(sb.toString(),list);
			if((list2!=null && list2.size()>0) || skip){
				//这里约定了一个参数，流程引擎在运行时会默认从session里按照这两个key来获取参数，如果替换了登录的方式，请保证这两个key依然可以获取到正确的数据
				if(skip){
					request.getSession().setAttribute(FlowCenterService.LOGIN_USER_ID, "fixadmin");
					request.getSession().setAttribute(FlowCenterService.LOGIN_USER_NAME, "跳过账号");
					request.setAttribute("action", "getBPA");
					response.sendRedirect(context+"/FixFlowBPACenter?action=getBPA");
				}else{
					Object isAdmin = list2.get(0).get("ISADMIN");
					if (isAdmin != null && Integer.parseInt(isAdmin.toString()) == 1) {
						request.getSession().setAttribute(FlowCenterService.LOGIN_USER_ID, list2.get(0).get("USERID"));
						request.getSession().setAttribute(FlowCenterService.LOGIN_USER_NAME, list2.get(0).get("USERNAME"));
						
						//登录时根据登录的目标切换跳转目标
						String loginType = request.getParameter("loginType");
						if(loginType !=null && loginType.equals("on")){
							request.setAttribute("action", "processDefinitionList");
							response.sendRedirect(context+"/FlowManager?action=processDefinitionList");
						}else{
							request.setAttribute("action", "getBPA");
							response.sendRedirect(context+"/FixFlowBPACenter?action=getBPA");
						}
					} else {
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print("<script>alert('该用户不是管理员！');window.location.href='"+context+"/fixflow/login.jsp';</script>");
					}
				}
			}else{
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("<script>alert('用户名或密码错误！');window.location.href='"+context+"/fixflow/login.jsp';</script>");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//最终关闭数据库连接
			try {
				if(connection!=null && !connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}

}
