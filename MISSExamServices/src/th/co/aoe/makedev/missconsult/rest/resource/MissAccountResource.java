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
	private static final String[] id_ignore=new String[]{"missTheme","missIndustryMaster"};
	 
	 
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
					
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,id_ignore); 
					if(xbpsTerm.getMissTheme()!=null && xbpsTerm.getMissTheme().getMtId()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme missTheme = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme();						
						BeanUtils.copyProperties(xbpsTerm.getMissTheme(),missTheme); 
						bpsTerm.setMissTheme(missTheme);
					}
					if(xbpsTerm.getMissIndustryMaster()!=null && xbpsTerm.getMissIndustryMaster().getMimId()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissIndustryMaster missIndustryMaster = new th.co.aoe.makedev.missconsult.hibernate.bean.MissIndustryMaster();						
						BeanUtils.copyProperties(xbpsTerm.getMissIndustryMaster(),missIndustryMaster); 
						bpsTerm.setMissIndustryMaster(missIndustryMaster);
					}
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.xstream.MissAccount xntcCalendarReturn  = missAccountService.findMissAccountById(bpsTerm.getMaId());
						logger.debug(" object return ="+xntcCalendarReturn);
							if(xntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>(1);
								 
								xntcCalendarReturn.setPagging(null);
								//List<th.co.aoe.makedev.missconsult.xstream.MissAccountSeriesMap> missAccountSeriesMapList =missAccountService.listMissAccountSeriesMapByMaId(bpsTerm.getMaId());
								List<th.co.aoe.makedev.missconsult.xstream.MissSery> missSeryList =missAccountService.listMissAccountSeriesMapByMaId(bpsTerm.getMaId());
								//missAccountSeriesMapList.add(e)
								//xntcCalendarReturn.setMissAccountSeriesMapList(missAccountSeriesMapList);
								xntcCalendarReturn.setMissSeryList(missSeryList);
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
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_UPDATE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missAccountService.updateMissAccount(bpsTerm,xbpsTerm.getSection());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_REFILL)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							th.co.aoe.makedev.missconsult.xstream.MissAccount xntcCalendarReturn=missAccountService.refill(xbpsTerm.getMaId(),xbpsTerm.getRefill());
							VResultMessage vresultMessage = new VResultMessage();
							List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>(1);
							
							xntcCalendarReturn.setPagging(null);
							xntcCalendars.add(xntcCalendarReturn);
							vresultMessage.setResultListObj(xntcCalendars);
							return getRepresentation(entity, vresultMessage, xstream);
						}
						
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_UPDATE_LOGO)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missAccountService.updateMissAccountLogo(bpsTerm,xbpsTerm.getSection());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_DELETE)){
							int updateRecord=missAccountService.deleteMissAccount(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
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
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missAccountService.searchMissAccount(bpsTerm,xbpsTerm.getMaContactName(),xbpsTerm.getMeIds(), page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>();
								if (faqs_size != null && faqs_size.length()!=0)
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
		@SuppressWarnings("rawtypes")
		List result = (List) missAccountService.searchMissAccount(bpsTerm,null,null,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>();
		if (result != null && result.size() == 2) {
			@SuppressWarnings("unchecked")
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && faqs_size.length()!=0)
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
			BeanUtils.copyProperties(missAccount, xmissAccount,id_ignore);
			if(missAccount.getMissTheme()!=null && missAccount.getMissTheme().getMtId()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissTheme missTheme = new th.co.aoe.makedev.missconsult.xstream.MissTheme();						
				BeanUtils.copyProperties(missAccount.getMissTheme(),missTheme); 
				xmissAccount.setMissTheme(missTheme);
			}
			if(missAccount.getMissIndustryMaster()!=null && missAccount.getMissIndustryMaster().getMimId()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster missIndustryMaster = new th.co.aoe.makedev.missconsult.xstream.MissIndustryMaster();						
				BeanUtils.copyProperties(missAccount.getMissIndustryMaster(),missIndustryMaster); 
				xmissAccount.setMissIndustryMaster(missIndustryMaster);
			}
			
			xmissAccount.setPagging(null);
			xntcCalendars.add(xmissAccount);
		}
		return xntcCalendars;
	} 
/*	private void returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissAccount xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		export(entity, vresultMessage, xstream);
	}*/
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissAccount xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissAccount> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccount>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
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