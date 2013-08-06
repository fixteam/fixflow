<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发起流程</title>
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
<div>
  <a id="b1" target="_self" href="FlowCenter?action=getMyProcess">发起流程</a>
  <a id="b1" target="_self" href="FlowCenter?action=getMyTask">待办任务</a>
  <button id="b3">流程查询</button>
  <button id="b4">归档任务</button>
</div>
<c:forEach items="${result}" var="row" varStatus="status">
${status.index+1}<button processDefinitionKey="${row.processDefinitionKey}">${row.processDefinitionName}</button>
</c:forEach>
</body>
<script>
$(function(){
  $("#b3").click(function(){
    window.location.href = "QueryTaskServlet";
  });
  $("#b4").click(function(){
    window.location.href = "DoneTaskServlet";
  });
  $("button[name=startTask]").click(function(){
    var processId = $(this).attr("processId");
    var obj = {};
    window.showModal("",obj,"dialogWidth=200px;dialogHeight=100px");
  });
});
</script>
</html>