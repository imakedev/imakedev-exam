package th.co.aoe.makedev.missconsult.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import th.co.aoe.makedev.missconsult.domain.MissEptAttitudeDetectorReport;
import th.co.aoe.makedev.missconsult.domain.MissEptCareer;
import th.co.aoe.makedev.missconsult.domain.MissEptDominance;
import th.co.aoe.makedev.missconsult.domain.MissEptEvalBehavioralGroup;
import th.co.aoe.makedev.missconsult.domain.MissEptEvalBehavioralValue;
import th.co.aoe.makedev.missconsult.domain.MissEptMessageConfig;
import th.co.aoe.makedev.missconsult.domain.MissEptPlusWorkWheelMessage;
import th.co.aoe.makedev.missconsult.domain.MissEptTraitsDetector;

/**
 * Servlet implementation class EPTPlusServlet
 */
@WebServlet("/eptplus")
public class EPTPlusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/missdb")
    DataSource ds; 
	public static final ResourceBundle bundle;
	public static String SCHEMA="";
	static{
		bundle =  ResourceBundle.getBundle( "jdbc" );	
		SCHEMA=bundle.getString("schema");
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EPTPlusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//http://localhost:8080/MISSProcessImage/eptplus?page=workwheel_1&type=ept_plus&mtrId=61&lang=1
		//http://localhost:8080/MISSProcessImage/eptplus?page=analysis_1&type=ept_plus&mtrId=61&lang=1
		//http://localhost:8080/MISSProcessImage/eptplus?page=evalOfbehavioral_1&type=ept_plus&mtrId=61&lang=1
		String page=request.getParameter("page");
		String type=request.getParameter("type");
		String mtrId=request.getParameter("mtrId");
		String lang=request.getParameter("lang");
		String key= request.getParameter("key");
		 HttpSession session = request.getSession(true);
		if(page.indexOf("evalOfbehavioral")!=-1){
			List<MissEptEvalBehavioralGroup> groups= getMissEptEvalBehavioralGroups(mtrId,page,lang);
			session.setAttribute("groups", groups);
			page="evalOfbehavioral";
		}else if(page.indexOf("workwheel")!=-1){
			 List<MissEptPlusWorkWheelMessage> messages= getMissEptPlusWorkWheelMessages(mtrId,page,lang);
			session.setAttribute("messages", messages);
			if(page.indexOf("workwheel_1")!=-1)
				session.setAttribute("xmlData", getXML(mtrId,"chart2_eptplus",lang));
			
		}else if(page.indexOf("analysis_")!=-1){
			 List analysis=getAnalysisMessage(mtrId,lang); 
			 
			session.setAttribute("fullname", analysis.get(0));
			session.setAttribute("configs", analysis.get(1));			
			session.setAttribute("careers", analysis.get(2));
			/*if(page.indexOf("workwheel_2")!=-1)
				session.setAttribute("xmlData", getXML(mtrId,"chart2_eptplus",lang));*/
			
		}
		else if(page.indexOf("attitudeDetector_1")!=-1){
			List attitudeDetectors = getAttitudeDetectorReport(mtrId,lang);
			 
			session.setAttribute("radarxmlData", attitudeDetectors.get(0));
			session.setAttribute("dominance", attitudeDetectors.get(1));			
			session.setAttribute("messages", attitudeDetectors.get(2)); 
		}else if(page.indexOf("attitudeDetector_2")!=-1){
			List traitsDetectors = getTraitsDetector(mtrId,lang);
			 
			session.setAttribute("column2DxmlData", traitsDetectors.get(0));
			session.setAttribute("traits", traitsDetectors.get(1)); 
		}
		
		// RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/ept_plus/"+page+".jsp");
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/"+type+"/"+page+".jsp");
		 dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	private List<MissEptEvalBehavioralGroup> getMissEptEvalBehavioralGroups(String mtrId,String page,String lang){
		String groupIn="";
		if(page.endsWith("evalOfbehavioral_1")){
			groupIn=" where b_group.MEEBG_ID in (1,2) ";
		}else if(page.endsWith("evalOfbehavioral_2")){
			groupIn=" where b_group.MEEBG_ID in (3) ";
		}else if(page.endsWith("evalOfbehavioral_3")){
			groupIn=" where b_group.MEEBG_ID in (4,5,6,7) ";
		}
		Connection con = null; 
		org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs =null;
		 PreparedStatement pst = null;
		 ResultSet result= null;
		 PreparedStatement pst_inner = null;
		 ResultSet result_inner= null;
		 int [] values=new int[]{-10,-8,-6,-4,-2,0,1,3,5,7,9};
		 String [] valuesString=new String[]{"img-5","img-4","img-3","img-2","img-1","img0","img-1","img-2","img-3","img-4","img-5"};
		
		 List<MissEptEvalBehavioralGroup> missEptEvalBehavioralGroups =new ArrayList<MissEptEvalBehavioralGroup>();
		try {
			basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
			con = basicDs.getConnection();//("oracle", "password");//Connection();
			//String sql=;
			StringBuffer sqlSB=new StringBuffer("SELECT * FROM "+SCHEMA+".MISS_EPT_EVAL_BEHAVIORAL_GROUP b_group "+groupIn+" order by b_group.meebg_id  ");
			 pst = con.prepareStatement(sqlSB.toString());
			 result = pst.executeQuery();
				
				StringBuffer sbGroupId=new StringBuffer("");
				int meebvValue=0;
				if(result!=null)					
						while (result.next()) { 
							MissEptEvalBehavioralGroup group=new MissEptEvalBehavioralGroup();
							group.setMeebgGroup(result.getString("MEEBG_GROUP"));
							sbGroupId.setLength(0);
							sbGroupId.append(result.getInt("MEEBG_ID")+"");
							List<MissEptEvalBehavioralValue> missEptEvalBehavioralValues =new ArrayList<MissEptEvalBehavioralValue>();
							//sql="SELECT * FROM "+SCHEMA+".MISS_EPT_EVAL_BEHAVIORAL_VALUE b_group order by b_group.meebg_id  ";
							sqlSB.setLength(0);
							sqlSB.append("SELECT b_config.meeb_key as MEEB_KEY ,b_value.meebv_value as MEEBV_VALUE ,b_config.MEEB_MESSAGE1 as MEEB_MESSAGE1,b_config.MEEB_MESSAGE2 as MEEB_MESSAGE2 " +
								" FROM "+SCHEMA+".MISS_EPT_EVAL_BEHAVIORAL_VALUE b_value left  join "+SCHEMA+".MISS_EPT_EVAL_BEHAVIORAL_GROUP b_group" +
								"	on (b_value.meebg_id=b_group.meebg_id and b_group.meebg_lang='"+lang+"') left join "+SCHEMA+".MISS_EPT_EVAL_BEHAVIORAL b_config" +
								"	on ( b_value.meebg_id=b_config.meebg_id and b_value.meebv_order=b_config.meeb_order and b_config.meeb_lang='"+lang+"' )" +
								"	where b_value.mtr_id="+mtrId+"  and b_group.meebg_id="+sbGroupId.toString()+"	order by b_group.meebg_id , b_value.meebv_order");
							pst_inner = con.prepareStatement(sqlSB.toString());
							result_inner = pst_inner.executeQuery();
							 if(result_inner!=null){
								 while (result_inner.next()) { 
									 MissEptEvalBehavioralValue value=new MissEptEvalBehavioralValue();
									 value.setMeebvKey(result_inner.getString("MEEB_KEY"));
									 // int [] values=new int[]{-10,-8,-6,-4,-2,0,1,3,5,7,9};
									 // String [] valuesString=new String[]{"img-5","img-4","img-3","img-2","img-1","img0","img-1","img-2","img-3","img-4","img-5"};
									 meebvValue=result_inner.getInt("MEEBV_VALUE");
									 String [] imges=new String[values.length];
									 for (int i = 0; i < values.length; i++) {
										if(i==5){
											if(meebvValue==0)
												imges[i]=valuesString[i]+"-selected";
											else
												imges[i]=valuesString[i]+"";
										}else{
											 if(meebvValue==values[i] || meebvValue==(values[i]+1)){
												imges[i]=valuesString[i]+"-selected";
											}else
												imges[i]=valuesString[i]+"";
										}  
									}
				
									 value.setImges(imges);
									 value.setMeebvValue(meebvValue);
									
									 value.setMessage1(result_inner.getString("MEEB_MESSAGE1"));
									 value.setMessage2(result_inner.getString("MEEB_MESSAGE2"));
									 missEptEvalBehavioralValues.add(value);
								 }
							 }
							 group.setMissEptEvalBehavioralValues(missEptEvalBehavioralValues);
							// close connection
									try {
										if(result_inner!=null){
											if(!result_inner.isClosed()){
												result_inner.close();
												result_inner=null;
											}
										}
										if (pst_inner != null) {
											if(!pst_inner.isClosed()){
												pst_inner.close();			 
												pst_inner = null;
											} 
										} 
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}		 
							 missEptEvalBehavioralGroups.add(group);
						}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{ 
			if (con != null) {
				try {
					if(result!=null){
						if(!result.isClosed()){
							result.close();
							result=null;
						}
					}
					if (pst != null) {
						if(!pst.isClosed()){
							pst.close();			 
							pst = null;
						} 
						
					} 
					if(!con.isClosed());
						con.close(); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			} 
		}
	return missEptEvalBehavioralGroups;
	}
	private List<MissEptPlusWorkWheelMessage> getMissEptPlusWorkWheelMessages(String mtrId,String page,String lang){
		Connection con = null; 
		org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs =null;
		 PreparedStatement pst = null;
		 ResultSet result= null;
		 List<MissEptPlusWorkWheelMessage> missEptPlusWorkWheelMessages =new ArrayList<MissEptPlusWorkWheelMessage>();
		try {
			basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
			con = basicDs.getConnection();//("oracle", "password");//Connection();
			StringBuffer sqlSB=new StringBuffer("SELECT * FROM "+SCHEMA+".MISS_EPT_PLUS_WORK_WHEEL_MESSAGE message where message.mtr_id="+mtrId+" and message.mepwwm_lang='"+lang+"' " +
					" order by message.mepwwm_value desc  ");
			
			pst = con.prepareStatement(sqlSB.toString());
			 result = pst.executeQuery();
				if(result!=null)					
						while (result.next()) { 
							MissEptPlusWorkWheelMessage message=new MissEptPlusWorkWheelMessage();
							message.setMepwwmCharecter1(result.getString("MEPWWM_CHARECTER1"));
							message.setMepwwmCharecter2(result.getString("MEPWWM_CHARECTER2"));
							message.setMepwwmPercent(result.getString("MEPWWM_PERCENT"));
							message.setMepwwmRole(result.getString("MEPWWM_ROLE"));
							message.setMepwwmSample(result.getString("MEPWWM_SAMPLE"));
							message.setMepwwmType(result.getString("MEPWWM_TYPE"));
							message.setMepwwmValue(result.getBigDecimal("MEPWWM_VALUE"));
							 
							missEptPlusWorkWheelMessages.add(message);
						}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{ 
			if (con != null) {
				try {
					if(result!=null){
						if(!result.isClosed()){
							result.close();
							result=null;
						}
					}
					if (pst != null) {
						if(!pst.isClosed()){
							pst.close();			 
							pst = null;
						} 
						
					} 
					if(!con.isClosed());
						con.close(); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			} 
		}
	return missEptPlusWorkWheelMessages;
	}
	private String getXML(String mtrId,String mdcKey,String lang){
		//SELECT * FROM MISS_CONSULT_EXAM.MISS_DATA_CHART;
		Connection con = null; 
		org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs =null;
		 PreparedStatement pst = null;
		 ResultSet result= null;
		 String xmlData="";
		// String swfName="";
		try {
			basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
			con = basicDs.getConnection();//("oracle", "password");//Connection();
			StringBuffer sqlSB=new StringBuffer("SELECT * FROM "+SCHEMA+".MISS_DATA_CHART chart where chart.mtr_id="+mtrId+" and chart.mdc_key='"+mdcKey+"' " +
					" ");
			
			pst = con.prepareStatement(sqlSB.toString());
			 result = pst.executeQuery();
				if(result!=null)					
						while (result.next()) { 
							xmlData=result.getString("MDC_DATA");
							//swfName=result.getString("MDC_SWF_NAME");
						}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{ 
			if (con != null) {
				try {
					if(result!=null){
						if(!result.isClosed()){
							result.close();
							result=null;
						}
					}
					if (pst != null) {
						if(!pst.isClosed()){
							pst.close();			 
							pst = null;
						} 
						
					} 
					if(!con.isClosed());
						con.close(); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			} 
		}
	return xmlData;
	}
	private List getAnalysisMessage(String mtrId,String lang){
		/*
		 * 
		 * 
		  SELECT CONCAT_WS(" ",candidate.mca_first_name,candidate.mca_last_name),candidate.mca_first_name , candidate.mca_last_name FROM MISS_TEST_RESULT result 
left join MISS_CANDIDATE candidate 
on result.mca_id=candidate.mca_id 
WHERE result.mtr_id=61

select * from MISS_EPT_MESSAGE_CONFIG 
WHERE CODE='FJAE' AND MEMC_LANG='1'
ORDER BY MEMC_ORDER 



SELECT * FROM MISS_EPT_CAREER where MEC_CODE='FJAE' 
AND MEC_LANG='1' ORDER BY MEC_ORDER 
		 */
		Connection con = null; 
		org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs =null;
		 PreparedStatement pst = null;
		 ResultSet result= null;
		 List results=new ArrayList(3);
		 String fullname="";
		 String code="";
		 List<MissEptMessageConfig> configs=new ArrayList<MissEptMessageConfig>();
		 List<MissEptCareer> careers=new ArrayList<MissEptCareer>();
		  
		 List<MissEptPlusWorkWheelMessage> missEptPlusWorkWheelMessages =new ArrayList<MissEptPlusWorkWheelMessage>();
		try {
			basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
			con = basicDs.getConnection();//("oracle", "password");//Connection();
			/*StringBuffer sqlSB=new StringBuffer("SELECT * FROM "+SCHEMA+".MISS_EPT_PLUS_WORK_WHEEL_MESSAGE message where message.mtr_id="+mtrId+" and message.mepwwm_lang='"+lang+"' " +
					" order by message.mepwwm_value desc  ");*/
			StringBuffer sqlSB=new StringBuffer("SELECT CONCAT_WS(' ',candidate.mca_first_name,candidate.mca_last_name) as FULL_NAME , " +
					" result.MTR_RESULT_CODE AS CODE ,candidate.mca_first_name" +
					" , candidate.mca_last_name FROM "+SCHEMA+".MISS_TEST_RESULT result left join "+SCHEMA+".MISS_CANDIDATE candidate" +
					"	on result.mca_id=candidate.mca_id WHERE result.mtr_id="+mtrId);			
			pst = con.prepareStatement(sqlSB.toString());
			 result = pst.executeQuery();
				if(result!=null){					
						while (result.next()) { 
							fullname=result.getString("FULL_NAME");
							code=result.getString("CODE"); 
						}
						// for test
						code="FJAE";
						sqlSB.setLength(0);
						sqlSB.append("SELECT * from "+SCHEMA+".MISS_EPT_MESSAGE_CONFIG WHERE CODE='"+code+"' AND MEMC_LANG='"+lang+"' ORDER BY MEMC_ORDER ");
						pst = con.prepareStatement(sqlSB.toString());
						 result = pst.executeQuery();
							if(result!=null){					
									while (result.next()) { 
										MissEptMessageConfig config=new MissEptMessageConfig();
										config.setCode(code);
										config.setMemcDesc(result.getString("MEMC_DESC"));
										config.setMemcKey(result.getString("MEMC_KEY"));
										config.setMemcLang(result.getString("MEMC_LANG"));
										config.setMemcMessage(result.getString("MEMC_MESSAGE")); 
										configs.add(config);
									}
							}
						
						sqlSB.setLength(0);
						sqlSB.append("SELECT * FROM MISS_EPT_CAREER where MEC_CODE='"+code+"' " +
								" AND MEC_LANG='"+lang+"' ORDER BY MEC_ORDER");
						pst = con.prepareStatement(sqlSB.toString());
						result = pst.executeQuery();
								if(result!=null){					
										while (result.next()) { 
											MissEptCareer career=new MissEptCareer();
											career.setMecCareerName(result.getString("MEC_CAREER_NAME"));
											career.setMecCode(result.getString("MEC_CODE"));
											career.setMecLang(result.getString("MEC_LANG"));
											//career.setMecOrder(result.getString("MEC_ORDER"));  
											careers.add(career);
										}
								}	
							 
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{ 
			if (con != null) {
				try {
					if(result!=null){
						if(!result.isClosed()){
							result.close();
							result=null;
						}
					}
					if (pst != null) {
						if(!pst.isClosed()){
							pst.close();			 
							pst = null;
						} 
						
					} 
					if(!con.isClosed());
						con.close(); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			} 
		}
		results.add(fullname);
		results.add(configs);
		results.add(careers);
	return results;
	}
	
	private List getAttitudeDetectorReport(String mtrId,String lang){
		/*
		 * 
		 * 
		 SELECT * FROM MISS_CONSULT_EXAM.MISS_EPT_ATTITUDE_DETECTOR_REPORT
where mtr_id=61 and meadr_lang='1' order by meadr_order 

SELECT * FROM MISS_CONSULT_EXAM.MISS_EPT_DOMINANCE
where mtr_id=61

SELECT * FROM MISS_CONSULT_EXAM.MISS_DATA_CHART 
where mtr_id=20 and mdc_key='chart2_ept'
		 */
		Connection con = null; 
		org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs =null;
		 PreparedStatement pst = null;
		 ResultSet result= null;
		 List results=new ArrayList(3);
		 
		 String xml=""; 
		 List<MissEptAttitudeDetectorReport> attitudes=new ArrayList<MissEptAttitudeDetectorReport>();
		 MissEptDominance dominance =new MissEptDominance();
		try {
			basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
			con = basicDs.getConnection();//("oracle", "password");//Connection();
			StringBuffer sqlSB=new StringBuffer("SELECT * FROM "+SCHEMA+".MISS_EPT_ATTITUDE_DETECTOR_REPORT " +
					" where mtr_id="+mtrId+" and meadr_lang='"+lang+"' order by meadr_order ");			
			pst = con.prepareStatement(sqlSB.toString());
			 result = pst.executeQuery();
				if(result!=null){					
						while (result.next()) { 
							MissEptAttitudeDetectorReport report =new MissEptAttitudeDetectorReport();
							report.setMeadrDetail(result.getString("MEADR_DETAIL"));
							report.setMeadrKey(result.getString("MEADR_KEY"));
							report.setMeadrTopic(result.getString("MEADR_TOPIC")); 
							attitudes.add(report);
						}
				}
				sqlSB.setLength(0);
				sqlSB.append("SELECT * FROM "+SCHEMA+".MISS_EPT_DOMINANCE where mtr_id="+mtrId);
				pst = con.prepareStatement(sqlSB.toString());
				 result = pst.executeQuery();
					if(result!=null){					
							while (result.next()) {   
								dominance.setMepDominance(result.getString("MEP_DOMINANCE"));
								dominance.setMepSubDominance(result.getString("MEP_SUB_DOMINANCE")); 
							}
					}
				sqlSB.setLength(0);
				sqlSB.append(" SELECT * FROM "+SCHEMA+".MISS_DATA_CHART where mtr_id="+mtrId+" and mdc_key='chart2_ept'");
				pst = con.prepareStatement(sqlSB.toString());
				result = pst.executeQuery();
					if(result!=null){					
						while (result.next()) { 
							xml=result.getString("MDC_DATA");
						}
					}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{ 
			if (con != null) {
				try {
					if(result!=null){
						if(!result.isClosed()){
							result.close();
							result=null;
						}
					}
					if (pst != null) {
						if(!pst.isClosed()){
							pst.close();			 
							pst = null;
						} 
						
					} 
					if(!con.isClosed());
						con.close(); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			} 
		}
		results.add(xml);
		results.add(dominance);
		results.add(attitudes);
	return results;
	}
	private List getTraitsDetector(String mtrId,String lang){
		/*
		 * 
		 * 
		 SELECT * FROM MISS_CONSULT_EXAM.MISS_DATA_CHART 
where mtr_id=20 and mdc_key='chart3_ept'

SELECT * FROM MISS_CONSULT_EXAM.MISS_EPT_TRAITS_DETECTOR
where mtr_id=61 and metd_lang='1' order by metd_order asc 
		 */
		Connection con = null; 
		org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs =null;
		 PreparedStatement pst = null;
		 ResultSet result= null;
		 List results=new ArrayList(2);
		
		 String xml="";
		 List<MissEptTraitsDetector> traitsDetectors=new ArrayList<MissEptTraitsDetector>(); 
		try {
			basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
			con = basicDs.getConnection();//("oracle", "password");//Connection();
			StringBuffer sqlSB=new StringBuffer(" SELECT * FROM "+SCHEMA+".MISS_DATA_CHART where mtr_id="+mtrId+" and mdc_key='chart3_ept'");			
			 
			pst = con.prepareStatement(sqlSB.toString());
			 result = pst.executeQuery();
				if(result!=null){					
						while (result.next()) { 
							xml=result.getString("MDC_DATA");
						}   
				}
				sqlSB.setLength(0);
				sqlSB.append(" SELECT * FROM  "+SCHEMA+".MISS_EPT_TRAITS_DETECTOR where mtr_id="+mtrId+" and metd_lang='"+lang+"' order by metd_order asc ");
				pst = con.prepareStatement(sqlSB.toString());
				 result = pst.executeQuery();
					if(result!=null){					
							while (result.next()) { 
								MissEptTraitsDetector traits=new MissEptTraitsDetector();
								traits.setMetdName(result.getString("METD_NAME"));
								traits.setMetdValue(result.getBigDecimal("METD_VALUE"));
								traitsDetectors.add(traits);
							}
					}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{ 
			if (con != null) {
				try {
					if(result!=null){
						if(!result.isClosed()){
							result.close();
							result=null;
						}
					}
					if (pst != null) {
						if(!pst.isClosed()){
							pst.close();			 
							pst = null;
						} 
						
					} 
					if(!con.isClosed());
						con.close(); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			} 
		}
		results.add(xml);
		results.add(traitsDetectors); 
	return results;
	}
}



