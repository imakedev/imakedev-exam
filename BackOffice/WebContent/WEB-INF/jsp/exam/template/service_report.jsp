<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
	    <!--Body content-->
<script type="text/javascript">
$(document).ready(function() {
});

</script>
<style>
th{ font-family:Tahoma; font-size:12px; font-weight:bold;
 color: #fff;background:url(<c:url value='/resources/images/${UserMissContact.missTheme.mtTr}'/>) repeat-x scroll 0 0 ${UserMissContact.missTheme.mtTrColor};padding: 5px 8px;border:1px solid #fff; 
}
</style>
<div id="dialog-confirmDelete" title="Delete Candidate" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
	Are you sure you want to delete Candidate ?
</div>
<fieldset style="font-family: sans-serif;">
  <form   class="well" style="border:2px solid #DDD;background: url(<c:url value='/resources/images/${UserMissContact.missTheme.mtWaterWall}'/>) no-repeat scroll right top ${UserMissContact.missTheme.mtBgColor}">
           <strong style="font-family: sans-serif;font-size: 14px">3. Service Report: รายงานสรุปภาพรวมของระบบการเข้าใช้งาน</strong><br/><br/>
a. เปอร์เซนต์ของลูกค้าที่เข้ามาแต่ใช้งานระบบในแต่ละอาทิตย์ เวลาไหน หรือ วันใดที่มีการเข้าใช้งานมากสุด น้อยสุด เลือกเดือน : 
<select style="width: 60px">
<option value="">ม.ค</option>
<option value="">ก.พ</option>
</select> เลือกปี : 
<select style="width: 70px">
<option value="">2011</option>
<option value="">2012</option>
</select><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="70%"><div class="th_class">Week</div></th> 
            		<th width="15%"><div class="th_class">วัน/เวลา เข้าใช้งานมากสุด</div></th>
            		<th width="15%"><div class="th_class">วัน/เวลา เข้าใช้งานน้อยสุด</div></th>
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;Week 1</td>
            	<td>&nbsp;28/08/2012 13:00</td>
            	<td>&nbsp;28/08/2012 13:00</td>
         	</tr>
         	<tr>
         		<td>&nbsp;Week 1</td>
            	<td>&nbsp;28/08/2012 13:00</td>
            	<td>&nbsp;28/08/2012 13:00</td>
         	</tr>
         	<tr>
            	<td>&nbsp;Week 3</td>
            	<td>&nbsp;28/08/2012 13:00</td>
            	<td>&nbsp;28/08/2012 13:00</td>
         	</tr>
         	<tr>
            	<td>&nbsp;Week 4</td>
            	<td>&nbsp;28/08/2012 13:00</td>
            	<td>&nbsp;28/08/2012 13:00</td>
         	</tr>
        	</tbody>
      </table>
<b>b. เปอร์เซนต์ ของแต่ละแบบประเมินที่มีการ Reactive<br/>
- เก็บจำนวนครั้ง</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="70%"><div class="th_class">แบบประเมิน</div></th> 
            		<th width="15%"><div class="th_class">เปอร์เซนต์(Reactive)</div></th>
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;EPT 1</td>
            	<td>&nbsp;10%</td>
         	</tr>
         	<tr>
            	<td>&nbsp;EPT 2</td>
            	<td>&nbsp;90%</td>
         	</tr>
        	</tbody>
      </table>
<b>c. แสดงรายงาน ชื่อลูกค้า ที่เข้ามาทำการ Reactive และ จำนวนการ Reactive</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="70%"><div class="th_class">รายชื่อลูกค้า</div></th> 
            		<th width="15%"><div class="th_class">จำนวนการ Reactive</div></th>
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;Company 1</td>
            	<td>&nbsp;1</td>
         	</tr>
         	<tr>
            	<td>&nbsp;Company 2</td>
            	<td>&nbsp;9</td>
         	</tr>
        	</tbody>
      </table>
<b>d. แสดงรายงาน การเรียกใช้ Browser แต่ละประเภท ของลูกค้า</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="100%"><div class="th_class">Browser</div></th>  
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;Chrome > Firefox </td> 
         	</tr> 
        	</tbody>
      </table>
<b>e. แสดงประเภทแบบประเมินที่ลูกค้าใช้เวลาทำ เกินกำหนดหรือทำไม่ครบแบบประเมินแล้วหมดเวลา</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="100%"><div class="th_class">ประเภทแบบประเมิน</div></th>  
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;Personality > Aptitude </td> 
         	</tr> 
        	</tbody>
      </table>
f. จัดเก็บประวัติ การเกิดปัญหา ในแต่ละเดือน ตลอดปี<br/>
g. จัดเก็บประวัติ จำนวนการทำ Reactive ของแต่ละประเภทแบบทดสอบตลอดปี<br/>
h. จัดเก็บประวัติ ช่วงเวลาการเข้าทำแบบประเมินสูงสุด ตลอดปี<br/>
</form>
      </fieldset> 
  