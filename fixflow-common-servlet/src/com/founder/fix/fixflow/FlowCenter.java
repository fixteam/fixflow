package com.founder.fix.fixflow;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founder.fix.fixflow.core.impl.util.StringUtil;
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
		Map<String, Object> filter = new HashMap<String, Object>();
		Enumeration enums = request.getParameterNames();
		while (enums.hasMoreElements()) {
			String paramName = (String) enums.nextElement();

			String paramValue = request.getParameter(paramName);

			// 形成键值对应的map
			filter.put(paramName, paramValue);

		}
		
		Enumeration attenums = request.getAttributeNames();
		while (attenums.hasMoreElements()) {
			String paramName = (String) attenums.nextElement();

			Object paramValue = request.getAttribute(paramName);

			// 形成键值对应的map
			filter.put(paramName, paramValue);

		}
		try{
			RequestDispatcher rd = null;
			String userId = StringUtil.getString(request.getSession().getAttribute(FlowCenterService.LOGIN_USER_ID));
			filter.put("userId", userId);
			if(action.equals("getMyProcess")){
				List<Map<String,String>> result = getFlowCenter().queryStartProcess(userId);
				request.setAttribute("result", result);
				rd = request.getRequestDispatcher("startTask.jsp");
			}else if(action.equals("getMyTask")){
				Map<String,Object> pageResult = getFlowCenter().queryMyTaskNotEnd(filter);
				filter.putAll(pageResult);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/todoTask.jsp");
			}else if(action.equals("getProcessImage")){
				response.getOutputStream();
			}else if(action.equals("getInitorTask")){
				Map<String,Object> pageResult = getFlowCenter().queryTaskInitiator(filter);
				filter.putAll(pageResult);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/queryTask.jsp");
			}else if(action.equals("getParticipantsTask")){
				Map<String,Object> pageResult = getFlowCenter().queryTaskParticipants(filter);
				filter.putAll(pageResult);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/queryTask.jsp");
			}else if(action.equals("getFlowGraph")){
				InputStream is = getFlowCenter().getFlowGraph(filter);
				ServletOutputStream out = response.getOutputStream();
				response.setContentType("application/octet-stream;charset=UTF-8");
				byte[] buff = new byte[2048];
				int size = 0;
				while(is!=null && (size = is.read(buff))!=-1){
					out.write(buff,0,size);
				}
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
