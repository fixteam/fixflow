<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<jsp:include page="head.jsp" flush="true"/>
<script type="text/javascript">
$(function(){
  //暂停
   $("a[name=suspend]").click(function(){
  	var triggerKeyGroup = $(this).attr("triggerKeyGroup");
  	var triggerKeyName = $(this).attr("triggerKeyName");
    $("#triggerKeyName").val(triggerKeyName);
     $("#triggerKeyGroup").val(triggerKeyGroup);
    $("#action").val("suspendTrigger");
    $("#subForm").submit();
  });
  
   $("a[name=continue]").click(function(){
  	var triggerKeyGroup = $(this).attr("triggerKeyGroup");
  	var triggerKeyName = $(this).attr("triggerKeyName");
    $("#triggerKeyName").val(triggerKeyName);
     $("#triggerKeyGroup").val(triggerKeyGroup);
    $("#action").val("continueTrigger");
    $("#subForm").submit();
  });
});
</script>
</head>
<body>
<form action="FlowManager" method="post" id="subForm">
<div class="popup">
    <div class="info" style="width:900px;padding-top:30px;padding-left:20px;">
        <table width="900" border="0">
          <tr>
            <td width="50">编号：</td>
            <td width="200"><input type="text" name="jobKeyName" readOnly style="border:0px;width:100%" value="${result.job.jobKeyName}"></td>
             <td width="50">组名：</td>
            <td><input type="text" name="jobKeyGroup" readOnly style="border:0px;width:100%" value="${result.job.jobKeyGroup}"></td>
          </tr>
        </table>
        <table width="900" border="1">
          <tr>
            <td>开始时间</td>
            <td>结束时间</td>
            <td>上次触发时间</td>
            <td>下次触发时间</td>
            <td>最后触发时间</td>
            <td>状态</td>
            <td>操作</td>
          </tr>
		<c:forEach items="${result.dataList}" var="row" varStatus="status">
			<tr>
				<td class="time"><fmt:formatDate value="${row.startTime}" type="both"/></td>
				<td class="time"><fmt:formatDate value="${row.endTime}" type="both"/></td>
				<td class="time"><fmt:formatDate value="${row.lastFireTime}" type="both"/></td>
				<td class="time"><fmt:formatDate value="${row.nextFireTime}" type="both"/></td>
				<td class="time"><fmt:formatDate value="${row.finalFireTime}" type="both"/></td>
				<td>${row.triggerState}</td>
				<td>
					 <c:choose>
				    <c:when test="${row.isPaused == true}">
				        <a href="#" name="continue" triggerKeyGroup="${row.triggerGroup}" triggerKeyName="${row.triggerName}" >恢复</a>
				    </c:when>
				    <c:otherwise>
				          <a href="#" name="suspend" triggerKeyGroup="${row.triggerGroup}" triggerKeyName="${row.triggerName}" >暂停</a>
				    </c:otherwise>
				</c:choose>
				</td>
			</tr>
		</c:forEach>
        </table>
    </div>
</div>
<input type="hidden" name="action" id="action">
<input type="hidden" name="triggerKeyGroup" id="triggerKeyGroup">
<input type="hidden" name="triggerKeyName" id="triggerKeyName">
</form>
</body>
</html>