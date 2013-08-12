<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程查询</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<style>
a{text-decoration: none;}
</style>
</head>

<body>
<img src="FlowCenter?action=getFlowGraph&processDefinitionKey=${result.processDefinitionKey}"/>
<c:if test="${result.dataList!=null}">
<table>
	<tr>
		<td>
			执行人
		<td>
				<td>
			执行时间
		<td>
				<td>
			处理意见
		<td>
	<tr>
	<c:forEach items="${result.dataList}" var="row" varStatus="status">
		<tr>
			<td>
				${row.assignee}
			</td>
						<td>${row.endTime}
			</td>
						<td>${row.taskComment}
			</td>
		</tr>
	</c:forEach>
</table>
</c:if>
</body>
</html>