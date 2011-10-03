package org.openmrs.module.sdmxhddataexport.web.controller.report;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.openmrs.module.sdmxhddataexport.model.DataElement;
import org.openmrs.module.sdmxhddataexport.model.Query;
import org.openmrs.module.sdmxhddataexport.model.Report;
import org.openmrs.module.sdmxhddataexport.model.ReportDataElement;
import org.openmrs.module.sdmxhddataexport.model.ReportDataElementResult;
import org.openmrs.module.sdmxhddataexport.web.controller.editor.DataElementEditor;
import org.openmrs.module.sdmxhddataexport.web.controller.editor.QueryEditor;
import org.openmrs.module.sdmxhddataexport.web.controller.editor.ReportEditor;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller("SDMXHDDataExportReportDataElementController")
public class ReportDataElementController {
	Log log = LogFactory.getLog(this.getClass());
	 @RequestMapping(value="/module/sdmxhddataexport/reportDataElement.form", method=RequestMethod.GET)
		public String view(@RequestParam(value="reportDataElementId",required=false) Integer  reportDataElementId,@RequestParam(value="reportId",required=false) Integer  reportId,@ModelAttribute("reportDataElement") ReportDataElement reportDataElement, Model model){
			SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
			if(reportDataElementId != null){
				reportDataElement = sDMXHDDataExportService.getReportDataElementById(reportDataElementId);
				reportId = reportDataElement.getReport().getId();
				model.addAttribute("reportDataElement",reportDataElement);
				
			}
			model.addAttribute("reportId",reportId);
			return "/module/sdmxhddataexport/report/reportDataElement";
		}
	 
	 	@RequestMapping(value="/module/sdmxhddataexport/reportDataElement.form", method=RequestMethod.POST)
		public String post(@ModelAttribute("reportDataElement") ReportDataElement reportDataElement,BindingResult bindingResult, SessionStatus status, Model model){
			new ReportDataElementValidator().validate(reportDataElement, bindingResult);
			if (bindingResult.hasErrors()) {
				return "/module/sdmxhddataexport/report/reportDataElement";
			}else{
				SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
				reportDataElement.setCreatedOn(new java.util.Date());
				sDMXHDDataExportService.saveReportDataElement(reportDataElement);
				status.setComplete();
				return "redirect:/module/sdmxhddataexport/reportDataElement.form?reportId="+reportDataElement.getReport().getId();
			}
			//return "/module/sdmxhddataexport/report/reportDataElement";
		}
	 	
	 	@RequestMapping(value="/module/sdmxhddataexport/resultExecuteReport.form", method=RequestMethod.GET)
		public String executeReport(@RequestParam(value="reportId",required=false) Integer  reportId, 
				@RequestParam(value="startDate",required=false) String  startDate, 
				@RequestParam(value="endDate",required=false) String  endDate, 
				Model model){
			
				SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
				List<ReportDataElement> list = sDMXHDDataExportService.listReportDataElement(reportId, null, null, 0, 0);
				
				if(CollectionUtils.isNotEmpty(list)){
					List<ReportDataElementResult> results = new ArrayList<ReportDataElementResult>();
					for(ReportDataElement reportDataElement : list){
						ReportDataElementResult result = new ReportDataElementResult();
						result.setDataElement(reportDataElement.getDataElement());
						result.setId(reportDataElement.getId());
						result.setReport(reportDataElement.getReport());
						result.setQuery(reportDataElement.getQuery());
						result.setResult(sDMXHDDataExportService.executeQuery(reportDataElement.getQuery().getSqlQuery(), startDate, endDate));
						results.add(result);
					}
					model.addAttribute("results", results);
				}
				return "/module/sdmxhddataexport/report/resultExecuteReport";
			
			//return "/module/sdmxhddataexport/report/reportDataElement";
		}
	 	
	 	@RequestMapping(value="/module/sdmxhddataexport/deleteReportDataElement.form",  method=RequestMethod.POST)
	    public String deleteReportDataElement(@RequestParam("ids") String[] ids,HttpServletRequest request){
			String temp = "";
	    	HttpSession httpSession = request.getSession();
			Integer reportDataElementId  = null;
			Integer reportId = null;
			try{
				SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
				if( ids != null && ids.length > 0 ){
					for(String sId : ids )
					{
						reportDataElementId = Integer.parseInt(sId);
						if( reportDataElementId!= null && reportDataElementId > 0)
						{
							ReportDataElement reportDataElement =sDMXHDDataExportService.getReportDataElementById(reportDataElementId);
							sDMXHDDataExportService.deleteReportDataElement(reportDataElement);
							reportId = reportDataElement.getReport().getId();
						}else{
							//temp += "We can't delete store="+store.getName()+" because that store is using please check <br/>";
							temp = "This reportDataElement cannot be deleted as it is in use";
						}
					}
				}
			}catch (Exception e) {
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
				"Can not delete reportDataElement ");
				log.error(e);
			}
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, StringUtils.isBlank(temp) ?  "sdmxhddataexport.reportDataElement.deleted" : temp);
	    	
	    	return "redirect:/module/sdmxhddataexport/reportDataElement.form?reportId="+reportId;
	    }
	 	@InitBinder
		public void initBinder(WebDataBinder binder) {
			binder.registerCustomEditor(Report.class, new ReportEditor());
			binder.registerCustomEditor(Query.class, new QueryEditor());
			binder.registerCustomEditor(DataElement.class, new DataElementEditor());
	 	}
	 @ModelAttribute("reportDataElements")
	 public List<ReportDataElement> reportDataElements(HttpServletRequest request){
		 SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
		 Integer reportId = NumberUtils.toInt(request.getParameter("reportId"), 0 );
		 List<ReportDataElement> reportDataElements = sDMXHDDataExportService.listReportDataElement(reportId, null, null, 0, 0);
		 return reportDataElements;
	 }
	 @ModelAttribute("reports")
	 public List<Report> reports(){
		 SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
		 List<Report> reports = sDMXHDDataExportService.listReport("", 0, 0);
		 return reports;
	 }
	 @ModelAttribute("queries")
	 public List<Query> queries(){
		 SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
		 List<Query> queries = sDMXHDDataExportService.listQuery("", 0, 0);
		 return queries;
	 }
	 @ModelAttribute("dataElements")
	 public List<DataElement> dataelements(){
		 SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
		 List<DataElement> dataelements = sDMXHDDataExportService.listDataElement("", 0, 0);
		 return dataelements;
	 }
}
