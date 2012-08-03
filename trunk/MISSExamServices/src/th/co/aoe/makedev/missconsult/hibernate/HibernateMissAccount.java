package th.co.aoe.makedev.missconsult.hibernate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import th.co.aoe.makedev.missconsult.hibernate.bean.MissExam;
import th.co.aoe.makedev.missconsult.managers.MissAccountService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissAccount  extends HibernateCommon implements MissAccountService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public MissAccount findMissAccountById(Long maId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissAccount missAccount = null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missAccount from MissAccount missAccount where missAccount.maId=:maId");
		query.setParameter("maId", maId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missAccount=(MissAccount)obj;
		}
	  return missAccount;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissAccount(MissAccount transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		try{
			transientInstance.setMaGrade("1");
			transientInstance.setMaCustomizeHeadColor("body.gif");
			transientInstance.setMaCustomizeColor("smoothness");
			transientInstance.setMaBackgroundColor("253,253,253");
			Object obj = session.save(transientInstance);
		
			if(obj!=null){
				returnId =(Long) obj;
				Query query=session.createQuery("update MissAccount missAccount " +
						" set missAccount.maRegisterNo =:maRegisterNo " +
						" where missAccount.maId ="+returnId);
				query.setParameter("maRegisterNo", "M000000"+returnId);
				query.executeUpdate();
			}
		} finally {
				if (session != null) {
					session = null;
				} 
		}
		return returnId; 
	}
	
	

	private int getSize(Session session, MissAccount instance) throws Exception{
		try {
			String maType=instance.getMaType();
			String maRegisterType = instance.getMaRegisterType();
			String maRegisterNo = instance.getMaRegisterNo();
			Timestamp maRegisterFrom = instance.getMaRegisterFrom();
			Timestamp maRegisterTo = instance.getMaRegisterTo();
			//String maContactName = instance.getMaContactName();
			String maPhone = instance.getMaPhone();
			String maName = instance.getMaName();
		
		
			StringBuffer sb =new StringBuffer(" select count(missAccount) from MissAccount missAccount ");
			
			boolean iscriteria = false;
			if(maType !=null && maType.length()> 0 ){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missAccount.maType='"+maType+"'"):(" where missAccount.maType='"+maType+"'"));
				  iscriteria = true;
			}
			if(maRegisterType !=null && maRegisterType.length()> 0 && !maRegisterType.equals("-1")){  
				//criteria.add(Expression.eq("megId", megId));	
				 sb.append(iscriteria?(" and missAccount.maRegisterType='"+maRegisterType+"'"):(" where missAccount.maRegisterType='"+maRegisterType+"'"));
				  iscriteria = true;
			}
			if(maRegisterNo !=null && maRegisterNo.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missAccount.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			/*if(maContactName !=null && maContactName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missAccount.maContactName) like '%"+maContactName.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maContactName) like '%"+maContactName.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}*/
			if(maPhone !=null && maPhone.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missAccount.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"));
				  iscriteria = true;
			}
			if(maName !=null && maName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				sb.append(iscriteria?(" and lcase(missAccount.maName) like '%"+maName.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maName) like '%"+maName.trim().toLowerCase()+"%'"));
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
	 public List searchMissAccount(MissAccount instance,Pagging pagging) throws DataAccessException {
			ArrayList  transList = new ArrayList ();
			Session session = sessionAnnotationFactory.getCurrentSession();
			try {
				/*private String maRegisterType;
				private String maRegisterNo;
				private String maRegisterFrom;
				private String maRegisterTo;
				private String maContactName;
				private String maDayTimePhone;
				private String maName;*/
				String maType=instance.getMaType();
				String maRegisterType = instance.getMaRegisterType();
				String maRegisterNo = instance.getMaRegisterNo();
				Timestamp maRegisterFrom = instance.getMaRegisterFrom();
				Timestamp maRegisterTo = instance.getMaRegisterTo();
				//String maContactName = instance.getMaContactName();
				String maPhone = instance.getMaPhone();
				String maName = instance.getMaName();
			
			
				StringBuffer sb =new StringBuffer(" select missAccount from MissAccount missAccount ");
				
				boolean iscriteria = false;
				if(maType !=null && maType.length()> 0 ){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missAccount.maType='"+maType+"'"):(" where missAccount.maType='"+maType+"'"));
					  iscriteria = true;
				}
				if(maRegisterType !=null && maRegisterType.length()> 0 && !maRegisterType.equals("-1")){  
					//criteria.add(Expression.eq("megId", megId));	
					 sb.append(iscriteria?(" and missAccount.maRegisterType='"+maRegisterType+"'"):(" where missAccount.maRegisterType='"+maRegisterType+"'"));
					  iscriteria = true;
				}
				if(maRegisterNo !=null && maRegisterNo.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missAccount.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				/*if(maContactName !=null && maContactName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missAccount.maContactName) like '%"+maContactName.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maContactName) like '%"+maContactName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}*/
				if(maPhone !=null && maPhone.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missAccount.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(maName !=null && maName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					sb.append(iscriteria?(" and lcase(missAccount.maName) like '%"+maName.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maName) like '%"+maName.trim().toLowerCase()+"%'"));
					  iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missAccount."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
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
			return transList;
		}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissAccount(MissAccount transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=null;
		//int result = query.executeUpdate();
		/*Query query = session.createQuery("update Stock set stockName = :stockName" +
				" where stockCode = :stockCode");
query.setParameter("stockName", "DIALOG1");
query.setParameter("stockCode", "7277");
int result = query.executeUpdate();*/
		/*if(section.equals("0") || section.equals("4") ){
			query=session.createQuery("update MissAccount missAccount " +
					" set missAccount.maPassword =:maPassword," +
					" missAccount.maUsername =:maUsername " +
					" where missAccount.maId ="+transientInstance.getMaId());
			query.setParameter("maPassword", transientInstance.getMaPassword());
			query.setParameter("maUsername", transientInstance.getMaUsername());
			return query.executeUpdate();
			
		}else*/if(section.equals("1") || section.equals("5")){
			query=session.createQuery("update MissAccount missAccount " +
					" set missAccount.maName =:maName ,  " +
					" missAccount.maAddress =:maAddress ,  " +
					" missAccount.maPhone =:maPhone ,  " +
					" missAccount.maFax =:maFax ,  " + 
					" missAccount.maEmail =:maEmail   " +
				//	" missAccount.maContactName =:maContactName ,  " +					
				/*	" missAccount.maContactLastname =:maContactLastname ,  " +
					" missAccount.maContactGender =:maContactGender ,  " + 
					" missAccount.maContactBirthDate =:maContactBirthDate ,  " + 
					" missAccount.maContactTitle =:maContactTitle ,  " +
					" missAccount.maContactDepartment =:maContactDepartment ,  " +
					" missAccount.maContactPhone =:maContactPhone ,  " +
					" missAccount.maContactFax =:maContactFax ,  " +
					" missAccount.maContactEmail =:maContactEmail  " +*/
					" where missAccount.maId ="+transientInstance.getMaId());
			query.setParameter("maName", transientInstance.getMaName());
			query.setParameter("maAddress", transientInstance.getMaAddress());
			query.setParameter("maPhone", transientInstance.getMaPhone());
			query.setParameter("maFax", transientInstance.getMaFax());
			query.setParameter("maEmail", transientInstance.getMaEmail());
			/*query.setParameter("maContactName", transientInstance.getMaContactName());
			query.setParameter("maContactLastname", transientInstance.getMaContactLastname());
			query.setParameter("maContactGender", transientInstance.getMaContactGender());
			query.setParameter("maContactBirthDate", transientInstance.getMaContactBirthDate());  
			query.setParameter("maContactTitle", transientInstance.getMaContactTitle());
			query.setParameter("maContactDepartment", transientInstance.getMaContactDepartment());
			query.setParameter("maContactPhone", transientInstance.getMaContactPhone());
			query.setParameter("maContactFax", transientInstance.getMaContactFax());
			query.setParameter("maContactEmail", transientInstance.getMaContactEmail());*/
			return query.executeUpdate();
		}else if(section.equals("4")||section.equals("9")  ){
			query=session.createQuery("update MissAccount missAccount " +
					" set missAccount.maCustomizePassMessage =:maCustomizePassMessage ," +
					"  missAccount.maCustomizeRejectMessage =:maCustomizeRejectMessage , " +
					"  missAccount.maCustomizeRetestMessage =:maCustomizeRetestMessage, " +
					"  missAccount.maCustomizeColor =:maCustomizeColor, " +
					"  missAccount.maCustomizeHeadColor =:maCustomizeHeadColor, " +
					"  missAccount.maBackgroundColor =:maBackgroundColor ," +
					"  missAccount.maGrade =:maGrade " +
					
					" where missAccount.maId ="+transientInstance.getMaId());
			query.setParameter("maCustomizePassMessage", transientInstance.getMaCustomizePassMessage());
			query.setParameter("maCustomizeRejectMessage", transientInstance.getMaCustomizeRejectMessage());
			query.setParameter("maCustomizeRetestMessage", transientInstance.getMaCustomizeRetestMessage());
			query.setParameter("maCustomizeColor", transientInstance.getMaCustomizeColor());
			query.setParameter("maCustomizeHeadColor", transientInstance.getMaCustomizeHeadColor());
			query.setParameter("maBackgroundColor", transientInstance.getMaBackgroundColor());
			query.setParameter("maGrade", transientInstance.getMaGrade());
			
			return query.executeUpdate();
		}
		return 0;
		/*Query query=session.createQuery("delete MissSeriesMap missSeriesMap where missSeriesMap.id.msId ="+msId.intValue());
		int result = query.executeUpdate();*/
		//return update(sessionAnnotationFactory.getCurrentSession(), transientInstance);
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int updateMissAccountLogo(MissAccount transientInstance,String section)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		Query query=null;
			query=session.createQuery("update MissAccount missAccount " +
					" set missAccount.maCustomizeLogoPath =:maCustomizeLogoPath," +
					" missAccount.maCustomizeLogoFileName =:maCustomizeLogoFileName ," +
					" missAccount.maCustomizeLogoHotlink =:maCustomizeLogoHotlink " +
					" where missAccount.maId ="+transientInstance.getMaId());
			query.setParameter("maCustomizeLogoPath", transientInstance.getMaCustomizeLogoPath());
			query.setParameter("maCustomizeLogoFileName", transientInstance.getMaCustomizeLogoFileName());
			query.setParameter("maCustomizeLogoHotlink", transientInstance.getMaCustomizeLogoHotlink());
			return query.executeUpdate();
	}
	@Override
	public MissAccount refill(Long maId,Long refill) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		MissAccount missAccount = null;
		Query query=session.createQuery(" select missAccount from MissAccount missAccount where missAccount.maId=:maId");
		query.setParameter("maId", maId);
		Long refill_add=0l;
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missAccount=(MissAccount)obj;
		}
		if(missAccount!=null){
			Long total=missAccount.getMaTotalUnit()!=null?missAccount.getMaTotalUnit():0l;
			logger.debug(" total===>"+total);
			logger.debug(" refill===>"+refill);
			refill_add=total+refill;
			logger.debug(" refill_add===>"+refill_add);
			
			query=session.createQuery("update MissAccount missAccount " +
					" set missAccount.maTotalUnit =:maTotalUnit" +
					" where missAccount.maId ="+maId);
			query.setParameter("maTotalUnit", refill_add);
			query.executeUpdate();
			missAccount.setMaTotalUnit(refill_add);
		} 
		return missAccount;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public int deleteMissAccount(MissAccount persistentInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return delete(sessionAnnotationFactory.getCurrentSession(), persistentInstance);
	}
	@Override
	public List<th.co.aoe.makedev.missconsult.xstream.MissSery> listMissAccountSeriesMapByMaId(Long maId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> xmissAccountSeriesMapList=null;
		List<th.co.aoe.makedev.missconsult.xstream.MissSery> xmissSeryList=null;
		try { 
			StringBuffer sb =new StringBuffer();
		
			 sb.append(" select missAccountSeriesMap from MissAccountSeriesMap missAccountSeriesMap where missAccountSeriesMap.id.maId="+maId);
			Query query=session.createQuery(sb.toString());
			//query.setParameter("maId", maId);
			Map  map= null;
			List<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap> missAccountSeriesMapList = query.list();
			if(missAccountSeriesMapList!=null && missAccountSeriesMapList.size()>0){
				map=new HashMap(missAccountSeriesMapList.size());
				   xmissAccountSeriesMapList=new 
						 ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap>(missAccountSeriesMapList.size());
				 
				for (th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountSeriesMap missAccountSeriesMap : missAccountSeriesMapList) {
					th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap xmissAccountSeriesMap =new 
							th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap();
					xmissAccountSeriesMap.setMaId(maId);
					xmissAccountSeriesMap.setMsId(missAccountSeriesMap.getId().getMsId());
					xmissAccountSeriesMap.setMasmAvailable(missAccountSeriesMap.getMasmAvailable());
					xmissAccountSeriesMap.setMasmExpire(missAccountSeriesMap.getMasmExpire());
					xmissAccountSeriesMap.setMasmOrderUnit(missAccountSeriesMap.getMasmOrderUnit());
					xmissAccountSeriesMap.setMasmStatus(missAccountSeriesMap.getMasmStatus());
					
					/* query=session.createQuery("select missSeriesMap from MissSeriesMap missSeriesMap where missSeriesMap.id.msId="+missAccountSeriesMap.getId().getMsId().intValue());
					 List<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap> missSeriesMapList = query.list();
					 query =session.createQuery("select missSery from MissSery missSery where missSery.msId="+missAccountSeriesMap.getId().getMsId().intValue());
					 Object obj=query.uniqueResult();
					 if(obj!=null){
						 th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery =(th.co.aoe.makedev.missconsult.hibernate.bean.MissSery)obj;
						 xmissAccountSeriesMap.setSeryName(missSery.getMsSeriesName());
						 xmissAccountSeriesMap.setSeryUnit(missSery.getMsUnitCost().toString());
					 }
					 StringBuffer groupStr=new StringBuffer("");
					 for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap missSeriesMap : missSeriesMapList) {
						 query =session.createQuery("select missExam from MissExam missExam where missExam.meId="+missSeriesMap.getId().getMeId().intValue());
						 List<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam> missExamList = query.list();
						 if(missExamList!=null && missExamList.size()>0){
							 groupStr.append(missExamList.get(0).getMissExamGroup().getMegName()+" ");
						 }
					}
					 xmissAccountSeriesMap.setGroupStr(groupStr.toString());*/					 
					 xmissAccountSeriesMapList.add(xmissAccountSeriesMap);
					 map.put(maId+"_"+missAccountSeriesMap.getId().getMsId(), xmissAccountSeriesMap);
				}
			}
			sb.setLength(0);
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
					 		"where missMap.id.meId=missExam.meId  and  missMap.id.msId= "+missSery.getMsId());
					List<MissExam> missExams= (List<MissExam>)query.list();
					 String str="";
						for (th.co.aoe.makedev.missconsult.hibernate.bean.MissExam missExam : missExams) {
							str=str+missExam.getMeName()+" ";
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
				/* query =session.createQuery("select missSery from MissSery missSery where missSery.msId="+missSery.getMsId().intValue());
				 Object obj=query.uniqueResult();
				 if(obj!=null){
					 th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery =(th.co.aoe.makedev.missconsult.hibernate.bean.MissSery)obj;
					 xmissAccountSeriesMap.setSeryName(missSery.getMsSeriesName());
					 xmissAccountSeriesMap.setSeryUnit(missSery.getMsUnitCost().toString());
				 }*/
				 StringBuffer groupStr=new StringBuffer("");
				 for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeriesMap missSeriesMap : missSeriesMapList) {
					 query =session.createQuery("select missExam from MissExam missExam where missExam.meId="+missSeriesMap.getId().getMeId().intValue());
					 List<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam> missExamList = query.list();
					 if(missExamList!=null && missExamList.size()>0){
						 groupStr.append(missExamList.get(0).getMissExamGroup().getMegName()+" ");
					 }
				}
				 xmissSery.setGroupStr(groupStr.toString());
				 xmissSeryList.add(xmissSery);
			}
			//return xmissAccountSeriesMapList;
			//System.out.println(xmissSeryList);
			return xmissSeryList;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	 

}