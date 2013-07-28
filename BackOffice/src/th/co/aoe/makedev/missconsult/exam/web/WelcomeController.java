// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:06:21 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   WelcomeController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import th.co.aoe.makedev.missconsult.exam.mail.MailRunnableAttach;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.exam.utils.IMakeDevUtils;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;
import th.co.aoe.makedev.missconsult.xstream.MissTodo;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

import com.sun.mail.smtp.SMTPTransport;

@Controller 
@SessionAttributes(value={"UserMissContact","welcomeForm"})
public class WelcomeController
{
	private static int PAGE_SIZE=20;
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
        if(pageNoStr != null && pageNoStr.length()!=0)
            pageNo = Integer.parseInt(pageNoStr);
        Pagging page = new Pagging();
        page.setPageNo(pageNo);
        page.setPageSize(PAGE_SIZE);
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
        model.addAttribute("pageCount", IMakeDevUtils.calculatePage(PAGE_SIZE, Integer.parseInt(vresult.getMaxRow())));
        return "exam/template/home";
    } 
    @RequestMapping(value={"/todoList/ignore/{ignore_id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String ignoreToDolist(Model model, @RequestParam(value="pageNo", required=false) String pageNoStr
    		,@PathVariable Long ignore_id)
    {
       // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      //  String name = auth.getName();
      //  logger.debug((new StringBuilder(" name  ===>")).append(name).toString());
       // logger.debug((new StringBuilder(" auth.getAuthorities() ====>")).append(auth.getAuthorities()).toString());
    	MissTodo missTodo =new MissTodo();
    	missTodo.setMtodoId(ignore_id);
    	missExamService.deleteMissTodo(missTodo);
        int pageNo = 1;
        if(pageNoStr != null && pageNoStr.length()!=0)
            pageNo = Integer.parseInt(pageNoStr);
        Pagging page = new Pagging();
        page.setPageNo(pageNo);
        page.setPageSize(PAGE_SIZE);
        missTodo = new MissTodo();
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
       // return "redirect:/";
    }
    
    @RequestMapping(value={"/doTodoAction/{pageNo}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String doTodoAction(HttpServletRequest request
    		//, @ModelAttribute(value="welcomeForm") WelcomeForm welcomeForm, BindingResult result
    		, @PathVariable int pageNo,Model model)
    {
    	 
    	   Pagging page = new Pagging();
    	   MissTodo misstodo =new MissTodo();
    	   page.setPageSize(PAGE_SIZE);
    	   page.setPageNo(pageNo);
    	   misstodo.setPagging(page);
         
     
    	   VResultMessage vresult = missExamService.searchMissTodo(misstodo);    
           model.addAttribute("todolists", vresult.getResultListObj());
           model.addAttribute("totals", vresult.getMaxRow());
         //  model.addAttribute("UserMissContact", missContact);
           model.addAttribute("pageObj", page);
        model.addAttribute("pageCount", IMakeDevUtils.calculatePage(PAGE_SIZE, Integer.parseInt(vresult.getMaxRow())));
        return "exam/template/home";
    }

    
   
    @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getNewForm(HttpServletRequest request,HttpServletResponse response,  Model model)
    {
    	String language=request.getParameter("language");
    	if(language!=null && language.length()>0){
    	 LocaleEditor localeEditor = new LocaleEditor();
         localeEditor.setAsText(language);

        /* Locale locale = StringUtils.parseLocaleString("th_TH"
                 .toLowerCase());*/
         // set the new locale
         LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
         localeResolver.setLocale(request, response,
             (Locale) localeEditor.getValue());
    	}
    	//logger.error("into init local "+LocaleContextHolder.getLocale());
    	//locale.getDisplayLanguage()
        int pageNo = 1;
       // DateTime dt = new DateTime();
        Pagging page = new Pagging();
        page.setPageNo(pageNo);
       
        page.setPageSize(PAGE_SIZE);
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
        model.addAttribute("pageCount", IMakeDevUtils.calculatePage(PAGE_SIZE, Integer.parseInt(vresult.getMaxRow()))); 
        
    	//List<MissAccountSeriesMap> missAccountSeriesMaps = missExamService.findMissAccountSeriesMapByRole(missContact.getMcontactRef(),missContact.getRcId());
    	 // model.addAttribute("seriesMaps_menu",missAccountSeriesMaps);
        model.addAttribute("systemDate", format1.format(new Date()));
        return "exam/common";
    }
    @RequestMapping(value={"/getmailToApprove/{mailTodoId}/{mailTodoRef}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getMailForm(HttpServletRequest request, @PathVariable Long mailTodoId,@PathVariable Long mailTodoRef ,Model model)
    { 
    	model.addAttribute("mail_todo_idG", mailTodoId);
    	model.addAttribute("mail_todo_refG", mailTodoRef);
    	MissTodo missTodo=new  MissTodo();
    	missTodo.setMtodoId(mailTodoId);
    	missTodo.setMtodoRef(mailTodoRef);
    	MissTestResult missTestResult=missExamService.findMissTestResultById(mailTodoRef);
    	model.addAttribute("mail_msIdG", missTestResult.getMsId());
    	model.addAttribute("mail_maIdG", missTestResult.getMissCandidate().getMissAccount().getMaId());
    	//missExamService.findMissCandidateById(missTestResult.getMissCandidate().getMcaId()sCandidate())
    	String mail =missExamService.getEmailFromMissTodo(missTodo);
    	if(mail!=null)
    		model.addAttribute("mail_todo_to", mail);
     
    	  return "exam/template/todoResponse";
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value={"/sendmailToApprove"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String sendmail(HttpServletRequest request, Model model)
    {
    	/*logger.error("request   mail_attach ==> "+request.getParameter("mail_attach"));
    	logger.error("request   mail_message ==> "+);*/
    	String message=request.getParameter("mail_message");
    	String subject=request.getParameter("mail_subject");
    	String mailTo=request.getParameter("mail_to");
    	String mailCC=request.getParameter("mail_cc");
    	String mailBCC=request.getParameter("mail_bcc");
    	String msOrder=request.getParameter("msOrder");
    	String mraLang=request.getParameter("mraLang");
    	String  msId=request.getParameter("msId");
    	/*logger.error("request   mail_message==>"+message);
    	logger.error("request   mail_subject==>"+subject);
    	
    	logger.error("request   mail_attach==>"+request.getParameter("mail_attach"));
    	*/
    	//logger.error("request   mail_todo_ref==>"+request.getParameter("mail_todo_ref"));
    	int status=0;
    	//send mail to Approver
    	//byte [] fileSize=null;
    	/*if(request.getParameter("mail_attach")!=null && request.getParameter("mail_attach").equals("1")){
    		String todo_ref=request.getParameter("mail_todo_ref");
    		MissTestResult missTestResult =missExamService.findMissTestResultById(Long.valueOf(todo_ref));
    		System.out.println("missTestResult->"+missTestResult);
    		if(missTestResult!=null){
    			
    			fileSize=getFileSize(missTestResult.getMsId(),missTestResult.getMtrId());
    		}
    	}*/
    	String[] mailTos=null;
    	if(mailTo!=null  && mailTo.length()>0){
    		 mailTos=mailTo.split(",");
    		 List recipientsTo= new ArrayList(mailTos.length);
    		 for (int i = 0; i < mailTos.length; i++) {
    			 recipientsTo.add(mailTos[i]);
			}
    			String[] mailCCs=null;
    			String[] mailBCCs=null;
    			 List recipientsCC=null;
    			 List recipientsBCC=null;
    		 if(mailCC!=null  && mailCC.length()>0){
    			 mailCCs=mailCC.split(",");
    			 recipientsCC= new ArrayList(mailCCs.length);
        		 for (int i = 0; i < mailCCs.length; i++) {
        			 recipientsCC.add(mailCCs[i]);
    			}
    		 }
    		 if(mailBCC!=null  && mailBCC.length()>0){
    			 mailBCCs=mailBCC.split(",");
    			 recipientsBCC= new ArrayList(mailBCCs.length);
        		 for (int i = 0; i < mailBCCs.length; i++) {
        			 recipientsBCC.add(mailBCCs[i]);
    			}
    		 }
    		/* status= sendMail(MAIL_PROTOCAL, MAIL_SERVER, MAIL_EMAIL
						, MAIL_PASSWORD, MAIL_USE_AUTHEN,
						recipientsTo, subject,
				message, "99",MAIL_PERSONAL_NAME,MAIL_PORT,recipientsCC,recipientsBCC,fileSize,MAIL_TLS);*/
    		 try{
    		 MailRunnableAttach mailRunnableToTeam = new MailRunnableAttach(missExamService,Long.valueOf(request.getParameter("mail_todo_ref")),Long.valueOf(msId),
    				 request.getParameter("mail_attach"),msOrder,mraLang,bundle.getString("reportTemplatePath"),
    					MAIL_PROTOCAL, MAIL_SERVER, MAIL_EMAIL
    							, MAIL_PASSWORD, MAIL_USE_AUTHEN,
    					recipientsTo, subject,
    					message, "99",MAIL_PERSONAL_NAME,MAIL_PORT,recipientsCC,recipientsBCC,MAIL_TLS);
    			Thread mailThreadToTeam = new Thread(
    					mailRunnableToTeam);
    			mailThreadToTeam.start(); 
    		 }catch(Exception ex){
    			 ex.printStackTrace();
    		 }
    			status=1;
    	/*	 MailRunnable mailRunnableToTeam = new MailRunnable(
    					;
    			Thread mailThreadToTeam = new Thread(
    					mailRunnableToTeam);
    			mailThreadToTeam.start();*/
    	}
    /*	List recipientsTo= new ArrayList(1);
    	recipientsTo.add(mailTo);*/
    	
    	// update to do status
    	 MissTodo missTodo = new MissTodo();
    	if(status==1){
		
		 missTodo.setMtodoId(Long.valueOf(request.getParameter("mail_todo_id")));
		 missTodo.setMtodoResponse("1");
		//misstodo misstodo =new misstodo();
		missExamService.updateMissTodo(missTodo);
    	}
    	  int pageNo = 1;
         
          Pagging page = new Pagging();
          page.setPageNo(pageNo);
          page.setPageSize(PAGE_SIZE);
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
          model.addAttribute("status", status+""); 
    	 //return "exam/template/home";
          return "exam/template/todoTaskComplete";
    }
    @SuppressWarnings("rawtypes")
	private int sendMail(String protocal_,String  host_,String email_ ,String  password_,String  useAuthen_,
			List recipients
			,String subject,String messagebody,String sessionId,String personal_name,String port,
			List recipients_cc,List recipients_bcc,byte[] fileSize,String tls){
    	int cansent=0;
    	File temp  = null;
		Properties props = new Properties();
		/*this.subject = subject;
		this.messagebody = messagebody;
		this.sessionId = sessionId;
		this.protocal=protocal_;
		this. host=host_;
		this.email =email_;
		this. password=password_;
		this. useAuthen=useAuthen_;
		this.recipients=recipients;
		this.personal_name=personal_name;
		this.port=port;
		this.recipients_cc=recipients_cc;
		this.recipients_bcc=recipients_bcc;
		this.fileSize=fileSize;
		this.tls=tls;*/
	    boolean isAuthen = false;
			//props.put("mail.transport.protocol", protocal);//"smtp");
	    props.put("protocol", protocal_);//"smtp");
			if(tls!=null && tls.toLowerCase().equals("1"))
				props.put("mail.smtp.starttls.enable","true");
			props.put("mail.smtp.host", host_);//"smtp.gmail.com");
			props.put("mail.smtp.port",port);
			
			 
			//props.put("mail.smtp.ssl.enable", "true");
			if(useAuthen_!=null && useAuthen_.equals("1")){
				isAuthen = true;
			}
			if(isAuthen)
			  props.put("mail.smtp.auth", "true");
			// Get a Session object 
		   
		//	String[] recipients=null;
			//if(ntcNewsLetterRecipient!=null && ntcNewsLetterRecipient.size()>0){
			 	int size = recipients.size();
				//recipients= new String[1];
			 	for (int i = 0; i < size; i++) {
					
					 //Session session = Session.getInstance(props, null);
					 Session session = Session.getDefaultInstance(props, null);
					    session.setDebug(true);
			MimeMessage msgArray[] = null;// new MimeMessage(session); 
			try {
				msgArray = new MimeMessage[1];//recipients.length];  
		    	InternetAddress[] addressTo = new InternetAddress[1];//recipients.length];
		    	Date date = new Date();
		    	StringBuffer sb = new StringBuffer();
				sb.append("<HTML>\n");
				sb.append("<HEAD>\n");
				sb.append("<TITLE>\n");
				sb.append(" MissConsult " + "\n");
				sb.append("</TITLE>\n");
				sb.append("</HEAD>\n");
				sb.append("<BODY>\n");
				sb.append(messagebody);
				sb.append("\n");
				sb.append("</BODY>\n");
				sb.append("</HTML>\n");
				MimeBodyPart mbp1 = new MimeBodyPart(); 
				String message = StringEscapeUtils.unescapeHtml(sb.toString());
				mbp1.setContent(message, "text/html; charset=UTF-8");		
				
				Multipart mp = new MimeMultipart();
				mp.addBodyPart(mbp1);				 
				if(fileSize!=null && fileSize.length>0){
					FileOutputStream fos = null;
					
					try {
					//	byte []filesize = multipart.getBytes();
						//if(filesize.length>0){
						 MimeBodyPart mbp2 = new MimeBodyPart();
						 temp = File.createTempFile(genToken(),".howto"); 
						 temp.deleteOnExit();
						 fos = new FileOutputStream(temp.getAbsolutePath());
						
						 fos.write(fileSize); 
						 
						 	mbp2.attachFile(temp);
							mbp2.setFileName("Report.pdf");
							mp.addBodyPart(mbp2);
					//	}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally{
						if(fos!=null)
							try {
								fos.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						 
					} 
				}
			
				InternetAddress addressFrom=null;
				try {
					if(personal_name!=null && personal_name.length()>0)
						addressFrom = new InternetAddress(email_,personal_name);
					else
						addressFrom = new InternetAddress(email_);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					addressTo[0] = new InternetAddress(((String)recipients.get(i)).trim(),false);
				
					msgArray[0] = new MimeMessage(session);
					msgArray[0].setFrom(addressFrom); 
					msgArray[0].setRecipient(Message.RecipientType.TO,addressTo[0]);
					
					//set CC
					if(recipients_cc!=null && recipients_cc.size()>0){
						InternetAddress[] addressCC = new InternetAddress[recipients_cc.size()];
						for (int j = 0; j < recipients_cc.size(); j++) {
							InternetAddress	address = new InternetAddress(((String)recipients_cc.get(j)).trim(),false);
							addressCC[j]=address; 
						}
						msgArray[0].setRecipients(Message.RecipientType.CC,addressCC);
					}
					//set BCC
					if(recipients_bcc!=null && recipients_bcc.size()>0){
						InternetAddress[] addressBCC = new InternetAddress[recipients_bcc.size()];
						for (int j = 0; j < recipients_bcc.size(); j++) {
							InternetAddress	address= new InternetAddress(((String)recipients_bcc.get(j)).trim(),false);
							addressBCC[j]=address;							
						}
						msgArray[0].setRecipients(Message.RecipientType.BCC,addressBCC);
					}
					
					msgArray[0].setSentDate(date);
					 try {
						 msgArray[0].setSubject(subject,"UTF-8");
						 msgArray[0].setHeader("Content-Transfert-Encoding","8Bit");
						 msgArray[0].setContent(mp);
					 } catch (MessagingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
					 }
				//} 
			} catch (AddressException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
			
		    SMTPTransport t =null;
			try {
				t = (SMTPTransport)session.getTransport("smtp");
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
				try {
					if(isAuthen)
						t.connect(host_, email_, password_);
					else
						t.connect();
						//for (int i = 0; i < msgArray.length; i++) {
							//logger.debug("Chatchai Debug==>"+msgArray[0].getAllRecipients().toString());
						//	t.sendMessage(arg0, arg1)send(msgArray[i]);
							t.sendMessage(msgArray[0], msgArray[0].getAllRecipients());
							cansent=1;
						//}
						
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					//e.printStackTrace();
					//continue;
					e.printStackTrace();
				} 
				  finally {			 
			    	try {
						t.close();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(temp!=null){
						/*logger.error(" delete file================= "+temp.getAbsolutePath());
						logger.error(" recipients_cc.size()="+ recipients_cc.size());
						logger.error(" recipients_bcc.size()="+ recipients_bcc.size());*/
						if(temp.exists() && temp.isFile())
							temp.delete();
					}
			    } 
			}
		//}
	
    	return cansent;
    }
    private String genToken(){
  		StringBuffer sb = new StringBuffer();
  	    for (int i = 36; i > 0; i -= 12) {
  	      int n = Math.min(12, Math.abs(i));
  	      sb.append(org.apache.commons.lang.StringUtils.leftPad(Long.toString(Math.round(Math.random() * Math.pow(36, n)), 36), n, '0'));
  	    }
  	    return sb.toString();
   }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private byte[] getFileSize(Long msId,Long mtrId){
    	byte [] fileSize=null;
    	Context ctx =null;
		Connection con = null;
    	try{
    	
		 MissSeriesAttach missSeriesAttach=missExamService.findMissSeriesAttachSearch("template", msId, null, null);
		 
		 String  reportPath=  bundle.getString("templatePath")+missSeriesAttach.getMsatPath();  
		 JasperPrint jasperPrint=null;
		 
		 Map p =new HashMap<String,String>();
		 p.put("mtrId",(mtrId+""));
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
					  e.printStackTrace();
				}					
			}	
		}
    	//System.out.println("fileSize->"+fileSize);
    	return fileSize;
    }
    @RequestMapping(value={"/checksession"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public @ResponseBody String checksession()
    { 
    	Authentication authen=SecurityContextHolder.getContext().getAuthentication();		
		String userid=null;
		if(authen!=null)
			userid=authen.getName();
       // Gson gson=new Gson();
		// return gson.toJson(missTheme);
		return userid;
    }
  
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
   // private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
   // private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
