<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
	    <!--Body content-->
	    <fieldset style="font-family: sans-serif;">  
          <form class="well">
              <table border="0" width="100%" style="font-size: 13px">
              				<tr>
	    					 <td align="left" width="17%" colspan="6"><strong>Test Response</strong></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Candidate Name:</td>
	    					 <td align="left"  colspan="4">Full Name
	    					 </td> 
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Email:</td>
	    					 <td align="left" colspan="4">email@gmail.com  
	    					 </td>	    					 
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">CC:</td>
	    					 <td align="left" colspan="4"><input type="text"> 
	    					 </td>	    					 
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">BCC:</td>
	    					 <td align="left" colspan="4"><input type="text">
	    					 </td>	    					 
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Decision:</td>
	    					 <td align="left" colspan="4"><input type="radio">Pass&nbsp;
	    					 <input type="radio">Reject&nbsp;
	    					 <input type="radio">Retest&nbsp;				
	    					 </td> 
	    					</tr>
	    					<tr>
	    					 <td align="left" colspan="6">Message:</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" colspan="6"><textarea cols="4" rows="4" id="reponse_editor11"></textarea>
    					<script>
    					if (CKEDITOR.instances['reponse_editor11']) {
    			            CKEDITOR.remove(CKEDITOR.instances['reponse_editor11']);
    			         }
    					CKEDITOR.replace( 'reponse_editor11',
    						    {
    						        toolbar : 'Basic',
    						      //  uiColor : '#9AB8F3'
    						    });
    					</script></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" colspan="6"><input type="checkbox"> Reactive&nbsp;<input type="checkbox"> Attach Report(PDF)</td>
	    					</tr>
	    					</table> 
	    					</form>
	    					<!-- <div align="center"><input type="button" value="Send"></div> -->
	    					
	    					<div align="center"><a class="btn btn-primary"><i class="icon-envelope icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Send</span></a></div>
      </fieldset> 
   