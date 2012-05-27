<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
//	$('#tabs').tabs();
  //   $("fieldset.collapsibleClosed").collapse( { closed : true } );
//	$('#tabs').tabs('select', parseInt($("#_test_section").val()));
	/* $('#tabs').bind('tabsselect', function(event, ui) {

	    // Objects available in the function context:
	  
	   //alert("index="+ui.index+",panel="+ui.panel+",tab="+ui.tab)
	   if(ui.index==2){
		   alert($("#_meId").val())
		   $("#tabs-3").html("");
	   }

	}); */
});
function goBackQuestions(){
	  $.ajax({
		  type: "get",
		  url: "test/exam/${testForm.missExam.meId}/questions",
		  cache: false
		 // data: { name: "John", location: "Boston" }
		}).done(function( data ) {
			if(data!=null){
				appendContentWithId(data,"tabs-3")
				// $("#tabs-3").html(data);
			  }
		});
}
function doQuestionAction(action,mode,id){
	$("#mqNameTh1").val(CKEDITOR.instances["mqNameTh1"].getData());
	 
	$("#modeQuestion").val(mode);
	if(mode!='search'){
		$("#mqId").val(id);
	}else{
		$("#mqId").val("0");
	}
	//alert(action)
	$.post("test/action/exam/question",$("#testForm_questionList").serialize(), function(data) {
		  // alert(data);
		   appendContentWithId(data,"tabs-3")
		  // alert($("#_content").html());
		});
  }
</script>
 <div class="alert alert-success" style="${display}">
    <button class="close" data-dismiss="alert"><span style="font-size: 12px">x</span></button>
    <strong>${message}</strong> 
    </div>
 <fieldset style="font-family: sans-serif;"> 
<form:form  id="testForm_questionList" name="testForm_questionList" modelAttribute="testForm" cssClass="well"  method="post" action="">
			<form:hidden path="modeQuestion"/>
            <form:hidden path="missQuestion.mqId"/>
			<strong>Question&nbsp;
							 <c:if test="${testForm.modeQuestion=='new'}">
	    					 New
	    					 </c:if>
	    					 <c:if test="${testForm.modeQuestion=='edit'}">
	    					 Edit
	    					 </c:if>
			</strong>
    		 <table border="0" width="100%" style="font-size: 12px">
			    	<tr>
    					<td width="25%" align="right">Test&nbsp;:&nbsp;</td>
    					<td width="75%">${testForm.missExam.meName}</td>
    				</tr>
    				
    				<tr>
    					<td width="25%" align="right">Template&nbsp;:&nbsp;</td>
    					<td width="75%">
    					
    					<form:select path="missQuestion.missTemplate.mtId">
    						<form:option value="1" label="Template1"></form:option>
    					</form:select>
    					</td>
    				</tr>
    				<tr>
    					<td width="25%" align="right">Language&nbsp;:&nbsp;</td>
    					<td width="75%">
    					  		<select name="question_lang" id="question_lang"> 
											 <option value="1">Thai</option>
											<!-- <option value="2">Eng</option>	 -->
	    					</select></td>
    				</tr>
    				<tr>
    					<td width="25%" align="right">Question&nbsp;:&nbsp;</td>
    					<td width="75%"><img src="" /></td>
    				</tr>
    				<tr>
    					<td width="25%" align="left" colspan="2">
    					<form:textarea path="missQuestion.mqNameTh1" cols="4" rows="4" id="mqNameTh1"/>  
    					<!-- <textarea cols="4" rows="4" id="mqName"></textarea> -->
    					<script>
    					if (CKEDITOR.instances['mqNameTh1']) {
    			            CKEDITOR.remove(CKEDITOR.instances['mqNameTh1']);
    			         }
    					CKEDITOR.replace( 'mqNameTh1',
    						    {
    						        toolbar : 'Basic',
    						      //  uiColor : '#9AB8F3'
    						    });
    					</script>
    					</td> 
    				</tr>
    				<tr>
    					<td   align="left" colspan="2">Multiple Choose &nbsp;:&nbsp;
    					<form:input path="missQuestion.mqChoose" cssStyle="width:30px"></form:input>
    					</td> 
    				</tr> 
    		</table>
</form:form>
			<div> Choices:</div>
			<table class="table table-striped table-bordered table-condensed" border="0" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="5%"><div class="th_class">Order</div></th>
            		<th width="95%"><div class="th_class">Text</div></th>             		 
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>1</td>
            	<td><input type="text" value="AA"/></td>
            	 
          	</tr>
          	<tr>
            	<td>2</td>
            	<td><input type="text" value="BB"/></td> 
            	 
          	</tr>
          	<tr>
            	<td>3</td>
            	<td><input type="text" value="CC"/></td>
            	 
          	</tr>
          	<tr>
            	<td>4</td>
            	<td><input type="text" value="DD"/></td>
            	 
          	</tr>
        	</tbody>
      </table>
			
			<div align="center">
			<a class="btn btn-info"  onclick="goBackQuestions()"><i class="icon-chevron-left icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Back</span></a>	
    					 <a class="btn btn-primary"  onclick="doQuestionAction('action','${testForm.modeQuestion}','${testForm.missQuestion.mqId}')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a>
			</div>
			 <!-- <table border="0" width="100%" style="font-size: 12px">
			    	<tr>
    					<td width="50%" align="right">
    					<input type="button" class="btn" value="Delete"/>
    					<a class="btn btn-info"  onclick="doAction('search','doBack','0')"><i class="icon-chevron-left icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Back</span></a>	
    					</td>
    					<td width="50%" align="left">
    					<input type="button" class="btn" value="Save"/>
    					 <a class="btn btn-primary"  onclick="doAction('action','mode','msId')"><i class="icon-ok icon-white"></i>&nbsp;<span style="color: white;font-weight: bold;">Save</span></a>
    					</td>
    				</tr>
				</table> -->
</fieldset>