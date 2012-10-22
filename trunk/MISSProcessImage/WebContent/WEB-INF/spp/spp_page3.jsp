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
	height:30px;
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
.footer{
	width: 980px; 
	height:100px;
	margin-left:20px;
	margin-right:auto;
}
div.body #tbresult{
	font-family:Tahoma, Geneva, sans-serif;
	font-size:14px;
	font-weight:bold;
	color: #FFF;
}
div.body #tbresult td{
	background-color: #0000FF;
}
div.body #tbsq{
	font-family:Tahoma, Geneva, sans-serif;
	font-size:12px;
}
</style>
</head>

<body>
<table style="width: 1070px;border-spacing:0px;border-color:#DBDBDB;" border="0">
<tr><td>
<div class="header"></div>
<div class="body">
<p style="color:#0000FF; font-weight:bold;">à¸à¸²à¸£à¸­à¹à¸²à¸à¸à¸¥ (Sales Scale)</p>
<table id="tbresult" width="500" border="0" cellspacing="2">
  <tr>
    <td width="169">Result:</td>
    <td width="312">Low Score</td>
  </tr>
  <tr>
    <td>Report Status:</td>
    <td>Reliable</td>
  </tr>
</table>
<p style="color:#0000FF;">A person who gets a Score in "Low Score" is a person who doesnât pass Sales Test cause of their attitude. They might be a good person but they have
a negative attitude that let them lack a strong sense of individual ambition for Sales Career. They donât disassociate themselves from coworker who are
negative and lack ambitions. They can't work under high pressure and competitive environment like Sales Position</p>
<p style="color:#0000FF;">Low Score à¹à¸ªà¸à¸à¹à¸«à¹à¹à¸«à¹à¸à¸à¸¶à¸ à¸à¸¹à¹à¸à¸à¸ªà¸­à¸à¸à¸µà¹à¸¡à¸µà¸à¸´à¸ªà¸±à¸¢à¸à¸µà¹à¹à¸¡à¹à¸à¸­à¸à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¸à¸à¸à¸²à¸£à¸à¸³à¸«à¸à¸à¸¢à¸­à¸à¸«à¸£à¸·à¸­à¸à¸¥à¸à¸²à¸à¸à¸²à¸¢ à¹à¸¥à¸° à¸¥à¸±à¸à¸©à¸à¸°à¸à¸²à¸à¸à¸µà¹à¸¡à¸µà¸à¸§à¸²à¸¡à¸à¸à¸à¸±à¸à¸ªà¸¹à¸ à¸à¸²à¸à¹à¸£à¸à¸à¸¹à¸à¹à¸à¹à¸à¸à¸à¹à¸­à¸: à¹à¸à¹à¸à¸à¸¥à¸¸à¹à¸¡à¸à¸à¸à¸µà¹à¸à¸°à¹à¸¡à¹à¸à¹à¸²à¸à¹à¸à¸à¸à¹à¹à¸à¸ªà¹à¸§à¸à¸à¸°à¹à¸à¸à¸ªà¸¸à¸à¸à¹à¸²à¸¢à¸à¸­à¸à¸à¸²à¸£à¸à¸à¸ªà¸­à¸ Sales à¹à¸à¸¢à¸à¸¥à¸¸à¹à¸¡à¸à¸¹à¹à¸à¸à¸ªà¸­à¸à¸à¸µà¹à¹à¸à¹à¸à¸°à¹à¸à¸ Low Score à¹à¸à¸¢à¸ªà¹à¸§à¸à¹à¸«à¸à¹à¸à¸°à¸à¸¥à¸±à¸§à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¸à¸µà¹à¸à¹à¸­à¸à¹à¸à¹à¸à¸à¸±à¸ à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¸à¸µà¹à¹à¸£à¹à¸à¸£à¸±à¸ à¹à¸¥à¸° à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¸à¸à¸à¸²à¸£à¹à¸à¸¥à¸µà¹à¸¢à¸à¹à¸à¸¥à¸ à¹à¸à¸£à¸²à¸° à¸¥à¸±à¸à¸©à¸à¸°à¸à¸²à¸à¸à¸±à¸à¸à¸¥à¹à¸²à¸§ à¸ªà¸£à¹à¸²à¸à¸à¸§à¸²à¸¡à¸à¸à¸à¸±à¸ à¹à¸¥à¸°à¸à¸³à¹à¸«à¹à¸à¸§à¸à¹à¸à¸²à¸£à¸¹à¹à¸ªà¸¶à¸à¸­à¸¶à¸à¸­à¸±à¸ à¹à¸¥à¸° à¸§à¸´à¸à¸à¸à¸±à¸à¸§à¸¥à¸à¹à¸­à¸ªà¸à¸²à¸à¸à¸²à¸£à¸à¹à¸à¸µà¹à¸à¸§à¸à¸à¸¸à¸¡ à¸«à¸£à¸·à¸­à¹à¸à¸¥à¸µà¹à¸¢à¸à¹à¸à¸¥à¸à¸à¹à¸­à¸¢ à¸à¸³à¹à¸«à¹à¹à¸¡à¹à¸ªà¸²à¸¡à¸²à¸£à¸à¸ªà¸£à¹à¸²à¸à¸à¸¥à¸à¸²à¸à¹à¸à¹à¸à¸µ à¸à¸¥à¸­à¸à¸à¸ à¸«à¸¡à¸à¸à¸³à¸¥à¸±à¸à¹à¸à¹à¸à¹à¸à¹à¸²à¸¢ à¸à¸±à¸à¸à¸±à¹à¸ à¸à¸§à¸à¹à¸à¸²à¹à¸¡à¹à¸ªà¸²à¸¡à¸²à¸£à¸à¸à¸³à¸à¸²à¸à¸à¸µà¹à¸à¸à¸à¸±à¸ à¹à¸à¹à¸à¸¢à¸­à¸à¸à¸²à¸¢ à¸«à¸£à¸·à¸­à¸ªà¸£à¹à¸²à¸à¸à¸¥à¸à¸²à¸à¹à¸à¸¥à¸±à¸à¸©à¸à¸° à¸à¸µà¹à¸à¹à¸­à¸à¹à¸à¹à¸à¸à¸±à¸ à¸à¹à¸²à¸à¹à¸­à¸à¸à¸²à¸£à¸£à¸±à¸à¸à¸³à¹à¸à¹à¸à¸à¹à¸­à¸à¹à¸à¹à¸à¸¥à¸±à¸à¸©à¸à¸°à¸à¸²à¸à¸à¸µà¹à¹à¸¡à¹à¹à¸à¹à¸¥à¸±à¸à¸©à¸à¸°à¸à¸­à¸à¸à¸²à¸£à¹à¸à¹à¸à¸à¸±à¸à¸à¸à¸¢à¸­à¸à¸à¸²à¸¢ à¹à¸à¹à¹à¸à¹à¸à¸¥à¸±à¸à¸©à¸à¸°à¸à¸­à¸à¸à¸²à¸à¸à¸²à¸¢à¹à¸à¸´à¸à¹à¸«à¹à¸à¸³à¸à¸£à¸¶à¸à¸©à¸² (à¸à¸¹à¸£à¸²à¸¢à¸¥à¸°à¹à¸­à¸µà¸¢à¸à¹à¸à¸´à¹à¸¡à¹à¸à¸´à¸¡à¹à¸à¸à¸£à¸²à¸à¹à¸ªà¸à¸à¸à¸¸à¸à¸¥à¸±à¸à¸©à¸à¸° Sales à¹à¸¥à¹à¸§à¸ªà¸±à¸¡à¸ à¸²à¸©à¸à¹à¹à¸à¸´à¹à¸¡à¹à¸à¸´à¸¡)</p><br />
<img src="images/spp07.png" hspace="80" />
<p>Table of Sales Qualifications</p>
<table id="tbsq" width="980" border="0" cellspacing="0">
  <tr>
    <td width="430" align="center" style="font-size:14px; background-color:#00FFFF;">Type of Traits</td>
    <td width="94" align="center" style="font-size:14px; background-color:#00FFFF;">Score<br />
      out of 10</td>
    <td width="430" align="center" style="font-size:14px; background-color:#00FFFF;">Remark</td>
  </tr>
  <tr>
    <td>Flexible - à¸à¸±à¸à¸©à¸°à¸à¸²à¸£à¸à¸£à¸±à¸à¸à¸±à¸§ à¸à¸§à¸²à¸¡à¸¢à¸·à¸à¸«à¸¢à¸¸à¹à¸</td>
    <td align="center">6.25</td>
    <td>à¸à¸²à¸£à¸à¸£à¸±à¸à¸à¸±à¸§ à¸à¸§à¸²à¸¡à¸¢à¸·à¸à¸«à¸¢à¸¸à¹à¸ à¹à¸¥à¸°à¸à¸²à¸£à¹à¸à¸´à¸à¸£à¸±à¸à¸ªà¸´à¹à¸à¹à¸«à¸¡à¹à¹</td>
  </tr>
  <tr>
    <td>Positive Thinking - à¸à¸±à¸¨à¸à¸à¸à¸´à¸à¸§à¸</td>
    <td align="center">6.25</td>
    <td>à¸à¸±à¸¨à¸à¸à¸à¸´à¸à¸²à¸£à¸à¸´à¸à¸à¸§à¸à¸à¹à¸­à¸à¸à¹à¸­à¸à¹à¸¥à¸°à¸ªà¸´à¹à¸à¹à¸§à¸à¸¥à¹à¸­à¸¡à¸£à¸­à¸à¸à¸±à¸§</td>
  </tr>
  <tr>
    <td>Enthusiasm - à¸à¸§à¸²à¸¡à¸à¸£à¸°à¸à¸·à¸­à¸£à¸·à¸­à¸£à¹à¸</td>
    <td align="center">6.00</td>
    <td>à¸¥à¸±à¸à¸©à¸à¸°à¸à¸²à¸à¸à¸¸à¸à¸¥à¸´à¸à¸ à¸²à¸à¸à¸µà¹à¹à¸ªà¸à¸à¸à¸¶à¸à¸à¸§à¸²à¸¡à¸à¸£à¸°à¸à¸·à¸­à¸£à¸·à¸­à¸£à¹à¸à¹à¸¥à¸°à¸à¸¥à¸±à¸</td>
  </tr>
  <tr>
    <td>Out going - à¸à¸³à¸à¸²à¸à¸­à¸­à¸à¸ à¸²à¸¢à¸à¸­à¸</td>
    <td align="center">5.75</td>
    <td>à¸¥à¸±à¸à¸©à¸à¸°à¸à¸­à¸à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¸ à¸²à¸¢à¸à¸­à¸ à¸à¸²à¸£à¸ªà¸£à¹à¸²à¸à¹à¸à¸£à¸·à¸­à¸à¹à¸²à¸¢</td>
  </tr>
  <tr>
    <td>Fast Learning - à¸à¸±à¸à¸©à¸°à¸à¸²à¸£à¹à¸£à¸µà¸¢à¸à¸£à¸¹à¹</td>
    <td align="center">5.38</td>
    <td>à¸à¸²à¸£à¹à¸£à¸µà¸¢à¸à¸£à¸¹à¹à¹à¸¥à¸°à¸à¸²à¸£à¹à¸à¹à¸²à¹à¸à¹à¸à¹à¸£à¸·à¹à¸­à¸à¸à¹à¸²à¸à¹</td>
  </tr>
  <tr>
    <td>Stress &amp; Competitive - à¸à¸²à¸£à¸£à¸±à¸à¸¡à¸·à¸­à¸à¹à¸­à¸à¸²à¸£à¹à¸à¹à¸à¸à¸±à¸</td>
    <td align="center">5.13</td>
    <td>à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¸à¸à¸à¸±à¸à¹à¸¥à¸°à¸à¸²à¸£à¹à¸à¹à¸à¸à¸±à¸à¸à¸µà¹à¸¡à¸µà¹à¸à¹à¸²à¸«à¸¡à¸²à¸¢à¹à¸à¹à¸à¸à¸±à¸§à¸à¸³à¸«à¸à¸</td>
  </tr>
  <tr>
    <td>Ambition - à¸à¸§à¸²à¸¡à¸à¸°à¹à¸¢à¸­à¸à¸°à¸¢à¸²à¸</td>
    <td align="center">5.00</td>
    <td>à¸à¸±à¸¨à¸à¸à¸à¸´à¸à¹à¸­à¸à¸²à¸£à¸à¸±à¸à¸à¸²à¸à¸à¹à¸­à¸à¹à¸¥à¸°à¸à¸²à¸£à¸¡à¸¸à¹à¸à¸ªà¸¹à¹à¸à¸¥à¸ªà¸³à¹à¸£à¹à¸</td>
  </tr>
  <tr>
    <td>Communication - à¸à¸²à¸£à¸ªà¸·à¹à¸­à¸ªà¸²à¸£</td>
    <td align="center">5.00</td>
    <td>à¸à¸²à¸£à¸ªà¸·à¹à¸­à¸ªà¸²à¸£à¹à¸¥à¸°à¸à¸£à¸°à¸ªà¸´à¸à¸à¸´à¸ à¸²à¸à¸à¸­à¸à¸à¸²à¸£à¸à¸´à¸à¸à¹à¸­à¸ªà¸·à¹à¸­à¸ªà¸²à¸£à¸à¸±à¸à¸à¸¹à¹à¸­à¸·à¹à¸</td>
  </tr>
  <tr>
    <td>Problem Solving - à¸à¸²à¸£à¹à¸à¹à¹à¸à¸à¸±à¸à¸«à¸²à¹à¸à¸à¸²à¸°à¸«à¸à¹à¸²</td>
    <td align="center">4.75</td>
    <td>à¸à¸²à¸£à¹à¸à¹à¹à¸à¸à¸±à¸à¸«à¸²à¹à¸à¸à¸²à¸°à¸«à¸à¹à¸²à¹à¸¥à¸°à¸à¸²à¸£à¸à¸´à¸à¹à¸à¸´à¸à¹à¸«à¸à¸¸à¹à¸¥à¸°à¸à¸¥</td>
  </tr>
  <tr>
    <td bgcolor="#666666">&nbsp;</td>
    <td bgcolor="#666666">&nbsp;</td>
    <td bgcolor="#666666">&nbsp;</td>
  </tr>
</table>
<p align="center">Thank you for using our professional test by MissConsult.com</p>
<center><img src="images/spp08.png"  /></center>
</div>
<div class="footer"></div>
</td></tr>
</table>
</body>
</html>
