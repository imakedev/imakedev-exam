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
import org.springframework.web.bind.annotation.SessionAttributes;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.form.RoleForm;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.RoleContact;
import th.co.aoe.makedev.missconsult.xstream.RoleMapping;
import th.co.aoe.makedev.missconsult.xstream.RoleType;

@Controller
@RequestMapping(value = { "/role" })
@SessionAttributes(value={"roleForm"})
public class RoleController {
	private static Logger logger = Logger.getRootLogger();
	@Autowired
	private MissExamService missExamService;

	@RequestMapping(value = { "/{maId}" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String postItem(HttpServletRequest request,@PathVariable String maId, @ModelAttribute(value="roleForm") RoleForm roleForm, BindingResult result, Model model) {
		//RoleForm roleForm = null;
		String display="display: none";
		String message="";
				
		if (model.containsAttribute("roleForm"))
			roleForm = (RoleForm) model.asMap().get("roleForm");
		else{
			roleForm = new RoleForm();
			roleForm.setMode("edit");
		}
		String mode=roleForm.getMode();
		logger.debug(" ooooooooooooooooooooooo mode =>"+mode);
		/*Enumeration e_num_header=request.getHeaderNames();
		while (e_num_header.hasMoreElements()) {
			String header = (String) e_num_header.nextElement();
			heade
		}*/
		if(mode!=null ){
			if(mode.equals("updateRoleMapping")){
				//String[] rtIdCheckbox=request.getParameterValues("rtIdCheckbox");
				Enumeration e_num=request.getParameterNames();
				List<String> rtIdsList=new ArrayList<String>();
				while (e_num.hasMoreElements()) {
					String param_name = (String) e_num.nextElement();
					if(param_name.startsWith("rtIdCheckbox_radio_")){
						//System.out.println("object="+param_name+",value="+request.getParameter(param_name)); 
						if(!request.getParameter(param_name).equals("0")){
							rtIdsList.add(request.getParameter(param_name));
						}
					}
				}
				String[] rtIdRadio = new String[rtIdsList.size()];
				rtIdRadio = rtIdsList.toArray(rtIdRadio);
				//System.out.println("rtIdRadio size="+rtIdRadio.length); 
				//logger.debug(" rtIdCheckbox length="+rtIdCheckbox);
				if(roleForm.getRcId()!=null && roleForm.getRcId().intValue()!=0){
				RoleMapping roleMapping =new RoleMapping();
				//roleMapping.setRtIds(rtIdCheckbox);
				roleMapping.setRtIds(rtIdRadio);
				roleMapping.setRcId(roleForm.getRcId());
				missExamService.updateRoleMapping(roleMapping);
				 message = "Update Role success !";
				 display="display: block";
				}
			}else if(mode.equals("addRole")){//
				 message = "Add Role success !";
				 display="display: block";
				 RoleContact roleContact=new RoleContact();
				 roleContact.setMaId(Long.parseLong(maId));
				/* private String roleName;
					private String roleId;*/
				 roleContact.setRcName(roleForm.getRoleName());
				 missExamService.saveRoleContact(roleContact);
			}else if(mode.equals("updateRole")){//
				 message = "Update Role success !";
				 display="display: block";
				 RoleContact roleContact=new RoleContact();
				 roleContact.setMaId(Long.parseLong(maId)); 
				 roleContact.setRcId(roleForm.getRcId());
				 roleContact.setRcName(roleForm.getRoleName());
				 missExamService.updateRoleContact(roleContact);
			}else if(mode.equals("deleteRole")){//
				 message = "Delete Role success !";
				 display="display: block";
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
		if(roleForm.getRcId()!=null && roleForm.getRcId().intValue()!=0){
			roleMappings=missExamService.listRoleMappingByrcId(roleForm.getRcId());
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
		model.addAttribute("roleTypes", roleTypes);
		 model.addAttribute("message", message);
		 model.addAttribute("display", display);
		
		return "exam/template/roleSection";
	}
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
		
	
		List<RoleMapping> roleMappings= null;
		if(roleForm.getRcId()!=null && roleForm.getRcId().intValue()!=0){
			roleMappings=missExamService.listRoleMappingByrcId(roleForm.getRcId());
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
		
		model.addAttribute("roleTypes", roleTypes);
		model.addAttribute("display", "display: none");
		return "exam/template/roleSection";
	}
}
