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
 * @author ych
 */
package com.founder.fix.fixflow.editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import com.founder.fix.fixflow.bpmn.converter.FixFlowConverter;
import com.founder.fix.fixflow.explorer.BaseServlet;
import com.founder.fix.fixflow.explorer.util.FileAndDirectoryUtils;
import com.founder.fix.fixflow.explorer.util.FileHandle;
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
            InputStream input = new FileInputStream(buildPath() +File.separator+request("fileName")); 
    		ObjectNode on = new FixFlowConverter().convertBpmn2Json("process_testych", input);
    		ajaxResultObj(on);
		} catch (Exception e) {
			e.printStackTrace();
			error("加载web流程图，返回json格式对象出错!");
		}
    }
    
    public void reTryModelInfo(){
    	try {
    		ObjectMapper objectMapper = new ObjectMapper();
            InputStream input = new FileInputStream(buildPath() +File.separator+request("fileName")); 
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
    
    
    public void createBPMNFile(){
    	try{
    		String processId = request("id");
        	String fileName = "";
        	if(processId.endsWith(".bpmn")){
        		fileName = processId;
        		processId = processId.substring(0, processId.indexOf("."));
        	}else{
        		fileName = processId + ".bpmn";
        	}
        	String processName = request("name");
        	String path = buildPath()+File.separator+fileName;
        	new FixFlowConverter().createBPMNFile(path, processId, processName);
        	success("创建成功","String");
    	}catch(Exception ex){
    		ex.printStackTrace();
    		error("创建文件失败");
    	}
    }
    
   
	/**
	 * 任务：将流文件写入到指定的目录下的指定文件
	 */
    public void writeFile2Address(){
    	try {
    		String msg =  FileHandle.updload(requestParm, responseParm, buildPath(), requestAttribute("fileName"));
    		success(msg, "string");
    	} catch (Exception e) {
    		e.printStackTrace();
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
	
	
    
    public String buildPath(){
    	String loginId = session(FlowCenterService.LOGIN_USER_ID);
    	String path = request("path");
    	if(path == null){
    		path = requestAttribute("path");	
    	}
		String [] pathArr = path.split(",");
		String type = pathArr[0];
		
		String tmpPathString = "";
		File file = null;
		
		if("private".equals(type)){
			tmpPathString = FileAndDirectoryUtils.privatePath +file.separator + loginId;
		}else{
			tmpPathString = FileAndDirectoryUtils.sharedPath;
		}
		
		for(int i = 1; i<pathArr.length;i++){
			tmpPathString += File.separator;
			tmpPathString += pathArr[i];
		}
    	return tmpPathString;
    }
}
