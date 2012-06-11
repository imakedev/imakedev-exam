package th.co.aoe.makedev.missconsult.hibernate;

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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissContact;
import th.co.aoe.makedev.missconsult.managers.MissContactService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissContact extends HibernateCommon implements MissContactService {

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
	public MissContact findMissContactById(Long mcontactId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissContact missContact = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missContact from MissContact missContact where missContact.mcontactId=:mcontactId");
		query.setParameter("mcontactId", mcontactId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missContact=(MissContact)obj;
		}
	  return missContact;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissContact(MissContact transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		//String password=new BigInteger(40, random).toString(32);
		//73gqqnghrkvfq202q6696gc35o
		//String big=new String(130, random).toString(32);
		//System.out.println(big);
		try{
			Object obj = session.save(transientInstance);
		
			if(obj!=null){
				returnId =(Long) obj;
				/*Query query=session.createQuery("update MissContact missContact " +
						" set missContact.mcaUsername =:mcaUsername , " +
						" missContact.mcaStatus ='2' ," +
						" missContact.mcaPassword ='"+password+"' " +
						" where missContact.mcaId ="+returnId);
				query.setParameter("mcaUsername", "MCA0000"+returnId);
				query.executeUpdate();*/
			}
		} finally {
				if (session != null) {
					session = null;
				} 
		}
		return returnId; 
		
		
	}
	
	

	private int getSize(Session session, MissContact instance) throws Exception{
		try {
		/*	String mcaStatus=instance.getMcaStatus();
			String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
					 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
			String mcaUsername=instance.getMcaUsername();
			String mcaPassword=instance.getMcaPassword();
			String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
		
		
			StringBuffer sb =new StringBuffer(" select count(missContact) from MissContact missContact ");
			
			boolean iscriteria = false;
			if(mcaStatus !=null && !mcaStatus.equals("-1")){  
				//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
				 sb.append(iscriteria?(" and missContact.mcaStatus='"+mcaStatus+"'"):(" where missContact.mcaStatus='"+mcaStatus+"'"));
				  iscriteria = true;
			}
			if(mcaSeries !=null && mcaSeries.trim().length()>0){  
				//criteria.add(Expression.eq("mcaSeries", mcaSeries));	
				 sb.append(iscriteria?(" and missContact.missSery.msId="+mcaSeries+""):(" where missContact.missSery.msId="+mcaSeries+""));
				  iscriteria = true;
			}
			if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missContact.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where lcase(missContact.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaPassword !=null && mcaPassword.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missContact.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"):(" where lcase(missContact.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaCompanyName !=null && mcaCompanyName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missContact.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"):(" where lcase(missContact.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"));
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
	 public List searchMissContact(MissContact instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				 
/*					String mcaStatus=instance.getMcaStatus();
					String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
							 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
					String mcaUsername=instance.getMcaUsername();
					String mcaPassword=instance.getMcaPassword();
					String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
				
					StringBuffer sb =new StringBuffer(" select missContact from MissContact missContact ");
					
					boolean iscriteria = false;
					if(mcaStatus !=null && !mcaStatus.equals("-1")){  
						//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
						 sb.append(iscriteria?(" and missContact.mcaStatus='"+mcaStatus+"'"):(" where missContact.mcaStatus='"+mcaStatus+"'"));
						  iscriteria = true;
					}
					if(mcaSeries !=null && mcaSeries.trim().length()>0){  
						//criteria.add(Expression.eq("mcaSeries", mcaSeries));	
						 sb.append(iscriteria?(" and missContact.missSery.msId="+mcaSeries+""):(" where missContact.missSery.msId="+mcaSeries+""));
						  iscriteria = true;
					}
					if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
						//criteria.add(Expression.eq("megId", megId));	
						sb.append(iscriteria?(" and lcase(missContact.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where lcase(missContact.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
						  iscriteria = true;
					}
					if(mcaPassword !=null && mcaPassword.trim().length() > 0){  
						//criteria.add(Expression.eq("megId", megId));	
						sb.append(iscriteria?(" and lcase(missContact.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"):(" where lcase(missContact.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"));
						  iscriteria = true;
					}
					if(mcaCompanyName !=null && mcaCompanyName.trim().length() > 0){  
						//criteria.add(Expression.eq("megId", megId));	
						sb.append(iscriteria?(" and lcase(missContact.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"):(" where lcase(missContact.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"));
						  iscriteria = true;
					}
					
					if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
							sb.append( " order by missContact."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
	public int updateMissContact(MissContact transientInstance,String section)
			throws DataAccessException {
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
		//return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissContactPhoto(MissContact transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=null;
			query=session.createQuery("update MissContact missContact " +
					" set missContact.mcontactPicturePath =:mcontactPicturePath," +
					" missContact.mcontactPictureFileName =:mcontactPictureFileName ," +
					" missContact.mcontactPictureHotlink =:mcontactPictureHotlink " +
					" where missContact.mcontactId ="+transientInstance.getMcontactId());
			query.setParameter("mcontactPicturePath", transientInstance.getMcontactPicturePath());
			query.setParameter("mcontactPictureFileName", transientInstance.getMcontactPictureFileName());
			query.setParameter("mcontactPictureHotlink", transientInstance.getMcontactPictureHotlink());
			return query.executeUpdate();
	}

	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissContact(MissContact persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public List listContacts(Long long1, String mcontactType)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missContact from MissContact missContact where missContact.mcontactRef=:mcontactRef and " +
				" missContact.mcontactType=:mcontactType ");
		query.setParameter("mcontactRef", long1);
		query.setParameter("mcontactType", mcontactType);
		return query.list(); 
	}
	 

 
}
