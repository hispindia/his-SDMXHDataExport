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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.mail.iap.ByteArray;

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
	 
	 @RequestMapping(value="/module/sdmxhddataexport/uploadDataElements.form", method=RequestMethod.GET)
		public String upload(Model model){
		 System.out.println("1st Part Done");
		 return "/module/sdmxhddataexport/dataElement/upload";
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
	 
	 @RequestMapping(value="/module/sdmxhddataexport/dataElementExport.form", method=RequestMethod.GET)
	 public String export(@ModelAttribute("dataElement") DataElement dataElement,BindingResult bindingResult, SessionStatus status, Map<String, Object> model){
				SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);				
		 		List<DataElement> dataElements =sDMXHDDataExportService.listDataElement ("", 0, 0);
		 		model.put("dataElements", dataElements );
		 		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 		String org = Context.getAdministrationService().getGlobalProperty("hospitalcore.hospitalName");
		 		model.put("orgunit", org);
		 		model.put("prepared", formatter.format(new Date()));
				return "/module/sdmxhddataexport/dataElement/export";
		}
	 
	 
	 @RequestMapping(value = "/module/sdmxhddataexport/downloadDataElements.form", method = RequestMethod.GET)
	    public void downloadDataElements(
	            HttpServletRequest request, HttpServletResponse response)
	            throws ParseException, IOException {

	        String urlToRead = "http://" + request.getLocalAddr() + ":"
	                + request.getLocalPort() + request.getContextPath()
	                + "/module/sdmxhddataexport/dataElementExport.form";
	        URL url;
	        HttpURLConnection conn;
	        response.setContentType("application/download");
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
	        response.setHeader("Content-Disposition", "attachment; filename=\""
	                + "DataElements-" + formatter.format(new Date()) + ".xml" + "\"");

	        try {
	            url = new URL(urlToRead);
	            conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            InputStream rd = conn.getInputStream();
	            byte[] bytes = new byte[1024];
	            int bytesRead;
	            boolean firstRead = true;
	            while ((bytesRead = rd.read(bytes)) != -1) {
	                String str = new String(bytes);
	                str = str.substring(0, bytesRead);
	                if(firstRead){
	                	firstRead = false;
	                	str = str.replaceAll("^\\s+", "");
	                }
	                
	                response.getOutputStream().write(str.getBytes(), 0, str.getBytes().length);
	            }
	            rd.close();
	        } catch (Exception e) {
	            e.printStackTrace();
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
	    public String deleteDataElement(@RequestParam(value="ids",required=false) String[] ids,@RequestParam(value="files",required=false) MultipartFile uploadItem,HttpServletRequest request,@RequestParam(value="upload",required=false) String upload,@RequestParam(value="IncludeQueries",required=false) String IncludeQueries,Object command,SessionStatus status){
			String temp = "";
	    	HttpSession httpSession = request.getSession();
			Integer dataElementId  = null;
			System.out.println(" done " + upload);
			if(upload!=null){
				System.out.println("1st part truly done");
				//FileUpload file = uploadItem;
				String fileName="";
				if(uploadItem!=null){
					fileName = uploadItem.getOriginalFilename();
					System.out.println("2nd part truly done " + fileName);
					
					byte[] byteArray = null;
					try {
						byteArray = uploadItem.getBytes();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Bad file name");
						
						
						e.printStackTrace();
					}
					
					String data = new String(byteArray);
					System.out.println("3rd part truly done " + data);
					XPath xpath = XPathFactory.newInstance().newXPath();
					DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
					domFactory.setNamespaceAware(true); 
					DocumentBuilder builder = null;
					try {
						builder = domFactory.newDocumentBuilder();
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
						
					}
					Document doc = null;
					try {
						doc = builder.parse(uploadItem.getInputStream());
					} catch (SAXException e1) {
						httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Unsupported Document type is uploaded " );

						//or we can use httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "sdmxhddataexport.UploadedFile.Wrong" );

				    	return "redirect:/module/sdmxhddataexport/listDataElement.form";
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "error in reading Document type  " );

						//e1.printStackTrace();

					}
					XPathExpression expr = null;
					XPathExpression exprDesc = null;
					XPathExpression exprQuery = null;
					try {
						expr = xpath.compile("//*[local-name(.)='CodeList' and @id='CL_DATAELEMENT']/*[local-name(.)='Code']");
						exprDesc = xpath.compile("//[local-name(.)='Description']");
						
						exprQuery = xpath.compile("//[local-name(.)='Query']");
					} catch (XPathExpressionException e) {
						httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Error parsing document " );


					}
					Object result = null;
					try {
						result = expr.evaluate(doc, XPathConstants.NODESET);
					} catch (XPathExpressionException e) {
						httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Error parsing document " );

						// e.printStackTrace();
					}
					NodeList nodes = (NodeList) result;
					ArrayList<DataElement> dataElements = new ArrayList<DataElement>();
					for (int i = 0; i < nodes.getLength(); i++) {
						boolean putItIn=false;
						DataElement de = new DataElement();
				System.out.println(nodes.item(i).getLocalName());
						
						NamedNodeMap nmp=nodes.item(i).getAttributes();
						System.out.println(nmp.getNamedItem("value").getNodeValue() + "  hellooo..");
						de.setCode(nmp.getNamedItem("value").getNodeValue());
						//Node tempNode = (Node)nodes.item(i); 
						NodeList innerNodeList=nodes.item(i).getChildNodes();
						System.out.println("length: "+innerNodeList.getLength());
						for(int j=0;j<innerNodeList.getLength();j++){
							if(innerNodeList.item(j).getLocalName()!=null){
								System.out.println("hehe " + innerNodeList.item(j).getLocalName());
							if(innerNodeList.item(j).getLocalName().equals(new String("Description"))){
								System.out.println("Description: " + innerNodeList.item(j).getTextContent());
								de.setName(innerNodeList.item(j).getTextContent());
								
								putItIn=true;
							}
							
							if(innerNodeList.item(j).getLocalName().equals(new String("Query")) && IncludeQueries!=null){
								System.out.println("Query: " + innerNodeList.item(j).getTextContent());
								de.setSqlQuery(innerNodeList.item(j).getTextContent());
							}
							}
						}
						if(putItIn){
						
							boolean bool;		
							DataElementValidator valid = new DataElementValidator();
							bool=valid.fileValidate(de);
							if(bool==true)
							dataElements.add(de);
							
							
							
							
							
						}
					}
					ListIterator<DataElement> li = dataElements.listIterator();
					DataElement de;
					while(li.hasNext()){
						de = (DataElement) li.next();
						System.out.println("Element 1 = " + de.getName());
						de.setCreatedOn(new java.util.Date());
						de.setCreatedBy(Context.getAuthenticatedUser().getGivenName());
						SDMXHDDataExportService sDMXHDDataExportService =Context.getService(SDMXHDDataExportService.class);
					
						sDMXHDDataExportService.saveDataElement(de);
						
				
					
					}
					
					status.setComplete();
				}
				
				return "redirect:/module/sdmxhddataexport/listDataElement.form";
			}
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
