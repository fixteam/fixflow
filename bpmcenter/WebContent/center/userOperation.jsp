<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script src="js/Jcrop/jquery.min.js"></script>
	<script src="js/Jcrop/jquery.Jcrop.js"></script>
	<script type="text/javascript" src="center/userOperation.js"></script>
	<link rel="stylesheet" href="js/Jcrop/css/jquery.Jcrop.css" type="text/css" />
	<title>用户信息</title>
	
	<style type="text/css">
	#target{
		max-width:300px;
		max-height:300px;
		min-width:150px;
		min-height:150px;
	}
	
	/* Apply these styles only when #preview-pane has
	   been placed within the Jcrop widget */
	.jcrop-holder #preview-pane {
	  display: block;
	  position: absolute;
	  z-index: 2000;
	  top: 10px;
	  right: -280px;
	  padding: 6px;
	  border: 1px rgba(0,0,0,.4) solid;
	  background-color: white;
	
	  -webkit-border-radius: 6px;
	  -moz-border-radius: 6px;
	  border-radius: 6px;
	
	  -webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	  -moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	  box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	}
	
	/* The Javascript code will set the aspect ratio of the crop
	   area based on the size of the thumbnail preview,
	   specified here */
	#preview-pane .preview-container {
	  width: 250px;
	  height: 250px;
	  overflow: hidden;
	}
	
	</style>
</head>
<body>
	<form action="FlowCenter" enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<input type="hidden" name="action" value="updateUserIcon"/>
				<td><img src="${result.icon}" id="target"></img></td>
				<td><div id="preview-pane">
					    <div class="preview-container">
					      <img src="${result.icon}" class="jcrop-preview" alt="Preview"></img>
					    </div>
					</div></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="file" name="icon"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">${result.user.userId}</td>
			</tr>
			<tr>
				<td colspan="2">${result.user.userName}</td>
			</tr>
			<tr>
				<td colspan="2">组</td>
			</tr>
			<tr>
				<td colspan="2"><table>
				<tr>
				<td>id</td>
				<td>name</td>
				</tr>
				<c:forEach items="${result.groups}" var="row" varStatus="status">
					<tr>
					<td>${row.groupId}</td>
					<td>${row.groupName}</td>
					</tr>
				</c:forEach>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input id="btnconfirm" type="submit" value="保存图片"/>
					<input id="fn_scaled" type="button" value="裁剪"/>
					<input type="text" name="scaled" id="scaled">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>