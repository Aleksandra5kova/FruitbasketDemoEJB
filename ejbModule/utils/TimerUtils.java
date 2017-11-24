package utils;

import configuration.Config;

public class TimerUtils {
	
	public static final String INFO = "info";
	public static final String EXPRESSION = "expression";
	public static final String SEPARATOR = ".";
	
	private static final Config config = Config.getInstance();

	public static String getTimerInfo(String name) {
		String timerInfo = name + TimerUtils.SEPARATOR + TimerUtils.INFO;
		return config.getProperty(timerInfo);
	}

	public static String getTimerExpression(String name) {
		String timerExpression = name + TimerUtils.SEPARATOR + TimerUtils.EXPRESSION;
		return config.getProperty(timerExpression);
	}

}
