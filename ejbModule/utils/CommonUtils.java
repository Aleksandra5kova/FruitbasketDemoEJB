package utils;

import java.util.HashMap;
import java.util.Map;

import configuration.Config;

public class CommonUtils {
	
	public static Map<String, String> extractCrontabToMap(Config config, String expression) {
		String[] parts = config.getProperty(expression).split(" ");
		Map<String, String> result = new HashMap<>();
		result.put(CronTabConstants.SECOND, TimerContraints.ZERO);
		result.put(CronTabConstants.MINUTES, parts[0]);
		result.put(CronTabConstants.HOUR, parts[1]);
		result.put(CronTabConstants.DAY_OF_MONTH, parts[2]);
		result.put(CronTabConstants.MONTH, parts[3]);
		result.put(CronTabConstants.DAY_OF_WEEK, parts[4]);
		return result;
	}

	public static String[] extractCrontabToStrings(Config config, String expression) {
		return config.getProperty(expression).split(" ");
	}
}