package Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class BugXmlTimer {
	
	public   Timer timer;
	 
	  
   public void timerStart(){
	       timer = new Timer();
	       Date datetime=new Date();
	       Date midnightDate=new Date();
	
	      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       
	    try {
	               
	        midnightDate = sdf2.parse(sdf1.format(datetime)+" 23:00:00");
	    } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	       
	       long in=midnightDate.getTime()-datetime.getTime();
	  
	        System.out.println("before task");
	//立刻执行，然后每隔30s执行一次
	        timer.schedule(new BugXmlTimerTask(), 0,30000);
	       
   
	  }
   
	  public void timerStop(){
     if(timer!=null) 
	         timer.cancel();
	   }
	
	  public static void main(String[] args){
	       BugXmlTimer myTimer=new BugXmlTimer();
	        
	
	           // TODO Auto-generated method stub
	        myTimer.timerStart();
	        
	   }


}
