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
		
		/* mtrId="20";
		 mdc_key="chart1";*/
		 try {
			 long start =System.currentTimeMillis();
			 String fileGen=genToken();
			 Runtime rt = Runtime.getRuntime();
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
				  proc = rt.exec(new String[]{"/usr/local/data/HttpServer/apache2/htdocs/wkhtmltoimage-amd64","--javascript-delay","3000",  
						  "--quality","75","--crop-w",width,"--crop-h",height,"--format","jpg","--use-xserver","http://localhost:8080/MISSProcessImage/chart?key="+mdc_key+"&width="+width+"&height="+height+"&lang="+lang+"&mtrId="+mtrId+"","/tmp/"+fileGen+".jpg"});
				/*  proc = rt.exec(new String[]{"/opt/apache2/htdocs/fcimg/bin/wkhtmltoimage-i386","--javascript-delay","5000",  
						  "--quality","75","--crop-w",width,"--crop-h",height,"--format","jpg","http://203.150.20.37/MISSProcessImage/chart?key="+mdc_key+"&width="+width+"&height="+height+"&lang="+lang+"&mtrId="+mtrId+"","/tmp/"+fileGen+".jpg"});*/
				  System.out.println("cmd chart\nhttp://localhost:8080/MISSProcessImage/chart?key="+mdc_key+"&width="+width+"&height="+height+"&lang="+lang+"&mtrId="+mtrId+" /tmp/"+fileGen+".jpg");
			  }else	  if(page!=null && page.length()>0){
				  // 1074
				  String speed="1000";
				  if(page.equals("workwheel_1"))
						  speed="3000";
				 // cmd="/usr/local/data/HttpServer/apache2/htdocs/wkhtmltoimage-amd64 --javascript-delay 5000 --quality 75 --crop-w "+width+" --crop-h "+height+" --format jpg  --use-xserver http://localhost:8080/MISSProcessImage/render?mtrId="+mtrId+"_"+mdc_key+"_"+width+"_"+height+" /tmp/"+fileGen+".jpg";
				 proc = rt.exec(new String[]{"/usr/local/data/HttpServer/apache2/htdocs/wkhtmltoimage-amd64","--javascript-delay",speed,
						  "--quality","75","--crop-w",width,"--format","jpg","--use-xserver","http://localhost:8080/MISSProcessImage/eptplus?page="+page+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"","/tmp/"+fileGen+".jpg"}); 
				 /* proc = rt.exec(new String[]{"/opt/apache2/htdocs/fcimg/bin/wkhtmltoimage-i386","--javascript-delay",speed,  
						  "--quality","75","--crop-w",width,"--format","jpg","http://203.150.20.37/MISSProcessImage/eptplus?page="+page+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"","/tmp/"+fileGen+".jpg"});*/ 
			      //  System.out.println("chatchai debug =>"+"http://localhost:8080/MISSProcessImage/eptplus?page="+page+"&type="+type+"&lang="+lang+"&mtrId="+mtrId+"");
			  }else{
				    proc = rt.exec(cmd);
			  }
			  System.out.println("cmd\n"+cmd);
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
					 th.co.aoe.makedev.missconsult.thread.StreamGobbler(proc.getErrorStream(), "OUTPUT");
	                
	            // kick them off
	           // errorGobbler.start();
	            outputGobbler.start();
	                                    
	            // any error???
	            try {
					int exitVal = proc.waitFor();
					
					//System.out.println("exitVal="+exitVal);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			long end =System.currentTimeMillis();
			//System.out.println(end-start);
			File file = new File("/tmp/"+fileGen+".jpg");

			boolean fileExists = file.exists();
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
