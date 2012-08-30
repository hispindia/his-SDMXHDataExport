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

<openmrs:require privilege="View SDMXHDDataExport" otherwise="/login.htm" redirect="/module/sdmxhddataexport/listDataElement.form" />

<spring:message var="pageTitle" code="sdmxhddataexport.dataElement.manage" scope="page"/>

<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="../includes/nav.jsp" %>
<script type="text/javascript">window.onload = SDMXHDDataExport.whatUrl('url');</script>
<h2><spring:message code="sdmxhddataexport.dataElement.manage"/></h2>	

<script type="text/javascript">
	function showPopup( id )
	{
		alert("something hapnd");
		tb_show( "testing", "showForm.form?script=refresh();&modal=true&height=600&width=800&id=2&encounterId=305160&mode=edit" );
		alert("something hapnd again");
	}
	
	function refresh(){
		window.location.href = window.location.href;
	}
	
	function whatUrl(){
		var str=window.location.href;
		var n=str.split("//");
		var s=n[1].split('/')
		var t=s[0];
		return t;
	}
</script>
<!-- tb_show( "testing", "uploadDataElements.form?script=refresh();&modal=true&height=600&width=800" );-->
<br />
<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><
</c:forEach>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code='sdmxhddataexport.dataElement.add'/>" onclick="ACT.go('dataElement.form');"/>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code='sdmxhddataexport.dataElement.export'/>" onclick="SDMXHDDataExport.setAddress('downloadDataElements.form?url='+whatUrl());"/>
<!-- ACT.go('dataElementExport.form');"/> -->
<br /><br />

<span class="boxHeader"><spring:message code="sdmxhddataexport.dataElement.upload"/></span>
<div class="box">
<form method="POST" id="uploadDataElements" enctype="multipart/form-data"  modelAttribute="uploadItem" commandName="fileUploadForm" >

<!-- <form:errors path="*" cssClass="errorblock" element="div"/> -->

Please select a file to upload :
<input type="hidden" name="upload" value="true" />
<input type="file" name="files" /> 
<span>Include Queries<span /><input type="checkbox" value="IncludeQueries" name="IncludeQueries" />
<br />



<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" onclick="SDMXHDDataExport.checkUpload();" value="Upload Queries"/>
<!-- <span><form:errors path="file" cssClass="error" /></span> -->

</form>
</div>

<form method="post" onsubmit="return false" id="form">
<input type="hidden" id="pageId" value="listDataElementPage" /> 
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
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" onclick="SDMXHDDataExport.select();" value="<spring:message code='sdmxhddataexport.SelectAll'/>"/>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" onclick="SDMXHDDataExport.unselect();" value="<spring:message code='sdmxhddataexport.UnselectAll'/>"/>

<table cellpadding="5" cellspacing="0" width="100%">
<tr>
<th></th>
	<th>#</th>
	<th><spring:message code="Code"/></th>
	<th><spring:message code="general.name"/></th>
	<th><spring:message code="sdmxhddataexport.dataElement.createdOn"/></th>
	<th><spring:message code="sdmxhddataexport.dataElement.sqlQuery"/></th>
	<th><spring:message code="sdmxhddataexport.dataElement.createdBy"/></th>
	<th></th>
	
</tr>
<c:forEach items="${dataElements}" var="dataElement" varStatus="varStatus">

	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
	<td><input type="checkbox" name="ids" value="${dataElement.id}"/></td>
		<td><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>	
		
		<td>${dataElement.code }</td>
		
		<td><a href="#" onclick="ACT.go('dataElementEdit.form?dataElementId=${ dataElement.id}');">${dataElement.name}</a> </td>
		<td><openmrs:formatDate date="${dataElement.createdOn}" type="textbox"/></td>
		<td>${dataElement.sqlQuery }</td>
		<td>${dataElement.createdBy}</td>
		<td>
		<c:choose>
		<c:when test="${not empty dataElement.sqlQuery }">
		<a href="#" onclick="SDMXHDDataExport.showQuery('${dataElement.id}')">Run this query</a>
		</c:when>
		<c:otherwise>
		Run this query
		</c:otherwise>
		</c:choose>
		
		
		</td>
		
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







<%@ include file="/WEB-INF/template/footer.jsp" %>
