<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>头像修改</title>
<jsp:include page="../center/head.jsp" flush="true"/>
</head>
<body>
<div class="popup">
    <div class="upload">
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
    </div>
</div>
</body>
</html>