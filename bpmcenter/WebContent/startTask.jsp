<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发起流程</title>
<script type="text/javascript" src="js/jquery.js"></script>
<style>
a{text-decoration: none;}
</style>
</head>
<body>
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>

	<div style="margin-top:10px;">
	<c:forEach items="${result}" var="row" varStatus="status">
	<button id="btn_${status.index+1}" formUrl="${row.formUrl}" processDefinitionKey="${row.processDefinitionKey}">${row.processDefinitionName}</button>
	</c:forEach>
	</div>
</div>
</body>
<script>
$(function(){
  $("button[processDefinitionKey]").click(function(){
     var pdk = $(this).attr("processDefinitionKey");
     var formUrl = $(this).attr("formUrl");//"FlowCenter?action=startOneTask";
     var url = formUrl;
     if(formUrl.indexOf("?")!=-1){
    	 url+="&";
    	
     }else{
    	 url+="?";
     }
     url+="userId=<%=request.getAttribute("userId")%>&processDefinitionKey="+pdk;
    var obj = {};
    window.showModalDialog(url,obj,"dialogWidth=800px;dialogHeight=600px");
  });
});
</script>
</html>