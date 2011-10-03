package org.openmrs.module.sdmxhddataexport.web.controller.query;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.openmrs.module.sdmxhddataexport.model.Query;
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

@Controller("SDMXHDDataExportQueryController")
public class QueryController {
	Log log = LogFactory.getLog(this.getClass());
	 @RequestMapping(value="/module/sdmxhddataexport/query.form", method=RequestMethod.GET)
		public String view(@RequestParam(value="queryId",required=false) Integer  queryId,@ModelAttribute("query") Query query, Model model){
			SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
			if(queryId != null){
				query = sDMXHDDataExportService.getQueryById(queryId);
				model.addAttribute("query",query);
			}
			return "/module/sdmxhddataexport/query/form";
		}
	 
	 @RequestMapping(value="/module/sdmxhddataexport/query.form", method=RequestMethod.POST)
		public String post(@ModelAttribute("query") Query query,BindingResult bindingResult, SessionStatus status, Model model){
			new QueryValidator().validate(query, bindingResult);
			if (bindingResult.hasErrors()) {
				return "/module/sdmxhddataexport/query/form";
			}else{
				SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
				query.setCreatedOn(new java.util.Date());
				query.setCreatedBy(Context.getAuthenticatedUser().getGivenName());
				sDMXHDDataExportService.saveQuery(query);
				status.setComplete();
				return "redirect:/module/sdmxhddataexport/listQuery.form";
			}
		}
	 
	 @RequestMapping(value="/module/sdmxhddataexport/listQuery.form", method=RequestMethod.GET)
		public String list( 
				@RequestParam(value="pageSize",required=false)  Integer pageSize, 
	            @RequestParam(value="currentPage",required=false)  Integer currentPage,
	            @RequestParam(value="searchName",required=false)  String name,
	           
	            Map<String, Object> model, HttpServletRequest request){
		 	SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
			
			int total = sDMXHDDataExportService.countListQuery(name);
			PagingUtil pagingUtil = new PagingUtil( RequestUtil.getCurrentLink(request) , pageSize, currentPage, total );
			List<Query> queries =sDMXHDDataExportService.listQuery (name, pagingUtil.getStartPos(), pagingUtil.getPageSize());

			//process excel here
			

			model.put("queries", queries );
			model.put("pagingUtil", pagingUtil);
			model.put("searchName", name);
			
			
			
			return "/module/sdmxhddataexport/query/list";

	 }
	 
	 @RequestMapping(value="/module/sdmxhddataexport/listQuery.form",  method=RequestMethod.POST)
	    public String deleteQuery(@RequestParam("ids") String[] ids,HttpServletRequest request){
			String temp = "";
	    	HttpSession httpSession = request.getSession();
			Integer queryId  = null;
			try{
				SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
				if( ids != null && ids.length > 0 ){
					for(String sId : ids )
					{
						queryId = Integer.parseInt(sId);
						if( queryId!= null && queryId > 0  && CollectionUtils.isEmpty(sDMXHDDataExportService.listReportDataElement(null, null, queryId, 0, 1)))
						{
							sDMXHDDataExportService.deleteQuery(sDMXHDDataExportService.getQueryById(queryId));
						}else{
							//temp += "We can't delete store="+store.getName()+" because that store is using please check <br/>";
							temp = "This query cannot be deleted as it is in use";
						}
					}
				}
			}catch (Exception e) {
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
				"Can not delete query ");
				log.error(e);
			}
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, StringUtils.isBlank(temp) ?  "sdmxhddataexport.query.deleted" : temp);
	    	
	    	return "redirect:/module/sdmxhddataexport/listQuery.form";
	    }
}
