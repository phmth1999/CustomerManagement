package com.phmth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phmth.model.AccountModel;
import com.phmth.model.CustomerModel;
import com.phmth.service.CustomerService;

@SuppressWarnings("serial")
@WebServlet("/edit")
public class EditCustomerController extends HttpServlet {
	CustomerService customerService = new CustomerService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int page = 1;
		int customerID = 0;
		try {
			String fullnamePageSearch = req.getParameter("fullname");
			String sexPageSearch = req.getParameter("sex");
			String birthdayFirstPageSearch = req.getParameter("birthdayFirst");
			String birthdayLastPageSearch = req.getParameter("birthdayLast");
			AccountModel accountLogin = (AccountModel) session.getAttribute("accountLogin");
			if (accountLogin != null) {
				if (req.getParameter("currentPageSearch") != null && req.getParameter("currentPageSearch") != "") {
					page = Integer.parseInt(req.getParameter("currentPageSearch").toString());
				}
				if (req.getParameter("customerID") != null && req.getParameter("customerID") != "") {
					customerID = Integer.parseInt(req.getParameter("customerID").toString());
				}
				if (customerID <= 0) {
					req.setAttribute("add", "add");
				} else {
					req.setAttribute("edit", "edit");
					req.setAttribute("customer", customerService.getCustomerById(customerID));
				}
				req.setAttribute("currentPageSearch", page);
				req.setAttribute("fullnamePageSearch", fullnamePageSearch);
				req.setAttribute("sexPageSearch", sexPageSearch);
				req.setAttribute("birthdayFirstPageSearch", birthdayFirstPageSearch);
				req.setAttribute("birthdayLastPageSearch", birthdayLastPageSearch);
				RequestDispatcher rd = req.getRequestDispatcher("/views/edit.jsp");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int customerID = 0;
		try {
			if (req.getParameter("id") != null && req.getParameter("id") != "") {
				customerID = Integer.parseInt(req.getParameter("id").toString());
			}
			String fullname = req.getParameter("fullname").toString();
			String sex = req.getParameter("sex").toString();
			String birthday = req.getParameter("birthday").toString();
			String email = req.getParameter("email").toString();
			String phone = req.getParameter("phone").toString();
			String address = req.getParameter("address").toString();
			int currentPageSearch = Integer.parseInt(req.getParameter("currentPageSearch").toString());

			CustomerModel customerModel = new CustomerModel();
			customerModel.setId(customerID);
			customerModel.setFullname(fullname);
			customerModel.setSex(sex);
			customerModel.setBirthday(birthday);
			customerModel.setEmail(email);
			customerModel.setPhone(phone);
			customerModel.setAddress(address);
			if (customerID > 0) {
				customerService.editCustomer(customerModel);
			} else {
				customerService.addCustomer(customerModel);
			}
			String fullnamePageSearch = req.getParameter("fullnamePageSearch");
			String sexPageSearch = req.getParameter("sexPageSearch");
			String birthdayFirstPageSearch = req.getParameter("birthdayFirstPageSearch");
			String birthdayLastPageSearch = req.getParameter("birthdayLastPageSearch");
			resp.sendRedirect(req.getContextPath() + "/search?page=" + currentPageSearch + "&?fullname="
					+ fullnamePageSearch + "&sex=" + sexPageSearch + "&birthdayFirst=" + birthdayFirstPageSearch
					+ "&birthdayLast=" + birthdayLastPageSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
