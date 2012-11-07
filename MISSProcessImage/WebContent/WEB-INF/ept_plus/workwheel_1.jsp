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
     var myChart = new FusionCharts("/MISSProcessImage/Charts/Pie3D.swf", "myChartId","1000", "600", "0", "1"); //for new version
  
     var xml='<chart palette="2" animation="1" pieSliceDepth="30" startingAngle="125">'+
     '<set label="Promoting" value="61.00" displayValue="Promoting,Score,61.00,15.25%" isSliced="1" />'+
     '<set label="Developing" value="73.50" displayValue="Developing,Score,73.50,18.38%" isSliced="1" />'+
     '<set label="Organizing" value="16.38" displayValue="Organizing,Score,65.50,16.38%"  isSliced="1" />'+
     '<set label="Innovating" value="44.00" displayValue="Innovating,Score,44.00,11.00%"  isSliced="1" />'+
     '<set label="Advising" value="34.50" displayValue="Advising,Score,34.50,8.63%"  isSliced="1" />'+
     '<set label="Maintaining" value="26.50" displayValue="Maintaining,Score,39.00,9.75%"  isSliced="1"  />'+
     '<set label="Inspecting" value="39.00" displayValue="Inspecting,Score,39.00,9.75%"  isSliced="1" />'+
     '<set label="Producing" value="56.00" displayValue="Producing,Score,56.00,14.00%"  isSliced="1" />'+
     '<styles>'+
     '	<definition>'+
     '		<style type="font" name="CaptionFont" size="15" color="666666" />'+
     '		<style type="font" name="SubCaptionFont" bold="0" />'+
     '	</definition>'+
     '	<application>'+
     '	<apply toObject="caption" styles="CaptionFont" />'+
     '	<apply toObject="SubCaption" styles="SubCaptionFont" />'+
     '	</application>'+
     '</styles>'+
     '</chart>';
     
  //   var myChart = new FusionCharts("../../FusionCharts/Column2D.swf", "myChartId", "600", "300", "0", "1"); // for old version
//	myChart.setDataURL("ServerSideSimple.xml");
    myChart.setXMLData('${xmlData}');
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
		 <c:set var="endLoop" value="${fn:length(messages)}"/>  
		 <c:forEach var="message" items="${messages}"  varStatus="ms">  
		  <c:if test="${ms.index < 2}">
				<tr>
		 			<td width="70%" align="center">${message.mepwwmType}</td> 
		 			<td width="30%" align="center">${message.mepwwmValue}</td>
		    	</tr>
		    </c:if> 
		 </c:forEach>
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
		  
		 <c:set var="endLoop" value="${fn:length(messages)}"/>  
		 <c:forEach var="message" items="${messages}"  varStatus="ms"> 
		    <c:if test="${ms.index > (endLoop-3)}">
				<tr>
		 			<td width="70%" align="center">${message.mepwwmType}</td> 
		 			<td width="30%" align="center">${message.mepwwmValue}</td>
		    	</tr>
		    </c:if>
		 </c:forEach>
		 </table>
		</td> 
		<td    width="5%" align="center">&nbsp;</td>
	</tr>
</table>    
<br/>
<table style="width: 1070px;border-spacing:0px;border-color:#DBDBDB;" border="1">
	<tr style="background-color: #DBDBDB;">
		<td  width="20%"  align="center">Type of Work</td>
		 <c:forEach var="message" items="${messages}">
			<td   width="10%" align="center">${message.mepwwmType}</td>
		</c:forEach>
	</tr>
	<tr style="background-color: #FFFFFF;">
		<td align="center">Score c</td>
		<!--  count index -->
		 <c:forEach var="message" items="${messages}">
			<td align="center">${message.mepwwmValue}</td>
		</c:forEach>
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
	 <c:forEach var="message" items="${messages}"  varStatus="ms">  
		  <c:if test="${ms.index < 5}">
		  <tr style="background-color: #FFFFFF">
				<td rowspan="2"><b>${message.mepwwmType}</b>
				</td>
				<td  rowspan="2" style="text-align: center;">
					${message.mepwwmRole}
				</td>
				<td  rowspan="2" >
					${message.mepwwmPercent}
				</td>
				<td style="background-color: #DBDBDB;" >
					<b>${message.mepwwmCharecter1}</b> 
				</td> 
				<td rowspan="2">
					<b>Example:</b>${message.mepwwmSample}
				</td>
			</tr>
			<tr style="background-color: #FFFFFF">
				<td>
					${message.mepwwmCharecter2} 
				</td> 
			</tr>
		  </c:if>
	 </c:forEach>
</table>
</body>
</html>