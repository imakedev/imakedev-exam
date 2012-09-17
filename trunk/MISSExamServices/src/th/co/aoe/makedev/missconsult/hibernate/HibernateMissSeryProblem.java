package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblemPK;
import th.co.aoe.makedev.missconsult.managers.MissSeryProblemService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissSeryProblem extends HibernateCommon implements MissSeryProblemService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	 
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissSeryProblem(MissSeryProblem transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		try{
			/*
			transientInstance.setMaGrade("1");
			transientInstance.setMaCustomizeHeadColor("body.gif");
			transientInstance.setMaCustomizeColor("smoothness");
			transientInstance.setMaBackgroundColor("253,253,253");
			Object obj = session.save(transientInstance);
		
			if(obj!=null){
				returnId =(Long) obj;
				Query query=session.createQuery("update MissSeryProblem missSeryProblem " +
						" set missSeryProblem.maRegisterNo =:maRegisterNo " +
						" where missSeryProblem.maId ="+returnId);
				query.setParameter("maRegisterNo", "M000000"+returnId);
				query.executeUpdate();
			}
			*/
			MissSeryProblemPK pk =(MissSeryProblemPK)session.save(transientInstance);
			returnId=pk.getMcaId();
		} finally {
				if (session != null) {
					session = null;
				} 
		}
		return returnId; 
	}
	
	

	private int getSize(Session session, MissSeryProblem instance) throws Exception{
		try {
			/*
			String maType=instance.getMaType();
			String maRegisterType = instance.getMaRegisterType();
			String maRegisterNo = instance.getMaRegisterNo();
			Timestamp maRegisterFrom = instance.getMaRegisterFrom();
			Timestamp maRegisterTo = instance.getMaRegisterTo();
			//String maContactName = instance.getMaContactName();
			String maPhone = instance.getMaPhone();
			String maName = instance.getMaName();
		
		
			StringBuffer sb =new StringBuffer(" select count(missSeryProblem) from MissSeryProblem missSeryProblem ");
			
			boolean iscriteria = false;
			if(maType !=null && maType.length()> 0 ){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeryProblem.maType='"+maType+"'"):(" where missSeryProblem.maType='"+maType+"'"));
				  iscriteria = true;
			}
			if(maRegisterType !=null && maRegisterType.length()> 0 && !maRegisterType.equals("-1")){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeryProblem.maRegisterType='"+maRegisterType+"'"):(" where missSeryProblem.maRegisterType='"+maRegisterType+"'"));
				  iscriteria = true;
			}
			if(maRegisterNo !=null && maRegisterNo.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missSeryProblem.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lcase(missSeryProblem.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			 
			if(maPhone !=null && maPhone.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missSeryProblem.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lcase(missSeryProblem.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(maName !=null && maName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missSeryProblem.maName) like '%"+maName.trim().toLowerCase()+"%'"):(" where lcase(missSeryProblem.maName) like '%"+maName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			 
		
			
			
			Query query =session.createQuery(sb.toString());
			 
				 return ((Long)query.uniqueResult()).intValue(); 
				 */
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
	 public List searchMissSeryProblem(MissSeryProblem instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			/*
			try {
				 
				String maType=instance.getMaType();
				String maRegisterType = instance.getMaRegisterType();
				String maRegisterNo = instance.getMaRegisterNo();
				Timestamp maRegisterFrom = instance.getMaRegisterFrom();
				Timestamp maRegisterTo = instance.getMaRegisterTo();
				//String maContactName = instance.getMaContactName();
				String maPhone = instance.getMaPhone();
				String maName = instance.getMaName();
			
			
				StringBuffer sb =new StringBuffer(" select missSeryProblem from MissSeryProblem missSeryProblem ");
				
				boolean iscriteria = false;
				if(maType !=null && maType.length()> 0 ){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeryProblem.maType='"+maType+"'"):(" where missSeryProblem.maType='"+maType+"'"));
					  iscriteria = true;
				}
				if(maRegisterType !=null && maRegisterType.length()> 0 && !maRegisterType.equals("-1")){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeryProblem.maRegisterType='"+maRegisterType+"'"):(" where missSeryProblem.maRegisterType='"+maRegisterType+"'"));
					  iscriteria = true;
				}
				if(maRegisterNo !=null && maRegisterNo.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missSeryProblem.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lcase(missSeryProblem.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				 
				if(maPhone !=null && maPhone.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missSeryProblem.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lcase(missSeryProblem.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(maName !=null && maName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missSeryProblem.maName) like '%"+maName.trim().toLowerCase()+"%'"):(" where lcase(missSeryProblem.maName) like '%"+maName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missSeryProblem."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				Query query =session.createQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance)); 
				 logger.debug(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
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
			*/
			return transList;
		}
	 

}
