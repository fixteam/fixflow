package com.founder.fix.fixflow.util;

import java.net.URLDecoder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring容器工具类
 *
 */
public class SpringConfigLoadHelper {
//	private static ApplicationContext _instance;
	private static Object object = new Object();
	private static ApplicationContext _wac =null;
	public static ApplicationContext context;
	static{
		String[] sources = {"applicationContext*.xml"};
		context = new ClassPathXmlApplicationContext(sources);
	}
//	private static final String path = "/applicationContext*.xml";

/*	static {
		String urlPath =

		URLDecoder.decode(SpringConfigLoadHelper.class.getResource("/")
				.getFile());
		urlPath = urlPath.substring(0, urlPath.indexOf("WEB-INF")
				+ "WEB-INF".length());
		urlPath += path;
		if (_instance == null)
			_instance = buildApplicationContext(urlPath);
	}*/

	/**
	 * 加载spring配置信息
	 * @param wac
	 */
	public static void buildApplicationContext(ApplicationContext wac) {
		if (_wac == null) {
			synchronized (object) {
				if (_wac == null) {
					_wac =  wac; 
				}
			}
		} 
	}
	
	/**
	 * 根据Class从容器中查询bean名称
	 * @param clazz
	 * @return bean名称
	 */
	public static String[] getBeanId(Class clazz){
		return getContext().getBeanNamesForType(clazz);
	}

	/**
	 * 获得spring容器
	 * @return spring容器
	 */
	public  ApplicationContext getApplicationContext() { 
		return _wac;
	}

	/**
	 * 根据名称从容器中获得实例
	 * @param name
	 * @return 实例
	 */
	public static Object getBean(String name) {
		if(_wac==null)
			return context.getBean(name);
		return _wac.getBean(name);
	}
	
	public static ApplicationContext getContext() {
		if(_wac==null)
			return context;
		return _wac;
	}
	
//	/**
//	 * 根据名称从WEB上下文中获得实例
//	 * @param name
//	 * @return 实例
//	 */
//	public static Object getSpringBean(String name) {
//		Object _Interface = null;
//		Object object = new Object();
//		if (_Interface == null) {
//			synchronized (object) {
//				if (_Interface == null) {
//					WebApplicationContext wac = WebApplicationContextUtils
//							.getRequiredWebApplicationContext(AppInfo
//									.getApplication());
//					SpringConfigLoadHelper.buildApplicationContext(wac);
//					_Interface = SpringConfigLoadHelper.getBean(name);
//				}
//			}
//		}
//		return _Interface;
//	}

	public static void main(String[] args) {
		String str = URLDecoder.decode(ClassLoader.getSystemClassLoader()
				.getResource("").getFile());
		System.out.println(str.substring(0, str.indexOf("WEB-INF")
				+ "WEB-INF".length()));
		System.out.println(getBean("au_loginAuthorityService"));
	}
}