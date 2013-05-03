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

import th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissChoicePK;
import th.co.aoe.makedev.missconsult.managers.MissChoiceService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissChoice  extends HibernateCommon implements MissChoiceService {

	//private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissChoice findMissChoiceById(Long megId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissChoice missChoice = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missChoice from MissChoice missChoice where missChoice.megId=:megId");
		query.setParameter("megId", megId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missChoice=(MissChoice)obj;
		}
	  return missChoice;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissChoice(MissChoice transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		try{
			Object obj = session.save(transientInstance);
		
			if(obj!=null){
				MissChoicePK pk = (MissChoicePK)obj;
				returnId =pk.getMcNo();//(Long) obj;
			}
		} finally {
				if (session != null) {
					session = null;
				} 
		}
		return returnId; 
	}
	
	

	/*private int getSize(Session session, MissChoice instance) throws Exception{
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
	 public List searchMissChoice(MissChoice instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			 
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissChoice(MissChoice transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissChoice(Long mqId,String lang)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		
		Query query=session.createQuery("delete MissChoice  missChoice where missChoice.id.mqId=:mqId and " +
				"missChoice.id.mcLang=:mcLang  "); 
	//	query.setParameter("mcNo", persistentInstance.getId().getMcNo());
		query.setParameter("mqId",mqId);
		query.setParameter("mcLang",lang);
		return query.executeUpdate();
		//return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 

}