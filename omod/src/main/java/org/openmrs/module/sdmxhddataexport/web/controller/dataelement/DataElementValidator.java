package org.openmrs.module.sdmxhddataexport.web.controller.dataelement;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.openmrs.module.sdmxhddataexport.model.DataElement;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DataElementValidator implements  Validator {

	/**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
    	return DataElement.class.equals(clazz);
    }

	/**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors error) {
    	DataElement dataElement = (DataElement) command;
    	
    	if( StringUtils.isBlank(dataElement.getName())){
    		error.reject("sdmxhddataexport.dataElement.name.required");
    	}
    	SDMXHDDataExportService inventoryService = Context.getService(SDMXHDDataExportService.class);
    	DataElement dataElementE = inventoryService.getDataElementByName(dataElement.getName());
    	if(dataElement.getId() != null){
    		if(dataElementE != null && dataElementE.getId().intValue() != dataElement.getId().intValue()){
    			error.reject("sdmxhddataexport.dataElement.name.existed");
    		}
    	}else{
    		if(dataElementE != null){
    	    		error.reject("sdmxhddataexport.dataElement.name.existed");
    		}
    	}
    	
    	
    }
}
