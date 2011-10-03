package org.openmrs.module.sdmxhddataexport.web.controller.dataelement;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.openmrs.module.sdmxhddataexport.model.DataElement;
import org.openmrs.module.sdmxhddataexport.util.PagingUtil;
import org.openmrs.module.sdmxhddataexport.util.RequestUtil;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller("SDMXHDDataExportDataElementController")
public class DataElementController {
	
	 Log log = LogFactory.getLog(this.getClass());
	 @RequestMapping(value="/module/sdmxhddataexport/dataElement.form", method=RequestMethod.GET)
		public String view(@RequestParam(value="dataElementId",required=false) Integer  dataElementId,@ModelAttribute("dataElement") DataElement dataElement, Model model){
			SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
			if(dataElementId != null){
				dataElement = sDMXHDDataExportService.getDataElementById(dataElementId);
				model.addAttribute("dataElement",dataElement);
			}
			return "/module/sdmxhddataexport/dataElement/form";
		}
	 
	 @RequestMapping(value="/module/sdmxhddataexport/dataElement.form", method=RequestMethod.POST)
		public String post(@ModelAttribute("dataElement") DataElement dataElement,BindingResult bindingResult, SessionStatus status, Model model){
			new DataElementValidator().validate(dataElement, bindingResult);
			if (bindingResult.hasErrors()) {
				return "/module/sdmxhddataexport/dataElement/form";
			}else{
				SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
				dataElement.setCreatedOn(new java.util.Date());
				dataElement.setCreatedBy(Context.getAuthenticatedUser().getGivenName());
				sDMXHDDataExportService.saveDataElement(dataElement);
				status.setComplete();
				return "redirect:/module/sdmxhddataexport/listDataElement.form";
			}
		}
	 
	 @RequestMapping(value="/module/sdmxhddataexport/listDataElement.form", method=RequestMethod.GET)
		public String list( 
				@RequestParam(value="pageSize",required=false)  Integer pageSize, 
	            @RequestParam(value="currentPage",required=false)  Integer currentPage,
	            @RequestParam(value="searchName",required=false)  String name,
	           
	            Map<String, Object> model, HttpServletRequest request){
		 	SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
			
			int total = sDMXHDDataExportService.countListDataElement(name);
			PagingUtil pagingUtil = new PagingUtil( RequestUtil.getCurrentLink(request) , pageSize, currentPage, total );
			List<DataElement> dataElements =sDMXHDDataExportService.listDataElement (name, pagingUtil.getStartPos(), pagingUtil.getPageSize());

			//process excel here
			

			model.put("dataElements", dataElements );
			model.put("pagingUtil", pagingUtil);
			model.put("searchName", name);
			
			
			
			return "/module/sdmxhddataexport/dataElement/list";

	 }
	 
	 @RequestMapping(value="/module/sdmxhddataexport/listDataElement.form",  method=RequestMethod.POST)
	    public String deleteDataElement(@RequestParam("ids") String[] ids,HttpServletRequest request){
			String temp = "";
	    	HttpSession httpSession = request.getSession();
			Integer dataElementId  = null;
			try{
				SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
				if( ids != null && ids.length > 0 ){
					for(String sId : ids )
					{
						dataElementId = Integer.parseInt(sId);
						if( dataElementId!= null && dataElementId > 0 && CollectionUtils.isEmpty(sDMXHDDataExportService.listReportDataElement(null, dataElementId, null, 0, 1)))
						{
							sDMXHDDataExportService.deleteDataElement(sDMXHDDataExportService.getDataElementById(dataElementId));
						}else{
							//temp += "We can't delete store="+store.getName()+" because that store is using please check <br/>";
							temp = "This dataElement cannot be deleted as it is in use";
						}
					}
				}
			}catch (Exception e) {
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
				"Can not delete dataElement ");
				log.error(e);
			}
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, StringUtils.isBlank(temp) ?  "sdmxhddataexport.dataElement.deleted" : temp);
	    	
	    	return "redirect:/module/sdmxhddataexport/listDataElement.form";
	    }
}
