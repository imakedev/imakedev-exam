package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.MissContactService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissContactResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissContactService missContactService;
	private com.thoughtworks.xstream.XStream xstream;
	private static 	final String[] ignore_id=new String[]{"missAccount","missSery"};
	 
	public MissContactResource() {
		super();
		logger.debug("into constructor MissContactResource");
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
		logger.debug("into Post MissContactResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissContact.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissContact xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissContact();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissContact) ntcCalendarObj;
				if (xbpsTerm != null) {
				
					 
					th.co.aoe.makedev.missconsult.hibernate.bean.MissContact bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissContact();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					/*if(xbpsTerm.getMissSery()!=null && xbpsTerm.getMissSery().getMsId()!=null && xbpsTerm.getMissSery().getMsId().intValue()!=0){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery=new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
						missSery.setMsId(xbpsTerm.getMissSery().getMsId());
						bpsTerm.setMissSery(missSery);
					}
					if(xbpsTerm.getMissAccount()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount missAccount=new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount();
						//missAccount.setMaName(xbpsTerm.getMissAccount().getMaName());
						BeanUtils.copyProperties(xbpsTerm.getMissAccount(),missAccount);
						bpsTerm.setMissAccount(missAccount);
					}*/
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_CONTACT_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissContact ntcCalendarReturn = missContactService.findMissContactById(bpsTerm.getMcontactId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissContact> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissContact>(1);
								th.co.aoe.makedev.missconsult.xstream.MissContact xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissContact();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn,ignore_id);	
								xntcCalendarReturn.setPagging(null);
								/*if(ntcCalendarReturn.getMissSery()!=null && ntcCalendarReturn.getMissSery().getMsId()!=null && ntcCalendarReturn.getMissSery().getMsId().intValue()!=0){
									th.co.aoe.makedev.missconsult.xstream.MissSery missSery=new th.co.aoe.makedev.missconsult.xstream.MissSery();
									BeanUtils.copyProperties(ntcCalendarReturn.getMissSery(), missSery);
									missSery.setPagging(null);
									xntcCalendarReturn.setMissSery(missSery);
								}
								if(ntcCalendarReturn.getMissAccount()!=null && ntcCalendarReturn.getMissAccount().getMaId()!=null && ntcCalendarReturn.getMissAccount().getMaId().intValue()!=0){
									th.co.aoe.makedev.missconsult.xstream.MissAccount missAccount=new th.co.aoe.makedev.missconsult.xstream.MissAccount();
									BeanUtils.copyProperties(ntcCalendarReturn.getMissAccount(), missAccount);
									 missAccount.setPagging(null);
									xntcCalendarReturn.setMissAccount(missAccount);
								}
								*/
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
								 
								
							}
						} 
						else if(serviceName.equals(ServiceConstant.MISS_CONTACT_FIND_BY_USERNAME)){
							th.co.aoe.makedev.missconsult.xstream.MissContact xntcCalendarReturn = missContactService.findMissContactByUsername(bpsTerm.getMcontactUsername());
						logger.debug(" object return ="+xntcCalendarReturn);
							if(xntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissContact> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissContact>(1);
								//th.co.aoe.makedev.missconsult.xstream.MissContact xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissContact();
								//BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn,ignore_id);	
								xntcCalendarReturn.setPagging(null);
							
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						else if(serviceName.equals(ServiceConstant.MISS_CONTACT_COUNT_BY_USERNAME)){
							int updateRecord= missContactService.countMissContactByUsername(bpsTerm.getMcontactUsername(),bpsTerm.getMcontactId()); 
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						} 
						else if(serviceName.equals(ServiceConstant.MISS_CONTACT_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							Long mcontactId=0l;
							bpsTerm.setMcontactCreatedTime(timeStampStartDate);
								mcontactId=(missContactService.saveMissContact(bpsTerm));
							xbpsTerm.setMcontactId(mcontactId);
						
							return returnUpdateRecord(entity,xbpsTerm,mcontactId.intValue());
						}
						else if(serviceName.equals(ServiceConstant.MISS_CONTACT_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							bpsTerm.setMcontactUpdatedTime(timeStampStartDate);
							int updateRecord=missContactService.updateMissContact(bpsTerm,xbpsTerm.getSection());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_CONTACT_UPDATE_PHOTO)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							bpsTerm.setMcontactUpdatedTime(timeStampStartDate);
							int updateRecord=missContactService.updateMissContactPhoto(bpsTerm,xbpsTerm.getSection());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						
						else if(serviceName.equals(ServiceConstant.MISS_CONTACT_ITEMS_DELETE)){
						/*	int updateRecord=missContactService.deleteMissContact(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
							*/
							String[] mcontactIds=xbpsTerm.getMcontactIds().split(",");
							logger.debug("    mcontactIds ="+mcontactIds.length);
							int updateRecord=0;
							for (int i = 0; i <mcontactIds.length; i++) {
								th.co.aoe.makedev.missconsult.hibernate.bean.MissContact item = new th.co.aoe.makedev.missconsult.hibernate.bean.MissContact();
								item.setMcontactId(Long.parseLong(mcontactIds[i]));
								updateRecord=missContactService.deleteMissContact(item);
							}
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_CONTACT_DELETE)){
							int updateRecord=missContactService.deleteMissContact(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_CONTACT_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missContactService.searchMissContact(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissContact> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissContact>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissContact> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissContact>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissContactObject(ntcCalendars);
								}
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						}
						else if(serviceName.equals(ServiceConstant.MISS_CONTACT_LIST)){
							java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissContact> ntcCalendars=(ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissContact>) missContactService.listContacts(xbpsTerm.getMcontactRef(),xbpsTerm.getMcontactType());
							List<th.co.aoe.makedev.missconsult.xstream.MissContact> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissContact>();
							if (ntcCalendars != null && ntcCalendars.size() > 0) {
								xntcCalendars = getxMissContactObject(ntcCalendars);
							}
							logger.debug(" contact return ="+ntcCalendars);
							VResultMessage vresultMessage = new VResultMessage();
							vresultMessage.setResultListObj(xntcCalendars);
							return getRepresentation(entity, vresultMessage, xstream);
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
		logger.debug("into GET MissContactResource");
		
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissContact bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissContact();
		//bpsTerm.setMegName("Aoe");
		List result = (List) missContactService.searchMissContact(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissContact> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissContact>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissContact> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissContact>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissContactObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissContact> getxMissContactObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissContact> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissContact> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissContact>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissContact missContact : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissContact xmissContact =new th.co.aoe.makedev.missconsult.xstream.MissContact ();
			BeanUtils.copyProperties(missContact, xmissContact,ignore_id);
			xmissContact.setPagging(null);
			xntcCalendars.add(xmissContact);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissContact xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissContact> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissContact>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissContactService getMissContactService() {
		return missContactService;
	}

	public void setMissContactService(MissContactService missContactService) {
		this.missContactService = missContactService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
