<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务管理</title>
<jsp:include page="head.jsp" flush="true"/>
<script type="text/javascript">
function doSuspend(){
	doProcess("doTaskSuspend");
}

function doResume(){
	doProcess("doTaskResume");
}

function doTransfer(){
	var obj = {
			  type:"user"
			};
	var d = FixSelect(obj);
	if(d!=null){
		$("#transferUserId").val(d[0].USERID);
		doProcess("doTaskTransfer");
	}
}

function doRollBackNode(){
	var checkboxs = $("input:checked[name=checked]");
	var id = "";
	if(checkboxs.length!=1){
		alert("请选中一个流程实例！");	
		return;
	}else{
		id = $(checkboxs[0]).val();
	}
	var obj = {
			  type:"node",
			taskId:id
			};
	var d = FixSelect(obj);
	if(d!=null){
		$("#rollBackNodeId").val(d[0].nodeId);
		doProcess("doTaskRollBackNode",id);
	}
}

function doProcess(action,taskId){
		if(taskId==null){
	 		var checkboxs = $("input:checked[name=checked]");
	 		if(checkboxs.length!=1){
	 			alert("请选中一个流程实例！");	
	 			return;
	 		}else{
	 			taskId = $(checkboxs[0]).val();
	 		}
 		}
 		
 		if(confirm("确认提交?")){
			$("#action").val(action);
			$("#taskId").val(taskId);
			$("#subForm").submit();
		}
}
$(function(){
	$("#selectUser").click(function(){
		var obj = {
		  type:"user"
		};
		var d = FixSelect(obj);
		var userId = d[0].USERID;
		var userName = d[0].USERNAME;
		$("#initor").val(userId);
		$("#initorName").val(userName);
	});
  $("a[name=flowGraph]").click(function(){
    var pdk = $(this).attr("pdk");
    var pii = $(this).attr("pii");
    var obj = {};
    window.open("FlowCenter?action=getTaskDetailInfo&processDefinitionKey="+pdk+"&processInstanceId="+pii);
  });
    $("a[name=doTask]").click(function(){
    var tii = $(this).attr("tii");
    var pdk = $(this).attr("pdk");
    var pii = $(this).attr("pii");
    var bizKey = $(this).attr("bk");
    
    var obj = {};
    var formUrl = $(this).attr("formUri");//"FlowCenter?action=startOneTask";
    var url = formUrl;
    if(formUrl.indexOf("?")!=-1){
   	 url+="&";
   	
    }else{
   	 url+="?";
    }
    url+="taskId="+tii+"&processInstanceId="+pii+"&bizKey="+bizKey+"&processDefinitionKey="+pdk,obj,"dialogWidth=800px;dialogHeight=600px";
   	window.open(url);
  });
	Fix.Util.ClickTr(null,true,true,0);
});
function clearInfo(){
 		$("#title").val("");
 		$("#processDefinitionKey").val("");
 		$("#bizKey").val("");
 		$("#initor").val("");
 		$("#initorName").val("");
 		$("#arrivalTimeS").val("");
 		$("#arrivalTimeE").val("");
 	}
</script>
</head>

<body>
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>

<div class="center-panel">
<form id="subForm" method="post" action="FlowManager">
    <div class="right">
    <!-- 隐藏参数部分 -->
    	<input type="hidden" id="action" name="action" value="taskInstanceList"/>
    	<input type="hidden" id="transferUserId" name="transferUserId"/> 
    	<input type="hidden" id="rollBackNodeId" name="rollBackNodeId"/> 
    	<input type="hidden" id="taskId" name="taskId">
    	<div class="search">
        	<table>
              <tr>
                <td class="title-r">任务主题：</td>
                <td><input type="text" id="title" name="title" class="fix-input" style="width:160px;" value="${result.title}"/></td>
                <td class="title-r">流程名称：</td>
                <td><input type="text" id="processDefinitionKey" name="processDefinitionKey" class="fix-input" style="width:160px;" value=""/></td>
                <td class="title-r">单 据 号：</td>
                <td style="width:200px;"><input type="text" id="bizKey" name="bizKey" class="fix-input" style="width:160px;" value="${result.bizKey}"/></td>
                <td>
                <table style="margin:0">
                <tr>
                <td>
                <div class="btn-normal"><a href="#" onclick="$('#subForm').submit();">查 找</a></div>
                </td>
                <td>
                <div class="btn-normal"><a href="#" onclick="clearInfo();">清空</a></div>
				</td>                
                </tr>
                </table>
                </td>
              </tr>
              <tr>
                <td class="title-r">发 起 人：</td>
                <td>
                <table style="margin:0">
                <tr>
                <td>
                <input type="hidden" id="initor" name="initor" class="fix-input" value="${result.initor}"/>
                <input type="text" id="initorName" readonly="true" name="initorName" class="fix-input" style="width:94px;" value="${result.initorName}"/>
                </td>
                <td>
                <div class="btn-normal">
										<a href="#" onclick="" id="selectUser">选择<em
											class="arrow-small"></em></a>
				</div>
				</td>
				</tr>
				</table>
                </td>
                <td class="title-r">到达时间：</td>
                <td><input type="text" id="arrivalTimeS" name="arrivalTimeS" class="fix-input" style="width:69px;" value="${result.arrivalTimeS}" onClick="WdatePicker()"/>
                 - <input type="text" id="arrivalTimeE" name="arrivalTimeE" class="fix-input" style="width:69px;" value="${result.arrivalTimeE}" onClick="WdatePicker()"/></td>
                <td></td>
                <td></td>
              	<td></td>
              </tr>
            </table>
        </div>
        <div id="toolbar" style="padding-right:2px;text-align: right;margin-bottom: 4px;">
        	<div class="btn-normal" data-scope=single style="display:inline-block;margin-left:5px;"><a href="#" onclick="doSuspend();">暂停</a></div>
            <div class="btn-normal" data-scope=single style="display:inline-block;margin-left:5px;"><a href="#" onclick="doResume();">恢复</a></div>
            <div class="btn-normal" data-scope=single style="display:inline-block;margin-left:5px;"><a href="#" onclick="doTransfer();">转发</a></div>
            <div class="btn-normal" data-scope=single style="display:inline-block;margin-left:5px;"><a href="#" onclick="doRollBackNode();">退回-节点</a></div>
            <div class="btn-normal" data-scope=single style="display:inline-block;margin-left:5px;"><a href="#" onclick="">退回-步骤</a></div>
        </div>
        <div class="content">
        	<table width="100%" class="fix-table">
							<thead>
								<th width="30"></th>
								<th width="30">&nbsp;</th>
								<th width="30"></th>
								<th>流程名称</th>
								<th>单据号</th>
								<th>任务主题</th>
								<th>发起人</th>
								<th>发起时间</th>
								<th>当前步骤</th>
								<th width="160">到达时间</th>
								<th width="60">流程状态</th>
								<th width="60">运行状态</th>
							</thead>
							<c:forEach items="${result.dataList}" var="dataList"
								varStatus="index">
								<tr isSuspended = ${dataList.isSuspended}>
								<td class="num"><input type="checkbox" name="checked" value="${dataList.taskInstanceId}"></td>
									<td class="num"><c:out value="${index.index+1}" /></td>

									<td><img src="icon/${dataList.PI_START_AUTHOR}_small.png"
										height="30" width="30" alt="头像"
										onerror="miniImgNotFound('${pageContext.request.contextPath}',this);"></td>
									<td>${dataList.processDefinitionName}</td>
									<td>${dataList.bizKey}</td>
									<td><a name="doTask" href="#"
										formUri="${dataList.formUri}" tii="${dataList.taskInstanceId}"
										pii="${dataList.processInstanceId}" bk="${dataList.bizKey}"
										pdk="${dataList.processDefinitionKey}">${dataList.description}</a>
									</td>
									<td>${dataList.userName}</td>
									<td class="time"><fmt:formatDate value="${dataList.PI_START_TIME}"
											type="both" /></td>
									<td>${dataList.nowProc}</td>
									<td class="time"><fmt:formatDate value="${dataList.createTime}"
											type="both" />
									</td>
									<td><a name="flowGraph" href="#"
										pii="${dataList.processInstanceId}"
										pdk="${dataList.processDefinitionKey}">查看</a></td>
									<td>
										<c:if test="${dataList.isSuspended == true}" var="runStatue">暂停</c:if>
										<c:if test="${dataList.isSuspended == false}" var="runStatue">运行中</c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
        </div>
    </div>
<!-- 分页 -->	    
	    <div id="page">
	      <jsp:include page="../common/page.jsp" flush="true"/>
	    </div>

	</form>
</div>
</div>
 
</body>
</html>
