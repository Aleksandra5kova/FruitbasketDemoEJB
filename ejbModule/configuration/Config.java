package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static final Config instance = new Config();

	private Properties data = new Properties();

	private Config() {
		loadData();
	}

	private void loadData() {

		InputStream in = Config.class.getClassLoader().getResourceAsStream("application.properties");

		if (in != null) {
			try {
				data.load(in);
			} catch (IOException e) {
				System.out.println("Configuration file could not be read.");
				e.printStackTrace();
			}
		} else {
			System.out.println("Configuration file could not be found.");
		}
	}

	public static Config getInstance() {
		return Config.instance;
	}

	public String getProperty(String key) {
		return data.getProperty(key);
	}

}
