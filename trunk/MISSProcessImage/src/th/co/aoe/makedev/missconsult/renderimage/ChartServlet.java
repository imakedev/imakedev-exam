package th.co.aoe.makedev.missconsult.renderimage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/**
 * Servlet implementation class ChartServlet
 */
@WebServlet("/chart")
public class ChartServlet extends HttpServlet {

	@Resource(name = "jdbc/missdb")
    DataSource ds;
	private static final long serialVersionUID = 1L;
	public static final ResourceBundle bundle;
	public static String SCHEMA="";
	static{
		bundle =  ResourceBundle.getBundle( "jdbc" );	
		SCHEMA=bundle.getString("schema");
	}

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//chart="+chart+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"
		 HttpSession session = request.getSession(true);
		 //String page=request.getParameter("page");
			
			String key= request.getParameter("key");
			String mtrId=request.getParameter("mtrId"); 
			String width=request.getParameter("width");
			String height=request.getParameter("height");
			
			String lang=request.getParameter("lang");
			String type=request.getParameter("type");
			String chart= request.getParameter("chart");
			//"http://203.150.20.37/MISSProcessImage/process?mtrId="+$P{mtrId}.toString()+"&key=chart1&w=800&h=400&chart=bar"
			//"+mtrId+"_"+mdc_key+"_"+chart+"_"+lang+"_"+width+"_"+height+"
		
		String[] data = getDefualtXML(mtrId,key);
		 String xmlData=data[0];
		 String swfName=data[1];
		 String mdcType=data[2];
		  /* if(type.equals("ept") || type.equals("ept_plus")){
			   xmlData = getBarChartXML(mtrId,chart,lang);
		   }
		   if(type.equals("bar2d")){
			   swfName="Bar2D.swf"; 
		   }*/
		
			session.setAttribute("XMLDATA", xmlData);
	        session.setAttribute("swfName", swfName);
	        session.setAttribute("mdcType", mdcType);
	        session.setAttribute("width", width);
	        session.setAttribute("height", height);
	        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/chart.jsp");
	        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	 
	private String getBarChartXML(String mtrId,String chart,String lang){
		if(chart.equalsIgnoreCase("Bar2D")){
			String xml="<chart  alternateVGridColor=\"AFD8F8\" baseFontColor=\"114B78\" toolTipBorderColor=\"114B78\" toolTipBgColor=\"E7EFF6\" useRoundEdges=\"1\" showBorder=\"0\" bgColor=\"FFFFFF,FFFFFF\">" +
					"<set label=\"Imagineative\" value=\"9\" color=\"#54E8FF\"/>" +
					"<set label=\"Factual\" value=\"10\"  color=\"#54E8FF\"/>" +
					"<set label=\"Perceiving\" value=\"9\" color=\"#BBFF00\"/>" +
					"<set label=\"Judging\" value=\"13\" color=\"#BBFF00\"/>" +
					"<set label=\"Assertive\" value=\"18\" color=\"#D4D300\"/>" +
					"<set label=\"Passive\" value=\"7\" color=\"#D4D300\"/>" +
					"<set label=\"Introvert\" value=\"5\" color=\"#4891A2\"/>" +
					"<set label=\"Extravert\" value=\"15\" color=\"#4891A2\"/>" +
					"</chart>";
		}
		return null;
	}
	private String[] getDefualtXML(String mtrId,String mdc_key){
		Connection con = null; 
		org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs =null;
		 PreparedStatement pst = null;
		 ResultSet result= null;
		 String[] data=new String[3];
		 String xmlData="";
		 String swfName="";
		 String mdcType="";
		// mdc_key="chart1"; 
		try {
			basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
			con = basicDs.getConnection();//("oracle", "password");//Connection();
			String sql="SELECT * FROM "+SCHEMA+".MISS_DATA_CHART where mtr_id="+mtrId+" and mdc_key='"+mdc_key+"'";
			//String sql="SELECT * FROM "+SCHEMA+".MISS_DATA_CHART";
			 pst = con.prepareStatement(sql);
			 result = pst.executeQuery();
				if(result!=null)					
						while (result.next()) { 
							xmlData=result.getString("MDC_DATA");
							swfName=result.getString("MDC_SWF_NAME");
							mdcType=result.getString("MDC_TYPE");
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
		data[0]=xmlData;
		data[1]=swfName;
		/*/usr/local/data/FusionChart/FusionChartsSuiteEval/FusionCharts XT
		/usr/local/data/FusionChart/FusionChartsSuiteEval/FusionMaps XT
		/usr/local/data/FusionChart/FusionChartsSuiteEval/FusionWidgets XT
		/usr/local/data/FusionChart/FusionChartsSuiteEval/PowerCharts XT*/
		data[2]=(mdcType!=null&&mdcType.length()>0)?mdcType:"FusionCharts";
		
		return data;
	}

}
