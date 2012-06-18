<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<c:url value="/exam/template" var="finishUrl"/>
<script type="text/javascript">
$(document).ready(function() {
	
});
function goToDynamicPage(pageId,formID,navi){
	/* if(navi=='prev'){
		//$("#examIndex").val((parseInt($("#examIndex").val())-1)+"");
		$("#questionIndex").val((parseInt($("#questionIndex").val())-1)+"");
	}else if(navi=='next'){
		$("#questionIndex").val((parseInt($("#questionIndex").val())+1)+"");
	} */
	$("#mode").val(navi);
	if(navi!='finish')
		postDynamicPage(pageId,formID);
	else
		document.forms["missExamForm"].submit();
}
</script>
<fieldset style="font-family: sans-serif;">  
           <!-- <legend  style="font-size: 13px">Criteria</legend> -->
           <!-- <div style="position:relative;right:-94%;">  </div> --> 
        <!--   <form class="well" style="border:2px solid #DDD"> -->
          <form:form  id="missExamForm" name="missExamForm" modelAttribute="missExamForm" cssClass="well" cssStyle="border:2px solid #DDD" method="post" action="${finishUrl}">
          <div> 
          <form:hidden path="examIndex" id="examIndex"/>
          <form:hidden path="questionIndex" id="questionIndex"/>
          <form:hidden path="mode" id="mode"/>
              <table border="0" width="100%" style="font-size: 13px">
              				<tr>
	    					 <td align="left" width="100%" colspan="6"><strong>Question No. ${missExamForm.questionIndex+1}</strong></td>
	    					</tr>
	    					<tr>
	    					<%--  <td align="left" width="100%"><pre>&nbsp;${missQuestion.mqNameTh1}</pre></td>  --%>
	    					 <td align="left" width="100%">&nbsp;${missQuestion.mqNameTh1}</td> 
	    					</tr> 
	    					<%-- <tr>
	    					 <td align="left" width="100%">&nbsp;
	    					 <c:forEach items="${missQuestion.missChoices}" var="missChoice" varStatus="loop"> 
	    					     <c:if test="${not empty missChoice.choiceSelect}"> 
	    					 		<input type="radio" name="mcScore" checked="checked" value="${missChoice.mcId}">&nbsp; ${missChoice.mcName}&nbsp;&nbsp;
	    					 	 </c:if>
	    					 	 <c:if test="${empty missChoice.choiceSelect}"> 
	    					 	 	<input type="radio" name="mcScore" value="${missChoice.mcId}">&nbsp; ${missChoice.mcName}&nbsp;&nbsp;
	    					 	 </c:if>
	    					 </c:forEach>
	    					 </td> 
	    					</tr> --%>
	    					 <c:forEach items="${missQuestion.missChoices}" var="missChoice" varStatus="loop">
	    					<tr>
	    					 <td align="left" width="100%">&nbsp;
	    					 
	    					     <c:if test="${not empty missChoice.choiceSelect}"> 
	    					 		<input type="radio" name="mcScore" checked="checked" value="${missChoice.mcId}">&nbsp; ${missChoice.mcName}&nbsp;&nbsp;
	    					 	 </c:if>
	    					 	 <c:if test="${empty missChoice.choiceSelect}"> 
	    					 	 	<input type="radio" name="mcScore" value="${missChoice.mcId}">&nbsp; ${missChoice.mcName}&nbsp;&nbsp;
	    					 	 </c:if>
	    					 
	    					 </td> 
	    					</tr>
	    					</c:forEach>
	    					</table> 
	    	</div>
	    	<div style="padding:  45px 0px 0px 0px">
	    					<table border="0" width="100%" style="font-size: 13px">
              				<tr>
              				
	    					 <td align="left" width="50%"> 
	    					 <c:if test="${missExamForm.questionIndex!=0}">
	    					  <a class="btn btn-primary" onclick="goToDynamicPage('exam/template','missExamForm','prev')" ><span style="color: white;font-weight: bold;"><i class="icon-chevron-left icon-white"></i>&nbsp;Previous</span></a>
	    					 </c:if>
	    					</td>	    					
	    					 <td align="right" width="50%">
	    					  <c:if test="${questionTotal!=(missExamForm.questionIndex+1)}">
	    					  <a class="btn btn-primary" onclick="goToDynamicPage('exam/template','missExamForm','next')" ><span style="color: white;font-weight: bold;">Next&nbsp;<i class="icon-chevron-right icon-white"></i></span></a>
	    					 </c:if>
	    					  <c:if test="${questionTotal==(missExamForm.questionIndex+1)}">
	    					  <a class="btn btn-success" onclick="goToDynamicPage('exam/template','missExamForm','finish')" ><span style="color: white;font-weight: bold;">Finish&nbsp;<i class="icon-chevron-right icon-white"></i></span></a>
	    					 </c:if>
	    					 </td>
	    					</tr>
	    					</table>
	    					</div>
	    					</form:form>
	     <div align="center">			
      </div>
</fieldset> 