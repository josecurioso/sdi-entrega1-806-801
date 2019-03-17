package com.uniovi.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Offer {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private Date date = new Date();
	private Double price;
	private Boolean sold = false;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
	private Set<Conversation> conversation=new HashSet<Conversation>();
	
	@ManyToOne
	@JoinColumn(name = "buyer_id")
	private User buyer;

	public Offer() {
	}

	
	
	
	public Offer(String name, String description, Double price, User user) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.user = user;
		this.sold = false;
	}

	
	
	
	public Set<Conversation> getConversation() {
		return new HashSet<Conversation>(conversation);
	}

	public void setConversation(Set<Conversation> conversation) {
		this.conversation = new HashSet<Conversation>(conversation);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
		this.sold = true;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	@Override
	public String toString() {
		return "Offer [name=" + name + ", description=" + description + ", price=" + price + ", date=" + date + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Offer other = (Offer) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
