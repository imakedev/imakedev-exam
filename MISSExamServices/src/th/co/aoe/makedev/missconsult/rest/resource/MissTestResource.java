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
import th.co.aoe.makedev.missconsult.managers.MissTestService;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;


public class MissTestResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);  
	private MissTestService missTestService;
	private com.thoughtworks.xstream.XStream xstream;
	private static 	final String[] ignore_id=new String[]{"missCandidate","missChoice","missExam","missQuestion","missSery"};
	private static 	final String[] ignore_id_choice=new String[]{"missQuestion"};
	private static 	final String[] ignore_id_exam=new String[]{"missExamGroup","missExamType"};
	private static 	final String[] ignore_id_question=new String[]{"missExam","missTemplate"}; 
	
	
	public MissTestResource() {
		super();
		logger.debug("into constructor MissTestResource");
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
		logger.debug("into Post MissTestResource 2");
		InputStream in = null;
		try {
			in = entity.getStream();
			xstream.processAnnotations(th.co.aoe.makedev.missconsult.xstream.MissTest.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
			th.co.aoe.makedev.missconsult.xstream.MissTest xbpsTerm = new th.co.aoe.makedev.missconsult.xstream.MissTest();
			Object ntcCalendarObj = xstream.fromXML(in);
			if (ntcCalendarObj != null) {
				xbpsTerm = (th.co.aoe.makedev.missconsult.xstream.MissTest) ntcCalendarObj;
				if (xbpsTerm != null) {
					th.co.aoe.makedev.missconsult.hibernate.bean.MissTest bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTest();
					BeanUtils.copyProperties(xbpsTerm,bpsTerm,ignore_id); 
					th.co.aoe.makedev.missconsult.hibernate.bean.MissTestPK pk =new th.co.aoe.makedev.missconsult.hibernate.bean.MissTestPK();
					if(xbpsTerm.getMissChoice()!=null && xbpsTerm.getMissChoice().getMcNo()!=null){
						pk.setMcNo(xbpsTerm.getMissChoice().getMcNo());
					}
					/*if(xbpsTerm.getMissChoice()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice missChoice = new th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice();
						BeanUtils.copyProperties(xbpsTerm.getMissChoice(),missChoice,ignore_id_choice); 
						//pk.setMissChoice(missChoice);
						//bpsTerm.setMissChoice(missChoice);
					}*/
					if(xbpsTerm.getMissExam()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissExam missExam = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExam();
						BeanUtils.copyProperties(xbpsTerm.getMissExam(),missExam,ignore_id_exam); 
						pk.setMissExam(missExam);
					}
					if(xbpsTerm.getMissQuestion()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion missQuestion = new th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion();
						BeanUtils.copyProperties(xbpsTerm.getMissQuestion(),missQuestion,ignore_id_question); 
						pk.setMissQuestion(missQuestion);
					}
					if(xbpsTerm.getMissSery()!=null){
						th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
						BeanUtils.copyProperties(xbpsTerm.getMissSery(),missSery); 
						pk.setMissSery(missSery);
					}
					bpsTerm.setId(pk);
					if (xbpsTerm.getServiceName() != null
							&& !xbpsTerm.getServiceName().equals("")) {
						logger.debug(" BPS servicename = "
								+ xbpsTerm.getServiceName());
						String serviceName = xbpsTerm.getServiceName();
						/*if(serviceName.equals(ServiceConstant.MISS_TEST_FIND_BY_ID)){
							th.co.aoe.makedev.missconsult.hibernate.bean.MissTest ntcCalendarReturn = missTestService.findMissTestById(bpsTerm.getMtestId());
						logger.debug(" object return ="+ntcCalendarReturn);
							if(ntcCalendarReturn!=null){
								VResultMessage vresultMessage = new VResultMessage();
								List<th.co.aoe.makedev.missconsult.xstream.MissTest> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTest>(1);
								th.co.aoe.makedev.missconsult.xstream.MissTest xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissTest();
								BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);	
								xntcCalendarReturn.setPagging(null);
								xntcCalendars.add(xntcCalendarReturn);
								vresultMessage.setResultListObj(xntcCalendars);
								return getRepresentation(entity, vresultMessage, xstream);
							}
						} */
						if(serviceName.equals(ServiceConstant.MISS_TEST_SAVE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=0;
						    List<th.co.aoe.makedev.missconsult.xstream.MissTest> missTests=xbpsTerm.getMissTests();
						    		for (th.co.aoe.makedev.missconsult.xstream.MissTest xmissTest : missTests) {
						    			th.co.aoe.makedev.missconsult.hibernate.bean.MissTest missTest = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTest();
										BeanUtils.copyProperties(xmissTest,missTest,ignore_id); 
										th.co.aoe.makedev.missconsult.hibernate.bean.MissTestPK pk_inner =new th.co.aoe.makedev.missconsult.hibernate.bean.MissTestPK();
										if(xmissTest.getMissChoice()!=null && xmissTest.getMissChoice().getMcNo()!=null){
											pk_inner.setMcNo(xmissTest.getMissChoice().getMcNo());
										/*	th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice missChoice = new th.co.aoe.makedev.missconsult.hibernate.bean.MissChoice();
											BeanUtils.copyProperties(xmissTest.getMissChoice(),missChoice,ignore_id_choice); */
											/*
											pk_inner.setMissChoice(missChoice);*/
										}
										if(xmissTest.getMissExam()!=null){
											th.co.aoe.makedev.missconsult.hibernate.bean.MissExam missExam = new th.co.aoe.makedev.missconsult.hibernate.bean.MissExam();
											BeanUtils.copyProperties(xmissTest.getMissExam(),missExam,ignore_id_exam); 
											pk_inner.setMissExam(missExam);
										}
										if(xmissTest.getMissQuestion()!=null){
											th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion missQuestion = new th.co.aoe.makedev.missconsult.hibernate.bean.MissQuestion();
											BeanUtils.copyProperties(xmissTest.getMissQuestion(),missQuestion,ignore_id_question); 
											pk_inner.setMissQuestion(missQuestion);
										}
										if(xmissTest.getMissSery()!=null){
											th.co.aoe.makedev.missconsult.hibernate.bean.MissSery missSery = new th.co.aoe.makedev.missconsult.hibernate.bean.MissSery();
											BeanUtils.copyProperties(xmissTest.getMissSery(),missSery); 
											pk_inner.setMissSery(missSery);
										}
										missTest.setId(pk_inner);
										 updateRecord=(missTestService.saveOrUpdateMissTest(xmissTest.getUserid(),missTest).intValue());
						    		}
							return returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_FIND_ANSWERED)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTest> ntcCalendars=(ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTest>) missTestService.findMissTestAnswer(xbpsTerm.getUserid(),bpsTerm);
							VResultMessage vresultMessage = new VResultMessage();
							if(ntcCalendars!=null && ntcCalendars.size()>0){
								List<th.co.aoe.makedev.missconsult.xstream.MissTest> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTest>(ntcCalendars.size());
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissTestObject(ntcCalendars);
								}
								vresultMessage.setResultListObj(xntcCalendars);
							}
							
							return getRepresentation(entity, vresultMessage, xstream);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_UPDATE)){
							java.sql.Timestamp timeStampStartDate = new java.sql.Timestamp(new Date().getTime());
							int updateRecord=missTestService.updateMissTest(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						
						
						else if(serviceName.equals(ServiceConstant.MISS_TEST_DELETE)){
							int updateRecord=missTestService.deleteMissTest(bpsTerm);
							returnUpdateRecord(entity,xbpsTerm,updateRecord);
						}
						else if(serviceName.equals(ServiceConstant.MISS_TEST_SEARCH)){
							Pagging page = xbpsTerm.getPagging(); 
							List result = (List) missTestService.searchMissTest(bpsTerm,page);
							if (result != null && result.size() == 2) {
								java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTest> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTest>) result
										.get(0);
								String faqs_size = (String) result.get(1);
//								 
								VResultMessage vresultMessage = new VResultMessage();

								List<th.co.aoe.makedev.missconsult.xstream.MissTest> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTest>();
								if (faqs_size != null && !faqs_size.equals(""))
									vresultMessage.setMaxRow(faqs_size);
								if (ntcCalendars != null && ntcCalendars.size() > 0) {
									xntcCalendars = getxMissTestObject(ntcCalendars);
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
		logger.debug("into GET MissTestResource");
		// Representation result = null;
		/* th.co.aoe.makedev.missconsult.hibernate.bean.MissTest ntcCalendarReturn = missTestService.findMissTestById(new Long(1));
		 logger.debug("ntcCalendarReturn="+ntcCalendarReturn.getMegName());
	        VResultMessage vresultMessage = new VResultMessage();
			List<th.co.aoe.makedev.missconsult.xstream.MissTest> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTest>(1);
			th.co.aoe.makedev.missconsult.xstream.MissTest xntcCalendarReturn = new th.co.aoe.makedev.missconsult.xstream.MissTest();
			BeanUtils.copyProperties(ntcCalendarReturn,xntcCalendarReturn);								
			xntcCalendarReturn.setPagging(null);
		 
			xntcCalendars.add(xntcCalendarReturn);
			vresultMessage.setResultListObj(xntcCalendars);
			ntcCalendarReturn.setMegName("Aoe update");
			int updateRecord=missTestService.updateMissTest(ntcCalendarReturn);*/
			/* th.co.aoe.makedev.missconsult.hibernate.bean.MissTest  xntcCalendarReturn_save = new  th.co.aoe.makedev.missconsult.hibernate.bean.MissTest ();
			xntcCalendarReturn_save.setMegName("save new");
			logger.debug("xxx="+updateRecord);
			missTestService.saveMissTest(xntcCalendarReturn_save);*/
			//returnUpdateRecord(entity,xbpsTerm,updateRecord);
			 /*th.co.aoe.makedev.missconsult.hibernate.bean.MissTest  xntcCalendarReturn_delete= new  th.co.aoe.makedev.missconsult.hibernate.bean.MissTest ();
			 xntcCalendarReturn_delete.setMegId(new Long(3));
			missTestService.deleteMissTest(xntcCalendarReturn_delete);*/
			//return getRepresentation(null, vresultMessage, xstream);
		Pagging page =new Pagging(); 
		th.co.aoe.makedev.missconsult.hibernate.bean.MissTest bpsTerm = new th.co.aoe.makedev.missconsult.hibernate.bean.MissTest();
		//bpsTerm.setMegName("Aoe");
		List result = (List) missTestService.searchMissTest(bpsTerm,page);
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissTest> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTest>();
		if (result != null && result.size() == 2) {
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTest> ntcCalendars = (java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTest>) result
					.get(0);
			String faqs_size = (String) result.get(1);
//			 
		

		
			if (faqs_size != null && !faqs_size.equals(""))
				vresultMessage.setMaxRow(faqs_size);
			if (ntcCalendars != null && ntcCalendars.size() > 0) {
				xntcCalendars = getxMissTestObject(ntcCalendars);
			}
		}
			vresultMessage.setResultListObj(xntcCalendars);
			return getRepresentation(null, vresultMessage, xstream);
	}
	private List<th.co.aoe.makedev.missconsult.xstream.MissTest> getxMissTestObject(
			java.util.ArrayList<th.co.aoe.makedev.missconsult.hibernate.bean.MissTest> ntcCalendars) {
		List<th.co.aoe.makedev.missconsult.xstream.MissTest> xntcCalendars = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTest>(
				ntcCalendars.size());
		for (th.co.aoe.makedev.missconsult.hibernate.bean.MissTest missTest : ntcCalendars) {
			th.co.aoe.makedev.missconsult.xstream.MissTest xmissTest =new th.co.aoe.makedev.missconsult.xstream.MissTest ();
			BeanUtils.copyProperties(missTest, xmissTest);
			xmissTest.setPagging(null);
			th.co.aoe.makedev.missconsult.hibernate.bean.MissTestPK pk_inner =missTest.getId();
			if(pk_inner.getMcNo()!=null && pk_inner.getMissQuestion()!=null ){
				th.co.aoe.makedev.missconsult.xstream.MissChoice missChoice = new th.co.aoe.makedev.missconsult.xstream.MissChoice();
				//BeanUtils.copyProperties(pk_inner.getMissChoice(),missChoice,ignore_id_choice); 
				missChoice.setMcNo(pk_inner.getMcNo());
				missChoice.setMqId(pk_inner.getMissQuestion().getMqId());
				missChoice.setPagging(null);
				xmissTest.setMissChoice(missChoice);
			}
			/*if(xmissTest.getMissChoice()!=null && xmissTest.getMissChoice().getMcNo()!=null){
				pk_inner.setMcNo(xmissTest.getMissChoice().getMcNo());
			}*/
			if(pk_inner.getMissExam()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissExam missExam = new th.co.aoe.makedev.missconsult.xstream.MissExam();
				BeanUtils.copyProperties(pk_inner.getMissExam(),missExam,ignore_id_exam); 
				missExam.setPagging(null);
				xmissTest.setMissExam(missExam);
			}
			if(pk_inner.getMissQuestion()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissQuestion missQuestion = new th.co.aoe.makedev.missconsult.xstream.MissQuestion();
				BeanUtils.copyProperties(pk_inner.getMissQuestion(),missQuestion,ignore_id_question); 
				missQuestion.setPagging(null);
				xmissTest.setMissQuestion(missQuestion);
			}
			if(pk_inner.getMissSery()!=null){
				th.co.aoe.makedev.missconsult.xstream.MissSery missSery = new th.co.aoe.makedev.missconsult.xstream.MissSery();
				BeanUtils.copyProperties(pk_inner.getMissSery(),missSery); 
				missSery.setPagging(null);
				xmissTest.setMissSery(missSery);
			}
		//	missTest.setId(pk_inner);
			xntcCalendars.add(xmissTest);
		}
		return xntcCalendars;
	} 
	private Representation returnUpdateRecord(Representation entity,th.co.aoe.makedev.missconsult.xstream.MissTest xbpsTerm,int updateRecord){
		VResultMessage vresultMessage = new VResultMessage();
		List<th.co.aoe.makedev.missconsult.xstream.MissTest> xbpsTerms = new ArrayList<th.co.aoe.makedev.missconsult.xstream.MissTest>(1);
		xbpsTerm.setUpdateRecord(updateRecord);
		xbpsTerms.add(xbpsTerm);
		vresultMessage.setResultListObj(xbpsTerms);
		//export(entity, vresultMessage, xstream);
		return getRepresentation(entity, vresultMessage, xstream);
	}
 
	public MissTestService getMissTestService() {
		return missTestService;
	}

	public void setMissTestService(MissTestService missTestService) {
		this.missTestService = missTestService;
	}
	public com.thoughtworks.xstream.XStream getXstream() {
		return xstream;
	}

	public void setXstream(com.thoughtworks.xstream.XStream xstream) {
		this.xstream = xstream;
	}

	 
}