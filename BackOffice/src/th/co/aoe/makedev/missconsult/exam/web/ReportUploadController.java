package th.co.aoe.makedev.missconsult.exam.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissFile;
import th.co.aoe.makedev.missconsult.xstream.MissReportAttach;

import com.google.gson.Gson;

@Controller
@RequestMapping(value={"/reportUpload"})
public class ReportUploadController {
	
	//private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	//private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static Logger logger = Logger.getRootLogger();
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
    @RequestMapping(value={"/upload/{msId}/{msOrder}/{mraLang}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public  String doUpload(HttpServletRequest request, Model model,@PathVariable Long msId
    		,@PathVariable Long msOrder,@PathVariable String mraLang)
    { 
    	 String reportName=request.getParameter("reportName"); 
    	 //System.out.println("reportName-->"+reportName);
    	 String ndPathFileGen=null;
    	 MissFile missFile =new MissFile();
    	 String hotLink="";
    	 String s="";
    	 String pathFolder="";
    	 String ndFilePath="";
       /* logger.debug("xxxxxxxxxxxxxxxxxxxxxxxx="+request.getParameter("test"));
        Map m =request.getParameterMap();
        for (Iterator iterator = m.keySet().iterator(); iterator.hasNext();) {
			String type = (String) iterator.next();
			String[] key=(String[])m.get(type);
			for (int i = 0; i < key.length; i++) {
			}
		}*/
    	  MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest)request;
        MultipartFile multipart = multipartRequest.getFile("userfile");
		if(multipart!=null){
                String contentType = multipart.getContentType();
                 s = multipart.getOriginalFilename();
                logger.debug("fileName ===> "+s);
                logger.debug("contentType ===> "+contentType);
                s = FilenameUtils.getName(s);
                logger.debug("fileName2 ===> "+s);
                String monthStr= "";
				  String yearStr="";
				  
				  //String pathFolder="";
                FileOutputStream fos = null;
					try {  
						byte []filesize = multipart.getBytes(); 
						logger.debug("xxxxxxxxxxxxx="+filesize.length);
						if(filesize.length>0){									
							long current = System.currentTimeMillis();
						org.joda.time.DateTime    dt1  = new org.joda.time.DateTime (new Date().getTime()); 
							
						  monthStr= dt1.getMonthOfYear()+"";
						  yearStr= dt1.getYear()+"";
						  monthStr = monthStr.length()>1?monthStr:"0"+monthStr;
					
						  pathFolder=yearStr+"_"+monthStr+"";
						  ndFilePath = bundle.getString("reportTemplatePath")+pathFolder;
						  String path =ndFilePath;
						  createDirectoryIfNeeded(path);
						  String filename =s ;// multipart.getOriginalFilename();
						  String []filenameSplit  =filename.split("\\.");
						  String extension ="";
						  if(filenameSplit!=null && filenameSplit.length>0){
							  extension =filenameSplit[filenameSplit.length-1];
						  }
						  hotLink=current+""+genToken();
						 ndPathFileGen =hotLink+"."+extension; 
						 pathFolder=pathFolder+"/"+ndPathFileGen;
					//	 FileInputStream fin= new FileInputStream(file)
						 fos = new FileOutputStream(path+"/"+ndPathFileGen);								
						 fos.write(filesize);
						}
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					finally{
						if(fos!=null)
							try {
								fos.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	 
					} 
			  
					 MissReportAttach missReportAttach = new MissReportAttach();
					 missReportAttach.setMsId(msId);
					 missReportAttach.setMsOrder(msOrder);
					 missReportAttach.setMraLang(mraLang);
					 missReportAttach.setMraHotlink(hotLink);
					 missReportAttach.setMraPath(pathFolder);
					 missReportAttach.setMraFileName(s);
					 missReportAttach.setMraReportName(reportName);
					 missExamService.updateMissReportAttach(missReportAttach);
				 
		}
       // return missCandidate;
		missFile.setHotlink(hotLink);
		missFile.setFilename(s);
		missFile.setFilepath(pathFolder);
		Gson gson=new Gson();
		gson.toJson(missFile );
	//	return hotLink;
		 return gson.toJson(missFile );
    }
    @RequestMapping(value={"/getfile/{msId}/{msOrder}/{mraLang}/{hotlink}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public void getFile(HttpServletRequest request,HttpServletResponse response,@PathVariable Long msId
    		,@PathVariable Long msOrder,@PathVariable String mraLang,@PathVariable String hotlink)
    {
    	                                           
    	String  content_type= "image/jpeg";
    	//String  content_disposition= "";
    	String  filename= "";
    	String path= bundle.getString("reportTemplatePath");
    	String ndPathFileGen="";
    	//path+"/"+ndPathFileGen
    	  // jasper
			MissReportAttach missReportAttach =missExamService.findMissReportAttachById(msId, msOrder, mraLang, hotlink);
			 ndPathFileGen=path+missReportAttach.getMraPath();
			 content_type="";
			 //content_disposition="attachment; filename="+missReportAttach.getMsatFileName();
			 filename=missReportAttach.getMraFileName();
		 
    	//String filePath =  bundle.getString(module+"Path")+hotlink+".jpg";
		//	String fileName = null;
			  
			//	String filenameStr ="เทสfชาติชาย.jpg";// fileName.trim().replaceAll(" ","_");
				//response.setHeader("Content-Type", "application/octet-stream; charset=tis620");
    	    if(content_type.length()>0)
				response.setHeader("Content-Type", content_type);
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
				//byte[] fileNameBytes=null;
				/*try {
					fileNameBytes = fileName.getBytes(("utf-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			    String dispositionFileName = "";
				 //for (byte b: fileNameBytes) dispositionFileName += (char)(b & 0xff);
			    for (byte b: fileNameBytes) dispositionFileName += (char)(b & 0xff);

				 String disposition = "attachment; filename=\"" + dispositionFileName + "\"";
				 response.setHeader("Content-disposition", disposition);
				//response.addHeader("Content-Disposition",content_disposition);
			}
			//	logger.debug(" filenameStr==>"+filenameStr);
			/*	response.addHeader("content-disposition",
				        "attachment; filename=\"\u0e01เทสfชาติชาย.jpg\"");*/
			/*	response.addHeader("content-disposition",
				        "inline; filename="+filenameStr.trim());*/
			File file = new File(ndPathFileGen);

			boolean fileExists = file.exists();
			if(fileExists){
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
			    		  stream = new FileInputStream(file);
			    		 
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
    }
    private void createDirectoryIfNeeded(String directoryName)
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
   }

}
