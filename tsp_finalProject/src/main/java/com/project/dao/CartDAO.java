package com.project.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import com.project.pojo.Buyer;
import com.project.pojo.Cart;
import com.project.pojo.Order;
import com.project.pojo.ProductCart;

public class CartDAO extends DAO {

	public CartDAO() {

	}

	public Cart saveToCart(Cart c) {
		begin();
		getSession().save(c);
		commit();
		return c;
	}

	public Cart updateProdCart(long id, Set<ProductCart> prodCart) {

		begin();
		Query q = getSession().createQuery("from Cart where id = :cartId");
		q.setLong("cartId", id);
		Cart cart = (Cart) q.uniqueResult();
		cart.setProductCart(prodCart);
		getSession().update(cart);

		commit();
		return cart;

	}

	public Cart updateOrder(Long cartid, Order order) {
		// TODO Auto-generated method stub
		begin();
		Query q = getSession().createQuery("from Cart where id = :cartId");
		q.setLong("cartId", cartid);
		Cart cart = (Cart) q.uniqueResult();
		cart.setOrder(order);
		getSession().update(cart);

		commit();
		return cart;
	}

	public Cart getCart(Long cartid) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery("from Cart where id = :cartId");
		q.setLong("cartId", cartid);
		Cart cart = (Cart) q.uniqueResult();
		return cart;
	}

	public List<Cart> allCartItems() {
		begin();
		Query q = getSession().createQuery("from Cart");
		List<Cart> list = q.list();
		commit();
		return list;
	}
}
