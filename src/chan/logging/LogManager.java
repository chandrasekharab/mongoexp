package chan.logging;

public class LogManager {	
	private static Logger logger = null;

	public static Logger getLoggerInstance() {
		if (logger == null) {			
			synchronized (LogManager.class) {
				 if (logger == null) {
					 //logger = new AsyncLogger();
					 logger = new AsyncLogger(new MongoAppender());					 
					 AsyncLogger l = (AsyncLogger)logger;
					 Thread t = new Thread(l);
					 t.setPriority(Thread.MIN_PRIORITY);
					 t.start();
				 }
			}
		}
		return logger;
	}
}
