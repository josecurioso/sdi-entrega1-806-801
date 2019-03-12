package com.uniovi.entities;

import java.util.Date;

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
	private Date date;
	private Double price;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Offer() {

	}

	public Offer(Long id, String name, String description, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.date = new Date();
	}

	public Offer(String name, String description, Double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.date = new Date();
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

	@Override
	public String toString() {
		return "Offer [name=" + name + ", description=" + description + ", price=" + price + ", date=" + date + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
