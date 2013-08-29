<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>归档任务</title>
<jsp:include page="head.jsp" flush="true"/>
<script type="text/javascript" src="fixflow/js/select.js"></script>
</head>
<body>
<form id="subForm" method="post" action="FlowCenter">
	<div class="main-panel">
		<jsp:include page="top.jsp" flush="true" />
		<div class="center-panel">
				<!-- 左 -->
				<div class="left">
				<!-- 左侧暂为空 -->
				</div>
				<!-- 右-->
				<div class="right">
					<!-- 查 -->
					<div id="search" class="search">
					 		<input type="hidden" name="action" value="getPlaceOnFile"/> 
					  		<input type="hidden" id="processType" name="processType" value="${result.processType}"/>
							<table width="100%">
				              <tr>
				                <td class="title-r">流程定义：</td>
				                <td><input type="text" id="text_0" name="processDefinitionKey" class="fix-input" style="width:160px;" value="${result.processDefinitionKey}"/></td>
				                <td class="title-r">流程实例号：</td>
				                <td><input type="text" id="text_1" name="processInstanceId" class="fix-input" style="width:160px;" value="${result.processInstanceId}"/></td>
				                <td class="title-r">主 题：</td>
				                <td><input type="text" id="text_2" name="subject" class="fix-input" style="width:160px;" value="${result.subject}"/></td>
				              </tr>
				              <tr>
				                <td class="title-r">发 起 人：</td>
				                <td><input type="text" id="text_3" name="initor" class="fix-input" style="width:160px;" value="${result.initor}"/></td>
				                <td class="title-r">业务数据：</td>
				                <td><input type="text" id="text_4" name="BIZ_KEY" class="fix-input" style="width:160px;" value="${result.BIZ_KEY}"/></td>
				                <td class="title-r">归档时间：</td>
				                <td><input type="text" id="text_4" name="arrivalTimeS" class="fix-input" style="width:69px;" value="${result.arrivalTimeS}" onClick="WdatePicker()"/>
				                 - <input type="text" id="text_5" name="arrivalTimeE" class="fix-input" style="width:69px;" value="${result.arrivalTimeE}" onClick="WdatePicker()"/></td>
				              </tr>
				              <tr>
				                <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
				                <td><div class="btn-normal"><a href="#" onclick="$('#subForm').submit();">查 找<em class="arrow-small"></em></a></div></td>
				              </tr>
				            </table>
					</div>
					<!-- 表 -->
					<div class="content">
						<table width="100%" class="fix-table">
							<thead>
								<th>实例编号</th>
								<th>流程定义</th>
								<th>任务主题</th>
								<th>发起人</th>
								<th>启动时间</th>
								<th>更新时间</th>
								<th>业务数据</th>
								<th>结束时间</th>
							</thead>
							<tbody>
								<c:forEach items="${result.dataList}" var="dataList" varStatus="index">
									<tr>
										<td>${dataList.BIZ_KEY}</td>
										<td>${dataList.processDefinitionKey}</td>
										<td>${dataList.subject}</td>
										<td>${dataList.startAuthor}</td>
										<td><fmt:formatDate value="${dataList.startTime}" type="both"/></td>
										<td><fmt:formatDate value="${dataList.updateTime}" type="both"/></td>
										<td>${dataList.BIZ_KEY}</td>
										<td><fmt:formatDate value="${dataList.endTime}" type="both"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 分页 -->
					    <div id="page">
					      <jsp:include page="../common/page.jsp" flush="true"/>
					    </div>
					</div>
				</div>
		</div>
	</div>
	<!-- 隐藏参数部分 -->
	<input type="hidden" name="userId"
		value="<c:out value="${result.userId}"/>">
	<input type="hidden" name="pageIndex"
		value="<c:out value="${result.pageIndex}"/>">
	<input type="hidden" name="rowNum"
		value="<c:out value="${result.rowNum}"/>">
	<input type="hidden" name="type"
		value="<c:out value="${result.action}"/>">
</form>
</body>
<script>
$(function(){
  var type = $("input[name=type]").val();
  var userId = $("input[name=userId]").val();
  $("a[name=page]").click(function(){
    var pageNo = $(this).html();
    window.location.href = "FlowCenter?action="+type+"&pageIndex="+pageNo+"&rowNum=15&userId="+userId;
  });
  $("input[name=initor]").click(function(){
    var obj = {type:"user",taskId:"d4a1db01-c6b7-475f-bc9d-e720c6ec01fd"};
  	var d = FixSelect(obj);
  	if(d&&d.length>0)
  		$(this).val(d[0].USERNAME);
  });
}); 
</script>
</html>