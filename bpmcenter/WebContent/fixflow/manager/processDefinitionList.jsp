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
		window.showModalDialog("fixflow/manager/deployment.jsp",obj,"dialogWidth=600px;dialogHeight=400px");
	}
	
	function deleteDeploy(obj){
		if($(obj).attr("class")=="btn-disable"){
			alert("请选择流程");
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
			alert("请选择一个流程！");
			return;
		}
		var deploymentId = "";
		var checkList = $("input:checked");
		if(checkList.length >0){
		 	deploymentId = $(checkList[0]).attr("deploymentId");
		}
		var obj = {};
		window.showModalDialog("manager/deployment.jsp?deploymentId="+deploymentId,obj,"dialogWidth=600px;dialogHeight=400px");
	}
	
	function downloadProcess(obj){
		if($(obj).attr("class")=="btn-disable"){
			alert("请选择流程！");
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
		Fix.Util.ClickTr(null,true,true,0);
	});
</script>
</head>

<body>
<form action="FlowManager" id="subForm">
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>
<div style="margin-top:1px;">
<!-- 左 -->
<div class="left" style="width:0px;">
    	
</div>
<!-- 右-->
	<div class="right" style="width:98%;">
	  <!-- 查 -->
	  <div class="search">
        	<table width="100%">
              <tr>
                <td class="title-r">流程编号：</td>
                <td><input type="text" id="text_3" name="queryProcessId" class="fix-input" style="width:160px;" value="${result.queryProcessId}"/></td>
                <td class="title-r">流程名称：</td>
                <td><input type="text" id="text_4" name="queryProcessName" class="fix-input" style="width:160px;" value="${result.queryProcessName}"/></td>
                <td class="title-r">流程分类：</td>
                <td style="width:200px;"><input type="text" id="text_4" name="queryType" class="fix-input" style="width:160px;" value="${result.queryType}"/></td>
                <td><div class="btn-normal"><a href="#" onclick="$('#subForm').submit();">查 找<em class="arrow-small"></em></a></div></td>
              </tr>
            </table>
        </div>
       <div class="toolbar" style="padding-right:2px;text-align: right;margin-bottom: 4px;">
		  <div class="btn-normal" style="display:inline-block;margin-left:5px;" onclick="deployment()" data-scope=all><a href="#" >发布流程</a></div>
		  <div class="btn-normal" style="display:inline-block;margin-left:5px;" data-scope=multi onclick="deleteDeploy(this)"><a href="#" >删除定义</a></div>
		  <div class="btn-normal" style="display:inline-block;margin-left:5px;" data-scope=single onclick="updateDeploy(this)"><a href="#" >更新定义</a></div>
		  <div class="btn-normal" style="display:inline-block;margin-left:5px;" data-scope=single onclick="downloadProcess(this)"><a href="#" >下载定义</a></div>
	  </div>
	  <div>
	 
		<table style="width:100%;" class="fix-table">
		  <thead>
		   <th width="2%"></th>
		   <th >流程名称</th>
		    <th width="12%">流程编号</th>
		    <th width="28%">唯一编号</th>
		    <th width="5%">流程版本</th>
		    <th width="12%">流程分类</th>
		    <th width="12%">发布时间</th>
		  </thead>
		  <tbody>
		   <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		     <td><input type="checkbox" deploymentId="${dataList.deploymentId}" processDefinitionId="${dataList.processDefinitionId}" /></td>
		       <td>${dataList.processDefinitionName}</td>
		      <td>${dataList.processDefinitionKey}</td>
		      <td>${dataList.processDefinitionId}</td>
		      <td>${dataList.version}</td>
		      <td>${dataList.category}</td>
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
</form>
</body>
</html>
