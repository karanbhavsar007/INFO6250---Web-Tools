package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.dao.CartDAO;
import com.project.dao.ProductDAO;
import com.project.pojo.Buyer;
import com.project.pojo.Cart;
import com.project.pojo.Product;

@Controller
@RequestMapping("/welcome/*")
public class WelcomeControllers {

	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;

	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
	@RequestMapping(value = "welcome/backBuyer", method = RequestMethod.GET)
	protected ModelAndView welcomeBackBuyer(HttpServletRequest request){
		return new ModelAndView("buyer-search");
	}
	
	@RequestMapping(value = "welcome/buyer", method = RequestMethod.POST)
	protected ModelAndView welcomeBuyer(HttpServletRequest request, @ModelAttribute("buyer") Buyer buyer,
			BindingResult result) {

		HttpSession session = request.getSession(true);
		String selection = request.getParameter("option");
		session.setAttribute("selection", selection);
		if (selection.equals("search")) {
			return new ModelAndView("buyer-search", "", new Product());
		} else {
			List<Product> p = productDao.approvedProducts();
			return new ModelAndView("view-products", "approvedProducts", p);
		}
	}
	
	@RequestMapping(value = "welcome/supplier", method = RequestMethod.POST)
	protected ModelAndView welcomeSupplier(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String selection = request.getParameter("option");
		session.setAttribute("selection", selection);
		if (selection.equals("add")) {

			return new ModelAndView("supplier-add", "supplieradd", new Product());
		} else {
			List<Product> p = productDao.approvedProducts();

			return new ModelAndView("view-products", "approvedProducts", p);

		}
	}
	
	@RequestMapping(value = "welcome/back", method = RequestMethod.GET)
	protected ModelAndView welcomeBack(HttpServletRequest request) {
		return new ModelAndView("supplier-add", "supplieradd", new Product());
		
	}

	@RequestMapping(value = "welcome/admin", method = RequestMethod.POST)
	protected ModelAndView welcomeAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String selection = request.getParameter("option");
		session.setAttribute("selection", selection);
		
			List<Product> p = productDao.productsForAdmin();

			return new ModelAndView("admin-products", "viewRequest", p);
		
	}
}
