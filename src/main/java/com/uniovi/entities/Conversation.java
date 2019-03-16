package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;

	@ManyToOne
	@JoinColumn(name = "offer_id")
	private Offer offer;
	
	
	@OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
	private Set<Message> messages=new HashSet<Message>();

	
	public Conversation(){
		
	}
	
	public Conversation( User author,Offer offer){
		this.author=author;
		this.offer=offer;
		
		
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}


	public void setAuthor(User author) {
		this.author = author;
	}


	public Offer getOffer() {
		return offer;
	}


	public void setOffer(Offer offer) {
		this.offer = offer;
	}


	public Set<Message> getMessages() {
		return new HashSet<>(messages);
	}


	public void addMessages(Message message) {
		this.messages.add(message);
	}
	
	
	
}
