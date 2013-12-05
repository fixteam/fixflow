package com.founder.fix.fixflow.editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.emf.common.util.URI;

import com.founder.fix.fixflow.bpmn.converter.FixFlowConverter;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.util.FileUtil;
public class ModelSaveServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	  public String buildPath(HttpServletRequest request,String filePath){
			String[] node = filePath.split(",");
			String path = request.getSession().getAttribute(FlowCenterService.LOGIN_USER_ID).toString();
			for (int i = 0; i < node.length; i++) {
				path += File.separator+node[i];
			}
			return path;
	    }
	
	 
	public String getBasePath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/")+File.separator+"fixflow-repository"+File.separator;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String body = getBody(req);
		body = URLDecoder.decode(body);
		String json_xml = getBodyFromPayload(body,"json_xml");
		String path = getBodyFromPayload(body,"path");
		String fileName = getBodyFromPayload(body,"fileName");
	    ObjectMapper objectMapper = new ObjectMapper();
    	JsonNode objectNode = objectMapper.readTree(json_xml);
    	String resFilePath = getBasePath(req)+"temp"+File.separator+"node_template.bpmn";;
    	String newFilePath = getBasePath(req)+buildPath(req,path)+File.separator+fileName;
    	String staticFilePath = getBasePath(req)+"system-template"+File.separator+"node_template.bpmn";
    	try{
    		FileUtil.copyFile(staticFilePath, resFilePath);
    		new FixFlowConverter().save(objectNode,URI.createFileURI(getBasePath(req)+"temp"+File.separator+"node_template.bpmn"));
    	    FileUtil.copyFile(resFilePath, newFilePath);
    	}catch(Exception e){
    		e.printStackTrace();
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
}
