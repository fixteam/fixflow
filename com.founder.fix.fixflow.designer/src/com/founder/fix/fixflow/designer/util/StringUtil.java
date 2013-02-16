package com.founder.fix.fixflow.designer.util;



import java.util.Date;


public class StringUtil {
	
	/**
     * 获得对象描述字符串
     * @param obj
     * @return 字符串
     */
    public static String getString(Object obj){
    	if(obj!=null){
    		return obj.toString();
    	}else{
    		return null;
    	}
    }
    
    public static int getInt(Object obj)
    {
    	if(obj!=null){
    		return Integer.parseInt(obj.toString());
    	}else{
    		return 0;
    	}
    }
    
    public static Date getDate(Object obj)
    {
    	if(obj!=null){
    			return (Date)(obj);
  
    	}else{
    		return null;
    	}

    }
    
    /**
     * 判断字符串中有无内容
     * @param str 字符串
     * @return 结果
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    public static boolean getBoolean(Object booleanString)
    {
    	String booleanStringTemp=booleanString.toString();
    	return Boolean.parseBoolean(booleanStringTemp);
    }


}
