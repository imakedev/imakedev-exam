package th.co.aoe.makedev.missconsult.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.managers.MissSeryProblemService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissSeryProblemResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissSeryProblemService missSeryProblemService;
	private com.thoughtworks.xstream.XStream xstream;
	private static 	final String[] ignore_id=new String[]{"id"};
	 
	 
	public MissSeryProblemResource() {
		super();
		logger.debug("into constructor MissSeryProblemResource");
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
		logger.debug("into Post MissSeryProblemResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissSeryProblem.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissSeryProblem xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissSeryProblem();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissSeryProblem) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem();
					
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblemPK pk =new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblemPK();
					pk.setMcaId(xbpsTerm.getMcaId());
					pk.setMsId(xbpsTerm.getMsId());
					
					java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
					DateTime datetime=new DateTime(timeStampStartDate.getTime()); 
					pk.setMspDateTime(timeStampStartDate);
					bpsTerm.setMspWeek(Long.valueOf(datetime.weekOfWeekyear().get()));
					bpsTerm.setId(pk);
					
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_SERY_PROBLEM_SAVE)){
							// java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
						//	bpsTerm.setMaRegisterDate(timeStampStartDate); 
							Long maId=missSeryProblemService.saveMissSeryProblem(bpsTerm);
							int updateRecord=maId.intValue();
							//xbpsTerm.setMaId(maId);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERY_PROBLEM_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missSeryProblemService.searchMissSeryProblem(bpsTerm,page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissSeryProblem> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryProblem>();
								if (faqs_size != null && faqs_size.length()!=0)
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissSeryProblemObject(ntcCalendars);
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
		logger.debug("into GET MissSeryProblemResource");
	
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem();
		@SuppressWarnings("rawtypes")
		List result = (List) missSeryProblemService.searchMissSeryProblem(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSeryProblem> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryProblem>();
		if (result != null && result.size() == 2) {
			@SuppressWarnings("unchecked")
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem>) result
					.get(0);
			String faqs_size = (String) result.get(1);
			if (faqs_size != null && faqs_size.length()!=0)
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissSeryProblemObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissSeryProblem> getxMissSeryProblemObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissSeryProblem> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryProblem>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryProblem missSeryProblem : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissSeryProblem xmissSeryProblem =new th.co.aoe.makedev.missconsult.xstream.MissSeryProblem ();
			BeanUtils.copyProperties(missSeryProblem, xmissSeryProblem);
			xmissSeryProblem.setPagging(null);
			xntcCalendars.add(xmissSeryProblem);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissSeryProblem xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSeryProblem> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryProblem>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissSeryProblemService getMissSeryProblemService() {
		return missSeryProblemService;
	}

	public void setMissSeryProblemService(MissSeryProblemService missSeryProblemService) {
		this.missSeryProblemService = missSeryProblemService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	

}
