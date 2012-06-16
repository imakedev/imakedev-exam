package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.managers.MissSeriesAttachService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissSeriesAttach  extends HibernateCommon implements MissSeriesAttachService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissSeriesAttach(MissSeriesAttach transientInstance)
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
	
	

	private int getSize(Session session, MissSeriesAttach instance) throws Exception{
		try {
			 
			StringBuffer sb =new StringBuffer(" select count(missSeriesAttach) from MissSeriesAttach missSeriesAttach ");
			
			Long msatId = instance.getMsatId();
			Long msatRef1=instance.getMsatRef1();
			Long msatRef2=instance.getMsatRef2();
			String msatModule=instance.getMsatModule();
			String msatHotlink=instance.getMsatHotlink();
			
			boolean iscriteria = false;
			if(msatId !=null && msatId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeriesAttach.msatId="+msatId+""):(" where missSeriesAttach.msatId="+msatId+""));
				  iscriteria = true;
			}
			if(msatRef1 !=null && msatRef1 > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeriesAttach.msatRef1="+msatRef1+""):(" where missSeriesAttach.msatRef1="+msatRef1+""));
				  iscriteria = true;
			}
			if(msatRef2 !=null && msatRef2 > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeriesAttach.msatRef2="+msatRef2+""):(" where missSeriesAttach.msatRef2="+msatRef2+""));
				  iscriteria = true;
			}
			if(msatModule !=null && msatModule.length()> 0 ){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeriesAttach.msatModule='"+msatModule+"'"):(" where missSeriesAttach.msatModule='"+msatModule+"'"));
				  iscriteria = true;
			}if(msatHotlink !=null && msatHotlink.length()> 0 ){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeriesAttach.msatHotlink='"+msatHotlink+"'"):(" where missSeriesAttach.msatHotlink='"+msatHotlink+"'"));
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
	 public List searchMissSeriesAttach(MissSeriesAttach instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				Long msatId = instance.getMsatId();
				Long msatRef1=instance.getMsatRef1();
				Long msatRef2=instance.getMsatRef2();
				String msatModule=instance.getMsatModule();
				String msatHotlink=instance.getMsatHotlink();
				StringBuffer sb =new StringBuffer(" select missSeriesAttach from MissSeriesAttach missSeriesAttach ");
				
				boolean iscriteria = false;
				if(msatId !=null && msatId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeriesAttach.msatId="+msatId+""):(" where missSeriesAttach.msatId="+msatId+""));
					  iscriteria = true;
				}
				if(msatRef1 !=null && msatRef1 > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeriesAttach.msatRef1="+msatRef1+""):(" where missSeriesAttach.msatRef1="+msatRef1+""));
					  iscriteria = true;
				}
				if(msatRef2 !=null && msatRef2 > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeriesAttach.msatRef2="+msatRef2+""):(" where missSeriesAttach.msatRef2="+msatRef2+""));
					  iscriteria = true;
				}
				if(msatModule !=null && msatModule.length()> 0 ){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeriesAttach.msatModule='"+msatModule+"'"):(" where missSeriesAttach.msatModule='"+msatModule+"'"));
					  iscriteria = true;
				}if(msatHotlink !=null && msatHotlink.length()> 0 ){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeriesAttach.msatHotlink='"+msatHotlink+"'"):(" where missSeriesAttach.msatHotlink='"+msatHotlink+"'"));
					  iscriteria = true;
				}

				
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missSeriesAttach."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				//sb.append(" order by missSeriesAttach.msmOrder asc ");
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
	public int updateMissSeriesAttach(MissSeriesAttach transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		String query_ref2="";
		if(transientInstance.getMsatRef2()!=null && transientInstance.getMsatRef2().intValue()!=0){
			query_ref2=" and missSeriesAttach.msatRef2=:msatRef2 " ;
		}
		MissSeriesAttach missSeriesAttach = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		
		Query query=session.createQuery(" select missSeriesAttach from MissSeriesAttach missSeriesAttach " +
				" where missSeriesAttach.msatModule=:msatModule " +
				" and missSeriesAttach.msatRef1=:msatRef1 " +
				query_ref2+
				" and missSeriesAttach.msatHotlink=:msatHotlink ");
		query.setParameter("msatModule", transientInstance.getMsatModule());
		query.setParameter("msatRef1", transientInstance.getMsatRef1());
		if(query_ref2.length()>0)
			query.setParameter("msatRef2", transientInstance.getMsatRef2());
		query.setParameter("msatHotlink", transientInstance.getMsatHotlink());
		List list=query.list();
		logger.debug(" attach size="+list.size());
		if(list.size()>0){
			missSeriesAttach=(MissSeriesAttach)list.get(0);
			 missSeriesAttach.setMsatFileName(transientInstance.getMsatFileName());
			 missSeriesAttach.setMsatHotlink(transientInstance.getMsatHotlink());
			 missSeriesAttach.setMsatPath(transientInstance.getMsatPath());
			/* missSeriesAttach.setMatRef(Long.parseLong(id));
			 missSeriesAttach.setMatModule(module);*/
		//	BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);					
			return update(session, missSeriesAttach);
		}else{
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
			return returnId.intValue(); 
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissSeriesAttach(MissSeriesAttach persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 
	 


}
