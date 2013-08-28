<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../center/head.jsp" flush="true"/>
</head>
<body>
<form id="subForm" method="post" action="FlowCenter">
<div style="padding:8px;">
<div id="search" class="search">
 		<input type="hidden" name="action" value="selectUserList"/> 
		<table width="100%">
			<tr>
               	<td class="title-r">用户编号/姓名：</td>
               	<td style="width:180px"><input type="text" id="text_0" name="queryInfo" class="fix-input" style="width:160px;" value="${result.queryInfo}"/></td>
             	<td style="width:70px"><div class="btn-normal"><a href="#" onclick="$('#subForm').submit();">查 找<em class="arrow-small"></em></a></div></td>
             	<td><div class="btn-normal"><a href="#" id="ok">确定<em class="arrow-small"></em></a></div></td>
             </tr>
		</table>
</div>
<div class="content">
	<table id="dataList" width="100%" class="fix-table">
		<thead>
			<th>用户编号</th>
			<th>用户姓名</th>
		</thead>
		<tbody>
			<c:forEach items="${result.dataList}" var="list" varStatus="index">
				<tr data-rowData="${list}">
					<td>${list.USERID}</td>
					<td>${list.USERNAME}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
    <div id="page">
      <jsp:include page="page.jsp" flush="true"/>
    </div>
</div>
</div>
</form>
</body>
<script>
$(function(){
	var isMulti = Fix.Util.GetQueryString("isMulti");
	$("table#dataList tr").click(function(){
		if(isMulti=="false"){
			$("table#dataList tr.selected").removeClass("selected");
			$(this).addClass("selected");
		}else{
			if($(this).hasClass("selected")){
				$(this).removeClass("selected");
			}else{
				$(this).addClass("selected");
			}
		}
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
		window.returnValue=rv;
		window.close();
	});
})
</script>
</html>