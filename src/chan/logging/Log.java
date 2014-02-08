package chan.logging;

public class Log {	
	private LEVEL level;
	private Object message;

	public enum LEVEL {
		ERROR, INFO, DEBUG, FATAL
	}

	public Log(LEVEL level) {
		this.level = level;
	}

	public Log(LEVEL level, Object message) {
		this.level = level;
		this.message = message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Object getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return this.level.name() + " : " + (String)this.message;
	}
}