<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>启动任务</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/fixflow/css/form.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/fixflow/css/reset.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fixflow/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fixflow/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fixflow/js/flowcommand.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fixflow/js/flowautoassemble.js"></script>
<style>
</style>
</head>

<body>
	<div class="tpl-form-border">
		<form id="form1" action="DemoServlet">
			<table class="table-form">
				<tr>
					<td class="title-r">流程关联键：</td>
					<td><input type="text" name="businessKey" value="${result.demoObject.COL1}"/></td>
				</tr>
				<tr>
					<td class="title-r">信息：</td>
					<td><input type="text" name="COL2" value="${result.demoObject.COL2}"/></td>
				</tr>
				<tr>
					<td class="title-r">审批意见：</td>
					<td><textarea rows="3" cols="20" name="_taskComment"></textarea></td>
				</tr>
			</table>
			<div class="toolbar">
				<input type="hidden" name="action" value="demoCompleteTask" />
				<c:forEach items="${result.commandList}" var="row"
					varStatus="status">
					<div class="btn-normal" id="btn_${status.index+1}"
						commandId="${row.id}" commandName="${row.name}"
						commandType="${row.type}" isAdmin="${row.isAdmin}"
						isVerification="${row.isVerification}"
						isSaveData="${row.isSaveData}"
						isSimulationRun="${row.isSimulationRun}" nodeId="${row.nodeId}"
						nodeName="${row.nodeName}">
						<a href="#">${row.name}</a>
					</div>
				</c:forEach>
			</div>
		</form>
	</div>
</body>
</html>
