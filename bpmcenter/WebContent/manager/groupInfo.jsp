<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组信息</title>
<jsp:include page="head.jsp" flush="true"/>
</head>
<body>
<div class="popup">
<div style="height:40px;width:600px;margin-left:100px;"><img src="images/user-50.png">${result.group.groupName}</div>
    <div class="info">
        <table width="660" border="0">
        <tr>
            <td style="text-align:left; padding-left:40px;">详细信息</td>
          </tr>
          <tr>
            <td style="text-align:left;">组编号：${result.group.groupId}</td>
          </tr>
          <tr>
            <td style="text-align:left;">组名称：${result.group.groupName}</td>
          </tr>
          <tr>
            <td style="text-align:left;">组类别：${result.group.groupType}</td>
          </tr>
        </table>
        <table width="660" border="0">
          <tr>
            <td width="300">用户编号</td>
            <td>用户名称</td>
          </tr>
		<c:forEach items="${result.users}" var="row" varStatus="status">
			<tr>
				<td>${row.userId}</td>
				<td>${row.userName}</td>
			</tr>
		</c:forEach>
        </table>
    </div>
</div>
</body>
</html>