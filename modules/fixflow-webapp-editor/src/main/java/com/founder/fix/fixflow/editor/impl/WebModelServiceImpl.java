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
package com.founder.fix.fixflow.editor.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.common.util.URI;

import com.founder.fix.fixflow.bpmn.converter.FixFlowConverter;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.editor.service.WebModelService;
import com.founder.fix.fixflow.util.FileUtil;
import com.founder.fix.fixflow.util.FlowWebConst;

/**
 * 处理流程模型的servlet
 */
public class WebModelServiceImpl implements WebModelService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	public WebModelServiceImpl(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		this.request = request;
		this.response = response;
		this.request.setCharacterEncoding("utf-8");
		this.response.setContentType("application/json");
		this.response.setCharacterEncoding("utf-8");
	}
	
	/**
	 * 加载模型JSON
	 */
	public void loadBPMNJson() throws ServletException, IOException{
		PrintWriter out = null;
		try {
			out = response.getWriter();
            InputStream input = new FileInputStream(buildPath() +File.separator+request.getParameter("fileName")); 
    		ObjectNode on = new FixFlowConverter().convertBpmn2Json("process_testych", input);
    		out.print(on);
		} catch (Exception e) {
			error(e.getMessage());
		}finally{
        	out.flush();
        	out.close();
        }

	}

	/**
	 * 保存模型文件
	 */
	@SuppressWarnings("deprecation")
	public void modelSave() throws ServletException, IOException{
		String body = getBody(this.request);
		
		String json_xml = getParameterFromPayload(body,"json_xml");
		String svg_xml = getParameterFromPayload(body,"svg_xml");
		String fileName = getParameterFromPayload(body,"fileName");
	    ObjectMapper objectMapper = new ObjectMapper();
    	JsonNode objectNode = objectMapper.readTree(json_xml);
    	String resFilePath = getBasePath(this.request)+"temp"+File.separator+"node_template.bpmn";;
    	String newFilePath = buildPath(body)+File.separator+fileName;
    	String staticFilePath = getBasePath(this.request)+"template"+File.separator+"node_template.bpmn";
    	FileOutputStream outputStream = null;
    	try{
    		//保存思想：因为save方法不一定保存成功，并且保存失败后会导致原有文件丢失，所以做了个临时保存，没问题再复制过去
    		FileUtil.copyFile(staticFilePath, resFilePath);
    		new FixFlowConverter().save(objectNode,URI.createFileURI(resFilePath));
    	    FileUtil.copyFile(resFilePath, newFilePath);
    	    
    	    //png图片处理
    	    String pngPath = newFilePath.substring(0,newFilePath.lastIndexOf(".")) + ".png";
    		File file = new File(pngPath);
    		InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes("utf-8"));
    	    TranscoderInput input = new TranscoderInput(svgStream);
    	    file.createNewFile();
	    	outputStream = new FileOutputStream(file);
		    PNGTranscoder transcoder = new PNGTranscoder();
		    TranscoderOutput output = new TranscoderOutput(outputStream);
			transcoder.transcode(input, output);
			outputStream.flush();
			
    	}catch(Exception e){
    		error(e.getMessage());
    	}finally{
			if(outputStream !=null){
				outputStream.close();	
			}
		}
	}
	
	/**
	 * 二次请求模型信息
	 */
	public void reTryModelInfo() throws ServletException, IOException {
		PrintWriter out = null;
		try {
			FixFlowConverter fixFlowConverter = new FixFlowConverter();
			
    		ObjectMapper objectMapper = new ObjectMapper();
            InputStream input = new FileInputStream(buildPath() +File.separator+request.getParameter("fileName")); 
            Definitions definitions = fixFlowConverter.getDefinitions("process_1", input);
            Process process = (Process)definitions.getRootElements().get(0);
    		ObjectNode on = fixFlowConverter.convertDefinitions2Json(definitions);
    		ObjectNode rootNode = objectMapper.createObjectNode();
    		rootNode.put("name", process.getName());
    		rootNode.put("description", process.getName());
    		rootNode.put("modelId", process.getId());
    		rootNode.put("model", on);
    		out = response.getWriter();
    		out.print(rootNode);
		} catch (Exception e) {
			error("加载web流程图，返回json格式对象出错!");
		}
		
	}
	
	/**
	 * 出错处理
	 * @param message
	 */
	public void error(String message){
		try {
			ObjectNode jsonNode = new ObjectMapper().createObjectNode();
			jsonNode.put("state", "error");
			jsonNode.put("result", message);
            response.getWriter().print(jsonNode);
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
	}
	
	/**
	 * 构建路径
	 * @return
	 * @throws IOException
	 */
	public String buildPath() throws IOException{
    	String loginId = request.getSession().getAttribute("LOGIN_USER_ID").toString();
    	String path = request.getParameter("path");
    	if(path == null){
    		Object pathObject = request.getAttribute("path");
    		if(pathObject != null){
    			path = pathObject.toString();	
    		}
    	}
    	if(path == null){
    		throw new FixFlowException("path不能为空");
    	}
		String [] pathArr = path.split(",");
		String type = pathArr[0];
		
		String tmpPathString = "";
		
		if("private".equals(type)){
			tmpPathString = FlowWebConst.privatePath +File.separator + loginId;
		}else{
			tmpPathString = FlowWebConst.sharedPath;
		}
		
		for(int i = 1; i<pathArr.length;i++){
			tmpPathString += File.separator;
			tmpPathString += pathArr[i];
		}
    	return tmpPathString;
	 }
	 
	/**
	 * 从payLoad的方式构建路径 
	 * @param body
	 * @return
	 * @throws IOException
	 */
	 public String buildPath(String body) throws IOException{
    	String loginId = request.getSession().getAttribute("LOGIN_USER_ID").toString();
    	String path = request.getParameter("path");
    	if(path == null){
    		Object pathObject = request.getAttribute("path");
    		if(pathObject != null){
    			path = pathObject.toString();	
    		}
    	}
    	if(path == null){
    		path = getParameterFromPayload(body, "path");
    	}
    	if(path == null){
    		throw new FixFlowException("path不能为空");
    	}
		String [] pathArr = path.split(",");
		String type = pathArr[0];
		
		String tmpPathString = "";
		
		if("private".equals(type)){
			tmpPathString = FlowWebConst.privatePath +File.separator + loginId;
		}else{
			tmpPathString = FlowWebConst.sharedPath;
		}
		
		for(int i = 1; i<pathArr.length;i++){
			tmpPathString += File.separator;
			tmpPathString += pathArr[i];
		}
    	return tmpPathString;
    }
	 
	/**
	 * 从payload中获取参数
	 * @param content
	 * @param title
	 * @return
	 */
	public String getParameterFromPayload(String content,String title){
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
		
	/**
	 * 获取payload中的body信息
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public String getBody(HttpServletRequest request) throws IOException {
	    String body = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;
	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
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
	    body = new String(stringBuilder.toString().getBytes("ISO-8859-1"), "UTF-8") ;
	    body = URLDecoder.decode(body, "UTF-8");
	    return body;
	}
	
	/**
	 * 获取根路径
	 * @param request
	 * @return
	 */
	public String getBasePath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/")+File.separator+"fixflow-editor"+File.separator;
	}

}
