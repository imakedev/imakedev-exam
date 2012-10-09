<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <html>
<head><title></title>
        <style>
            body{
                padding:0 0 0 0;
                margin:0 0 0 0;
            }
        </style> 
<script type="text/javascript" src="/MISSProcessImage/PowerChart/Charts/FusionCharts.js"></script>  
</head>

<body>
<div id="chartdiv"><small>Loading chart...</small></div>  
 <script type="text/javascript">
   	//Create the chart
    
	  FusionCharts.setCurrentRenderer('javascript'); // for new version
     //var myChart = new FusionCharts("<c:url value='/Charts/Column2D.swf'/>", "myChartId", "600", "300", "0", "1"); //for new version
     //Bar2D.swf , 500 , 400 
     var myChart = new FusionCharts("/MISSProcessImage/PowerChart/Charts/Radar.swf", "myChartId", "500", "400", "0", "0"); //for new version
    
     var data='<chart caption="Budget Analysis" bgcolor="ffffff" anchoralpha="0" basefont="calibri" basefontsize="13" basefontcolor="3E3E3F" bordercolor="6FACD1" captionpadding="20" radarFillColor="FFFFFF" >\n\
     <categories>\n\
     <category label="Customer Support" />\n\
     <category label="Marketing" />\n\
     <category label="HR" />\n\
     <category label="Development" />\n\
     <category label="Finance" />\n\
	  <category label="Sales" />\n\
<category label="Admin" />\n\
  </categories>\n\
  <dataset seriesName="Actual Spending" color="68D740" anchorbgcolor="77C55B">\n\
     <set value="70" />\n\
     <set value="90" />\n\
     <set value="55" />\n\
     <set value="40" />\n\
     <set value="45" />\n\
     <set value="70" />\n\
<set value="57" />\n\
  </dataset>\n\
  <dataset seriesName="Allocated Budget" color="6FACD1">\n\
     <set value="70" />\n\
     <set value="65" />\n\
     <set value="57" />\n\
     <set value="56" />\n\
     <set value="69" />\n\
	  <set value="47" />\n\
<set value="45" />\n\
  </dataset>  \n\
<styles>\n\
<definition>\n\
<style name="Font_0" type="font" font="Calibri" size="17" color="2F2F2F" bold="1" underline="1" isHTML="0"/>\n\
<style type="Bevel" name="Bevel_0" angle="170" Distance="6" shadowAlpha="90"  blurX="6"/>\n\
</definition>\n\
<application>\n\
<apply toObject="CAPTION" styles="Font_0"/>\n\
<apply toObject="DATAPLOT" styles="Bevel_0"/>\n\
</application>\n\
</styles>\n\
</chart>';
  //   var myChart = new FusionCharts("../../FusionCharts/Column2D.swf", "myChartId", "600", "300", "0", "1"); // for old version
//	myChart.setDataURL("ServerSideSimple.xml");
    myChart.setXMLData(data);// '<chart  alternateVGridColor="AFD8F8" baseFontColor="114B78" toolTipBorderColor="114B78" toolTipBgColor="E7EFF6" useRoundEdges="1" showBorder="0" bgColor="FFFFFF,FFFFFF"><set label="Orange" value="23" color="AFD8F8"/><set label="Apple" value="12" color="F6BD0F"/><set label="Banana" value="17" color="8BBA00"/><set label="Mango" value="14"  color="A66EDD"/><set label="Litchi" value="12"  color="F984A1"/></chart>');
	myChart.render("chartdiv");
   </script>
</body>
</html>