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
            <td width="300">编号：${result.job.jobKey}</td>
            <td></td>
          </tr>
        </table>
        <table width="660" border="0">
          <tr>
            <td width="300">编号</td>
            <td>下次执行时间</td>
          </tr>
		<c:forEach items="${result.dataList}" var="row" varStatus="status">
			<tr>
				<td>${row.groupName}</td>
				<td>${row.nextFireTime}</td>
			</tr>
		</c:forEach>
        </table>
    </div>
</div>
</body>
</html>