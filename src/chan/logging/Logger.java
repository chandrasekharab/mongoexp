package chan.logging;

import chan.logging.Log.LEVEL;


public interface Logger {

	/**
	 * 
	 * @param error
	 */
	public void error(Object error);

	/**
	 * 
	 * @param info
	 */
	public void info(Object info);

	/**
	 * 
	 * @param debug
	 */
	public void debug(Object debug);

	/**
	 * 
	 * @param fatal
	 */
	public void fatal(Object fatal);

	/**
	 * 
	 * @param level
	 * @param message
	 */
	public void log(LEVEL level, Object message);

}
