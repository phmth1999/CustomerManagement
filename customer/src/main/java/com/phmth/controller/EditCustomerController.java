package com.phmth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phmth.dto.FormSearch;
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
		int customerID = 0;
		try {
			AccountModel accountLogin = (AccountModel) session.getAttribute("accountLogin");
			if (accountLogin != null) {
				if (req.getParameter("customerID") != null && req.getParameter("customerID") != "") {
					customerID = Integer.parseInt(req.getParameter("customerID").toString());
				}
				if (customerID <= 0) {
					req.setAttribute("add", "add");
				} else {
					req.setAttribute("edit", "edit");
					req.setAttribute("customer", customerService.getCustomerById(customerID));
				}
				RequestDispatcher rd = req.getRequestDispatcher("/views/edit.jsp");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
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
				FormSearch formSearch = (FormSearch) session.getAttribute("formSearch");
				resp.sendRedirect(req.getContextPath() + "/search?page=" + formSearch.getPage() + "&?fullname="
						+ formSearch.getFullname() + "&sex=" + formSearch.getSex() + "&birthdayFirst=" + formSearch.getBirthdayFirst()
						+ "&birthdayLast=" + formSearch.getBirthdayLast());
			} else {
				customerService.addCustomer(customerModel);
				resp.sendRedirect(req.getContextPath() + "/search");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
