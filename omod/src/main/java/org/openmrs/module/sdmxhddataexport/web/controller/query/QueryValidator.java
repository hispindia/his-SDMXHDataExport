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
