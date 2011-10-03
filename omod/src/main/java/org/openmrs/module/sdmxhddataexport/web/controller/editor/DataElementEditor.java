/**
 * <p> File: org.openmrs.module.sdmxhddataexport.web.controller.editor.DataElementEditor.java </p>
 * <p> Project: sdmxhddataexport-1.8-omod </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Sep 22, 2011 3:00:31 PM </p>
 * <p> Update date: Sep 22, 2011 3:00:31 PM </p>
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
