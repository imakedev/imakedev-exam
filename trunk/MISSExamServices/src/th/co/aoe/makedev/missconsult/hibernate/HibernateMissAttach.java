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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAttach;
import th.co.aoe.makedev.missconsult.managers.MissAttachService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissAttach  extends HibernateCommon implements MissAttachService {

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
	public MissAttach findMissAttachById(Long mcaId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissAttach missAttach = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missAttach from MissAttach missAttach where missAttach.mcaId=:mcaId");
		query.setParameter("mcaId", mcaId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missAttach=(MissAttach)obj;
		}
	  return missAttach;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissAttach(MissAttach transientInstance)
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
				Query query=session.createQuery("update MissAttach missAttach " +
						" set missAttach.mcaUsername =:mcaUsername , " +
						" missAttach.mcaStatus ='2' ," +
						" missAttach.mcaPassword ='"+password+"' " +
						" where missAttach.mcaId ="+returnId);
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
	
	

	private int getSize(Session session, MissAttach instance) throws Exception{
		try {
		/*	String mcaStatus=instance.getMcaStatus();
			String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
					 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
			String mcaUsername=instance.getMcaUsername();
			String mcaPassword=instance.getMcaPassword();
			String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
		
		
			StringBuffer sb =new StringBuffer(" select count(missAttach) from MissAttach missAttach ");
			
			boolean iscriteria = false;
			if(mcaStatus !=null && !mcaStatus.equals("-1")){  
				//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
				 sb.append(iscriteria?(" and missAttach.mcaStatus='"+mcaStatus+"'"):(" where missAttach.mcaStatus='"+mcaStatus+"'"));
				  iscriteria = true;
			}
			if(mcaSeries !=null && mcaSeries.trim().length()>0){  
				//criteria.add(Expression.eq("mcaSeries", mcaSeries));	
				 sb.append(iscriteria?(" and missAttach.missSery.msId="+mcaSeries+""):(" where missAttach.missSery.msId="+mcaSeries+""));
				  iscriteria = true;
			}
			if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missAttach.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where lcase(missAttach.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaPassword !=null && mcaPassword.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missAttach.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"):(" where lcase(missAttach.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaCompanyName !=null && mcaCompanyName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missAttach.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"):(" where lcase(missAttach.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"));
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
	 public List searchMissAttach(MissAttach instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				 
/*					String mcaStatus=instance.getMcaStatus();
					String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
							 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
					String mcaUsername=instance.getMcaUsername();
					String mcaPassword=instance.getMcaPassword();
					String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
				
					StringBuffer sb =new StringBuffer(" select missAttach from MissAttach missAttach ");
					
					boolean iscriteria = false;
					if(mcaStatus !=null && !mcaStatus.equals("-1")){  
						//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
						 sb.append(iscriteria?(" and missAttach.mcaStatus='"+mcaStatus+"'"):(" where missAttach.mcaStatus='"+mcaStatus+"'"));
						  iscriteria = true;
					}
					if(mcaSeries !=null && mcaSeries.trim().length()>0){  
						//criteria.add(Expression.eq("mcaSeries", mcaSeries));	
						 sb.append(iscriteria?(" and missAttach.missSery.msId="+mcaSeries+""):(" where missAttach.missSery.msId="+mcaSeries+""));
						  iscriteria = true;
					}
					if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
						//criteria.add(Expression.eq("megId", megId));	
						sb.append(iscriteria?(" and lcase(missAttach.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where lcase(missAttach.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
						  iscriteria = true;
					}
					if(mcaPassword !=null && mcaPassword.trim().length() > 0){  
						//criteria.add(Expression.eq("megId", megId));	
						sb.append(iscriteria?(" and lcase(missAttach.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"):(" where lcase(missAttach.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"));
						  iscriteria = true;
					}
					if(mcaCompanyName !=null && mcaCompanyName.trim().length() > 0){  
						//criteria.add(Expression.eq("megId", megId));	
						sb.append(iscriteria?(" and lcase(missAttach.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"):(" where lcase(missAttach.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"));
						  iscriteria = true;
					}
					
					if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
							sb.append( " order by missAttach."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
*/				return transList;
			} catch (Exception re) {
				//re.printStackTrace();
				logger.error("find by property name failed", re);
				 
			}
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissAttach(MissAttach transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=null;
	
		if(section.equals("0") ){
		/*	query=session.createQuery("update MissAttach missAttach " +
					" set missAttach.mcaUsername =:mcaUsername," +
					" missAttach.mcaPassword =:mcaPassword ," +
					" missAttach.missSery.msId=:msId " +
					" where missAttach.mcaId ="+transientInstance.getMcaId());
			query.setParameter("mcaUsername", transientInstance.getMcaUsername());
			query.setParameter("mcaPassword", transientInstance.getMcaPassword());
			query.setParameter("msId", transientInstance.getMissSery().getMsId());
			return query.executeUpdate();
		}else if(section.equals("1")){
			query=session.createQuery("update MissAttach missAttach " +
					" set missAttach.mcaType =:mcaType ,  " +
					" missAttach.mcaCitizenId =:mcaCitizenId ,  " +
					" missAttach.mcaEmail =:mcaEmail ,  " +
					" missAttach.mcaFirstName =:mcaFirstName ,  " + 
					" missAttach.mcaLastName =:mcaLastName ,  " +
					" missAttach.mcaGender =:mcaGender ,  " +					
					" missAttach.mcaBirthDate =:mcaBirthDate ,  " +
					" missAttach.mcaTitle =:mcaTitle ,  " + 
					" missAttach.mcaDepartment =:mcaDepartment ,  " + 
					" missAttach.mcaPhone =:mcaPhone    " + 
					" where missAttach.mcaId ="+transientInstance.getMcaId());
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
			return query.executeUpdate();*/
		}	
		return 0;
		//return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissAttach(MissAttach persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 

}
