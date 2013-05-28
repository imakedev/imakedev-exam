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
<%-- 
<div style="display: none"> 
  <form  id="seriesManagementSectionForm" name="seriesManagementSectionForm" method="post" action="">
  	<input type="hidden" id="mraReportName_section" name="mraReportName_section"/>
  	<input type="hidden" id="msId_section" name="msId_section"/>
  	<input type="hidden" id="msOrder_section" name="msOrder_section"/>
  	<input type="hidden" id="mraLang_section" name="mraLang_section"/>
  	 
  </form>
</div>      
--%>  <form  id="seriesParticipantsForm" name="seriesParticipantsForm" method="post" action="">
	<input type="hidden" id="participant_msId" name="participant_msId" value="${participant_msId}"/>
       <table  border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="left" width="100%">
	    					<strong>Participants</strong>
	    					</td>
	    					</tr>
	    					</table>
	    					<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="5%"><div class="th_class">#</div></th> 
            		<th width="80%"><div class="th_class">Group Name</div></th>
            		<th width="15%"><div class="th_class">Require Number</div></th>
            		<!--  <th width="4%"><div class="th_class"></div></th>  -->       
            		       		        		 
          		</tr>
        	</thead>
        	<tbody>
        	<c:forEach items="${missSeriesParticipantsMaps}" var="missSeriesParticipantsMap" varStatus="loop"> 
          	<tr>
            	<td>${(loop.index+1)}</td> 
            	 <td>
            	 <div><input type="text" style="width: 400px" id="participants_group_${(loop.index+1)}" name="participants_group_${(loop.index+1)}"  value="${missSeriesParticipantsMap.mspmGroupName}"/></div>
	    				</td>
            	<td>
            	<div align="right"><input style="width: 40px;text-align: right;" type="text" name="participants_number_${(loop.index+1)}" id="participants_number_${(loop.index+1)}" value="${missSeriesParticipantsMap.mspmGroupAmount}"/></div></td> 
          	</tr>
          	</c:forEach>
          	<%-- 
          	<tr>
            	<td>2</td> 
            	 <td>
            	 <div><input type="text" id="participants_group_2" value=""/></div>
	    				</td>
            	<td>
            	<div align="right"><input type="text" id="participants_number_2" value=""/></div></td> 
          	</tr>
          	<tr>
            	<td>3</td> 
            	 <td>
            	 <div><input type="text" id="participants_group_3" value=""/></div>
	    				</td>
            	<td>
            	<div align="right"><input type="text" id="participants_number_3" value=""/></div></td> 
          	</tr>
          	<tr>
            	<td>4</td> 
            	 <td>
            	 <div><input type="text" id="participants_group_4" value=""/></div>
	    				</td>
            	<td>
            	<div align="right"><input type="text" id="participants_number_4" value=""/></div></td> 
          	</tr>
          <tr>
            	<td>5</td> 
            	 <td>
            	 <div><input type="text" id="participants_group_5" value=""/></div>
	    				</td>
            	<td>
            	<div align="right"><input type="text" id="participants_number_5" value=""/></div></td> 
          	</tr>
          	--%>
        	</tbody>
      </table>
       <div align="center"><a class="btn btn-primary"  onclick="updateParticipants()"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save Participants</span></a></div>
      </form>