<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程令牌管理</title>
<jsp:include page="head.jsp" flush="true"/>
</head>

<body>
<form method="post" id="form" action="FlowManager">
<div class="popup">
    <table width="100%" class="fix-table">
			<thead>
                <th width="30">&nbsp;</th>
                <th width="">令牌编号</th>
                <th width="">令牌名称</th>
                <th>开始时间</th>
                <th width="">节点进入时间</th>
                <th width="">节点编号</th>
                <th width="">流程实例编号</th>
                <th width="">父令牌编</th>
             </thead>
		    <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		      <td class="num"><c:out value="${index.index+1}"/></td>
		      <td>${dataList.tokenId}</td>
		      <td>${dataList.name}</td>
		      <td class="time"><fmt:formatDate value="${dataList.startTime}" type="both"/></td>
		      <td class="time"><fmt:formatDate value="${dataList.nodeEnterTime}" type="both"/></td>
		      <td>${dataList.nodeId}</td>
		      <td>${dataList.parentTokenId}</td>
		    </tr>
		    </c:forEach>
      </table>
      </div>
</form>
</body>
<script type="text/javascript">

</script>
</html>