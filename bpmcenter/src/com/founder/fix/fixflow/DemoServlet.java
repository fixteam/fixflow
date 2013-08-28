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
import java.sql.PreparedStatement;
import java.util.ArrayList;
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

import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.shell.FixFlowShellProxy;
import com.founder.fix.fixflow.util.CurrentThread;
import com.founder.fix.fixflow.util.JSONUtil;
import com.founder.fix.fixflow.util.SpringConfigLoadHelper;

/**
 * Servlet implementation class FlowCenter
 */
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DemoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("ISGET", "ISGET");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userId = StringUtil.getString(request.getSession().getAttribute(
				FlowCenterService.LOGIN_USER_ID));
		if (StringUtil.isEmpty(userId)) {
			String context = request.getContextPath();
			response.sendRedirect(context + "/center/login.jsp");
			return;
		}
		CurrentThread.init();
		ServletOutputStream out = null;
		String action = StringUtil.getString(request.getParameter("action"));
		if (StringUtil.isEmpty(action)) {
			action = StringUtil.getString(request.getAttribute("action"));
		}

		RequestDispatcher rd = null;
		try {
			Map<String, Object> filter = new HashMap<String, Object>();


			Enumeration enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				Object tmp = enu.nextElement();
				Object obj = request
						.getParameter(StringUtil.getString(tmp));

				// if (request.getAttribute("ISGET") != null)
				obj = new String(obj.toString().getBytes("ISO8859-1"),
						"utf-8");

				filter.put(StringUtil.getString(tmp), obj);
			}


			Enumeration attenums = request.getAttributeNames();
			while (attenums.hasMoreElements()) {
				String paramName = (String) attenums.nextElement();

				Object paramValue = request.getAttribute(paramName);

				// 形成键值对应的map
				filter.put(paramName, paramValue);

			}

			filter.put("userId", userId);
			request.setAttribute("nowAction", action);
			if (action.equals("startOneTask")) { // 仅实现获取按钮功能 add by Rex
				filter.put("path", request.getSession().getServletContext()
						.getRealPath("/"));

				Map<String, Object> list = getFlowCenter().GetFlowRefInfo(
						filter);
				filter.putAll(list);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/demo/startOneTask.jsp");
			} else if (action.equals("doTask")) {// 演示如何进入一个已发起的流程处理页面
				filter.put("path", request.getSession().getServletContext()
						.getRealPath("/"));

				Connection connection = FixFlowShellProxy
						.getConnection(FixFlowShellProxy.DB_FIX_BIZ_BASE);

				try {
					SqlCommand sc = new SqlCommand(connection);
					List params = new ArrayList();
					params.add(filter.get("bizKey"));
					List<Map<String, Object>> res = sc.queryForList(
							"select * from DEMOTABLE where COL1=?", params);

					filter.put("demoObject", res.get(0));

					FlowCenterService fcs = getFlowCenter();
					Map<String, Object> list = fcs.GetFlowRefInfo(filter);
					filter.putAll(list);
					request.setAttribute("result", filter);
					rd = request.getRequestDispatcher("/demo/doTask.jsp");
				}finally{
					connection.close();					
				}
			} else if (action.equals("demoCompleteTask")) {// 演示如何完成下一步
				//这里直接打开了DB_FIX_BIZ_BASE库
				Connection connection = FixFlowShellProxy
						.getConnection(FixFlowShellProxy.DB_FIX_BIZ_BASE);
				PreparedStatement ps = null;

				try {
					//设置这里开始jdbc级别事务
					connection.setAutoCommit(false);
					ps = connection
							.prepareStatement("insert into DEMOTABLE(COL1,COL2) values(?,?)");
					ps.setObject(1, filter.get("businessKey"));
					ps.setObject(2, filter.get("COL2"));
					ps.execute();
					FlowCenterService fcs = getFlowCenter();
					fcs.setConnection(connection);
					fcs.completeTask(filter);
					rd = request
							.getRequestDispatcher("/common/result.jsp");
				} catch(Exception e){
					//事务回滚
					connection.rollback();
				}finally {
					//事务提交
					connection.commit();
					if (ps != null)
						ps.close();
					connection.close();
				}
			} else if (action.equals("demoDoNext")) {// 演示如何在流程已经发起后继续往下运行
				String taskParams = StringUtil.getString(filter
						.get("taskParams"));
				Map<String, Object> flowMaps = JSONUtil
						.parseJSON2Map(taskParams);
				filter.put("taskParams", flowMaps);
				getFlowCenter().completeTask(filter);
				rd = request
						.getRequestDispatcher("/common/result.jsp");
			}
			if (rd != null)
				rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
			CurrentThread.clear();
		}
	}

	public FlowCenterService getFlowCenter() {
		return (FlowCenterService) SpringConfigLoadHelper
				.getBean("flowCenterServiceImpl");
	}

}
