package utils;

import java.util.HashMap;
import java.util.Map;

import constraints.CrontabConstants;

public class CommonUtils {

	public static final String ZERO = "0";
	
	public static Map<String, String> extractCrontabToMap(String expression) {
		String[] parts = expression.split(" ");
		Map<String, String> result = new HashMap<>();
		result.put(CrontabConstants.SECOND, CommonUtils.ZERO);
		result.put(CrontabConstants.MINUTES, parts[0]);
		result.put(CrontabConstants.HOUR, parts[1]);
		result.put(CrontabConstants.DAY_OF_MONTH, parts[2]);
		result.put(CrontabConstants.MONTH, parts[3]);
		result.put(CrontabConstants.DAY_OF_WEEK, parts[4]);
		return result;
	}

}