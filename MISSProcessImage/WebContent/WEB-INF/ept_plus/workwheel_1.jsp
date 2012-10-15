<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <html>
<head><title></title>
        <style>
            body{
                padding:0 0 0 0;
               /* margin:0 0 0 0; */
                margin:2 2 2 2;
            }
        </style> 
<script type="text/javascript" src="/MISSProcessImage/Charts/FusionCharts.js"></script>  
</head>

<body>
<table style="width: 1070px;border-spacing:0px;border-color:#DBDBDB;" border="0">
	<tr>
	<td width="10%" align="left"></td>
	<!--   style="background-color: #DBDBDB;"  -->
		<td  width="80%" align="left">Conclusion: From testing date, your personality reflect to your work type preferences
as follow
The unique contribution of our research has been to combine the model of the nature of work to the code of EPT preferences, creating
the Work Wheel Fit.
</td>
<td width="10%" align="left"></td>
	</tr>
</table>
<table style="width: 1070px;border-color: black;" border="0">
	<tr>
		<td align="center"><div id="chartdiv"><small>Loading chart...</small></div> </td>
	</tr>
</table>

 <script type="text/javascript">
   	//Create the chart
    
	  FusionCharts.setCurrentRenderer('javascript'); // for new version
     //var myChart = new FusionCharts("<c:url value='/Charts/Column2D.swf'/>", "myChartId", "600", "300", "0", "1"); //for new version
     //Bar2D.swf , 500 , 400 
     var myChart = new FusionCharts("/MISSProcessImage/Charts/Pie3D.swf", "myChartId","560", "400", "0", "0"); //for new version
    var xml='<chart palette="2" animation="1" pieSliceDepth="30" startingAngle="125"><set label="Leverling" value="87790" isSliced="1" /><set label="Fuller" value="87790" isSliced="1" /><set label="Davolio" value="81898" isSliced="1" /><set label="Peacock" value="76438" isSliced="1" /><set label="King" value="57430" isSliced="1" /><set label="Callahan" value="55091" isSliced="1"  /><set label="Dodsworth" value="43962" isSliced="1" /><set label="Suyama" value="22474" isSliced="1" /><styles><definition><style type="font" name="CaptionFont" size="15" color="666666" /><style type="font" name="SubCaptionFont" bold="0" /></definition><application><apply toObject="caption" styles="CaptionFont" /><apply toObject="SubCaption" styles="SubCaptionFont" /></application></styles></chart>';
     
  //   var myChart = new FusionCharts("../../FusionCharts/Column2D.swf", "myChartId", "600", "300", "0", "1"); // for old version
//	myChart.setDataURL("ServerSideSimple.xml");
    myChart.setXMLData(xml);
	myChart.render("chartdiv");
   </script>
    <table style="width: 1070px;border-spacing:0px;" border="0">
	<tr>
		<td  width="5%"  align="center">&nbsp;</td>
		<td   width="40%" align="center">
		 <table style="width: 100%;border-spacing:0px;border-color:#DBDBDB;" border="1">
		 <tr>
		 	<td colspan="2" align="center"><font color="#4B00D3">Your 2 Top Score</font></td>
		 </tr>
		 <tr bgcolor="#4F86D1">
		 	<td width="70%" align="center"><font color="#FFFFFF">Type of Work</font></td>
		 	<td width="30%" align="center"><font color="#FFFFFF">Score</font></td>
		 </tr>
		  <tr>
		 	<td width="70%" align="center">?</td>
		 	<td width="30%" align="center">?</td>
		 </tr>
		 <tr>
		 	<td width="70%" align="center">?</td>
		 	<td width="30%" align="center">?</td>
		 </tr>
		
		 </table>
		</td> 
		<td    width="10%" align="center">&nbsp;</td>
		<td    width="40%" align="center">
		 <table style="width: 100%;border-spacing:0px;border-color:#DBDBDB;" border="1">
		 <tr>
		 	<td colspan="2" align="center"><font color="#6C0000">Your 2 Lowest Score</font></td>
		 </tr>
		 <tr bgcolor="#640000">
		 	<td width="70%" align="center"><font color="#FFFFFF">Type of Work</font></td>
		 	<td width="30%" align="center"><font color="#FFFFFF">Score</font></td>
		 </tr>
		  <tr>
		 	<td width="70%" align="center">?</td>
		 	<td width="30%" align="center">?</td>
		 </tr>
		 <tr>
		 	<td width="70%" align="center">?</td>
		 	<td width="30%" align="center">?</td>
		 </tr>
		
		 </table>
		</td> 
		<td    width="5%" align="center">&nbsp;</td>
	</tr>
	
	
</table>    
<br/>
<table style="width: 1070px;border-spacing:0px;border-color:#DBDBDB;" border="1">
	<tr style="background-color: #DBDBDB;">
		<td  width="20%"  align="center">Type of Work</td>
		<td   width="10%" align="center">Promoting</td>
		<td   width="10%" align="center">Organizing</td>
		<td    width="10%" align="center">Producing</td>
		<td    width="10%" align="center">Inspecting
</td>
<td   width="10%" align="center">Maintaining</td>
		<td    width="10%" align="center">Advising</td>
		<td    width="10%" align="center">Innovating
</td>
	</tr>
	
	<tr style="background-color: #FFFFFF;">
		<td align="center">Score</td>
		<td align="center">?</td>
		<td align="center">?</td>
		<td align="center">?</td>
		<td align="center">?
</td>
<td  align="center">?</td>
		<td align="center">?</td>
		<td  align="center">?
</td>
	</tr>
</table>
<br/>
<table style="width: 1070px;border-spacing:0px;border-color:#DBDBDB;" border="1">
	<tr style="background-color: #DBDBDB;">
		<td  width="10%"  align="center">Type of Work Wheel</td>
		<td   width="10%" align="center">Role of Preference</td>
		<td   width="5%" align="center">%</td>
		<td    width="40%" align="center">Characteristics</td>
		<td    width="40%" align="center">Sample of Suitable Career
***
</td>
	</tr>
	
	<tr style="background-color: #FFFFFF">
		<td rowspan="2"><b>Developing</b>
</td>
		<td  rowspan="2" style="text-align: center;">
		Analyst
Developer
		
		</td>
		<td  rowspan="2" >
?%
		</td>
		<td style="background-color: #DBDBDB;" >
<b>Initiating Temperament</b>

		</td> 
		<td rowspan="2">
<b>Example:</b>Analyst, System Engineering, Sales Representative, Sales manager, AE, Bank Manager, etc 
		</td>
	</tr>
	<tr style="background-color: #FFFFFF">
		<td>
		Initiating Temperament: Will prefer to plan and analyze ideas to see how they can be made to work 
</td>
		 
	</tr>
	
	<tr style="background-color: #FFFFFF">
		<td rowspan="2"><b>Organizing</b>
</td>
		<td  rowspan="2" style="text-align: center;">
		Drive Organizer

		</td>
		<td  rowspan="2" >
?%
		</td>
		<td style="background-color: #DBDBDB;" >
<b>Directing Temperament</b>

		</td> 
		<td rowspan="2">
<b>Example:</b>Accountant, Organizer,
ISO,Architect, Purchaser, Real
Estate agent, etc

		</td>
	</tr>
	<tr style="background-color: #FFFFFF">
		<td>
		Directing Temperament: Enjoys thrusting into action to make things happen 
</td>
		 
	</tr>
	
	<tr style="background-color: #FFFFFF">
		<td rowspan="2"><b>Promoting</b>
</td>
		<td  rowspan="2" style="text-align: center;">
		Pioneer
		</td>
		<td  rowspan="2" >
?%
		</td>
		<td style="background-color: #DBDBDB;" >
<b>Initiating Temperament</b>

		</td> 
		<td rowspan="2">
<b>Example:</b>:Marketing, Product Developer, Flight Attendant, Club manager,HRD etc 
		</td>
	</tr>
	<tr style="background-color: #FFFFFF">
		<td>
		Initiating Temperament: Tends to focus on exploring opportunities and promoting ideas and concepts 
</td>
		 
	</tr>
	 <tr style="background-color: #FFFFFF">
		<td rowspan="2"><b>Producing</b>
</td>
		<td  rowspan="2" style="text-align: center;">
		Directing
Producer
		</td>
		<td  rowspan="2" >
?%
		</td>
		<td style="background-color: #DBDBDB;" >
<b>Stabilization Temperament</b>

		</td> 
		<td rowspan="2">
<b>Example:</b>Management , Director,Finance & Accountant, Engineering,Guard, Public Health officer, Building Manager, Researcher etc 
		</td>
	</tr>
	<tr style="background-color: #FFFFFF">
		<td>
		Stabilization Temperament: Likes to work systematically and deliver outputs 
</td>
		 
	</tr>
	<tr style="background-color: #FFFFFF">
		<td rowspan="2"><b>Innovating</b>
</td>
		<td  rowspan="2" style="text-align: center;">
		Distributor
Innovator
		</td>
		<td  rowspan="2" >
?%
		</td>
		<td style="background-color: #DBDBDB;" >
<b>Abstract Informing Temperament</b>

		</td> 
		<td rowspan="2">
<b>Example:</b>Actor,Critize,Commentator,Banquet, Creative, etc 
		</td>
	</tr>
	<tr style="background-color: #FFFFFF">
		<td>
		Abstract Informing Temperament: Likes to create ideas and diverge their thinking 
</td>
		 
	</tr>
</table>
</body>
</html>