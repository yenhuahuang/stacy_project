package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyKey;
import com.example.dao.MemberDAO;
import com.example.dao.OrderDAO;
import com.example.dao.OrderdetailDAO;
import com.example.dao.ProductCategoryDAO;
import com.example.dao.ProductDAO;
import com.example.dao.UserRepository;
import com.example.entity.Member;
import com.example.entity.Order;
import com.example.entity.Orderdetail;
import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.entity.ShoppingCart;

@Controller
public class BookController {
	@Autowired
	ProductDAO dao;

	@Autowired
	ProductCategoryDAO categoryDao;

	@Autowired
	MemberDAO memDao;

	@Autowired
	ShoppingCart cart;

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderDAO orderDao;

	@Autowired
	OrderdetailDAO orderdeDao;

	
	@RequestMapping(value = "/HelloWithId", method = RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("username") String memid,
			HttpSession session) {
		ModelAndView model = new ModelAndView("redirect:/bookRetrieveAll");
		session.setAttribute("loginId", memid);
		model.addObject(memid);
		return model;
	}

	@RequestMapping(value = "/bookCreate", method = RequestMethod.GET)
	public ModelAndView openFormCreate() {
		ModelAndView model = new ModelAndView("admin/bookCreate");

		Iterable<ProductCategory> categories = categoryDao.findAll();

		model.addObject("allBookCategories", categories);
		return model;
	}

	@RequestMapping(value = "/bookCreate", method = RequestMethod.POST)
	public ModelAndView processFormCreate(@ModelAttribute Product product) {
		// ModelAndView model = new ModelAndView("bookDone");
		ModelAndView model = new ModelAndView("redirect:/bookRetrieveAll");

		dao.save(product);

		model.addObject(product);
		return model;
	}

	@RequestMapping(value = { "/bookRetrieveAll", "/book" }, method = RequestMethod.GET)
	public ModelAndView retrieveBooks(@ModelAttribute("memid") String memid) {

		ModelAndView model = new ModelAndView("bookList");
		model.addObject(memid);
		Iterable<ProductCategory> categories = categoryDao.findAll();
		model.addObject("allBookCategories", categories);
		ProductCategory category = categories.iterator().next();// get first
																// category
		model.addObject("bookCategory", category);

		Iterable<Product> products = dao.findAll();

		for (Product p : products) {
			p.setUnderdate();
			p.setNow();
			p.setCount(0);
			dao.save(p);

		}
		model.addObject("count", 0);
		model.addObject("bid");
		model.addObject("allBooks", products);
		return model;
	}

	@RequestMapping(value = { "/bookChooseCount" }, method = RequestMethod.POST)
	public ModelAndView chooseBooksCount(@ModelAttribute("bid") Long id,
			@ModelAttribute("count") int count) {

		ModelAndView model = new ModelAndView("bookList");
		Iterable<ProductCategory> categories = categoryDao.findAll();
		model.addObject("allBookCategories", categories);
		ProductCategory category = categories.iterator().next();// get first //
																// category
		model.addObject("bookCategory", category);

		Iterable<Product> products = dao.findAll();
		Product productcount = dao.findOne(id);
		productcount.setCount(count);
		dao.save(productcount);
		for (Product p : products) {
			p.setUnderdate();
			p.setNow();
			dao.save(p);

		}
		model.addObject("allBooks", products);
		return model;
	}

	@RequestMapping(value = { "/bookRetrievebySale" }, method = RequestMethod.GET)
	public ModelAndView retrieveBooksbySale() {
		ModelAndView model = new ModelAndView("bookList");
		Iterable<ProductCategory> categories = categoryDao.findAll();
		model.addObject("allBookCategories", categories);
		ProductCategory category = categories.iterator().next();// get first
																// category
		model.addObject("bookCategory", category);
		Iterable<Product> products = dao.findAll();
		for (Product p : products) {
			p.setUnderdate();
			dao.save(p);
		}
		Iterable<Product> pros = dao.findAll();
		ArrayList<Product> prossale = new ArrayList<Product>();
		for (Product ps : pros) {
			if (ps.getUnderdate().equals("是")) {
				prossale.add(ps);
			}
		}
		model.addObject("allBooks", prossale);
		return model;
	}

	@RequestMapping(value = { "/bookRetrieveByCategory" }, method = RequestMethod.POST)
	public ModelAndView retrieveProductsByCategory(
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id,
			@ModelAttribute("memid") String memid) {

		/*
		 * public ModelAndView retrieveBooksByCategory(@ModelAttribute
		 * BookCategory category) {
		 */
		ModelAndView model = new ModelAndView("bookList");
		// BookCategory category = new BookCategory();
		// category.setId(id);
		// Iterable<Book> books = dao.findByBookCategory(category);

		// System.out.println(category.getName());
		Iterable<ProductCategory> categories = categoryDao.findAll();
		model.addObject("allBookCategories", categories);
		ProductCategory category = categoryDao.findOne(id);
		model.addObject("bookCategory", category);

		model.addObject("allBooks", category.getProducts());
		// model.addObject("allBooks",books);
		// model.addObject(allBooks);
		model.addObject(memid);
		return model;
	}

	@RequestMapping(value = "/booksearch", method = RequestMethod.POST)
	public ModelAndView searchBooksbyBook(
			@RequestParam(value = "name", required = false, defaultValue = "1") String name,
			@ModelAttribute("memid") String memid) {
		ModelAndView model = new ModelAndView("bookList");
		Iterable<Product> typelist = userRepository.findByNameLike(name);

		Iterable<ProductCategory> categories = categoryDao.findAll();
		model.addObject("allBookCategories", categories);
		ProductCategory category = categories.iterator().next();// get first
																// category
		model.addObject("bookCategory", category);

		model.addObject("allBooks", typelist);
		model.addObject(memid);
		return model;
	}

	@RequestMapping(value = "/bookUpdate", method = RequestMethod.GET)
	public ModelAndView openFormUpdate(
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("bookUpdate");
		Product product = dao.findOne(id);
		model.addObject(product);
		Iterable<ProductCategory> categories = categoryDao.findAll();

		model.addObject("allBookCategories", categories);

		return model;
	}

	@RequestMapping(value = "/bookUpdate", method = RequestMethod.POST)
	public ModelAndView processFormUpdate(@ModelAttribute Product product) {
		// ModelAndView model = new ModelAndView("bookDone");
		ModelAndView model = new ModelAndView("redirect:/bookRetrieveAll");
		dao.save(product);

		return model;
	}

	@RequestMapping(value = "/bookDelete", method = RequestMethod.GET)
	public ModelAndView deleteBook(
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("redirect:/bookRetrieveAll");

		dao.delete(id);

		return model;
	}

	@RequestMapping(value = "/shoppingCartAdd", method = RequestMethod.GET)
	public ModelAndView addShoppingCart(
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id,
			@RequestParam(value = "count", required = false, defaultValue = "1") int count) {
		ModelAndView model = new ModelAndView("redirect:/bookRetrieveAll");
		Product product = dao.findOne(id);
		product.setCount(count);
		dao.save(product);
		cart.add(product);
		return model;
	}

	@RequestMapping(value = "/shoppingCartList", method = RequestMethod.GET)
	public ModelAndView showShoppingCart(@ModelAttribute("memid") String memid) {
		ModelAndView model = new ModelAndView("shoppingCart");
		model.addObject(memid);
		return model;
	}

	@RequestMapping(value = "/cleanShoppingCart", method = RequestMethod.GET)
	public ModelAndView cleanShoppingCart(@ModelAttribute("memid") String memid) {
		ModelAndView model = new ModelAndView("redirect:/shoppingCartList");
		model.addObject(memid);
		cart.cleanup();
		return model;
	}

	@RequestMapping(value = "/cartDelete", method = RequestMethod.GET)
	public ModelAndView deletecart(
			@RequestParam(value = "id", required = false, defaultValue = "1") String id,
			@ModelAttribute("memid") String memid) {
		ModelAndView model = new ModelAndView("redirect:/shoppingCartList");
		int id2 = Integer.parseInt(id);
		cart.remove(id2);
		model.addObject(memid);
		return model;
	}

	@RequestMapping(value = "/orderAdd", method = RequestMethod.GET)
	public ModelAndView addOrder(@ModelAttribute("memid") String memid,
			HttpSession session) {
		ModelAndView model = new ModelAndView("redirect:/orderRetrieveBycus");
		String memname = (String) session.getAttribute("loginId");
		Long oid;
		Order order = new Order();
		Iterable<Member> members = memDao.findAll();
		for (Member m : members) {
			if (m.getName().equals(memname)) {
				order.setMember(m);
				oid = m.getId();
				model.addObject(oid);
				break;
			}
		}
		order.setOrderdate(3);
		orderDao.save(order);
		Iterable<Product> products = cart.getCart();
		for (Product p : products) {
			MyKey mk = new MyKey();
			mk.setOrderid(order.getId());

			mk.setProduct(p);

			Orderdetail od = new Orderdetail();
			od.setMyKey(mk);
			od.setCount(p.getCount());
			od.setOrder(order);
			if (p.getUnderdate().equals("是")) {
				od.setPrice(p.getSale());
			} else {
				od.setPrice(p.getPrice());
			}
			int temp2 = od.getCount() * od.getPrice();
			od.setStotal(temp2);
			orderdeDao.save(od);
		}
		cart.cleanup();
		return model;
	}

	@RequestMapping(value = { "/orderRetrieveBycus" }, method = RequestMethod.GET)
	public ModelAndView retrieveOrdersBycus(
			@RequestParam(value = "oid", required = false, defaultValue = "1") Long oid,
			HttpSession session) {
		ModelAndView model = new ModelAndView("orderListforCus");
		Iterable<Order> orders = orderDao.findAll();
		for (Order o : orders) {
			o.setTotal();
			orderDao.save(o);
		}
		Iterable<Member> members = memDao.findAll();
		model.addObject("allMembers", members);
		String memname = (String) session.getAttribute("loginId");
		for (Member m : members) {
			if (m.getName().equals(memname)) {
				oid = m.getId();
				model.addObject(oid);
				break;
			}
		}
		Member member = memDao.findOne(oid);
		model.addObject("oid", oid);
		model.addObject("member", member);
		model.addObject("allorders", member.getOrders());

		return model;
	}

	@RequestMapping(value = { "/orderdetailRetrievecus" }, method = RequestMethod.POST)
	public ModelAndView retrieveOrderdetailsBycus(
			@RequestParam(value = "oid", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("orderdetailListforCus");

		Iterable<Order> orders = orderDao.findAll();
		model.addObject("allOrders", orders);
		Order order = orderDao.findOne(id);
		model.addObject("order", order);
		model.addObject("allorderdetails", order.getOrderdetails());
		return model;
	}

}
