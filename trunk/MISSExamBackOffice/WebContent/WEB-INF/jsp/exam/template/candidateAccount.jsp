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
	$('#tabs').tabs('select', parseInt($("#_candidate_section").val()));
});
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
			<div id="tabs-1">
			<!-- <form class="well"> -->
			<form:form  id="candidateForm_account" name="candidateForm_account" modelAttribute="candidateForm" cssClass="well"  method="post" action="">
			  <fieldset style="font-family: sans-serif;">   
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
    					
    					<form:select path="missCandidate.missSery.msId">
    						 <form:option value="-1">-- Select Series --</form:option>
    						 <form:options items="${missSeries}" itemLabel="msSeriesName" itemValue="msId"></form:options>
	    					     
    					</form:select>
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
    			</fieldset>			
			</form:form>
			<div align="center"><a class="btn btn-primary" onclick="doAction('action','candidateForm_account','0')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a>&nbsp;
			<%--
			<a class="btn btn-danger"><i class="icon-trash icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Delete</span></a>&nbsp;
			 --%>
			<a class="btn btn-info"><i class="icon-refresh icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Reactivate</span></a>&nbsp;</div>
			    </div>
			<div id="tabs-2">
			<!-- <form class="well"> -->
			<form:form  id="candidateForm_profile" name="candidateForm_profile" modelAttribute="candidateForm" cssClass="well"  method="post" action="">
			  <fieldset style="font-family: sans-serif;">   
	      <h6><strong>Candidate - Profile</strong></h6> 
	      <pre  class="prettyprint" style="font-family: sans-serif;font-size:12px:;margin-top: 0px">
			    <table border="0" width="100%" style="font-size: 12px">
			    	<tr valign="top">
    					<td width="25%">Account Type:</td>
    					<td width="50%" colspan="2">
    					 	<form:radiobutton path="missCandidate.mcaType" value="1"/>New Recruit&nbsp;&nbsp;&nbsp;<form:radiobutton path="missCandidate.mcaType" value="2"/>Current Employee
    				<!-- 	<input type="radio" name="type"/>New Recruit&nbsp;&nbsp;&nbsp;<input type="radio" name="type">Current Employee -->
    				</td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    			</table>
    			</pre>
    			</fieldset>
			<!-- </form> -->
		<!-- 	<form class="well"> -->
		<pre  class="prettyprint" style="font-family: sans-serif;font-size:12px:;margin-top: 0px">
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
    					 <td width="25%" align="right" rowspan="9"><img src="<c:url value='/resources/images/photo.png'/>"/><div align="center"><input type="button" value="Upload"></div></td>
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
    					<!-- <input type="text" style="width: 120px" /> -->
    					<form:input path="missCandidate.mcaFirstName"/>
    					&nbsp;
    					<!-- <input type="text" style="width: 120px" /> -->
    					<form:input path="missCandidate.mcaLastName"/>
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
    					<td width="25%">Title:</td>
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
    				 
    			</table>
    			</pre>
			</form:form>
			
			<!-- <div align="center"><input type="button" class="btn" value="Save"/></div> -->
				<div align="center"><a class="btn btn-primary"  onclick="doAction('action','candidateForm_profile','1')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a></div>
			</div>
			
		</div>