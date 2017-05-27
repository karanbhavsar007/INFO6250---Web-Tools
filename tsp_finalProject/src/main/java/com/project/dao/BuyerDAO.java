package com.project.dao;

import org.hibernate.Query;

import com.project.pojo.Buyer;

public class BuyerDAO extends DAO {

	public BuyerDAO() {

	}

	public Buyer register(Buyer b) {

		begin();

		getSession().save(b);
		commit();
		return b;

	}

	public Buyer getBuyer(Long userId) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery("from Buyer where userId = :userId");
		q.setLong("userId", userId);
		Buyer buyer = (Buyer) q.uniqueResult();
		return buyer;

	}
}
