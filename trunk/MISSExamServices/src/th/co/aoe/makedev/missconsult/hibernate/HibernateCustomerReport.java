package th.co.aoe.makedev.missconsult.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.CustomerReportService;
import th.co.aoe.makedev.missconsult.xstream.CustomerReport;

@Repository
@Transactional
public class HibernateCustomerReport  extends HibernateCommon implements CustomerReportService {

	//private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	//private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private SessionFactory sessionAnnotationFactory;
	
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	private List<List<String>> getResult(Session session,StringBuffer sb){
		Query query =session.createSQLQuery(sb.toString());
		@SuppressWarnings("unchecked")
		
		List<Object[]> list=query.list();
		List<List<String>> results=new ArrayList<List<String>>(list.size());
		for (Object[] objects : list) {
			List<String> strings =new ArrayList<String>(objects.length);
			for (int i = 0; i < objects.length; i++) {
			 if(objects[i]==null)
				 strings.add(null);
			 else if(objects[i] instanceof java.lang.String){
				strings.add((String)objects[i]);
			 } else if(objects[i] instanceof java.lang.Integer){ 
					strings.add((java.lang.Integer)objects[i]+"");
			}else if(objects[i] instanceof java.math.BigInteger){
					strings.add((java.math.BigInteger)objects[i]+"");
			} else if(objects[i] instanceof java.math.BigDecimal){
				strings.add(((java.math.BigDecimal)objects[i]+""));
			}else if(objects[i] instanceof java.sql.Timestamp){
				strings.add(format2.format((java.sql.Timestamp)objects[i])); 
			}
		  }
			results.add(strings);
		}
		return results;
	}
	@Override
	public CustomerReport findCustomerReport(String mode, String magId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		CustomerReport customerReport =new CustomerReport();;
		Session session=sessionAnnotationFactory.getCurrentSession();
		try{
			StringBuffer sb =new StringBuffer();
			// get 2b
			sb.setLength(0);  
			sb.append("SELECT count(account.MIM_ID),truncate(((count(account.MIM_ID)*100)/( select count(*)" +
					"  FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_MAPPING mapping where mapping.mag_id="+magId+"))+0.006,2) as _percent ," +
					" (select mim_name from "+ServiceConstant.SCHEMA+".MISS_INDUSTRY_MASTER industry " +
					" where industry.mim_id=account.mim_id ) as industry FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_MAPPING map left join " +
					" "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on map.ma_id =account.ma_id where map.mag_id="+magId+"" +
					" and account.mim_id is not null  group by account.MIM_ID "); 
			customerReport.setIndustryPercent(getResult(session,sb));
		   
			// get 2c
			sb.setLength(0);  
			sb.append("SELECT account.ma_name, account.ma_total_unit ,account.ma_used_unit," +
					" (account.ma_total_unit-account.ma_used_unit) as available " +
					"FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_MAPPING map left join " +
					" "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on map.ma_id =account.ma_id where map.mag_id="+magId+"");
			customerReport.setOrderStat(getResult(session,sb));
			
			// get 2d
			sb.setLength(0);  
			sb.append("SELECT count(account.ma_id) ,account.ma_id,account.ma_name" +
					" FROM "+ServiceConstant.SCHEMA+".MISS_SYSTEM_USE system_used left join "+ServiceConstant.SCHEMA+".MISS_CONTACT contact" +
					" on system_used.msystem_user_id =contact.mcontact_username left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account" +
					" on contact.mcontact_ref=account.ma_id left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_MAPPING map on account.ma_id=map.ma_id" +
					" where map.mag_id="+magId+" group by account.ma_id ");
			customerReport.setUsedStat(getResult(session,sb));
			
			// get 2e
			sb.setLength(0);  
		sb.append("select map_outer.mag_id ,account_outer.ma_id,account_outer.ma_name ,( SELECT system_used.msystem_date_time_login" +
					" FROM "+ServiceConstant.SCHEMA+".MISS_SYSTEM_USE system_used left join "+ServiceConstant.SCHEMA+".MISS_CONTACT contact" +
					" on system_used.msystem_user_id =contact.mcontact_username left join  "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account" +
					" on contact.mcontact_ref=account.ma_id left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_MAPPING map on account.ma_id=map.ma_id" +
					" where map.mag_id=map_outer.mag_id  and map.ma_id=map_outer.ma_id order by msystem_date_time_login desc limit 1 ) as xx ," +
					"(select count(*) from "+ServiceConstant.SCHEMA+".MISS_SYSTEM_USE used left join "+ServiceConstant.SCHEMA+".MISS_CONTACT contact_inner" +
					" on used.msystem_user_id =contact_inner.mcontact_username left join  "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account_inner" +
					" on contact_inner.mcontact_ref=account_inner.ma_id where account_inner.ma_id=map_outer.ma_id ) as count " +
					" from "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_MAPPING map_outer left join" +
					" "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account_outer on map_outer.ma_id=account_outer.ma_id where map_outer.mag_id="+magId+"");
		customerReport.setLastLogin(getResult(session,sb));
			
			// get 2h
			sb.setLength(0);  
			sb.append("SELECT account.ma_name ,account.ma_total_unit ,account.ma_used_unit," +
					" (account.ma_total_unit-account.ma_used_unit) as available FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_MAPPING map left join" +
					" "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on map.ma_id =account.ma_id where map.mag_id="+magId+" and ma_used_unit=0 ");
			customerReport.setDeadstock(getResult(session, sb)); 
		
		 }catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
				if (session != null) {
					session = null;
				} 
		}
	  return customerReport;
	
	}
	@Override
	public CustomerReport findGroups(String sql) throws DataAccessException {
		// TODO Auto-generated method stub
		CustomerReport customerReport =new CustomerReport();
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createSQLQuery("SELECT * FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_GROUP ");
		//Query query =session.createSQLQuery(sb.toString()); 
		List<Object[]> list=query.list();
		List<List<String>> results=new ArrayList<List<String>>(list.size());
	try{
		for (Object[] objects : list) {
			List<String> strings =new ArrayList<String>(objects.length);
			for (int i = 0; i < objects.length; i++) {
				 if(objects[i] instanceof java.lang.String){ 
					strings.add((String)objects[i]);
				 } else if(objects[i] instanceof java.lang.Integer){ 
						strings.add((java.lang.Integer)objects[i]+"");
				}else if(objects[i] instanceof java.math.BigInteger){ 
						strings.add((java.math.BigInteger)objects[i]+"");
				} else if(objects[i] instanceof java.math.BigDecimal){ 
					strings.add(((java.math.BigDecimal)objects[i]+""));
				}					
			}
		  results.add(strings);
		}
		customerReport.setGroupList(results);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
			if (session != null) {
				session = null;
			} 
	} 
	return customerReport;
	}

}
