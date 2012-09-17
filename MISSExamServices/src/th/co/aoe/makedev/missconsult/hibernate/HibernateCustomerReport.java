package th.co.aoe.makedev.missconsult.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.CustomerReportService;
import th.co.aoe.makedev.missconsult.xstream.CustomerReport;

@Repository
@Transactional
public class HibernateCustomerReport  extends HibernateCommon implements CustomerReportService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Override
	public CustomerReport findCustomerReport(String mode, String magId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CustomerReport findGroups(String query) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
