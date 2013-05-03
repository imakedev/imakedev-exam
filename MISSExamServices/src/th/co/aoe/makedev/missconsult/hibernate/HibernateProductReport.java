package th.co.aoe.makedev.missconsult.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.aoe.makedev.missconsult.managers.ProductReportService;
import th.co.aoe.makedev.missconsult.xstream.ProductReport;
@Repository
@Transactional
public class HibernateProductReport extends HibernateCommon implements ProductReportService {

	//private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
	private SessionFactory sessionAnnotationFactory;
	private static ResourceBundle bundle;
	private static String schema="";
	static{
		bundle =  ResourceBundle.getBundle( "jdbc" );	
		schema=bundle.getString("schema");
	}
	public SessionFactory getSessionAnnotationFactory() {
		return sessionAnnotationFactory;
	}
	public void setSessionAnnotationFactory(SessionFactory sessionAnnotationFactory) {
		this.sessionAnnotationFactory = sessionAnnotationFactory;
	}
	private List<String> getResult(Session session,StringBuffer sb,int index){
		Query query =session.createSQLQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list=query.list();
		List<String> results =new ArrayList<String>(list.size());
		for (Object[] objects : list) {
			results.add((String)objects[index]);
		}
		return results;
	}
	private String getResultWithYear(Session session,StringBuffer sb,int index){
		Query query =session.createSQLQuery(sb.toString()); 
		Object obj=query.uniqueResult();
		if(obj!=null){
			Object[] obj_values=(Object[])obj;
			return (String)obj_values[index];
		}
		return ""; 
	}
	@Transactional(readOnly=true)
	public ProductReport findProductReport(String mode,String year)
			throws DataAccessException {
		// TODO Auto-generated method stub
		ProductReport productReport =new ProductReport();;
		Session session=sessionAnnotationFactory.getCurrentSession();
		try{
	//	Query query=session.createQuery(" select ProductReport from ProductReport ProductReport where ProductReport.mmId=:mmId");
		//Query query=session.createQuery(" select ProductReport from ProductReport ProductReport where ProductReport.missSery.msId=:msId");
			StringBuffer sb =new StringBuffer();
		if(mode.equals("1")){
			// get Sery used
			sb.setLength(0);
			sb.append(" SELECT sery_map.me_id,count(sery_map.me_id),exam.me_name FROM " +
					" "+schema+".MISS_SERY_USE sery_use left join" +
					" "+schema+".MISS_SERIES_MAP sery_map on sery_use.ms_id=sery_map.ms_id" +
					"  left join  "+schema+".MISS_EXAM exam on sery_map.me_id=exam.me_id" +
					"  group by me_id order by count(sery_map.me_id) desc " ); 
			productReport.setSeryUses(getResult(session,sb,2));
			
			// get Sery Order
			sb.setLength(0);
			sb.append(" SELECT sery_map.me_id,count(sery_map.me_id),exam.me_name FROM    " +
					" "+schema+".MISS_SERY_ORDER sery_order left join" +
					" "+schema+".MISS_SERIES_MAP sery_map on sery_order.ms_id=sery_map.ms_id" +
					"  left join  "+schema+".MISS_EXAM exam on sery_map.me_id=exam.me_id" +
					"  group by me_id order by count(sery_map.me_id) desc ");
			 
			productReport.setSeryOrders(getResult(session,sb,2));
			
			// get Sery Order
			sb.setLength(0);
			sb.append("SELECT sery_map.me_id,count(sery_map.me_id),exam.me_name FROM " +
					" "+schema+".MISS_SERY_PROBLEM sery_problem left join" +
					" "+schema+".MISS_SERIES_MAP sery_map on sery_problem.ms_id=sery_map.ms_id" +
					"  left join  "+schema+".MISS_EXAM exam on sery_map.me_id=exam.me_id" +
					"  group by me_id order by count(sery_map.me_id) desc ");
			 
			productReport.setSeryProblems(getResult(session,sb,2));
		}
		 
		// get Sery user with year
		List<String> seryMaxUses =new ArrayList<String>(12);
		   for(int i=1;i<=12;i++){
			   sb.setLength(0);
				sb.append(" SELECT sery_map.me_id,count(sery_map.me_id),exam.me_name FROM  " +
						" "+schema+".MISS_SERY_USE sery_use left join" +
						" "+schema+".MISS_SERIES_MAP sery_map on sery_use.ms_id=sery_map.ms_id" +
						"  left join  "+schema+".MISS_EXAM exam on sery_map.me_id=exam.me_id" +
						"    where year(sery_use.msu_ddate_time)="+year+" and  month(sery_use.msu_ddate_time)="+i +
						"   group by me_id order by count(sery_map.me_id) desc limit 1");
				seryMaxUses.add(getResultWithYear(session,sb,2));
		   }  
		   productReport.setSeryMaxUses(seryMaxUses);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
				if (session != null) {
					session = null;
				} 
		}
	  return productReport;
	}

}
