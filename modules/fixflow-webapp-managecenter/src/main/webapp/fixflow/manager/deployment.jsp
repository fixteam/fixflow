<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fixflow/css/form.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fixflow/css/reset.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/fixflow/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/fixflow/js/My97DatePicker/WdatePicker.js"></script>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/fixflow/images/favicon.ico" type="image/x-icon">
<title>${applicationScope.appInfo["product.title"]}</title>
<%
	String deploymentId = request.getParameter("deploymentId");
	if(deploymentId == null){
		deploymentId = "";
	}
%>

<script type="text/javascript">
	function sub(){
		var fileName = $("#ProcessFile").val();
		var d=/\.[^\.]+$/.exec(fileName);
		if(d == ".zip" || d == ".ZIP"){
			$("#subForm").submit();
		}else{
			alert("请选择正确的文件类型");
			return false;
		}
	}
	
	function bodyOnLoad(){
		var deployId= $("#deploymentId").val();
		if(deployId == ""){
			$("#trDeploy").css("display","none");
		}
	}
</script>
</head>
<body onload="bodyOnLoad();">
<form action="../../FlowManager" method="post" id="subForm" ENCTYPE="multipart/form-data">
<input type="hidden" name="action" value="deploy">
<div class="tpl-form-border">
			<table class="table-form">
				<tr>
					<td class="title-r" style="width:300px"><p>请选择文件：</p>(<span style="color:red">注：只能选择设计器或者web下载的zip格式文件</span>)：</td>
					<td><input type="file" name="ProcessFile" id="ProcessFile"/></td>
				</tr>
				<tr id="trDeploy">
					<td class="title-r">发布号：</td>
					<td><input type="text" name="deploymentId" id="deploymentId" readOnly value="<%=deploymentId%>"></td>
				</tr>
			</table>
			<div class="toolbar">	
					<div class="btn-normal" onclick="sub();">
						<a href="#">发布</a>
					</div>
			</div>
	</div>
</form>
</body>
</html>