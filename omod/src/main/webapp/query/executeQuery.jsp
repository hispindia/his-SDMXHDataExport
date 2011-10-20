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
<h2><spring:message code="sdmxhddataexport.query.executeQuery"/></h2>

<input type="hidden" id="pageId" value="executeQueryPage" />
<form method="post" class="box" id="executeQueryForm">
<input type="hidden" name="queryId" id="queryId" value="${queryId}"/>
<table >
	<tr>
		<td><spring:message code="sdmxhddataexport.query.sqlQuery"/></td>
		<td>
			<textarea readonly="readonly"  rows="12" style="width:800px;"  name="sqlQuery" id="sqlQuery"  >${sqlQuery}</textarea>
		</td>
	</tr>
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
<br />
<input type="submit" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="sdmxhddataexport.run"/>">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.cancel"/>" onclick="ACT.go('listQuery.form');">
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>