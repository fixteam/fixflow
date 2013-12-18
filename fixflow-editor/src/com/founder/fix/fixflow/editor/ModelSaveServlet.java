package com.founder.fix.fixflow.editor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.emf.common.util.URI;

import com.founder.fix.fixflow.bpmn.converter.FixFlowConverter;
import com.founder.fix.fixflow.explorer.util.FileAndDirectoryUtils;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.util.FileUtil;
public class ModelSaveServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	  public String buildPath(HttpServletRequest request,String path){
		  
		  	String loginId = request.getSession().getAttribute(FlowCenterService.LOGIN_USER_ID).toString();
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
	
	 
	public String getBasePath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/")+File.separator+"fixflow-editor"+File.separator;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String body = getBody(req);
		body = URLDecoder.decode(body);
		String json_xml = getBodyFromPayload(body,"json_xml");
		String svg_xml = getBodyFromPayload(body,"svg_xml");
		
		String path = getBodyFromPayload(body,"path");
		String fileName = getBodyFromPayload(body,"fileName");
	    ObjectMapper objectMapper = new ObjectMapper();
    	JsonNode objectNode = objectMapper.readTree(json_xml);
    	String resFilePath = getBasePath(req)+"temp"+File.separator+"node_template.bpmn";;
    	String newFilePath = buildPath(req,path)+File.separator+fileName;
    	String staticFilePath = getBasePath(req)+"template"+File.separator+"node_template.bpmn";
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
    		e.printStackTrace();
    	}finally{
			if(outputStream !=null){
				outputStream.close();	
			}
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
