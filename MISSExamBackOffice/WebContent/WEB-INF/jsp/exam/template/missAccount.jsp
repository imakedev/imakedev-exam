<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	$('#tabs').tabs();
	$("#maContactBirthDate" ).datepicker({
		showOn: "button",
		buttonImage: _path+"resources/images/calendar.gif",
		buttonImageOnly: true,
		dateFormat:"dd/mm/yy" 
	});
	//alert(parseInt($("#_miss_section").val()))
	$('#tabs').tabs('select', parseInt($("#_miss_section").val()));
	//$tabs.tabs('select', 2);
	/* var $tabs = $('#example').tabs();
	var selected = $tabs.tabs('option', 'selected'); // => 0 */
	/* //getter
	var selected = $( ".selector" ).tabs( "option", "selected" );
	//setter
	$( ".selector" ).tabs( "option", "selected", 3 ); */
});
function doAction(action,formID,sectionID){
	/* $("#mode").val(mode);
	if(mode!='search'){
		$("#msId").val(id);
	}else{
		$("#msId").val("0");
	} */
	//alert(CKEDITOR.instances["maAddress"].getData());
	$("#maAddress").val(CKEDITOR.instances["maAddress"].getData());
	$("#maCustomizePassMessage").val(CKEDITOR.instances["maCustomizePassMessage"].getData());
	$("#maCustomizeRejectMessage").val(CKEDITOR.instances["maCustomizeRejectMessage"].getData());
	$("#maCustomizeRetestMessage").val(CKEDITOR.instances["maCustomizeRetestMessage"].getData());
	//alert($("#maAddress").val());
	//alert(action)
	//$("#_miss_section").val(sectionID);
	$.post("miss/"+action+"/"+sectionID,$("#"+formID).serialize(), function(data) {
		  // alert(data);
		    appendContent(data);
		  // alert($("#_content").html());
		});
  }
</script>
 <div class="alert alert-success" style="${display}">
    <button class="close" data-dismiss="alert"><span style="font-size: 12px">x</span></button>
    <strong>${message}</strong> 
    </div>
<fieldset style="font-family: sans-serif;">  
<input type="hidden" id="_miss_section" name="_miss_section" value="${missForm.missAccount.section}"/>
<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Account</a></li>
				<li><a href="#tabs-2">Profile</a></li>
				<li><a href="#tabs-3">Customize</a></li>
			</ul>
			<div id="tabs-1">
			<!-- <form class="well"> -->
			 <form:form  id="missForm_account" name="missForm_account" modelAttribute="missForm" cssClass="well"  method="post" action="">
			
			  <fieldset style="font-family: sans-serif;">   
	     <h6><strong>Miss - Account</strong></h6> 
			    <table border="0" width="100%" style="font-size: 12px">
   		 			<tr>
    					<td width="25%">&nbsp;</td>
    					<td width="50%" colspan="2">Username&nbsp;&nbsp;:&nbsp;&nbsp;admin</td>
    					 <form:hidden path="missAccount.maUsername" id="maUsername"/>
    					<td width="25%">&nbsp;</td>
    				</tr>
    				<tr>
    					<td width="25%">&nbsp;</td>
    					<td width="50%" colspan="2">Password&nbsp;&nbsp;:&nbsp;&nbsp;
    					<!-- <input type="password"/> -->
    					<form:password path="missAccount.maPassword" id="maPassword"/></td>
    					  					
    					<td width="25%">&nbsp;</td>
    				</tr>
    				<tr>
    					<td colspan="4" align="center">
    					<%--
    					<input type="button" class="btn" value="Save"/>
    					<div align="center"><a class="btn">&nbsp;Save</a></div>
    					 --%>
    				
    					</td> 
    					
    				</tr>
    				 
    			</table>    
    			</fieldset>			
			</form:form>
				<div align="center"><a class="btn btn-primary" onclick="doAction('action','missForm_account','0')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a></div>
			<%--
			<div align="center"><button type="button">Save</button></div>
			<div align="center"><a class="btn">&nbsp;Save</a></div>
			<div align="center"><a class="btn btn-primary"><i class="icon-plus-sign icon-white"></i>&nbsp;<span style="color: white;">Save</span></a></div>
			<div align="center"><a class="btn btn-success"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;">Save</span></a></div>
			 --%>
			    </div>
			<div id="tabs-2">
		 <form:form  id="missForm_profile" name="missForm_profile" modelAttribute="missForm" method="post" cssClass="well" action="">
		<!--   <form class="well"> -->
		
			  <fieldset style="font-family: sans-serif;">  
			    <h6><strong>Miss - Profile</strong></h6>  
			  <pre  class="prettyprint" style="font-family: sans-serif;font-size:12px:;margin-top: 0px">
	   
			    <table border="0" width="100%" style="font-size: 12px">
			    	<tr>
    					<td width="100%" colspan="4"><strong>Company Profile</strong></td>
    				</tr>
   		 			<tr valign="top">
    					<td width="25%">Name:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maName" id="maName"/>
    				<!-- 	<input type="text" width="100%" /> -->
    					</td>
    					 <td width="25%" align="right" rowspan="2"><img src="<c:url value='/resources/images/photo.png'/>"/><div align="right"><input type="button" value="Upload"></div></td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Address:</td>
    					<td width="50%" colspan="2"><form:textarea path="missAccount.maAddress" cols="4" rows="4" id="maAddress"/>
    					<script>
    					if (CKEDITOR.instances['maAddress']) {
    			            CKEDITOR.remove(CKEDITOR.instances['maAddress']);
    			         }
    					CKEDITOR.replace( 'maAddress',
    						    {
    						        toolbar : 'Basic',
    						      //  uiColor : '#9AB8F3'
    						    });
    					</script>
    					</td>
    					<!--  <td width="25%">&nbsp;</td> -->
    				</tr>
    				<tr valign="top">
    					<td width="25%">Phone:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maPhone" id="maPhone"/>
    					<!-- <input type="text" width="100%" /> -->
    					</td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Fax:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maFax" id="maFax"/>
    					<!-- <input type="text" width="100%" /> -->
    					</td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Email:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maEmail" id="maEmail"/>
    					<!-- <input type="text" width="100%" /> -->
    					</td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				 
    			</table>
    			</pre>
    			  </fieldset>
    			  <!-- </form> -->
			<%-- </form:form> --%>
				<!-- <form class="well"> -->
				 <%-- <form:form  id="missForm_profile2" name="missForm_profile2" modelAttribute="missForm" cssClass="well"  method="post" action=""> --%>
			<!--  <form class="well"> -->
			
			  <fieldset style="font-family: sans-serif;">   
			  <pre  class="prettyprint" style="font-family: sans-serif;font-size:12px:;margin-top: 0px">
	    <!--  <legend><h6><strong>Miss - Account</strong></h6></legend>  -->
			    <table border="0" width="100%" style="font-size: 12px">
			    	<tr>
    					<td width="100%" colspan="4"><strong>Contct Point Profile</strong></td>
    				</tr>
   		 			<tr valign="top">
    					<td width="25%">First-Lastname:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactName" id="maContactName" cssStyle="width:120px"/>
    					<!-- <input type="text" style="width: 120px" /> -->
    					&nbsp;
    					<form:input path="missAccount.maContactLastname" id="maContactLastname" cssStyle="width:120px"/>
    					<!-- <input type="text" style="width: 120px" /> -->
    					</td>
    					 <td width="25%"  align="right"  rowspan="8"><img src="<c:url value='/resources/images/photo.png'/>"/><div align="right"><input type="button" value="Upload"></div></td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Gender:</td>
    					<td width="50%" colspan="2">
    					<form:radiobutton path="missAccount.maContactGender" value="0"/>Female&nbsp;&nbsp;&nbsp;<form:radiobutton path="missAccount.maContactGender" value="1"/>Male
    					<!-- <input type="radio" name="sex"/>Female&nbsp;&nbsp;&nbsp;<input type="radio" name="sex">Male -->
    					</td>
    					<!--  <td width="25%">&nbsp;</td> -->
    				</tr>
    				<tr valign="top">
    					<td width="25%">Birth Date:</td>
    					<td width="50%" colspan="2">
    					<form:input path="maContactBirthDate" id="maContactBirthDate" cssStyle="width: 75px"/>
    					<!-- <input type="text"  id="datepicker" style="width: 75px"/> -->
    					
    					</td>
    					<!--  <td width="25%">&nbsp;</td> -->
    				</tr>
    				<tr valign="top">
    					<td width="25%">Title:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactTitle" id="maContactTitle"/>
    					<!-- <input type="text" width="100%" /> -->
    					</td>
    					<!--  <td width="25%">&nbsp;</td> -->
    				</tr>
    				<tr valign="top">
    					<td width="25%">Department:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactDepartment" id="maContactDepartment"/>
    					<!-- <input type="text" width="100%" /> -->
    					</td>
    					<!--  <td width="25%">&nbsp;</td> -->
    				</tr>
    				 <tr valign="top">
    					<td width="25%">Phone:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactPhone" id="maContactPhone"/>
    				<!-- 	<input type="text" width="100%"  -->
    					</td>
    					<!-- <td width="25%">&nbsp;</td> --> 
    				</tr>
    				 <tr valign="top">
    					<td width="25%">Fax:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactFax" id="maContactFax"/>
    					<!-- <input type="text" width="100%" /> -->
    					</td>
    					<!--  <td width="25%">&nbsp;</td> -->
    				</tr>
    				 <tr valign="top">
    					<td width="25%">Email:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactEmail" id="maContactEmail"/>
    					<!-- <input type="text" width="100%" /> -->
    					</td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    			</table>
    			</pre>
    			</fieldset>
    			
    			<!-- </form> -->
			  </form:form> 
			<%--
			<div align="center"><input type="button" class="btn" value="Save"/></div>
			 --%>
			<div align="center"><a class="btn btn-primary" onclick="doAction('action','missForm_profile','1')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a></div>
			</div>
			<div id="tabs-3">
				<!-- <form class="well"> -->
				 <form:form  id="missForm_customize" name="missForm_customize" modelAttribute="missForm" cssClass="well"  method="post" action="">
			  <fieldset style="font-family: sans-serif;">   
	      <h6><strong>Miss - Customize</strong></h6> 
			    <table border="0" width="100%" style="font-size: 12px">
			    	<!-- <tr>
    					<td width="100%" colspan="4"><strong>MissConsult Customize</strong></td>
    				</tr> -->
   		 			<tr valign="top">
    					<td width="25%">Logo:</td>
    					<td width="50%" colspan="2"><img src="<c:url value='/resources/images/logowebmc.jpg'/>"/><div><input type="button" value="browse"/></div></td>
    					
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Theme Color:</td>
    					<td width="50%" colspan="2"><select name="bpsGroupId" id="bpgGroupId"> 
											 <option value="0">Blue</option>
											 <option value="20">Red</option>
	    					</select>&nbsp;&nbsp;&nbsp;<select name="bpsGroupId" id="bpgGroupId"> 
											 <option value="0">Green</option>
											 <option value="20">Gray</option>
	    					</select>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Background:</td>
    					<td width="50%" colspan="2"><img src=""/>&nbsp;&nbsp;&nbsp;<select name="bpsGroupId" id="bpgGroupId"> 
											 <option value="0">Green</option>
											 <option value="20">Gray</option>
	    					</select></td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				<tr>
    					<td width="100%" colspan="4"><strong>Response Candidate</strong></td>
    				</tr>
    				<tr>
    					<td width="100%" colspan="4">Pass Message</td>
    				</tr>
    				<tr>
    					<td width="100%" colspan="4"><form:textarea path="missAccount.maCustomizePassMessage" cols="4" rows="4" id="maCustomizePassMessage"/>
    					<%-- <c:out value="" escapeXml="false" /> --%>
    					<script>
    					if (CKEDITOR.instances['maCustomizePassMessage']) {
    			            CKEDITOR.remove(CKEDITOR.instances['maCustomizePassMessage']);
    			         }
    					CKEDITOR.replace( 'maCustomizePassMessage',
    						    {
    						        toolbar : 'Basic',
    						      //  uiColor : '#9AB8F3'
    						    });
    					</script>
    					</td>
    				</tr>
    				<tr>
    					<td width="100%" colspan="4">Reject Message</td>
    				</tr>
    				<tr>
    					<td width="100%" colspan="4"><form:textarea path="missAccount.maCustomizeRejectMessage" cols="4" rows="4" id="maCustomizeRejectMessage"/>
    					<script>
    					if (CKEDITOR.instances['maCustomizeRejectMessage']) {
    			            CKEDITOR.remove(CKEDITOR.instances['maCustomizeRejectMessage']);
    			         }
    					CKEDITOR.replace( 'maCustomizeRejectMessage',
    						    {
    						        toolbar : 'Basic',
    						      //  uiColor : '#9AB8F3'
    						    });
    					</script>
    					</td>
    				</tr>
    				<tr>
    					<td width="100%" colspan="4">Retest Message</td>
    				</tr>
    				<tr>
    					<td width="100%" colspan="4"><form:textarea path="missAccount.maCustomizeRetestMessage" cols="4" rows="4" id="maCustomizeRetestMessage"/>
    					<script>
    					if (CKEDITOR.instances['maCustomizeRetestMessage']) {
    			            CKEDITOR.remove(CKEDITOR.instances['maCustomizeRetestMessage']);
    			         }
    					CKEDITOR.replace( 'maCustomizeRetestMessage',
    						    {
    						        toolbar : 'Basic',
    						      //  uiColor : '#9AB8F3'
    						    });
    					</script>
    					</td>
    				</tr>
    			</table>
    			</fieldset>
			</form:form>
			<!-- <div align="center"><input type="button" class="btn" value="Save"/></div> -->
			<div align="center"><a class="btn btn-primary" onclick="doAction('action','missForm_customize','2')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a></div>
			
			</div>
			
		</div>
		</fieldset>
	  