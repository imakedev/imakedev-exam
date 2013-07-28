<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script>
var intRegex = /^\d+$/;
//var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+)|(-\d+(\.\d *)?)|((-\d*\.)?\d+))$/;
$(document).ready(function() {

}); 
function updateParticipants(){  
	var isBreak=false;
		$("input[id^=participants_number_]").each(function(){
          //  alert(this.id);
            if(jQuery.trim($("#participants_group_"+this.id.split("_")[2]).val()).length>0){
            	var isBank=checkBank(jQuery.trim(this.value));
            	if(isBank){
            		 alert("Please fill Data !!!");
            		 $(this).focus();
            		 isBreak=true;
            		 return false; 
            	} 
            	  var isNumber=checkNumber(jQuery.trim(this.value));
               	  if(isNumber){
               		 alert('Please fill Number !!!');  
               		 $(this).focus();
               		isBreak=true;
               		 return false; 
               	  } 
            } 
       }); 
 if(!isBreak){
	$.post("series/update/participantSection/${participant_msId}", $("#seriesParticipantsForm").serialize(),function(data) {
	      // appendContent(data);
	      alert("Update Success.");
	});
 }
}
function checkNumber(txtVal){
	// alert(txtVal) 
	 if(!(intRegex.test(txtVal) || floatRegex.test(txtVal))) {
	      //  alert('Please fill Number !!!');
	      return true;
	    }
	 return false;
 } 
function checkBank(txtVal){
	 if(txtVal.length==0){
	      //  alert('Please fill Number !!!');
	      return true;
	    }
	 return false;
}

</script> 
 <form  id="surveyParticipantsForm" name="surveyParticipantsForm" method="post" action="">
	<input type="hidden" id="participant_msId" name="participant_msId" value="${participant_msId}"/>
       <table  border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="left" width="100%">
	    					<strong>Participants</strong>
	    					</td>
	    					</tr>
	    					</table> 
	    					<c:forEach items="${missSeriesParticipantsMaps}" var="missSeriesParticipantsMap" varStatus="loop"> 
	    					    <div align="center">กลุ่ม&nbsp;&nbsp;<strong style="text-decoration: underline;">${missSeriesParticipantsMap.mspmGroupName}</strong>
	    					     &nbsp;&nbsp;จำนวนสุ่ม : <input type="text" style="width: 35px" name="_sample" id="_sample"/>
	    					      &nbsp;&nbsp;จำนวนที่ต้องการจะส่ง : <input type="text" style="width: 35px" name="amountSend" id="amountSend"/> 
	    					    &nbsp;&nbsp;<a class="btn btn-primary"  style="margin-top:-10px" onclick="setSample('dataTable')"><span style="color: white;font-weight: bold;">Set</span></a>
	    					     </div>
	    						<table class="table stable-striped table-bordered table-condensed" border="0" style="font-size: 12px">
        							<thead>
          								<tr>
            								<th width="5%"><div class="th_class">#</div></th> 
            								<th width="50%"><div class="th_class">Email</div></th>
            								<th width="45%"><div class="th_class">Name</div></th>
						          		</tr>
        							</thead>
        							<tbody>
        							<c:forEach var="i" begin="1" end="${missSeriesParticipantsMap.mspmGroupAmount}"> 
          								<tr>
            								<td>${i}</td> 
            	 							<td>
            	 								<div><input type="text" style="width: 400px"   name="survey_email" /></div>
	    									</td>
            								<td>
            								<div align="right"><input style="width: 400px;" type="text" name="survey_name" /></div>
            								<input style="width: 400px;" type="hidden" name="survey_group" value="${missSeriesParticipantsMap.mspmGroupName}" />
            								</td> 
          								</tr>
          							</c:forEach> 
        							</tbody>
     							 </table>
     							 <br/>
	    					</c:forEach> 
     				 </form>