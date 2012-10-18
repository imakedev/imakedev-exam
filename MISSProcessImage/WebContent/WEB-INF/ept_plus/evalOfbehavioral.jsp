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
<!-- <script type="text/javascript" src="/MISSProcessImage/Charts/FusionCharts.js"></script> -->  
</head>

<body>
<c:forEach var="group" items="${groups}">
       <table style="width: 1070px;border-spacing:0px;border-color:#DBDBDB;" border="1">
       	<tr style="background-color: #DBDBDB;">
			<td colspan="3" align="center">${group.meebgGroup}</td>
		</tr>
		 <c:forEach var="evalBehavioral" items="${group.missEptEvalBehavioralValues}">
		 	<tr style="background-color: #E6F5FF">
		<td width="30%">${evalBehavioral.message1}</td>
		<td width="40%" style="text-align: center;">
		<table  width="100%" >
			<tr>
				<td colspan="11" style="text-align: center;">${evalBehavioral.meebvKey} (${evalBehavioral.meebvValue})</td>
			</tr>
			<tr>
				<td style="text-align: center;">10</td>
				<td style="text-align: center;">8</td>
				<td style="text-align: center;">6</td>
				<td style="text-align: center;">4</td>
				<td style="text-align: center;">2</td>
				<td style="text-align: center;">0</td>
				<td style="text-align: center;">2</td>
				<td style="text-align: center;">4</td>
				<td style="text-align: center;">6</td>
				<td style="text-align: center;">8</td>
				<td style="text-align: center;">10</td>
			</tr>
			<tr>
				<td style="text-align: center;"><img src="<c:url value='/images/img-5.png'/>"/></td>
				<td style="text-align: center;"><img src="<c:url value='/images/img-4.png'/>"/></td>
				<td style="text-align: center;"><img src="<c:url value='/images/img-3.png'/>"/></td>
				<td style="text-align: center;"><img src="<c:url value='/images/img-2.png'/>"/></td>
				<td style="text-align: center;"><img src="<c:url value='/images/img-1.png'/>"/></td>
				<td style="text-align: center;"><img src="<c:url value='/images/img0.png'/>"/></td>
				<td style="text-align: center;"><img src="<c:url value='/images/img-1.png'/>"/></td>
				<td style="text-align: center;"><img src="<c:url value='/images/img-2.png'/>"/></td>
				<td style="text-align: center;"><img src="<c:url value='/images/img-3.png'/>"/></td>
				<td style="text-align: center;"><img src="<c:url value='/images/img-4.png'/>"/></td>
				<td style="text-align: center;"><img src="<c:url value='/images/img-5.png'/>"/></td>
			</tr>
		</table>
		</td>
		<td width="30" >${evalBehavioral.message2}
		</td>
	</tr> 
		 </c:forEach>
         
        </table>
        <br/>
      </c:forEach> 
</body>
</html>