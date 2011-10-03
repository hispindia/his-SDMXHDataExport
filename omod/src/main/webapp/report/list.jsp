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
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code='sdmxhddataexport.report.add'/>" onclick="ACT.go('report.form');"/>

<br /><br />
<input type="hidden" id="pageId" value="listReportPage" />
<form method="post" onsubmit="return false" id="form">
<table cellpadding="5" cellspacing="0"  >
	<tr>
		<td><spring:message code="general.name"/></td>
		<td><input type="text" id="searchName" name="searchName" value="${searchName}" /></td>
		<td><input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="Search" onclick="SDMXHDDataExport.search('listReport.form','searchName');"/></td>
	</tr>
</table>
<span class="boxHeader"><spring:message code="sdmxhddataexport.report.list"/></span>
<div class="box">
<c:choose>
<c:when test="${not empty reports}">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" onclick="SDMXHDDataExport.checkValue();" value="<spring:message code='sdmxhddataexport.deleteSelected'/>"/>
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
	<th>#</th>
	<th>No</th>
	<th><spring:message code="general.name"/></th>
	<th><spring:message code="sdmxhddataexport.report.createdOn"/></th>
	<th><spring:message code="sdmxhddataexport.report.createdBy"/></th>
	<th></th>
</tr>
<c:forEach items="${reports}" var="report" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td><input type="checkbox" name="ids" value="${report.id}"/></td>
		<td><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>	
		<td><a href="#" onclick="ACT.go('report.form?reportId=${ report.id}');">${report.name}</a> </td>
		<td><openmrs:formatDate date="${report.createdOn}" type="textbox"/></td>
		<td>${report.createdBy}</td>
		<td>
			<c:choose>
				<c:when test="${not empty report.reportDataElements }">
					<a href="#" onclick="ACT.go('reportDataElement.form?reportId=${ report.id}');" title="Map dataelement to this report">Change data element</a>
					&nbsp;&nbsp;<a href="#" onclick="SDMXHDDataExport.showDialog('${ report.id}');" >Run</a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="ACT.go('reportDataElement.form?reportId=${ report.id}');" title="Map dataelement to this report">Add data element</a>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>

<tr class="paging-container">
	<td colspan="6"><%@ include file="../paging.jsp" %></td>
</tr>
</table>
<div id="excecuteQuery">
<input type="hidden" id="reportId" name="reportId" />
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
	
</table>
<div id="divResults">

</div>
</div>
</c:when>
<c:otherwise>
	No report found.
</c:otherwise>
</c:choose>
</div>
</form>



<%@ include file="/WEB-INF/template/footer.jsp" %>
