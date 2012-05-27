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
import th.co.aoe.makedev.missconsult.managers.MissCandidateService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissCandidateResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissCandidateService missCandidateService;
	private com.thoughtworks.xstream.XStream xstream;
	private static 	final String[] ignore_id=new String[]{"missAccount","missSery"};
	 
	public MissCandidateResource() {
		super();
		logger.debug("into constructor MissCandidateResource");
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
		logger.debug("into Post MissCandidateResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissCandidate.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissCandidate xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissCandidate();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissCandidate) ntcCalendarObj;
				if (xbpsTerm != null) {
				
					 
					th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					if(xbpsTerm.getMissSery()!=null && xbpsTerm.getMissSery().getMsId()!=null && xbpsTerm.getMissSery().getMsId().intValue()!=0){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery=new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
						missSery.setMsId(xbpsTerm.getMissSery().getMsId());
						bpsTerm.setMissSery(missSery);
					}
					/*if(xbpsTerm.getMissAccount()!=null && xbpsTerm.getMissAccount().getMaName()!=null && xbpsTerm.getMissAccount().getMaName().trim().length()>0){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount missAccount=new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount();
						missAccount.setMaName(xbpsTerm.getMissAccount().getMaName());
						bpsTerm.setMissAccount(missAccount);
					}*/
					if(xbpsTerm.getMissAccount()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount missAccount=new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccount();
						//missAccount.setMaName(xbpsTerm.getMissAccount().getMaName());
						BeanUtils.copyProperties(xbpsTerm.getMissAccount(),missAccount);
						bpsTerm.setMissAccount(missAccount);
					}
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_CANDIDATE_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate ntcCalendarReturn = missCandidateService.findMissCandidateById(bpsTerm.getMcaId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCandidate>(1);
								th.co.aoe.makedev.missconsult.xstream.MissCandidate xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissCandidate();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn,ignore_id);	
								xntcCalendarReturn.setPagging(null);
								if(ntcCalendarReturn.getMissSery()!=null && ntcCalendarReturn.getMissSery().getMsId()!=null && ntcCalendarReturn.getMissSery().getMsId().intValue()!=0){
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
								
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
								 
								
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_CANDIDATE_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							Long mcaId=0l;
							if(xbpsTerm.getAmount()!=null && xbpsTerm.getAmount().length()>0){
								int size= Integer.parseInt(xbpsTerm.getAmount());
								for(int i=0;i<size;i++){
									mcaId=missCandidateService.saveMissCandidate(bpsTerm);
								}
							}else{
								mcaId=(missCandidateService.saveMissCandidate(bpsTerm));
							}
							xbpsTerm.setMcaId(mcaId);
						
							return returnUpdateRecord(entity,xbpsTerm,mcaId.intValue());
						}
						else if(serviceName.equals(ServiceConstant.MISS_CANDIDATE_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missCandidateService.updateMissCandidate(bpsTerm,xbpsTerm.getSection());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_CANDIDATE_ITEMS_DELETE)){
						/*	int updateRecord=missCandidateService.deleteMissCandidate(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
							*/
							String[] mcaIds=xbpsTerm.getMcaIds().split(",");
							//logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
							int updateRecord=0;
							for (int i = 0; i <mcaIds.length; i++) {
								th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate item = new th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate();
								item.setMcaId(Long.parseLong(mcaIds[i]));
								updateRecord=missCandidateService.deleteMissCandidate(item);
							}
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_CANDIDATE_DELETE)){
							int updateRecord=missCandidateService.deleteMissCandidate(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_CANDIDATE_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missCandidateService.searchMissCandidate(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCandidate>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissCandidateObject(ntcCalendars);
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

	/*@Override
	protected Representation post(Representation entity)
			throws ResourceException {
		// TODO Auto-generated method stub
		logger.debug("into Post MissCandidateResource");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissCandidate.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissCandidate xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissCandidate();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissCandidate) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate();
					BeanUtils.copyProperties(bpsTerm, xbpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_CANDIDATE_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate ntcCalendarReturn = missCandidateService.findMissCandidateById(bpsTerm.getMcaId());
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCandidate>(1);
								th.co.aoe.makedev.missconsult.xstream.MissCandidate xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissCandidate();
								BeanUtils.copyProperties(xntcCalendarReturn, ntcCalendarReturn);								
								
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								export(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_CANDIDATE_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missCandidateService.saveMissCandidate(bpsTerm)).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_CANDIDATE_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missCandidateService.updateMissCandidate(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_CANDIDATE_DELETE)){
							int updateRecord=missCandidateService.deleteMissCandidate(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_CANDIDATE_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missCandidateService.searchMissCandidate(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCandidate>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissCandidateObject(ntcCalendars);
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
	}*/
	@Override
	protected Representation get(Variant variant) throws ResourceException {
		// TODO Auto-generated method stub
		logger.debug("test2"+variant.getMediaType()+","+MediaType.TEXT_PLAIN);
		logger.debug("into GET MissCandidateResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate ntcCalendarReturn = missCandidateService.findMissCandidateById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCandidate>(1);
			th.co.aoe.makedev.missconsult.xstream.MissCandidate xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissCandidate();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missCandidateService.updateMissCandidate(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missCandidateService.saveMissCandidate(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missCandidateService.deleteMissCandidate(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate();
		//bpsTerm.setMegName("Aoe");
		List result = (List) missCandidateService.searchMissCandidate(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCandidate>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissCandidateObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> getxMissCandidateObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCandidate>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate missCandidate : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissCandidate xmissCandidate =new th.co.aoe.makedev.missconsult.xstream.MissCandidate ();
			BeanUtils.copyProperties(missCandidate, xmissCandidate,ignore_id);
			xmissCandidate.setPagging(null);
			if(missCandidate.getMissSery()!=null && missCandidate.getMissSery().getMsId()!=null && missCandidate.getMissSery().getMsId().intValue()!=0){
				th.co.aoe.makedev.missconsult.xstream.MissSery missSery=new th.co.aoe.makedev.missconsult.xstream.MissSery();
				BeanUtils.copyProperties(missCandidate.getMissSery(), missSery);
				missSery.setPagging(null);
				xmissCandidate.setMissSery(missSery);
			}
			if(missCandidate.getMissAccount()!=null && missCandidate.getMissAccount().getMaId()!=null && missCandidate.getMissAccount().getMaId().intValue()!=0){
				th.co.aoe.makedev.missconsult.xstream.MissAccount missAccount=new th.co.aoe.makedev.missconsult.xstream.MissAccount();
				BeanUtils.copyProperties(missCandidate.getMissAccount(), missAccount);
				 missAccount.setPagging(null);
				xmissCandidate.setMissAccount(missAccount);
			}
			
			xntcCalendars.add(xmissCandidate);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissCandidate xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissCandidate> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissCandidate>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissCandidateService getMissCandidateService() {
		return missCandidateService;
	}

	public void setMissCandidateService(MissCandidateService missCandidateService) {
		this.missCandidateService = missCandidateService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 
}