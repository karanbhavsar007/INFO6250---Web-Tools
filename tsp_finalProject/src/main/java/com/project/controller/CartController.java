package com.project.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.BuyerDAO;
import com.project.dao.CartDAO;
import com.project.dao.ProductCartDAO;
import com.project.dao.ProductDAO;
import com.project.pojo.Buyer;
import com.project.pojo.Cart;
import com.project.pojo.Product;
import com.project.pojo.ProductCart;
import com.project.pojo.User;

@Controller
@RequestMapping("/cart/*")
public class CartController {

	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;

	@Autowired
	@Qualifier("productCartDao")
	ProductCartDAO productCartDao;

	@Autowired
	@Qualifier("buyerDao")
	BuyerDAO buyerDao;

	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;

	@RequestMapping(value = "/cart/addToCart", method = RequestMethod.POST)
	protected ModelAndView addToCart(HttpServletRequest request) {

		HttpSession session = (HttpSession) request.getSession();

		User user = (User) session.getAttribute("user");
		Long userId = user.getUserId();
		Buyer buyer = buyerDao.getBuyer(userId);

		String[] option = request.getParameterValues("option");
		String[] productId = request.getParameterValues("prodId");
		String[] price = request.getParameterValues("price");
		String[] quantity = request.getParameterValues("quantity");
		float totalPrice = 0;
		int totalItems = 0;
		for (int i = 0; i < productId.length; i++) {

			if (option[i].equals("yes")) {

				float prc = Float.parseFloat(price[i]);
				int qty = Integer.parseInt(quantity[i]);
				totalPrice += prc * qty;
				totalItems += qty;
			}
		}
		Cart c = new Cart();
		c.setBuyer(buyer);
		c.setTotalItems(totalItems);
		c.setTotalPrice(totalPrice);
		Cart cart = cartDao.saveToCart(c);

		Set<ProductCart> prodCart = new HashSet<ProductCart>();
		ProductCart pc = new ProductCart();

		for (int i = 0; i < productId.length; i++) {
			if (option[i].equals("yes")) {

				long prodId = Long.parseLong(productId[i]);
				int qty = Integer.parseInt(quantity[i]);

				Product pro = productDao.getProduct(prodId);
				pc = new ProductCart();
				pc.setQuantity(qty);
				pc.setCart(cart);
				pc.setProduct(pro);
				ProductCart p = productCartDao.save(pc);
				if (p != null)
					prodCart.add(p);
			}
		}

		cart = cartDao.updateProdCart(cart.getId(), prodCart);

		return new ModelAndView("cart", "cart", cart);
	}
}
