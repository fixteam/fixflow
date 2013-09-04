<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.founder.fix.fixflow.service.FlowCenterService"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../center/formHead.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fixflow/css/form.css"></link>
<title>设置代理人</title>
<script type="text/javascript">
	$(function(){
	
	})
</script>
</head>
<body>
<div class="popup">	
<div class="formbox">	
<table class="table-form">
		<tr>
			<td class="title">操作人:</td>
			<td><%=session.getAttribute(FlowCenterService.LOGIN_USER_NAME)%><input type="hidden" value="<%=session.getAttribute(FlowCenterService.LOGIN_USER_ID) %>"  name="operator"/></td>
			<td class="title">委托人:</td>
			<td><input type="text" value="<%=session.getAttribute(FlowCenterService.LOGIN_USER_NAME)%>" name="agentName"/><input type="hidden" id="agent" name="agent" value="<%=session.getAttribute(FlowCenterService.LOGIN_USER_ID) %>"/>
			</td>
		</tr>
		<tr>
		<jsp:useBean id="now" class="java.util.Date" /> 
			<td class="title">开始时间:</td>
			<td><input type="text" id="sdate" name="sdate" value="<fmt:formatDate value="${now}" type="date"/>" onClick="WdatePicker()"/></td>
			<td class="title">结束时间：</td>
			<td><input type="text" id="edate" name="edate" onClick="WdatePicker()"/></td>
		</tr>
		<tr>
			<td class="title">状态：</td>
			<td><select>
					<option value="1">启用</option>
					<option value="0">停用</option>
			</select></td>
			<td class="title"></td>
			<td><input type="hidden" name="viewType"/></td>
		</tr>
	</table>
</div>
<div class="listbox">
	<table class="table-list" id="detailTable">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkall" name="checkall"></th>
				<th>流程</th>
				<th>代理人</th>
			</tr>
		</thead>
		<tbody>
		<tr id="row2">
			<td><input type="checkbox" name="check" rowindex="2"
				></td>
			<td><input id="" name="" type="text"></td>
			<td><input type="text"></td>
		</tr>
		</tbody>
	</table>
            <div class="btn-normal"><a href="#" id="saveBtn">保存</a></div>
	</div>
	</div>
	
</body>
</html>