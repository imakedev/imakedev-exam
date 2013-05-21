<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGE_MISSCONSULT')" var="isManageMC"/>
<script type="text/javascript" src="<c:url value='/resources/FusionCharts/FusionCharts.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.dateFormat-1.0.js'/>"></script>   
<script type="text/javascript">
 var color_G=["#B9DDF8","#F6BD0F","#8BBA00","#FF934E","#059090","#D64646","#914B91","#618C32"];
 var color_G2=["B9DDF8","F6BD0F","8BBA00","FF934E","059090","D64646","914B91","618C32"]; 
 var mtrIds_G;
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
	$("input[id=mcaUsername],[id=mcaFirstName],[id=mcaLastName],[id=mcaPosition],[id=mcaDepartment],[id=mcaCompanyName]").keypress(function(event) {
		// $(document).keypress(function(event) {
		 	  if ( event.which == 13 ) {
		 	     event.preventDefault();
		 	    	doAction('search','0');
		 	   }
		 }); 
	$("#sortItemSelect").val($("#orderBy").val());
	$("#sortOrderSelect").val($("#sortBy").val()); 
	  
	
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
	if(mode=='deleteItems' ||mode=='ignoreItems' ){
		$("#mtrIdArray").val(id);
	}else if(mode!='search'){
		$("#mtrId").val(id);
	}
	else {
		$("#mtrId").val("0");
	}
   $("#orderBy").val($("#sortItemSelect").val());
   $("#sortBy").val($("#sortOrderSelect").val()); 
	$.post("result/search",$("#resultForm").serialize(), function(data) {
		  // alert(data);
		    appendContent(data);
		  // alert($("#_content").html());
		});
}
function toggleCheckbox(){
	var _check=document.getElementById("mtrIdCheckboxAll").checked;
	var mtrIdCheckbox=document.getElementsByName("mtrIdCheckbox"); 
	for(var i=0;i<mtrIdCheckbox.length;i++){ 
		mtrIdCheckbox[i].checked=_check;
	}
}
function exportTest(){
	var src = "result/export";
	//alert(src)
	var mtrIdCheckbox=document.getElementsByName("mtrIdCheckbox");
	//alert(mtrIdCheckbox.length);
	var mtrIds="";
	for(var i=0;i<mtrIdCheckbox.length;i++){
		 if(mtrIdCheckbox[i].checked)
			 mtrIds=mtrIds+mtrIdCheckbox[i].value+",";
	}
	 
	mtrIds=mtrIds.substring(0, mtrIds.length-1);
	if(!(mtrIds.length>0)){
		mtrIds="-1";
	}
	if(!(mtrIds.length>0)){
		//alert(mtrIds);dialog-empty
		$( "#dialog-empty" ).dialog({
			/* height: 140, */
			modal: true,
			buttons: {
				"Ok": function() { 
					$( this ).dialog( "close" );
				} 
			}
		});
	}else{
		 
		src=src+"?id="+mtrIds+"&mcaSeries="+$("#mcaSeries").val()+"&orderBy="+$("#sortItemSelect").val()+"&sortBy="+$("#sortOrderSelect").val();
	     var div = document.createElement("div");
	    document.body.appendChild(div);
	    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";  
	}
	
}
function compareTest(){
	//$("#dialog-compare").html();
	var mtrIdCheckbox=document.getElementsByName("mtrIdCheckbox");
	var index=0;
	var mtrIds="";
	for(var i=0;i<mtrIdCheckbox.length;i++){
		 if(mtrIdCheckbox[i].checked){
			 mtrIds=mtrIds+mtrIdCheckbox[i].value+"_";
			 index++;
		 }
	} 
	mtrIds_G=mtrIds;
	if(index!=2){
		alert("Candidate must be two.");
		return false;
	}
	$.ajax({
		  type: "get",
		  url: "result/compare/"+$("#mcaSeries").val()+"/"+mtrIds,
		  cache: false
		}).done(function( data ) {
			//alert(data)
				//missTestResult.setMissCandidate(candidate);
			if(data!=null){  
				/* alert(data[0].missCandidate.mcaFirstName)  
				alert(data[0].missCandidate.mcaFirstName)  
				alert(data[0].missCandidate.mcaFirstName) */  
				/* if(data.mcaId!=null){
					
				} */
				 
				//alert($.datepicker.formatDate("dd/MM/yyyy HH:mm",data[0].missCandidate.mtrStartTime));
            	/* <td>&nbsp;<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${missTestResult.mtrStartTime}" /></td>  */
             
    			var mcaTitle1="";
    				if(data[0].missCandidate.mcaTitleType!=null){
    					if(data[0].missCandidate.mcaTitleType=='0')
    						mcaTitle1="นาย";
    				}else if(data[0].missCandidate.mcaTitleType!=null){
    					if(data[0].missCandidate.mcaTitleType=='1')
    						mcaTitle1="นาง";
    				}else if(data[0].missCandidate.mcaTitleType!=null){
    					if(data[0].missCandidate.mcaTitleType=='2')
    						mcaTitle1="นางสาว";
    				}else if(data[0].missCandidate.mcaTitleType!=null){
    					if(data[0].missCandidate.mcaTitleType=='3')
    						mcaTitle1="";
    				}
    				mcaTitle1="";
				$("#profile1").html(mcaTitle1 +" "+data[0].missCandidate.mcaFirstName+" "+data[0].missCandidate.mcaLastName);
				var mcaTitle2="";
				if(data[1].missCandidate.mcaTitleType!=null){
					if(data[1].missCandidate.mcaTitleType=='0')
						mcaTitle2="นาย";
				}else if(data[1].missCandidate.mcaTitleType!=null){
					if(data[1].missCandidate.mcaTitleType=='1')
						mcaTitle2="นาง";
				}else if(data[1].missCandidate.mcaTitleType!=null){
					if(data[1].missCandidate.mcaTitleType=='2')
						mcaTitle2="นางสาว";
				}else if(data[1].missCandidate.mcaTitleType!=null){
					if(data[1].missCandidate.mcaTitleType=='3')
						mcaTitle2="";
				}
				mcaTitle2="";
				$("#profile2").html(mcaTitle2 +" "+data[1].missCandidate.mcaFirstName+" "+data[1].missCandidate.mcaLastName);
				var mcaGender1="";
				if(data[0].missCandidate.mcaGender=='0')
					mcaGender1="Female";
				else if(data[0].missCandidate.mcaGender=='1')
					mcaGender1="Male";
				$("#gender1").html(mcaGender1);
				
				var mcaGender2="";
				if(data[1].missCandidate.mcaGender=='0')
					mcaGender2="Female";
				else if(data[1].missCandidate.mcaGender=='1')
					mcaGender2="Male";
				$("#gender2").html(mcaGender2);
				$("#age1").html(data[0].missCandidate.age);
				$("#age2").html(data[1].missCandidate.age);
				$("#careerGroup1").html(data[0].missCandidate.missCareerMaster.mcmName);
				$("#careerGroup2").html(data[1].missCandidate.missCareerMaster.mcmName);
				$("#position1").html(data[0].missCandidate.mcaPosition);
				$("#position2").html(data[1].missCandidate.mcaPosition);
				$("#department1").html(data[0].missCandidate.mcaDepartment);
				$("#department2").html(data[1].missCandidate.mcaDepartment);
				$("#phone1").html(data[0].missCandidate.mcaPhone);
				$("#phone2").html(data[1].missCandidate.mcaPhone); 
				$("#industryType1").html(data[0].missCandidate.missIndustryMaster.mimName);
				$("#industryType2").html(data[1].missCandidate.missIndustryMaster.mimName); 
				//alert(data[0].mtrStartTime)
				//alert($.format.date(data[0].mtrStartTime, "dd/MM/yyyy HH:mm"));
				$("#testDate1").html($.format.date(data[0].mtrStartTime, "dd/MM/yyyy HH:mm"));
				$("#testDate2").html($.format.date(data[1].mtrStartTime, "dd/MM/yyyy HH:mm"));
				$("#result1").html("<strong>"+data[0].mtrResultCode+"</strong>"); 
				$("#result2").html("<strong>"+data[1].mtrResultCode+"</strong>"); 
				
				//alert(data[2])
				var table_header="<table class=\"table table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\">"+
								"<thead><tr>"; 
								for(var i=0;i<data[2].length;i++){
									table_header=table_header+"<th>"+data[2][i]+"</th>";
								}
					table_header=table_header+	
								"<tr>"+
								"</thead>";
				var table_value1=table_header+"<tbody><tr>";
				var chart_value1="";
				for(var i=0;i<data[0].axisValues.length;i++){
									table_value1=table_value1+"<td>"+data[0].axisValues[i]+"</td>";
									chart_value1=chart_value1+ "<set label='"+data[2][i]+"' value='"+data[0].axisValues[i]+"' color='"+color_G2[i]+"'/>";
				}	 
								table_value1=table_value1+"</table>";
				$("#compare_score1").html(table_value1);
				
				var table_value2=table_header+"<tbody><tr>";
				var chart_value2="";
				for(var i=0;i<data[1].axisValues.length;i++){
					table_value2=table_value2+"<td>"+data[1].axisValues[i]+"</td>";
					chart_value2=chart_value2+ "<set label='"+data[2][i]+"' value='"+data[1].axisValues[i]+"' color='"+color_G2[i]+"'/>"; 
				}	 
				table_value2=table_value2+"</table>";
				$("#compare_score2").html(table_value2); 
				/* #B9DDF8
				#F6BD0F
				#8BBA00
				#FF934E
				#059090
				#D64646
				#914B91
				#618C32 */
				//var dataString ="<chart caption='Fruit Production for March' subCaption='(in Millions)' yAxisName='Quantity' xAxisName='Fruit' alternateVGridColor='AFD8F8' baseFontColor='114B78' toolTipBorderColor='114B78' toolTipBgColor='E7EFF6' useRoundEdges='1' showBorder='0' bgColor='FFFFFF,FFFFFF'>"+
				var dataString ="<chart   alternateVGridColor='AFD8F8' baseFontColor='114B78' "+
				" toolTipBorderColor='114B78' toolTipBgColor='E7EFF6' useRoundEdges='1' showBorder='0' bgColor='FFFFFF,FFFFFF'>";
				chart_value1=dataString+chart_value1+"</chart> ";
				chart_value2=dataString+chart_value2+"</chart> "; 
		       /*  "<set label='Orange' value='23' color='AFD8F8'/>"+
		        "<set label='Apple' value='12' color='F6BD0F'/> "+
		       " <set label='Banana' value='17' color='8BBA00'/> "+
		        "<set label='Mango' value='14'  color='A66EDD'/> "+
		        "<set label='Litchi' value='12'  color='F984A1'/>"+
		        "<set label='Litchi' value='12'  color='F984A1'/>"+
		        "<set label='Apple' value='12' color='F6BD0F'/> "+
			       " <set label='Banana' value='17' color='8BBA00'/> "+ */
			
				  var myChart1 = new FusionCharts("<c:url value='/resources/FusionCharts/Column2D.swf'/>", "myChartId1", "600", "300", "0", "1"); // for old version
					//myChart.setDataXML(xml);
				   // alert(myChart1);
				   myChart1.setDataXML(chart_value1);
				    myChart1.render("chartdiv1");
				    
				    var myChart2 = new FusionCharts("<c:url value='/resources/FusionCharts/Column2D.swf'/>", "myChartId2", "600", "300", "0", "1"); // for old version
					//myChart.setDataXML(xml);
				   // alert(myChart1);
				   myChart2.setDataXML(chart_value2);
				    myChart2.render("chartdiv2");
				/* <table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
    			<thead>
      				<tr>
      					<th>x1</th><th>x2</th>
      				</tr>
      			</thead> 
      			<tbody> 
    	  			<tr>
      					<td>x1</td><td>x2</td>
      				</tr>
    	  		</tbody>
    	  	</table> */
				$( "#dialog-compare" ).dialog({
					/* height: 140, */
				//	width:603,
					position:{ my: "top", at: "top", of: window },
					width:1252,
					//height:603,
					height:640, 
					modal: true,
					buttons: {
						/* "Yes": function() { 
							$( this ).dialog( "close" );
							//doAction("ignoreItems",mtrIds);
						}, */
						"No": function() {
							$( this ).dialog( "close" );
							return false;
						}
					}
				});
			}
		}); 
}
 
function setIgnore(){
	var mtrIdCheckbox=document.getElementsByName("mtrIdCheckbox");
	//alert(mtrIdCheckbox.length);
	var mtrIds="";
	for(var i=0;i<mtrIdCheckbox.length;i++){
		 if(mtrIdCheckbox[i].checked)
			 mtrIds=mtrIds+mtrIdCheckbox[i].value+",";
	} 
	mtrIds=mtrIds.substring(0, mtrIds.length-1);
	if(mtrIds.length>0){
		//alert(mtrIds);
		$( "#dialog-confirmIgnore" ).dialog({
			/* height: 140, */
			modal: true,
			buttons: {
				"Yes": function() { 
					$( this ).dialog( "close" );
					doAction("ignoreItems",mtrIds);
				},
				"No": function() {
					$( this ).dialog( "close" );
					return false;
				}
			}
		});
	}else{
		$( "#dialog-empty" ).dialog({
			/* height: 140, */
			modal: true,
			buttons: {
				"Ok": function() { 
					$( this ).dialog( "close" );
				} 
			}
		});
	} 
}
function exportComparePDF(){
	//mtrIds_G $("#mcaSeries").val() 
	//var src = "/MISSProcessImage/compareTest?mcaSeries="+$("#mcaSeries").val()+"&mtrIds="+mtrIds_G;
	var src = "/MISSExamBackOffice/reportExport/compareexport?mcaSeries="+$("#mcaSeries").val()+"&mtrIds="+mtrIds_G;
	
	//alert(src)
	var div = document.createElement("div");
    document.body.appendChild(div);
    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";
}
function exportReportPDF(_mtrId,_meId,_msId,_mcaId,_msOrder,_mraLang){
	//mtrIds_G $("#mcaSeries").val() 
	//var src = "/MISSProcessImage/compareTest?mcaSeries="+$("#mcaSeries").val()+"&mtrIds="+mtrIds_G;
	var src = "/MISSExamBackOffice/result/testPDF?mtrId="+_mtrId+"&meId="+_meId+"&msId="+_msId+"&mcaId="+_mcaId+"&msOrder="+_msOrder+"&mraLang="+_mraLang;
	
	//alert(src)
	var div = document.createElement("div");
    document.body.appendChild(div);
    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";
}
function showFormDownload(_mtrId,_meId,_msId,_mcaId){
	<%--
	<c:url value="/result/testPDF" var="downloadUrl">
	<c:param name="mtrId" value="${missTestResult.mtrId}"></c:param>
	<c:param name="meId" value="${missTestResult.meId}"></c:param>
	<c:param name="msId" value="${missTestResult.msId}"></c:param>
	<c:param name="mcaId" value="${missTestResult.missCandidate.mcaId}"></c:param>
</c:url>
--%>
	   $("#dialog-download-report-element").html("");
	//	var _str_table=	"<div id=\"dialog-download-report-element\"><form id=\"report_template_role_form\" name=\"report_template_role_form\">"+
		var _str_table=	"<form id=\"report_template_role_form\" name=\"report_template_role_form\">"+
	    "	<table id=\"table_role_report_list\"  class=\"table stable-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\">"+
     "	<thead>"+
     "  		<tr>"+
     "    		<th width=\"6%\"><div class=\"th_class\">#</div></th>"+ 
     "    		<th width=\"47%\"><div class=\"th_class\">Thai</div></th>"+ 
     "    		<th width=\"47%\"><div class=\"th_class\">English</div></th>"+             		 
     "  		</tr>"+
     "	</thead>"+
     "	<tbody>"; 
      
	 $.get("role/get/reportDownload/"+_msId, function(data) {
		 var height_dialog=100;
		// alert(data)
		if(data!=null && data.length>0){
			for(var i=0;i<data.length;i++){
				_str_table=_str_table+"<tr>"+  
				"<td>&nbsp;"+(i+1)+"</td>"+
         					"<td>&nbsp;<span onclick=\"exportReportPDF('"+_mtrId+"','"+_meId+"','"+_msId+"','"+_mcaId+"','"+data[i][0].msOrder+"','"+data[i][0].mraLang+"')\" style=\"color: #08c;cursor: pointer;\">"+data[i][0].mraReportName+"</span></td>"+
         					"<td>&nbsp;<span onclick=\"exportReportPDF('"+_mtrId+"','"+_meId+"','"+_msId+"','"+_mcaId+"','"+data[i][1].msOrder+"','"+data[i][1].mraLang+"')\" style=\"color: #08c;cursor: pointer;\">"+data[i][1].mraReportName+"</span></td>"+
       			"</tr>";
			}
			_str_table=_str_table+"</tbody>"+
		      "</table>"+
		       "</form>";
		       
		       //"</form></div>";
			height_dialog=230;
		}else{
			_str_table="<div align=\"center\">Not Found.</div>";
		}
		
		 //   alert(_str_table)
		   $("#dialog-download-report-element").html(_str_table);
		//alert(data.length);
		$( "#dialog-download-report" ).dialog({
			height: height_dialog, 
			width:810,
			modal: true
		});
	});
	
}
</script>

<style>
th{ font-family:Tahoma; font-size:12px; font-weight:bold;
 color: #fff;background:url(<c:url value='/resources/images/${UserMissContact.missTheme.mtTr}'/>) repeat-x scroll 0 0 ${UserMissContact.missTheme.mtTrColor};padding: 5px 8px;border:1px solid #fff; 
 
} 
/* tr:nth-child(odd) {background: #e0e0e0} */ 
</style>
<div id="dialog-download-report" title="Download Report" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
  <div id="dialog-download-report-element">
  </div>
</div>
<div id="dialog-confirmIgnore" title="Ignore Result" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
	Are you sure you want to ignore Result ?
</div>
<div id="dialog-empty" title="Empty Result" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
	Please select  Result
</div>
<div id="dialog-compare" title="Compare Result" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
	<table width="100%" border="0">
		<tr>
			<td width="50%" style="border: 1px solid;border-radius: 10px;padding: 10px;">
			    <div ><strong id="profile1">Candidate 1</strong></div>
				<form class="form-horizontal">
  					<div class="control-group">
   						 <label class="control-label">Gender: </label><label id="gender1" style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
   						 <label style="width: 70px;" class="control-label">Age: </label> <label id="age1" style="text-align: left;padding-left: 20px"  class="control-label">xx: </label>
 				 	 </div> 
 				 	 <div class="control-group">
 				 	 	 <label class="control-label">Career Group:</label><label id="careerGroup1"  style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
 				 	 	 <label style="width: 70px;" class="control-label">Position:</label> <label id="position1"  style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
 				 	 </div> 
 				 	 <div class="control-group">
 				 	 	 <label class="control-label">Department:</label><label id="department1" style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
 				 	 	 <label style="width: 70px;"class="control-label">Phone:</label><label id="phone1" style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
    					 
 				 	 </div> 
 				 	 <div class="control-group">
 				 	 	 <label class="control-label">Industry Type:</label><label id="industryType1" style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
 				 	 	 <label style="width: 70px;" class="control-label">Test Date:</label> <label  id="testDate1"  style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
 				 	 </div>
 				 	 <div class="control-group">
 				 	 	 <label class="control-label"><strong>Result :</strong></label><label  id="result1" style="text-align: left;padding-left: 20px" class="control-label"></label>
 				 	 	
 				 	 </div>
  				</form>	 
			</td>
			<td width="50%" style="border: 1px solid;border-radius: 10px;padding: 10px;">
				 <div ><strong  id="profile2">Candidate 2</strong></div>
				<form class="form-horizontal">
  					<div class="control-group">
   						 <label class="control-label">Gender: </label><label id="gender2" style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
   						 <label style="width: 70px;" class="control-label">Age: </label> <label id="age2" style="text-align: left;padding-left: 20px"  class="control-label">xx: </label>
 				 	 </div> 
 				 	 <div class="control-group">
 				 	 	 <label class="control-label">Career Group:</label><label id="careerGroup2" style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
 				 	 	 <label style="width: 70px;" class="control-label">Position:</label> <label  id="position2" style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
 				 	 </div> 
 				 	 <div class="control-group">
 				 	 	 <label class="control-label">Department:</label><label  id="department2" style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
 				 	 	 <label style="width: 70px;"class="control-label">Phone:</label><label  id="phone2" style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
    					 
 				 	 </div> 
 				 	 <div class="control-group">
 				 	 	 <label class="control-label">Industry Type:</label><label  id="industryType2" style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
 				 	 	 <label style="width: 70px;" class="control-label">Test Date:</label> <label  id="testDate2" style="text-align: left;padding-left: 20px" class="control-label">xxx: </label>
 				 	 </div>
 				 	 <div class="control-group">
 				 	 	 <label class="control-label"><strong>Result :</strong></label><label  id="result2" style="text-align: left;padding-left: 20px" class="control-label"></label>
 				 	 	
 				 	 </div>
  				</form>	
   	 
			</td>
		</tr>
		<tr style="text-align: left">
			 <td colspan="2" align="left" width="100%">
			 	<div><strong>Score</strong></div>
			 </td> 
		</tr>
		<tr>
			<td width="50%">
				<div id="compare_score1"></div>
				
        	  </td>
        	  <td width="50%" >
				 <div id="compare_score2"></div>
        	  </td>
		</tr>
		<tr>
			<td width="50%">
			<div id="chartdiv1" align="center"></div>
			 </td>
			<td width="50%">
				<div id="chartdiv2" align="center"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" width="100%">
					<div align="center"><a  class="btn btn-primary" onclick="exportComparePDF()">&nbsp;Export PDF</a>
					</div>
			</td>
		</tr>
	
	</table>
</div>
	    <fieldset style="font-family: sans-serif;">  
           <!-- <legend  style="font-size: 13px">Criteria</legend> -->
           <!-- <div style="position:relative;right:-94%;">  </div> --> 
         <!--  <form class="well" style="border:2px solid #DDD"> -->
             <form:form  id="resultForm" name="resultForm" modelAttribute="resultForm" cssClass="well" cssStyle="border:2px solid ${UserMissContact.missTheme.mtBgColor};background: url('/MISSExamBackOffice/resources/images/${UserMissContact.missTheme.mtWaterWall}') no-repeat scroll right bottom ${UserMissContact.missTheme.mtBgColor}" method="post" action="">
              
             <form:hidden path="mode"/>
            
            <form:hidden path="mtrIdArray" id="mtrIdArray"/>
            <form:hidden path="paging.pageNo" id="pageNo"/>
            <form:hidden path="paging.pageSize" id="pageSize"/> 
            <form:hidden path="pageCount" id="pageCount"/>
            <form:hidden path="paging.orderBy" id="orderBy"/> 
            <form:hidden path="paging.sortBy" id="sortBy"/>
              <table border="0" width="100%" style="font-size: 13px">
              				<tr>
	    					 <td align="left" width="100%" colspan="6"><strong><spring:message code="page_testsearch_title"/></strong></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%"><spring:message code="page_testsearch_series"/><font color="red">*</font>:</td>
	    					 <td align="left" width="17%">    					
	    					<!--  <select id="mcaSeries">
	    					      <option value="-1">-- Select Series --</option>
	    					      
	    					    </select>	 -->  
	    					   <c:if test="${not empty missSeries}">  
	    					     	 <form:select path="mcaSeries" onchange="doAction('search','0')">
	    					     		<form:options itemValue="missSery.msId" itemLabel="missSery.msSeriesName" items="${missSeries}"/>
	    					    	</form:select>	
	    					    </c:if>
	    					    <c:if test="${empty missSeries}"> 
	    					    	 <form:select path="mcaSeries"> 
	    					     		<form:option value="-1" label="---"/>
	    					    	</form:select>	 
	    					    </c:if>
	    					   	
	    					 </td>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%"><spring:message code="page_testsearch_username"/>:</td>
	    					 <td align="left" width="17%">    					
	    					<form:input path="mcaUsername"/>
	    					 </td>
	    					<td align="left" width="17%">&nbsp;</td>
	    					<td align="left" width="17%">&nbsp;</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%"><spring:message code="page_testsearch_firstname"/>:</td>
	    					 <td align="left" width="17%"> <form:input path="mcaFirstName" />
	    					 </td>
	    					<td align="left" width="17%"><spring:message code="page_testsearch_lastname"/>:</td>
	    					<td align="left" width="17%"><form:input path="mcaLastName" /></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%"><spring:message code="page_testsearch_position"/>:</td>
	    					 <td align="left" width="17%"> <form:input path="mcaPosition" />
	    					 </td>
	    					<td align="left" width="17%"><spring:message code="page_testsearch_department"/>:</td>
	    					<td align="left" width="17%"><form:input path="mcaDepartment"/></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%"><spring:message code="page_testsearch_testfrom"/>:</td>
	    					 <td align="left" width="17%">  <form:input path="testFrom" cssStyle="width:75px"/>
	    					 </td>
	    					<td align="left" width="17%"><spring:message code="page_testsearch_testto"/>:</td>
	    					<td align="left" width="17%"><form:input path="testTo" cssStyle="width:75px"/></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					 <c:if test="${isManageMC}">
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%"><spring:message code="page_testsearch_companyname"/>:</td>
	    					 <td align="left" colspan="3" width="51%">  
	    						 <form:input path="mcaCompanyName" cssStyle="width:100%"/> 				
	    					 </td> 
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					</c:if>  
	    					 <c:if test="${! isManageMC}">	
	    						 <form:hidden path="mcaCompanyName" cssStyle="width:100%"/> 
	    					</c:if>	
	    					</table> 
	    					</form:form>
	    					<table  border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="right" width="100%">
	    					<span id="sortElement"> 
	    					Sort By:&nbsp; 
	    					  <select name="sortItemSelect" id="sortItemSelect"  style="width: 115px">   
	    						<option value="candidate.MCA_USERNAME">Username</option>
	    						<option value="candidate.MCA_FIRST_NAME">First Name</option> 
	    						<option value="candidate.MCA_LAST_NAME">Last Name</option> 
	    						<option value="candidate.MCA_POSITION">Position</option> 
	    						<option value="candidate.MCA_DEPARTMENT">Department</option>
	    						<option value="result.MTR_START_TIME">Test Date</option>   
	    					  </select> 
	    					&nbsp;
	    					Order By:&nbsp;<select name="sortOrderSelect" id="sortOrderSelect"  style="width: 59px">
	    						<option value="asc">asc</option>
	    						<option value="desc">desc</option>
	    					</select>
	    					</span>
	    					</td>
	    					</tr>
	    					</table>
	    					<table  border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="left" width="60%">
	    					
	    					<a class="btn btn-success"><i class="icon-pencil icon-white"></i>&nbsp;<spring:message code="page_testsearch_dopaper"/></a>&nbsp;
	    					<a class="btn btn-info" onclick="exportTest()"><i class="icon-circle-arrow-up icon-white"></i>&nbsp;<spring:message code="page_testsearch_export"/></a>&nbsp;
	    					<a class="btn btn-info disabled"><i class="icon-list-alt icon-white"></i>&nbsp;<spring:message code="page_testsearch_summary"/></a>&nbsp;
	    					<a class="btn btn-danger" onclick="setIgnore()"><i class="icon-eject icon-white"></i>&nbsp;<spring:message code="page_testsearch_ignore"/></a>
	    					<a class="btn btn-info" onclick="compareTest()"><i class="icon-tasks icon-white"></i>&nbsp;<spring:message code="page_testsearch_compare"/></a>
	    					</td>
	    					<td align="right" width="40%">
	    					<a onclick="goPrev()"><spring:message code='page_prev'/></a>&nbsp;|&nbsp;<span id="pageElement"></span>&nbsp;|&nbsp;<a onclick="goNext()"><spring:message code='page_next'/></a>&nbsp;<a  class="btn btn-primary" onclick="doAction('search','0')"><i class="icon-search icon-white"></i>&nbsp;<spring:message code='button_search'/></a>
	    					</td>
	    					</tr>
	    					</table> 
	    					<div id="_result_content" align="center">
		<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="5%"><div class="th_class"><input type="checkbox" id="mtrIdCheckboxAll" onclick="toggleCheckbox()"/></div></th>
            		<th width="10%"><div class="th_class"><spring:message code="page_testsearch_username"/></div></th> 
            		<th width="15%"><div class="th_class"><spring:message code="page_testsearch_firstname"/></div></th>
            		<th width="10%"><div class="th_class"><spring:message code="page_testsearch_lastname"/></div></th> 
            		<th width="10%"><div class="th_class"><spring:message code="page_testsearch_position"/></div></th>
            		<th width="10%"><div class="th_class"><spring:message code="page_testsearch_department"/></div></th> 
            		<c:forEach items="${axisHeaders}" var="axisHeader" varStatus="loop">
            				<th width="5%"><div class="th_class">${axisHeader}</div></th>
            		</c:forEach>
            		<!-- <th width="5%"><div class="th_class">Fa</div></th>
            		<th width="5%"><div class="th_class">Im</div></th> 
            		<th width="5%"><div class="th_class">Pe</div></th>
            		<th width="5%"><div class="th_class">Ju</div></th>  -->
            		<th width="10%"><div class="th_class"><spring:message code="page_testsearch_testdate"/></div></th> 
            		<th width="5%"><div class="th_class"><spring:message code="page_testsearch_report"/></div></th>
            		<th width="5%"><div class="th_class"><spring:message code="page_testsearch_status"/></div></th>
            		<th width="5%"><div class="th_class"><spring:message code="page_testsearch_response"/></div></th> 
          		</tr>
        	</thead>
        	<tbody>
        	<c:if test="${not empty missTestResults}">  
        	 <c:forEach items="${missTestResults}" var="missTestResult" varStatus="loop"> 
          	<tr>
            	<td><input type="checkbox" name="mtrIdCheckbox" value="${missTestResult.mtrId}"/></td>
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
            	<td>&nbsp;<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${missTestResult.mtrStartTime}" /></td> 
            	<c:url value="/result/testPDF" var="downloadUrl">
            		<c:param name="mtrId" value="${missTestResult.mtrId}"></c:param>
            		<c:param name="meId" value="${missTestResult.meId}"></c:param>
            		<c:param name="msId" value="${missTestResult.msId}"></c:param>
            		<c:param name="mcaId" value="${missTestResult.missCandidate.mcaId}"></c:param>
            	</c:url>
            	<td>
            	 <c:if test="${missTestResult.mtrResultCode!=''}">
            	 	<%-- &nbsp;<a href="${downloadUrl}">${missTestResult.mtrResultCode}</a> --%>
            	 	<span onclick="showFormDownload('${missTestResult.mtrId}','${missTestResult.meId}','${missTestResult.msId}','${missTestResult.missCandidate.mcaId}')" style="color: #08c;cursor: pointer;">${missTestResult.mtrResultCode}</span>
            	 </c:if>
            	  <c:if test="${missTestResult.mtrResultCode==''}">
            	
            	 </c:if>
            	</td>            	
            	<td>
            	<c:if test="${missTestResult.mtrStatus=='0'}">Not finished</c:if>
            	<c:if test="${missTestResult.mtrStatus=='1'}">Finished</c:if>
            	<c:if test="${missTestResult.mtrStatus=='2'}">Responded</c:if>
            	</td>
            	<td>
            	<%-- <c:if test="${(missTestResult.mtrStatus=='1' || missTestResult.mtrStatus=='2') &&  missTestResult.mtrIgnored!='1'}">            	
            			<a onclick="loadDynamicPage('result/response/${missTestResult.mtrId}')">Email</a>
            	</c:if> --%>
            	<c:if test="${missTestResult.mtrRespondedStatus=='1' && missTestResult.mtrStatus!='0'}">            	
            			<a onclick="loadDynamicPage('result/response/${missTestResult.mtrId}')">Completed</a>
            	</c:if>
            	<c:if test="${missTestResult.mtrRespondedStatus=='0' && missTestResult.mtrStatus!='0'}">            	
            			<a onclick="loadDynamicPage('result/response/${missTestResult.mtrId}')">Pending</a>
            	</c:if>
            	<%-- <c:if test="${missTestResult.mtrRespondedStatus=='2' && missTestResult.mtrStatus!='0'}">    --%>
            	<c:if test="${missTestResult.mtrRespondedStatus=='2'}">         	
            			Ignored
            	</c:if>
            	<c:if test="${missTestResult.mtrStatus=='0'}">            	
            			&nbsp;
            	</c:if>
            	</td>
          	</tr>
          	</c:forEach>
          </c:if>
           <c:if test="${empty missTestResults}"> 
          	<tr> 
          		<td colspan="${fn:length(axisHeaders)+10}" style="text-align: center;">&nbsp;<spring:message code="searh_result_not_found"/>&nbsp;</td>
          	</tr>
          </c:if> 
        	</tbody>
      </table>
      </div>
</fieldset>