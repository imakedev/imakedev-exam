package th.co.aoe.makedev.missconsult.hibernate;

import java.io.File;
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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissManual;
import th.co.aoe.makedev.missconsult.managers.MissManualService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissManual  extends HibernateCommon implements MissManualService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	//private static final SecureRandom random = new SecureRandom();
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
	//	Query query=session.createQuery(" select missManual from MissManual missManual where missManual.mmId=:mmId");
		Query query=session.createQuery(" select missManual from MissManual missManual where missManual.missSery.msId=:msId");
		query.setParameter("msId", mmId);
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
	
	

	private int getSize(Session session,Long maId, MissManual instance) throws Exception{
		try {
			Long msId=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
					 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()):null;
		
		
			StringBuffer sb =new StringBuffer(" select count(missManual) from MissManual missManual ");
			Query query =null;
			String msIds="";
			    if(maId!=null){
			    	query=session.createQuery(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap" +
			    			" where missAccountSeriesMap.id.maId=:maId ");
			    	query.setParameter("maId", maId);
			    	@SuppressWarnings("unchecked")
					List<MissAccountSeriesMap>  missAccountSeriesMaps=query.list();
			    	int size=missAccountSeriesMaps.size();
			    	int index=0;
			    	
			    	for (MissAccountSeriesMap missAccountSeriesMap : missAccountSeriesMaps) {
						if(index==size-1)
							msIds=msIds+missAccountSeriesMap.getId().getMsId().intValue()+"";
						else
							msIds=msIds+missAccountSeriesMap.getId().getMsId().intValue()+",";
						index++;
					}
			    }
			boolean iscriteria = false;
			if(msId !=null && msId.intValue()!=0){  
				//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
				 sb.append(iscriteria?(" and missManual.missSery.msId="+msId.intValue()+""):(" where missManual.missSery.msId="+msId.intValue()+""));
				  iscriteria = true;
			}
			if(msIds.length()>0){
				sb.append(iscriteria?(" and missManual.missSery.msId in("+msIds+")"):(" where missManual.missSery.msId in ("+msIds+")"));
				  iscriteria = true;
			}
			 query =session.createQuery(sb.toString());
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
	 public List searchMissManual(MissManual instance,Long maId,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				Query query =null;
				String msIds="";
				    if(maId!=null){
				    	query=session.createQuery(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap" +
				    			" where missAccountSeriesMap.id.maId=:maId ");
				    	query.setParameter("maId", maId);
				    	List<MissAccountSeriesMap>  missAccountSeriesMaps=query.list();
				    	int size=missAccountSeriesMaps.size();
				    	int index=0;
				    	
				    	for (MissAccountSeriesMap missAccountSeriesMap : missAccountSeriesMaps) {
							if(index==size-1)
								msIds=msIds+missAccountSeriesMap.getId().getMsId().intValue()+"";
							else
								msIds=msIds+missAccountSeriesMap.getId().getMsId().intValue()+",";
							index++;
						}
				    }
					Long msId=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
							 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()):null;
				
					StringBuffer sb =new StringBuffer(" select missManual from MissManual missManual ");
					
					boolean iscriteria = false;
					if(msId !=null && msId.intValue()!=0){  
						//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
						 sb.append(iscriteria?(" and missManual.missSery.msId="+msId.intValue()+""):(" where missManual.missSery.msId="+msId.intValue()+""));
						  iscriteria = true;
					}
					if(msIds.length()>0){
						sb.append(iscriteria?(" and missManual.missSery.msId in("+msIds+")"):(" where missManual.missSery.msId in ("+msIds+")"));
						  iscriteria = true;
					}
					
					
					if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
							sb.append( " order by missManual."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
					}			
					 query =session.createQuery(sb.toString());
					// set pagging.
					 String size = String.valueOf(getSize(session,maId,instance)); 
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
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissManual(MissManual transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
		MissManual missManual = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		
		Query query=session.createQuery(" select missManual from MissManual missManual " +
				" where missManual.missSery.msId=:msId ");
		query.setParameter("msId", transientInstance.getMissSery().getMsId());
		@SuppressWarnings("rawtypes")
		List list=query.list();
		logger.debug(" attach size="+list.size());
		if(list.size()>0){
			 missManual=(MissManual)list.get(0); 
				if(missManual.getMmPath()!=null && missManual.getMmPath().length()>0){
					 File file_delete=new File("/opt/attach/attachManual/"+missManual.getMmPath().trim());
					 if(file_delete.exists())
						 file_delete.delete(); 
				} 
			 missManual.setMmFileName(transientInstance.getMmFileName());
			 missManual.setMmHotlink(transientInstance.getMmHotlink());
			 missManual.setMmPath(transientInstance.getMmPath());
			/* missManual.setMatRef(Long.parseLong(id));
			 missManual.setMatModule(module);*/
		//	BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);					
			return update(session, missManual);
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
	public int deleteMissManual(MissManual persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 
 

}
