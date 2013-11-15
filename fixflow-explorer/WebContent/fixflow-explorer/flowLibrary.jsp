<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="/bpmcenter/fixflow/images/favicon.ico" type="image/x-icon">
<title>Fixflow</title>
<script type="text/javascript" src="/bpmcenter/fixflow/js/jquery.js"></script>
<script type="text/javascript" src="/bpmcenter/fixflow/js/common.js"></script>
<script type="text/javascript" src="/bpmcenter/fixflow/js/select.js"></script>
<link rel="stylesheet" type="text/css" href="/bpmcenter/fixflow/css/reset.css">
<link rel="stylesheet" type="text/css" href="/bpmcenter/fixflow/css/global.css">
<link rel="stylesheet" type="text/css" href="/bpmcenter/fixflow/css/index.css">
<link id="color" rel="stylesheet" type="text/css" href="/bpmcenter/fixflow/css/color_blue.css">

<link id="color" rel="stylesheet" type="text/css" href="/bpmcenter/fixflow-explorer/css/xtheme-specific.css">
<link rel="stylesheet" href="/bpmcenter/fixflow-explorer/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/bpmcenter/fixflow-explorer/js/ztree.js"></script>
<script type="text/javascript" src="/bpmcenter/fixflow-explorer/js/flowLibrary.js"></script>
<script type="text/javascript" src="/bpmcenter/fixflow-explorer/js/utils.js"></script>

<style>
	div.right { position: relative; background-color: #f2f2f2;}
	div.center-panel { background-color: #FFFFFF; }
	div.content { height: 100%; background-color: #f2f2f2;}
	.listBtn { display:inline-block;margin-left:5px; }
	.x-breadcrumb-wrapper .details a { cursor: pointer;font-weight: bold;padding: 15px 5px; line-height:40px; }
	div.dialog { position: absolute; top: 250px; left: 100px; }
	div.block { position: absolute; width: 100%; height: 100%; background-color: gray;}
</style>
</head>
<body>
<div class="main-panel">
	<div class="header">
	    <div class="top-right">
	      <ul>
	        <li><a id="updateCache" href="#">更新缓存</a></li>
	        <li><a href="#" onclick="updateMyself();">${sessionScope.LOGIN_USER_NAME}</a></li>
	        <li><a href="LoginServlet?doLogOut=true">退出</a></li>
	      </ul>
	    </div>
	    <div class="menu">
			<div class="logo"></div>
        	<ul>
        		<li><a id="processDefinitionList" href="FlowManager?action=processDefinitionList"><h1>定义管理</h1><h4>definition</h4></a></li>
       			<li><a id="processManageList" href="FlowManager?action=processManageList"><h1>实例管理</h1><h4>instance</h4></a></li>
       			<li><a id="taskInstanceList" href="FlowManager?action=taskInstanceList"><h1>任务管理</h1><h4>task manager</h4></a></li>
       			<li><a id="UserGroup" href="FlowManager?action=getUserList"><h1>组织机构</h1><h4>organization</h4></a></li>
        		<li><a id="jobManager" href="FlowManager?action=getJobList"><h1>定时任务</h1><h4>schedule</h4></a></li>
        		<li><a href="#"><h1>系统配置</h1><h4>system</h4></a></li>
        		<li><a id="flowLibrary" href="FlowManager?action=flowLibrary" class="select"><h1>流程梳理库</h1><h4>Flow Library</h4></a></li>
        	</ul>
	    </div>
	</div>
	<div style="margin-top:1px;" class="center-panel">
		<!-- 左侧树 -->
		<div class="left">
			<div class="left-nav-box">
			<div class="left-nav m-top">
				<h1>流程梳理库</h1>
			</div>
		   	<ul class="ztree" id="folderTree"></ul>
		   	</div>
		</div>
		
		<!-- 右侧展示区 -->
		<div class="right">
		  <div class="content">
		  
		  	<!-- breadcrumb -->
		  	<div class="x-panel-body x-panel-body-noheader x-panel-body-noborder" id="ext-gen465" style="height: 30px;">
				<span class="x-breadcrumb-wrapper" id="ext-gen466">
					<span id="ext-gen467">
						<span class="details">
							<span>»</span>
							<a rel="/directory/09a3c14509a44992a287ec193ebd06b9"> 公有流程</a>
							<span>»</span> 
							<a rel="/directory/e7bd5482c48245d39624998afb6a8802"> 我的公有流程</a>  
						</span>
					</span>
				</span>
			</div>
			
			<!-- toolbar -->
			<div class="toolbar" style="padding-right:2px;text-align: right;margin-bottom: 4px;">
		  		<div class="btn-normal listBtn" btn-type="createFolder" btn-scope=all><a href="#" >新建文件夹</a></div>
		  		<div class="btn-normal listBtn" btn-type="rename" btn-scope=single><a href="#" >重命名</a></div>
		  		<div class="btn-normal listBtn" btn-type="delete" btn-scope=single><a href="#" >删除</a></div>
	  		</div>
			
			<!-- thumbView -->
			<div class="view_plugin" style="background-color: #ffffff;">
			
			  <!-- <div class="thumb-wrap">
				<div class="thumb">
					<img src="images/nuvola/64x64/filesystems/folder_grey.png" title="End-to-End processes1" class="x-thumb-icon">
				</div>
				<span class="editable">
					文件夹1
				</span>
			  </div>
			  
			  <div class="thumb-wrap">
				<div class="thumb">
					<img src="images/nuvola/64x64/filesystems/folder_grey.png" title="End-to-End processes2" class="x-thumb-icon">
				</div>
				<span class="editable">
					文件夹2
				</span>
			  </div>
			  
			  <div class="thumb-wrap " storeindex="4" unselectable="on">
			  	<div class="thumb model"></div>
				<span class="x-editable" title="">报销流程v1.0</span>
			  </div> -->
			  
			</div>
		  </div>
		  
		  <!-- Block 
		  <div class="block"></div> -->
		</div> 
	</div>
</div>
</body>
</html>
