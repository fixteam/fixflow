<%@ page language="java" contentType="text/html; charset=utf-8"pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="head.jsp" flush="true"/>
<link rel="stylesheet" href="fixflow/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="fixflow/js/jquery.ztree.core-3.5.js"></script>
<title>用户查询</title>

<script type="text/javascript">
	function viewUser(userid){
		var obj = {};
		window.showModalDialog("FlowCenter?action=getUserInfo&targetUserId="+userid,obj,"dialogWidth=800px;dialogHeight=600px");
	}
</script>
<SCRIPT type="text/javascript">
		var setting = {
			data: {
				key: {
					title:"t"
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick
			}
		};
		function onClick(event, treeId, treeNode, clickFlag) {
			var supId = treeNode.id.split("__");
			$("#supId").val(supId[1]);
			$("#groupType").val(supId[0]);
			$("#action").val("getGroupList");
			$("#subForm").submit();
		}		

		$(document).ready(function(){
			$(".zTreeDiv").each(function(index,obj){
				var jsonStr = $(obj).find("div").eq(0).html();
				var zNodeInfo = eval(jsonStr);
				$.fn.zTree.init($(obj).find("ul").eq(0), setting, zNodeInfo);
			});
		});
	</SCRIPT>
</head>
<body>
<form action="FlowManager" id="subForm">
<div class="main-panel">
<jsp:include page="top.jsp" flush="true"/>
<div style="margin-top:1px;">
<!-- 左 -->
<div class="left">
    	<div class="left-nav-box">
    	<div class="left-nav"><a name="userList" href="#">用户</a></div>
        <div class="left-nav-orange-line">&nbsp;</div>
       	<div class="left-nav"><a name="group" href="#">组</a></div>
       	  	<c:if test="${groupList!= null && fn:length(groupList) != 0}">
			    <c:forEach items="${groupList}" var="group" varStatus="index">
			      <div class="left-nav"><a name="groupList" href="FlowManager?action=getGroupList&groupType=${group.typeId}"><img src="fixflow/images/man02.png" />${group.typeName}</a></div>
				  <c:if test="${group.isTree!= null && group.isTree == true}">
			      	<div class="zTreeDiv" style="padding-left:25px;"><div class="jsonStr" style="display:none;">${group.groupJson}</div><ul class="ztree"></ul></div>
			      </c:if>			   
			    </c:forEach>
       	  	</c:if>
        </div>
</div>
<!-- 右-->
	<div class="right">
	  <!-- 查 -->
	  <div class="search">
        	<table width="100%">
              <tr>
                <td class="title-r">用户编号：</td>
                <td><input type="text" id="text_3" name="queryUserId" class="fix-input" style="width:160px;" value="${result.queryUserId}"/></td>
                <td class="title-r">用户姓名：</td>
                <td><input type="text" id="text_4" name="queryUserName" class="fix-input" style="width:160px;" value="${result.queryUserName}"/>
                <td></td>
                <td><div class="btn-normal" ><a href="#" onclick="$('#subForm').submit();">查 找<em class="arrow-small"></em></a></div></td>
              </tr>
            </table>
        </div>
	  <div>
		<!-- 表 -->
		<table style="width:100%;" class="fix-table">
		  <thead>
		   <th width="2%"></th>
		    <th width="30%">用户编号</th>
		    <th >用户姓名</th>
		    <th width="5%">登陆号</th>
		    <th width="8%">邮箱</th>
		    <th width="8%">操作</th>
		  </thead>
		  <tbody>
		   <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		     <td><input type="checkbox"/></td>
		      <td>${dataList.USERID}</td>
		      <td>${dataList.USERNAME}</td>
		      <td>${dataList.LOGINID}</td>
		      <td>${dataList.EMAIL}</td>
		       <td><a href="#" onclick="viewUser('${dataList.USERID}')">查看</a></td>
		    </tr>
		    </c:forEach>
		  </tbody>
	    </table>
		<!-- 分页 -->	    
	   <jsp:include page="../common/page.jsp" flush="true"/>
	    </div>
	  </div>
	</div>
</div>
<!-- 隐藏参数部分 -->
<input type="hidden" name="action" id="action" value="getUserList"> 
<input type="hidden" name="supId" id="supId"> 
<input type="hidden" name="groupType" id="groupType" value="dept"> 
</form>
</body>
</html>
