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
package org.openmrs.module.sdmxhddataexport.web.controller.report;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

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
import org.openmrs.module.sdmxhddataexport.util.HttpRestUtil;
import org.openmrs.module.sdmxhddataexport.util.MyHandler;
import org.openmrs.module.sdmxhddataexport.web.controller.editor.DataElementEditor;
import org.openmrs.module.sdmxhddataexport.web.controller.editor.QueryEditor;
import org.openmrs.module.sdmxhddataexport.web.controller.editor.ReportEditor;
import org.openmrs.module.sdmxhddataexport.web.controller.utils.SDMXHDataExportUtils;
import org.openmrs.util.OpenmrsClassLoader;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

@Controller("SDMXHDDataExportReportDataElementController")
public class ReportDataElementController {

    Log log = LogFactory.getLog(this.getClass());
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @RequestMapping(value = "/module/sdmxhddataexport/reportDataElement.form", method = RequestMethod.GET)
    public String view(
            @RequestParam(value = "reportDataElementId", required = false) Integer reportDataElementId,
            @RequestParam(value = "reportId", required = false) Integer reportId,
            @ModelAttribute("reportDataElement") ReportDataElement reportDataElement,
            Model model) {
        SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
        if (reportDataElementId != null) {
            reportDataElement = sDMXHDDataExportService.getReportDataElementById(reportDataElementId);
            reportId = reportDataElement.getReport().getId();
            model.addAttribute("reportDataElement", reportDataElement);

        }
        model.addAttribute("reportId", reportId);
        return "/module/sdmxhddataexport/report/reportDataElement";
    }

    public String transform(String str) throws Exception
    {											   
       try {
          //String XMLFileName = "src\\cdcatalog.xml";
          //String OutputFileName = "src\\myResult.html";
        //  String StyleFileName = "/media/xml-to-string.xsl";
    	  InputStream inpXsl = OpenmrsClassLoader.getInstance().getResourceAsStream("xml-to-string.xsl");
          
          
          XMLReader reader = XMLReaderFactory.createXMLReader();
          InputStream inp=new ByteArrayInputStream(str.getBytes());
          Source source = new SAXSource(reader, new InputSource(inp));
          //Source source2 = new SAXSource(reader, new InputSource(StyleFileName));
          //ContentHandler theHandler = new MyHandler();
          StringWriter outWriter = new StringWriter();  
          StreamResult result = new StreamResult( outWriter ); 
          //Result result = new StreamResult(OutputFileName);
//          InputStream stinp=new ByteArrayInputStream(styleText.getBytes());         
          Source style = new StreamSource(inpXsl);

          TransformerFactory transFactory = TransformerFactory.newInstance();
          Transformer trans = transFactory.newTransformer(style);

          trans.transform(source, result);
          String s = outWriter.getBuffer().toString();
          System.out.println(s);
          return s;
       } catch (SAXException e) {
           System.out.println(e.getMessage());
           return e.getMessage();
       }
    }
    @RequestMapping(value = "/module/sdmxhddataexport/reportDataElement.form", method = RequestMethod.POST)
    public String post(
            @ModelAttribute("reportDataElement") ReportDataElement reportDataElement,
            BindingResult bindingResult, SessionStatus status, Model model) {
        new ReportDataElementValidator().validate(reportDataElement,
                bindingResult);
        if (bindingResult.hasErrors()) {
        	System.out.println("binding errors");
            return "/module/sdmxhddataexport/report/reportDataElement";
        } else {
        	System.out.println("Supposedly No errors");
            SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
            reportDataElement.setCreatedOn(new java.util.Date());
            sDMXHDDataExportService.saveReportDataElement(reportDataElement);
            status.setComplete();
            return "redirect:/module/sdmxhddataexport/reportDataElement.form?reportId="
                    + reportDataElement.getReport().getId();
        }
        
    }

    @RequestMapping(value = "/module/sdmxhddataexport/resultExecuteReport.form", method = RequestMethod.GET)
    public String executeReport(
            @RequestParam(value = "reportId", required = false) Integer reportId,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            Model model) throws ParseException {

        SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
        List<ReportDataElement> list = sDMXHDDataExportService.listReportDataElement(reportId, null, null, 0, 0);
        String dataSetCode = "";
        String reportName = "";
        List<String> periods = new ArrayList<String>();
        Map<String, List<ReportDataElementResult>> periodResults = new HashMap<String, List<ReportDataElementResult>>();
        
        if (CollectionUtils.isNotEmpty(list)) {
            dataSetCode = list.get(0).getReport().getCode();
            reportName = list.get(0).getReport().getName();
            Date begin = SDMXHDataExportUtils.getFirstDate(sdf.parse(startDate));
            Date end = SDMXHDataExportUtils.getLastDate(begin);
            SimpleDateFormat periodFormatter = new SimpleDateFormat("yyyyMM");
            Date finalDate = SDMXHDataExportUtils.getLastDate(sdf.parse(endDate));
            while (begin.before(finalDate)) {
                periods.add(periodFormatter.format(begin));
                List<ReportDataElementResult> results = new ArrayList<ReportDataElementResult>();
                for (ReportDataElement reportDataElement : list) {
                    ReportDataElementResult result = new ReportDataElementResult();
                    result.setDataElement(reportDataElement.getDataElement());
                    result.setId(reportDataElement.getId());
                    result.setReport(reportDataElement.getReport());
                    result.setQuery(reportDataElement.getQuery());
                    result.setResult(sDMXHDDataExportService.executeQuery(
                            reportDataElement.getDataElement().getSqlQuery(),
                            sdf.format(begin), sdf.format(end)));
                    results.add(result);
                }

                periodResults.put(periodFormatter.format(begin), results);
                begin = SDMXHDataExportUtils.nextMonth(begin);
                end = SDMXHDataExportUtils.getLastDate(begin);
            }
            String orgunitCode = Context.getAdministrationService().getGlobalProperty("sdmxhddataexport.organisationUnit");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            model.addAttribute("DATASET_CODE", dataSetCode);
            model.addAttribute("reportName", reportName);
            model.addAttribute("periods", periods);
            model.addAttribute("periodResults", periodResults);
            model.addAttribute("orgunit", orgunitCode);
            model.addAttribute("prepared", formatter.format(new Date()));
        }
        return "/module/sdmxhddataexport/report/result";
    }

    @RequestMapping(value = "/module/sdmxhddataexport/downloadExecutedReport.form", method = RequestMethod.GET)
    public String downloadExecutedReport(
            @RequestParam(value = "reportId", required = false) Integer reportId,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "outputType", required = false) String outputType,
            HttpServletRequest request, HttpServletResponse response,
            Model model)
            throws ParseException, IOException {
    	String u=request.getParameter("url");
    	System.out.print("i think it is a url - "+u);
    	//String s=(u.split("/")[2]).split(":")[0];
        String urlToRead = "http://" + u 
                +  request.getContextPath()
                + "/module/sdmxhddataexport/resultExecuteReport.form?reportId="
                + reportId + "&startDate=" + startDate + "&endDate=" + endDate;
//    	String urlToRead = "http://" + "127.0.0.1" + ":"
//        + request.getLocalPort() + request.getContextPath()
//        + "/module/sdmxhddataexport/resultExecuteReport.form?reportId="
//        + reportId + "&startDate=" + startDate + "&endDate=" + endDate;
        URL url;
        
        System.out.println("http servlet request"+ u +request.getLocalAddr()+","+request.getLocalName());
        HttpURLConnection conn;
        InputStream rd = null;
        String contents="";
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = conn.getInputStream();
            byte[] bytes = new byte[1024];
            int bytesRead;
            boolean firstRead = true;
            
            while ((bytesRead = rd.read(bytes)) != -1) {
                String str = new String(bytes);
                str = str.substring(0, bytesRead);
                //if(firstRead){
                firstRead = false;
                str = str.replaceAll("^\\s+", "");
                System.out.print(str);
                //}
                contents=contents.concat(str);
                
            }
            //rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        if(outputType.contentEquals("download")){
//        	File fil1=new File("/sdmx-temp-sdmx.xml");
//        	FileOutputStream fos = new FileOutputStream("sdmx-temp-sdmx.xml");
        	File file = File.createTempFile("sdmx", "report");
        	Writer output = new BufferedWriter(new FileWriter(file));
        	output.write(contents);
        	output.flush();
//        	output = new BufferedWriter(new FileWriter(fil1));
//        	output.write(contents);
//        	output.flush();
      
        	output.close();
        	System.out.println("these are contents ------------------------");
        	System.out.println(contents);
        	System.out.println("these wre contents ------------------------");
        	response.setContentType("application/download");
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        	response.setHeader("Content-Disposition", "attachment; filename=\""
        			+ "sdmxhd-" + formatter.format(new Date()) + ".xml" + "\"");
        	FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
            file.delete();

        }
        
        
        if(outputType.equals("view")){
        	try {
				contents=transform(contents);
			} catch (Exception e) {
				System.out.println("some error"+ contents);
				e.printStackTrace();
			}
        	model.addAttribute("contents",contents);
        	System.out.println("Now contents---------------------------" + contents +":");
        	return "/module/sdmxhddataexport/report/resultView";
        }
        else if(outputType.equals("send")){
            HttpSession httpSession = request.getSession();

        	String urlStr = Context.getAdministrationService().getGlobalProperty("sdmxhddataexport.reportUrl");
        	String[] paramName = {"report1"};
        	String[] paramVal = new String[10];
        	paramVal[0] = contents;
        	try {
				String Response = HttpRestUtil.httpRestPost(urlStr, paramName, paramVal);
				System.out.println("Response:" + Response);
				String temp = "Report Sent Successfully";
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, StringUtils.isBlank(temp) ?  "sdmxhddataexport.dataElement.deleted" : temp);

			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    	return "/module/sdmxhddataexport/report/list";
    }

    @RequestMapping(value = "/module/sdmxhddataexport/extractMonth.form", method = RequestMethod.GET)
    public void extractMonth(@RequestParam(value = "date") String dateStr,
            HttpServletResponse response) throws IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Date date = sdf.parse(dateStr);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
        out.print(formatter.format(date));
    }

    @RequestMapping(value = "/module/sdmxhddataexport/deleteReportDataElement.form", method = RequestMethod.POST)
    public String deleteReportDataElement(@RequestParam("ids") String[] ids,
            HttpServletRequest request) {
        String temp = "";
        HttpSession httpSession = request.getSession();
        Integer reportDataElementId = null;
        Integer reportId = null;
        try {
            SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
            if (ids != null && ids.length > 0) {
                for (String sId : ids) {
                    reportDataElementId = Integer.parseInt(sId);
                    if (reportDataElementId != null && reportDataElementId > 0) {
                        ReportDataElement reportDataElement = sDMXHDDataExportService.getReportDataElementById(reportDataElementId);
                        sDMXHDDataExportService.deleteReportDataElement(reportDataElement);
                        reportId = reportDataElement.getReport().getId();
                    } else {
                        // temp +=
                        // "We can't delete store="+store.getName()+" because that store is using please check <br/>";
                        temp = "This reportDataElement cannot be deleted as it is in use";
                    }
                }
            }
        } catch (Exception e) {
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
                    "Can not delete reportDataElement ");
            log.error(e);
        }
        httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, StringUtils.isBlank(temp) ? "sdmxhddataexport.reportDataElement.deleted"
                : temp);

        return "redirect:/module/sdmxhddataexport/reportDataElement.form?reportId="
                + reportId;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Report.class, new ReportEditor());
        binder.registerCustomEditor(Query.class, new QueryEditor());
        binder.registerCustomEditor(DataElement.class, new DataElementEditor());
    }

    @ModelAttribute("reportDataElements")
    public List<ReportDataElement> reportDataElements(HttpServletRequest request) {
        SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
        Integer reportId = NumberUtils.toInt(request.getParameter("reportId"),
                0);
        List<ReportDataElement> reportDataElements = sDMXHDDataExportService.listReportDataElement(reportId, null, null, 0, 0);
        return reportDataElements;
    }

    @ModelAttribute("reports")
    public List<Report> reports() {
        SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
        List<Report> reports = sDMXHDDataExportService.listReport("", 0, 0);
        return reports;
    }

    @ModelAttribute("queries")
    public List<Query> queries() {
        SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
        List<Query> queries = sDMXHDDataExportService.listQuery("", 0, 0);
        return queries;
    }

    @ModelAttribute("dataElements")
    public List<DataElement> dataelements() {
        SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
        List<DataElement> dataelements = sDMXHDDataExportService.listDataElement("", 0, 0);
        return dataelements;
    }
}
