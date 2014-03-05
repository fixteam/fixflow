<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<tr id="row${param.index+1}">
	<td><input type="checkbox" name="check" rowIndex="${param.index+1}" isNew="true"/></td>
	<td><input type="text" id="key${param.index+1}"/></td>
	<td><input type="text" id="class${param.index+1}"/></td>
	<td><input type="text" id="type${param.index+1}"/></td>
	<td></td>
	<td><input type="text" id="value${param.index+1}"/></td>
</tr>