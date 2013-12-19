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
	<jsp:include page="/fixflow/manager/top.jsp" flush="true"/>
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
							
						</span>
					</span>
				</span>
			</div>
			
			<!-- toolbar -->
			<div class="toolbar" style="padding-right:2px;text-align: right;margin-bottom: 4px;">
				<div class="btn-normal listBtn" btn-type="delopy" btn-scope=single><a href="#" >发布流程</a></div>
				<div class="btn-normal listBtn" btn-type="createFile" btn-scope=all><a href="#" >新建流程</a></div>
		  		<div class="btn-normal listBtn" btn-type="createFolder" btn-scope=all><a href="#" >新建文件夹</a></div>
		  		<div class="btn-normal listBtn" btn-type="rename" btn-scope=single><a href="#" >重命名</a></div>
		  		<div class="btn-normal listBtn" btn-type="delete" btn-scope=single><a href="#" >删除</a></div>
		  		<div class="btn-normal listBtn" btn-type="upload" btn-scope=all><a href="#" >上传</a></div>
	  		</div>
			
			<!-- thumbView -->
			<div class="view_plugin" style="background-color: #ffffff;">
			
			</div>
			
			<form style='display:none' id="uploadFile" action="/bpmcenter/FlowWebManagerServlet"  enctype="multipart/form-data" method="post" target="hidden_frame">
			  	<input type="file" id="upload" name="upload" />
			  	<input type="text" id="fileName" name="fileName" />
			  	<input type="text" id="path" name="path" />
			  	<input type="submit" id="uploadSubmit"/>
				<input type="hidden" name="method" value="writeFile2Address"/>
				<iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>
			</form>
		  </div>
		  
		</div> 
	</div>
</div>

<div id="createFileDialog" class="popup-A sl-shadow" style="position:absolute;display: none;">
	<div class="title"><h4>新建流程</h4></div>
		<div class="content">
			<table width="100%" border="0" class="fix-table2">
				<tbody>
					<tr>
          				<td width="70" class="title-r">流程ID</td>
          				<td><input id="flowFileId" type="text" id="text_0" name="text_0" class="fix-input" style="width:200px;"></td>
        			</tr>
        			<tr>
          				<td class="title-r">流程名称</td>
          				<td><input id="flowFileName"  type="text" id="text_1" name="text_0" class="fix-input" style="width:200px;"></td>
        			</tr>
			        <tr>
			         	<td>&nbsp;</td>
          				<td>
          					<div class="btn-normal fn-left m-r-30"><a href="#" id="okBtn">确 定</a></div>
              				<div class="btn-normal fn-left"><a href="#" id="closeBtn">取 消</a></div>
              			</td>
       				 </tr>
      			</tbody></table>
	</div>
</div>

<div id="delopyFlowDialog" class="popup-A sl-shadow" style="position:absolute; display: none; width: 880px; right: 80px;">
	<div class="title"><h4>发布流程</h4></div>
		<div class="content">
			<table width="100%" border="0" class="popup-tb">
				<thead>
					<tr>
						<th>编号</th>
						<th>名称</th>
						<th>版本</th>
					</tr>
				</thead>
				<tbody>
					
      			</tbody>
      			<tfoot>
      				<tr>
      					<td colspan="4">
      						<div class="btn-normal fn-left" style="float:none;"><a href="#" id="delopyOkBtn">发布</a></div>
      						<div class="btn-normal fn-left" style="float:none;"><a href="#" id="delopyUpdateBtn">更新</a></div>
              				<div class="btn-normal fn-left" style="float:none;"><a href="#" id="delopyCloseBtn">取 消</a></div>
              			</td>
      				</tr>
      			</tfoot>
      		</table>
	</div>
</div>
<div class="bg"></div>
</body>
</html>
