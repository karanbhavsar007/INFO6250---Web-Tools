package com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
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
import com.project.exception.UserException;
import com.project.pojo.Buyer;
import com.project.pojo.Supplier;
import com.project.pojo.User;
import com.project.validator.BuyerValidator;
import com.project.validator.SupplierValidator;
import com.project.dao.BuyerDAO;
import com.project.dao.SupplierDAO;
import com.project.dao.UserDAO;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("buyerDao")
	BuyerDAO buyerDao;

	@Autowired
	@Qualifier("supplierDao")
	SupplierDAO supplierDao;
	
	@Autowired
	@Qualifier("buyerValidator")
	BuyerValidator buyerValidator;
	
	@InitBinder("buyerValidator")
	private void initBuyerBinder(WebDataBinder binder) {
		binder.setValidator(buyerValidator);
	}
	
	@Autowired
	@Qualifier("supplierValidator")
	SupplierValidator supplierValidator;
	
	@InitBinder("supplierValidator")
	private void initSupplierBinder(WebDataBinder binder) {
		binder.setValidator(supplierValidator);
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();

		try {

			System.out.print("loginUser");

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"),
					request.getParameter("email"), request.getParameter("role"));
			session.setAttribute("user", u);

			if (u == null) {
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}

			else if (u.getRole().equals("supplier")) {
				return "welcome-supplier";
			} else if (u.getRole().equals("admin")) {
				return "welcome-admin";
			} else if (!(u.getRole().equals("admin")) && !(u.getRole().equals("buyer"))
					&& !(u.getRole().equals("supplier"))) {
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			} else {

				return "welcome-buyer";
			}

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request) throws Exception {
		if (request.getParameter("radios").equals("buyer")) {
			return new ModelAndView("register-buyer", "buyer", new Buyer());
		} else {
			return new ModelAndView("register-supplier", "supplier", new Supplier());
		}
	}
	
	@RequestMapping(value = "/user/welcomebackbuyer", method = RequestMethod.GET)
	protected ModelAndView welcomeBack(HttpServletRequest request){
		return new ModelAndView("welcome-buyer");
	}
	
	@RequestMapping(value = "/user/registerbuyer", method = RequestMethod.POST)
	protected ModelAndView registerNewBuyer(HttpServletRequest request,  @ModelAttribute("buyer") Buyer buyer,BindingResult result) throws Exception {
		
		buyerValidator.validate(buyer, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("register-buyer", "buyer", buyer);
		}
		HttpSession session = (HttpSession) request.getSession();

		Buyer b = new Buyer();
		b.setFirstName(request.getParameter("firstName"));
		b.setLastName(request.getParameter("lastName"));
		b.setGender(request.getParameter("gender"));

		b.setUsername(request.getParameter("username"));
		b.setPassword(request.getParameter("password"));
		b.setEmail(request.getParameter("email"));
		b.setRole("buyer");
		Buyer byr = buyerDao.register(b);
		session.setAttribute("user", byr);
		
		Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("finalproj94@gmail.com", "lionelmessi10"));
        email.setSSLOnConnect(true);
        email.setFrom("finalproj94@gmail.comm");
        email.setSubject("Registration");
        email.setMsg("You have successfully been registered");
        email.addTo(b.getEmail());
       
        email.send();
		
		return new ModelAndView("welcome-buyer", "buyer", b);
	}

	@RequestMapping(value = "/user/registersupplier", method = RequestMethod.POST)
	protected ModelAndView registerNewSupplier(HttpServletRequest request,  @ModelAttribute("supplier") Supplier supplier, BindingResult result) throws Exception {
		
supplierValidator.validate(supplier, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("register-supplier", "supplier", supplier);
		}
		HttpSession session = (HttpSession) request.getSession();

		Supplier s = new Supplier();
		s.setCompany(request.getParameter("company"));

		s.setUsername(request.getParameter("username"));
		s.setPassword(request.getParameter("password"));
		s.setEmail(request.getParameter("email"));
		s.setRole("supplier");
		Supplier supp = supplierDao.register(s);
		session.setAttribute("user", supp);
		
		Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("finalproj94@gmail.com", "lionelmessi10"));
        email.setSSLOnConnect(true);
        email.setFrom("finalproj94@gmail.comm");
        email.setSubject("Registration");
        email.setMsg("You have successfully been registered");
        email.addTo(s.getEmail());
        email.send();
		return new ModelAndView("welcome-supplier", "supplier", s);
	}
	
	@RequestMapping(value = "/user/home", method = RequestMethod.POST)
    public String logout(HttpServletRequest request){
		
		return "index";
	}
        
}