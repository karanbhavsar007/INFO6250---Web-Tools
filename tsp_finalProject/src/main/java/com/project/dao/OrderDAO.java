package com.project.dao;

import com.project.pojo.Order;

public class OrderDAO extends DAO {

	public Order saveOrder(Order o) {
		// TODO Auto-generated method stub
		begin();
		getSession().save(o);
		commit();
		return o;
	}

}
