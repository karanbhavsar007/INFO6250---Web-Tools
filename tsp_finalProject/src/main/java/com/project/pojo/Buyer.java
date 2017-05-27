package com.project.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "buyer")
@PrimaryKeyJoinColumn(name = "userId")

public class Buyer extends User {

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "gender")
	private String gender;

	@OneToMany(mappedBy = "buyer")
	private Set<Cart> carts = new HashSet<Cart>();

	@OneToMany(mappedBy = "buyer")
	private Set<Order> orderHistory = new HashSet<Order>();

	public Buyer() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

	public Set<Order> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(Set<Order> orderHistory) {
		this.orderHistory = orderHistory;
	}
}
