<%@ page contentType="text/html; charset=utf-8" %>
 <%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script>
$(document).ready(function() {
	<%-- 
 // button1
   new AjaxUpload('template_file_thai_1', {
       action: 'reportUpload/upload/${template_msId}/1/0',
		onSubmit : function(file , ext){  
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_thai_1").val()
				});		
			
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}	
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_thai_1").val()
				});		
			
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}	
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("reportUpload/getfile/${template_msId}/1/0/'+obj.hotlink+'")'; 
			 $('#template_file_thai_1_attached').attr('onclick',path_file);
			 //alert(file) 
			 $('#template_file_thai_1_attached').html(file);
			 $('#template_file_thai_1_attached').attr('style','cursor: pointer;');
			 //$('#template_file_thai_1_attached_delete').html("&nbsp;&nbsp;"+"<i title=\"Update\" class=\"icon-trash\"></i>");
			 //$('#template_file_thai_1_attached_delete').attr('style','cursor: pointer;');
			 
		}		
	});
   new AjaxUpload('template_file_eng_1', {
       action: 'reportUpload/upload/${template_msId}/1/1',
		onSubmit : function(file , ext){ 
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_eng_1").val()
				});					 
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}		
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_eng_1").val()
				});					 
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("getfile/template/${template_msId}/'+obj.hotlink+'")'; 
			 $('#template_file_eng_1_attached').attr('onclick',path_file);
			 $('#template_file_eng_1_attached').html(file);
			$('#template_file_eng_1_attached').attr('style','cursor: pointer;');	
			 //$('#template_file_eng_1_attached_delete').html("&nbsp;&nbsp;"+"<i class=\"icon-trash\"></i>");
			 //$('#template_file_eng_1_attached_delete').attr('style','cursor: pointer;');	 
		}		
	});
    
// button2
   new AjaxUpload('template_file_thai_2', {
       action: 'reportUpload/upload/${template_msId}/2/0',
		onSubmit : function(file , ext){ 
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_thai_2").val()
				});					 
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}	
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_thai_2").val()
				});					 
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}	
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("getfile/template/${template_msId}/'+obj.hotlink+'")'; 
			 $('#template_file_thai_2_attached').attr('onclick',path_file);
			 $('#template_file_thai_2_attached').html(file);
			 $('#template_file_thai_2_attached').attr('style','cursor: pointer;');	 
			 //$('#template_file_thai_2_attached_delete').html("&nbsp;&nbsp;"+"<i class=\"icon-trash\"></i>");
			 //$('#template_file_thai_2_attached_delete').attr('style','cursor: pointer;');	
		}		
	});
   new AjaxUpload('template_file_eng_2', {
       action: 'reportUpload/upload/${template_msId}/2/1',
		onSubmit : function(file , ext){ 
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_eng_2").val()
				});					 
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}	
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_eng_2").val()
				});					 
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("getfile/template/${template_msId}/'+obj.hotlink+'")'; 
			 $('#template_file_eng_2_attached').attr('onclick',path_file);
			 $('#template_file_eng_2_attached').html(file);
			 $('#template_file_eng_2_attached').attr('style','cursor: pointer;');	
			// $('#template_file_eng_2_attached_delete').html("&nbsp;&nbsp;"+"<i class=\"icon-trash\"></i>");
			// $('#template_file_eng_2_attached_delete').attr('style','cursor: pointer;');	
		}		
	});
   
// button3
   new AjaxUpload('template_file_thai_3', {
       action: 'reportUpload/upload/${template_msId}/3/0',
		onSubmit : function(file , ext){ 
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_thai_3").val()
				});					 
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}		
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_thai_3").val()
				});					 
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("getfile/template/${template_msId}/'+obj.hotlink+'")'; 
			 $('#template_file_thai_3_attached').attr('onclick',path_file);
			 $('#template_file_thai_3_attached').html(file);
			 $('#template_file_thai_3_attached').attr('style','cursor: pointer;');	
			// $('#template_file_thai_3_attached_delete').html("&nbsp;&nbsp;"+"<i class=\"icon-trash\"></i>");
			// $('#template_file_thai_3_attached_delete').attr('style','cursor: pointer;');	
		}		
	});
   new AjaxUpload('template_file_eng_3', {
       action: 'reportUpload/upload/${template_msId}/3/1',
		onSubmit : function(file , ext){ 
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_eng_3").val()
				});					 
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}		
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_eng_3").val()
				});					 
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("getfile/template/${template_msId}/'+obj.hotlink+'")'; 
			 $('#template_file_eng_3_attached').attr('onclick',path_file);
			 $('#template_file_eng_3_attached').html(file);
			 $('#template_file_eng_3_attached').attr('style','cursor: pointer;');	
			// $('#template_file_eng_3_attached_delete').html("&nbsp;&nbsp;"+"<i class=\"icon-trash\"></i>");
			// $('#template_file_eng_3_attached_delete').attr('style','cursor: pointer;');	
		}		
	});
   
  //button4
   new AjaxUpload('template_file_thai_4', {
       action: 'reportUpload/upload/${template_msId}/4/0',
		onSubmit : function(file , ext){ 
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_thai_4").val()
				});					 
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}		
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_thai_4").val()
				});					 
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}	
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("getfile/template/${template_msId}/'+obj.hotlink+'")'; 
			 $('#template_file_thai_4_attached').attr('onclick',path_file);
			 $('#template_file_thai_4_attached').html(file);
			 $('#template_file_thai_4_attached').attr('style','cursor: pointer;');	
			// $('#template_file_thai_4_attached_delete').html("&nbsp;&nbsp;"+"<i class=\"icon-trash\"></i>");
			// $('#template_file_thai_4_attached_delete').attr('style','cursor: pointer;');	
		}		
	});
   new AjaxUpload('template_file_eng_4', {
       action: 'reportUpload/upload/${template_msId}/4/1',
		onSubmit : function(file , ext){ 
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_eng_4").val()
				});					 
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}		
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_eng_4").val()
				});					 
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}	
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("getfile/template/${template_msId}/'+obj.hotlink+'")'; 
			 $('#template_file_eng_4_attached').attr('onclick',path_file);
			 $('#template_file_eng_4_attached').html(file);
			 $('#template_file_eng_4_attached').attr('style','cursor: pointer;');	
			 //$('#template_file_eng_4_attached_delete').html("&nbsp;&nbsp;"+"<i class=\"icon-trash\"></i>");
			 //$('#template_file_eng_4_attached_delete').attr('style','cursor: pointer;');	
		}		
	});
   
// button5
   new AjaxUpload('template_file_thai_5', {
       action: 'reportUpload/upload/${template_msId}/5/0',
		onSubmit : function(file , ext){ 
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_thai_5").val()
				});					 
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}		
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_thai_5").val()
				});					 
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}	
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("getfile/template/${template_msId}/'+obj.hotlink+'")'; 
			 $('#template_file_thai_5_attached').attr('onclick',path_file);
			 $('#template_file_thai_5_attached').html(file);
			 $('#template_file_thai_5_attached').attr('style','cursor: pointer;');	
			 //$('#template_file_thai_5_attached_delete').html("&nbsp;&nbsp;"+"<i class=\"icon-trash\"></i>");
			 //$('#template_file_thai_5_attached_delete').attr('style','cursor: pointer;');	
		}		
	});
   new AjaxUpload('template_file_eng_5', {
       action: 'reportUpload/upload/${template_msId}/5/1',
		onSubmit : function(file , ext){ 
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_eng_5").val()
				});					 
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}		
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_eng_5").val()
				});					 
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("getfile/template/${template_msId}/'+obj.hotlink+'")'; 
			 $('#template_file_eng_5_attached').attr('onclick',path_file);
			 $('#template_file_eng_5_attached').html(file);
			 $('#template_file_eng_5_attached').attr('style','cursor: pointer;');	
			 //$('#template_file_eng_5_attached_delete').html("&nbsp;&nbsp;"+"<i class=\"icon-trash\"></i>");
			 //$('#template_file_eng_5_attached_delete').attr('style','cursor: pointer;');	
		}		
	});
   
// button6
   new AjaxUpload('template_file_thai_6', {
       action: 'reportUpload/upload/${template_msId}/6/0',
		onSubmit : function(file , ext){ 
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_thai_6").val()
				});					 
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}		
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_thai_6").val()
				});					 
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("getfile/template/${template_msId}/'+obj.hotlink+'")'; 
			 $('#template_file_thai_6_attached').attr('onclick',path_file);
			 $('#template_file_thai_6_attached').html(file);
			 $('#template_file_thai_6_attached').attr('style','cursor: pointer;');	
			 //$('#template_file_thai_5_attached_delete').html("&nbsp;&nbsp;"+"<i class=\"icon-trash\"></i>");
			 //$('#template_file_thai_5_attached_delete').attr('style','cursor: pointer;');	
		}		
	});
   new AjaxUpload('template_file_eng_6', {
       action: 'reportUpload/upload/${template_msId}/6/1',
		onSubmit : function(file , ext){ 
			<c:if test="${missSery.msExporting=='0'}">
			if (ext && /^(jasper)$/.test(ext)){ 
				this.setData({ 
					reportName:$("#template_report_eng_6").val()
				});					 
			} else {					 
				alert('Error: only jasper are allowed') ; 
				return false;				
			}		
			</c:if>
			<c:if test="${missSery.msExporting=='1'}">
			if (ext && /^(xls|XLS|xlsx|XLSX)$/.test(ext)){
				this.setData({ 
					reportName:$("#template_report_eng_6").val()
				});					 
			} else {					 
				alert('Error: only xls are allowed') ; 
				return false;				
			}	
			</c:if>
		},
		onComplete : function(file, response){ 
			var obj = jQuery.parseJSON(response);
			var path_file='getFileAttached("getfile/template/${template_msId}/'+obj.hotlink+'")'; 
			 $('#template_file_eng_6_attached').attr('onclick',path_file);
			 $('#template_file_eng_6_attached').html(file);
			 $('#template_file_eng_6_attached').attr('style','cursor: pointer;');	
			 //$('#template_file_eng_5_attached_delete').html("&nbsp;&nbsp;"+"<i class=\"icon-trash\"></i>");
			 //$('#template_file_eng_5_attached_delete').attr('style','cursor: pointer;');	
		}		
	});
	--%>
	<%-- 
	for(var i=1;i<=6;i++){
		// button1 
		  $('#template_file_thai_'+i).fileupload({
		        add: function(e, data) {
		                var uploadErrors = [];
		                var acceptFileTypes ;//= /^image\/(gif|jpe?g|png)$/i;
		                <c:if test="${missSery.msExporting=='0'}">
		                acceptFileTypes=/(\.|\/)(jasper)$/i;
		                 if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
		                	 uploadErrors.push('Error: only jasper are allowed');
						
						} 	
						</c:if>
						<c:if test="${missSery.msExporting=='1'}"> 
						 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
						 if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
							 uploadErrors.push('Error: only xls are allowed');
						
						} 
						</c:if>
		               
		                if(uploadErrors.length > 0) {
		                    alert(uploadErrors.join("\n"));
		                } else {
		                	 
		                	data.formData= {
		        		        	reportName: $("#template_report_thai_"+i).val() 
		      	          };
		                	 
		                    data.submit();
		                }
		        },
		       
			        url: 'reportUpload/upload/${template_msId}/'+i+'/0',
			        dataType: 'json', 
			        autoUpload: false, 
			        done: function (e, data) { 
			         var ua = window.navigator.userAgent;
		            var msie = ua.indexOf("MSIE ");
		            
						var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
						 $('#template_file_thai_'+data.result.id+'_attached').attr('onclick',path_file);
						 //alert(file) 
						 $('#template_file_thai_'+data.result.id+'_attached').html(data.result.filename);
						 $('#template_file_thai_'+data.result.id+'_attached').attr('style','cursor: pointer;');
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
		  
		        
		  $('#template_file_eng_'+i).fileupload({
		        add: function(e, data) {
		                var uploadErrors = [];
		                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
		                <c:if test="${missSery.msExporting=='0'}">
		                acceptFileTypes=/(\.|\/)(jasper)$/i;
		               if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
		            	   uploadErrors.push('Error: only jasper are allowed');
						
						}  
						</c:if>
						<c:if test="${missSery.msExporting=='1'}"> 
						 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
						 if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
							 uploadErrors.push('Error: only xls are allowed');
						}  	
						</c:if>
		              
		                if(uploadErrors.length > 0) {
		                    alert(uploadErrors.join("\n"));
		                } else {
		                	 
		                	data.formData={
		        		        	 reportName: $("#template_report_eng_"+i).val() 
		        	        };
		                	 
		                    data.submit();
		                }
		        },
		        
			        url: 'reportUpload/upload/${template_msId}/'+i+'/1',
			        dataType: 'json', 
			        autoUpload: false, 
			        done: function (e, data) { 
			         var ua = window.navigator.userAgent;
		            var msie = ua.indexOf("MSIE ");
		           
						var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
						 $('#template_file_eng_'+data.result.id+'_attached').attr('onclick',path_file);
						  alert(data.result.id+","+data.result.filename) 
						  template_file_eng_2_attached
						 $('#template_file_eng_'+data.result.id+'_attached').html(data.result.filename);
						 $('#template_file_eng_'+data.result.id+'_attached').attr('style','cursor: pointer;');
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
	}
	 --%> 
	// button1 
	  $('#template_file_thai_1').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	              
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	 data.formData= {
	         	        	reportName: $("#template_report_thai_1").val() 
	                   };
	                    data.submit();
	                }
	        },
	       
		        url: 'reportUpload/upload/${template_msId}/1/0',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
  			 if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/1/0",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){  
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_thai_1_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_thai_1_attached').html(data.filename);
	   					 $('#template_file_thai_1_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
	            	var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_thai_1_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_thai_1_attached').html(data.result.filename);
					 $('#template_file_thai_1_attached').attr('style','cursor: pointer;');
	            }
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response);
					 
					
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
	  
	        
	  $('#template_file_eng_1').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	              
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	 data.formData= {
	         	        	reportName: $("#template_report_eng_1").val() 
	                 };
	                    data.submit();
	                }
	        },
	       
		        url: 'reportUpload/upload/${template_msId}/1/1',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
 			if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/1/1",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){  
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_eng_1_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_eng_1_attached').html(data.filename);
	   					 $('#template_file_eng_1_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
	            	var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_eng_1_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_eng_1_attached').html(data.result.filename);
					 $('#template_file_eng_1_attached').attr('style','cursor: pointer;');
	            }
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response); 
					
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
	  
	// button2
	  $('#template_file_thai_2').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	              
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	 // e.g. {id: 123}
	                	 data.formData = {
	             	        				reportName:  $("#template_report_thai_2").val() 
	                     				};
	                    data.submit();
	                }
	        },
	       
		        url: 'reportUpload/upload/${template_msId}/2/0',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
			if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/2/0",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){ 
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_thai_2_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_thai_2_attached').html(data.filename);
	   					 $('#template_file_thai_2_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
	            	var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_thai_2_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_thai_2_attached').html(data.result.filename);
					 $('#template_file_thai_2_attached').attr('style','cursor: pointer;');
	            }
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response);
					 
					
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
	  
	        
	  $('#template_file_eng_2').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	              
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	data.formData= {
	        	        	reportName: $("#template_report_eng_2").val() 
	                };
	                    data.submit();
	                }
	        },
	        
		        url: 'reportUpload/upload/${template_msId}/2/1',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
				if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/2/1",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){  
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_eng_2_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_eng_2_attached').html(data.filename);
	   					 $('#template_file_eng_2_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response); 
					var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_eng_2_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_eng_2_attached').html(data.result.filename);
					 $('#template_file_eng_2_attached').attr('style','cursor: pointer;');
	            }
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
	  
	// button3
	  $('#template_file_thai_3').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	              
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	 data.formData={
	         	        	reportName: $("#template_report_thai_3").val() 
	                   };
	                    data.submit();
	                }
	        },
	       
		        url: 'reportUpload/upload/${template_msId}/3/0',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
				if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/3/0",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){  
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_thai_3_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_thai_3_attached').html(data.filename);
	   					 $('#template_file_thai_3_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
	            	var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_thai_3_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_thai_3_attached').html(data.result.filename);
					 $('#template_file_thai_3_attached').attr('style','cursor: pointer;');
	            }
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response);
					 
					
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
	  
	        
	  $('#template_file_eng_3').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	              
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	 data.formData= {
	         	        	reportName: $("#template_report_eng_3").val() 
	                 };
	                    data.submit();
	                }
	        },
	       
		        url: 'reportUpload/upload/${template_msId}/3/1',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
				if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/3/1",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){  
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_eng_3_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_eng_3_attached').html(data.filename);
	   					 $('#template_file_eng_3_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
	            	var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_eng_3_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_eng_3_attached').html(data.result.filename);
					 $('#template_file_eng_3_attached').attr('style','cursor: pointer;');
	            }
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response); 
					
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
	  
	// button4
	  $('#template_file_thai_4').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	              
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	 data.formData= {
	         	        	reportName: $("#template_report_thai_4").val() 
	                   };
	                    data.submit();
	                }
	        },
	       
		        url: 'reportUpload/upload/${template_msId}/4/0',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
			if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/4/0",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){  
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_thai_4_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_thai_4_attached').html(data.filename);
	   					 $('#template_file_thai_4_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response);
					 
					var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_thai_4_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_thai_4_attached').html(data.result.filename);
					 $('#template_file_thai_4_attached').attr('style','cursor: pointer;');
	            }
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
	  
	        
	  $('#template_file_eng_4').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	              
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	 data.formData= {
	         	        	reportName: $("#template_report_eng_4").val() 
	                 };
	                    data.submit();
	                }
	        },
	       
		        url: 'reportUpload/upload/${template_msId}/4/1',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/4/1",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){  
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_eng_4_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_eng_4_attached').html(data.filename);
	   					 $('#template_file_eng_4_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response); 
					var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_eng_4_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_eng_4_attached').html(data.result.filename);
					 $('#template_file_eng_4_attached').attr('style','cursor: pointer;');
	            }
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
	  
	// button5
	  $('#template_file_thai_5').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	              
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	  data.formData= {
	          	        	reportName: $("#template_report_thai_5").val() 
	                    };
	                    data.submit();
	                }
	        },
	      
		        url: 'reportUpload/upload/${template_msId}/5/0',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/5/0",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){  
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_thai_5_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_thai_5_attached').html(data.filename);
	   					 $('#template_file_thai_5_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response);
					 
					var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_thai_5_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_thai_5_attached').html(data.result.filename);
					 $('#template_file_thai_5_attached').attr('style','cursor: pointer;');
	            }
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
	  
	        
	  $('#template_file_eng_5').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	              
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	 data.formData= {
	         	        	reportName: $("#template_report_eng_5").val() 
	                 };
	                    data.submit();
	                }
	        },
	       
		        url: 'reportUpload/upload/${template_msId}/5/1',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/5/1",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){  
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_eng_5_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_eng_5_attached').html(data.filename);
	   					 $('#template_file_eng_5_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response); 
					var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_eng_5_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_eng_5_attached').html(data.result.filename);
					 $('#template_file_eng_5_attached').attr('style','cursor: pointer;');
	            }
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
	  
	// button6
	  $('#template_file_thai_6').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	              
	               
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	 data.formData= {
	         	        	reportName: $("#template_report_thai_6").val() 
	                	 };
	                    data.submit();
	                }
	        },
	       
		        url: 'reportUpload/upload/${template_msId}/6/0',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/6/0",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){  
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_thai_6_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_thai_6_attached').html(data.filename);
	   					 $('#template_file_thai_6_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response);
					 
					var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_thai_6_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_thai_6_attached').html(data.result.filename);
					 $('#template_file_thai_6_attached').attr('style','cursor: pointer;');
	            }
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
	  
	        
	  $('#template_file_eng_6').fileupload({
	        add: function(e, data) {
	                var uploadErrors = [];
	                var acceptFileTypes ;// /^image\/(gif|jpe?g|png)$/i;
	                <c:if test="${missSery.msExporting=='0'}">
	                acceptFileTypes=/(\.|\/)(jasper)$/i;
	               if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
	            	   uploadErrors.push('Error: only jasper are allowed');
					
					}  
					</c:if>
					<c:if test="${missSery.msExporting=='1'}"> 
					 acceptFileTypes=/(\.|\/)(xls|XLS|xlsx|XLSX)$/i;
					 if(data.originalFiles[0]['name'].length>0 && !acceptFileTypes.test(data.originalFiles[0]['name'])) {
						 uploadErrors.push('Error: only xls are allowed');
					}  	
					</c:if>
	               
	                if(uploadErrors.length > 0) {
	                    alert(uploadErrors.join("\n"));
	                } else {
	                	 data.formData= {
	         	        	reportName: $("#template_report_eng_6").val() 
	                    };
	                    data.submit();
	                }
	        },
	       
		        url: 'reportUpload/upload/${template_msId}/6/1',
		        dataType: 'json', 
		        autoUpload: false, 
		        done: function (e, data) { 
		         var ua = window.navigator.userAgent;
	            var msie = ua.indexOf("MSIE ");
if (true)   {   // If Internet Explorer, return version number{
	            	
	            	$.ajax({
	          		  type: "get",
	          		  url: "ajax/getMissFile/template/${template_msId}/6/1",
	          		  cache: false
	          		 // data: { name: "John", location: "Boston" }
	          		}).done(function( data ) {
	          			if(data!=null){  
	          				var path_file='getFileAttached("getfile/template/${template_msId}/'+data.hotlink+'")'; 
	   					 $('#template_file_eng_6_attached').attr('onclick',path_file);
	   					 //alert(file) 
	   					 $('#template_file_eng_6_attached').html(data.filename);
	   					 $('#template_file_eng_6_attached').attr('style','cursor: pointer;');
	          			  }
	          		});
	            }else{
					// $("#candidate_photo").attr("src","getfile/candidateImg/${candidateForm.missCandidate.mcaId}/"+data.result.hotlink);
					//var obj = jQuery.parseJSON(response); 
					var path_file='getFileAttached("getfile/template/${template_msId}/'+data.result.hotlink+'")'; 
					 $('#template_file_eng_6_attached').attr('onclick',path_file);
					 //alert(file) 
					 $('#template_file_eng_6_attached').html(data.result.filename);
					 $('#template_file_eng_6_attached').attr('style','cursor: pointer;');
	            }
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
	  
});
function confirmDelete(id,order){
	$( "#dialog-confirm-template-Delete" ).dialog({
		/* height: 140, */
		modal: true,
		buttons: {
			"Yes": function() { 
				$( this ).dialog( "close" );
				//doQuestionAction(mode,id);
				
				$.get("series/delete/templateSection/"+id+"/"+order+"",function(data) {
					  appendContentWithId(data,"_templateElement");
				});
			},
			"No": function() {
				$( this ).dialog( "close" );
				return false;
			}
		}
	});
}
function updateReportName(id,order,lang){
	
	$("#mraReportName_section").val($("#template_report_"+lang+"_"+order).val());
	//alert($("#mraReportName_section").val());
	$("#msId_section").val(id);
	$("#msOrder_section").val(order);
	var lang_str="0";
	if(lang=='eng')
		lang_str="1";
	$("#mraLang_section").val(lang_str);
	/* $.get("series/update/templateSection/"+id+"/"+order+"/"+$("#mraReportName_section").val()+"/"+lang_str, function(data) {
	      // appendContent(data);
	}); */
	 
	$.post("series/update/templateSection", $("#seriesManagementSectionForm").serialize(),function(data) {
	      // appendContent(data);
	      alert("Update Success.");
	});
} 
</script>
<div id="dialog-confirm-template-Delete" title="Delete Template" style="display: none;background: ('images/ui-bg_highlight-soft_75_cccccc_1x100.png') repeat-x scroll 50% 50% rgb(204, 204, 204)">
	Are you sure you want to delete Template ?
</div>
<div style="display: none"> 
  <form  id="seriesManagementSectionForm" name="seriesManagementSectionForm" method="post" action="">
  	<input type="hidden" id="mraReportName_section" name="mraReportName_section"/>
  	<input type="hidden" id="msId_section" name="msId_section"/>
  	<input type="hidden" id="msOrder_section" name="msOrder_section"/>
  	<input type="hidden" id="mraLang_section" name="mraLang_section"/>
  	 
  </form>
</div>       
       <table  border="0" width="100%" style="font-size: 13px">
	    					<tr>
	    					<td align="left" width="100%">
	    					<strong>Template File</strong>
	    					</td>
	    					</tr>
	    					</table>
	    					<table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
        	<thead>
          		<tr>
            		<th width="6%"><div class="th_class">#</div></th> 
            		<th width="45%"><div class="th_class">Thai</div></th>
            		<th width="45%"><div class="th_class">English</div></th>
            		 <th width="4%"><div class="th_class"></div></th>        
            		       		        		 
          		</tr>
        	</thead>
        	<tbody>
          	<tr>
            	<td>1</td> 
            	 <td>
	<!-- private String mraFileName;
 
	private String mraHotlink;
 
	private String mraPath;
 
	private String mraReportName -->
            	 <div><input type="text" id="template_report_thai_1" value="${templateList[0][0].mraReportName}">&nbsp;&nbsp;<i onclick="updateReportName('${template_msId}','1','thai')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            	<%-- <a class="btn" id="template_file_thai_1" ><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            	 --%> 
            	 <span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_thai_1" type="file" name="userfile" multiple>
    </span>				 
       <%-- 
            	 <c:if test="${templateList[0][0].mraFileName!=null}">
            	  --%>
            		<span id="template_file_thai_1_attached" style="cursor: pointer;"  
            		 onclick="getFileAttached('reportUpload/getfile/${template_msId}/1/0/${templateList[0][0].mraHotlink}')">
            		 ${templateList[0][0].mraFileName}
	    			</span>
	    			<span  id="template_file_thai_1_attached_delete">
	    			</span>
	    		 <%--  </c:if>  --%>
	    		   </td>
            	<td>
            	<div><input type="text" id="template_report_eng_1" value="${templateList[0][1].mraReportName}">&nbsp;&nbsp;<i onclick="updateReportName('${template_msId}','1','eng')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            	<%-- <a class="btn" id="template_file_eng_1"><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            	--%>
            	<span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_eng_1" type="file" name="userfile" multiple>
    </span>		
            	<%-- <c:if test="${templateList[0][1].mraFileName!=null}">  --%>
            		<span id="template_file_eng_1_attached" style="cursor: pointer;" 
            		onclick="getFileAttached('reportUpload/getfile/${template_msId}/1/1/${templateList[0][1].mraHotlink}')">
            		${templateList[0][1].mraFileName}
	    				</span><span id="template_file_eng_1_attached_delete">
	    				</span>
	    		<%-- </c:if>  --%>		
	    		</td> 
	    				<td><i title="Delete" onclick="confirmDelete('${template_msId}','1')" style="cursor: pointer;" class="icon-trash"></i></td>
          	</tr>
          	<tr>
            	<td>2</td>
            	 <td>
            	 <div><input type="text" id="template_report_thai_2" value="${templateList[1][0].mraReportName}">&nbsp;&nbsp;<i  onclick="updateReportName('${template_msId}','2','thai')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            		<%-- <a class="btn" id="template_file_thai_2" ><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            		--%>
            		<span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_thai_2" type="file" name="userfile" multiple>
    </span>		
            	<%-- 
            	<c:if test="${templateList[1][0].mraFileName!=null}">
            	 --%>
            		<span id="template_file_thai_2_attached" style="cursor: pointer;" 
            			onclick="getFileAttached('reportUpload/getfile/${template_msId}/2/0/${templateList[1][0].mraHotlink}')">
            				${templateList[1][0].mraFileName}
	    				</span><span id="template_file_thai_2_attached_delete">
	    				</span>
	    				<%-- 
	    				</c:if>
	    				 --%>
	    				</td>
            	<td>
            	 <div><input type="text" id="template_report_eng_2" value="${templateList[1][1].mraReportName}">&nbsp;&nbsp;<i  onclick="updateReportName('${template_msId}','2','eng')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            	<%-- <a class="btn" id="template_file_eng_2"><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            	--%>
            	<span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_eng_2" type="file" name="userfile" multiple>
    </span>		
            		<%-- <c:if test="${templateList[1][1].mraFileName!=null}">  --%>
            			<span id="template_file_eng_2_attached"  style="cursor: pointer;" 
            			onclick="getFileAttached('reportUpload/getfile/${template_msId}/2/1/${templateList[1][1].mraHotlink}')">
            			${templateList[1][1].mraFileName}
	    				</span><span id="template_file_eng_2_attached_delete">
	    				</span>
	    				<%-- </c:if>  --%>
	    				</td> 
	    				<td><i title="Delete" onclick="confirmDelete('${template_msId}','2')" style="cursor: pointer;" class="icon-trash"></i></td>
          	</tr>
          	<tr>
            	<td>3</td> 
            	<td>
            	<div><input type="text" id="template_report_thai_3" value="${templateList[2][0].mraReportName}">&nbsp;&nbsp;<i  onclick="updateReportName('${template_msId}','3','thai')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            	<%-- <a class="btn" id="template_file_thai_3" ><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            	--%>
            	<span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_thai_3" type="file" name="userfile" multiple>
    </span>		
            	<%-- <c:if test="${templateList[2][0].mraFileName!=null}">  --%>	
            	<span id="template_file_thai_3_attached" style="cursor: pointer;"
            	 onclick="getFileAttached('reportUpload/getfile/${template_msId}/3/0/${templateList[2][0].mraHotlink}')">
            	 ${templateList[2][0].mraFileName}
	    				</span><span id="template_file_thai_3_attached_delete">
	    				</span>
	    			<%-- </c:if>  --%>	
	    			
	    				</td>
	    				
            	<td>
            	<div><input type="text" id="template_report_eng_3" value="${templateList[2][1].mraReportName}">&nbsp;&nbsp;<i  onclick="updateReportName('${template_msId}','3','eng')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            	<%-- <a class="btn" id="template_file_eng_3"><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            	--%>
            	<span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_eng_3" type="file" name="userfile" multiple>
    </span>		
            	<c:if test="${templateList[2][1].mraFileName!=null}">	
            		
	    		</c:if>
	    		<span id="template_file_eng_3_attached" style="cursor: pointer;"
            		 onclick="getFileAttached('reportUpload/getfile/${template_msId}/3/1/${templateList[2][1].mraHotlink}')">
            			${templateList[2][1].mraFileName}
	    				</span><span id="template_file_eng_3_attached_delete">
	    				</span>		
	    			</td>
	    			<td><i title="Delete" onclick="confirmDelete('${template_msId}','3')" style="cursor: pointer;" class="icon-trash"></i></td>
          	</tr>
          	<tr>
            	<td>4</td>
            	
            	<td>
            	<div><input type="text" id="template_report_thai_4" value="${templateList[3][0].mraReportName}">&nbsp;&nbsp;<i  onclick="updateReportName('${template_msId}','4','thai')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            	<%-- <a class="btn" id="template_file_thai_4" ><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            	--%>
            	<span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_thai_4" type="file" name="userfile" multiple>
    </span>		
            	<c:if test="${templateList[3][0].mraFileName!=null}">	
            	
	    			</c:if>	
	    			<span id="template_file_thai_4_attached" style="cursor: pointer;"
            	onclick="getFileAttached('reportUpload/getfile/${template_msId}/4/0/${templateList[3][0].mraHotlink}')">
            	${templateList[3][0].mraFileName}
	    				</span><span id="template_file_thai_4_attached_delete">
	    				</span>
	    				</td>
            	<td>
            	<div><input type="text" id="template_report_eng_4" value="${templateList[3][1].mraReportName}">&nbsp;&nbsp;<i  onclick="updateReportName('${template_msId}','4','eng')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            	<%-- <a class="btn" id="template_file_eng_4"><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            	--%>
            	<span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_eng_4" type="file" name="userfile" multiple>
    </span>		
            	<c:if test="${templateList[3][1].mraFileName!=null}">	
            		
	    				</c:if>
	    				<span id="template_file_eng_4_attached"  style="cursor: pointer;"
            		onclick="getFileAttached('reportUpload/getfile/${template_msId}/4/1/${templateList[3][1].mraHotlink}')">
            		${templateList[3][1].mraFileName}
	    				</span><span id="template_file_eng_4_attached_delete">
	    				</span>
	    				</td> 
	    				<td><i title="Delete" onclick="confirmDelete('${template_msId}','4')" style="cursor: pointer;" class="icon-trash"></i></td>
          	</tr>
          	<tr>
            	<td>5</td>
            	
            	<td>
            	<div><input type="text" id="template_report_thai_5" value="${templateList[4][0].mraReportName}">&nbsp;&nbsp;<i  onclick="updateReportName('${template_msId}','5','thai')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            	<%-- <a class="btn" id="template_file_thai_5" ><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            	--%>
            	<span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_thai_5" type="file" name="userfile" multiple>
    </span>		
            	<c:if test="${templateList[4][0].mraFileName!=null}">	
            		
	    				</c:if>
	    				<span id="template_file_thai_5_attached" style="cursor: pointer;"
           		 	onclick="getFileAttached('reportUpload/getfile/${template_msId}/5/0/${templateList[4][0].mraHotlink}')">
            		${templateList[4][0].mraFileName}
	    				</span><span id="template_file_thai_5_attached_delete">
	    				</span>
	    				</td>
            	<td>
            	<div><input type="text" id="template_report_eng_5" value="${templateList[4][1].mraReportName}">&nbsp;&nbsp;<i  onclick="updateReportName('${template_msId}','5','eng')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            	<%-- <a class="btn" id="template_file_eng_5"><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            	--%>
            	<span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_eng_5" type="file" name="userfile" multiple>
    </span>		
            	<c:if test="${templateList[4][1].mraFileName!=null}">
            	
	    				</c:if>
	    				<span id="template_file_eng_5_attached" style="cursor: pointer;"
            	onclick="getFileAttached('reportUpload/getfile/${template_msId}/5/1/${templateList[4][1].mraHotlink}')">
            	${templateList[4][1].mraFileName}
	    				</span><span id="template_file_eng_5_attached_delete">
	    				</span>
	    				</td>
	    				<td><i title="Delete" onclick="confirmDelete('${template_msId}','5')" style="cursor: pointer;" class="icon-trash"></i></td>
          	</tr>
          	<tr>
            	<td>6</td> 
            	<td>
            	<div><input type="text" id="template_report_thai_6" value="${templateList[5][0].mraReportName}">&nbsp;&nbsp;<i  onclick="updateReportName('${template_msId}','6','thai')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            	<%-- <a class="btn" id="template_file_thai_6" ><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            	--%>
            	<span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_thai_6" type="file" name="userfile" multiple>
    </span>		
            	<c:if test="${templateList[5][0].mraFileName!=null}"> 
            	
	    				</c:if>
	    				<span id="template_file_thai_6_attached" style="cursor: pointer;"
            	onclick="getFileAttached('reportUpload/getfile/${template_msId}/6/0/${templateList[5][0].mraHotlink}')">
            	${templateList[5][0].mraFileName}
	    				</span><span id="template_file_thai_6_attached_delete">
	    				</span>
	    				</td>
            	<td>
            	<div><input type="text" id="template_report_eng_6" value="${templateList[5][1].mraReportName}">&nbsp;&nbsp;<i  onclick="updateReportName('${template_msId}','6','eng')" title="Update Report name" style="cursor: pointer;" class="icon-refresh"></i></div>
            	<%-- <a class="btn" id="template_file_eng_6"><i class="icon-file"></i>&nbsp;<span style="">Upload</span></a>
            --%>
            <span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Upload</span>
        <!-- The file input field used as target for the file upload widget -->
       	 <input id="template_file_eng_6" type="file" name="userfile" multiple>
    </span>		
            	<c:if test="${templateList[5][1].mraFileName!=null}">
            	
	    			</c:if>
	    			<span id="template_file_eng_6_attached" style="cursor: pointer;"
            	onclick="getFileAttached('reportUpload/getfile/${template_msId}/5/1/${templateList[5][1].mraHotlink}')">
            	${templateList[5][1].mraFileName}
	    				</span><span id="template_file_eng_6_attached_delete">
	    				</span>
	    			</td>
	    				<td><i title="Delete" onclick="confirmDelete('${template_msId}','6')" style="cursor: pointer;" class="icon-trash"></i></td>
          	</tr>
        	</tbody>
      </table>