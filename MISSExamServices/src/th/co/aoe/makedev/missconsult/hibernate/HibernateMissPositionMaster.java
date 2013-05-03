package th.co.aoe.makedev.missconsult.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.managers.MissPositionMasterService;

@Repository
@Transactional
public class HibernateMissPositionMaster extends HibernateCommon implements MissPositionMasterService {

	//private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
//	private static final SecureRandom random = new SecureRandom();
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly=true)
	@Override
	public List listMissPositionMaster() throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missPositionMaster from MissPositionMaster missPositionMaster ");
		return query.list();
	}

}
