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
import th.co.aoe.makedev.missconsult.managers.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissExamResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissExamService missExamService;
	private com.thoughtworks.xstream.XStream xstream;
	private static final String[] ignore_id=new String[]{"missExamGroup","missExamType"};
	 
	 
	public MissExamResource() {
		super();
		logger.debug("into constructor MissExamResource");
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
		logger.debug("into Post MissExamResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissExam.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissExam xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissExam();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissExam) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissExam bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExam();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					if(xbpsTerm.getMissExamGroup()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup missExamGroup = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExamGroup();
						BeanUtils.copyProperties(xbpsTerm.getMissExamGroup(),missExamGroup); 
						bpsTerm.setMissExamGroup(missExamGroup);
					}
					if(xbpsTerm.getMissExamType()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType missExamType = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType();
						BeanUtils.copyProperties(xbpsTerm.getMissExamType(),missExamType); 
						bpsTerm.setMissExamType(missExamType);
					}
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_EXAM_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissExam ntcCalendarReturn = missExamService.findMissExamById(bpsTerm.getMeId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissExam> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExam>(1);
								th.co.aoe.makedev.missconsult.xstream.MissExam xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissExam();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn,ignore_id); 
								xntcCalendarReturn.setPagging(null);
								if(ntcCalendarReturn.getMissExamGroup()!=null){
									th.co.aoe.makedev.missconsult.xstream.MissExamGroup ntcCalendarReturnGroup = new th.co.aoe.makedev.missconsult.xstream.MissExamGroup();
									BeanUtils.copyProperties(ntcCalendarReturn.getMissExamGroup(),ntcCalendarReturnGroup); 
									xntcCalendarReturn.setMissExamGroup(ntcCalendarReturnGroup);
								}
								if(ntcCalendarReturn.getMissExamType()!=null){
									th.co.aoe.makedev.missconsult.xstream.MissExamType ntcCalendarReturnType = new th.co.aoe.makedev.missconsult.xstream.MissExamType();
									BeanUtils.copyProperties(ntcCalendarReturn.getMissExamType(),ntcCalendarReturnType); 
									xntcCalendarReturn.setMissExamType(ntcCalendarReturnType);
								}
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_EXAM_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType missExamType=new th.co.aoe.makedev.missconsult.hibernate.bean.MissExamType();
							missExamType.setMetId(1l);
							if(xbpsTerm.getMissExamGroup().getMegId().intValue()==6)
								missExamType.setMetId(2l);
							bpsTerm.setMissExamType(missExamType);
							
							Long meId=missExamService.saveMissExam(bpsTerm);
							int updateRecord=meId.intValue();
							xbpsTerm.setMeId(meId);
							
						//	int updateRecord=(missExamService.saveMissExam(bpsTerm)).intValue();
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missExamService.updateMissExam(bpsTerm,xbpsTerm.getSection());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_COPY)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missExamService.copyMissExam(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_CREATE_EMPTY)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missExamService.createEmptyMissExam(bpsTerm,Integer.parseInt(xbpsTerm.getQuestionCountEmpty()),Integer.parseInt(xbpsTerm.getChoiceCountEmpty()),xbpsTerm.getMeTimeLimit());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_ITEMS_DELETE)){
								String[] meIds=xbpsTerm.getMeIds().split(",");
								int updateRecord=0;
								for (int i = 0; i <meIds.length; i++) {
									th.co.aoe.makedev.missconsult.hibernate.bean.MissExam item = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExam();
									item.setMeId(Long.parseLong(meIds[i]));
									updateRecord=missExamService.deleteMissExam(item);
								}
								return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_DELETE)){
							int updateRecord=missExamService.deleteMissExam(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_LIST)){
							java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam> ntcCalendars=(ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam>) missExamService.listMissExam();
							List<th.co.aoe.makedev.missconsult.xstream.MissExam> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExam>();
							if (ntcCalendars != null && ntcCalendars.size() > 0) {
								xntcCalendars = getxMissExamObject(ntcCalendars);
							}
							VResultMessage vresultMessage = new VResultMessage();
							vresultMessage.setResultListObj(xntcCalendars);
							return getRepresentation(entity, vresultMessage, xstream);
						}
						
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missExamService.searchMissExam(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissExam> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExam>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissExamObject(ntcCalendars);
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

/*	@Override
	protected Representation post(Representation entity)
			throws ResourceException {
		// TODO Auto-generated method stub
		logger.debug("into Post MissExamResource");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissExam.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissExam xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissExam();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissExam) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissExam bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExam();
					BeanUtils.copyProperties(bpsTerm, xbpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_EXAM_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissExam ntcCalendarReturn = missExamService.findMissExamById(bpsTerm.getMeId());
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissExam> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExam>(1);
								th.co.aoe.makedev.missconsult.xstream.MissExam xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissExam();
								BeanUtils.copyProperties(xntcCalendarReturn, ntcCalendarReturn);								
								
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								export(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_EXAM_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=(missExamService.saveMissExam(bpsTerm)).intValue();
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missExamService.updateMissExam(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_DELETE)){
							int updateRecord=missExamService.deleteMissExam(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_EXAM_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missExamService.searchMissExam(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissExam> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExam>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissExamObject(ntcCalendars);
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
		logger.debug("into GET MissExamResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissExam ntcCalendarReturn = missExamService.findMissExamById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissExam> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExam>(1);
			th.co.aoe.makedev.missconsult.xstream.MissExam xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissExam();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missExamService.updateMissExam(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissExam  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissExam ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missExamService.saveMissExam(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissExam  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissExam ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missExamService.deleteMissExam(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissExam bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExam();
		//bpsTerm.setMegName("Aoe");
		List result = (List) missExamService.searchMissExam(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissExam> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExam>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissExamObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissExam> getxMissExamObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissExam> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissExam> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExam>(
				ntcCalendars.size());
		String[] ids=new String[]{"missExamGroup","missExamType"};
		   
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissExam missExam : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissExam xmissExam =new th.co.aoe.makedev.missconsult.xstream.MissExam ();
			BeanUtils.copyProperties(missExam, xmissExam,ids);
			xmissExam.setPagging(null);
			if(missExam.getMissExamGroup()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissExamGroup missExamGroup = new th.co.aoe.makedev.missconsult.xstream.MissExamGroup();
				BeanUtils.copyProperties(missExam.getMissExamGroup(),missExamGroup); 
				xmissExam.setMissExamGroup(missExamGroup);
			}
			if(missExam.getMissExamType()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissExamType missExamType = new th.co.aoe.makedev.missconsult.xstream.MissExamType();
				BeanUtils.copyProperties(missExam.getMissExamType(),missExamType); 
				xmissExam.setMissExamType(missExamType);
			}
			xntcCalendars.add(xmissExam);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissExam xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissExam> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissExam>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//System.out.println("vresultMessage==>"+vresultMessage);
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissExamService getMissExamService() {
		return missExamService;
	}

	public void setMissExamService(MissExamService missExamService) {
		this.missExamService = missExamService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 
	
}