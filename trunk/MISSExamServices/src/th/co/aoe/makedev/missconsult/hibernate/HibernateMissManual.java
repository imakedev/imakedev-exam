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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissManual;
import th.co.aoe.makedev.missconsult.managers.MissManualService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissManual  extends HibernateCommon implements MissManualService {

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
	public MissManual findMissManualById(Long mmId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissManual missManual = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missManual from MissManual missManual where missManual.mmId=:mmId");
		query.setParameter("mmId", mmId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missManual=(MissManual)obj;
		}
	  return missManual;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissManual(MissManual transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
	//	String password=new BigInteger(40, random).toString(32);
		//73gqqnghrkvfq202q6696gc35o
		//String big=new String(130, random).toString(32);
		//System.out.println(big);
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
	
	

	private int getSize(Session session, MissManual instance) throws Exception{
		try {
		/*	String mcaStatus=instance.getMcaStatus();
			String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
					 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
			String mcaUsername=instance.getMcaUsername();
			String mcaPassword=instance.getMcaPassword();
			String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
		
		
			StringBuffer sb =new StringBuffer(" select count(missManual) from MissManual missManual ");
			
			boolean iscriteria = false;
			if(mcaStatus !=null && !mcaStatus.equals("-1")){  
				//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
				 sb.append(iscriteria?(" and missManual.mcaStatus='"+mcaStatus+"'"):(" where missManual.mcaStatus='"+mcaStatus+"'"));
				  iscriteria = true;
			}
			if(mcaSeries !=null && mcaSeries.trim().length()>0){  
				//criteria.add(Expression.eq("mcaSeries", mcaSeries));	
				 sb.append(iscriteria?(" and missManual.missSery.msId="+mcaSeries+""):(" where missManual.missSery.msId="+mcaSeries+""));
				  iscriteria = true;
			}
			if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missManual.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where lcase(missManual.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaPassword !=null && mcaPassword.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missManual.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"):(" where lcase(missManual.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(mcaCompanyName !=null && mcaCompanyName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missManual.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"):(" where lcase(missManual.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"));
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
	 public List searchMissManual(MissManual instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				 
/*					String mcaStatus=instance.getMcaStatus();
					String mcaSeries=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
							 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()+""):null;
					String mcaUsername=instance.getMcaUsername();
					String mcaPassword=instance.getMcaPassword();
					String mcaCompanyName=(instance.getMissAccount()!=null && instance.getMissAccount().getMaName()!=null)?(instance.getMissAccount().getMaName()):null;
				
					StringBuffer sb =new StringBuffer(" select missManual from MissManual missManual ");
					
					boolean iscriteria = false;
					if(mcaStatus !=null && !mcaStatus.equals("-1")){  
						//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
						 sb.append(iscriteria?(" and missManual.mcaStatus='"+mcaStatus+"'"):(" where missManual.mcaStatus='"+mcaStatus+"'"));
						  iscriteria = true;
					}
					if(mcaSeries !=null && mcaSeries.trim().length()>0){  
						//criteria.add(Expression.eq("mcaSeries", mcaSeries));	
						 sb.append(iscriteria?(" and missManual.missSery.msId="+mcaSeries+""):(" where missManual.missSery.msId="+mcaSeries+""));
						  iscriteria = true;
					}
					if(mcaUsername !=null && mcaUsername.trim().length() > 0){  
						//criteria.add(Expression.eq("megId", megId));	
						sb.append(iscriteria?(" and lcase(missManual.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"):(" where lcase(missManual.mcaUsername) like '%"+mcaUsername.trim().toLowerCase()+"%'"));
						  iscriteria = true;
					}
					if(mcaPassword !=null && mcaPassword.trim().length() > 0){  
						//criteria.add(Expression.eq("megId", megId));	
						sb.append(iscriteria?(" and lcase(missManual.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"):(" where lcase(missManual.mcaPassword) like '%"+mcaPassword.trim().toLowerCase()+"%'"));
						  iscriteria = true;
					}
					if(mcaCompanyName !=null && mcaCompanyName.trim().length() > 0){  
						//criteria.add(Expression.eq("megId", megId));	
						sb.append(iscriteria?(" and lcase(missManual.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"):(" where lcase(missManual.missAccount.maName) like '%"+mcaCompanyName.trim().toLowerCase()+"%'"));
						  iscriteria = true;
					}
					
					if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
							sb.append( " order by missManual."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
	public int updateMissManual(MissManual transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissManual(MissManual persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 
 

}
