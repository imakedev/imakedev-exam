package th.co.aoe.makedev.missconsult.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.ConsultantReportService;
import th.co.aoe.makedev.missconsult.xstream.ConsultantReport;

@Repository
@Transactional
public class HibernateConsultantReport  extends HibernateCommon implements ConsultantReportService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Override
	public ConsultantReport findConsultantReport(String mode, String mcontactId,String month,String year)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ConsultantReport findSales(String query) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
