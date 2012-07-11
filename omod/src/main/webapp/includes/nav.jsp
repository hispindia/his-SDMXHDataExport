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
<%@ include file="js_css.jsp" %>
<br/>
<c:if test='<%= request.getRequestURI().contains("dataElement/list") %>'>
<b><spring:message code="sdmxhddataexport.dataElement.manage"/></b>&nbsp; |
 <%--<b><a href="#" onclick="ACT.go('listQuery.form');"><spring:message code="sdmxhddataexport.query.manage"/></a></b>&nbsp; |--%>
<b><a href="#" onclick="ACT.go('listReport.form');"><spring:message code="sdmxhddataexport.report.manage"/></a></b>
</c:if>

 
<c:if test='<%= request.getRequestURI().contains("query/list") %>'> 
<b><a href="#" onclick="ACT.go('listDataElement.form');"><spring:message code="sdmxhddataexport.dataElement.manage"/></a></b>&nbsp; |
<b><spring:message code="sdmxhddataexport.query.manage"/></b>&nbsp; |
<b><a href="#" onclick="ACT.go('listReport.form');"><spring:message code="sdmxhddataexport.report.manage"/></a></b>
</c:if>

<c:if test='<%= request.getRequestURI().contains("report/list") %>'>
<b><a href="#" onclick="ACT.go('listDataElement.form');"><spring:message code="sdmxhddataexport.dataElement.manage"/></a></b>&nbsp; |
<b><spring:message code="sdmxhddataexport.report.manage"/></b> 
</c:if>

<c:if test='<%= request.getRequestURI().contains("report/form") %>'> 
<b><a href="#" onclick="ACT.go('listDataElement.form');"><spring:message code="sdmxhddataexport.dataElement.manage"/></a></b>&nbsp; |
<b><a href="#" onclick="ACT.go('listReport.form');"><spring:message code="sdmxhddataexport.report.manage"/></a></b>
</c:if>

<c:if test='<%= request.getRequestURI().contains("dataElement/form") %>'> 
<b><a href="#" onclick="ACT.go('listDataElement.form');"><spring:message code="sdmxhddataexport.dataElement.manage"/></a></b>&nbsp; |
<b><a href="#" onclick="ACT.go('listReport.form');"><spring:message code="sdmxhddataexport.report.manage"/></a></b>
</c:if>
<c:if test='<%= request.getRequestURI().contains("report/resultView") %>'> 
<b><a href="#" onclick="ACT.go('listDataElement.form');"><spring:message code="sdmxhddataexport.dataElement.manage"/></a></b>&nbsp; |
<b><a href="#" onclick="ACT.go('listReport.form');"><spring:message code="sdmxhddataexport.report.manage"/></a></b>
</c:if>
<!-- added last two sttements for adding a navigation bar on the jsp's 1)report.form 2)dataElement.form 3)data-->
<br/><br/>


