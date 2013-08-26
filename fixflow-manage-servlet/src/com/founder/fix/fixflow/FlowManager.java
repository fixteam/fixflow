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

import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.service.JobService;
import com.founder.fix.fixflow.service.ProcessDefinitionService;
import com.founder.fix.fixflow.service.ProcessInstanceService;
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

			String userId = StringUtil.getString(request.getSession()
					.getAttribute(FlowCenterService.LOGIN_USER_ID));
			filter.put("userId", userId);
			request.setAttribute("nowAction", action);
			if ("processDefinitionList".endsWith(action)) {
				Map<String, Object> result = getProcessDefinitionService().getProcessDefitionList(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				request.setAttribute("pageInfo", filter.get("pageInfo"));
				rd = request.getRequestDispatcher("/manager/processDefinitionList.jsp");
			}else if(action.equals("processManageList")){
//				String processAction = StringUtil.getString(filter.get("processAction"));
				request.setAttribute("nowProcessAction", action);
				Map<String,Object> result = getFlowManager().getProcessInstances(filter);
				request.setAttribute("result", result);
				rd = request.getRequestDispatcher("/manager/processInstanceList.jsp");

			}else if(action.equals("suspendProcessInstance")){
				getFlowManager().suspendProcessInstance(filter);
				rd = request.getRequestDispatcher("/FlowManager?action=processManageList");
			}else if(action.equals("continueProcessInstance")){
				getFlowManager().continueProcessInstance(filter);
				rd = request.getRequestDispatcher("/FlowManager?action=processManageList");
			}else if(action.equals("terminatProcessInstance")){
				getFlowManager().terminatProcessInstance(filter);
				rd = request.getRequestDispatcher("/FlowManager?action=processManageList");
			}else if(action.equals("deleteProcessInstance")){
				getFlowManager().deleteProcessInstance(filter);
				rd = request.getRequestDispatcher("/FlowManager?action=processManageList");
			}else if(action.equals("toProcessVariable")){
				Map<String, Object> result = getFlowManager().getProcessVariables(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/manager/processVariableList.jsp");
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
				rd = request.getRequestDispatcher("/manager/processTokenList.jsp");
			}
			//流程定义新增和更新，取决于参数中有没有deploymentId
			if("deploy".equals(action)){
				getProcessDefinitionService().deployByZip(filter);
				rd = request.getRequestDispatcher("/FlowManager?action=processDefinitionList");
			}
			if("deleteDeploy".equals(action)){
				getProcessDefinitionService().deleteDeploy(filter);
				rd = request.getRequestDispatcher("/FlowManager?action=processDefinitionList");
			}
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
			}
			
			if("getUserList".equals(action)){
				request.setAttribute("nowAction", "UserGroup");
				Map<String, Object> result = getUserGroupService().getAllUsers(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				
				List<Map<String,Object>> groupList = getUserGroupService().getAllGroupDefinition(filter);
				request.setAttribute("groupList", groupList);
				request.setAttribute("pageInfo", filter.get("pageInfo"));
				rd = request.getRequestDispatcher("/manager/userList.jsp");
			}
			if("getGroupList".equals(action)){
				request.setAttribute("nowAction", "UserGroup");
				Map<String, Object> result = getUserGroupService().getAllGroup(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				List<Map<String,Object>> groupList = getUserGroupService().getAllGroupDefinition(filter);
				request.setAttribute("groupList", groupList);
				request.setAttribute("pageInfo", filter.get("pageInfo"));
				rd = request.getRequestDispatcher("/manager/groupList.jsp");
			}
			if("getUserInfo".equals(action)){
				Map<String, Object> pageResult = getUserGroupService().getUserInfo(
						filter);
				filter.putAll(pageResult);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/manager/userInfo.jsp");
			}
			if("getGroupInfo".equals(action)){
				Map<String, Object> pageResult = getUserGroupService().getGroupInfo(
						filter);
				filter.putAll(pageResult);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/manager/groupInfo.jsp");
			}
			if("getJobList".equals(action)){
				request.setAttribute("nowAction", "jobManager");
				Map<String, Object> result = getJobService().getJobList(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/manager/jobList.jsp");
			}
			if("viewJobInfo".equals(action)){
				request.setAttribute("nowAction", "jobManager");
				Map<String, Object> result = getJobService().getJobTrigger(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/manager/jobInfo.jsp");
			}
			if("suspendJob".equals(action)){
				request.setAttribute("nowAction", "jobManager");
				getJobService().suspendJob(filter);
				Map<String, Object> result = getJobService().getJobList(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/manager/jobList.jsp");
			}
			if("continueJob".equals(action)){
				getJobService().continueJob(filter);
				request.setAttribute("nowAction", "jobManager");
				Map<String, Object> result = getJobService().getJobList(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/manager/jobList.jsp");
			}
			if("suspendTrigger".equals(action)){
				getJobService().suspendTrigger(filter);
				request.setAttribute("nowAction", "jobManager");
				Map<String, Object> result = getJobService().getJobTrigger(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/manager/jobInfo.jsp");
			}
			if("continueTrigger".equals(action)){
				getJobService().continueTrigger(filter);
				request.setAttribute("nowAction", "jobManager");
				Map<String, Object> result = getJobService().getJobTrigger(filter);
				filter.putAll(result);
				request.setAttribute("result", filter);
				rd = request.getRequestDispatcher("/manager/jobInfo.jsp");
			}
			if (rd != null)
				rd.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
			CurrentThread.clear();
		}
		
	}
	
	public ProcessInstanceService getFlowManager() {
		return (ProcessInstanceService) SpringConfigLoadHelper
				.getBean("processInstanceServiceImpl");
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
