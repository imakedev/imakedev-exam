package th.co.aoe.makedev.missconsult.hibernate;

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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo;
import th.co.aoe.makedev.missconsult.managers.MissTodoService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissTodo  extends HibernateCommon implements MissTodoService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissTodo findMissTodoById(Long megId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissTodo missTodo = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missTodo from MissTodo missTodo where missTodo.megId=:megId");
		query.setParameter("megId", megId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missTodo=(MissTodo)obj;
		}
	  return missTodo;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissTodo(MissTodo transientInstance)
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
	
	

	private int getSize(Session session, MissTodo instance) throws Exception{
		try {
			MissAccount account = instance.getMissAccount();
			Long maId= null;
			if(account!=null)
				maId=account.getMaId();
		//	String megName = instance.getMegName();
		
		
			StringBuffer sb =new StringBuffer(" select count(missTodo) from MissTodo missTodo ");
			
			boolean iscriteria = false;
			if(maId !=null && maId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missTodo.missAccount.maId="+maId+""):(" where missTodo.missAccount.maId="+maId+""));
				  iscriteria = true;
			}
		/*	if(megName !=null && megName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missTodo.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missTodo.megName) like '%"+megName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}*/
			Query query =session.createQuery(sb.toString());
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
	 public List searchMissTodo(MissTodo instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				MissAccount account = instance.getMissAccount();
				Long maId= null;
				if(account!=null)
					maId=account.getMaId();
			//	String megName = instance.getMegName();
			
			
				StringBuffer sb =new StringBuffer(" select missTodo from MissTodo missTodo ");
				
				boolean iscriteria = false;
				System.out.println(" maId="+maId+",pageSize="+pagging.getPageSize()+",pageNo="+pagging.getPageNo());
				if(maId !=null && maId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missTodo.missAccount.maId="+maId+""):(" where missTodo.missAccount.maId="+maId+""));
					  iscriteria = true;
				}
				/*if(megName !=null && megName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missTodo.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missTodo.megName) like '%"+megName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}*/
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missTodo."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				Query query =session.createQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance)); 
				 logger.info(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
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
	public int updateMissTodo(MissTodo transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissTodo(MissTodo persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 

}