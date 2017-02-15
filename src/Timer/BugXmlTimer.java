package Timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class BugXmlTimer {
	
	public   Timer timer;
	 
	  
   public void timerStart() throws ParseException{
	       timer = new Timer();
	       long daySpan=24*60*60*1000;
	     // 规定的每天时间15:33:30运行
	       final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd '21:02:00'");

	     //首次运行时间
	       Date startTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
	       
 //如果今天的已经过了 首次运行时间就改为明天
	       if(System.currentTimeMillis()>startTime.getTime()){
	    	   startTime=new Date(startTime.getTime()+daySpan);
	       }
  
	  
	        System.out.println("before task");
	        timer.scheduleAtFixedRate(new BugXmlTimerTask(), startTime, daySpan);
	//立刻执行，然后每隔30s执行一次
	     //   timer.schedule(new BugXmlTimerTask(), midnightDate,3000);
	      
   
	  }
   
	  public void timerStop(){
     if(timer!=null) 
	         timer.cancel();
	   }
	
	  public static void main(String[] args) throws ParseException{
	       BugXmlTimer myTimer=new BugXmlTimer();
	        
	
	           // TODO Auto-generated method stub
	        myTimer.timerStart();
	        
	   }


}
