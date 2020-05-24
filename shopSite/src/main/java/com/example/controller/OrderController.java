package com.example.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.MemberDAO;
import com.example.dao.OrderDAO;
import com.example.dao.OrderdetailDAO;
import com.example.dao.ProductDAO;
import com.example.dao.ProductdetailDAO;
import com.example.entity.Member;
import com.example.entity.Order;
import com.example.entity.Orderdetail;
import com.example.entity.Product;
import com.example.entity.Productdetail;

@Controller
public class OrderController {

	@Autowired
	MemberDAO dao;

	@Autowired
	ProductdetailDAO prodeDao;

	@Autowired
	ProductDAO proDao;

	@Autowired
	OrderDAO orderDao;

	@Autowired
	OrderdetailDAO orderdetailDao;

	@RequestMapping(value = { "/admin/orderRetrieveAll", }, method = RequestMethod.GET)
	public ModelAndView retrieveOrder() throws SQLException {

		Iterable<Order> orders = orderDao.findAll();
		ModelAndView model = new ModelAndView("admin/orderList");
		Iterable<Member> members = dao.findAll();
		model.addObject("allMembers", members);
		Member member = members.iterator().next();// get first category
		model.addObject("member", member);
		model.addObject("allorders", orders);
		String situation = "未出貨";
		model.addObject("situation",situation);
		model.addObject("oid");
		return model;
	}

	@RequestMapping(value = { "/admin/orderRetrieveBymember" }, method = RequestMethod.POST)
	public ModelAndView retrieveOrdersBymember(
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("admin/orderList");

		Iterable<Member> members = dao.findAll();
		model.addObject("allMembers", members);
		Member member = dao.findOne(id);
		model.addObject("member", member);
		model.addObject("allorders", member.getOrders());
		model.addObject("oid");
		return model;
	}

	@RequestMapping(value = { "/admin/orderdetailRetrieveByorder" }, method = RequestMethod.POST)
	public ModelAndView retrieveOrderdetailsByorder(
			@RequestParam(value = "oid", required = false, defaultValue = "1") Long id) {
		ModelAndView model = new ModelAndView("admin/orderdetailList");

		Iterable<Order> orders = orderDao.findAll();
		model.addObject("allOrders", orders);
		Order order = orderDao.findOne(id);
		model.addObject("order", order);
		model.addObject("allorderdetails", order.getOrderdetails());
		return model;
	}

	@RequestMapping(value = { "/admin/orderReach", }, method = RequestMethod.POST)
	public ModelAndView reachOrder(
			@RequestParam(value = "oid", required = false, defaultValue = "1") Long id) {
		Order order = orderDao.findOne(id);
		order.setSituation("已到貨");
		orderDao.save(order);
		ModelAndView model = new ModelAndView("redirect:/admin/orderRetrieveAll");
		return model;
	}

	@RequestMapping(value = { "/admin/orderGo", }, method = RequestMethod.POST)
	public ModelAndView goOrder(
			@RequestParam(value = "oid", required = false, defaultValue = "1") Long id) {
		Order order = orderDao.findOne(id);
		order.setSituation("出貨中");

		for (Orderdetail ordet : order.getOrderdetails()) {
			int tempCount = ordet.getCount();
			Product pro = ordet.getMyKey().getProduct();
			Iterable<Productdetail> prodels = pro.getProductdetails();
			ArrayList<Productdetail> prodear = new ArrayList<Productdetail>();
			for (Productdetail prodel : prodels) {
				prodear.add(prodel);
			}
			Productdetail tempDate = null;
			for (int i = prodear.size() - 1; i > 0; --i) {
				for (int j = 0; j < i; ++j) {
					if (prodear.get(j + 1).getExpire()
							.before(prodear.get(j).getExpire())) {
						tempDate = prodear.get(j);
						prodear.set(j, prodear.get(j + 1));
						prodear.set(j + 1, tempDate);
					}
				}
			}
			if (tempCount <= pro.getInventory()) {
				int i = 0;
				while (tempCount != 0) {
					if (tempCount < prodear.get(i).getInventory()) {
						prodear.get(i).setInventory(
								prodear.get(i).getInventory() - tempCount);
						pro.setInventory();
						tempCount = 0;
						proDao.save(pro);
						prodeDao.save(prodear.get(i));
					} else {
						tempCount -= prodear.get(i).getInventory();
						prodear.get(i).setInventory(0);
						prodeDao.save(prodear.get(i));
						i++;
					}
				}
			} else {
				order.setSituation(pro.getName() + "存貨不足");
			}
		}
		orderDao.save(order);
		ModelAndView model = new ModelAndView("redirect:/admin/orderRetrieveAll");
		return model;
	}
	@RequestMapping(value = { "/admin/orderRetrieveBysituation", }, method = RequestMethod.POST)
	public ModelAndView orderRetrieveBysituation(
			@ModelAttribute(value = "situation") String situation) {		
		Iterable<Order> orders = orderDao.findAll();
		
		ModelAndView model = new ModelAndView("admin/orderList");
		model.addObject("situation",situation);
		Iterable<Member> members = dao.findAll();
		model.addObject("allMembers", members);
		Member member = members.iterator().next();// get first category
		model.addObject("member", member);		
		model.addObject("oid");
		ArrayList<Order> sitar = new ArrayList<Order>();
		for(Order o:orders){
			if(o.getSituation().equals(situation)){
				sitar.add(o);
			}
		}
		Iterable<Order> ordersits = sitar;
		model.addObject("allorders", ordersits);
		return model;
	}
}
