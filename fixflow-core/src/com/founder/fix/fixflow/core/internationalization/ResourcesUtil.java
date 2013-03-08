package com.founder.fix.fixflow.core.internationalization;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.founder.fix.fixflow.core.impl.Context;

public class ResourcesUtil {
	
	
	private static  String __REGEX_SIGNS = "\\$\\{[^}{]+\\}";

	public static  String getExpressionAll(String groupName,String inexp) {
		String str = null;
		String regex = __REGEX_SIGNS;
		Pattern regexExpType = Pattern.compile(regex);
		Matcher mType = regexExpType.matcher(inexp);
		String expType = inexp;
		StringBuffer sb = new StringBuffer();
		while (mType.find()) {
			expType = mType.group();
			String dist = expType.substring(2, expType.length() - 1); 
			
			dist=getResourcesValue(groupName,dist);
			// StringUtil.getString(getExpressionValue(dataView,expType));
			mType.appendReplacement(sb, dist);
		}
		mType.appendTail(sb);
		str = sb.toString();
		return str;
	}
	
	public static String getResourcesValue(String groupName,String key){
	
    		FixFlowResources fixFlowResources=Context.getProcessEngineConfiguration().getFixFlowResources();

        	String nameTemp=fixFlowResources.getResourceName(groupName, key);
            	
        	return nameTemp;
 
	}

}
