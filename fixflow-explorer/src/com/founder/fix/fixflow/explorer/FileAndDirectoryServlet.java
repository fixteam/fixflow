package com.founder.fix.fixflow.explorer;

import groovy.lang.Buildable;

import java.io.File;

import com.founder.fix.fixflow.service.FlowCenterService;
 
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
    		FileAndDirectoryUtils.moveFileAndDirectory(getMoveResource()[0], getMoveResource()[1],request("fileName"));
    		success("删除成功!","string");
    	} catch (Exception e) {
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
    	//resutl[1] = session(FlowCenterService.LOGIN_USER_ID)+File.separator+ node[0]+File.separator+"resolvent";
    	return resutl;
    }
}
