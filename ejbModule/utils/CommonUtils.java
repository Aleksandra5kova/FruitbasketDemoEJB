package utils;

import java.util.HashMap;
import java.util.Map;

import configuration.Config;
import constraints.CrontabConstants;
import constraints.TimerContraints;

public class CommonUtils {
	
	public static Map<String, String> extractCrontabToMap(Config config, String expression) {
		String[] parts = expression.split(" ");
		Map<String, String> result = new HashMap<>();
		result.put(CrontabConstants.SECOND, TimerContraints.ZERO);
		result.put(CrontabConstants.MINUTES, parts[0]);
		result.put(CrontabConstants.HOUR, parts[1]);
		result.put(CrontabConstants.DAY_OF_MONTH, parts[2]);
		result.put(CrontabConstants.MONTH, parts[3]);
		result.put(CrontabConstants.DAY_OF_WEEK, parts[4]);
		return result;
	}

}