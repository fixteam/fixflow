<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程实例管理</title>
<jsp:include page="head.jsp" flush="true"/>
<script type="text/javascript">

	$('#checkall').click(function(){
    	var tii = $(this).attr("checked");
    	var checkboxs = $("input[name=checked]");
    	if(tii=="checked"){	
    		for(var i=0;i<checkboxs.length;i++) 
			{ 
				$(checkboxs[i]).attr("checked",'true');
			} 
    	}else{
    		for(var i=0;i<checkboxs.length;i++) 
			{ 
				$(checkboxs[i]).removeAttr("checked");
			} 
    	}
  	});
  	function updateVariables(){
  		if(checkButton("updateVariables")){
  			return false;
  		}
 		var checkboxs = $("input[name=checked]");
 		var id = "";
 		
 		for(var i=0;i<checkboxs.length;i++) 
		{ 
			if(checkboxs[i].checked==true){
				id = $(checkboxs[i]).val();
				break;
			}
		}
		if(id!=""){
			var obj = {};
		    window.open("FlowManager?action=toProcessVariable&processInstanceId="+id);
	    }
  	}
  	
  	function updateToken(){
  		if(checkButton("updateToken")){
  			return false;
  		}
 		var checkboxs = $("input[name=checked]");
 		var id = "";
 		
 		for(var i=0;i<checkboxs.length;i++) 
		{ 
			if(checkboxs[i].checked==true){
				id = $(checkboxs[i]).val();
				break;
			}
		}
		if(id!=""){
			var obj = {};
		    window.open("FlowManager?action=processTokenList&processInstanceId="+id);
	    }
  	}
  	function doSuspend(){
  		if(checkButton("doSuspend")){
  			return false;
  		}
  		doProcess("suspendProcessInstance");
  	}
  	function doContinue(){
  		if(checkButton("doContinue")){
  			return false;
  		}
  		doProcess("continueProcessInstance");
  	}
  	function doTerminat(){
  		if(checkButton("doTerminat")){
  			return false;
  		}
  		doProcess("terminatProcessInstance");
  	}
  	function doDelete(){
  		if(checkButton("doDelete")){
  			return false;
  		}
  		doProcess("deleteProcessInstance");
  	}
  	function setHis(){
  		if(checkButton("setHis")){
  			return false;
  		}
  		doProcess("setHis");
  	}
  	function doProcess(action){
 		var checkboxs = $("input:checked[name=checked]");
 		var id = "";
 		if(checkboxs.length!=1){
 			alert("请选中一个流程实例！");	
 			return;
 		}else{
	   		for(var i=0;i<checkboxs.length;i++) 
			{ 
				if(i!=0){
					id += ',';
				}
				id += $(checkboxs[i]).val();
			}
 		}
 		if(confirm("确认提交?")){
			$("#action").val(action);
			$("#operProcessInstanceId").val(id);
			$("#subForm").submit();
 		}
  	}
  	
  	$(function(){
		Fix.Util.ClickTr(null,false,true,0,function($table){
		  var flag = true;
		  $("tbody tr.selected",$table).each(function(){
		    var state = $("td:eq(10)",$(this)).html();
		    //alert(state);
		    if(state.trim()=="运行中" ||state.trim()=="暂停"){
		      flag = false;
		    }
		  });
		  if(!flag){
		    $("div#setHis").removeClass("btn-normal").addClass("btn-disable");
		  }
		});
		
	  $("a[name=flowGraph]").click(function(){
		    var pdk = $(this).attr("pdk");
		    var pii = $(this).attr("pii");
		    var obj = {};
		    window.open("FlowCenter?action=getTaskDetailInfo&processDefinitionKey="+pdk+"&processInstanceId="+pii);
		  });
	  
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
	  
	  var status = '${result.status}';
	  if(status!='')
	  	$("#status").val(status);
	});
  	
  	
  	function clearInfo(){
  		$("#processDefinitionKey").val("");
  		$("#processInstanceId").val("");
  		$("#subject").val("");
  		$("#bizKey").val("");
  		$("#initor").val("");
  		$("#initorName").val("");
  		$("#status").val("");
  	}
</script>
</head>

<body>
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>
<div class="center-panel">
<form id="subForm" method="post" action="FlowManager">
<!-- 左 -->
    <div class="right">
    <!-- 隐藏参数部分 -->
		<input type="hidden" id="operProcessInstanceId" name="operProcessInstanceId">
    	<input type="hidden" id="action" name="action" value="processManageList"/>
    	<div class="search">
        	<table>
              <tr>
                <td class="title-r">流程名称：</td>
                <td><input type="text" id="processDefinitionKey" name="processDefinitionKey" class="fix-input" style="width:160px;" value="${result.processDefinitionKey}"/></td>
                <td class="title-r">实例编号：</td>
                <td><input type="text" id="processInstanceId" name="processInstanceId" class="fix-input" style="width:160px;" value="${result.processInstanceId}"/></td>
                <td class="title-r">主题：</td>
                <td style="width:200px;"><input type="text" id="subject" name="subject" class="fix-input" style="width:160px;" value="${result.subject}"/></td>
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
                <td class="title-r">业务数据：</td>
                <td><input type="text" id="bizKey" name="bizKey" class="fix-input" style="width:160px;" value="${result.bizKey}"/></td>
                <td class="title-r">发起人：</td>
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
                <td class="title-r">状态：</td>
                <td>
                <select id="status" name="status" class="fix-input" style="width:172px;">
                  <option value ="">请选择</option>
				  <option value ="SUSPEND">暂停</option>
				  <option value ="RUNNING">运行中</option>
				  <option value ="COMPLETE">完成</option>
				  <option value ="TERMINATION">终止</option>
				</select>
                </td>
                <td></td>
              </tr>
            </table>
        </div>
        <div id="toolbar" style="padding-right:2px;text-align: right;margin-bottom: 4px;">
        	<div id="doSuspend" class="btn-normal" data-scope=single style="display:inline-block;margin-left:5px;"><a href="#" onclick="doSuspend();">暂停</a></div>
        	<div id="doContinue" class="btn-normal" data-scope=single style="display:inline-block;margin-left:5px;"><a href="#" onclick="doContinue();">恢复</a></div>
           	<div id="doTerminat" class="btn-normal" data-scope=single style="display:inline-block;margin-left:5px;"><a href="#" onclick="doTerminat();">作废</a></div>
           	<div id="doDelete" class="btn-normal" data-scope=single style="display:inline-block;margin-left:5px;"><a href="#" onclick="doDelete();">删除</a></div>
        	<div id="updateVariables" class="btn-normal" data-scope=single style="display:inline-block;margin-left:5px;"><a href="#" onclick="updateVariables();">变量管理</a></div>
           	<div id="updateToken" class="btn-normal" data-scope=single style="display:inline-block;margin-left:5px;"><a href="#" onclick="updateToken();">令牌管理</a></div>
          	<div id="setHis" class="btn-normal" data-scope=multi style="display:inline-block;margin-left:5px;"><a href="#" onclick="setHis();">归档</a></div>
        </div>
        <div class="content">
        	<table width="100%" class="fix-table">
              <thead>
              	<th width="30">&nbsp;</th>
                <th width="30">序号</th>
                <th width="">实例编号</th>
                <th>流程名称</th>
                <th width="">主题</th>
                <th width="">业务数据</th>
                <th width="130">启动时间</th>
                <th width="130">更新时间</th>
                <th width="">发起人</th>
                <th width="">当前步骤</th>
                 <th width="">运行状态</th>
                  <th width="60">操作</th>
              </thead>
		    <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		      <td class="num"><input type="radio" name="checked" value="${dataList.processInstanceId}"></td>
		     <td style="text-align:center;">${(index.index+1)+pageInfo.pageSize*(pageInfo.pageIndex-1)}</td>
		      <td>${dataList.processInstanceId}</td>
		      <td>${dataList.processDefinitionName}</td>
		      <td>${dataList.subject}</td>
		      <td>${dataList.BIZ_KEY}</td>
		      <td class="time"><fmt:formatDate value="${dataList.startTime}" type="both"/></td>
				<td class="time"><fmt:formatDate value="${dataList.updateTime}" type="both"/></td>
				 <td>${dataList.startAuthorName}</td>
				<td>${dataList.nowNodeInfo}</td>
				<td>
					<c:if test="${dataList.instanceStatus == 'SUSPEND'}" var="runStatue">暂停</c:if>
					<c:if test="${dataList.instanceStatus == 'RUNNING'}" var="runStatue">运行中</c:if>
					<c:if test="${dataList.instanceStatus == 'COMPLETE'}" var="runStatue">完成</c:if>
					<c:if test="${dataList.instanceStatus == 'TERMINATION'}" var="runStatue">终止</c:if>
				</td>
				<td><a name="flowGraph" href="#"
				pii="${dataList.processInstanceId}"
				pdk="${dataList.processDefinitionKey}">查看</a></td>
		    </tr>
		    </c:forEach>
            </table>
			<jsp:include page="../common/page.jsp" flush="true"/>
        </div>
    </div>
<!-- 分页 -->
	</form>
</div>
</div>

</body>
</html>
