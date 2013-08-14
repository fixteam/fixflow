<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="header">
    <div class="top-right">
        <ul>
        <li>委托人：<a href="#" onclick="updateMyself();">${sessionScope.LOGIN_USER_NAME}</a></li>
        <li><a href="login.jsp" onclick="">退出</a></li>
        </ul>
    </div>
    <div class="menu">
        <div class="logo"></div>
        <ul>
        <li><a id="getMyProcess" href="FlowCenter?action=getMyProcess"><h1>发起流程</h1><h4>start flow</h4></a></li>
        <li><a id="getMyTask" href="FlowCenter?action=getMyTask"><h1>待办任务</h1><h4>schedule</h4></a></li>
        <li><a id="getInitorTask" href="FlowCenter?action=getInitorTask"><h1>流程查询</h1><h4>flow query</h4></a></li>
        <li><a href="#"><h1>归档任务</h1><h4>place on file</h4></a></li>
        </ul>
    </div>
</div>
<script>
	chooseSelect();
	function updateMyself(){
		var obj = {};
		window.showModalDialog("FlowCenter?action=getUserInfo",obj,"dialogWidth=800px;dialogHeight=600px");
	}
	
	function chooseSelect(){
		var now = '${nowAction}';
		$("#"+now).addClass("select");
	}
</script>
