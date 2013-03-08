package com.founder.fix.fixflow.designer.util;

import java.util.UUID;


/**
 * <!-- 开始-用户-文档 -->
 * <em><b>创建唯一编号</b></em>
 * <br><br>
 * <!-- 结束-用户-文档 -->
 * 
 * <p>
 * <em><b>支持以下功能:</b></em>
 * <ul>
 *   <li>{@link com.founder.fix.fixflow.util.GuidUtil#CreateGuid <em>Guid</em>}</li>
 * </ul>
 * </p>
 * 
 * 
 * 
 * @version 1.0
 * @author kenshin Created: 2011-7-4 Location:中国-江苏-苏州-三山岛　Weather: 晴
 * @generated
 */
public class GuidUtil {
	
	/**
	 * <!-- 开始-用户-文档 -->
	 * 静态方法创建唯一编号，主要用于流程实例对象创建时的编号赋值.
	 * <!-- 结束-用户-文档 -->
	 * @return 返回唯一编号
	 */
	public static String CreateGuid()
	{
		//创建GUID 以后可以替换为其他方法
		return UUID.randomUUID().toString();
	}
	

}
