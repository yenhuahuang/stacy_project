package com.example.controller;


import java.sql.SQLException;




import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.ProductDAO;
import com.example.dao.ProductdetailDAO;
import com.example.entity.Product;
import com.example.entity.Productdetail;


@Controller
public class ProductDetailController {

	@Autowired
	 ProductDAO dao;
	
	@Autowired
	 ProductdetailDAO detailDao;

	        
	
	 @RequestMapping(value = "/admin/productdetailCreate", method = RequestMethod.GET)
	    public ModelAndView openFormCreate(@ModelAttribute Productdetail productdetail) {
		 ModelAndView model = new ModelAndView("admin/productdetail");		 
		model.addObject("productdetail",productdetail);
		 Iterable<Product> products = dao.findAll();		 
		 model.addObject("allProducts", products);
	       return model;
	    }

	 @RequestMapping(value = "/admin/productdetailCreate", method = RequestMethod.POST)
	    public ModelAndView processFormCreate(@Valid@ModelAttribute Productdetail productdetail ,  BindingResult bindingResult) throws SQLException {
	       ModelAndView model = new ModelAndView("redirect:/admin/productdetailRetrieveAll");
	       if (bindingResult.hasErrors()) {
	           model = new ModelAndView("admin/productdetail");
	           return model;
	         }
	       productdetail.setIndate();
	       detailDao.save(productdetail);
	       Iterable<Product> products = dao.findAll();
	       for(Product p : products){
	    	   p.setInventory();
	       }	       
	       dao.save(products);
	       model.addObject(productdetail);
	       return model;
	    }
	 
	 @RequestMapping(value = {"/admin/productdetailRetrieveAll"}, method = RequestMethod.GET)
	    public ModelAndView retrieveProductdetail() throws SQLException{
	    
	    Iterable<Productdetail> productdetails = detailDao.findAll();
	       ModelAndView model = new ModelAndView("admin/productdetailList");
	       Iterable<Product> products = dao.findAll();
	       model.addObject("allProducts", products);
	       Product product = products.iterator().next();//get first category
	       model.addObject("product", product);
	       model.addObject("allproductdetails",productdetails);
	       return model;
	    }
	 
	 @RequestMapping(value = {"/admin/productReach"}, method = RequestMethod.GET)
	 public ModelAndView reachProductdetail(@RequestParam(value="id", required=false, defaultValue="1") Long id){
		 ModelAndView model = new ModelAndView("redirect:/admin/productdetailRetrieveAll");
		 Iterable<Productdetail> productdetails = detailDao.findAll();
			model.addObject("allProductdetails", productdetails);
			Productdetail productdetail = detailDao.findOne(id);
			model.addObject("productdetail", productdetail);
			productdetail.setReach();
			productdetail.setIsreach(1);
			detailDao.save(productdetail);
			
	       return model;
	    }
	 
	 
	 @RequestMapping(value = { "/admin/productdetailRetrieveByproduct" }, method = RequestMethod.POST)
	 public ModelAndView retrieveProductdetailsByproduct(
	   @RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
	  ModelAndView model = new ModelAndView("admin/productdetailList");
	  
	  Iterable<Product> products = dao.findAll();
	  model.addObject("allProducts", products);
	  Product product = dao.findOne(id);
	  model.addObject("product", product);
	  
	  model.addObject("allproductdetails", product.getProductdetails());
	  return model;
	 }

	 @RequestMapping(value = "/admin/productdetailUpdate", method = RequestMethod.GET)
	    public ModelAndView openFormUpdate(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
	       ModelAndView model = new ModelAndView("admin/productdetailUpdate");
	       Productdetail productdetail = detailDao.findOne(id);
	       model.addObject(productdetail);
	       Iterable<Product> products = dao.findAll();
	       model.addObject("allProducts", products);
	       return model;
	    }
   
	 @RequestMapping(value = "/admin/productdetailUpdate", method = RequestMethod.POST)
	    public ModelAndView processFormUpdate(@ModelAttribute Productdetail prode) throws SQLException {
	       ModelAndView model = new ModelAndView("redirect:/admin/productdetailRetrieveAll");
	       detailDao.save(prode);
	       Iterable<Product> products = dao.findAll();
	       for(Product p : products){
	    	   p.setInventory();
	       }
	       dao.save(products);
	       detailDao.save(prode);             
	       return model;
	    }
	 
	 @RequestMapping(value = "/admin/productdetailDelete", method = RequestMethod.GET)
	    public ModelAndView deleteProductdetail(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
	       ModelAndView model = new ModelAndView("redirect:/admin/productdetailRetrieveAll");
	       detailDao.delete(id);
	       return model;
	    }
	 
    
      
       
        
           
    
} 