<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.web.servletapi.*" %>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGE_MISSCONSULT')" var="isManageMC"/>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGE_COMPANY')" var="isManageCompany"/>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGE_CANDIDATE')" var="isManageCandidate"/>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGE_SEARCH_REPORT')" var="isManageSearchReport"/>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGE_SERIES')" var="isManageSeries"/>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGE_TEST')" var="isManageTest"/>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGE_DOWNLOAD')" var="isManageDownload"/>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGE_MISSCONSULT_REPORT_MANAGEMENT')" var="isManageReportManagement"/> 
<sec:authentication var="myUser" property="principal.myUser"/> 
<html>
<head>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="X-UA-Compatible" content="IE=7, IE=9"/>
<title></title>  
<c:url var="url" value="/" />
<c:url value="/logout" var="logoutUrl"/>
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


<%-- --%>
<%-- <sec:authorize access="hasAnyRole('ROLE_MANAGE_MISSCONSULT')"> --%>
<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>" type="text/javascript"></script>
<%-- <script type="text/javascript" src="<c:url value='/resources/js/jquery-ui-1.8.20.custom.min.js'/>"></script> 
</sec:authorize> --%>
<%-- <script src="<c:url value='/resources/bootstrap/Blue/js/bootstrap.min.js'/>" type="text/javascript"></script> --%>
<%-- <sec:authorize access="hasAnyRole('ROLE_MANAGE_COMPANY')"> --%>
<%-- 
<c:if test="${myUser.missContact.maCustomizeColor=='smoothness'}">
	<script type="text/javascript" src="<c:url value='/resources/js/smoothness/jquery-ui-1.8.21.custom.min.js'/>"></script> 
</c:if>
<c:if test="${myUser.missContact.maCustomizeColor=='redmond'}">
	<script type="text/javascript" src="<c:url value='/resources/js/redmond/jquery-ui-1.8.21.custom.min.js'/>"></script> 
</c:if>
<c:if test='${myUser.missContact.maCustomizeColor=="le-frog"}'>
	<script type="text/javascript" src="<c:url value='/resources/js/le-frog/jquery-ui-1.8.21.custom.min.js'/>"></script> 
</c:if>
<c:if test="${myUser.missContact.maCustomizeColor=='ui-lightness'}">
	<script type="text/javascript" src="<c:url value='/resources/js/ui-lightness/jquery-ui-1.8.21.custom.min.js'/>"></script> 
</c:if> 
<c:if test="${myUser.missContact.maCustomizeColor!='smoothness' && myUser.missContact.maCustomizeColor!='redmond'
 && myUser.missContact.maCustomizeColor!='le-frog' && myUser.missContact.maCustomizeColor!='ui-lightness'}">
	<script type="text/javascript" src="<c:url value='/resources/js/ui-lightness/jquery-ui-1.8.21.custom.min.js'/>"></script> 
	<link href="<c:url value='/resources/css/smoothness/jquery-ui-1.8.21.custom.css'/>" type="text/css"  rel="stylesheet" /> 
</c:if> 
--%>
<script type="text/javascript" src="<c:url value='/resources/js/smoothness/jquery-ui-1.8.21.custom.min.js'/>"></script> 
<%-- </sec:authorize> --%>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.selectmenu.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.jstree.js'/>"></script>


<script type="text/javascript" src="<c:url value='/resources/js/jquery.countdown.js'/>"></script>
<%-- 
<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui-1.8.20.custom.min_bbff81bc5a8d9d5e8cda11.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.selectmenu_305c7ca38a3344d0476fe44c3f837.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/resources/js/jquery.jstree_51c0096a278d6381f2055339948db37e.js'/>"></script>
 --%>
 <script type="text/javascript" src="<c:url value='/resources/ckeditorV2/ckeditor.js'/>"></script>
 <script type="text/javascript" src="<c:url value='/resources/js/bday-picker.min.js'/>"></script> 
 
<link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"  type="text/css">
<%-- <sec:authorize access="hasAnyRole('ROLE_MANAGE_MISSCONSULT')">

<link href="<c:url value='/resources/css/smoothness/jquery-ui-1.8.20.custom.css'/>" type="text/css"  rel="stylesheet" /> 
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_MANAGE_COMPANY')">
<link href="<c:url value='/resources/bootstrap/Blue/css/bootstrap.min.css'/>" rel="stylesheet"  type="text/css">
<style>
.well{
  background-color: #d8e7f3
  }
.prettyprint{
  background-color: #d8e7f3
  }
</style> --%>
<link href="<c:url value='/resources/css/smoothness/jquery-ui-1.8.21.custom.css'/>" type="text/css"  rel="stylesheet" />
<%--
<c:if test="${myUser.missContact.maCustomizeColor=='smoothness'}">
	<link href="<c:url value='/resources/css/smoothness/jquery-ui-1.8.21.custom.css'/>" type="text/css"  rel="stylesheet" /> 
</c:if>
<c:if test="${myUser.missContact.maCustomizeColor=='redmond'}">
	<link href="<c:url value='/resources/css/redmond/jquery-ui-1.8.21.custom.css'/>" type="text/css"  rel="stylesheet" /> 
	<style>
.well{
  background-color: #d0e5f5;
  }
.prettyprint{
  background-color: #d0e5f5;
  }
</style>
</c:if>
<c:if test='${myUser.missContact.maCustomizeColor=="le-frog"}'>
	<link href="<c:url value='/resources/css/le-frog/jquery-ui-1.8.21.custom.css'/>" type="text/css"  rel="stylesheet" /> 
	<style>
.well{
  background-color: #A5CE28;
  }
.prettyprint{
  background-color: #A5CE28;
  }
</style>
</c:if>
 
<!--  #ffe45c -->
<c:if test="${myUser.missContact.maCustomizeColor=='ui-lightness'}">
	<link href="<c:url value='/resources/css/ui-lightness/jquery-ui-1.8.21.custom.css'/>" type="text/css"  rel="stylesheet" /> 
	<style>
.well{
  background-color: #f2d563;
  }
.prettyprint{
  background-color: #f2d563;
  }
</style>
</c:if> 
--%>
<%-- </sec:authorize> --%>
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
/* #328AA4 */
th{ font-family:Tahoma; font-size:12px; font-weight:bold;
 color: #fff;background:url(<c:url value='/resources/images/${UserMissContact.missTheme.mtTr}'/>) repeat-x scroll 0 0 ${UserMissContact.missTheme.mtTrColor};padding: 5px 8px;border:1px solid #fff; 
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

.stable-striped{
   background-color: #F9F9F9;
}
table[id=table_list] tr:nth-child(even) {background: #FFFFFF}
</style>
<c:set var="aoeTest">
  <spring:message code='navigation_home'/>
</c:set>

<script type="text/javascript">

var _path="";
var mail_toG;
var mail_subjectG;
var mail_messageG;
var mail_attachG; 
var intRegex = /^\d+$/;
//var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+)|(-\d+(\.\d *)?)|((-\d*\.)?\d+))$/;
 
$(document).ready(function() {
	 
	//alert("aa");
	//$('#tabs').tabs();
  //   $("fieldset.collapsibleClosed").collapse( { closed : true } );
 //  alert('');
  //alert('${seriesMaps_menu}')
	mail_toG= $( "#mail_to" );
	mail_subjectG= $( "#mail_subject" );
	mail_messageG= $( "#mail_message" );
	mail_attachG= $( "#mail_attach" );
  _path="${url}";
  //alert(_path)
  //alert(_path.split(";jsessionid=").length)
  if(_path.split(";jsessionid=").length>0){
	  _path=_path.split(";jsessionid=")[0];
	  //alert(_path.split(";jsessionid=").length);
  }
  var thisDay='${systemDate}'.split("/");
  var startYear=new Date(thisDay[2], parseInt(thisDay[1])-1, thisDay[0]);
 //var startYear=new Date();
 //var startYear=new Date(2012, 8, 6, 22, 43, 0, 0);
 //var d3 = new Date(79,5,24,11,33,0) //new Date(year, month, day, hours, minutes, seconds, milliseconds)
 // alert('${systemDate}');
  //alert(startYear)
  $('#defaultCountdown').countdown({since: startYear, compact: true, 
//	  $('#defaultCountdown').countdown({until: startYear, compact: true,
  format: 'DHMS', description: ''});
 // format: 'DHMS', description: ''});
	//  format: 'hms', description: ''});
	//  format: 'HMS', description: ''});
  
  //alert("new path="+_path)
 // $.jstree._themes = "/BackOffice/resources/js/themes/";
   $.jstree._themes = "/MISSExamBackOffice/resources/js/themes/";
 //$('select#bpgGroupId').selectmenu({style:'dropdown'});
	$("#demo1").jstree({ 
		"json_data" : {
			"data" : [
				{ 
					"data" : {title:"<spring:message code='navigation_home'/>",icon : "<c:url value='/resources/js/_demo/file.png'/>" },
					"metadata" : { id : "child_23" ,"link":"template/todolist"},
					"attr" : { "id" : "home_node" }, 
				}
				, 
				<c:if test="${isManageMC || isManageCompany || isManageCandidate}">	
				{ 
					"attr" : { "id" : "account_node" }, 
					"data" : { 
						"title" : "<spring:message code='navigation_account'/>", 
						"attr" : { "href" : "#" } 
					},
					"metadata" : { id : "root_aoee2"},
					"children" : [
					   <c:if test="${isManageMC}">
						{ attributes: { id : "pjson_2" }, data: { title : "<spring:message code='navigation_account_mc'/>", icon : "<c:url value='/resources/js/_demo/file.png'/>" },"attr" : { "id" : "24"},"metadata" : { id : "child_24","link":"miss/account"} },
					   </c:if>
					   <c:if test="${isManageCompany && UserMissContact.isMC=='1'}">
						{ attributes: { id : "pjson_3" }, data: {title:"<spring:message code='navigation_account_company'/>",attributes:{ "href" : "www.google.com" } , icon : "<c:url value='/resources/js/_demo/file.png'/>"},"metadata" : { id : "child_25","link":"company/search" } },
					   </c:if>
					    <c:if test="${isManageCompany && UserMissContact.isMC=='0'}">
						 { attributes: { id : "pjson_3" }, data: {title:"<spring:message code='navigation_account_company'/>",attributes:{ "href" : "www.google.com" } , icon : "<c:url value='/resources/js/_demo/file.png'/>"},"metadata" : { id : "child_25","link":"company/item/${UserMissContact.mcontactRef}" } },
						</c:if>
						<c:if test="${isManageCandidate}">
						{ attributes: { id : "pjson_4" }, data: { title:"<spring:message code='navigation_account_candidate'/>", icon : "<c:url value='/resources/js/_demo/file.png'/>"} ,"metadata" : { id : "child_26","link":"candidate/search" }}
						</c:if>   
						] 
					
				},
				</c:if>
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
				<c:if test="${isManageSearchReport}">				
				{ 
					
					"data" : {title:"<spring:message code='navigation_search'/>",icon : "<c:url value='/resources/js/_demo/file.png'/>" }, 
					<%-- "data" : {title:"<spring:message code='navigation_search'/>" },  --%>
					"metadata" : { id : "child_288" ,"link":"result/search"},
					"attr" : { "id" : "tree_288" } 
					<%-- 
				  "children" : [
					 <c:forEach items="${seriesMaps_menu}" var="seriesMaps" varStatus="loop"> 
						 { attributes: { id : "seriesMaps${seriesMaps.missSery.msId}" }, data: { title : "${seriesMaps.missSery.msSeriesName}",  icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "seriesMaps${seriesMaps.missSery.msId}","link":"result/search" } }
						
						  ${not loop.last ? ',' : ''}
					 </c:forEach>
					 ] 
					--%>
					
				},
				</c:if>
				<c:if test="${isManageMC || isManageSeries || isManageTest}">	
				{ 
					"attr" : { "id" : "management_node" }, 
					"data" : { 
						"title" : "<spring:message code='navigation_test_management'/>", 
						"attr" : { "href" : "www.google.com" } 
					},
					"children" : [
					<c:if test="${isManageSeries}">	            
						{ attributes: { id : "pjson_5" }, data: { title : "Series",  icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_25","link":"series/search" } }
						,
					</c:if>
					<c:if test="${isManageTest}">	        
						{ attributes: { id : "pjson_6" }, data: {title:"Test",attributes:{ "href" : "www.google.com" }, icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_26" ,"link":"test/search"} }
					 </c:if>    
						] 
					
				}
				,	
				</c:if>
				<c:if test="${isManageMC || isManageDownload}">	
				{ 
					"attr" : { "id" : "etc_node" }, 
					"data" : { 
						"title" : "<spring:message code='navigation_etc'/>", 
						"attr" : { "href" : "www.google.com" } 
					},
					"children" : [
							<c:if test="${isManageDownload}">	
								{ 							
									attributes: { id : "pjson_5" }, data: { title : "<spring:message code='navigation_download'/>",  icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_28" ,"link":"manual/search"} 
								},
							</c:if>
							<c:if test="${isManageDownload}">	
								{ 							
									attributes: { id : "pjson_55" }, data: { title : "<spring:message code='navigation_doc'/>",  icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_28" ,"link":"manual/doc"} 
								}
							</c:if>
					          ] 					
				}
				,	
				</c:if>
				<c:if test="${isManageMC || isManageReportManagement}">	
				{ 
					"attr" : { "id" : "manage_report_node" }, 
					"data" : { 
						"title" : "<spring:message code='navigation_manage_report'/>", 
						"attr" : { "href" : "www.google.com" } 
					},
					"children" : [
							<c:if test="${isManageReportManagement}">	
								{ 							
									attributes: { id : "pjson_7" }, data: { title : "EPT Norm Report",  icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_28" ,"link":"reportmanagement/eptNormReport"} 
								},
								{ 							
									attributes: { id : "pjson_8" }, data: { title : "Customer Report",  icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_28" ,"link":"reportmanagement/customerReport"} 
								},
								{ 							
									attributes: { id : "pjson_9" }, data: { title : "Service Report",  icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_28" ,"link":"reportmanagement/serviceReport"} 
								},
								{ 							
									attributes: { id : "pjson_10" }, data: { title : "Product Report",  icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_28" ,"link":"reportmanagement/productReport"} 
								},
								{ 							
									attributes: { id : "pjson_11" }, data: { title : "Consultant Report",  icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_28" ,"link":"reportmanagement/consultantReport"} 
								}
								
							</c:if>
					          ] 					
				}
				</c:if>
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
	renderTodoPageSelect();
}); 
function renderTodoPageSelect(){
	var pageStr="<select name=\"todoPageSelect\" id=\"todoPageSelect\" onchange=\"goToTodoPage()\" style=\"width: 50px\">";
//	var pageCount=parseInt($("#pageCount").val());
	var pageCount=$("#pageCount").val();
	for(var i=1;i<=pageCount;i++){
		pageStr=pageStr+"<option value=\""+i+"\">"+i+"</option>";
	}
	pageStr=pageStr+"</select>"; 
	$("#pageTodoElement").html(pageStr);
	document.getElementById("todoPageSelect").value=$("#pageNo").val();
}
function goTodoPrev(){
	if($("#pageNo").val()!='1'){
		var prev=parseInt($("#pageNo").val())-1;
		$("#pageNo").val(prev);
		doIgnoreAction('search','0');
	}
}
function goTodoNext(){
	var next=parseInt($("#pageNo").val());
	if(next<parseInt($("#pageCount").val())){
		next=next+1;
		$("#pageNo").val(next);
		doIgnoreAction('search','0');
	}
} 
function doIgnoreAction(mode,id){ 
	$.post("doTodoAction/"+$("#pageNo").val(),$("#formTodoList").serialize(), function(data) {
		  // alert(data);
		    appendContent(data);
		  // alert($("#_content").html());
		});
}
function goToTodoPage(){ 
	$("#pageNo").val(document.getElementById("todoPageSelect").value);
	doIgnoreAction('search','0');
}
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
	if(data.indexOf("j_username")!=-1 || data.indexOf("loginform")!=-1){
		//alert("to home") 
		  /* window.location.href="<c:url value='/j_spring_security_logout'/>"; */
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
	appendContentWithId(data,"_content");
	
}
function confirmIgnore(mode,id){
	$( "#dialog-confirmIgnore" ).dialog({
		/* height: 140, */
		modal: true,
		buttons: {
			"Yes": function() { 
				$( this ).dialog( "close" );
				doIgnore('delete',id);
			},
			"No": function() {
				$( this ).dialog( "close" );
				return false;
			}
		}
	});
}
function doIgnore(mode,id){
	$("#ignore_id").val(id);	 
	/* $.get("todoList/ignore/"+id, function(data) {
		  // alert(data);
		     appendContent(data);
		  // alert($("#_content").html());
		}); */
	$.ajax({
		  type: "get",
		  url: "todoList/ignore/"+id,
		  cache: false
		 // data: { name: "John", location: "Boston" }
		}).done(function( data ) {
			if(data!=null){
				  appendContent(data);
			  }
		});
}

function doSendMailToApprove(mail_todo_idG,mail_todo_refG){
	loadDynamicPage("getmailToApprove/"+mail_todo_idG+"/"+mail_todo_refG);
	/* //alert(action)
	var checked="1";
	// alert(document.getElementById("mail_attach").checked);
	if(!document.getElementById("mail_attach").checked)
		checked="0";
	   var data_to_server= { 
			  mail_todo_id:mail_todo_idG,
			  mail_todo_ref:mail_todo_refG,
			  mail_to: mail_toG.val(),
			  mail_subject: mail_subjectG.val(),
			  mail_message:mail_messageG.val(),
			  mail_attach:checked
				};
	$.post("sendmailToApprove",data_to_server, function(data) {
		  //alert(data);
		    appendContent(data);
		  // alert($("#_content").html());
		}); */
  }
function openMailDialog(todo_id,todo_ref){
	$("#mail_todo_id").val(todo_id);
	$("#mail_todo_ref").val(todo_ref);
	$( "#dialog-modal" ).dialog({
		/* height: 140, */
		modal: true,
		buttons: {
			"Ok": function() { 
				$( this ).dialog( "close" );  
				doSendMailToApprove(todo_id,todo_ref); 
				 
			},
			"Close": function() { 
				$( this ).dialog( "close" );				 
			}
		}
	});
}

/*
document.onHistoryGo = function() { return false; }
window.onbeforeunload = function() {
	 
	}

function noBack() { 
	//alert("can't go Back 1");
	window.history.forward();
}
*/
</script>
</head>
<!-- <body style="background-color:rgb(231, 235, 242)"> -->
 <!-- <body style="background-color:rgb(241, 241, 241)"> -->
 <!--   style="background-color: white;" --> 
  
 <%-- <body style="background-color:rgb(253, 253, 253);background-image:url(<c:url value='/resources/images/body.gif'/>); "> --%>
<%--  <sec:authorize access="hasAnyRole('ROLE_MANAGE_MISSCONSULT')">
	<body style="background-color:rgb(240,240,240);background-image:url(<c:url value='/resources/images/body-y.gif'/>); ">
</sec:authorize> --%>
<!-- 253,253,253 --> 
<%--
<c:if test="${myUser.missContact.maCustomizeHeadColor=='body.gif'}">
	<body style="background-color:rgb(${myUser.missContact.maBackgroundColor});background-image:url(<c:url value='/resources/images/body.gif'/>); "> 
</c:if>
<c:if test="${myUser.missContact.maCustomizeHeadColor=='body-b.gif'}">
	<body style="background-color:rgb(${myUser.missContact.maBackgroundColor});background-image:url(<c:url value='/resources/images/body-b.gif'/>); "> 
</c:if>
<c:if test='${myUser.missContact.maCustomizeHeadColor=="body-g.gif"}'>
	<body style="background-color:rgb(${myUser.missContact.maBackgroundColor});background-image:url(<c:url value='/resources/images/body-g.gif'/>); "> 
</c:if>
<c:if test="${myUser.missContact.maCustomizeHeadColor=='body-o.gif'}">
	<body style="background-color:rgb(${myUser.missContact.maBackgroundColor});background-image:url(<c:url value='/resources/images/body-o.gif'/>); "> 
</c:if> 
   --%>
  
<%-- <body  onload="noBack();" onpageshow="if(event.persisted)noBack();" onunload="" style="background-image:url(<c:url value='/resources/images/${UserMissContact.missTheme.mtLogo}'/>); "> --%>
<body  style="background-image:url(<c:url value='/resources/images/${UserMissContact.missTheme.mtLogo}'/>); "> 
 <div class="container-fluid">
    <div class="row-fluid" >
    	<div class="span12" align="center"> 
    	<div id="header2" align="left"  style="height: 66px">
    	<!--  width="200px" height="33px" -->
    	<div align="left"> 
	 
    	<c:if test="${myUser.missContact.maGrade=='2'}">
    	<h1>
    	<img width="230px" height="60px" src="getfile/mcLogo/1/${myUser.missContact.maCustomizeLogoMCPath}" />
    	<%-- <img src="<c:url value='/resources/images/logowebmc.png'/>" width="230px" height="60px" />  --%>	
    	 </h1> 
    	 </c:if>
    	 </div> 
    	 <div align="center" style="position: absolute;top:0px; left:0px;right:0px; padding-top:10px;">
    	 <c:if test="${myUser.missContact.maGrade=='1'}">
    	 <h1><img width="347px" height="66px" src="getfile/mcLogo/1/${myUser.missContact.maCustomizeLogoMCPath}" />
    	</h1>
    	</c:if>
    	<c:if test="${myUser.missContact.maGrade=='2'}">
    	 <h1><img   width="347px" height="66px" src="getfile/companyLogo/${myUser.missContact.mcontactRef}/${myUser.missContact.maCustomizeLogoPath}" />
    	</h1>
    	</c:if>
    	  </div> 
    	<!-- <div align="right" style="position: absolute; z-index:-5; width:300px; right:0;top:0; padding-top:10px; padding-right:10px;"> -->
    	<div align="right" style="position: absolute; z-index:0; width:300px; right:0;top:0; padding-top:10px; padding-right:10px;">
       <strong><spring:message code="home_system_time"/>:</strong>&nbsp;&nbsp;${systemDate}&nbsp;&nbsp;<span id="defaultCountdown"></span><br/><br/>
      <a  style="cursor: pointer;" href="?language=th_TH"><spring:message code="home_lang_th"/></a> | <a  style="cursor: pointer;" href="?language=en"><spring:message code="home_lang_en"/></a> 
           <%-- Current Locale : ${pageContext.response.locale} --%>
        </div>
         <div align="right" style="position: absolute;right:0;top:75; padding-right:10px;">
           <%=SecurityContextHolder.getContext().getAuthentication().getName()%> <a href="${logoutUrl}">Logout</a>
          
          <!--  <a  style="cursor: pointer;" href="?language=th_TH">TH</a> | <a  style="cursor: pointer;" href="?language=en">EN</a> -->
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
		<form id="_navi_element" class="well" style="background-color: ${UserMissContact.missTheme.mtBgColor}">
		<!-- 
		<form class="well" style="background-color:rgb(245, 245, 245);border: 2px solid #DDD">
		 -->
	   <!--  <span id="demo1" style="font-family: sans-serif;font-size:13px: top;position:relative;top:-35px;right:5px;"></span> -->
	   <h3><strong>Navigation</strong></h3>
	    <span id="demo1" style="font-family: sans-serif;font-size:13px:"></span>
	    </form>
	   <!--  </pre> -->
	    </div>
	    <div class="span8" id="_content">
	    <div id="dialog-confirmIgnore" title="Ignore Item" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
				Are you sure you want to Ignore Item ?
		</div>
	     <div id="dialog-modal" title="Send Email Form" style="display: none">
	<!-- <p>Adding the modal overlay screen makes the dialog look more prominent because it dims out the page content.</p> -->
	<form id="mailApproveForm" name="mailApproveForm"  method="post" action="">
	<input type="hidden" id="mail_todo_id" name="mail_todo_id">
	<input type="hidden" id="mail_todo_ref" name="mail_todo_ref">
	<table>
	<tr valign="top"><td width="20%">To</td><td width="80%"><input type="text" id="mail_to" name="mail_to"></td></tr>
	<tr valign="top"><td width="20%">Subject</td><td width="80%"><input type="text" id="mail_subject" name="mail_subject" ></td></tr>
	<tr valign="top"><td width="20%">Message</td><td width="80%"><textarea rows="4" cols="4" id="mail_message" name="mail_message"></textarea></td></tr>
	<tr valign="top"><td align="left" colspan="2" width="100%"><input type="checkbox" value="1" id="mail_attach" name="mail_attach">Attach Report(PDF)</td></tr>
	 
	<tr valign="top"><td width="20%"></td><td width="80%"></td></tr>
	</table>
	</form>
</div>
	    <!--Body content-->
	<!--  <div class="alert alert-info">
    <button class="close" data-dismiss="alert">×</button>
    <strong>Heads up!</strong> Best check yo self, you're not looking too good.2
    </div> -->
 
    <!-- <h3><strong>MC - Home</strong></h3> -->
      <fieldset style="font-family: sans-serif;">   
    <!--  <form   class="well" style="background-color:white;border: 2px solid #DDD" > -->
    <%--
     <form   class="well" style="border: 2px solid #DDD;background-color: ${UserMissContact.missTheme.mtBgColor}" method="post" enctype="multipart/form-data">
      --%>
    <form   name="formTodoList" class="well" style="border:2px solid #DDD;background: url(<c:url value='/resources/images/${UserMissContact.missTheme.mtWaterWall}'/>) no-repeat scroll right bottom ${UserMissContact.missTheme.mtBgColor}" method="post" enctype="multipart/form-data">
       <!-- <form   class="well" style="background-color:white;border: 2px solid rgba(0, 0, 0, 0.05)" > -->
     <!--  <form   class="well" style=";border: 2px solid rgba(0, 0, 0, 0.05)" > -->
      <input type="hidden" id="ignore_id"/> 
	   <!--   <fieldset style="font-family: sans-serif;">    -->
	     <h3><strong>
	     <c:if test="${UserMissContact.isMC=='1'}">
	      	MC - Home
	      </c:if>
	       <c:if test="${UserMissContact.isMC=='0'}">
	      	Company - Home
	      </c:if>
	      </strong>
	      
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
	    					
	    					<strong><spring:message code="home_todo"/></strong></td>
	    					<td align="right" width="80%">
	    					<%--
	    					<a   href="#">Prev</a>&nbsp;|&nbsp;<select name="bpsGroupId" id="bpgGroupId"  style="width: 55px;"> 
											 <option value="0">1</option>
											 <option value="20">20</option>
											 <option value="300">300</option>
												
	    					</select>&nbsp;|&nbsp;<a href="#">Next</a>
	    					 --%>
	    					<input type="hidden" value="${totals}" id="totals"/>
	    					<input type="hidden" value="${pageObj.pageNo}" id="pageNo"/>
	    					<input type="hidden" value="${pageObj.pageSize}" id="pageSize"/>
	    					<input type="hidden" value="${pageCount}" id="pageCount"/> 
	    					</td>
	    					</tr>
	    					</table> 
	    	<table  border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="left" width="50%">
	    					
	    					<!-- <a class="btn btn-primary" onclick="loadDynamicPage('candidate/new')"><i class="icon-plus-sign icon-white"></i>&nbsp;Create</a>&nbsp; -->
	    					<%-- 
	    					<a class="btn btn-info" onclick="exportCandidat()"><i class="icon-circle-arrow-up icon-white"></i>&nbsp;Export</a>&nbsp;
	    					<a class="btn btn-danger" onclick="doDeleteItems()"><i class="icon-trash icon-white"></i>&nbsp;Delete</a>&nbsp;
	    					 --%>
	    					<td align="right" width="50%">
	    					
	    					<a onclick="goTodoPrev()"><spring:message code='page_prev'/></a>&nbsp;|&nbsp;<span id="pageTodoElement"></span>&nbsp;|&nbsp;<a onclick="goTodoNext()"><spring:message code='page_next'/></a></td>
	    					</tr>
	    					</table>
			 <table id="table_list"  class="table stable-striped table-bordered table-condensed" border="1" style="font-size: 12px"> 
			 <!-- <table class="table table-striped table-bordered" border="1" style="font-size: 12px">  -->
		<!-- 	<table class="simply" border="1" style="font-size: 12px"> 
			 -->
        <thead>
          <tr>
            <th width="65%"><div class="th_class"><spring:message code="home_task"/></div></th>
            <th width="15%"><div class="th_class">Status</div></th>
            <th width="15%"><div class="th_class"><spring:message code="home_respond"/></div></th>
            <th width="5%"><div class="th_class">Ignore</div></th>    
          </tr>
        </thead>
        <tbody>
         <c:forEach items="${todolists}" var="todolist" varStatus="loop">  
            <tr>
             <td><div class="th_class"  style="text-align: left;"><c:out value="${todolist.mtodoTask}"/></div>
              </td> 
               <td><div class="th_class"><c:if test="${todolist.mtodoResponse=='1'}">
              &nbsp;<span style="color: green;">Completed</span>
             </c:if>
              <c:if test="${todolist.mtodoResponse!='1'}">
               &nbsp;<span style="color: orange;">Pending</span>
              </c:if></div></td>
            <td><div class="th_class"><a onclick="doSendMailToApprove('${todolist.mtodoId}','${todolist.mtodoRef}')">Send Email</a></div></td>
            
            <td align="center"><i title="Delete" onclick="confirmIgnore('delete','${todolist.mtodoId}')" style="cursor: pointer;" class="icon-trash"></i></td> 
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
				<img src="<c:url value='/resources/images/h2bg.gif'/>"  border="0" align="absmiddle"/>&nbsp; <spring:message code="home_description"/></h3>
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
