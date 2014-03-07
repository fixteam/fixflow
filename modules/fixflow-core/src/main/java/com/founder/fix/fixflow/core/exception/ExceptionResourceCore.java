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
 * @author ych
 */
package com.founder.fix.fixflow.core.exception;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

/**
 * 异常国际化信息处理类
 * 默认加载resourcePath下的  zh_CN、en_US等目录下的FixFlowExceptionResource.properties文件
 * 默认从线程副本读取当前session语言，取对应的国际化值
 * @author ych
 *
 */
public class ExceptionResourceCore {
	
	private static Logger log = LoggerFactory.getLogger(ExceptionResourceCore.class);
	public static final String EXCEPTION_PROPERTIES = "FixFlowExceptionResource.properties";
	private static String RESOURCE_PATH="";
	public static Map<String,Properties> exceptionResource = new HashMap<String,Properties>();
	
	/**
	 * 系统初始化，加载resourcePath下的  zh_CN、en_US等目录下的FixFlowExceptionResource.properties文件
	 * @param resourcePath 资源文件目录
	 */
	public static void system_init(String resourcePath){
		RESOURCE_PATH = resourcePath;
		List<String> languages = getLanguages();
		for(String language :languages){
			loadExceptionResource(language);
		}
	}
	
	/**
	 * 获取资源值，如果不存在则返回key本身
	 * @param key
	 * @return
	 */
	public static String getResourceValue(String key){
		return getResourceValue(key,new Object[]{});
	}
	
	/**
	 * 获取资源值，如果不存在则返回key本身（带占位符）
	 * @param key
	 * @param args
	 * @return
	 */
	public static String getResourceValue(String key,Object... args){
		String result = "";
		String localLanguage = Context.getLanguageType();
		if(exceptionResource!=null && StringUtil.isNotEmpty(key)){
			Properties props = exceptionResource.get(localLanguage);
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
	
	/**
	 * 获取国际化语言列表 加载resourcePath目录下的以及目录为语言名
	 * @return
	 */
	private static List<String> getLanguages(){
		List<String> resultList = new ArrayList<String>(); 
		URL url = ReflectUtil.getResource(RESOURCE_PATH);
		if(url == null){
			return resultList;
		}
		File file =new File(url.getPath());
		if(file.isDirectory()){
			for(File tmp :file.listFiles()){
				if(tmp.isDirectory()){
					resultList.add(tmp.getName());
				}
			}
		}
		return resultList;
	}
	
	/**
	 * 加载对应语言的国际化资源文件
	 * @param languageType
	 */
	private static void loadExceptionResource(String languageType){
		String path = RESOURCE_PATH + File.separator + languageType + File.separator +  EXCEPTION_PROPERTIES; 
		InputStream is = null;
		try{
			Properties props = new Properties();
            is = ReflectUtil.getResourceAsStream(path);
            props.load(is);
            is.close();
            exceptionResource.put(languageType, props);
            log.debug("加载异常国际化资源文件成功：文件名{}，国际化值个数：{}",languageType,props.size());
	    }catch(Exception e){
	    	log.error("国际化资源文件"+languageType+"加载失败",e);
	    }finally{
	    	if(is!=null){
	    		try {
					is.close();
				} catch (IOException e) {
					log.error("国际化资源文件"+languageType+"关闭失败",e);
				}
	    	}
	    }
	}
}
