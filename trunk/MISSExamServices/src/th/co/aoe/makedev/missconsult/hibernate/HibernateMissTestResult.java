package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.Date;
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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissTestResult;
import th.co.aoe.makedev.missconsult.managers.MissTestResultService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissTestResult  extends HibernateCommon implements MissTestResultService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissTestResult findMissTestResultById(Long mtrId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissTestResult missTestResult = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.mtrId=:mtrId");
		query.setParameter("mtrId", mtrId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missTestResult=(MissTestResult)obj;
		}
	  return missTestResult;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissTestResult(MissTestResult transientInstance)
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
	
	

	private int getSize(Session session, MissTestResult instance) throws Exception{
		try {
			/* 
			Long megId = instance.getMegId();
			String megName = instance.getMegName();*/
			 
			StringBuffer sb =new StringBuffer(" select count(missTestResult) from MissTestResult missTestResult ");
			
			/*boolean iscriteria = false;
			if(megId !=null && megId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missTestResult.megId="+megId+""):(" where missTestResult.megId="+megId+""));
				  iscriteria = true;
			}
			if(megName !=null && megName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missTestResult.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missTestResult.megName) like '%"+megName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}*/
			Query query =session.createQuery(sb.toString());
				 return ((Long)query.uniqueResult()).intValue();
			//return 0;
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
	 public List searchMissTestResult(MissTestResult instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				/*Long megId = instance.getMegId();
				String megName = instance.getMegName();
			*/
			
				StringBuffer sb =new StringBuffer(" select missTestResult from MissTestResult missTestResult ");
				
				/*boolean iscriteria = false;
				if(megId !=null && megId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missTestResult.megId="+megId+""):(" where missTestResult.megId="+megId+""));
					  iscriteria = true;
				}
				if(megName !=null && megName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missTestResult.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missTestResult.megName) like '%"+megName.trim().toLowerCase()+"%'"));
					 iscriteria = true;
				}*/ 
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missTestResult."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
	public int updateMissTestResult(MissTestResult transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissTestResult(MissTestResult persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public int processMissTestResult() throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Long saveOrUpdateMissTestResult(String userid,
			MissTestResult missTestResult) throws DataAccessException {
		// TODO Auto-generated method stub

		MissCandidate missCandidate = null;
		Long returnId  = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){		
			missCandidate=(MissCandidate)obj;
			logger.debug("xxxxxxxxxx="+missCandidate.getMcaId().intValue());
			missTestResult.setMissCandidate(missCandidate);
			query=session.createQuery(" select missTestResult from MissTestResult missTestResult where missTestResult.missCandidate.mcaId=:mcaId and " +
					" missTestResult.meId=:meId and "+
					" missTestResult.msId=:msId  ");
			query.setParameter("mcaId", missCandidate.getMcaId());
			query.setParameter("meId", missTestResult.getMeId());
			query.setParameter("msId", missTestResult.getMsId());
			
			/*
			java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());*/
			List list=query.list();
			if(list!=null && list.size()>0){//update 
			
				logger.debug("size="+list.size());
				logger.debug("MCA_ID="+missTestResult.getMissCandidate().getMcaId());
				logger.debug("ME_ID="+missTestResult.getMeId());
				logger.debug("MS_ID="+missTestResult.getMsId());
				//session.update(missTestResultUpdate);
				query=session.createQuery("update MissTestResult missTestResult " +
						" set missTestResult.mtrResultCode =:mtrResultCode ," +
						" missTestResult.mtrTestDate =:mtrTestDate " +
						" where missTestResult.missCandidate.mcaId=:mcaId and " +
						" missTestResult.meId=:meId and " +
						" missTestResult.msId=:msId ");
				
				query.setParameter("mcaId", missCandidate.getMcaId()); 
				query.setParameter("meId", missTestResult.getMeId());
				query.setParameter("msId", missTestResult.getMsId());  
				query.setParameter("mtrResultCode", missTestResult.getMtrResultCode()); 
				query.setParameter("mtrTestDate", new Date());
				returnId = Long.parseLong((query.executeUpdate())+"");
			}else{ //save
				try{
					obj = session.save(missTestResult);
				
					if(obj!=null){
						//returnId =(th.co.aoe.makedev.missconsult.hibernate.bean.MissTestPK) obj;
						returnId=1l;
					}
				} finally {
						if (session != null) {
							session = null;
						} 
				}
			}
    
		}
		// TODO Auto-generated method stub
		return returnId;
	
	}
	 

}