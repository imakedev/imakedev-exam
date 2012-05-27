package th.co.aoe.makedev.missconsult.hibernate;

import java.math.BigInteger;
import java.security.SecureRandom;
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
import th.co.aoe.makedev.missconsult.managers.MissCandidateService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissCandidate  extends HibernateCommon implements MissCandidateService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private static final SecureRandom random = new SecureRandom();
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissCandidate findMissCandidateById(Long mcaId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissCandidate missCandidate = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missCandidate from MissCandidate missCandidate where missCandidate.mcaId=:mcaId");
		query.setParameter("mcaId", mcaId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missCandidate=(MissCandidate)obj;
		}
	  return missCandidate;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissCandidate(MissCandidate transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		String password=new BigInteger(40, random).toString(32);
		//73gqqnghrkvfq202q6696gc35o
		//String big=new String(130, random).toString(32);
		//System.out.println(big);
		try{
			Object obj = session.save(transientInstance);
		
			if(obj!=null){
				returnId =(Long) obj;
				Query query=session.createQuery("update MissCandidate missCandidate " +
						" set missCandidate.mcaUsername =:mcaUsername , " +
						" missCandidate.mcaStatus ='2' ," +
						" missCandidate.mcaPassword ='"+password+"' " +
						" where missCandidate.mcaId ="+returnId);
				query.setParameter("mcaUsername", "MCA0000"+returnId);
				query.executeUpdate();
			}
		} finally {
				if (session != null) {
					session = null;
				} 
		}
		return returnId; 
		
		
	}
	
	

	private int getSize(Session session, MissCandidate instance) throws Exception{
		try {
			String mcaStatus=instance.getMcaStatus();
			String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
					 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
			String mcaUsername=instance.getMcaUsername();
			String mcaPassword=instance.getMcaPassword();
			String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
		
		
			StringBuffer sb =new StringBuffer(" select count(missCandidate) from MissCandidate missCandidate ");
			
			boolean iscriteria = false;
			if(mcaStatus !=null && !mcaStatus.equals("-1")){  
				//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
				 sb.append(iscriteria?(" and missCandidate.mcaStatus='"+mcaStatus+"'"):(" where missCandidate.mcaStatus='"+mcaStatus+"'"));
				  iscriteria = true;
			}
			if(mcaSeries !=null && mcaSeries.trim().length()>0){  
				//criteria.add(Expression.eq("mcaSeries", mcaSeries));	
				 sb.append(iscriteria?(" and missCandidate.missSery.msId="+mcaSeries+""):(" where missCandidate.missSery.msId="+mcaSeries+""));
				  iscriteria = true;
			}
			if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missCandidate.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaPassword !=null && mcaPassword.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missCandidate.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaCompanyName !=null && mcaCompanyName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missCandidate.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"));
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
	 public List searchMissCandidate(MissCandidate instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
			 
				String mcaStatus=instance.getMcaStatus();
				String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
						 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
				String mcaUsername=instance.getMcaUsername();
				String mcaPassword=instance.getMcaPassword();
				String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
			
				StringBuffer sb =new StringBuffer(" select missCandidate from MissCandidate missCandidate ");
				
				boolean iscriteria = false;
				if(mcaStatus !=null && !mcaStatus.equals("-1")){  
					//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
					 sb.append(iscriteria?(" and missCandidate.mcaStatus='"+mcaStatus+"'"):(" where missCandidate.mcaStatus='"+mcaStatus+"'"));
					  iscriteria = true;
				}
				if(mcaSeries !=null && mcaSeries.trim().length()>0){  
					//criteria.add(Expression.eq("mcaSeries", mcaSeries));	
					 sb.append(iscriteria?(" and missCandidate.missSery.msId="+mcaSeries+""):(" where missCandidate.missSery.msId="+mcaSeries+""));
					  iscriteria = true;
				}
				if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missCandidate.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(mcaPassword !=null && mcaPassword.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missCandidate.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(mcaCompanyName !=null && mcaCompanyName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missCandidate.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"):(" where lcase(missCandidate.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missCandidate."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
	public int updateMissCandidate(MissCandidate transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=null;
		//int result = query.executeUpdate();
		/*Query query = session.createQuery("update Stock set stockName = :stockName" +
				" where stockCode = :stockCode");
query.setParameter("stockName", "DIALOG1");
query.setParameter("stockCode", "7277");
int result = query.executeUpdate();*/
		if(section.equals("0") ){
			query=session.createQuery("update MissCandidate missCandidate " +
					" set missCandidate.mcaUsername =:mcaUsername," +
					" missCandidate.mcaPassword =:mcaPassword ," +
					" missCandidate.missSery.msId=:msId " +
					" where missCandidate.mcaId ="+transientInstance.getMcaId());
			query.setParameter("mcaUsername", transientInstance.getMcaUsername());
			query.setParameter("mcaPassword", transientInstance.getMcaPassword());
			query.setParameter("msId", transientInstance.getMissSery().getMsId());
			return query.executeUpdate();
		}else if(section.equals("1")){
			query=session.createQuery("update MissCandidate missCandidate " +
					" set missCandidate.mcaType =:mcaType ,  " +
					" missCandidate.mcaCitizenId =:mcaCitizenId ,  " +
					" missCandidate.mcaEmail =:mcaEmail ,  " +
					" missCandidate.mcaFirstName =:mcaFirstName ,  " + 
					" missCandidate.mcaLastName =:mcaLastName ,  " +
					" missCandidate.mcaGender =:mcaGender ,  " +					
					" missCandidate.mcaBirthDate =:mcaBirthDate ,  " +
					" missCandidate.mcaTitle =:mcaTitle ,  " + 
					" missCandidate.mcaDepartment =:mcaDepartment ,  " + 
					" missCandidate.mcaPhone =:mcaPhone    " + 
					" where missCandidate.mcaId ="+transientInstance.getMcaId());
			query.setParameter("mcaType", transientInstance.getMcaType());
			query.setParameter("mcaCitizenId", transientInstance.getMcaCitizenId());
			query.setParameter("mcaEmail", transientInstance.getMcaEmail());
			query.setParameter("mcaFirstName", transientInstance.getMcaFirstName());
			query.setParameter("mcaLastName", transientInstance.getMcaLastName());
			query.setParameter("mcaGender", transientInstance.getMcaGender());
			query.setParameter("mcaBirthDate", transientInstance.getMcaBirthDate());
			query.setParameter("mcaTitle", transientInstance.getMcaTitle());
			query.setParameter("mcaDepartment", transientInstance.getMcaDepartment());
			query.setParameter("mcaPhone", transientInstance.getMcaPhone());
			return query.executeUpdate();
		}	
		return 0;
		//return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissCandidate(MissCandidate persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 

}