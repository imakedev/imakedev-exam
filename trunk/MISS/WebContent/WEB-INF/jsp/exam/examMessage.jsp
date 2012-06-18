<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<head>
<title></title>  
<c:url var="url" value="/" />
<c:url value="/logout" var="logoutUrl"/>
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
  //format: 'YOWDHMS', description: ''});
 // format: 'HMS', description: ''});
	  format: 'hms', description: ''});
});
function loadDynamicPage(pageId){
	//	var id="1";
		//$.get('ajax/search', function(data) {
	 /* 	$.get(pageId, function(data) {
			  // alert(data);
			  if(data!=null){
				  appendContent(data);
			  }
			}); */
			
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
function appendContentWithId(data,contentId){
	if(data.indexOf("j_username")!=-1){
		//alert("to home") 
		  window.location.href="<c:url value='/j_spring_security_logout'/>";
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
	appendContentWithId(data,"_content");
	
}

</script>
</head>
<!-- <body style="background-color:rgb(231, 235, 242)"> -->
 <!-- <body style="background-color:rgb(241, 241, 241)"> -->
 <!--   style="background-color: white;" --> 
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
            <span id="menu-username"><%=SecurityContextHolder.getContext().getAuthentication().getName()%></span> &nbsp;&nbsp;<a href="${logoutUrl}">Logout</a>
            </div>
           </div>
           </div>
    </div>
    	<!-- <div class="row-fluid" style="background-color: rgb(0, 136, 204)">  -->
    	<div class="row-fluid"> 
    	 <%--   <div class="span2">
    	   <form class="well" style="background-color:rgb(245, 245, 245);border: 2px solid #DDD">
	    <img src="<c:url value='/resources/images/logowebmc.png'/>" />
	    </form>
	    </div> --%>
	    <div class="span10" id="_content">
	      <fieldset style="font-family: sans-serif;">  
           <!-- <legend  style="font-size: 13px">Criteria</legend> -->
           <!-- <div style="position:relative;right:-94%;">  </div> --> 
          <form class="well" style="border:2px solid #DDD">
              <table border="0" width="100%" style="font-size: 13px">
              				<tr>
	    					 <td align="left" width="100%" colspan="6"><strong>Final Message</strong></td>
	    					</tr>
	    					<tr>
	    					 <td align="center" width="100%">&nbsp;Thank you message /Instruction</td> 
	    					</tr> 
	    					</table> 
	    					</form>
	   
       
</fieldset> 
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
