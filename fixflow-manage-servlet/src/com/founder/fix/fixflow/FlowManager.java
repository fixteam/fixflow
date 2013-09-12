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
 * @author yangchenhui
 */
package com.founder.fix.fixflow;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.service.JobService;
import com.founder.fix.fixflow.service.ProcessDefinitionService;
import com.founder.fix.fixflow.service.ProcessInstanceService;
import com.founder.fix.fixflow.service.TaskInstanceService;
import com.founder.fix.fixflow.service.UserGroupService;
import com.founder.fix.fixflow.util.CurrentThread;
import com.founder.fix.fixflow.util.JSONUtil;
import com.founder.fix.fixflow.util.SpringConfigLoadHelper;

public class FlowManager extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("ISGET", "ISGET");
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CurrentThread.init();
		String userId = StringUtil.getString(request.getSession().getAttribute(
				FlowCenterService.LOGIN_USER_ID));
		if (StringUtil.isEmpty(userId)) {
			String context = request.getContextPath();
			response.sendRedirect(context + "/");
			return;
		}
		ServletOutputStream out = null;
		String action = StringUtil.getString(request.getParameter("action"));
		if (StringUtil.isEmpty(action)) {
			action = StringUtil.getString(request.getAttribute("action"));
		}
		RequestDispatcher rd = null;
		try {
			Map<String, Object> filter = new HashMap<String, Object>();

			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload Uploader = new ServletFileUpload(
						new DiskFileItemFactory());
				// Uploader.setSizeMax("); // 设置最大文件尺寸
				Uploader.setHeaderEncoding("utf-8");
				List<FileItem> fileItems = Uploader.parseRequest(request);
				for (FileItem item : fileItems) {
					filter.put(item.getFieldName(), item);
					if (item.getFieldName().equals("action"))
						action = item.getString();
					if(item.getFieldName().equals("deploymentId")){
						filter.put("deploymentId", item.getString());
					}
				}
			} else {
				Enumeration enu = request.getParameterNames();
				while (enu.hasMoreElements()) {
					Object tmp = enu.nextElement();
					Object obj = request
							.getParameter(StringUtil.getString(tmp));

//					if (request.getAttribute("ISGET") != null)
						obj = new String(obj.toString().getBytes("ISO8859-1"),
								"utf-8");

					filter.put(StringUtil.getString(tmp), obj);
				}
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
			if ("processDefinitionList".equals(action)) {
				Map<String, Object> result = getProcessDefinitionService().getProcessDefitionList(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				request.setAttribute("pageInfo", filter.get("pageInfo"));
				rd = request.getRequestDispatcher("/fixflow/manager/processDefinitionList.jsp");
			}else if(action.equals("processManageList")){
//				String processAction = StringUtil.getString(filter.get("processAction"));
//				request.setAttribute("nowProcessAction", action);
				Map<String,Object> result = getFlowManager().getProcessInstances(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				request.setAttribute("pageInfo", filter.get("pageInfo"));
				rd = request.getRequestDispatcher("/fixflow/manager/processInstanceList.jsp");
			}else if(action.equals("suspendProcessInstance")){
				try{
					getFlowManager().suspendProcessInstance(filter);
				}catch(Exception e){
					request.setAttribute("errorMsg", e.getMessage());
					throw e;
				}finally{
					rd = request.getRequestDispatcher("/FlowManager?action=processManageList");
				}
			}else if(action.equals("continueProcessInstance")){
				try{
					getFlowManager().continueProcessInstance(filter);
				}catch(Exception e){
					request.setAttribute("errorMsg", e.getMessage());
					throw e;
				}finally{
					rd = request.getRequestDispatcher("/FlowManager?action=processManageList");
				}
			}else if(action.equals("terminatProcessInstance")){
				try{
					getFlowManager().terminatProcessInstance(filter);
				}catch(Exception e){
					request.setAttribute("errorMsg", e.getMessage());
					throw e;
				}finally{
					rd = request.getRequestDispatcher("/FlowManager?action=processManageList");
				}
			}else if(action.equals("deleteProcessInstance")){
				try{
					getFlowManager().deleteProcessInstance(filter);
				}catch(Exception e){
					request.setAttribute("errorMsg", e.getMessage());
					throw e;
				}finally{
					rd = request.getRequestDispatcher("/FlowManager?action=processManageList");
				}
			}else if(action.equals("toProcessVariable")){
				Map<String, Object> result = getFlowManager().getProcessVariables(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/fixflow/manager/processVariableList.jsp");
			}else if(action.equals("saveProcessVariables")){
				String tmp = (String)filter.get("insertAndUpdate");
				if(StringUtil.isNotEmpty(tmp)){
					Map<String,Object> tMap = JSONUtil.parseJSON2Map(tmp);
					filter.put("insertAndUpdate", tMap);
				}
				getFlowManager().saveProcessVariables(filter);
				rd = request.getRequestDispatcher("/FlowManager?action=toProcessVariable");
			}else if(action.equals("processTokenList")){
				Map<String,Object> result = getFlowManager().getProcessTokens(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/fixflow/manager/processTokenList.jsp");
			}else if(action.equals("taskInstanceList")){
				try{
					filter.put("path", request.getSession().getServletContext()
							.getRealPath("/"));
					Map<String, Object> pageResult = getTaskManager().getTaskList(filter);
					filter.putAll(pageResult);
					request.setAttribute("result", filter);
					request.setAttribute("pageInfo", filter.get("pageInfo"));
				}catch(Exception e){
					request.setAttribute("errorMsg", e.getMessage());
					throw e;
				}finally{
					rd = request.getRequestDispatcher("/fixflow/manager/taskInstanceList.jsp");
				}
			}else if(action.equals("doTaskSuspend")){
				try{
					getTaskManager().suspendTask(filter);
				}catch(Exception e){
					request.setAttribute("errorMsg", e.getMessage());
					throw e;
				}finally{
					rd = request.getRequestDispatcher("/FlowManager?action=taskInstanceList");
				}
			}else if(action.equals("doTaskResume")){
				try{
					getTaskManager().resumeTask(filter);
				}catch(Exception e){
					request.setAttribute("errorMsg", e.getMessage());
					throw e;
				}finally{
					rd = request.getRequestDispatcher("/FlowManager?action=taskInstanceList");
				}
			}else if(action.equals("doTaskTransfer")){
				try{
					getTaskManager().transferTask(filter);
				}catch(Exception e){
					request.setAttribute("errorMsg", e.getMessage());
					throw e;
				}finally{
					rd = request.getRequestDispatcher("/FlowManager?action=taskInstanceList");
				}
			}else if(action.equals("doTaskRollBackNode")){
				try{
					getTaskManager().rollBackNode(filter);
				}catch(Exception e){
					request.setAttribute("errorMsg", e.getMessage());
					throw e;
				}finally{
					rd = request.getRequestDispatcher("/FlowManager?action=taskInstanceList");
				}
			}
			//流程定义新增和更新，取决于参数中有没有deploymentId
			if("deploy".equals(action)){
				String message = "操作成功！";
				try{
					getProcessDefinitionService().deployByZip(filter);
					response.setContentType("text/html;charset=utf-8");
				}catch(Exception ex){
					message +="操作失败："+ex.getMessage();
				}
				finally{
					response.getWriter().print("<script>alert('"+message+"');window.close();</script>");
				}
			}else
			if("deleteDeploy".equals(action)){
				getProcessDefinitionService().deleteDeploy(filter);
				rd = request.getRequestDispatcher("/FlowManager?action=processDefinitionList");
			}else
			if("download".equals(action)){
				String processDefinitionId = StringUtil.getString(filter.get("processDefinitionId"));
				response.reset();
				request.setCharacterEncoding("gbk");
				response.setContentType("applcation/octet-stream");
				response.setHeader("Content-disposition","attachment; filename="+processDefinitionId+".zip");
				response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0,private, max-age=0");
				response.setHeader("Content-Type", "application/octet-stream");
				response.setHeader("Content-Type", "application/force-download");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
				
				ZipOutputStream outZip = new ZipOutputStream(response.getOutputStream());
				List<Map<String,Object>> fileList = getProcessDefinitionService().getResources(filter);
				for (Map<String, Object> file : fileList) {
					ZipEntry entry = new ZipEntry(file.get("FILENAME").toString());
					entry.setSize(((byte[])file.get("BYTES")).length);
					outZip.putNextEntry(entry);
					outZip.write((byte[])file.get("BYTES"));
					outZip.closeEntry();
				}
				outZip.close();
				outZip.flush();
				outZip.close();
			}else
			
			if("getUserList".equals(action)){
				request.setAttribute("nowAction", "UserGroup");
				Map<String, Object> result = getUserGroupService().getAllUsers(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				
				List<Map<String,Object>> groupList = getUserGroupService().getAllGroupDefinition(filter);
				request.setAttribute("groupList", groupList);
				request.setAttribute("pageInfo", filter.get("pageInfo"));
				rd = request.getRequestDispatcher("/fixflow/manager/userList.jsp");
			}else
			if("getGroupList".equals(action)){
				request.setAttribute("nowAction", "UserGroup");
				Map<String, Object> result = getUserGroupService().getAllGroup(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				List<Map<String,Object>> groupList = getUserGroupService().getAllGroupDefinition(filter);
				request.setAttribute("groupList", groupList);
				request.setAttribute("pageInfo", filter.get("pageInfo"));
				rd = request.getRequestDispatcher("/fixflow/manager/groupList.jsp");
			}else
			if("getUserInfo".equals(action)){
				Map<String, Object> pageResult = getUserGroupService().getUserInfo(
						filter);
				filter.putAll(pageResult);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/fixflow/manager/userInfo.jsp");
			}else
			if("getGroupInfo".equals(action)){
				Map<String, Object> pageResult = getUserGroupService().getGroupInfo(
						filter);
				filter.putAll(pageResult);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/fixflow/manager/groupInfo.jsp");
			}else
			if("getJobList".equals(action)){
				request.setAttribute("nowAction", "jobManager");
				Map<String, Object> result = getJobService().getJobList(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/fixflow/manager/jobList.jsp");
			}else
			if("viewJobInfo".equals(action)){
				request.setAttribute("nowAction", "jobManager");
				Map<String, Object> result = getJobService().getJobTrigger(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/fixflow/manager/jobInfo.jsp");
			}else
			if("suspendJob".equals(action)){
				request.setAttribute("nowAction", "jobManager");
				getJobService().suspendJob(filter);
				Map<String, Object> result = getJobService().getJobList(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/fixflow/manager/jobList.jsp");
			}else
			if("continueJob".equals(action)){
				getJobService().continueJob(filter);
				request.setAttribute("nowAction", "jobManager");
				Map<String, Object> result = getJobService().getJobList(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/fixflow/manager/jobList.jsp");
			}else
			if("suspendTrigger".equals(action)){
				getJobService().suspendTrigger(filter);
				request.setAttribute("nowAction", "jobManager");
				Map<String, Object> result = getJobService().getJobTrigger(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/fixflow/manager/jobInfo.jsp");
			}else
			if("continueTrigger".equals(action)){
				getJobService().continueTrigger(filter);
				request.setAttribute("nowAction", "jobManager");
				Map<String, Object> result = getJobService().getJobTrigger(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/fixflow/manager/jobInfo.jsp");
			}else
			if("setHis".equals(action)){
				getFlowManager().setHistory(filter);
				rd = request.getRequestDispatcher("/FlowManager?action=processManageList");
			}else
			if("updateCache".equals(action)){
				ProcessEngineManagement.getDefaultProcessEngine().cleanCache(true, true);
				response.getWriter().write("update success!");
			}
		}catch (Exception e) {
			e.printStackTrace();
			try {
				CurrentThread.rollBack();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
			try {
				CurrentThread.clear();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rd != null)
			rd.forward(request, response);
	}
	
	public ProcessInstanceService getFlowManager() {
		return (ProcessInstanceService) SpringConfigLoadHelper
				.getBean("processInstanceServiceImpl");
	}
	
	public TaskInstanceService getTaskManager() {
		return (TaskInstanceService) SpringConfigLoadHelper
				.getBean("taskInstanceServiceImpl");
	}

	private ProcessDefinitionService getProcessDefinitionService(){
		return (ProcessDefinitionService) SpringConfigLoadHelper.getBean("processDefinitionServiceImpl");
	}
	
	private UserGroupService getUserGroupService(){
		return (UserGroupService) SpringConfigLoadHelper.getBean("userGroupServiceImpl");
	}
	private JobService getJobService(){
		return (JobService) SpringConfigLoadHelper.getBean("jobServiceImpl");
	}
	
}
