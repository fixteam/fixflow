<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../center/head.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/fixflow/css/form.css"></link>
<title>设置代理人</title>
</head>
<body>
<div class="popup">	
<h1>委托授权</h1>
<div class="formbox">	

<table class="table-form">
		<tr>
			<td class="title">操作人:</td>
			<td><input type="text" name="operatorName"/><input type="hidden" name="operator"/></td>
			<td class="title">委托人:</td>
			<td><input type="text" name="agentName"/><input type="hidden" name="agent"/></td>
		</tr>
		<tr>
			<td class="title">开始时间:</td>
			<td><input type="text" /></td>
			<td class="title">结束时间：</td>
			<td><input type="text" /></td>
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