<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<head>
<title></title>  
<c:url var="url" value="/" /> 
<c:url value="/logout" var="logoutUrl"/>
<c:url value="/exam" var="examUrl"/>


<link rel="icon" href="<c:url value='/resources/images/favicon.ico'/>" type="image/x-icon" />
<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico'/>" type="image/x-icon" />  
<script  src="<c:url value='/resources/js/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>" type="text/javascript"></script>

<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui-1.8.21.custom.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/resources/js/jquery.countdown.pack.js'/>"></script>

<link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"  type="text/css">
<link href="<c:url value='/resources/css/custom-theme/jquery-ui-1.8.21.custom.css'/>" type="text/css"  rel="stylesheet" /> 

<link href="<c:url value='/resources/css/3column.css'/>"  type="text/css" rel="stylesheet" />
<link href="<c:url value='/resources/css/menubar.css'/>"  type="text/css" rel="stylesheet" /> 

<style>
.ui-widget { font-family: Trebuchet MS, Tahoma, Verdana,
 Arial, sans-serif; font-size: 12px; }
 </style>
 <style type="text/css">
 /*  fieldset { width: 100%; }
  fieldset legend { width: 100%; }
  fieldset legend div { margin: 0.3em 0.5em; }
  fieldset .field { margin: 0.5em; padding: 0.5em; }
  fieldset .field label { margin-right: 0.4em; } */
</style>
<style type="text/css">
/*.th_class{font-family: Tahoma;font-size: 13px;text-align: center;*/
.th_class{text-align: center;
}
a{cursor: pointer;}
</style>
<style type="text/css">
/*label,select,.ui-select-menu { float: left; margin-right: 10px; }*/
/*select { width: 200px; }*/
/*label,select,.ui-select-menu { } */
/* select { width: 55px;}*/
/*.ui-widget{font-family: Tahoma;font-size: 12px; }*/
th{ font-family:Tahoma; font-size:12px; font-weight:bold;
 color: #fff;background:#328AA4 url(<c:url value='/resources/images/tr_back.gif'/>) repeat-x;;padding: 5px 8px;border:1px solid #fff; 
}
input[type="text"],[type="password"]{height:30px}
img.ui-datepicker-trigger{cursor: pointer;}
 .span8 {
   padding: 2px;
}
.span2 {
   padding: 2px;
}
/* padding:10px 5px 15px 20px;
top padding is 10px
right padding is 5px
bottom padding is 15px
left padding is 20px */
</style>

<script type="text/javascript">
var _path="";
$(document).ready(function() {
	//alert("aa");
	//$('#tabs').tabs();
  //   $("fieldset.collapsibleClosed").collapse( { closed : true } );
  
  _path="${url}";
  //alert(_path)
  //alert(_path.split(";jsessionid=").length)
  if(_path.split(";jsessionid=").length>0){
	  _path=_path.split(";jsessionid=")[0];
	  //alert(_path.split(";jsessionid=").length);
  }
  var thisDay='${systemDate}'.split("/");
  var startYear=new Date(thisDay[2], parseInt(thisDay[1])-1, thisDay[0]);
  $('#defaultCountdown').countdown({since: startYear, compact: true, 
	  format: 'hms', description: ''});
  var timelimit=(parseInt('${timelimit}'))+"";
	 // var endTime =new Date(parseInt(timelimit[2]), parseInt(timelimit[1]),  parseInt(timelimit[0]),timelimit[3],parseInt(timelimit[4])+4,parseInt(timelimit[5]),0);
	 			// new Date(year        , month                 ,  day                   ,hours       ,minutes                  ,seconds,              milliseconds)
		  $('#examCountdown').countdown({until:+timelimit, compact: true,
		  format: 'dHMS', description: '',onExpiry: watchCountdown});
 if(parseInt('${timelimit}')<=0){
	 $( "#dialog-timeOut" ).dialog({
			/* height: 140, */
			modal: true,
			buttons: {
				"Ok": function() { 
					$( this ).dialog( "close" );
					 
				}
			},
			close: function(event, ui) {  window.location.href="<c:url value='/logout'/>"; }
		});
 }else{
		   var page="exam/template?examIndex="+${missExamForm.examIndex}+"&questionIndex="+${missExamForm.questionIndex};
		  // alert(page)
		  loadDynamicPage(page);
 }  
}); ; 
 
function watchCountdown(periods) { 
	$( "#dialog-timeOut" ).dialog({
		/* height: 140, */
		modal: true,
		buttons: {
			"Ok": function() { 
				$( this ).dialog( "close" );
				 
			}
		},
		close: function(event, ui) {  window.location.href="<c:url value='/logout'/>"; }
	});
}
function loadDynamicPage(pageId){
//	alert(pageId)
			$.ajax({
				  type: "get",
				  url: pageId,
				  cache: false
				 // data: { name: "John", location: "Boston" }
				}).done(function( data ) {
					if(data!=null){
						  appendContent(data);
					  }
				});
}
function goToQuestion(question_number){
	// var page="exam/template?examIndex="+${missExamForm.examIndex}+"&questionIndex="+question_number;
	  // alert(page)
	 // alert($("#missExamForm"))
	  //loadDynamicPage(page);
	  $("#mode").val("goToPage");
	  $("#oldQuestionIndex").val($("#questionIndex").val());
	  $("#questionIndex").val(question_number);
	  // alert()
	 // alert(question_number);
	  postDynamicPage("exam/template","missExamForm");
}
function postDynamicPage(pageId,formID){
	$.post(pageId,$("#"+formID).serialize(), function(data) {
		appendContentWithId(data,"_exam_content");
	});
}
function markQuestion(answereds){
	//var answereds=$("#answered").val();
	if(answereds.length>0){
		var answered_array=answereds.split(",");
		for(var i=0;i<answered_array.length;i++){
			document.getElementById("question_number_checkbox_"+answered_array[i]).checked=true;
		}
	}
}
function appendContentWithId(data,contentId){
//	alert(data)
//	alert(data.indexOf("j_username")!=-1);
	if(data.indexOf("j_username")!=-1){
		//alert("to home") 
		  window.location.href="<c:url value='/logout'/>";
		 //$("#_content").html(data);
	  }else{
		/*   var _url_template=window.location.href;
		  if(_url_template.indexOf("template")!=-1){ 
			  window.location.href="<c:url value='/'/>";
		  }else{ */
			  $("#"+contentId).html(data);
		 // }
	  }
	
}
function appendContent(data){
	//alert(data)
	appendContentWithId(data,"_exam_content");
	
}

</script>
</head>
<!-- <body style="background-color:rgb(231, 235, 242)"> -->
 <!-- <body style="background-color:rgb(241, 241, 241)"> -->
 <!--   style="background-color: white;" --> 
 <div id="dialog-timeOut" title="TimeOut" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
	TimeOut
</div>
 <body style="background-color:rgb(253, 253, 253);background-image:url(<c:url value='/resources/images/body.gif'/>); ">
 <div class="container-fluid">
    <div class="row-fluid">
    	<div class="span12" align="center"> 
    	<div id="header2" align="left"  style="height: 66px">
    	<!--  width="200px" height="33px" -->
    	<div align="left"><h1><img src="<c:url value='/resources/images/logowebmc.png'/>" width="200px" height="33px" />
    	
    	 </h1> </div> 
    	 <div align="center" style="position: absolute;top:0px; left:0px;right:0px; padding-top:10px;"><h1><img src="<c:url value='/resources/images/logowebmc.png'/>" />
    	</h1>
    	  </div> 
    	<div align="right" style="position: absolute; z-index:-5; width:300px; right:0;top:0; padding-top:10px; padding-right:10px;">
       <strong>System Time:</strong>&nbsp;&nbsp;${systemDate}&nbsp;&nbsp;<span id="defaultCountdown">hh:mm</span><br/><br/>
        TH | EN        
        </div>
         <div align="right" style="position: absolute;right:0;top:75; padding-right:10px;">
        <sec:authentication var="myUser" property="principal.myUser"/> 
       <%--  ${myUser.fullName} --%>
            <span id="menu-username"><%=SecurityContextHolder.getContext().getAuthentication().getName()%></span> &nbsp;&nbsp;<a href="${logoutUrl}">Logout</a>
            </div>
           </div>
           </div>
    </div>
    	<!-- <div class="row-fluid" style="background-color: rgb(0, 136, 204)">  -->
    	<div class="row-fluid"> 
    	<div class="span2">
    	   <form class="well" style="background-color:rgb(245, 245, 245);border: 2px solid #DDD"> 
    	<%--    <input type="hidden" id="examIndex" value="${missExamForm.examIndex}"/>
    	   <input type="hidden" id="questionIndex" value="${missExamForm.questionIndex}"/> --%>
    	   <%--
    	    <c:forEach items="${missExamForm.missCandidate.missSery.missExams[0].missQuestions}" var="missQuestions" varStatus="loop"> 
    	    	 <c:if test="${loop.index%4==0}">
    	    		<tr>
    	    	</c:if>
    	    	<c:if test="${loop.index%4==3}">
    	    		</tr>
    	    	</c:if>  
    	    	
    	      	${loop.index+1} ,${len(missExamForm.missCandidate.missSery.missExams[0].missQuestions)} 
    	    </c:forEach>
    	    <c:set var="endLoop" value="${offset + maxPageItems}"/>  
<c:if test="${fn:length(searchResults) < endLoop}">  
  <c:set var="endLoop" value="${fn:length(searchResults)"/>  
</c:if> 
    	    --%>
    	   <div align="right">Time Left <span id="examCountdown"></span></div><br/>
    	   <strong>${missExamForm.missCandidate.missSery.missExams[missExamForm.examIndex].meName}</strong>
    	   <div>
    	   <table width="100%" border="0" style="font-size: 12px"> 
    	    <c:forEach items="${missExamForm.missCandidate.missSery.missExams[missExamForm.examIndex].missQuestions}" var="missQuestions" varStatus="loop"> 
    	    	 <c:if test="${loop.index%4==0}">
    	    		<tr>
    	    	</c:if>
    	    	<td><input id="question_number_checkbox_${missQuestions.mqId}" type="checkbox" disabled="disabled"><span style="cursor: pointer;" onclick="goToQuestion('${loop.index}')">${loop.index+1}</span></td>
    	    	<c:if test="${loop.index%4==3}">
    	    		</tr>
    	    	</c:if>  
    	    	
    	      <%-- 	${loop.index+1} ,${len(missExamForm.missCandidate.missSery.missExams[${missExamForm.examIndex}].missQuestions)}  --%>
    	    </c:forEach>
    	   
    	  <!--   <tr>
				<td><input type="checkbox"><span style="cursor: pointer;">1</span></td>    	      
				<td><input type="checkbox"><span style="cursor: pointer;">2</span></td>
				<td><input type="checkbox"><span style="cursor: pointer;">3</span></td>
				<td><input type="checkbox"><span style="cursor: pointer;">4</span></td>
    	    </tr>
    	    <tr>
				<td><input type="checkbox"><span style="cursor: pointer;">5</span></td>    	      
				<td><input type="checkbox"><span style="cursor: pointer;">6</span></td>
				<td></td>
				<td></td>
    	    </tr> -->
    	   <!--   <tr>
				<td><input type="checkbox" disabled="disabled" checked="checked"><span style="cursor: pointer;">9</span></td>    	      
				<td><input type="checkbox"><span style="cursor: pointer;">10</span></td>
				<td><input type="checkbox"><span style="cursor: pointer;">11</span></td>
				<td><input type="checkbox"><span style="cursor: pointer;">12</span></td>
    	    </tr>
    	     <tr>
				<td><input type="checkbox"><span style="cursor: pointer;">133</span></td>    	      
				<td><input type="checkbox"><span style="cursor: pointer;">144</span></td>
				<td><input type="checkbox"><span style="cursor: pointer;">155</span></td>
				<td><input type="checkbox"><span style="cursor: pointer;">166</span></td>
    	    </tr> -->
    	<!--     <tr>
				<td><input type="checkbox">133</td>    	      
				<td><input type="checkbox">144</td>
				<td><input type="checkbox">155</td>
				<td><input type="checkbox">166</td>
    	    </tr> -->
    	   </table>
	      </div>
	    </form>
	    </div> 
	    <div class="span8" id="_exam_content">
	     
	    </div>
  <div class="span2">
	    <!--Sidebar content--> 
            <div class="post_section">
				<h3>
				<img src="<c:url value='/resources/images/h2bg.gif'/>"  border="0" align="absmiddle"/>&nbsp;Description / Instruction / Remark </h3>
				<p>
					Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan.
				</p>
                 
              </div>
	    
	 </div>
    </div>
</div>
</body>
</html>
