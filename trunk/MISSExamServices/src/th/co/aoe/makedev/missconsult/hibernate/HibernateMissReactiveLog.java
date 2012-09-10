package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissReactiveLog;
import th.co.aoe.makedev.missconsult.managers.MissReactiveLogService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public class HibernateMissReactiveLog extends HibernateCommon implements MissReactiveLogService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	 
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissReactiveLog(MissReactiveLog transientInstance)
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
				Query query=session.createQuery("update MissReactiveLog missReactiveLog " +
						" set missReactiveLog.maRegisterNo =:maRegisterNo " +
						" where missReactiveLog.maId ="+returnId);
				query.setParameter("maRegisterNo", "M000000"+returnId);
				query.executeUpdate();
			}
			*/
		} finally {
				if (session != null) {
					session = null;
				} 
		}
		return returnId; 
	}
	
	

	private int getSize(Session session, MissReactiveLog instance) throws Exception{
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
		
		
			StringBuffer sb =new StringBuffer(" select count(missReactiveLog) from MissReactiveLog missReactiveLog ");
			
			boolean iscriteria = false;
			if(maType !=null && maType.length()> 0 ){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missReactiveLog.maType='"+maType+"'"):(" where missReactiveLog.maType='"+maType+"'"));
				  iscriteria = true;
			}
			if(maRegisterType !=null && maRegisterType.length()> 0 && !maRegisterType.equals("-1")){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missReactiveLog.maRegisterType='"+maRegisterType+"'"):(" where missReactiveLog.maRegisterType='"+maRegisterType+"'"));
				  iscriteria = true;
			}
			if(maRegisterNo !=null && maRegisterNo.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missReactiveLog.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lcase(missReactiveLog.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			 
			if(maPhone !=null && maPhone.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missReactiveLog.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lcase(missReactiveLog.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(maName !=null && maName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missReactiveLog.maName) like '%"+maName.trim().toLowerCase()+"%'"):(" where lcase(missReactiveLog.maName) like '%"+maName.trim().toLowerCase()+"%'"));
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
	 public List searchMissReactiveLog(MissReactiveLog instance,Pagging pagging) throws DataAccessException {
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
			
			
				StringBuffer sb =new StringBuffer(" select missReactiveLog from MissReactiveLog missReactiveLog ");
				
				boolean iscriteria = false;
				if(maType !=null && maType.length()> 0 ){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missReactiveLog.maType='"+maType+"'"):(" where missReactiveLog.maType='"+maType+"'"));
					  iscriteria = true;
				}
				if(maRegisterType !=null && maRegisterType.length()> 0 && !maRegisterType.equals("-1")){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missReactiveLog.maRegisterType='"+maRegisterType+"'"):(" where missReactiveLog.maRegisterType='"+maRegisterType+"'"));
					  iscriteria = true;
				}
				if(maRegisterNo !=null && maRegisterNo.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missReactiveLog.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lcase(missReactiveLog.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				 
				if(maPhone !=null && maPhone.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missReactiveLog.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lcase(missReactiveLog.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(maName !=null && maName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missReactiveLog.maName) like '%"+maName.trim().toLowerCase()+"%'"):(" where lcase(missReactiveLo.maName) like '%"+maName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missReactiveLog."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
