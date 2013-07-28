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
import th.co.aoe.makedev.missconsult.managers.MissChoiceService;
import th.co.aoe.makedev.missconsult.managers.MissQuestionService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissQuestionResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissQuestionService missQuestionService;
	private MissChoiceService missChoiceService;
	private com.thoughtworks.xstream.XStream xstream;
	private static final String[] ignore_id=new String[]{"missTemplate","missExam","missChoices"};
	private static final String[] ignore_exam_id=new String[]{"missExamGroup","missExamType"}; 
	private static final String[] ignore_question_id=new String[]{"missQuestion"}; 
	 
	public MissQuestionResource() {
		super();
		logger.debug("into constructor MissQuestionResource");
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
		logger.debug("into Post MissQuestionResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissQuestion.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissQuestion xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissQuestion();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissQuestion) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					
					if(xbpsTerm.getMissExam()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissExam missExam = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExam();
						BeanUtils.copyProperties(xbpsTerm.getMissExam(),missExam,ignore_exam_id); 
						bpsTerm.setMissExam(missExam);
					}
					if(xbpsTerm.getMissTemplate()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate missTemplate = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTemplate();
						BeanUtils.copyProperties(xbpsTerm.getMissTemplate(),missTemplate); 
						bpsTerm.setMissTemplate(missTemplate);
					}
					
					if (xbpsTerm.getServiceName() != null
							&& xbpsTerm.getServiceName().length()!=0) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						if(serviceName.equals(ServiceConstant.MISS_QUESTION_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.xstream.MissQuestion xntcCalendarReturn = missQuestionService.findMissQuestionById(bpsTerm.getMqId());
						logger.debug(" object return ="+xntcCalendarReturn);
							if(xntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissQuestion> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissQuestion>(1);
								/*th.co.aoe.makedev.missconsult.xstream.MissQuestion xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissQuestion();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn,ignore_id);	
								xntcCalendarReturn.setPagging(null);
								
								if(ntcCalendarReturn.getMissExam()!=null){
									th.co.aoe.makedev.missconsult.xstream.MissExam missExam = new th.co.aoe.makedev.missconsult.xstream.MissExam();
									BeanUtils.copyProperties(ntcCalendarReturn.getMissExam(),missExam,ignore_exam_id); 
									xntcCalendarReturn.setMissExam(missExam);
								}
								if(ntcCalendarReturn.getMissTemplate()!=null){
									th.co.aoe.makedev.missconsult.xstream.MissTemplate missTemplate = new th.co.aoe.makedev.missconsult.xstream.MissTemplate();
									BeanUtils.copyProperties(ntcCalendarReturn.getMissTemplate(),missTemplate); 
									xntcCalendarReturn.setMissTemplate(missTemplate);
								}*/
								//missQuestionService.searchMissQuestion(persistentInstance, pagging)
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} 
						if(serviceName.equals(ServiceConstant.MISS_QUESTION_SAVE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							Long mqId=missQuestionService.saveMissQuestion(bpsTerm);
							int updateRecord=mqId.intValue(); 
							xbpsTerm.setMqId(mqId);
							
						//	int updateRecord=(missExamService.saveMissExam(bpsTerm)).intValue();
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_QUESTION_UPDATE)){
						//	java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							logger.debug("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx=>"+bpsTerm.getMqId());
							int updateRecord=missQuestionService.updateMissQuestion(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
							//returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_QUESTION_CHOICES_UPDATE)){
							//java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							
							List<th.co.aoe.makedev.missconsult.xstream.MissChoice>	 xmissChoices= xbpsTerm.getMissChoicesAdd();
							if(xmissChoices!=null && xmissChoices.size()>0){
								
								missChoiceService.deleteMissChoice(xbpsTerm.getMqId(),xbpsTerm.getLang());
								for (th.co.aoe.makedev.missconsult.xstream.MissChoice xmissChoice : xmissChoices) {
									th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice  missChoice= new th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice();
									BeanUtils.copyProperties(xmissChoice, missChoice,ignore_question_id);
									missChoice.setMissQuestion(bpsTerm);
									th.co.aoe.makedev.missconsult.hibernate.bean.MissChoicePK pk = 
											new th.co.aoe.makedev.missconsult.hibernate.bean.MissChoicePK();
									pk.setMqId(bpsTerm.getMqId());
									pk.setMcNo(xmissChoice.getMcNo());
									pk.setMcLang(xbpsTerm.getLang());
									missChoice.setId(pk);
									missChoiceService.saveMissChoice(missChoice);
								}
							}
							/* xmissChoices= xbpsTerm.getMissChoicesUpdate();
							if(xmissChoices!=null && xmissChoices.size()>0){
								for (th.co.aoe.makedev.missconsult.xstream.MissChoice xmissChoice : xmissChoices) {
									th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice  missChoice= new th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice();
									BeanUtils.copyProperties(xmissChoice, missChoice,ignore_question_id);
									missChoice.setMissQuestion(bpsTerm);
									th.co.aoe.makedev.missconsult.hibernate.bean.MissChoicePK pk = 
											new th.co.aoe.makedev.missconsult.hibernate.bean.MissChoicePK();
									pk.setMqId(bpsTerm.getMqId());
									pk.setMcNo(xmissChoice.getMcNo());
									missChoice.setId(pk);
									missChoiceService.updateMissChoice(missChoice);
								}
							}
							// mqId_mcNo
							String[] mcIds=xbpsTerm.getMcIds();
							if(mcIds!=null)
							for (int i = 0; i <mcIds.length; i++) {
								String[] ids=mcIds[i].split("_");
								th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice item = new th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice();
								//item.setMcId(Long.parseLong(mcIds[i]));
								th.co.aoe.makedev.missconsult.hibernate.bean.MissChoicePK pk =new th.co.aoe.makedev.missconsult.hibernate.bean.MissChoicePK(); 
								pk.setMqId(Long.parseLong(ids[0]));
								pk.setMcNo(Long.parseLong(ids[1]));
								item.setId(pk);
								item.setMissQuestion(bpsTerm);
								missChoiceService.deleteMissChoice(item);
							}*/
							int updateRecord=1;
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
							//returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_QUESTION_DELETE)){
							int updateRecord=missQuestionService.deleteMissQuestion(bpsTerm);
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						
						else if(serviceName.equals(ServiceConstant.MISS_QUESTION_LIST)){
							@SuppressWarnings("unchecked")
							java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion> ntcCalendars=(ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion>) missQuestionService.listMissQuestions(xbpsTerm.getMissExam().getMeId());
							List<th.co.aoe.makedev.missconsult.xstream.MissQuestion> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissQuestion>();
							if (ntcCalendars != null && ntcCalendars.size() > 0) {
								xntcCalendars = getxMissQuestionObject(ntcCalendars);
							}
							VResultMessage vresultMessage = new VResultMessage();
							vresultMessage.setResultListObj(xntcCalendars);
							return getRepresentation(entity, vresultMessage, xstream);
						}
						else if(serviceName.equals(ServiceConstant.MISS_QUESTION_LIST_WITH_CHOICES)){
							VResultMessage vresultMessage = new VResultMessage();
							vresultMessage.setResultListObj(missQuestionService.listMissQuestionsWithChoices(xbpsTerm.getMissExam().getMeId()));
							return getRepresentation(entity, vresultMessage, xstream);
						}else if(serviceName.equals(ServiceConstant.MISS_QUESTION_GET_ORDERED)){
							
							int updateRecord=missQuestionService.getQuestionOrdered(xbpsTerm.getMissExam().getMeId());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_QUESTION_SET_ORDERED_ITEMS)){
							
							int updateRecord=missQuestionService.setOrderItems(xbpsTerm.getMissExam().getMeId(),xbpsTerm.getMissExam().getMqNos(),xbpsTerm.getMissExam().getMqIds());
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						
						else if(serviceName.equals(ServiceConstant.MISS_QUESTION_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							@SuppressWarnings("rawtypes")
							List result = (List) missQuestionService.searchMissQuestion(bpsTerm,page);
							if (result != null && result.size() == 2) {
								@SuppressWarnings("unchecked")
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissQuestion> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissQuestion>();
								if (faqs_size != null && faqs_size.length()!=0)
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissQuestionObject(ntcCalendars);
								}
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						}
						else if(serviceName.equals(ServiceConstant.MISS_QUESTION_SETUP_TEST)){
							missQuestionService.setUpTestMissQuestion();
							return returnUpdateRecord(entity,xbpsTerm,1);
							
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
		logger.debug("into GET MissQuestionResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion ntcCalendarReturn = missQuestionService.findMissQuestionById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissQuestion> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissQuestion>(1);
			th.co.aoe.makedev.missconsult.xstream.MissQuestion xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissQuestion();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missQuestionService.updateMissQuestion(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missQuestionService.saveMissQuestion(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missQuestionService.deleteMissQuestion(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion();
		//bpsTerm.setMegName("Aoe");
		@SuppressWarnings("rawtypes")
		List result = (List) missQuestionService.searchMissQuestion(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissQuestion> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissQuestion>();
		if (result != null && result.size() == 2) {
			@SuppressWarnings("unchecked")
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && faqs_size.length()!=0)
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissQuestionObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissQuestion> getxMissQuestionObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissQuestion> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissQuestion>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion missQuestion : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissQuestion xmissQuestion =new th.co.aoe.makedev.missconsult.xstream.MissQuestion ();
			BeanUtils.copyProperties(missQuestion, xmissQuestion,ignore_id);
			xmissQuestion.setPagging(null);
			 
			if(missQuestion.getMissExam()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissExam missExam = new th.co.aoe.makedev.missconsult.xstream.MissExam();
				BeanUtils.copyProperties(missQuestion.getMissExam(),missExam,ignore_exam_id); 
				xmissQuestion.setMissExam(missExam);
			}
			if(missQuestion.getMissTemplate()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissTemplate missTemplate = new th.co.aoe.makedev.missconsult.xstream.MissTemplate();
				BeanUtils.copyProperties(missQuestion.getMissTemplate(),missTemplate); 
				xmissQuestion.setMissTemplate(missTemplate);
			}
			
			xntcCalendars.add(xmissQuestion);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissQuestion xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissQuestion> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissQuestion>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);
		return getRepresentation(entity, vresultMessage, xstream);
	}
 


	public MissChoiceService getMissChoiceService() {
		return missChoiceService;
	}

	public void setMissChoiceService(MissChoiceService missChoiceService) {
		this.missChoiceService = missChoiceService;
	}

	public MissQuestionService getMissQuestionService() {
		return missQuestionService;
	}

	public void setMissQuestionService(MissQuestionService missQuestionService) {
		this.missQuestionService = missQuestionService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 
}