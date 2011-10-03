package org.openmrs.module.sdmxhddataexport.model;

import java.io.Serializable;
import java.util.Date;

public class Query implements  Serializable {

	  private static final long serialVersionUID = 1L;
	  private Integer id;
	  private String name;
	  private String sqlQuery;
	  private Date createdOn;
	  private String createdBy;
	  
	  
	  
	@Override
	public String toString() {
		return "Query [id=" + id + ", name=" + name + ", sqlQuery=" + sqlQuery
				+ ", createdOn=" + createdOn + ", createdBy=" + createdBy + "]";
	}
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
	public String getSqlQuery() {
		return sqlQuery;
	}
	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
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
	  
	  
}
