<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CST Report</title>
<style type="text/css">
body{
padding:0 0 0 0;
/* margin:0 0 0 0; */
margin:2 2 2 2;
}

div.header{
	margin-left:20px;
	width:880px;
	margin-right:auto;
}
div.body{
	margin-top:50px;
	margin-bottom:100px;
	margin-left:20px;
	width:880px;
	height:auto;
	margin-right:auto;
	font-family:Tahoma, Geneva, sans-serif;
}
p{
	font-family:Tahoma, Geneva, sans-serif;
	font-size:14px;
}
.footer{
	width: 880px; 
	height:auto;
	margin-left:20px;
	margin-right:auto;
	vertical-align:bottom;
}
#txttitle{
	width: 650px;
	font-size:16px;
	font-weight:bold;
	color:#666666;
}
.footer table {
	text-align: center;
}
</style>
</head>

<body>
<table style="width: 1070px;border-spacing:0px;border-color:#DBDBDB;" border="0">
<tr><td>
<div class="header">
<table width="880" border="0" cellspacing="0">
  <tr>
    <td width="676"><p id="txttitle">Psychometric Testing & Career Development & Assessment Center Training</p></td>
    <td width="200"><img src="<c:url value='/images/cst/cst_banner.png'/>" align="right"  /></td>
  </tr>
  <tr>
  <td></td>
  <td align="right"><p style="color:#990000;">Private & Confidential</p><p style="color:#666666;">www.missconsult.com</p></td>
  </tr>
</table></div>
<div class="body">
<img src="<c:url value='/images/cst/cst01.png'/>" />
<p style="font-size:40px; font-family:AngsanaUPC; font-weight:bold; color:#666666;">&nbsp;แบบประเมิณทัศนคติด้านงานบริการ</p>
<p style="font-size:36px; font-family:AngsanaUPC; font-weight:bold; color:#990000;">&nbsp;SERVICE ATTITUDE TEST REPORT</p>
<p><img src="<c:url value='/images/cst/cst01.png'/>" />
  <br /><br />
</p>
<p>&nbsp;</p>
<p style="font-size:36px; font-family:AngsanaUPC; font-weight:bold; color:#000;">Report prepared for:&nbsp;<span class="rp_name">Edward</span></p>
<p><img src="<c:url value='/images/cst/cst_banner02.png'/>"/></p>
<p>&nbsp; </p>
<p style="font-size:34px; font-family:AngsanaUPC; font-weight:bold; color:#000;">Report prepared for:</p>
<p style="font-size:30px; font-family:AngsanaUPC; font-weight:bold; color:#0000FF;">Prataporn Anancharoenyos</p>
<table width="500" border="0" cellspacing="0">
  <tr>
    <td width="250" style="font-weight:bold;">ตำแหน่ง / Position Applied For :</td>
    <td width="231">Section head</td>
  </tr>
  <tr>
    <td style="font-weight:bold;">อีเมลล์ / E-mail :</td>
    <td>Prataporn_a@acer.co.th</td>
  </tr>
  <tr>
    <td style="font-weight:bold;">เบอร์โทรศัพท์ / Tel :</td>
    <td>0814554406</td>
  </tr>
  <tr>
    <td style="font-weight:bold;">อายุ / Age :</td>
    <td>30</td>
  </tr>
  <tr>
    <td style="font-weight:bold;">วันที่ทดสอบ / Date Test :</td>
    <td>02/03/2554</td>
  </tr>
</table>

</div>
<div class="footer">
<table width="880" border="0" cellspacing="5">
  <tr>
    <td width="163"><img src="<c:url value='/images/cst/cst_footer01.png'/>" /></td>
    <td width="381" style="color:#666;">14th Flr.,Q.House Asoke Bldg., 66 Sukhumvit 21
North Klongtoey,Wattana, Bangkok 10110</td>
    <td width="103"><img src="<c:url value='/images/cst/cst_footer02.png'/>" /></td>
    <td width="200" style="color:#666;">www.missconsult.com
info@missconsult.com</td>
  </tr>
</table>

</div>
</td></tr>
</table>
</body>
</html>