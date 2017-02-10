package Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyTimerListener implements ServletContextListener{
	private BugXmlTimer  mytimer = new BugXmlTimer  ();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	     mytimer.timerStop();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		mytimer.timerStart();
	}

}
