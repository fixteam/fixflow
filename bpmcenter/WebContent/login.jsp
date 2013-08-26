<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/jquery.js"></script>

<title>Founder Fix BPM</title>
</head>

<body>
<div class="main-panel">
	<div class="center-panel">
        <div class="login">
            <div class="logo">&nbsp;</div>
            <form id="loginF" method=post action="${pageContext.request.contextPath}/LoginServlet">
	            <table id="lockScreen" class="hide">
	              <tbody>
	                <tr>
	                  <td rowspan="3" width="110"><img src="images/temp/user-m.png" /></td>
	                  <td class="username" width="330"><span id="lastLoginUser"></span></td>
	                </tr>
	                <tr>
	                  <td class="password"><div class="btn-login"><a href="#"><em class="arrow-login"></em></a></div>
	                  <input type="password" class="inputset" /></td>
	                </tr>
	                <tr>
	                  <td class="change"><a href="#" id="returnLoginForm">切换用户</a></td>
	                </tr>
	              </tbody>
	            </table>
	            <table id="loginForm">
	              <tbody>
	                <tr>
	                  <td><span class="label-orange">用户名：</span></td>
	                  <td><input type="text" id="userName" name="userName" class="inputset"/></td>
	                </tr>
	                <tr>
	                  <td><span class="label-orange">密　码：</span></td>
	                  <td><input type="password" id="password" name="password" class="inputset"/></td>
	                </tr>
	                <tr>
	                	<td class="change">
	                		<input type="checkbox" id="loginType" name = "loginType" /><label for="loginType">管控中心登陆 </label>
	                	</td>
	                	<td class="change_right">
	                		<a href="#" id="returnToLockScreen">返回锁屏画面</a>
	                	</td>
	                </tr>
	                <tr>
	                  <td colspan="2"><div class="btn-box">
	                      <div class="btn-orange"><a href="#" id="login">登&emsp;录</a></div>
	                      <div class="btn-gray"><a href="#">返&emsp;回</a></div>
	                  </div></td>
	                </tr>
	              </tbody>
	            </table>
            </form>
        </div>
    </div>
    <div class="bottom-panel">
    </div>
</div>
</body>
</html>

<script>
$(function(){
	$("#returnToLockScreen").click(function(){
		$("#lastLoginUser").html(window.sessionStorage.getItem("username"));
		$("#lockScreen").show("fast");
		$("#loginForm").hide("fast");
	});
	
	$("#returnLoginForm").click(function(){
		$("#lockScreen").hide("fast");
		$("#loginForm").show("fast");
	});
	$("#login").click(function(){
		var storage = window.sessionStorage;
		storage.setItem("username",$("#userName").val());
		$("#loginF").submit();
	});
})
</script>
