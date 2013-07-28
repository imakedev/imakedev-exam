<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %> 
<script type="text/javascript">
$(document).ready(function() {
 
	  
});
function goBackDopaperTest(){ 
	loadDynamicPage("paper/exam/info");
}
function doSubmitAnswer(){
	$.post("paper/saveExam",$("#paperForm").serialize(), function(data) {
		  // window.location.href='${examInfoUrl}';
			 appendContent(data)
		});
}
</script>
 <div id="dialog-create-candidate-alert"  title="Message" style="display: none;"> 
    <div id="message_candidate_create"></div> 
  </div>
	      <fieldset style="font-family: sans-serif;"> 
         <form:form  id="paperForm" name="paperForm" modelAttribute="paperForm" cssClass="well" cssStyle="border:2px solid ${UserMissContact.missTheme.mtBgColor};background: url(/MISSExamBackOffice/resources/images/${UserMissContact.missTheme.mtWaterWall}) no-repeat scroll right bottom ${UserMissContact.missTheme.mtBgColor}" method="post" action="">
         	  <form:hidden path="mcaSeries" id="mcaSeries"/> 
         	<%--   <form:hidden path="examId" id="examId"/>
         	  <form:hidden path="setId" id="setId"/> 
         	  <form:hidden path="missSery.msId" id="msId"/>
         	  <form:hidden path="missCandidate.mcaId" id="mcaId"/> --%>
	    	 <div id="info_element">
              <table  border="0" width="100%" style="font-size: 13px;">
              				<tr>
	    					 <td align="left" width="100%" colspan="6"><strong>Paper Test</strong></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="7%">&nbsp;</td>
	    					 <td align="left" width="10%">Series:</td>
	    					 <td align="left" width="29%">
	    					   ${missSery.msSeriesName} 
	    					 </td>  
	    					 <td align="left" width="10%">Set:</td>
	    					<td align="left" width="29%">
	    					  ${paperForm.setId} 
	    					</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr> 
	    		</table>  
	    	</div>
	   
	    <div><b>Test : ${missExam.meName}</b></div>	
	    <div align="center">
		<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px;width: 100%">
        	<thead>
          		<tr> 
            		<th width="10%"><div class="th_class">Question</div></th>  
            		<th width="90%"><div class="th_class">Answer</div></th> 
          		</tr>
        	</thead>
        	<tbody>
        	 <c:forEach items="${missQuestions}" var="missQuestion" varStatus="loop"> 
         		 	<tr> 
            			<td>
            			<%-- ${missQuestion.mqNo} --%> ${loop.index+1}
            			</td> 
            			<td>
            			 <c:forEach items="${missQuestion.missChoices}" var="missChoice" varStatus="loop2"> 
            			 	<input type="radio" name="choice_${missQuestion.mqId}" value="${missChoice.mcNo}" /> ${missChoice.mcNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			 </c:forEach>
            			</td> 
          			</tr>
          	</c:forEach>
        	</tbody>
        </table>
	     </div>
	      </form:form>	
       <div align="center">	
       <a class="btn btn-info"  onclick="goBackDopaperTest()"><i class="icon-chevron-left icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Back</span></a>
       <a id="save_element" class="btn btn-primary" onclick="doSubmitAnswer()" ><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a>
       <!-- <input type="submit" value="AA"> -->
       </div>
</fieldset>  
