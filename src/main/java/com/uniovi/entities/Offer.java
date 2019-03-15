package com.uniovi.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Offer offer = (Offer) o;
		return name.equals(offer.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
