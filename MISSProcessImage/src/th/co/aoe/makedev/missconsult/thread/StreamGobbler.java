package th.co.aoe.makedev.missconsult.thread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamGobbler extends Thread
{
    InputStream is;
    String type;
    
  public  StreamGobbler(InputStream is, String type)
    {
        this.is = is;
        this.type = type;
    }
    
    public void run()
    {
    	 InputStreamReader isr =null;
    	 BufferedReader br =null;
        try
        {
             isr = new InputStreamReader(is);
             br = new BufferedReader(isr);
            String line=null;
            
            while ( (line = br.readLine()) != null)
                System.out.println(type + ">" + line);    
            } catch (IOException ioe)
              {
                ioe.printStackTrace();  
              }catch (Exception e) {
				// TODO: handle exception
            	  e.printStackTrace();
			}
         
        finally{
        	/*if(br!=null)
				try {
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	 if(is!=null)
				try {
					is.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	 if(isr!=null)
				try {
					isr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
        }
    }
}