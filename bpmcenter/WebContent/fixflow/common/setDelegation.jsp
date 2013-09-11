<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../center/formHead.jsp" flush="true" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/fixflow/js/select.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/fixflow/css/form.css"></link>
<title>设置代理人</title>
<script type="text/javascript">
	$(function(){
		$("#selectUser").click(function(){
			var obj = {
			  type:"user"
			};
			var d = FixSelect(obj);
			var userId = d[0].USERID;
			var userName = d[0].USERNAME;
			var $tr = $(this).closest("tr");
			$("#auser", $tr).val(userId);
			$("#auserName", $tr).text(userName);
		})
		
		$("#saveBtn").click(function()	{
			var detailInfoList = [];
			$("#detailTable tbody tr").each(function(i, tr){
				var $tr = $(tr);
				
				var processId = $("#processId", $tr).val();;
				var auser = $("#auser", $tr).val();

				detailInfoList.push({
					processId : processId,
					auser : auser
				})
			})
			
			var agentInfo = {
				operator:"${sessionScope.LOGIN_USER_ID}",
				sDate: $("#sDate").val(),
				eDate: $("#eDate").val(),
				status: $("#status").val(),
				agentId: $("#agentId").val(),
				detailInfoList : detailInfoList
			}
			
			var ss = JSON.stringify(agentInfo);
			$("#insertAndUpdate").val(ss);
			$("#form").submit();
			
		})
	})
	
	
	
</script>
</head>
<body>
	<form method="post" id="form" action="FlowCenter">
		<div class="tpl-form-border">
			<div class="formbox">

				<table class="table-form">
					<tr>
						<td class="title-r">操作人:</td>
						<td>${sessionScope.LOGIN_USER_NAME}<input type="hidden"
							value="${sessionScope.LOGIN_USER_ID}" name="operator" id="operator"/></td>
						<td class="title-r">委托人:</td>
						<td><input type="text" value="${result.agentInfo.agentName}" id="agentName"
							name="agentName" /><input type="hidden" id="agentId" name="agentId"
							value="${result.agentInfo.agentId}" /></td>
					</tr>
					<tr>
						<td class="title-r">开始时间:</td>
						<td><input type="text" id="sDate" name="sDate" readOnly
							value='<fmt:formatDate value="${result.agentInfo.sDate}"/>'
							onClick="WdatePicker()" /></td>
						<td class="title-r">结束时间:</td>
						<td><input type="text" id="eDate" name="eDate" readOnly
							value='<fmt:formatDate value="${result.agentInfo.eDate}"/>'
							onClick="WdatePicker()" /></td>
					</tr>
					<tr>
						<td class="title-r">状态：</td>
						<td><select id="status" name="status">
								<option value="1" <c:if test="${result.agentInfo.status==1}">selected</c:if>>启用</option>
								<option value="0" <c:if test="${result.agentInfo.status==0}">selected</c:if>>停用</option>
						</select></td>
						<td></td>
						<td><input type="hidden" name="viewType" /></td>
					</tr>
				</table>
			</div>
			<input type="hidden" name="insertAndUpdate" id="insertAndUpdate" />
			<div class="listbox">
				<table class="table-list" id="detailTable">
					<thead>
						<tr>
							<!-- 
						<th><input type="checkbox" id="checkall" name="checkall"></th>
						 -->
							<th>流程</th>
							<th>代理人</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${result.agentInfo.detailInfoList}"
							var="dataList" varStatus="index">
							<tr id="row${index.index+1}">
								<!-- 
							<td style="text-align:center"><input type="checkbox" name="check"
								rowindex="${index.index+1}"></td>
								-->
								<td>${dataList.processName}<input id="processId"
									value="${dataList.processId}" name="processId" type="hidden"></td>
								<td><label id="auserName">${dataList.auserName}</label><input
									id="auser" name="auser" value="${dataList.auser}" type="hidden">
								<div class="btn-normal">
										<a href="#" onclick="" id="selectUser">选择<em
											class="arrow-small"></em></a>
									</div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="toolbar">
					<div class="btn-normal">
						<a href="#" id="saveBtn">保存</a>
					</div>
				</div>

			</div>

		</div>
		<input type="hidden" name="action" value="saveDelegation"/>
	</form>
</body>
</html>