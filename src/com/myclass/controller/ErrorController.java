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
		UrlConstant.URL_ERROR_403
})
public class ErrorController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case UrlConstant.URL_ERROR_403:
			req.getRequestDispatcher(ViewConstant.VIEWS_ERROR_403).forward(req, resp);
			break;
		default:
			break;
		}
	}
}
