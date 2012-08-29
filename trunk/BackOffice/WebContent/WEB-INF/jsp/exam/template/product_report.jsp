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
          <strong style="font-family: sans-serif;font-size: 14px">4. Product Report: รายงานสรุปภาพรวมของแบบประเมิน ที่ถูกใช้และซื้อ เข้าใช้งาน</strong><br/><br/>
<b>a. แสดงรายการใช้ของแต่ละ แบบประเมินจากมากไปน้อย</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="100%"><div class="th_class">แบบประเมิน</div></th> 
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;EPT1 > &nbsp;EPT2</td>
         	</tr>
         	
        	</tbody>
      </table>
<b>b. แสดงรายการ แบบประเมินที่ถูกซื้อมากสุดไปน้อยสุด</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="100%"><div class="th_class">แบบประเมิน</div></th> 
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;EPT1 > &nbsp;EPT2</td>
         	</tr>
        	</tbody>
      </table>
<b>c. แสดงรายการ แบบประเมินที่มีปัญหาการเข้าใช้งานมากสุด</b><br/>
	<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="100%"><div class="th_class">แบบประเมิน</div></th> 
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;EPT1 > &nbsp;EPT2</td>
         	</tr>
        	</tbody>
      </table>
<b>d. แสดงรายการ  แบบประเมินที่ถูกเข้าใช้งานมากสุดแต่ละเดือน ของปี 
<select style="width: 70px" >
<option value="">2011</option>
<option value="">2012</option>
</select>
</b><br/>
	<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="100%" colspan="12"><div class="th_class">แบบประเมิน</div></th> 
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;ม.ค.</td>
            	<td>&nbsp;ก.พ.</td>
            	<td>&nbsp;มี.ค.</td>
            	<td>&nbsp;เม.ย.</td>
            	<td>&nbsp;พ.ค.</td>
            	<td>&nbsp;มิ.ย.</td>
            	<td>&nbsp;ก.ค.</td>
            	<td>&nbsp;ส.ค.</td>
            	<td>&nbsp;ก.ย.</td>
            	<td>&nbsp;ต.ค.</td>
            	<td>&nbsp;พ.ย.</td>
            	<td>&nbsp;ธ.ค.</td>
         	</tr>
         	<tr>
            	<td>&nbsp;EPT1</td>
            	<td>&nbsp;EPT2</td>
            	<td>&nbsp;EPT1</td>
            	<td>&nbsp;EPT2</td>
            	<td>&nbsp;EPT1</td>
            	<td>&nbsp;EPT2</td>
            	<td>&nbsp;EPT1</td>
            	<td>&nbsp;EPT2</td>
            	<td>&nbsp;EPT1</td>
            	<td>&nbsp;EPT2</td>
            	<td>&nbsp;EPT1</td>
            	<td>&nbsp;EPT2</td>
         	</tr>
        	</tbody>
      </table>
</form>
      </fieldset> 
  