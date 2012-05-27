<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script>
function testAlert(){
	alert("ok")
}
function openDialog(){
	$( "#dialog-modal" ).dialog({
		/* height: 140, */
		modal: true
	});
}
</script>
<!--Body content-->
<!-- <div class="alert alert-info">
    <button class="close" data-dismiss="alert">×</button>
    <strong>Heads up!</strong> Best check yo self, you're not looking too good.2
    </div> -->
    <div id="dialog-modal" title="Send Email Form" style="display: none">
	<!-- <p>Adding the modal overlay screen makes the dialog look more prominent because it dims out the page content.</p> -->
	<table>
	<tr valign="top"><td width="20%">To</td><td width="80%"><input type="text"></td></tr>
	<tr valign="top"><td width="20%">Message</td><td width="80%"><textarea></textarea></td></tr>
	<tr valign="top"><td width="20%"></td><td width="80%"></td></tr>
	</table>
</div>
  <fieldset style="font-family: sans-serif;"> 
	   <!--  <form   class="well" style="background-color:white;border: 2px solid rgba(0, 0, 0, 0.05)" > -->
	   <form   class="well" style="border:2px solid #DDD" >
	   <!--   <fieldset style="font-family: sans-serif;">  -->  
	      <h3  style="font:Arial,Helvetica,sans-serif"><strong>Miss - Home</strong></h3>  
           <!-- <div style="position:relative;right:-94%;">  </div> --> 
	    					<table border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="left" width="20%">
	    					
	    					<strong>To Do:</strong></td>
	    					<td align="right" width="80%">
	    				<!-- 	<a  class="btn btn-primary" onclick="testAlert()"><i class="icon-search icon-white"></i>&nbsp;Seach</a> -->
	    					<a href="#">Prev</a>&nbsp;|&nbsp;<select name="bpsGroupId" id="bpgGroupId" style="width: 50px"> 
											 <option value="0">1</option>
											 <option value="20">20</option>
											 <option value="300">300</option>
												
	    					</select>&nbsp;|&nbsp;<a href="#">Next</a>
	    					<input type="hidden" value="${totals}" id="totals"/>
	    					<input type="hidden" value="${pageObj.pageNo}" id="pageNo"/>
	    					<input type="hidden" value="${pageObj.pageSize}" id="pageSize"/>
	    					</td>
	    					</tr>
	    					</table> 
<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        <thead>
          <tr>
            <th width="80%"><div class="th_class">Task</div></th>
            <th width="20%"><div class="th_class">Response</div></th> 
          </tr>
        </thead>
        <tbody>
         <c:forEach items="${todolists}" var="todolist" varStatus="loop">  
            <tr>
             <td><div class="th_class"><c:out value="${todolist.mtodoTask}"/></div></td>
            <td><div class="th_class"><a onclick="openDialog()">Send Email</a></div></td> 
          </tr>
          </c:forEach>
        </tbody>
      </table>
      
     
      </form>
 </fieldset>
	  