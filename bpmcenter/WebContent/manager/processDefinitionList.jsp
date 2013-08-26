<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/page.css">
<style>
a{text-decoration: none;}
.red_star{
   color:red;
}
</style>
<title>流程定义列表</title>
<script type="text/javascript">
	function deployment(){
		window.open("manager/deployment.jsp");
	}
	
	function deleteDeploy(){
		if(!checkSelect()){
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
	
	function updateDeploy(){
		if(!checkSelect()){
			alert("请选择流程");
			return;
		}
		var deploymentId = "";
		var checkList = $("input:checked");
		if(checkList.length >0){
		 	deploymentId = $(checkList[0]).attr("deploymentId");
		}
		window.open("manager/deployment.jsp?deploymentId="+deploymentId);
	}
	
	function downloadProcess(){
		if(!checkSelect()){
			alert("请选择流程");
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
                <td><input type="text" id="text_4" name="queryType" class="fix-input" style="width:160px;" value="${result.queryType}"/></td>
                <td></td>
              </tr>
            </table>
        </div>
       <div class="toolbar" style="padding-left:0px; padding-right:80px;">
		  <div class="btn-normal" style="float:left;margin-left:5px;"><a href="#" onclick="deployment()">发布流程</a></div>
		  <div class="btn-normal" style="float:left;margin-left:5px;"><a href="#" onclick="deleteDeploy()">删除定义</a></div>
		  <div class="btn-normal" style="float:left;margin-left:5px;"><a href="#" onclick="updateDeploy()">更新定义</a></div>
		  <div class="btn-normal" style="float:left;margin-left:5px;"><a href="#" onclick="downloadProcess()">下载定义</a></div>
	  	<div class="btn-normal" style="float:right;"><a href="#" onclick="$('#subForm').submit();">查 找<em class="arrow-small"></em></a></div>
	  </div>
	  <div>
	 
		<table style="width:100%;" class="fix-table">
		  <thead>
		   <th width="2%"></th>
		    <th width="12%">流程编号</th>
		    <th width="28%">唯一编号</th>
		    <th >流程名称</th>
		    <th width="5%">流程版本</th>
		    <th width="8%">流程分类</th>
		  </thead>
		  <tbody>
		   <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		     <td><input type="checkbox" deploymentId="${dataList.deploymentId}" processDefinitionId="${dataList.processDefinitionId}" /></td>
		      <td>${dataList.processDefinitionKey}</td>
		      <td>${dataList.processDefinitionId}</td>
		      <td>${dataList.processDefinitionName}</td>
		      <td>${dataList.version}</td>
		      <td>${dataList.category}</td>
		    </tr>
		    </c:forEach>
		  </tbody>
	    </table>
		<!-- 分页 -->	    
	   <jsp:include page="page.jsp" flush="true"/>
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