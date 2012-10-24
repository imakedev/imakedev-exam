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

.Example_R {
	-moz-border-radius: 5px;
	border-radius: 5px;
	-moz-box-shadow: 5px 5px 5px black;
	-webkit-box-shadow: 5px 5px 5px black;
	box-shadow: 5px 5px 5px black;
}
</style>
<!-- <script type="text/javascript" src="/MISSProcessImage/Charts/FusionCharts.js"></script> -->
</head>

<body>
<table
		style="width: 1070px; border-spacing: 0px;"
		border="0">
		<tr>
			<td>
			<img id="candidate_photo" width="1070px" src="<c:url value='/images/EPT_Personalized_Analysis_of_the_report.png'/>" /> 
			</td>
		</tr>
	</table> 
	<br />
	<table
		style="width: 1070px; border-spacing: 0px; border-color: #DBDBDB;"
		border="0">
		<c:forEach var="config" items="${configs}"  varStatus="ms">
			<c:if test="${ms.index ==5}">
				<tr>
					<td width="10%"></td>
					<td width="90%">
						<ul><li><b>${config.memcKey} : ${config.memcDesc}</b></li></ul> 			
					</td>
				</tr>
				<c:if test="${not empty config.memcMessage && fn:length(config.memcMessage)>0}">
				<tr>
					<td width="10%"></td>
					<td width="90%">${config.memcMessage}  
					</td>
				</tr>
				</c:if>
		</c:if>
		</c:forEach>
		<!-- 
		<tr>
			<td width="10%"></td>
			<td width="90%">
				<ul>
					<li>Career Satisfactions:</li> 
				</ul>				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
			For this candidate, career satisfaction means doing work as follow
			</td>
		</tr> 
		<tr>
			<td width="10%"></td>
			<td width="90%">
				1. Let them work systematically, organizing facts, policies, or people, and use time and resources efficiently toward a
logical conclusion			
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				2. Let them use mastered skills while working on concrete and straightforward assignments with clear specifications,
using their strong reasoning powers.				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				3. Is measured and evaluated by fair, logical, explicit, and objective standards				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				4. Is done in a friendly environment with other hardworking and conscientious people who do not bring their personal
problems to work or expect them to share their personal feelings on the job				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				5. Is realistic and tangible in nature and has practical applications and concrete results.				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				6. Has clear expectations and reporting hierarchy				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				7. Let them be productive, organizing the necessary steps and resources, following established procedures, and setting
and meeting deadlines.				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				8. Is done in a stable and predictable environment, but one that is also filled with action and a variety of people				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				9. Can be done with other people, enabling me to be in charge of myself and others				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				10. Lets them make decisions and have a great deal of control and responsibility; where my opinions, recommendations,
and experience and considered important.				
			</td>
		</tr> 
		 -->
	</table>	
	<table
		style="width: 1070px; border-spacing: 0px;"
		border="0">
		<tr>
			<td>
			<img   width="1070px" src="<c:url value='/images/Some_of_Perfacet_Careers.png'/>" /> 
			</td>
		</tr>
	</table> 
	<table
		style="width: 1070px; border-spacing: 0px; border-color: #DBDBDB;"
		border="0">
		<tr>
			<td>In listing careers that are popular for this type, it is important to note that there are successful people of all types in all
careers. However, the following are careers that this candidate may find particularly satisfying and suit with them.
</td>
		</tr>
	</table>
	<table style="width: 1070px;border-spacing:0px;border-color:#DBDBDB;" border="0">
	 <c:set var="endLoop" value="${fn:length(careers)}"/>  
	<c:forEach var="career" items="${careers}"  varStatus="ms">
	<!--  // 0 =0 , =1 , 2=2,3=0,4=1  -->
	        <c:if test="${ms.index % 3==0}">
	        	<tr style="background-color: #DBDBDB;">
					<td width="5%" align="left">&nbsp;</td>
					<td  width="30%" align="left">${career.mecCareerName}</td>
	        </c:if>
	         <c:if test="${ms.index % 3==2}"> 
					<td  width="30%" align="left">${career.mecCareerName}</td>
					<td  width="5%" align="left">&nbsp;</td>
				</tr>
	        </c:if>
	         <c:if test="${ms.index % 3==1}">
	         	<td  width="30%" align="left">${career.mecCareerName}</td>
	         </c:if> 
	</c:forEach>
	<c:if test="${endLoop % 3 !=0 }">
		 <c:set var="loop_td" value="${endLoop % 3 }"/>  
		<%--  <c:forEach var="td" items="${loop_td}"  varStatus="ms"> --%>
		 	 <c:if test="${loop_td==1}">
	         	<td  width="30%" align="left"></td>
	         	<td  width="30%" align="left"></td>
	         	<td  width="5%" align="left"></td>
	         	</tr>
	         </c:if>  
	          <c:if test="${loop_td==2}"> 
	         	<td  width="30%" align="left"></td>
	         	<td  width="5%" align="left"></td>
	         	</tr>
	         </c:if> 
		 <%-- </c:forEach> --%>
	</c:if>
	<!--  
	<tr style="background-color: #DBDBDB;">
		<td width="5%" align="left">&nbsp;</td>
		<td  width="30%" align="left">Government employee</td>
		<td  width="30%" align="left">Pharmaceutical sales</td>
		<td  width="30%" align="left">Computer analyst</td>
		<td  width="5%" align="left">&nbsp;</td>
	</tr>
	<tr style="background-color: #DBDBDB;">
		<td width="5%" align="left">&nbsp;</td>
		<td  width="30%" align="left">Auditor</td>
		<td  width="30%" align="left">Factory supervisor</td>
		<td  width="30%" align="left">Technical trainer</td>
		<td  width="5%" align="left">&nbsp;</td>
	</tr>
	 -->
	</table>
	<table
		style="width: 1070px; border-spacing: 0px;"
		border="0">
		<tr>
			<td align="center">END OF TEST / THANK YOU FOR YOUR TESTING
</td>
		</tr>
		<tr>
			<td align="center">Thank you for using our service-ขอบพระคุณสําหรับการใช ้บริการ แบบบทดสอบของ MissConsult.com

</td>
		</tr>
		<tr>
			<td align="center">สงสัยสอบถาม 02-258-4966
</td>
		</tr>
	</table>
 
</body>
</html>

