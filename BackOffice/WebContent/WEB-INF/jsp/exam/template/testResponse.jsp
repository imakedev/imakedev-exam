<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script>
$(document).ready(function() {
	
});
function doSendMail(){
	
	//alert(action)
	$("#mailMessage").val(CKEDITOR.instances["mailMessage"].getData());
	$.post("result/sendmail",$("#resultForm").serialize(), function(data) {
		  // alert(data);
		    appendContent(data);
		  // alert($("#_content").html());
		});
  }
  </script>
	    <!--Body content-->
	    <fieldset style="font-family: sans-serif;">  
<!--           <form class="well"> -->
          <form:form  id="resultForm" name="resultForm" modelAttribute="resultForm" cssClass="well" cssStyle="border:2px solid #DDD" method="post" action="">
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
	    					 <td align="left" colspan="4"><form:input path="missTestResult.missCandidate.mcaEmail"/>  
	    					 </td>	    					 
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">CC:</td>
	    					 <td align="left" colspan="4"><form:input path="mailcc"/>  
	    					 </td>	    					 
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">BCC:</td>
	    					 <td align="left" colspan="4"><form:input path="mailbcc"/>  
	    					 </td>	    					 
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Decision:</td>
	    					 <td align="left" colspan="4"><form:radiobutton path="mailDecision" value="0"/>Pass&nbsp;
	    					 <form:radiobutton path="mailDecision" value="1"/>Reject&nbsp;
	    					<form:radiobutton path="mailDecision" value="2"/>Retest&nbsp;				
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
    						        toolbar : 'Basic',
    						      //  uiColor : '#9AB8F3'
    						    });
    					</script></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" colspan="6"><form:checkbox path="mailReactive" value="1"/>  Reactive&nbsp;<form:checkbox path="mailAttachReport" value="1"/> Attach Report(PDF)</td>
	    					</tr>
	    					</table> 
	    					</form:form>
	    					<!-- <div align="center"><input type="button" value="Send"></div> -->
	    					
	    					<div align="center"><a class="btn btn-primary" onclick="doSendMail();"><i class="icon-envelope icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Send</span></a></div>
      </fieldset> 
   