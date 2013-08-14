<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>待办任务</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<style>
a{text-decoration: none;}
</style>
</head>

<body>
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>

<div style="margin-top:10px;">
<!-- 左 -->
	<div style="float:left;width:10%;">
	  <div id="myTask">我的任务</div>
	  <div>代理任务</div>
	  <%
	  Map<String,Object> result = (Map<String,Object>)request.getAttribute("result");
	  List agentUsersList = (List)result.get("agentUsers");
	  if(agentUsersList.size()!=0){ 
	  %>
	  <div>
	    <ul>
	    <c:forEach items="${result.agentUsers}" var="agentUsers" varStatus="index">
	      <li name="agentUsers">${agentUsers.name}</li>
	    </c:forEach>
	    </ul>
	  </div>
	  <%}else{%>
	  <div><span style="color:gray;font-size:10px;">没有代理人</span></div>
	  <%} %>
	  <div>委托任务</div>
	  <%
	  List agentToUsersList = (List)result.get("agentToUsers");
	  if(agentToUsersList.size()!=0){ 
	  %>
	  <div>
	    <ul>
	    <c:forEach items="${result.agentToUsers}" var="agentToUsers" varStatus="index">
	      <li name="agentToUsers">${agentToUsers.name}</li>
	    </c:forEach>
	    </ul>
	  </div>
	  <%}else{%>
	  <div><span style="color:gray;font-size:10px;">没有委托人</span></div>
	  <%} %>
	</div>
<!-- 右-->
	<div style="float:right;width:89%;">
<!-- 查 -->
	  <div id="search">
	  </div>
	    任务主题：<input type="text" value="<c:out value="${result.title}"/>"/>
	    流程变量：<input type="text"/>
	        单据号：<input type="text"/><br/>
	    到达时间：<input type="text" class="Wdate" onClick="WdatePicker()" value="<c:out value="${result.arrivalTimeS}"/>"/> 
	    	—— <input type="text" class="Wdate" onClick="WdatePicker()" value="<c:out value="${result.arrivalTimeE}"/>"/>
	        发起人：<input type="text" value="<c:out value="${result.initor}"/>"/>
	  <div>
<!-- 表 -->
		<table style="width:100%;">
		  <thead>
		    <th>bizKey</th>
		    <th>processDefinitionName</th>
		    <th>processDefinitionKey</th>
		    <th>startTime</th>
		    <th>nodeName</th>
		    <th>nodeId</th>
		    <th>操作</th>
		    <th>查看流程图</th>
		  </thead>
		  <tbody>
		    <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		      <td>${dataList.bizKey}</td>
		      <td>${dataList.processDefinitionName}</td>
		      <td>${dataList.processDefinitionKey}</td>
		      <td>${dataList.startTime}</td>
		      <td>${dataList.nodeName}</td>
		      <td>${dataList.nodeId}</td>
		      <td><button name="doTask" tii="${dataList.taskInstanceId}" pii="${dataList.processInstanceId}" bk="${dataList.bizKey}" pdk="${dataList.processDefinitionKey}">处理</button></td>
		      <td><button name="flowGraph" pii="${dataList.processInstanceId}" pdk="${dataList.processDefinitionKey}">流程图</button></td>
		    </tr>
		    </c:forEach>
		  </tbody>
	    </table>
<!-- 分页 -->	    
	    <div id="page">
	      <%
	      Map map = (Map)request.getAttribute("result");
	      Object pageIndex = (Object)map.get("pageIndex");
	      int pi = 1;
	      if(pageIndex==null){
	    	  pi=1;
	      }else{
	    	  pi = (Integer)pageIndex;
	      }
	      for(int m=0;m<pi;m++){ 
	      %>
	      <a name="page" href="javascript:void(0);"><%=m+1 %></a>
	      <%} %>
	    </div>
	  </div>
	</div>
</div>
</div>
<!-- 隐藏参数部分 -->
<input type="hidden" name="userId" value="<c:out value="${result.userId}"/>">
<input type="hidden" name="pageIndex" value="<c:out value="${result.pageIndex}"/>">
<input type="hidden" name="rowNum" value="<c:out value="${result.rowNum}"/>">
<input type="hidden" name="agentType" value="<c:out value="${result.agentType}"/>">
</body>
<script>
/*
 * "userId" 用户编号
 * "pdkey" 流程编号
 * "pageIndex" 第几页
 * "rowNum" 有几行
 * "agentUserId" 有几行
 * "agentType" 0我代理别人，1别人委托给我
 * "title" 查询主题
 * "processVeriy" 查询变量
 * "arrivalTimeS" 到达时间开始
 * "arrivalTimeE" 到达时间结束
 * "initor" 发起人
 * @param @return
 * "dataList" 数据列表
 * "pageNumber" 总行数
 * "agentUsers" 代理用户
 * "agentToUsers" 委托用户
 * "pageIndex" 第几页
 * "rowNum" 有几行
 */
$(function(){
  var agentType = $("input[name=agentType]").val();
  var userId = $("input[name=userId]").val();
  $("a[name=page]").click(function(){
    var pageNo = $(this).html();
    window.location.href = "FlowCenter?action=getMyTask&pageIndex="+pageNo+"&rowNum=15&agentType="+agentType+"&userId="+userId;
  });
  $("li[name=agentUsers]").click(function(){
    var userId = $(this).attr("userId");
    window.location.href = "FlowCenter?action=getMyTask&agentType=0&userId="+userId;
  });
  $("li[name=agentToUsers]").click(function(){
    var userId = $(this).attr("userId");
    window.location.href = "FlowCenter?action=getMyTask&agentType=1&userId="+userId;
  });
  $("button[name=flowGraph]").click(function(){
    var pdk = $(this).attr("pdk");
    var pii = $(this).attr("pii");
    var obj = {};
    window.showModalDialog("FlowCenter?action=getTaskDetailInfo&processDefinitionKey="+pdk+"&processInstanceId="+pii,obj,"dialogWidth=800px;dialogHeight=600px");
  });
  $("button[name=doTask]").click(function(){
    var tii = $(this).attr("tii");
    var pdk = $(this).attr("pdk");
    var pii = $(this).attr("pii");
    var bizKey = $(this).attr("bk");
    
    var obj = {};
    window.showModalDialog("FlowCenter?action=doTask&taskId="+tii+"&processInstanceId="+pii+"&bizKey="+bizKey+"&processDefinitionKey="+pdk,obj,"dialogWidth=800px;dialogHeight=600px");
  });
});
</script>
</html>