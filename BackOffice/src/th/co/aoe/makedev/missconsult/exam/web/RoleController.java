package th.co.aoe.makedev.missconsult.exam.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.form.RoleForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap;
import th.co.aoe.makedev.missconsult.xstream.MissContact;
import th.co.aoe.makedev.missconsult.xstream.MissReportAttach;
import th.co.aoe.makedev.missconsult.xstream.RoleContact;
import th.co.aoe.makedev.missconsult.xstream.RoleMapping;
import th.co.aoe.makedev.missconsult.xstream.RoleSeriesMapping;
import th.co.aoe.makedev.missconsult.xstream.RoleSeriesReportMapping;
import th.co.aoe.makedev.missconsult.xstream.RoleType;

@Controller
@RequestMapping(value = { "/role" }) 
@SessionAttributes(value={"UserMissContact","roleForm"})
public class RoleController {
	private static Logger logger = Logger.getRootLogger();
	@Autowired
	private MissExamService missExamService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/{maId}" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String postItem(HttpServletRequest request,@PathVariable String maId, @ModelAttribute(value="roleForm") RoleForm roleForm, BindingResult result, Model model) {
		//RoleForm roleForm = null;
		String display="display: none";
		String message="";
		String message_class="";
		
		//logger.error("roleForm=====>"+roleForm.getMode());
		if (model.containsAttribute("roleForm"))
			roleForm = (RoleForm) model.asMap().get("roleForm");
		else{
			//logger.error(" new Form=====>");
			roleForm = new RoleForm();
			roleForm.setMode("edit");
		}
		String mode=request.getParameter("modeExt");//roleForm.getMode();
		//logger.error(" ooooooooooooooooooooooo mode =>"+mode);
		/*Enumeration e_num_header=request.getHeaderNames();
		while (e_num_header.hasMoreElements()) {
			String header = (String) e_num_header.nextElement();
			heade
		}*/
		if(mode!=null ){
			if(mode.equals("updateRoleMapping")){
				//String[] rtIdCheckbox=request.getParameterValues("rtIdCheckbox");
				@SuppressWarnings("rawtypes")
				Enumeration e_num=request.getParameterNames();
				List<String> rtIdsList=new ArrayList<String>();
				List<String> msIdsList=new ArrayList<String>();
				while (e_num.hasMoreElements()) {
					String param_name = (String) e_num.nextElement();
					if(param_name.startsWith("rtIdCheckbox_radio_")){
						if(!request.getParameter(param_name).equals("0")){
							rtIdsList.add(request.getParameter(param_name));
						}
					}
					if(param_name.startsWith("msIdCheckbox_radio_")){
						if(!request.getParameter(param_name).equals("0")){
							msIdsList.add(request.getParameter(param_name));
						}
					}
				}
				String[] rtIdRadio = new String[rtIdsList.size()];
				String[] msIdRadio = new String[msIdsList.size()];
				rtIdRadio = rtIdsList.toArray(rtIdRadio);
				msIdRadio= msIdsList.toArray(msIdRadio);
				//logger.debug(" rtIdCheckbox length="+rtIdCheckbox);
				if(roleForm.getRcId()!=null && roleForm.getRcId().intValue()!=0){
				RoleMapping roleMapping =new RoleMapping();
				//roleMapping.setRtIds(rtIdCheckbox);
				roleMapping.setRtIds(rtIdRadio);
				roleMapping.setRcId(roleForm.getRcId());
				missExamService.updateRoleMapping(roleMapping);
				
				RoleSeriesMapping roleSeriesMapping =new RoleSeriesMapping();
				roleSeriesMapping.setMsIds(msIdRadio);
				roleSeriesMapping.setRcId(roleForm.getRcId());
				missExamService.updateRoleSeriesMapping(roleSeriesMapping);
				 message = "Update Role success !";
				 message_class="success";
				 display="display: block";
				}
			}else if(mode.equals("addRole")){//
				 message = "Add Role success !";
				 display="display: block";
				 message_class="success";
				 RoleContact roleContact=new RoleContact();
				 roleContact.setMaId(Long.parseLong(maId));
				/* private String roleName;
					private String roleId;*/
				 roleContact.setRcName(roleForm.getRoleName());
				 missExamService.saveRoleContact(roleContact);
			}else if(mode.equals("updateRole")){//
				 message = "Update Role success !";
				 display="display: block";
				 message_class="success";
				 RoleContact roleContact=new RoleContact();
				 roleContact.setMaId(Long.parseLong(maId)); 
				 roleContact.setRcId(roleForm.getRcId());
				 roleContact.setRcName(roleForm.getRoleName());
				 missExamService.updateRoleContact(roleContact);
			}else if(mode.equals("deleteRole")){//
				 message = "Delete Role success !";
				 display="display: block";
				 message_class="success";
				 RoleContact roleContact=new RoleContact();
				 roleContact.setRcId(roleForm.getRcId());
				 roleContact.setMaId(Long.parseLong(maId));  
				 missExamService.deleteRoleContact(roleContact, ServiceConstant.ROLE_CONTACT_DELETE);
				 roleForm.setRcId(null);
			}
		}
		// action 0=List Role,1=Add Role,2=Edit Role,3=Delete Role,
		logger.debug(" ooooooooooooooooooooooo getRcId =>"+roleForm.getRcId());
		logger.debug(" ooooooooooooooooooooooo getRcActionId=>"+roleForm.getRcActionId());
		model.addAttribute("roleForm", roleForm);
		model.addAttribute("roleContacts", missExamService
				.listRoleContactBymaId(Long.valueOf(Long.parseLong(maId))));
		// missExamService.listRoleMappingByrcId(rcId)
		List<RoleMapping> roleMappings= null;
		List<RoleSeriesMapping> roleSeriesMappings= null;
		if(roleForm.getRcId()!=null && roleForm.getRcId().intValue()!=0){
			roleMappings=missExamService.listRoleMappingByrcId(roleForm.getRcId());
			roleSeriesMappings=missExamService.listRoleSeriesMappingByrcId(roleForm.getRcId());
		}
		List<RoleType> roleTypes = missExamService.listRoleTypes(Long.parseLong(maId));
		if(roleTypes!=null && roleTypes.size()>0){
			logger.debug(" roleTypes =>"+roleTypes.size());
			for (RoleType roleType : roleTypes) {
				if(roleMappings!=null && roleMappings.size()>0){
					logger.debug(" roleMappings =>"+roleMappings.size());
					 for (RoleMapping roleMapping : roleMappings) {
						 logger.debug("xxxxxxxxxx roleMapping.getRtId =>"+roleMapping.getRtId());
						if(roleMapping.getRtId().intValue()==roleType.getRtId().intValue()){
							roleType.setSelected("1");
							break;
						}
					}
				}
			}
		}
		List<MissAccountSeriesMap> missAccountSeriesMaps = missExamService.findMissAccountSeriesMapByMaId(Long.parseLong(maId));
		if(missAccountSeriesMaps!=null && missAccountSeriesMaps.size()>0){
			logger.debug(" missAccountSeriesMaps =>"+missAccountSeriesMaps.size());
			for (MissAccountSeriesMap missAccountSeriesMap : missAccountSeriesMaps) {
				if(roleSeriesMappings!=null && roleSeriesMappings.size()>0){
					logger.debug(" roleSeriesMappings =>"+roleSeriesMappings.size());
					 for (RoleSeriesMapping roleSeriesMapping : roleSeriesMappings) {
					//	 logger.debug("xxxxxxxxxx roleSeriesMapping.getRtId =>"+roleSeriesMapping.getRtId());
						if(roleSeriesMapping.getMsId().intValue()==missAccountSeriesMap.getMissSery().getMsId()){
							missAccountSeriesMap.setSelected("1");
							break;
						}
					}
				}
			}
		}
		model.addAttribute("missAccountSeriesMaps", missAccountSeriesMaps);
		model.addAttribute("roleTypes", roleTypes);
		 model.addAttribute("message", message);
		 model.addAttribute("display", display);
		 model.addAttribute("message_class", message_class); 
		return "exam/template/roleSection";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/{maId}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String getItem(@PathVariable String maId,  Model model) {
		RoleForm roleForm = null;
		if (model.containsAttribute("roleForm"))
			roleForm = (RoleForm) model.asMap().get("roleForm");
		else
			roleForm = new RoleForm();
		roleForm.setMode("edit");
		// action 0=List Role,1=Add Role,2=Edit Role,3=Delete Role,
		logger.debug(" ooooooooooooooooooooooo 1=>"+roleForm.getRcId());
		logger.debug(" ooooooooooooooooooooooo 2=>"+roleForm.getRcActionId());
		roleForm.setRcId(null);
		roleForm.setRcActionId(null);
		model.addAttribute("roleForm", roleForm);
		model.addAttribute("roleContacts", missExamService
				.listRoleContactBymaId(Long.valueOf(Long.parseLong(maId))));
		
	
		/*List<RoleMapping> roleMappings= null;
		if(roleForm.getRcId()!=null && roleForm.getRcId().intValue()!=0){
			roleMappings=missExamService.listRoleMappingByrcId(roleForm.getRcId());
		}*/
		
		List<RoleMapping> roleMappings= null;
		List<RoleSeriesMapping> roleSeriesMappings= null;
		if(roleForm.getRcId()!=null && roleForm.getRcId().intValue()!=0){
			roleMappings=missExamService.listRoleMappingByrcId(roleForm.getRcId());
			roleSeriesMappings=missExamService.listRoleSeriesMappingByrcId(roleForm.getRcId());
		}
		
		List<RoleType> roleTypes = missExamService.listRoleTypes(Long.parseLong(maId));
		if(roleTypes!=null && roleTypes.size()>0){
			logger.debug(" roleTypes =>"+roleTypes.size());
			for (RoleType roleType : roleTypes) {
				if(roleMappings!=null && roleMappings.size()>0){
					logger.debug(" roleMappings =>"+roleMappings.size());
					 for (RoleMapping roleMapping : roleMappings) {
						 logger.debug("xxxxxxxxxx roleMapping.getRtId =>"+roleMapping.getRtId());
						if(roleMapping.getRtId().intValue()==roleType.getRtId().intValue()){
							roleType.setSelected("1");
							break;
						}
					}
				}
			}
		}
		List<MissAccountSeriesMap> missAccountSeriesMaps = missExamService.findMissAccountSeriesMapByMaId(Long.parseLong(maId));
		if(missAccountSeriesMaps!=null && missAccountSeriesMaps.size()>0){
			logger.debug(" missAccountSeriesMaps =>"+missAccountSeriesMaps.size());
			for (MissAccountSeriesMap missAccountSeriesMap : missAccountSeriesMaps) {
				if(roleSeriesMappings!=null && roleSeriesMappings.size()>0){
					logger.debug(" roleSeriesMappings =>"+roleSeriesMappings.size());
					 for (RoleSeriesMapping roleSeriesMapping : roleSeriesMappings) {
					//	 logger.debug("xxxxxxxxxx roleSeriesMapping.getRtId =>"+roleSeriesMapping.getRtId());
						if(roleSeriesMapping.getMsId().intValue()==missAccountSeriesMap.getMsId().intValue()){
							missAccountSeriesMap.setSelected("1");
							break;
						}
					}
				}
			}
		}
		model.addAttribute("missAccountSeriesMaps", missAccountSeriesMaps);
		model.addAttribute("roleTypes", roleTypes);
		model.addAttribute("display", "display: none");
		return "exam/template/roleSection";
	}
	 @RequestMapping(value={"/get/templateSection/{rcId}/{msId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	    public  @ResponseBody List<List<MissReportAttach>> getTemplateSection(HttpServletRequest request, @PathVariable Long rcId,@PathVariable Long msId, Model model)
	    {
	    	MissReportAttach missReportAttach =new MissReportAttach();
	    	missReportAttach.setMsId(msId);
	 	
	    	List<List<MissReportAttach>> missReportAttachs= missExamService.getTemplateMissReportAttachForRole(missReportAttach);
	    	//System.out.println(missReportAttachs);
			List<RoleSeriesReportMapping> roleSeriesReportMappings=missExamService.listRoleSeriesReportMappingByrcId(rcId, msId);
			
	    	//System.out.println(roleSeriesReportMappings);
	    	if(missReportAttachs!=null && missReportAttachs.size()>0){
				logger.debug(" missReportAttachs =>"+missReportAttachs.size());
				for (List<MissReportAttach> missReportAttach_loop : missReportAttachs) {
					if(roleSeriesReportMappings!=null && roleSeriesReportMappings.size()>0){
						logger.debug(" roleSeriesReportMappings =>"+roleSeriesReportMappings.size());
						 for (RoleSeriesReportMapping roleSeriesReportMapping : roleSeriesReportMappings) {
						//	 logger.debug("xxxxxxxxxx roleSeriesMapping.getRtId =>"+roleSeriesMapping.getRtId());
							 for (MissReportAttach missReportAttach2 : missReportAttach_loop) {
								 if(roleSeriesReportMapping.getMsId().intValue()==missReportAttach2.getMsId().intValue() 
											&& roleSeriesReportMapping.getMsOrder().intValue()==missReportAttach2.getMsOrder().intValue()){
									 missReportAttach2.setSelected("1");
										//break;
									}
							}
						}
					}
				}
			}
	    	return missReportAttachs;
	    } 
	 @RequestMapping(value={"/updateRoleReportMapping/{rcId}/{msId}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	    public  @ResponseBody int updateRoleReportMapping(HttpServletRequest request, @PathVariable Long rcId,@PathVariable Long msId, Model model)
	    {
		      //System.out.println("rcId->"+rcId+",msId->"+msId+"");
				@SuppressWarnings("rawtypes")
				Enumeration e_num=request.getParameterNames();
				List<String> msOrderIdsList=new ArrayList<String>();
				while (e_num.hasMoreElements()) {
					String param_name = (String) e_num.nextElement();
					//System.out.println("not filter->"+param_name);
					if(param_name.startsWith("rtIdCheckbox_radio_report_template_")){
						//System.out.println("param_name->"+param_name);
						if(!request.getParameter(param_name).equals("0")){
							msOrderIdsList.add(request.getParameter(param_name));
						}
					}
				}
				//System.out.println("msOrderIdsList->"+msOrderIdsList);
				String[] msOrderIdRadio = new String[msOrderIdsList.size()];
				msOrderIdRadio= msOrderIdsList.toArray(msOrderIdRadio);
			return	missExamService.updateRoleSeriesReportMapping(rcId, msId, msOrderIdRadio);
		 //return 1;	
	 }
	 @RequestMapping(value={"/get/reportDownload/{msId}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	    public  @ResponseBody List<List<MissReportAttach>> getReportDownload(HttpServletRequest request, @PathVariable Long msId, Model model)
	    { 
	    	Long rcId=null;
	    	 if(model.containsAttribute("UserMissContact")){
	         	MissContact missContact= (MissContact)model.asMap().get("UserMissContact");
	         	if(missContact!=null)
	         		rcId=missContact.getRcId();
	         }
	    	 List<List<MissReportAttach>> missReportAttachs=null;
	 	   if(rcId!=null){
	 		  MissReportAttach missReportAttach =new MissReportAttach();
		    	missReportAttach.setMsId(msId);
		    	//missReportAttach.set(msId);
		    	/*System.out.println("rcId->"+rcId);
		    	System.out.println("msId->"+msId);*/
		    	missReportAttachs= missExamService.getTemplateMissReportAttachForRole(missReportAttach);
				List<RoleSeriesReportMapping> roleSeriesReportMappings=missExamService.listRoleSeriesReportMappingByrcId(rcId, msId);
				
		    	if(missReportAttachs!=null && missReportAttachs.size()>0){
					for (List<MissReportAttach> missReportAttach_loop : missReportAttachs) {
						if(roleSeriesReportMappings!=null && roleSeriesReportMappings.size()>0){
							 for (RoleSeriesReportMapping roleSeriesReportMapping : roleSeriesReportMappings) {
								 for (MissReportAttach missReportAttach2 : missReportAttach_loop) {
								  if(rcId.intValue()!=1){
									 if(roleSeriesReportMapping.getMsId().intValue()==missReportAttach2.getMsId().intValue() 
												&& roleSeriesReportMapping.getMsOrder().intValue()==missReportAttach2.getMsOrder().intValue()
												){
										 missReportAttach2.setSelected("1");
									 }else {
											missReportAttachs.remove(missReportAttach_loop);
									 }
								  }		
								}
							}
						}
					}
				}
	 	   }
	    	
	    	return missReportAttachs;
	    } 
}
