<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../center/head.jsp" flush="true"/>
</head>
<body>
<div style="padding:8px;">
<div id="search" class="search">
	<td><div class="btn-normal"><a href="#" id="ok">确定</a></div></td>
</div>
<div class="content">
	<table id="dataList" width="100%" class="fix-table">
		<thead>
			<th>任务名称</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>处理者</th>
		</thead>
		<tbody>
			<c:forEach items="${result.dataList}" var="list" varStatus="index">
				<tr data-rowData="${list}">
					<td>${list.taskName}</td>
					<td class="time"><fmt:formatDate value="${list.startTime}" type="both"/></td>
					<td class="time"><fmt:formatDate value="${list.endTime}" type="both"/></td>
					<td>${list.assigneeUserName}</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>
</body>
<script>
$(function(){
	$("table#dataList tr").click(function(){
		$("table#dataList tr.selected").removeClass("selected");
		$(this).addClass("selected");
	});
	$("#ok").click(function(){
		var rv = [];
		$("table#dataList tr.selected").each(function(index){
			var r = "{";
			var rowData = $(this).attr("data-rowData");
			rowData = rowData.substring(1,rowData.length-1);
			var rowDatas = rowData.split(",");
			for(var i=0;i<rowDatas.length;i++){
				var d = rowDatas[i].split("=");
				r+=d[0];
				r+=":\""+d[1]+"\","
			};
			r = r.substring(0,r.length-1);
			r+="}"
			eval("var j = " + r)
			rv[index] = j;
		});
		window.opener.rv = rv;
		window.close();
	});
})
</script>
</html>