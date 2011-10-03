/**
 * <p> File: org.openmrs.module.sdmxhddataexport.model.ReportDataElement.java </p>
 * <p> Project: sdmxhddataexport-1.8-api </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Sep 19, 2011 11:42:29 AM </p>
 * <p> Update date: Sep 19, 2011 11:42:29 AM </p>
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
