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
          <strong style="font-family: sans-serif;font-size: 14px">5. Consultant Report: รายงานสรุปภาพรวมของฝ่ายขาย</strong><br/><br/>
          <div>ชื่อฝ่ายขาย : <select>
<option value="">นาย A</option>
<option value="">นาย B</option>
</select></div>
<b>a. แสดงรายการ ลูกค้า (แสดงการเข้าระบบครั้งแรก และ ล่าสุด ) ของแต่ละคนของ ฝ่ายขาย</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="70%"><div class="th_class">รายชื่อลูกค้า</div></th> 
            		<th width="15%"><div class="th_class">เข้าระบบครั้งแรก</div></th>
            		<th width="15%"><div class="th_class">เข้าระบบครั้งล่าสุด</div></th>
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;Company 1</td>
            	<td>&nbsp;28/08/2012 12:00</td>
            	<td>&nbsp;28/09/2012 12:00</td>
         	</tr>
        	</tbody>
      </table>
<b>b. แสดงรายการ สถานการณ์การเข้าใช้งานของลูกค้าและจำนวนการ Reactive ของลูกค้าแต่ละคนของฝ่ายขาย</b><br/>
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
            	<td>&nbsp;10</td>
         	</tr>
        	</tbody>
      </table>
<b>c. แสดงรายการ การสั่งซื้อแบบประเมินเพิ่ม หรือ ใหม่ของแต่ละคนของฝ่ายขาย</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="70%"><div class="th_class">รายชื่อลูกค้า</div></th> 
            		<th width="15%"><div class="th_class">จำนวนการสั่งซื้อ</div></th>
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;Company 1</td>
            	<td>&nbsp;10</td>
         	</tr>
        	</tbody>
      </table>
<b>d. แสดงรายการ  การขายได้ของแบบประเมินแต่ละแบบของฝ่ายขายแต่ละคน (คิดเป็นเปอร์เซนต์) ในแต่ละเดือนและเก็บประวัติในแต่ละเดือนด้วย เลือกเดือน : 
<select style="width: 60px">
<option value="">ม.ค</option>
<option value="">ก.พ</option>
</select> เลือกปี : 
<select style="width: 70px">
<option value="">2011</option>
<option value="">2012</option>
</select></b> <br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="70%"><div class="th_class">แบบประเมิน</div></th> 
            		<th width="15%"><div class="th_class">เปอร์เซนต์</div></th>
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
<b>e. แสดงรายการ การ update ผู้ติดต่อหลักของแต่ละลูกค้าฝ่ายขายที่มีการขาย</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="70%"><div class="th_class">รายชื่อลูกค้า</div></th> 
            		<th width="15%"><div class="th_class">Update ล่าสุด</div></th>
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;นาย A [ Company 1 ]</td>
            	<td>&nbsp;28/09/2012 12:00</td>
         	</tr>
        	</tbody>
      </table>
<b>f. แสดงรายการ เตือนแต่ละเดือน วันเกิดลูกค้าที่รับผิดชอบ </b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="70%"><div class="th_class">รายชื่อลูกค้า</div></th> 
            		<th width="15%"><div class="th_class">วันเกิด</div></th>
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;นาย A</td>
            	<td>&nbsp;28</td>
         	</tr>
        	</tbody>
      </table>
</form>
      </fieldset> 
  