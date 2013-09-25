<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="head.jsp" flush="true"/>
</head>
<body>
<div class="popup">
<div style="height:40px;width:600px;margin-left:100px;"><img src="images/user-50.png">${result.user.userName}</div>
    <div class="info">
        <table width="660" border="0">
        <tr>
            <td colspan="2" style="text-align:left; padding-left:40px;">详细信息</td>
          </tr>
          <tr>
            <td width="250" rowspan=3><img src="${result.icon}"></td>
            <td style="text-align:left;">编号：${result.user.userId}</td>
          </tr>
          <tr>
            <td style="text-align:left;">姓名：${result.user.userName}</td>
          </tr>
          <tr>
            <td></td>
          </tr>
        </table>
        <table width="660" border="0">
         
          <tr>
            <td width="300">组编号</td>
            <td>组名称</td>
            <td>组类型</td>
          </tr>
		<c:forEach items="${result.groups}" var="row" varStatus="status">
			<tr>
				<td>${row.groupId}</td>
				<td>${row.groupName}</td>
				<td>${row.groupType}</td>
			</tr>
		</c:forEach>
        </table>
    </div>
</div>
</body>
</html>