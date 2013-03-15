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
import th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping;
import th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMappingPK;
import th.co.aoe.makedev.missconsult.managers.RoleSeriesMappingService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateRoleSeriesMapping extends HibernateCommon implements RoleSeriesMappingService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public RoleSeriesMapping findRoleSeriesMappingById(Long mmId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		RoleSeriesMapping roleSeriesMapping = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
	//	Query query=session.createQuery(" select roleSeriesMapping from RoleSeriesMapping roleSeriesMapping where roleSeriesMapping.mmId=:mmId");
		Query query=session.createQuery(" select roleSeriesMapping from RoleSeriesMapping roleSeriesMapping where roleSeriesMapping.missSery.msId=:msId");
		query.setParameter("msId", mmId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			roleSeriesMapping=(RoleSeriesMapping)obj;
		}
	  return roleSeriesMapping;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveRoleSeriesMapping(RoleSeriesMapping transientInstance)
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
	
	

	private int getSize(Session session, RoleSeriesMapping instance) throws Exception{
		try {
			/*Long msId=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
					 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()):null;
		
		
			StringBuffer sb =new StringBuffer(" select count(roleSeriesMapping) from RoleSeriesMapping roleSeriesMapping ");
			
			boolean iscriteria = false;
			if(msId !=null && msId.intValue()!=0){  
				//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
				 sb.append(iscriteria?(" and roleSeriesMapping.missSery.msId="+msId.intValue()+""):(" where roleSeriesMapping.missSery.msId="+msId.intValue()+""));
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
	 public List searchRoleSeriesMapping(RoleSeriesMapping instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			/*Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				 
					Long msId=(instance.getMissSery()!=null && instance.getMissSery().getMsId()!=null 
							 && instance.getMissSery().getMsId().intValue()!=0 )?(instance.getMissSery().getMsId()):null;
				
					StringBuffer sb =new StringBuffer(" select roleSeriesMapping from RoleSeriesMapping roleSeriesMapping ");
					
					boolean iscriteria = false;
					if(msId !=null && msId.intValue()!=0){  
						//criteria.add(Expression.eq("mcaStatus", mcaStatus));	
						 sb.append(iscriteria?(" and roleSeriesMapping.missSery.msId="+msId.intValue()+""):(" where roleSeriesMapping.missSery.msId="+msId.intValue()+""));
						  iscriteria = true;
					}
					
					
					if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
							sb.append( " order by roleSeriesMapping."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
				 
			}*/
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateRoleSeriesMapping(RoleSeriesMapping transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
		/*RoleSeriesMapping roleSeriesMapping = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		
		Query query=session.createQuery(" select roleSeriesMapping from RoleSeriesMapping roleSeriesMapping " +
				" where roleSeriesMapping.missSery.msId=:msId ");
		query.setParameter("msId", transientInstance.getMissSery().getMsId());
		List list=query.list();
		logger.debug(" attach size="+list.size());
		if(list.size()>0){
			 roleSeriesMapping=(RoleSeriesMapping)list.get(0);
			 roleSeriesMapping.setMmFileName(transientInstance.getMmFileName());
			 roleSeriesMapping.setMmHotlink(transientInstance.getMmHotlink());
			 roleSeriesMapping.setMmPath(transientInstance.getMmPath());
			 roleSeriesMapping.setMatRef(Long.parseLong(id));
			 roleSeriesMapping.setMatModule(module);
		//	BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);					
			return update(session, roleSeriesMapping);
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
		}*/
		return 0;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteRoleSeriesMapping(RoleSeriesMapping persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public List listRoleSeriesMappingByrcId(Long rcId) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
			Query query=session.createQuery(" select roleSeriesMapping from RoleSeriesMapping roleSeriesMapping where roleSeriesMapping.id.rcId=:rcId");
			query.setParameter("rcId", rcId);
			//System.out.println("rcId="+rcId);
			List<th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping> list=query.list();
			List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping> roles=new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping>(list.size());
			for (th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping type : list) {
				th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping xrole=new th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping();
				th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMappingPK pk= type.getId();
				xrole.setRcId(pk.getRcId());
				xrole.setMsId(pk.getMsId());
				//System.out.println("getMsId="+pk.getMsId());
				xrole.setPagging(null);
				roles.add(xrole);
			}
			return roles;
	}
	@Override
	public int updateRoleSeriesMapping(Long rcId, String[] msIds)
			throws DataAccessException {
		// TODO Auto-generated method stub
		try{
		Session session=sessionAnnotationFactory.getCurrentSession();
		//System.out.println("rcId=="+rcId);
		//	Query query=session.createQuery(" select roleSeriesMapping from RoleSeriesMapping roleSeriesMapping where roleSeriesMapping.mmId=:mmId");
		Query query=session.createQuery("delete RoleSeriesMapping roleSeriesMapping where roleSeriesMapping.id.rcId ="+rcId.intValue());
		int result = query.executeUpdate();
		//System.out.println(result);
		if(msIds!=null && msIds.length>0)
		for (String msid : msIds) {
			//System.out.println("		rtid==>"+rtid);
			RoleSeriesMapping mapping =new RoleSeriesMapping();
			RoleSeriesMappingPK pk =new RoleSeriesMappingPK();
			 pk.setRcId(rcId);
			pk.setMsId(Long.parseLong(msid)); 
			mapping.setId(pk);
			session.save(mapping); 
			
		}
		//int canUpdate = 0;
		return result;
		}catch(Exception e){
			//System.out.println("error");
			e.printStackTrace();
		}
		return 0;
	}
	 

}
