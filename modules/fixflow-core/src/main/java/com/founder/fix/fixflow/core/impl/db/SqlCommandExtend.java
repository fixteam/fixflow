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
package com.founder.fix.fixflow.core.impl.db;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.log4j.Logger;


/**
 * 数据库操作类
 * @author kenshin
 *
 */
public class SqlCommandExtend {

	// --------------------------------------------------------- Instance
	private static Logger logger = Logger.getLogger(SqlCommandExtend.class);
	// --------------------------------------------------------- Methods

	// 数据库连接对象
	private Connection con;
	// SQL语句对象
	private Statement stmt;
	// 带参数的Sql语句对象
	private PreparedStatement pstmt;
	// 记录集对象
	private ResultSet rs;


	/** ********************************************************************** */

	// 默认构造
	public SqlCommandExtend(Connection connection) {
		con=connection;
		
	}

	/** ****************************************************************************************** */
	/**
	 * **************************************** 数据库连接初始化
	 * ***********************************
	 */
	/** ****************************************************************************************** */





	/** ****************************************************************************************** */
	/**
	 * **************************************** 数据库操作方法
	 * ***********************************
	 */
	/** ****************************************************************************************** */

	/**
	 * 执行SQL语句操作(更新数据 无参数)
	 * 
	 * @param strSql
	 *            SQL语句
	 * @throws Exception
	 */
	public boolean executeUpdate(String strSql) throws SQLException {
		//getConnection();
		// getConnection(_DRIVER,_URL,_USER_NA,_PASSWORD);
		boolean flag = false;
		stmt = con.createStatement();
		logger.info("###############::执行SQL语句操作(更新数据 无参数):" + strSql);
		try {
			if (0 < stmt.executeUpdate(strSql)) {
				//close_DB_Object();
				flag = true;
				//con.commit();
			}
		} catch (SQLException ex) {
			logger
					.info("###############Error DBManager Line126::执行SQL语句操作(更新数据 无参数):"
							+ strSql + "失败!");
			flag = false;
			//con.rollback();
			throw ex;
		}
		return flag;

	}

	/**
	 * 执行SQL语句操作(更新数据 有参数)
	 * 
	 * @param strSql
	 *            sql指令
	 * @param prams
	 *            参数列表
	 * @return
	 * @throws SQLException
	 */
	public boolean executeUpdate(String strSql, HashMap<Integer, Object> prams)
			throws SQLException, ClassNotFoundException {
		//getConnection();
		// getConnection(_DRIVER,_URL,_USER_NA,_PASSWORD);
		boolean flag = false;
		try {
			pstmt = con.prepareStatement(strSql);
			setParamet(pstmt, prams);
			logger.info("###############::执行SQL语句操作(更新数据 有参数):" + strSql);

			if (0 < pstmt.executeUpdate()) {
				//close_DB_Object();
				flag = true;
				//con.commit();
			}
		} catch (SQLException ex) {
			logger
					.info("###############Error DBManager Line121::执行SQL语句操作(更新数据 无参数):"
							+ strSql + "失败!");
			flag = false;
			//con.rollback();
			throw ex;
		} catch (ClassNotFoundException ex) {
			logger
					.info("###############Error DBManager Line152::执行SQL语句操作(更新数据 无参数):"
							+ strSql + "失败! 参数设置类型错误!");
			//con.rollback();
			throw ex;
		}
		return flag;

	}

	/**
	 * 执行SQL语句操作(查询数据 无参数)
	 * 
	 * @param strSql
	 *            SQL语句
	 * @return 数组对象列表
	 * @throws Exception
	 */
	public ArrayList<HashMap<Object, Object>> executeSql(String strSql)
			throws Exception {
		//getConnection();
		// getConnection(_DRIVER,_URL,_USER_NA,_PASSWORD);
		stmt = con.createStatement();
		logger.info("###############::执行SQL语句操作(查询数据):" + strSql);
		rs = stmt.executeQuery(strSql);
		//con.commit();
		if (null != rs) {
			return convertResultSetToArrayList(rs);
		}
		//close_DB_Object();
		return null;
	}

	/**
	 * 执行SQL语句操作(查询数据 有参数)
	 * 
	 * @param strSql
	 *            SQL语句
	 * @param prams
	 *            参数列表
	 * @return 数组对象列表
	 * @throws Exception
	 */
	public ArrayList<HashMap<Object, Object>> executeSql(String strSql,
			HashMap<Integer, Object> prams) throws Exception {
		//getConnection();
		// getConnection(_DRIVER,_URL,_USER_NA,_PASSWORD);
		pstmt = con.prepareStatement(strSql);
		setParamet(pstmt, prams);
		logger.info("###############::执行SQL语句操作(查询数据):" + strSql);
		rs = pstmt.executeQuery();
		//con.commit();
		if (null != rs) {
			return convertResultSetToArrayList(rs);
		}
		return null;
	}

	/**
	 * 执行存储过程(查询数据 无参数)
	 * 
	 * @param procName
	 *            存储过程名称
	 * @return 数组列表对象
	 * @throws Exception
	 */
	public ArrayList<HashMap<Object, Object>> executeProcedureQuery(
			String procName) throws Exception {
		//getConnection();// 获取连接
		String callStr = "{call " + procName + "}";// 构造执行存储过程的sql指令
		CallableStatement cs = con.prepareCall(callStr);
		logger.info("###############::执行存储过程(查询数据):" + procName);
		rs = cs.executeQuery();
		//con.commit();
		cs.close();
		//close_DB_Object();
		return convertResultSetToArrayList(rs);
	}

	/**
	 * 执行存储过程(查询数据,带参数)返回结果集合
	 * 
	 * @param procName
	 *            存储过程名称
	 * @param parameters
	 *            参数对象数组
	 * @param al
	 *            数组列表对象
	 * @return 数组列表对象
	 * @throws Exception
	 */
	public ArrayList<HashMap<Object, Object>> executeProcedureQuery(
			String procName, Object[] parameters) throws Exception {
		int parameterPoint = 0;
		// 获取存储过程信息列表集合
		ArrayList<HashMap<Object, Object>> procedureInfo = getProcedureInfo(procName);
		// 获取存储过程的完全名称
		String procedureCallName = getProcedureCallName(procName,parameters.length);
		// 获取连接对象
		//getConnection();
		// 初始化 存储过程 执行对象
		CallableStatement cs = con.prepareCall(procedureCallName);
		// 参数下标变量
		int index = 0;
		// 获取 存储过程信息列表集合的 迭代器 对象
		Iterator<HashMap<Object, Object>> iter = procedureInfo.iterator();
		// 遍历存储过程信息列表集合
		while (iter.hasNext()) {
			HashMap<Object, Object> hm = iter.next();

			parameterPoint++;
			// 如果参数是输入参数 way = 0
			if (hm.get("WAY").equals("0")) {
				// 设置参数到cs
				cs.setObject(parameterPoint, parameters[index]);
				// 参数下标+1
				index++;
			}
		}
		// 释放这个对象,做为第二次使用
		procedureInfo = null;
		logger.info("###############::执行存储过程(查询数据):::::" + procedureCallName);
		rs = cs.executeQuery();
		//con.commit();
		procedureInfo = convertResultSetToArrayList(rs);
		cs.close();
		//close_DB_Object();
		return procedureInfo;

	}

	/**
	 * 执行存储过程(更新，查询数据[简单查询、非纪录集]，返回输出参数[非纪录集])
	 * 
	 * @param procName
	 *            存储过程名称
	 * @param parameters
	 *            参数对象数组
	 * @param os
	 *            输出参数对象数组
	 * @return 输出参数对象数组
	 * @throws Exception
	 */
	public Object[] executeProcedureUpdate(String procName, Object[] parameters)
			throws Exception {
		logger.info("------------------------------------------------------------------------------------------------------");
		logger.info(" Run --> executeProcedureUpdate ##############   正在执行 存储过程: " + procName +"   ##############");
		CallableStatement cs = null;
		Object []returnVal = null;
		try {
		// 获取 存储过程 调用全名
		String fullPCallName = getProcedureCallName(procName,parameters.length);
		logger.info(" Run --> executeProcedureUpdate #   存储过程命令: " + fullPCallName +"   #");
		//获取存储过程参数信息
		ArrayList<HashMap<Object, Object>> p_Call_Info_List = getProcedureInfo(procName);
		//获取连接
		//getConnection();
		//创建 存储过程 执行对象
		cs = con.prepareCall(fullPCallName);
		//数组下标
		int index = 1;
		//输出参数下标 纪录
        ArrayList<Integer> outPutIndexList = new ArrayList<Integer>();
        logger.info(" Run --> executeProcedureUpdate #   参数个数是: " + parameters.length +"   #");
		for(HashMap<Object,Object> tempHash:p_Call_Info_List)
		{
			if("0".equals(tempHash.get("WAY")))
		    {
				//设置输入参数
				cs.setObject(index, parameters[index-1]);
				logger.info(" Run --> executeProcedureUpdate #   输入 Input: 编号:" + index +" 值: "+parameters[index-1]+" 类型: "+parameters[index-1].getClass()+"   #");
			}
			else
			{
				//注册输出参数
				cs.registerOutParameter(index, getDataType(tempHash.get("TYPENAME").toString()));
				//纪录输出参数的下标
				outPutIndexList.add(index);
				logger.info(" Run --> executeProcedureUpdate #   输出 OutPut: 编号:" + index +" 值: "+parameters[index-1]+" 类型: "+parameters[index-1].getClass()+"   #");
			}
			index++;
		}
		logger.info(" Run --> executeProcedureUpdate #   参数设置完毕,正在执行中 ... :   #");
		
		//-------------------- 执行 -----------------
		if(!cs.execute())
		{
			returnVal = new Object[outPutIndexList.size()];
			logger.info(" Run --> executeProcedureUpdate #   执行成功! :   #");
			//取输 出参数的 返回值
			for(int i = 0 ;i<outPutIndexList.size();i++)
			{
				returnVal[i] = cs.getObject(outPutIndexList.get(i));
				logger.info(" Run --> executeProcedureUpdate #   返回值 "+(i+1)+" "+returnVal[i]+"   #");
			}
			//con.commit();//提交
		}
		} catch (Exception e) {
			logger.info(" Run --> executeProcedureUpdate #   执行失败!事务回滚中... :   #");
			//con.rollback();
			throw e;
		} 
		logger.info("------------------------------------------------------------------------------------------------------");
		return returnVal;
	}

	/** ****************************************************************************************** */
	/**
	 * ********************************* 小工具
	 * ************************************************
	 */
	/** ****************************************************************************************** */




	/**
	 * 设置Sql 指令参数
	 * 
	 * @param p_stmt
	 *            PreparedStatement
	 * @param pramets
	 *            HashMap
	 */
	private PreparedStatement setParamet(PreparedStatement p_stmt,
			HashMap<Integer, Object> pramets) throws ClassNotFoundException,
			SQLException {
		// 如果参数为空
		if (null != pramets) {
			// 如果参数个数为0
			if (0 <= pramets.size()) {
				for (int i = 1; i <= pramets.size(); i++) {
					try {
						// 字符类型 String
						if (pramets.get(i).getClass() == Class
								.forName("java.lang.String")) {
							p_stmt.setString(i, pramets.get(i).toString());
						}
						// 日期类型 Date
						if (pramets.get(i).getClass() == Class
								.forName("java.sql.Date")) {
							p_stmt.setDate(i, java.sql.Date.valueOf(pramets
									.get(i).toString()));
						}
						// 布尔类型 Boolean
						if (pramets.get(i).getClass() == Class
								.forName("java.lang.Boolean")) {
							p_stmt.setBoolean(i, (Boolean) (pramets.get(i)));
						}
						// 整型 int
						if (pramets.get(i).getClass() == Class
								.forName("java.lang.Integer")) {
							p_stmt.setInt(i, (Integer) pramets.get(i));
						}
						// 浮点 float
						if (pramets.get(i).getClass() == Class
								.forName("java.lang.Float")) {
							p_stmt.setFloat(i, (Float) pramets.get(i));
						}
						// 双精度型 double
						if (pramets.get(i).getClass() == Class
								.forName("java.lang.Double")) {
							p_stmt.setDouble(i, (Double) pramets.get(i));
						}

					} catch (ClassNotFoundException ex) {
						throw ex;
					} catch (SQLException ex) {
						throw ex;
					}
				}
			}
		}
		return p_stmt;
	}

	/**
	 * 转换记录集对象为数组列表对象
	 * 
	 * @param rs
	 *            纪录集合对象
	 * @return 数组列表对象
	 * @throws Exception
	 */
	private ArrayList<HashMap<Object, Object>> convertResultSetToArrayList(
			ResultSet rs) throws Exception {
		logger.info("###############::转换记录集对象为数组列表对象");
		// 获取rs 集合信息对象
		ResultSetMetaData rsmd = rs.getMetaData();
		// 创建数组列表集合对象
		ArrayList<HashMap<Object, Object>> tempList = new ArrayList<HashMap<Object, Object>>();
		HashMap<Object, Object> tempHash = null;
		// 填充数组列表集合
		while (rs.next()) {
			// 创建键值对集合对象
			tempHash = new HashMap<Object, Object>();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				// 遍历每列数据，以键值形式存在对象tempHash中
				tempHash.put(rsmd.getColumnName(i + 1).toUpperCase(), rs
						.getString(rsmd.getColumnName(i + 1)));
			}
			// 第一个键值对，存储在tempList列表集合对象中
			tempList.add(tempHash);
		}
		//close_DB_Object();// 关闭相关链接
		return tempList;// 返回填充完毕的数组列表集合对象
	}

	/**
	 * 从数据库得到存储过程信息
	 * 
	 * @param procName
	 *            存储过程名称
	 * @return 数组列表对象
	 * @throws Exception
	 */
	private ArrayList<HashMap<Object, Object>> getProcedureInfo(String procName)
			throws Exception {
		return this.executeSql("select Syscolumns.isoutparam as Way,systypes.name as TypeName from sysobjects,syscolumns,systypes where systypes.xtype=syscolumns.xtype and syscolumns.id=sysobjects.id and sysobjects.name='"
				+ procName + "' order by Syscolumns.isoutparam");
	}

	/**
	 * 从数据库得到存储过程参数个数
	 * 
	 * @param procName
	 *            存储过程名称
	 * @return 数组列表对象
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private int getParametersCount(String procName) throws Exception {
		int returnVal = 0;
		for (HashMap<Object, Object> tempHas : this
				.executeSql("select count(*) as RowsCount from sysobjects,syscolumns,systypes where systypes.xtype=syscolumns.xtype and syscolumns.id=sysobjects.id and sysobjects.name='"
						+ procName + "'")) {
			returnVal = Integer.parseInt(tempHas.get("ROWSCOUNT").toString());
		}
		return returnVal;
	}

	/**
	 * 得到调用存储过程的全名
	 * 
	 * @param procName
	 *            存储过程名称
	 * @return 调用存储过程的全名
	 * @throws Exception
	 */
	private String getProcedureCallName(String procName, int prametCount)
			throws Exception {
		String procedureCallName = "{call " + procName;
		for (int i = 0; i < prametCount; i++) {
			if (0 == i) {
				procedureCallName = procedureCallName + "(?";
			}
			if (0 != i) {
				procedureCallName = procedureCallName + ",?";
			}
		}
		procedureCallName = procedureCallName + ")}";
		return procedureCallName;
	}

	/**
	 * 得到数据类型的整型值
	 * 
	 * @param typeName
	 *            类型名称
	 * @return 数据类型的整型值
	 */
	private int getDataType(String typeName) {
		if (typeName.equals("varchar"))
			return Types.VARCHAR;
		if (typeName.equals("int"))
			return Types.INTEGER;
		if (typeName.equals("bit"))
			return Types.BIT;
		if (typeName.equals("float"))
			return Types.FLOAT;
		return 0;
	}

	
}

