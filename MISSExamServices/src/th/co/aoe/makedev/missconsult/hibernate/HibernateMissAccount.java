package th.co.aoe.makedev.missconsult.hibernate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissContact;
import th.co.aoe.makedev.missconsult.hibernate.bean.MissExam;
import th.co.aoe.makedev.missconsult.managers.MissAccountService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;

@Repository
@Transactional
public class HibernateMissAccount  extends HibernateCommon implements MissAccountService {
	private static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	 //2012-05-21 23:59:59.0
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	@Transactional(readOnly=true)
	public th.co.aoe.makedev.missconsult.xstream.MissAccount findMissAccountById(Long maId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		MissAccount missAccount = null;
		th.co.aoe.makedev.missconsult.xstream.MissAccount xntcCalendarReturn =null;
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createQuery(" select missAccount from MissAccount missAccount where missAccount.maId=:maId");
		query.setParameter("maId", maId);
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missAccount=(MissAccount)obj;
			xntcCalendarReturn= new th.co.aoe.makedev.missconsult.xstream.MissAccount();
			query=session.createQuery("select count(missCandidate) from MissCandidate missCandidate " +
					" where missCandidate.mcaStatus ='2' " +
					" and missCandidate.missAccount.maId ="+maId); 
			Object countObj=query.uniqueResult();  
			BeanUtils.copyProperties(missAccount,xntcCalendarReturn,new String[]{"missTheme","missIndustryMaster"});
			xntcCalendarReturn.setMaAvailableCandidate((Long)countObj);
			
			if(missAccount.getMissTheme()!=null && missAccount.getMissTheme().getMtId()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissTheme missTheme = new th.co.aoe.makedev.missconsult.xstream.MissTheme();						
				BeanUtils.copyProperties(missAccount.getMissTheme(),missTheme); 
				xntcCalendarReturn.setMissTheme(missTheme);
			}
			if(missAccount.getMissIndustryMaster()!=null && missAccount.getMissIndustryMaster().getMimId()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster missIndustryMaster = new th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster();						
				BeanUtils.copyProperties(missAccount.getMissIndustryMaster(),missIndustryMaster); 
				xntcCalendarReturn.setMissIndustryMaster(missIndustryMaster);
			}
		}
		
	  return xntcCalendarReturn;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor={RuntimeException.class})
	public Long saveMissAccount(MissAccount transientInstance)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session=sessionAnnotationFactory.getCurrentSession();
		Long returnId  = null;
		Query query =null;
		try{
			query=session.createQuery("select missAccount from MissAccount missAccount " +
					" where missAccount.maId =1 "
					); 
			Object objReturn=query.uniqueResult(); 	 
			if(objReturn!=null){
				MissAccount missAccount=(MissAccount)objReturn;
				transientInstance.setMaCustomizeHeadColor(missAccount.getMaCustomizeHeadColor());
				transientInstance.setMaCustomizeColor(missAccount.getMaCustomizeColor());				
				transientInstance.setMaCustomizePassMessage(missAccount.getMaCustomizePassMessage());
				transientInstance.setMaCustomizeRejectMessage(missAccount.getMaCustomizeRejectMessage());				
				transientInstance.setMaCustomizeRetestMessage(missAccount.getMaCustomizeRetestMessage());
				transientInstance.setMaLogo(missAccount.getMaLogo());
				transientInstance.setMissTheme(missAccount.getMissTheme());
				transientInstance.setMaBackgroundColor(missAccount.getMaBackgroundColor());
				transientInstance.setMaBackgroundPicture(missAccount.getMaBackgroundPicture());
			//	transientInstance.setMaCustomizeLogoFileName(missAccount.getMaCustomizeLogoFileName());
				transientInstance.setMaCustomizeLogoHotlink(missAccount.getMaCustomizeLogoHotlink());
				transientInstance.setMaCustomizeLogoPath(missAccount.getMaCustomizeLogoPath());
				transientInstance.setMaClearTest("12");
				transientInstance.setMaClearCandidate1("12");
				transientInstance.setMaClearCandidate2("12");
				transientInstance.setMaClearCandidate3("12");
			} 
			transientInstance.setMaGrade("1");
		/*	transientInstance.setMaCustomizeHeadColor("body.gif");
			transientInstance.setMaCustomizeColor("smoothness");
			transientInstance.setMaBackgroundColor("253,253,253");*/
			Object obj = session.save(transientInstance);
		 
			if(obj!=null){
				returnId =(Long) obj;
				query=session.createQuery("update MissAccount missAccount " +
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
	
	

	private int getSize(Session session, MissAccount instance,String maContactName,String[] meIds) throws Exception{
		try {
			 String me_id_in="";
				if(meIds!=null && meIds.length>0){
					me_id_in="(";
				 for (int i = 0; i < meIds.length; i++) {
						//MissContact contact=(MissContact)l.get(i); 
						if(i==(meIds.length-1)){
							me_id_in=me_id_in+""+meIds[i]+")";
						}else
							me_id_in=me_id_in+""+meIds[i]+",";
						
					}
				} 
			String maType=instance.getMaType();
			String maRegisterType = instance.getMaRegisterType();
			String maRegisterNo = instance.getMaRegisterNo();
			Timestamp maRegisterFrom = instance.getMaRegisterFrom();
			Timestamp maRegisterTo = instance.getMaRegisterTo();
			
			//String maContactName = instance.getMaContactName();
			String maPhone = instance.getMaPhone();
			String maName = instance.getMaName();
			Query query=null;
			String maId_in="";
			if(maContactName!=null && maContactName.length()>0){
				StringBuffer sb =new StringBuffer(" select missContact from MissContact missContact " +
						" where lcase(concat(concat(missContact.mcontactName,' '),missContact.mcontactLastname)) like '%"+maContactName.trim().toLowerCase()+"%' ");
						/*"where lcase(missContact.mcontactName) like '%"+maContactName.trim().toLowerCase()+"%' " +
						" or lcase(missContact.mcontactLastname) like '%"+maContactName.trim().toLowerCase()+"%' ");*/					 
				query=session.createQuery(sb.toString());
				List l = query.list();
				int size=l.size();
				if(size>0)
					maId_in="(";
				for (int i = 0; i < size; i++) {
					MissContact contact=(MissContact)l.get(i); 
					if(i==(size-1)){
						maId_in=maId_in+""+contact.getMcontactRef().intValue()+")";
					}else
						maId_in=maId_in+""+contact.getMcontactRef().intValue()+",";
					
				}
			}
			boolean iscriteria = false;
			//StringBuffer sb =new StringBuffer(" select count(missAccount) from MissAccount missAccount ");
			StringBuffer sb =new StringBuffer(" SELECT count(*) FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT missAccount " );
			if(meIds!=null && meIds.length>0){
				sb.append(" where  exists ( select account_map.ma_id from "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_SERIES_MAP account_map" +
						"  inner join  "+ServiceConstant.SCHEMA+".MISS_SERIES_MAP series_map on" +
						"  (account_map.ms_id=series_map.ms_id)" +
						" where missAccount.ma_id=account_map.ma_id and series_map.me_id in "+me_id_in+" )");
				iscriteria=true;
			}
			
			
			if(maId_in.length()>0){
				// sb.append(iscriteria?(" and missAccount.maId in "+maId_in):(" where missAccount.maId in "+maId_in+""));
				 sb.append(iscriteria?(" and missAccount.ma_id in "+maId_in):(" where missAccount.ma_id in "+maId_in+""));
				  iscriteria = true;
			}
			if(maType !=null && maType.length()> 0 ){  
				//criteria.add(Expression.eq("megId", megId));	
				// sb.append(iscriteria?(" and missAccount.maType='"+maType+"'"):(" where missAccount.maType='"+maType+"'"));
				 sb.append(iscriteria?(" and missAccount.ma_type='"+maType+"'"):(" where missAccount.ma_type='"+maType+"'"));
				  iscriteria = true;
			}
			  
			if(maRegisterType !=null && maRegisterType.length()> 0 && !maRegisterType.equals("-1")){  
				//criteria.add(Expression.eq("megId", megId));	
				 //sb.append(iscriteria?(" and missAccount.maRegisterType='"+maRegisterType+"'"):(" where missAccount.maRegisterType='"+maRegisterType+"'"));
				 sb.append(iscriteria?(" and missAccount.MA_REGISTER_TYPE='"+maRegisterType+"'"):(" where missAccount.MA_REGISTER_TYPE='"+maRegisterType+"'"));
				 
				 iscriteria = true;
			}
			if(maRegisterNo !=null && maRegisterNo.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
			//	sb.append(iscriteria?(" and lcase(missAccount.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
				sb.append(iscriteria?(" and lower(missAccount.MA_REGISTER_NO) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lower(missAccount.MA_REGISTER_NO) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
				
				iscriteria = true;
			} 
			if(maRegisterTo !=null && maRegisterFrom!=null){  
				//criteria.add(Expression.eq("megId", megId));	
			//	sb.append(iscriteria?(" and missAccount.maRegisterDate between '"+maRegisterFrom+"' and '"+format1.format(maRegisterTo.getTime())+" 23:59:59.0'"):(" where missAccount.maRegisterDate between '"+maRegisterFrom+"' and '"+format1.format(maRegisterTo.getTime())+"'"));
				sb.append(iscriteria?(" and missAccount.MA_REGISTER_DATE between '"+maRegisterFrom+"' and '"+format1.format(maRegisterTo.getTime())+" 23:59:59.0'"):(" where missAccount.MA_REGISTER_DATE between '"+maRegisterFrom+"' and '"+format1.format(maRegisterTo.getTime())+"'"));
				
				iscriteria = true;
				  //2012-05-21 23:59:59.0 
			}else if(maRegisterTo !=null && maRegisterFrom==null){ 
					//criteria.add(Expression.eq("megId", megId));	
				//	sb.append(iscriteria?(" and missAccount.maRegisterDate <=  '"+format1.format(maRegisterTo.getTime())+" 23:59:59.0'"):(" where missAccount.maRegisterDate  <= '"+format1.format(maRegisterTo.getTime())+"'"));
				sb.append(iscriteria?(" and missAccount.MA_REGISTER_DATE <=  '"+format1.format(maRegisterTo.getTime())+" 23:59:59.0'"):(" where missAccount.MA_REGISTER_DATE  <= '"+format1.format(maRegisterTo.getTime())+"'"));
					
					iscriteria = true; 
			}else if(maRegisterTo ==null && maRegisterFrom!=null){ 
				//criteria.add(Expression.eq("megId", megId));	
				//sb.append(iscriteria?(" and missAccount.maRegisterDate >=  '"+maRegisterFrom+"'"):(" where missAccount.maRegisterDate  >= '"+maRegisterFrom+"'"));
				sb.append(iscriteria?(" and missAccount.MA_REGISTER_DATE >=  '"+maRegisterFrom+"'"):(" where missAccount.MA_REGISTER_DATE  >= '"+maRegisterFrom+"'"));
				
				iscriteria = true; 
		    }
			 
			if(maPhone !=null && maPhone.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				//sb.append(iscriteria?(" and lcase(missAccount.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"));
				sb.append(iscriteria?(" and lower(missAccount.MA_PHONE) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lower(missAccount.MA_PHONE) like '%"+maPhone.trim().toLowerCase()+"%'"));
				
				iscriteria = true;
			}
			if(maName !=null && maName.trim().length() > 0){  
				//criteria.add(Expression.eq("megId", megId));	
				//sb.append(iscriteria?(" and lcase(missAccount.maName) like '%"+maName.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maName) like '%"+maName.trim().toLowerCase()+"%'"));
				sb.append(iscriteria?(" and lower(missAccount.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"):(" where lower(missAccount.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"));
				
				iscriteria = true;
			}
			 
		
			
			
			  //query =session.createQuery(sb.toString());
			  query =session.createSQLQuery(sb.toString());
			 
				// return ((Long)query.uniqueResult()).intValue();
			  return ((java.math.BigInteger)query.uniqueResult()).intValue();
			  
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
	 public List searchMissAccount(MissAccount instance,String maContactName,String[] meIds,Pagging pagging) throws DataAccessException {
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
			//System.out.println("meIds="+meIds);
			 String me_id_in="";
			if(meIds!=null && meIds.length>0){
				me_id_in="(";
			 for (int i = 0; i < meIds.length; i++) {
					//MissContact contact=(MissContact)l.get(i); 
					if(i==(meIds.length-1)){
						me_id_in=me_id_in+""+meIds[i]+")";
					}else
						me_id_in=me_id_in+""+meIds[i]+",";
					
				}
			}
		//	System.out.println("me_id_in="+me_id_in);
				String maType=instance.getMaType();
				String maRegisterType = instance.getMaRegisterType();
				String maRegisterNo = instance.getMaRegisterNo();
				Timestamp maRegisterFrom = instance.getMaRegisterFrom();
				Timestamp maRegisterTo = instance.getMaRegisterTo();
				String maPhone = instance.getMaPhone();
				String maName = instance.getMaName();
				Query query = null;
				String maId_in="";
				if(maContactName!=null && maContactName.length()>0){
					StringBuffer sb =new StringBuffer(" select missContact from MissContact missContact " +
							" where lcase(concat(concat(missContact.mcontactName,' '),missContact.mcontactLastname)) like '%"+maContactName.trim().toLowerCase()+"%' ");
							/*"where lcase(missContact.mcontactName) like '%"+maContactName.trim().toLowerCase()+"%' " +
							" or lcase(missContact.mcontactLastname) like '%"+maContactName.trim().toLowerCase()+"%' ");*/					 
					query=session.createQuery(sb.toString());
					List l = query.list();
					int size=l.size();
					if(size>0)
						maId_in="(";
					for (int i = 0; i < size; i++) {
						MissContact contact=(MissContact)l.get(i); 
						if(i==(size-1)){
							maId_in=maId_in+""+contact.getMcontactRef().intValue()+")";
						}else
							maId_in=maId_in+""+contact.getMcontactRef().intValue()+",";
						
					}
				}
				boolean iscriteria = false;
				//System.out.println("maId_in="+maId_in);
				 //session.createSQLQuery(queryStr);
				//StringBuffer sb =new StringBuffer(" select missAccount from MissAccount missAccount ");
				StringBuffer sb =new StringBuffer(" SELECT MA_ID , MA_NAME,MA_PHONE,MA_REGISTER_NO,MA_REGISTER_DATE,MA_TOTAL_UNIT,MA_USED_UNIT FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT missAccount " );
				if(meIds!=null && meIds.length>0){
					sb.append(" where  exists ( select account_map.ma_id from "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_SERIES_MAP account_map" +
							"  inner join  "+ServiceConstant.SCHEMA+".MISS_SERIES_MAP series_map on" +
							"  (account_map.ms_id=series_map.ms_id)" +
							" where missAccount.ma_id=account_map.ma_id and series_map.me_id in "+me_id_in+" )");
					iscriteria=true;
				}
						
				
			/*	sb.append(" where exists (from MissAccountSeriesMap account_map   " +
						" where   missAccount.ma_id=account_map.id.maId   )");
				 */
			/*	sb.append(" where exists ( select account_map.missAccount MissAccountSeriesMap account_map , MissSeriesMap  series_map " +
						" where account_map.id.msId=series_map.id.msId and missAccount.ma_id=account_map.id.maId and  series_map.id.meId=15 )");
				 */
				//if(meIds!=null && meIds.length>0){
				
				
				if(maId_in.length()>0){
					// sb.append(iscriteria?(" and missAccount.maId in "+maId_in):(" where missAccount.maId in "+maId_in+""));
					 sb.append(iscriteria?(" and missAccount.ma_id in "+maId_in):(" where missAccount.ma_id in "+maId_in+""));
					  iscriteria = true;
				}
				if(maType !=null && maType.length()> 0 ){  
					//criteria.add(Expression.eq("megId", megId));	
					// sb.append(iscriteria?(" and missAccount.maType='"+maType+"'"):(" where missAccount.maType='"+maType+"'"));
					 sb.append(iscriteria?(" and missAccount.ma_type='"+maType+"'"):(" where missAccount.ma_type='"+maType+"'"));
					  iscriteria = true;
				}
				  
				if(maRegisterType !=null && maRegisterType.length()> 0 && !maRegisterType.equals("-1")){  
					//criteria.add(Expression.eq("megId", megId));	
					 //sb.append(iscriteria?(" and missAccount.maRegisterType='"+maRegisterType+"'"):(" where missAccount.maRegisterType='"+maRegisterType+"'"));
					 sb.append(iscriteria?(" and missAccount.MA_REGISTER_TYPE='"+maRegisterType+"'"):(" where missAccount.MA_REGISTER_TYPE='"+maRegisterType+"'"));
					 
					 iscriteria = true;
				}
				if(maRegisterNo !=null && maRegisterNo.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
				//	sb.append(iscriteria?(" and lcase(missAccount.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maRegisterNo) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
					sb.append(iscriteria?(" and lower(missAccount.MA_REGISTER_NO) like '%"+maRegisterNo.trim().toLowerCase()+"%'"):(" where lower(missAccount.MA_REGISTER_NO) like '%"+maRegisterNo.trim().toLowerCase()+"%'"));
					
					iscriteria = true;
				} 
				if(maRegisterTo !=null && maRegisterFrom!=null){  
					//criteria.add(Expression.eq("megId", megId));	
				//	sb.append(iscriteria?(" and missAccount.maRegisterDate between '"+maRegisterFrom+"' and '"+format1.format(maRegisterTo.getTime())+" 23:59:59.0'"):(" where missAccount.maRegisterDate between '"+maRegisterFrom+"' and '"+format1.format(maRegisterTo.getTime())+"'"));
					sb.append(iscriteria?(" and missAccount.MA_REGISTER_DATE between '"+maRegisterFrom+"' and '"+format1.format(maRegisterTo.getTime())+" 23:59:59.0'"):(" where missAccount.MA_REGISTER_DATE between '"+maRegisterFrom+"' and '"+format1.format(maRegisterTo.getTime())+"'"));
					
					iscriteria = true;
					  //2012-05-21 23:59:59.0 
				}else if(maRegisterTo !=null && maRegisterFrom==null){ 
						//criteria.add(Expression.eq("megId", megId));	
					//	sb.append(iscriteria?(" and missAccount.maRegisterDate <=  '"+format1.format(maRegisterTo.getTime())+" 23:59:59.0'"):(" where missAccount.maRegisterDate  <= '"+format1.format(maRegisterTo.getTime())+"'"));
					sb.append(iscriteria?(" and missAccount.MA_REGISTER_DATE <=  '"+format1.format(maRegisterTo.getTime())+" 23:59:59.0'"):(" where missAccount.MA_REGISTER_DATE  <= '"+format1.format(maRegisterTo.getTime())+"'"));
						
						iscriteria = true; 
				}else if(maRegisterTo ==null && maRegisterFrom!=null){ 
					//criteria.add(Expression.eq("megId", megId));	
					//sb.append(iscriteria?(" and missAccount.maRegisterDate >=  '"+maRegisterFrom+"'"):(" where missAccount.maRegisterDate  >= '"+maRegisterFrom+"'"));
					sb.append(iscriteria?(" and missAccount.MA_REGISTER_DATE >=  '"+maRegisterFrom+"'"):(" where missAccount.MA_REGISTER_DATE  >= '"+maRegisterFrom+"'"));
					
					iscriteria = true; 
			    }
				 
				if(maPhone !=null && maPhone.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					//sb.append(iscriteria?(" and lcase(missAccount.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maPhone) like '%"+maPhone.trim().toLowerCase()+"%'"));
					sb.append(iscriteria?(" and lower(missAccount.MA_PHONE) like '%"+maPhone.trim().toLowerCase()+"%'"):(" where lower(missAccount.MA_PHONE) like '%"+maPhone.trim().toLowerCase()+"%'"));
					
					iscriteria = true;
				}
				if(maName !=null && maName.trim().length() > 0){  
					//criteria.add(Expression.eq("megId", megId));	
					//sb.append(iscriteria?(" and lcase(missAccount.maName) like '%"+maName.trim().toLowerCase()+"%'"):(" where lcase(missAccount.maName) like '%"+maName.trim().toLowerCase()+"%'"));
					sb.append(iscriteria?(" and lower(missAccount.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"):(" where lower(missAccount.MA_NAME) like '%"+maName.trim().toLowerCase()+"%'"));
					
					iscriteria = true;
				}
				if(pagging.getSortBy()!=null && pagging.getSortBy().length()>0){
						sb.append( " order by missAccount."+pagging.getOrderBy()+" "+pagging.getSortBy().toLowerCase());
				}			
				 //query =session.createQuery(sb.toString());
				query =session.createSQLQuery(sb.toString());
				//System.out.println(sb.toString());
				// set pagging.
				 String size = String.valueOf(getSize(session, instance,maContactName,meIds)); 
				 logger.debug(" first Result="+(pagging.getPageSize()* (pagging.getPageNo() - 1))); 
				 
				 query.setFirstResult(pagging.getPageSize() * (pagging.getPageNo() - 1));
				 query.setMaxResults(pagging.getPageSize());
				 
				 //List l = query.list();
				 List<Object[]> l = query.list();
				 List<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount> missAccounts =new ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount>(l.size());
				 for (Object[] objects : l) {
					 th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount missAccount=new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount();
						//th.co.aoe.makedev.missconsult.xstream.MissSery missSery=new th.co.aoe.makedev.missconsult.xstream.MissSery();
						//missSery.setMsId(Long.valueOf((java.lang.Integer)objects[0]));
					 
					 //MA_ID , MA_NAME,MA_PHONE,MA_REGISTER_NO,MA_REGISTER_DATE,MA_TOTAL_UNIT,MA_USED_UNIT FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT missAccount " );" +
					        if(objects[0]!=null)
							 missAccount.setMaId(Long.valueOf((java.lang.Integer)objects[0])); 
					        if(objects[1]!=null)
							 missAccount.setMaName((java.lang.String)objects[1]); 
					        if(objects[2]!=null)
							 missAccount.setMaPhone((java.lang.String)objects[2]);
					        if(objects[3]!=null)
							 missAccount.setMaRegisterNo((java.lang.String)objects[3]);
					        if(objects[4]!=null)
							 missAccount.setMaRegisterDate((java.sql.Timestamp)objects[4]);
					        if(objects[5]!=null)
							 missAccount.setMaTotalUnit(Long.valueOf((java.lang.Integer)objects[5]));
					        if(objects[6]!=null)
							 missAccount.setMaUsedUnit(Long.valueOf((java.lang.Integer)objects[6]));
					 missAccounts.add(missAccount);
					
				 }
				 //transList.add(l);
				 transList.add(missAccounts);
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
					" missAccount.maPhoneExt =:maPhoneExt ,  " +
					" missAccount.maFax =:maFax ,  " + 
					" missAccount.missIndustryMaster.mimId =:mimId ,  " + 
					" missAccount.maEmail =:maEmail,   " +
					" missAccount.maEmail2 =:maEmail2   " +
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
			query.setParameter("maPhoneExt", transientInstance.getMaPhoneExt());
			query.setParameter("maFax", transientInstance.getMaFax());
			query.setParameter("maEmail", transientInstance.getMaEmail());
			query.setParameter("maEmail2", transientInstance.getMaEmail2());
			query.setParameter("mimId", transientInstance.getMissIndustryMaster().getMimId());
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
					"  missAccount.maGrade =:maGrade, " +
					"  missAccount.maClearTest =:maClearTest, " +
					"  missAccount.maClearCandidate1 =:maClearCandidate1, " +
					"  missAccount.maClearCandidate2 =:maClearCandidate2, " +
					"  missAccount.maClearCandidate3 =:maClearCandidate3" +
					
					" where missAccount.maId ="+transientInstance.getMaId());
			query.setParameter("maCustomizePassMessage", transientInstance.getMaCustomizePassMessage());
			query.setParameter("maCustomizeRejectMessage", transientInstance.getMaCustomizeRejectMessage());
			query.setParameter("maCustomizeRetestMessage", transientInstance.getMaCustomizeRetestMessage());
			query.setParameter("maCustomizeColor", transientInstance.getMaCustomizeColor());
			query.setParameter("maCustomizeHeadColor", transientInstance.getMaCustomizeHeadColor());
			query.setParameter("maBackgroundColor", transientInstance.getMaBackgroundColor());
			query.setParameter("maGrade", transientInstance.getMaGrade());
			query.setParameter("maClearTest", transientInstance.getMaClearTest());
			query.setParameter("maClearCandidate1", transientInstance.getMaClearCandidate1());
			query.setParameter("maClearCandidate2", transientInstance.getMaClearCandidate2());
			query.setParameter("maClearCandidate3", transientInstance.getMaClearCandidate3());
			
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
	public th.co.aoe.makedev.missconsult.xstream.MissAccount refill(Long maId,Long refill) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = sessionAnnotationFactory.getCurrentSession();
		MissAccount missAccount = null;
		th.co.aoe.makedev.missconsult.xstream.MissAccount xntcCalendarReturn =null;
		Query query=session.createQuery(" select missAccount from MissAccount missAccount where missAccount.maId=:maId");
		query.setParameter("maId", maId);
		Long refill_add=0l;
		Object obj=query.uniqueResult(); 	 
		if(obj!=null){
			missAccount=(MissAccount)obj;
			xntcCalendarReturn= new th.co.aoe.makedev.missconsult.xstream.MissAccount();
			BeanUtils.copyProperties(missAccount,xntcCalendarReturn,new String[]{"missTheme","missIndustryMaster"});	
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
			xntcCalendarReturn.setMaTotalUnit(refill_add);
			query=session.createQuery("select count(missCandidate) from MissCandidate missCandidate " +
					" where missCandidate.mcaStatus ='2' " +
					" and missCandidate.missAccount.maId ="+maId); 
			Object countObj=query.uniqueResult();
			xntcCalendarReturn.setMaAvailableCandidate((Long)countObj); 
		} 
		return xntcCalendarReturn;
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
				 query=session.createQuery("select count(missCandidate) from MissCandidate missCandidate " +
							" where missCandidate.mcaStatus ='2' " +
							" and missCandidate.missSery.msId =" +missSery.getMsId()+
							" and missCandidate.missAccount.maId ="+maId); 
				 	Object countObj=query.uniqueResult(); 
				 	xmissSery.setMasmCandidateAvailable(((Long)countObj).intValue()+"");
				 xmissSery.setGroupStr(groupStr.toString());
				 xmissSeryList.add(xmissSery);
			}
			return xmissSeryList;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	 

}