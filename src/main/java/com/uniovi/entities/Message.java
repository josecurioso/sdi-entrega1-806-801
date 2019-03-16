package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private Long id;
	
	private String text;
	private Date date = new Date();
	

	@ManyToOne
	@JoinColumn(name = "conver_id")
	private Conversation conversation;

	@ManyToOne
	@JoinColumn(name = "authorMsg_id")
	private User authorMsg;
	
	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Message() {
		
	}

	
	
	public User getAuthorMsg() {
		return authorMsg;
	}

	public void setAuthorMsg(User authorMsg) {
		this.authorMsg = authorMsg;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conversation == null) ? 0 : conversation.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (conversation == null) {
			if (other.conversation != null)
				return false;
		} else if (!conversation.equals(other.conversation))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [text=" + text + ", date=" + date + "]";
	}
	
	
	
	
}
