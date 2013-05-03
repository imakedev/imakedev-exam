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
import th.co.aoe.makedev.missconsult.managers.MissChoiceService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissChoiceResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissChoiceService missChoiceService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	public MissChoiceResource() {
		super();
		logger.debug("into constructor MissChoiceResource");
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
		logger.debug("into Post MissChoiceResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissChoice.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissChoice xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissChoice();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissChoice) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						/*if(serviceName.equals(ServiceConstant.MISS_CHOICE_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice ntcCalendarReturn = missChoiceService.findMissChoiceById(bpsTerm.getMcId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissChoice> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissChoice>(1);
								th.co.aoe.makedev.missconsult.xstream.MissChoice xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissChoice();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						}*/ 
						if(serviceName.equals(ServiceConstant.MISS_CHOICE_SAVE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missChoiceService.saveMissChoice(bpsTerm)).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_CHOICE_UPDATE)){
						//	java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missChoiceService.updateMissChoice(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_CHOICE_DELETE)){
							/*int updateRecord=missChoiceService.deleteMissChoice(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);*/
						}
						else if(serviceName.equals(ServiceConstant.MISS_CHOICE_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missChoiceService.searchMissChoice(bpsTerm,page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissChoice> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissChoice>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissChoiceObject(ntcCalendars);
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
		logger.debug("into GET MissChoiceResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice ntcCalendarReturn = missChoiceService.findMissChoiceById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissChoice> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissChoice>(1);
			th.co.aoe.makedev.missconsult.xstream.MissChoice xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissChoice();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missChoiceService.updateMissChoice(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missChoiceService.saveMissChoice(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missChoiceService.deleteMissChoice(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice();
		//bpsTerm.setMegName("Aoe");
		@SuppressWarnings("rawtypes")
		List result = (List) missChoiceService.searchMissChoice(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissChoice> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissChoice>();
		if (result != null && result.size() == 2) {
			@SuppressWarnings("unchecked")
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissChoiceObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissChoice> getxMissChoiceObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissChoice> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissChoice>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice missChoice : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissChoice xmissChoice =new th.co.aoe.makedev.missconsult.xstream.MissChoice ();
			BeanUtils.copyProperties(missChoice, xmissChoice);
			xmissChoice.setPagging(null);
			xntcCalendars.add(xmissChoice);
		}
		return xntcCalendars;
	} 
	private void returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissChoice xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissChoice> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissChoice>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		export(entity, vresultMessage, xstream);
	}
 
	public MissChoiceService getMissChoiceService() {
		return missChoiceService;
	}

	public void setMissChoiceService(MissChoiceService missChoiceService) {
		this.missChoiceService = missChoiceService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 
	
}