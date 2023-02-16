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
import com.phmth.service.AccountService;

@SuppressWarnings("serial")
@WebServlet("/login")
public class loginController extends HttpServlet {
	AccountService accountService = new AccountService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			String userName = req.getParameter("username").toString();
			String password = req.getParameter("password").toString();
			req.setAttribute("userName", userName);
			req.setAttribute("password", password);
			if (accountService.checkUserNameExist(userName)) {
				if (accountService.login(userName, password) != null) {
					AccountModel accountLogin = accountService.login(userName, password);
					session.setAttribute("accountLogin", accountLogin);
					resp.sendRedirect(req.getContextPath() + "/search");
				} else {
					req.setAttribute("errMessage", "Password not correct!");
					RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
					rd.forward(req, resp);
				}
			} else {
				req.setAttribute("errMessage", "Username not exist!");
				RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
