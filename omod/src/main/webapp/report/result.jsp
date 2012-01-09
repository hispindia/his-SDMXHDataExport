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
<CrossSectionalData xmlns="http://www.SDMX.org/resources/SDMXML/schemas/v2_0/message" 
                    xmlns:ns="urn:sdmx:org.sdmx.infomodel.keyfamily.KeyFamily=HP_MOH:${DATASET_CODE}:1.0:cross" >

    <Header>
        <ID>SDMX-HD-CSD</ID>
        <Test>false</Test>
        <Truncated>false</Truncated>
        <Name xml:lang="en">${reportName}</Name>
        <Prepared>${prepared}</Prepared>
        <Sender id="OpenMRS">
            <Name>${orgunit}</Name>
        </Sender>
    </Header>
    <ns:DataSet datasetID="${DATASET_CODE}" >
        <c:forEach items="${periods}" var="period">
            <ns:Group VALUE_TYPE="0" FREQ="M" TIME_PERIOD="${period}">
                <ns:Section>
                    <c:forEach items="${periodResults[period]}" var="result">
                        <OBS_VALUE FACILITY="${orgunit}" DATAELEMENT="${result.dataElement.code}" value="${result.result}"/>			
                    </c:forEach>
                </ns:Section>
            </ns:Group>
        </c:forEach>
    </ns:DataSet>
</CrossSectionalData>