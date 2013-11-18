package com.founder.fix.fixflow.explorer;

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
			error("初始化参数出错!");
		}
    }
    
	 
    public void create(){
    	try {
			FileAndDirectoryUtils.createFileOrDirectory(buildPath()+File.separator+request("newFileName"),getBasePath());
			success("创建成功!","string");
		} catch (Exception e) {
			error(e.getMessage());
		}
    }
    
    public void readSubFileAndDirectory(){
    	try {
    		String subJson = FileAndDirectoryUtils.readSubFileAndDirectory(buildPath(),getBasePath());
    		FileAndDirectoryUtils.clear();
    		success(subJson);
    	} catch (Exception e) {
    		error("下属文件及文件夹读取失败!");
    	}
    }
    
    
    public void reName(){
    	try {
    		FileAndDirectoryUtils.renameFile(buildPath()+File.separator+request("oldFileName"),buildPath()+File.separator+request("newFileName"),getBasePath());
    			success("重命名成功!","string");
    	} catch (Exception e) {
    		error("重命名失败!");
    	}
    }
    
    public void moveFileOrDirectory(){
    	try {
    		FileAndDirectoryUtils.moveFileAndDirectory(getMoveResource()[0], getMoveResource()[1], getBasePath(),request("fileName"));
    		success("重命名成功!","string");
    	} catch (Exception e) {
    		error("重命名失败!");
    	}
    }
    
    public String buildPath(){
		String[] node = request("path").split(",");
		String path = session(FlowCenterService.LOGIN_USER_ID);
		for (int i = 0; i < node.length; i++) {
			path += File.separator+node[i];
		}
		return path;
    }
    
    public String[] getMoveResource(){
    	String[] resutl = new String[2];
    	String[] node = request("path").split(",");
    	resutl[0] = buildPath()+File.separator+ request("fileName");
    	resutl[1] =session(FlowCenterService.LOGIN_USER_ID)+File.separator+ node[0]+File.separator+"resolvent";
    	return resutl;
    }
}
