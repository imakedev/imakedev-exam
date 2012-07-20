// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:06:21 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   WelcomeController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.exam.mail.MailRunnable;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.MissTodo;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@SessionAttributes(value={"UserMissContact"})
public class WelcomeController
{
	 private static String MAIL_SERVER = "";
	  private static String MAIL_PROTOCAL = "";
	  private static String MAIL_PORT="";
	  private static String MAIL_USE_AUTHEN="";
	  private static String MAIL_EMAIL="";
	  private static String MAIL_PASSWORD=""; 
	  private static String MAIL_PERSONAL_NAME="";
	  private static String MAIL_TLS="";
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
   /* @Autowired
    public WelcomeController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired WelcomeController #######################");
        this.missExamService = missExamService;
    }
*/
    @RequestMapping(value={"/template/todolist"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getToDoList(Model model, @RequestParam(value="pageNo", required=false) String pageNoStr)
    {
       // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      //  String name = auth.getName();
      //  logger.debug((new StringBuilder(" name  ===>")).append(name).toString());
       // logger.debug((new StringBuilder(" auth.getAuthorities() ====>")).append(auth.getAuthorities()).toString());
        int pageNo = 1;
        if(pageNoStr != null && !pageNoStr.equals(""))
            pageNo = Integer.parseInt(pageNoStr);
        Pagging page = new Pagging();
        page.setPageNo(pageNo);
        page.setPageSize(20);
        MissTodo missTodo = new MissTodo();
        missTodo.setPagging(page);
        if(model.containsAttribute("UserMissContact")){
        	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
        	if(missContact.getIsMC()!=null && missContact.getIsMC().equals("0")){
        		 MissAccount missAccount = new MissAccount(); 
        		 missAccount.setMaId(missContact.getMcontactRef());
        		 missTodo.setMissAccount(missAccount);
        		 //candidateForm.getMissCandidate().setMissAccount(missAccount);
        	}
        }
        VResultMessage vresult = missExamService.searchMissTodo(missTodo);
        model.addAttribute("todolists", vresult.getResultListObj());
        model.addAttribute("totals", vresult.getMaxRow());
        model.addAttribute("pageObj", page);
        return "exam/template/home";
    }

    @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm(Model model)
    {
        int pageNo = 1;
        DateTime dt = new DateTime();
        Pagging page = new Pagging();
        page.setPageNo(pageNo);
        page.setPageSize(20);
        MissTodo missTodo = new MissTodo();
        MissContact missContact= missExamService.findMissContactByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(missContact.getIsMC()!=null && missContact.getIsMC().equals("0")){
   		 MissAccount missAccount = new MissAccount(); 
   		 missAccount.setMaId(missContact.getMcontactRef());
   		 missTodo.setMissAccount(missAccount);
   		 //candidateForm.getMissCandidate().setMissAccount(missAccount);
     	}
        missTodo.setPagging(page);
        VResultMessage vresult = missExamService.searchMissTodo(missTodo);    
        model.addAttribute("todolists", vresult.getResultListObj());
        model.addAttribute("totals", vresult.getMaxRow());
        model.addAttribute("UserMissContact", missContact);
        model.addAttribute("pageObj", page);
        model.addAttribute("systemDate", format1.format(new Date()));
        return "exam/common";
    }
    @RequestMapping(value={"/getmailToApprove/{mailTodoId}/{mailTodoRef}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getMailForm(HttpServletRequest request, @PathVariable Long mailTodoId,@PathVariable Long mailTodoRef ,Model model)
    { 
    	model.addAttribute("mail_todo_idG", mailTodoId);
    	model.addAttribute("mail_todo_refG", mailTodoRef);
    	  return "exam/template/todoResponse";
    }
    @RequestMapping(value={"/sendmailToApprove"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String sendmail(HttpServletRequest request, Model model)
    {
    	/*logger.error("request   mail_attach ==> "+request.getParameter("mail_attach"));
    	logger.error("request   mail_message ==> "+);*/
    	String message=request.getParameter("mail_message");
    	String subject=request.getParameter("mail_subject");
    	String mailTo=request.getParameter("mail_to");
    	logger.error("request   mail_message==>"+message);
    	logger.error("request   mail_subject==>"+subject);
    	//send mail to Approver
    	byte [] fileSize=null;
    	if(request.getParameter("mail_attach")!=null && request.getParameter("mail_attach").equals("1")){
    		/*$("#mail_todo_id").val(todo_id);
    		$("#mail_todo_ref").val(todo_ref);*/ 
    		String todo_ref=request.getParameter("mail_todo_ref");
    		MissTestResult missTestResult =missExamService.findMissTestResultById(Long.valueOf(todo_ref));
    		fileSize=getFileSize(missTestResult.getMsId(),missTestResult.getMtrId());
    	}
    	List recipientsTo= new ArrayList(1);
    	recipientsTo.add(mailTo);
    	MailRunnable mailRunnableToTeam = new MailRunnable(
				MAIL_PROTOCAL, MAIL_SERVER, MAIL_EMAIL
						, MAIL_PASSWORD, MAIL_USE_AUTHEN,
						recipientsTo, subject,
				message, "99",MAIL_PERSONAL_NAME,MAIL_PORT,null,null,fileSize,MAIL_TLS);
		Thread mailThreadToTeam = new Thread(
				mailRunnableToTeam);
		mailThreadToTeam.start();
    	// update to do status
		 MissTodo missTodo = new MissTodo();
		 missTodo.setMtodoId(Long.valueOf(request.getParameter("mail_todo_id")));
		 missTodo.setMtodoResponse("1");
		//misstodo misstodo =new misstodo();
		missExamService.updateMissTodo(missTodo);
    	
    	  int pageNo = 1;
         
          Pagging page = new Pagging();
          page.setPageNo(pageNo);
          page.setPageSize(20);
          missTodo.setMtodoId(null);
          missTodo.setMtodoResponse(null);
         // MissTodo missTodo = new MissTodo();
          missTodo.setPagging(page);
          if(model.containsAttribute("UserMissContact")){
          	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
          	if(missContact.getIsMC()!=null && missContact.getIsMC().equals("0")){
          		 MissAccount missAccount = new MissAccount(); 
          		 missAccount.setMaId(missContact.getMcontactRef());
          		 missTodo.setMissAccount(missAccount);
          		 //candidateForm.getMissCandidate().setMissAccount(missAccount);
          	}
          }
          VResultMessage vresult = missExamService.searchMissTodo(missTodo);
          model.addAttribute("todolists", vresult.getResultListObj());
          model.addAttribute("totals", vresult.getMaxRow());
          model.addAttribute("pageObj", page);
    	 //return "exam/template/home";
          return "exam/template/todoTaskComplete";
    }
    private byte[] getFileSize(Long msId,Long mtrId){
    	byte [] fileSize=null;
    	Context ctx =null;
		Connection con = null;
    	try{
    	
		 MissSeriesAttach missSeriesAttach=missExamService.findMissSeriesAttachSearch("template", msId, null, null);
		 
		 String  reportPath=  bundle.getString("templatePath")+missSeriesAttach.getMsatPath();  
		 JasperPrint jasperPrint=null;
		 
		 Map p =new HashMap();
		 p.put("mtrId",mtrId);
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
  
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
