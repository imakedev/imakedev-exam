package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMapPK;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrderPK;
import th.co.aoe.makedev.missconsult.managers.MissAccountSeriesMapService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
@Repository
@Transactional
public class HibernateMissAccountSeriesMap  extends HibernateCommon implements MissAccountSeriesMapService {

	//private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	//public static final ResourceBundle bundle;
	/*public static String SCHEMA="";
	static{
		bundle =  ResourceBundle.getBundle( "jdbc" );	
		SCHEMA=bundle.getString("schema");
	}*/
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
		//d
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
		System.out.println("available->"+available+",orderUnit->"+orderUnit);
		Long returnId=0l;
		if(orderUnit<=available){
		sb.setLength(0); 
		sb.append(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap" +
				" where missAccountSeriesMap.id.maId=:maId and missAccountSeriesMap.id.msId=:msId ");
	
		query =session.createQuery(sb.toString());
		query.setParameter("maId", maId);
		query.setParameter("msId", msId);
		@SuppressWarnings("rawtypes")
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
			MissSeryOrder order=new MissSeryOrder();
			MissSeryOrderPK pk =new MissSeryOrderPK();
			pk.setMaId(maId);
			pk.setMsId(msId);
			java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
			DateTime datetime=new DateTime(timeStampStartDate.getTime());
			order.setMsoWeek(Long.valueOf(datetime.weekOfWeekyear().get()));
			pk.setMsoDateTime(timeStampStartDate);
			order.setId(pk);
			order.setMsoAmount(orderUnit);
			session.save(order);
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
	
	
/*
	private int getSize(Session session, MissAccountSeriesMap instance) throws Exception{
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
	 @SuppressWarnings({ "rawtypes" })
	 @Transactional(readOnly=true)
	 public List searchMissAccountSeriesMap(MissAccountSeriesMap instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			 
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
	@Override
	public List<th.co.aoe.makedev.missconsult.xstream.MissSery> listMissAccountSeriesMapByMaId(Long maId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		
		//List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> xmissAccountSeriesMapList=null;
		List<th.co.aoe.makedev.missconsult.xstream.MissSery> xmissSeryList=null;
		try { 
			StringBuffer sb =new StringBuffer();
		
			 sb.append(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap where missAccountSeriesMap.id.maId="+maId);
			Query query=session.createQuery(sb.toString());
			//query.setParameter("maId", maId);
			//@SuppressWarnings("rawtypes")
			//Map  map= null;
			@SuppressWarnings("unchecked")
			List<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap> missAccountSeriesMapList = query.list();
			if(missAccountSeriesMapList!=null && missAccountSeriesMapList.size()>0){
			//	map=new HashMap(missAccountSeriesMapList.size());
				xmissSeryList=new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>(missAccountSeriesMapList.size());
				  /* xmissAccountSeriesMapList=new 
						 ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap>(missAccountSeriesMapList.size());
				 */
				for (th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap missAccountSeriesMap : missAccountSeriesMapList) {
					th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap xmissAccountSeriesMap =new 
							th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap();
					
					xmissAccountSeriesMap.setMaId(maId);
					xmissAccountSeriesMap.setMsId(missAccountSeriesMap.getId().getMsId());
					xmissAccountSeriesMap.setMasmAvailable(missAccountSeriesMap.getMasmAvailable());
					xmissAccountSeriesMap.setMasmExpire(missAccountSeriesMap.getMasmExpire());
					xmissAccountSeriesMap.setMasmOrderUnit(missAccountSeriesMap.getMasmOrderUnit());
					xmissAccountSeriesMap.setMasmStatus(missAccountSeriesMap.getMasmStatus()); 
					
				/*	 query=session.createQuery("select missSeriesMap from MissSeriesMap missSeriesMap where missSeriesMap.id.msId="+missAccountSeriesMap.getId().getMsId().intValue());
					 List<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap> missSeriesMapList = query.list();*/
					 query =session.createQuery("select missSery from MissSery missSery where missSery.msId="+missAccountSeriesMap.getId().getMsId().intValue());
					 Object obj=query.uniqueResult();
					 th.co.aoe.makedev.missconsult.xstream.MissSery xmissSery =new th.co.aoe.makedev.missconsult.xstream.MissSery ();
					 if(obj!=null){
						 th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery =(th.co.aoe.makedev.missconsult.hibernate.bean.MissSery)obj;
						/* xmissAccountSeriesMap.setSeryName(missSery.getMsSeriesName());
						 xmissAccountSeriesMap.setSeryUnit(missSery.getMsUnitCost().toString());*/
						 xmissSery.setMsId(missSery.getMsId());
						 xmissSery.setMsSeriesName(missSery.getMsSeriesName());
					 }
					 
					 xmissSeryList.add(xmissSery);
				}
			}
			/*sb.setLength(0);
			sb.append(" select missSery from MissSery missSery");
			query=session.createQuery(sb.toString());
			//query.setParameter("maId", maId);
			List<th.co.aoe.makedev.missconsult.hibernate.bean.MissSery> missSerylist = query.list();
			xmissSeryList=new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSery>(missSerylist.size());
			for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery : missSerylist) {
				 th.co.aoe.makedev.missconsult.xstream.MissSery xmissSery =new th.co.aoe.makedev.missconsult.xstream.MissSery ();
				 xmissSery.setMsId(missSery.getMsId());
				 xmissSery.setMsSeriesName(missSery.getMsSeriesName());
				 xmissSery.setMsUnitCost(missSery.getMsUnitCost());
				    query =session.createQuery("select missExam from MissSeriesMap missMap, MissExam missExam " +
					 		"where missMap.id.meId=missExam.meId  and  missMap.id.msId= "+missSery.getMsId()+
				    		" order by missMap.msmOrder asc ");
				    List<MissExam> missExams= (List<MissExam>)query.list();
					 
					String str="";
					 int index=0;
					 int missExamsSize=missExams.size();
						for (th.co.aoe.makedev.missconsult.hibernate.bean.MissExam missExam : missExams) {
							str=str+((index==(missExamsSize-1))?missExam.getMeName():missExam.getMeName()+" , ");
							index++;
						}
				 xmissSery.setTestStr(str);
				 if(map!=null){
					 if(map.containsKey(maId+"_"+missSery.getMsId())){
						 th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap value=(th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap)map.get(maId+"_"+missSery.getMsId());
						 if(value.getMasmAvailable()!=null && value.getMasmAvailable().length()>0){
							 xmissSery.setMasmAvailable(value.getMasmAvailable());	 
						 }else{
							 xmissSery.setMasmAvailable("0");	
						 }
					 }
				 }
				 query=session.createQuery("select missSeriesMap from MissSeriesMap missSeriesMap where missSeriesMap.id.msId="+missSery.getMsId().intValue());
				 List<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap> missSeriesMapList = query.list();
				 StringBuffer groupStr=new StringBuffer("");
				 for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap missSeriesMap : missSeriesMapList) {
					 query =session.createQuery("select missExam from MissExam missExam where missExam.meId="+missSeriesMap.getId().getMeId().intValue());
					 List<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam> missExamList = query.list();
					 if(missExamList!=null && missExamList.size()>0){
						 groupStr.append(missExamList.get(0).getMissExamGroup().getMegName()+" ");
					 }
				}
				 query=session.createQuery("select count(missCandidate) from MissCandidate missCandidate " +
							" where missCandidate.mcaStatus ='2' " +
							" and missCandidate.missSery.msId =" +missSery.getMsId()+
							" and missCandidate.missAccount.maId ="+maId); 
				 	Object countObj=query.uniqueResult(); 
				 	xmissSery.setMasmCandidateAvailable(((Long)countObj).intValue()+"");
				 xmissSery.setGroupStr(groupStr.toString());
				 xmissSeryList.add(xmissSery);
			}*/
			return xmissSeryList;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> listMissAccountSeriesMapByRole(Long maId,
			Long rcId) throws DataAccessException {
		// TODO Auto-generated method stub
		/*SELECT ac_map.ms_id,sery.ms_series_name FROM MISS_CONSULT_EXAM.MISS_ACCOUNT_SERIES_MAP ac_map inner join 
		MISS_CONSULT_EXAM.MISS_SERIES sery on ac_map.ms_id=sery.ms_id
		where  not exists (
		SELECT s_map.ms_id FROM MISS_CONSULT_EXAM.role_series_mapping  s_map 
		 where ac_map.ms_id=s_map.ms_id  and  s_map.rc_id=2
		) and ma_id=10*/
		//EPTNormReport eptNormReport =new EPTNormReport();
		Session session=sessionAnnotationFactory.getCurrentSession();
		// SELECT * FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT where ma_type=0
		String queryStr="SELECT sery.ms_id,sery.ms_series_name FROM "+ServiceConstant.SCHEMA+".MISS_SERIES sery "; 
		if(maId.intValue()!=1){
			queryStr="SELECT ac_map.MS_ID,sery.MS_SERIES_NAME FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_SERIES_MAP ac_map inner join " +
					" "+ServiceConstant.SCHEMA+".MISS_SERIES sery on ac_map.ms_id=sery.ms_id 	where  not exists (" +
					"	SELECT s_map.ms_id FROM "+ServiceConstant.SCHEMA+".role_series_mapping  s_map" +
					" where ac_map.ms_id=s_map.ms_id  and  s_map.rc_id="+rcId.intValue()+") and ma_id="+maId.intValue()+
					"";
		}
		Query query=session.createSQLQuery(queryStr);
		

		 @SuppressWarnings("unchecked")
		List<Object[]> list=query.list();
		//List<List<String>> results=new ArrayList<List<String>>(list.size());
		List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> missAccountSeriesMaps =new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap>(list.size()); 
	try{
		for (Object[] objects : list) {
		//	List<String> strings =new ArrayList<String>(objects.length);
		//	for (int i = 0; i < objects.length; i++) {
				th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap missAccountSeriesMap=new th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap();
				th.co.aoe.makedev.missconsult.xstream.MissSery missSery=new th.co.aoe.makedev.missconsult.xstream.MissSery();
				missSery.setMsId(Long.valueOf((java.lang.Integer)objects[0]));
				missSery.setMsSeriesName((java.lang.String)objects[1]);
				missAccountSeriesMap.setMissSery(missSery);
				missAccountSeriesMap.setMsId(Long.valueOf((java.lang.Integer)objects[0]));
				 /*if(objects[i] instanceof java.lang.String){ 
					strings.add((String)objects[i]);
				 } else if(objects[i] instanceof java.lang.Integer){ 
						strings.add((java.lang.Integer)objects[i]+"");
				}else if(objects[i] instanceof java.math.BigInteger){ 
						strings.add((java.math.BigInteger)objects[i]+"");
				} else if(objects[i] instanceof java.math.BigDecimal){ 
					strings.add(((java.math.BigDecimal)objects[i]+""));
				}	*/				
			//}
			missAccountSeriesMaps.add(missAccountSeriesMap);
		}
		//eptNormReport.setCompanyList(results);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
			if (session != null) {
				session = null;
			} 
	} 
	return missAccountSeriesMaps;
	
	
	}
}
