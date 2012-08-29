<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	$('#tabs').tabs();
	$("#mcaBirthDate" ).datepicker({
		showOn: "button",
		buttonImage: _path+"resources/images/calendar.gif",
		buttonImageOnly: true,
		dateFormat:"dd/mm/yy" 
	});
	var _candidate_section=$("#_candidate_section").val().length>0?parseInt($("#_candidate_section").val()):0;
	if(_candidate_section==2)
		_candidate_section=0;
	$('#tabs').tabs('select', _candidate_section);
	new AjaxUpload('candidate_upload', {
        action: 'upload/candidateImg/${candidateForm.missCandidate.mcaId}',
		onSubmit : function(file , ext){
            // Allow only images. You should add security check on the server-side.
			if (ext && /^(jpg|png|jpeg|gif)$/.test(ext)){
				/* Setting data */
				this.setData({
					'key': 'This string will be send with the file',
					'test':'chatchai'
				});					 
			$('#candidate_photo').attr('src', _path+"resources/images/loading.gif");
			} else {					
				// extension is not allowed
				alert('Error: only images are allowed') ;
				// cancel upload
				return false;				
			}		
		},
		onComplete : function(file, response){
			//alert(response)
			var obj = jQuery.parseJSON(response);
			$("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+obj.hotlink);
			//$('#example2 .text').text('Uploaded ' + file);		
			//alert(file);
			//alert(response)
		
		}		
	});
});
function doReactivate(action,formID,sectionID){
	//$("#mode").val("reactivate");
	doAction(action,formID,sectionID);
}
function doAction(action,formID,sectionID){
	//alert($("#maCustomizePassMessage").val());
	/* $("#maAddress").val(CKEDITOR.instances["maAddress"].getData());
	$("#maCustomizePassMessage").val(CKEDITOR.instances["maCustomizePassMessage"].getData());
	$("#maCustomizeRejectMessage").val(CKEDITOR.instances["maCustomizeRejectMessage"].getData());
	$("#maCustomizeRetestMessage").val(CKEDITOR.instances["maCustomizeRetestMessage"].getData()); */
	//alert($("#maCustomizePassMessage").val());
	//$("#_miss_section").val(sectionID);
	$.post("candidate/"+action+"/"+sectionID,$("#"+formID).serialize(), function(data) {
		    appendContent(data);
		});
  }
</script> 
 <div class="alert alert-success" style="${display}">
    <button class="close" data-dismiss="alert"><span style="font-size: 12px">x</span></button>
    <strong>${message}</strong> 
  </div>
   <input type="hidden" id="_candidate_section" name="_candidate_section" value="${candidateForm.missCandidate.section}"/>
            <div id="tabs">
			<ul>
				<li><a href="#tabs-1">Account</a></li>
				<li><a href="#tabs-2">Profile</a></li>
			</ul>
			<div id="tabs-1" style="background: url(<c:url value='/resources/images/${UserMissContact.missTheme.mtWaterWall}'/>) no-repeat scroll right top ${UserMissContact.missTheme.mtBgColor}">
			<!-- <form class="well"> -->
			<form:form  id="candidateForm_account" name="candidateForm_account" modelAttribute="candidateForm"  method="post" action="">
			 <form:hidden path="mode"></form:hidden>
			  <!-- <fieldset style="font-family: sans-serif;"> -->   
	      <h6><strong>Candidate - Account</strong></h6> 
			    <table border="0" width="100%" style="font-size: 12px">
   		 			<tr>
    					<td width="10%">&nbsp;</td>
    					<td width="10%" align="right">Username :</td>
    					 <td width="55%" >
    					 <form:input path="missCandidate.mcaUsername"/>
    					 <!-- <input type="text"/> -->
    					 </td>
    					<td width="25%">&nbsp;</td>
    				</tr>
    				<tr>
    					<td width="10%">&nbsp;</td>
    					<td width="10%" align="right">Password :</td>
    					<td width="55%">
    					 <form:input path="missCandidate.mcaPassword"/>
    					<!-- <input type="password"/> -->
    					</td>
    					  					
    					<td width="25%">&nbsp;</td>
    				</tr>
    				<tr>
    					<td width="10%">&nbsp;</td>
    					<td width="10%" align="right">Series :</td>
    					<td width="55%">
    					<c:if test="${candidateForm.missCandidate.mcaStatus=='1'}">
    					<form:select path="missCandidate.missSery.msId" disabled="true">
    						<%--  <form:option value="-1">-- Select Series --</form:option> --%>
    						 <form:options items="${missSeries}" itemLabel="msSeriesName" itemValue="msId"></form:options>
	    					     
    					</form:select>
    					</c:if>
    					<c:if test="${candidateForm.missCandidate.mcaStatus!='1'}">
    					<form:select path="missCandidate.missSery.msId">
    						<%--  <form:option value="-1">-- Select Series --</form:option> --%>
    						 <form:options items="${missSeries}" itemLabel="msSeriesName" itemValue="msId"></form:options>
	    					     
    					</form:select>
    					</c:if>
    					<!-- <select name="bpsGroupId" id="bpgGroupId"> 
											 <option value="0">Series1</option>
											 <option value="2">Series2</option>
	    					</select> -->
	    				</td>
    					  					
    					<td width="25%">&nbsp;</td>
    				</tr>
    				<tr>
    					<td colspan="4" align="center">
    					<!-- <input type="button" class="btn" value="Save"/>&nbsp;
    					<input type="button" class="btn" value="Delete"/>&nbsp;
    					<input type="button" class="btn" value="Reactivate"/>&nbsp; -->
    					</td> 
    				</tr>
    				 
    			</table>    
    			<!-- </fieldset>	 -->		
			</form:form>
			<div align="center"><a class="btn btn-primary" onclick="doAction('action','candidateForm_account','0')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a>&nbsp;
			<%--
			<a class="btn btn-danger"><i class="icon-trash icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Delete</span></a>&nbsp;
			 --%>
			<a class="btn btn-info"  onclick="doReactivate('action','candidateForm_account','2')"><i class="icon-refresh icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Reactivate</span></a>&nbsp;</div>
			    </div>
			<div id="tabs-2" style="background: url(<c:url value='/resources/images/${UserMissContact.missTheme.mtWaterWall}'/>) no-repeat scroll right top ${UserMissContact.missTheme.mtBgColor}">
			<!-- <form class="well"> -->
			<form:form  id="candidateForm_profile" name="candidateForm_profile" modelAttribute="candidateForm"    method="post" action="">
			 <!--  <fieldset style="font-family: sans-serif;">    -->
	      <h6><strong>Candidate - Profile</strong></h6> 
	      
	   <!--    <pre  class="prettyprint" style="font-family: sans-serif;font-size:12px;margin-top: 0px"> -->
			    <table border="0" width="100%" style="font-size: 12px">
			    	<tr>
    					<td width="100%" colspan="3"><strong> </strong></td>
    				</tr>
			    	<tr valign="top">
    					<td width="25%">Account Type:</td>
    					<td width="50%" colspan="2">
    					 	<form:radiobutton path="missCandidate.mcaType" value="1"/>New Recruit&nbsp;&nbsp;&nbsp;<form:radiobutton path="missCandidate.mcaType" value="2"/>Current Employee
    				<!-- 	<input type="radio" name="type"/>New Recruit&nbsp;&nbsp;&nbsp;<input type="radio" name="type">Current Employee -->
    				</td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    			</table>
    			<br></br>
    			<!-- </pre> -->
    			<!-- </fieldset> -->
			<!-- </form> -->
		<!-- 	<form class="well"> -->
		<!-- <pre  class="prettyprint" style="font-family: sans-serif;font-size:12px:;margin-top: 0px"> -->
			    <table border="0" width="100%" style="font-size: 12px">
			    	<tr>
    					<td width="100%" colspan="4"><strong>Candidate Profile</strong></td>
    				</tr>
   		 			<tr valign="top">
    					<td width="25%">Citizen ID:</td>
    					<td width="50%" colspan="2">
    					<!-- <input type="text" width="100%" /> -->
    					 <form:input path="missCandidate.mcaCitizenId"/>
    					</td>
    					 <td width="25%" align="right" rowspan="9">
    					  <c:if test="${not empty candidateForm.missCandidate.mcaPictureHotlink}"> 
						 	<img id="candidate_photo" width="128" height="128" src="getfile/candidateImg/${candidateForm.missCandidate.mcaId}/${candidateForm.missCandidate.mcaPictureHotlink}" />
						 </c:if>
						 <c:if test="${empty candidateForm.missCandidate.mcaPictureHotlink}"> 
						 	<img id="candidate_photo" width="128"  height="128" src="<c:url value='/resources/images/photo.png'/>" />
						 </c:if>
    					 <div align="right">
    					<!--  <input type="button" id="candidate_photo" value="Upload"> -->
    					  <!-- <a id="candidate_upload" class="btn btn-mini"><i class="icon-picture"></i>&nbsp;Upload</a> -->
    					  <input  id="candidate_upload" type="button" value="Upload">
    					 </div>
    					  <div align="right">(128px × 128px)</div>
    					  </td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Email:</td>
    					<td width="50%" colspan="2">
    					<!-- <input type="text" width="100%" /> -->
    					<form:input path="missCandidate.mcaEmail"/>
    					</td>
    					 <!-- <td width="25%">&nbsp;</td> -->
    				</tr>
    				<tr valign="top">
    					<td width="25%">&nbsp;</td>
    					<td width="50%" colspan="2" align="left"><input type="button" value="Search" /></td>
    					 <!-- <td width="25%">&nbsp;</td> -->
    				</tr>
    				<tr valign="top">
    					<td width="25%">First-Lastname:</td>
    					<td width="50%" colspan="2">
    					<form:select path="missCandidate.mcaTitleType" cssStyle="width:70px;">
    						<form:option value="0">นาย</form:option>
    						<form:option value="1">นาง</form:option>
    						<form:option value="2">นางสาว</form:option>
    						<form:option value="3">ระบุ 	&rarr;</form:option>
    					</form:select>
    					<!-- <select style="width: 50px" > 
    					<option value="0">นาย</option>
    					<option  value="1">นาง</option>
    					<option  value="2">นางสาว</option>
    					<option  value="3">อื่นๆ</option>
    					</select> -->
    					<!-- <input type="text" style="width: 120px" /> -->
    					<form:input path="missCandidate.mcaFirstName" cssStyle="width:120px;bgcolor:#FFFFFF"/>
    					&nbsp;
    					<!-- <input type="text" style="width: 120px" /> -->
    					<form:input path="missCandidate.mcaLastName" cssStyle="width:120px"/>
    					</td>
    					 <!-- <td width="25%">&nbsp;</td> -->
    				</tr>
    				<tr valign="top">
    					<td width="25%">Gender:</td>
    					<td width="50%" colspan="2">
    					<!-- <input type="radio" name="sex"/>Female&nbsp;&nbsp;&nbsp;<input type="radio" name="sex">Male -->
    					<form:radiobutton path="missCandidate.mcaGender" value="0"/>Female&nbsp;&nbsp;&nbsp;<form:radiobutton path="missCandidate.mcaGender" value="1"/>Male
    					</td>
    					 <!-- <td width="25%">&nbsp;</td> -->
    				</tr>
    				<tr valign="top">
    					<td width="25%">Birth Date:</td>
    					<td width="50%" colspan="2">
    					<!-- <input type="text" id="datepicker" style="width: 75px"/> -->
    					<form:input path="mcaBirthDate"  id="mcaBirthDate" cssStyle="width: 75px"/>
    					</td>
    					 <!-- <td width="25%">&nbsp;</td> -->
    				</tr>
    				<tr valign="top">
    					<td width="25%">Position:</td>
    					<td width="50%" colspan="2">
    					<!-- <input type="text" width="100%" /> -->
    					<form:input path="missCandidate.mcaTitle"/>
    					</td>
    					 <!-- <td width="25%">&nbsp;</td> -->
    				</tr>
    				<tr valign="top">
    					<td width="25%">Department:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missCandidate.mcaDepartment"/>
    				   <!-- <input type="text" width="100%" /> -->
    					</td>
    					 <!-- <td width="25%">&nbsp;</td> -->
    				</tr>
    				 <tr valign="top">
    					<td width="25%">Phone:</td>
    					<td width="50%" colspan="2">
    					<!-- <input type="text" width="100%" /> -->
    					<form:input path="missCandidate.mcaPhone"/>
    					</td>
    					 <!-- <td width="25%">&nbsp;</td> -->
    				</tr>
    				<tr valign="top">
    					<td width="25%">Industry Type:</td>
    					<td width="50%" colspan="2">
    					 <form:select path="missCandidate.missIndustryMaster.mimId" cssStyle="background:#FFFFFF">    					 
    						<form:options items="${missIndustryMasterList}" itemLabel="mimName" itemValue="mimId"></form:options>
    					</form:select>
    					</td>
    				</tr>
    				 <tr valign="top">
    					<td width="25%">Career Type:</td>
    					<td width="50%" colspan="2">
    					 <form:select path="missCandidate.missCareerMaster.mcmId" cssStyle="background:#FFFFFF">
    						<form:options items="${missCareerMasterList}" itemLabel="mcmName" itemValue="mcmId"></form:options>
    					</form:select>
    					</td>
    				</tr>
    				 
    			</table>
    		<!-- 	</pre> -->
			</form:form>
			
			<!-- <div align="center"><input type="button" class="btn" value="Save"/></div> -->
				<div align="center"><a class="btn btn-primary"  onclick="doAction('action','candidateForm_profile','1')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a></div>
			</div>
			
		</div>