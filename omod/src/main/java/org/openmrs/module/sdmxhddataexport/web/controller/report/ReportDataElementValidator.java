/**
 * <p> File: org.openmrs.module.sdmxhddataexport.web.controller.report.ReportDataElementValidator.java </p>
 * <p> Project: sdmxhddataexport-1.8-omod </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Sep 22, 2011 2:03:38 PM </p>
 * <p> Update date: Sep 22, 2011 2:03:38 PM </p>
 **/

package org.openmrs.module.sdmxhddataexport.web.controller.report;

import org.openmrs.api.context.Context;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.openmrs.module.sdmxhddataexport.model.ReportDataElement;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <p> Class: ReportDataElementValidator </p>
 * <p> Package: org.openmrs.module.sdmxhddataexport.web.controller.report </p> 
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Sep 22, 2011 2:03:38 PM </p>
 * <p> Update date: Sep 22, 2011 2:03:38 PM </p>
 **/
public class ReportDataElementValidator implements  Validator {

	/**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
    	return ReportDataElement.class.equals(clazz);
    }

	/**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors error) {
    	ReportDataElement reportDataElement = (ReportDataElement) command;
    	
    	if(reportDataElement.getReport() == null){
    		error.reject("sdmxhddataexport.reportDataElement.report.required");
    	}
    	if(reportDataElement.getDataElement() == null){
    		error.reject("sdmxhddataexport.reportDataElement.dataElement.required");
    	}
    	if(reportDataElement.getQuery() == null){
    		error.reject("sdmxhddataexport.reportDataElement.query.required");
    	}
    	SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
    	ReportDataElement reportE = sDMXHDDataExportService.getReportDataElement(reportDataElement.getReport().getId(), reportDataElement.getDataElement().getId());
    	if(reportDataElement.getId() != null){
    		if(reportE != null && reportE.getId().intValue() != reportDataElement.getId().intValue()){
    			error.reject("sdmxhddataexport.reportDataElement.existed");
    		}
    	}else{
    		if(reportE != null){
    	    		error.reject("sdmxhddataexport.reportDataElement.existed");
    		}
    	}
    	
    	
    }
}
