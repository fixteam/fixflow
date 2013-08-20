<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>待办任务</title>
<jsp:include page="head.jsp" flush="true"/>

</head>

<body>
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>
<div class="center-panel">
        <ul>
        <li><a id="processInstanceList" href="FlowManager?action=processManageList&processAction=processInstanceList"><h1>定义管理</h1><h4>start flow</h4></a></li>
        <li><a id="processManageList" href="FlowManager?action=processManageList&processAction=processInstanceList"><h1>实例管理</h1><h4>schedule</h4></a></li>
        </ul>
</div>

<div class="center-panel">
<form id="subForm" method="post" action="FlowManager">
<!-- 左 -->
	<div class="left">
        <div class="message">
        	<div class="title"><a href="#"><em class="icon-message"></em>消息中心</a></div>
        	<div class="message-content">
            	<div class="msg"><img src="images/temp/user01.jpg" />张飞：今天还没吃午饭！<div class="time">一小时前</div></div>
             	<div class="msg"><img src="images/temp/user01.jpg" />曹操：煮酒论英雄！谁一起吃饭啊<div class="time">一小时前</div></div>
            	<div class="msg"><img src="images/temp/user01.jpg" />张飞：今天还没吃午饭！<div class="time">一小时前</div></div>
            	<div class="msg"><img src="images/temp/user01.jpg" />张飞：今天还没吃午饭！<div class="time">一小时前</div></div>
            	<div class="msg"><img src="images/temp/user01.jpg" />张飞：今天还没吃午饭！<div class="time">一小时前</div></div>
       	</div>
        </div> 
    </div>
    <div class="right">
    <!-- 隐藏参数部分 -->
		<input type="hidden" name="agentUserId" value="<c:out value="${result.agentUserId}"/>">
		<input type="hidden" name="agentType" value="<c:out value="${result.agentType}"/>">
    	<input type="hidden" name="action" value="processManageList"/> 
    	<input type="hidden" name="processAction" value="processInstanceList"/> 
    	<div class="search">
        	<table width="100%">
        	<tr>
        		<td colspan="6"><a href="#" onclick="stopProcess();">暂停</a><a href="#">恢复</a><a href="#">作废</a><a href="#">删除</a></td>
        	</tr>
              <tr>
                <td class="title-r">任务定义：</td>
                <td><input type="text" id="text_0" name="processDefinitionKey" class="fix-input" style="width:160px;" value="${result.processDefinitionKey}"/></td>
                <td class="title-r">流程实例号：</td>
                <td><input type="text" id="text_1" name="processInstanceId" class="fix-input" style="width:160px;" value="${result.processInstanceId}"/></td>
                <td class="title-r">主题：</td>
                <td><input type="text" id="text_2" name="subject" class="fix-input" style="width:160px;" value="${result.subject}"/></td>
              </tr>
              <tr>
                <td class="title-r">业务数据：</td>
                <td><input type="text" id="text_3" name="bizKey" class="fix-input" style="width:160px;" value="${result.bizKey}"/></td>
                <td class="title-r">发起人：</td>
                <td><input type="text" id="text_4" name="initor" class="fix-input" style="width:160px;" value="${result.initor}"/></td>
                <td class="title-r">状态：</td>
                <td>
                <input type="text" id="text_5" name="status" class="fix-input" style="width:160px;" value="${result.status}"/>
                </td>
              </tr>
              <tr>
              	<td></td>
              	<td></td>
              	<td></td>
              	<td></td>
              	<td></td>
              	<td>
              	<div class="btn-normal"><a href="#" onclick="$('#subForm').submit();">查 找<em class="arrow-small"></em></a></div>
              	</td>
              </tr>
            </table>
        </div>
        <div class="content">
        	<table width="100%" class="fix-table">
              <thead>
              	<th width="30"><input type="checkbox" id="checkall" name="checkall"/></th>
                <th width="30">&nbsp;</th>
                <th width="">实例编号</th>
                <th>流程定义</th>
                <th width="">主题</th>
                <th width="">启动时间</th>
                <th width="">结束时间</th>
                <th width="">业务数据</th>
                <th width="">发起人</th>
                <th width="">更新时间</th>
              </thead>
		    <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		    	<td class="num"><input type="checkbox" name="checked" value="${dataList.processInstanceId}"></td>
		      <td class="num"><c:out value="${index.index+1}"/></td>
		      <td>${dataList.processInstanceId}</td>
		      <td>${dataList.processDefinitionKey}</td>
		      <td>${dataList.subject}</td>
		      <td class="time"><fmt:formatDate value="${dataList.startTime}" type="both"/></td>
		      <td class="time"><fmt:formatDate value="${dataList.endTime}" type="both"/></td>
		      <td>${dataList.BIZ_KEY}</td>
		      <td>${dataList.initiator}</td>
				<td class="time"><fmt:formatDate value="${dataList.updateTime}" type="both"/></td>
		    </tr>
		    </c:forEach>
            </table>
			<jsp:include page="page.jsp" flush="true"/>
        </div>
    </div>
<!-- 分页 -->
	</form>
</div>
</div>

</body>
<script type="text/javascript">
	chooseInstanceSelect()
	function chooseInstanceSelect(){
		var now = '${nowProcessAction}';
		$("#"+now).addClass("select");
	}
	
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
  	
  	function stopProcess(){
 		var checkboxs = $("input[name=checked]");
 		var surl = "action=processManageList&processAction=stopProcess";
 		var id = "";
   		for(var i=0;i<checkboxs.length;i++) 
		{ 
			if(i!=0){
				id += ',';
			}
				
			id += $(checkboxs[i]).val();
		}
		surl +="&ids=";
		surl +=id;
		$.ajax({ type: "POST", 
		url: "FlowManager", 
		data: surl, 
		success: function(msg)
		{ 
			
		}
		});
  	}
</script>
</html>