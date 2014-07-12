<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<sec:authorize access="hasAnyRole('ROLE_ADMIN')" var="isManageMC"/>
<script>
$(document).ready(function() {
	//renderPageSelect();
<%-- <c:if test="${not isManageMC}">	 --%>
<%-- <c:if test="${false}"> --%>
<c:if test="${isManageMC}">
  /*
	  new AjaxUpload('doc_file', {
	       action: 'upload/doc/1',
			onSubmit : function(file , ext){
	           // Allow only images. You should add security check on the server-side.
				if (ext && /^(pdf|PDF)$/.test(ext)){
				 
					this.setData({
					});					
				//$('#contact_photo').attr('src', _path+"resources/images/ui-anim_basic_16x16.gif");
				//$('#contact_photo').attr('src', _path+"resources/images/loading.gif");
				} else {					
					// extension is not allowed
					alert('Error: only pdf are allowed') ;
					// cancel upload
					return false;				
				}		
			},
			onComplete : function(file, response){
				var obj = jQuery.parseJSON(response);
				loadDynamicPage("manual/doc");
				 
			}		
		});
  */
	  $('#update_user_profile_file').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes = /(\.|\/)(xls|XLS|xlsx|XLSX)$/i; 
	                
	              //  var xx=JSON.stringify(data);
	               // alert(xx)
	                 // alert(data.originalFiles[0]['name'])
	                  	
	                 var ua = window.navigator.userAgent;
	                var msie = ua.indexOf("MSIE ");
	                  	 if (msie > 0)  {
	                  		if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	    	                    uploadErrors.push('Error: only excel are allowed');
	    	                   // alert('Not an accepted file type 2')
	    	                }
	    	                
	                  	 }
	           		 else {
	           			if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
		                    uploadErrors.push('Error: only excel are allowed');
		                   // alert('Not an accepted file type 2')
		                }
		                if(data.originalFiles[0]['size'].length>0 && data.originalFiles[0]['size'] > 5000000) {
		                    uploadErrors.push('Filesize is too big');
		                }
	           		 }
	                /*
	                if(data.originalFiles[0]['name'].length && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	                    uploadErrors.push('Error: only excel are allowed');
	                   // alert('Not an accepted file type 2')
	                }
	                if(data.originalFiles[0]['size'].length && data.originalFiles[0]['size'] > 5000000) {
	                    uploadErrors.push('Filesize is too big');
	                }
	                */
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                    data.submit();
	                }
	        },
		        url: 'upload/sap/1',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
	           /*
	            if (msie > 0)      // If Internet Explorer, return version number
	                alert(parseInt(ua.substring(msie + 5, ua.indexOf(".", msie))));
	            else                 // If another browser, return 0
	                alert('otherbrowser');
	            */
	            //loadDynamicPage("manual/doc");
	           // var xx=JSON.stringify(data);
               // alert(xx)
              // alert(data.jqXHR.status)
		        },
		        fail: function (e, data) {
		            $.each(data.messages, function (index, error) {
		            	alert('error->'+error);
		            });
		        },
		        progressall: function (e, data) {
		        	//$('#candidate_photo').attr('src', _path+"resources/images/loading.gif");
		        }
		    }).prop('disabled', !$.support.fileInput)
		        .parent().addClass($.support.fileInput ? undefined : 'disabled');
	  
	  </c:if>
});
function confirmDelete(mode,id){
	$( "#dialog-confirmDelete" ).dialog({
		/* height: 140, */
		modal: true,
		buttons: {
			"Yes": function() { 
				$( this ).dialog( "close" );
				doDeleteFile(id)
			},
			"No": function() {
				$( this ).dialog( "close" );
				return false;
			}
		}
	});
}
function goPrev(){
	if($("#pageNo").val()!='1'){
		var prev=parseInt($("#pageNo").val())-1;
		$("#pageNo").val(prev);
		doAction('search','0');
	}
}
function goNext(){
	var next=parseInt($("#pageNo").val());
	if(next<parseInt($("#pageCount").val())){
		next=next+1;
		$("#pageNo").val(next);
		doAction('search','0');
	}
} 
function goToPage(){ 
	$("#pageNo").val(document.getElementById("testPageSelect").value);
	doAction('search','0');
}
function getFileAttached(path){
	// alert(path)
    var div = document.createElement("div");
    document.body.appendChild(div);
    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + path + "'></iframe>";
	  // Create an IFRAME.
}
function doDeleteFile(id){
	$.get("deletefile/doc/"+id, function(data) {
		  // alert(data);
		   loadDynamicPage("manual/doc");
		  // alert($("#_content").html());
		});
}
function doAction(mode,id){
	$("#mode").val(mode);
	if(mode=='deleteItems'){
		$("#meIdArray").val(id);
	}else if(mode!='search'){
		$("#meId").val(id);
	}else {
		$("#meId").val("0");
	}
	$.post("test/search",$("#testForm").serialize(), function(data) {
		  // alert(data);
		    appendContent(data);
		  // alert($("#_content").html());
		});
}
/* function doSearch(){
	$("#mode").val("search");
	$("#mode").val("edit");
	$.post("series/search",$("#testForm").serialize(), function(data) {
		  // alert(data);
		    appendContent(data);
		  // alert($("#_content").html());
		});
	
} */
function call(){
	//alert("xx "+document.getElementById("productReport_year").value)
	$.ajax({
		  type: "get",
		  url: "ajax/aoe",
		  cache: false
		 // data: { name: "John", location: "Boston" }
		}).done(function( data ) {
			if(data!=null){
				 alert(data);
			  }
		});
}

</script> 
<style>
th{ font-family:Tahoma; font-size:12px; font-weight:bold;
 color: #fff;background:url(<c:url value='/resources/images/${UserMissContact.missTheme.mtTr}'/>) repeat-x scroll 0 0 ${UserMissContact.missTheme.mtTrColor};padding: 5px 8px;border:1px solid #fff; 
}
</style>
  <div id="dialog-confirmDelete" title="Delete Document" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
	Are you sure you want to delete Document ?
</div>
	    <fieldset style="font-family: sans-serif;">  
	  <%-- <c:if test="${false}"> --%>
	
		 <table class="table table-striped table-bordered table-condensed"  border="1" style="font-size: 12px;width: 100%;"> 
		<!-- <table  border="1" style="font-size: 12px;width: 100%;"> -->
        	<thead>
          		<tr>
            		<th width="100%"><div class="th_class">Update User profile</div></th>            		 
          		</tr>
        	</thead>
        	<tbody>
        	  
          	<tr> 
            	<td>
            	<c:if test="${isManageMC}">
	     <table border="0" width="100%">
	     	<tr>
	     		<td align="right">
	     			<%-- <span ><a class="btn" id="doc_file"><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a></span>  --%>
	     			 <span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="update_user_profile_file" type="file" name="userfile" multiple>
    </span>
    <span onclick="call()">Aoe</span>
	     		</td>
	     	</tr>
	     </table>
	     </c:if>
            	</td>
          	</tr>
           
        	</tbody>
      </table>
</fieldset>