package Timer;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TranslateTimer {
	

    private final ScheduledExecutorService scheduler = Executors  
            .newScheduledThreadPool(1);  
    Translator tranChinese = new Translator(); //操作实现类，我这里为翻译类  
  
   public void executeTranslateTimer() {  
  
        Runnable task = new Runnable() {  
 
            public void run() {  
                // 翻译  
               tranChinese.translateGo(); //调用操作实现方法  
                 
               System.out.println("--------------翻译：" + new Date().getTime()  
                       + "------------------");  
          }  
        };  
       if (scheduler.isShutdown()) {  
            scheduler = Executors.newScheduledThreadPool(1);  
          scheduler.scheduleAtFixedRate(task, 10, 30, TimeUnit.SECONDS);  
      } else {  
           scheduler.scheduleAtFixedRate(task, 10, 30, TimeUnit.SECONDS); // 延迟10秒，每隔30秒翻译一次  
       }  
    }  

 //停止任务，不再提交新任务，已提交任务会继续执行以致完成  
  public void stop() {  
       scheduler.shutdown();  
 } 


}
