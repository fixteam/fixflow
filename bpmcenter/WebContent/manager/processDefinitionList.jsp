<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<style>
a{text-decoration: none;}

.red_star{
   color:red;
}
.pagearea{
   margin-top:20px;
   float:right;
   text-align:right;
   width:100%;
   font-size:12px;
   
}
.pagearea .qp{
   border:#AAAADD solid 1px;
   width:60px;
   height:10px;
   margin-right:3px;
   margin-left:3px;
   text-align:center;
}
.pagearea .disqp{
   border:#EEEEEE solid 1px;
   width:60px;
   height:10px;
   margin-right:3px;
   margin-left:3px;
   color:#EEEEEE;
}
.pagearea a {
   border:#AAAADD solid 1px;
   width:25px;
   height:10px;
   margin-right:3px;
   margin-left:3px;
   text-align:center;
}
.pagearea .point{
   width:25px;
   height:10px;
   margin-right:3px;
   margin-left:3px;
}
.pagearea .focuspage{
  border:#FD6D01 solid 1px;
  background-color:#FFEDE1;
  color:#FD6D01;
  width:25px;
  height:10px;
  margin-right:3px;
  margin-left:3px;
  font-weight:bold;
  text-align:center;
}
.pagearea info{
  color:#666666;
}
.pagearea a{
  TEXT-DECORATION:none;
  color:#3366CC;
}
</style>
<title>流程定义列表</title>
</head>

<body>
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>
<div style="margin-top:1px;">
<!-- 左 -->
<div class="left" style="width:0px;">
    	
</div>
<!-- 右-->
	<div class="right" style="width:99%;">
	  <!-- 查 -->
	  <div id="search">
	 	<table width="100%">
              <tr>
                <td class="title-r">任务主题：</td>
                <td><input type="text" id="text_0" name="title" class="fix-input" style="width:160px;" value="${result.title}"/></td>
                <td class="title-r">流程变量：</td>
                <td><input type="text" id="text_1" name="text_1" class="fix-input" style="width:160px;" value=""/></td>
                <td class="title-r">单 据 号：</td>
                <td><input type="text" id="text_2" name="bizKey" class="fix-input" style="width:160px;" value="${result.bizKey}"/></td>
              </tr>
              <tr>
                <td class="title-r">发 起 人：</td>
                <td><input type="text" id="text_3" name="initor" class="fix-input" style="width:160px;" value="${result.initor}"/></td>
                <td class="title-r">到达时间：</td>
                <td><input type="text" id="text_4" name="arrivalTimeS" class="fix-input" style="width:69px;" value="${result.arrivalTimeS}"/>
                 - <input type="text" id="text_5" name="arrivalTimeE" class="fix-input" style="width:69px;" value="${result.arrivalTimeE}"/></td>
                <td><input type="submit"/></td>
                <td>&nbsp;</td>
              </tr>
            </table>
         </div>
	  <div>
		<!-- 表 -->
		<table style="width:100%;" class="fix-table">
		  <thead>
		    <th>流程定义编号</th>
		    <th>流程定义名称</th>
		    <th>流程版本</th>
		    <th>流程分类</th>
		  </thead>
		  <tbody>
		   <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		      <td>${dataList.processDefinitionId}</td>
		      <td>${dataList.processDefinitionName}</td>
		      <td>${dataList.version}</td>
		      <td>${dataList.category}</td>
		    </tr>
		    </c:forEach>
		  </tbody>
	    </table>
		<!-- 分页 -->	    
	    <div id="page">
	    </div>
	  </div>
	</div>
</div>
</div>
<!-- 隐藏参数部分 -->
<input type="hidden" name="userId" value="<c:out value="${result.userId}"/>">
<input type="hidden" name="pageIndex" value="<c:out value="${result.pageIndex}"/>">
<input type="hidden" name="rowNum" value="<c:out value="${result.rowNum}"/>">
<input type="hidden" name="type" value="<c:out value="${result.action}"/>">
</body>
</html>