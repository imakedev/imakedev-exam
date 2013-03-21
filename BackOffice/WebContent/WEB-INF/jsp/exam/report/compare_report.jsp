<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
	    <!--Body content-->
<html>
<head>
<title></title> 
<script  src="<c:url value='/resources/js/jquery-1.7.2.min.js'/>" type="text/javascript"></script> 
<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/resources/js/smoothness/jquery-ui-1.8.21.custom.min.js'/>"></script> 
<%-- </sec:authorize> --%>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.selectmenu.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.jstree.js'/>"></script>
<link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"  type="text/css">
<link href="<c:url value='/resources/css/smoothness/jquery-ui-1.8.21.custom.css'/>" type="text/css"  rel="stylesheet" />
<link  href="<c:url value='/resources/css/jquery.ui.selectmenu.css'/>" rel="stylesheet" type="text/css">

<link href="<c:url value='/resources/css/3column.css'/>"  type="text/css" rel="stylesheet" />
<link href="<c:url value='/resources/css/menubar.css'/>"  type="text/css" rel="stylesheet" /> 
<script type="text/javascript" src="/MISSProcessImage/Charts/FusionCharts.js"></script> 
<script type="text/javascript" src="<c:url value='/resources/js/jquery.dateFormat-1.0.js'/>"></script>   
  
<style>
.ui-widget { font-family: Trebuchet MS, Tahoma, Verdana,
 Arial, sans-serif; font-size: 12px; }
 </style> 
<style type="text/css"> 
.th_class{text-align: center;
}
a{cursor: pointer;}
</style>
<style type="text/css"> 
th{ font-family:Tahoma; font-size:12px; font-weight:bold;
 color: #fff;background:url(<c:url value='/resources/images/${missAccount.missTheme.mtTr}'/>) repeat-x scroll 0 0 ${missAccount.missTheme.mtTrColor};padding: 5px 8px;border:1px solid #fff; 
}
input[type="text"],[type="password"]{height:30px}
img.ui-datepicker-trigger{cursor: pointer;}
 .span8 {
   padding: 2px;
}
.span2 {
   padding: 2px;
}  
.stable-striped{
   background-color: #F9F9F9;
}
table[id=table_list] tr:nth-child(even) {background: #FFFFFF}
</style>
<script type="text/javascript">
var color_G2=["B9DDF8","F6BD0F","8BBA00","FF934E","059090","D64646","914B91","618C32"]; 
$(document).ready(function() {
	//initCompanyList();
	<%-- var mcaSeries="${mcaSeries}";
	var mtrIds="${mtrIds}"; 
	--%>
	//compareTest(mcaSeries,mtrIds);
	compareTest();
});
//function compareTest(mcaSeries,mtrIds){
function compareTest(){
	//$("#dialog-compare").html();
	/* var mtrIdCheckbox=document.getElementsByName("mtrIdCheckbox");
	var index=0;
	var mtrIds="";
	for(var i=0;i<mtrIdCheckbox.length;i++){
		 if(mtrIdCheckbox[i].checked){
			 mtrIds=mtrIds+mtrIdCheckbox[i].value+"_";
			 index++;
		 }
	} 
	if(index!=2){
		alert("Candidate must be two.");
		return false;
	} */

	$.ajax({
		  type: "get",
		  //url: "/MISSExamBackOffice/result/compare/"+mcaSeries+"/"+mtrIds,
		   url: "/MISSExamBackOffice/reportExport/compare/${mcaSeries}/${mtrIds}",
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
			       FusionCharts.setCurrentRenderer('javascript');
				  var myChart1 = new FusionCharts("/MISSProcessImage/Charts/Column2D.swf", "myChartId1", "600", "300", "0", "1"); // for old version
				  //myChart.setDataXML(xml);
				   // alert(myChart1);
				   myChart1.setDataXML(chart_value1);
				    myChart1.render("chartdiv1");
				    FusionCharts.setCurrentRenderer('javascript');
				    var myChart2 = new FusionCharts("/MISSProcessImage/Charts/Column2D.swf", "myChartId2", "600", "300", "0", "1"); // for old version
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
</script>
<style>
th{ font-family:Tahoma; font-size:12px; font-weight:bold;
 color: #fff;background:url(<c:url value='/resources/images/${missAccount.missTheme.mtTr}'/>) repeat-x scroll 0 0 ${missAccount.missTheme.mtTrColor};padding: 5px 8px;border:1px solid #fff; 
}
</style> 
<fieldset style="font-family: sans-serif;">
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
	<!-- 	<tr>
			<td colspan="2" align="center" width="100%">
					<div align="center"><a  class="btn btn-primary" onclick="exportComparePDF()">&nbsp;Export PDF</a>
					</div>
			</td>
		</tr> -->
	
	</table>
  </fieldset>