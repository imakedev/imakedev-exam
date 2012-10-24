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
<script type="text/javascript" src="/MISSProcessImage/PowerChart/Charts/FusionCharts.js"></script> 
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
	<table
		style="width: 1070px; border-spacing: 0px; border-color: #DBDBDB;"
		border="0">
		<tr>
			<td width="10%"></td>
			<td width="90%">
				<ul>
					<li>Introduction: </li> 
				</ul>				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				Summary results of the attitude of the person, than at the test. Demonstrate the nature of attitudes found.
Through the development process of the mind and the nature of the different test Can be observed from the interviews.				
			</td>
		</tr> 
	</table> 
	
<table style="width: 1070px;border-color: black;" border="0">
	<tr>
		<td width="50%" align="center">
			<div id="chartdiv"><small>Loading chart...</small>
			</div>
		</td>
		<td width="50%" align="center">
			<!-- <table width="300px"> -->
			<table style="width: 300px;border-spacing:0px;border-color:#DBDBDB;" border="1">
				<tr>
					<td align="center" style="background-color: #329ACC">Dominance
					</td>
					<td align="center" style="background-color: #9ACCCC">Sub-Dominance 
					</td>
				</tr>
				<tr>
					<td align="center"><font color="#FF3D3D">Passive</font>
					</td>
					<td align="center"><font color="#0303FF">Imaginative</font>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<table
		style="width: 1070px; border-spacing: 0px; border-color: #DBDBDB;"
		border="0">
		<tr>
			<td width="10%"></td>
			<td width="90%">
				<ul>
					<li>Inner Personality:</li> 
				</ul>				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
			The person taking the test is someone who is good with concentration and can plan well. Likes to think deeply when
planning on things. However when they think to too much it can affect on the outcome because of too much awareness
that lead to some skepticism and may not want any changes or things that are unfamiliar.
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				<ul>
					<li>Social Personality:</li> 
				</ul>				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
			The social characteristics of the person taking the test includes possessing good-natured personality, understanding and
making friends with others. All the actions and thoughts of this person is focused on their effort to establish relationships
of feeling with other people. A well liked and easily accepted among people but if they are denied to be in a group they
might feel bad easily. This person constantly seeks expression and tries accordingly to arouse corresponding feelings in
others, as a result of their deep emotions, feelings towards others and spending a lot of time with peers and trying to get
accepted by everyone, this may sometimes lead them to lose focus on their intended purpose, objectives and goals. A
person that can be exceedingly unhappy when out of touch with the environment and if they meet with no sympathy, this
person may feel discourage and indifferent. They may also tend to keep those feelings within themselves that can harm
their health without even knowing, like having headaches and even weight loss. So learning to let go is essential for this
person and they are not suited to work in an environment where there are pressure, no harmony and understanding.
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				<ul>
					<li>Leadership Style:</li> 
				</ul>				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
			In becoming a leader or by developing to become one, the leadership qualities of the person taking the test focuses on
the importance of being recognized and being appreciated by the organization or team, so as leaders in an organization it
is more appropriate and important for them to have the support and good relation among the team rather than focusing on
being competitive and achieving targets.
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				<ul>
					<li>Core Strength: Idealist</li> 
				</ul>				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
			Likes to create, develop and think about new things and ideas, but dislikes being treated impersonally. They excel in
working in a friendly and in an environment where people are helping one another and building good relationships.
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				<ul>
					<li>Interpersonal Type:</li> 
				</ul>				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
			The person taking this test possesses the characteristics of an individual who likes being sociable and entertain. A person
who likes to create and build relationship with the others and someone who very much likes to meet people. This person
understands the feelings of others and tends to be sensitive to their needs. A very friendly and someone who likes
activities at work.
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
				<ul>
					<li>Weaker Point:</li> 
				</ul>				
			</td>
		</tr>
		<tr>
			<td width="10%"></td>
			<td width="90%">
			At present, the person taking this test possesses the characteristics of an individual who is confident and has got
knowledge and ideas of their own. It is important for them to use their knowledge and skills at work. But they do not like to
work if they are not given the opportunity to express their own ideas. They are meditative persons who like to work on
things which need deep thoughts and they have a diverse idea about it which ultimately makes them love the work and
likes the opportunity of having their ideas known and used.
			</td>
		</tr>
</table>
<script type="text/javascript">
//var dataString='<chart   canvasBorderAlpha="0"  yMaxValue="10" showLimits="0" bgColor="FFFFFF" plotBorderThickness="1" radarSpikeThickness="3" divlineColor="9A9F9B" anchorRadius="4" anchorBorderThickness="2">\n\
var dataString='<chart  canvasBorderAlpha="0"  bgColor="FFFFFF" plotBorderThickness="1" radarSpikeThickness="3" divlineColor="9A9F9B">\n\
	<categories>\n\
	<category label="Imaginative" toolText="Centralized Deployability"/>\n\
	<category label="Perceiving" toolText="Centralized Signature Updating"/>\n\
	<category label="Assertive" toolText="Active Firewall"/>\n\
	<category label="Introvert" toolText="Centralized Access &amp; Firewall"/>\n\
	<category label="Factual" toolText="Centralized Network Health Monitoring"/>\n\
	<category label="Judging" toolText="Cost"/>\n\
	<category label="Passive" toolText="Technical Support"/>\n\
	<category label="Extravert" toolText="Technical Support"/>\n\
	</categories>\n\
	<dataset showValues="1">\n\
	<set value="8" />\n\
	<set value="9" />\n\
	<set value="9"/>\n\
	<set value="8"/>\n\
	<set value="7"/>\n\
	<set value="9"/>\n\
	<set value="8"/>\n\
	<set value="6" />\n\
	</dataset>\n\
	</chart>';
	/*
	<dataset seriesName="Norton">\n\
	<set value="7"/>\n\
	<set value="6"/>\n\
	<set value="6"/>\n\
	<set value="4"/>\n\
	<set value="7"/>\n\
	<set value="6"/>\n\
	<set value="5"/>\n\
	</dataset>\n\
	</chart>';
	*/
	  FusionCharts.setCurrentRenderer('javascript'); // for new version 
             var chart = new FusionCharts("/MISSProcessImage/PowerChart/Charts/Radar.swf", "ChartId", "380", "300", "0");
            chart.setXMLData( dataString);
            chart.render("chartdiv");
       </script>
</body>
</html>

