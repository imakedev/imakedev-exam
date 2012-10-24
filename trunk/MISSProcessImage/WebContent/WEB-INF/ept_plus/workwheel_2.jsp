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
 <c:set var="endLoop" value="${fn:length(messages)}"/>  
 <c:forEach var="message" items="${messages}"  varStatus="ms">  
		  <c:if test="${ms.index > (endLoop-4)}">
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
	 <tr>
	<td colspan="5"><font color="#640000"> Remark: *** A list of sample career do not covered all career that suit you, you have to consider the nature of working
style and your characteristics</font>
	</td>
	</tr>
</table>
</body>
</html>