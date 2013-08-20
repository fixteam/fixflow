<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<jsp:include page="head.jsp" flush="true"/>
</head>
<body>
<div class="popup">
    <div class="info">
        <table width="660" border="0">
          <tr>
            <td width="300">编号：${result.user.userId}</td>
            <td>姓名：${result.user.userName}</td>
          </tr>
        </table>
        <table width="660" border="0">
          <tr>
            <td width="300">组编号</td>
            <td>组名称</td>
          </tr>
		<c:forEach items="${result.groups}" var="row" varStatus="status">
			<tr>
				<td>${row.groupId}</td>
				<td>${row.groupName}</td>
			</tr>
		</c:forEach>
        </table>
    </div>
</div>
</body>
</html>