package com.phmth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phmth.service.AccountService;

@SuppressWarnings("serial")
@WebServlet("/logout")
public class logoutController extends HttpServlet{
	AccountService accountService = new AccountService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			session.removeAttribute("accountLogin");
			resp.sendRedirect(req.getContextPath()+"/login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
