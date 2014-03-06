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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class FixResourceCore{
	
	private static Logger log = LoggerFactory.getLogger(FixResourceCore.class);
	private static final ThreadLocal<String> currentLanguage = new ThreadLocal<String>();
	public static final String defaultLocal = "defauld";
	public static final String DOT_PROPERTIES = ".properties";
	private static String RESOURCE_PATH="";
	private static final Map<String,Map<String,Properties>> fixResource = new HashMap<String,Map<String,Properties>>();
	
	public static void setNowLanguage(String local){
		currentLanguage.set(local);
	}
	
	public static String getNowLanguage(){
		String nowLanguage = currentLanguage.get();
		if(StringUtil.isEmpty(nowLanguage))
			nowLanguage = defaultLocal;
		return nowLanguage;
	}
	
	public static String getResource(String propertiesName,String key){
		return getResourceByArray(propertiesName,key,null);
	}
	
	public static String getResource(String propertiesName,String key,Object... args){
		return getResourceByArray(propertiesName,key,args);
	}
	
	public static String getResourceByArray(String propertiesName,String key,Object[] args){
		String result = "";
		if(fixResource!=null && StringUtil.isNotEmpty(key)){
			Properties props = getProperties(propertiesName);
			if(props!=null){
				result = props.getProperty(key);
				if(StringUtil.isNotEmpty(result)){
					try {
						result = new String(result.getBytes("ISO8859-1"), "UTF-8");
						MessageFormat.format(result, args);
						if(StringUtil.isEmpty(result))
							result = key;
					} catch (UnsupportedEncodingException e) {
						log.error("国际化:"+key+"取值时转码失败!", e);
					}
				}
			}
		}

		return result;
	}
	
	public static Properties getProperties(String domain){
		Properties props = null;
		Map<String,Properties> map = fixResource.get(getNowLanguage());
		if(map!=null)
			props = map.get(domain);
		return props;
	}
	
	public static String processLocalFromInfo(String local){
        if(StringUtil.isEmpty(local) || !fixResource.containsKey(local)){
        	local = defaultLocal;
        }
        setNowLanguage(local);
        return local;
	}
	
	public static void loadResource(File file,Map<String,Properties> value){
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
	
	public static void loadLanguageResource(String languageType,Map<String,Properties> value){
		String realPath = RESOURCE_PATH + File.separator + languageType;
		File resourceDir = new File(realPath);
		loadResource(resourceDir,value);
	}
	
	public static void write(Properties props,String path,String symbio) throws IOException{
		OutputStream fos = new FileOutputStream(path);
		props.store(fos, symbio);
	}
	
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

	public static void systemInit(String resourcePath){
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
}
