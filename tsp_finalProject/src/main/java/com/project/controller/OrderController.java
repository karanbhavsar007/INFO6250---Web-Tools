package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.project.dao.BuyerDAO;
import com.project.dao.CartDAO;
import com.project.dao.OrderDAO;
import com.project.pojo.Buyer;
import com.project.pojo.Cart;
import com.project.pojo.Order;
import com.project.pojo.PDFView;
import com.project.pojo.User;

@Controller
@RequestMapping("/order/*")
public class OrderController extends PDFView {

	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;

	@Autowired
	@Qualifier("buyerDao")
	BuyerDAO buyerDao;

	@Autowired
	@Qualifier("orderDao")
	OrderDAO orderDao;

	@RequestMapping(value = "/order/generateOrder", method = RequestMethod.POST)
	protected ModelAndView generateOrder(@ModelAttribute("cart") Cart cart, ModelMap model, BindingResult result,
			HttpServletRequest request) {

		HttpSession session = (HttpSession) request.getSession();

		User user = (User) session.getAttribute("user");
		Long userId = user.getUserId();
		Buyer buyer = buyerDao.getBuyer(userId);
		String cartId = request.getParameter("cartId");
		Long cartid = Long.parseLong(cartId);
		Cart c = cartDao.getCart(cartid);
		Order o = new Order();
		o.setBuyer(buyer);
		o.setCart(c);
		Order order = orderDao.saveOrder(o);
		cartDao.updateOrder(cartid, order);

		List<Cart> pdfView = cartDao.allCartItems();
		model.addAttribute("cartList", pdfView);
		View v = new PDFView();
		return new ModelAndView(v);

	}
}