<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Manage SDMXHDDataExport" otherwise="/login.htm" redirect="/module/sdmxhddataexport/reportDataElement.form" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<h2><spring:message code="sdmxhddataexport.report.manage"/></h2>


<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span>
</c:forEach>
<spring:bind path="reportDataElement">
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

<!-- Report -->
<input type="hidden" id="pageId" value="reportDataElementPage" />
<form method="post" class="box" id="reportDataElementForm">
<spring:bind path="reportDataElement.id">
	<input type="hidden" name="${status.expression}" id="${status.expression}" value="${status.value}" />
</spring:bind>
<span class="boxHeader"><spring:message code="sdmxhddataexport.report.title"/></span>
<div class="box" id="divReport">
<spring:bind path="reportDataElement.report">
	<select  name="${status.expression }" id="reportId" onchange="SDMXHDDataExport.onChangeReportDataElement(this);"  style="width:150px">
			<option value=""></option>
			<c:forEach items="${reports}" var="report">
				<option value="${report.id}"
				<c:if test="${report.id == reportId || report.id == reportDataElement.report.id}"> selected</c:if>
				>${report.name}
				</option>
			</c:forEach>
	</select>
<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>	
</spring:bind>
</div>

<!-- Dataelement && Query -->
<span class="boxHeader"><spring:message code="sdmxhddataexport.reportDataElement.dataElementQuery"/></span>
<div class="box" id="divDataElementQuery">
<table>
	<tr>
		<td><spring:message code="sdmxhddataexport.dataElement.title"/><em>*</em></td>
		<td>
		<spring:bind path="reportDataElement.dataElement">
			<select  name="${status.expression }"  id="dataElementId"   style="width:150px">
				<option value=""></option>
				<c:forEach items="${dataElements}" var="dataElement">
					<option value="${dataElement.id}"
					<c:if test="${dataElement.id == reportDataElement.dataElement.id}"> selected</c:if>
					>${dataElement.name}
					</option>
				</c:forEach>
			</select>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
		</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="sdmxhddataexport.query.title"/><em>*</em></td>
		<td>
		<spring:bind path="reportDataElement.query">
			<select  name="${status.expression }" id="queryId"   style="width:150px">
			<option value=""></option>
				<c:forEach items="${queries}" var="query">
					<option value="${query.id}"
					<c:if test="${query.id == reportDataElement.query.id}"> selected</c:if>
					>${query.name}
					</option>
				</c:forEach>
			</select>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
		</spring:bind>
		</td>
	</tr>
</table>

<input type="submit" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.save"/>">
<c:if test="${ not empty reportDataElements}">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" id="excecuteQueryButton" value="<spring:message code="sdmxhddataexport.report.run"/>">
<div id="excecuteQuery">
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
</c:if>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.cancel"/>" onclick="ACT.go('listReport.form');">
</div>
</form>
<!-- List report dataelement query of this report -->

<span class="boxHeader"><spring:message code="sdmxhddataexport.reportDataElement.title"/></span>
<div class="box" id="divReportDataElement">
<form method="post" action="deleteReportDataElement.form" id="form">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" onclick="SDMXHDDataExport.checkValue();" value="<spring:message code='sdmxhddataexport.deleteSelected'/>"/>
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
	<th>#</th>
	<th>No</th>
	<th><spring:message code="sdmxhddataexport.report.title"/></th>
	<th><spring:message code="sdmxhddataexport.dataElement.title"/></th>
	<th><spring:message code="sdmxhddataexport.query.title"/></th>
</tr>
<c:forEach items="${reportDataElements}" var="reportDataElement" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td><input type="checkbox" name="ids" value="${reportDataElement.id}"/></td>
		<td><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>	
		<td><a href="#" onclick="ACT.go('reportDataElement.form?reportDataElementId=${ reportDataElement.id}');" title="Edit">${reportDataElement.report.name}</a> </td>
		<td>${reportDataElement.dataElement.name }</td>
		<td>${reportDataElement.query.name}</td>
	</tr>
</c:forEach>

<tr class="paging-container">
	<td colspan="5"><%@ include file="../paging.jsp" %></td>
</tr>
</table>
</form>
</div>