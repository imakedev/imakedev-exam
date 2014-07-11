<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script>
$(document).ready(function() {
	getReportList();
});
function doSendMail(){
	var mailAttachReport=document.getElementsByName("mailAttachReport")[0];
    var msOrder=null;
    var mraLang=null;
	if(mailAttachReport.checked){
		if($("#reportShowSelect").val()!=null && $("#reportShowSelect").val()!='null'){
			//alert($("#reportShowSelect").val())
			//21_1_0
			var val_array=$("#reportShowSelect").val().split("_");
			msOrder=val_array[1];
			mraLang=val_array[2];
		} 
	}
	$("#msOrder").val(msOrder);
	$("#mraLang").val(mraLang);
	//return false;
	//alert(action)
	$("#mailMessage").val(CKEDITOR.instances["mailMessage"].getData());
	$.post("result/sendmail",$("#resultForm").serialize(), function(data) {
		  // alert(data);
		    appendContent(data);
		  // alert($("#_content").html());
		});
  }
function _getMessage(id){
	$.get("company/messaage/${resultForm.missTestResult.missCandidate.missAccount.maId}", function(data) {
		  // alert(data.maCustomizePassMessage);
		 //  $("#_content").html(data);
		 	var obj =data;// jQuery.parseJSON(data);		 	
		 	if(id=='0')
		  		 CKEDITOR.instances["mailMessage"].setData(obj.maCustomizePassMessage);
		 	else if(id=='1')
		 		CKEDITOR.instances["mailMessage"].setData(obj.maCustomizeRejectMessage);
		 	else if(id=='2')
		 		CKEDITOR.instances["mailMessage"].setData(obj.maCustomizeRetestMessage);
		});
	
	//
}
function getReportList(){
	//alert('${resultForm.missTestResult.msId}')
	$.get("role/get/reportDownload/${resultForm.missTestResult.msId}", function(data) {
		// var height_dialog=100;
			// alert(data)
		var _str_table="<select name=\"reportShowSelect\" id=\"reportShowSelect\"   style=\"width: 250px\">";
			if(data!=null && data.length>0){
				for(var i=0;i<data.length;i++){
					if(data[i][0].mraReportName!=null)
					_str_table=_str_table+"<option value=\"${resultForm.missTestResult.msId}_"+data[i][0].msOrder+"_"+data[i][0].mraLang+"\">"+data[i][0].mraReportName+"</option>";
					if(data[i][1].mraReportName!=null)
					_str_table=_str_table+"<option value=\"${resultForm.missTestResult.msId}_"+data[i][1].msOrder+"_"+data[i][1].mraLang+"\">"+data[i][1].mraReportName+"</option>";
				}
				
				//height_dialog=230;
			}else{
				 
			}
			_str_table=_str_table+"</select>";
			//alert(_str_table)
			$("#report_list_section").slideUp(300);
			$("#report_list_section").html(_str_table);
			//$("#message_element").slideUp(300)
			
	});
}
function showReport(){

	var mailAttachReport=document.getElementsByName("mailAttachReport")[0];
//	alert(mailAttachReport)
	//alert(mailAttachReport.checked)
	if(mailAttachReport.checked){
		$("#report_list_section").slideDown(300);
	}else
		$("#report_list_section").slideUp(300);
} 
  </script>
	    <!--Body content-->
	    <fieldset style="font-family: sans-serif;">  
<!--           <form class="well"> -->
          <form:form  id="resultForm" name="resultForm" modelAttribute="resultForm" cssClass="well" cssStyle="border:2px solid #DDD" method="post" action="">
             	<form:hidden path="msOrder"/>
             	<form:hidden path="mraLang"/> 
              <table border="0" width="100%" style="font-size: 13px">
              				<tr>
	    					 <td align="left" width="17%" colspan="6"><strong>Test Response</strong></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Candidate Name:</td>
	    					 <td align="left"  colspan="4">${resultForm.missTestResult.missCandidate.mcaFirstName} ${resultForm.missTestResult.missCandidate.mcaLastName}
	    					 </td> 
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Email:</td>
	    					 <td align="left" colspan="4"><form:input path="missTestResult.missCandidate.mcaEmail" cssStyle="width:500px"/>  
	    					 </td>	    					 
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">CC:</td>
	    					 <td align="left" colspan="4"><form:input path="mailcc" cssStyle="width:500px"/>  
	    					 </td>	    					 
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">BCC:</td>
	    					 <td align="left" colspan="4"><form:input path="mailbcc" cssStyle="width:500px"/>  
	    					 </td>	    					 
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Decision:</td>
	    					 <td align="left" colspan="4"><form:radiobutton path="mailDecision" value="0" onclick="_getMessage('0')"/>Pass&nbsp;
	    					 <form:radiobutton path="mailDecision" value="1" onclick="_getMessage('1')"/>Reject&nbsp;
	    					<form:radiobutton path="mailDecision" value="2" onclick="_getMessage('2')"/>Retest&nbsp;				
	    					 </td> 
	    					</tr>
	    					<tr>
	    					 <td align="left" colspan="6">Message:</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" colspan="6"><form:textarea path="mailMessage" cols="4" rows="4" id="mailMessage"/> 
    					<script>
    					if (CKEDITOR.instances['mailMessage']) {
    			            CKEDITOR.remove(CKEDITOR.instances['mailMessage']);
    			         }
    					CKEDITOR.replace( 'mailMessage',
    						    {
    						      //  toolbar : 'Basic',
    						      toolbar : [
										    { name: 'document', items : [ 'Source','-','Preview','-'] }, 
											{ name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike',] },
											{ name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock' ] },
											{ name: 'links', items : [ 'Link','Unlink'] },
											{ name: 'insert', items : [ 'Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak' ] },
											'/',
									  	 	{ name: 'styles', items : [ 'Styles','Format','Font','FontSize' ] },
											{ name: 'colors', items : [ 'TextColor','BGColor'] } 
											//{ name: 'tools', items : [ 'MyButton' ] }
										]
    						      //  uiColor : '#9AB8F3'
    						    });
    					</script></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" colspan="6"><form:checkbox path="mailReactive" value="1"/>  Reactive&nbsp;<form:checkbox path="mailAttachReport" onclick="showReport()" value="1"/> Attach Report(PDF)&nbsp;&nbsp;<span id="report_list_section"></span></td>
	    					</tr>
	    					</table> 
	    					</form:form>
	    					<!-- <div align="center"><input type="button" value="Send"></div> -->
	    					
	    					<div align="center"><a class="btn btn-primary" onclick="doSendMail();"><i class="icon-envelope icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Send</span></a></div>
      </fieldset> 
   