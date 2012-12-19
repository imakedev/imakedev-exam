// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:05:58 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SurveyController.java

package th.co.aoe.makedev.missconsult.exam.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.exam.form.SurveyForm;
import th.co.aoe.makedev.missconsult.exam.mail.MailRunnable;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissSery;
import th.co.aoe.makedev.missconsult.xstream.MissSurveySend;

@Controller
@RequestMapping(value={"/survey"})
@SessionAttributes(value={"surveyForm"})
public class SurveyController
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
    public SurveyController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired SurveyController #######################");
        this.missExamService = missExamService;
    }*/

    @RequestMapping(value={"/init/{maId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getToDoList(Model model,@PathVariable Long maId)
    {
      //  logger.debug((new StringBuilder("testtttttttttt")).append(missExamService).toString());
    	SurveyForm surveyForm =new SurveyForm();
    	surveyForm.setMaId(maId);
    	model.addAttribute("surveyForm", surveyForm);
    	model.addAttribute("display", "display: none");
    	model.addAttribute("message", "");
    	 model.addAttribute("missSeries", missExamService.listMissSery());
        return "exam/template/surveySend";
    }

    @RequestMapping(value={"/sendmail"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String sendMail(@ModelAttribute(value="surveyForm") SurveyForm surveyForm, BindingResult result, Model model)
    {
      
    	/*System.out.println(surveyForm.getMaId());
    	System.out.println(surveyForm.getSurvey_email().length);
    	System.out.println(surveyForm.getAmountSend());*/
    	int resultReturn=0;
    	if(surveyForm.getSurvey_email().length>=surveyForm.getAmountSend()){
    		 Random randomGenerator = new Random();
    		 Map map=new HashMap<String,String >();
    		 while (map.size()<surveyForm.getAmountSend()) {
    			  int randomInt = randomGenerator.nextInt(surveyForm.getSurvey_email().length-1);
    			  map.put(randomInt+"", randomInt+"");
    		}
    	
    		 List<List<String>> userEmail =new ArrayList<List<String>>(map.size());
    		 for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
    			 String key = (String) iterator.next();
    			 int keyInt=Integer.parseInt(key);
    			 List<String> email = new ArrayList<String>(2);
    			 email.add(surveyForm.getSurvey_name()[keyInt]);
    			 email.add(surveyForm.getSurvey_email()[keyInt]);
    			 userEmail.add(email);				
			}
    		 MissSurveySend missSurveySend =new MissSurveySend();
    		
    		 missSurveySend.setMaId(surveyForm.getMaId());
    		 MissSery missSery = new MissSery();
    		 missSery.setMsId(surveyForm.getMsId());
    		 missSurveySend.setMissSery(missSery);
    		 missSurveySend.setUserEmail(userEmail);
    		 resultReturn= missExamService.sendSurvey(missSurveySend);
    		 //System.out.println(" resultReturn="+resultReturn);
    		 int size=map.size();
    		 for(int i=0;i<size;i++){
    			 List<String> list=userEmail.get(i);
    			 System.out.println("["+i+"] name="+list.get(0)+",email="+list.get(1));
    			/* MailRunnable mailRunnableToTeam = new MailRunnable(
     					MAIL_PROTOCAL, MAIL_SERVER, MAIL_EMAIL
     							, MAIL_PASSWORD, MAIL_USE_AUTHEN,
     					recipientsTo, subject,
     					resultForm.getMailMessage(), "99",MAIL_PERSONAL_NAME,MAIL_PORT,recipientsCC,recipientsBCC,fileSize,MAIL_TLS);
     			Thread mailThreadToTeam = new Thread(
     					mailRunnableToTeam);
     			mailThreadToTeam.start();
     			*/
    		 }
    		/**/ 
    	}
    	model.addAttribute("display", "display: block");
    	model.addAttribute("message", ((resultReturn==1)?"Send Success !!!":"Send not Success !!!"));
    	 model.addAttribute("missSeries", missExamService.listMissSery());
    	 model.addAttribute("surveyForm", surveyForm);
    	 model.addAttribute("message_class", ((resultReturn==1)?"success":"error"));
    	 return "exam/template/surveySend";
        //return "exam/template/surveySend";
    }

    private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
