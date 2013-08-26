<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
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
a {
	text-decoration: none; 
}
</style>
<script type="text/javascript">
$("a[name=flowGraph]").click(function(){
    var pdk = $(this).attr("pdk");
    var pii = $(this).attr("pii");
    var obj = {};
    window.showModalDialog("FlowCenter?action=getTaskDetailInfo&processDefinitionKey="+pdk+"&processInstanceId="+pii,obj,"dialogWidth=800px;dialogHeight=600px");
  });
</script>
</head>
<body>
	<div class="main-panel">
		<jsp:include page="top.jsp" flush="true" />
		<div class="center-panel">
			<form id="subForm" method="post" action="FlowCenter">
				<!-- 左 -->
				<div class="left">
					<div class="left-nav-box">
						<div class="left-nav">
							<a id="getAllProcess" name="getAllProcess" target="_self"
								href="#" onclick="$('#processType').val('');$('#subForm').submit();"
								 style="display: block;">全部流程</a>
						</div>
            			<div class="left-nav-orange-line">&nbsp;</div>
						<div class="left-nav">
							<a id="getInitorTask" name="getInitorTask" target="_self"
								href="#" onclick="$('#processType').val('initor');$('#subForm').submit();"
								style="display: block;">我发起的流程</a>
						</div>
            			<div class="left-nav-orange-line">&nbsp;</div>
						<div class="left-nav">
							<a id="getInitorTask" name="getParticipantsTask" target="_self"
								href="#" onclick="$('#processType').val('participants');$('#subForm').submit();"
								style="display: block;">我参与的流程</a>
						</div>
            			<div class="left-nav-orange-line">&nbsp;</div>
					</div>
				</div>
				<!-- 右-->
				<div class="right">
					
					<!-- 查 -->
					<div id="search" class="search">
					<input type="hidden" name="action" value="getAllProcess"/> 
					<input type="hidden" id="processType" name="processType" value="${result.processType}"/> 
						<table width="100%">
			              <tr>
			                <td class="title-r">任务主题：</td>
			                <td><input type="text" id="text_0" name="title" class="fix-input" style="width:160px;" value="${result.title}"/></td>
			                <td class="title-r">流程变量：</td>
			                <td><input type="text" id="text_1" name="text_1" class="fix-input" style="width:160px;" value=""/></td>
			                <td class="title-r">单 据 号：</td>
			                <td><input type="text" id="text_2" name="bizKey" class="fix-input" style="width:160px;" value="${result.bizKey}"/></td>
			              </tr>
			              <tr>
			                <td class="title-r">发 起 人：</td>
			                <td><input type="text" id="text_3" name="initor" class="fix-input" style="width:160px;" value="${result.initor}"/></td>
			                <td class="title-r">发起时间：</td>
			                <td><input type="text" id="text_4" name="startTimeS" class="fix-input" style="width:69px;" value="${result.startTimeS}" onClick="WdatePicker()"/>
			                 - <input type="text" id="text_5" name="startlTimeE" class="fix-input" style="width:69px;" value="${result.startlTimeE}" onClick="WdatePicker()"/></td>
			                <td>&nbsp;</td>
			                <td><div class="btn-normal"><a href="#" onclick="$('#subForm').submit();">查 找<em class="arrow-small"></em></a></div></td>
			              </tr>
			            </table>
					</div>
					<div class="content">
						<!-- 表 -->
						<table width="100%" class="fix-table">
							<thead>
								<th>单据号</th>
								<th>流程名称</th>
								<th>任务主题</th>
								<th>发起人</th>
								<th>发起时间</th>
								<th>更新时间</th>
								<th>当前步骤</th>
								<th>流程状态</th>
							</thead>
							<tbody>
								<c:forEach items="${result.dataList}" var="dataList"
									varStatus="index">
									<tr>
										<td>${dataList.BIZ_KEY}</td>
										<td>${dataList.processDefinitionName}</td>
										<td>${dataList.subject}</td>
										<td>${dataList.startAuthor}</td>
										<td><fmt:formatDate value="${dataList.startTime}" type="both"/></td>
										<td><fmt:formatDate value="${dataList.updateTime}" type="both"/></td>
										<td>${dataList.processLocation}</td>
										<td><a name="flowGraph" href="#" pii="${dataList.processInstanceId}" pdk="${dataList.processDefinitionKey}">查看</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 分页 -->
					    <div id="page">
					      <jsp:include page="page.jsp" flush="true"/>
					    </div>
					</div>
				</div>
			</form>
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