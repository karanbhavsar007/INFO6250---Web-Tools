package com.project.dao;

import com.project.pojo.ProductCart;

public class ProductCartDAO extends DAO {
	public ProductCartDAO() {

	}

	public ProductCart save(ProductCart pc) {
		begin();
		getSession().save(pc);
		commit();
		return pc;
	}
}
