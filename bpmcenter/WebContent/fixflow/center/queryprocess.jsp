<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程查询</title>
<jsp:include page="head.jsp" flush="true"/>
<script type="text/javascript">
$(function(){
$("a[name=flowGraph]").click(function(){
    var pdk = $(this).attr("pdk");
    var pii = $(this).attr("pii");
    var obj = {};
    window.open("FlowCenter?action=getTaskDetailInfo&processDefinitionKey="+pdk+"&processInstanceId="+pii);
  });
var status = '${result.status}';
if(status!='')
	$("#status").val(status);
	
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
function clearInfo(){
	$("#title").val("");
	$("#processDefinitionKey").val("");
	$("#bizKey").val("");
	$("#initor").val("");
	$("#initorName").val("");
	$("#startTimeS").val("");
	$("#startTimeE").val("");
	$("#status").val("");
}
</script>
</head>
<body>
	<div class="main-panel">
		<jsp:include page="top.jsp" flush="true" />
		<div class="center-panel">
			
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
					<form id="subForm" method="post" action="FlowCenter">
					<!-- 查 -->
					<div id="search" class="search">
					<input type="hidden" name="action" value="getAllProcess"/> 
					<input type="hidden" id="processType" name="processType" value="${result.processType}"/> 
						<table>
			              <tr>
			                <td class="title-r">${applicationScope.appInfo["task.subject"]}：</td>
			                <td><input type="text" id="title" name="title" class="fix-input" value="${result.title}"/></td>
			                <td class="title-r">${applicationScope.appInfo["task.processDefinitionName"]}：</td>
			                <td><input type="text" id="processDefinitionKey" name="processDefinitionKey" class="fix-input" value=""/></td>
			                <td class="title-r">${applicationScope.appInfo["task.bizKey"]}：</td>
			                <td><input type="text" id="bizKey" name="bizKey" class="fix-input" value="${result.bizKey}"/></td>
			                <td>
				                <table style="margin:0">
				                <tr>
				                <td>
				                <div class="btn-normal"><a href="#" onclick="$('#subForm').submit();">${applicationScope.appInfo["common.search"]}</a></div>
				                </td>
				                <td>
				                <div class="btn-normal"><a href="#" onclick="clearInfo();">${applicationScope.appInfo["common.clear"]}</a></div>
								</td>                
				                </tr>
				                </table>
			                </td>
			              </tr>
			              <tr>
			              	
			                <td class="title-r">${applicationScope.appInfo["task.initor"]}：</td>
			               <td>
									<table style="margin: 0">
										<tr>
											<td><input type="hidden" id="initor" name="initor"
												class="fix-input" value="${result.initor}" /> <input
												type="text" id="initorName" readonly="true"
												name="initorName" class="fix-input" style="width: 94px;"
												value="${result.initorName}" /></td>
											<td>
												<div class="btn-normal">
													<a href="#" onclick="" id="selectUser">${applicationScope.appInfo["common.select"]}<em
														class="arrow-small"></em></a>
												</div>
											</td>
										</tr>
									</table>
								</td>
			                <td class="title-r">${applicationScope.appInfo["task.startTime"]}：</td>
			                <td><input type="text" id="startTimeS" name="startTimeS" class="fix-input" style="width:69px;" value="${result.startTimeS}" onClick="WdatePicker()"/>
			                 - <input type="text" id="startTimeE"  name="startTimeE" class="fix-input" style="width:69px;" value="${result.startTimeE}" onClick="WdatePicker()"/></td>
			                <td class="title-r">${applicationScope.appInfo["task.status"]}：</td>
			                <td>
		                    <select id="status" name="status" class="fix-input" style="width:172px;">
			                  <option value ="">请选择</option>
							  <option value ="SUSPEND">暂停</option>
							  <option value ="RUNNING">运行中</option>
							  <option value ="COMPLETE">完成</option>
							  <option value ="TERMINATION">终止</option>
							</select>
			                </td>
			                <td></td>
			              </tr>
			            </table>
					</div>
					<div class="content">
						<!-- 表 -->
						<table width="100%" class="fix-table">
							<thead>
							<th width="30px">${applicationScope.appInfo["common.no"]}</th>
								<th>${applicationScope.appInfo["task.bizKey"]}</th>
								<th>${applicationScope.appInfo["task.processDefinitionName"]}</th>
								<th>${applicationScope.appInfo["task.subject"]}</th>
								<c:if test="${result.processType != 'initor'}"><th>${applicationScope.appInfo["task.initor"]}</th> </c:if>
								
								<th width="130">${applicationScope.appInfo["task.startTime"]}</th>
								<th width="130">${applicationScope.appInfo["task.updateTime"]}</th>
								<th>${applicationScope.appInfo["task.nodeName"]}</th>
								<th>${applicationScope.appInfo["task.status"]}</th>
								<th>${applicationScope.appInfo["common.operation"]}</th>
							</thead>
							<tbody>
								<c:forEach items="${result.dataList}" var="dataList"
									varStatus="index">
									<tr>
									<td style="text-align:center;">${(index.index+1)+pageInfo.pageSize*(pageInfo.pageIndex-1)}</td>
										<td>${dataList.BIZ_KEY}</td>
										<td>${dataList.processDefinitionName}</td>
										<td>${dataList.subject}</td>
										<c:if test="${result.processType != 'initor'}"><td>${dataList.startAuthorName}</td></c:if>
										<td><fmt:formatDate value="${dataList.startTime}" type="both"/></td>
										<td><fmt:formatDate value="${dataList.updateTime}" type="both"/></td>
										<td>${dataList.nowNodeInfo}</td>
										<td>
											<c:if test="${dataList.instanceStatus == 'SUSPEND'}" var="runStatue">暂停</c:if>
											<c:if test="${dataList.instanceStatus == 'RUNNING'}" var="runStatue">运行中</c:if>
											<c:if test="${dataList.instanceStatus == 'COMPLETE'}" var="runStatue">完成</c:if>
											<c:if test="${dataList.instanceStatus == 'TERMINATION'}" var="runStatue">终止</c:if>
										</td>
										<td><a name="flowGraph" href="#" pii="${dataList.processInstanceId}" pdk="${dataList.processDefinitionKey}">查看</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 分页 -->
					    <div id="page">
					      <jsp:include page="../common/page.jsp" flush="true"/>
					    </div>
					</div>
					</form>
				</div>
			
		</div>
	</div>
	<!-- 隐藏参数部分 -->
	<input type="hidden" name="userId"
		value="<c:out value="${result.userId}"/>">
	<input type="hidden" name="type"
		value="<c:out value="${result.action}"/>">
</body>
</html>
