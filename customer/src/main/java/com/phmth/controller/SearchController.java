package com.phmth.controller;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/search")
public class SearchController extends HttpServlet {
	CustomerService customerService = new CustomerService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int page = 1;
		int limit = 2;
		try {
			String fullname = req.getParameter("fullname");
			String sex = req.getParameter("sex");
			String birthdayFirst = req.getParameter("birthdayFirst");
			String birthdayLast = req.getParameter("birthdayLast");
			FormSearch formSearch = new FormSearch();
			formSearch.setFullname(fullname);
			formSearch.setSex(sex);
			formSearch.setBirthdayFirst(birthdayFirst);
			formSearch.setBirthdayLast(birthdayLast);

			AccountModel accountLogin = (AccountModel) session.getAttribute("accountLogin");
			if (accountLogin != null) {

				if (req.getParameter("page") != null && !req.getParameter("page").isEmpty()) {
					page = Integer.parseInt(req.getParameter("page").toString());
				}

				List<CustomerModel> listCustomer = customerService.getCustomer(formSearch, (page - 1) * limit, limit);
				int total = customerService.countCustomer(formSearch);
				int totalPage = (int) Math.ceil((double) total / limit);
				req.setAttribute("listCustomer", listCustomer);
				req.setAttribute("currentPage", page);
				req.setAttribute("totalPage", totalPage);
				req.setAttribute("fullname", fullname);
				req.setAttribute("sex", sex);
				req.setAttribute("birthdayFirst", birthdayFirst);
				req.setAttribute("birthdayLast", birthdayLast);
				RequestDispatcher rd = req.getRequestDispatcher("/views/search.jsp");
				rd.forward(req, resp);

			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullname = req.getParameter("fullname");
		String sex = req.getParameter("sex");
		String birthdayFirst = req.getParameter("birthdayFirst");
		String birthdayLast = req.getParameter("birthdayLast");
		if (req.getParameter("btnDelete") != null) {
			String[] listCheckBoxItem = req.getParameterValues("checkBoxItem");
			if (listCheckBoxItem != null) {
				for (int i = 0; i < listCheckBoxItem.length; i++) {
					customerService.deleteCustomer(listCheckBoxItem[i]);
				}
			} else {
				req.setAttribute("errorDelete", "Not Null");
			}
		}
		if (req.getParameter("btnAdd") != null) {
			resp.sendRedirect(req.getContextPath() + "/edit?fullname=" + fullname + "&sex=" + sex + "&birthdayFirst="
					+ birthdayFirst + "&birthdayLast=" + birthdayLast);
		}
		req.setAttribute("fullname", fullname);
		req.setAttribute("sex", sex);
		req.setAttribute("birthdayFirst", birthdayFirst);
		req.setAttribute("birthdayLast", birthdayLast);
		doGet(req, resp);
	}
}
