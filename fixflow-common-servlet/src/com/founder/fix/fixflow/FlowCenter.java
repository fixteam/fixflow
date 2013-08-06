package com.founder.fix.fixflow;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.pojo.PageResultTo;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.util.CurrentThread;
import com.founder.fix.fixflow.util.SpringConfigLoadHelper;

/**
 * Servlet implementation class FlowCenter
 */
public class FlowCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlowCenter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CurrentThread.init();
		String action = StringUtil.getString(request.getParameter("action"));
		if(StringUtil.isEmpty(action)){
			action = StringUtil.getString(request.getAttribute("action"));
		}
		
		try{
			RequestDispatcher rd = null;
			String userId = StringUtil.getString(request.getSession().getAttribute(FlowCenterService.LOGIN_USER_ID));
			if(action.equals("getMyProcess")){
				List<Map<String,String>> result = getFlowCenter().queryStartProcess(userId);
				request.setAttribute("result", result);
				rd = request.getRequestDispatcher("/index.jsp");
			}else if(action.equals("getMyTask")){
				Map<String,String> filter = new HashMap<String,String>();
				filter.put("userId", userId);
				PageResultTo pageResult = getFlowCenter().queryMyTask(filter);
				request.setAttribute("result", pageResult);
				rd = request.getRequestDispatcher("/index.jsp");
			}else if(action.equals("getIcon")){
				response.getOutputStream();
			}
			
			if(rd!=null)
				rd.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			CurrentThread.clear();
		}
		
	}
	
	public FlowCenterService getFlowCenter(){
		return (FlowCenterService)SpringConfigLoadHelper.getBean("flowCenterServiceImpl");
	}

}
