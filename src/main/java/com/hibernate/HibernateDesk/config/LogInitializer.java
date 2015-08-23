package com.hibernate.HibernateDesk.config;

import org.apache.log4j.xml.DOMConfigurator;

public class LogInitializer {
	static{
		DOMConfigurator.configure("target/classes/com/hibernate/HibernateDesk/config/log4j.xml");
	}
}
