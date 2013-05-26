package th.co.aoe.makedev.missconsult.processimage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessimageServlet
 */
@WebServlet("/process")
public class ProcessimageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final  Runtime rt = Runtime.getRuntime();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessimageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("doProcess");
		//"http://203.150.20.37/MISSProcessImage/process?mtrId="+$P{mtrId}.toString()+"&key=chart1&w=800&h=400&chart=bar"
		
		 String mtrId= request.getParameter("mtrId"); 
		 String mdc_key=request.getParameter("key");
		 String chart=request.getParameter("chart");
		 String width=request.getParameter("w");
		 String height=request.getParameter("h");
		 String page=request.getParameter("page");
		 String type=request.getParameter("type");
		 String lang=request.getParameter("lang");
		 String report=request.getParameter("report");
		 String servletname="eptplus";
		 if(width==null){
			 width="800";
		 }
		 
		/* mtrId="20";
		 mdc_key="chart1";*/
		 try {
			 long start =System.currentTimeMillis();
			 String fileGen=genToken();
			
			// ProcessBuilder b = new ProcessBuilder(" ");
			 
			 /*http://203.150.20.37/MISSProcessImage/process?page=workwheel_1&type=ept_plus&w=1090&lang=1&mtrId=61
			 http://203.150.20.37/MISSProcessImage/process?mtrId="+$P{mtrId}.toString()+"&key=chart1&w=800&h=400&lang=1
			 http://203.150.20.37/MISSProcessImage/process?page=evalOfbehavioral_1&type=ept_plus&w=1090&lang=1&mtrId=61&lang=1
*/	           /* System.out.println("Execing " + cmd[0] + " " + cmd[1] 
	            * http://localhost:8080/MISSProcessImage/render?mtrId=20&key=chart1
	                               + " " + cmd[2]);*/
	       
			 String cmd="/usr/local/data/HttpServer/apache2/htdocs/wkhtmltoimage-amd64 --javascript-delay 3000 --quality 75 --crop-w "+width+" --crop-h "+height+" --format jpg  --use-xserver http://localhost:8080/MISSProcessImage/render?mtrId="+mtrId+"_"+mdc_key+"_"+chart+"_"+lang+"_"+width+"_"+height+" /tmp/"+fileGen+".jpg";
	         //String cmd="";
			  Process proc=null;
			  if(mdc_key!=null && mdc_key.length()>0){
				  if(lang==null)
					  lang="1";
				//  xvfb-run --server-args="-screen 0, 1024x768x24" ./wkhtmltoimage-amd64 --use-xserver --javascript-delay 2000 --window-status Done --enable-plugins http://www.hulu.com hulu.jpg
					 
				  // server
				  proc = rt.exec(new String[]{"/opt/tomcat/apache-tomcat-7.0.34/xvfb-run-aoe.sh","/opt/apache2/htdocs/fcimg/bin/wkhtmltoimage-amd64","--use-xserver","--javascript-delay","4000",  
						  "--quality","100","--crop-w",width,"--crop-h",height,"--format","jpg",
						  "--load-error-handling","ignore",
						 // "http://localhost:8080/MISSProcessImage/chart?key="+mdc_key+"&width="+width+"&height="+height+"&lang="+lang+"&mtrId="+mtrId+"","/tmp/"+fileGen+".jpg"});   
						  "http://203.150.20.37/MISSProcessImage/chart?key="+mdc_key+"&width="+width+"&height="+height+"&lang="+lang+"&mtrId="+mtrId+"","/tmp/"+fileGen+".jpg"});
				 
				/*  proc = rt.exec("xvfb-run /opt/apache2/htdocs/fcimg/bin/wkhtmltoimage-amd64 --use-xserver --javascript-delay 4000"+
						  " --quality 100 --crop-w "+width+" --crop-h "+height+" --format jpg \"http://localhost:8080/MISSProcessImage/chart?key="+mdc_key+"&width="+width+"&height="+height+"&lang="+lang+"&mtrId="+mtrId+"\" /tmp/"+fileGen+".jpg");
				  */
				  /*  proc = rt.exec(new String[]{"/opt/apache2/htdocs/fcimg/bin/wkhtmltoimage-i386","--javascript-delay","4000",  
						  "--quality","75","--crop-w",width,"--crop-h",height,"--format","jpg","http://203.150.20.37/MISSProcessImage/chart?key="+mdc_key+"&width="+width+"&height="+height+"&lang="+lang+"&mtrId="+mtrId+"","/tmp/"+fileGen+".jpg"});
						  */  
				//System.out.println("cmd chart 1");
				   
				System.out.println("cmd==> /opt/tomcat/apache-tomcat-7.0.34/xvfb-run-aoe.sh wkhtmltoimage-amd64 --use-xserver --javascript-delay  4000 --quality 100 --crop-w "+width+" --crop-h "+height+" -f jpg   \"http://203.150.20.37/MISSProcessImage/chart?key="+mdc_key+"&width="+width+"&height="+height+"&lang="+lang+"&mtrId="+mtrId+"\" /tmp/"+fileGen+".jpg");
				//proc=rt.exec("/opt/tomcat/apache-tomcat-7.0.34/xvfb-run-aoe.sh wkhtmltoimage-amd64 --use-xserver --javascript-delay  4000 --quality 100 --crop-w "+width+" --crop-h "+height+" -f jpg   \"http://203.150.20.37:8080/MISSProcessImage/chart?key="+mdc_key+"&width="+width+"&height="+height+"&lang="+lang+"&mtrId="+mtrId+"\" /tmp/"+fileGen+".jpg");
			  }else	  if(page!=null && page.length()>0){
				  // 1074
				  String speed="3000";
				  if(page.equals("workwheel_1") || page.indexOf("attitudeDetector_")!=-1)
						  speed="3000";
				  if(report!=null && report.length()>0)
					  servletname=report;
				  //server
				   proc = rt.exec(new String[]{"/opt/tomcat/apache-tomcat-7.0.34/xvfb-run-aoe.sh","wkhtmltoimage-amd64","--use-xserver","--javascript-delay",speed,
						  "--quality","100","--crop-w",width,"--format","jpg",
						 // "--load-error-handling","ignore",
						 // "http://localhost:8080/MISSProcessImage/"+servletname+"?page="+page+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"","/tmp/"+fileGen+".jpg"});
						  "http://203.150.20.37/MISSProcessImage/"+servletname+"?page="+page+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"","/tmp/"+fileGen+".jpg"});
				   
				/*  proc = rt.exec("/usr/bin/xvfb-run /opt/apache2/htdocs/fcimg/bin/wkhtmltoimage-amd64 --use-xserver --javascript-delay "+speed+
						  " --quality 100 --crop-w "+width+" --format jpg \"http://localhost:8080/MISSProcessImage/"+servletname+"?page="+page+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"\" /tmp/"+fileGen+".jpg"); 
				*/
				 
				 /* proc = rt.exec(new String[]{"/opt/apache2/htdocs/fcimg/bin/wkhtmltoimage-i386","--javascript-delay",speed,  
						  "--quality","75","--crop-w",width,"--format","jpg","http://203.150.20.37/MISSProcessImage/"+servletname+"?page="+page+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"","/tmp/"+fileGen+".jpg"}); */  
				  //System.out.println("cmd chart 2");
				  System.out.println("cmd2==> /opt/tomcat/apache-tomcat-7.0.34/xvfb-run-aoe.sh wkhtmltoimage-amd64 --use-xserver --javascript-delay "+speed+" --quality 100 --crop-w "+width+" -f jpg --username admin --password password  --load-error-handling ignore \"http://203.150.20.37/MISSProcessImage/"+servletname+"?page="+page+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"\" /tmp/"+fileGen+".jpg");
				  //proc=rt.exec("/usr/bin/xvfb-run /opt/apache2/htdocs/fcimg/bin/wkhtmltoimage-amd64 --use-xserver --javascript-delay "+speed+" --quality 100 --crop-w "+width+" --format jpg \"http://localhost:8080/MISSProcessImage/"+servletname+"?page="+page+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"\" /tmp/"+fileGen+".jpg");
				  //proc=rt.exec("/opt/tomcat/apache-tomcat-7.0.34/xvfb-run-aoe.sh wkhtmltoimage-amd64 --use-xserver --javascript-delay "+speed+" --quality 100 --crop-w "+width+" -f jpg --username admin --password password \"http://203.150.20.37/MISSProcessImage/"+servletname+"?page="+page+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"\" /tmp/"+fileGen+".jpg");
				 // proc=rt.exec("/opt/tomcat/apache-tomcat-7.0.34/xvfb-run-aoe.sh wkhtmltoimage-amd64 --use-xserver --javascript-delay "+speed+" --quality 100 --crop-w "+width+" -f jpg   \"http://203.150.20.37:8080/MISSProcessImage/"+servletname+"?page="+page+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"\" /tmp/"+fileGen+".jpg");
			  }else{
				    proc = rt.exec(cmd);
				    //System.out.println("cmd chart 3");
				    //System.out.println("cmd\n"+cmd);
			  }
			//  System.out.println("cmd\n"+cmd);
			// String cmd="/opt/apache2/htdocs/fcimg/bin/wkhtmltoimage-i386 --javascript-delay 1000 --quality 75 --format jpg  http://203.150.20.37:8080/TestFusion/ExportChartSamples/JavaScriptExport/ServerSideSimple.html /tmp/aoe2.jpg";
	        // ProcessBuilder pb = new ProcessBuilder(cmd);
	        // Map<String, String> env = pb.environment();
	        
	        // Process proc =	pb.start(); 
	            // any error message?
	           /* StreamGobbler errorGobbler = new 
	                StreamGobbler(proc.getErrorStream(), "ERROR");            
	            */
	            // any output?
			  th.co.aoe.makedev.missconsult.thread.StreamGobbler outputGobbler = new 
					 th.co.aoe.makedev.missconsult.thread.StreamGobbler(proc.getInputStream(), "OUTPUT");
	                
	            // kick them off
	           // errorGobbler.start();
	            outputGobbler.start();
	                                    
	            // any error???
	         // any error message?
	              th.co.aoe.makedev.missconsult.thread.StreamGobbler errorGobbler = new 
	            		th.co.aoe.makedev.missconsult.thread.StreamGobbler(proc.getErrorStream(), "ERROR");   
	            errorGobbler.start();  
	            try {
					int exitVal = proc.waitFor();
					
					System.out.println("exitVal="+exitVal);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			long end =System.currentTimeMillis();
			//System.out.println(end-start);
			File file = new File("/tmp/"+fileGen+".jpg");

			boolean fileExists = file.exists();
			System.out.println("filename ===>"+fileGen+".jpg , fileExists ===>"+fileExists);
			if(fileExists){
				InputStream in = null;
			      OutputStream out=null;
				try {
					out = response.getOutputStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			      InputStream stream  = null;
			      try {   
			    		  stream = new FileInputStream(file);
			    		 
					         in = new BufferedInputStream(stream);
			         while (true) {
			            int data = in.read();
			            if (data == -1) {
			               break;
			            }
			            out.write(data);
			         }
			      }catch (Exception e) {
			    	  e.printStackTrace();
					// TODO: handle exception
				 } finally {
			         if (in != null) {
			            try {
							in.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			         }
			         if (stream != null) {
			        	 try {
							stream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				         } 
			         if (out != null) {
			            try { 
							  out.flush();
						      out.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			         }
			         file.delete();
				 }
			    }
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}
	 private String genToken(){
	  		StringBuffer sb = new StringBuffer();
	  	    for (int i = 36; i > 0; i -= 12) {
	  	      int n = Math.min(12, Math.abs(i));
	  	      sb.append(org.apache.commons.lang3.StringUtils.leftPad(Long.toString(Math.round(Math.random() * Math.pow(36, n)), 36), n, '0'));
	  	    }
	  	    return sb.toString();
	   }
}
