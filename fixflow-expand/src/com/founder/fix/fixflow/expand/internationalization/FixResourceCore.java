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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FixResourceCore{
	private static final ThreadLocal<String> currentLanguage = new ThreadLocal<String>();
	
	public static final String defaultLocal = "defauld";
	
	public static final String DOT_PROPERTIES = ".properties";
	
	public static final String GLOBAL = "_global";
	
	public static final String LOCALLANGUAGE="localLanguage";
	
	private static String RESOURCE_PATH="";
	
	private static String __REGEX_SIGNS = "\\{[^}{]+\\}";

	private static final Map<String,String> fixResourcePath = new HashMap<String,String>();
	
	private static final Map<String,Map<String,Properties>> fixResource = new HashMap<String,Map<String,Properties>>();
	
    private static String getString(Object obj){
    	if(obj!=null){
    		return obj.toString();
    	}else{
    		return "";
    	}
    }
    
    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    private static int convertToInt(String str) {
        int result = -1;
        
        str = str.trim();
        result = Integer.parseInt(str);

        return result;
    }
    
    ////////////////////////////////////
	
	public static String getInfoKey(String... domain){
		StringBuffer sb = new StringBuffer();
		for(int i =0;i<domain.length;i++){
			if(i!=0)
				sb.append(".");
			sb.append(domain[i]);
		}
		return sb.toString();
	}
	
	public static void setNowLanguage(String local){
		currentLanguage.set(local);
	}
	
	public static String getNowLanguage(){
		String str = currentLanguage.get();
		if(isEmpty(str))
			str = defaultLocal;
		return str;
	}
	
	public static String getResource(String domain,String key){
		return getResourceByArray(domain,key,null);
	}
	
	public static String getResource(String domain,String key,Object... args){
		return getResourceByArray(domain,key,args);
	}
	
	public static String getResourceByArray(String domain,String key,Object[] args){
		String str = getString(key);
		String result = str;
		if(fixResource!=null && isNotEmpty(str)){
			Properties props = getProperties(domain);
			if(props!=null){
				str = props.getProperty(str);
				if(isNotEmpty(str)){
					try {
						str = new String(str.getBytes("ISO8859-1"), "UTF-8");
						str = processInfo(str,args);
						if(isEmpty(str))
							result = key;
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
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
        if(isEmpty(local) || !fixResource.containsKey(local)){
        	local = defaultLocal;
        }
        setNowLanguage(local);
        
        return local;
	}
	
    public static String processInfo(String info,Object[] str){
    	String result= info;
    	if(hasSigns(info) && str!=null){
    		String regex = __REGEX_SIGNS;
    		Pattern regexExpType = Pattern.compile(regex);
    		Matcher mType = regexExpType.matcher(info);
    		String expType = null;
    		StringBuffer sb = new StringBuffer();
    		while(mType.find()){
    			expType = mType.group();
    			expType = expType.replaceAll("[{}]", "");
    			int stat = convertToInt(expType);
    			if(stat>-1 && stat<str.length){
    				mType.appendReplacement(sb, getString(str[stat]));
    			}
    		}
    		mType.appendTail(sb);
    		result = sb.toString();
    	}
    	return result;
    }
    
	public static boolean hasSigns(String str){
		String regex = __REGEX_SIGNS;
		Pattern regexExpType = Pattern.compile(regex);
		Matcher mType = regexExpType.matcher(str);
		return mType.find();
	}
	
	public static void putfixResourcePath(String key,String path){
		fixResourcePath.put(key, path);
	}
	
	public static Properties load(String path) throws IOException{
		Properties props = new Properties();
		InputStream is = null;
	    try{
            is = getInputStreamForFullPath(path);
            props.load(is);
            is.close();
	    }catch(Exception e){
//	    	e.printStackTrace();
	    }finally{
	    	if(is!=null)
	    		is.close();
	    }
	    return props;
	}
	public static InputStream getInputStreamForFullPath(String url) throws FileNotFoundException{
		InputStream in;
		File file = new File(url);
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw e;
		}

		return in;
	}
	
	public static void loadAllLanguage(String path,String domain) throws IOException{
		loadAllLanguageResource(path,domain);
	}
	
	public static void loadAllLanguageResource(String path,String domain) throws IOException{
		for(Entry<String,Map<String,Properties>> tmp:fixResource.entrySet()){
			String local = tmp.getKey();
			Map<String,Properties> pros = tmp.getValue();
			String tPath = RESOURCE_PATH+local+path;
			Properties pro = null;
			pro = load(tPath);
			pros.put(domain, pro);
		}
		
	}
	
	public static void write(Properties props,String path,String symbio) throws IOException{
		OutputStream fos = new FileOutputStream(path);
		props.store(fos, symbio);
	}
	
	private static List<String> getLanguages() throws IOException{
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

	public static void systemInit(String absPath) throws Exception {
		RESOURCE_PATH = absPath;
//		List<String> language = getLanguages();
//		
//		for(String path:language){
//			fixResource.put(path, new HashMap<String,Properties>());
//		}
		systemInitSpecial(null);
	}

	public static void systemInitSpecial(String args) throws Exception {
		List<String> language = getLanguages();
		
		for(String path:language){
			fixResource.put(path, new HashMap<String,Properties>());
		}
//		systemInitSpecial(null);
		
		synchronized(fixResource){
			for(Entry<String,String> key:fixResourcePath.entrySet()){
				loadAllLanguageResource(key.getValue(),key.getKey());
			}
		}
	}
	
}
