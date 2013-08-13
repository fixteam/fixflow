<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程查询</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<style>
a{text-decoration: none;}
</style>
</head>

<body>
<img src="FlowCenter?action=getFlowGraph&processDefinitionKey=${result.processDefinitionKey}"/>

<c:if test="${result.dataList!=null}">
<table>
	<tr>
		<td colspan="3">
			已完成
		<td>
	</tr>
	<tr>
		<td>
			步骤名称
		</td>
				<td>
			处理者
		</td>
				<td>
			到达时间
		</td>
				<td>
			完成时间
		</td>
				<td>
			预计完成时间
		</td>
				<td>
			处理结果
		</td>
				<td>
			处理意见
		</td>
	</tr>
	<c:forEach items="${result.dataList}" var="row" varStatus="status">
		<tr>
			<td>
				${row.nodeName}
			</td>
						<td>${row.assignee}
			</td>
						<td>${row.createTime}
			</td>
						<td>
				${row.endTime}
			</td>
						<td>${row.expectedExecutionTime}
			</td>
						<td>${row.taskComment}
			</td>
				<td>${row.taskComment}
			</td>
		</tr>
	</c:forEach>
</table>

<table>
	<tr>
		<td colspan="3">
			未完成
		<td>
	</tr>
	<tr>
		<td>
			步骤名称
		</td>
				<td>
			当前处理
		</td>
				<td>
			到达时间
		</td>

				<td>
			预计完成时间
		</td>
	</tr>
	<c:forEach items="${result.notEnddataList}" var="row" varStatus="status">
		<tr>
			<td>
				${row.nodeName}
			</td>
						<td>${row.assignee}
			</td>
						<td>${row.createTime}
			</td>

						<td>${row.expectedExecutionTime}
			</td>
		</tr>
	</c:forEach>
</table>
</c:if>
</body>
</html>