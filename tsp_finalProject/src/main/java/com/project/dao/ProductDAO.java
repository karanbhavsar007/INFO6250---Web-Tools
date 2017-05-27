package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.project.pojo.Buyer;
import com.project.pojo.Product;

public class ProductDAO extends DAO {
	public ProductDAO() {

	}

	public Product requestProduct(Product p) {

		begin();
		getSession().save(p);
		commit();
		return p;
	}

	public List<Product> list() {

		begin();
		Query q = getSession().createQuery("from Product");
		List<Product> list = q.list();
		commit();
		return list;
	}

	public List<Product> productsForAdmin() {

		begin();
		Query q = getSession().createQuery("from Product");
		List<Product> productList = q.list();
		commit();
		return productList;
	}

	public void updateProduct(String id, String status) {
		// TODO Auto-generated method stub
		try {
			begin();
			Long ID = Long.parseLong(id);
			Query q = getSession().createQuery("from Product where productId = :id");
			q.setLong("id", ID);
			Product product = (Product) q.uniqueResult();
			product.setStatus(status);
			getSession().update(product);
			commit();
		} catch (HibernateException e) {
			rollback();
		}
	}

	public List<Product> approvedProducts() {
		begin();
		Query q = getSession().createQuery("from Product where status=:status");
		q.setString("status", "approve");
		List<Product> approvedProductsList = q.list();
		commit();
		return approvedProductsList;
	}

	public Product getProduct(long prodId) {
		// TODO Auto-generated method stub

		Query q = getSession().createQuery("from Product where productId = :productId");
		q.setLong("productId", prodId);
		Product product = (Product) q.uniqueResult();
		return product;

	}

	public ArrayList<Product> productListByName(String name) {
		Criteria cr = getSession().createCriteria(Product.class);
		cr.add(Restrictions.like("name", "%" + name + "%"));
		ArrayList<Product> productList = (ArrayList<Product>) cr.list();
		return productList;

	}

	public ArrayList<Product> productListByCategory(String category) {
		Criteria cr = getSession().createCriteria(Product.class);
		cr.add(Restrictions.like("category", "%" + category + "%"));
		ArrayList<Product> productList = (ArrayList<Product>) cr.list();
		return productList;
	}

}
