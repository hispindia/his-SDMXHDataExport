package org.openmrs.module.sdmxhddataexport.web.controller.result;

import org.openmrs.api.context.Context;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.openmrs.module.sdmxhddataexport.model.ReportDataElement;
import org.openmrs.module.sdmxhddataexport.model.ReportDataElementResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("SDMXHDDataExportResultController")
public class ResultController {

	@RequestMapping(value = "/module/sdmxhddataexport/result/executeQuery.form", method = RequestMethod.GET)
	public String execute(
			@RequestParam(value = "reportDataElementId") Integer reportDataElementId,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate, Model model) {

		ReportDataElement reportDataElement = Context.getService(
				SDMXHDDataExportService.class).getReportDataElementById(
				reportDataElementId);
		ReportDataElementResult result = new ReportDataElementResult(
				reportDataElement);
		Integer times = Context.getService(SDMXHDDataExportService.class).executeQuery(
				result.getQuery().getSqlQuery(), startDate, endDate);
		result.setResult(times);
		model.addAttribute("result", result);
		
		return "/module/sdmxhddataexport/result/result";
	}

}
