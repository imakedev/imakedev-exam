<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
	    <fieldset style="font-family: sans-serif;">  
           <!-- <legend  style="font-size: 13px">Criteria</legend> -->
           <!-- <div style="position:relative;right:-94%;">  </div> --> 
          <form class="well" style="border:2px solid #DDD">
              <table border="0" width="100%" style="font-size: 13px">
              				<tr>
	    					 <td align="left" width="100%" colspan="6"><strong>Test Result Search</strong></td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Series<font color="red">*</font>:</td>
	    					 <td align="left" width="17%">    					
	    					 <select id="mcaSeries">
	    					      <option value="-1">-- Select Series --</option>
	    					      
	    					      <%--
	    					      <c:forEach items="${missSeries}" var="missSery" varStatus="loop"> 
	    					 			 <option value="<c:out value="${missSery.msId}"></c:out>"><c:out value="${missSery.msSeriesName}"></c:out></option>
	    					 	 </c:forEach>
	    					 	  --%>
	    					    </select>	
	    					 </td>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Username:</td>
	    					 <td align="left" width="17%">    					
	    					<input type="text">	
	    					 </td>
	    					<td align="left" width="17%">&nbsp;</td>
	    					<td align="left" width="17%">&nbsp;</td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">First Name:</td>
	    					 <td align="left" width="17%"> <input type="text" name="registerNo" />
	    					 </td>
	    					<td align="left" width="17%">Last Name:</td>
	    					<td align="left" width="17%"><input type="text" name="registerNo" /></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Position:</td>
	    					 <td align="left" width="17%">  <input type="text" name="registerNo" />
	    					 </td>
	    					<td align="left" width="17%">Department:</td>
	    					<td align="left" width="17%"><input type="text" name="registerNo"  /></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Test From:</td>
	    					 <td align="left" width="17%">  <input type="text" id="datepicker_from" style="width:75px"/>
	    					 </td>
	    					<td align="left" width="17%">Test To:</td>
	    					<td align="left" width="17%"><input type="text" id="datepicker_to" style="width:75px"  /></td>
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					<tr>
	    					 <td align="left" width="17%">&nbsp;</td>
	    					 <td align="left" width="17%">Company Name:</td>
	    					 <td align="left" colspan="3" width="51%">    					
	    						<input type="text" name="registerNo"   style="width: 100%"/>
	    					 </td> 
	    					<td align="left" width="15%">&nbsp;</td>
	    					</tr>
	    					</table> 
	    					</form>
	    					<table  border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="left" width="60%">
	    					
	    					<a class="btn btn-success"><i class="icon-pencil icon-white"></i>&nbsp;Do Paper Test</a>&nbsp;
	    					<a class="btn btn-info"><i class="icon-circle-arrow-up icon-white"></i>&nbsp;Export</a>&nbsp;
	    					<a class="btn btn-info"><i class="icon-list-alt icon-white"></i>&nbsp;Summary</a>&nbsp;
	    					<a class="btn btn-danger"><i class="icon-eject icon-white"></i>&nbsp;Ignore</a></td>
	    					<td align="right" width="40%">
	    					
	    					<a href="#">Prev</a>&nbsp;|&nbsp;<select name="bpsGroupId" id="bpgGroupId" style="width: 50px"> 
											 <option value="0">1</option>
											 <option value="20">20</option>
											 <option value="300">300</option>
												
	    					</select>&nbsp;|&nbsp;<a href="#">Next</a>&nbsp;<a  onclick="callAjax()" class="btn btn-primary"><i class="icon-search icon-white"></i>&nbsp;Seach</a></td>
	    					</tr>
	    					</table> 
		<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="5%"><div class="th_class"><input type="checkbox"/></div></th>
            		<th width="10%"><div class="th_class">Username</div></th> 
            		<th width="15%"><div class="th_class">First Name</div></th>
            		<th width="10%"><div class="th_class">Last Name</div></th> 
            		<th width="10%"><div class="th_class">Title</div></th>
            		<th width="10%"><div class="th_class">Department</div></th> 
            		<th width="5%"><div class="th_class">Fa</div></th>
            		<th width="5%"><div class="th_class">Im</div></th> 
            		<th width="5%"><div class="th_class">Pe</div></th>
            		<th width="5%"><div class="th_class">Ju</div></th> 
            		<th width="10%"><div class="th_class">Test Date</div></th> 
            		<th width="5%"><div class="th_class">Report</div></th>
            		<th width="5%"><div class="th_class">Response</div></th> 
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td><input type="checkbox" /></td>
            	<td><a onclick="loadDynamicPage('result/viewAnswer/1')">M000000</a></td>
            	<td>Mr A</td>
            	<td>Last</td>
            	<td>A</td>
            	<td>Department A</td>
            	<td>11</td>
            	<td>23</td>            	
            	<td>14</td> 
            	<td>12</td>
            	<td>D/M/Y h:m</td>
            	<td><a onclick="loadDynamicPage('result/report/')">IPAI</a></td>
            	<td><a onclick="loadDynamicPage('result/response/1')">Pending</a></td>
          	</tr>
         	<tr>
            	<td><input type="checkbox" /></td>
            	<td><a onclick="loadDynamicPage('result/viewAnswer/1')">M000000</a></td>
            	<td>Mr A</td>
            	<td>Last</td>
            	<td>A</td>
            	<td>Department A</td>
            	<td>11</td>
            	<td>23</td>            	
            	<td>14</td> 
            	<td>12</td>
            	<td>D/M/Y h:m</td>
            	<td><a onclick="loadDynamicPage('result/report/1')">IPAI</a></td>
            	<td><a onclick="loadDynamicPage('result/response/1')">Pending</a></td>
          	</tr>
          		<tr>
            	<td><input type="checkbox" /></td>
            	<td><a onclick="loadDynamicPage('result/viewAnswer/1')">M000000</a></td>
            	<td>Mr A</td>
            	<td>Last</td>
            	<td>A</td>
            	<td>Department A</td>
            	<td>11</td>
            	<td>23</td>            	
            	<td>14</td> 
            	<td>12</td>
            	<td>D/M/Y h:m</td>
            	<td><a onclick="loadDynamicPage('result/report/1')">IPAI</a></td>
            	<td><a onclick="loadDynamicPage('result/response/1')">Complete</a></td>
          	</tr>
        	</tbody>
      </table>
      
</fieldset>