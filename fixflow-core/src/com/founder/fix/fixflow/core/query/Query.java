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
