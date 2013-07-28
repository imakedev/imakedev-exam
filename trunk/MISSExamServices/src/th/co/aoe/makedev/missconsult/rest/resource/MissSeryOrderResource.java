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
import th.co.aoe.makedev.missconsult.managers.MissSeryOrderService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissSeryOrderResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissSeryOrderService missSeryOrderService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	 
	public MissSeryOrderResource() {
		super();
		logger.debug("into constructor MissSeryOrderResource");
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
		logger.debug("into Post MissSeryOrderResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissSeryOrder.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissSeryOrder xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissSeryOrder();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissSeryOrder) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder();
					
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_SERY_ORDER_SAVE)){
						//	java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
						//	bpsTerm.setMaRegisterDate(timeStampStartDate);
							Long maId=missSeryOrderService.saveMissSeryOrder(bpsTerm);
							int updateRecord=maId.intValue();
							//xbpsTerm.setMaId(maId);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_SERY_ORDER_SEARCH
								)){
							Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missSeryOrderService.searchMissSeryOrder(bpsTerm,page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissSeryOrder> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryOrder>();
								if (faqs_size != null && faqs_size.length()!=0)
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissSeryOrderObject(ntcCalendars);
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
		logger.debug("into GET MissSeryOrderResource");
	
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder();
		@SuppressWarnings("rawtypes")
		List result = (List) missSeryOrderService.searchMissSeryOrder(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSeryOrder> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryOrder>();
		if (result != null && result.size() == 2) {
			@SuppressWarnings("unchecked")
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder>) result
					.get(0);
			String faqs_size = (String) result.get(1);
			if (faqs_size != null && faqs_size.length()!=0)
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissSeryOrderObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissSeryOrder> getxMissSeryOrderObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissSeryOrder> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryOrder>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissSeryOrder missSeryOrder : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissSeryOrder xmissSeryOrder =new th.co.aoe.makedev.missconsult.xstream.MissSeryOrder ();
			BeanUtils.copyProperties(missSeryOrder, xmissSeryOrder);
			xmissSeryOrder.setPagging(null);
			xntcCalendars.add(xmissSeryOrder);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissSeryOrder xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissSeryOrder> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissSeryOrder>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissSeryOrderService getMissSeryOrderService() {
		return missSeryOrderService;
	}

	public void setMissSeryOrderService(MissSeryOrderService missSeryOrderService) {
		this.missSeryOrderService = missSeryOrderService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}


}
