package utils;

import configuration.Config;

@SuppressWarnings("rawtypes")
public class ReportUtils {

	public static final String ABSOLUTEPATH = "absolutePath";
	public static final String DOCUMENTNAME = "documentName";

	public static final String SEPARATOR = ".";

	private static final Config config = Config.getInstance();

	public static String getFileName(Class type, String namePrefix) {
		String absolutePath = getAbsolutePath(type);
		String documentName = getDocumentName(type, namePrefix);
		return absolutePath + documentName;
	}

	private static String getAbsolutePath(Class type) {
		String absolutePathProperty = getAbsolutePathProperty(type);
		return config.getProperty(absolutePathProperty);
	}

	private static String getDocumentName(Class type, String namePrefix) {
		String documentNameProperty = getDocumentNameProperty(type);
		return namePrefix + config.getProperty(documentNameProperty);
	}

	private static String getDocumentNameProperty(Class type) {
		return type.getSimpleName() + ReportUtils.SEPARATOR + ReportUtils.DOCUMENTNAME;
	}

	private static String getAbsolutePathProperty(Class type) {
		return type.getSimpleName() + ReportUtils.SEPARATOR + ReportUtils.ABSOLUTEPATH;
	}

}
