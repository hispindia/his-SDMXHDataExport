 <%--
 *  Copyright 2009 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of SDMXHDataExport module.
 *
 *  SDMXHDataExport module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  SDMXHDataExport module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with SDMXHDataExport module.  If not, see <http://www.gnu.org/licenses/>.
 *
--%> 
<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="View SDMXHDDataExport" otherwise="/login.htm" redirect="/module/sdmxhddataexport/listQuery.form" />

<spring:message var="pageTitle" code="sdmxhddataexport.query.manage" scope="page"/>

<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="../includes/nav.jsp" %>

<h2><spring:message code="sdmxhddataexport.query.manage"/></h2>	

<br />
<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><
</c:forEach>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code='sdmxhddataexport.query.add'/>" onclick="ACT.go('query.form');"/>

<br /><br />
<input type="hidden" id="pageId" value="listQueryPage" />
<form method="post" onsubmit="return false" id="form">
<table cellpadding="5" cellspacing="0"  >
	<tr>
		<td><spring:message code="general.name"/></td>
		<td><input type="text" id="searchName" name="searchName" value="${searchName}" /></td>
		<td><input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="Search" onclick="SDMXHDDataExport.search('listQuery.form','searchName');"/></td>
	</tr>
</table>
<span class="boxHeader"><spring:message code="sdmxhddataexport.query.list"/></span>
<div class="box">
<c:choose>
<c:when test="${not empty queries}">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" onclick="SDMXHDDataExport.checkValue();" value="<spring:message code='sdmxhddataexport.deleteSelected'/>"/>
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
	<th></th>
	<th>#</th>
	<th><spring:message code="general.name"/></th>
	<th><spring:message code="sdmxhddataexport.query.sqlQuery"/></th>
	<th><spring:message code="sdmxhddataexport.query.createdOn"/></th>
	<th><spring:message code="sdmxhddataexport.query.createdBy"/></th>
	<th></th>
</tr>
<c:forEach items="${queries}" var="query" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>
		<td><input type="checkbox" name="ids" value="${query.id}"/></td>	
		<td><a href="#" onclick="ACT.go('query.form?queryId=${ query.id}');">${query.name}</a> </td>
		<td>${query.sqlQuery }</td>
		<td><openmrs:formatDate date="${query.createdOn}" type="textbox"/></td>
		<td>${query.createdBy}</td>
		<td><a href="#" onclick="SDMXHDDataExport.showQuery('${query.id}')">Run this query</a></td>
	</tr>
</c:forEach>

<tr class="paging-container">
	<td colspan="7"><%@ include file="../paging.jsp" %></td>
</tr>
</table>
<div id="excecuteQuery">
<input type="hidden" id="queryId" name="queryId" />
<table >
	<tr>
		<td><spring:message code="sdmxhddataexport.startDate"/><em>*</em></td>
		<td>
			<input type="text" name="startDate" id="startDate" value="${startDate }" class="date-pick left" readonly="readonly"  ondblclick="this.value='';"/>
		</td>
	</tr>
	<tr>
		<td><spring:message code="sdmxhddataexport.endDate"/><em>*</em></td>
		<td>
			<input type="text" name="endDate" id="endDate" value="${endDate }"  class="date-pick left" readonly="readonly"  ondblclick="this.value='';"/>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<h2><b><div id="resultExecute">${result}</div></b></h2>
		</td>
	</tr>
</table>
</div>

</c:when>
<c:otherwise>
	No query found.
</c:otherwise>
</c:choose>
</div>
</form>



<%@ include file="/WEB-INF/template/footer.jsp" %>
