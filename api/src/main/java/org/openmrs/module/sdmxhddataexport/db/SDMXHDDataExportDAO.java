/**
 *  Copyright 2011 Health Information Systems Project of India
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

package org.openmrs.module.sdmxhddataexport.db;

import java.util.List;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.sdmxhddataexport.model.DataElement;
import org.openmrs.module.sdmxhddataexport.model.Query;
import org.openmrs.module.sdmxhddataexport.model.Report;
import org.openmrs.module.sdmxhddataexport.model.ReportDataElement;

public interface SDMXHDDataExportDAO {

	
	/**
	 * DataElement
	 */
	 
	
	public List<DataElement> listDataElement( String name ,int min, int max) throws DAOException;
	
	public int countListDataElement(String name)  throws DAOException;
	
	public DataElement saveDataElement(DataElement dataElement) throws DAOException;

	public DataElement getDataElementById(Integer id) throws DAOException;
	
	public DataElement getDataElementByName(String name) throws DAOException;
	
	public void deleteDataElement(DataElement dataElement) throws DAOException;
	
	
	/**
	 * Query
	 */
	 
	
	public List<Query> listQuery( String name ,int min, int max) throws DAOException;
	
	public int countListQuery(String name)  throws DAOException;
	
	public Query saveQuery(Query query) throws DAOException;

	public Query getQueryById(Integer id) throws DAOException;
	
	public Query getQueryByName(String name) throws DAOException;
	
	public void deleteQuery(Query query) throws DAOException;
	
	
	/**
	 * Report
	 */
	 
	
	public List<Report> listReport( String name ,int min, int max) throws DAOException;
	
	public int countListReport(String name)  throws DAOException;
	
	public Report saveReport(Report report) throws DAOException;

	public Report getReportById(Integer id) throws DAOException;
	
	public Report getReportByName(String name) throws DAOException;
	
	public void deleteReport(Report report) throws DAOException;
	
	
	/**
	 * ReportDataElement
	 */
	 
	
	public List<ReportDataElement> listReportDataElement( Integer reportId,Integer dataElementId,Integer queryId ,int min, int max) throws DAOException;
	
	public int countListReportDataElement( Integer reportId,Integer dataElementId,Integer queryId)  throws DAOException;
	
	public ReportDataElement saveReportDataElement(ReportDataElement reportDataElement) throws DAOException;

	public ReportDataElement getReportDataElementById(Integer id) throws DAOException;
	
	public void deleteReportDataElement(ReportDataElement reportDataElement) throws DAOException;
	
	public ReportDataElement getReportDataElement(Integer reportId,Integer dataElementId) throws DAOException;
	
	public Integer executeQuery(String query,String startDate, String endDate) throws DAOException;
	
}
