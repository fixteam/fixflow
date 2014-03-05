package com.founder.fix.fixflow.expand.test;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		
		System.out.println(TimeUnit.DAYS);
		System.out.println(TimeUnit.HOURS);
				System.out.println(TimeUnit.MICROSECONDS);
						System.out.println(TimeUnit.MILLISECONDS);
								System.out.println(TimeUnit.MINUTES);
										System.out.println(TimeUnit.NANOSECONDS);
												System.out.println(TimeUnit.SECONDS);
										
	}
	
	private static String __REGEX_SIGNS = "\\$\\{[^}{]+\\}";

	public static String getExpressionAll(String inexp) {
		String str = null;
		String regex = __REGEX_SIGNS;
		Pattern regexExpType = Pattern.compile(regex);
		Matcher mType = regexExpType.matcher(inexp);
		String expType = inexp;
		StringBuffer sb = new StringBuffer();
		while (mType.find()) {
			expType = mType.group();
			String dist = expType.substring(2, expType.length() - 1); 
			
			
			// StringUtil.getString(getExpressionValue(dataView,expType));
			mType.appendReplacement(sb, dist);
		}
		mType.appendTail(sb);
		str = sb.toString();
		return str;
	}

}
