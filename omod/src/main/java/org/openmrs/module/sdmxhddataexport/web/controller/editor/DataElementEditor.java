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


package org.openmrs.module.sdmxhddataexport.web.controller.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.openmrs.module.sdmxhddataexport.model.DataElement;

/**
 * <p> Class: DataElementEditor </p>
 * <p> Package: org.openmrs.module.sdmxhddataexport.web.controller.editor </p> 
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Sep 22, 2011 3:00:31 PM </p>
 * <p> Update date: Sep 22, 2011 3:00:31 PM </p>
 **/
public class DataElementEditor extends PropertyEditorSupport{
	private Log log = LogFactory.getLog(this.getClass());
	public DataElementEditor() {
	}
	public void setAsText(String text) throws IllegalArgumentException {
		SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
		if (text != null && text.trim().length() > 0 ) {
			try {
				setValue(sDMXHDDataExportService.getDataElementById(NumberUtils.toInt(text)));
			}
			catch (Exception ex) {
				log.error("Error setting text: " + text, ex);
				throw new IllegalArgumentException("DataElement not found: " + ex.getMessage());
			}
		} else {
			setValue(null);
		}
	}
	
	public String getAsText() {
		DataElement s = (DataElement) getValue();
		if (s == null ) {
			return null; 
		} else {
			return s.getId()+"";
		}
	}
}
