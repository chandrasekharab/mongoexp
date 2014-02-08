package chan.logging;

import org.junit.Test;

public class TestLogger {

	@Test
	public void testMongoAppender() {
		/*Logger logger = LogManager.getLoggerInstance();		
		logger.debug("testing");*/
		
		Appender appender = new MongoAppender();
		appender.write("teststt");
	}
}
