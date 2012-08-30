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
    	if( StringUtils.isBlank(dataElement.getCode())){
    		error.reject("sdmxhddataexport.dataElement.code.required");
    	}
    	SDMXHDDataExportService inventoryService = Context.getService(SDMXHDDataExportService.class);
    	DataElement dataElementE = inventoryService.getDataElementByName(dataElement.getName());

    	if(dataElement.getSqlQuery() =="")
    	{
    		error.reject("sdmxhddataexport.dataElement.sql.required");
    	}
    	
    	else
    		
    		{String sql = dataElement.getSqlQuery();
	
		Integer result = inventoryService.executeQuery(sql, "01/07/2012", "01/07/2012");
if(result == -1)
{
	error.reject("sdmxhddataexport.dataElement.sql.wrong");

}
    		}
    	
    	if(dataElement.getId() != null){
    		if(dataElementE != null && dataElementE.getId().intValue() != dataElement.getId().intValue()){
    			error.reject("sdmxhddataexport.dataElement.name.existed");
    		}
    	}else{
    		if(dataElementE != null){
    	    		error.reject("sdmxhddataexport.dataElement.name.existed");
    		}
    	}

    	
    	boolean check = inventoryService.getDataElementByCode(dataElement.getCode());

    	if(check ==false )
    		
    	{
    		error.reject("sdmxhddataexport.dataElement.code.existed");

    	}
  	
    	
    }
    
    
    public void validateEdit(Object command, Errors error) {
    	DataElement dataElement = (DataElement) command;
    	
    	if( StringUtils.isBlank(dataElement.getName())){
    		error.reject("sdmxhddataexport.dataElement.name.required");
    	}
    	if( StringUtils.isBlank(dataElement.getCode())){
    		error.reject("sdmxhddataexport.dataElement.code.required");
    	}
    	SDMXHDDataExportService inventoryService = Context.getService(SDMXHDDataExportService.class);
    	
    	if(dataElement.getSqlQuery() =="")
    	{
    		error.reject("sdmxhddataexport.dataElement.sql.required");
    	}
    	
    	else		
    		{
    		String sql = dataElement.getSqlQuery();
    		Integer result = inventoryService.executeQuery(sql, "01/07/2012", "01/07/2012");
    		if(result == -1)
    		{
			error.reject("sdmxhddataexport.dataElement.sql.wrong");
    		}
    }
    	DataElement check = inventoryService.getDataElementByCode1(dataElement.getCode());
    	if (check!=null)
    		System.out.println(dataElement.getId() + "--" + check.getId());
    	if(dataElement.getId().intValue()!= check.getId().intValue())
      	{
    		System.out.println(dataElement.getId() + "-98908|-" + check.getId());	
    		error.reject("sdmxhddataexport.dataElement.code.existed");
    	}
    	
}
    
    
    
    
    public boolean fileValidate(Object command) {

    	DataElement dataElement = (DataElement) command;

    	boolean check=true;

    	if( StringUtils.isBlank(dataElement.getName())){

    		//error.reject("sdmxhddataexport.dataElement.name.required");

    		check=false;

    	}

    	if( StringUtils.isBlank(dataElement.getCode())){

    		//error.reject("sdmxhddataexport.dataElement.code.required");

    		check=false;

    	}

    	SDMXHDDataExportService inventoryService = Context.getService(SDMXHDDataExportService.class);

    	DataElement dataElementE = inventoryService.getDataElementByName(dataElement.getName());

    	if(dataElement.getId() != null){

    		if(dataElementE != null && dataElementE.getId().intValue() != dataElement.getId().intValue()){

    			//error.reject("sdmxhddataexport.dataElement.name.existed");

    			check=false;

    		}

    	}else{

    		if(dataElementE != null){

    	    	//	error.reject("sdmxhddataexport.dataElement.name.existed");

    			check=false;

    		}

    	}

    	if(dataElement.getSqlQuery() !="")
    	{
    	String sql = dataElement.getSqlQuery();
	
		Integer result = inventoryService.executeQuery(sql, "01/07/2012", "01/07/2012");
if(result==-1)
{
	check =false;

}
    		}

    	boolean check1=inventoryService.getDataElementByCode(dataElement.getCode());

    	if(check1==false)

    	{

    		//error.reject("sdmxhddataexport.dataElement.code.existed");

    		check=false;

    	}

    	

    	return check;

    }
    
    
    
    
    
    
    
}
