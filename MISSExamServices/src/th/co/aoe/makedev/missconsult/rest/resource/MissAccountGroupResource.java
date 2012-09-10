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
import th.co.aoe.makedev.missconsult.managers.MissAccountGroupService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissAccountGroupResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissAccountGroupService missAccountGroupService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	 
	public MissAccountGroupResource() {
		super();
		logger.debug("into constructor MissAccountGroupResource");
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
		logger.debug("into Post MissAccountGroupResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissAccountGroup.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissAccountGroup xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissAccountGroup();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissAccountGroup) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountGroup bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountGroup();
					
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_GROUP_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
						//	bpsTerm.setMaRegisterDate(timeStampStartDate);
							Long maId=missAccountGroupService.saveMissAccountGroup(bpsTerm);
							int updateRecord=maId.intValue();
							//xbpsTerm.setMaId(maId);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_ACCOUNT_GROUP_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missAccountGroupService.searchMissAccountGroup(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountGroup> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountGroup>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissAccountGroup> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountGroup>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissAccountGroupObject(ntcCalendars);
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
		logger.debug("into GET MissAccountGroupResource");
	
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountGroup bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountGroup();
		List result = (List) missAccountGroupService.searchMissAccountGroup(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissAccountGroup> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountGroup>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountGroup> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountGroup>) result
					.get(0);
			String faqs_size = (String) result.get(1);
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissAccountGroupObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissAccountGroup> getxMissAccountGroupObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountGroup> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissAccountGroup> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountGroup>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissAccountGroup missAccountGroup : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissAccountGroup xmissAccountGroup =new th.co.aoe.makedev.missconsult.xstream.MissAccountGroup ();
			BeanUtils.copyProperties(missAccountGroup, xmissAccountGroup);
			xmissAccountGroup.setPagging(null);
			xntcCalendars.add(xmissAccountGroup);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissAccountGroup xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissAccountGroup> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissAccountGroup>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissAccountGroupService getMissAccountGroupService() {
		return missAccountGroupService;
	}

	public void setMissAccountGroupService(MissAccountGroupService missAccountGroupService) {
		this.missAccountGroupService = missAccountGroupService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
