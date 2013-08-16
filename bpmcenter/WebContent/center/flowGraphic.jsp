<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程查询</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<style>
a {
	text-decoration: none;
}
</style>
</head>

<body>
	<img
		src="FlowCenter?action=getFlowGraph&processDefinitionKey=${result.processDefinitionKey}" />
	<c:if test="${result.dataList!=null}">
		<div class="content">
			<table class="fix-table" width="100%">
				<tr>
					<td colspan="3">已完成
					<td>
				</tr>
				<thead>
					<th>步骤名称</th>
					<th>处理者</th>
					<th>到达时间</th>
					<th>完成时间</th>
					<th>预计完成时间</th>
					<th>处理结果</th>
					<th>处理意见</th>
				</thead>
				<c:forEach items="${result.dataList}" var="row" varStatus="status">
					<tr>
						<td>${row.nodeName}</td>
						<td>${row.assignee}</td>
						<td>${row.createTime}</td>
						<td>${row.endTime}</td>
						<td>${row.expectedExecutionTime}</td>
						<td>${row.taskComment}</td>
						<td>${row.taskComment}</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div class="content">
			<table class="fix-table" width="100%">
				<tr>
					<td colspan="3">未完成
					<td>
				</tr>
				<thead>
					<th>步骤名称</th>
					<th>当前处理</th>
					<th>到达时间</th>

					<th>预计完成时间</th>
				</thead>
				<c:forEach items="${result.notEnddataList}" var="row"
					varStatus="status">
					<tr>
						<td>${row.nodeName}</td>
						<td>${row.assignee}</td>
						<td>${row.createTime}</td>

						<td>${row.expectedExecutionTime}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
</body>
</html>