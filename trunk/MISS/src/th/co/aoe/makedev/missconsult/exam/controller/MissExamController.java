package th.co.aoe.makedev.missconsult.exam.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;

@Controller
@SessionAttributes( { "missExamForm" })
public class MissExamController {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER); 
	@Autowired
	private MissExamService missExamService;
//	private static ResourceBundle bundle;
	/*static{
		  //bundle =  ResourceBundle.getBundle( "org/restlet/example/book/restlet/ch8/mailApplication" );
	//	bundle =  ResourceBundle.getBundle( "config" );				
	}*/
	/*@Autowired
	public MissExamController(MissExamService missExamService) {
		logger.debug("########################### @Autowired MissExamController #######################");
		this.missExamService = missExamService;
	}*/

	/*@InitBinder
	public void initBinder(WebDataBinder binder
			) {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor()); 
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}*/
	@RequestMapping(value="/exam/info", method = RequestMethod.GET)
    public String getExamInfo(Model model) {
		logger.debug("testtttttttttt"+missExamService);
		model.addAttribute("aoe", "chatchai");
		
		System.out.println("aoee==>"+missExamService);
        return "exam/examInfo";
    }
	@RequestMapping(value="/exam", method = RequestMethod.GET)
    public String getExam(Model model) {
		logger.debug("testtttttttttt"+missExamService);
		model.addAttribute("aoe", "chatchai");
		System.out.println("aoee==>"+missExamService);
        return "exam/exam";
    }
	@RequestMapping(value="/exam", method = RequestMethod.POST)
    public String postExam(Model model) {
		logger.debug("testtttttttttt"+missExamService);
		model.addAttribute("aoe", "chatchai");
		System.out.println("aoee==>"+missExamService);
        return "exam/exam";
    }
	@RequestMapping(value="/exam/message", method = RequestMethod.GET)
    public String getExamMessage(Model model) {
		logger.debug("testtttttttttt"+missExamService);
		model.addAttribute("aoe", "chatchai");
		System.out.println("aoee==>"+missExamService);
        return "exam/examMessage";
    } 
	/* 
	@RequestMapping(params="action=manageBpsGroup")
	public String manageBpsGroup(Model model,@RequestParam(value="pageNo",required = false) String pageNoStr
			,@RequestParam(value="bpgGroupName",required = false) String bpgGroupName
			,@RequestParam(value="orderBy",required = false) String orderBy
			,@RequestParam(value="orderColumn",required = false) String orderColumn)  {
		MissExamForm bpsAdminForm=null;
		bpgGroupName=(bpgGroupName!=null&&bpgGroupName.trim().length()>0)?bpgGroupName.trim():"";
		orderBy=(orderBy!=null&&orderBy.trim().length()>0)?orderBy.trim():"";
		orderColumn=(orderColumn!=null&&orderColumn.trim().length()>0)?orderColumn.trim():"";
		int pageNo = 1; 
		if(pageNoStr!=null && !pageNoStr.equals(""))
			pageNo= Integer.parseInt(pageNoStr);
		 
		Pagging page   = new Pagging();
		page.setPageNo(pageNo);
		page.setPageSize(20);
		if(!model.containsAttribute("bpsAdminForm")){
			bpsAdminForm= new MissExamForm();
		}else {
			Map map  = model.asMap();
			bpsAdminForm = (MissExamForm)map.get("bpsAdminForm");			 
		}  
		BpsGroup bpsGroup =bpsAdminForm.getBpsGroup();
		bpsGroup.setPagging(page);
		bpsGroup.setBpgGroupName(bpgGroupName);
		bpsAdminForm.setBpgGroupName(bpgGroupName);
		bpsGroup.getVcriteria().setOrderBy(orderBy);
		bpsGroup.getVcriteria().setOrderColumn(orderColumn);
		VResultMessage resultList = bpsAdminService.searchBpsGroup(bpsGroup);
		
	//	model.addAttribute("bpsGroups", resultList);
		model.addAttribute("bpsAdminForm", bpsAdminForm);
		return "manageBpsGroup";
	}
	
	@RequestMapping(params="action=addOrEditBpsGroup")
	public String addOrEditBpsGroup(Model model,
			@RequestParam("bpgId") String bpgId,
			@RequestParam(value="mode") String mode) {
	try{
		BpsAdminForm bpsAdminForm=null; 
		if(!model.containsAttribute("bpsAdminForm")){
			bpsAdminForm= new BpsAdminForm();
		}else {
			Map map  = model.asMap();
			bpsAdminForm = (BpsAdminForm)map.get("bpsAdminForm");			 
		} 
		BpsGroup bpsGroup =null; 
		if(!mode.equals("add")){
			bpsGroup = bpsAdminService.findBpsGroupById(bpgId);
		}else{
			bpsGroup=new BpsGroup();
		}
		bpsAdminForm.setMode(mode);
		bpsAdminForm.setBpsGroup(bpsGroup);
		 
		model.addAttribute("bpsAdminForm", bpsAdminForm);
		model.addAttribute("mode",mode); 
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		return "addOrEditBpsGroup";
	}
	@RequestMapping(value="/someUrl", method = RequestMethod.POST)
	public String onSubmit(@RequestPart("meta-data") MetaData metadata,
	                       @RequestPart("file-data") MultipartFile file) {
	    // ...

	}
	@RequestMapping(params = "action=saveBpsGroup")
	public String saveBpsGroup(
			@ModelAttribute("bpsAdminForm")
			MissExamForm bpsAdminForm, BindingResult result, Model model) {
		BpsGroup bpsGroup = bpsAdminForm.getBpsGroup();
		int recordUpdate=0;
		if (bpsAdminForm.getMode().equals("add")) {
			recordUpdate=bpsAdminService.saveBpsGroup(bpsGroup);
		} else {
			recordUpdate=bpsAdminService.updateBpsGroup(bpsGroup);
		}
		//response.setRenderParameter("action", "manageBpsGroup");
		return null;
	}
	
	@RequestMapping(params="action=deleteBpsGroup")
	public void deleteBpsGroup(ActionRequest request, ActionResponse response,
			Model model, @RequestParam(value="bpgId") String bpgId) {
		bpsAdminService.deleteBpsGroup(bpgId);
		response.setRenderParameter("action", "manageBpsGroup");
	}
	
	// BPS Term
	@RequestMapping // (params="action=manageBpsTerm")
	public String manageBpsTerm(HttpRequest request,Model model,@RequestParam(value="pageNo",required = false) String pageNoStr
			,@RequestParam(value="bptTerm",required = false) String bptTerm
			,@RequestParam(value="bpgId",required = false) String bpgId
			,@RequestParam(value="searchBy",required = false) String searchBy
			,@RequestParam(value="orderBy",required = false) String orderBy
			,@RequestParam(value="orderColumn",required = false) String orderColumn
			,@RequestParam(value="indexChar",required = false) String indexChar)  {
	 
		MissExamForm bpsAdminForm=null;
		
		bptTerm=(bptTerm!=null&&bptTerm.trim().length()>0)?bptTerm.trim():"";
		bpgId=(bpgId!=null&&bpgId.trim().length()>0)?bpgId.trim():"0";
		searchBy=(searchBy!=null&&searchBy.trim().length()>0)?searchBy.trim():"0";
		orderBy=(orderBy!=null&&orderBy.trim().length()>0)?orderBy.trim():"asc";
		orderColumn=(orderColumn!=null&&orderColumn.trim().length()>0)?orderColumn.trim():"bptTerm";
		if(bptTerm.length()>0)
			indexChar=bptTerm.substring(0, 1);
		else
			indexChar=(indexChar!=null&&indexChar.trim().length()>0)?indexChar.trim():"a";
		int pageNo = 1; 
		if(pageNoStr!=null && !pageNoStr.equals(""))
			pageNo= Integer.parseInt(pageNoStr);
		 
		Pagging page   = new Pagging();
		page.setPageNo(pageNo);
		page.setPageSize(10);
		 
		bpsAdminForm= new MissExamForm();
		BpsTerm bpsTerm =bpsAdminForm.getBpsTerm();
		bpsAdminForm.setOrderColumn(orderColumn);
		bpsAdminForm.setOrderBy(orderBy);
		bpsAdminForm.setIndexChar(indexChar);
		bpsTerm.setPagging(page);
		bpsTerm.setBptTerm(bptTerm);
		bpsTerm.getVcriteria().setValue(bptTerm) ;
		bpsTerm.getVcriteria().setOrderBy(orderBy);
		bpsTerm.getVcriteria().setOrderColumn(orderColumn);
		bpsTerm.getVcriteria().setIndexChar(indexChar);
		if(!bpgId.equals("0")){
			BpsGroup group = new BpsGroup();
			group.setBpgId(Long.parseLong(bpgId));
			bpsTerm.setBpsGroup(group);
		}
		String key="";
		if(!searchBy.equals("0")){ //1=by term ,2 =by Difinition , 3 all
			if(searchBy.equals("1")){
				//bptTerm
				key="bptTerm";
			}else if(searchBy.equals("2")){
				//bptDefinitionSearch
				key="bptDefinitionSearch";
			}else if(searchBy.equals("3")){
				key="bptAll";
			}
		}
		bpsTerm.getVcriteria().setKey(searchBy);
		bpsAdminForm.setBpgId(bpgId); 
		bpsAdminForm.setSearchBy(searchBy);
		bpsAdminForm.setBptTerm(bptTerm);
		//VResultMessage resultList = bpsAdminService.searchBpsTerm(bpsTerm);
	//	page.setTotalRecord(Integer.parseInt(resultList.getMaxRow()));
		model.addAttribute("pageObj", 	page);
		//model.addAttribute("bpsTerms", resultList);
		model.addAttribute("listCates", listBpsGroup());
		model.addAttribute("bpsAdminForm", bpsAdminForm);
		return "manageBpsTerm";
	}
	
	@RequestMapping(params="action=addOrEditBpsTerm")
	public String addOrEditBpsTerm(Model model,
			@RequestParam("bptId") String bptId,
			@RequestParam(value="mode") String mode) {
		MissExamForm bpsAdminForm=null; 
		VResultMessage resultList_version=null;
		VResultMessage resultList_files=null;
		if(!model.containsAttribute("bpsAdminForm")){
			bpsAdminForm= new MissExamForm();
		}else {
			Map map  = model.asMap();
			bpsAdminForm = (MissExamForm)map.get("bpsAdminForm");			 
		} 
	 
		bpsAdminForm.setMode(mode);
	//	bpsAdminForm.setBpsTerm(bpsTerm);
		model.addAttribute("bpsAttachFiles", resultList_files); 
		model.addAttribute("bpsTermVersions", resultList_version);
		model.addAttribute("bpsAdminForm", bpsAdminForm);
		model.addAttribute("listCates", listBpsGroup());
		model.addAttribute("mode",mode); 
		 
		return "addOrEditBpsTerm";
	}
	@RequestMapping(params="action=viewBpsTerm")
	public String viewBpsTerm(Model model,
			@RequestParam("bptId") String bptId) { 
 
		 
		return "viewBpsTerm";
	}
	@RequestMapping(params = "action=saveBpsTerm")
	public void saveBpsTerm(HttpRequest request, ActionResponse response,
			@ModelAttribute("bpsAdminForm")
			MissExamForm bpsAdminForm, BindingResult result, Model model) {
	try{
		BpsTerm bpsTerm = bpsAdminForm.getBpsTerm();
		String user=request.getUserPrincipal().getName();
		int recordUpdate=0; 
		String mode=bpsAdminForm.getMode();
		bpsTerm.setBptCreateBy(user);
		if (mode.equalsIgnoreCase("add")) {
			recordUpdate=bpsAdminService.saveBpsTerm(bpsTerm);
			bpsTerm.setBptId(Long.parseLong(recordUpdate+"")); 
		} else if(mode.equalsIgnoreCase("updateVersion")){
			recordUpdate=bpsAdminService.updateBpsTermVersion(bpsTerm); 
		}else if(mode.equalsIgnoreCase("edit")){
			recordUpdate=bpsAdminService.updateBpsTerm(bpsTerm); 
		}

		//MultipartActionRequest multipartRequest = (MultipartActionRequest) request;
	//	PortletRequest req = (PortletRequest)request;
		 //  MultipartFile multipartFile = multipartRequest.getFile("file");
		   org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest  multipartRequest = (org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest)request;
		   				  
		   if(multipartRequest!=null){				 
			  Map file_map= multipartRequest.getFileMap();
			  for (Iterator iterator = file_map.keySet().iterator(); iterator.hasNext();) {
				  String key=(String)iterator.next();
				  MultipartFile multipart =multipartRequest.getFile(key);
				  //MultipartFile multipartFile = multipartRequest.getFile("file");
				//org.springframework.web.multipart.MultipartFile 
				if(multipart!=null){
					FileOutputStream fos = null;

					try {
						byte []filesize = multipart.getBytes(); 
						if(filesize.length>0){									
							long current = System.currentTimeMillis();
						org.joda.time.DateTime    dt1  = new org.joda.time.DateTime (new Date().getTime()); 
							
						  String monthStr= dt1.getMonthOfYear()+"";
						  String yearStr= dt1.getYear()+"";
						  monthStr = monthStr.length()>1?monthStr:"0"+monthStr;
						  //String ndFilePath = "/usr/local/Work/TestDownload/"+yearStr+"_"+monthStr+"";
						  String ndFilePath = bundle.getString("downloadPath")+yearStr+"_"+monthStr+"";
						  String path =ndFilePath;
						  createDirectoryIfNeeded(path);
						  String filename = multipart.getOriginalFilename();
						  String []filenameSplit  =filename.split("\\.");
						  String extension ="";
						  if(filenameSplit!=null && filenameSplit.length>0){
							  extension =filenameSplit[filenameSplit.length-1];
						  }
						 String ndPathFileGen =current+"."+extension; 
						 fos = new FileOutputStream(path+"/"+ndPathFileGen);								
						 fos.write(filesize);
						 //gen url
						 BpsAttachFile bpsAttachFile =new BpsAttachFile();
						 bpsAttachFile.setBpafFileName(filename);
						 bpsAttachFile.setBpafFilePath(path+"/"+ndPathFileGen);
						 bpsAttachFile.setBpafFileSize(filesize.length+"");
						 bpsAttachFile.setBpafHotLink(genToken());
						 bpsAttachFile.setBpsTerm(bpsTerm);
						 bpsAdminService.saveBpsAttachFile(bpsAttachFile);
						 
						}
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
				
			} 
		  }
		
		response.setRenderParameter("action", "manageBpsTerm");
		response.setRenderParameter("bptTerm", "");
		response.setRenderParameter("bpgId", "");
		response.setRenderParameter("searchBy", "");
		response.setRenderParameter("orderBy", "");
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}
	
	@RequestMapping(params="action=deleteBpsTerm")
	public void deleteBpsTerm(HttpRequest request, HttpResponse response,
			Model model, @RequestParam(value="bptId") String bptId) {
		bpsAdminService.deleteBpsTerm(bptId); 
		response.setRenderParameter("action", "manageBpsTerm"); 
	}
	@RequestMapping(params = "action=doSubmit")
	public void doSubmit(HttpRequest request, ActionResponse response,
			@ModelAttribute("bpsAdminForm")	MissExamForm bpsAdminForm,
			BindingResult result, Model model) {
		String command=bpsAdminForm.getCommand();
		if(command.equals("edit")){
			response.setRenderParameter("action", "addOrEditBpsTerm");
		}else if(command.equals("view")){
			response.setRenderParameter("action", "viewBpsTerm");
		}
		response.setRenderParameter("bptId", bpsAdminForm.getBptId() + ""); 
		response.setRenderParameter("mode", command); 
	}
	 private void createDirectoryIfNeeded(String directoryName)
	 {
	   File theDir = new File(directoryName);

	   // if the directory does not exist, create it
	   if (!theDir.exists())
	   {
		   boolean cancreate = theDir.mkdir();
	   }
	  
	 }
	 private void deleteOldFile(String realPathFile){
		 File f1 = new File(realPathFile);
		 if(f1.exists())				
			 f1.delete();	
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
