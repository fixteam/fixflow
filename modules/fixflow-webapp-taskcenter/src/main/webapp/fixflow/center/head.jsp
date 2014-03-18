<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%    
String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
pageContext.setAttribute("basePath",basePath); 
%>   
<link rel="shortcut icon" href="${pageContext.request.contextPath}/fixflow/images/favicon.ico" type="image/x-icon">
<title>${applicationScope.appInfo["product.title"]}</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/fixflow/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/fixflow/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/fixflow/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/fixflow/js/select.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fixflow/css/reset.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fixflow/css/global.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fixflow/css/index.css">
<link id="color" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fixflow/css/color_red.css">
<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fixflow/css/popup.css"> -->
<script type="text/javascript">
var message = "${errorMsg}";
if(message!=''){
	alert(message);
}
</script>
