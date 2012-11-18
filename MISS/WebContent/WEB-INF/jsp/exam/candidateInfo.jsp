<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<head>
<title></title>  
<c:url var="url" value="/" />
<c:url value="/logout" var="logoutUrl"/>
<c:url value="/exam/info" var="examInfoUrl"/>
<link rel="icon" href="<c:url value='/resources/images/favicon.ico'/>" type="image/x-icon" />
<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico'/>" type="image/x-icon" />  
<script  src="<c:url value='/resources/js/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>" type="text/javascript"></script>

<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui-1.8.21.custom.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/resources/js/jquery.countdown.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bday-picker.min.js'/>"></script> 

<link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"  type="text/css">
<link href="<c:url value='/resources/css/custom-theme/jquery-ui-1.8.21.custom.css'/>" type="text/css"  rel="stylesheet" /> 
 
<link href="<c:url value='/resources/css/3column.css'/>"  type="text/css" rel="stylesheet" />
<%--
<link href="<c:url value='/resources/css/menubar.css'/>"  type="text/css" rel="stylesheet" />  
--%>

<style>
.ui-widget { font-family: Trebuchet MS, Tahoma, Verdana,
 Arial, sans-serif; font-size: 12px; }
 </style>
 <style type="text/css">
 /*  fieldset { width: 100%; }
  fieldset legend { width: 100%; }
  fieldset legend div { margin: 0.3em 0.5em; }
  fieldset .field { margin: 0.5em; padding: 0.5em; }
  fieldset .field label { margin-right: 0.4em; } */
</style>
<style type="text/css">
/*.th_class{font-family: Tahoma;font-size: 13px;text-align: center;*/
.th_class{text-align: center;
}
a{cursor: pointer;}
</style>
<style type="text/css">
/*label,select,.ui-select-menu { float: left; margin-right: 10px; }*/
/*select { width: 200px; }*/
/*label,select,.ui-select-menu { } */
/* select { width: 55px;}*/
/*.ui-widget{font-family: Tahoma;font-size: 12px; }*/
th{ font-family:Tahoma; font-size:12px; font-weight:bold;
 color: #fff;background:#328AA4 url(<c:url value='/resources/images/tr_back.gif'/>) repeat-x;;padding: 5px 8px;border:1px solid #fff; 
}
input[type="text"],[type="password"]{height:30px}
img.ui-datepicker-trigger{cursor: pointer;}
 .span8 {
   padding: 2px;
}
.span2 {
   padding: 2px;
}
/* padding:10px 5px 15px 20px;
top padding is 10px
right padding is 5px
bottom padding is 15px
left padding is 20px */
</style>

<script type="text/javascript">
var _path="";
$(document).ready(function() {
	//alert("aa");
	//$('#tabs').tabs();
  //   $("fieldset.collapsibleClosed").collapse( { closed : true } );
 
  _path="${url}";
  //alert(_path)
  //alert(_path.split(";jsessionid=").length)
  if(_path.split(";jsessionid=").length>0){
	  _path=_path.split(";jsessionid=")[0];
	  //alert(_path.split(";jsessionid=").length);
  }
  /*
  $("#mcaBirthDate" ).datepicker({
		showOn: "button",
		buttonImage: _path+"resources/images/calendar.gif",
		buttonImageOnly: true,
		dateFormat:"dd/mm/yy" ,
		changeMonth: true,
		changeYear: true
	});
  */
  $("#picker2").birthdaypicker({
      futureDates: true,
     // maxYear: 2020,
      maxAge: 90 ,
     // defaultDate: "10-17-1980"
      defaultDate: "${missExamForm.mcaBirthDate}"
    });
	 $('select[class="birth-month"]').css("width","70px");
	 $('select[class="birth-day"]').css("width","61px");
	 $('select[class="birth-year"]').css("width","63px");
	 $('fieldset[class="birthday-picker"]').css("padding","0px");
	 
  var thisDay='${systemDate}'.split("/");
  var startYear=new Date(thisDay[2], parseInt(thisDay[1])-1, thisDay[0]);
  $('#defaultCountdown').countdown({since: startYear, compact: true, 
  format: 'DHMS', description: ''});
 // format: 'HMS', description: ''});
	//  format: 'hms', description: ''});
});
function doStart(){
	//document.forms["missExamForm"].submit();
//	$("#a_start").click(); 
//alert("x");
  $("#mcaBirthDate").val($("#birthdate").val());
	$.post("exam/info",$("#missExamForm").serialize(), function(data) {
	   window.location.href='${examInfoUrl}';
	});
}
function loadDynamicPage(pageId){
	//	var id="1";
		//$.get('ajax/search', function(data) {
	 /* 	$.get(pageId, function(data) {
			  // alert(data);
			  if(data!=null){
				  appendContent(data);
			  }
			}); */
			
			$.ajax({
				  type: "get",
				  url: pageId,
				  cache: false
				 // data: { name: "John", location: "Boston" }
				}).done(function( data ) {
					if(data!=null){
						  appendContent(data);
					  }
				});
}
function appendContentWithId(data,contentId){
	if(data.indexOf("j_username")!=-1){
		//alert("to home") 
		  window.location.href="<c:url value='/j_spring_security_logout'/>";
		 //$("#_content").html(data);
	  }else{
		/*   var _url_template=window.location.href;
		  if(_url_template.indexOf("template")!=-1){ 
			  window.location.href="<c:url value='/'/>";
		  }else{ */
			  $("#"+contentId).html(data);
		 // }
	  }
	
}
function appendContent(data){
	//alert(data)
	appendContentWithId(data,"_content");
	
}
function checkID(id) 
{ 
	if(id.length != 13)
		return false; 
		for(i=0, sum=0; i < 12; i++) 
			sum += parseFloat(id.charAt(i))*(13-i); 
		if((11-sum%11)%10!=parseFloat(id.charAt(12))) 
	 		return false; 
	return true;
} 
function getCandidateInfo(){ 
	//
	//
	var mcaCitizenId=jQuery.trim($("#missCandidate\\.mcaCitizenId").val());
	var mcaEmail=jQuery.trim($("#missCandidate\\.mcaEmail").val());
	var haveError=false;
	var message="";
	if(!checkID(mcaCitizenId) && mcaCitizenId.length>0){
		message="รหัสประชาชนไม่ถูกต้อง";	
		haveError=true;		
	}else if(mcaEmail.length==0 && mcaCitizenId.length==0){
		message="กรุณากรอก รหัสประชาชน และ Email";
		haveError=true;
	}
	if(haveError){
		$("#_message_show").html(message);
		$( "#dialog-Message" ).dialog({
			/* height: 140, */
			modal: true,
			buttons: {
				"Ok": function() { 
					$( this ).dialog( "close" );
					 
				}
			}
		  });
	}else{ 
		//alert("xxx")
		$.ajax({
			  type: "get",
			  url: "exam/getcandidateinfo?citizendID="+mcaCitizenId+"&email="+mcaEmail,
			  cache: false
			}).done(function( data ) {
				//alert(data)
				if(data!=null){  
					if(data.mcaId!=null){
						$("#missCandidate\\.mcaTitleType").val(data.mcaTitleType);
						$("#missCandidate\\.mcaFirstName").val(data.mcaFirstName);
						$("#missCandidate\\.mcaLastName").val(data.mcaLastName);
						//$("#missCandidate\\.mcaGender").val(data.mcaGender);
						 var mcaGenders=document.getElementsByName("missCandidate.mcaGender");
						for(var i=0;i<mcaGenders.length;i++){
							if(mcaGenders[i].value==data.mcaGender)
								mcaGenders[i].checked=true;
							else
								mcaGenders[i].checked=false;
						} 
						//alert(data.mcaBirthDate);
						if(data.mcaBirthDate!=null){
							//1983-04-17
							var birthDate=data.mcaBirthDate.split("-");
							$("select[name='birth\[day\]']").val(parseInt(birthDate[2]));
							$("select[name='birth\[month\]']").val(parseInt(birthDate[1]));
							$("select[name='birth\[year\]']").val(birthDate[0]);
						}
						/* $("select[name='birth\[day\]']").val(data.mcaTitleType);
						$("select[name='birth\[day\]']").val(data.mcaTitleType);
						$("select[name='birth\[day\]']").val(data.mcaTitleType); */
						if(data.missCareerMaster.mcmId!=null)
							$("#missCandidate\\.missCareerMaster\\.mcmId").val(data.missCareerMaster.mcmId);
						$("#missCandidate\\.mcaPosition").val(data.mcaPosition);
						$("#missCandidate\\.mcaDepartment").val(data.mcaDepartment);
						$("#missCandidate\\.mcaPhone").val(data.mcaPhone);
						//alert(data.missIndustryMaster.mimId)
						/* if(data.missIndustryMaster.mimId!=null)
							$("#missCandidate\\.missIndustryMaster\\.mimId").val(data.missIndustryMaster.mimId);  */
						//alert(data.mcaPictureHotlink)
						 
						
					}
					else
						alert("not have data");
				} 
			});
	}
}
</script>
</head>
<!-- <body style="background-color:rgb(231, 235, 242)"> -->
 <!-- <body style="background-color:rgb(241, 241, 241)"> -->
 <!--   style="background-color: white;" --> 
 <div id="dialog-Message" title="Message" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
	<span id="_message_show"></span>
</div>
 <body style="background-color:rgb(253, 253, 253);background-image:url(<c:url value='/resources/images/body.gif'/>); ">
 <div class="container-fluid">
    <div class="row-fluid">
    	<div class="span12" align="center"> 
    	<div id="header2" align="left"  style="height: 66px">
    	<!--  width="200px" height="33px" -->
    	<div align="left"><h1><img src="<c:url value='/resources/images/logowebmc.png'/>" width="200px" height="33px" />
    	
    	 </h1> </div> 
    	 <div align="center" style="position: absolute;top:0px; left:0px;right:0px; padding-top:10px;"><h1><img src="<c:url value='/resources/images/logowebmc.png'/>" />
    	</h1>
    	  </div> 
    	<!-- <div align="right" style="position: absolute; z-index:-5; width:300px; right:0;top:0; padding-top:10px; padding-right:10px;"> -->
    	<div align="right" style="position: absolute; z-index:0; width:300px; right:0;top:0; padding-top:10px; padding-right:10px;">
       <strong>System Time:</strong>&nbsp;&nbsp;${systemDate}&nbsp;&nbsp;<span id="defaultCountdown">hh:mm</span><br/><br/>
        <%-- <a  style="cursor: pointer;" href="?language=th_TH"><spring:message code="home_lang_th"/></a> | <a  style="cursor: pointer;" href="?language=en"><spring:message code="home_lang_en"/></a> --%>      
        </div>
         <div align="right" style="position: absolute;right:0;top:75; padding-right:10px;">
            <span id="menu-username"><%=SecurityContextHolder.getContext().getAuthentication().getName()%></span> &nbsp;&nbsp;<a href="${logoutUrl}">Logout</a>
            </div>
           </div>
           </div>
    </div>
    	<!-- <div class="row-fluid" style="background-color: rgb(0, 136, 204)">  -->
    	<div class="row-fluid"> 
    	 <%--   <div class="span2">
    	   <form class="well" style="background-color:rgb(245, 245, 245);border: 2px solid #DDD">
	    <img src="<c:url value='/resources/images/logowebmc.png'/>" />
	    </form>
	    </div> --%>
	    <div class="span10" id="_content">
	      <fieldset style="font-family: sans-serif;">  
           <!-- <legend  style="font-size: 13px">Criteria</legend> -->
           <!-- <div style="position:relative;right:-94%;">  </div> --> 
        <!--   <form class="well" style="border:2px solid #DDD"> -->
         <form:form  id="missExamForm" name="missExamForm" modelAttribute="missExamForm" cssClass="well" cssStyle="border:2px solid #DDD" method="post" action="">
              <table border="0" width="100%" style="font-size: 13px">
              				<tr>
	    					 <td align="left" width="100%" colspan="6"><strong>Candidate Infomation</strong></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="10%">Citizen ID:</td>
	    					 <td align="left" width="24%">
	    					<form:input path="missCandidate.mcaCitizenId" cssStyle="width:105px"/>  
	    					 </td>  
	    					 <td align="left" width="10%">Email:</td>
	    					<td align="left" width="24%">
	    					 <form:input path="missCandidate.mcaEmail"/>
	    					</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr> 
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="10%"></td>
	    					 <td align="left" width="58%" colspan="3">
	    							<a class="btn"  onclick="getCandidateInfo()"><i class="icon-search"></i>&nbsp;<spring:message code='button_search'/></a><br></br>
	    					 </td>  
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="10%">First Name:</td>
	    					 <td align="left" width="24%">
	    					 <form:select path="missCandidate.mcaTitleType" cssStyle="width:70px;background:#FFFFFF">
    						<form:option value="0">นาย</form:option>
    						<form:option value="1">นาง</form:option>
    						<form:option value="2">นางสาว</form:option>
    						<form:option value="3">ระบุ 	&rarr;</form:option>
    					</form:select>
    					<form:input path="missCandidate.mcaFirstName" cssStyle="width:120px"/>
	    					 </td>
	    					<td align="left" width="10%">Last Name:</td>
	    					<td align="left" width="24%"><form:input path="missCandidate.mcaLastName"/></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="10%">Career Group:</td>
	    					 <td align="left" width="24%">  
	    					   <form:select path="missCandidate.missCareerMaster.mcmId" cssStyle="background:#FFFFFF">
    								<form:options items="${missCareerMasterList}" itemLabel="mcmName" itemValue="mcmId"></form:options>
    						  </form:select>
	    					 </td> 
	    					<td align="left" width="10%"></td>
	    					<td align="left" width="24%">
	    					
	    					</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="10%">Position:</td>
	    					 <td align="left" width="24%"> <form:input path="missCandidate.mcaPosition"/>
	    					 </td>
	    					<td align="left" width="10%">Department:</td>
	    					<td align="left" width="24%"><form:input path="missCandidate.mcaDepartment"/></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="10%">Phone:</td>
	    					 <td align="left" width="24%"><form:input path="missCandidate.mcaPhone"/>  
	    					 </td>
	    					<td align="left" width="10%">Birth date:</td>
	    					<td align="left" width="24%"><div class="picker" id="picker2"></div> 
    						<form:hidden path="mcaBirthDate"  id="mcaBirthDate"/></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="10%">Gender:</td>
	    					 <td align="left" width="24%">  
	    					 <form:radiobutton path="missCandidate.mcaGender" value="0"/>Female&nbsp;&nbsp;&nbsp;<form:radiobutton path="missCandidate.mcaGender" value="1"/>Male 
	    					 </td>
	    					<td align="left" width="10%"></td>
	    					<td align="left" width="24%">
	    					
	    					</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    				  
	    					<%--
	    					 <tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="10%">Industry Type:</td>
	    					 <td align="left" width="24%">  
	    					  <form:select path="missCandidate.missIndustryMaster.mimId" cssStyle="background:#FFFFFF">
    								<form:options items="${missIndustryMasterList}" itemLabel="mimName" itemValue="mimId"></form:options>
    						  </form:select>
	    					 </td> 
	    					<td align="left" width="10%">Career Type:</td>
	    					<td align="left" width="24%">
	    					 <form:select path="missCandidate.missCareerMaster.mcmId" cssStyle="background:#FFFFFF">
    						<form:options items="${missCareerMasterList}" itemLabel="mcmName" itemValue="mcmId"></form:options>
    					</form:select>
	    					</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr> --%>
	    					</table>  
	    					</form:form>
	     <div align="center">			
		<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px;width: 20%">
        	<thead>
          		<tr> 
            		<th width="10%"><div class="th_class">${missExamForm.missCandidate.missSery.msSeriesName}</div></th>  
          		</tr>
        	</thead>
        	<tbody>
        		 <c:forEach items="${missExamForm.missCandidate.missSery.missExams}" var="missExam" varStatus="loop"> 
         		 	<tr> 
            			<td><div align="center">${missExam.meName}</div></td> 
          			</tr>
          		 </c:forEach>
        	</tbody>
      </table>
      </div>
       <div align="center">	
       <a class="btn btn-primary" onclick="doStart()" ><span style="color: white;font-weight: bold;">Next&nbsp;<i class="icon-chevron-right icon-white"></i></span></a>
       <!-- <input type="submit" value="AA"> -->
       </div>
</fieldset> 
	    </div>
  <div class="span2">
	    <!--Sidebar content--> 
            <div class="post_section">
				<h3>
				<img src="<c:url value='/resources/images/h2bg.gif'/>"  border="0" align="absmiddle"/>&nbsp;Description / Instruction / Remark </h3>
				<p>
					Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan.
				</p>
                 
              </div>
	    
	 </div>
    </div>
</div>
</body>
</html>
