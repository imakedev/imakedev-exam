<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<c:url value='/css/mc9t/style.css'/>" type="text/css" media="screen"/>
<title>MissConsult : MCC_CH_01 TEST REPORT</title>
<style>
body {
	padding: 0 0 0 0;
	/* margin:0 0 0 0; */
	margin: 2 2 2 2;
	font-family:Tahoma, Geneva, sans-serif;
	font-size:14px;
}

.footer{
	width: 900px; 
	height:100px;
}

td.hisleft{
	font-weight:bold;
	color:#104E8B;
}
td.hisright{
	font-weight:bold;
}
td.result{color:#333333; font-size:13px; font-weight:bold;}
td.result2{color:#333333; font-size:15px;}
</style>
</head>

<body>
<table width="900" border="0" cellspacing="5">
<tr>
  <td>
<table width="900" border="0" cellspacing="5">
  <tr>
    <td width="440"><img src="<c:url value='/images/mc9t/fourft_banner.png'/>" /></td>
    <td width="441" align="right"><img src="<c:url value='/images/mc9t/mc9t_page1_01.png'/>" /></td>
  </tr>
</table>
<table width="500" border="0" cellspacing="5">
  <tr>
    <td height="30" class="hisleft" colspan="2"><span style="text-decoration:underline;">ข้อมูลผู้ทดสอบ</span></td>
    </tr>
  <tr>
				<td width="150" class="hisleft">คุณ :</td>
				<td class="hisright">Dummy Dummy</td>
			</tr>
			<tr>
				<td class="hisleft">ตำแหน่ง :</td>
				<td class="hisright">Account</td>
			</tr>
			<tr>
				<td class="hisleft">บริษัท :</td>
				<td class="hisright">Pornpun</td>
			</tr>
			<tr>
				<td class="hisleft">อายุ :</td>
				<td class="hisright">43</td>
			</tr>
			<tr>
				<td class="hisleft">เบอร์โทรศัพท์ :</td>
				<td class="hisright">025999550</td>
			</tr>
			<tr>
				<td class="hisleft">วัน :</td>
				<td class="hisright">22/04/2552</td>
			</tr>
			<tr>
				<td class="hisleft">เวลา :</td>
				<td class="hisright">13:37:07</td>
			</tr>
</table><br />
<table style="width: 890px; border-spacing: 0px; border-color: #DBDBDB;" border="0">
			<tr>
				<td height="30" style="color:#336699"><strong>CH_O1 : Honest Test</strong></td>
			</tr>
			<tr>
  				<td height="10px"></td>
  			</tr>
			<tr>
				<td class="result">Testing for potentially dishonest employees has become a booming business today. Known as ‘Honest
Testing” It’s a specialty that’s currently in extremely high demand. Workers today are actually more likely
to engage in thievery involving supplies, equipments, and paid time than in the past is a question for
debate among HR professionals. Therefore, when it come to honest testing, only candidates who present
themselves as scrupulously “squeaky clean” are likely to be hired.</td>
			</tr>
			<tr>
  				<td height="10px"></td>
  			</tr>
			<tr>
  				<td class="result2"><strong>แบบทดสอบชุด MCCT Group </strong>ซึ่งถูกออกแบบมาวัดอย่างเฉพาะเจาะจงในลักษณะนิสัยสำคัญ และ ถูกออกแบบมาเพื่อการจับโกหกของผู้ทดสอบ ที่ต้องการหลอกผลการทดสอบ</td>
  			</tr>
			<tr>
  				<td height="10px"></td>
  			</tr>
			<tr>
  				<td class="result2">เหมาะสำหรับการวิเคราะห์นิสัยสำคัญ ที่ส่งผลต่อการทำงานในแต่ละองค์กร โดยองค์กรสามารถเลือกวัดเฉพาะในนิสัยที่องค์กรเล็งเห็นว่า<br />
			    มีความสำคัญเป็นอย่างยิ่งต่อการคัดเลือก หรือ ส่งผลเป็นหลักต่อตำแหน่งงานหรือลักษณะงานที่ต้องรับผิดชอบ</td>
  			</tr>
		</table><br />
      <table style="width: 890px; border-spacing: 0px; border-color: #DBDBDB;" border="0">
			<tr>
				<td style="color:#336699"><strong>แบบทดสอบ CH_01 วัดนิสัย ความซื่อสัตย์ โดยผลการวัด สรุปได้ดังนี้</strong></td>
			</tr>
			<tr>
  				<td height="10px"></td>
  			</tr>
			<tr>
				<td class="result2">Lie Score คือค่าที่วัดการโกหกของผู้สอบ อาจจะโกหกให้ตัวเองดูดี</td>
			</tr>
			<tr>
				<td class="result2">Lie Score คือค่าผลลัพท์ที่บอกถึงความซื่อสัตย์ของผู้สอบ</td>
			</tr>
			<tr>
  				<td height="10px"></td>
  			</tr>
			<tr>
				<td>
					<table style="width: 890px; border-spacing: 0px; border-color: #DBDBDB;" border="0">
						<tr>
							<td align="center"><img src="<c:url value='/images/mc9t/mc9t_page1_graph_circle.png'/>" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
      <table style="width: 890px; border-spacing: 0px; border-color: #DBDBDB;" border="0">
			<tr>
				<td align="center" style="color:#CC0000; font-weight:bold;"><u>รูปแบบการประเมิน</u></td>
			</tr>
			<tr>
  				<td height="10px"></td>
  			</tr>
			<tr>
				<td style="color:#CC0000; font-weight:bold;"><u>step 1</u> : ประเมิน ผล การโกหก ระหว่างการทำแบบทดสอบ (Lie Scale)</td>
			</tr>
			<tr>
				<td height="70" style="color:#333333;font:bold">Comment: Please consider the person who gets the score in ‘Good’ score only.<br />
ควรเลือก ผลทดสอบที่อยู่ในระดับ “Good”</td>
			</tr>
			<tr>
  				<td>
                <table class="table1">
                <thead>
                    <tr>
                        <th width="132" id="score" style="background-color: #BEBEBE;" scope="col" abbr="Starter">Score / Name</th>
                        <th width="151" id="very" scope="col" abbr="Medium">Very High Score<br />
“Dangerous”</th>
                        <th width="165" id="nearly" scope="col" abbr="Business">Nearly High Score<br />
“Be careful”</th>
                        <th width="156" id="neutral" scope="col" abbr="Deluxe">Some LIER Score<br />
“Neutral”</th>
                        <th width="240" id="good" scope="col" abbr="Deluxe">Perfect Score<br />
“Good”</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>STD</td>
                        <td>90</td>
                        <td>72-89</td>
                        <td>54-71</td>
                        <td>ต่ำกว่า - 54</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                        <td>Result</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td><span style="font-weight:bold; font-size:22px;">38</span></td>
                    </tr>
                </tbody>
        </table>
              </td>
  			</tr></table>
</td>
</tr>
<tr>
  	<td height="50" valign="bottom" style="color:#FF6600; font-weight:bold;">• Good: A person who gets a mark below 54 score is the person who is perfectly doing the test.
She/he doing the test without lying.</td>
  </tr>
  <tr>
  		<td height="10px"></td>
  </tr>
  <tr>
  	<td style="color:#FF6600; font-weight:bold;">เป็นกลุ่มคะแนน ที่ต่ำกว่า 54 คะแนน หมายถึง ผู้ทดสอบตั้งใจในการทำข้อสอบอย่างจริงใจ หรือ แทบไม่โกหก
เลย ส่งผลทำให้ สามารถเชื่อผลคะแนนของ ผลทดสอบที่ได้เป็นอย่างดี</td>
  </tr>
</table>
<div class="footer"></div>
</body>
</html>