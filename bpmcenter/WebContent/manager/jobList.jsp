<%@ page language="java" contentType="text/html; charset=utf-8"pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="head.jsp" flush="true"/>
<title>用户查询</title>
<script type="text/javascript">
$(function(){
  $("a[name=viewInfo]").click(function(){
  	var jobKeyGroup = $(this).attr("jobKeyGroup");
  	var jobKeyName = $(this).attr("jobKeyName");
    $("#jobKeyName").val(jobKeyName);
     $("#jobKeyGroup").val(jobKeyGroup);
    $("#action").val("viewJobInfo");
    $("#subForm").submit();
  });
  //暂停
   $("a[name=suspend]").click(function(){
  	var jobKeyGroup = $(this).attr("jobKeyGroup");
  	var jobKeyName = $(this).attr("jobKeyName");
    $("#jobKeyName").val(jobKeyName);
     $("#jobKeyGroup").val(jobKeyGroup);
    $("#action").val("suspendJob");
    $("#subForm").submit();
  });
  
   $("a[name=continue]").click(function(){
  	var jobKeyGroup = $(this).attr("jobKeyGroup");
  	var jobKeyName = $(this).attr("jobKeyName");
    $("#jobKeyName").val(jobKeyName);
     $("#jobKeyGroup").val(jobKeyGroup);
    $("#action").val("continueJob");
    $("#subForm").submit();
  });
});
</script>
</head>
<body>
<form action="FlowManager" id="subForm" method="post">
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>
<div style="margin-top:1px;">
<!-- 左 -->
<div class="left">
</div>
<!-- 右-->
	<div class="right">
	  <div>
		<!-- 表 -->
		<table style="width:100%;" class="fix-table">
		  <thead>
		   <th width="2%"></th>
		    <th>任务名称</th>
		    <th >组名</th>
		    <th width="5%">流程名称</th>
		    <th width="8%">流程编号</th>
		    <th width="">实例编号</th>
		    <th >节点</th>
		    <th width="5%">节点名称</th>
		    <th width="8%">任务类型</th>
		    <th width="8%">操作</th>
		  </thead>
		  <tbody>
		   <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		     <td><input type="checkbox"/></td>
		      <td><a name="viewInfo" jobKeyGroup="${dataList.jobKeyGroup}" jobKeyName="${dataList.jobKeyName}" href="#">${dataList.jobName}</a></td>
		      <td>${dataList.groupName}</td>
		      <td>${dataList.processName}</td>
		      <td>${dataList.processKey}</td>
		      <td>${dataList.processInstanceId}</td>
		      <td>${dataList.nodeId}</td>
		      <td>${dataList.nodeName}</td>
		      <td>${dataList.jobType}</td>
		      <td>
		      <c:choose>
				    <c:when test="${dataList.isPaused == true}">
				        <a href="#" name="continue" jobKeyGroup="${dataList.jobKeyGroup}" jobKeyName="${dataList.jobKeyName}" >恢复</a>
				    </c:when>
				    <c:otherwise>
				          <a href="#" name="suspend" jobKeyGroup="${dataList.jobKeyGroup}" jobKeyName="${dataList.jobKeyName}" >暂停</a>
				    </c:otherwise>
				</c:choose>
		    </tr>
		    </c:forEach>
		  </tbody>
	    </table>
		<!-- 分页 -->	    
	   <jsp:include page="page.jsp" flush="true"/>
	    </div>
	  </div>
	</div>
</div>
<!-- 隐藏参数部分 -->
<input type="hidden" name="jobKeyGroup" id="jobKeyGroup">
<input type="hidden" name="jobKeyName" id="jobKeyName">
<input type="hidden" name="action" id="action" value="getJobList"> 
</form>
</body>
</html>
