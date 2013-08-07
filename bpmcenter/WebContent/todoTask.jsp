<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
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
  <a id="b2" target="_self" href="FlowCenter?action=getMyTask">待办任务</a>
  <a id="b3" target="_self" href="login.jsp">流程查询</a>
  <a id="b4" target="_self" href="login.jsp">归档任务</a>
  <a id="b5" target="_self" href="login.jsp">返回登陆</a>
</div>
<div style="margin-top:10px;">
<!-- 左 -->
	<div style="float:left;width:20%;">
	  <div>我的任务</div>
	  <div>代理任务</div>
	  <%
	  Map<String,Object> agentUsers = (Map<String,Object>)request.getAttribute("result");
	  if(agentUsers.get("agentUsers")!=null){ 
	  %>
	  <div>
	    <ul>
	    <c:forEach items="${result.agentUsers}" var="agentUsers" varStatus="index">
	      <li>${agentUsers.name}</li>
	    </c:forEach>
	    </ul>
	  </div>
	  <%}else{%>
	  <div><span style="color:gray;font-size:10px;">没有代理人</span></div>
	  <%} %>
	  <div>委托任务</div>
	  <%
	  Map<String,Object> agentToUsers = (Map<String,Object>)request.getAttribute("result");
	  if(agentToUsers.get("agentToUsers")!=null){ 
	  %>
	  <div>
	    <ul>
	    <c:forEach items="${result.agentToUsers}" var="agentToUsers" varStatus="index">
	      <li>${agentToUsers.name}</li>
	    </c:forEach>
	    </ul>
	  </div>
	  <%}else{%>
	  <div><span style="color:gray;font-size:10px;">没有委托人</span></div>
	  <%} %>
	</div>
<!-- 右-->
	<div style="float:right;width:79%;">
	  <div id="search">
	  </div>
	    任务主题：<input type="text"/>流程变量：<input type="text"/>单据号：<input type="text"/><br/>
	    到达时间：<input type="text"/>——<input type="text"/>发起人：<input type="text"/>
	  <div>
		<table>
		  <thead>
		    <th>优先级</th>
		    <th>流程名称</th>
		    <th>单据号</th>
		    <th>任务主题</th>
		    <th>发起人</th>
		    <th>发起时间</th>
		    <th>当前步骤</th>
		    <th>到达时间</th>
		  </thead>
		  <tbody>
		    <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		      <td>${dataList.name}</td>
		    </tr>
		    </c:forEach>
		  </tbody>
	    </table>
	  </div>
	</div>
</div>
</body>
<script>

</script>
</html>