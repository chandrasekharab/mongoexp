package chan.logging;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import chan.logging.Log.LEVEL;


public class AsyncLogger implements Logger, Runnable{

	private Queue<Log> messages = new ConcurrentLinkedQueue<>();
	private Appender appender;

	public AsyncLogger() {
		this.appender = new ConsoleAppender();
	}

	public AsyncLogger(Appender appender) {
		this.appender = appender;
	}

	@Override
	public void error(Object aError) {
		messages.add(new Log(LEVEL.ERROR, aError));
	}

	@Override
	public void info(Object aInfo) {
		messages.add(new Log(LEVEL.INFO, aInfo));
	}

	@Override
	public void debug(Object aDebug) {
		messages.add(new Log(LEVEL.DEBUG, aDebug));

	}

	@Override
	public void fatal(Object aFatal) {
		messages.add(new Log(LEVEL.FATAL, aFatal));

	}

	@Override
	public void log(LEVEL aLevel, Object aMessage) {
		messages.add(new Log(aLevel, aMessage));

	}

	@Override
	public void run() {
		while (true) {
			if (!this.messages.isEmpty()) {
				Log log = this.messages.remove();
				this.appender.write(log);
			}
		}

	}

}
