package th.co.aoe.makedev.missconsult.exam.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissAccount;
import th.co.aoe.makedev.missconsult.xstream.MissAttach;
import th.co.aoe.makedev.missconsult.xstream.MissCandidate;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissManual;
import th.co.aoe.makedev.missconsult.xstream.MissSeriesAttach;
@Controller
public class UploadController {	
	/*private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");*/
//	private static Logger logger = Logger.getRootLogger();
	 @Autowired
	private MissExamService missExamService;
	private static ResourceBundle bundle;
	static{
		bundle =  ResourceBundle.getBundle( "config" );				
	}
   /* @Autowired
    public UploadController(MissExamService missExamService)
    {
        logger.debug("########################### @Autowired WelcomeController #######################");
        this.missExamService = missExamService;
    }*/
   
    @RequestMapping(value={"/getfile/{module}/{id}/{hotlink}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public void getFile(HttpServletRequest request,HttpServletResponse response,@PathVariable String module
    		,@PathVariable String id,@PathVariable String hotlink)
    {
    	//String hotlink = request.getQueryString();
		//String []adminview = hotlink.split("&mode=");
    	
		//	String filePath = "/usr/local/Work/TestDownload/1338218105884kqyoujf6uwhsqqwgwqitedq89kpl01u8nitc.jpg";
    	 
    	                                          
    	String  content_type= "image/jpeg";
    	String  content_disposition= "";
    	String path= bundle.getString(module+"Path");
    	String ndPathFileGen="";
    	//path+"/"+ndPathFileGen
    	if(module.equals("mcLogo")){
    		MissAccount missAccount= missExamService.findMissAccountById(Long.parseLong(id));
    		ndPathFileGen=path+missAccount.getMaCustomizeLogoPath();
		}else if(module.equals("companyLogo")){
			MissAccount missAccount=missExamService.findMissAccountById(Long.parseLong(id));
	    	ndPathFileGen=path+missAccount.getMaCustomizeLogoPath();
		}else if(module.equals("candidateImg")){
			MissCandidate missCandidate =missExamService.findMissCandidateById(Long.parseLong(id));
			 ndPathFileGen=path+missCandidate.getMcaPicturePath();
		}else if(module.equals("contactImg")){
			MissContact missContact=missExamService.findMissContactById(Long.parseLong(id));
			 ndPathFileGen=path+missContact.getMcontactPicturePath();
		}else if(module.equals("attachManual")){
			MissManual missManual=missExamService.findMissManualById(Long.parseLong(id));
			 ndPathFileGen=path+missManual.getMmPath();
			 content_type="application/pdf";
			 content_disposition="attachment; filename="+missManual.getMmFileName();
		}else if(module.equals("questionImg")){
			MissAttach missAttach =missExamService.findMissAttachById(module,Long.parseLong(id),hotlink);
			 ndPathFileGen=path+missAttach.getMatPath();
		}else if(module.equals("template")){ // jasper
			MissSeriesAttach missSeriesAttach =missExamService.findMissSeriesAttachSearch(module,Long.parseLong(id),null,hotlink);
			 ndPathFileGen=path+missSeriesAttach.getMsatPath();
			 content_type="";
			 content_disposition="attachment; filename="+missSeriesAttach.getMsatFileName();
		}else if(module.equals("evaluation")){
			String[] ids=id.split("_");
			MissSeriesAttach missSeriesAttach =missExamService.findMissSeriesAttachSearch(module,Long.parseLong(ids[0]),Long.parseLong(ids[1]),hotlink);
			 ndPathFileGen=path+missSeriesAttach.getMsatPath();
			 content_type="application/vnd.ms-excel";
			 content_disposition="attachment; filename="+missSeriesAttach.getMsatFileName();
		}
    	//String filePath =  bundle.getString(module+"Path")+hotlink+".jpg";
		//	String fileName = null;
			  
			//	String filenameStr ="เทสfชาติชาย.jpg";// fileName.trim().replaceAll(" ","_");
				//response.setHeader("Content-Type", "application/octet-stream; charset=tis620");
    	    if(content_type.length()>0)
				response.setHeader("Content-Type", content_type);
			if(content_disposition.length()>0)
				response.addHeader("Content-Disposition",content_disposition);
			//	logger.debug(" filenameStr==>"+filenameStr);
			/*	response.addHeader("content-disposition",
				        "attachment; filename=\"\u0e01เทสfชาติชาย.jpg\"");*/
			/*	response.addHeader("content-disposition",
				        "inline; filename="+filenameStr.trim());*/
				
				InputStream in = null;
			      OutputStream out=null;
				try {
					out = response.getOutputStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			      InputStream stream  = null;
			      try {   
			    		  stream = new FileInputStream(ndPathFileGen);
					         in = new BufferedInputStream(stream);
			         while (true) {
			            int data = in.read();
			            if (data == -1) {
			               break;
			            }
			            out.write(data);
			         }
			      }catch (Exception e) {
			    	  e.printStackTrace();
					// TODO: handle exception
				 } finally {
			         if (in != null) {
			            try {
							in.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			         }
			         if (stream != null) {
			        	 try {
							stream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				         } 
			         if (out != null) {
			            try { 
							  out.flush();
						      out.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			         }
			      }
    }
   /* private void createDirectoryIfNeeded(String directoryName)
  	 {
  	   File theDir = new File(directoryName);

  	   // if the directory does not exist, create it
  	   if (!theDir.exists())
  	   {
  		   //boolean cancreate = theDir.mkdir();
  		   theDir.mkdir();
  	   }
  	  
  	 }
      private String genToken(){
  		StringBuffer sb = new StringBuffer();
  	    for (int i = 36; i > 0; i -= 12) {
  	      int n = Math.min(12, Math.abs(i));
  	      sb.append(org.apache.commons.lang.StringUtils.leftPad(Long.toString(Math.round(Math.random() * Math.pow(36, n)), 36), n, '0'));
  	    }
  	    return sb.toString();
   }*/
}
