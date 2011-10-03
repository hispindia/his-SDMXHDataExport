<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="View SDMXHDDataExport" otherwise="/login.htm" redirect="/module/sdmxhddataexport/listDataElement.form" />

<spring:message var="pageTitle" code="sdmxhddataexport.dataElement.manage" scope="page"/>

<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="../includes/nav.jsp" %>

<h2><spring:message code="sdmxhddataexport.dataElement.manage"/></h2>	

<br />
<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><
</c:forEach>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code='sdmxhddataexport.dataElement.add'/>" onclick="ACT.go('dataElement.form');"/>

<br /><br />
<form method="post" onsubmit="return false" id="form">
<table cellpadding="5" cellspacing="0"  >
	<tr>
		<td><spring:message code="general.name"/></td>
		<td><input type="text" id="searchName" name="searchName" value="${searchName}" /></td>
		<td><input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="Search" onclick="SDMXHDDataExport.search('listDataElement.form','searchName');"/></td>
	</tr>
</table>
<span class="boxHeader"><spring:message code="sdmxhddataexport.dataElement.list"/></span>
<div class="box">
<c:choose>
<c:when test="${not empty dataElements}">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" onclick="SDMXHDDataExport.checkValue();" value="<spring:message code='sdmxhddataexport.deleteSelected'/>"/>
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
	<th>#</th>
	<th><spring:message code="general.name"/></th>
	<th><spring:message code="sdmxhddataexport.dataElement.createdOn"/></th>
	<th><spring:message code="sdmxhddataexport.dataElement.createdBy"/></th>
	<th></th>
</tr>
<c:forEach items="${dataElements}" var="dataElement" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>	
		<td><a href="#" onclick="ACT.go('dataElement.form?dataElementId=${ dataElement.id}');">${dataElement.name}</a> </td>
		<td><openmrs:formatDate date="${dataElement.createdOn}" type="textbox"/></td>
		<td>${dataElement.createdBy}</td>
		<td><input type="checkbox" name="ids" value="${dataElement.id}"/></td>
	</tr>
</c:forEach>

<tr class="paging-container">
	<td colspan="5"><%@ include file="../paging.jsp" %></td>
</tr>
</table>
</c:when>
<c:otherwise>
	No dataElement found.
</c:otherwise>
</c:choose>
</div>
</form>



<%@ include file="/WEB-INF/template/footer.jsp" %>
