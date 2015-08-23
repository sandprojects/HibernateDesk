package com.hibernate.HibernateDesk.Mains;


import java.util.List;

import javassist.bytecode.Descriptor.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.hibernate.HibernateDesk.config.LogInitializer;
import com.hibernate.HibernateDesk.domainModels.Message;
import com.hibernate.HibernateDesk.persistence.HibernateUtil;


public class HelloWorld {

	static Logger logger=Logger.getLogger(HelloWorld.class.getName());
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LogInitializer();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx=session.beginTransaction();
		
		logger.info("Transaction Begine..!");
		
		Message message=new Message("HelloWorld");
		System.out.println("Before save: " + message);
		Long msgId = (Long) session.save(message);
		System.out.println("After save: " + message);
		tx.commit();
		session.clear();
		
		Session newSess=HibernateUtil.getSessionFactory().openSession();
		Transaction newTx=newSess.beginTransaction();
		
		List messages = newSess.createQuery("From Message m order by m.text asc").list();
		System.out.println( messages.size() + " message(s) found:" );
		
		for(java.util.Iterator iter = messages.iterator(); iter.hasNext() ;){
			
			Message loadedMsg = (Message) iter.next();
			System.out.println( loadedMsg.getText() );
			//newSess.delete(loadedMsg);
			
		}
		newTx.commit();
		newSess.close();
		
		Session thirdSession = HibernateUtil.getSessionFactory().openSession();
		Transaction thirdTransaction = thirdSession.beginTransaction();
		// msgId holds the identifier value of the first message
		message = (Message) thirdSession.get( Message.class, msgId );
		message.setText( "Greetings Earthling" );
		message.setNextMessage(new Message( "Take me to your leader (please)" ));
		
		messages = thirdSession.createQuery("From Message m order by m.text asc").list();
		System.out.println( messages.size() + " message(s) found:" );
		
		for(java.util.Iterator iter = messages.iterator(); iter.hasNext() ;){
			
			Message loadedMsg = (Message) iter.next();
			System.out.println( loadedMsg.getText() );
			thirdSession.delete(loadedMsg);
			
		}
		
		thirdTransaction.commit();
		thirdSession.close();
		
		HibernateUtil.shutdown();
	}

}
