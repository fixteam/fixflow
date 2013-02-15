package com.founder.fix.fixflow.core.query;

import java.util.List;

/**
 * Describes basic methods for querying.
 * 
 * @author Frederik Heremans
 */
public interface Query<T extends Query<?, ?>, U extends Object> {

	/**
	 * 升序排列
	 * @return
	 */
	T asc();

	/**
	 * 降序排列
	 * @return
	 */
	T desc();

	/**
	 * 获取查询数据行数
	 * @return
	 */
	long count();

	/**
	 * 获取单条查询数据
	 * @return
	 */
	U singleResult();

	/**
	 * 获取查询数据列表
	 * 
	 * @return 分页列表
	 */
	List<U> list();

	/**
	 * 获取查询数据分页列表
	 * 
	 * @param firstResult 起始行数
	 * @param maxResults 结束行数
	 * @return 分页列表
	 */
	List<U> listPage(int firstResult, int maxResults);

	/**
	 * 获取查询数据分页列表
	 * 
	 * @param pageNum 页码数
	 * @param rowNum 显示行数
	 * @return 分页列表
	 */
	List<U> listPagination(int pageNum, int rowNum);
}
