<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>  
<c:url var="url" value="/" />
<link rel="icon" href="<c:url value='/resources/images/favicon.ico'/>" type="image/x-icon" />
<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico'/>" type="image/x-icon" />  
<%-- 
<script type="text/javascript" 
        src="<c:url value='/dwr/interface/MissExamAjax.js'/>"></script> 
<script type="text/javascript"
        src="<c:url value='/dwr/engine.js'/>"></script> 
<script type="text/javascript"
        src="<c:url value='/dwr/util.js'/>"></script>	
       --%>
<script  src="<c:url value='/resources/js/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>" type="text/javascript"></script>

<%-- --%>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui-1.8.20.custom.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.selectmenu.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.jstree.js'/>"></script>


<script type="text/javascript" src="<c:url value='/resources/js/jquery.countdown.pack.js'/>"></script>
<%-- 
<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui-1.8.20.custom.min_bbff81bc5a8d9d5e8cda11.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.selectmenu_305c7ca38a3344d0476fe44c3f837.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/resources/js/jquery.jstree_51c0096a278d6381f2055339948db37e.js'/>"></script>
 --%>
 <script type="text/javascript" src="<c:url value='/resources/ckeditorV2/ckeditor.js'/>"></script>
 
<link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"  type="text/css">
<link href="<c:url value='/resources/css/custom-theme/jquery-ui-1.8.20.custom.css'/>" type="text/css"  rel="stylesheet" /> 
<link  href="<c:url value='/resources/css/jquery.ui.selectmenu.css'/>" rel="stylesheet" type="text/css">

<link href="<c:url value='/resources/css/3column.css'/>"  type="text/css" rel="stylesheet" />
<link href="<c:url value='/resources/css/menubar.css'/>"  type="text/css" rel="stylesheet" /> 

<!-- Bootstrap styles for responsive website layout, supporting different screen sizes -->
<!-- 
<link rel="stylesheet" href="http://blueimp.github.com/cdn/css/bootstrap-responsive.min.css">
 -->
<!-- Bootstrap CSS fixes for IE6 -->
<!--[if lt IE 7]><link rel="stylesheet" href="http://blueimp.github.com/cdn/css/bootstrap-ie6.min.css"><![endif]-->
<!-- Bootstrap Image Gallery styles -->
<!-- 
<link rel="stylesheet" href="http://blueimp.github.com/Bootstrap-Image-Gallery/css/bootstrap-image-gallery.min.css">
 -->
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="<c:url value='/resources/css/jquery.fileupload-ui.css'/>">
<!-- Shim to make HTML5 elements usable in older Internet Explorer versions -->
<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<%--  --%>


 

<%--   
 <script type="text/javascript" src="<c:url value='/resources/ckeditorV2/ckeditor_f45f9a39b926bd88f32e6915a8fb856d.js'/>"></script> 
  --%>
<%--
/MISSExamBackOffice/WebContent/resources/js/engine_37fe29c942e07f41809c0417f329b2bd.js
/MISSExamBackOffice/WebContent/resources/js/MissExamAjax_283eca6d8a59568a6bc4d0c10e194dcb.js
/MISSExamBackOffice/WebContent/resources/js/util_8a535c2111d2cac94b2695c8241f66d1.js
 --%>
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
  
  //alert("new path="+_path)
  $.jstree._themes = "/MISSExamBackOffice/resources/js/themes/";
 //$('select#bpgGroupId').selectmenu({style:'dropdown'});
	$("#demo1").jstree({ 
		"json_data" : {
			"data" : [
				{ 
					"data" : {title:"Home",icon : "<c:url value='/resources/js/_demo/file.png'/>" },
					"metadata" : { id : "child_23" ,"link":"template/todolist"},
					"attr" : { "id" : "home_node" }, 
				},
				{ 
					"attr" : { "id" : "account_node" }, 
					"data" : { 
						"title" : "บัญชีผู้ใช้", 
						"attr" : { "href" : "#" } 
					},
					"metadata" : { id : "root_aoee2"},
					"children" : [
						{ attributes: { id : "pjson_2" }, data: { title : "MissConsult", icon : "<c:url value='/resources/js/_demo/file.png'/>" },"attr" : { "id" : "24"},"metadata" : { id : "child_24","link":"miss/account"} },
						{ attributes: { id : "pjson_3" }, data: {title:"Company",attributes:{ "href" : "www.google.com" } , icon : "<c:url value='/resources/js/_demo/file.png'/>"},"metadata" : { id : "child_25","link":"company/search" } },
						{ attributes: { id : "pjson_4" }, data: { title:"Candidate", icon : "<c:url value='/resources/js/_demo/file.png'/>"} ,"metadata" : { id : "child_26","link":"candidate/search" }}
					          ] 
					
				},
				/* { 
					"attr" : { "id" : "report_node" }, 
					"data" : { 
						"title" : "Result Report", 
						"attr" : { "href" : "www.google.com" } 
					},
					"children" : [
						{ attributes: { id : "pjson_5" }, data: { title : "EPT",  icon : "<c:url value='/resources/js/_demo/file.png'/>"} },
						{ attributes: { id : "pjson_6" }, data: {title:"EPT Plus",attributes:{ "href" : "www.google.com" },  icon : "<c:url value='/resources/js/_demo/file.png'/>"},"metadata" : { id : 25 } },
						{ attributes: { id : "pjson_7" }, data: {title: "..." , icon : "<c:url value='/resources/js/_demo/file.png'/>"} },
						{ attributes: { id : "pjson_8" }, data: {title: "Search Report" , icon : "<c:url value='/resources/js/_demo/file.png'/>"},"metadata" : { id : "child_288","link":"result/search" },"attr" : { "id" : "tree_288" }    }
						] 
					
				} */
				{ 
					"data" : {title:"Search Report",icon : "<c:url value='/resources/js/_demo/file.png'/>" },
					"metadata" : { id : "child_288" ,"link":"result/search"},
					"attr" : { "id" : "tree_288" }, 
				},
				{ 
					"attr" : { "id" : "management_node" }, 
					"data" : { 
						"title" : "จัดการแบบสอบถาม", 
						"attr" : { "href" : "www.google.com" } 
					},
					"children" : [
						{ attributes: { id : "pjson_5" }, data: { title : "Series",  icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_25","link":"series/search" } },
						{ attributes: { id : "pjson_6" }, data: {title:"Test",attributes:{ "href" : "www.google.com" }, icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_26" ,"link":"test/search"} }
					          ] 
					
				},
				{ 
					"attr" : { "id" : "etc_node" }, 
					"data" : { 
						"title" : "etc.", 
						"attr" : { "href" : "www.google.com" } 
					},
					"children" : [
						{ attributes: { id : "pjson_5" }, data: { title : "Download",  icon : "<c:url value='/resources/js/_demo/file.png'/>" } }
						
					          ] 					
				}
			]
		},
		"plugins" : [ "themes", "json_data", "ui" ],
		"core" : {
			// this makes the node with ID node_4 selected onload
				"initially_open" : [ "home_node","account_node","report_node","management_node","etc_node" ]
			}
	}).bind("select_node.jstree", function (e, data) {
		//alert(data.rslt.obj.data("id"));
		var id=data.rslt.obj.data("id");
		var link=data.rslt.obj.data("link");
		//alert(link)
		if(link !=undefined && link!="undefined" && link.indexOf("undefined")==-1){
			//alert("load link="+link);
		
			loadDynamicPage(link);
		}	
 	/* 	alert(data.rslt.obj.data("id"));
		alert("e="+e);
		alert("data="+data);
		alert("href="+data.rslt.obj.data("href")); */
		});
	//var button = $('#button1'), interval;
	//alert(button)
	/*
	new AjaxUpload('button2', {
        action: 'upload',
		data : {
			'key1' : "This data won't",
			'key2' : "be send because",
			'key3' : "we will overwrite it"
		},
		onSubmit : function(file , ext){
            // Allow only images. You should add security check on the server-side.
			if (ext && /^(jpg|png|jpeg|gif)$/.test(ext)){
				alert("xxx");
				this.setData({
					'key': 'This string will be send with the file',
					'test':'chatchai'
				});					
				$('#example2 .text').text('Uploading ' + file);	
			} else {					
				// extension is not allowed
				$('#example2 .text').text('Error: only images are allowed');
				// cancel upload
				return false;				
			}		
		},
		onComplete : function(file, response){
			$('#example2 .text').text('Uploaded ' + file);		
			alert(file);
			//alert(response)
			response=response.replace("<pre>","");
			response=response.replace("</pre>","");
			  var obj = jQuery.parseJSON(response);
			alert(obj)
		}		
	});
	*/
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
            <a href="j_spring_security_logout">Logout</a>
            </div>
           </div>
          
          <!--  <div   align="right" >
            <a href="j_spring_security_logout">Logout</a>
            </div> -->
           </div>
    </div>
    	<!-- <div class="row-fluid" style="background-color: rgb(0, 136, 204)">  -->
    	<div class="row-fluid"> 
    	   <div class="span2">
	    <!--Sidebar content--> 
		<!--  <pre style="font-size: 13px">  -->
		 <!-- <div><br/></div> --> 
		 <!-- <form class="well" style="background-color:white;border: 2px solid #DDD"> -->
		<!-- <form class="well" style="background-color:white;border: 2px solid rgba(0, 0, 0, 0.05)"> -->
		
		<!-- <form class="well" style="background-color:rgb(245, 245, 245);border: 2px solid rgba(0, 0, 0, 0.05)"> -->
		<form class="well" style="background-color:rgb(245, 245, 245);border: 2px solid #DDD">
	   <!--  <span id="demo1" style="font-family: sans-serif;font-size:13px: top;position:relative;top:-35px;right:5px;"></span> -->
	   <h3><strong>Navigation</strong></h3>
	    <span id="demo1" style="font-family: sans-serif;font-size:13px:"></span>
	    </form>
	   <!--  </pre> -->
	    </div>
	    <div class="span8" id="_content">
	    <!--Body content-->
	<!--  <div class="alert alert-info">
    <button class="close" data-dismiss="alert">×</button>
    <strong>Heads up!</strong> Best check yo self, you're not looking too good.2
    </div> -->
 
    <!-- <h3><strong>MC - Home</strong></h3> -->
      <fieldset style="font-family: sans-serif;">   
    <!--  <form   class="well" style="background-color:white;border: 2px solid #DDD" > -->
     <form   class="well" style="border: 2px solid #DDD" method="post" enctype="multipart/form-data">
       <!-- <form   class="well" style="background-color:white;border: 2px solid rgba(0, 0, 0, 0.05)" > -->
     <!--  <form   class="well" style=";border: 2px solid rgba(0, 0, 0, 0.05)" > -->
	   <!--   <fieldset style="font-family: sans-serif;">    -->
	     <h3><strong>MC - Home</strong>
	      
	      <!--  <form id="fileupload" action="server/php/" method="POST" enctype="multipart/form-data">
	        <span class="btn btn-success fileinput-button">
                    <i class="icon-plus icon-white"></i>
                    <span>Add files...</span>
                    <input type="file" name="files[]" multiple>
                </span>
	       </form> --> 
	      <!--  <li id="example2" class="example">
		<p>You can make a list of allowed file types</p>
		<a href="#" id="button2">Upload Image</a>
		<p class="text"></p>		
	  
	</li> -->
	     </h3>  
           <!-- <div style="position:relative;right:-94%;">  </div> --> 
	    					<table border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="left" width="20%">
	    					
	    					<strong>To Do(Common):</strong></td>
	    					<td align="right" width="80%">
	    					
	    					<a href="#">Prev</a>&nbsp;|&nbsp;<select name="bpsGroupId" id="bpgGroupId"  style="width: 55px;"> 
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
            <td><div class="th_class"><a >Send Email</a></div></td> 
          </tr>
          </c:forEach>
        </tbody>
      </table>
      
    
      </form>
        </fieldset> 
<!-- <br/>
	   <form id="form" name="myform"  class="well" method="post" action="#">
          <fieldset>            
            <legend><h2><span>MC - Home</span></h2></legend>           
           <div style=" margin-bottom:3px; border-bottom:1px dashed #666;">
           		<div style="float:left; width:50%;"><strong>To Do..</strong></div>
                <div style="float:right;width:50%;" align="right">
                Previous | <select name="mydropdown">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                </select> | Next</div>
                <div class="cleaner"></div>
           </div>
            <div>
            <table cellspacing="0" cellpadding="0" class="simply">			
                <tr>				
                    <th width="70%">Task</th>
                    <th width="30%">Response</th>                    
                </tr>
                <tr>
                    <td>Data</td>
                    <td>Send mail</td>                    
                </tr>
                <tr>
                    <td>Data</td>
                    <td>Send mail</td>                    
                </tr>
               				
            </table>
            </div>
            
         </fieldset> </form> -->
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
 
   <script src="<c:url value='/resources/js/ajaxupload.js'/>"></script>


</body>
</html>
