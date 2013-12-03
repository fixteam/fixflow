package com.founder.fix.fixflow.editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.common.util.URI;

import com.founder.fix.fixflow.bpmn.converter.FixFlowConverter;
import com.founder.fix.fixflow.explorer.BaseServlet;
import com.founder.fix.fixflow.explorer.FileHandle;
import com.founder.fix.fixflow.service.FlowCenterService;

/**
 * 
 * 职责：web流程图的基本操作
 * 开发者：徐海洋
 */
public class FlowWebManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * 任务：加载web流程图，返回json格式对象
	 */
    public void loadBPMWeb(){
    	try {
            InputStream input = new FileInputStream(new File(getBasePath()+File.separator+"fixflow-repository"+File.separator+ buildPath() +File.separator+request("fileName"))); 
    		ObjectNode on = new FixFlowConverter().convertBpmn2Json("process_testych", input);
    		ajaxResultObj(on);
		} catch (Exception e) {
			error("加载web流程图，返回json格式对象出错!");
		}
    }
    
    public void reTryModelInfo(){
    	try {
    		ObjectMapper objectMapper = new ObjectMapper();
            InputStream input = new FileInputStream(new File(getBasePath()+File.separator+"fixflow-repository"+File.separator+ buildPath() +File.separator+request("fileName"))); 
    		ObjectNode on = new FixFlowConverter().convertBpmn2Json("process_testych", input);
    		ObjectNode rootNode = objectMapper.createObjectNode();
    		rootNode.put("name", "testName");
    		rootNode.put("revision", 2);
    		rootNode.put("description", "测试流程实例");
    		rootNode.put("modelId", "11212");
    		rootNode.put("model", on);
    		ajaxResultObj(rootNode);
		} catch (Exception e) {
			error("加载web流程图，返回json格式对象出错!");
		}
    }
    
    
    public void save() throws JsonProcessingException, IOException{
    	ObjectMapper objectMapper = new ObjectMapper();
    	ObjectNode modelNode = objectMapper.createObjectNode();
    	String body;
		body = getBody(requestParm);
		body = URLDecoder.decode(body);
    	String json_xml = getBodyFromPayload(body,"json_xml");
    	JsonNode objectNode = objectMapper.readTree(json_xml);
    	new FixFlowConverter().save(objectNode,URI.createFileURI("d:\\node_template.bpmn"));
    	
    }
	/**
	 * 任务：将流文件写入到指定的目录下的指定文件
	 */
    public void writeFile2Address(){
    	try {
    		String msg =  FileHandle.updload(requestParm, responseParm, getBasePath()+File.separator+"fixflow-repository"+File.separator+ buildPath(), requestAttribute("fileName"));
    		success(msg, "string");
    	} catch (Exception e) {
			error("文件写入到指定的目录下出错!");
		}
    }
    
    public String getBodyFromPayload(String content,String title){
		String []valueArr = content.split("&");
		for(String str :valueArr){
			String []tmpArr = str.split("=");
			if(tmpArr.length>1){
				
				if(tmpArr[0].equals(title)){
					return str.substring(tmpArr[0].length()+1);
				}
			}
		}
		return null;
	}
	
	public String getBody(HttpServletRequest request) throws IOException {

	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;

	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }

	    body = stringBuilder.toString();
	    return body;
	}
    
    
    public String buildPath(){
		String[] node = null;
		try{
			node = request("path").split(",");
		}catch(Exception e){
			node = requestAttribute("path").split(",");
		}
		String path = session(FlowCenterService.LOGIN_USER_ID);
		for (int i = 0; i < node.length; i++) {
			path += File.separator+node[i];
		}
		return path;
    }
}
