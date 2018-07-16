package xyz.tuny.jx.server;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xyz.tuny.jx.util.PropertiesUtil;

/**
 * netty 启动服务
 */
public class JServerStartService {
	private static final Logger logger = Logger.getLogger(JServerStartService.class);
	public static void main(String[] args) {
		new Thread() {
			public void run() {
				System.out.println("************starting jx************");
				try {
			    	ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
			    	int port = Integer.valueOf(PropertiesUtil.getProperty("nettyport"));
			    	System.out.println("***************listening:" + port +  " ****************");
			    	new Server(port).run();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("***************shutdown***************");
			}
		}.start();
	}
}
