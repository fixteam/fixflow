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
<link rel="stylesheet" type="text/css" href="css/page.css">
<style>
a{text-decoration: none;}
.red_star{
   color:red;
}
</style>
<title>流程定义列表</title>


<script type="text/javascript">
	function deployment(){
		window.open("manager/deployment.jsp");
	}
	
	function deleteDeploy(){
		var deploymentId = "";
		var checkList = $("input:checked");
		for(var i=0;i<checkList.length;i++){
			if(i == 0){
				deploymentId = $(checkList[i]).attr("deploymentId");
			}else{
				deploymentId = deploymentId+","+$(checkList[i]).attr("deploymentId");
			}
		}
		$("#deploymentId").val(deploymentId);
		$("#action").val("deleteDeploy");
		document.forms[0].submit();
	}
	
	function updateDeploy(){
		var deploymentId = "";
		var checkList = $("input:checked");
		if(checkList.length >0){
		 	deploymentId = $(checkList[0]).attr("deploymentId");
		}
		window.open("manager/deployment.jsp?deploymentId="+deploymentId);
	}
	
	function download(){
		var deploymentId = "";
		var processDefinitionId = "";
		var checkList = $("input:checked");
		if(checkList.length >0){
		 	deploymentId = $(checkList[0]).attr("deploymentId");
		 	processDefinitionId = $(checkList[0]).attr("processDefinitionId");
		}
		$("#deploymentId").val(deploymentId);
		$("#action").val("download");
		$("#processDefinitionId").val(processDefinitionId);
		document.forms[0].submit();
	}
</script>
</head>

<body>
<form action="FlowManager" id="subForm">
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>
<div style="margin-top:1px;">
<!-- 左 -->
<div class="left" style="width:0px;">
    	
</div>
<!-- 右-->
	<div class="right" style="width:98%;">
	  <!-- 查 -->
	  <div class="search">
        	<table width="100%">
              <tr>
                <td class="title-r">流程名称：</td>
                <td><input type="text" id="text_3" name="processName" class="fix-input" style="width:160px;" value="${result.processName}"/></td>
                <td class="title-r">到达时间：</td>
                <td><input type="text" id="text_4" name="arrivalTimeS" class="fix-input" style="width:69px;" value="${result.arrivalTimeS}"/>
                 - <input type="text" id="text_5" name="arrivalTimeE" class="fix-input" style="width:69px;" value="${result.arrivalTimeE}"/></td>
                <td></td>
                <td><div class="btn-normal"><a href="#" onclick="$('#subForm').submit();">查 找<em class="arrow-small"></em></a></div></td>
              </tr>
            </table>
        </div>
	  <div>
	  
	  <input type="button" value="发布流程" onclick="deployment()" />
	  <input type="button" value="删除定义" onclick="deleteDeploy()" />
	  <input type="button" value="更新定义" onclick="updateDeploy()" />
	   <input type="button" value="下载定义" onclick="download()" />
		<!-- 表 -->
		<table style="width:100%;" class="fix-table">
		  <thead>
		   <th width="2%"></th>
		    <th width="30%">流程定义编号</th>
		    <th >流程定义名称</th>
		    <th width="5%">流程版本</th>
		    <th width="8%">流程分类</th>
		  </thead>
		  <tbody>
		   <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		     <td><input type="checkbox" deploymentId="${dataList.deploymentId}" processDefinitionId="${dataList.processDefinitionId}" /></td>
		      <td>${dataList.processDefinitionId}</td>
		      <td>${dataList.processDefinitionName}</td>
		      <td>${dataList.version}</td>
		      <td>${dataList.category}</td>
		    </tr>
		    </c:forEach>
		  </tbody>
	    </table>
		<!-- 分页 -->	    
	   <jsp:include page="../center/page.jsp" flush="true"/>
	    </div>
	  </div>
	</div>
</div>
<!-- 隐藏参数部分 -->
	<input type="hidden" name="deploymentId" id="deploymentId" value=""/>
	<input type="hidden" name="action" id="action" value="processDefinitionList"/>
	<input type="hidden" name="processDefinitionId" id="processDefinitionId" value=""/>
</form>
</body>

<script>
/*  
 * "userId" 用户编号
 * "pdkey" 流程编号
 * "pageIndex" 第几页
 * "rowNum" 有几行
 * "agentUserId" 有几行
 * "agentType" 0我代理别人，1别人委托给我
 * "title" 查询主题
 * "processVeriy" 查询变量
 * "arrivalTimeS" 到达时间开始
 * "arrivalTimeE" 到达时间结束
 * "initor" 发起人
 * @param @return
 * "dataList" 数据列表
 * "pageNumber" 总行数
 * "agentUsers" 代理用户
 * "agentToUsers" 委托用户
 * "pageIndex" 第几页
 * "rowNum" 有几行
 */
$(function(){
  var agentType = $("input[name=agentType]").val();
  var userId = $("input[name=userId]").val();
  $("a[name=myTask]").click(function(){
    $("#agentUserId").val();
    $("#agentType").val();
    $("#subForm").submit();
  });
  $("a[name=agentUsers]").click(function(){
    var userId = $(this).attr("userId");
    $("#agentUserId").val(userId);
    $("#agentType").val('1');
    $("#subForm").submit();
  });
  $("a[name=agentToUsers]").click(function(){
    var userId = $(this).attr("userId");
    $("#agentUserId").val(userId);
    $("#agentType").val('0');
    $("#subForm").submit();
  });
  $("a[name=flowGraph]").click(function(){
    var pdk = $(this).attr("pdk");
    var pii = $(this).attr("pii");
    var obj = {};
    window.showModalDialog("FlowCenter?action=getTaskDetailInfo&processDefinitionKey="+pdk+"&processInstanceId="+pii,obj,"dialogWidth=800px;dialogHeight=600px");
  });
  $("a[name=doTask]").click(function(){
    var tii = $(this).attr("tii");
    var pdk = $(this).attr("pdk");
    var pii = $(this).attr("pii");
    var bizKey = $(this).attr("bk");
    
    var obj = {};
    window.showModalDialog("FlowCenter?action=doTask&taskId="+tii+"&processInstanceId="+pii+"&bizKey="+bizKey+"&processDefinitionKey="+pdk,obj,"dialogWidth=800px;dialogHeight=600px");
  });
});
</script>
</html>