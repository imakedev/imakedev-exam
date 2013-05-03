package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate;
import th.co.aoe.makedev.missconsult.managers.MissTemplateService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissTemplate  extends HibernateCommon implements MissTemplateService {

	//private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissTemplate findMissTemplateById(Long megId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissTemplate missTemplate = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missTemplate from MissTemplate missTemplate where missTemplate.megId=:megId");
		query.setParameter("megId", megId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missTemplate=(MissTemplate)obj;
		}
	  return missTemplate;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissTemplate(MissTemplate transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		try{
			Object obj = session.save(transientInstance);
		
			if(obj!=null){
				returnId =(Long) obj;
			}
		} finally {
				if (session != null) {
					session = null;
				} 
		}
		return returnId; 
	}
	
	

	/*private int getSize(Session session, MissTemplate instance) throws Exception{
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
	 public List searchMissTemplate(MissTemplate instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			 
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissTemplate(MissTemplate transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissTemplate(MissTemplate persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 

}