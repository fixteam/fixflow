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
package com.founder.fix.fixflow.core.impl.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class ListUtil {
	
	/**
	 * 
	  * strToList(将字符串按 "," 分割，组成list返回)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: strToList
	  * @Description: TODO
	  * @param @param str 如果str为""或者null，返回长度为0的list
	  * @param @return    设定文件
	  * @return List<String>    返回类型
	  * @throws
	 */
	public static List<String> strToList(String str){
		return strToList(str, ",");
	}
	
	/**
	 * 
	  * isEmpty(判断是否为空)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: isEmpty
	  * @Description: TODO
	  * @param @param list
	  * @param @return    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection list){
		return !isNotEmpty(list);
	}
	
	/**
	 * 
	  * isEmpty(判断是否为空)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: isEmpty
	  * @Description: TODO
	  * @param @param arr
	  * @param @return    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	public static boolean isEmpty(Object[] arr){
		return !isNotEmpty(arr);
	}
	
	/**
	 * 
	  * isNotEmpty(判断是否不为空)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: isNotEmpty
	  * @Description: TODO
	  * @param @param list
	  * @param @return    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Collection list){
		if(list!=null && list.size()>0)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	  * isNotEmpty(判断是否不为空)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: isNotEmpty
	  * @Description: TODO
	  * @param @param arr
	  * @param @return    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	public static boolean isNotEmpty(Object[] arr){
		if(arr!=null && arr.length>0)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	  * strToList(将字符串按 regex 分割，组成list返回)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: strToList
	  * @Description: TODO
	  * @param @param str 如果str为""或者null，返回长度为0的list
	  * @param @param regex 正则表达式
	  * @param @return    设定文件
	  * @return List<String>    返回类型
	  * @throws
	 */
	static List<String> strToList(String str, String regex){
		List<String> list = new ArrayList<String>();
		
		if(StringUtil.isEmpty(str)){
			return list;
		}
		
		String[] strArr = str.split(regex);
		
		for(String eachStr : strArr){
			list.add(eachStr);
		}
		
		return list;
	}
	

	
	/**
	 * 
	  * listToStr(将String的list以joinChar连接)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: listToStr
	  * @Description: TODO
	  * @param @param list
	  * @param @param joinChar
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String listToStr(List<String> list, String joinChar){
		if(isEmpty(list) || joinChar == null){
			return "";
		}
		
		String listStr = "";
		
		for(String item : list){
			listStr = listStr+item+joinChar;
		}
		
		listStr = listStr.substring(0, listStr.length()- joinChar.length());
		
		return listStr;
		
	}
}
