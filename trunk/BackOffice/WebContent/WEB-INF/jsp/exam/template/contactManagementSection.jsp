<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
//	$('#tabs').tabs();
  //   $("fieldset.collapsibleClosed").collapse( { closed : true } );
//	$('#tabs').tabs('select', parseInt($("#_test_section").val()));
	/* $('#tabs').bind('tabsselect', function(event, ui) {

	    // Objects available in the function context:
	  
	   //alert("index="+ui.index+",panel="+ui.panel+",tab="+ui.tab)
	   if(ui.index==2){
		   alert($("#_meId").val())
		   $("#tabs-3").html("");
	   }

	}); */
	$("#mcontactBirthDate" ).datepicker({
		showOn: "button",
		buttonImage: _path+"resources/images/calendar.gif",
		buttonImageOnly: true,
		dateFormat:"dd/mm/yy" ,
		changeMonth: true,
		changeYear: true
	});
	var target="contactImg";
	/* if($("#mcontactType").val() != '1'){
		target="company";
	} */
	new AjaxUpload('contact_upload', {
        action: 'upload/'+target+'/${contactForm.missContact.mcontactId}',
		onSubmit : function(file , ext){
			if('${contactForm.mode}' !='edit'){
				alert(" Please Save before Upload Photo");
				return false;
			}else{
				 // Allow only images. You should add security check on the server-side.
				if (ext && /^(jpg|png|jpeg|gif)$/.test(ext)){
					/* Setting data */
					this.setData({
						'key': 'This string will be send with the file',
						'test':'chatchai'
					});					
				//$('#contact_photo').attr('src', _path+"resources/images/ui-anim_basic_16x16.gif");
				$('#contact_photo').attr('src', _path+"resources/images/loading.gif");
				} else {					
					// extension is not allowed
					alert('Error: only images are allowed') ;
					// cancel upload
					return false;				
				}
			}
           		
		},
		onComplete : function(file, response){
			//alert(response);
			/* response=response.replace("<pre>","");
			response=response.replace("</pre>","");
			  var obj = jQuery.parseJSON(response);
			  alert(obj); */
			  var obj = jQuery.parseJSON(response); //obj.hotlink
			$("#contact_photo").attr("src","getfile/"+target+"/${contactForm.missContact.mcontactId}/"+obj.hotlink);
			$("#mcontactPictureFileName").val(obj.filename);
			$("#mcontactPictureHotlink").val(obj.hotlink);
			$("#mcontactPicturePath").val(obj.filepath);
			 
			//$('#example2 .text').text('Uploaded ' + file);		
			//alert(file);
			//alert(response)
		
		}		
	});
});
function goBackContacts(){
	var target="miss";
	if($("#maId").val() != '1'){
		target="company";
	}
	  $.ajax({
		  type: "get",
		  url: target+"/account/${contactForm.missContact.mcontactRef}/contacts",
		  cache: false
		 // data: { name: "John", location: "Boston" }
		}).done(function( data ) {
			if(data!=null){
				appendContentWithId(data,"tabs-3");
				// $("#tabs-3").html(data);
			  }
		});
}
function doContactAction(action,mode,id){
	//alert(id)
/* 	 
	$("#modeQuestion").val(mode);
	if(mode!='search'){
		$("#mqId").val(id);
	}else{
		$("#mqId").val("0");
	}
 */	
	var target="miss";
	if($("#mcontactType").val() != '1'){
		target="company";
	}
 	$.post(target+"/action/account/contact",$("#contactForm").serialize(), function(data) {
		  // alert(data);
		   appendContentWithId(data,"tabs-3");
		  // alert($("#_content").html());
		});
  }
function test(){
	var editor_data =CKEDITOR.instances['mqNameTh1']; //alert(editor2) // [obj]
	var selection = editor_data.getSelection();//alert(selection) // [obj]
	var text = selection.getNative();//alert(text) // ""
	var ranges = selection.getRanges();// alert(ranges) //[obj]
	var type = selection.getType();// alert(type) // 2 
var	 newElement=CKEDITOR.dom.element.createFromHtml( '<img alt="" src="http://10.2.0.76:10000/BPSDownloadServlet/DownloadServlet?id=" />');
	 ranges[0].deleteContents();
	 ranges[0].insertNode(newElement);
	 ranges[0].selectNodeContents( newElement ); 
	// CKEDITOR.dialog.getCurrent().hide();
}
</script>
 <div class="alert alert-success" style="${display}">
    <button class="close" data-dismiss="alert"><span style="font-size: 12px">x</span></button>
    <strong>${message}</strong> 
    </div>
 <fieldset style="font-family: sans-serif;"> 
	 <form:form  id="contactForm" name="contactForm" modelAttribute="contactForm"  method="post" action="">
			<!--  <form class="well"> -->
			  <input type="hidden" value="${contactForm.missContact.mcontactRef}" id="maId"/>
			  <input type="hidden" value="${contactForm.missContact.mcontactType}" id="mcontactType"/>
			  <form:hidden path="mode"/>
			  <form:hidden path="missContact.mcontactPictureFileName" id="mcontactPictureFileName" />
			  <form:hidden path="missContact.mcontactPictureHotlink" id="mcontactPictureHotlink" />
			  <form:hidden path="missContact.mcontactPicturePath" id="mcontactPicturePath" />
			   
			  <fieldset style="font-family: sans-serif;">   
			 <!--  <pre  class="prettyprint" style="font-family: sans-serif;font-size:12px:;margin-top: 0px"> -->
			    <table border="0" width="100%" style="font-size: 12px">
			    	<tr>
    					<td width="100%" colspan="4"><strong>Contct Point Profile</strong></td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Username:</td>
    					<td width="50%" colspan="2">
    					<c:if test="${contactForm.mode=='edit'}">    					
    						<form:input path="missContact.mcontactUsername" id="mcontactUsername" readonly="true"/>
    					</c:if>
    					<c:if test="${contactForm.mode!='edit'}">    					
    						<form:input path="missContact.mcontactUsername" id="mcontactUsername"/>
    					</c:if>
    					</td>
    					 <td width="25%"  align="right"  rowspan="8">
    					 <c:if test="${not empty contactForm.missContact.mcontactPictureHotlink}"> 
						 	<img id="contact_photo" width="128" height="128" src="getfile/contactImg/${contactForm.missContact.mcontactId}/${contactForm.missContact.mcontactPictureHotlink}" />
						 </c:if>
						 <c:if test="${empty contactForm.missContact.mcontactPictureHotlink}"> 
						 	<img id="contact_photo" width="128"  height="128" src="<c:url value='/resources/images/photo.png'/>" />
						 </c:if>
    					 
    					 <div align="right"><input  id="contact_upload" type="button" value="Upload"></div>
    					 <div align="right">(128px × 128px)</div>
    					 </td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Password:</td>
    					<td width="50%" colspan="2">
    					<input type="password" value="${contactForm.missContact.mcontactPassword}" id="mcontactPassword" name="missContact.mcontactPassword">
    					<%-- <form:password path="missContact.mcontactPassword" id="mcontactPassword"/> --%>
    					</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Role:</td>
    					<td width="50%" colspan="2">
    					<form:select path="missContact.rcId">
    						 <form:option value="-1">-- Select Role --</form:option> 
    						 <form:options items="${roleContacts}" itemLabel="rcName" itemValue="rcId"></form:options>
	    					     
    					</form:select>
    					</td>
    				</tr>
   		 			<tr valign="top">
    					<td width="25%">First-Lastname:</td>
    					<td width="50%" colspan="2">
    					<form:select path="missContact.mcontactTitleType" cssStyle="width:70px">
    						<form:option value="0">นาย</form:option>
    						<form:option value="1">นาง</form:option>
    						<form:option value="2">นางสาว</form:option>
    						<form:option value="3">ระบุ 	&rarr;</form:option>
    					</form:select>
    					<form:input path="missContact.mcontactName" id="mcontactName" cssStyle="width:120px"/>
    					&nbsp;
    					<form:input path="missContact.mcontactLastname" id="mcontactLastname" cssStyle="width:120px"/>
    					</td>
    					<%--  <td width="25%"  align="right"  rowspan="8">
    					 <c:if test="${not empty contactForm.missContact.mcontactPictureHotlink}"> 
						 	<img id="contact_photo" width="128" height="128" src="getfile/contactImg/${contactForm.missContact.mcontactId}/${contactForm.missContact.mcontactPictureHotlink}" />
						 </c:if>
						 <c:if test="${empty contactForm.missContact.mcontactPictureHotlink}"> 
						 	<img id="contact_photo" width="128"  height="128" src="<c:url value='/resources/images/photo.png'/>" />
						 </c:if>
    					 
    					 <div align="right"><input  id="contact_upload" type="button" value="Upload"></div>
    					 <div align="right">(128px × 128px)</div>
    					 </td> --%>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Gender:</td>
    					<td width="50%" colspan="2">
    					<form:radiobutton path="missContact.mcontactGender" value="0"/>Female&nbsp;&nbsp;&nbsp;<form:radiobutton path="missContact.mcontactGender" value="1"/>Male
    					</td>
    				</tr>
    				  <tr valign="top">
    					<td width="25%">Birth Date:</td>
    					<td width="50%" colspan="2">
    					<form:input path="mcontactBirthDate" id="mcontactBirthDate" cssStyle="width: 75px"/>
    					</td>
    				</tr>  
    				<tr valign="top">
    					<td width="25%">Position:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missContact.mcontactPostion" id="mcontactPostion"/>
    					</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Department:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missContact.mcontactDepartment" id="mcontactDepartment"/>
    					</td>
    				</tr>
    				 <tr valign="top">
    					<td width="25%">Phone:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missContact.mcontactPhone" id="mcontactPhone"/>
    					</td>
    				</tr>
    				 <tr valign="top">
    					<td width="25%">Fax:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missContact.mcontactFax" id="mcontactFax"/>
    					</td>
    				</tr>
    		<%-- 	<c:if test="${contactForm.missContact.mcontactType=='1'}"> --%>
    				<tr valign="top">
    					<td width="25%">Email 1:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missContact.mcontactEmail" id="mcontactEmail"/>
    					</td>
    					<td width="25%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="25%">Email 2:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missContact.mcontactEmail2" id="mcontactEmail2"/>
    					</td>
    					 <td width="25%">&nbsp;</td>
    				</tr>
    			<%-- </c:if>
    			<c:if test="${contactForm.missContact.mcontactType!='1'}">
    				<tr valign="top">
    					<td width="25%">Email:</td>
    					<td width="50%" colspan="2">
    					<form:input path="missContact.mcontactEmail" id="mcontactEmail"/>
    					</td>
    					<td width="25%">&nbsp;</td>
    				</tr>
    				
    			</c:if> --%>
    			</table>
    			<!-- </pre> -->
    			</fieldset>
    			<!-- </form> -->
			  </form:form> 
			<%--
			<div align="center"><input type="button" class="btn" value="Save"/></div>
			 --%>
			<div align="center">
			<a class="btn btn-info"  onclick="goBackContacts()"><i class="icon-chevron-left icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Back</span></a>	
    					 <a class="btn btn-primary"  onclick="doContactAction('action','${contactForm.mode}','${contactForm.missContact.mcontactId}')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a>
			</div>
</fieldset>