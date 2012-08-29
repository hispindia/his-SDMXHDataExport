package org.openmrs.module.sdmxhddataexport.web.controller.utils;

import java.util.Calendar;
import java.util.Date;

public class SDMXHDataExportUtils {
	
	public static Date getLastDate(Date month){
 		
		Date date = nextMonth(month);
		Calendar calendar = Calendar.getInstance();
 		calendar.setTime(date);
 		calendar.add(Calendar.DATE, -1);
 		return calendar.getTime();
 	}
	
	public static Date nextMonth(Date date){
		Calendar calendar = Calendar.getInstance();
 		calendar.setTime(getFirstDate(date));
 		calendar.add(Calendar.MONTH, 1); 		
 		return calendar.getTime();
	}
	
	public static Date getFirstDate(Date date){
		Calendar calendar = Calendar.getInstance();
 		calendar.setTime(date);
 		calendar.set(Calendar.DATE, 1); 		
 		return calendar.getTime();
	}
}
