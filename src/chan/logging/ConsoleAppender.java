package chan.logging;

public class ConsoleAppender implements Appender{

	@Override
	public void write(Object aData) {
		System.out.println(aData);
	}
}