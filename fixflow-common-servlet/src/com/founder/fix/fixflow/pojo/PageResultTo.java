/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author shao
 */
package com.founder.fix.fixflow.pojo;

import java.util.List;

/**
  * @ClassName: PageResultTo
  * @author shao
  *
  */
public class PageResultTo {
	/**
	 * @Fields dataList : 数据列表
	 */
	private List dataList;
	
	/**
	 * @Fields pageNumber : 数据总数
	 */
	private long pageNumber;

	/**
	  * getDataList
	
	  * @Title: getDataList
	  * @param @return    设定文件
	  * @return List    返回类型
	  * @throws
	  */
	public List getDataList() {
		return dataList;
	}

	/**
	  * setDataList
	
	  * @Title: setDataList
	  * @param @param dataList    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	/**
	  * getPageNumber
	
	  * @Title: getPageNumber
	  * @param @return    设定文件
	  * @return long    返回类型
	  * @throws
	  */
	public long getPageNumber() {
		return pageNumber;
	}

	/**
	  * setPageNumber
	
	  * @Title: setPageNumber
	  * @param @param pageNumber    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
}
