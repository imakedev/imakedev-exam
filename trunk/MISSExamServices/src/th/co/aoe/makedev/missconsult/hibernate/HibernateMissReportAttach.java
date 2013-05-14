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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttachPK;
import th.co.aoe.makedev.missconsult.managers.MissReportAttachService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissReportAttach extends HibernateCommon implements MissReportAttachService {

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
	public MissReportAttach findMissReportAttachById(Long msId,Long msOrder,String mraLang,String hotlink)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissReportAttach missReportAttach = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		
		Query query=session.createQuery(" select missReportAttach from MissReportAttach missReportAttach " +
				" where missReportAttach.msId=:msId " +
				" and missReportAttach.msOrder=:msOrder "+
				" and missReportAttach.mraLang=:mraLang "+
				" and missReportAttach.matHotlink=:matHotlink ");
		query.setParameter("msId", msId);
		query.setParameter("msOrder", msOrder);
		query.setParameter("mraLang", mraLang);
		query.setParameter("matHotlink", hotlink);
		@SuppressWarnings("rawtypes")
		List list=query.list();
		if(list.size()>0){
			missReportAttach=(MissReportAttach)list.get(0);
		}
		/*Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missReportAttach=(MissReportAttach)obj;
		}*/
	  return missReportAttach;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissReportAttach(MissReportAttach transientInstance)
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
	
	

	/*private int getSize(Session session, MissReportAttach instance) throws Exception{
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
	 public List searchMissReportAttach(MissReportAttach instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			 
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissReportAttach(MissReportAttach transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissReportAttach missReportAttach = null;
			Session session=sessionAnnotationFactory.getCurrentSession();
			 
			Query query=session.createQuery(" select missReportAttach from MissReportAttach missReportAttach " +
					" where missReportAttach.id.msId=:msId " +
					" and missReportAttach.id.msOrder=:msOrder " +
					" and missReportAttach.id.mraLang=:mraLang " +
				//	" and missReportAttach.mraHotlink=:mraHotlink " +
					" ");
		 	query.setParameter("msId", transientInstance.getId().getMsId());
			query.setParameter("msOrder", transientInstance.getId().getMsOrder());
			query.setParameter("mraLang", transientInstance.getId().getMraLang());
			//query.setParameter("mraHotlink", transientInstance.getMraHotlink()); 
			@SuppressWarnings("rawtypes")
			List list=query.list();
		//	System.out.println(" mraHotlink="+ transientInstance.getMraHotlink());
			//System.out.println(" attach size="+list.size());
			if(list.size()>0){
			 	missReportAttach=(MissReportAttach)list.get(0);
				 missReportAttach.setMraFileName(transientInstance.getMraFileName());
				 missReportAttach.setMraHotlink(transientInstance.getMraHotlink());
				 missReportAttach.setMraPath(transientInstance.getMraPath()); 
				 missReportAttach.setMraReportName(transientInstance.getMraReportName()); 
				/* missReportAttach.setMatRef(Long.parseLong(id));
				 missReportAttach.setMatModule(module);*/
			//	BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);					
				return update(session, missReportAttach);
			}else{
				Long returnId  = null;
				MissReportAttachPK pk=null;
				try{
					Object obj = session.save(transientInstance);
				
					if(obj!=null){
						pk=(MissReportAttachPK)obj;
						returnId =pk.getMsOrder();
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
	public int deleteMissReportAttach(MissReportAttach persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 

}
