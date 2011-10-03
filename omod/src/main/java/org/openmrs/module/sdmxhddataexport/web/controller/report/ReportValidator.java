package org.openmrs.module.sdmxhddataexport.web.controller.report;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.openmrs.module.sdmxhddataexport.model.Report;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReportValidator implements  Validator {

	/**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
    	return Report.class.equals(clazz);
    }

	/**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors error) {
    	Report report = (Report) command;
    	
    	if( StringUtils.isBlank(report.getName())){
    		error.reject("sdmxhddataexport.report.name.required");
    	}
    	SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
    	Report reportE = sDMXHDDataExportService.getReportByName(report.getName());
    	if(report.getId() != null){
    		if(reportE != null && reportE.getId().intValue() != report.getId().intValue()){
    			error.reject("sdmxhddataexport.report.name.existed");
    		}
    	}else{
    		if(reportE != null){
    	    		error.reject("sdmxhddataexport.report.name.existed");
    		}
    	}
    	
    	
    }
}
