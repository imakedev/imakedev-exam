package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountMapping;
import th.co.aoe.makedev.missconsult.managers.MissAccountMappingService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissAccountMapping extends HibernateCommon implements MissAccountMappingService {

//	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	 
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissAccountMapping(MissAccountMapping transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		try{
			/*
			transientInstance.setMaGrade("1");
			transientInstance.setMaCustomizeHeadColor("body.gif");
			transientInstance.setMaCustomizeColor("smoothness");
			transientInstance.setMaBackgroundColor("253,253,253");
			Object obj = session.save(transientInstance);
		
			if(obj!=null){
				returnId =(Long) obj;
				Query query=session.createQuery("update MissAccountMapping missAccountMapping " +
						" set missAccountMapping.maRegisterNo =:maRegisterNo " +
						" where missAccountMapping.maId ="+returnId);
				query.setParameter("maRegisterNo", "M000000"+returnId);
				query.executeUpdate();
			}
			*/
		} finally {
				if (session != null) {
					session = null;
				} 
		}
		return returnId; 
	}
	
	

	/*private int getSize(Session session, MissAccountMapping instance) throws Exception{
		try {
			 
			return 0;
		} catch (HibernateException re) {
			logger.error("HibernateException",re);
			throw re;
		} catch (Exception e) {
			logger.error("Exception",e);
			throw e;
		}
	}*/
	 @SuppressWarnings({ "rawtypes" })
	 @Transactional(readOnly=true)
	 public List searchMissAccountMapping(MissAccountMapping instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			 
			return transList;
		}
	 

}
