/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author kenshin
 */
package com.founder.fix.fixflow.expand.internationalization;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

/**
 * fixflow默认的国际化功能核心文件
 * @author ych 2014.3.7 -- 重构
 *
 */
public class FixResourceCore{
	
	private static Logger log = LoggerFactory.getLogger(FixResourceCore.class);
	public static final String defaultLocal = "zh_CN";
	public static final String DOT_PROPERTIES = ".properties";
	private static String RESOURCE_PATH="";
	private static final Map<String,Map<String,Properties>> fixResource = new HashMap<String,Map<String,Properties>>();
	
	/**
	 * 设置当前线程副本中流程引擎所使用的国际化语言
	 * @param local
	 */
	public static void setNowLanguage(String local){
		Context.setLanguageType(local);
	}
	
	/**
	 * 获取当前线程副本中的国际化语言
	 * @return
	 */
	public static String getNowLanguage(){
		return Context.getLanguageType();
	}
	
	/**
	 * 获取指定资源文件中指定key的国际化值
	 * @param propertiesName 资源文件名（不带文件后缀名）
	 * @param key 
	 * @return
	 */
	public static String getResource(String propertiesName,String key){
		return getResourceByArray(propertiesName,key,null);
	}
	
	/**
	 * 获取指定资源文件中指定key带占位符的国际化值
	 * @param propertiesName 资源文件名（不带文件后缀名）
	 * @param key
	 * @param args 占位符的替换值
	 * @return
	 */
	public static String getResource(String propertiesName,String key,Object... args){
		return getResourceByArray(propertiesName,key,args);
	}
	
	/**
	 * 获取国际化值
	 * @param propertiesName 资源文件名（不带文件后缀名）
	 * @param key
	 * @param args 占位符的替换值
	 * @return
	 */
	private static String getResourceByArray(String propertiesName,String key,Object[] args){
		String result = "";
		if(fixResource!=null && StringUtil.isNotEmpty(key)){
			Properties props = getProperties(propertiesName);
			if(props!=null){
				result = props.getProperty(key);
				if(StringUtil.isNotEmpty(result)){
					try {
						result = new String(result.getBytes("ISO8859-1"), "UTF-8");
						result = MessageFormat.format(result, args);
					} catch (UnsupportedEncodingException e) {
						log.error("国际化:"+key+"取值时转码失败!", e);
					}
				}
			}
		}
		if(StringUtil.isEmpty(result))
			result = key;
		return result;
	}
	
	private static Properties getProperties(String propertiesName){
		Properties props = null;
		Map<String,Properties> map = fixResource.get(getNowLanguage());
		if(map!=null)
			props = map.get(propertiesName);
		return props;
	}
	
	/**
	 * 加载指定的国际化资源文件。properties(递归)
	 * @param file 资源文件名或文件夹名
	 * @param value 国际化信息map
	 */
	private static void loadResource(File file,Map<String,Properties> value){
		if(file.isDirectory()){
			for(File resourceFile : file.listFiles()){
				loadResource(resourceFile,value);
			}
		}
		else if(file.isFile() && file.getName().endsWith(DOT_PROPERTIES)){
			InputStream is = null;
			try{
				Properties props = new Properties();
	            is = new FileInputStream(file);
	            props.load(is);
	            is.close();
	            String propertiesKey = file.getName().substring(0,file.getName().lastIndexOf("."));
	            value.put(propertiesKey, props);
	            log.debug("加载国际化资源文件成功：文件名{}，国际化值个数：{}",file.getName(),props.size());
		    }catch(Exception e){
		    	log.error("国际化资源文件"+file.getName()+"加载失败",e);
		    }finally{
		    	if(is!=null){
		    		try {
						is.close();
					} catch (IOException e) {
						log.error("国际化资源文件"+file.getName()+"关闭失败",e);
					}
		    	}
		    }
		}
	}
	
	/**
	 * 加载指定语言的所有资源文件
	 * @param languageType 语言名（文件夹名）
	 * @param value 保存国际化信息的map
	 */
	private static void loadLanguageResource(String languageType,Map<String,Properties> value){
		String realPath = RESOURCE_PATH + File.separator + languageType;
		File resourceDir = new File(realPath);
		loadResource(resourceDir,value);
	}
	
	/**
	 * 获取资源文件夹下所有的语言 （遍历一级文件夹名）
	 * @return
	 */
	private static List<String> getLanguages(){
		List<String> result = new ArrayList<String>();
		File file = new File(RESOURCE_PATH);
		File[] files = file.listFiles();
		for(File tmp:files){
			if(tmp.isDirectory()){
				result.add(tmp.getName());
			}
		}
		return result;
	}

	/**
	 * 系统初始化，加载所有的资源文件（递归）
	 * @param resourcePath
	 */
	public static void systemInit(String resourcePath){
		fixResource.clear();
		RESOURCE_PATH = resourcePath;
		List<String> languages = getLanguages();
		for(String language:languages){
			fixResource.put(language, new HashMap<String,Properties>());
		}
		synchronized(fixResource){
			for(Entry<String,Map<String,Properties>> languageEntry:fixResource.entrySet()){
				loadLanguageResource(languageEntry.getKey(),languageEntry.getValue());
			}
		}
	}
	
	public static String getResourcePath(){
		return RESOURCE_PATH;
	}
}
