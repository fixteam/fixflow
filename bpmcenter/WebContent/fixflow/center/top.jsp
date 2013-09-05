<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="header">
    <div class="top-right">
        <ul>
        <li><a id="updateCache" href="#">更新缓存</a></li>
        <li><a id="setAgent" href="#">委托授权</a></li>
        <li><a href="#" onclick="updateMyself();">${sessionScope.LOGIN_USER_NAME}</a></li>
        <li><a href="LoginServlet?doLogOut=true">退出</a></li>
        <li><a href="FlowManager?action=processDefinitionList" target='_blank'>管控中心</a></li>
        <li><a href="#" id="red" style="background-color: #dc562e;display: block;width:10px;height:10px;margin-top:5px;"></a></li>
        <li><a href="#" id="blue" style="background-color: #10a7d9;display: block;width:10px;height:10px;margin-top:4px;"></a></li>
        </ul>
    </div>
    <div class="menu">
        <div class="logo"></div>
        <ul>
        <li><a id="getMyProcess" href="FlowCenter?action=getMyProcess"><h1>发起流程</h1><h4>start flow</h4></a></li>
        <li><a id="getMyTask" href="FlowCenter?action=getMyTask"><h1>待办任务</h1><h4>schedule</h4></a></li>
        <li><a id="getAllProcess" href="FlowCenter?action=getAllProcess"><h1>流程查询</h1><h4>flow query</h4></a></li>
        <li><a id="getPlaceOnFile" href="FlowCenter?action=getPlaceOnFile"><h1>归档任务</h1><h4>place on file</h4></a></li>
        </ul>
    </div>
</div>
<script>
 
	$(function(){
		$("#updateCache").click(function(){
			window.open("FlowManager?action=updateCache")
		});
		
		$("#setAgent").click(function(){
			var url = "FlowCenter?action=viewDelegation";
			Fix.OpenMethod.openWindow(url);
		});
		
		$("#red").click(function(){
			var url = $("#color").attr("href");
			url=url.substring(0,url.lastIndexOf("_")+1);
			url+="red.css";
			$("#color").attr("href",url);
			return false;
		});
		$("#blue").click(function(){
			var url = $("#color").attr("href");
			url=url.substring(0,url.lastIndexOf("_")+1);
			url+="blue.css";
			$("#color").attr("href",url);
			return false;
		});
	})

	chooseSelect();
	function updateMyself(){
		var obj = {};
		window.showModalDialog("FlowCenter?action=getUserInfo&isUpdate=true",obj,"dialogWidth=800px;dialogHeight=600px");
	}
	
	function chooseSelect(){
		var now = '${nowAction}';
		$("#"+now).addClass("select");
	}
</script>
