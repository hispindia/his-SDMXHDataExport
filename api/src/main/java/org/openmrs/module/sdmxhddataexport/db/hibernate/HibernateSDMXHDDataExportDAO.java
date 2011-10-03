package org.openmrs.module.sdmxhddataexport.db.hibernate;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.sdmxhddataexport.db.SDMXHDDataExportDAO;
import org.openmrs.module.sdmxhddataexport.model.DataElement;
import org.openmrs.module.sdmxhddataexport.model.Query;
import org.openmrs.module.sdmxhddataexport.model.Report;
import org.openmrs.module.sdmxhddataexport.model.ReportDataElement;
import org.openmrs.module.sdmxhddataexport.util.DateUtils;

public class HibernateSDMXHDDataExportDAO  implements SDMXHDDataExportDAO{

	
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * Hibernate session factory
	 */
	private SessionFactory sessionFactory;
	
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//DataElement
	
	@Override
	public List<DataElement> listDataElement(String name, int min, int max)
			throws DAOException {
		// TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DataElement.class,"dataElement");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.like("dataElement.name", "%"+name+"%"));
		}
		if(max > 0){
			criteria.setFirstResult(min).setMaxResults(max);
		}
		List<DataElement> l = criteria.list();
		
		return l;
	}

	@Override
	public int countListDataElement(String name) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DataElement.class,"dataElement");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.like("dataElement.name", "%"+name+"%"));
		}
		Number rs =  (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}

	@Override
	public DataElement saveDataElement(DataElement dataElement)
			throws DAOException {
		
		return (DataElement)sessionFactory.getCurrentSession().merge(dataElement);
	}

	@Override
	public DataElement getDataElementById(Integer id) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DataElement.class);
		criteria.add(Restrictions.eq("id", id));
		DataElement dataElement = (DataElement) criteria.uniqueResult();
		return dataElement;
	}

	@Override
	public DataElement getDataElementByName(String name) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DataElement.class);
		criteria.add(Restrictions.eq("name", name));
		DataElement dataElement = (DataElement) criteria.uniqueResult();
		return dataElement;
	}

	@Override
	public void deleteDataElement(DataElement dataElement) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(dataElement);
	}

	//Query
	
	@Override
	public List<Query> listQuery(String name, int min, int max)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Query.class,"query");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.like("query.name", "%"+name+"%"));
		}
		if(max > 0){
			criteria.setFirstResult(min).setMaxResults(max);
		}
		List<Query> l = criteria.list();
		return l;
	}

	@Override
	public int countListQuery(String name) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Query.class,"query");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.like("query.name", "%"+name+"%"));
		}
		Number rs =  (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}

	@Override
	public Query saveQuery(Query query) throws DAOException {
		// TODO Auto-generated method stub
		return (Query)sessionFactory.getCurrentSession().merge(query);
	}

	@Override
	public Query getQueryById(Integer id) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Query.class);
		criteria.add(Restrictions.eq("id", id));
		Query query = (Query) criteria.uniqueResult();
		return query;
	}

	@Override
	public Query getQueryByName(String name) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Query.class);
		criteria.add(Restrictions.eq("name", name));
		Query query = (Query) criteria.uniqueResult();
		return query;
	}

	@Override
	public void deleteQuery(Query query) throws DAOException {
		
		 sessionFactory.getCurrentSession().delete(query);
	}

	
	//Report
	
	@Override
	public List<Report> listReport(String name, int min, int max)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Report.class,"report");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.like("report.name", "%"+name+"%"));
		}
		if(max > 0){
			criteria.setFirstResult(min).setMaxResults(max);
		}
		List<Report> l = criteria.list();
		return l;
	}

	@Override
	public int countListReport(String name) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Report.class,"report");
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.like("report.name", "%"+name+"%"));
		}
		Number rs =  (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}

	@Override
	public Report saveReport(Report report) throws DAOException {
		// TODO Auto-generated method stub
		return (Report) sessionFactory.getCurrentSession().merge(report);
	}

	@Override
	public Report getReportById(Integer id) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Report.class);
		criteria.add(Restrictions.eq("id", id));
		Report report = (Report) criteria.uniqueResult();
		return report;
	}

	@Override
	public Report getReportByName(String name) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Report.class);
		criteria.add(Restrictions.eq("name", name));
		Report report = (Report) criteria.uniqueResult();
		return report;
	}

	@Override
	public void deleteReport(Report report) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(report);
	}
	
	//ReportDataElement

	@Override
	public List<ReportDataElement> listReportDataElement(Integer reportId,
			Integer dataElementId, Integer queryId, int min, int max)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ReportDataElement.class,"reportDataElement");
		if(reportId != null && reportId > 0){
			criteria.add(Restrictions.like("reportDataElement.report.id", reportId));
		}
		if(dataElementId != null && dataElementId > 0){
			criteria.add(Restrictions.like("reportDataElement.dataElement.id", dataElementId));
		}
		if(queryId != null && queryId > 0){
			criteria.add(Restrictions.like("reportDataElement.query.id", queryId));
		}
		if(max > 0){
			criteria.setFirstResult(min).setMaxResults(max);
		}
		
		List<ReportDataElement> l = criteria.list();
		return l;
	}

	@Override
	public int countListReportDataElement(Integer reportId,
			Integer dataElementId, Integer queryId) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ReportDataElement.class,"reportDataElement");
		if(reportId != null && reportId > 0){
			criteria.add(Restrictions.like("reportDataElement.report.id", reportId));
		}
		if(dataElementId != null && dataElementId > 0){
			criteria.add(Restrictions.like("reportDataElement.dataElement.id", dataElementId));
		}
		if(queryId != null && queryId > 0){
			criteria.add(Restrictions.like("reportDataElement.query.id", queryId));
		}
		Number rs =  (Number) criteria.setProjection( Projections.rowCount() ).uniqueResult();
		return rs != null ? rs.intValue() : 0;
	}

	@Override
	public ReportDataElement saveReportDataElement(
			ReportDataElement reportDataElement) throws DAOException {
		// TODO Auto-generated method stub
		return (ReportDataElement)sessionFactory.getCurrentSession().merge(reportDataElement);
	}

	@Override
	public ReportDataElement getReportDataElementById(Integer id)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ReportDataElement.class);
		criteria.add(Restrictions.eq("id", id));
		ReportDataElement reportDataElement = (ReportDataElement) criteria.uniqueResult();
		return reportDataElement;
	}

	

	@Override
	public void deleteReportDataElement(ReportDataElement reportDataElement)
			throws DAOException {
		sessionFactory.getCurrentSession().delete(reportDataElement);
	}
	
	public Integer executeQuery(String query,String startDate, String endDate) throws DAOException{
		query = query.toLowerCase();
		if(!query.startsWith("select")){
			return 0;
		}
		BigInteger  result = new BigInteger("0");
		String start = DateUtils.getDateFromRange(startDate) + " 00:00:00";
		String end = DateUtils.getDateFromRange(endDate) + " 23:59:59";
		
		 
		 if(query.indexOf("?") != -1){
			 query = query.replaceFirst("\\?", " '"+start+"' ");
			 query = query.replaceFirst("\\?", " '"+end+"' ");
		 }
		 org.hibernate.Query q = sessionFactory.getCurrentSession().createSQLQuery(query);
		 Object l = q.uniqueResult();
		 if(l != null){
			 result = (BigInteger ) l;
		 }
		 
		 return result != null ? result.intValue() : 0;
	}
	
	
	@Override
	public ReportDataElement getReportDataElement(Integer reportId, Integer dataElementId)
			throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ReportDataElement.class);
		criteria.add(Restrictions.eq("report.id", reportId))
		.add(Restrictions.eq("dataElement.id", dataElementId));
		ReportDataElement reportDataElement = (ReportDataElement) criteria.uniqueResult();
		return reportDataElement  ;
	}

	public static void main(String[] args) {
		System.out.println("abca".replaceFirst("a", "x").replaceFirst("a", "y"));
	}
	
}
