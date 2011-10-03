/**
 * <p> File: org.openmrs.module.sdmxhddataexport.model.DataElement.java </p>
 * <p> Project: sdmxhddataexport-1.8-api </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Sep 15, 2011 12:38:53 PM </p>
 * <p> Update date: Sep 15, 2011 12:38:53 PM </p>
 **/

package org.openmrs.module.sdmxhddataexport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> Class: DataElement </p>
 * <p> Package: org.openmrs.module.sdmxhddataexport.model </p> 
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Sep 15, 2011 12:38:53 PM </p>
 * <p> Update date: Sep 15, 2011 12:38:53 PM </p>
 **/
public class DataElement implements  Serializable {

	  private static final long serialVersionUID = 1L;
	  private Integer id;
	  private String name;
	  private Date createdOn;
	  private String createdBy;
	  
	  
	  
	@Override
	public String toString() {
		return "DataElement [id=" + id + ", name=" + name + ", createdOn="
				+ createdOn + ", createdBy=" + createdBy + "]";
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
