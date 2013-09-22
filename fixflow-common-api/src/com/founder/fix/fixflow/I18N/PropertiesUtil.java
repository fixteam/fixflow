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
 * @author shao
 */
package com.founder.fix.fixflow.I18N;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

/**
 * @ClassName: PropertyUtil
 * @Description: TODO
 * @author shao
 *
 */
@Scope("singleton")
@Lazy(false)
@Service
public class PropertiesUtil implements ServletContextAware {
	private static final String FILE="/resource/zh_cn/pageInfo.properties";
	
	private static Map<String,Properties> propsMap = new HashMap<String,Properties>();
	
	private static ServletContext servletContext;
	
	@PostConstruct
	public static void initProperties(){
		load(FILE);
		servletContext.setAttribute("appInfo", propsMap.get(FILE));
	}
	
	/**
	 * 从指定文件加载属性配置
	 * @param path 文件
	 * @return 属性配置
	 */
	public static Properties load(String path){
		Properties prop = null;
		try {
			InputStream is = null;
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
			prop = new Properties();
			
			prop.load(is);
			is.close();
			propsMap.put(path, prop);
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return prop;
	}
	
	/**
	 * 获得指定文件中的值
	 * @param path 文件
	 * @param key 键
	 * @return 值
	 */
	public static String getProperty(String path,String key) {
		Properties prop = propsMap.get(path);
		if(prop == null)
			prop = load(path);
		if(prop == null)
			return null;
		return prop.getProperty(key);
	}
	
	/**
	 * 设置指定文件中的值
	 * @param path 文件
	 * @param key 键
	 * @param value 值
	 */
	public static void setProperty(String path,String key, String value) {
		Properties prop = propsMap.get(path);
		if(prop == null)
			prop = load(path);
		if(prop != null){
			prop.setProperty(key, value);
			propsMap.put(path, prop);
		}
	}
	
	public static void setProperty(String key, String value){
		setProperty(FILE,key,value);
	}
	
	public static String getProperty(String key){
		return getProperty(FILE,key);
	}
	
	public static Properties getProperty(){
		return propsMap.get(FILE);
	}

	/*
	  * <p>Title: setServletContext</p>
	  * <p>Description: </p>
	  * @param arg0
	  * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	  */
	public void setServletContext(ServletContext arg0) {
		servletContext = arg0;
		
	}
	
}
