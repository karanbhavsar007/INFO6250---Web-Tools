package com.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.project.dao.ProductDAO;
import com.project.dao.SupplierDAO;
import com.project.exception.UserException;
import com.project.pojo.Product;
import com.project.pojo.Supplier;
import com.project.pojo.User;
import com.project.validator.AddProductsValidator;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;

	@Autowired
	@Qualifier("supplierDao")
	SupplierDAO supplierDao;
	
	@Autowired
	@Qualifier("productValidator")
	AddProductsValidator productValidator;
	
	@InitBinder("productValidator")
	private void initBuyerBinder(WebDataBinder binder) {
		binder.setValidator(productValidator);
	}

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	protected ModelAndView requestToAddProduct(HttpServletRequest request, @ModelAttribute("supplieradd") Product product , BindingResult result) {
		
		productValidator.validate(product, result);
		if (result.hasErrors()) {
			return new ModelAndView("supplier-add", "supplieradd",product);
		}
		
		HttpSession session = (HttpSession) request.getSession();
		try {

			if (product.getFilename().trim() != "" || product.getFilename() != null) {
				File directory;
				String check = File.separator; // Checking if system is linux
												// based or windows based by
												// checking seprator used.
				String path = null;
				if (check.equalsIgnoreCase("\\")) {
					path = "D:";
					path += "/";
					//path = servletContext.getRealPath("").replace("build\\", ""); // gives
																					// real
																					// path
																					// as
																					// Lab9/build/web/
																					// so
																					// we
																					// need
																					// to
																					// replace
																					// build
																					// in
																					// the
																					// path
				}

				if (check.equalsIgnoreCase("/")) {
					//path = servletContext.getRealPath("").replace("build/", "");
					path = "D:";
					path += "/"; // Adding trailing slash for Mac systems.
				}
				directory = new File(path + "\\" + product.getFilename());
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = product.getPhoto();

					String fileName = photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File(directory.getPath(), fileName);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					product.setFilename(localFile.getPath());
					System.out.println("File is stored at" + localFile.getPath());
					User user = (User) session.getAttribute("user");
					Long userId = user.getUserId();
					Supplier supplier = supplierDao.getSupplier(userId);
					product.setSupplier(supplier);
					Product p = productDao.requestProduct(product);

				} else {
					System.out.println("Failed to create directory!");
				}

			}

		} catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
		}

		return new ModelAndView("request-success", "request", product);
	}

	@RequestMapping(value = "/product/adminadd", method = RequestMethod.POST)
	protected ModelAndView requestAccepted(HttpServletRequest request) {
		
		String[] id = request.getParameterValues("id");

		String[] option = request.getParameterValues("option");
		for (int i = 0; i < id.length; i++) {

			productDao.updateProduct(id[i], option[i]);

		}
		List<Product> p = productDao.approvedProducts();
		return new ModelAndView("view-products", "approvedProducts", p);
	}

	@RequestMapping(value = "/product/buyerSearch", method = RequestMethod.POST)
	protected ModelAndView searchProducts(HttpServletRequest request, String searchBy, String keyword) {

		HttpSession session = (HttpSession) request.getSession();
		searchBy = request.getParameter("search");
		keyword = request.getParameter("keyword");
		
		ArrayList<Product> productList = new ArrayList();

		if (searchBy.equals("name")) {
			productList = productDao.productListByName(keyword);
		}

		else if (searchBy.equals("category")) {
			productList = productDao.productListByCategory(keyword);
		}
		session.setAttribute("keyword", keyword);
		return new ModelAndView("search-results", "productList", productList);

	}

}
