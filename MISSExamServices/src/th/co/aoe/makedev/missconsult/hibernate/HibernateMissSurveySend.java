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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSurveySend;
import th.co.aoe.makedev.missconsult.managers.MissSurveySendService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissSurveySend  extends HibernateCommon implements MissSurveySendService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissSurveySend findMissSurveySendById(Long megId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissSurveySend missSurveySend = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missSurveySend from MissSurveySend missSurveySend where missSurveySend.megId=:megId");
		query.setParameter("megId", megId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missSurveySend=(MissSurveySend)obj;
		}
	  return missSurveySend;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissSurveySend(MissSurveySend transientInstance)
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
	
	

	private int getSize(Session session, MissSurveySend instance) throws Exception{
		try {
			 
			/*Long megId = instance.getMegId();
			String megName = instance.getMegName();
			 
			 
			StringBuffer sb =new StringBuffer(" select count(missSurveySend) from MissSurveySend missSurveySend ");
			
			boolean iscriteria = false;
			if(megId !=null && megId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSurveySend.megId="+megId+""):(" where missSurveySend.megId="+megId+""));
				  iscriteria = true;
			}
			if(megName !=null && megName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missSurveySend.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missSurveySend.megName) like '%"+megName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			Query query =session.createQuery(sb.toString());
				 return ((Long)query.uniqueResult()).intValue(); */
			return 0;
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
	 public List searchMissSurveySend(MissSurveySend instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
			/*	Long megId = instance.getMegId();
				String megName = instance.getMegName();
			
			
				StringBuffer sb =new StringBuffer(" select missSurveySend from MissSurveySend missSurveySend ");
				
				boolean iscriteria = false;
				if(megId !=null && megId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSurveySend.megId="+megId+""):(" where missSurveySend.megId="+megId+""));
					  iscriteria = true;
				}
				if(megName !=null && megName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missSurveySend.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missSurveySend.megName) like '%"+megName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missSurveySend."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				Query query =session.createQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance)); 
				 logger.debug(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
				 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
				 query.setMaxResults(pagging.getPageSize());
				 
				 List l = query.list();   
				 transList.add(l); 
			 	 transList.add(size); */
				return transList;
			} catch (Exception re) {
				//re.printStackTrace();
				logger.error("find by property name failed", re);
				 
			}
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissSurveySend(MissSurveySend transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissSurveySend(MissSurveySend persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	@Override	
	public int sendSurvey(MissSurveySend persistentInstance,Long maId,List<List<String>> userEmail)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		System.out.println(persistentInstance.getMissSery().getMsId());
		for (List<String> list : userEmail) {
			System.out.println("name="+list.get(0));			
			System.out.println("email="+list.get(1));
			MissSurveySend missSurveySend =new MissSurveySend();
			missSurveySend.setMsEmail(list.get(1));
			missSurveySend.setMissSery(persistentInstance.getMissSery());
			session.save(missSurveySend);
		}
		// 1= success , 0 not success
		return 0;
	}
	 

}