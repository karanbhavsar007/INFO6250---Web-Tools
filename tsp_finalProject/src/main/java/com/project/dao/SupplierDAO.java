package com.project.dao;

import java.util.Set;

import org.hibernate.Query;

import com.project.pojo.Product;
import com.project.pojo.Supplier;

public class SupplierDAO extends DAO {

	public SupplierDAO() {

	}

	public Supplier register(Supplier s) {

		begin();

		getSession().save(s);
		commit();
		return s;
	}

	public Supplier getSupplier(Long userId) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery("from Supplier where userId = :userId");
		q.setLong("userId", userId);
		Supplier supplier = (Supplier) q.uniqueResult();
		return supplier;
	}

}
