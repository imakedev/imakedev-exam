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
import th.co.aoe.makedev.missconsult.managers.MissReportAttachService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissReportAttachResource  extends BaseResource {

	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissReportAttachService missReportAttachService;
	private com.thoughtworks.xstream.XStream xstream;
	private static final String[] ignore_id={"missSery"};
	 
	public MissReportAttachResource() {
		super();
		logger.debug("into constructor MissReportAttachResource");
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
		logger.debug("into Post MissReportAttachResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissReportAttach.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissReportAttach xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissReportAttach();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissReportAttach) ntcCalendarObj;
				if (xbpsTerm != null) {
				
					 
					th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					if(xbpsTerm.getMissSery()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissSery sery=new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
						BeanUtils.copyProperties(xbpsTerm.getMissSery(),sery);
						bpsTerm.setMissSery(sery);
					}
					th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttachPK pk = new th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttachPK();
					pk.setMraLang(xbpsTerm.getMraLang());
					pk.setMsId(xbpsTerm.getMsId());
					pk.setMsOrder(xbpsTerm.getMsOrder());
					bpsTerm.setId(pk);
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_REPORT_ATTACH_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach ntcCalendarReturn = missReportAttachService.findMissReportAttachById(xbpsTerm.getMsId(),xbpsTerm.getMsOrder(),xbpsTerm.getMraLang(),xbpsTerm.getMraHotlink());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissReportAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReportAttach>(1);
								th.co.aoe.makedev.missconsult.xstream.MissReportAttach xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissReportAttach();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn,ignore_id); 	
								th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttachPK pk_id  =ntcCalendarReturn.getId();
								xntcCalendarReturn.setMraLang(pk_id.getMraLang());
								xntcCalendarReturn.setMsId(pk_id.getMsId());
								xntcCalendarReturn.setMsOrder(pk_id.getMsOrder());
								if(ntcCalendarReturn.getMissSery()!=null){
									th.co.aoe.makedev.missconsult.xstream.MissSery sery=new th.co.aoe.makedev.missconsult.xstream.MissSery();
									BeanUtils.copyProperties(ntcCalendarReturn.getMissSery(),sery);
									xntcCalendarReturn.setMissSery(sery);
								}
								xntcCalendarReturn.setPagging(null);
								
								
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
								 
								
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_REPORT_ATTACH_SAVE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							Long matId=0l;
						
								matId=(missReportAttachService.saveMissReportAttach(bpsTerm));
							
							xbpsTerm.setMsId(matId);
						
							return returnUpdateRecord(entity,xbpsTerm,matId.intValue());
						}
						else if(serviceName.equals(ServiceConstant.MISS_REPORT_ATTACH_UPDATE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missReportAttachService.updateMissReportAttach(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						/*else if(serviceName.equals(ServiceConstant.MISS_REPORT_ATTACH_ITEMS_DELETE)){
							String[] matIds=xbpsTerm.getMatIds().split(",");
							//logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
							int updateRecord=0;
							for (int i = 0; i <matIds.length; i++) {
								th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach item = new th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach();
								item.setMatId(Long.parseLong(matIds[i]));
								updateRecord=missReportAttachService.deleteMissReportAttach(item);
							}
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}*/
						else if(serviceName.equals(ServiceConstant.MISS_REPORT_ATTACH_DELETE)){
							int updateRecord=missReportAttachService.deleteMissReportAttach(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_REPORT_ATTACH_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missReportAttachService.searchMissReportAttach(bpsTerm,page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissReportAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReportAttach>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissReportAttachObject(ntcCalendars);
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
		logger.debug("into GET MissReportAttachResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach ntcCalendarReturn = missReportAttachService.findMissReportAttachById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissReportAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReportAttach>(1);
			th.co.aoe.makedev.missconsult.xstream.MissReportAttach xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissReportAttach();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missReportAttachService.updateMissReportAttach(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missReportAttachService.saveMissReportAttach(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missReportAttachService.deleteMissReportAttach(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach();
		//bpsTerm.setMegName("Aoe");
		@SuppressWarnings("rawtypes")
		List result = (List) missReportAttachService.searchMissReportAttach(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissReportAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReportAttach>();
		if (result != null && result.size() == 2) {
			@SuppressWarnings("unchecked")
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissReportAttachObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissReportAttach> getxMissReportAttachObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissReportAttach> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReportAttach>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttach missReportAttach : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissReportAttach xmissReportAttach =new th.co.aoe.makedev.missconsult.xstream.MissReportAttach ();
			BeanUtils.copyProperties(missReportAttach, xmissReportAttach,ignore_id);
			if(missReportAttach.getMissSery()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissSery sery=new th.co.aoe.makedev.missconsult.xstream.MissSery();
				BeanUtils.copyProperties(missReportAttach.getMissSery(),sery);
				xmissReportAttach.setMissSery(sery);
			} 	
			th.co.aoe.makedev.missconsult.hibernate.bean.MissReportAttachPK pk_id  =missReportAttach.getId();
			xmissReportAttach.setMraLang(pk_id.getMraLang());
			xmissReportAttach.setMsId(pk_id.getMsId());
			xmissReportAttach.setMsOrder(pk_id.getMsOrder());
			 
			xmissReportAttach.setPagging(null);
			 
			xntcCalendars.add(xmissReportAttach);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissReportAttach xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissReportAttach> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissReportAttach>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissReportAttachService getMissReportAttachService() {
		return missReportAttachService;
	}

	public void setMissReportAttachService(MissReportAttachService missReportAttachService) {
		this.missReportAttachService = missReportAttachService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
