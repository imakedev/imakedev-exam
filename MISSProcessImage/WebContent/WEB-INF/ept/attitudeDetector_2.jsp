<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title></title>
<style>
body {
	padding: 0 0 0 0;
	/* margin:0 0 0 0; */
	margin: 2 2 2 2;
} 
</style>
<script type="text/javascript" src="/MISSProcessImage/Charts/FusionCharts.js"></script> 
</head>

<body>
<table
		style="width: 1070px; border-spacing: 0px;"
		border="0">
		<tr>
			<td>
			<%-- <img id="candidate_photo" width="1070px" src="<c:url value='/images/EPT_Personalized_Analysis_of_the_report.png'/>" /> --%> 
			</td>
		</tr>
	</table>
<table style="width: 1070px;border-color: black;" border="0">
	<tr>
		<td width="100%" align="center">
			<div id="chartdiv"><small>Loading chart...</small>
			</div>
		</td> 
	</tr>
</table>
<table
		style="width: 1070px; border-spacing: 0px; border-color: #DBDBDB;"
		border="0">
		<tr>
			<td width="10%">&nbsp;</td>
			<td width="30%">
				<table style="width: 100%;">
					<tr align="center">
						<td colspan="3" style="background-color: #52A09D">Your 3 Highest Detector Traits</td>
					</tr>
					<tr align="center">
						<td width="10%"><b>No.</b></td>
						<td width="45%"><b>Type of Traits</b></td>
						<td width="45%"><b>Yr Score</b></td>
					</tr>
					<c:forEach var="trait" items="${traits}"  varStatus="ms"> 
		   			 <c:if test="${ms.index < 3}">
						<tr align="center">
							<td>${ms.index+1}</td>
							<td width="45%">${trait.metdName}</td>
							<td width="45%">${trait.metdValue}</td>
						</tr>
		   			 </c:if>
					 </c:forEach>
					 <!-- 
					<tr align="center">
						<td>1</td>
						<td width="45%">Communication</td>
						<td width="45%">4.55</td>
					</tr>
					<tr align="center">
						<td>2</td>
						<td width="45%">Vision</td>
						<td width="45%">3.82</td>
					</tr>
					<tr align="center">
						<td>3</td>
						<td width="45%">Flexible</td>
						<td width="45%">3.82</td>
					</tr>
					 -->
				</table>
			</td>
			<td width="20%">&nbsp;</td>
			<td width="30%">
				<table  style="width: 100%;">
					<tr align="center">
						<td colspan="3" style="background-color: #E16951">Your 3 Lowest Detector Traits</td>
					</tr>
					<tr align="center">
						<td width="10%"><b>No.</b></td>
						<td width="45%"><b>Type of Traits</b></td>
						<td width="45%"><b>Yr Score</b></td>
					</tr>
					<c:set var="endLoop" value="${fn:length(traits)}"/>
					<c:set var="indexLoop" value="1"/>  
					<c:forEach var="trait" items="${traits}"  varStatus="ms"> 
		   			 <c:if test="${ms.index > (endLoop-4)}">
						<tr align="center">
							<td>${indexLoop}</td>
							<td width="45%">${trait.metdName}</td>
							<td width="45%">${trait.metdValue}</td>
							<c:set var="indexLoop" value="${indexLoop+1}"/> 
						</tr>
		   			 </c:if>
					 </c:forEach>
					 
					 <!-- 
					<tr align="center">
						<td>1</td>
						<td width="45%">Persistent</td>
						<td width="45%">2.36</td>
					</tr>
					<tr align="center">
						<td>2</td>
						<td width="45%">Self-Motivate</td>
						<td width="45%">2.55</td>
					</tr>
					<tr align="center">
						<td>3</td>
						<td width="45%">Dependenable</td>
						<td width="45%">2.73</td>
					</tr>
					 -->
				</table>
			</td>
			<td width="10%">&nbsp;</td>
		</tr>
		
</table>
<br/>
<table
		style="width: 1070px; border-spacing: 0px; border-color: #DBDBDB;"
		border="1">
		<tr align="center" style="background-color: #A0BCE3">
			<td colspan="2">Table of Traits Detector</td> 
		</tr> 
		<tr align="center" style="background-color: #D7D6D4">
			<td width="80%">Type of Traits</td>
			<td width="20%">Score out of 10</td> 
		</tr>
		<c:forEach var="trait" items="${traits}"  varStatus="ms">
			<tr> 
				<td width="80%">&nbsp;&nbsp;${trait.metdName}</td>
				<td width="20%" align="center">${trait.metdValue}</td> 
			</tr>
		</c:forEach>
		<!--  
		<tr>
			<td width="80%">Communication</td>
			<td width="20%" align="center">4.55</td> 
		</tr>
		<tr>
			<td width="80%">Flexible</td>
			<td width="20%" align="center">3.82</td> 
		</tr>
		<tr>
			<td width="80%">Vision</td>
			<td width="20%" align="center">3.82</td> 
		</tr>
		<tr>
			<td width="80%">Positive Thinking</td>
			<td width="20%" align="center">3.64</td> 
		</tr>
		<tr>
			<td width="80%">Ambition</td>
			<td width="20%" align="center">3.45</td> 
		</tr>
		<tr>
			<td width="80%">Self - Confident</td>
			<td width="20%" align="center">3.45</td> 
		</tr>
		<tr>
			<td width="80%">Creditable</td>
			<td width="20%" align="center">3.09</td> 
		</tr>
		<tr>
			<td width="80%">Determined</td>
			<td width="20%" align="center">3.09</td> 
		</tr>
		<tr>
			<td width="80%">Dependenable</td>
			<td width="20%" align="center">2.73</td> 
		</tr>
		<tr>
			<td width="80%">Self - Motivate</td>
			<td width="20%" align="center">2.55</td> 
		</tr>
		<tr>
			<td width="80%">Persistent</td>
			<td width="20%" align="center">2.36</td> 
		</tr>
		 -->
</table>
<script type="text/javascript">
var dataString ='<chart   useRoundEdges="1" bgColor="FFFFFF,FFFFFF" showBorder="0">\n\
<set label="Communication" value="4.55"  /> \n\
<set label="Flexible" value="3.82" /> \n\
<set label="Vision" value="3.82" /> \n\
<set label="Positive Thinking" value="3.64" /> \n\
<set label="Ambition" value="3.45" />\n\
<set label="Self - Confident" value="3.45" />\n\
<set label="Creditable" value="3.09" />\n\
<set label="Determined" value="3.09" />\n\
<set label="Dependenable" value="2.73" />\n\
<set label="Self - Motivate" value="2.55" />\n\
<set label="Persistent" value="2.36" />\n\
\n\
</chart>';
	  FusionCharts.setCurrentRenderer('javascript'); // for new version 
          //   var chart = new FusionCharts("/MISSProcessImage/Charts/Radar.swf", "ChartId", "380", "300", "0");
             var chart = new FusionCharts("/MISSProcessImage/Charts/Column2D.swf", "ChartId_flash", "1000", "400", "0", "1" );      
	  chart.setXMLData('${column2DxmlData}');
            chart.render("chartdiv");
       </script>
</body>
</html>

