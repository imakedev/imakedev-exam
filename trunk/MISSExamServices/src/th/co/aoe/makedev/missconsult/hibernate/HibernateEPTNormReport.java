package th.co.aoe.makedev.missconsult.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.EPTNormReportService;
import th.co.aoe.makedev.missconsult.xstream.EPTNormReport;

@Repository
@Transactional
public class HibernateEPTNormReport  extends HibernateCommon implements EPTNormReportService {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private static final String[] CODE = new String[]{"FJPI","FJPE","FJAI","FJAE","FPPI","FPPE","FPAI","FPAE","IJPI","IJPE","IJAI","IJAE","IPPI","IPPE","IPAI","IPAE"};
    private static final String[] BETWEEN_AGE = new String[]{"between 1 and 14 ","between 15 and 35 ","between 36 and 60 "," > 60 "};
	private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private SessionFactory sessionAnnotationFactory;
	
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	private int getCountResult(Session session,StringBuffer sb){
		Query query =session.createSQLQuery(sb.toString()); 
		Object obj=query.uniqueResult();
		int result=0;
		if(obj!=null){
			//java.math.BigInteger[] obj_values=(Object[])obj;
			//return ((java.math.BigDecimal)Wobj_values[0]).intValue();
			return ((java.math.BigInteger)obj).intValue();
		}
		return result; 
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
		      else	if(objects[i] instanceof java.lang.String){
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
	/*private List<String> getResultWithCode(Session session,StringBuffer sb){
		Query query =session.createSQLQuery(sb.toString());
		@SuppressWarnings("unchecked")
		
		List<Object[]> list=query.list();
		List<List<String>> results=new ArrayList<List<String>>(list.size());
		for (Object[] objects : list) {
			List<String> strings =new ArrayList<String>(objects.length);
			for (int i = 0; i < objects.length; i++) {
			  if(objects[i]==null)
					 strings.add(null);
		      else	if(objects[i] instanceof java.lang.String){
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
	}*/
	@Override
	public EPTNormReport findEPTNormReport(String mode, String maId)
			throws DataAccessException {

		// TODO Auto-generated method stub
		EPTNormReport eptNormReport =new EPTNormReport();;
		Session session=sessionAnnotationFactory.getCurrentSession();
		try{
			StringBuffer sb =new StringBuffer();
			// get 1a
			sb.setLength(0);  
			sb.append("SELECT count(*) FROM "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result left join "+ServiceConstant.SCHEMA+".MISS_CANDIDATE  candidate " +
					" on result.mca_id=candidate.mca_id left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account " +
					" on candidate.ma_id=account.ma_id where result.ms_id=12 and account.ma_id="+maId+""); 
			int count= getCountResult(session, sb);
			eptNormReport.setEptCount(count+"");
		   
			if(count>0){
				// get 1b
				 List<List<String>> liePercent =new ArrayList<List<String>>(CODE.length);
				 //List<String> groupPercent =new ArrayList<String>(CODE.length);
				 List<List<String>> groupPercent =new ArrayList<List<String>>(CODE.length);
				 List<List<String>>  careerPercent =new ArrayList<List<String>>(CODE.length);
				 List<List<String>> genderPercent=new ArrayList<List<String>>(CODE.length);
				 List<List<String>> industryPercent=new ArrayList<List<String>>(CODE.length);
				 List<List<String>> agePercent=new ArrayList<List<String>>(CODE.length);
				for(int i=0;i<CODE.length;i++){
					List<String> innerResult=new ArrayList<String>(4);
					innerResult.add(CODE[i]);
					//-- calculate age%	
					 List<List<String>> calculateResult=null;
					sb.setLength(0);  
					sb.append("select count(age_.age), truncate(((count(age_.age)*100)/"+count+")+0.006,2) ,age_.age from (" +
							"	SELECT (select mcm_name from "+ServiceConstant.SCHEMA+".MISS_CAREER_MASTER career" +
							"	where career.mcm_id=candidate.mcm_id ) as career, " +
							"(select mim_name from "+ServiceConstant.SCHEMA+".MISS_INDUSTRY_MASTER industry" +
							"	where industry.mim_id=account.mim_id ) as industry , (YEAR(CURDATE())-YEAR(candidate.mca_birth_date)) " +
							"- (RIGHT(CURDATE(),5)<RIGHT(candidate.mca_birth_date,5)) AS age FROM "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result " +
							" left join "+ServiceConstant.SCHEMA+".MISS_CANDIDATE  candidate on result.mca_id=candidate.mca_id" +
							"	left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on candidate.ma_id=account.ma_id" +
							"	where result.ms_id=12 and account.ma_id="+maId+" and result.mtr_result_code='"+CODE[i]+"'	) " +
							" as age_ group by age_.age order by count(age_.age) desc limit 1");
					calculateResult=getResult(session, sb);
					 if(calculateResult!=null && calculateResult.size()>0){
						List<String> results= calculateResult.get(0);
						 innerResult.add(results.get(2));
						 innerResult.add(results.get(1)); 
					 }else{
						 innerResult.add(""); 
						 innerResult.add(""); 
					 }
					 // -- calculate gender%
					 sb.setLength(0);  
						sb.append("select count(candidate.mca_gender) " +
								" , truncate(((count(candidate.mca_gender)*100)/"+count+")+0.006,2) ,candidate.mca_gender" +
								" FROM "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result left join "+ServiceConstant.SCHEMA+".MISS_CANDIDATE  candidate" +
								" on result.mca_id=candidate.mca_id left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account " +
								" on candidate.ma_id=account.ma_id where result.ms_id=12 and account.ma_id="+maId+" " +
								" and result.mtr_result_code='"+CODE[i]+"'  group by candidate.mca_gender " +
								" order by count(candidate.mca_gender) desc limit 1");
						calculateResult=getResult(session, sb);
						 if(calculateResult!=null && calculateResult.size()>0){
							List<String> results= calculateResult.get(0);
							 innerResult.add(results.get(2));
							 innerResult.add(results.get(1)); 
						 }else{
							 innerResult.add(""); 
							 innerResult.add(""); 
						 } 
						 
					// -- calculate career%
						 sb.setLength(0);  
							sb.append(" select count(candidate.mcm_id), truncate(((count(candidate.mcm_id)*100)/"+count+")+0.006,2)," +
									" career.mcm_name FROM "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result" +
									" left join "+ServiceConstant.SCHEMA+".MISS_CANDIDATE  candidate on result.mca_id=candidate.mca_id" +
									" left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account" +
									" on candidate.ma_id=account.ma_id left join "+ServiceConstant.SCHEMA+".MISS_CAREER_MASTER career" +
									" on candidate.mcm_id=career.mcm_id	 where result.ms_id=12 and account.ma_id="+maId+"" +
									" and result.mtr_result_code='"+CODE[i]+"'  group by candidate.mcm_id" +
									" order by count(candidate.mcm_id) desc limit 1");
							calculateResult=getResult(session, sb);
							 if(calculateResult!=null && calculateResult.size()>0){
								List<String> results= calculateResult.get(0);
								 innerResult.add(results.get(2));
								 innerResult.add(results.get(1)); 
							 }else{
								 innerResult.add(""); 
								 innerResult.add(""); 
							 } 
						 
							// -- calculate industry%
							 sb.setLength(0);  
								sb.append("select count(account.mim_id), truncate(((count(account.mim_id)*100)/"+count+")+0.006,2) , industry.mim_name" +
										" FROM "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result" +
										" left join "+ServiceConstant.SCHEMA+".MISS_CANDIDATE  candidate on result.mca_id=candidate.mca_id" +
										" left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account	 on candidate.ma_id=account.ma_id" +
										" left join "+ServiceConstant.SCHEMA+".MISS_INDUSTRY_MASTER industry on account.mim_id=industry.mim_id" +
										" where result.ms_id=12 and account.ma_id="+maId+" and result.mtr_result_code='"+CODE[i]+"' group by account.mim_id " +
										" order by count(account.mim_id) desc limit 1");
								calculateResult=getResult(session, sb);
								 if(calculateResult!=null && calculateResult.size()>0){
									List<String> results= calculateResult.get(0);
									 innerResult.add(results.get(2));
									 innerResult.add(results.get(1)); 
								 }else{
									 innerResult.add(""); 
									 innerResult.add(""); 
								 }
							 
								 liePercent.add(innerResult);
								 
								// get 1c 
									sb.setLength(0);  
									sb.append(" select result.mtr_result_code ,count(result.mtr_result_code)," +
											" truncate(((count(result.mtr_result_code)*100)/"+count+")+0.006,2) FROM "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result" +
											"	left join "+ServiceConstant.SCHEMA+".MISS_CANDIDATE  candidate" +
											"	on result.mca_id=candidate.mca_id left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account" +
											"	on candidate.ma_id=account.ma_id left join "+ServiceConstant.SCHEMA+".MISS_INDUSTRY_MASTER industry" +
											"	on account.mim_id=industry.mim_id where result.ms_id=12 and account.ma_id="+maId+"" +
											"	 and result.mtr_result_code='"+CODE[i]+"'	 group by result.mtr_result_code ");
									calculateResult=getResult(session, sb);
									innerResult=new ArrayList<String>(2);
									innerResult.add(CODE[i]);
									 if(calculateResult!=null && calculateResult.size()>0){
										List<String> results= calculateResult.get(0);
										 //innerResult.add(results.get(2));
										innerResult.add(results.get(2)) ;
									 }else{
										 innerResult.add("") ;
										// innerResult.add(""); 
									 } 
									 groupPercent.add(innerResult);
									
									 // -- 1d
									 sb.setLength(0);  
										sb.append("select career.mcm_id  , count(career.mcm_id ), truncate(((count(career.mcm_id )*100)/"+count+")+0.006,2)" +
												" ,career.mcm_name FROM "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result" +
												" left join "+ServiceConstant.SCHEMA+".MISS_CANDIDATE  candidate on result.mca_id=candidate.mca_id" +
												" left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on candidate.ma_id=account.ma_id" +
												" left join "+ServiceConstant.SCHEMA+".MISS_CAREER_MASTER career on candidate.mcm_id=career.mcm_id" +
												" where result.ms_id=12 and account.ma_id="+maId+"  and result.mtr_result_code='"+CODE[i]+"' group by career.mcm_id" +
												" order by count(career.mcm_id) desc limit 3");
										calculateResult=getResult(session, sb);
										innerResult=new ArrayList<String>(4);
										innerResult.add(CODE[i]);
										 if(calculateResult!=null){
											 int size=calculateResult.size();
											 for (int j = 0; j < 3; j++) {
												if(j<size)
													innerResult.add(calculateResult.get(j).get(3));
												else
													innerResult.add(""); 
											}
									    }
									careerPercent.add(innerResult); 
									
									// 1e
									innerResult=new ArrayList<String>(4);
									innerResult.add(CODE[i]);
									sb.setLength(0);  
									sb.append("select candidate.mca_gender  ,count(candidate.mca_gender )," +
											" truncate(((count(candidate.mca_gender )*100)/"+count+")+0.006,2)" +
											"	FROM "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result" +
											"	left join "+ServiceConstant.SCHEMA+".MISS_CANDIDATE  candidate " +
											"on result.mca_id=candidate.mca_id left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account" +
											" on candidate.ma_id=account.ma_id	where result.ms_id=12 and account.ma_id="+maId+"" +
											" and candidate.mca_gender=1 and result.mtr_result_code='"+CODE[i]+"' group by candidate.mca_gender ");
									calculateResult=getResult(session, sb);
									
									 if(calculateResult!=null && calculateResult.size()>0){
										List<String> results= calculateResult.get(0);
										 //innerResult.add(results.get(0));
										 innerResult.add(results.get(2)); 
									 }else{
										// innerResult.add("1"); 
										 innerResult.add(""); 
									 }
									 sb.setLength(0);  
										sb.append("select candidate.mca_gender  ,count(candidate.mca_gender )," +
												" truncate(((count(candidate.mca_gender )*100)/"+count+")+0.006,2)" +
												"	FROM "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result" +
												"	left join "+ServiceConstant.SCHEMA+".MISS_CANDIDATE  candidate " +
												"on result.mca_id=candidate.mca_id left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account" +
												" on candidate.ma_id=account.ma_id	where result.ms_id=12 and account.ma_id="+maId+"" +
												" and candidate.mca_gender=0 and result.mtr_result_code='"+CODE[i]+"' group by candidate.mca_gender ");
										calculateResult=getResult(session, sb);
										
										 if(calculateResult!=null && calculateResult.size()>0){
											List<String> results= calculateResult.get(0);
											 //innerResult.add(results.get(0));
											 innerResult.add(results.get(2)); 
										 }else{
											// innerResult.add("1"); 
											 innerResult.add(""); 
										 }
									 genderPercent.add(innerResult);
									 
									 // 1f
									 sb.setLength(0);  
										sb.append("select industry.mim_id  ,count(industry.mim_id ),truncate(((count(industry.mim_id )*100)/"+count+")+0.006,2)" +
												" , industry.mim_name FROM "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result" +
												" left join "+ServiceConstant.SCHEMA+".MISS_CANDIDATE  candidate" +
												" on result.mca_id=candidate.mca_id left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account" +
												" on candidate.ma_id=account.ma_id left join "+ServiceConstant.SCHEMA+".MISS_INDUSTRY_MASTER industry" +
												" on account.mim_id=industry.mim_id where result.ms_id=12 and account.ma_id="+maId+"" +
												" and result.mtr_result_code='"+CODE[i]+"'  group by industry.mim_id order by count(industry.mim_id) desc limit 3");
										calculateResult=getResult(session, sb);
										innerResult=new ArrayList<String>(4);
										innerResult.add(CODE[i]);
										 if(calculateResult!=null){
											 int size=calculateResult.size();
											 for (int j = 0; j < 3; j++) {
												if(j<size)
													innerResult.add(calculateResult.get(j).get(3));
												else
													innerResult.add(""); 
											}
									    }
										industryPercent.add(innerResult);
										
										// 1g
										innerResult=new ArrayList<String>(5);
										innerResult.add(CODE[i]);
										for (int k = 0; k < BETWEEN_AGE.length; k++) {
											sb.setLength(0);  
											sb.append("select count(*) ,truncate((( count(*)*100)/"+count+")+0.006,2) from ( SELECT " +
													"(select mcm_name from "+ServiceConstant.SCHEMA+".MISS_CAREER_MASTER career " +
													"where career.mcm_id=candidate.mcm_id ) as career, " +
													" (select mim_name from "+ServiceConstant.SCHEMA+".MISS_INDUSTRY_MASTER industry " +
													" where industry.mim_id=account.mim_id ) as industry , " +
													" (YEAR(CURDATE())-YEAR(candidate.mca_birth_date)) - (RIGHT(CURDATE(),5)<RIGHT(candidate.mca_birth_date,5)) AS age" +
													" FROM "+ServiceConstant.SCHEMA+".MISS_TEST_RESULT result left join "+ServiceConstant.SCHEMA+".MISS_CANDIDATE  candidate" +
													" on result.mca_id=candidate.mca_id left join "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account " +
													" on candidate.ma_id=account.ma_id where result.ms_id=12 and account.ma_id="+maId+" and result.mtr_result_code='"+CODE[i]+"' " +
													" ) as age_  where age_.age "+BETWEEN_AGE[k]);
											calculateResult=getResult(session, sb);
											
											 if(calculateResult!=null){
												 int size=calculateResult.size();
												 for (int j = 0; j < 1; j++) {
													if(j<size)
														innerResult.add(calculateResult.get(j).get(1));
													else
														innerResult.add(""); 
												}
										    }
											
										}
										
										agePercent.add(innerResult);  
									 
									 
				}
				eptNormReport.setLiePercent(liePercent);
				eptNormReport.setGroupPercent(groupPercent);
				eptNormReport.setCareerPercent(careerPercent);
				eptNormReport.setGenderPercent(genderPercent);
				eptNormReport.setIndustryPercent(industryPercent);
				eptNormReport.setAgePercent(agePercent);
				
			}

			
			
			
		 }catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
				if (session != null) {
					session = null;
				} 
		}
	  return eptNormReport;
	
	
	}
	@Override
	public EPTNormReport findCompanies(String sql) throws DataAccessException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		EPTNormReport eptNormReport =new EPTNormReport();
		Session session=sessionAnnotationFactory.getCurrentSession();
		// SELECT * FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT where ma_type=0
		Query query=session.createSQLQuery("SELECT map.ma_id,account.ma_name FROM "+ServiceConstant.SCHEMA+".MISS_ACCOUNT_SERIES_MAP map left join " +
				" "+ServiceConstant.SCHEMA+".MISS_ACCOUNT account on map.ma_id=account.ma_id where map.ms_id=12 and  account.ma_type=0 ");
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
		eptNormReport.setCompanyList(results);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
			if (session != null) {
				session = null;
			} 
	} 
	return eptNormReport;
	
	}

}
