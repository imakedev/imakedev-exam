<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
<title>CST Report</title>
<style type="text/css">
body{
padding:0 0 0 0;
/* margin:0 0 0 0; */
margin:2 2 2 2;
}

div.header{
	margin-left:20px;
	width:980px;
	height:220px;
	margin-right:auto;
	font-family:Tahoma, Geneva, sans-serif;
}
div.body{
	margin-top:50px;
	margin-bottom:100px;
	margin-left:20px;
	width:980px;
	height:auto;
	margin-right:auto;
	font-family:Tahoma, Geneva, sans-serif;
}
p{
	font-family:Tahoma, Geneva, sans-serif;
	font-size:14px;
}
.footer{
	width: 980px; 
	height:auto;
	margin-left:20px;
	margin-right:auto;
	vertical-align:bottom;
}
.header table {
	text-align: right;
}
span.name {
	font-size:16px;
	font-weight:bold;
	color: #0000FF;
}
</style>
</head>

<body>
<table style="width: 1070px;border-spacing:0px;border-color:#DBDBDB;" border="0">
<tr><td>
<div class="header">
<table width="450" border="0" align="right" cellspacing="5">
  <tr>
    <td style="font-size:24px; font-weight:bold; color:#666666;">à¹à¸à¸à¸à¸£à¸°à¹à¸¡à¸´à¸à¸à¸±à¸¨à¸à¸à¸à¸´à¸à¹à¸²à¸à¸à¸²à¸à¸à¸£à¸´à¸à¸²à¸£</td>
  </tr>
  <tr>
    <td style="font-size:22px; font-weight:bold; color:#999999; font-family: Georgia, 'Times New Roman', Times, serif;">SERVICE ATTITUDE TEST REPORT</td>
  </tr>
  <tr>
    <td style="color: #930;">Private & Confidential</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><span class="name">Prataporn Anancharoenyos</span></td>
  </tr>
  <tr>
    <td>Edward</td>
  </tr>
  <tr>
    <td>à¸§à¸±à¸à¸à¸µà¹à¸à¸³à¸à¸²à¸£à¸à¸à¸ªà¸­à¸ <span id="date">02/03/2554</span></td>
  </tr>
</table>
<br />


</div>
<div class="body">
<p style="color: #0000FF;"><span style="font-weight:bold">à¹à¸à¸à¸à¸£à¸°à¹à¸¡à¸´à¸à¸à¸±à¸¨à¸à¸à¸à¸´à¸à¸²à¸à¸à¹à¸²à¸à¸à¸²à¸£à¸à¸£à¸´à¸à¸²à¸£ </span>- Service Attitude Personality Test</p>
<p><span style="font-weight:bold">à¹à¸à¸à¸à¸à¸ªà¸­à¸à¸à¸¸à¸</span> Service Attitude Personality Test à¹à¸à¹à¸à¹à¸à¸à¸à¸à¸ªà¸­à¸à¸à¸µà¹à¸à¸¹à¸à¸­à¸­à¸à¹à¸à¸à¸¡à¸²à¸§à¸±à¸à¸¥à¸±à¸à¸©à¸à¸°à¸à¸±à¸¨à¸à¸à¸à¸´à¸à¹à¸­à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¸à¹à¸²à¸à¸à¸£à¸´à¸à¸²à¸£à¹à¸à¸¢à¹à¸à¸à¸²à¸°à¹à¸à¸²à¸°à¸à¸à¸§à¸±à¸à¸à¸´à¸ªà¸±à¸¢ 6 à¸¥à¸±à¸à¸©à¸à¸° à¹à¸¥à¸° à¸à¸¹à¸à¸­à¸­à¸à¹à¸à¸à¸¡à¸²à¹à¸à¸·à¹à¸­à¸à¸²à¸£à¸à¸±à¸à¹à¸à¸«à¸à¸à¸­à¸à¸à¸¹à¹à¸à¸à¸ªà¸­à¸à¸à¸µà¹à¸à¹à¸­à¸à¸à¸²à¸£à¸«à¸¥à¸­à¸à¸à¸¥à¸à¸²à¸£à¸à¸à¸ªà¸­à¸ à¹à¸«à¸¡à¸²à¸°à¸ªà¸³à¸«à¸£à¸±à¸à¸à¸²à¸£à¸§à¸´à¹à¸à¸£à¸²à¸°à¸«à¹à¸à¸´à¸ªà¸±à¸¢à¸à¸­à¸à¸à¸¸à¸à¸à¸¥ à¸à¸µà¹à¸ªà¹à¸à¸à¸¥à¸à¹à¸­à¸à¸²à¸£à¸à¸³à¸à¸²à¸à¸à¹à¸²à¸à¸à¸£à¸´à¸à¸²à¸£à¹à¸à¹à¸à¹à¸¥à¸°à¸­à¸à¸à¹à¸à¸£</p>
<p style="color: #990000; text-decoration:underline;"><span style="font-weight:bold">à¸à¸±à¹à¸à¸à¸µà¹ 1: </span> à¸à¸¥à¸à¸£à¸°à¹à¸¡à¸´à¸à¸à¸²à¸£à¹à¸à¸«à¸à¸£à¸°à¸«à¸§à¹à¸²à¸à¸à¸²à¸£à¸à¸³à¹à¸à¸à¸à¸à¸ªà¸­à¸ / Lie Scale Result</p>

<table class="table1">
                <thead>
                    <tr>
                        <th id="score" scope="col" abbr="Starter" style="background-color: #BEBEBE;">à¸à¸¥à¸à¸°à¹à¸à¸<br />Score</th>
                        <th id="very" scope="col" abbr="Medium">à¸à¸à¸à¸²à¸£à¹à¸à¸«à¸à¸¡à¸²à¸à¹à¸¡à¹à¸à¸§à¸£à¸à¸´à¸à¸²à¸£à¸à¸² <br />Very High Score
âDangerousâ</th>
                        <th id="nearly" scope="col" abbr="Business">à¸à¸à¸à¸²à¸£à¹à¸à¸«à¸à¸¡à¸²à¸à¹à¸¡à¹à¸à¸§à¸£à¸à¸´à¸à¸²à¸£à¸à¸² <br />Nearly High Score
âBe carefulâ</th>
                        <th id="neutral" scope="col" abbr="Deluxe">à¸£à¸°à¸à¸±à¸à¸à¸à¸à¸´à¸à¸´à¸à¸²à¸£à¸à¸²à¹à¸à¹ <br />Some LIER Score
âNeutralâ</th>
                        <th id="good" scope="col" abbr="Deluxe">à¸à¸·à¹à¸­à¸à¸£à¸à¸à¹à¸­à¸à¸²à¸£à¸à¸à¸ªà¸­à¸à¸à¸µà¸¡à¸²à¸ <br />Perfect Score
âGoodâ</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>à¸à¹à¸²à¸à¸°à¹à¸à¸à¸à¸¥à¸²à¸</td>
                        <td>90</td>
                        <td>72-89</td>
                        <td>54-71</td>
                        <td>à¸à¹à¸³à¸à¸§à¹à¸² - 54</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                        <td>à¸à¸¥à¸à¸µà¹à¹à¸à¹</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><span style="font-weight:bold; font-size:22px;">42.75</span></td>
                    </tr>
                </tbody>
        </table>
<p style="color:#0066CC;"><span style="font-weight:bold">à¸à¸¥à¸à¸²à¸£à¸à¸£à¸°à¹à¸¡à¸´à¸/Result:</span>&nbsp;à¸à¸³à¸à¹à¸­à¸ªà¸­à¸à¸à¹à¸§à¸¢à¸à¸§à¸²à¸¡à¸à¸·à¹à¸­à¸à¸£à¸ à¸à¸±à¹à¸à¹à¸ à¸ªà¸²à¸¡à¸²à¸£à¸à¸à¸´à¸à¸²à¸£à¸à¸²à¸à¸¥à¹à¸à¸à¸±à¹à¸à¸à¹à¸­à¹à¸à¸à¸¥à¸¸à¹à¸¡à¸à¸°à¹à¸à¸à¸à¹à¸³à¸à¸§à¹à¸² 54 à¸à¸°à¹à¸à¸ à¸«à¸¡à¸²à¸¢à¸à¸¶à¸à¸à¸¹à¹à¸à¸à¸ªà¸­à¸à¸à¸±à¹à¸à¹à¸à¹à¸à¸à¸²à¸£à¸à¸³à¸à¹à¸­à¸ªà¸­à¸ à¸­à¸¢à¹à¸²à¸à¸à¸£à¸´à¸à¹à¸ à¸ªà¹à¸à¸à¸¥à¸à¸³à¹à¸«à¹à¸ªà¸²à¸¡à¸²à¸£à¸à¹à¸à¸·à¹à¸­à¸à¸¥à¸à¸°à¹à¸à¸à¸à¸­à¸à¸à¸¥à¸à¸à¸ªà¸­à¸à¸à¸µà¹à¹à¸à¹à¹à¸à¹à¸à¸­à¸¢à¹à¸²à¸à¸à¸µ</p>

<p style="color: #990000;"><span style="font-weight:bold">à¸à¸±à¹à¸à¸à¸µà¹ 2: </span> à¸à¸¥à¸à¸£à¸°à¹à¸¡à¸´à¸	à¸à¸±à¸¨à¸à¸à¸à¸´à¸à¸²à¸à¸à¹à¸²à¸à¸à¸²à¸£à¸à¸£à¸´à¸à¸²à¸£ / Service Attitude Test Result</p>
<table class="table1">
                <thead>
                    <tr>
                        <th id="score" scope="col">à¸à¸¥à¸à¸°à¹à¸à¸<br />Score</th>
                        <th id="very" scope="col" abbr="Very Lowest Score">à¸£à¸°à¸à¸±à¸à¸à¸°à¹à¸à¸à¸à¹à¸³à¸¡à¸²à¸à¹à¸¡à¹à¸à¸§à¸£à¸à¸´à¸à¸²à¸£à¸à¸² <br />Very Lowest Score</th>
                        <th id="low" scope="col" abbr="Low Score">à¸£à¸°à¸à¸±à¸à¸à¸°à¹à¸à¸à¸à¹à¸³à¸à¸§à¹à¸²à¸¡à¸²à¸à¸£à¸à¸²à¸à¹à¸¡à¹à¸à¸§à¸£à¸à¸´à¸à¸²à¸£à¸à¸² <br />Low Score</th>
                        <th id="neutral" scope="col" abbr="Moderate Score">à¸£à¸°à¸à¸±à¸à¸à¸°à¹à¸à¸à¸¡à¸²à¸à¸£à¸à¸²à¸<br />Moderate Score</th>
                        <th id="good" scope="col" abbr="Good Score">à¸£à¸°à¸à¸±à¸à¸à¸°à¹à¸à¸à¸ªà¸¹à¸à¸à¸§à¹à¸²à¸¡à¸²à¸à¸£à¸à¸²à¸<br />Good Score</th>
                        <th id="perfect" scope="col" abbr="Perfect Score">à¸£à¸°à¸à¸±à¸à¸à¸°à¹à¸à¸à¸ªà¸¹à¸à¸à¸§à¹à¸²à¸¡à¸²à¸à¸£à¸à¸²à¸<br />Perfect Score</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>à¸à¹à¸²à¸à¸°à¹à¸à¸à¸à¸¥à¸²à¸</td>
                        <td>à¸à¹à¸³à¸à¸§à¹à¸² 37</td>
                        <td>37-60</td>
                        <td>61-80</td>
                        <td>81-95</td>
                        <td>96-120</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                        <td>à¸à¸¥à¸à¸µà¹à¹à¸à¹</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><span style="font-weight:bold; font-size:22px; color:#00C;">69.00</span></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </tbody>
        </table>
<p style="color:#0066CC;"><span style="font-weight:bold">à¸à¸¥à¸à¸²à¸£à¸à¸£à¸°à¹à¸¡à¸´à¸/Result:</span>&nbsp;à¸£à¸°à¸à¸±à¸à¸à¸°à¹à¸à¸à¸¡à¸²à¸à¸£à¸à¸²à¸ à¸«à¸£à¸·à¸­ âModerate Scoreâ à¸ªà¸²à¸¡à¸²à¸£à¸à¸à¸´à¸à¸²à¸£à¸à¸²à¸£à¸±à¸à¹à¸à¹à¸²à¸à¸²à¸à¹à¸à¹à¸à¸·à¸­à¸§à¹à¸²à¹à¸à¹à¸à¸à¸¸à¸à¸à¸¥à¸à¸µà¹à¸ªà¸²à¸¡à¸²à¸£à¸à¸à¸³à¸à¸²à¸à¸à¹à¸²à¸à¸à¸²à¸£à¸à¸£à¸´à¸à¸²à¸£à¸à¸µà¹à¸à¹à¸­à¸à¸à¸²à¸£à¸à¸±à¸¨à¸à¸à¸à¸´ 6 à¸¥à¸±à¸à¸©à¸à¸°à¸à¸µà¹à¸à¸£à¸°à¹à¸¡à¸´à¸à¹à¸à¹à¸à¸µà¹à¸à¸µà¸¢à¸à¸à¸­ à¸à¸±à¸à¸£à¸²à¸¢à¸¥à¸°à¹à¸­à¸µà¸¢à¸</p>

<table class="table2">
                <thead>
                    <tr>
                        <th align="center" width="177" scope="col" abbr="Starter">à¸à¸±à¸¨à¸à¸à¸à¸´à¸à¸²à¸£à¸à¸£à¸´à¸à¸²à¸£ /<br  />
Service Attitudes</th>
                        <th align="center" width="617" scope="col" abbr="Medium">à¸£à¸²à¸¢à¸¥à¸°à¹à¸­à¸µà¸¢à¸</th>
                        <th align="center" width="170" scope="col" abbr="Business">à¸à¸¥à¸à¸°à¹à¸à¸<br  />(à¹à¸à¹à¸¡ 20à¸à¸°à¹à¸à¸)</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Discipline</td>
                        <td>à¸¡à¸µà¸§à¸´à¸à¸±à¸¢à¸à¸±à¸à¸à¸±à¸§à¹à¸­à¸ à¸¥à¸°à¹à¸­à¸µà¸¢à¸à¸£à¸­à¸à¸à¸­à¸ à¹à¸à¹à¸à¸µà¸§à¸´à¸à¸à¸¹à¸à¸à¹à¸­à¸ à¸¡à¸²à¸à¸³à¸à¸²à¸à¸à¸£à¸à¹à¸§à¸¥à¸² à¸à¸³à¸à¸²à¸¡à¸à¸à¸£à¸°à¹à¸à¸µà¸¢à¸à¸à¸²à¸£à¹à¸à¹à¸à¸à¸²à¸¢ à¹à¸¡à¹à¸à¹à¸²à¸à¸·à¸à¸à¹à¸­à¸«à¹à¸²à¸¡ à¸à¸±à¸à¸à¸µà¹à¸à¸¥à¸­à¸à¸ à¸±à¸¢</td>
                        <td align="center">12.00</td>
                    </tr>
                    <tr>
                        <td>Self Accountability</td>
                        <td>à¸à¸à¸´à¸à¸±à¸à¸´à¸«à¸à¹à¸²à¸à¸µà¹à¹à¸¥à¸°à¸£à¸±à¸à¸à¸´à¸à¸à¸­à¸à¸à¸¥à¸¥à¸±à¸à¸à¹à¸à¸­à¸à¸à¸²à¸à¸à¸à¹à¸­à¸ à¹à¸à¸£à¸µà¸¢à¸à¹à¸ªà¸¡à¸·à¸­à¸à¹à¸à¹à¸²à¸à¸­à¸</td>
                        <td align="center">8.00</td>
                    </tr>
                    <tr>
                        <td>Pleasant Personality</td>
                        <td>à¸¡à¸µà¸à¸¸à¸à¸¥à¸´à¸à¸ à¸²à¸à¹à¸à¹à¸à¸¡à¸´à¸à¸£ à¸à¸¹à¸à¸µ à¸à¸§à¸à¸à¸¸à¸¡à¸­à¸²à¸£à¸¡à¸à¹ à¸¡à¸µà¸à¸§à¸²à¸¡à¹à¸­à¸à¸­à¹à¸­à¸à¸à¹à¸­à¸à¸à¸²à¸¡ à¸£à¸±à¸à¸à¸±à¸à¸à¸§à¸²à¸¡à¹à¸«à¹à¸ à¸ªà¸¸à¸ à¸²à¸à¸­à¹à¸­à¸à¸à¹à¸­à¸¡à¸à¹à¸­à¸¡à¸à¸ à¹à¸à¹à¸²à¸à¸±à¸à¸à¸à¸à¹à¸²à¸¢</td>
                        <td align="center">5.00</td>
                    </tr>
                    <tr>
                        <td>Relation Building</td>
                        <td>à¸à¸£à¸°à¸à¸·à¸­à¸£à¸·à¸­à¸£à¹à¸à¸à¸µà¹à¸à¸°à¸ªà¸£à¹à¸²à¸à¸à¸§à¸²à¸¡à¸ªà¸±à¸¡à¸à¸±à¸à¸à¹à¸à¸µà¹à¸à¸µà¸à¸±à¸à¸à¸¹à¹à¸à¸µà¹à¹à¸à¸µà¹à¸¢à¸§à¸à¹à¸­à¸à¸­à¸¢à¹à¸²à¸à¹à¸à¹à¸à¸à¸±à¸à¹à¸­à¸ à¸à¸³à¹à¸«à¹à¹à¸à¸·à¹à¸­à¹à¸</td>
                        <td align="center">19.00</td>
                    </tr>
                    <tr>
                        <td>Willing to help Customer</td>
                        <td>à¸à¹à¸§à¸¢à¹à¸«à¸¥à¸·à¸­à¸¥à¸¹à¸à¸à¹à¸²à¸à¸²à¸¡à¸à¸§à¸²à¸¡à¸à¹à¸­à¸à¸à¸²à¸£ à¸à¸­à¸à¹à¸«à¸à¸·à¸­à¸à¸²à¸à¸à¸²à¸à¸à¸µà¹à¸£à¸±à¸à¸à¸´à¸à¸à¸­à¸ à¸à¹à¸­à¸à¸¥à¸¹à¸à¸à¹à¸²à¸à¸­à¸</td>
                        <td align="center">10.50</td>
                    </tr>
                    <tr>
                        <td>Good Judgment</td>
                        <td>à¸à¸²à¸£à¸à¸±à¸à¸ªà¸´à¸à¹à¸à¸à¸µà¹à¸£à¸­à¸à¸à¸­à¸à¸à¹à¸­à¸ªà¸à¸²à¸à¸à¸²à¸£à¸à¹à¹à¸à¸à¸²à¸°à¸«à¸à¹à¸² à¹à¸¥à¸°à¸ªà¸·à¹à¸­à¸ªà¸²à¸£à¹à¸«à¹à¸¥à¸¹à¸à¸à¹à¸²à¹à¸à¹à¸²à¹à¸</td>
                        <td align="center">14.50</td>
                    </tr>
                    <tfoot>
                    <tr>
                        <td height="80">&nbsp;</td>
                        <td align="right">à¸à¸¥à¸à¸°à¹à¸à¸à¸£à¸§à¸¡</td>
                        <td align="center">69.00/120</td>
                    </tr>
                </tfoot>
                </tbody>
            </table>
            <p align="center" style="color:#666666; font-weight:bold;">Thank You for Using Our Service / MissConsult.com</p>
</div>
<div class="footer">

</div>
</td></tr>
</table>
</body>
</html>
