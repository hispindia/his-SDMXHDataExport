package org.openmrs.module.sdmxhddataexport;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.sdmxhddataexport.model.DataElement;
import org.openmrs.module.sdmxhddataexport.model.Query;
import org.openmrs.module.sdmxhddataexport.model.Report;
import org.openmrs.module.sdmxhddataexport.model.ReportDataElement;
import org.springframework.transaction.annotation.Transactional;


public interface SDMXHDDataExportService extends OpenmrsService {
	/**
	 * DataElement
	 */
	 
	
	public List<DataElement> listDataElement( String name ,int min, int max) throws APIException;
	
	public int countListDataElement(String name)  throws APIException;
	
	@Transactional(readOnly=false)
	public DataElement saveDataElement(DataElement dataElement) throws APIException;

	
	public DataElement getDataElementById(Integer id) throws APIException;
	
	public DataElement getDataElementByName(String name) throws APIException;
	
	@Transactional(readOnly=false)
	public void deleteDataElement(DataElement dataElement) throws APIException;
	
	
	/**
	 * Query
	 */
	 
	
	public List<Query> listQuery( String name ,int min, int max) throws APIException;
	
	public int countListQuery(String name)  throws APIException;
	
	@Transactional(readOnly=false)
	public Query saveQuery(Query query) throws APIException;

	
	public Query getQueryById(Integer id) throws APIException;
	
	public Query getQueryByName(String name) throws APIException;
	
	@Transactional(readOnly=false)
	public void deleteQuery(Query query) throws APIException;
	
	
	/**
	 * Report
	 */
	 
	
	public List<Report> listReport( String name ,int min, int max) throws APIException;
	
	public int countListReport(String name)  throws APIException;
	
	@Transactional(readOnly=false)
	public Report saveReport(Report report) throws APIException;

	public Report getReportById(Integer id) throws APIException;
	
	public Report getReportByName(String name) throws APIException;
	
	@Transactional(readOnly=false)
	public void deleteReport(Report report) throws APIException;
	
	
	/**
	 * ReportDataElement
	 */
	 
	
	public List<ReportDataElement> listReportDataElement( Integer reportId,Integer dataElementId,Integer queryId ,int min, int max) throws APIException;
	
	public int countListReportDataElement( Integer reportId,Integer dataElementId,Integer queryId)  throws APIException;
	
	@Transactional(readOnly=false)
	public ReportDataElement saveReportDataElement(ReportDataElement reportDataElement) throws APIException;

	public ReportDataElement getReportDataElementById(Integer id) throws APIException;
	
	@Transactional(readOnly=false)
	public void deleteReportDataElement(ReportDataElement reportDataElement) throws APIException;
	
	public Integer executeQuery(String query,String startDate, String endDate) throws DAOException;
	
	public ReportDataElement getReportDataElement(Integer reportId, Integer dataElementId)
			throws DAOException;

}
