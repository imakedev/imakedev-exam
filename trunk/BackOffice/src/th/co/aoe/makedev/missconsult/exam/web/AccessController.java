package th.co.aoe.makedev.missconsult.exam.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;

@Controller
@RequestMapping
public class AccessController {
	@Autowired
	private MissExamService missExamService;
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,Model model, @RequestParam(required=false) String message) {
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
		//model.addAttribute("message", message);
	//	model.addAttribute("message", "เทสสส");
		//SecurityExpressionRoot.		
		/*SecurityContextHolder.getContext().getAuthentication().
		SecurityExpressionRoot x = SecurityExpressionRoot(SecurityContextHolder.getContext().getAuthentication());*/
		//x.hasAnyRole(arg0)
		return "access/login";
	}
	
	@RequestMapping(value = "/denied")
 	public String denied() {
		return "access/denied";
	}
	@RequestMapping(value = "/checking")
   public String checking(Model model) {
		MissTestResult missTest=new MissTestResult();
		String userid=SecurityContextHolder.getContext().getAuthentication().getName();
		missTest.setUserid(userid);
		int result=0;//missExamService.checkMissTestResult(missTest);
		//0=not yet test finish, 1=  test finish
		if(result==1){ 
			model.addAttribute("message", "You test finish"); 
			return "access/login";
		}else
			return "redirect:/";
	}
	
	@RequestMapping(value = "/login/failure")
 	public String loginFailure(Model model) {
		/*String message = "Login Failure!";
		return "redirect:/login?message="+message;*/
		String message = "Login Failure!";
	//	String message = "Login เออเร่อ!";
	/*	MissTestResult missTest=new MissTestResult();
		missTest.setMtrResultCode(message);
		model.addAttribute("missTest", missTest);*/
		model.addAttribute("message", message);
		return "access/login";
	}
	
	@RequestMapping(value = "/logout/success")
 	public String logoutSuccess() {
		String message = "Logout Success!";
		/*MissTestResult missTest=new MissTestResult();
		int result=missExamService.checkMissTestResult(missTest);
		//0=not yet test finish, 1=  test finish
		if(result==1){
			message="You test finish";
		}else{
			
		}*/
		return "redirect:/login?message="+message;
	}
}