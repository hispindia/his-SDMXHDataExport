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

import java.io.Serializable;
import java.util.Date;

/**
 * <p> Class: ReportDataElement </p>
 * <p> Package: org.openmrs.module.sdmxhddataexport.model </p> 
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Sep 19, 2011 11:42:29 AM </p>
 * <p> Update date: Sep 19, 2011 11:42:29 AM </p>
 **/
public class ReportDataElement implements Serializable {

	  private static final long serialVersionUID = 1L;
	  private Integer id;
	  private Report report;
	  private DataElement dataElement;
	  private Query query;
	  private Date createdOn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	public DataElement getDataElement() {
		return dataElement;
	}
	public void setDataElement(DataElement dataElement) {
		this.dataElement = dataElement;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	@Override
	public String toString() {
		return "ReportDataElement [id=" + id + ", report=" + report
				+ ", dataElement=" + dataElement + ", query=" + query
				+ ", createdOn=" + createdOn + "]";
	}
	  
	  
	  

}
