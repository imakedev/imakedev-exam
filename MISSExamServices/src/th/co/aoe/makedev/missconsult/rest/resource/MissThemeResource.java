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
import th.co.aoe.makedev.missconsult.managers.MissThemeService;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;

public class MissThemeResource extends BaseResource{
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissThemeService missThemeService;
	private com.thoughtworks.xstream.XStream xstream;
	 
	public MissThemeResource() {
		super();
		logger.debug("into constructor MissThemeResource");
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
		logger.debug("into Post MissThemeResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissTheme.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissTheme xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissTheme();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissTheme) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm); 
					
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_THEME_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme ntcCalendarReturn = missThemeService.findMissThemeById(xbpsTerm.getMaId(),bpsTerm.getMtId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissTheme> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTheme>(1);
								th.co.aoe.makedev.missconsult.xstream.MissTheme xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissTheme();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						else if(serviceName.equals(ServiceConstant.MISS_THEME_LIST)){
							//Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missThemeService.listMissTheme(bpsTerm);
							if (result != null && result.size() >0) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme>) result;
										
								String faqs_size = result.size()+"";
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissTheme> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTheme>();
								if (faqs_size != null && faqs_size.length()!=0)
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissThemeObject(ntcCalendars);
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
	private List<th.co.aoe.makedev.missconsult.xstream.MissTheme> getxMissThemeObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissTheme> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTheme>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissTheme missTheme : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissTheme xmissTheme =new th.co.aoe.makedev.missconsult.xstream.MissTheme ();
			BeanUtils.copyProperties(missTheme, xmissTheme);
			xmissTheme.setPagging(null);
			xntcCalendars.add(xmissTheme);
		}
		return xntcCalendars;
	} 

	public MissThemeService getMissThemeService() {
		return missThemeService;
	}

	public void setMissThemeService(MissThemeService missThemeService) {
		this.missThemeService = missThemeService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 


}
