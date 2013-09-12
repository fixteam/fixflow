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
<script type="text/javascript">
function clearInfo(){
	$("#processDefinitionKey").val("");
	$("#processInstanceId").val("");
	$("#subject").val("");
	$("#initor").val("");
	$("#initorName").val("");
	$("#BIZ_KEY").val("");
	$("#arrivalTimeS").val("");
	$("#arrivalTimeE").val("");
}

$(function(){
	$("#selectUser").click(function(){
		var obj = {
		  type:"user"
		};
		var d = FixSelect(obj);
		var userId = d[0].USERID;
		var userName = d[0].USERNAME;
		$("#initor").val(userId);
		$("#initorName").val(userName);
	});
});
</script>
</head>
<body>
	<div class="main-panel">
		<jsp:include page="top.jsp" flush="true" />
		<div class="center-panel">
		<form id="subForm" method="post" action="FlowCenter">
				<!-- 右-->
				<div class="right">
					<!-- 查 -->
					<div id="search" class="search">
					 		<input type="hidden" name="action" value="getPlaceOnFile"/> 
					  		<input type="hidden" id="processType" name="processType" value="${result.processType}"/>
							<table>
				              <tr>
				                <td class="title-r">流程定义：</td>
				                <td><input type="text" id="processDefinitionKey" name="processDefinitionKey" class="fix-input" value="${result.processDefinitionKey}"/></td>
				                <td class="title-r">流程实例号：</td>
				                <td><input type="text" id="processInstanceId" name="processInstanceId" class="fix-input" value="${result.processInstanceId}"/></td>
				                <td class="title-r">主 题：</td>
				                <td><input type="text" id="subject" name="subject" class="fix-input" value="${result.subject}"/></td>
				              	<td>
					                <table style="margin:0">
					                <tr>
					                <td>
					                <div class="btn-normal"><a href="#" onclick="$('#subForm').submit();">查 找</a></div>
					                </td>
					                <td>
					                <div class="btn-normal"><a href="#" onclick="clearInfo();">清空</a></div>
									</td>                
					                </tr>
					                </table>
				              	</td>
				              </tr>
				              <tr>
				                <td class="title-r">发 起 人：</td>
				                <td>
					                <table style="margin:0">
					                <tr>
					                <td>
					                <input type="hidden" id="initor" name="initor" class="fix-input" value="${result.initor}"/>
					                <input type="text" id="initorName" readonly="true" name="initorName" class="fix-input" style="width:94px;" value="${result.initorName}"/>
					                </td>
					                <td>
					                <div class="btn-normal">
															<a href="#" onclick="" id="selectUser">选择<em
																class="arrow-small"></em></a>
									</div>
									</td>
									</tr>
									</table>
				                </td>
				                <td class="title-r">业务数据：</td>
				                <td><input type="text" id="BIZ_KEY" name="BIZ_KEY" class="fix-input" value="${result.BIZ_KEY}"/></td>
				                <td class="title-r">归档时间：</td>
				                <td><input type="text" id="arrivalTimeS" name="arrivalTimeS" class="fix-input fix-input-data" value="${result.arrivalTimeS}" onClick="WdatePicker()"/>
				                 - <input type="text" id="arrivalTimeE" name="arrivalTimeE" class="fix-input fix-input-data" value="${result.arrivalTimeE}" onClick="WdatePicker()"/></td>
				              	<td></td>
				              </tr>
				            </table>
					</div>
					<!-- 表 -->
					<div class="content">
						<table width="100%" class="fix-table">
							<thead>
								<th width="30">序号</th>
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
										<td style="text-align:center;">${(index.index+1)+pageInfo.pageSize*(pageInfo.pageIndex-1)}</td>
										<td>${dataList.BIZ_KEY}</td>
										<td>${dataList.processDefinitionKey}</td>
										<td>${dataList.subject}</td>
										<td>${dataList.startAuthorName}</td>
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
				<!-- 隐藏参数部分 -->
				<input type="hidden" name="userId" value="<c:out value="${result.userId}"/>">
				<input type="hidden" name="pageIndex" value="<c:out value="${result.pageIndex}"/>">
				<input type="hidden" name="rowNum" value="<c:out value="${result.rowNum}"/>">
				<input type="hidden" name="type" value="<c:out value="${result.action}"/>">
			</form>
		</div>
	</div>
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