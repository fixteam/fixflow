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
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.founder.fix.fixflow.core.impl.db.SqlCommand;
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
		if(!isCreated){
			WebApplicationContext wac = WebApplicationContextUtils
			.getRequiredWebApplicationContext(this.getServletContext());
			SpringConfigLoadHelper.buildApplicationContext(wac);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnFactory dbcf = (DBConnFactory)SpringConfigLoadHelper.getBean("DB_FIX_BIZ_BASE");
		try {
			
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			List<Object> list= new ArrayList<Object>();
			list.add(userName);
			password = MD5.getMD5(password.getBytes());
			list.add(password);
			
			StringBuffer sb = new StringBuffer();
			sb.append("select USERID from AU_USERINFO where LOGINID=? and PASSWORD=?");
			Connection connection = dbcf.createConnection();
			SqlCommand sqlcommand = new SqlCommand(connection);
			List<Map<String, Object>> list2 = sqlcommand.queryForList(sb.toString(),list);
			if(list2!=null && list2.size()>0){
				
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

}
