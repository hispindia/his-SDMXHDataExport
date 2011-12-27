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

package org.openmrs.module.sdmxhddataexport.impl;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.db.DAOException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.openmrs.module.sdmxhddataexport.db.SDMXHDDataExportDAO;
import org.openmrs.module.sdmxhddataexport.model.DataElement;
import org.openmrs.module.sdmxhddataexport.model.Query;
import org.openmrs.module.sdmxhddataexport.model.Report;
import org.openmrs.module.sdmxhddataexport.model.ReportDataElement;

public class SDMXHDDataExportServiceImpl  extends BaseOpenmrsService implements SDMXHDDataExportService {

	 protected SDMXHDDataExportDAO dao;
	 
	 
	 
	public void setDao(SDMXHDDataExportDAO dao) {
		this.dao = dao;
	}

	//DataElement
	
	@Override
	public List<DataElement> listDataElement(String name, int min, int max)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.listDataElement(name, min, max);
	}

	@Override
	public int countListDataElement(String name) throws APIException {
		// TODO Auto-generated method stub
		return dao.countListDataElement(name);
	}

	@Override
	public DataElement saveDataElement(DataElement dataElement)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.saveDataElement(dataElement);
	}

	@Override
	public DataElement getDataElementById(Integer id) throws APIException {
		// TODO Auto-generated method stub
		return dao.getDataElementById(id);
	}

	@Override
	public DataElement getDataElementByName(String name) throws APIException {
		// TODO Auto-generated method stub
		return dao.getDataElementByName(name);
	}

	@Override
	public void deleteDataElement(DataElement dataElement) throws APIException {
		// TODO Auto-generated method stub
		dao.deleteDataElement(dataElement);
	}

	//Query
	
	@Override
	public List<Query> listQuery(String name, int min, int max)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.listQuery(name, min, max);
	}

	@Override
	public int countListQuery(String name) throws APIException {
		// TODO Auto-generated method stub
		return dao.countListQuery(name);
	}

	@Override
	public Query saveQuery(Query query) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveQuery(query);
	}

	@Override
	public Query getQueryById(Integer id) throws APIException {
		// TODO Auto-generated method stub
		return dao.getQueryById(id);
	}

	@Override
	public Query getQueryByName(String name) throws APIException {
		// TODO Auto-generated method stub
		return dao.getQueryByName(name);
	}

	@Override
	public void deleteQuery(Query query) throws APIException {
		// TODO Auto-generated method stub
		dao.deleteQuery(query);
	}
	
	//Report

	@Override
	public List<Report> listReport(String name, int min, int max)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.listReport(name, min, max);
	}

	@Override
	public int countListReport(String name) throws APIException {
		// TODO Auto-generated method stub
		return dao.countListReport(name);
	}

	@Override
	public Report saveReport(Report report) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveReport(report);
	}

	@Override
	public Report getReportById(Integer id) throws APIException {
		// TODO Auto-generated method stub
		return dao.getReportById(id);
	}

	@Override
	public Report getReportByName(String name) throws APIException {
		// TODO Auto-generated method stub
		return dao.getReportByName(name);
	}

	@Override
	public void deleteReport(Report report) throws APIException {
		// TODO Auto-generated method stub
		dao.deleteReport(report);
	}

	//ReportDataElement
	
	@Override
	public List<ReportDataElement> listReportDataElement(Integer reportId,
			Integer dataElementId, Integer queryId, int min, int max)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.listReportDataElement(reportId, dataElementId, queryId, min, max);
	}

	@Override
	public int countListReportDataElement(Integer reportId,
			Integer dataElementId, Integer queryId) throws APIException {
		// TODO Auto-generated method stub
		return dao.countListReportDataElement(reportId, dataElementId, queryId);
	}

	@Override
	public ReportDataElement saveReportDataElement(
			ReportDataElement reportDataElement) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveReportDataElement(reportDataElement);
	}

	@Override
	public ReportDataElement getReportDataElementById(Integer id)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getReportDataElementById(id);
	}


	@Override
	public void deleteReportDataElement(ReportDataElement reportDataElement)
			throws APIException {
		// TODO Auto-generated method stub
		dao.deleteReportDataElement(reportDataElement);
	}

	
	public Integer executeQuery(String query, String startDate, String endDate) throws DAOException{
		return dao.executeQuery(query, startDate, endDate);
	}

	@Override
	public ReportDataElement getReportDataElement(Integer reportId, Integer dataElementId)
			throws DAOException {
		// TODO Auto-generated method stub
		return dao.getReportDataElement(reportId, dataElementId);
	}
	
	
}
