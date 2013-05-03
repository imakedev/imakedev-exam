package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach;
import th.co.aoe.makedev.missconsult.managers.MissAttachService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissAttach  extends HibernateCommon implements MissAttachService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
//	private static final SecureRandom random = new SecureRandom();
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissAttach findMissAttachById(String matModule,Long matRef,String hotlink)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissAttach missAttach = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		
		Query query=session.createQuery(" select missAttach from MissAttach missAttach " +
				" where missAttach.matModule=:matModule " +
				" and missAttach.matRef=:matRef "+
				" and missAttach.matHotlink=:matHotlink ");
		query.setParameter("matModule", matModule);
		query.setParameter("matRef", matRef);
		query.setParameter("matHotlink", hotlink);
		@SuppressWarnings("rawtypes")
		List list=query.list();
		if(list.size()>0){
			missAttach=(MissAttach)list.get(0);
		}
		/*Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missAttach=(MissAttach)obj;
		}*/
	  return missAttach;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissAttach(MissAttach transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		//73gqqnghrkvfq202q6696gc35o
		//String big=new String(130, random).toString(32);
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
	
	

	/*private int getSize(Session session, MissAttach instance) throws Exception{
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
	 public List searchMissAttach(MissAttach instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			 
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissAttach(MissAttach transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissAttach missAttach = null;
			Session session=sessionAnnotationFactory.getCurrentSession();
			
			Query query=session.createQuery(" select missAttach from MissAttach missAttach " +
					" where missAttach.matModule=:matModule " +
					" and missAttach.matRef=:matRef " +
					" and missAttach.matHotlink=:matHotlink ");
			query.setParameter("matModule", transientInstance.getMatModule());
			query.setParameter("matRef", transientInstance.getMatRef());
			query.setParameter("matHotlink", transientInstance.getMatHotlink());
			@SuppressWarnings("rawtypes")
			List list=query.list();
			logger.debug(" attach size="+list.size());
			if(list.size()>0){
				missAttach=(MissAttach)list.get(0);
				 missAttach.setMatFileName(transientInstance.getMatFileName());
				 missAttach.setMatHotlink(transientInstance.getMatHotlink());
				 missAttach.setMatPath(transientInstance.getMatPath());
				/* missAttach.setMatRef(Long.parseLong(id));
				 missAttach.setMatModule(module);*/
			//	BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);					
				return update(session, missAttach);
			}else{
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
				return returnId.intValue(); 
			}
		
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissAttach(MissAttach persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 

}
