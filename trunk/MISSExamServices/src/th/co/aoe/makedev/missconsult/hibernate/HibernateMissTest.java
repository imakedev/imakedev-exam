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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissTest;
import th.co.aoe.makedev.missconsult.managers.MissTestService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissTest  extends HibernateCommon implements MissTestService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissTest findMissTestById(Long mtestId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissTest missTest = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missTest from MissTest missTest where missTest.mtestId=:mtestId");
		query.setParameter("mtestId", mtestId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missTest=(MissTest)obj;
		}
	  return missTest;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissTest(MissTest transientInstance)
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
	
	

	private int getSize(Session session, MissTest instance) throws Exception{
		try {
			 
		/*	Long mtestId = instance.getMegId();
			String megName = instance.getMegName();
			 
			 
			StringBuffer sb =new StringBuffer(" select count(missTest) from MissTest missTest ");
			
			boolean iscriteria = false;
			if(mtestId !=null && mtestId > 0){  
				//criteria.add(Expression.eq("mtestId", mtestId));	
				 sb.append(iscriteria?(" and missTest.mtestId="+mtestId+""):(" where missTest.mtestId="+mtestId+""));
				  iscriteria = true;
			}
			if(megName !=null && megName.trim().length() > 0){  
				//criteria.add(Expression.eq("mtestId", mtestId));	
				sb.append(iscriteria?(" and lcase(missTest.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missTest.megName) like '%"+megName.trim().toLowerCase()+"%'"));
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
	 public List searchMissTest(MissTest instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
			/*	Long mtestId = instance.getMegId();
				String megName = instance.getMegName();
			
			
				StringBuffer sb =new StringBuffer(" select missTest from MissTest missTest ");
				
				boolean iscriteria = false;
				if(mtestId !=null && mtestId > 0){  
					//criteria.add(Expression.eq("mtestId", mtestId));	
					 sb.append(iscriteria?(" and missTest.mtestId="+mtestId+""):(" where missTest.mtestId="+mtestId+""));
					  iscriteria = true;
				}
				if(megName !=null && megName.trim().length() > 0){  
					//criteria.add(Expression.eq("mtestId", mtestId));	
					sb.append(iscriteria?(" and lcase(missTest.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missTest.megName) like '%"+megName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missTest."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
	public int updateMissTest(MissTest transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissTest(MissTest persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public Long saveOrUpdateMissTest(String userid, MissTest missTest)
			throws DataAccessException {
		MissCandidate missCandidate = null;
		Long returnId  = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){		
			missCandidate=(MissCandidate)obj;
			logger.debug("xxxxxxxxxx="+missCandidate.getMcaId().intValue());
			missTest.getId().setMissCandidate(missCandidate);
			query=session.createQuery(" select missTest from MissTest missTest where missTest.id.missCandidate.mcaId=:mcaId and " +
			//		" missTest.id.missChoice.mcId=:mcId and "+
					" missTest.id.missExam.meId=:meId and "+
					" missTest.id.missQuestion.mqId=:mqId and "+
					" missTest.id.missSery.msId=:msId  ");
			query.setParameter("mcaId", missCandidate.getMcaId());
		//	query.setParameter("mcId", missTest.getId().getMissChoice().getMcId());
			query.setParameter("meId", missTest.getId().getMissExam().getMeId());
			query.setParameter("mqId", missTest.getId().getMissQuestion().getMqId()); 
			query.setParameter("msId", missTest.getId().getMissSery().getMsId());  
			List list=query.list();
			if(list!=null && list.size()>0){//update 
				/*int update=update(session, missTest);*/
			/*	MissTest missTestUpdate=(MissTest)list.get(0);
				MissChoice choice=new MissChoice();
				choice.setMcId(missTest.getId().getMissChoice().getMcId());
				missTestUpdate.getId().setMissChoice(choice);*/
				logger.debug("size="+list.size());
				logger.debug("MCA_ID="+missTest.getId().getMissCandidate().getMcaId());
				logger.debug("MC_ID="+missTest.getId().getMissChoice().getMcId());
				logger.debug("ME_ID="+missTest.getId().getMissExam().getMeId());
				logger.debug("MQ_ID="+missTest.getId().getMissCandidate().getMcaId());
				logger.debug("MS_ID="+missTest.getId().getMissSery().getMsId());
				//session.update(missTestUpdate);
				query=session.createQuery("update MissTest missTest " +
						" set missTest.id.missChoice.mcId =:mcId " +
						" where missTest.id.missCandidate.mcaId=:mcaId and " +
						" missTest.id.missExam.meId=:meId and " +
						" missTest.id.missQuestion.mqId=:mqId and " +
						" missTest.id.missSery.msId=:msId ");
				query.setParameter("mcId", missTest.getId().getMissChoice().getMcId());
				
				query.setParameter("mcaId", missCandidate.getMcaId()); 
				query.setParameter("meId", missTest.getId().getMissExam().getMeId());
				query.setParameter("mqId", missTest.getId().getMissQuestion().getMqId()); 
				query.setParameter("msId", missTest.getId().getMissSery().getMsId());  
				returnId = Long.parseLong((query.executeUpdate())+"");
			}else{ //save
				try{
					obj = session.save(missTest);
				
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
	@Override
	public List findMissTestAnswer(String userid,MissTest missTest)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissCandidate missCandidate = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaUsername=:mcaUsername");
		query.setParameter("mcaUsername", userid);
		Object obj=query.uniqueResult(); 	 
		String queryMq="";
		boolean whereMq=false;
		if(missTest.getId().getMissQuestion()!=null && missTest.getId().getMissQuestion().getMqId()!=null)
		{
			queryMq=" missTest.id.missQuestion.mqId=:mqId and";
			whereMq=true;
		}
		if(obj!=null){		
			missCandidate=(MissCandidate)obj;
			logger.debug("xxxxxxxxxx="+missCandidate.getMcaId().intValue());
			missTest.getId().setMissCandidate(missCandidate);
			query=session.createQuery(" select missTest from MissTest missTest where missTest.id.missCandidate.mcaId=:mcaId and " +
					" missTest.id.missExam.meId=:meId and "+
					queryMq+
					" missTest.id.missSery.msId=:msId  ");
			query.setParameter("mcaId", missCandidate.getMcaId());
			query.setParameter("meId", missTest.getId().getMissExam().getMeId());
			if(whereMq)
				query.setParameter("mqId", missTest.getId().getMissQuestion().getMqId()); 
			query.setParameter("msId", missTest.getId().getMissSery().getMsId());  
			return query.list();
		}
		return null;
	}
	 

}