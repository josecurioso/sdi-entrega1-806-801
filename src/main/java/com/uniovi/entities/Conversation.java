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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((offer == null) ? 0 : offer.hashCode());
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
		Conversation other = (Conversation) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (offer == null) {
			if (other.offer != null)
				return false;
		} else if (!offer.equals(other.offer))
			return false;
		return true;
	}



	
	
	
}
