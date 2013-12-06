package com.founder.fix.fixflow.editor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.editor.language.json.converter.BpmnJsonConverterUtil;
import org.activiti.editor.language.json.converter.util.JsonConverterUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicy;
import com.founder.fix.bpmn2extensions.coreconfig.AssignPolicyConfig;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandConfig;
import com.founder.fix.bpmn2extensions.coreconfig.TaskCommandDef;
import com.founder.fix.fixflow.core.ProcessEngineConfiguration;
import com.founder.fix.fixflow.core.ProcessEngineManagement;
import com.founder.fix.fixflow.core.impl.ProcessEngineConfigurationImpl;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class StencilsetServlet extends HttpServlet {

	private ObjectMapper objectMapper = new ObjectMapper();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
		StringBuffer document = new StringBuffer();
		resp.setContentType("application/x-json");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
        try 
        {
            JsonNode rootNode = objectMapper.readTree(stencilsetStream);
            initTaskCommandType(rootNode);
            initAssigneeType(rootNode);
            initGroupInfo(rootNode);
            out.print(rootNode);
        }catch(Exception ex){
        	ex.printStackTrace();
        }finally{
        	out.flush();
        	out.close();
        }
       
	}
	
	/**
	 * 初始化处理命令类型
	 * @param rootNode
	 */
	private void initTaskCommandType(JsonNode rootNode){
		 ArrayNode baseNodeArray = (ArrayNode)rootNode.get("propertyPackages");
         JsonNode commandInfoBase = JsonConverterUtil.getChildElementByProperty("commandinfobase", "name", baseNodeArray);
         commandInfoBase = commandInfoBase.get("properties").get(0);
         ArrayNode complexItemsNode =(ArrayNode)commandInfoBase.get("complexItems");
         JsonNode commandType = JsonConverterUtil.getChildElementByProperty("commandtype", "id", complexItemsNode);
         ArrayNode arrayNode = objectMapper.createArrayNode();
         TaskCommandConfig  taskCommandConfig = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getTaskCommandConfig();
         List<TaskCommandDef> commandDefs = taskCommandConfig.getTaskCommandDef();
         for(TaskCommandDef taskCommandDef :commandDefs){
         	 ObjectNode typeNode = objectMapper.createObjectNode();
         	 if(StringUtil.getBoolean(taskCommandDef.getIsEnabled())){
         		 typeNode.put("id",taskCommandDef.getId());
                  typeNode.put("title", taskCommandDef.getName());
                  typeNode.put("value", taskCommandDef.getId());
                  typeNode.put("refToView", "");
                  arrayNode.add(typeNode);
         	 }
         }
         ((ObjectNode)commandType).put("items", arrayNode);
	}
	
	/**
	 * 初始化分配策略
	 * @param rootNode
	 */
	private void initAssigneeType(JsonNode rootNode){
		ArrayNode baseNodeArray = (ArrayNode)rootNode.get("propertyPackages");
        JsonNode taskAssignBase = JsonConverterUtil.getChildElementByProperty("taskassignbase", "name", baseNodeArray);
        taskAssignBase = taskAssignBase.get("properties");
        JsonNode assigneeType = JsonConverterUtil.getChildElementByProperty("assignpolicytype", "id", (ArrayNode)taskAssignBase);
        ArrayNode arrayNode = objectMapper.createArrayNode();
        
        
        AssignPolicyConfig assignPolicyConfig = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getAssignPolicyConfig();
        List<AssignPolicy> assignPolicys = assignPolicyConfig.getAssignPolicy();
        for(AssignPolicy assignPolicy :assignPolicys){
        	 ObjectNode typeNode = objectMapper.createObjectNode();
    		 typeNode.put("id",assignPolicy.getId());
             typeNode.put("title", assignPolicy.getName());
             typeNode.put("value", assignPolicy.getId());
             arrayNode.add(typeNode);
        }
        ((ObjectNode)assigneeType).put("items", arrayNode);
	}
	
	/**
	 * 初始化任务分配中的资源类型
	 */
	private void initGroupInfo(JsonNode rootNode){
		ArrayNode baseNodeArray = (ArrayNode)rootNode.get("propertyPackages");
        JsonNode taskAssignBase = JsonConverterUtil.getChildElementByProperty("taskassignbase", "name", baseNodeArray);
        taskAssignBase = taskAssignBase.get("properties");
        JsonNode potentialownerNode = JsonConverterUtil.getChildElementByProperty("potentialowner", "id", (ArrayNode)taskAssignBase);
        
        JsonNode complexItemsNode = potentialownerNode.get("complexItems");
        JsonNode resourceType = JsonConverterUtil.getChildElementByProperty("resourcetype", "id", (ArrayNode)complexItemsNode);
        
        ArrayNode arrayNode = objectMapper.createArrayNode();
        ObjectNode typeNode = objectMapper.createObjectNode();
		typeNode.put("id","user");
        typeNode.put("title", "用户");
        typeNode.put("value", "user");
        arrayNode.add(typeNode);
        
        List<GroupDefinition> groupDefinitions = ProcessEngineManagement.getDefaultProcessEngine().getProcessEngineConfiguration().getGroupDefinitions();
        for(GroupDefinition groupDefinition :groupDefinitions){
        	ObjectNode objectNode = objectMapper.createObjectNode();
        	objectNode.put("id",groupDefinition.getId());
        	objectNode.put("title", groupDefinition.getName());
        	objectNode.put("value", groupDefinition.getId());
            arrayNode.add(objectNode);
        }
        ((ObjectNode)resourceType).put("items", arrayNode);
	}
}
