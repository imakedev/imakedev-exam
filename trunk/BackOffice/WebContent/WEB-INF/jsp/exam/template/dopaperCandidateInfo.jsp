<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %> 
<script type="text/javascript">
$(document).ready(function() {
  //alert("ss")
  $("#picker2").birthdaypicker({
      futureDates: true,
     // maxYear: 2020,
      maxAge: 90 ,
     // defaultDate: "10-17-1980"
      defaultDate: "${paperForm.mcaBirthDate}"
    });
	 $('select[class="birth-month"]').css("width","70px");
	 $('select[class="birth-day"]').css("width","61px");
	 $('select[class="birth-year"]').css("width","63px");
	 $('fieldset[class="birthday-picker"]').css("padding","0px");
	  
});
function doStart(){
	 
 // $("#mcaBirthDate").val($("#birthdate").val()); 
  $("#mcaBirthDate").val($("select[name='birth\[day\]']").val()+"/"+$("select[name='birth\[month\]']").val()+"/"+$("select[name='birth\[year\]']").val());
 //  "missSery.msId" id="msId"
 // alert($("#msId").val());
  $("#examId").val($('select[id="selectExamElement"]').val());
  $("#setId").val($('select[id="setExamElement"]').val());
 
	$.post("paper/exam",$("#paperForm").serialize(), function(data) {
	  // window.location.href='${examInfoUrl}';
		 appendContent(data)
	});
}
function changeLangExam(objSelect){
	$("#selectLang").val(objSelect.value);
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
function goBackTestResult(){
	
	loadDynamicPage("result/search");
}
function getInfoByUsername(){ 
	var mcaUsername=jQuery.trim($("#missCandidate\\.mcaUsername").val());
	//alert(mcaUsername)
	$("#info_element").slideUp(300);
	$("#set_element").slideUp(300);
	$("#start_element").slideUp(300);
	$("#exam_element").slideUp(300);
	$("#missCandidate\\.mcaTitleType").val("");
	$("#missCandidate\\.mcaFirstName").val("");
	$("#missCandidate\\.mcaLastName").val("");
	$("#missCandidate\\.mcaCitizenId").val("");
	$("#missCandidate\\.mcaEmail").val("");
	$("#missCandidate\\.mcaPosition").val("");
	$("#missCandidate\\.mcaDepartment").val("");
	$("#missCandidate\\.mcaPhone").val("");
	$.ajax({
		  type: "get",
		  url: "paper/getcandidateinfo?username="+mcaUsername,
		  cache: false
		}).done(function( data ) {
			// alert(data)
			if(data!=null){  
				if(data.mcaId!=null){
					$("#mcaId").val(data.mcaId); 
					$("#msId").val(data.missSery.msId);
					$("#sery_element").html(data.missSery.msSeriesName); 
					var missExams_str="<select id=\"selectExamElement\" onchange=\"selectExamElement()\">";
					if(data.missSery.missExams!=null && data.missSery.missExams.length>0){
						for(var i=0;i<data.missSery.missExams.length;i++){
							missExams_str=missExams_str+"<option value=\""+data.missSery.missExams[i].meId+"\">"+data.missSery.missExams[i].meName+"</option>"
						}
					}
					missExams_str=missExams_str+"</select>";
					
					$("#exam_element_select").html(missExams_str); 
					$("#missCandidate\\.mcaTitleType").val(data.mcaTitleType);
					$("#missCandidate\\.mcaFirstName").val(data.mcaFirstName);
					$("#missCandidate\\.mcaLastName").val(data.mcaLastName);
					$("#missCandidate\\.mcaCitizenId").val(data.mcaCitizenId);
					$("#missCandidate\\.mcaEmail").val(data.mcaEmail);
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
					 
					if(data.missCareerMaster!=null && data.missCareerMaster.mcmId!=null)
						$("#missCandidate\\.missCareerMaster\\.mcmId").val(data.missCareerMaster.mcmId);
					$("#missCandidate\\.mcaPosition").val(data.mcaPosition);
					$("#missCandidate\\.mcaDepartment").val(data.mcaDepartment);
					$("#missCandidate\\.mcaPhone").val(data.mcaPhone);
					//alert(data.missIndustryMaster.mimId)
					/* if(data.missIndustryMaster.mimId!=null)
						$("#missCandidate\\.missIndustryMaster\\.mimId").val(data.missIndustryMaster.mimId);  */
					//alert(data.mcaPictureHotlink)
					 
					$("#exam_element").slideDown(300);
					$("#info_element").slideDown(300);
					$("#set_element").slideDown(300);
					$("#start_element").slideDown(300);
				}
				else{
					alert("not have data");
					
				}
			} 
		});
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
						$("#mcaId").val(data.mcaId);
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
						//$("#info_element").slideDown(300);
						//$("#set_element").slideDown(300);
						
					}
					else{
						alert("not have data");
						//$("#info_element").slideUp(300);
						//$("#set_element").slideUp(300);
					}
				} 
			});
	}
}
function createCandidate(){   
	var amount="1";
	var mssery_candidate_hidden=$("select[id=mcaSeries] option:selected").val();
	var company_candidate_hidden="${UserMissContact.mcontactRef}";
	 
	//alert("mssery_candidate_hidden->"+mssery_candidate_hidden+"company_candidate_hidden->"+company_candidate_hidden);
	//return false;
//	Long mssery_candidate_hidden=Long.valueOf(request.getParameter("mssery_candidate_hidden"));
//	Long company_candidate_hidden=Long.valueOf(request.getParameter("company_candidate_hidden"));
//	Long amount=Long.valueOf(request.getParameter("amount"));
	//$.get("company/candidate/create",$("#create_candidate_form").serialize(),function(data) {
	$.get("company/candidate/create?amount="+amount+"&mssery_candidate_hidden="+mssery_candidate_hidden+"&company_candidate_hidden="+company_candidate_hidden,function(data) {
		var obj = data;//jQuery.parseJSON(data);
		
		/* alert(jQuery.type(data)==="string");
		alert(jQuery.type(data)==='object'); */
		var size=280;
		var buttons;
		  if(jQuery.type(data)==="string"){
			  $("#message_candidate_create").html("<strong>Session time out</strong>"); 
			   $("#message_candidate_create").attr("class","alert alert-error"); 
			   buttons=  {
						"Ok": function() { 
							$( this ).dialog( "close" ); 
						  } 
					   };
		  } else if(obj.updateRecord!=0 && obj.updateRecord!=-1){
			   $("#message_candidate_create").html("<strong>Add Candidate Success</strong>"); 
			   $("#message_candidate_create").attr("class","alert alert-success"); 
			 //  alert(obj.mcaUsername);
			   $("#missCandidate\\.mcaUsername").val(obj.mcaUsername)
			   buttons=  {
						"Ok": function() { 
							$( this ).dialog( "close" ); 
							getInfoByUsername();
						  } 
					   };
		   }else{ 
			   $("#message_candidate_create").html("<strong>Can't Create Candidate [Unit not enough]/ Company: "+obj.missAccount.maName+" / Series: "+obj.missSery.msSeriesName+"</strong>");
			   $("#message_candidate_create").attr("class","alert alert-error"); 
			   size=580;
			   buttons=  {
						"Ok": function() { 
							$( this ).dialog( "close" ); 
						  } 
					   };
		   } 
		  $( "#dialog-create-candidate-alert" ).dialog({
				/* height: 140, */
				width:size,
				modal: true,
				title:"Messages",
				buttons:buttons
			}); 
	});
}
</script>
 <div id="dialog-create-candidate-alert"  title="Message" style="display: none;"> 
    <div id="message_candidate_create"></div> 
  </div>
	      <fieldset style="font-family: sans-serif;"> 
         <form:form  id="paperForm" name="paperForm" modelAttribute="paperForm" cssClass="well" cssStyle="border:2px solid ${UserMissContact.missTheme.mtBgColor};background: url(/MISSExamBackOffice/resources/images/${UserMissContact.missTheme.mtWaterWall}) no-repeat scroll right bottom ${UserMissContact.missTheme.mtBgColor}" method="post" action="">
         	  <form:hidden path="examId" id="examId"/>
         	  <form:hidden path="setId" id="setId"/> 
         	  <form:hidden path="missSery.msId" id="msId"/>
         	  <form:hidden path="missCandidate.mcaId" id="mcaId"/>
         	  <table border="0" width="100%" style="font-size: 13px">
              				<tr valign="top">
	    					 <td align="left" width="15%" valign="top"><div style="margin-top: 10px"><b>ค้นหา Candidate</b></div></td>
	    					 <td align="left" width="35%" colspan="5">
	    					  </td>  
	    					  <td align="left" width="15%"><div style="margin-top: 10px"><b>สร้าง Candidate</b></div></td>
	    					 <td align="left" width="35%" colspan="5">
	    					  </td> 
	    					</tr>
	    					<tr valign="top">
	    					 <td align="left" width="50%" colspan="6">
	    					 <span>Username :</span>
	    					 <form:input path="missCandidate.mcaUsername" cssStyle="width:105px"/> 
	    					 <a class="btn" style="margin-top: -9px"  onclick="getInfoByUsername()"><i class="icon-search"></i>&nbsp;get Info</a><br></br>
	    					 
	    					</td>   
	    					 <td align="left" width="50%" colspan="6">
	    					 <span>Series :</span>
	    					  <form:select path="mcaSeries">
	    					     		<form:options itemValue="missSery.msId" itemLabel="missSery.msSeriesName" items="${missSeries}"/>
	    					   </form:select>	
	    					 <div style="padding-left: 50px;padding-top: 2px;"><a class="btn" style="margin-top: -9px"  onclick="createCandidate()"><i class="icon-search"></i>&nbsp;Create Candidate</a><br></br>
	    					 </div>
	    					 </td> 
	    					</tr>
	    	 </table>
	    	 <div id="info_element" style="display: none">
              <table  border="0" width="100%" style="font-size: 13px;">
              				<tr>
	    					 <td align="left" width="100%" colspan="6"><strong>Candidate Information</strong></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="7%">&nbsp;</td>
	    					 <td align="left" width="10%">Citizen ID:</td>
	    					 <td align="left" width="29%">
	    					<form:input path="missCandidate.mcaCitizenId" cssStyle="width:105px"/>  
	    					 </td>  
	    					 <td align="left" width="10%">Email:</td>
	    					<td align="left" width="29%">
	    					 <form:input path="missCandidate.mcaEmail"/>&nbsp;<font color="red">*</font>
	    					</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr> 
	    					<tr>
	    					 <td align="left" width="7%">&nbsp;</td>
	    					 <td align="left" width="10%"></td>
	    					 <td align="left" width="68%" colspan="3">
	    							<a class="btn"  onclick="getCandidateInfo()"><i class="icon-search"></i>&nbsp;<spring:message code='button_search'/></a><br></br>
	    					 </td>  
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="7%">&nbsp;</td>
	    					 <td align="left" width="10%">First Name:</td>
	    					 <td align="left" width="29%">
	    					 <form:select path="missCandidate.mcaTitleType" cssStyle="width:70px;background:#FFFFFF">
    						<form:option value="0">นาย</form:option>
    						<form:option value="1">นาง</form:option>
    						<form:option value="2">นางสาว</form:option>
    						<form:option value="3">ระบุ 	&rarr;</form:option>
    					</form:select>
    					<form:input path="missCandidate.mcaFirstName" cssStyle="width:120px"/>&nbsp;<font color="red">*</font>
	    					 </td>
	    					<td align="left" width="10%">Last Name:</td>
	    					<td align="left" width="29%"><form:input path="missCandidate.mcaLastName"/>&nbsp;<font color="red">*</font></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="7%">&nbsp;</td>
	    					 <td align="left" width="11%">Career Group:</td>
	    					 <td align="left" width="28%">  
	    					   <form:select path="missCandidate.missCareerMaster.mcmId" cssStyle="background:#FFFFFF">
    								<form:options items="${missCareerMasterList}" itemLabel="mcmName" itemValue="mcmId"></form:options>
    						  </form:select>&nbsp;<font color="red">*</font>
	    					 </td> 
	    					<td align="left" width="10%"></td>
	    					<td align="left" width="29%">
	    					
	    					</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="7%">&nbsp;</td>
	    					 <td align="left" width="10%">Position:</td>
	    					 <td align="left" width="29%">
	    					  <%-- <form:input path="missCandidate.mcaPosition"/> --%>
	    					  <c:if test="${paperForm.missCandidate.missAccount.maId==17}"> 
    							 <form:select path="missCandidate.mcaPosition" cssStyle="background:#FFFFFF">
    									 <form:options items="${missPositionMasterList}" itemLabel="mpmName" itemValue="mpmName"></form:options> 
    							</form:select>
    						</c:if>
    							<c:if test="${paperForm.missCandidate.missAccount.maId!=17}">
    								<form:input path="missCandidate.mcaPosition"/>
    							</c:if>
	    					  &nbsp;<font color="red">*</font>
	    					 </td>
	    					<td align="left" width="10%">Department:</td>
	    					<td align="left" width="29%">
	    					<%-- <form:input path="missCandidate.mcaDepartment"/> --%>
	    					<c:if test="${paperForm.missCandidate.missAccount.maId==17}"> 
    						<form:select path="missCandidate.mcaDepartment" cssStyle="background:#FFFFFF">
    								 <form:options items="${missDepartmentMasterList}" itemLabel="mdmName" itemValue="mdmName"></form:options> 
    						</form:select>
    						</c:if>
    						<c:if test="${paperForm.missCandidate.missAccount.maId!=17}">
    							<form:input path="missCandidate.mcaDepartment"/>
    						</c:if>
	    					&nbsp;<font color="red">*</font></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="7%">&nbsp;</td>
	    					 <td align="left" width="10%">Phone:</td>
	    					 <td align="left" width="29%"><form:input path="missCandidate.mcaPhone"/>&nbsp;<font color="red">*</font>
	    					 </td>
	    					<td align="left" width="10%">Birth date:&nbsp;<font color="red">*</font></td>
	    					<td align="left" width="29%"><div class="picker" id="picker2"></div>
    						<form:hidden path="mcaBirthDate"  id="mcaBirthDate"/></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="7%">&nbsp;</td>
	    					 <td align="left" width="10%">Gender:</td>
	    					 <td align="left" width="29%">  
	    					 <form:radiobutton path="missCandidate.mcaGender" value="0"/>Female&nbsp;&nbsp;&nbsp;<form:radiobutton path="missCandidate.mcaGender" value="1"/>Male 
	    					 &nbsp;<font color="red">*</font></td>
	    					<td align="left" width="10%"></td>
	    					<td align="left" width="29%">
	    					
	    					</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr> 
	    					 
	    					</table>  
	    					</div>
	    					</form:form>
	    					 <div align="center">			
		 
      </div>
      <table style="font-size: 12px;width: 100%"> 
        	<tbody>
        		 
         		 	<tr> 
            			<td align="right" width="50%">
            				<div id="exam_element" align="right" style="display: none">			
		<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px;width: 20%">
        	<thead>
          		<tr> 
            		<th width="10%"><div class="th_class">Series: &nbsp;<span id="sery_element"></span></div></th>  
          		</tr>
        	</thead>
        	<tbody>
        		 
         		 	<tr> 
            			<td>
            			<div align="center" id="exam_element_select">
            			 
            			</div></td> 
          			</tr>
          		 
        	</tbody>
      </table>
      </div>
            			 </td> 
            			 <td align="left" width="50%">
            			 	<div id="set_element" align="left" style="display: none">			
		<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px;width: 20%">
        	<thead>
          		<tr> 
            		<th width="10%"><div class="th_class">Set</div></th>  
          		</tr>
        	</thead>
        	<tbody>
        		 
         		 	<tr> 
            			<td><div align="center">
            			<select id="setExamElement" onchange="setExamElement(this)" style="width: 57px">
            					<option value="1">1</option>	
            					<option value="2">2</option>	
            				</select>
            			</div></td> 
          			</tr>
          		 
        	</tbody>
      </table>
      </div>
            			 </td>
          			</tr>
          		 
        	</tbody>
      </table>
       
	     
       <div align="center">	
       <a class="btn btn-info"  onclick="goBackTestResult()"><i class="icon-chevron-left icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Back</span></a>
       <a id="start_element" style="display: none" class="btn btn-primary" onclick="doStart()" ><span style="color: white;font-weight: bold;">Next&nbsp;<i class="icon-chevron-right icon-white"></i></span></a>
       <!-- <input type="submit" value="AA"> -->
       </div>
</fieldset>  
