/**
 * <p> File: org.openmrs.module.sdmxhddataexport.web.controller.query.ExecuteQueryController.java </p>
 * <p> Project: sdmxhddataexport-1.8-omod </p>
 * <p> Copyright (c) 2011 HISP Technologies. </p>
 * <p> All rights reserved. </p>
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Sep 21, 2011 3:26:03 PM </p>
 * <p> Update date: Sep 21, 2011 3:26:03 PM </p>
 **/

package org.openmrs.module.sdmxhddataexport.web.controller.query;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p> Class: ExecuteQueryController </p>
 * <p> Package: org.openmrs.module.sdmxhddataexport.web.controller.query </p> 
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Sep 21, 2011 3:26:03 PM </p>
 * <p> Update date: Sep 21, 2011 3:26:03 PM </p>
 **/

@Controller("SDMXHDDataExportExecuteQueryController")
public class ExecuteQueryController {
	Log log = LogFactory.getLog(this.getClass());
	 @RequestMapping(value="/module/sdmxhddataexport/executeQuery.form", method=RequestMethod.GET)
		public String view(
				HttpServletRequest request,
				Model model){
		 SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
		    String queryId = request.getParameter("queryId");
		 	String sqlQuery = request.getParameter("sqlQuery");
		 	//String startDate = request.getParameter("startDate");
		 	//String endDate = request.getParameter("endDate");
		 	//String result = request.getParameter("result");
		 	String sql = "";
			if(StringUtils.isBlank(sqlQuery)){
				sql = sDMXHDDataExportService.getQueryById(NumberUtils.toInt(queryId)).getSqlQuery();
			}else{
				sql = sqlQuery;
			}
			model.addAttribute("queryId", queryId);
			model.addAttribute("sqlQuery", sql);
			//model.addAttribute("startDate", startDate);
			//model.addAttribute("endDate", endDate);
			//model.addAttribute("result", result);
			return "/module/sdmxhddataexport/query/executeQuery";
		}
	 @RequestMapping(value="/module/sdmxhddataexport/executeQuery.form", method=RequestMethod.POST)
		public @ResponseBody String POST(
				HttpServletRequest request,
				Model model){
		 	String queryId = request.getParameter("queryId");
		 	String sqlQuery = request.getParameter("sqlQuery");
		 	String startDate = request.getParameter("startDate");
		 	String endDate = request.getParameter("endDate");
		 	
			SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
			String sql = "";
			if(StringUtils.isBlank(sqlQuery)){
				sql = sDMXHDDataExportService.getQueryById(NumberUtils.toInt(queryId)).getSqlQuery();
			}else{
				sql = sqlQuery;
			}
			Integer result = sDMXHDDataExportService.executeQuery(sql, startDate, endDate);
			return "Result: "+result;
		}
}
