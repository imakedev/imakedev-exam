<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7, IE=9"/>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<title>MissConsult</title>
<link rel="icon" href="<c:url value='/resources/images/favicon.ico'/>" type="image/x-icon" />
<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico'/>" type="image/x-icon" />
<script  src="<c:url value='/resources/js/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/resources/js/smoothness/jquery-ui-1.8.21.custom.min.js'/>"></script>
<link href="<c:url value='/resources/css/smoothness/jquery-ui-1.8.21.custom.css'/>" type="text/css"  rel="stylesheet" /> 
<link  href="<c:url value='/resources/css/style-Login.css'/>"  type="text/css" rel="stylesheet"/>

<script type="text/javascript">
//var url_home=window.location.href;
//alert(url_home);
function goHome(){
	window.location.href="<c:url value='/todolist'/>";
}
jQuery(window).bind("unload", function() {
	location.reload();
});  
$(document).ready(function() { 
	
	$.get("/MISSExamBackOffice/checksession",function(data) {
		//var obj = data;//jQuery.parseJSON(data);
		//  alert(data);
		//  alert(window.location.href.indexOf("login\/"))
	//	wrapper var n=str.indexOf("welcome");
		//if(data!='anonymousUser' && window.location.href.indexOf("login\/")==-1){
		if(data!='anonymousUser'){
		//	alert("redirect")
			window.location.href="<c:url value='/'/>";
		}
		else{
			$("#login_body").slideDown("slow"); 
		}
		});
	//location.reload();
	//alert(url_home+","+xxxxxx);
	if($("#_message").val().length>0){ 
		//$("#_message_show").html($("#_message").val())
		$( "#dialog-Message" ).dialog({
			/* height: 140, */ 
			 height: 25,
			modal: true,
			buttons: {
				"Ok": function() { 
					$( this ).dialog( "close" );
					 
				}
			}
		   //,
			//close: function(event, ui) {  window.location.href="<c:url value='/logout'/>"; }
		});
	}
});
/* window.onbeforeunload = function(){
    return "are you sure?";
}; */
</script>
</head> 
<body id="login_body" style="display: none">
<c:url value="/j_spring_security_check" var="security_check"/>
 <div id="dialog-Message" title="Message" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
	<span id="_message_show">${message}</span>
</div>
<div class="bg2">&nbsp;</div>
<div id="wrapper">
<div align="left" style="padding-top:25px;"><a href="#" title="MissConsult"><img src="<c:url value='/resources/images/logowebmc-b.png'/>"  border="0"/></a></div>
        <div class="content_box">
        <div align="right" class="language" style="padding-top:50px; padding-right:10px;"><a title="Thai" style="cursor: pointer;" href="?language=th_TH">TH</a> | <a title="English" style="cursor: pointer;" href="?language=en">EN</a></div>
          <div style="border-bottom:2px dashed #BAC5C8; margin-bottom:8px;">
          			<div style="float:left; width:50%;color:#737C82;font-size:16px;">Welcome to   
                    <span style=" font-weight:bold; color:#737C82;font-size:16px;">MissConsult</span>
                    </div>
                    <div style="float:right; width:50%; text-align:right;">
                    <span style="color:#FF2FA2;font-weight:bold; font-size:24px;">Corporate</span>
                     <span style="color:#666B79;font-weight:bold; font-size:20px;">Login</span></div>
                    <div style="clear:both;"></div>
          </div>                   
        <div  style="padding-bottom:10px;">                       
           <div id="stylized" class="loginform">
            <form id="form" name="form" method="post" action='${security_check}'>          
            <div style="border-bottom:2px dashed #BAC5C8; margin-bottom:8px;">
            &nbsp;
            </div>                    
               <div style="border-bottom:2px dashed #BAC5C8; margin-bottom:8px;">
                <input type="hidden" id="_message" value="${message}"/>
               <label>Username</label>
            	<input type="text" name="j_username" id="name" />   
                 <div style="clear:both;"></div>
                </div>                  
             <div style="border-bottom:2px dashed #BAC5C8; margin-bottom:8px;">         
            <label>Password</label>&nbsp;      
            <input type="password" name="j_password" id="password" />
            <div style="clear:both;"></div>
            </div> 
            <div  align="center">
            <button type="submit" style="border:2px dashed #BAC5C8; background:#CCD2D0; font-weight:bold; padding:5px; cursor:pointer;" title="Login">Login</button>
            </div>            
            <div class="spacer"></div>
            
            </form>
          </div>
                        
          </div>
                  <div class="cleaner"></div>
  </div>
	<div class="content_box_bottom"></div>
    <div class="footer">©2012 MISSCONSULT ALL RIGHT RESERVED</div>
</div>  
     
</body>

</html>