package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.MissDocService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissDocResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissDocService missDocService;
	private com.thoughtworks.xstream.XStream xstream;
	//private static 	final String[] ignore_id=new String[]{"missAccount","missSery"};
	 
	public MissDocResource() {
		super();
		logger.debug("into constructor MissDocResource");
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
		logger.debug("into Post MissDocResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissDoc.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissDoc xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissDoc();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissDoc) ntcCalendarObj;
				if (xbpsTerm != null) {
				
					 
					th.co.aoe.makedev.missconsult.hibernate.bean.MissDoc bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissDoc();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_DOC_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissDoc ntcCalendarReturn = missDocService.findMissDocById(bpsTerm.getMdId());
						logger.debug(" object return ="+ntcCalendarReturn);
						VResultMessage vresultMessage = new VResultMessage();
							if(ntcCalendarReturn!=null){
								List<th.co.aoe.makedev.missconsult.xstream.MissDoc> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissDoc>(1);
								th.co.aoe.makedev.missconsult.xstream.MissDoc xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissDoc();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
							}
							return getRepresentation(entity, vresultMessage, xstream);
						} 
						if(serviceName.equals(ServiceConstant.MISS_DOC_SAVE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							Long mdId=0l;
							
							mdId=(missDocService.saveMissDoc(bpsTerm));
							xbpsTerm.setMdId(mdId);
						
							return returnUpdateRecord(entity,xbpsTerm,mdId.intValue());
						}
						else if(serviceName.equals(ServiceConstant.MISS_DOC_UPDATE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missDocService.updateMissDoc(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_DOC_DELETE)){
							int updateRecord=missDocService.deleteMissDoc(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_DOC_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missDocService.searchMissDoc(bpsTerm,page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissDoc> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissDoc>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissDoc> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissDoc>();
								if (faqs_size != null && faqs_size.length()!=0)
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissDocObject(ntcCalendars);
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
		return null;
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissDoc> getxMissDocObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissDoc> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissDoc> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissDoc>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissDoc missDoc : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissDoc xmissDoc =new th.co.aoe.makedev.missconsult.xstream.MissDoc ();
			BeanUtils.copyProperties(missDoc, xmissDoc);
			xmissDoc.setPagging(null);
			xntcCalendars.add(xmissDoc);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissDoc xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissDoc> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissDoc>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);	
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissDocService getMissDocService() {
		return missDocService;
	}

	public void setMissDocService(MissDocService missDocService) {
		this.missDocService = missDocService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

}
