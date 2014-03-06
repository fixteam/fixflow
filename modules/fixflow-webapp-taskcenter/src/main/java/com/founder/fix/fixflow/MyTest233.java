package com.founder.fix.fixflow;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

public class MyTest233 {

	public static void main(String[] args) {
		System.out.println(MessageFormat.format("wodwo de ni hao {0},wo shi \''{1}\''", "ssssssssssss","dddddd"));
		System.out.println("Hello it`s " + new Date());  
        Locale list[] = DateFormat.getAvailableLocales();//返回所有语言环境的数组  
        System.out.println("========Systme available locales==========");  
        for (int i = 0; i < list.length; i++) {  
            System.out.println(list[i].toString() + "\t" + list[i].getDisplayName());//返回适合向用户显示的语言环境名  
        }  
        System.out.println("===========System property=========");  
        System.getProperties().list(System.out);//得到当前的系统属性。并将属性列表输出到控制台
	}
}
