package com.founder.fix.fixflow.explorer;
 
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
			success(FileAndDirectoryUtils.buildLevelJsonDataWithLoginPerson(request("userId"),getBasePath()));
		} catch (Exception e) {
			error("初始化参数出错!");
		}
    }
    
	 
    public void create(){
    	try {
			FileAndDirectoryUtils.createFileOrDirectory(buildPath(),getBasePath());
			success("创建成功!");
		} catch (Exception e) {
			error("创建失败!");
		}
    }
    
    public String buildPath(){
    		String[] node = request("path").split(",");
    		String path = "";
    		for (int i = 0; i < node.length; i++) {
				if(i == 0){
					path = node[i]+"/"+request("userId");
					continue;
				}
				path += "/"+node[i];
			}
    		return path;
    }
}
