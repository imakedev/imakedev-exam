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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMapPK;
import th.co.aoe.makedev.missconsult.managers.MissAccountSeriesMapService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissAccountSeriesMap  extends HibernateCommon implements MissAccountSeriesMapService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissAccountSeriesMap findMissAccountSeriesMapById(MissAccountSeriesMapPK id)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissAccountSeriesMap missAccountSeriesMap = null;
		/*Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap where missAccountSeriesMap.megId=:megId");
		query.setParameter("megId", megId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missAccountSeriesMap=(MissAccountSeriesMap)obj;
		}*/
	  return missAccountSeriesMap;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissAccountSeriesMap(MissAccountSeriesMap transientInstance,Long maId,Long msId )
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long orderUnit=transientInstance.getMasmOrderUnit();
		StringBuffer sb =new StringBuffer();
		sb.setLength(0); 
		sb.append("select missAccount from MissAccount missAccount  where missAccount.maId=:maId " );
		Query query= session.createQuery(sb.toString());
		query.setParameter("maId", maId);
		Object obj = query.uniqueResult();
		Long maTotalUnit=0l;
		Long maUsedUnit=0l;
		if(obj!=null){
			MissAccount missAccount  = (MissAccount)obj;
			maTotalUnit=(missAccount.getMaTotalUnit()!=null && missAccount.getMaTotalUnit().intValue()>0)?missAccount.getMaTotalUnit():0l;
			maUsedUnit=(missAccount.getMaUsedUnit()!=null && missAccount.getMaUsedUnit().intValue()>0)?missAccount.getMaUsedUnit():0l;
		}
		int available=maTotalUnit.intValue()-maUsedUnit.intValue();
		Long returnId=0l;
		if(orderUnit<=available){
		sb.setLength(0); 
		sb.append(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap" +
				" where missAccountSeriesMap.id.maId=:maId and missAccountSeriesMap.id.msId=:msId ");
	
		query =session.createQuery(sb.toString());
		query.setParameter("maId", maId);
		query.setParameter("msId", msId);
		List list=   query.list();
		if(list!=null && list.size()>0){
			MissAccountSeriesMap missAccountSeriesMap = (MissAccountSeriesMap)list.get(0);
			Long masmAvailable=0l;
			if(missAccountSeriesMap.getMasmAvailable()!=null && missAccountSeriesMap.getMasmAvailable().length()>0){
				 masmAvailable=Long.valueOf(missAccountSeriesMap.getMasmAvailable());				
			}
			sb.setLength(0); 
			sb.append("update MissAccountSeriesMap missAccountSeriesMap set missAccountSeriesMap.masmAvailable =:masmAvailable where " +
					" missAccountSeriesMap.id.maId=:maId and missAccountSeriesMap.id.msId=:msId " );
			query= session.createQuery(sb.toString());
			query.setParameter("masmAvailable", (orderUnit.intValue()+masmAvailable.intValue())+"");
			query.setParameter("maId", maId);
			query.setParameter("msId", msId);
			query.executeUpdate();
		}else{
			MissAccountSeriesMap newSeries = new MissAccountSeriesMap();
			MissAccountSeriesMapPK pk =new MissAccountSeriesMapPK();
			pk.setMaId(maId);
			pk.setMsId(msId);
			newSeries.setId(pk);
			newSeries.setMasmAvailable(transientInstance.getMasmOrderUnit().intValue()+"");
			session.save(newSeries);
		}
		/*sb.setLength(0); 
		sb.append("select missAccount from MissAccount missAccount  where missAccount.maId=:maId " );
		query= session.createQuery(sb.toString());
		query.setParameter("maId", maId);*/
	//	Object obj = query.uniqueResult();
		/*if(obj!=null){
			MissAccount missAccount  = (MissAccount)obj;
			Long usedUnit=0l;*/
			/*if(missAccount.getMaUsedUnit()!=null && missAccount.getMaUsedUnit().intValue()!=0){
				usedUnit=missAccount.getMaUsedUnit();
			}*/
			sb.setLength(0); 
			sb.append("update MissAccount missAccount set missAccount.maUsedUnit =:maUsedUnit where missAccount.maId=:maId " );
			query= session.createQuery(sb.toString());
			query.setParameter("maUsedUnit", Long.valueOf((orderUnit.intValue()+maUsedUnit.intValue())+""));
			query.setParameter("maId", maId);
			query.executeUpdate();
	//	}
			returnId=1l;
   	 }
	return returnId;
		/*Long returnId  = null;
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
		return returnId; */
	}
	
	

	private int getSize(Session session, MissAccountSeriesMap instance) throws Exception{
		try {
			 
			/*Long megId = instance.getMegId();
			String megName = instance.getMegName();
			 
			 
			StringBuffer sb =new StringBuffer(" select count(missAccountSeriesMap) from MissAccountSeriesMap missAccountSeriesMap ");
			
			boolean iscriteria = false;
			if(megId !=null && megId > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missAccountSeriesMap.megId="+megId+""):(" where missAccountSeriesMap.megId="+megId+""));
				  iscriteria = true;
			}
			if(megName !=null && megName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missAccountSeriesMap.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missAccountSeriesMap.megName) like '%"+megName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			Query query =session.createQuery(sb.toString());
			
				// criteria.setProjection(Projections.rowCount()); 
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
	 public List searchMissAccountSeriesMap(MissAccountSeriesMap instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				/*Long megId = instance.getMegId();
				String megName = instance.getMegName();
			
			
				StringBuffer sb =new StringBuffer(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap ");
				
				boolean iscriteria = false;
				if(megId !=null && megId > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missAccountSeriesMap.megId="+megId+""):(" where missAccountSeriesMap.megId="+megId+""));
					  iscriteria = true;
				}
				if(megName !=null && megName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missAccountSeriesMap.megName) like '%"+megName.trim().toLowerCase()+"%'"):(" where lcase(missAccountSeriesMap.megName) like '%"+megName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missAccountSeriesMap."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				Query query =session.createQuery(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance)); 
				 logger.debug(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
				 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
				 query.setMaxResults(pagging.getPageSize());
				 
				 List l = query.list();   
				 transList.add(l); 
			 	 transList.add(size); */
				return transList;
			} catch (Exception re) {
				//re.printStackTrace();
				logger.error("find by property name failed", re);
				 
			}
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissAccountSeriesMap(MissAccountSeriesMap transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissAccountSeriesMap(MissAccountSeriesMap persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}

}
