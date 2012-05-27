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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissExam;
import th.co.aoe.makedev.missconsult.managers.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissExam  extends HibernateCommon implements MissExamService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissExam findMissExamById(Long meId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissExam missExam = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missExam from MissExam missExam where missExam.meId=:meId");
		query.setParameter("meId", meId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missExam=(MissExam)obj;
		}
	  return missExam;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissExam(MissExam transientInstance)
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
	
	

	private int getSize(Session session, MissExam instance) throws Exception{
		try {
			 
			Long megId = (instance.getMissExamGroup()!=null)?instance.getMissExamGroup().getMegId():null;
			String meName = instance.getMeName();
			 
			StringBuffer sb =new StringBuffer(" select count(missExam) from MissExam missExam ");
			
			boolean iscriteria = false;
			if(megId !=null && megId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missExam.missExamGroup.megId="+megId+""):(" where missExam.missExamGroup.megId="+megId+""));
				  iscriteria = true;
			}
			if(meName !=null && meName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missExam.meName) like '%"+meName.trim().toLowerCase()+"%'"):(" where lcase(missExam.meName) like '%"+meName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
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
	 public List searchMissExam(MissExam instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				Long megId = (instance.getMissExamGroup()!=null)?instance.getMissExamGroup().getMegId():null;
				String meName = instance.getMeName();
			
			 //  logger.debug("megIDDD="+megId);
				StringBuffer sb =new StringBuffer(" select missExam from MissExam missExam ");
				
				boolean iscriteria = false;
				if(megId !=null && megId.intValue() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missExam.missExamGroup.megId="+megId+""):(" where missExam.missExamGroup.megId="+megId+""));
					  iscriteria = true;
				}
				if(meName !=null && meName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missExam.meName) like '%"+meName.trim().toLowerCase()+"%'"):(" where lcase(missExam.meName) like '%"+meName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missExam."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
	public int updateMissExam(MissExam transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=null;
		if(section.equals("0") ){
			query=session.createQuery("update MissExam missExam " +
					" set missExam.meName =:meName," +
					" missExam.meTimeLimit =:meTimeLimit ," +
					" missExam.missExamGroup.megId =:megId, " + 
					" missExam.meIntroduction =:meIntroduction, " +
					" missExam.meInstruction =:meInstruction " +
					" where missExam.meId ="+transientInstance.getMeId());
			query.setParameter("meName", transientInstance.getMeName());
			query.setParameter("meTimeLimit", transientInstance.getMeTimeLimit());
			query.setParameter("megId", transientInstance.getMissExamGroup().getMegId());
			query.setParameter("meIntroduction", transientInstance.getMeIntroduction());
			query.setParameter("meInstruction", transientInstance.getMeInstruction());
			return query.executeUpdate();
		}
			else if(section.equals("2") ){
				query=session.createQuery("update MissExam missExam " +
						" set missExam.meFixAnswerOrder =:meFixAnswerOrder" + 
						" where missExam.meId ="+transientInstance.getMeId());
				query.setParameter("meFixAnswerOrder", transientInstance.getMeFixAnswerOrder());
				return query.executeUpdate();
		//return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	return 0;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissExam(MissExam persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public List listMissExam() throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missExam from MissExam missExam ");
		return query.list(); 	 
	}
	 

}