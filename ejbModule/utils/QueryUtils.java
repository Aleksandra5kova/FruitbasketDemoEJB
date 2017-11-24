package utils;

@SuppressWarnings("rawtypes")
public class QueryUtils {

	public static final String FINDALL = "FINDALL";
	public static final String FINDBY = "FINDBY";
	public static final String SEPARATOR = ".";

	public static String generateFindAllQuery(Class type) {
		return type.getSimpleName() + QueryUtils.SEPARATOR + QueryUtils.FINDALL;
	}

	public static String generateFindByFieldQuery(Class type, String name) {
		return type.getSimpleName() + QueryUtils.SEPARATOR + QueryUtils.FINDBY + name.toUpperCase();
	}
}
