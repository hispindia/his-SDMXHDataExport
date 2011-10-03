package org.openmrs.module.sdmxhddataexport.web.controller.query;

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.openmrs.module.sdmxhddataexport.model.Query;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class QueryValidator implements  Validator {

	/**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
    	return Query.class.equals(clazz);
    }

	/**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors error) {
    	Query query = (Query) command;
    	
    	if( StringUtils.isBlank(query.getName())){
    		error.reject("sdmxhddataexport.query.name.required");
    	}
    	if( StringUtils.isBlank(query.getSqlQuery())){
    		error.reject("sdmxhddataexport.query.sqlQuery.required");
    	}else{
    		String temp = query.getSqlQuery().toLowerCase().trim();
    		if(!temp.startsWith("select")){
    			error.reject("sdmxhddataexport.query.sqlQuery.wrong");
    		}
    		if(temp.indexOf("?") != 2){
    			error.reject("sdmxhddataexport.query.sqlQuery.wrong");
    		}
    	}
    	SDMXHDDataExportService inventoryService = Context.getService(SDMXHDDataExportService.class);
    	Query queryE = inventoryService.getQueryByName(query.getName());
    	if(query.getId() != null){
    		if(queryE != null && queryE.getId().intValue() != query.getId().intValue()){
    			error.reject("sdmxhddataexport.query.name.existed");
    		}
    	}else{
    		if(queryE != null){
    	    		error.reject("sdmxhddataexport.query.name.existed");
    		}
    	}
    	
    	
    }
    public static void main(String[] args) {
		String tem = "a ? ? a";
		System.out.println(tem.indexOf("?"));
	}
}
