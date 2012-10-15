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
 * Servlet implementation class RenderImageServlet
 */
@WebServlet("/render")
public class RenderImageServlet extends HttpServlet {
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
    public RenderImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession(true);
		 String xmlData="";
		 String[] mtrIds= request.getParameter("mtrId").split("_"); 
		 String mtrId=mtrIds[0];
		 String mdc_key=mtrIds[1];//request.getParameter("key");
		  mtrId="20";
		 mdc_key="chart1"; 
		 String swfName="";
		 String width=mtrIds[2];
		 String height=mtrIds[3];
		 
			Connection con = null; 
			org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs =null;
			 PreparedStatement pst = null;
			 ResultSet result= null;
			//DataSource ds = null; 
			/*try { 
				ds = (datasource)ctx.lookup("java:/comp/env/jdbc/missdb");
				//ds = (datasource)ctx.lookup("jdbc/localoracle");
				//system.out.println("chatchai debug ds="+ds);
			} catch (namingexception e) {
				// todo auto-generated catch block
				e.printstacktrace();
			}*/               
			
			try {
				//System.out.println(this.getClass());
				//System.out.println("ds=="+ds);
				basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
				con = basicDs.getConnection();//("oracle", "password");//Connection();
				//con = ds.getConnection();//("oracle", "password");//Connection();
				//System.out.println("basicDs="+basicDs);
				//System.out.println("con="+con);
				String sql="SELECT * FROM "+SCHEMA+".MISS_DATA_CHART where mtr_id="+mtrId+" and mdc_key='"+mdc_key+"'";
				//System.out.println("sql\n"+sql);
				 pst = con.prepareStatement(sql);
				 result = pst.executeQuery();
					if(result!=null)					
							while (result.next()) { 
								xmlData=result.getString("MDC_DATA");
								swfName=result.getString("MDC_SWF_NAME");
								//width=result.getString("MDC_WIDTH");
								//height=result.getString("MDC_HEIGHT");
							}
			
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				/*if (basicDs != null) {
					try {
						if(!basicDs.isClosed())
							basicDs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}*/
				if (con != null) {
					try {
						if(result!=null){
							//System.out.println("result is Closed="+result.isClosed());
							if(!result.isClosed()){
								result.close();
								//System.out.println("result is Closed affter close="+result.isClosed());
								result=null;
							}
							
						}
						if (pst != null) {
							//System.out.println("pst is Closed="+pst.isClosed());
							if(!pst.isClosed()){
								pst.close();			
								//System.out.println("pst is Closed affter close="+pst.isClosed());
								pst = null;
							} 
							
						}
						//System.out.println("connection is Closed="+con.isClosed());
						if(!con.isClosed());
							con.close();
						//System.out.println("connection is Closed affter close="+con.isClosed());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
				/*if (ctx != null) {
					try {
						ctx.close();
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}	*/
			
			}
		 /*{
			    'chart': {
			        'caption': 'Fruit Production for March',
			        'subcaption': 'in Millions',
			        'yaxisname': 'Quantity',
			        'xaxisname': 'Fruit',
			        'alternatevgridcolor': 'AFD8F8',
			        'basefontcolor': '114B78',
			        'tooltipbordercolor': '114B78',
			        'tooltipbgcolor': 'E7EFF6',
			        'plotborderdashed': '0',
			        'plotborderdashlen': '2',
			        'plotborderdashgap': '2',
			        'useroundedges': '1',
			        'showborder': '0',
			        'bgcolor': 'FFFFFF,FFFFFF'
			    },
			    'data': [
			        {
			            'label': 'Orange',
			            'value': '23',
			            'color': 'AFD8F8'
			        },
			        {
			            'label': 'Apple',
			            "value'': '12',
			            'color': 'F6BD0F'
			        },
			        {
			            'label': 'Banana',
			            'value': '17',
			            'color': '8BBA00'
			        },
			        {
			            'label': 'Mango',
			            'value': '14',
			            'color': 'A66EDD'
			        },
			        {
			            'label': 'Litchi',
			            'value': '12',
			            'color': 'F984A1'
			        }
			    ]
			}*/
	        //String xmlData="<chart yAxisName='Score' caption='Student' useRoundEdges='1' bgColor='FFFFFF,FFFFFF' showBorder='0' exportEnabled='1' exportAtClient='1' exportAction='download' exportHandler='http://172.16.108.24:8080/TestFusion/FCExporter.jsp' exportFileName='MyFile'>" +
		/* String xmlData="<chart yaxisname='Score' caption='Student'   useroundedges='1' bgcolor='FFFFFF,FFFFFF' showborder='0' >"+
	        		"<set label='Nui' value='21'  /><set label='Ton' value='20' /><set label='Non' value='9' /><set label='Boom' value='6' /><set label='La' value='3' />" +
	        		"<set label='Aoae' value='2' /></chart>";
		 */
		 /* xmlData="<chart caption=\"Fruit Production for March\" subCaption=\"(in Millions)\" yAxisName=\"Quantity\" xAxisName=\"Fruit\" alternateVGridColor=\"AFD8F8\" baseFontColor=\"114B78\" toolTipBorderColor=\"114B78\" toolTipBgColor=\"E7EFF6\" useRoundEdges=\"1\" showBorder=\"0\" bgColor=\"FFFFFF,FFFFFF\">"+
					"<set label=\"Orange\" value=\"23\" color=\"AFD8F8\"/>"+
					"<set label=\"Apple\" value=\"12\" color=\"F6BD0F\"/> "+
					"<set label=\"Banana\" value=\"17\" color=\"8BBA00\"/> "+
					"<set label=\"Mango\" value=\"14\"  color=\"A66EDD\"/> "+
					"<set label=\"Litchi\" value=\"12\"  color=\"F984A1\"/>"+
				"</chart>";*/
			/*<chart  alternateVGridColor="AFD8F8" baseFontColor="114B78" toolTipBorderColor="114B78" toolTipBgColor="E7EFF6" useRoundEdges="1" showBorder="0" bgColor="FFFFFF,FFFFFF"><set label="Orange" value="23"/><set label="Apple" value="12"/><set label="Banana" value="17"/><set label="Mango" value="14"/><set label="Litchi" value="12"/></chart>*/

			//<chart  alternateVGridColor="AFD8F8" baseFontColor="114B78" toolTipBorderColor="114B78" toolTipBgColor="E7EFF6" useRoundEdges="1" showBorder="0" bgColor="FFFFFF,FFFFFF"><set label="Imagineative" value="9" color="#54E8FF"/><set label="Factual" value="10"  color="#54E8FF"/><set label="Perceiving" value="9" color="#BBFF00"/><set label="Judging" value="13" color="#BBFF00"/><set label="Assertive" value="18" color="#D4D300"/><set label="Passive" value="7" color="#D4D300"/><set label="Introvert" value="5" color="#4891A2"/><set label="Extravert" value="15" color="#4891A2"/></chart>
			
			  
			
			
			
		 
			session.setAttribute("XMLDATA", xmlData);
	        session.setAttribute("swfName", swfName);
	        session.setAttribute("width", width);
	        session.setAttribute("height", height);
	        //request.getServletContext().getRequestDispatcher(arg0)
	        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/chart.jsp");
	        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	 
}
