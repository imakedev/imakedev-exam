package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.MissTodoService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissTodoResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissTodoService missTodoService;
	private com.thoughtworks.xstream.XStream xstream;
	private static final String[] ignore_account=new String[]{"missTheme","missIndustryMaster"};
	private static final String[] ignore_todo=new String[]{"missAccount"}; 
	
	  
	 
	public MissTodoResource() {
		super();
		logger.debug("into constructor MissTodoResource");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doInit() throws ResourceException {
		// TODO Auto-generated method stub
		super.doInit();
		logger.debug("into doInit");
	}
	
	@Override
	protected Representation post(Representation entity, Variant variant)
			throws ResourceException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		logger.debug("into Post MissTodoResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissTodo.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissTodo xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissTodo();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissTodo) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_todo); 
					th.co.aoe.makedev.missconsult.xstream.MissAccount account=xbpsTerm.getMissAccount();
				    if(account!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount xaccount = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount();
						BeanUtils.copyProperties(account, xaccount,ignore_account);  
						bpsTerm.setMissAccount(xaccount);
					}
					
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_TODO_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo ntcCalendarReturn = missTodoService.findMissTodoById(bpsTerm.getMtodoId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissTodo> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTodo>(1);
								th.co.aoe.makedev.missconsult.xstream.MissTodo xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissTodo();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn,ignore_todo);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						else if(serviceName.equals(ServiceConstant.MISS_TODO_FIND_MAIL)){
							String email= missTodoService.findCandidateEmailFrom(bpsTerm); 
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissTodo> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTodo>(1);
								//th.co.aoe.makedev.missconsult.xstream.MissTodo xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissTodo();
								xbpsTerm.setPagging(null);
								xbpsTerm.setCandidateEmail(email);
								xntcCalendars.add(xbpsTerm); 
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							 
						} 
						else if(serviceName.equals(ServiceConstant.MISS_TODO_SAVE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missTodoService.saveMissTodo(bpsTerm)).intValue();
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TODO_UPDATE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTodoService.updateMissTodo(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TODO_DELETE)){
							int updateRecord=missTodoService.deleteMissTodo(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TODO_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missTodoService.searchMissTodo(bpsTerm,page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissTodo> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTodo>();
								if (faqs_size != null && faqs_size.length()!=0)
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissTodoObject(ntcCalendars);
								}
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						}
						
					} else {
					}
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.debug(" into Finally Call");
			try {
				if (in != null)
					in.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	
	}

	
	@Override
	protected Representation get(Variant variant) throws ResourceException {
		// TODO Auto-generated method stub
		logger.debug("test2"+variant.getMediaType()+","+MediaType.TEXT_PLAIN);
		logger.debug("into GET MissTodoResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo ntcCalendarReturn = missTodoService.findMissTodoById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissTodo> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTodo>(1);
			th.co.aoe.makedev.missconsult.xstream.MissTodo xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissTodo();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missTodoService.updateMissTodo(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missTodoService.saveMissTodo(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missTodoService.deleteMissTodo(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo();
		//bpsTerm.setMegName("Aoe");
		@SuppressWarnings("rawtypes")
		List result = (List) missTodoService.searchMissTodo(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissTodo> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTodo>();
		if (result != null && result.size() == 2) {
			@SuppressWarnings("unchecked")
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && faqs_size.length()!=0)
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissTodoObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissTodo> getxMissTodoObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissTodo> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTodo>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissTodo missTodo : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissTodo xmissTodo =new th.co.aoe.makedev.missconsult.xstream.MissTodo ();
			BeanUtils.copyProperties(missTodo, xmissTodo,ignore_todo);
			xmissTodo.setPagging(null);
			xntcCalendars.add(xmissTodo);
		}
		return xntcCalendars;
	} 
/*	private void returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissTodo xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissTodo> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTodo>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		export(entity, vresultMessage, xstream);
	}*/
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissTodo xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissTodo> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTodo>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		return getRepresentation(entity, vresultMessage, xstream);
	}
	public MissTodoService getMissTodoService() {
		return missTodoService;
	}

	public void setMissTodoService(MissTodoService missTodoService) {
		this.missTodoService = missTodoService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 
}