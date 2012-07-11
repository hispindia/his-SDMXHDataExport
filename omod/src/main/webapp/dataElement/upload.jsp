<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="View SDMXHDDataExport" otherwise="/login.htm" redirect="/module/sdmxhddataexport/listDataElement.form" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<div id="uploadBox">

<form:form method="POST" id="uploadDataElements" enctype="multipart/form-data">

<!-- <form:errors path="*" cssClass="errorblock" element="div"/> -->

Please select a file to upload : 
<input type="file" name="file" /> 
<input type="submit" value="Upload" />
<!-- <span><form:errors path="file" cssClass="error" /></span> -->

</form:form>

</div>