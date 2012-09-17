package th.co.aoe.makedev.missconsult.exam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissSeryProblem;
import th.co.aoe.makedev.missconsult.xstream.MissSystemUse;
import th.co.aoe.makedev.missconsult.xstream.MissTestResult;

@Controller
@RequestMapping
public class AccessController {
	@Autowired
	private MissExamService missExamService;
	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required=false) String message) {
		
		//model.addAttribute("message", message);
	//	model.addAttribute("message", "เทสสส");
		return "access/login";
	}
	
	@RequestMapping(value = "/denied")
 	public String denied() {
		return "access/denied";
	}
	@RequestMapping(value = "/checking")
 	public String checking(Model model,HttpServletRequest request) {
		//request.get
	String	useragent = request.getHeader("User-Agent");
		String user = useragent.toLowerCase();
		 
		//System.out.println("xxxxxxxxxxxxxx= "+user); 
		/*Enumeration<String> ex=request.getHeaderNames();
		while (ex.hasMoreElements()) {
			String param_name = (String) ex.nextElement();
		}*/
		/*if(user.indexOf("chrome") != -1) {
			ie = true;
		} else if(user.indexOf("firefox") != -1) {
			ns6 = true;
		}else if(user.indexOf("opera") != -1) {
			ns6 = true;
		}*/
		
		MissTestResult missTest=new MissTestResult();
		String band="";
		 if(user.indexOf("firefox") != -1) {
			band="Firefox"; 		
		} else if(user.indexOf("chrome") != -1) {
			band="Chrome";
		} else if(user.indexOf("opera") != -1) {
			band="Opera";
		}else if(user.indexOf("safari") != -1) {
			band="Safari";
		} else if(user.indexOf("msie") != -1) {
			band="IE";
		}
		 
		String userid=SecurityContextHolder.getContext().getAuthentication().getName();
		missTest.setUserid(userid);
		int result=missExamService.checkMissTestResult(missTest);
		MissSystemUse missSystemUse=new MissSystemUse(); 
		missSystemUse.setMsystemType(0L);
		missSystemUse.setMsystemUserId(userid); 
		missSystemUse.setMsystemBrowserBand(band);
		missSystemUse.setMsystemBrowserVersion(getVersionBrowser(band,user)); 
		missSystemUse.setMsystemBrowserFullVersion(useragent); 
		missExamService.saveMissSystemUse(missSystemUse);
		//0=not yet test finish, 1=  test finish
		if(result==1){ 
			model.addAttribute("message", "You test finish"); 
			return "access/login";
		}else
			return "redirect:/";
	}
	private String getVersionBrowser(String band,String fullAgent){
		// mozilla/5.0 (x11; ubuntu; linux x86_64; rv:15.0) gecko/20100101 firefox/15.0
		// mozilla/5.0 (x11; linux x86_64) applewebkit/537.1 (khtml, like gecko) chrome/21.0.1180.57 safari/537.1
		// opera/9.80 (x11; linux x86_64; u; en) presto/2.10.289 version/12.01
		String version=""; 
		if(band.length()>0){
			String[] versions=fullAgent.split(" ");
			//System.out.println(" size "+versions.length); 
			if("Chrome".equals(band)){				
				for (int i = 0; i < versions.length; i++) {
					if(versions[i].indexOf("chrome")!=-1){
						version=versions[i].split("/")[1];
						break;
					}
				}
			}else if("Firefox".equals(band)){
				for (int i = 0; i < versions.length; i++) {
					if(versions[i].indexOf("firefox")!=-1){
						version=versions[i].split("/")[1];
						break;
					}
				}
			}else if("Opera".equals(band)){
				for (int i = 0; i < versions.length; i++) {
					if(versions[i].indexOf("version")!=-1){
						version=versions[i].split("/")[1];
						break;
					}
				}
			}else if("Safari".equals(band)){
				for (int i = 0; i < versions.length; i++) {
					if(versions[i].indexOf("version")!=-1){
						version=versions[i].split("/")[1];
						break;
					}
				}
			}else if("IE".equals(band)){
				 versions=fullAgent.split(";");
				for (int i = 0; i < versions.length; i++) {
					if(versions[i].trim().indexOf("msie")!=-1){
						versions[i]=versions[i].trim();
						version=versions[i].split(" ")[1];
						break;
					}
				}
			}
		}
		//System.out.println("vvvvvvvvvvvvvv "+version);
		return version;
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
	  
	@RequestMapping(value = "/timeout/{mcaId}/{msId}")
 	public String timeOutSuccess(@PathVariable Long mcaId,@PathVariable Long msId ) {
		//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx mcaId="+mcaId+",msId="+msId);
		MissSeryProblem missSeryProblem =new MissSeryProblem();
		missSeryProblem.setMcaId(mcaId);
		missSeryProblem.setMsId(msId);
		missSeryProblem.setMspMessage("Time Out");
		missSeryProblem.setMspType("0");
		 
		missExamService.saveMissSeryProblem(missSeryProblem);
		//String message = "Logout Success!";
		/*MissTestResult missTest=new MissTestResult();
		int result=missExamService.checkMissTestResult(missTest);
		//0=not yet test finish, 1=  test finish
		if(result==1){
			message="You test finish";
		}else{
			
		}*/
		return "redirect:/logout";
	}
}