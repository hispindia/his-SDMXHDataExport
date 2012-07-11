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

<openmrs:require privilege="View SDMXHDDataExport" otherwise="/login.htm" redirect="/module/sdmxhddataexport/listReport.form" />

<spring:message var="pageTitle" code="sdmxhddataexport.report.manage" scope="page"/>

<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="../includes/nav.jsp" %>

<h2><spring:message code="sdmxhddataexport.report.manage"/></h2>	

<br />
<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><
</c:forEach>

<br /><br />
<input type="hidden" id="pageId" value="listReportPage" />
<table cellpadding="5" cellspacing="0"  >
	<tr>
	
	<!-- Modified the back link from report.form to listReport.form  -->
		<td><input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="Back" onclick="ACT.go('listReport.form');"/></td>
	</tr>
</table>
<span class="boxHeader"><spring:message code="XML Report"/></span>
<div class="box">
<pre>${contents}</pre>
</div>
<%@ include file="/WEB-INF/template/footer.jsp" %>
