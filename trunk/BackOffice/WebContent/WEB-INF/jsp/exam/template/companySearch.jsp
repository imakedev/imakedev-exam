<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %> 
<style>
.ui-dialog-titlebar2{
	    background-image:url("http://www.elementalsurface.com/beech-wood.jpg"); 
	}
</style>
<script type="text/javascript">
var amount_G;
$(document).ready(function() {
	renderPageSelect();
	amount_G=$("#amount");
	var idArray=$("#meIdArray").val();
//	 alert(idArray);
	 if(idArray.length>0){
		 var idSplit=idArray.split(",");
//		 alert(" split size="+idSplit.length)
		 var missExam_selectboxes=document.getElementsByName("missExam_selectbox");
		 for(var i=0;i<missExam_selectboxes.length;i++){
			 for(var j =0;j<idSplit.length;j++){
				 if(missExam_selectboxes[i].value==idSplit[j]){
					 missExam_selectboxes[i].checked=true;
					 break;
				 }
			 }
		 }
	 }
		
	 $("#maRegisterFrom" ).datepicker({
			showOn: "button",
			buttonImage: _path+"resources/images/calendar.gif",
			buttonImageOnly: true,
			dateFormat:"dd/mm/yy" 
		});
	 $("#maRegisterTo" ).datepicker({
			showOn: "button",
			buttonImage: _path+"resources/images/calendar.gif",
			buttonImageOnly: true,
			dateFormat:"dd/mm/yy" 
		});
	/*  $("input[id=msSeriesName],[id=msUnitCost]").keypress(function(event) {
	 	  if ( event.which == 13 ) {
	 	     event.preventDefault();
	 	    	doAction('search','0');
	 	   }
	 }); */
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
	$("#pageNo").val(document.getElementById("companyPageSelect").value);
	doAction('search','0');
}
function renderPageSelect(){
	var pageStr="<select name=\"companyPageSelect\" id=\"companyPageSelect\" onchange=\"goToPage()\" style=\"width: 50px\">";
//	var pageCount=parseInt($("#pageCount").val());
	var pageCount=$("#pageCount").val();
	for(var i=1;i<=pageCount;i++){
		pageStr=pageStr+"<option value=\""+i+"\">"+i+"</option>";
	}
	pageStr=pageStr+"</select>"; 
	$("#pageElement").html(pageStr);
	document.getElementById("companyPageSelect").value=$("#pageNo").val();
}
function toggleCheckbox(){
	var _check=document.getElementById("maIdCheckboxAll").checked;
	var maIdCheckbox=document.getElementsByName("maIdCheckbox"); 
	for(var i=0;i<maIdCheckbox.length;i++){ 
		maIdCheckbox[i].checked=_check;
	}
}

function doDeleteItems(){
	var maIdCheckbox=document.getElementsByName("maIdCheckbox");
	//alert(maIdCheckbox.length);
	var maIds="";
	for(var i=0;i<maIdCheckbox.length;i++){
		 if(maIdCheckbox[i].checked)
			 maIds=maIds+maIdCheckbox[i].value+",";
	}
	 
	maIds=maIds.substring(0, maIds.length-1);
	if(maIds.length>0){
		//alert(maIds);
		$( "#dialog-confirmDelete" ).dialog({
			/* height: 140, */
			modal: true,
			buttons: {
				"Yes": function() { 
					$( this ).dialog( "close" );
					doAction("deleteItems",maIds);
				},
				"No": function() {
					$( this ).dialog( "close" );
					return false;
				}
			}
		});
	}
// 	alert(maIds)
	/*   $("#maId").val(id);
	$.post("series/search",$("#seriesForm").serialize(), function(data) {
		  // alert(data);
		    appendContent(data);
		  // alert($("#_content").html());
		}); */
}
function doCreateCandidate(company_id){
	//alert(company_id);
	//alert($("#amount").val());
	$("#amount").val(amount_G.val());
	//alert($("#mssery_candidate").val());
	//alert($("select[id=mssery_candidate] option:selected").val());
	$("#mssery_candidate_hidden").val($("select[id=mssery_candidate] option:selected").val());
	$("#company_candidate_hidden").val(company_id);	
	$.get("company/candidate/create",$("#create_candidate_form").serialize(), function(data) {
		   if(data.updateRecord!=0){
			   $("#message_candidate_create").html("<strong>Add Candidate Success</strong>");
			   $("#dialog-create-candidate-alert").css("display","block");
		   }
		 //  alert(data.updateRecord);
		 /*   var myJSONText = JSON.stringify(data, replacer);
		   alert(myJSONText)  */
		   //  appendContent(data);
		  // alert($("#_content").html());
	});
}
function replacer(key, value) {
    if (typeof value === 'number' && !isFinite(value)) {
        return String(value);
    }
    return value;
}
function createCandidate(company_id){
 //alert();
 //$("div[class='ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix']").removeClass("ui-dialog-titlebar");
/*  var dialogClass = $( "#dialog-create-candidate" ).dialog( "option", "dialogClass" );
 alert(dialogClass.ui-dialog-titlebar) */
	$( "#dialog-create-candidate" ).dialog({
		/* height: 140, */
		modal: true,
		buttons: {
			"Yes": function() { 
				$( this ).dialog( "close" );
				doCreateCandidate(company_id);
			},
			"No": function() {
				$( this ).dialog( "close" );
				return false;
			}
		}
	});
	
// 	$("div[class='ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix']").css("background:url","http://www.elementalsurface.com/beech-wood.jpg");
}
function confirmDelete(mode,id){
	$( "#dialog-confirmDelete" ).dialog({
		/* height: 140, */
		modal: true,
		buttons: {
			"Yes": function() { 
				$( this ).dialog( "close" );
				doAction(mode,id);
			},
			"No": function() {
				$( this ).dialog( "close" );
				return false;
			}
		}
	});
}
function doAction(mode,id){
	$("#mode").val(mode);
	if(mode=='deleteItems'){
		$("#maIdArray").val(id);
	}else if(mode!='search'){
		$("#maId").val(id);
	}else {
		$("#maId").val("0");
	}
	$.post("company/search",$("#companyForm").serialize(), function(data) {
		  // alert(data);
		    appendContent(data);
		  // alert($("#_content").html());
		});
}
</script>
 <div id="dialog-confirmDelete" title="Delete Company" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
	Are you sure you want to delete Company ?
</div>
 <div id="dialog-create-candidate-alert"class="alert alert-success" style="display: none;">
    <button class="close" data-dismiss="alert"><span style="font-size: 12px">x</span></button>
    <span id="message_candidate_create"></span> 
  </div>
 <!-- <div id="dialog-create-candidate" title="Create Candidate" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)"> -->
 <div id="dialog-create-candidate" title="Create Candidate" style="display: none;background:'')" class="ui-dialog-titlebar2">
	<form id="create_candidate_form" name="create_candidate_form">
	    <input type="hidden" name="mssery_candidate_hidden" id="mssery_candidate_hidden">
	    <input type="hidden" name="company_candidate_hidden" id="company_candidate_hidden">
	    Candidate&nbsp;:&nbsp;<input type="text" name="amount" id="amount" style="width: 50px" />&nbsp;&nbsp;person<br/>
	    Series&nbsp;:&nbsp;<select id="mssery_candidate"> 
	         <c:forEach items="${missSeries}" var="missSery" varStatus="loop"> 
	    					 			 <option value="<c:out value="${missSery.msId}"></c:out>"><c:out value="${missSery.msSeriesName}"></c:out></option>
	    					 	 </c:forEach>
	    	<%-- <form:options itemValue="msId" itemLabel="msSeriesName" items="${missSeries}"/> --%>
	     </select>
	</form>
</div>
	    <fieldset style="font-family: sans-serif;">  
           <!-- <legend  style="font-size: 13px">Criteria</legend> -->
           <!-- <div style="position:relative;right:-94%;">  </div> --> 
         <!--  <form class="well" style="border:2px solid #DDD"> -->
           <form:form  id="companyForm" name="companyForm" modelAttribute="companyForm" cssClass="well" cssStyle="border:2px solid #DDD" method="post" action="">
            <form:hidden path="mode"/>
            <form:hidden path="missAccount.maId" id="maId"/>
            <form:hidden path="maIdArray" id="maIdArray"/>
            <form:hidden path="paging.pageNo" id="pageNo"/>
            <form:hidden path="paging.pageSize" id="pageSize"/> 
            <form:hidden path="pageCount" id="pageCount"/> 
              <table border="0" width="100%" style="font-size: 13px">
              				<tr>
	    					 <td align="left" width="100%" colspan="6"><strong>Company Search</strong></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Register Type:</td>
	    					 <td align="left" width="17%">    
	    					   	<form:select path="maRegisterType">
	    					   		<form:option value="-1">-- select type --</form:option>
	    					   		<form:option value="Company">Company</form:option>
	    					   	</form:select>				
	    					 </td>
	    					<td align="left" width="17%">Register No:</td>
	    					<td align="left" width="17%">
	    					<form:input path="maRegisterNo"/>
	    					<!-- <input type="text" name="registerNo"/> -->
	    					</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Register From:</td>
	    					 <td align="left" width="17%"> 
	    					 <!-- <input type="text" id="datepicker_from"  style="width:75px"/> -->
	    					 <form:input path="maRegisterFrom" cssStyle="width:75px"/> 
	    					 </td>
	    					<td align="left" width="17%">Register To:</td>
	    					<td align="left" width="17%">
	    					<!-- <input type="text" id="datepicker_to"  style="width:75px" /> -->
	    					<form:input path="maRegisterTo" cssStyle="width:75px"/>
	    					</td> 
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Contact Name:</td>
	    					 <td align="left" width="17%"> 
	    					 <!--  <input type="text" name="registerNo" /> -->
	    					  <form:input path="maContactName"/>
	    					 </td>
	    					<td align="left" width="17%">Daytime Phone:</td>
	    					<td align="left" width="17%">
	    					
	    					 <form:input path="maDayTimePhone"/>
	    					<!-- <input type="text" name="registerNo"  /> -->
	    					</td> 
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Company Name:</td>
	    					 <td align="left" colspan="3" width="51%">    					
	    						<!-- <input type="text" name="registerNo"   style="width: 100%"/> -->
	    						 <form:input path="maName" cssStyle="width:100%"/>
	    					 </td> 
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr valign="top">
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Purchased Test:</td>
	    					 <td align="left"  colspan="3" width="51%">    					
	    						 <span style="display: block;">
	    					  		<c:forEach items="${missExams}" var="missExam" varStatus="loop"> 
	    					 			 <input type="checkbox" value="<c:out value="${missExam.meId}"></c:out>" name="missExam_selectbox"/><c:out value="${missExam.meName}"></c:out>&nbsp;&nbsp;
	    					 		</c:forEach>
	    					  </span>
	    					   <input type="hidden" id="meIdArray" value="${meIdArray}">
	    						<!-- <input type="checkbox"/>4FT	&nbsp;&nbsp;<input type="checkbox"/>EPT	&nbsp;&nbsp;<input type="checkbox"/>EST	&nbsp;&nbsp;<input type="checkbox"/>MCCT	&nbsp;&nbsp; -->
	    					 </td>
	    					
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					</table> 
	    					</form:form>
	    					<table  border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="left" width="50%">
	    					
	    					<a class="btn btn-primary" onclick="loadDynamicPage('company/new')"><i class="icon-plus-sign icon-white"></i>&nbsp;Create</a>&nbsp;
	    					<a class="btn btn-danger"  onclick="doDeleteItems()"><i class="icon-trash icon-white"></i>&nbsp;Delete</a></td>
	    					<td align="right" width="50%">
	    					
	    					<a  onclick="goPrev()">Prev</a>&nbsp;|&nbsp;<span id="pageElement"></span>&nbsp;|&nbsp;<a  onclick="goNext()">Next</a>&nbsp;<a  class="btn btn-primary"  onclick="doAction('search','0')"><i class="icon-search icon-white"></i>&nbsp;Search</a></td>
	    					</tr>
	    					</table> 
		<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="5%"><div class="th_class"><input type="checkbox" id="maIdCheckboxAll" onclick="toggleCheckbox()"/></div></th>
            		<th width="20%"><div class="th_class">Register Type</div></th> 
            		<th width="25%"><div class="th_class">Name</div></th>
            		<th width="10%"><div class="th_class">Phone</div></th> 
            		<th width="10%"><div class="th_class">Register No</div></th>
            		<th width="10%"><div class="th_class">Register Date</div></th> 
            		<th width="5%"><div class="th_class">Total Unit</div></th>
            		<th width="5%"><div class="th_class">Create Candidate</div></th> 
            		<th width="5%"><div class="th_class">Edit</div></th>
            		<th width="5%"><div class="th_class">Delete</div></th> 
          		</tr>
        	</thead>
        	<tbody>
        	 <c:forEach items="${missAccounts}" var="missAccount" varStatus="loop"> 
          	<tr>
            	<td><input type="checkbox" name="maIdCheckbox" value="${missAccount.maId}"/></td>
            	<td>&nbsp;${missAccount.maRegisterType}</td>
            	<td>&nbsp;${missAccount.maName}</td>
            	<td>&nbsp;${missAccount.maPhone}</td>
            	<td>&nbsp;${missAccount.maRegisterNo}</td>
            	<td>&nbsp;<fmt:formatDate pattern="dd/MM/yyyy hh:ss" value="${missAccount.maRegisterDate}" />
            	</td>
            	<td>&nbsp;${missAccount.maTotalUnit}</td>
            	<td style="text-align: center;"><i  title="Create" style="cursor: pointer;" onclick="createCandidate('${missAccount.maId}')" class="icon-user"></i></td>
            	<td style="text-align: center;"><i  title="Edit" style="cursor: pointer;" onclick="loadDynamicPage('company/item/${missAccount.maId}')"  class="icon-edit"></i></td>
            	<td style="text-align: center;"><i  title="Delete"  style="cursor: pointer;"  onclick="confirmDelete('delete','${missAccount.maId}')" class="icon-trash"></i></td> 
          	</tr>
          	</c:forEach>
        <!--   <tr>
            	<td><input type="checkbox" /></td>
            	<td>Company</td>
            	<td>Company A chatchai pimtun co.th make dev aoe</td>
            	<td>0848810484</td>
            	<td>M000000</td>
            	<td>D/M/Y h:m</td>
            	<td>300</td>
            	<td>xx</td>
            	<td><a  onclick="loadDynamicPage('company/account/2')">xx</a></td>
            	<td>xx</td> 
          	</tr><tr>
            	<td><input type="checkbox" /></td>
            	<td>Company</td>
            	<td>Company A</td>
            	<td>0848810484</td>
            	<td>M000000</td>
            	<td>D/M/Y h:m</td>
            	<td>300</td>
            	<td>xx</td>
            	<td><a onclick="loadDynamicPage('company/account/3')" >xx</a></td>
            	<td>xx</td> 
          	</tr> -->
        	</tbody>
      </table>
</fieldset>