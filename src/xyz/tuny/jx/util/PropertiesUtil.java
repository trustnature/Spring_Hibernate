package xyz.tuny.jx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	private Logger log = Logger.getLogger(PropertiesUtil.class);
	private static Properties props;

	public PropertiesUtil(String location) {
		ClassLoader loader = getClass().getClassLoader();
		InputStream inputStream = loader.getResourceAsStream(location);
		try {
			props = new Properties();
			props.load(inputStream);
			inputStream.close();
			this.log.info("参数加载完成=========");
		} catch (IOException e) {
			this.log.error("PropertiesUtil-->load property error !", e);
		}
	}

	public static String getProperty(String property) {
		return props.getProperty(property);
	}
}
