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
		
<c:if test="${mdcType=='FusionCharts'}">
<script type="text/javascript" src="/MISSProcessImage/Charts/FusionCharts.js"></script>  
</c:if>
<c:if test="${mdcType=='PowerCharts'}">
<script type="text/javascript" src="/MISSProcessImage/PowerChart/Charts/FusionCharts.js"></script>  
</c:if>
<c:if test="${mdcType=='FusionWidgets'}">
<script type="text/javascript" src="/MISSProcessImage/Widgets/Charts/FusionCharts.js"></script>  
</c:if>
</head>

<body>
<div id="chartdiv"><small>Loading chart...</small></div>  
 <script type="text/javascript">
   	//Create the chart
    
	  FusionCharts.setCurrentRenderer('javascript'); // for new version
     //var myChart = new FusionCharts("<c:url value='/Charts/Column2D.swf'/>", "myChartId", "600", "300", "0", "1"); //for new version
     //Bar2D.swf , 500 , 400 
     var myChart = new FusionCharts("/MISSProcessImage/Charts/${swfName}", "myChartId", "${width}", "${height}", "0", "0"); //for new version
    
     
  //   var myChart = new FusionCharts("../../FusionCharts/Column2D.swf", "myChartId", "600", "300", "0", "1"); // for old version
//	myChart.setDataURL("ServerSideSimple.xml");
    myChart.setXMLData('${XMLDATA}');
	myChart.render("chartdiv");
   </script>
</body>
</html>