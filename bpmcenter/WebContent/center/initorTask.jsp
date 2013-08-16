<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
	<table>
		<tr>
			<td>流程名称</td>
			<td>任务主题</td>
			<td>发起人</td>
			<td>发起时间</td>
			<td>当前步骤</td>
			<td>查看状态</td>
		</tr>
		<c:forEach items="${result.dataList}" var="row" varStatus="status">
		<tr>
			<td>${row.processDefinitionKey}</td>
			<td>${row.subject}</td>
			<td>${row.initiator}<</td>
			<td>${row.startTime}<</td>
			<td>${row.subject}<</td>
			<td>${row.subject}<</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>