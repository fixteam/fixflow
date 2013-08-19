<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>流程发布</title>
<%
	String deploymentId = request.getParameter("deploymentId");
	if(deploymentId == null){
		deploymentId = "";
	}
%>
</head>
<body>
<form action="../FlowManager" method="post" ENCTYPE="multipart/form-data">
<input type="file" name="ProcessFile" />
<input type="text" name="deploymentId" id="deploymentId" value="<%=deploymentId%>">
<input type="submit" value="发布" />
<input type="hidden" name="action" value="deploy">

</form>
</body>
</html>