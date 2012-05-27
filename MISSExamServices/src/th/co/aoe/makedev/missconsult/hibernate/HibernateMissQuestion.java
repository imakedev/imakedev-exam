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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion;
import th.co.aoe.makedev.missconsult.managers.MissQuestionService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissQuestion  extends HibernateCommon implements MissQuestionService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissQuestion findMissQuestionById(Long mqId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissQuestion missQuestion = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where missQuestion.mqId=:mqId");
		query.setParameter("mqId", mqId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missQuestion=(MissQuestion)obj;
		}
	  return missQuestion;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissQuestion(MissQuestion transientInstance)
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
	
	

	private int getSize(Session session, MissQuestion instance) throws Exception{
		try {
			 
		/*	Long megId = instance.getMegId();
			String megName = instance.getMegName();
			 
			StringBuffer sb =new StringBuffer(" select count(missQuestion) from MissQuestion missQuestion ");
			
			boolean iscriteria = false;
			if(megId !=null && megId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missQuestion.megId="+megId+""):(" where missQuestion.megId="+megId+""));
				  iscriteria = true;
			}
			if(megName !=null && megName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missQuestion.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missQuestion.megName) like '%"+megName.trim().toLowerCase()+"%'"));
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
	 public List searchMissQuestion(MissQuestion instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				/*Long megId = instance.getMegId();
				String megName = instance.getMegName();
			
			
				StringBuffer sb =new StringBuffer(" select missQuestion from MissQuestion missQuestion ");
				
				boolean iscriteria = false;
				if(megId !=null && megId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missQuestion.megId="+megId+""):(" where missQuestion.megId="+megId+""));
					  iscriteria = true;
				}
				if(megName !=null && megName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missQuestion.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missQuestion.megName) like '%"+megName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missQuestion."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				Query query =session.createQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance)); 
				 logger.info(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
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
	public int updateMissQuestion(MissQuestion transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissQuestion(MissQuestion persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public List listMissQuestions(Long meId) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missQuestion from MissQuestion missQuestion where  missQuestion.missExam.meId="+meId.intValue());
		return query.list(); 	
	}
	

}