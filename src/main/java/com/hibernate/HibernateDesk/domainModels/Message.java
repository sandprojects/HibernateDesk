package com.hibernate.HibernateDesk.domainModels;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name="Messages")
public class Message {
	@Id @GeneratedValue//(strategy=GenerationType.AUTO)
	@Column(name="MESSAGE_ID")
	private Long id;
	
	@Column(name="MESSAGE_TEXT")
	private String text;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="NEXT_MESSAGE_ID")
	private Message nextMessage;

	static Logger logger=Logger.getLogger(Message.class.getName());
	
	Message() {
	}

	public Message(String text) {
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Message getNextMessage() {
		return nextMessage;
	}

	public void setNextMessage(Message nextMessage) {
		this.nextMessage = nextMessage;
	}
	
	@Override
	public String toString(){
		String msgStr = "Message:";
		msgStr += getId()==null ? "" : " " + getId();
		msgStr += getNextMessage()==null ? "" : "("+getNextMessage()+")";
		msgStr += " ==> " + getText();
		return msgStr;
	}
	
}
