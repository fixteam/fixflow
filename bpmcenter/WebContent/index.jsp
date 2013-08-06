<%@ page language="java" contentType="text/html; charset="
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中文</title>
</head>
<body>
	<table>
		<tr>
			<td>
				1
			</td>
						<td>
				2
			</td>
						<td>
				3
			</td>
						<td>
				4
			</td>
						<td>
				5
			</td>
						<td>
				6
			</td>
						<td>
				7
			</td>
						<td>
				8
			</td>
						<td>
				9
			</td>
		</tr>
		<c:forEach items="${result}" var="row" varStatus="status">
			<tr>
				<td>
					<c:out value="${status.index+1}"/>
				</td>
				<td>
					<c:out value="${row.processDefinitionKey}"/>
				</td>
				<td>
				</td>
				<td>
				</td>
				<td>
				</td>
				<td>
				</td>
				<td>
				</td>
				<td>
				</td>
				<td>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>