package com.founder.fix.fixflow.core.db.pagination;

/**
 * 数据库分页接口
 * @author kenshin
 *
 */
public interface Pagination {
	
	/**
	 * 获取分页的Sql语句
	 * @param sql 数据结果集SQl语句
	 * @param firstResult 开始行
	 * @param maxResults 结束行
	 * @param fields 需要显示的字段
	 * @param orderBy 分页排序
	 * @return
	 */
	String getPaginationSql(String sql, int firstResult, int maxResults,String fields,String orderBy);
	
	/**
	 * 获取判断空的数据库方言
	 * @param value 判断为空的值
	 * @return
	 */
	String getIsNullLocalismSql(String sql);
	
	/**
	 * 获取时间类型方言
	 * @param date
	 * @return
	 */
	String getDateSql();
	
	/**
	 * 获取当前时间类型方言
	 * @return
	 */
	String getCurrentDateSql();
	
	/**
	 * 获取转换类型
	 * @param sql
	 * @param type
	 * @return
	 */
	String getCastConvertSql(String sql, String type);
	
	
	
	
	/**
	 * 获取方言数据库语句 
	 * @param localismKey 方言的Key
	 * @param localismValue 方言带入的值
	 * @return
	 */
	String getLocalismSql(String localismKey,String localismValue);
	
	

}
