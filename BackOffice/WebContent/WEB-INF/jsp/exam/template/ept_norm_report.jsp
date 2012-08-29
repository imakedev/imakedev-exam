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
           <strong style="font-family: sans-serif;font-size: 14px">1. EPT Norm Report: อันนี้จะเป็นรายงานที่รวบรวมข้อมูลของผู้ทำข้อสอบในชุด EPT ทั้งหมด โดยรูปแบบของ EPT จะแบ่งออกเป็น 16 ประเภท พี่ต้องการได้ข้อมูลดังนี้คะ</strong><br/>
- เพิ่ม search criteria แยก company<br/>
 <div>เลือก Company : <select>
<option value="">Company A</option>
<option value="">Company B</option>
</select></div>
<b>a. จำนวนผู้ทดสอบ EPT ทั้งหมด<br/>
- count(*)</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="100%"><div class="th_class">จำนวนผู้เข้าสอบ</div></th> 
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;120</td> 
         	</tr>
         	
        	</tbody>
      </table>
     
<b>b. จำนวนอัตราการโกหก คิดเป็นเปอร์เซนต์ของแต่ละประเภทว่าอยู่ที่เท่าไหร่ อายุใดมากสุด เพศใดมากสุด อาชีพใด อุตสาหกรรมใดมากสุด</b><br/>
- แสดงเป็นตาราง<br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="20%"><div class="th_class">ประเภท</div></th> 
            		<th width="20%"><div class="th_class">อายุ</div></th> 
            		<th width="20%"><div class="th_class">เพศ</div></th>
            		<th width="20%"><div class="th_class">อาชีพ</div></th> 
            		<th width="20%"><div class="th_class">อุตสาหกรรม</div></th>  
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;ABCD</td>
            	<td>&nbsp;16(90%)</td>
            	<td>&nbsp;ชาย(90%)</td>
            	<td>&nbsp;พนักงาน(90%)</td>
            	<td>&nbsp;ราชการ(90%)</td>
         	</tr>
         		<tr>
            	<td>&nbsp;EFGH</td>
            	<td>&nbsp;19(90%)</td>
            	<td>&nbsp;หญิง(90%)</td>
            	<td>&nbsp;พนักงาน(90%)</td>
            	<td>&nbsp;ราชการ(90%)</td>
         	</tr>
        	</tbody>
      </table>
<b>c. คิดเป็นเปอร์เซนต์ในแต่ละกลุ่มของ 16 ประเภท ว่า แต่ละกลุ่มแบ่งออกเป็นกี่เปอร์เซนต์</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="6%"><div class="th_class">ABCD</div></th> 
            		<th width="6%"><div class="th_class">EFGH</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">MNOP</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>  
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>  
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">IJKL</div></th>    
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
         	</tr>
         	 
        	</tbody>
      </table>
d. แต่ละกลุ่มของ 16 ประเภท มีสายอาชีพอะไรได้เปอร์เซนต์มากสุด ออกมาเป็น 3 ลำดับ<br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr> 
            		<th width="12%"><div class="th_class">ABCD</div></th> 
            		<th width="12%"><div class="th_class">EFGH</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th>
            		<th width="12%"><div class="th_class">MNOP</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th>  
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th> 
          		</tr>
        	</thead>
        	<tbody>
          	<tr> 
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td> 
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td> 
             
         	</tr>
         	 <tr> 
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td> 
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td> 
            	 
         	</tr>
         	<tr> 
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3</td> 
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3</td> 
            	 
         	</tr>
        	</tbody>
      </table>
      <table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>  
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th>  
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th>
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th>
            		<th width="12%"><div class="th_class">IJKL</div></th>    
          		</tr>
        	</thead>
        	<tbody>
          	<tr> 
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td> 
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td>
            	<td>&nbsp;อาชีพ 1</td> 
             
         	</tr>
         	 <tr> 
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td> 
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td>
            	<td>&nbsp;อาชีพ 2</td> 
            	 
         	</tr>
         	<tr> 
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3</td> 
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3</td>
            	<td>&nbsp;อาชีพ 3&nbsp;อาชีพ 3&nbsp;อาชีพ 3&nbsp;อาชีพ 3</td> 
            	 
         	</tr>
        	</tbody>
      </table>
e. แต่ละกลุ่มของ 16 ประเภท เพศหญิง เพศชายได้เปอร์เซนต์เท่าไหร่<br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
          		   <th width="6%"><div class="th_class">เพศ</div></th>
            		<th width="6%"><div class="th_class">ABCD</div></th> 
            		<th width="6%"><div class="th_class">EFGH</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">MNOP</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>  
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>  
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">IJKL</div></th>    
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
          	    <td>&nbsp;ชาย</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
         	</tr>
         	 <tr>
          	    <td>&nbsp;หญิง</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
         	</tr>
        	</tbody>
      </table>
f. แต่ละกลุ่มของ 16 ประเภท มี อุตสาหกรรม อะไรได้เปอร์เซนต์มากสุด ออกมาเป็น 3 ลำดับ<br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr> 
            		<th width="12%"><div class="th_class">ABCD</div></th> 
            		<th width="12%"><div class="th_class">EFGH</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th>
            		<th width="12%"><div class="th_class">MNOP</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th>  
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th> 
          		</tr>
        	</thead>
        	<tbody>
          	<tr> 
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td> 
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td> 
             
         	</tr>
         	 <tr> 
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td> 
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td> 
            	 
         	</tr>
         	<tr> 
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3</td> 
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3</td> 
            	 
         	</tr>
        	</tbody>
      </table>
      <table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>  
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th>  
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th>
            		<th width="12%"><div class="th_class">IJKL</div></th> 
            		<th width="12%"><div class="th_class">IJKL</div></th>
            		<th width="12%"><div class="th_class">IJKL</div></th>    
          		</tr>
        	</thead>
        	<tbody>
          	<tr> 
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td> 
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td>
            	<td>&nbsp;อุตสาหกรรม 1</td> 
             
         	</tr>
         	 <tr> 
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td> 
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td>
            	<td>&nbsp;อุตสาหกรรม 2</td> 
            	 
         	</tr>
         	<tr> 
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3</td> 
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3</td>
            	<td>&nbsp;อุตสาหกรรม 3&nbsp;อุตสาหกรรม 3&nbsp;อุตสาหกรรม 3&nbsp;อุตสาหกรรม 3</td> 
            	 
         	</tr>
        	</tbody>
      </table>
<b>g. แต่ละกลุ่มของ 16 ประเภท แบ่งอายุออกมา ว่าอายุกลุ่มใดได้เปอร์เซนต์เท่าไหร่</b><br/>
<table class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
          		   <th width="6%"><div class="th_class">ช่วงอาขุ</div></th>
            		<th width="6%"><div class="th_class">ABCD</div></th> 
            		<th width="6%"><div class="th_class">EFGH</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">MNOP</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>  
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>  
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">IJKL</div></th> 
            		<th width="6%"><div class="th_class">IJKL</div></th>
            		<th width="6%"><div class="th_class">IJKL</div></th>    
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
          	    <td>&nbsp;15-35</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
         	</tr>
         	 <tr>
          	    <td>&nbsp;36-60</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td>
            	<td>&nbsp;2%</td> 
         	</tr>
        	</tbody>
      </table>
</form>
      </fieldset> 
  