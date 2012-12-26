package th.co.aoe.makedev.missconsult.hibernate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.hibernate.bean.Xls;
import th.co.aoe.makedev.missconsult.managers.ConsultantReportService;
import th.co.aoe.makedev.missconsult.xstream.ConsultantReport;

@Repository
@Transactional
public class HibernateConsultantReport  extends HibernateCommon implements ConsultantReportService {

	/*private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");*/
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
			 }else if(objects[i] instanceof java.lang.Integer){ 
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
	/*private List<String> getResult(Session session,StringBuffer sb,int index){
		Query query =session.createSQLQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list=query.list();
		List<String> results =new ArrayList<String>(list.size());
		for (Object[] objects : list) {
			results.add((String)objects[index]);
		}
		return results;
	}
	private String getResultUniqueResult(Session session,StringBuffer sb,int index){
		Query query =session.createSQLQuery(sb.toString()); 
		Object obj=query.uniqueResult();
		if(obj!=null){
			Object[] obj_values=(Object[])obj;
			return ((String)obj_values[index]);
		}
		return ""; 
	}*/
	@Override
	public ConsultantReport findConsultantReport(String mode, String mcontactId,String month,String year)
			throws DataAccessException {
		// TODO Auto-generated method stub
		ConsultantReport consultantReport =new ConsultantReport();;
		Session session=sessionAnnotationFactory.getCurrentSession();
		try{
			StringBuffer sb =new StringBuffer();
			if(mode.equals(ServiceConstant.MANAGE_REPORT_MODE_ALL)){
			// get 5a
			sb.setLength(0);  
			sb.append("select map_outer.*,account_outer.ma_name ,( SELECT system_used.msystem_date_time_login" +
					" FROM "+ServiceConstant.SCHEMA+".MISS_SYSTEM_USE system_used left join" +
					" "+ServiceConstant.SCHEMA+".MISS_CONTACT contact on system_used.msystem_user_id =contact.mcontact_username left join" +
					"  "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on contact.mcontact_ref=account.ma_id left join" +
					" "+ServiceConstant.SCHEMA+".MISS_SALE_MAP map on account.ma_id=map.ma_id where map.mcontact_id=  map_outer.mcontact_id " +
					"  and map.ma_id=map_outer.ma_id  order by msystem_date_time_login desc limit 1 ) as x1" +
					" , " +
					"( SELECT system_used.msystem_date_time_login FROM "+ServiceConstant.SCHEMA+".MISS_SYSTEM_USE system_used left join" +
					" "+ServiceConstant.SCHEMA+".MISS_CONTACT contact" +
					" on system_used.msystem_user_id =contact.mcontact_username left join  "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account" +
					" on contact.mcontact_ref=account.ma_id left join "+ServiceConstant.SCHEMA+".MISS_SALE_MAP map on account.ma_id=map.ma_id" +
					" where map.mcontact_id=  map_outer.mcontact_id  and map.ma_id=map_outer.ma_id " +
					" order by msystem_date_time_login asc limit 1 ) as x2" +
					" from "+ServiceConstant.SCHEMA+".MISS_SALE_MAP map_outer left join " +
					""+ServiceConstant.SCHEMA+".MISS_ACCOUNT account_outer " +
					" on map_outer.ma_id=account_outer.ma_id where map_outer.mcontact_id="+mcontactId ); 
			consultantReport.setLoginStat(getResult(session,sb));
		   
			// get 5b
			sb.setLength(0);  
			sb.append("SELECT account.MA_NAME ,count(account.MA_ID)" +
					" FROM "+ServiceConstant.SCHEMA+".MISS_REACTIVE_LOG reactive left join" +
					" "+ServiceConstant.SCHEMA+".MISS_CANDIDATE candidate ON reactive.MCA_ID=candidate.MCA_ID" +
					" left join   "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on candidate.MA_ID=account.MA_ID" +
					"	left join "+ServiceConstant.SCHEMA+".MISS_SALE_MAP map on account.ma_id=map.ma_id" +
					" where map.mcontact_id="+mcontactId+"  group by account.MA_ID");
			consultantReport.setReactiveStat(getResult(session,sb));
			
			// get 5c
			sb.setLength(0);  
			sb.append("SELECT account.MA_NAME ,count(account.MA_ID) " +
					" FROM "+ServiceConstant.SCHEMA+".MISS_SERY_ORDER ordered left join" +
					" "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on ordered.MA_ID=account.MA_ID" +
					" left join "+ServiceConstant.SCHEMA+".MISS_SALE_MAP map on account.ma_id=map.ma_id " +
					" where map.mcontact_id="+mcontactId+" group by account.MA_ID");
			consultantReport.setOrderStat(getResult(session,sb));
			
			// get 5e
			sb.setLength(0);  
			sb.append("SELECT CONCAT(contact.mcontact_name, '  ', contact.mcontact_lastname) as name  ," +
					" account.ma_name ,contact.mcontact_updated_time FROM "+ServiceConstant.SCHEMA+".MISS_CONTACT contact " +
					" left join  "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on contact.mcontact_ref=account.MA_ID " +
					" left join "+ServiceConstant.SCHEMA+".MISS_SALE_MAP sale_map on account.ma_id=sale_map.ma_id " +
					" where sale_map.mcontact_id="+mcontactId+"");
			consultantReport.setUpdateStat(getResult(session,sb));
			
			// get 5f
			sb.setLength(0);  
			sb.append("SELECT CONCAT(contact.mcontact_name, '  ', contact.mcontact_lastname) as name  ," +
					" account.ma_name ,year(contact.mcontact_birth_date),month(contact.mcontact_birth_date),day(contact.mcontact_birth_date)" +
					" FROM "+ServiceConstant.SCHEMA+".MISS_CONTACT contact	left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on contact.mcontact_ref=account.MA_ID" +
					" left join "+ServiceConstant.SCHEMA+".MISS_SALE_MAP sale_map on account.ma_id=sale_map.ma_id where sale_map.mcontact_id="+mcontactId+" " +
					" and  month(contact.mcontact_birth_date)=month(CURDATE())");
			consultantReport.setBirthdayStat(getResult(session,sb)); 
			}
			// get 5d
			sb.setLength(0);  
			sb.append("SELECT exam.me_name , truncate(((count(exam.me_id)*100)/( select count(*)" +
					"   FROM "+ServiceConstant.SCHEMA+".MISS_SERY_ORDER order_inner" +
					"  where order_inner.ms_id=ordered.ms_id and" +
					"  order_inner.ma_id=ordered.ma_id ))+0.006,2) as x FROM "+ServiceConstant.SCHEMA+".MISS_SERY_ORDER ordered" +
					" left join  "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on ordered.MA_ID=account.MA_ID left join" +
					" "+ServiceConstant.SCHEMA+".MISS_SALE_MAP sale_map on account.ma_id=sale_map.ma_id left join" +
					" "+ServiceConstant.SCHEMA+".MISS_SERIES sery on  ordered.MS_ID=sery.MS_ID left join " +
					" "+ServiceConstant.SCHEMA+".MISS_SERIES_MAP map on sery.ms_id=map.ms_id left join " +
					" "+ServiceConstant.SCHEMA+".MISS_EXAM exam on  map.me_id=exam.me_id " +
					" where  sale_map.mcontact_id="+mcontactId+"  and year(ordered.mso_date_time)="+year+"" +
					" and month(ordered.mso_date_time)="+month+" " +
					" group by exam.me_id");
			consultantReport.setSaleStat(getResult(session,sb));
			//System.out.println("xxxxxx="+consultantReport);
			
			
			
		 }catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
				if (session != null) {
					session = null;
				} 
		}
	  return consultantReport;
	}
	@Override
	public ConsultantReport findSales(String sql) throws DataAccessException {
		// TODO Auto-generated method stub
		ConsultantReport consultantReport =new ConsultantReport();
		Session session=sessionAnnotationFactory.getCurrentSession();
		Query query=session.createSQLQuery("SELECT  map.mcontact_id, contact.mcontact_name FROM "+ServiceConstant.SCHEMA+".MISS_SALE_MAP map left join " +
				" "+ServiceConstant.SCHEMA+".MISS_CONTACT contact " +
				" on map.mcontact_id=contact.mcontact_id " +
				"group by map.mcontact_id ");
		//Query query =session.createSQLQuery(sb.toString()); 
		List<Object[]> list=query.list();
		List<List<String>> results=new ArrayList<List<String>>(list.size());
	try{
		for (Object[] objects : list) {
			List<String> strings =new ArrayList<String>(objects.length);
			//System.out.println(" object o="+objects[0]+" ,1="+objects[1]+" ,2 ="+objects[2]);
			for (int i = 0; i < objects.length; i++) {
				//System.out.println(objects[i].getClass().toString());
				 if(objects[i] instanceof java.lang.String){ 
					strings.add((String)objects[i]);
				 }
				 else if(objects[i] instanceof java.lang.Integer){ 
						strings.add((java.lang.Integer)objects[i]+"");
				}
				 else if(objects[i] instanceof java.math.BigInteger){ 
						strings.add((java.math.BigInteger)objects[i]+"");
				} else if(objects[i] instanceof java.math.BigDecimal){ 
					strings.add(((java.math.BigDecimal)objects[i]+""));
				}					
			}
		  results.add(strings);
		}
		//System.out.println(results.size());
		consultantReport.setSalesList(results);
		
		// test XLS
				//	setAnswer(session,"/root/Desktop/test_sort.xls"); 
					/*Query query_x= session.createQuery("select xls  from Xls xls ");
					List list_x=query_x.list();
					if(list_x!=null && list_x.size()>0){
						Xls xls = (Xls)list_x.get(0);
						getCode(xls.getDataXls());
					}*/
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
			if (session != null) {
				session = null;
			} 
	} 
	return consultantReport;
  } 
	private void getCode(byte[] byteArray){
		//System.out.println("byte size="+byteArray.length);
		ByteArrayInputStream bais = 
		         new ByteArrayInputStream(byteArray);
		HSSFWorkbook wb =null;
		try {
			wb= new HSSFWorkbook(bais);
			Sheet sheet1_0 = wb.getSheetAt(0);
			//System.out.println("xxxxxxxxxxxx"+sheet1_0.getSheetName());
			Row r = sheet1_0.getRow(1);
			//System.out.println(r.getCell(1).getStringCellValue());			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(bais!=null)
					bais.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private String setAnswer(Session session, String filePath) {
		FileInputStream fileIn = null;
		//FileOutputStream fileOut = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	//	String[] extensions = filePath.split("\\.");
		String outPut = "";
		try {
			try {
				fileIn = new FileInputStream(filePath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			POIFSFileSystem fs = null;
			try {
				fs = new POIFSFileSystem(fileIn);
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HSSFWorkbook wb = null;
			try {
				wb = new HSSFWorkbook(fs);
			 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//byte[] bytes = fs.toByteArray();
			/*
			 * Workbook wb=null; try { wb = WorkbookFactory.create(fs); } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */
			 
			// Write the output to a file
			// outPut=extensions[0]+"_"+msId.intValue()+"_"+meId.intValue()+"_"+mcaId.intValue()+"."+extensions[1];
			/*outPut = extensions[0] + "_" + msId.intValue() + "_"
					+ mcaId.intValue() + "." + extensions[1];
			try {
				fileOut = new FileOutputStream(outPut);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			/*try {
				wb.write(fileOut);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			try {
				wb.write(bos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] bytes=	bos.toByteArray();
			Xls xls =new Xls();
			xls.setDataXls(bytes);
			session.save(xls);
		} finally {
			 if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			if (fileIn != null)
				try {
					fileIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return outPut;
	}
}
