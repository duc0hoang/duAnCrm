package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.constant.UrlConstant;
import com.myclass.constant.ViewConstant;

@WebServlet(urlPatterns = {
		UrlConstant.URL_HOME
})
public class HomeController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case UrlConstant.URL_HOME:
			
			req.getRequestDispatcher(ViewConstant.VIEWS_HOME).forward(req, resp);
			break;
		default:
			break;
		}
	}
}
