<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发起流程</title>
<jsp:include page="head.jsp" flush="true"/>
<style>
a{text-decoration: none;}
</style>
</head>
<body>
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>
<div class="center-panel">
	<div class="type-box">
		<c:forEach items="${result}" var="row" varStatus="status">
	    	<div class="type">
	        	<h1>${row.key}</h1>
	        	<c:forEach items="${row.value}" var="tmp" varStatus="status">
	        		<a href="#" formUrl="${tmp.formUrl}" processDefinitionKey="${tmp.processDefinitionKey}"><img src="images/temp/type-01.jpg" />${tmp.processDefinitionName}</a>
	        	</c:forEach>
	    	</div>
		</c:forEach>
    </div>
</div>
</div>
</body>
<script>
$(function(){
  $("a[processDefinitionKey]").click(function(){
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