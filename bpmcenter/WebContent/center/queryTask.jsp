<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程查询</title>
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
	  <a id="myTask" style="display:block;">全部流程</a>
	  <a name="getInitorTask" target="_self" href="FlowCenter?action=getInitorTask" style="display:block;">我发起的流程</a>
	  <a name="getParticipantsTask" target="_self" href="FlowCenter?action=getParticipantsTask" style="display:block;">我参与的流程</a>
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
		    <th>definitionId</th>
		    <th>processLocation</th>
		    <th>startAuthor</th>
		    <th>updateTime</th>
		    <th>subject</th>
		  </thead>
		  <tbody>
		    <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		      <td>${dataList.BIZ_KEY}</td>
		      <td>${dataList.definitionId}</td>
		      <td>${dataList.processLocation}</td>
		      <td>${dataList.startAuthor}</td>
		      <td>${dataList.updateTime}</td>
		      <td>${dataList.subject}</td>
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
<input type="hidden" name="type" value="<c:out value="${result.action}"/>">
</body>
<script>
$(function(){
  var type = $("input[name=type]").val();
  var userId = $("input[name=userId]").val();
  $("a[name=page]").click(function(){
    var pageNo = $(this).html();
    window.location.href = "FlowCenter?action="+type+"&pageIndex="+pageNo+"&rowNum=15&userId="+userId;
  });
});
</script>
</html>