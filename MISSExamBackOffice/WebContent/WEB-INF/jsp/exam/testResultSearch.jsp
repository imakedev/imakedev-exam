<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<html>
<head>
<title></title>  
<c:url var="url" value="/" />
<script type="text/javascript"
        src="<c:url value='/dwr/interface/MissExamAjax.js'/>"></script> 
<script type="text/javascript"
        src="<c:url value='/dwr/engine.js'/>"></script> 
<script type="text/javascript"
        src="<c:url value='/dwr/util.js'/>"></script>	
<script type="text/javascript" src="<c:url value='/resources/js/jquery-1.7.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" type="text/css">
<link type="text/css" href="<c:url value='/resources/css/custom-theme/jquery-ui-1.8.20.custom.css'/>" rel="stylesheet" /> 
<link rel="stylesheet" href="<c:url value='/resources/css/jquery.ui.selectmenu.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/resources/js/jquery-ui-1.8.20.custom.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.selectmenu.js'/>"></script>

<script type="text/javascript" src="<c:url value='/resources/js/jquery.jstree.js'/>"></script>
<script type="text/javascript" src="${url}resources/js/jquery.jstree.js"></script>
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
.th_class{text-align: center;}
</style>
<style type="text/css">
/*label,select,.ui-select-menu { float: left; margin-right: 10px; }*/
/*select { width: 200px; }*/
/*label,select,.ui-select-menu { } */
select { width: 55px;}
/*.ui-widget{font-family: Tahoma;font-size: 12px; }*/
input[type="text"],[type="password"]{height:30px}
img.ui-datepicker-trigger{cursor: pointer;}
</style>
<script type="text/javascript">
$(document).ready(function() {
	//$('#tabs').tabs();
  //   $("fieldset.collapsibleClosed").collapse( { closed : true } );
  var _path="${url}";
  //alert(_path)
  //alert(_path.split(";jsessionid=").length)
  if(_path.split(";jsessionid=").length>0){
	  _path=_path.split(";jsessionid=")[0];
	  //alert(_path.split(";jsessionid=").length);
  }
  //alert("new path="+_path)
  $.jstree._themes = "/MISSExamBackOffice/resources/js/themes/";
 //$('select#bpgGroupId').selectmenu({style:'dropdown'});
	var _treemenu=$("#demo1").jstree({ 
		"json_data" : {
			"data" : [
				{ 
					"data" : {title:"Home",icon : "<c:url value='/resources/js/_demo/file.png'/>" },
					"metadata" : { id : "child_23" ,"link":"todolist"},
					"attr" : { "id" : "tree_23" }, 
				},
				{ 
					"attr" : { "id" : "aoe2" }, 
					"data" : { 
						"title" : "บัญชีผู้ใช้", 
						"attr" : { "href" : "#" } 
					},
					"metadata" : { id : "root_aoee2"},
					"children" : [
						{ attributes: { id : "pjson_2" }, data: { title : "Miss", icon : "<c:url value='/resources/js/_demo/file.png'/>" },"attr" : { "id" : "24"},"metadata" : { id : "child_24","link":"miss/account"} },
						{ attributes: { id : "pjson_3" }, data: {title:"Company",attributes:{ "href" : "www.google.com" } , icon : "<c:url value='/resources/js/_demo/file.png'/>"},"metadata" : { id : "child_25","link":"company/search" } },
						{ attributes: { id : "pjson_4" }, data: { title:"Candidate", icon : "<c:url value='/resources/js/_demo/file.png'/>"} ,"metadata" : { id : "child_26","link":"candidate/search" }}
					          ] 
					
				},
				{ 
					"attr" : { "id" : "li.node.id3" }, 
					"data" : { 
						"title" : "Result Report", 
						"attr" : { "href" : "www.google.com" } 
					},
					// "state" : "open", 
					"children" : [
						{ attributes: { id : "pjson_5" }, data: { title : "EPT",  icon : "<c:url value='/resources/js/_demo/file.png'/>"} },
						{ attributes: { id : "pjson_6" }, data: {title:"EPT Plus",attributes:{ "href" : "www.google.com" },  icon : "<c:url value='/resources/js/_demo/file.png'/>"},"metadata" : { id : 25 } },
						{ attributes: { id : "pjson_7" }, data: {title: "..." , icon : "<c:url value='/resources/js/_demo/file.png'/>"},"attr" : { "id" : "child_289" }  },
						{ attributes: { id : "pjson_8" }, data: {title: "Search Report" , icon : "<c:url value='/resources/js/_demo/file.png'/>"},"metadata" : { id : "child_288","link":"result/search"},"attr" : { "id" : "tree_288" }  }
					          ] 
					
				},
				{ 
					"attr" : { "id" : "li.node.id4" }, 
					"data" : { 
						"title" : "จัดการแบบสอบถาม", 
						"attr" : { "href" : "www.google.com" } 
					},
					"children" : [
						{ attributes: { id : "pjson_111" }, data: { title : "Series",  icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_25","link":"series/search" } },
						{ attributes: { id : "pjson_222" }, data: {title:"Test",attributes:{ "href" : "www.google.com" }, icon : "<c:url value='/resources/js/_demo/file.png'/>" },"metadata" : { id : "child_26" ,"link":"test/search"} }
					          ] 
					
				},
				{ 
					"attr" : { "id" : "li.node.id5" }, 
					"data" : { 
						"title" : "etc.", 
						"attr" : { "href" : "www.google.com" } 
					},
					"children" : [
						{ attributes: { id : "pjson_333" }, data: { title : "Download",  icon : "<c:url value='/resources/js/_demo/file.png'/>" } }
						
					          ] 					
				}
			]
		},
		"plugins" : [ "themes", "json_data", "ui" ],
		// the UI plugin - it handles selecting/deselecting/hovering nodes
		"core" : {
		// this makes the node with ID node_4 selected onload
			"initially_open" : [ "li.node.id3" ]
		}
	});
	 _treemenu.bind("select_node.jstree", function (e, data) {
		//alert(data.rslt.obj.data("id"));
		var id=data.rslt.obj.data("id");
		if(id!='undefined'){
			if(id.indexOf("child")!=-1){
				//alert(id);
				//alert(data.rslt.obj.data("link"));
				<%--  
				var _url2="<c:url value=\'/"+data.rslt.obj.data("link")+"\'/>";
				 --%>
					$("li#tree_"+id.split("_")[1]+" > a").removeClass('jstree-clicked');
				//var _url="http://localhost:8080/MISSExamBackOffice/welcome/todolist";
				window.location.href=_path+data.rslt.obj.data("link");
				//"http://localhost:8080/MISSExamBackOffice/welcome/todolist";
			   }
			}
 	/* 	alert(data.rslt.obj.data("id"));
		alert("e="+e);
		alert("data="+data);
		alert("href="+data.rslt.obj.data("href")); */
		});
	 setClicked("tree_288");
	 //setTimeout(function () { $("li#child_288 > a").addClass('jstree-clicked') }, 1000);
	// $.jstree._focused().select_node("#child_288"); 
	 //setTimeout(function () { $.jstree._focused().select_node("#child_288", true); }, 1000);
	
	/* alert("before") */
/* 	$("#demo1").jstree("deselect_all");
	$("#demo1").jstree("open_node", $("#li.node.id3"));
	
	$("#demo1").jstree("select_node", "#child_289"); */
	/*alert("affter") */
	 $("#datepicker_from" ).datepicker({
			showOn: "button",
			buttonImage: _path+"resources/images/calendar.gif",
			buttonImageOnly: true,
			dateFormat:"dd/mm/yy" 
		});
		$("#datepicker_to" ).datepicker({
			showOn: "button",
			buttonImage: _path+"resources/images/calendar.gif",
			buttonImageOnly: true,
			dateFormat:"dd/mm/yy" 
		});
});
function setClicked(id){
	 //setTimeout('setClicked("child_288")',1000); 
	 setTimeout(function () { $("li#"+id+" > a").addClass('jstree-clicked') }, 1000);
	 //  $("li#"+id+" > a").addClass('jstree-clicked') ;
}
function callAjax(){
	var id="1";
	$("#demo1").jstree("deselect_all");
	//child_288
	//$("#demo1").jstree("open_node", $("#li.node.id3"));
	// $("#demo1").jstree("select_node","#child_289");
	//$("li#child_288:nth-child(2)").addClass('jstree-clicked')
	
	 //alert();
	// $("ul li:nth-child(2)").append("<span> - 2nd!</span>");
/* 	MissExamAjax.findMissExamGroupById(id,{
		callback:function(data){
			alert(data.pagging.pageNo);
		}
	}); 
	alert("test") */
	//$("#demo1").jstree("open_node", $("#aoe1"));
	// $("#demo1").jstree("select_node", "#aoe2");   
	
	//$("#demo1").jstree("open_node", $("#pjson_3"));
	//  var node = $.jstree._reference('#demo1').get_selected(); // 

}
</script>
</head>
<body>
 <div class="container-fluid">
    <div class="row-fluid">
    	<div class="span12" align="center">
    	<%--
    	 <div class="alert alert-error">
    <button class="close" data-dismiss="alert">×</button>
    <strong>Warning!</strong> Best check yo self, you're not looking too good.
    </div>
    	    <div class="alert">
    <button class="close" data-dismiss="alert">×</button>
    <strong>Warning!</strong> Best check yo self, you're not looking too good.
    </div>
     <div class="alert alert-info">
    <button class="close" data-dismiss="alert">×</button>
    <strong>Heads up!</strong> Best check yo self, you're not looking too good.2
    </div>
     <div class="alert alert-success">
    <button class="close" data-dismiss="alert">×</button>
    <strong>Success!</strong> Best check yo self, you're not looking too good.2
    </div>
     
       <form>
       <select name="bpsGroupId" id="bpgGroupId"> 
											 <option value="0">---Select Category--</option>
												
	    					</select>
       </form>
       --%>
    	<!-- <fieldset class="collapsible">
<legend>Can Collapse Me!</legend>
  <h2>1914 translation by H. Rackham</h2>
  <p>"On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment...,"</p>
</fieldset> -->
<%--
 <div class="alert alert-info">
    <button class="close" data-dismiss="alert">×</button>
    <strong>Heads up!</strong> Best check yo self, you're not looking too good.2
    </div>
     --%>
    	<table width="100%">
    	<tr>
    			  <td width="15%"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="149" height="71">
                    <param name="movie" value="http://www.missconsult.com/jobexpertweb/images/logo.swf" />
                    <param name="quality" value="high" />
                    <embed src="http://www.missconsult.com/jobexpertweb/images/logo.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="149" height="71"></embed>
                  </object></td>
                  <td width="75%"><div align="left"><img src="http://www.missconsult.com/jobexpertweb/images/text.jpg" width="407" height="32" /></div></td>
                  <td width="10%"><div align="left"><pre>system time</pre></div></td>
                </tr>
             </table>
    	<fieldset>
		<!-- <legend>ทดสอบ2</legend>  -->
		<%--
    	Test ${aoe} ทดสอบ
<input type="button" onclick="callAjax()" value="aoe"/>
 --%>
    	</fieldset></div>
    </div>
    	<div class="row-fluid"> 
	    <div class="span2">
	    <!--Sidebar content--> 
		 <pre style="font-size: 13px"> 
	    <span id="demo1" style="font-family: sans-serif;font-size:13px: top;position:relative;top:-35px;right:5px;"></span>
	    </pre>
	    </div>
	    <div class="span8">
	    <!--Body content-->
	 <!--  <div class="alert alert-info">
    <button class="close" data-dismiss="alert">×</button>
    <strong>Heads up!</strong> Best check yo self, you're not looking too good.2
    </div>  -->
	    <fieldset style="font-family: sans-serif;">  
           <!-- <legend  style="font-size: 13px">Criteria</legend> -->
           <!-- <div style="position:relative;right:-94%;">  </div> --> 
          <form class="well">
              <table border="0" width="100%" style="font-size: 13px">
              				<tr>
	    					 <td align="left" width="100%" colspan="6"><strong>Test Result Search</strong></td>
	    					</tr>
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
	    					 <td align="left" width="17%">Title:</td>
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
	    					
	    					<a href="#">Prev</a>&nbsp;|&nbsp;<select name="bpsGroupId" id="bpgGroupId"> 
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
            	<td><a href="<c:url value='/result/viewAnswer/1'/>">M000000</a></td>
            	<td>Mr A</td>
            	<td>Last</td>
            	<td>A</td>
            	<td>Department A</td>
            	<td>11</td>
            	<td>23</td>            	
            	<td>14</td> 
            	<td>12</td>
            	<td>D/M/Y h:m</td>
            	<td><a href="<c:url value='/result/report/1'/>">xx</a></td>
            	<td><a href="<c:url value='/result/response/1'/>">xx</a></td>
          	</tr>
         	<tr>
            	<td><input type="checkbox" /></td>
            	<td><a href="<c:url value='/result/viewAnswer/1'/>">M000000</a></td>
            	<td>Mr A</td>
            	<td>Last</td>
            	<td>A</td>
            	<td>Department A</td>
            	<td>11</td>
            	<td>23</td>            	
            	<td>14</td> 
            	<td>12</td>
            	<td>D/M/Y h:m</td>
            	<td><a href="<c:url value='/result/report/1'/>">xx</a></td>
            	<td><a href="<c:url value='/result/response/1'/>">xx</a></td>
          	</tr>
          		<tr>
            	<td><input type="checkbox" /></td>
            	<td><a href="<c:url value='/result/viewAnswer/1'/>">M000000</a></td>
            	<td>Mr A</td>
            	<td>Last</td>
            	<td>A</td>
            	<td>Department A</td>
            	<td>11</td>
            	<td>23</td>            	
            	<td>14</td> 
            	<td>12</td>
            	<td>D/M/Y h:m</td>
            	<td><a href="<c:url value='/result/report/1'/>">xx</a></td>
            	<td><a href="<c:url value='/result/response/1'/>">xx</a></td>
          	</tr>
        	</tbody>
      </table>
      
      </fieldset>
      <%-- --%>
<br/>
	   <%--
          <h2 class="demoHeaders">Tabs</h2> 
		<fieldset>
		<legend>Test</legend>
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">First</a></li>
				<li><a href="#tabs-2">Second</a></li>
				<li><a href="#tabs-3">Third</a></li>
			</ul>
			<div id="tabs-1">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</div>
			<div id="tabs-2">Phasellus mattis tincidunt nibh. Cras orci urna, blandit id, pretium vel, aliquet ornare, felis. Maecenas scelerisque sem non nisl. Fusce sed lorem in enim dictum bibendum.</div>
			<div id="tabs-3">Nam dui erat, auctor a, dignissim quis, sollicitudin eu, felis. Pellentesque nisi urna, interdum eget, sagittis et, consequat vestibulum, lacus. Mauris porttitor ullamcorper augue.</div>
		</div>
		</fieldset>
		    <div class="pagination">
    <ul>
    <li><a href="#">Prev</a></li>
    <li class="active">
    <a href="#">1</a>
    </li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">Next</a></li>
    </ul>
    </div>
    <ul class="dropdown-menu">
    <li>aoe</li>
    <li>aoe2</li> 
    </ul>  --%>
	    </div>
  <div class="span2">
	    <!--Sidebar content-->
	      
	        <pre>
   <span style="font-family: sans-serif;">About Us: เกี่ยวกับเรา

MissConsult CO., Ltd (MC) หมายเลขทะเบียน 3-0321-0436-4 ก่อตั้งและดำเนินงาน โดยความร่วมมือระหว่าง ผู้บริหาร ไทย และ ผู้บริหารจาก สหรัฐอเมริกา ในปี 2005 บนวัตถุประสงค์ การบริการคุณภาพเพื่อการพัฒนาศักยภาพบุคคล ในฐานะที่ปรึกษาด้านการบริหารการจัดการความสามารถและกลยุทธ์ เพื่อความสำเร็จขององค์กร (MissConsult: Consult for Management Intelligence and Strategy for Success)</span>
    </pre>
         
	    </div>
	 </div>
    </div>

</body>
</html>