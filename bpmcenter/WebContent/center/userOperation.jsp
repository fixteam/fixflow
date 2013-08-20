<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="js/jquery.js"></script>
	<title>用户信息</title>
	<style type="text/css" media="screen">
  html, body { height:100%; background-color: #ffffff;}
  #flashContent { width:100%; height:100%; }
  	</style>
</head>
<body>
	<form action="FlowCenter" enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<td colspan="2">
					<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
					codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0"
					WIDTH="650" HEIGHT="450" id="myMovieName">
					<PARAM NAME=movie VALUE="js/flash_avatar/avatar.swf">
					<PARAM NAME=quality VALUE=high>
					<PARAM NAME=bgcolor VALUE=#FFFFFF>
					<param name="flashvars" value="imgUrl=${result.icon}&uploadUrl=js/flash_avatar/upfile.jsp&uploadSrc=false" />
					<EMBED src="js/flash_avatar/avatar.swf" quality=high bgcolor=#FFFFFF WIDTH="650" HEIGHT="450" wmode="transparent" flashVars="imgUrl=${result.icon}&uploadUrl=js/flash_avatar/upfile.jsp&uploadSrc=false"
					NAME="myMovieName" ALIGN="" TYPE="application/x-shockwave-flash" allowScriptAccess="always"
					PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer">
					</EMBED>
					</OBJECT>
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

		</table>
	</form>
  </div>
	
	
</body>
</html>