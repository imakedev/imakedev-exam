package th.co.aoe.makedev.missconsult.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.EPTNormReportService;
import th.co.aoe.makedev.missconsult.xstream.EPTNormReport;

@Repository
@Transactional
public class HibernateEPTNormReport  extends HibernateCommon implements EPTNormReportService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Override
	public EPTNormReport findEPTNormReport(String mode, String maId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public EPTNormReport findCompanies(String query) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
