package th.co.aoe.makedev.missconsult.hibernate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissDoc;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult;
import th.co.aoe.makedev.missconsult.managers.MissDocService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissDoc  extends HibernateCommon implements MissDocService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	//private static final SecureRandom random = new SecureRandom();
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissDoc findMissDocById(Long mdId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissDoc missDoc = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missDoc from MissDoc missDoc where missDoc.mdId=:mdId");
		query.setParameter("mdId", mdId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missDoc=(MissDoc)obj;
		}
	  return missDoc;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissDoc(MissDoc transientInstance)
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
	private int getSize(Session session, MissDoc instance) throws Exception{
		try {
			 
			StringBuffer sb =new StringBuffer(" select count(missDoc) from MissDoc missDoc ");
			Query query =null;
		
		//	boolean iscriteria = false;
			 query =session.createQuery(sb.toString());
				 return ((Long)query.uniqueResult()).intValue(); 
				 
		 
		} catch (HibernateException re) {
			logger.error("HibernateException",re);
			throw re;
		} catch (Exception e) {
			logger.error("Exception",e);
			throw e;
		}
	}
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 @Transactional(readOnly=true)
	 public List searchMissDoc(MissDoc instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				Query query =null;				  
				StringBuffer sb =new StringBuffer(" select missDoc from MissDoc missDoc ");
				//	boolean iscriteria = false;
					if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
							sb.append( " order by missDoc."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
					}			
					 query =session.createQuery(sb.toString());
					// set pagging.
					 String size = String.valueOf(getSize(session,instance)); 
					 logger.debug(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
					 
					 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
					 query.setMaxResults(pagging.getPageSize());
					 
					 List l = query.list();   
					 transList.add(l); 
				 	 transList.add(size);
				return transList;
			} catch (Exception re) {
				//re.printStackTrace();
				logger.error("find by property name failed", re);
				 
			}
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissDoc(MissDoc transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
		//MissDoc missDoc = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
	
			Long returnId  = 0l;
			try{
				session.update(transientInstance);
				returnId=1l;
			} finally {
					if (session != null) {
						session = null;
					} 
			}
			return returnId.intValue(); 
		}
	
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissDoc(MissDoc persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
	
		MissDoc missDoc = null;
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query = session
				.createQuery(" select missDoc from MissDoc missDoc where missDoc.mdId=:mdId");
		query.setParameter("mdId", persistentInstance.getMdId());
		Object obj = query.uniqueResult();
		int returnRecord=0;
		if (obj != null) {
			missDoc = (MissDoc) obj;
			 File file_delete=new File("/opt/attach/doc/"+missDoc.getMdDocPath().trim());
			 if(file_delete.exists())
				 file_delete.delete(); 
			  query = session
						.createQuery(" delete MissDoc missDoc   "
								+ " where missDoc.mdId=:mdId " +
								" ");
			  query.setParameter("mdId", persistentInstance.getMdId());
			  returnRecord=query.executeUpdate();
		}
		return returnRecord;
	}
	 

}
