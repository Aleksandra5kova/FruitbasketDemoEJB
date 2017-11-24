package utils;

import configuration.Config;

public class ReportUtils {

	public static final String ABSOLUTEPATH = "absolutePath";
	public static final String DOCUMENTNAME = "documentName";

	public static final String SEPARATOR = ".";

	private static final Config config = Config.getInstance();

	public static String getFileName(String name, String namePrefix) {
		String absolutePath = getAbsolutePath(name);
		String documentName = getDocumentName(name, namePrefix);
		return absolutePath + documentName;
	}

	private static String getAbsolutePath(String name) {
		String absolutePathProperty = getAbsolutePathProperty(name);
		return config.getProperty(absolutePathProperty);
	}

	private static String getDocumentName(String name, String namePrefix) {
		String documentNameProperty = getDocumentNameProperty(name);
		return namePrefix + config.getProperty(documentNameProperty);
	}

	private static String getDocumentNameProperty(String name) {
		return name + ReportUtils.SEPARATOR + ReportUtils.DOCUMENTNAME;
	}

	private static String getAbsolutePathProperty(String name) {
		return name + ReportUtils.SEPARATOR + ReportUtils.ABSOLUTEPATH;
	}

}
