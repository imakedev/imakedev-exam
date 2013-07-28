// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:27 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ResultController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
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
    	/*System.out.println("resultForm.getMailAttachReport()->"+resultForm.getMailAttachReport());
    	System.out.println("resultForm.getMsOrder()->"+resultForm.getMsOrder());
    	System.out.println("resultForm.getMraLang()->"+resultForm.getMraLang());*/
    	/*if(resultForm.getMailAttachReport()!=null && resultForm.getMailAttachReport().equals("1")
    			&& resultForm.getMsOrder()!=null && resultForm.getMsOrder().length()>0 
    			&& resultForm.getMraLang()!=null && resultForm.getMraLang().length()>0 ){
    		fileSize=getFileSize(resultForm.getMissTestResult().getMsId(),resultForm.getMissTestResult().getMtrId(),Long.valueOf(resultForm.getMsOrder()),
    				resultForm.getMraLang());
    		//  private byte[] getFileSize(Long msId,Long mtrId,Long msOrder,String mraLang){
    	}*/
    	//System.out.println("fileSize->"+fileSize);
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
		 System.out.println("missReportAttach->"+missReportAttach);
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
   /* public void testPDF(HttpServletRequest request, HttpServletResponse response ,@RequestParam(required=false) Long mtrId,
    		@RequestParam(required=false) Long meId,@RequestParam(required=false) Long msId,@RequestParam(required=false) Long mcaId){*/
    //	logger.debug(;
    	System.out.println(" testPDF======>  mtrId="+ mtrId+",meId="+meId+",msId="+msId+",mcaId="+mcaId+",msOrder="+msOrder+",mraLang="+mraLang);
    	Context ctx =null;
		Connection con = null;
		org.apache.tomcat.dbcp.dbcp.BasicDataSource basicDs =null;
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
		 
		 //MissSeriesAttach missSeriesAttach=missExamService.findMissSeriesAttachSearch("template", msId, null, null);
		 MissReportAttach missReportAttach=missExamService.findMissReportAttachById(msId, msOrder, mraLang, null);
		
		 //MissSeriesAttachSearch("template", msId, null, null);
		 MissTestResult missTestResult=missExamService.findMissTestResultById(mtrId);
		// String  reportPath=  bundle.getString("reportTemplatePath")+missSeriesAttach.getMsatPath();
		 String  reportPath=  bundle.getString("reportTemplatePath")+ missReportAttach.getMraPath(); 
		 JasperPrint jasperPrint=null;
		 Map p =new HashMap();
		 //System.out.println("missTestResult.getMissTestShows()->"+missTestResult.getMissTestShows());
		 List<MissTestShow> missTestShows= missTestResult.getMissTestShows();
		 if(missTestShows!=null && missTestShows.size()>0){
			 for (MissTestShow missTestShow : missTestShows) {
				p.put(missTestShow.getMtsColumn(), missTestShow.getMtsValue());
				//System.out.println("mmissTestShow.getMtsColumn()->"+missTestShow.getMtsColumn()+",missTestShow.getValue->"+missTestShow.getMtsValue());
			}
		 }
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
		 p.put("mtrId",mtrId+"");
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
		String filename="report";
		if(missReportAttach.getMraReportName()!=null && missReportAttach.getMraReportName().trim().length()>0)
			filename=missReportAttach.getMraReportName().trim();
		if(filename.length()>0){
			String userAgent = request.getHeader("user-agent");
			boolean isInternetExplorer = (userAgent.indexOf("MSIE") > -1);
			// filename="ทดสอบ โอ๋.xls";
			//System.out.println(fileName);
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

}
