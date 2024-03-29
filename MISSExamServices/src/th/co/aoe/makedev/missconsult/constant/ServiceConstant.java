package th.co.aoe.makedev.missconsult.constant;

import java.util.ResourceBundle;

public class ServiceConstant {
	public static final String hostReference = "http://10.2.0.76:10000/BPSService/RestletServlet/";
	
	public static final String LOG_APPENDER = "MISSConsultLog";
	
	public static final String INTERFACE_RETURN_TYPE = "java.util.List";
	public static final String VOID_RETURN_TYPE = "void";
	public static final ResourceBundle bundle;
	public static String SCHEMA="";
	static{
		bundle =  ResourceBundle.getBundle( "jdbc" );	
		SCHEMA=bundle.getString("schema");
	}

	// MISS_ACCOUNT
	public static final String MISS_ACCOUNT_SAVE = "saveMissAccount";
	public static final String MISS_ACCOUNT_UPDATE = "updateMissAccount";
	public static final String MISS_ACCOUNT_ITEMS_DELETE = "deleteMissAccountItems";
	public static final String MISS_ACCOUNT_DELETE = "deleteMissAccount";
	public static final String MISS_ACCOUNT_SEARCH = "searchMissAccount";
	public static final String MISS_ACCOUNT_FIND_BY_ID = "findMissAccountById";
	public static final String MISS_ACCOUNT_UPDATE_LOGO = "updateLogoMissAccount"; 
	public static final String MISS_ACCOUNT_REFILL = "refillMissAccount";
	
	 
	// MISS_EXAM_GROUP
	public static final String MISS_EXAM_GROUP_SAVE = "saveMissExamGroup";
	public static final String MISS_EXAM_GROUP_UPDATE = "updateMissExamGroup";
	public static final String MISS_EXAM_GROUP_DELETE = "deleteMissExamGroup";
	public static final String MISS_EXAM_GROUP_SEARCH = "searchMissExamGroup";
	public static final String MISS_EXAM_GROUP_FIND_BY_ID = "findMissExamGroupById"; 
	
	// MISS_ACCOUNT_SERIES_MAP
	public static final String MISS_ACCOUNT_SERIES_MAP_SAVE = "saveMissAccountSeriesMap";
	public static final String MISS_ACCOUNT_SERIES_MAP_UPDATE = "updateMissAccountSeriesMap";
	public static final String MISS_ACCOUNT_SERIES_MAP_DELETE = "deleteMissAccountSeriesMap";
	public static final String MISS_ACCOUNT_SERIES_MAP_SEARCH = "searchMissAccountSeriesMap";
	public static final String MISS_ACCOUNT_SERIES_MAP_FIND_BY_ID = "findMissAccountSeriesMapById";
	public static final String MISS_ACCOUNT_SERIES_MAP_FIND_BY_MA_ID = "findMissAccountSeriesMapByMaId";  
	public static final String MISS_ACCOUNT_SERIES_MAP_FIND_BY_ROLE = "findMissAccountSeriesMapByRole";  
	
	// MISS_CANDIDATE
	public static final String MISS_CANDIDATE_SAVE = "saveMissCandidate";
	public static final String MISS_CANDIDATE_UPDATE = "updateMissCandidate";
	public static final String MISS_CANDIDATE_ITEMS_DELETE = "deleteMissCandidateItems";
	public static final String MISS_CANDIDATE_DELETE = "deleteMissCandidate";
	public static final String MISS_CANDIDATE_SEARCH = "searchMissCandidate";
	public static final String MISS_CANDIDATE_FIND_BY_ID = "findMissCandidateById";
	public static final String MISS_CANDIDATE_UPDATE_PHOTO = "updatePhotoMissCandidate";
	
	public static final String MISS_CANDIDATE_FIND_BY_NAME = "findMissCandidateByName"; 
	public static final String MISS_CANDIDATE_EXPORT = "exportMissCandidate"; 
	
	// MISS_CHOICE
	public static final String MISS_CHOICE_SAVE = "saveMissChoice";
	public static final String MISS_CHOICE_UPDATE = "updateMissChoice";
	public static final String MISS_CHOICE_DELETE = "deleteMissChoice";
	public static final String MISS_CHOICE_SEARCH = "searchMissChoice";
	public static final String MISS_CHOICE_FIND_BY_ID = "findMissChoiceById"; 
	
	// MISS_EVALUATION_TEMPLATE
	public static final String MISS_EVALUATION_TEMPLATE_SAVE = "saveMissEvaluationTemplate";
	public static final String MISS_EVALUATION_TEMPLATE_UPDATE = "updateMissEvaluationTemplate";
	public static final String MISS_EVALUATION_TEMPLATE_DELETE = "deleteMissEvaluationTemplate";
	public static final String MISS_EVALUATION_TEMPLATE_SEARCH = "searchMissEvaluationTemplate";
	public static final String MISS_EVALUATION_TEMPLATE_FIND_BY_ID = "findMissEvaluationTemplateById"; 
	
	// MISS_EXAM
	public static final String MISS_EXAM_SAVE = "saveMissExam";
	public static final String MISS_EXAM_UPDATE = "updateMissExam";
	public static final String MISS_EXAM_ITEMS_DELETE = "deleteMissExamItems";
	public static final String MISS_EXAM_DELETE = "deleteMissExam";
	public static final String MISS_EXAM_SEARCH = "searchMissExam";
	public static final String MISS_EXAM_FIND_BY_ID = "findMissExamById"; 
	public static final String MISS_EXAM_LIST = "listMissExam"; 	
	public static final String MISS_EXAM_COPY = "copyMissExam";
	public static final String MISS_EXAM_CREATE_EMPTY = "createEmptyMissExam";
	
	// MISS_EXAM_TYPE
	public static final String MISS_EXAM_TYPE_SAVE = "saveMissExamType";
	public static final String MISS_EXAM_TYPE_UPDATE = "updateMissExamType";
	public static final String MISS_EXAM_TYPE_DELETE = "deleteMissExamType";
	public static final String MISS_EXAM_TYPE_SEARCH = "searchMissExamType";
	public static final String MISS_EXAM_TYPE_FIND_BY_ID = "findMissExamTypeById"; 
	
	// MISS_QUESTION
	public static final String MISS_QUESTION_SAVE = "saveMissQuestion";
	public static final String MISS_QUESTION_UPDATE = "updateMissQuestion";
	public static final String MISS_QUESTION_DELETE = "deleteMissQuestion";
	public static final String MISS_QUESTION_SEARCH = "searchMissQuestion";
	public static final String MISS_QUESTION_FIND_BY_ID = "findMissQuestionById"; 
	public static final String MISS_QUESTION_LIST = "listMissQuestion";
	public static final String MISS_QUESTION_LIST_WITH_CHOICES = "listMissQuestionWithChoices";
	
	
	
	public static final String MISS_QUESTION_CHOICES_SAVE = "saveMissChoices"; 
	public static final String MISS_QUESTION_CHOICES_UPDATE = "updateMissChoices"; 
	public static final String MISS_QUESTION_CHOICES_DELETE = "deleteMissChoices"; 
	public static final String MISS_QUESTION_SETUP_TEST = "setUpTestMissQuestion";
	public static final String MISS_QUESTION_GET_ORDERED = "getOrderedQuestion";
	public static final String MISS_QUESTION_SET_ORDERED_ITEMS = "setOrderedItemQuestion";
	
	
	// MISS_SERIES_MAP
	public static final String MISS_SERIES_MAP_SAVE = "saveMissSeriesMap";
	public static final String MISS_SERIES_MAP_UPDATE = "updateMissSeriesMap";
	public static final String MISS_SERIES_MAP_DELETE = "deleteMissSeriesMap";
	public static final String MISS_SERIES_MAP_SEARCH = "searchMissSeriesMap";
	public static final String MISS_SERIES_MAP_FIND_BY_ID = "findMissSeriesMapById"; 
	
	
	// MISS_SERIES_ATTACH
	public static final String MISS_SERIES_ATTACH_SAVE = "saveMissSeriesAttach";
	public static final String MISS_SERIES_ATTACH_UPDATE = "updateMissSeriesAttach";
	public static final String MISS_SERIES_ATTACH_DELETE = "deleteMissSeriesAttach";
	public static final String MISS_SERIES_ATTACH_SEARCH = "searchMissSeriesAttach";
//	public static final String MISS_SERIES_ATTACH_FIND_BY_ID = "findMissSeriesAttachById"; 
	
	// MISS_SERIES
	public static final String MISS_SERIES_SAVE = "saveMissSery";
	public static final String MISS_SERIES_UPDATE = "updateMissSery";
	public static final String MISS_SERIES_DELETE = "deleteMissSery";
	public static final String MISS_SERIES_ITEMS_DELETE = "deleteMissSeryItems";
	public static final String MISS_SERIES_SEARCH = "searchMissSery";
	public static final String MISS_SERIES_FIND_BY_ID = "findMissSeryById"; 
	public static final String MISS_SERIES_LIST = "listMissSeries"; 	
	
	// MISS_SURVEY_SEND
	public static final String MISS_SURVEY_SEND_SAVE = "saveMissSurveySend";
	public static final String MISS_SURVEY_SEND_UPDATE = "updateMissSurveySend";
	public static final String MISS_SURVEY_SEND_DELETE = "deleteMissSurveySend";
	public static final String MISS_SURVEY_SEND_SEARCH = "searchMissSurveySend";
	public static final String MISS_SURVEY_SEND_FIND_BY_ID = "findMissSurveySendById"; 
	public static final String MISS_SURVEY_SEND = "sendSurvey";
	
	// MISS_TEMPLATE
	public static final String MISS_TEMPLATE_SAVE = "saveMissTemplate";
	public static final String MISS_TEMPLATE_UPDATE = "updateMissTemplate";
	public static final String MISS_TEMPLATE_DELETE = "deleteMissTemplate";
	public static final String MISS_TEMPLATE_SEARCH = "searchMissTemplate";
	public static final String MISS_TEMPLATE_FIND_BY_ID = "findMissTemplateById"; 
	
	// MISS_THEME 
	public static final String MISS_THEME_LIST = "listMissTheme"; 
	public static final String MISS_THEME_FIND_BY_ID = "findMissThemeById";
	
	// MISS_TEST
	public static final String MISS_TEST_SAVE = "saveMissTest";
	public static final String MISS_TEST_UPDATE = "updateMissTest";
	public static final String MISS_TEST_DELETE = "deleteMissTest";
	public static final String MISS_TEST_SEARCH = "searchMissTest";
	public static final String MISS_TEST_FIND_BY_ID = "findMissTestById";
	public static final String MISS_TEST_FIND_ANSWERED = "findMissTestAnswered"; 
	public static final String MISS_TEST_PAPER_SAVE = "saveMissTestPaper"; 
	
	// MISS_TEST_RESULT
	public static final String MISS_TEST_RESULT_SAVE = "saveMissTestResult";
	public static final String MISS_TEST_RESULT_UPDATE = "updateMissTestResult";
	public static final String MISS_TEST_RESULT_DELETE = "deleteMissTestResult";
	public static final String MISS_TEST_RESULT_SEARCH = "searchMissTestResult";
	public static final String MISS_TEST_RESULT_FIND_BY_ID = "findMissTestResultById";
	public static final String MISS_TEST_RESULT_PROCESS = "processMissTestResult"; 
	public static final String MISS_TEST_RESULT_START = "startMissTestResult";
	public static final String MISS_TEST_RESULT_CHECK = "checkMissTestResult"; 
	public static final String MISS_TEST_RESULT_UPDATE_STATUS = "updateStatusMissTestResult"; 
	public static final String MISS_TEST_RESULT_UPDATE_STATUS_LIST = "updateStatusMissTestResultList";
	public static final String MISS_UPDATE_TIME_OUT = "updateTimeOut";
	
	// MISS_TODO
	public static final String MISS_TODO_SAVE = "saveMissTodo";
	public static final String MISS_TODO_UPDATE = "updateMissTodo";
	public static final String MISS_TODO_DELETE = "deleteMissTodo";
	public static final String MISS_TODO_SEARCH = "searchMissTodo";
	public static final String MISS_TODO_FIND_BY_ID = "findMissTodoById";
	public static final String MISS_TODO_FIND_MAIL = "findCandidateEmailFromMissTodo"; 
	
	// MISS_ATTACH
	public static final String MISS_ATTACH_SAVE = "saveMissAttach";
	public static final String MISS_ATTACH_UPDATE = "updateMissAttach";
	public static final String MISS_ATTACH_DELETE = "deleteMissAttach";
	public static final String MISS_ATTACH_SEARCH = "searchMissAttach";
	public static final String MISS_ATTACH_FIND_BY_ID = "findMissAttachById"; 
	
	// MISS_CONTACT
	public static final String MISS_CONTACT_SAVE = "saveMissContact";
	public static final String MISS_CONTACT_UPDATE = "updateMissContact";
	public static final String MISS_CONTACT_DELETE = "deleteMissContact";
	public static final String MISS_CONTACT_SEARCH = "searchMissContact";
	public static final String MISS_CONTACT_FIND_BY_ID = "findMissContactById";
	public static final String MISS_CONTACT_FIND_BY_USERNAME = "findMissContactByUsername";
	public static final String MISS_CONTACT_COUNT_BY_USERNAME = "countMissContactByUsername";
	
	public static final String MISS_CONTACT_FIND_BY_CITIZENID_AND_EMAIL = "findMissContactByCitizenIdAndEmail";
	
	public static final String MISS_CONTACT_ITEMS_DELETE ="deleteMissAccountItems";
	public static final String MISS_CONTACT_LIST = "listContacts";
	public static final String MISS_CONTACT_UPDATE_PHOTO = "updatePhotoMissContact"; 
	
	// MISS_MANUAL
	public static final String MISS_MANUAL_SAVE = "saveMissManual";
	public static final String MISS_MANUAL_UPDATE = "updateMissManual";
	public static final String MISS_MANUAL_DELETE = "deleteMissManual";
	public static final String MISS_MANUAL_SEARCH = "searchMissManual";
	public static final String MISS_MANUAL_FIND_BY_ID = "findMissManualById"; 
	public static final String MISS_MANUAL_ITEMS_DELETE ="deleteMissManualItems";
	
	
	// role_contact
		public static final String ROLE_CONTACT_SAVE = "saveRoleContact";
		public static final String ROLE_CONTACT_UPDATE = "updateRoleContact";
		public static final String ROLE_CONTACT_DELETE = "deleteRoleContact";
		public static final String ROLE_CONTACT_SEARCH = "searchRoleContact";
		public static final String ROLE_CONTACT_FIND_BY_ID = "findRoleContactById"; 
		public static final String ROLE_CONTACT_ITEMS_DELETE ="deleteRoleContactItems";
		public static final String ROLE_CONTACT_LIST_BY_MA_ID ="listRoleContactByMaId";
		
		
		// role_mapping
		public static final String ROLE_MAPPING_SAVE = "saveRoleMapping";
		public static final String ROLE_MAPPING_UPDATE = "updateRoleMapping";
		public static final String ROLE_MAPPING_DELETE = "deleteRoleMapping";
		public static final String ROLE_MAPPING_SEARCH = "searchRoleMapping";
		public static final String ROLE_MAPPING_FIND_BY_ID = "findRoleMappingById"; 
		public static final String ROLE_MAPPING_ITEMS_DELETE ="deleteRoleMappingItems";
		public static final String ROLE_MAPPING_LIST_BY_RC_ID ="listRoleMappingByRcId";
		
		// role_series_mapping
		public static final String ROLE_SERIES_MAPPING_SAVE = "saveRoleSeriesMapping";
		public static final String ROLE_SERIES_MAPPING_UPDATE = "updateRoleSeriesMapping";
		public static final String ROLE_SERIES_MAPPING_DELETE = "deleteRoleSeriesMapping";
		public static final String ROLE_SERIES_MAPPING_SEARCH = "searchRoleSeriesMapping";
		public static final String ROLE_SERIES_MAPPING_FIND_BY_ID = "findRoleSeriesMappingById"; 
		public static final String ROLE_SERIES_MAPPING_ITEMS_DELETE ="deleteRoleSeriesMappingItems";
		public static final String ROLE_SERIES_MAPPING_LIST_BY_RC_ID ="listRoleSeriesMappingByRcId";
		
		
		// role_type
		public static final String ROLE_TYPE_SAVE = "saveRoleType";
		public static final String ROLE_TYPE_UPDATE = "updateRoleType";
		public static final String ROLE_TYPE_DELETE = "deleteRoleType";
		public static final String ROLE_TYPE_SEARCH = "searchRoleType";
		public static final String ROLE_TYPE_FIND_BY_ID = "findRoleTypeById"; 
		public static final String ROLE_TYPE_ITEMS_DELETE ="deleteRoleTypeItems";
		public static final String ROLE_TYPE_LIST_BY_RC_ID ="listRoleTypeByRcId";
		public static final String ROLE_TYPE_LIST ="listRoleTypes";
		
		//MISS_CAREER_MASTER
		public static final String CAREER_MASTER_LIST ="listCareerMaster";
		
		//MISS_INDUSTRY_MASTER
		public static final String INDUSTRY_MASTER_LIST ="listIndustryMaster";
		
		
		//MISS_POSITION_MASTER
		public static final String POSITION_MASTER_LIST ="listPositionMaster";
		
		//MISS_DEPARTMENT_MASTER
		public static final String DEPARTMENT_MASTER_LIST ="listDepartmentMaster";
		
		// MISS_ACCOUNT_GROUP
		public static final String MISS_ACCOUNT_GROUP_SAVE = "saveMissAccountGroup";
		public static final String MISS_ACCOUNT_GROUP_SEARCH = "searchMissAccountGroup";
		
		// MISS_ACCOUNT_MAPPING
		public static final String MISS_ACCOUNT_MAPPING_SAVE = "saveMissAccountMapping";
		public static final String MISS_ACCOUNT_MAPPING_SEARCH = "searchMissAccountMapping";
		
		// MISS_REACTIVE_LOG
		public static final String MISS_REACTIVE_LOG_SAVE = "saveMissReactiveLog";
		public static final String MISS_REACTIVE_LOG_SEARCH = "searchMissReactiveLog";

		// MISS_SERY_ORDER
		public static final String MISS_SERY_ORDER_SAVE = "saveMissSeryOrder";
		public static final String MISS_SERY_ORDER_SEARCH = "searchMissSeryOrder";
				
		// MISS_SERY_PROBLEM
		public static final String MISS_SERY_PROBLEM_SAVE = "saveMissSeryProblem";
		public static final String MISS_SERY_PROBLEM_SEARCH = "searchMissSeryOrder";
				
		// MISS_SERY_USE
		public static final String MISS_SERY_USE_SAVE = "saveMissSeryUse";
		public static final String MISS_SERY_USE_SEARCH = "searchMissSeryUse";
				
		// MISS_SYSTEM_USE
		public static final String MISS_SYSTEM_USE_SAVE = "saveMissSystemUse";
		public static final String MISS_SYSTEM_USE_SEARCH = "searchMissSystemUse";		
		
		//Manage Report 
		public static final String MANAGE_REPORT_MODE_ALL= "1";
		public static final String MANAGE_REPORT_MODE_SECTION= "0";
		public static final String EPT_NORM_REPORT_FIND= "findEPTNormReport";
		public static final String EPT_NORM_REPORT_GET_COMPANIES= "getCompaniesEPTNormReport";
		public static final String CUSTOMER_REPORT_FIND= "findCustomerReport";
		public static final String CUSTOMER_REPORT_GET_GROUPS= "getGroupsCustomerReport";
		public static final String SERVICE_REPORT_FIND= "findServiceReport";
		public static final String PRODUCT_REPORT_FIND= "findProductReport";
		public static final String CONSULTANT_REPORT_FIND= "findConsultantReport";
		public static final String CONSULTANT_REPORT_GET_SALES= "getSalesConsultantReport";
		
		// MISS_DOC
		public static final String MISS_DOC_SAVE = "saveMissDoc";
		public static final String MISS_DOC_UPDATE = "updateMissDoc";
		public static final String MISS_DOC_DELETE = "deleteMissDoc";
		public static final String MISS_DOC_SEARCH = "searchMissDoc";
		public static final String MISS_DOC_FIND_BY_ID = "findMissDocById";
		
		// MISS_REPORT_ATTACH
		public static final String MISS_REPORT_ATTACH_SAVE = "saveMissReportAttach";
		public static final String MISS_REPORT_ATTACH_UPDATE = "updateMissReportAttach";
		public static final String MISS_REPORT_ATTACH_DELETE = "deleteMissReportAttach";
		public static final String MISS_REPORT_ATTACH_SEARCH = "searchMissReportAttach";
		public static final String MISS_REPORT_ATTACH_FIND_BY_ID = "findMissReportAttachById"; 
		public static final String MISS_REPORT_ATTACH_GET_TEMPLATE = "getTemplateMissReportAttach";
		public static final String MISS_REPORT_ATTACH_UPDATE_REPORTNAME = "updateMissReportAttachReportName";
		public static final String MISS_REPORT_ATTACH_GET_TEMPLATE_FOR_ROLE = "getTemplateMissReportAttachForRole";
		
		public static final String MISS_SERIES_PARTICIPANTS_MAP_GET = "getMissSeriesParticipantsMap";
		public static final String MISS_SERIES_PARTICIPANTS_MAP_DELETE = "deleteMissSeriesParticipantsMap";
		public static final String MISS_SERIES_PARTICIPANTS_MAP_UPDATE = "updateMissSeriesParticipantsMap";
		public static final String MISS_SERIES_PARTICIPANTS_MAP_LIST = "listMissSeriesParticipantsMap";
		
		// role_series_report_mapping 
		public static final String ROLE_SERIES_REPORT_MAPPING_UPDATE = "updateRoleSeriesReportMapping";   
		public static final String ROLE_SERIES_REPORT_MAPPING_LIST_BY_RC_ID ="listRoleSeriesReportMappingByRcId";
		
}
