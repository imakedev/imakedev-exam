<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Report : Sale Test</title>
<style type="text/css">
body{
padding:0 0 0 0;
/* margin:0 0 0 0; */
margin:2 2 2 2;
}

div.header{
	margin-left:20px;
	width:980px;
	margin-right:auto;
}
div.body{
	margin-left:20px;
	width:980px;
	margin-right:auto;
	font-family: Tahoma, Geneva, sans-serif;
}
p{
	font-family:Tahoma, Geneva, sans-serif;
	font-size:14px;
}
.body span.rp_detail{
	font-family:Tahoma, Geneva, sans-serif;
	font-size:14px;
	font-weight:bold;
	color: #4A708B;
}
.body .rp_title{
	font-family:Tahoma, Geneva, sans-serif;
	font-size:14px;
	font-weight:bold;
	color: #4A708B;
}
.body #rp_title{
	text-decoration: underline;
}
.body div#introduce{
	width: 960px;
}
.body #introduce table {
	text-align: center;
}
.body #tbresult{
	font-family:Tahoma, Geneva, sans-serif;
	font-size:14px;
	font-weight:bold;
	color: #FFF;
	border:none;
}
.footer{
	width: 980px; 
	height:100px;
	margin-left:20px;
	margin-right:auto;
}
</style>
</head>

<body>
<table style="width: 1070px;border-spacing:0px;border-color:#DBDBDB;" border="0">
<tr><td>
<div class="header">
	<div id="banner"> <img src="images/spp03.png" align="left" />
	</div>
</div>
<div class="body">
    <p align="right">Developed By MissConsult.com</p>
    <table width="420" border="0" cellspacing="3">
  <tr>
    <td id="rp_title" class="rp_title" width="150" height="30" valign="top">Report Prepared for:</td>
    <td width="231">&nbsp;</td>
  </tr>
  <tr>
    <td class="rp_title">Name of Candidate:</td>
    <td><span class="rp_detail">à¸à¸£à¸£à¸¨  à¸à¸à¸¨à¸²à¸ªà¸à¸­à¸à¸à¸¸à¸¥</span></td>
  </tr>
  <tr>
    <td class="rp_title">Date of Test:</td>
    <td><span class="rp_detail">20/09/2553</span></td>
  </tr>
  <tr>
    <td class="rp_title">Time of Test:</td>
    <td><span class="rp_detail">16:24:14</span></td>
  </tr>
  <tr>
    <td class="rp_title">Age:</td>
    <td><span class="rp_detail">37</span></td>
  </tr>
  <tr>
    <td class="rp_title">Position Apply:</td>
    <td><span class="rp_detail">RSM &amp; Sales training manager</span></td>
  </tr>
  <tr>
    <td class="rp_title">Company Apply:</td>
    <td><span class="rp_detail">Edward</span></td>
  </tr>
  <tr>
    <td class="rp_title">Tel:</td>
    <td><span class="rp_detail">0850706211</span></td>
  </tr>
  <tr>
    <td class="rp_title">E-mail:</td>
    <td><span class="rp_detail">ptat@scj.com</span></td>
  </tr>
</table>
 
	&nbsp;
	<img src="images/spp04.png" />
    <div id="introduce">
    <p style="color:#990000; font-weight:bold;">Introduction:</p>
    <p style="color:#0000FF;">The Sales Performance Predictor is the specific test for Sales Position only, an individual's Sales Performance Predictor results can be predicted as the
level of suitable for Sales Position. The objective of this test is to provide the sales qualification data for company as the supportive tool for Sales
Selection processes. The report provide the potential of qualification in each of individual that be considered as important qualification for Sales
Position.</p>
    <p>à¹à¸à¸à¸à¸£à¸°à¹à¸¡à¸´à¸ Sales Performance Predictor à¸«à¸£à¸·à¸­ Sale PPÂ® à¹à¸à¹à¸à¹à¸à¸à¸à¸£à¸°à¹à¸¡à¸´à¸à¸ªà¸³à¸«à¸£à¸±à¸à¸à¸³à¹à¸«à¸à¹à¸à¸à¸²à¸à¸à¹à¸²à¸¢à¸à¸²à¸¢à¹à¸à¹à¸²à¸à¸±à¹à¸ à¹à¸à¸¢à¸£à¸¹à¸à¹à¸à¸à¸à¸£à¸°à¹à¸¡à¸´à¸à¹à¸à¹à¸à¸à¸²à¸£ à¸à¸£à¸°à¹à¸¡à¸´à¸à¸à¸¶à¸à¸£à¸°à¸à¸±à¸à¸à¸§à¸²à¸¡à¹à¸«à¸¡à¸²à¸°à¸ªà¸¡à¸à¸­à¸à¸à¸¸à¸à¸à¸¥à¹à¸à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¸ªà¸³à¸«à¸£à¸±à¸à¸à¸³à¹à¸«à¸à¹à¸à¸à¸²à¸à¸à¹à¸²à¸¢à¸à¸²à¸¢ à¸§à¸±à¸à¸à¸¸à¸à¸£à¸°à¸ªà¸à¸à¹à¸à¸­à¸à¹à¸à¸à¸à¸£à¸°à¹à¸¡à¸´à¸à¸à¸·à¸­ à¹à¸à¸£à¸·à¹à¸­à¸à¸¡à¸·à¸­à¸ªà¸à¸±à¸à¸ªà¸à¸¸à¸à¹à¸à¸à¸²à¸£à¸à¸±à¸à¹à¸¥à¸·à¸­à¸ à¸à¸à¸±à¸à¸à¸²à¸à¹à¸à¹à¸²à¸ªà¸¹à¹à¸à¸³à¹à¸«à¸à¹à¸à¸à¸²à¸à¸à¹à¸²à¸¢à¸à¸²à¸¢à¹à¸à¸¢à¹à¸à¸à¸à¸£à¸°à¹à¸¡à¸´à¸à¸à¸°à¹à¸«à¹à¹à¸à¸§à¹à¸à¹à¸¡à¸à¸²à¸à¸à¹à¸²à¸à¸à¸¸à¸à¸ªà¸¡à¸à¸±à¸à¸´à¸à¸µà¹à¸à¸³à¹à¸à¹à¸à¸à¹à¸­à¸à¸¡à¸µà¸à¸­à¸à¸à¸¸à¸à¸à¸¥à¸ªà¸³à¸«à¸£à¸±à¸à¸à¸³à¹à¸«à¸à¹à¸à¸à¸²à¸à¸à¹à¸²à¸¢à¸à¸²à¸¢</p>
    <p style="color:#4A708B; font-weight:bold;">à¸à¸¥à¸à¸²à¸£à¸à¸à¸ªà¸­à¸à¸à¸­à¸à¸à¸¸à¸&nbsp;<span id="intro_name">à¸à¸£à¸£à¸¨  à¸à¸à¸¨à¸²à¸ªà¸à¸­à¸à¸à¸¸à¸¥</span>&nbsp;à¹à¸à¹à¸à¸à¸±à¸à¸à¸µà¹</p>
    <p style="color:#990000; font-weight:bold;">1. à¸¥à¸±à¸à¸©à¸à¸°à¸à¸²à¸£à¸à¸­à¸à¸à¸³à¸à¸²à¸¡à¸à¸­à¸à¹à¸à¸à¸à¸à¸ªà¸­à¸ - Lie Detector Result</p>
    <img src="images/spp05.png" hspace="90"  />
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Neutral: A tester is neutral lying on testing. She/he may want to make the test result to be good so that she may lie some points but the test result is
reliable. à¸à¸¹à¹à¸à¸à¸ªà¸­à¸à¸­à¸²à¸à¸¡à¸µà¸à¸§à¸²à¸¡à¸à¸±à¸à¸§à¸¥à¹à¸à¸à¸²à¸£à¸à¸³à¸à¹à¸­à¸ªà¸­à¸à¸«à¸£à¸·à¸­à¸à¸´à¸à¸¡à¸²à¸ à¸à¸³à¹à¸«à¹à¸à¸­à¸à¸à¹à¸­à¸ªà¸­à¸à¸à¸²à¸à¸à¹à¸­à¹à¸à¸·à¹à¸­à¸à¸±à¹à¸à¹à¸à¸à¸¹à¸à¸µà¸«à¸£à¸·à¸­à¸à¸±à¹à¸à¹à¸à¹à¸«à¹à¸à¸¥à¸­à¸­à¸à¸¡à¸²à¸à¸µà¹à¸à¹à¸ªà¸²à¸¡à¸²à¸£à¸à¹à¸à¸·à¹à¸­à¸à¸·à¸­à¸à¸¥à¸à¸°à¹à¸à¸à¹à¸à¹</p>
	<p style="color:#0000FF; font-weight:bold;">2. à¸à¸¥à¸à¸à¸ªà¸­à¸ Sales Scale - Sales Performance Predictor Result</p>
    <p style="color:#0000FF;">The Sales Performance Predictor is designed as a supportive tool for Sales selection which provide the potential of qualifications which be considered as
important for Sales Position such as Ambition, Positive Thinking, Work well under pressure, High competitive Problem solving and Fast learning. Also
provide the data about the potential of qualification that can be found from the test in the table format as better consideration.</p>
	<p>à¹à¸à¸à¸à¸à¸ªà¸­à¸à¸§à¸±à¸à¸à¸¸à¸à¸ªà¸¡à¸à¸±à¸à¸´à¸à¸­à¸à¸à¸¹à¹à¸à¸³à¸à¸²à¸à¹à¸à¸à¸³à¹à¸«à¸à¹à¸à¸à¸²à¸ Sales à¹à¸à¸·à¹à¸­à¸§à¸±à¸à¸à¸¸à¸à¸£à¸°à¸ªà¸à¸à¹à¸à¸²à¸£à¸à¸±à¸à¹à¸¥à¸·à¸­à¸à¸à¸¸à¸à¸à¸¥à¸à¸¹à¹à¸¡à¸µà¸à¸¸à¸à¸¥à¸±à¸à¸©à¸à¸°à¹à¸¥à¸°à¸à¸¸à¸à¸ªà¸¡à¸à¸±à¸à¸´à¸à¸µà¹à¹à¸«à¸¡à¸²à¸°à¸ªà¸¡à¹à¸¥à¸°à¸¡à¸µà¹à¸à¸§à¹à¸à¹à¸¡à¹à¸à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¹à¸à¹ à¸¡à¸µà¸à¸£à¸°à¸ªà¸´à¸à¸à¸´à¸ à¸²à¸à¸ªà¸¹à¸à¸ªà¸¸à¸à¸à¹à¸­à¸à¸³à¹à¸«à¸à¹à¸à¸à¸²à¸ Sales à¹à¸à¸à¸à¸à¸ªà¸­à¸ Sales à¸à¸¹à¸à¸­à¸­à¸à¹à¸à¸à¹à¸à¸·à¹à¸­à¸§à¸±à¸à¹à¸£à¸à¸à¸¹à¸à¹à¸ à¸à¸£à¸°à¸à¸¸à¹à¸ à¹à¸¥à¸°à¸à¸§à¸²à¸¡à¸à¹à¸­à¸à¸à¸²à¸£à¸à¸£à¸°à¸ªà¸à¸à¸§à¸²à¸¡à¸ªà¸³à¹à¸£à¹à¸à¹à¸à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¸à¸¶à¹à¸à¸£à¸§à¸¡à¸à¸¶à¸ à¸à¸§à¸²à¸¡à¸à¹à¸­à¸à¸à¸²à¸£à¹à¸à¹à¸à¸­à¸´à¸ªà¸£à¸°à¸à¸²à¸à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¸à¹à¸§à¸¢à¸à¸§à¸²à¸¡à¸ªà¸²à¸¡à¸²à¸£à¸à¸à¸­à¸à¸à¸à¹à¸­à¸ à¸à¸§à¸²à¸¡à¸ªà¸²à¸¡à¸²à¸£à¸à¸à¸²à¸à¸à¹à¸²à¸à¸à¸²à¸£à¸à¸±à¸à¸à¸¹à¸à¹à¸à¹à¸¡à¸à¹à¸²à¸§ à¸à¸±à¸¨à¸à¸à¸à¸´à¹à¸à¸à¸²à¸à¸à¸§à¸à¸à¸²à¸£à¸¡à¸­à¸à¸à¸±à¸à¸«à¸²à¸à¸¥à¸°à¸à¸²à¸£à¹à¸à¹à¸à¸±à¸à¸«à¸²à¸à¸¥à¸­à¸à¸à¸ à¸à¸§à¸²à¸¡à¸ªà¸²à¸¡à¸²à¸£à¸à¹à¸à¸à¸²à¸£à¸ªà¸£à¹à¸²à¸à¹à¸£à¸à¸à¸¹à¸à¹à¸à¹à¸«à¹à¹à¸à¹à¸à¸à¹à¸­à¸</p>
    <img src="images/spp06.png" hspace="90"  />
    <p style="color:#990000; font-weight:bold;">Table of result</p>
    <table width="960" border="0" cellspacing="0" id="tbresult">
  <tr>
    <td width="151" height="50" style="background-color:#999;">Score Score /<br />
      Result</td>
    <td width="150" style="background-color:#900;">Very Lowest Score</td>
    <td width="150" style="background-color:#F9F;">Low Score<br />
      âNot Interestedâ</td>
    <td width="150" style="background-color:#F90;">Moderate Score<br />
      âCan Be hiredâ</td>
    <td width="150" style="background-color:#999;">Good Score<br />
      âStarâ</td>
    <td width="162" style="background-color:#F30;">Perfect Score<br />
      âRole Modelâ</td>
  </tr>
  <tr>
    <td height="25" style="background-color:#FFFF99; color:#000099;">STD</td>
    <td style="background-color:#900;">Below 20</td>
    <td style="background-color:#F9F;">20-24</td>
    <td style="background-color:#F90;">25-29</td>
    <td style="background-color:#999;">30-35</td>
    <td style="background-color:#F30;">36-45</td>
  </tr>
  <tr>
    <td height="25" style="background-color:#FFFF99; color:#990000;">Result</td>
    <td style="background-color:#FFFF99;">&nbsp;</td>
    <td style="background-color:#FFFF99; color:#000099;">24</td>
    <td style="background-color:#FFFF99;">&nbsp;</td>
    <td style="background-color:#FFFF99;">&nbsp;</td>
    <td style="background-color:#FFFF99;">&nbsp;</td>
  </tr>
</table>

    </div>
</div>
<div class="footer"></div>
</td></tr></table>
</body>
</html>
