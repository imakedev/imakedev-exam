/**
 * 
 */
package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesParticipantsMap;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesParticipantsMapPK;
import th.co.aoe.makedev.missconsult.managers.MissSeriesParticipantsMapService;

/**
 * @author OS -> root
 * @author -> IMake
 * @project -> MISSExamServices
 * @time -> May 27, 2013 10:52:27 AM  
 */

@Repository
@Transactional
public class HibernateMissSeriesParticipantsMap extends HibernateCommon implements
MissSeriesParticipantsMapService {
	private static final Logger logger = Logger
			.getLogger(ServiceConstant.LOG_APPENDER);
	private static final String[] ignored = { "id","missSery" };
	// private static final SecureRandom random = new SecureRandom();
	private SessionFactory sessionAnnotationFactory;

	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}

	public void setSessionAnnotationFactory(
			SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Override
	public List getMissSeriesParticipantsMap(Long msId,int numberParticipant)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		List  xmissSeriesParticipantsMapS=
					new ArrayList(numberParticipant);
		for(int i=1;i<=numberParticipant;i++){ 
		List<MissSeriesParticipantsMap>  missSeriesParticipantsMap_list= listObject(session, "select missSeriesParticipantsMap from MissSeriesParticipantsMap missSeriesParticipantsMap" +
				"  where missSeriesParticipantsMap.id.msId="+msId+" " +
				" and missSeriesParticipantsMap.id.mspmOrder=" +i+
				" order by missSeriesParticipantsMap.id.mspmOrder  ");
 
		
		th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap xmissSeriesParticipantsMap=new th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap();
		if(missSeriesParticipantsMap_list!=null && missSeriesParticipantsMap_list.size()>0){
			MissSeriesParticipantsMap missSeriesParticipantsMap= missSeriesParticipantsMap_list.get(0); 
			BeanUtils.copyProperties(missSeriesParticipantsMap, xmissSeriesParticipantsMap,ignored);
			xmissSeriesParticipantsMap.setMsId(missSeriesParticipantsMap.getId().getMsId());
			xmissSeriesParticipantsMap.setMspmGroupName(missSeriesParticipantsMap.getId().getMspmGroupName());
			xmissSeriesParticipantsMap.setMspmOrder(missSeriesParticipantsMap.getId().getMspmOrder());
			xmissSeriesParticipantsMap.setMspmGroupAmount(missSeriesParticipantsMap.getId().getMspmGroupAmount());
			if(missSeriesParticipantsMap.getMissSery()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissSery xmissSery=new th.co.aoe.makedev.missconsult.xstream.MissSery();
				BeanUtils.copyProperties(missSeriesParticipantsMap.getMissSery(), xmissSery);
				xmissSeriesParticipantsMap.setMissSery(xmissSery);
			}
		}  
		xmissSeriesParticipantsMapS.add(xmissSeriesParticipantsMap);
		}
		return xmissSeriesParticipantsMapS;
	
	}
	@Override
	public List listMissSeriesParticipantsMap(Long msId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		List  xmissSeriesParticipantsMapS=
					new ArrayList();
	//	for(int i=1;i<=numberParticipant;i++){ 
		List<MissSeriesParticipantsMap>  missSeriesParticipantsMap_list= listObject(session, "select missSeriesParticipantsMap from MissSeriesParticipantsMap missSeriesParticipantsMap" +
				"  where missSeriesParticipantsMap.id.msId="+msId+" " +
				//" and missSeriesParticipantsMap.id.mspmOrder=" +i+
				" order by missSeriesParticipantsMap.id.mspmOrder  ");
 
		for (MissSeriesParticipantsMap missSeriesParticipantsMap : missSeriesParticipantsMap_list) {
			th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap xmissSeriesParticipantsMap=new th.co.aoe.makedev.missconsult.xstream.MissSeriesParticipantsMap();
			//if(missSeriesParticipantsMap_list!=null && missSeriesParticipantsMap_list.size()>0){
			//	MissSeriesParticipantsMap missSeriesParticipantsMap= missSeriesParticipantsMap_list.get(0); 
				BeanUtils.copyProperties(missSeriesParticipantsMap, xmissSeriesParticipantsMap,ignored);
				xmissSeriesParticipantsMap.setMsId(missSeriesParticipantsMap.getId().getMsId());
				xmissSeriesParticipantsMap.setMspmGroupName(missSeriesParticipantsMap.getId().getMspmGroupName());
				xmissSeriesParticipantsMap.setMspmOrder(missSeriesParticipantsMap.getId().getMspmOrder());
				xmissSeriesParticipantsMap.setMspmGroupAmount(missSeriesParticipantsMap.getId().getMspmGroupAmount());
				if(missSeriesParticipantsMap.getMissSery()!=null){
					th.co.aoe.makedev.missconsult.xstream.MissSery xmissSery=new th.co.aoe.makedev.missconsult.xstream.MissSery();
					BeanUtils.copyProperties(missSeriesParticipantsMap.getMissSery(), xmissSery);
					xmissSeriesParticipantsMap.setMissSery(xmissSery);
				}
			//}  
			xmissSeriesParticipantsMapS.add(xmissSeriesParticipantsMap);
		}
		
		//}
		return xmissSeriesParticipantsMapS;
	
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	@Override
	public int deleteMissSeriesParticipantsMap(Long msId, Long mspmOrder)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession(); 
		Query query = session
				.createQuery(" delete MissSeriesParticipantsMap missSeriesParticipantsMap   "
						+ " where missSeriesParticipantsMap.id.msId=:msId "
						+ " and missSeriesParticipantsMap.id.mspmOrder=:mspmOrder " +
						" ");
		query.setParameter("msId", msId);
		query.setParameter("mspmOrder", mspmOrder); 
		query.executeUpdate();
		  query = session
				.createQuery(" select missSeriesParticipantsMap from MissSeriesParticipantsMap missSeriesParticipantsMap "
						+ " where missSeriesParticipantsMap.id.msId=:msId "
						+ " and missSeriesParticipantsMap.id.mspmOrder >  "+mspmOrder +
						" ");
		  query.setParameter("msId", msId); 
		
		// query.setParameter("mraHotlink", transientInstance.getMraHotlink()); 
		List<MissSeriesParticipantsMap> list = query.list();
		 // System.out.println("list size->"+list.size());
		 for (MissSeriesParticipantsMap missSeriesParticipantsMap : list) {
			 query = session
						.createQuery(" update MissSeriesParticipantsMap missSeriesParticipantsMap   "
								+ " set missSeriesParticipantsMap.id.msOrder="+(missSeriesParticipantsMap.getId().getMspmOrder()-1)  
								+ " where missSeriesParticipantsMap.id.msId=:msId "
								+ " and missSeriesParticipantsMap.id.mspmOrder="+mspmOrder +
								" ");
			 	query.setParameter("msId", missSeriesParticipantsMap.getId().getMsId());
				query.executeUpdate();
		}
		return 0;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
	@Override
	public int updateMissSeriesParticipantsMap(Long msId, Integer[] mspmOrder,
			Integer[] mspmGroupAmount, String[] mspmGroupName) {
		// TODO Auto-generated method stub
	
		try{
			Session session=sessionAnnotationFactory.getCurrentSession();
			//	Query query=session.createQuery(" select roleSeriesMapping from RoleSeriesMapping roleSeriesMapping where roleSeriesMapping.mmId=:mmId");
			Query query=session.createQuery("delete MissSeriesParticipantsMap missSeriesParticipantsMap where missSeriesParticipantsMap.id.msId ="+msId.intValue());
			int result = query.executeUpdate();
			if(mspmGroupName!=null && mspmGroupName.length>0){
				int index=0;
			for (String group : mspmGroupName) {
				MissSeriesParticipantsMap participantsMap =new MissSeriesParticipantsMap();
				MissSeriesParticipantsMapPK pk =new MissSeriesParticipantsMapPK();
				pk.setMsId(msId);
				pk.setMspmGroupAmount(Long.valueOf(mspmGroupAmount[index]));
				pk.setMspmGroupName(group);
				pk.setMspmOrder(Long.valueOf(mspmOrder[index]));
				participantsMap.setId(pk);
				session.save(participantsMap); 
				index++;
			}
			}
			//int canUpdate = 0;
			return result;
			}catch(Exception e){
				e.printStackTrace();
			}
			return 0;
			
			/*Session session = sessionAnnotationFactory.getCurrentSession(); 
		Query   query = session
						.createQuery(" update MissSeriesParticipantsMap missSeriesParticipantsMap   "
								+ " set missSeriesParticipantsMap.id.mspmGroupName=:mspmGroupName"  
								+ " where missSeriesParticipantsMap.id.msId=:msId "
								+ " and missSeriesParticipantsMap.id.mspmOrder="+mspmOrder
								+ " and missSeriesParticipantsMap.id.mspmGroupAmount=" +mspmGroupAmount+
								" ");
	 		query.setParameter("mspmGroupName", mspmGroupName);
			 	query.setParameter("msId", msId);
			 
				query.executeUpdate();
		return 0;*/
	}

}
