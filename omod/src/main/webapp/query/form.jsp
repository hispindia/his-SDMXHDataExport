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

<openmrs:require privilege="Manage SDMXHDDataExport" otherwise="/login.htm" redirect="/module/sdmxhddataexport/query.form" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<h2><spring:message code="sdmxhddataexport.query.manage"/></h2>

<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span>
</c:forEach>
<spring:bind path="query">
<c:if test="${not empty  status.errorMessages}">
<div class="error">
<ul>
<c:forEach items="${status.errorMessages}" var="error">
    <li>${error}</li>   
</c:forEach>
</ul>
</div>
</c:if>
</spring:bind>
<input type="hidden" id="pageId" value="queryPage" />
<form method="post" class="box" id="queryForm">
<spring:bind path="query.id">
	<input type="hidden" name="${status.expression}" id="${status.expression}" value="${status.value}" />
</spring:bind>
<table>
	<tr>
		<td><spring:message code="general.name"/><em>*</em></td>
		<td>
			<spring:bind path="query.name">
				
				<input type="text" name="${status.expression}" id="${status.expression}" value="${status.value}" size="60" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="sdmxhddataexport.query.sqlQuery"/><em>*</em></td>
		<td>
			<spring:bind path="query.sqlQuery">
				
				<textarea rows="12" style="width:800px;"  name="${status.expression}" id="${status.expression}"  >${status.value}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
</table>
<br />
<input type="submit" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.save"/>">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.cancel"/>" onclick="ACT.go('listQuery.form');">
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>