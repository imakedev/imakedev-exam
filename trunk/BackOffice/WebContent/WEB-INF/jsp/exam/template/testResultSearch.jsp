<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	renderPageSelect();
	$("#testFrom" ).datepicker({
		showOn: "button",
		buttonImage: _path+"resources/images/calendar.gif",
		buttonImageOnly: true,
		dateFormat:"dd/mm/yy" 
	});
	$("#testTo" ).datepicker({
		showOn: "button",
		buttonImage: _path+"resources/images/calendar.gif",
		buttonImageOnly: true,
		dateFormat:"dd/mm/yy" 
	});
});
function goPrev(){
	if($("#pageNo").val()!='1'){
		var prev=parseInt($("#pageNo").val())-1;
		$("#pageNo").val(prev);
		doAction('search','0');
	}
}
function goNext(){
	var next=parseInt($("#pageNo").val());
	if(next<parseInt($("#pageCount").val())){
		next=next+1;
		$("#pageNo").val(next);
		doAction('search','0');
	}
} 
function goToPage(){ 
	$("#pageNo").val(document.getElementById("resultPageSelect").value);
	doAction('search','0');
}
function renderPageSelect(){
	var pageStr="<select name=\"resultPageSelect\" id=\"resultPageSelect\" onchange=\"goToPage()\" style=\"width: 50px\">";
//	var pageCount=parseInt($("#pageCount").val());
	var pageCount=$("#pageCount").val();
	for(var i=1;i<=pageCount;i++){
		pageStr=pageStr+"<option value=\""+i+"\">"+i+"</option>";
	}
	pageStr=pageStr+"</select>"; 
	$("#pageElement").html(pageStr);
	document.getElementById("resultPageSelect").value=$("#pageNo").val();
}
function doAction(mode,id){
	$("#mode").val(mode);
	if(mode=='deleteItems'){
		$("#mtrIdArray").val(id);
	}else if(mode!='search'){
		$("#mtrId").val(id);
	}else {
		$("#mtrId").val("0");
	}
	$.post("result/search",$("#resultForm").serialize(), function(data) {
		  // alert(data);
		    appendContent(data);
		  // alert($("#_content").html());
		});
}
</script>


	    <fieldset style="font-family: sans-serif;">  
           <!-- <legend  style="font-size: 13px">Criteria</legend> -->
           <!-- <div style="position:relative;right:-94%;">  </div> --> 
         <!--  <form class="well" style="border:2px solid #DDD"> -->
             <form:form  id="resultForm" name="resultForm" modelAttribute="resultForm" cssClass="well" cssStyle="border:2px solid #DDD" method="post" action="">
             <form:hidden path="mode"/>
            
            <form:hidden path="mtrIdArray" id="mtrIdArray"/>
            <form:hidden path="paging.pageNo" id="pageNo"/>
            <form:hidden path="paging.pageSize" id="pageSize"/> 
            <form:hidden path="pageCount" id="pageCount"/>
          
              <table border="0" width="100%" style="font-size: 13px">
              				<tr>
	    					 <td align="left" width="100%" colspan="6"><strong>Test Result Search</strong></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Series<font color="red">*</font>:</td>
	    					 <td align="left" width="17%">    					
	    					<!--  <select id="mcaSeries">
	    					      <option value="-1">-- Select Series --</option>
	    					      
	    					    </select>	 -->
	    					     <form:select path="mcaSeries">
	    					      <form:options itemValue="msId" itemLabel="msSeriesName" items="${missSeries}"/>
	    					    </form:select>		
	    					 </td>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Username:</td>
	    					 <td align="left" width="17%">    					
	    					<form:input path="mcaUsername"/>
	    					 </td>
	    					<td align="left" width="17%">&nbsp;</td>
	    					<td align="left" width="17%">&nbsp;</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">First Name:</td>
	    					 <td align="left" width="17%"> <form:input path="mcaFirstName" />
	    					 </td>
	    					<td align="left" width="17%">Last Name:</td>
	    					<td align="left" width="17%"><form:input path="mcaLastName" /></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Position:</td>
	    					 <td align="left" width="17%"> <form:input path="mcaPosition" />
	    					 </td>
	    					<td align="left" width="17%">Department:</td>
	    					<td align="left" width="17%"><form:input path="mcaDepartment"/></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Test From:</td>
	    					 <td align="left" width="17%">  <form:input path="testFrom" cssStyle="width:75px"/>
	    					 </td>
	    					<td align="left" width="17%">Test To:</td>
	    					<td align="left" width="17%"><form:input path="testTo" cssStyle="width:75px"/></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Company Name:</td>
	    					 <td align="left" colspan="3" width="51%"> 
	    					 <form:input path="mcaCompanyName" cssStyle="width:100%"/>   					
	    					 </td> 
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					</table> 
	    					</form:form>
	    					<table  border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="left" width="60%">
	    					
	    					<a class="btn btn-success disabled"><i class="icon-pencil icon-white"></i>&nbsp;Do Paper Test</a>&nbsp;
	    					<a class="btn btn-info disabled"><i class="icon-circle-arrow-up icon-white"></i>&nbsp;Export</a>&nbsp;
	    					<a class="btn btn-info disabled"><i class="icon-list-alt icon-white"></i>&nbsp;Summary</a>&nbsp;
	    					<a class="btn btn-danger disabled"><i class="icon-eject icon-white"></i>&nbsp;Ignore</a></td>
	    					<td align="right" width="40%">
	    					<a onclick="goPrev()">Prev</a>&nbsp;|&nbsp;<span id="pageElement"></span>&nbsp;|&nbsp;<a onclick="goNext()">Next</a>&nbsp;<a  class="btn btn-primary" onclick="doAction('search','0')"><i class="icon-search icon-white"></i>&nbsp;Search</a>
	    					</td>
	    					</tr>
	    					</table> 
	    					<div id="_result_content" align="center">
		<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="5%"><div class="th_class"><input type="checkbox"/></div></th>
            		<th width="10%"><div class="th_class">Username</div></th> 
            		<th width="15%"><div class="th_class">First Name</div></th>
            		<th width="10%"><div class="th_class">Last Name</div></th> 
            		<th width="10%"><div class="th_class">Position</div></th>
            		<th width="10%"><div class="th_class">Department</div></th> 
            		<c:forEach items="${axisHeaders}" var="axisHeader" varStatus="loop">
            				<th width="5%"><div class="th_class">${axisHeader}</div></th>
            		</c:forEach>
            		<!-- <th width="5%"><div class="th_class">Fa</div></th>
            		<th width="5%"><div class="th_class">Im</div></th> 
            		<th width="5%"><div class="th_class">Pe</div></th>
            		<th width="5%"><div class="th_class">Ju</div></th>  -->
            		<th width="10%"><div class="th_class">Test Date</div></th> 
            		<th width="5%"><div class="th_class">Report</div></th>
            		<th width="5%"><div class="th_class">Status</div></th>
            		<th width="5%"><div class="th_class">Response</div></th> 
          		</tr>
        	</thead>
        	<tbody>
        	 <c:forEach items="${missTestResults}" var="missTestResult" varStatus="loop"> 
          	<tr>
            	<td><input type="checkbox" /></td>
            	<td>
            	<a>${missTestResult.missCandidate.mcaUsername}</a>
            	</td>
            	<td>&nbsp;${missTestResult.missCandidate.mcaFirstName}</td>
            	<td>&nbsp;${missTestResult.missCandidate.mcaLastName}</td>
            	<td>&nbsp;${missTestResult.missCandidate.mcaPosition}</td>
            	<td>&nbsp;${missTestResult.missCandidate.mcaDepartment}</td>
            	<c:forEach items="${missTestResult.axisValues}" var="axisValue" varStatus="loop2">
            				<td>${axisValue}</td>
            	</c:forEach> 
            	<!-- <td>?</td>
            	<td>?</td>            	
            	<td>?</td> 
            	<td>?</td> -->
            	<td>&nbsp;<fmt:formatDate pattern="dd/MM/yyyy" value="${missTestResult.mtrTestDate}" /></td> 
            	<c:url value="/result/testPDF" var="downloadUrl">
            		<c:param name="mtrId" value="${missTestResult.mtrId}"></c:param>
            		<c:param name="meId" value="${missTestResult.meId}"></c:param>
            		<c:param name="msId" value="${missTestResult.msId}"></c:param>
            		<c:param name="mcaId" value="${missTestResult.missCandidate.mcaId}"></c:param>
            	</c:url>
            	<td>&nbsp;<a href="${downloadUrl}">${missTestResult.mtrResultCode}</a></td>            	
            	<td>
            	<c:if test="${missTestResult.mtrStatus=='0'}">Not finished</c:if>
            	<c:if test="${missTestResult.mtrStatus=='1'}">Finished</c:if>
            	<c:if test="${missTestResult.mtrStatus=='2'}">Responded</c:if>
            	</td>
            	<td>&nbsp;
            	<c:if test="${(missTestResult.mtrStatus=='1' || missTestResult.mtrStatus=='2') &&  missTestResult.mtrIgnored!='1'}">            	
            			<a onclick="loadDynamicPage('result/response/1')">Email</a>
            	</c:if></td>
          	</tr>
          	</c:forEach>
        	</tbody>
      </table>
      </div>
</fieldset>