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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder;
import th.co.aoe.makedev.missconsult.managers.MissSeryOrderService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

public class HibernateMissSeryOrder extends HibernateCommon implements MissSeryOrderService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	 
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissSeryOrder(MissSeryOrder transientInstance)
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
				Query query=session.createQuery("update MissSeryOrder missSeryOrder " +
						" set missSeryOrder.maRegisterNo =:maRegisterNo " +
						" where missSeryOrder.maId ="+returnId);
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
	
	

	private int getSize(Session session, MissSeryOrder instance) throws Exception{
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
		
		
			StringBuffer sb =new StringBuffer(" select count(missSeryOrder) from MissSeryOrder missSeryOrder ");
			
			boolean iscriteria = false;
			if(maType !=null && maType.length()> 0 ){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeryOrder.maType='"+maType+"'"):(" where missSeryOrder.maType='"+maType+"'"));
				  iscriteria = true;
			}
			if(maRegisterType !=null && maRegisterType.length()> 0 && !maRegisterType.equals("-1")){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeryOrder.maRegisterType='"+maRegisterType+"'"):(" where missSeryOrder.maRegisterType='"+maRegisterType+"'"));
				  iscriteria = true;
			}
			if(maRegisterNo !=null && maRegisterNo.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missSeryOrder.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lcase(missSeryOrder.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			 
			if(maPhone !=null && maPhone.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missSeryOrder.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lcase(missSeryOrder.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(maName !=null && maName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missSeryOrder.maName) like '%"+maName.trim().toLowerCase()+"%'"):(" where lcase(missSeryOrder.maName) like '%"+maName.trim().toLowerCase()+"%'"));
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
	 public List searchMissSeryOrder(MissSeryOrder instance,Pagging pagging) throws DataAccessException {
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
			
			
				StringBuffer sb =new StringBuffer(" select missSeryOrder from MissSeryOrder missSeryOrder ");
				
				boolean iscriteria = false;
				if(maType !=null && maType.length()> 0 ){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeryOrder.maType='"+maType+"'"):(" where missSeryOrder.maType='"+maType+"'"));
					  iscriteria = true;
				}
				if(maRegisterType !=null && maRegisterType.length()> 0 && !maRegisterType.equals("-1")){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeryOrder.maRegisterType='"+maRegisterType+"'"):(" where missSeryOrder.maRegisterType='"+maRegisterType+"'"));
					  iscriteria = true;
				}
				if(maRegisterNo !=null && maRegisterNo.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missSeryOrder.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lcase(missSeryOrder.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				 
				if(maPhone !=null && maPhone.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missSeryOrder.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lcase(missSeryOrder.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(maName !=null && maName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missSeryOrder.maName) like '%"+maName.trim().toLowerCase()+"%'"):(" where lcase(missSeryOrder.maName) like '%"+maName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missSeryOrder."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
