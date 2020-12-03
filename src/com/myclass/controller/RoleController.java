package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.myclass.constant.AttributeConstant;
import com.myclass.constant.UrlConstant;
import com.myclass.constant.ViewConstant;
import com.myclass.entity.Role;
import com.myclass.service.IRoleService;
@WebServlet(urlPatterns = {
		UrlConstant.URL_ROLE,
		UrlConstant.URL_ROLE_ADD,
		UrlConstant.URL_ROLE_DELETE,
		UrlConstant.URL_ROLE_EDIT
})
public class RoleController extends HttpServlet{
	IRoleService roleService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		int id;
		ApplicationContext context = (ApplicationContext) req.getAttribute(AttributeConstant.CONTEXT);
		
		roleService 	= (IRoleService) context.getBean(AttributeConstant.ROLE_SERVICE);
		
		switch (path) {
		case UrlConstant.URL_ROLE:
			req.setAttribute(AttributeConstant.ROLE_LIST, roleService.getAllRole());
			
			req.getRequestDispatcher(ViewConstant.VIEWS_ROLE).forward(req, resp);
			break;
		case UrlConstant.URL_ROLE_ADD:
			
			req.getRequestDispatcher(ViewConstant.VIEWS_ROLE_ADD).forward(req, resp);
			break;
		case UrlConstant.URL_ROLE_DELETE:
			
			roleService.removeRoleById(Integer.parseInt(req.getParameter(AttributeConstant.ID)));
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_ROLE);
			break;
		case UrlConstant.URL_ROLE_EDIT:
			
			req.setAttribute(AttributeConstant.ROLE, roleService.getRoleById(Integer.parseInt(req.getParameter(AttributeConstant.ID))));
			
			req.getRequestDispatcher(ViewConstant.VIEWS_ROLE_EDIT).forward(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		Role role;
		
		switch (path) {
		case UrlConstant.URL_ROLE_ADD:
			role = new Role();
			
			role.setName(req.getParameter(AttributeConstant.NAME));
			role.setDescription(req.getParameter(AttributeConstant.DESCRIPTION));
			
			roleService.addNewRole(role);
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_ROLE);
			break;
		case UrlConstant.URL_ROLE_EDIT:
			int id = Integer.parseInt(req.getParameter(AttributeConstant.ID));
			
			role = roleService.getRoleById(id);
			
			role.setName(req.getParameter(AttributeConstant.NAME));
			role.setDescription(req.getParameter(AttributeConstant.DESCRIPTION));
			
			roleService.updateRoleById(role,id);
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_ROLE);
			break;
		default:
			break;
		}
	}
}
