package Timer;

import java.util.TimerTask;

import jdbc.Brghsql;

public class BugXmlTimerTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Brghsql brghsql=new Brghsql();
		brghsql.zdgh();
		System.out.print("run task");
	}

}
