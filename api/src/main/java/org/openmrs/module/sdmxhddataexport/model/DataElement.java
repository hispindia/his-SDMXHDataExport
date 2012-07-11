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
 * <p>
 * Class: DataElement
 * </p>
 * <p>
 * Package: org.openmrs.module.sdmxhddataexport.model
 * </p>
 * <p>
 * Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com)
 * </p>
 * <p>
 * Update by: Nguyen manh chuyen
 * </p>
 * <p>
 * Version: $1.0
 * </p>
 * <p>
 * Create date: Sep 15, 2011 12:38:53 PM
 * </p>
 * <p>
 * Update date: Sep 15, 2011 12:38:53 PM
 * </p>
 **/
public class DataElement implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Date createdOn;
	private String createdBy;
	private String code;
	private String sqlQuery;

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

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	
	public String getSqlQuery() {
		return sqlQuery;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
