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
		window.showModalDialog("FlowCenter?action=getUserIcon",obj,"dialogWidth=800px;dialogHeight=600px");
	}
</script>
<body>
<div class="userinfo">
	<div class="head-info">
        <img src="${result.icon}" onerror="imgNotFound('${pageContext.request.contextPath}');"/>
        <div class="name">
        	<h1>${result.user.userName}</h1>
            <h5></h5>
            <div class="btn-normal"><c:if test="${result.isUpdate=='true'}"><a href="#" onclick="openUserIcon();">更换头像</a></c:if></div>
        </div>
    </div>
    <h2>详细资料<a href="#"></a></h2>
    <table>
      <tr>
        <td class="title">姓名：</td>
        <td>${result.user.userName}</td>
      </tr>
      <tr>
        <td class="title">职位：</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td class="title">权限：</td>
        <td></td>
      </tr>
      <tr>
        <td class="title">生日：</td>
        <td></td>
      </tr>
      <tr>
        <td class="title">所在地：</td>
        <td></td>
      </tr>
    </table>
    <h2>所属组<a href="#"></a></h2>
    <table>
     	<tr>
	        <td class="title">组类别</td>
	        <td>组编号</td>
	        <td>组名称</td>
	      </tr>
      <c:forEach items="${result.groups}" var="dataList" varStatus="index">
	      <tr>
	        <td class="title">${dataList.groupType}</td>
	        <td>${dataList.groupId}</td>
	        <td>${dataList.groupName}</td>
	      </tr>
      </c:forEach>
    </table>

</div>
</body>
</html>
    