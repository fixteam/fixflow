package com.founder.fix.fixflow.explorer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.founder.fix.fixflow.explorer.impl.FlowExplorerServiceImpl;
import com.founder.fix.fixflow.explorer.service.FlowExplorerService;
import com.founder.fix.fixflow.explorer.util.FileAndDirectoryUtils;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.founder.fix.fixflow.util.FileUtil;
 
/**
 * 文件目录管理类
 * 职责：接收、传递（文件及文件夹）数据信息
 * 辅助工具类：
 *          FileAndDirectoryUtils
 *          创建及收集（文件及文件夹）数据
 * 开发者：徐海洋
 */
public class FileAndDirectoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * 任务：提供前段ZTREE能展现的json格式数据
	 * json：[{id:1,pId:0,name:'公有一级'},{id:2,pId:1,name:'公有二级'},
	 * 		  {id:3,pId:0,name:'私有一级'},{id:4,pId:3,name:'私有二级'}]
	 * 
	 */
    public void loadTree(){
    	try {
    		String json = FileAndDirectoryUtils.buildLevelJsonDataWithLoginPerson(session(FlowCenterService.LOGIN_USER_ID),getBasePath());
    		FileAndDirectoryUtils.clear();
			success(json);
		} catch (Exception e) {
			e.printStackTrace();
			error("初始化参数出错!");
		}
    }
   
    /**
     * 新建文件夹
     */
    public void create(){
    	try {
    		String path = buildPath();
    		path = path + File.separator + request("newFileName");
			FileAndDirectoryUtils.createFileOrDirectory(path);
			success("创建成功!","string");
		} catch (Exception e) {
			error(e.getMessage());
		}
    }
    
    /**
     * 发布流程定义
     */
    public void delopy(){
    	String fileName = request("fileName");
    	String deploymentId = request("deploymentId");
    	String userId = session(FlowCenterService.LOGIN_USER_ID);
    	if(fileName != null){
    		InputStream input = null;
    		InputStream pngInputStream = null;
    		try{
				String pngFileName = fileName.substring(0,fileName.lastIndexOf("."))+".png";
        		input = new FileInputStream(buildPath() +File.separator+fileName); 
        		pngInputStream = new FileInputStream(buildPath() +File.separator+pngFileName); 
        		Map<String,InputStream> fileInputSteamMap = new HashMap<String, InputStream>();
        		fileInputSteamMap.put(fileName, input);
        		fileInputSteamMap.put(pngFileName, pngInputStream);
        		FlowExplorerService flowExplorerService = new FlowExplorerServiceImpl();
        		flowExplorerService.deploy(fileInputSteamMap, deploymentId, userId);
        		success("发布成功", "string");
			}catch(Exception ex){
				ex.printStackTrace();
				error(ex.getMessage());
			}
    	}
    }
    
    /**
     * 获取流程版本信息
     * @throws Exception
     */
    public void getProcessVersionInfo() throws Exception{
    	String userId = session(FlowCenterService.LOGIN_USER_ID);
    	String fileName =  request("fileName");
    	String result = null;
    	try{
    		FlowExplorerService flowExplorerService = new FlowExplorerServiceImpl();
        	result = flowExplorerService.getProcessVersionInfo(fileName, userId);
        	success(result);
    	}catch(Exception ex){
    		error("创建失败" + ex.getMessage());
    	}
    }
    
    public void readSubFileAndDirectory(){
    	try {
    		String path = buildPath();
    		String subJson = FileAndDirectoryUtils.readSubFileAndDirectory(path);
    		FileAndDirectoryUtils.clear();
    		success(subJson);
    	} catch (Exception e) {
    		error("下属文件及文件夹读取失败!");
    	}
    }
    
    public void reName(){
    	try {
    		String basePath = buildPath();
    		String oldFilePathString = basePath + File.separator + request("oldFileName");
    		String newFilePathString = basePath + File.separator + request("newFileName");
    		FileAndDirectoryUtils.renameFile(oldFilePathString,newFilePathString);
    		success("重命名成功!","string");
    	} catch (Exception e) {
    		error(e.getMessage());
    	}
    }
    
    public void moveFileOrDirectory(){
    	try {
    		String []resourceArr = getMoveResource();
    		String bpmnFile = resourceArr[0];
    		String bpmnFileName = request("fileName");
    		String newPath = resourceArr[1];
    		//如果删除File,则删除同名PNG
    		if(bpmnFile.lastIndexOf(".") > -1){
    			String pngFile = bpmnFile.substring(0, bpmnFile.lastIndexOf(".")) + ".png";
    			String pngFileName = bpmnFileName.substring(0, bpmnFileName.lastIndexOf(".")) + ".png";
    			FileAndDirectoryUtils.moveFileAndDirectory(pngFile, newPath,pngFileName);
    		}
    		FileAndDirectoryUtils.moveFileAndDirectory(bpmnFile, newPath,bpmnFileName);
    		success("删除成功!","string");
    	} catch (Exception e) {
    		e.printStackTrace();
    		error("删除失败!");
    	}
    }
    
    public String buildPath(){
    	String loginId = session(FlowCenterService.LOGIN_USER_ID);
		String path = request("path");	
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
    
    public String[] getMoveResource(){
    	String[] resutl = new String[2];
    	String[] node = request("path").split(",");
    	resutl[0] = buildPath()+File.separator+ request("fileName");
    	if("private".equals(node[0])){
    		resutl[1] = FileAndDirectoryUtils.privatePath + File.separator + session(FlowCenterService.LOGIN_USER_ID) + File.separator + "resolvent";
    	}else{
    		resutl[1] = FileAndDirectoryUtils.sharedPath + File.separator+"resolvent";
    	}
    	return resutl;
    }
}
