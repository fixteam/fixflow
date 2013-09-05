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
<script type="text/javascript">
	function addNew(){
		var nowIndex = $("#nowIndex").val();
		$.ajax({ type: "POST", 
			url: "fixflow/manager/processVariableModel.jsp", 
			data: "index="+nowIndex, 
			success: function(msg)
			{ 
				$("#detailTable").append(msg);
				$("#nowIndex").val(parseInt(nowIndex)+1);
			}
		});
	}
	
	function deleteRow(){
 		var checkboxs = $("input[name=check]");
 		var deleteed = $("#deleteIndex").val();
 		var id = "";
   		for(var i=0;i<checkboxs.length;i++) 
		{ 
			if(checkboxs[i].checked==true){
				var index = $(checkboxs[i]).attr("rowIndex");
				if($(checkboxs[i]).attr("isNew")==null){
					if(deleteed!=""){
						deleteed += ",";
					}
					deleteed += $("#key"+index).val();;
				}
				$("#row"+index).remove();
			}
		}
		
		$("#deleteIndex").val(deleteed);
	}
	
	function saveData(){
 		var checkboxs = $("input[name=check]");
 		var id = "";
 		var params = {};
   		for(var i=0;i<checkboxs.length;i++) 
		{
			var index = $(checkboxs[i]).attr("rowIndex");
			var key = $("#key"+index).val();
			var value = $("#value"+index).val();
			params[key] = value;
		}
		
		var ss = JSON.stringify(params);
		$("#insertAndUpdate").val(ss);
		$("#form").submit();
	}
</script>
</head>

<body>
<form method="post" id="form" action="FlowManager">
<div class="popup">
    <table width="100%" class="fix-table" id="detailTable">
		<thead>
			<th><input type="checkbox" id="checkall" name="checkall"/></th>
			<th>变量编号</th>
			<th>变量类名称</th>
			<th>变量业务类型</th>
			<th>任务实例</th>
			<th>查询值</th>
		</thead>	
		<c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		<tr id="row${index.index}">
			<td><input type="checkbox" name="check" rowIndex="${index.index}"/></td>
			<td>${dataList.key}<input type="hidden" id="key${index.index}" value="${dataList.key}"/></td>
			<td></td>
			<td></td>
			<td></td>
			<td><input type="text" id="value${index.index}" value="${dataList.value}"/></td>
			<c:set var="nowIndex" value="${index.index}"/>
		</tr>
		</c:forEach>

      </table>

			<table>
				<tr>
				<td>
			<input type="hidden" id="nowIndex" value="${nowIndex+1}"/>
			<input type="hidden" name="deleteIndex" id="deleteIndex"/>
			<input type="hidden" name="action" value="saveProcessVariables"/>
			<input type="hidden" name="insertAndUpdate" id="insertAndUpdate"/>
			<input type="hidden" name="processInstanceId" value="${result.processInstanceId}"/>
			<div class="btn-normal"><a href="#" onclick="addNew();">新增</a></div>
			</td>
			<td>
			<div class="btn-normal"><a href="#" onclick="deleteRow();">删除</a></div>
			</td>
			<td>
			<div class="btn-normal"><a href="#" onclick="saveData();">保存</a></div>
			</td>
				</tr>
			</table>

</div>
</form>

</body>

</html>