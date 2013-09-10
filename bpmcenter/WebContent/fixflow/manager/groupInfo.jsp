<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<jsp:include page="../center/head.jsp" flush="true"/>
<link rel="stylesheet" type="text/css" href="fixflow/css/popup.css">
</head>
<script type="text/javascript">
	function openUserIcon() {
		var obj = {};
		window.open("FlowCenter?action=getUserIcon");
	}
</script>
<body>
<div class="userinfo">
	<div class="head-info">
        <div class="name">
        	<h1>${result.group.groupName}</h1>
        </div>
    </div>
    <h2>详细资料<a href="#"></a></h2>
    <table>
      <tr>
        <td class="title">组编号：</td>
        <td>${result.group.groupId}</td>
      </tr>
      <tr>
        <td class="title">组名称</td>
        <td>${result.group.groupName}</td>
      </tr>
      <tr>
        <td class="title">组类型</td>
        <td>${result.group.groupType}</td>
      </tr>
    </table>
    <h2>成员列表<a href="#"></a></h2>
    <table style="width:90%">
     	<tr>
	        <td width="20%" style="text-align:center">用户编号</td>
	        <td style="text-align:center">用户姓名</td>
	      </tr>
      <c:forEach items="${result.users}" var="dataList" varStatus="index">
	      <tr>
	        <td style="text-align:center">${dataList.userId}：</td>
	        <td style="text-align:center">${dataList.userName}</td>
	      </tr>
      </c:forEach>
    </table>

</div>
</body>
</html>
    