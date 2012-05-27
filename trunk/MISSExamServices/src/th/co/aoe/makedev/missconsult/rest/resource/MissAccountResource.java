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
import th.co.aoe.makedev.missconsult.managers.MissAccountService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissAccountResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissAccountService missAccountService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	public MissAccountResource() {
		super();
		logger.debug("into constructor MissAccountResource");
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
		logger.debug("into Post MissAccountResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissAccount.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissAccount xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissAccount();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissAccount) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount();
					
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount ntcCalendarReturn = missAccountService.findMissAccountById(bpsTerm.getMaId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>(1);
								th.co.aoe.makedev.missconsult.xstream.MissAccount xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissAccount();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							bpsTerm.setMaRegisterDate(timeStampStartDate);
							Long maId=missAccountService.saveMissAccount(bpsTerm);
							int updateRecord=maId.intValue();
							xbpsTerm.setMaId(maId);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_UPDATE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missAccountService.updateMissAccount(bpsTerm,xbpsTerm.getSection());
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_DELETE)){
							int updateRecord=missAccountService.deleteMissAccount(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_ITEMS_DELETE)){
							/*int updateRecord=missAccountService.deleteMissAccount(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);*/
							
							String[] maIds=xbpsTerm.getMaIds().split(",");
							//logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
							int updateRecord=0;
							for (int i = 0; i <maIds.length; i++) {
								th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount item = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount();
								item.setMaId(Long.parseLong(maIds[i]));
								updateRecord=missAccountService.deleteMissAccount(item);
							}
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missAccountService.searchMissAccount(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissAccountObject(ntcCalendars);
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
		logger.debug("into GET MissAccountResource");
	
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount();
	//	bpsTerm.setMegName("Aoe");
		List result = (List) missAccountService.searchMissAccount(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissAccountObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissAccount> getxMissAccountObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount missAccount : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissAccount xmissAccount =new th.co.aoe.makedev.missconsult.xstream.MissAccount ();
			BeanUtils.copyProperties(missAccount, xmissAccount);
			xmissAccount.setPagging(null);
			xntcCalendars.add(xmissAccount);
		}
		return xntcCalendars;
	} 
	private void returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissAccount xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		export(entity, vresultMessage, xstream);
	}
 
	public MissAccountService getMissAccountService() {
		return missAccountService;
	}

	public void setMissAccountService(MissAccountService missAccountService) {
		this.missAccountService = missAccountService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	
}