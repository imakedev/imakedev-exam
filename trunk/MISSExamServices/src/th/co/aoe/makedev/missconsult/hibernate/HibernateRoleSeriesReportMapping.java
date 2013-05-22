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

import th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMapping;
import th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMappingPK;
import th.co.aoe.makedev.missconsult.managers.RoleSeriesReportMappingService;

@Repository
@Transactional
public class HibernateRoleSeriesReportMapping extends HibernateCommon implements RoleSeriesReportMappingService {

	//private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	} 
	 
	
 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List listRoleSeriesReportMappingByrcId(Long rcId,Long msId) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
			Query query=session.createQuery(" select roleSeriesReportMapping from RoleSeriesReportMapping roleSeriesReportMapping where roleSeriesReportMapping.id.rcId=:rcId" +
					" and roleSeriesReportMapping.id.msId=:msId ");
			query.setParameter("rcId", rcId);
			query.setParameter("msId", msId);
			List<th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMapping> list=query.list();
			List<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping> roles=new ArrayList<th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping>(list.size());
			for (th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMapping type : list) {
				th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping xrole=new th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping();
				th.co.aoe.makedev.missconsult.hibernate.bean.RoleSeriesReportMappingPK pk= type.getId();
				xrole.setRcId(pk.getRcId());
				xrole.setMsId(pk.getMsId());
				xrole.setMsOrder(pk.getMsOrder());
				xrole.setPagging(null);
				roles.add(xrole);
			}
			return roles;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateRoleSeriesReportMapping(Long rcId, Long msId,String[] msOrders)
			throws DataAccessException {
		// TODO Auto-generated method stub
		try{
		Session session=sessionAnnotationFactory.getCurrentSession();
		//	Query query=session.createQuery(" select roleSeriesReportMapping from RoleSeriesReportMapping roleSeriesReportMapping where roleSeriesReportMapping.mmId=:mmId");
		Query query=session.createQuery("delete RoleSeriesReportMapping roleSeriesReportMapping where roleSeriesReportMapping.id.rcId ="+rcId.intValue()+
				" and roleSeriesReportMapping.id.msId="+msId);
		int result = query.executeUpdate();
		/*System.out.println("msOrders.length->"+msOrders.length);
		System.out.println("rcId->"+rcId);
		System.out.println("msId->"+msId);*/ 
		if(msOrders!=null && msOrders.length>0)
		for (String msOrder : msOrders) {
			RoleSeriesReportMapping mapping =new RoleSeriesReportMapping();
			RoleSeriesReportMappingPK pk =new RoleSeriesReportMappingPK();
			pk.setRcId(rcId);
		    pk.setMsId(msId); 
			pk.setMsOrder(Long.parseLong(msOrder));
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
