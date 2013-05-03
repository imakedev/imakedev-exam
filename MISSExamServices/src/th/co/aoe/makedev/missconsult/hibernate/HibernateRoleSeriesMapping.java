package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping;
import th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMappingPK;
import th.co.aoe.makedev.missconsult.managers.RoleSeriesMappingService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateRoleSeriesMapping extends HibernateCommon implements RoleSeriesMappingService {

	//private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
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
	
	

	/*private int getSize(Session session, RoleSeriesMapping instance) throws Exception{
		try {
		 
			return 0;
				 
		 
		} catch (HibernateException re) {
			logger.error("HibernateException",re);
			throw re;
		} catch (Exception e) {
			logger.error("Exception",e);
			throw e;
		}
	}*/
	 @SuppressWarnings({ "rawtypes"})
	 @Transactional(readOnly=true)
	 public List searchRoleSeriesMapping(RoleSeriesMapping instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			 
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List listRoleSeriesMappingByrcId(Long rcId) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
			Query query=session.createQuery(" select roleSeriesMapping from RoleSeriesMapping roleSeriesMapping where roleSeriesMapping.id.rcId=:rcId");
			query.setParameter("rcId", rcId);
			List<th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping> list=query.list();
			List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping> roles=new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping>(list.size());
			for (th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMapping type : list) {
				th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping xrole=new th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping();
				th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesMappingPK pk= type.getId();
				xrole.setRcId(pk.getRcId());
				xrole.setMsId(pk.getMsId());
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
		//	Query query=session.createQuery(" select roleSeriesMapping from RoleSeriesMapping roleSeriesMapping where roleSeriesMapping.mmId=:mmId");
		Query query=session.createQuery("delete RoleSeriesMapping roleSeriesMapping where roleSeriesMapping.id.rcId ="+rcId.intValue());
		int result = query.executeUpdate();
		if(msIds!=null && msIds.length>0)
		for (String msid : msIds) {
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
			e.printStackTrace();
		}
		return 0;
	}
	 

}
