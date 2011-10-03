package org.openmrs.module.sdmxhddataexport.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Report implements Serializable {

	  private static final long serialVersionUID = 1L;
	  private Integer id;
	  private String name;
	  private Date createdOn;
	  private String createdBy;
	  private Set<ReportDataElement> reportDataElements ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Set<ReportDataElement> getReportDataElements() {
		return reportDataElements;
	}
	public void setReportDataElements(Set<ReportDataElement> reportDataElements) {
		this.reportDataElements = reportDataElements;
	}
	
	  
}
