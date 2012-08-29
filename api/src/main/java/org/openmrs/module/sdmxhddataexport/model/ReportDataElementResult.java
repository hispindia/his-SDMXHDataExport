/**
 *  Copyright 2011 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of SDMXDataExport module.
 *
 *  SDMXDataExport module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  SDMXDataExport module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with SDMXDataExport module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.sdmxhddataexport.model;

import java.util.Date;

public class ReportDataElementResult extends ReportDataElement{

	private static final long serialVersionUID = 1L;
	private Integer result;
	
	public ReportDataElementResult(){
		
	}
	
	public ReportDataElementResult(ReportDataElement reportDataElement){
		this.setCreatedOn(new Date());
		this.setDataElement(reportDataElement.getDataElement());
		this.setQuery(reportDataElement.getQuery());
		this.setReport(reportDataElement.getReport());		
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	
	
}
