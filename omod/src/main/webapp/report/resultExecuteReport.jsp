<%@ include file="/WEB-INF/template/include.jsp" %>
<table cellpadding="5" cellspacing="0" width="100%">
<c:forEach items="${results}" var="result" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td>${result.dataElement.name }</td>
		<td><b>${result.result}</b></td>
	</tr>
</c:forEach>
</table>