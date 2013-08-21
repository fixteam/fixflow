<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程追逐</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/popup.css">
</head>

<body>

<div class="popup">
    <table width="100%" class="fix-table">
		<c:if test="${result.dataList!=null}">
	      <thead>
	        <th>处理者</th>
	        <th>步骤名称</th>
	        <th>到达时间</th>
	        <th>完成时间</th>
	        <th>预计时间</th>
	        <th>处理结果</th>
	        <th>处理意见</th>
	        <th>状态</th>
	      </thead>
			<c:forEach items="${result.dataList}" var="row" varStatus="status">
				<tr <c:if test="${status.index%2!=0}">class="gray"</c:if>>
					<td>${row.assignee}</td>
					<td>${row.nodeName}</td>
					<td><fmt:formatDate value="${row.createTime}" type="both"/></td>
					<td><fmt:formatDate value="${row.endTime}" type="both"/></td>
					<td>${row.expectedExecutionTime}</td>
					<td class="left">${row.commandMessage}</td>
					<td class="left">${row.taskComment}</td>
					<td class="left">已完成</td>
				</tr>
			</c:forEach>
			<c:forEach items="${result.notEnddataList}" var="row" varStatus="status">
				<tr <c:if test="${status.index%2!=0}">class="gray"</c:if>>
					<td>${row.assignee}</td>
					<td>${row.nodeName}</td>
					<td><fmt:formatDate value="${row.createTime}" type="both"/></td>
					<td><fmt:formatDate value="${row.endTime}" type="both"/></td>
					<td>${row.expectedExecutionTime}</td>
					<td class="left">${row.commandMessage}</td>
					<td class="left">${row.taskComment}</td>
					<td class="left">进行中</td>
				</tr>
			</c:forEach>
      </c:if>
      <tr>
        <td colspan="8"><img src="FlowCenter?action=getFlowGraph&processDefinitionKey=${result.processDefinitionKey}" /></td>
      </tr>
    </table>
</div>
</body>
</html>