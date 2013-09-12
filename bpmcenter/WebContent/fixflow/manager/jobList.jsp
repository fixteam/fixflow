<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="head.jsp" flush="true"/>
<title>定时任务</title>
<script type="text/javascript">
$(function(){
  $("a[name=viewInfo]").click(function(){
  	var jobKeyGroup = $(this).attr("jobKeyGroup");
  	var jobKeyName = $(this).attr("jobKeyName");
	var obj = {};
	window.open("FlowManager?action=viewJobInfo&jobKeyName="+jobKeyName+"&jobKeyGroup="+jobKeyGroup);
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
<div style="margin-top:1px;" class="center-panel">
<!-- 左 -->
<div class="left" style="width:0px;">
</div>
<!-- 右-->
	<div class="right">
	  <!-- 查 -->
	   <div class="search">
        	<table>
              <tr>
                <td class="title-r" style="width:200px;">流程编号/实例编号/令牌编号：</td>
                <td style="width:220px;"><input type="text" id="text_3" name="queryId" class="fix-input" style="width:200px;" value="${result.queryId}"/></td>
                <td><div class="btn-normal"><a href="#" onclick="$('#subForm').submit();">查 找</a></div></td>
              </tr>
            </table>
        </div>
	  <div>
		<!-- 表 -->
		<table style="width:100%;" class="fix-table">
		  <thead>
		   <th width="2%"></th>
		    <th width="25%">流程编号</th>
		    <th>实例编号</th>
		    <th width="10%">节点编号</th>
		    <th>节点名称</th>
		     <th width="8%">任务类型</th>
		    <th width="8%">任务状态</th>
		    <th width="8%">操作</th>
		  </thead>
		  <tbody>
		   <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		     <td><input type="checkbox"/></td>
		     <td>${dataList.processKey}</td>
		     <td>${dataList.processInstanceId}</td>
		      <td>${dataList.nodeId}</td>
		       <td>${dataList.nodeName}</td>
		        <td>${dataList.jobType}</td>
		      <c:choose>
				    <c:when test="${dataList.isPaused == true}">
				    	<td>暂停中</td>
				        <td><a href="#" name="continue" jobKeyGroup="${dataList.jobKeyGroup}" jobKeyName="${dataList.jobKeyName}" >恢复</a>
				    </c:when>
				    <c:otherwise>
				    	<td>运行中</td>
				          <td><a href="#" name="suspend" jobKeyGroup="${dataList.jobKeyGroup}" jobKeyName="${dataList.jobKeyName}" >暂停</a>
				    </c:otherwise>
				</c:choose>
				<a name="viewInfo" jobKeyGroup="${dataList.jobKeyGroup}" jobKeyName="${dataList.jobKeyName}" href="#">查看Trigger</a></td>
		    </tr>
		    </c:forEach>
		  </tbody>
	    </table>
		<!-- 分页 -->	    
	   <jsp:include page="../common/page.jsp" flush="true"/>
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
