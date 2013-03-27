package th.co.aoe.makedev.missconsult.renderimage;

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
 * Servlet implementation class Export
 */
@WebServlet("/compareTest")
public class CompareTest  extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompareTest() {
        super();
        // TODO Auto-generated constructor stub
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
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//"http://203.150.20.37/MISSProcessImage/process?mtrId="+$P{mtrId}.toString()+"&key=chart1&w=800&h=400&chart=bar"
		
		 String mcaSeries= request.getParameter("mcaSeries");  
		 String mtrIds= request.getParameter("mtrIds");  
		/* mtrId="20";
		 mdc_key="chart1";*/
		 try {
			 long start =System.currentTimeMillis();
			 String fileGen=genToken();
			 Runtime rt = Runtime.getRuntime(); 
	       
			// String cmd="/usr/local/data/HttpServer/apache2/htdocs/wkhtmltoimage-amd64 --javascript-delay 3000 --quality 75 --crop-w "+width+" --crop-h "+height+" --format jpg  --use-xserver http://localhost:8080/MISSProcessImage/render?mtrId="+mtrId+"_"+mdc_key+"_"+chart+"_"+lang+"_"+width+"_"+height+" /tmp/"+fileGen+".jpg";
	         //String cmd=""; 
			  Process proc=null;
			  if(mcaSeries!=null && mcaSeries.length()>0 && mtrIds!=null && mtrIds.length()>0){
				   //xvfb-run --server-args="-screen 0, 1024x768x24"
				  proc = rt.exec(new String[]{"xvfb-run","/opt/apache2/htdocs/fcimg/bin/wkhtmltoimage-amd64","--use-xserver","--javascript-delay","5000",  
						  "--quality","100","--format","jpg","http://localhost:8080/MISSExamBackOffice/reportExport/compareTest/"+mcaSeries+"/"+mtrIds+"","/tmp/"+fileGen+".jpg"});
				
			  }/*else{
				    proc = rt.exec(cmd);
			  }*/
			 
			 th.co.aoe.makedev.missconsult.thread.StreamGobbler outputGobbler = new 
					 th.co.aoe.makedev.missconsult.thread.StreamGobbler(proc.getErrorStream(), "OUTPUT");
	       
	            outputGobbler.start();
	                                    
	            // any error???
	            try {
					int exitVal = proc.waitFor();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			long end =System.currentTimeMillis();
			File file = new File("/tmp/"+fileGen+".jpg");

			boolean fileExists = file.exists();
			if(fileExists){
				InputStream in = null;
			      OutputStream out=null;
			      response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
			      response.setHeader("Content-disposition", "attachment; filename=compareTest.jpg");
			        
			        
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
	 private String genToken(){
	  		StringBuffer sb = new StringBuffer();
	  	    for (int i = 36; i > 0; i -= 12) {
	  	      int n = Math.min(12, Math.abs(i));
	  	      sb.append(org.apache.commons.lang3.StringUtils.leftPad(Long.toString(Math.round(Math.random() * Math.pow(36, n)), 36), n, '0'));
	  	    }
	  	    return sb.toString();
	   }


}
