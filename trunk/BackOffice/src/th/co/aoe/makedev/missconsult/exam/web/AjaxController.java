// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 5/27/2012 12:03:59 AM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AjaxController.java

package th.co.aoe.makedev.missconsult.exam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissDoc;
import th.co.aoe.makedev.missconsult.xstream.MissFile;
import th.co.aoe.makedev.missconsult.xstream.MissManual;
import th.co.aoe.makedev.missconsult.xstream.MissQuestion;
import th.co.aoe.makedev.missconsult.xstream.MissReportAttach;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach;
import th.co.aoe.makedev.missconsult.xstream.MissTodo;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

@Controller
@RequestMapping(value={"/ajax"})
public class AjaxController
{

  /*  @Autowired
    public AjaxController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired TestController #######################");
        this.missExamService = missExamService;
    }*/

    @RequestMapping(value={"/search"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String getToDoList(Model model)
    {
        int pageNo = 1;
        Pagging page = new Pagging();
        page.setPageNo(pageNo);
        page.setPageSize(20);
        MissTodo missTodo = new MissTodo();
        missTodo.setPagging(page);
        VResultMessage vresult = missExamService.searchMissTodo(missTodo);
        model.addAttribute("todolists", vresult.getResultListObj());
        model.addAttribute("totals", vresult.getMaxRow());
        model.addAttribute("pageObj", page);
        return "exam/test";
    }
    @RequestMapping(value={"/getMissFile/{module}/{id}/{id2}/{id3}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public  @ResponseBody MissFile getMissFile(Model model,@PathVariable String module,@PathVariable String id,@PathVariable String id2,@PathVariable String id3)
    {
    	 MissFile missFile =new MissFile();
    	 String hotLink="";
    	 String s="";
    	 String pathFolder="";
    	 String ndFilePath="";
    	 if(module.equals("mcLogo")){
			 MissAccount missAccount=  missExamService.findMissAccountById(Long.valueOf(id));
			 //missAccount.setMaId(Long.parseLong(id));
			 s= missAccount.getMaCustomizeLogoFileName();
			 hotLink = missAccount.getMaCustomizeLogoHotlink();
			 pathFolder= missAccount.getMaCustomizeLogoPath();
			 
		}else if(module.equals("companyLogo")){
			 MissAccount missAccount =   missExamService.findMissAccountById(Long.valueOf(id));
			 //missAccount.setMaId(Long.parseLong(id));
			 s= missAccount.getMaCustomizeLogoFileName();
			  hotLink = missAccount.getMaCustomizeLogoHotlink();
			 pathFolder= missAccount.getMaCustomizeLogoPath();
		}else if(module.equals("candidateImg")){
			 MissCandidate missCandidate =  missExamService.findMissCandidateById(Long.valueOf(id));
			  
			 s=missCandidate.getMcaPictureFileName();
			 hotLink= missCandidate.getMcaPictureHotlink();
			 pathFolder= missCandidate.getMcaPicturePath( ); 
		}else if(module.equals("contactImg")){ 
			 MissContact missContact =  missExamService.findMissContactById(Long.valueOf(id));
			 
			 s=missContact.getMcontactPictureFileName( );
			 hotLink=missContact.getMcontactPictureHotlink( );
			 pathFolder=  missContact.getMcontactPicturePath( ); 
		}else if(module.equals("attachManual")){
			 MissManual missManual = missExamService.findMissManualById(Long.valueOf(id));
			 
			 s=missManual.getMmFileName( );
			 hotLink=missManual.getMmHotlink( );
			 pathFolder=missManual.getMmPath( ); 
		}else if(module.equals("questionImg")){
			MissQuestion missQuestion =missExamService.findMissQuestionById(Long.parseLong(id));
			if(id2.equals("0")){//thai
				hotLink= missQuestion.getMqImgTh1();
			}else{
				hotLink= missQuestion.getMqImgEng1();
			}
			 
		}else if(module.equals("template")){
			 
		 	MissReportAttach missReportAttach =missExamService.findMissReportAttachById(Long.valueOf(id), Long.valueOf(id2), id3, null);
		 	hotLink= missReportAttach.getMraHotlink();
		 	pathFolder=missReportAttach.getMraPath();
		 	 
			 //content_disposition="attachment; filename="+missReportAttach.getMsatFileName();
			 s=missReportAttach.getMraFileName(); 
			 
		}else if(module.equals("evaluation")){
			//String[] ids=id.split("_");
		 	 MissSeriesAttach missSeriesAttach  =missExamService.findMissSeriesAttachSearch(module, Long.valueOf(id), null, null);
			   
		 	hotLink=missSeriesAttach.getMsatHotlink();
			 pathFolder= missSeriesAttach.getMsatPath( );
			s= missSeriesAttach.getMsatFileName( ); 
		}else if(module.equals("doc")){
			//	String[] ids=id.split("_");
			MissDoc missDoc =  missExamService.findMissDocById(Long.valueOf(id));
			s=missDoc.getMdDocName( );
			hotLink=missDoc.getMdDocHotlink( );
			pathFolder=missDoc.getMdDocPath( );
		  
				//MissSeriesAttach missSeriesAttach =missExamService.findMissSeriesAttachSearch(module,Long.parseLong(ids[0]),Long.parseLong(ids[1]),hotlink);
				
			} 
// return missCandidate;
missFile.setHotlink(hotLink);
missFile.setFilename(s);
missFile.setFilepath(pathFolder);
       return missFile;
    }
    //private static Logger logger = Logger.getRootLogger();
    @Autowired
    private MissExamService missExamService;

}
