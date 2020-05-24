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

import com.example.dao.ProductCategoryDAO;
import com.example.dao.ProductDAO;
import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.storage.StorageService;


@Controller
public class ProductController {

	@Autowired
	 ProductDAO dao;
	
	@Autowired
	 ProductCategoryDAO categoryDao;
	
	 private final StorageService storageService;

	 @Autowired
	    public ProductController(StorageService storageService) {
	        this.storageService = storageService;
	    }
	
	 @RequestMapping(value = "/admin/productCreate", method = RequestMethod.GET)
	    public ModelAndView openFormCreate(@ModelAttribute Product product) {
		 ModelAndView model = new ModelAndView("admin/product");		 
		model.addObject("product",product);
		 Iterable<ProductCategory> productCategories = categoryDao.findAll();		 
		 model.addObject("allProductCategories", productCategories);
	       return model;
	    }

	 @RequestMapping(value = "/admin/productCreate", method = RequestMethod.POST)
	    public ModelAndView processFormCreate(@Valid@ModelAttribute Product pro ,  BindingResult bindingResult) throws SQLException {
	       ModelAndView model = new ModelAndView("redirect:/admin/productRetrieveAll");
	       if (bindingResult.hasErrors()) {
	           model = new ModelAndView("admin/product");
	           return model;
	         }
	       storageService.store(pro.getPhotoFile());
	       pro.setPhoto();//copy file name to the field photo
	       pro.setInventory();
	       pro.setUnderdate();
	       dao.save(pro);	  
	       model.addObject(pro);
	       return model;
	    }
	 
	 @RequestMapping(value = {"/admin/productRetrieveAll"}, method = RequestMethod.GET)
	    public ModelAndView retrieveProduct() throws SQLException{
	    
	    Iterable<Product> products = dao.findAll();
	       for(Product p : products){
	    	   p.setInventory();
	       }	       
	       dao.save(products);
	       
	       ModelAndView model = new ModelAndView("admin/productList");
	       
	       Iterable<ProductCategory> productCategories = categoryDao.findAll();
	       model.addObject("allProductCategories", productCategories);
	       ProductCategory productCategory = productCategories.iterator().next();//get first category
	       model.addObject("productCategory", productCategory);
	       model.addObject("allproducts",products);	       
	       return model;
	    }
	 
	 @RequestMapping(value = { "/admin/productRetrieveByCategory" }, method = RequestMethod.POST)
	 public ModelAndView retrieveProductsByCategory(
	   @RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
	  ModelAndView model = new ModelAndView("admin/productList");
	  
	  Iterable<ProductCategory> productCategories = categoryDao.findAll();
	  model.addObject("allProductCategories", productCategories);
	  ProductCategory productCategory = categoryDao.findOne(id);
	  model.addObject("productCategory", productCategory);
	  
	  model.addObject("allproducts", productCategory.getProducts());
	  return model;
	 }

	 @RequestMapping(value = "/admin/productUpdate", method = RequestMethod.GET)
	    public ModelAndView openFormUpdate(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
	       ModelAndView model = new ModelAndView("admin/productUpdate");
	       Product product = dao.findOne(id);
	       model.addObject(product);	       
	       Iterable<ProductCategory> productCategories = categoryDao.findAll();
	       model.addObject("allProductCategories", productCategories);
	       return model;
	    }
   
	 @RequestMapping(value = "/admin/productUpdate", method = RequestMethod.POST)
	    public ModelAndView processFormUpdate(@ModelAttribute Product pro) throws SQLException {		   
	       ModelAndView model = new ModelAndView("redirect:/admin/productRetrieveAll");  
	       dao.save(pro);             
	       return model;
	    }
	 
	 @RequestMapping(value = "/admin/productDelete", method = RequestMethod.GET)
	    public ModelAndView deleteProduct(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
	       ModelAndView model = new ModelAndView("redirect:/admin/productRetrieveAll");
	       dao.delete(id);
	       return model;
	    }
	 
    
      
       
        
           
    
} 