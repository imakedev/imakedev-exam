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
.th_class{text-align: center;
}
</style>
<style type="text/css">
/*label,select,.ui-select-menu { float: left; margin-right: 10px; }*/
/*select { width: 200px; }*/
/*label,select,.ui-select-menu { } */
select { width: 55px;}
/*.ui-widget{font-family: Tahoma;font-size: 12px; }*/
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
	$("#demo1").jstree({ 
		"json_data" : {
			"data" : [
				{ 
					"data" : {title:"Home",icon : "<c:url value='/resources/js/_demo/file.png'/>" },
					"metadata" : { id : "child_23" ,"link":"todolist"},
					 
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
					"children" : [
						{ attributes: { id : "pjson_5" }, data: { title : "EPT",  icon : "<c:url value='/resources/js/_demo/file.png'/>"} },
						{ attributes: { id : "pjson_6" }, data: {title:"EPT Plus",attributes:{ "href" : "www.google.com" },  icon : "<c:url value='/resources/js/_demo/file.png'/>"},"metadata" : { id : 25 } },
						{ attributes: { id : "pjson_7" }, data: {title: "..." , icon : "<c:url value='/resources/js/_demo/file.png'/>"} },
						{ attributes: { id : "pjson_8" }, data: {title: "Search Report" , icon : "<c:url value='/resources/js/_demo/file.png'/>"}, "metadata" : { id : "child_288","link":"result/search" }  }
					          ] 
					
				},
				{ 
					"attr" : { "id" : "li.node.id3" }, 
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
					"attr" : { "id" : "li.node.id3" }, 
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
		"plugins" : [ "themes", "json_data", "ui" ]
	}).bind("select_node.jstree", function (e, data) {
		//alert(data.rslt.obj.data("id"));
		var id=data.rslt.obj.data("id");
		if(id!='undefined'){
			if(id.indexOf("child")!=-1){
				//alert(id);
				//alert(data.rslt.obj.data("link"));
				<%--  
				var _url2="<c:url value=\'/"+data.rslt.obj.data("link")+"\'/>";
				 --%>
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
});
function callAjax(){
	var id="1";
/* 	MissExamAjax.findMissExamGroupById(id,{
		callback:function(data){
			alert(data.pagging.pageNo);
		}
	}); 
	alert("test") */
	//$("#demo1").jstree("open_node", $("#aoe1"));
	// $("#demo1").jstree("select_node", "#aoe2");   
	$("#demo1").jstree("deselect_all");
	  $("#demo1").jstree("select_node", "#24");
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
	    <fieldset style="font-family: sans-serif;">  
           <!-- <div style="position:relative;right:-94%;">  </div> --> 
           					<div><strong>Test Answers</strong></div>
	    					<table border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="left" width="20%">
	    					
	    					<strong>Test : EPT</strong></td>
	    					<td align="right" width="80%">
	    						Set 1
	    					</td>
	    					</tr>
	    					</table> 
<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        <thead>
          <tr>
            <th width="20%"><div class="th_class">Question</div></th>
            <th width="80%"><div class="th_class">Answer</div></th> 
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><div class="th_class">1</div></td>
            <td><input type="radio">1&nbsp;<input type="radio">2&nbsp;<input type="radio">3&nbsp;<input type="radio">4&nbsp;<input type="radio">5</td> 
          </tr>
           <tr>
            <td><div class="th_class">2</div></td>
            <td><input type="radio">1&nbsp;<input type="radio">2&nbsp;<input type="radio">3&nbsp;<input type="radio">4&nbsp;<input type="radio">5</td> 
          </tr>
        </tbody>
      </table>
      </fieldset>
      <%-- --%>
<br/>
	   <div align="center"><input type="button" value="Back"> </div>
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