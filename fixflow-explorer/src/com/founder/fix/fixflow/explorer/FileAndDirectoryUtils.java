package com.founder.fix.fixflow.explorer;

import java.io.File;

/**
 * 文件及文件夹的IO管理类
 * 职责：获取（文件及文件夹）数据信息
 * 开发者：徐海洋
 */
public class FileAndDirectoryUtils {
	
	private static int key = 0;
	private static String json = "[{id:-1,pId:0,name:'private',type:'dir',isParent:true},{id:-2,pId:0,name:'shared',type:'dir',isParent:true}";
	private static String subJson = "";
	 
	/**
	 * 构建文件及文件夹的层次结构对应的体现数据（json data）
	 * @param loginUserId
	 *          当前登入人的编号
	 * @param basePath
	 *          webcontent目录
	 */
	public static String buildLevelJsonDataWithLoginPerson(String loginUserId, String basePath) throws Exception{
		try{
			iterationRead(new File(basePath+File.separator+"fixflow-repository"+File.separator+"private"+File.separator+loginUserId),-1);
			iterationRead(new File(basePath+File.separator+"fixflow-repository"+File.separator+"shared"+File.separator+loginUserId),-2);
		}catch(Exception e){
		}
		 json += "]";
	    return json;
	}
	
	public static void clear(){
		 key = 0;json = "[{id:-1,pId:0,name:'private',type:'dir',isParent:true},{id:-2,pId:0,name:'shared',type:'dir',isParent:true}";subJson="";
	}
	
	/**
	 * 迭代目录层级提取json数据
	 * @param file 跟目录
	 * @param pid 树型结构的父节点
	 */
	private static void iterationRead(File file,int pid) throws Exception{
		File[] FList = file.listFiles();
		for (int i = 0; i < FList.length; i++){
			key ++;
			if (FList[i].isDirectory()==true){
				json += ",{id:"+key+",pId:"+pid+",name:'"+FList[i].getName()+"',type:'dir',isParent:true}";
				iterationRead(FList[i],key);
			}else{
				//json += ",{id:"+key+",pId:"+pid+",name:'"+FList[i].getName()+"',type:'file'}";
			}
		}
	}
	 
	/**
	 * 创建文件或文件夹
	 * @param fileLeveStr文件层级字符串
	 *                 如：user/andming/...
	 * @param basePath
	 *               webcontent目录
	 * @throws Exception
	 */
	public static void createFileOrDirectory(String fileLeveStr, String basePath) throws Exception{
		File file = new File( basePath+File.separator+"fixflow-repository"+File.separator+fileLeveStr);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	/**
	 * 读取我文件夹下的所有子文件信息
	 * @param fileLeveStr文件层级字符串
	 *                 如：user/andming/...
	 * @param basePath
	 *               webcontent目录
	 * @throws Exception
	 */
	public static String readSubFileAndDirectory(String fileLeveStr, String basePath) throws Exception{
		File file = new File( basePath+File.separator+"fixflow-repository"+File.separator+fileLeveStr);
		File[] FList = file.listFiles();
		for (int i = 0; i < FList.length; i++){
			if (FList[i].isDirectory()==true){
				subJson += ",{name:'"+FList[i].getName()+"',type:'dir'}";
				iterationRead(FList[i],key);
			}else{
				subJson += ",{name:'"+FList[i].getName()+"',type:'file'}";
			}
		}
 
		if(subJson.equals("")){
			return "[]";
		}
 
		return "["+subJson.substring(1)+"]";
	}
	
	 /** 
     * 重命名文件或文件夹 
     *  
     * @param resFilePath 
     *            源文件路径 
     * @param newFilePath 
     *            重命名 文件路径 
     *            
     * @param basePath 
     *            webcontent目录
     *            
     * @return 操作成功标识 
     */  
    public static boolean renameFile(String resFilePath, String newFilePath,String basePath) throws Exception{
        File resFile = new File(basePath+File.separator+"fixflow-repository"+File.separator+resFilePath);  
        File newFile = new File(basePath+File.separator+"fixflow-repository"+File.separator+newFilePath);  
        return resFile.renameTo(newFile);  
    }  
	
	
	public static void main(String[] args) {
		try {
			System.out.println(buildLevelJsonDataWithLoginPerson("1", "/Users/admin/Documents/java/founder/apache-tomcat-6.0.18/wtpwebapps/bpmcenter/"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
