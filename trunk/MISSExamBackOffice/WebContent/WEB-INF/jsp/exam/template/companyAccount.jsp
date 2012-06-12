<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	$('#tabs').tabs();
	$('#tabs').bind('tabsselect', function(event, ui) {
		if(ui.index==2){
			 // /exam/{meId}/questions
			// alert("test/exam/"+$("#_meId").val()+"/questions");
			 if($("#_maId").val().length>0){
			  $.ajax({
				  type: "get",
				  url: "company/account/"+$("#_maId").val()+"/contacts",
				  cache: false
				 // data: { name: "John", location: "Boston" }
				}).done(function( data ) {
					if(data!=null){
						appendContentWithId(data,"tabs-3")
						// $("#tabs-3").html(data);
					  }
				});
			 }
		   }else{
			   $("#tabs-3").html("");
		   }
		});
	/* $("#maContactBirthDate" ).datepicker({
		showOn: "button",
		buttonImage: _path+"resources/images/calendar.gif",
		buttonImageOnly: true,
		dateFormat:"dd/mm/yy" 
	}); */
	var _company_section=$("#_company_section").val().length>0?parseInt($("#_company_section").val()):4;
	//$('#tabs').tabs('select', parseInt($("#_company_section").val())-3);
	$('#tabs').tabs('select', _company_section-4);
	
	new AjaxUpload('company_upload', {
		 action: 'upload/companyLogo/${companyForm.missAccount.maId}',
		onSubmit : function(file , ext){
            // Allow only images. You should add security check on the server-side.
			if (ext && /^(jpg|png|jpeg|gif)$/.test(ext)){
				this.setData({
					'key': 'This string will be send with the file',
					'test':'chatchai'
				});					
				$('#company_photo').attr('src', _path+"resources/images/loading.gif");
			} else {					
				// extension is not allowed
				alert('Error: only images are allowed') ;
				// cancel upload
				return false;				
			}		
		},
		onComplete : function(file, response){
			$("#company_photo").attr("src","getfile/companyLogo/${companyForm.missAccount.maId}/"+response);
		}		
	});
	 
});
function doAction(action,formID,sectionID){
	//alert($("#maCustomizePassMessage").val());
	$("#maAddress").val(CKEDITOR.instances["maAddress"].getData());
	$("#maCustomizePassMessage").val(CKEDITOR.instances["maCustomizePassMessage"].getData());
	$("#maCustomizeRejectMessage").val(CKEDITOR.instances["maCustomizeRejectMessage"].getData());
	$("#maCustomizeRetestMessage").val(CKEDITOR.instances["maCustomizeRetestMessage"].getData());
	//alert($("#maCustomizePassMessage").val());
	//$("#_miss_section").val(sectionID);
	$.post("company/"+action+"/"+sectionID,$("#"+formID).serialize(), function(data) {
		    appendContent(data);
		});
  }
</script>
 <div class="alert alert-success" style="${display}">
    <button class="close" data-dismiss="alert"><span style="font-size: 12px">x</span></button>
    <strong>${message}</strong> 
  </div>
   <input type="hidden" id="_maId" name="_maId" value="${companyForm.missAccount.maId}"/>
   <input type="hidden" id="_company_section" name="_company_section" value="${companyForm.missAccount.section}"/>
            <div id="tabs">
			<ul>
				<li><a href="#tabs-1">Account</a></li>
				<li><a href="#tabs-2">Profile</a></li>
				<li><a href="#tabs-3">Contact</a></li>
				<li><a href="#tabs-4">Unit</a></li>
				<li><a href="#tabs-5">Customize</a></li>
			</ul>
			<div id="tabs-1">
			<!-- <form class="well"> -->
			<form:form  id="companyForm_account" name="companyForm_account" modelAttribute="companyForm" cssClass="well"  method="post" action="">
			  <fieldset style="font-family: sans-serif;">   
	     <h6><strong>Company - Account</strong></h6> 
			    <table border="0" width="100%" style="font-size: 12px">
   		 			<tr>
    					<td width="25%">&nbsp;</td>
    					<td width="50%" colspan="2">Username&nbsp;&nbsp;:&nbsp;&nbsp;
    					<!-- <input type="text" value="Moooo1" > -->
    					<form:input path="missAccount.maUsername"/>
    					</td>
    					 
    					<td width="25%">&nbsp;</td>
    				</tr>
    				<tr>
    					<td width="25%">&nbsp;</td>
    					<td width="50%" colspan="2">Password&nbsp;&nbsp;:&nbsp;&nbsp;
    					<!-- <input type="password" value="1111"/> -->
    						<form:password path="missAccount.maPassword"/>
    					</td>
    					  					
    					<td width="25%">&nbsp;</td>
    				</tr>
    				<tr>
    					<td colspan="4" align="center">
    					<!-- <input type="button" class="btn" value="Save"/> -->
    					</td> 
    				</tr>
    				 
    			</table>    
    			</fieldset>			
			</form:form>
			<div align="center"><a class="btn btn-primary"  onclick="doAction('action','companyForm_account','4')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a></div>
			    </div>
			<div id="tabs-2">
			<!-- <form class="well"> -->
			<form:form  id="companyForm_profile" name="companyForm_profile" modelAttribute="companyForm" cssClass="well"  method="post" action="">
			  <fieldset style="font-family: sans-serif;">   
	     <h6><strong>Company - Profile</strong></h6> 
	       <pre  class="prettyprint" style="font-family: sans-serif;font-size:12px:;margin-top: 0px">
			    <table border="0" width="100%" style="font-size: 12px">
			    	<tr>
    					<td width="100%" colspan="4"><strong>Company Profile</strong></td>
    				</tr>
   		 			<tr valign="top">
    					<td width="25%">Name:</td>
    					<td width="50%" colspan="2">
    					<!-- <input type="text" width="100%" /> -->
    					<form:input path="missAccount.maName"/>
    					</td>
    					 <td width="25%" align="right" rowspan="2"></td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Address:</td>
    					<td width="50%" colspan="2">
    					<form:textarea path="missAccount.maAddress" cols="4" rows="4" id="maAddress"/>
    					<!-- <textarea cols="4" rows="4" id="company_editor11"></textarea> -->
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
    					<!-- <input type="text" width="100%" /> -->
    					<form:input path="missAccount.maPhone"/>
    					</td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Fax:</td>
    					<td width="50%" colspan="2">
    					<!-- <input type="text" width="100%" /> -->
    					<form:input path="missAccount.maFax"/>
    					</td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Email:</td>
    					<td width="50%" colspan="2">
    					<!-- <input type="text" width="100%" /> -->
    					<form:input path="missAccount.maEmail"/>
    					</td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				 
    			</table>
    				</pre>
    			</fieldset>
		<!-- 	</form>
			<form class="well"> -->
			<%-- 
			 <pre  class="prettyprint" style="font-family: sans-serif;font-size:12px:;margin-top: 0px">
			    <table border="0" width="100%" style="font-size: 12px">
			    	<tr>
    					<td width="100%" colspan="4"><strong>Contct Point Profile</strong></td>
    				</tr>
   		 			<tr valign="top">
    					<td width="25%">First-Lastname:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactName" cssStyle="width:120px"/>
    					&nbsp;
    					<form:input path="missAccount.maContactLastname" cssStyle="width:120px"/>
    					</td>
    					 <td width="25%" align="right" rowspan="8"><img src="<c:url value='/resources/images/photo.png'/>"/>
    					 <div align="right">
    					 <a id="company_photo" class="btn btn-mini"><i class="icon-picture"></i>&nbsp;Upload</a>
    					 </div></td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Gender:</td>
    					<td width="50%" colspan="2">
    					<form:radiobutton path="missAccount.maContactGender" value="0"/>Female&nbsp;&nbsp;&nbsp;<form:radiobutton path="missAccount.maContactGender" value="1"/>Male
    					</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Birth Date:</td>
    					<td width="50%" colspan="2">
    					<form:input path="maContactBirthDate"  id="maContactBirthDate" cssStyle="width: 75px"/>
    					</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Position:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactTitle"/>
    					</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Department:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactDepartment"/>
    					</td>
    				</tr>
    				 <tr valign="top">
    					<td width="25%">Phone:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactPhone"/>
    					</td>
    				</tr>
    				 <tr valign="top">
    					<td width="25%">Fax:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactFax"/>
    					</td>
    				</tr>
    				 <tr valign="top">
    					<td width="25%">Email:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missAccount.maContactEmail"/>
    					</td>
    				</tr>
    			</table>
    			</pre>
    			--%> 
			<!-- </form> -->
			  </form:form> 
			<!-- <div align="center"><input type="button" class="btn" value="Save"/></div> -->
			<div align="center"><a class="btn btn-primary" onclick="doAction('action','companyForm_profile','5')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a></div>
			</div>
			<div id="tabs-3">
    		
    			</div>
			<div id="tabs-4">
			<!-- <form class="well"> -->
			<form:form  id="companyForm_unit" name="companyForm_unit" modelAttribute="companyForm" cssClass="well"  method="post" action="">
			  <fieldset style="font-family: sans-serif;">   
	     <h6><strong>Company - Unit</strong></h6> 
			   <div>
			    <table border="0" width="100%" style="font-size: 12px">
			    	<!-- <tr>
    					<td width="100%" colspan="3"><strong>Company Unit</strong></td>
    				</tr> -->
   		 			<tr valign="top">
    					<td width="20%">&nbsp;</td>
    					<td width="60%">Total Unit: 100</td>
    					 <td width="20%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="20%">&nbsp;</td>
    					<td width="60%">Used Unit: 100</td>
    					 <td width="20%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="20%">&nbsp;</td>
    					<td width="60%">Available Unit: 100</td>
    					 <td width="20%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="20%">&nbsp;</td>
    					<td width="60%">Re-fill <input type="text" /> <input type="button" value="Re-fill"></td>
    					 <td width="20%">&nbsp;</td>
    				</tr>
    				
    			</table>
    			 
    			</div>
    			 </fieldset>
    			 </form:form>
    			<div>
    			 <table class="table table-striped table-bordered table-condensed" border="0" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="15%"><div class="th_class">Series</div></th> 
            		<th width="60%"><div class="th_class">Group</div></th>
            		<th width="5%"><div class="th_class">Unit</div></th> 
            		<th width="10%"><div class="th_class">Available</div></th>
            		<th width="10%"><div class="th_class">Order</div></th> 
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>Series1</td>
            	<td>Personality Aptitude</td>
            	<td>2</td>
            	<td>20</td>
            	<td><input type="text"></td> 
          	</tr>
          	<tr>
            	<td>Series2</td>
            	<td>Motivation Management</td>
            	<td>2</td>
            	<td>20</td>
            	<td><input type="text"></td> 
          	</tr>
          	<tr>
            	<td>Survey</td>
            	<td>Survey</td>
            	<td>2</td>
            	<td>30</td>
            	<td><input type="text"></td> 
          	</tr>
    				</table>
    				</div>
			
			<!-- <div align="center"><input type="button" class="btn" value="Order"/></div> -->
			<div align="center"><a class="btn btn-primary" onclick="doAction('action','companyForm_unit','7')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Order</span></a></div>
			</div>
			<div id="tabs-5">
			<!-- <form class="well"> -->
			<form:form  id="companyForm_customize" name="companyForm_customize" modelAttribute="companyForm" cssClass="well"  method="post" action="">
			    <fieldset style="font-family: sans-serif;">   
	     <h6><strong>Company - Customize</strong></h6> 
			    <table border="0" width="100%" style="font-size: 12px">
			    	<!-- tr>
    					<td width="100%" colspan="4"><strong>Company Customize</strong></td>
    				</tr> -->
   		 			<tr valign="top">
    					<td width="25%">Logo:</td>
    					<td width="50%" colspan="2">
    					<c:if test="${not empty companyForm.missAccount.maCustomizeLogoHotlink}">
    						<img id="company_photo"  width="350" height="66" src="getfile/companyLogo/${companyForm.missAccount.maId}/${companyForm.missAccount.maCustomizeLogoHotlink}" />
    					</c:if>
    					<c:if test="${empty companyForm.missAccount.maCustomizeLogoHotlink}">
    						<img id="company_photo" width="350" height="66" src="<c:url value='/resources/images/logowebmc.png'/>"/>
    					</c:if>
    					<input  id="company_upload" type="button" value="Upload">(350px × 66px)</td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Type:</td>
    					<td width="50%" colspan="2">
    					<form:select path="missAccount.maGrade">
    						<form:option value="1">Standard</form:option>
							<form:option value="2">Premium</form:option>
    					</form:select>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Theme Color:</td>
    					<td width="50%" colspan="2">
    					<form:select path="missAccount.maCustomizeHeadColor">
    						<form:option value="gray">Gray</form:option>
							<form:option value="blue">Blue</form:option>
							<form:option value="green">Green</form:option>
							<form:option value="orange">Orange</form:option>
    					</form:select>
    					&nbsp;&nbsp;&nbsp;<form:select path="missAccount.maCustomizeColor">
    						<form:option value="gray">Gray</form:option>
							<form:option value="blue">Blue</form:option>
							<form:option value="green">Green</form:option>
							<form:option value="orange">Orange</form:option>
    					</form:select>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				 <tr valign="top">
    					<td width="25%">Background:</td>
    					<td width="50%" colspan="2">
    					<!-- <img src=""/> -->
    					<form:select path="missAccount.maBackgroundColor">
    						<form:option value="gray">Gray</form:option>
							<form:option value="white">White</form:option>
    					</form:select></td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    				<tr>
    					<td width="100%" colspan="4"><strong>Response Candidate</strong></td>
    				</tr>
    				<tr>
    					<td width="100%" colspan="4">Pass Message</td>
    				</tr>
    				<tr>
    					<td width="100%" colspan="4">
    					<!-- <textarea cols="4" rows="4" id="company_editor1"></textarea> -->
    						<form:textarea path="missAccount.maCustomizePassMessage" cols="4" rows="4" id="maCustomizePassMessage"/>
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
    					<td width="100%" colspan="4">
    					<form:textarea path="missAccount.maCustomizeRejectMessage" cols="4" rows="4" id="maCustomizeRejectMessage"/>
    					<!-- <textarea cols="4" rows="4" id="company_editor2"></textarea> -->
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
    					<td width="100%" colspan="4">
    					
    					<form:textarea path="missAccount.maCustomizeRetestMessage" cols="4" rows="4" id="maCustomizeRetestMessage"/>
    					<!-- <textarea cols="4" rows="4" id="maCustomizeRetestMessage"></textarea> -->
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
			<div align="center"><a class="btn btn-primary"  onclick="doAction('action','companyForm_customize','8')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a></div>
			</div>
			
		</div>