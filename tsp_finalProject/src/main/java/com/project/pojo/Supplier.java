package com.project.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
@PrimaryKeyJoinColumn(name = "userId")

public class Supplier extends User {

	@Column(name = "company")
	private String company;

	@OneToMany(mappedBy = "supplier")
	private Set<Product> products = new HashSet<Product>();

	public Supplier() {

	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
