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

import th.co.aoe.makedev.missconsult.domain.MissEptEvalBehavioralGroup;
import th.co.aoe.makedev.missconsult.domain.MissEptEvalBehavioralValue;

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
		String page=request.getParameter("page");
		String type=request.getParameter("type");
		String mtrId=request.getParameter("mtrId");
		String lang=request.getParameter("lang");
		 HttpSession session = request.getSession(true);
		if(page.indexOf("evalOfbehavioral")!=-1){
			List<MissEptEvalBehavioralGroup> groups= getMissEptEvalBehavioralGroups(mtrId,page,lang);
			System.out.println("groups size="+groups.size());
			session.setAttribute("groups", groups);
			page="evalOfbehavioral";
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
		 List<MissEptEvalBehavioralGroup> missEptEvalBehavioralGroups =new ArrayList<MissEptEvalBehavioralGroup>();
		try {
			basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
			con = basicDs.getConnection();//("oracle", "password");//Connection();
			//String sql=;
			StringBuffer sqlSB=new StringBuffer("SELECT * FROM "+SCHEMA+".MISS_EPT_EVAL_BEHAVIORAL_GROUP b_group "+groupIn+" order by b_group.meebg_id  ");
			 pst = con.prepareStatement(sqlSB.toString());
			 result = pst.executeQuery();
				
				StringBuffer sbGroupId=new StringBuffer("");
				if(result!=null)					
						while (result.next()) { 
							MissEptEvalBehavioralGroup group=new MissEptEvalBehavioralGroup();
							group.setMeebgGroup(result.getString("MEEBG_GROUP"));
							sbGroupId.setLength(0);
							sbGroupId.append(result.getInt("MEEBG_ID")+"");
							List<MissEptEvalBehavioralValue> missEptEvalBehavioralValues =new ArrayList<MissEptEvalBehavioralValue>();
							//sql="SELECT * FROM "+SCHEMA+".MISS_EPT_EVAL_BEHAVIORAL_VALUE b_group order by b_group.meebg_id  ";
							sqlSB.setLength(0);
							sqlSB.append("SELECT b_value.meeb_key as MEEB_KEY ,b_value.meebv_value as MEEBV_VALUE ,b_config.MEEB_MESSAGE1 as MEEB_MESSAGE1,b_config.MEEB_MESSAGE2 as MEEB_MESSAGE2 " +
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
									 value.setMeebvValue(result_inner.getInt("MEEBV_VALUE"));
									
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
}
