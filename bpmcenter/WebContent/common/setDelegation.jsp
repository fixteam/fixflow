<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../center/head.jsp" flush="true" />
<title>设置代理人</title>
</head>
<body>
	<table>
		<tr>
			<td class="title">操作人:</td>
			<td><input type="text" /></td>
			<td class="title">委托人:</td>
			<td><input type="text" /></td>
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
			<td class="title">姓名：</td>
			<td></td>
		</tr>
	</table>

	<table class="fix-table" id="detailTable">
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
			<td><input type="text"></td>
			<td><input type="text"></td>
		</tr>
		</tbody>
	</table>
</body>
</html>