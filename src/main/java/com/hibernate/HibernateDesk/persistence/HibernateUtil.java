package com.hibernate.HibernateDesk.persistence;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	static{
		
		 try {
	            Configuration configuration = new Configuration().configure("com/hibernate/HibernateDesk/config/hibernate.cfg.xml");
	            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	            sessionFactory = configuration.buildSessionFactory(builder.build());
	        } catch (HibernateException ex) {
	            System.err.println("Initial sessionFactory creation failed: " + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
		
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}
