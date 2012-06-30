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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMapPK;
import th.co.aoe.makedev.missconsult.managers.MissSeriesMapService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissSeriesMap  extends HibernateCommon implements MissSeriesMapService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissSeriesMap findMissSeriesMapById(MissSeriesMapPK id)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissSeriesMap missSeriesMap = null;
	/*	Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missSeriesMap from MissSeriesMap missSeriesMap where missSeriesMap.megId=:megId");
		query.setParameter("megId", megId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missSeriesMap=(MissSeriesMap)obj;
		}*/
	  return missSeriesMap;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissSeriesMap(MissSeriesMap transientInstance)
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
	
	

	private int getSize(Session session, MissSeriesMap instance) throws Exception{
		try {
			 
			Long msId = instance.getId().getMsId();
			
			StringBuffer sb =new StringBuffer(" select count(missSeriesMap) from MissSeriesMap missSeriesMap ");
			
			boolean iscriteria = false;
			if(msId !=null && msId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missSeriesMap.id.msId="+msId+""):(" where missSeriesMap.id.msId="+msId+""));
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
	 public List searchMissSeriesMap(MissSeriesMap instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				Long msId = instance.getId().getMsId();
			
				StringBuffer sb =new StringBuffer(" select missSeriesMap from MissSeriesMap missSeriesMap ");
				
				boolean iscriteria = false;
				if(msId !=null && msId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missSeriesMap.id.msId="+msId+""):(" where missSeriesMap.id.msId="+msId+""));
					  iscriteria = true;
				}
				//find misssery attach
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missSeriesMap."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				sb.append(" order by missSeriesMap.msmOrder asc ");
				Query query =session.createQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance)); 
				 logger.debug(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
				 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
				 query.setMaxResults(pagging.getPageSize()); 
				 List<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap> l = query.list();  
				
				 List<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeriesMap>(
							l.size());
					for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap missSeriesMap : l) {
						 th.co.aoe.makedev.missconsult.xstream.MissSeriesMap xmissSeriesMap =new th.co.aoe.makedev.missconsult.xstream.MissSeriesMap ();
						th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMapPK pk =missSeriesMap.getId();
						xmissSeriesMap.setMeId(pk.getMeId());
						xmissSeriesMap.setMsId(pk.getMsId());
						xmissSeriesMap.setMsmOrder(missSeriesMap.getMsmOrder()); 
						//BeanUtils.copyProperties(missSeriesMap, xmissSeriesMap);
						sb.setLength(0);
						sb.append("select missSeriesAttach from MissSeriesAttach missSeriesAttach where missSeriesAttach.msatRef1=:msatRef1 " +
								"and missSeriesAttach.msatRef2=:msatRef2 and missSeriesAttach.msatModule='evaluation' ");
						query =session.createQuery(sb.toString());
						query.setParameter("msatRef1", pk.getMsId());
						query.setParameter("msatRef2", pk.getMeId());
						List<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach> missSeriesAttachs=query.list();
						if(missSeriesAttachs!=null && missSeriesAttachs.size()>0){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesAttach missSeriesAttach=missSeriesAttachs.get(0);
							xmissSeriesMap.setMsatHotlink(missSeriesAttach.getMsatHotlink());
							xmissSeriesMap.setMsatFileName(missSeriesAttach.getMsatFileName());
							xmissSeriesMap.setMsatModule(missSeriesAttach.getMsatModule());
							xmissSeriesMap.setMsatPath(missSeriesAttach.getMsatPath());
							// ext
							/*private String msatFileName;
							private String msatHotlink;
							private String msatModule;
							private String msatPath;*/
							
						}
					
						xmissSeriesMap.setPagging(null);
						xntcCalendars.add(xmissSeriesMap);
					}
				 transList.add(xntcCalendars); 
			 	 transList.add(size); 
				return transList;
			} catch (Exception re) {
				//re.printStackTrace();
				logger.error("find by property name failed", re);
				 
			}
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissSeriesMap(MissSeriesMap transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissSeriesMap(MissSeriesMap persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	 
	 

}