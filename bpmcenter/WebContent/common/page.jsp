<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.founder.fix.fixflow.util.Pagination" %>
<%
		Pagination pagination = null;
		Object tpagination = request.getAttribute("pageInfo");
		if (tpagination == null) {
			out.print("");
			return;
		}else{
			pagination = (Pagination)tpagination;
		}

		pagination.setBaseUrl((String)request.getAttribute("baseUrl"));
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"page\"><ul>");
		// 上一页
		if (pagination.getPageCount() > 1) {
			if (pagination.getPageIndex() == 1) {
				sb.append(pagination.createPrePage(0, true));
			} else {
				sb.append(pagination.createPrePage(pagination.getPageIndex() - 1, false));
			}
		}
		// 当总页数小于8时显示全部页码
		if (pagination.getPageCount() <= Pagination.MAX_PAGE_HTML_LENGTH) {
			for (int i = 1; i <= pagination.getPageCount(); i++) {
				if (i == pagination.getPageIndex()) {
					sb.append(pagination.createPageIndex(i, true));
				} else {
					sb.append(pagination.createPageIndex(i, false));
				}
			}
		} else {
			// 当页码小于等于5时
			if (pagination.getPageIndex() <= Pagination.PRE_LAST_PAGE_LENGTH) {
				sb.append(pagination.createHeader(pagination.getPageIndex() + 1));
				sb.append(pagination.createMiddle(0));
				sb.append(pagination.createFooter());
			} else if (pagination.getPageIndex() + 4 >= pagination
					.getPageCount()) {// 当页码为后5页时
				sb.append(pagination.createHeader(2));
				sb.append(pagination.createMiddle(0));
				sb.append(pagination.createFooter()); 
			} else {
				sb.append(pagination.createHeader(2));
				sb.append(pagination.createMiddle(3));
				sb.append(pagination.createFooter());
			}
		}
		// 下一页
		if (pagination.getPageCount() > 1) {
			if (pagination.getPageIndex() == pagination.getPageCount()) {
				sb.append(pagination.createNextPage(0, true));
			} else {
				sb.append(pagination.createNextPage(pagination.getPageIndex() + 1, false));
			}
		}
		sb.append(pagination.createPageInfo());
		sb.append("<li>每页   <a href='#' onclick='toSize(10)'>10</a> <a href='#' onclick='toSize(20)'>20</a> <a href='#' onclick='toSize(30)'>30</a> 条</li>");
		sb.append("</ul></div>");
		out.print(sb.toString());
%>
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageInfo.pageIndex}"/>
<input type="hidden" id="pageSize" name="pageSize" value="${pageInfo.pageSize}"/>
<script>
	function submit(index,size){
		$("#pageIndex").val(index);
		$("#pageSize").val(size);
		$("#subForm").submit();
	}
	
	function toIndex(index){
		submit(index,$("#pageSize").val());
	}
	
	function toSize(size){
		submit($("#pageIndex").val(),size);
	}
</script>