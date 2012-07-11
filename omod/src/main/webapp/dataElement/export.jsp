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
<openmrs:globalProperty key="sdmxhddataexport.organisationUnit" var="organisationUnit"/>


<?xml version="1.0"?>
<Structure xmlns="http://www.SDMX.org/resources/SDMXML/schemas/v2_0/message" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:structure="http://www.SDMX.org/resources/SDMXML/schemas/v2_0/structure" xmlns:dxf2="http://dhis2.org/schema/dxf/2.0">
  <Header>
    <ID>DATAELEMENTS</ID>
    <Test>false</Test>
    <Truncated>false</Truncated>
    <Name xml:lang="en">Codelist_${orgunit}_Export</Name>
    <Prepared>${prepared}</Prepared>
    <Sender id="OpenMRS">
      <Name>${orgunit}</Name>
    </Sender>
  </Header>
  <CodeLists>
    <structure:CodeList id="CL_DATAELEMENT" agencyID="OpenMRS" version="1.0" isFinal="true">
    	<structure:Name xml:lang="en">Dataelements</structure:Name>
      <structure:Description xml:lang="en">Datelement codes for this dataset</structure:Description>
    	
    	<c:forEach items="${dataElements}" var="de">
    	<structure:Code value="${de.code}">
    	<structure:Description xml:lang="en">${de.name}</structure:Description>
    	<structure:Query xml:lang="en">${de.sqlQuery}</structure:Query>
      	</structure:Code>			
        </c:forEach>

      
    </structure:CodeList>
    </CodeLists>
</Structure>