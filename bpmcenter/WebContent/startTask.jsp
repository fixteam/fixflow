<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发起流程</title>
<script type="text/javascript" src="js/jquery.js"></script>
<style>
a{text-decoration: none;}
</style>
</head>
<body>
<div>
  <a id="b1" target="_self" href="FlowCenter?action=getMyProcess">发起流程</a>
  <a id="b2" target="_self" href="FlowCenter?action=getMyTask&rowNum=15">待办任务</a>
  <a id="b3" target="_self" href="FlowCenter?action=getInitorTask&rowNum=15">流程查询</a>
  <a id="b4" target="_self" href="login.jsp">归档任务</a>
  <a id="b5" target="_self" href="FlowCenter?action=getUserInfo">修改我的信息</a>
  <a id="b6" target="_self" href="login.jsp">返回登陆</a>
</div>
<div style="margin-top:10px;">
<c:forEach items="${result}" var="row" varStatus="status">
<button id="btn_${status.index+1}" processDefinitionKey="${row.processDefinitionKey}">${row.processDefinitionName}</button>
</c:forEach>
</div>
</body>
<script>
$(function(){
  $("button[processDefinitionKey]").click(function(){
     var pdk = $(this).attr("processDefinitionKey");
    var obj = {};
    window.showModalDialog("FlowCenter?action=startOneTask&userId=<%=request.getAttribute("userId")%>&processDefinitionKey="+pdk,obj,"dialogWidth=800px;dialogHeight=600px");
  });
});
</script>
</html>