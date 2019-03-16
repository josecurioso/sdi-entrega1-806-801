package com.uniovi.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String role;
	private String password;
	private double money;
	private boolean isDeleted = false;
	@Transient
	private String passwordConfirm;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)//, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Offer> offers = new HashSet<Offer>();

	@OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)//, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Offer> buys = new HashSet<Offer>();

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)//, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Conversation> conversation = new HashSet<Conversation>();
	
	@OneToMany(mappedBy = "authorMsg", cascade = CascadeType.ALL)//, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Message> messagesAuthored = new HashSet<Message>();

	
	public Set<Conversation> getConversation() {
		return new HashSet<>(conversation);
	}

	public void setConversation(Set<Conversation> conversation) {
		this.conversation = conversation;
	}

	public User(String email, String name, String lastName) {
		super();
		this.name = name;
		this.lastName = lastName;
		setEmail(email);
		this.money = 100.0;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Offer> getOffers() {
		return this.offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Set<Offer> getBuys() {
		return this.buys;
	}

	public void setBuys(Set<Offer> buys) {
		this.buys = buys;
	}

	public void addBuy(Offer o){
		this.buys.add(o);
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
}