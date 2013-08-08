<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户信息</title>
</head>
<body>
	<form action="FlowCenter" enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<input type="hidden" name="action" value="updateUserIcon"/>
				<td><img src="${result.icon}"></img></td>
				<td><input type="file" name="icon"/></td>
			</tr>
			<tr>
				<td colspan="2">${result.user.userId}</td>
			</tr>
			<tr>
				<td colspan="2">${result.user.userName}</td>
			</tr>
			<tr>
				<td colspan="2">组</td>
			</tr>
			<tr>
				<td colspan="2"><table>
				<tr>
				<td>id</td>
				<td>name</td>
				</tr>
				<c:forEach items="${result.groups}" var="row" varStatus="status">
					<tr>
					<td>${row.groupId}</td>
					<td>${row.groupName}</td>
					</tr>
				</c:forEach>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input id="btnconfirm" type="submit"/></td>
			</tr>
		</table>
	</form>
</body>
</html>