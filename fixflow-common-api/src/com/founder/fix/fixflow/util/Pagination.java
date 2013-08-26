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
package com.founder.fix.fixflow.util;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Pagination
 * @Description: 分页类
 * @author shao
 *
 */
public class Pagination<T> implements Serializable {
	private static final long serialVersionUID = -4263336128482001626L;
	
	// 分页区页码最大长度
	public final static int MAX_PAGE_HTML_LENGTH = 8;
	// 首部和尾部连续页码的长度
	public final static int PRE_LAST_PAGE_LENGTH = 5;
	
	private int total; // 总记录数
	private int pageIndex; // 当前页, 从1开始计数
	private int pageSize; // 页大小
	private List<T> items; // 页数据
	private int startRow; // 起始行, 从1开始计数
	private int endRow; // 结束行, 从1开始计数
	private int pageCount;// 总页数
	
	private String baseUrl;
	
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public Pagination() {
	}
	public Pagination(Pagination<T> page) {
		this.pageIndex = page.getPageIndex();
		this.pageSize = page.pageSize;
		this.startRow = pageSize * (pageIndex - 1) + 1;
		this.endRow = this.startRow + pageSize - 1;
	}
	public Pagination(int pageIndex, int pageSize) {
		this.pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		this.pageSize = pageSize;
		this.startRow = pageSize * (this.pageIndex - 1) + 1;
		this.endRow = this.startRow + this.pageSize - 1;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
		if (total < pageSize) {
			this.pageCount = 1;
		} else {
			if (total % pageSize == 0) {
				this.pageCount = total / pageSize;
			} else {
				this.pageCount = total / pageSize + 1;
			}
		}
		if (pageIndex > pageCount) {
			this.pageIndex = this.pageCount;
			startRow = pageSize * (pageIndex - 1) + 1;
			endRow = startRow + pageSize - 1;
		}
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	/**
	 * 起始行位置, 从1开始计数
	 * 
	 * @return
	 */
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	/**
	 * 结束行位置, 从1开始计数
	 * 
	 * @return
	 */
	public int getEndRow() {
		if(endRow>getTotal()){
			return getTotal();
		}
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	/**
	 * 头部创建
	 * 
	 * @param length
	 * @param pagination
	 * @return
	 */
	public String createHeader(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= length; i++) {
			if (i == this.getPageIndex()) {
				sb.append(createPageIndex(i, true));
			} else {
				sb.append(createPageIndex(i, false));
			}
		}
		return sb.toString();
	}
	/**
	 * 中部创建
	 * 
	 * @param length
	 * @param pagination
	 * @return
	 */
	public String createMiddle(int length) {
		StringBuilder sb = new StringBuilder();
		if (length == 0) {// 创建点...
			sb.append(createPoint());
		} else {
			sb.append(createPoint());
			for (int i = this.getPageIndex() - 1; i <= this
					.getPageIndex() + 1; i++) {
				if (i == this.getPageIndex()) {
					sb.append(createPageIndex(i, true));
				} else {
					sb.append(createPageIndex(i, false));
				}
			}
			sb.append(createPoint());
		}
		return sb.toString();
	}
	/**
	 * 尾部创建
	 * 
	 * @param pagination
	 * @return
	 */
	public String createFooter() {
		StringBuilder sb = new StringBuilder();
		if (this.getPageIndex() <= 5
				|| this.getPageIndex() + 4 < this.getPageCount()) {
			for (int i = this.getPageCount() - 1; i <= this
					.getPageCount(); i++) {
				sb.append(createPageIndex(i, false));
			}
		} else {
			for (int i = this.getPageIndex() - 1; i <= this
					.getPageCount(); i++) {
				if (i == this.getPageIndex()) {
					sb.append(createPageIndex(i, true));
				} else {
					sb.append(createPageIndex(i, false));
				}
			}
		}
		return sb.toString();
	}
	public String createPrePage(int pageIndex, boolean distable) {
		StringBuilder sb = new StringBuilder();
		if (distable) {
			sb.append("<li>上一页</li>");
		} else {
			sb.append("<li><a href='#' onclick='toIndex(\""+pageIndex+"\")'>上一页</a></li>");
		}
		return sb.toString();
	}
	public String createNextPage(int pageIndex, boolean distable) {
		StringBuilder sb = new StringBuilder();
		if (distable) {
			sb.append("<li>下一页 </li>");
		} else {
			sb.append("<li><a href='#' onclick='toIndex(\""+pageIndex+"\")'>下一页 </a></li>");
		}
		return sb.toString();
	}
	public String createPageIndex(int pageIndex, boolean cur) {
		StringBuilder sb = new StringBuilder();
		if (!cur) {
			sb.append("<li><a onclick='toIndex(\""+pageIndex+"\")' href='#'>");
			sb.append(pageIndex);
			sb.append("</a></li>");
		} else {
			sb.append("<li><a class=\"select\">" + pageIndex + "</a></li>");
		}
		return sb.toString();
	}
	public String createPoint() {
		return " <li>...</li>";
	}
	public String createPageInfo() {
		return "<li>&emsp;共<span class=\"orange\">" + this.getTotal()
				+ "</span>条</li>" +
				"<li>&nbsp;共<span class=\"orange\">"+ this.getPageCount() 
				+ "</span>页</li>";
	}
	
	/**
	 * 返回URL参数首字符
	 * @return
	 */
	public String getUrlStartChar(){
		if(baseUrl.indexOf("?")>=0){
			return "&";
		}else{
			return "?";
		}
	}
}