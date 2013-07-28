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
	      <fieldset style="font-family: sans-serif;"> 
         <form:form   id="paperForm" name="paperForm" modelAttribute="paperForm" cssClass="well" cssStyle="border:2px solid ${UserMissContact.missTheme.mtBgColor};background: url(/MISSExamBackOffice/resources/images/${UserMissContact.missTheme.mtWaterWall}) no-repeat scroll right bottom ${UserMissContact.missTheme.mtBgColor}" method="post" action="">
         	<%--   <form:hidden path="examId" id="examId"/>
         	  <form:hidden path="setId" id="setId"/> 
         	  <form:hidden path="missSery.msId" id="msId"/>
         	  <form:hidden path="missCandidate.mcaId" id="mcaId"/> --%>
	    	 <div id="info_element" align="center">
              <table  border="0" width="100%" style="font-size: 13px;">
              				<tr>
	    					 <td align="left" width="100%" colspan="6"><strong>บันทึกคำตอบเรียบร้อยแล้ว</strong></td>
	    					</tr> 
	    		</table>  
	    	</div>
	   </form:form>
	   
       <div align="center">	
       <a class="btn btn-info"  onclick="goBackDopaperTest()"><i class="icon-chevron-left icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Back</span></a>
       </div>
</fieldset>  
