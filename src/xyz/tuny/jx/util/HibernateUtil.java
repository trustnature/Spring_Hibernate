package xyz.tuny.jx.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private final static SessionFactory FACTORY = buildSessionFactroy();
	
	private static SessionFactory buildSessionFactroy() {
		Configuration cfg = new Configuration().configure();
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
		StandardServiceRegistry sr = srb.build();
		SessionFactory sf = cfg.buildSessionFactory(sr);
		return sf;
	}
	public static SessionFactory getSessionFactroy(){
		return FACTORY;	
	}
	public static Session openSession(){
		return FACTORY.openSession();
	}
	public static void close(Session session){
		if(session != null) session.close();
	}

	
}
