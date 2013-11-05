// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:27 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ResultController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.exam.form.ResultForm;
import th.co.aoe.makedev.missconsult.exam.mail.MailRunnableAttach;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissReportAttach;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.MissTestShow;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@RequestMapping(value={"/result"}) 
@SessionAttributes(value={"UserMissContact","resultForm"})
public class ResultController
{
	private static int PAGE_SIZE=20;
	  private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//	  private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy"); 
	  private static SimpleDateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	  private static String MAIL_SERVER = "";
	  private static String MAIL_PROTOCAL = "";
	  private static String MAIL_PORT="";
	  private static String MAIL_USE_AUTHEN="";
	  private static String MAIL_EMAIL="";
	  private static String MAIL_PASSWORD=""; 
	  private static String MAIL_PERSONAL_NAME="";
	  private static String MAIL_TLS="";
	 /* mail.protocal=smtp
			  mail.host=mail.missconsult.com
			  #1=use,0=not use
			  mail.useAuthen=1
			  mail.email=missconsultexam@missconsult.com
			  mail.password=#missc$%
			  mail.personal_name=Missconsult Exam*/
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
			MAIL_SERVER=bundle.getString("mail.host");
			MAIL_PROTOCAL=bundle.getString("mail.protocal");
			MAIL_USE_AUTHEN=bundle.getString("mail.useAuthen");
			MAIL_PORT=bundle.getString("mail.port");
			MAIL_EMAIL=bundle.getString("mail.email");
			MAIL_PASSWORD=bundle.getString("mail.password");
			MAIL_PERSONAL_NAME=bundle.getString("mail.personal_name");
			MAIL_TLS=bundle.getString("mail.TLS");
		}
   @SuppressWarnings("rawtypes")
@RequestMapping(value={"/compare/{msId}/{mtrIds}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public  @ResponseBody Object[]  compare(Model model,@PathVariable Long msId,@PathVariable String mtrIds)
		    {
			 //Gson gson=new Gson();
	   //MissTestResult[] missTestResults=new MissTestResult[2];
	   Object[] missTestResults=new Object[3];
	   MissTestResult missTestResult1 =null;
	   MissTestResult missTestResult2  =null;
	  String[] mtrId_array= mtrIds.split("_");
	  MissTestResult missTestResult=new MissTestResult();
	  missTestResult.setMsId(msId);
	  missTestResult.setMtrIds(mtrId_array[0]);
	   VResultMessage vresultMessage = missExamService.searchMissTestResult(missTestResult);
	   missTestResult1 = (MissTestResult)((java.util.ArrayList)vresultMessage.getResultListObj().get(0)).get(0);
	   
	   missTestResult.setMtrIds(mtrId_array[1]);
	   vresultMessage = missExamService.searchMissTestResult(missTestResult);
	   missTestResult2 = (MissTestResult)((java.util.ArrayList)vresultMessage.getResultListObj().get(0)).get(0);
	     
	  // model.addAttribute("axisHeaders",);
	   missTestResults[0]=missTestResult1;
	   missTestResults[1]=missTestResult2;
	   missTestResults[2]= vresultMessage.getResultListObj().get(1);
	 
	return missTestResults;
	}
    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String init(Model model,SecurityContextHolderAwareRequestWrapper srequest)
    {
    	 @SuppressWarnings("rawtypes")
		List missSeries=null;// missExamService.listMissSery();
    	 Long maId=null;
    	 if(model.containsAttribute("UserMissContact")){
         	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
         //	missSeries=
         	//List<MissAccountSeriesMap> missAccountSeriesMaps
         	maId=missContact.getMcontactRef();
         	missSeries= missExamService.findMissAccountSeriesMapByRole(maId,missContact.getRcId());
         }
    	
    	  model.addAttribute("missSeries",missSeries);
    	  int roleMC=0;
    	  if(srequest.isUserInRole("ROLE_MANAGE_MISSCONSULT"))
    		  roleMC=1;
    	  
    	//  SecurityContextHolder.getContext().getAuthentication().getAuthorities().;
    	  ResultForm resultForm = new ResultForm();
    	  resultForm.getMissTestResult().getPagging().setPageSize(PAGE_SIZE);
    	 Long msId=-1l;
    	  if(missSeries!=null && missSeries.size()>0){
    		  msId=((MissAccountSeriesMap)missSeries.get(0)).getMissSery().getMsId();
    	  }
    	  resultForm.getMissTestResult().setMsId(msId);
    		MissCandidate missCandidate =new MissCandidate();
        	MissAccount missAccount=new MissAccount();
        	missAccount.setMaName(resultForm.getMcaCompanyName());
        	missAccount.setMaId(maId);
        	missCandidate.setMissAccount(missAccount);
        	missCandidate.setMcaUsername(resultForm.getMcaUsername());
        	missCandidate.setMcaFirstName(resultForm.getMcaFirstName());
        	missCandidate.setMcaLastName(resultForm.getMcaLastName());
        	missCandidate.setMcaPosition(resultForm.getMcaPosition());
        	missCandidate.setMcaDepartment(resultForm.getMcaDepartment());
        	missCandidate.setMissAccount(missAccount);
        	
        	resultForm.getMissTestResult().setMissCandidate(missCandidate);
        	resultForm.getMissTestResult().setRoleMC(roleMC);
        	resultForm.getMissTestResult().getPagging().setOrderBy("candidate.MCA_USERNAME");
        	resultForm.getMissTestResult().getPagging().setSortBy("asc"); 
         VResultMessage vresultMessage = missExamService.searchMissTestResult(resultForm.getMissTestResult());
         model.addAttribute("missTestResults", vresultMessage.getResultListObj().get(0));
          resultForm.getPaging().setPageSize(PAGE_SIZE);
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
    	Long maId=null;
    	 @SuppressWarnings("rawtypes")
		List missSeries=null;// missExamService.listMissSery();
     	 if(model.containsAttribute("UserMissContact")){
          	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
          	maId=missContact.getMcontactRef();
          	missSeries= missExamService.findMissAccountSeriesMapByRole(maId,missContact.getRcId());
          }
         model.addAttribute("missSeries", missSeries);
        String mode = resultForm.getMode();
        int roleMC=0;
  	  if(request.isUserInRole("ROLE_MANAGE_MISSCONSULT"))
  		  roleMC=1;
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
    	missAccount.setMaId(maId);
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
        } else if(mode != null && mode.equals("ignoreItems"))
        {
        	resultForm.getMissTestResult().setMtrIds(resultForm.getMtrIdArray());
         //   missExamService.ignoreItems(resultForm.getMissSery(), "igMissSeryItems");
            missExamService.updateStatusMissTestResult(resultForm.getMtrIdArray(), "mtrRespondedStatus", "2");
            resultForm.getMissTestResult().setMtrIds(null);
          //  resultForm.getPaging().setPageNo(1);
        }else 
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
        resultForm.getPaging().setPageSize(PAGE_SIZE);
        resultForm.getMissTestResult().setPagging(resultForm.getPaging());
        resultForm.getMissTestResult().setRoleMC(roleMC);
        VResultMessage vresultMessage = missExamService.searchMissTestResult(resultForm.getMissTestResult());
      
        resultForm.setPageCount(IMakeDevUtils.calculatePage(resultForm.getPaging().getPageSize(), Integer.parseInt(vresultMessage.getMaxRow())));
        model.addAttribute("missTestResults", vresultMessage.getResultListObj().get(0));
       
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
    public String viewAnswer(@PathVariable String mtrId, Model model)
    {
        logger.debug((new StringBuilder("testtttttttttt")).append(missExamService).toString());
        model.addAttribute("aoe", "chatchai");
        return "exam/template/viewTestResult";
    }

    @RequestMapping(value={"/report/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm()
    {
        return "exam/template/testResultReport";
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
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
    	
		List recipientsTo= new ArrayList(1);
    	recipientsTo.add(resultForm.getMissTestResult().getMissCandidate().getMcaEmail());
    	String subject="Test Response";
    	
		List recipientsCC= null;
    	if(resultForm.getMailcc()!=null && resultForm.getMailcc().trim().length()>0){
    		String[] recipientStr=resultForm.getMailcc().trim().split(",");
    		recipientsCC=new ArrayList(recipientStr.length);
    		for (int i = 0; i < recipientStr.length; i++) {
    			recipientsCC.add(recipientStr[i]);
			} 
    	}
    	List recipientsBCC= null;
    	if(resultForm.getMailbcc()!=null && resultForm.getMailbcc().trim().length()>0){
    		String[] recipientStr=resultForm.getMailbcc().trim().split(",");
    		recipientsBCC=new ArrayList(recipientStr.length);
    		for (int i = 0; i < recipientStr.length; i++) {
    			recipientsBCC.add(recipientStr[i]);
			} 
    	}
    	//byte [] fileSize=null;
    	/*if(resultForm.getMailAttachReport()!=null && resultForm.getMailAttachReport().equals("1")){
    		fileSize=getFileSize(resultForm.getMissTestResult().getMsId(),resultForm.getMissTestResult().getMtrId());
    	}*/
    	
    	/*if(resultForm.getMailAttachReport()!=null && resultForm.getMailAttachReport().equals("1")
    			&& resultForm.getMsOrder()!=null && resultForm.getMsOrder().length()>0 
    			&& resultForm.getMraLang()!=null && resultForm.getMraLang().length()>0 ){
    		fileSize=getFileSize(resultForm.getMissTestResult().getMsId(),resultForm.getMissTestResult().getMtrId(),Long.valueOf(resultForm.getMsOrder()),
    				resultForm.getMraLang());
    		//  private byte[] getFileSize(Long msId,Long mtrId,Long msOrder,String mraLang){
    	}*/
    //	StringBuffer mailMessageBody=new StringBuffer("");
    //	mailMessageBody.append("Test Response");  
    	MailRunnableAttach mailRunnableToTeam = new MailRunnableAttach(missExamService,resultForm.getMissTestResult().getMtrId(),resultForm.getMissTestResult().getMsId(),
    			resultForm.getMailAttachReport(),resultForm.getMsOrder(),resultForm.getMraLang(),bundle.getString("reportTemplatePath"),
				MAIL_PROTOCAL, MAIL_SERVER, MAIL_EMAIL
						, MAIL_PASSWORD, MAIL_USE_AUTHEN,
				recipientsTo, subject,
				resultForm.getMailMessage(), "99",MAIL_PERSONAL_NAME,MAIL_PORT,recipientsCC,recipientsBCC,MAIL_TLS);
		Thread mailThreadToTeam = new Thread(
				mailRunnableToTeam);
		mailThreadToTeam.start(); 
		if(resultForm.getMailReactive()!=null && resultForm.getMailReactive().equals("1")){
    		//cal reactivate
			MissSery sery=new MissSery();
			sery.setMsId(resultForm.getMissTestResult().getMsId());
			resultForm.getMissTestResult().getMissCandidate().setSection("2");
			resultForm.getMissTestResult().getMissCandidate().setMissSery(sery);
			missExamService.updateMissCandidate(resultForm.getMissTestResult().getMissCandidate());
    	}
		missExamService.updateStatusMissTestResult(resultForm.getMissTestResult().getMtrId(), "mtrRespondedStatus", "1");
    	 return "exam/template/testSendmail";
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	//private byte[] getFileSize(Long msId,Long mtrId){
    private byte[] getFileSize(Long msId,Long mtrId,Long msOrder,String mraLang){
    	byte [] fileSize=null;
    	Context ctx =null;
		Connection con = null;
    	try{
    	 MissReportAttach missReportAttach =missExamService.findMissReportAttachById(msId, msOrder, mraLang, null);
		 //MissSeriesAttach missSeriesAttach=missExamService.findMissSeriesAttachSearch("template", msId, null, null);
		 //String  reportPath=  bundle.getString("templatePath")+missSeriesAttach.getMsatPath();  
    	 String  reportPath=  bundle.getString("reportTemplatePath")+missReportAttach.getMraPath();
		 JasperPrint jasperPrint=null;
		 
		 Map p =new HashMap();
		 p.put("mtrId",mtrId+"");
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
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}               
			org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
			try {
				con = basicDs.getConnection();//("oracle", "password");//Connection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}           
		try {
			jasperPrint = JasperFillManager.fillReport(reportPath, p, con);
		 
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
       try {
    	   fileSize=JasperExportManager.exportReportToPdf(jasperPrint);
    	   
       } catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      // FacesContext.getCurrentInstance().responseComplete(); 
	   
	       
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
    	return fileSize;
    }
    @RequestMapping(value={"/response/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String response(@PathVariable Long mtrId, Model model)
    {
    	MissTestResult missTestResult=missExamService.findMissTestResultById(mtrId);
    	logger.debug("missTestResult=>"+missTestResult);
    	ResultForm resultForm=new ResultForm();
    	resultForm.setMissTestResult(missTestResult);
    	model.addAttribute("resultForm", resultForm);
        return "exam/template/testResponse";
    }
    @RequestMapping(value={"/fusionchart/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String fusionchart(@PathVariable Long mtrId, Model model)
    {
    	/*MissTestResult missTestResult=missExamService.findMissTestResultById(mtrId);
    	logger.debug("missTestResult=>"+missTestResult);
    	ResultForm resultForm=new ResultForm();
    	resultForm.setMissTestResult(missTestResult);
    	model.addAttribute("resultForm", resultForm);*/
        return "exam/template/fusionchart";
    }

 /*   @RequestMapping(value={"/summary/{mtrId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String viewAnswer()d
    {
        return "exam/template/nopage";
    }*/  
    // testPDF?mtrId=8&meId=14&msId=9&mcaId=22
   // testPDF?mtrId="+_mtrId+"&meId="+_meId+"&msId="+_msId+"&mcaId="+_mcaId+"&msOrder="+_msOrder+"&mraLang="+_mraLang;
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value={"/testPDF"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public void testPDF(HttpServletRequest request, HttpServletResponse response ,@RequestParam(required=false) Long mtrId,
    		@RequestParam(required=false) Long meId,@RequestParam(required=false) Long msId,@RequestParam(required=false) Long mcaId
    		,@RequestParam(required=false)Long msOrder,@RequestParam(required=false)String mraLang){
    	Context ctx =null;
		Connection con = null;
		org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs =null;
		MissSery missSery= missExamService.findMissSeryById(msId);
		
		String msExporting=missSery.getMsExporting();
		logger.info("msExporting->"+msExporting);
		 logger.info("msOrder->"+msOrder);
		 logger.info("msId->"+msId);
		 logger.info("meId->"+meId);
		MissReportAttach missReportAttach=missExamService.findMissReportAttachById(msId, msOrder, mraLang, null);
		MissTestResult missTestResult=missExamService.findMissTestResultById(mtrId);
		missTestResult.setMeId(meId);
		missTestResult.setMsId(msId);
		missTestResult.setUserid(missTestResult.getMissCandidate().getMcaUsername());
		 logger.info("missTestResult.getUserid()->"+missTestResult.getUserid());
		logger.info("missTestResult.getMeId()->"+missTestResult.getMeId());
		logger.info("missTestResult.getMsId()->"+missTestResult.getMsId());
		 logger.info("missTestResult.getMissCandidate().getMcaUsername()->"+missTestResult.getMissCandidate().getMcaUsername());
		if(msExporting!=null && msExporting.equals("1")){
			/* MissTestResult missTestResult = new MissTestResult();
			 missTestResult.setMeId(missExamForm.getMissCandidate().getMissSery().getMissExams().get(missExamForm.getExamIndex()).getMeId());
			 missTestResult.setMsId((missExamForm.getMissCandidate().getMissSery().getMsId()));
			 */
			/* missTestResult.setUserid(SecurityContextHolder.getContext().getAuthentication().getName());
			 java.sql.Timestamp timeStampEndTime = new java.sql.Timestamp(new Date().getTime());
			 missTestResult.setMtrEndTime(timeStampEndTime);
			 missTestResult.setMtrStatus("1"); // 0=start test,1=test finish,2 =send response
			 missTestResult.setMtrRespondedStatus("0");
			 missTestResult.setRootPath(bundle.getString("evaluationPath"));*/
			 missTestResult.setMsOrder(msOrder);
			 missTestResult.setMraLang(mraLang); 
			 int status=missExamService.processMissTestResult(missTestResult);
			 //logger.info("status->"+status);
			 String  reportPath=  bundle.getString("reportTemplatePath")+ missReportAttach.getMraPath(); 
			 String[] extensions = reportPath.split("\\.");
			 String inputFile = extensions[0] + "_" + msId.intValue() + "_"
				+ mcaId.intValue() + "." + extensions[1];
			 String inputFile2 = extensions[0] + "_" + msId.intValue() + "_"
						+ mcaId.intValue() + "_out." + extensions[1];
			 String outputFile = extensions[0] + "_" + msId.intValue() + "_"
						+ mcaId.intValue() + ".pdf";
			logger.info("missTestResult.getMissCandidate().getMcaPicturePath()->"+missTestResult.getMissCandidate().getMcaPicturePath());
		     String picture_path="/opt/images.jpeg";
		     if(missTestResult.getMissCandidate()!=null && missTestResult.getMissCandidate().getMcaPicturePath()!=null 
		    		 && missTestResult.getMissCandidate().getMcaPicturePath().length()>0)
		    	 picture_path="/opt/attach/candidateImg/"+missTestResult.getMissCandidate().getMcaPicturePath();
			 // profile Picture
			 InputStream is=null;
			Map<String, byte[]> boxMap = new HashMap<String, byte[]>();
			try {
				is = new FileInputStream(new File(picture_path));
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			// read image into memory
				ByteArrayOutputStream img_bytes = new ByteArrayOutputStream();
				int img_b;
				try {
					while ((img_b = is.read()) != -1)
						img_bytes.write(img_b);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					is.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				byte[] img = img_bytes.toByteArray();

				Pattern pattern = Pattern.compile("([\\w\\!\\w]+)");
				// [4!B77][4!I417][4!B517]

				Pattern pattern_expand = Pattern.compile("([\\w\\|\\w]+)");
				//ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
				FileInputStream fin=null;
				try {
					fin = new FileInputStream(new File(inputFile));
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//HSSFWorkbook wb = new HSSFWorkbook(inputFileUpload.getInputStream());
				HSSFWorkbook wb=null;
				try {
					wb = new HSSFWorkbook(fin);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					fin.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				/*
				 * HSSFSheet sheet = wb.getSheetAt(0); sheet.getRow(1);
				 */
				Sheet sheet_view = wb.getSheetAt(1); // get View Config
				 Row row_code = sheet_view.getRow(1);
				Cell cell_code = null;
				String view ="";
				String export_pages ="";
				String landscape_pages ="";
				String mode ="";
				String view_data_ref ="";
				if (row_code != null) {
					cell_code = row_code.getCell(0);
					view = cell_code.getStringCellValue();
					cell_code = row_code.getCell(1);
					view_data_ref = cell_code.getStringCellValue(); 
				}
				  
				StringBuffer query = new StringBuffer("SELECT * FROM MISS_CONSULT_EXAM."+view+"  where mtr_id="+mtrId);
				ResultSet result = null;
				PreparedStatement pst1 = null;
				//Connection con = createPoolConnection();
				 con = createPoolConnection();
				try {
					pst1 = con.prepareStatement(query.toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					result = pst1.executeQuery();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String[] view_data_ref_split = view_data_ref.split(":");
				CellReference view_data_cr_start = new CellReference(view_data_ref_split[0]);
				CellReference view_data_cr_end = new CellReference(view_data_ref_split[1]);
				
				if (result != null)
					try {
						while (result.next()) {
							for (int i = view_data_cr_start.getRow(); i <= view_data_cr_end.getRow(); i++) {
								row_code = sheet_view.getRow(i);
								cell_code = row_code.getCell(0);
								String value=result.getString(cell_code.getStringCellValue());
								cell_code = row_code.getCell(1);
								String[] view_datacolumns = cell_code.getStringCellValue().split("!"); // 2!A2:A2
								Sheet sheet_inner = wb.getSheetAt(Integer.parseInt(view_datacolumns[0])); // get sheet
								CellReference cr_start = new CellReference(view_datacolumns[1]); 
								sheet_inner.getRow(cr_start.getRow()).getCell(cr_start.getCol()).setCellValue(value);
								//row_code.getCell(cr_start.getCol()).setCellValue(value);
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally {				
						try { 
							if(result!=null){
								result.close();
								result = null;
							}
							if (pst1 != null) {						 
								pst1.close();			
								pst1 = null;
							}
							if(con!=null){ 
								if(!con.isClosed())
										con.close();
								con = null;
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							//e.printStackTrace();
						}
					} 	
				Sheet sheet1 = wb.getSheetAt(0); // getImage Config
				row_code = sheet1.getRow(1);
				//if(false)
				if (row_code != null) {
					cell_code = row_code.getCell(4);
					export_pages = cell_code.getStringCellValue();
					
					cell_code = row_code.getCell(5);
					if(cell_code!=null)
						landscape_pages = cell_code.getStringCellValue();
					cell_code = row_code.getCell(6);
					if(cell_code!=null)
						mode = cell_code.getStringCellValue();
					cell_code = row_code.getCell(1);
					String columnReference = cell_code.getStringCellValue();
					String[] columns = columnReference.split("!"); // 2!A2:A2
					sheet1 = wb.getSheetAt(Integer.parseInt(columns[0])); // get sheet
					String[] ref_split = columns[1].split(":");
					CellReference cr_start = new CellReference(ref_split[0]);
					CellReference cr_end = new CellReference(ref_split[1]);
					// row_code = sheet1.getRow(cr_start.getRow());
					logger.info(" cr_start.getRow()->" + cr_start.getRow());
					logger.info(" cr_end.getRow()->" + cr_end.getRow());
					for (int i = cr_start.getRow(); i <= cr_end.getRow(); i++) {
						row_code = sheet1.getRow(i);
						byte[] img_use = null;
						// Start [Sheet!Column Row Ref]
						cell_code = row_code.getCell(0);
						 logger.info("Start [Sheet!Column Row Ref]->"
						 		+ cell_code.getStringCellValue());
						Matcher m = pattern.matcher(cell_code.getStringCellValue());
						List<String> values = new ArrayList<String>();
						while (m.find()) {
							values.add(m.group());
						}
						cell_code = row_code.getCell(2);
						String module = cell_code.getStringCellValue();
						logger.info("Module->" + module);
						cell_code = row_code.getCell(3);
						String endpoint = cell_code.getStringCellValue();
						logger.info("End Point->" + endpoint);
					  if(endpoint!=null && endpoint.length()>0){
						if (module.equals("profile_image")) {
							img_use = img;

						} else if (module.equals("chart"))  {
							//http://203.150.20.37/MISSProcessImage/process?key=chart1&w=700&h=400&mtrId=266
							img_use =readChartBytes(endpoint+"&mtrId="+mtrId);
						}else{  
							if (!boxMap.containsKey(module + "_" + endpoint)) {
								boxMap.put(module + "_" + endpoint, readBytes(module, endpoint));
							}
							img_use = boxMap.get(module + "_" + endpoint);
						
						}
					   
						// Expand Column|Row
						cell_code = row_code.getCell(1);
					 logger.info("Expand Column|Row->"
								+ cell_code.getStringCellValue()); 

						Matcher m_expand = pattern_expand.matcher(cell_code
								.getStringCellValue());
						List<String> values_expand = new ArrayList<String>();
						while (m_expand.find()) {
							values_expand.add(m_expand.group());
						}
						logger.info("values size->" + values.size());
						for (int j = 0; j < values.size(); j++) {
							String string = values.get(j);
							String string_expand = values_expand.get(j);
							String[] sheet_image = string.split("!");
							String[] expand_array = string_expand.split("\\|");
							HSSFSheet sheet = wb.getSheetAt(Integer
									.parseInt(sheet_image[0]));
							CellReference image_cr = new CellReference(sheet_image[1]); 
							HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
									(short) image_cr.getCol(), image_cr.getRow(),
									(short) (image_cr.getCol() + Integer
											.parseInt(expand_array[0])),
									image_cr.getRow()
											+ Integer.parseInt(expand_array[1]));
							// other possible image types are: PICTURE_TYPE_PICT,
							// PICTURE_TYPE_PNG,
							// PICTURE_TYPE_WMF, PICTURE_TYPE_DIB, PICTURE_TYPE_EMF
						if(img_use!=null){
							int index = wb.addPicture(img_use,
									HSSFWorkbook.PICTURE_TYPE_JPEG);
							 
							HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
							
							// anchor type: ClientAnchor.MOVE_AND_RESIZE
							// ClientAnchor.MOVE_DONT_RESIZE
							// ClientAnchor.DONT_MOVE_AND_RESIZE
							// anchor.setAnchorType(ClientAnchor.MOVE_DONT_RESIZE);
							 patriarch.createPicture(anchor, index);
						}
							// anchor.setAnchorType(ClientAnchor.MOVE_DONT_RESIZE); 
						}

						// End Point
						cell_code = row_code.getCell(2);
						logger.info("Module->" + cell_code.getStringCellValue());
						cell_code = row_code.getCell(3);
						 logger.info("End Point->"
								+ cell_code.getStringCellValue());
					 }
					} 
				}
				HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
				
			//	wb.ge
				FileOutputStream fos=null;
				try {
					fos = new FileOutputStream(inputFile);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					fos.write(wb.getBytes());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					fos.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			// connect to an OpenOffice.org instance running on port 8100
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
				try {
					connection.connect();
				} catch (ConnectException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	  ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
				// convert
				DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
				converter.convert(new File(inputFile), new File(outputFile));
				 
				// close the connection
				connection.disconnect();
				
			 Document document = new Document();

			   /*PdfWriter writer = PdfWriter.getInstance(document,
			        new FileOutputStream(OUTPUTFILE));*/
			 String filename="report";
				if(missReportAttach.getMraReportName()!=null && missReportAttach.getMraReportName().trim().length()>0)
					filename=missReportAttach.getMraReportName().trim();
				if(filename.length()>0){
					String userAgent = request.getHeader("user-agent");
					boolean isInternetExplorer = (userAgent.indexOf("MSIE") > -1);
					// filename="ทดสอบ โอ๋.xls";
					byte[] fileNameBytes=null;
					try {
						fileNameBytes = filename.getBytes((isInternetExplorer) ? ("windows-1250") : ("utf-8"));
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 
				    String dispositionFileName = ""; 
				    for (byte b: fileNameBytes) dispositionFileName += (char)(b & 0xff);

					 String disposition = "attachment; filename=\"" + dispositionFileName + "\"";
					 response.setHeader("Content-disposition", disposition);
					//response.addHeader("Content-Disposition",content_disposition);
				}
				 ServletOutputStream servletOutputStream=null;
					try {
						servletOutputStream = response.getOutputStream();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
			 PdfWriter writer=null;
			try {
				writer = PdfWriter.getInstance(document,
						servletOutputStream);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    document.open();
			    PdfReader reader=null;
				try {
					reader = new PdfReader(outputFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			    int n = reader.getNumberOfPages();
			    PdfImportedPage page;
			   // int start=187; //1
			    //int start=180; //2
			   // int start=180; //3
			  
			   String[] pages= export_pages.split("-");
			   Map landscapeMap=new HashMap();
					   if(landscape_pages!=null && landscape_pages.trim().length()>0){
							String[] landscapes=landscape_pages.split(",");
							if(landscapes!=null && landscapes.length>0){
								for (int i = 0; i < landscapes.length; i++) {
									int landscape_page=Integer.valueOf(landscapes[i]);
									landscapeMap.put(landscape_page, landscape_page);
								}
							}
						}
			   int start=Integer.valueOf(pages[0]);
			   int end=Integer.valueOf(pages[1]);
			   if(mode!=null && mode.trim().equalsIgnoreCase("TEST")){
				   start=1;
				   end=n;
			   }
			   logger.info("start->"+start);
			   logger.info("end->"+end);
			   /* if(msOrder.intValue()==1)
			       start=209; //3
*/			    //int end=174;
			    // Go through all pages 
			    PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data
			    // Go through all pages
			   // for (int i = 1; i <= n; i++) {
			    /*start=1;
			    end=n;*/
			    for (int i = start; i <= end; i++) { 
					int pageRotation=reader.getPageRotation(i);
			        page = writer.getImportedPage(reader, i);
			        if(landscapeMap.containsKey(i)){
			        	 Image pageImage=null;
							try {
								pageImage = Image.getInstance(page);
							} catch (BadElementException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
			                 pageImage.setAbsolutePosition(0f,0f); 
			                 if(pageRotation != 0){ 
			                         pageImage.setRotationDegrees(-pageRotation);  
			                 } 
							// document.setPageSize(reader.getPageSizeWithRotation(i));	
							 document.setPageSize(PageSize.A4.rotate());
			        }else 
				    	document.setPageSize(PageSize.A4);
			    	document.newPage();
			        cb.addTemplate(page, 0, 0); 
			    } 
			    document.close();
			    try {
					img_bytes.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    reader.close();
			    writer.close();
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
		}else{
			try{ 
				 //MissSeriesAttachSearch("template", msId, null, null); 
				// String  reportPath=  bundle.getString("reportTemplatePath")+missSeriesAttach.getMsatPath();
				 String  reportPath=  bundle.getString("reportTemplatePath")+ missReportAttach.getMraPath(); 
				 JasperPrint jasperPrint=null;
				 Map p =new HashMap();
				 List<MissTestShow> missTestShows= missTestResult.getMissTestShows();
				 if(missTestShows!=null && missTestShows.size()>0){
					 for (MissTestShow missTestShow : missTestShows) {
						p.put(missTestShow.getMtsColumn(), missTestShow.getMtsValue());
					}
				 }
			 
				 p.put("mtrId",mtrId+"");
				 
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
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}               
					 basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource)ds;
					//com.ibm.ws.rsadapter.jdbc.WSJdbcDataSource basicDs = (com.ibm.ws.rsadapter.jdbc.WSJdbcDataSource)ds;
					
				
					try {
						con = basicDs.getConnection();//("oracle", "password");//Connection();
						//con = ds.getConnection();//("oracle", "password");//Connection();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}           
					 
				try { 
					
					//jasperPrint = JasperFillManager.fillReport(reportPath, p,new JREmptyDataSource());
					jasperPrint = JasperFillManager.fillReport(reportPath, p, con);
//					 asperPrint.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
//					jasperPrint.setProperty("net.sf.jasperreports.default.font.name", defaultPDFFont);
					 
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				String filename="report";
				if(missReportAttach.getMraReportName()!=null && missReportAttach.getMraReportName().trim().length()>0)
					filename=missReportAttach.getMraReportName().trim();
				if(filename.length()>0){
					String userAgent = request.getHeader("user-agent");
					boolean isInternetExplorer = (userAgent.indexOf("MSIE") > -1);
					// filename="ทดสอบ โอ๋.xls";
					byte[] fileNameBytes=null;
					try {
						fileNameBytes = filename.getBytes((isInternetExplorer) ? ("windows-1250") : ("utf-8"));
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 
				    String dispositionFileName = ""; 
				    for (byte b: fileNameBytes) dispositionFileName += (char)(b & 0xff);

					 String disposition = "attachment; filename=\"" + dispositionFileName + "\"";
					 response.setHeader("Content-disposition", disposition);
					//response.addHeader("Content-Disposition",content_disposition);
				}
			   //  String fileName="เทส.pdf";
				 //response.addHeader("Content-disposition", "attachment; filename=report.pdf");  
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
					 //aoe add
					 if (basicDs != null) {
						try {
							if(!basicDs.isClosed())
								basicDs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}					
					} 
					 // end aoe add
					if (con != null) {
						try {
							if(!con.isClosed());
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
	   
    }
    @SuppressWarnings({ "deprecation", "unchecked" })
	@RequestMapping(value={"/export"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public void export(HttpServletRequest request, HttpServletResponse response,Model model,SecurityContextHolderAwareRequestWrapper srequest)
    {
    	
    	String mtrIds=request.getParameter("id");
    	if(mtrIds!=null && mtrIds.equals("-1"))
    		mtrIds=null;
    	 Long maId=null;
    	 if(model.containsAttribute("UserMissContact")){
         	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
         //	missSeries=
         	//List<MissAccountSeriesMap> missAccountSeriesMaps
         	maId=missContact.getMcontactRef(); 
         } 
    	  int roleMC=0;
    	  if(srequest.isUserInRole("ROLE_MANAGE_MISSCONSULT"))
    		  roleMC=1;
    	 
    	String msId=request.getParameter("mcaSeries");
    	MissCandidate missCandidate =new MissCandidate();
    	MissAccount missAccount=new MissAccount(); 
    	missAccount.setMaId(maId);
    	missCandidate.setMissAccount(missAccount);
    	
    	MissTestResult missTestResult =new MissTestResult();
    	missTestResult.setMtrIds(mtrIds);
    	missTestResult.setMsId(Long.parseLong(msId));
    	missTestResult.setMissCandidate(missCandidate);
    	missTestResult.setRoleMC(roleMC);
    	missTestResult.setShowAll(true);
    	Pagging pagging=new Pagging();
    	pagging.setOrderBy(request.getParameter("orderBy"));
    	pagging.setSortBy(request.getParameter("sortBy"));
    	missTestResult.setPagging(pagging);
    	 
    	   VResultMessage vresultMessage = missExamService.searchMissTestResult(missTestResult);
    	// model.addAttribute("missTestResults", vresultMessage.getResultListObj().get(0));
       
       // model.addAttribute("axisHeaders", vresultMessage.getResultListObj().get(1));
    	//xxx
    	 //VResultMessage vresultMessage = missExamService.exportMissCandidate(missCandidate); 
    	// vresultMessage.getResultListObj()
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Candidate");
      //  HSSFRow row = sheet.createRow(0);
      //  HSSFCellStyle style = wb.createCellStyle(); 
     
        int indexRow = 0;  
	    HSSFCellStyle cellStyle = wb.createCellStyle();
	    HSSFCellStyle cellStyle2 = wb.createCellStyle();
	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); 
	  
	    cellStyle.setFillBackgroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());     
	    cellStyle.setWrapText(true);
	    
	    cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    cellStyle2.setWrapText(true); 
	   
	 
				//Header 5
			    HSSFRow row = sheet.createRow(indexRow);
			    HSSFCell cell = row.createCell((short)0);
			   int index=0;
			    cell = row.createCell((short)index++);	    
			    cell.setCellValue("No");
			     cell.setCellStyle(cellStyle);	   
			    cell = row.createCell((short)index++);	    
			    cell.setCellValue("Username");
			    cell.setCellStyle(cellStyle); 
			    
			    cell = row.createCell((short)index++);	    
			    cell.setCellValue("First Name");
			    cell.setCellStyle(cellStyle);
			    
			    cell = row.createCell((short)index++);	    
			    cell.setCellValue("Last Name");
			    cell.setCellStyle(cellStyle);
			    
			    cell = row.createCell((short)index++);	    
			    cell.setCellValue("Positione");
			    cell.setCellStyle(cellStyle);
			    
			    cell = row.createCell((short)index++);	    
			    cell.setCellValue("Department");
			    cell.setCellStyle(cellStyle); 
			     
			    List<String> axisHeaders =(List<String>) vresultMessage.getResultListObj().get(1);
			    for (String string : axisHeaders) {
			    	  cell = row.createCell((short)index++);	    
					    cell.setCellValue(string);
					    cell.setCellStyle(cellStyle);
				}
			    
			    cell = row.createCell((short)index++);	    
			    cell.setCellValue("Test Date");
			    cell.setCellStyle(cellStyle); 
			    
			    cell = row.createCell((short)index++);	    
			    cell.setCellValue("Report");
			    cell.setCellStyle(cellStyle); 
			    
			    cell = row.createCell((short)index++);	    
			    cell.setCellValue("Status");
			    cell.setCellStyle(cellStyle); 
			    
			    cell = row.createCell((short)index++);	    
			    cell.setCellValue("Response");
			    cell.setCellStyle(cellStyle);  
			    
			    indexRow++;
			   
			    for(int i=0;i<index;i++){
			    	 sheet.setColumnWidth((short)i,(short)((50*8)/((double)1/20) ));
			    }
			   
			  /*  sheet.setColumnWidth((short)1,(short)((50*8)/((double)1/20) ));
			    sheet.setColumnWidth((short)2,(short)((50*8)/((double)1/20) ));
			    sheet.setColumnWidth((short)3,(short)((50*8)/((double)1/20) )); */
			   List<MissTestResult> results= (List<MissTestResult>) vresultMessage.getResultListObj().get(0);
			   int rowIndex=1;
			   String status="";
			   String responseToUser="";
			   for (MissTestResult result : results) {
				   row = sheet.createRow(indexRow);
				    indexRow++;
				    index=0;
				    cell = row.createCell((short)index++);	    
				    cell.setCellValue(rowIndex++);
				    cell.setCellStyle(cellStyle2);
				     
				    cell = row.createCell((short)index++);	    
				    cell.setCellValue(result.getMissCandidate().getMcaUsername());
				    cell.setCellStyle(cellStyle2); 
				    
				    cell = row.createCell((short)index++);	    
				    cell.setCellValue(result.getMissCandidate().getMcaFirstName());
				    cell.setCellStyle(cellStyle2);
				    
				    cell = row.createCell((short)index++);	    
				    cell.setCellValue(result.getMissCandidate().getMcaLastName());
				    cell.setCellStyle(cellStyle2);
				    
				    cell = row.createCell((short)index++);	    
				    cell.setCellValue(result.getMissCandidate().getMcaPosition());
				    cell.setCellStyle(cellStyle2);
				    
				    cell = row.createCell((short)index++);	    
				    cell.setCellValue(result.getMissCandidate().getMcaDepartment());
				    cell.setCellStyle(cellStyle2); 
				     
				   List<String> axisValues = result.getAxisValues();
				   for (String string : axisValues) {
					   cell = row.createCell((short)index++);	    
					    cell.setCellValue(string);
					    cell.setCellStyle(cellStyle2);
				   }
				   
				    
				    cell = row.createCell((short)index++);	 
				    if(result.getMtrStartTime()!=null)
				    	cell.setCellValue(format3.format(result.getMtrStartTime()));
				    else
				    	cell.setCellValue("");
				    cell.setCellStyle(cellStyle2); 
				    
				    cell = row.createCell((short)index++);	    
				    cell.setCellValue(result.getMtrResultCode());
				    cell.setCellStyle(cellStyle2); 
				    
				    cell = row.createCell((short)index++);	 
				    status="";
				    if(result.getMtrStatus()!=null){
				    	if(result.getMtrStatus().equals("0")){
				    		status="Not finished";
				    	}else if(result.getMtrStatus().equals("1")){
				    		status="Finished";
				    	}else if(result.getMtrStatus().equals("2")){
				    		status="Responded";
				    	}
				    }  
				    cell.setCellValue(status);
				    cell.setCellStyle(cellStyle2); 
				    
				    responseToUser=""; 
				    if(result.getMtrRespondedStatus()!=null){
				    	if(result.getMtrRespondedStatus().equals("1") && result.getMtrStatus().equals("0")){
				    		responseToUser="Completed";
				    	}else if(result.getMtrRespondedStatus().equals("0") && result.getMtrStatus().equals("0")){
				    		responseToUser="Pending";
				    	}else if(result.getMtrRespondedStatus().equals("2") ){
				    		responseToUser="Ignored";
				    	}
				    	 if(result.getMtrStatus().equals("0") ){
				    		 responseToUser="";
				    	}
				    } 
				    cell = row.createCell((short)index++);	    
				    cell.setCellValue(responseToUser);
				    cell.setCellStyle(cellStyle2);  
				     
			 } 
        response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=Report.xls");
        ServletOutputStream servletOutputStream = null;
        try
        {
            servletOutputStream = response.getOutputStream();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            wb.write(servletOutputStream);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            servletOutputStream.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            servletOutputStream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
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
/*	private void writeReportToResponseStream(HttpServletResponse response,
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
	}*/

    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;
    private Connection createPoolConnection() {
		Context ctx = null;
		Connection con = null;
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataSource ds = null;
		try {
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/missdb");
			// ds = (DataSource)ctx.lookup("jdbc/sabaDS");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs = (org.apache.tomcat.dbcp.dbcp.BasicDataSource) ds;
		// com.ibm.ws.rsadapter.jdbc.WSJdbcDataSource basicDs =
		// (com.ibm.ws.rsadapter.jdbc.WSJdbcDataSource)ds;

		try {
			con = basicDs.getConnection();// ("oracle",
											// "password");//Connection();
			// con = ds.getConnection();//("oracle", "password");//Connection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
    private byte[] readBytes(String folder,String fileName) {
		InputStream box1_1 = null;
		try {
			box1_1 = new FileInputStream(new File("/opt/" + folder+"/"+fileName + ".png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayOutputStream img_bytes_box = new ByteArrayOutputStream();
		int b_box;
		try {
			while ((b_box = box1_1.read()) != -1)
				img_bytes_box.write(b_box);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			box1_1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] img = img_bytes_box.toByteArray();
		try {
			img_bytes_box.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
    private byte[] readChartBytes(String url) {
    	InputStream is_chart=null;
		try {
			URL aURL = new URL(url);
			try {
				is_chart =aURL.openStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		 
		ByteArrayOutputStream img_bytes_box = new ByteArrayOutputStream();
		int b_box;
		try {
			while ((b_box = is_chart.read()) != -1)
				img_bytes_box.write(b_box);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			is_chart.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] img = img_bytes_box.toByteArray();
		try {
			img_bytes_box.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
}
