<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
//	$('#tabs').tabs();
  //   $("fieldset.collapsibleClosed").collapse( { closed : true } );
//	$('#tabs').tabs('select', parseInt($("#_test_section").val()));
	/* $('#tabs').bind('tabsselect', function(event, ui) {

	    // Objects available in the function context:
	  
	   //alert("index="+ui.index+",panel="+ui.panel+",tab="+ui.tab)
	   if(ui.index==2){
		   alert($("#_meId").val())
		   $("#tabs-3").html("");
	   }

	}); */
});
function doRefill(){
	 $.get("company/item/refile/${companyForm.missAccount.maId}/"+$("#refill").val(), function(data) {
			 $("#maTotalUnitElement").html(data.maTotalUnit);
			 $("#maUsedUnitElement").html(data.maUsedUnit);
			 $("#maAvailableUnitElement").html(data.maAvailableUnit);
			 $("#refill").val("");
		 //  alert(data.updateRecord);
		 /*   var myJSONText = JSON.stringify(data, replacer);
		   alert(myJSONText)  */
		   //  appendContent(data);
		  // alert($("#_content").html());
	});
}
</script>
<form:form  id="companyForm_unit" name="companyForm_unit" modelAttribute="companyForm" cssClass="well"  method="post" action="">
			  <fieldset style="font-family: sans-serif;">   
	     <h6><strong>Company - Unit</strong></h6> 
			   <div>
			    <table border="0" width="100%" style="font-size: 12px">
			    	<!-- <tr>
    					<td width="100%" colspan="3"><strong>Company Unit</strong></td>
    				</tr> -->
   		 			<tr valign="top">
    					<td width="20%">&nbsp;</td>
    					<td width="60%">Total Unit: <span id="maTotalUnitElement">${companyForm.missAccount.maTotalUnit}</span></td>
    					 <td width="20%">&nbsp;</td> 
    				</tr>
    				<tr valign="top">
    					<td width="20%">&nbsp;</td>
    					<td width="60%">Used Unit: <span id="maUsedUnitElement">${companyForm.missAccount.maUsedUnit}</span></td>
    					 <td width="20%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="20%">&nbsp;</td>
    					<td width="60%">Available Unit: <span id="maAvailableUnitElement">${companyForm.missAccount.maAvailableUnit}</span></td>
    					 <td width="20%">&nbsp;</td>
    				</tr>
    				<tr valign="top">
    					<td width="20%">&nbsp;</td>
    					<td width="60%">Re-fill <form:input path="refill"/><input type="button" value="Re-fill" onclick="doRefill()"></td>
    					 <td width="20%">&nbsp;</td>
    				</tr>
    				
    			</table>
    			 
    			</div>
    			 </fieldset>
    			 </form:form>
    			<div>
    			 <table class="table table-striped table-bordered table-condensed" border="0" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="15%"><div class="th_class">Series</div></th> 
            		<th width="60%"><div class="th_class">Group</div></th>
            		<th width="5%"><div class="th_class">Unit</div></th> 
            		<th width="10%"><div class="th_class">Available</div></th>
            		<th width="10%"><div class="th_class">Order</div></th> 
          		</tr>
        	</thead>
        	<tbody>
        	 <c:forEach items="${companyForm.missAccount.missAccountSeriesMapList}" var="missAccountSeriesMap" varStatus="loop"> 
        	 	<tr>
            	<td>${missAccountSeriesMap.seryName}</td>
            	<td>${missAccountSeriesMap.groupStr}</td>
            	<td>${missAccountSeriesMap.seryUnit}</td>
            	<td>${missAccountSeriesMap.masmAvailable}</td>
            	<td><input type="text"></td> 
          	</tr>
        	 </c:forEach>
          	<!-- <tr>
            	<td>Series1</td>
            	<td>Personality Aptitude</td>
            	<td>2</td>
            	<td>20</td>
            	<td><input type="text"></td> 
          	</tr>
          	<tr>
            	<td>Series2</td>
            	<td>Motivation Management</td>
            	<td>2</td>
            	<td>20</td>
            	<td><input type="text"></td> 
          	</tr>
          	<tr>
            	<td>Survey</td>
            	<td>Survey</td>
            	<td>2</td>
            	<td>30</td>
            	<td><input type="text"></td> 
          	</tr> -->
    				</table>
    				</div>
			
			<!-- <div align="center"><input type="button" class="btn" value="Order"/></div> onclick="doAction('action','companyForm_unit','7')"-->
			<div align="center"><a class="btn btn-primary" ><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Order</span></a></div>