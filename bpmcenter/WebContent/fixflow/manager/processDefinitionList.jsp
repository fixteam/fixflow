<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<jsp:include page="head.jsp" flush="true"/>

<style>
a{text-decoration: none;}
.red_star{
   color:red;
}
</style>
<title>流程定义列表</title>
<script type="text/javascript">
	function deployment(){
		var obj = {};
		window.open("fixflow/manager/deployment.jsp");
	}
	
	function searchProcess(){
		$("#action").val("processDefinitionList");
		document.forms[0].submit();
	}
	
	function deleteDeploy(obj){
		if($(obj).attr("class")=="btn-disable"){
			return;
		}
		if(!confirm("此操作会删除所有流程相关数据，并且不可恢复，请慎重选择，确认继续吗？")){
			return;
		}
		var deploymentId = "";
		var checkList = $("input:checked");
		for(var i=0;i<checkList.length;i++){
			if(i == 0){
				deploymentId = $(checkList[i]).attr("deploymentId");
			}else{
				deploymentId = deploymentId+","+$(checkList[i]).attr("deploymentId");
			}
		}
		$("#deploymentId").val(deploymentId);
		$("#action").val("deleteDeploy");
		document.forms[0].submit();
	}
	
	function updateDeploy(obj){
		if($(obj).attr("class")=="btn-disable"){
			return;
		}
		var deploymentId = "";
		var checkList = $("input:checked");
		if(checkList.length >0){
		 	deploymentId = $(checkList[0]).attr("deploymentId");
		}
		var obj = {};
		window.open("fixflow/manager/deployment.jsp?deploymentId="+deploymentId);
	}
	
	function downloadProcess(obj){
		if($(obj).attr("class")=="btn-disable"){
			return;
		}
		var deploymentId = "";
		var processDefinitionId = "";
		var checkList = $("input:checked");
		if(checkList.length >0){
		 	deploymentId = $(checkList[0]).attr("deploymentId");
		 	processDefinitionId = $(checkList[0]).attr("processDefinitionId");
		}
		$("#deploymentId").val(deploymentId);
		$("#action").val("download");
		$("#processDefinitionId").val(processDefinitionId);
		document.forms[0].submit();
	}
	
	function checkSelect(){
		var checkList = $("input:checked");
		if(checkList.length >0){
		 	return true;
		}
		return false;
	}
	$(function(){
		Fix.Util.ClickTr(null,false,true,0);
		$("#checkAll").click(function(obj){
			if($(this).attr("checked")=="checked"){
				$("table.fix-table tbody tr").each(function(){
				  if(!$(this).hasClass("selected"))
				  	$(this).click();
				});
			}else{
				$("table.fix-table tbody tr").each(function(){
					 if($(this).hasClass("selected"))
				  		$(this).click();
				});
			}
		});
	});
</script>
</head>

<body>
<form action="FlowManager" id="subForm">
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>
<div class="center-panel">
<div style="margin-top:1px;">
<!-- 左 -->
<div class="left" style="width:0px;">
</div>
<!-- 右-->
	<div class="right" style="width:98%;">
	  <!-- 查 -->
	  <div class="search">
        	<table>
              <tr>
                <td class="title-r">流程编号：</td>
                <td><input type="text" id="text_3" name="queryProcessId" class="fix-input" value="${result.queryProcessId}"/></td>
                <td class="title-r">流程名称：</td>
                <td><input type="text" id="text_4" name="queryProcessName" class="fix-input" value="${result.queryProcessName}"/></td>
                <td class="title-r">流程分类：</td>
                <td><input type="text" id="text_4" name="queryType" class="fix-input" value="${result.queryType}"/></td>
                <td><div class="btn-normal"><a href="#" onclick="searchProcess()">查 找</a></div></td>
              </tr>
            </table>
        </div>
       <div class="toolbar" style="padding-right:2px;text-align: right;margin-bottom: 4px;">
		  <div class="btn-normal" style="display:inline-block;margin-left:5px;" onclick="deployment()" data-scope=all><a href="#" >发布流程</a></div>
		  <div class="btn-normal" style="display:inline-block;margin-left:5px;" data-scope=multi onclick="deleteDeploy(this)"><a href="#" >删除定义</a></div>
		  <div class="btn-normal" style="display:inline-block;margin-left:5px;" data-scope=single onclick="updateDeploy(this)"><a href="#" >更新定义</a></div>
		  <div class="btn-normal" style="display:inline-block;margin-left:5px;" data-scope=single onclick="downloadProcess(this)"><a href="#" >下载定义</a></div>
	  </div>
	  <div class="content">
	 
		<table style="width:100%;" class="fix-table">
		  <thead>
		    <th width="2%">&nbsp;</th>
		    <th>序号</th>
		    <th >流程名称</th>
		    <th width="15%">编号</th>
		    <th width="5%">流程版本</th>
		    <th width="12%">流程分类</th>
		    <th width="31%">唯一编号</th>
		    <th width="12%">发布时间</th>
		  </thead>
		  <tbody>
		   <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		      <td><input type="radio" name="chk" deploymentId="${dataList.deploymentId}" processDefinitionId="${dataList.processDefinitionId}" /></td>
		      <td style="text-align:center;">${(index.index+1)+pageInfo.pageSize*(pageInfo.pageIndex-1)}</td>
		      <td>${dataList.processDefinitionName}</td>
		      <td>${dataList.processDefinitionKey}</td>
		      <td>${dataList.version}</td>
		      <td>${dataList.category}</td>
		      <td>${dataList.processDefinitionId}</td>
		      <td><fmt:formatDate value="${dataList.DEPLOY_TIME}" type="both"/></td>
		    </tr>
		    </c:forEach>
		  </tbody>
	    </table>
		<!-- 分页 -->	    
	   <jsp:include page="../common/page.jsp" flush="true"/>
	    </div>
	  </div>
	</div>
</div>
<!-- 隐藏参数部分 -->
	<input type="hidden" name="deploymentId" id="deploymentId" value=""/>
	<input type="hidden" name="action" id="action" value="processDefinitionList"/>
	<input type="hidden" name="processDefinitionId" id="processDefinitionId" value=""/>
	</div>
</form>

</body>
</html>
