// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:27 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ResultController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.exam.form.ResultForm;
import th.co.aoe.makedev.missconsult.exam.mail.MailRunnable;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/result"})
@SessionAttributes(value={"resultForm"})
public class ResultController
{
	  private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	  private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy"); 
	  private static final String MAIL_SERVER = "mail.missconsult.com";
	  private static final String MAIL_PROTOCAL = "smtp";
  /*  @Autowired
    public ResultController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired ResultController #######################");
        this.missExamService = missExamService;
    }
*/
	  private static ResourceBundle bundle;
		static{
			bundle =  ResourceBundle.getBundle( "config" );				
		}
    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String init(Model model)
    {
    	 List missSeries= missExamService.listMissSery();
    	  model.addAttribute("missSeries",missSeries);
    	 
    	  ResultForm resultForm = new ResultForm();
    	  resultForm.getMissTestResult().getPagging().setPageSize(3);
    	 
    	  if(missSeries!=null && missSeries.size()>0){
          		resultForm.getMissTestResult().setMsId(((MissSery)missSeries.get(0)).getMsId());
    	  }
    		MissCandidate missCandidate =new MissCandidate();
        	MissAccount missAccount=new MissAccount();
        	missAccount.setMaName(resultForm.getMcaCompanyName());
        	missCandidate.setMissAccount(missAccount);
        	missCandidate.setMcaUsername(resultForm.getMcaUsername());
        	missCandidate.setMcaFirstName(resultForm.getMcaFirstName());
        	missCandidate.setMcaLastName(resultForm.getMcaLastName());
        	missCandidate.setMcaPosition(resultForm.getMcaPosition());
        	missCandidate.setMcaDepartment(resultForm.getMcaDepartment());
        	missCandidate.setMissAccount(missAccount);
        	
        	resultForm.getMissTestResult().setMissCandidate(missCandidate);
         VResultMessage vresultMessage = missExamService.searchMissTestResult(resultForm.getMissTestResult());
         model.addAttribute("missTestResults", vresultMessage.getResultListObj().get(0));
          resultForm.getPaging().setPageSize(3);
          resultForm.setPageCount(IMakeDevUtils.calculatePage(resultForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
         /* List<String> axisHeaders=new ArrayList<String>(4);
          axisHeaders.add("Fa");
          axisHeaders.add("Im");
          axisHeaders.add("Pe");
          axisHeaders.add("Ju");*/ 
          model.addAttribute("axisHeaders", vresultMessage.getResultListObj().get(1));
          model.addAttribute("resultForm", resultForm);
        return "exam/template/testResultSearch";
    }
    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doSearch(HttpServletRequest request, @ModelAttribute(value="resultForm") ResultForm resultForm, BindingResult result, Model model)
    {
        String mode = resultForm.getMode();
       // String missExam_selectboxes[] = request.getParameterValues("missExam_selectbox");
      /*  private Long msId;
        private Timestamp mtrEndTime;
        private Timestamp mtrStartTime;
    	private MissCandidate missCandidate;
    	private String mcaCompanyName;*/
    	if(resultForm.getMcaSeries()!=null){
    		resultForm.getMissTestResult().setMsId(Long.parseLong(resultForm.getMcaSeries()));
    	}
    	 
    	if(resultForm.getTestFrom()!=null && resultForm.getTestFrom().trim().length()>0){
    	            try
    	            {
    	            	Timestamp fromTS1 = new Timestamp(format1.parse(resultForm.getTestFrom()+" 00:00:00").getTime()); 
    	            	resultForm.getMissTestResult().setMtrStartTime(fromTS1);
    	            }
    	            catch(ParseException e)
    	            {
    	                e.printStackTrace();
    	            }
    	}
    	if(resultForm.getTestTo()!=null && resultForm.getTestTo().trim().length()>0){
    	            try
    	            {
    	            	Timestamp fromTS1 = new Timestamp(format1.parse(resultForm.getTestTo()+" 23:59:59").getTime()); 
    	            	resultForm.getMissTestResult().setMtrEndTime(fromTS1);
    	            }
    	            catch(ParseException e)
    	            {
    	                e.printStackTrace();
    	            }
    	}
    	MissCandidate missCandidate =new MissCandidate();
    	MissAccount missAccount=new MissAccount();
    	missAccount.setMaName(resultForm.getMcaCompanyName());
    	missCandidate.setMissAccount(missAccount);
    	missCandidate.setMcaUsername(resultForm.getMcaUsername());
    	missCandidate.setMcaFirstName(resultForm.getMcaFirstName());
    	missCandidate.setMcaLastName(resultForm.getMcaLastName());
    	missCandidate.setMcaPosition(resultForm.getMcaPosition());
    	missCandidate.setMcaDepartment(resultForm.getMcaDepartment());
    	missCandidate.setMissAccount(missAccount);
    	
    	resultForm.getMissTestResult().setMissCandidate(missCandidate);
        if(mode != null && mode.equals("deleteItems"))
        {
        	resultForm.getMissTestResult().setMtrIds(resultForm.getMtrIdArray());
        //    missExamService.deleteMissSery(resultForm.getMissSery(), "deleteMissSeryItems");
            resultForm.getPaging().setPageNo(1);
        } else
        if(mode != null && mode.equals("delete")){
         //   missExamService.deleteMissSery(resultForm.getMissSery(), "deleteMissSery");
            resultForm.getPaging().setPageNo(1);
        }else
      /*  if(mode != null && mode.equals("doBack"))
        {
            if(model.containsAttribute("resultForm"))
            	resultForm = (SeriesForm)model.asMap().get("resultForm");
            else
            	resultForm = new SeriesForm();
            missExam_selectboxes = resultForm.getMissExam_selectbox();
        }*/
       // resultForm.setMissExam_selectbox(missExam_selectboxes);
       // resultForm.getMissSery().setMeIds(missExam_selectboxes);
        resultForm.getPaging().setPageSize(3);
        resultForm.getMissTestResult().setPagging(resultForm.getPaging());
        VResultMessage vresultMessage = missExamService.searchMissTestResult(resultForm.getMissTestResult());
      
        resultForm.setPageCount(IMakeDevUtils.calculatePage(resultForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("missTestResults", vresultMessage.getResultListObj().get(0));
        model.addAttribute("missSeries", missExamService.listMissSery());
       /* List<String> axisHeaders=new ArrayList<String>(4);
        axisHeaders.add("Fa");
        axisHeaders.add("Im");
        axisHeaders.add("Pe");
        axisHeaders.add("Ju"); */
        model.addAttribute("axisHeaders", vresultMessage.getResultListObj().get(1));
        model.addAttribute("resultForm", resultForm);
        return "exam/template/testResultSearch";
    }
    @RequestMapping(value={"/viewAnswer/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getToDoList(@PathVariable String mtrId, Model model)
    {
        logger.debug((new StringBuilder("testtttttttttt")).append(missExamService).toString());
        model.addAttribute("aoe", "chatchai");
        System.out.println("aoee");
        return "exam/template/viewTestResult";
    }

    @RequestMapping(value={"/report/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm()
    {
        return "exam/template/testResultReport";
    }
    @RequestMapping(value={"/sendmail"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String sendmail(HttpServletRequest request, @ModelAttribute(value="resultForm") ResultForm resultForm, BindingResult result, Model model)
    {
    	logger.debug("==========================resultForm="+resultForm);
    	logger.debug("getMailAttachReport="+resultForm.getMailAttachReport());
    	logger.debug("getMailbcc="+resultForm.getMailbcc());
    	logger.debug("getMailcc="+resultForm.getMailcc());
    	logger.debug("getMailDecision="+resultForm.getMailDecision());
    	logger.debug("getMailMessage="+resultForm.getMailMessage());
    	logger.debug("getMailReactive="+resultForm.getMailReactive());
    	List recipientsToTeams = new ArrayList(1);
    	recipientsToTeams.add("chatchai@lansingbs.com" );
    	String subject="Test";
    	StringBuffer mailMessageBody=new StringBuffer("");
    	mailMessageBody.append("Chatchai Test");
    	String personal_name= "MissConsult Exam";
    	String personal_email="missconsultexam@missconsult.com";
    	MailRunnable mailRunnableToTeam = new MailRunnable(
				MAIL_PROTOCAL, MAIL_SERVER, personal_email
						, "#missc$%", "1",
				recipientsToTeams, subject,
				mailMessageBody.toString(), "99",personal_name);
		Thread mailThreadToTeam = new Thread(
				mailRunnableToTeam);
		mailThreadToTeam.start();
    	 return "exam/template/testSendmail";
    }
    @RequestMapping(value={"/response/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String response(@PathVariable Long mtrId, Model model)
    {
    	MissTestResult missTestResult=missExamService.findMissTestResultById(mtrId);
    	logger.debug("missTestResult=>"+missTestResult);
    	/*List recipientsToTeams = new ArrayList(1);
    	recipientsToTeams.add("chatchai@vlink.co.th" );
    	String subject="Test";
    	StringBuffer mailMessageBody=new StringBuffer("");
    	mailMessageBody.append("Chatchai Test");
    	String personal_name= "personal Chatchai";
    	String personal_email="chatchai@vlink.co.th";
    	MailRunnable mailRunnableToTeam = new MailRunnable(
				MAIL_PROTOCAL, MAIL_SERVER, personal_email
						, "015482543a6ee6acoder", "1",
				recipientsToTeams, subject,
				mailMessageBody.toString(), "99",personal_name);
		Thread mailThreadToTeam = new Thread(
				mailRunnableToTeam);
		mailThreadToTeam.start();*/
    	ResultForm resultForm=new ResultForm();
    	resultForm.setMissTestResult(missTestResult);
    	model.addAttribute("resultForm", resultForm);
        return "exam/template/testResponse";
    }

    @RequestMapping(value={"/summary/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String viewAnswer()
    {
        return "exam/template/nopage";
    }
    // testPDF?mtrId=8&meId=14&msId=9&mcaId=22
    @RequestMapping(value={"/testPDF"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public void testPDF(HttpServletRequest request, HttpServletResponse response ,@RequestParam(required=false) Long mtrId,
    		@RequestParam(required=false) Long meId,@RequestParam(required=false) Long msId,@RequestParam(required=false) Long mcaId){
    	logger.debug(" testPDF======>  mtrId="+ mtrId+",meId="+meId+",msId="+msId+",mcaId="+mcaId);
    	Context ctx =null;
		Connection con = null;
    	try{
    	/* MissTestResult missTestResult=missExamService.findMissTestResultById(mtrId);
    	 MissTestResult report=new MissTestResult(); 
			report.setMeId(Long.valueOf(missTestResult.getLieScore())); */
			/*byte[] imageInByte=null;
			BufferedImage originalImage = null;
			ByteArrayOutputStream baos =null;
			try{
				originalImage=ImageIO.read(new File("/root/Desktop/lambo.jpg"));
			 
				baos= new ByteArrayOutputStream();
				ImageIO.write( originalImage, "jpg", baos );
				baos.flush();
				imageInByte = baos.toByteArray();
				baos.close(); 
				}catch(IOException e){
					e.printStackTrace();
					//System.out.println(e.getMessage());
				} 
			String encodedImgStr = org.apache.commons.codec.binary.StringUtils.newStringIso8859_1(org.apache.commons.codec.binary.Base64
					.encodeBase64(imageInByte));
			report.setServiceName(encodedImgStr);*/
			//report.setMtrStatus("Lie Score:\n 45 Mark 90\n(Perface Score)");
			/*report.setMtrStatus("Lie Score:\n "+missTestResult.getLieScore());
			report.setMtrResultCode("Line Score");
			 MissTestResult report2=new MissTestResult(); 
				report2.setMeId(Long.valueOf(missTestResult.getTotalScore()));*/
			/*try{
				originalImage=ImageIO.read(new File("/root/Desktop/lum.jpg"));
			 
				baos= new ByteArrayOutputStream();
				ImageIO.write( originalImage, "jpg", baos );
				baos.flush();
				imageInByte = baos.toByteArray();
				baos.close(); 
				}catch(IOException e){
					e.printStackTrace();
					//System.out.println(e.getMessage());
				} 
			encodedImgStr = org.apache.commons.codec.binary.StringUtils.newStringIso8859_1(org.apache.commons.codec.binary.Base64
					.encodeBase64(imageInByte));
			report2.setServiceName(encodedImgStr);*/
			//report2.setMtrStatus("Honest Score:\n 85 Mark 108\n(Definietly Want)");
			/*report2.setMtrStatus("Honest Score:\n "+missTestResult.getTotalScore());
			report2.setMtrResultCode("Honest Score");*/
			
		
		//	logger.debug("encodedImgStr============>"+encodedImgStr);
		/*	List<MissTestResult> newList=new ArrayList<MissTestResult>();
			newList.add(report);
			newList.add(report2);*/
		// JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(newList); 
		
		 MissSeriesAttach missSeriesAttach=missExamService.findMissSeriesAttachSearch("template", msId, null, null);
		 
		 String  reportPath=  bundle.getString("templatePath")+missSeriesAttach.getMsatPath();  
		 JasperPrint jasperPrint=null;
		 
		 Map p =new HashMap();
		/* p.put("SubDataSource", beanCollectionDataSource);
		 p.put("name",missTestResult.getMissCandidate().getMcaFirstName()+" "+missTestResult.getMissCandidate().getMcaLastName());
		 p.put("position",missTestResult.getMissCandidate().getMcaPosition());
		 String testDate="";
		 
		 p.put("tel",missTestResult.getMissCandidate().getMcaPhone());
		
		 if(missTestResult.getMtrTestDate() != null )
			testDate=format2.format(missTestResult.getMtrTestDate());
		 p.put("testDate",testDate);
		 */
		  // set Lie Score , Honest Score
		// missExamService.findMissTestById(long1)SeriesAttachSearch
		/* p.put("lieScore", missTestResult.getLieScore());
		 p.put("honestScore", missTestResult.getTotalScore());*/
		 p.put("mtrId",mtrId);
		 //p.put("lieScore", missTestResult.get)
	 
		 /*DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue("Java", new Double(43.2));
			dataset.setValue("Visual Basic", new Double(10.0));
			dataset.setValue("C/C++", new Double(17.5));
			dataset.setValue("PHP", new Double(32.5));
			dataset.setValue("Perl", new Double(1.0));

			JFreeChart chart = 
				ChartFactory.createPieChart3D(
					"Pie Chart 3D Demo 1",
					dataset,
					true,
					true,
					false
					);
 
			PiePlot3D plot = (PiePlot3D) chart.getPlot();
			plot.setStartAngle(290);
			plot.setDirection(Rotation.CLOCKWISE);
			plot.setForegroundAlpha(0.5f);
			plot.setNoDataMessage("No data to display");
			
			 
			 p.put("Chart", new JCommonDrawableRenderer(chart));*/
			//p.put("Chart",new JFreeChartRenderer(chart));
		 
			try {
				ctx = new InitialContext();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			DataSource ds = null;
			try { 
				ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/missdb");
				//ds = (DataSource)ctx.lookup("jdbc/localOracle");
				//System.out.println("chatchai debug ds="+ds);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}               
			org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
			//com.ibm.ws.rsadapter.jdbc.WSJdbcDataSource basicDs = (com.ibm.ws.rsadapter.jdbc.WSJdbcDataSource)ds;
			
		
			try {
				con = basicDs.getConnection();//("oracle", "password");//Connection();
				//con = ds.getConnection();//("oracle", "password");//Connection();
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}           
			 
		try {
			//jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(),new JREmptyDataSource());
			//String defaultPDFFont = "Arial";

			
			
			//jasperPrint = JasperFillManager.fillReport(reportPath, p,new JREmptyDataSource());
			jasperPrint = JasperFillManager.fillReport(reportPath, p, con);
			/*jasperPrint.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
			jasperPrint.setProperty("net.sf.jasperreports.default.font.name", defaultPDFFont);*/
			 
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   //  String fileName="เทส.pdf";
		 response.addHeader("Content-disposition", "attachment; filename=report.pdf");  
		/* response.setHeader("Content-Disposition", "inline; filename="
					+ fileName);*/
	       ServletOutputStream servletOutputStream=null;
		try {
			servletOutputStream = response.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	       try {
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      // FacesContext.getCurrentInstance().responseComplete(); 
	       try {
			servletOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       try {
			servletOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}finally{
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			}	
		}
	   
    }
    /**
	 * Processes the download for Excel format
	 */
	public void downloadXLS(HttpServletResponse response) throws  ClassNotFoundException, JRException {

		logger.debug("Downloading Excel report");
	/*	
		// Retrieve our data source
		SalesDAO datasource = new SalesDAO();
		JRDataSource ds = datasource.getDataSource();

		// Create our report layout
		// We delegate the reporting layout to a custom ReportLayout instance
		// The ReportLayout is a wrapper class I made. Feel free to remove or modify it
		ReportLayout layout = new ReportLayout();
		DynamicReport dr = layout.buildReportLayout();

		// params is used for passing extra parameters like when passing
		// a custom datasource, such as Hibernate datasource
		// In this application we won't utilize this parameter
		HashMap params = new HashMap(); 
		
		// Compile our report layout
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		// Creates the JasperPrint object
		// It needs a JasperReport layout and a datasource
		JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);

		// Create our output byte stream
		// This is the stream where the data will be written
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// Export to output stream
		// The data will be exported to the ByteArrayOutputStream baos
		// We delegate the exporting  to a custom Exporter instance
		// The Exporter is a wrapper class I made. Feel free to remove or modify it
		Exporter exporter = new Exporter();
		exporter.export(jp, baos);

		// Set our response properties
		// Here you can declare a custom filename
		String fileName = "SalesReport.xls";
		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		// Make sure to set the correct content type
		// Each format has its own content type
		response.setContentType("application/vnd.ms-excel");
		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);*/
	}
    /**
	 * Writes the report to the output stream
	 */
	private void writeReportToResponseStream(HttpServletResponse response,
			ByteArrayOutputStream baos) {
		
		logger.debug("Writing report to the stream");
		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			baos.writeTo(outputStream);
			// Flush the stream
			outputStream.flush();

		} catch (Exception e) {
			logger.error("Unable to write report to the output stream");
		}
	}

    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
