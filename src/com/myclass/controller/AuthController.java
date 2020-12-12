package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.ApplicationContext;

import com.myclass.constant.AttributeConstant;
import com.myclass.constant.UrlConstant;
import com.myclass.constant.ViewConstant;
import com.myclass.dto.UserLoginDto;
import com.myclass.entity.Role;
import com.myclass.entity.Task;
import com.myclass.entity.User;
import com.myclass.service.IAuthService;
import com.myclass.service.IRoleService;
import com.myclass.service.ITaskService;
@WebServlet(urlPatterns = {
		UrlConstant.URL_LOGIN,
		UrlConstant.URL_LOGOUT
})
public class AuthController extends HttpServlet{
	IAuthService authService;
	IRoleService roleService;
	ITaskService taskService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		ApplicationContext context = (ApplicationContext) req.getAttribute(AttributeConstant.CONTEXT);
		
		authService 	= (IAuthService) context.getBean(AttributeConstant.AUTH_SERVICE);
		roleService 	= (IRoleService) context.getBean(AttributeConstant.ROLE_SERVICE);
		taskService 	= (ITaskService) context.getBean(AttributeConstant.TASK_SERVICE);
		
		switch (path) {
		case UrlConstant.URL_LOGIN:
			req.getRequestDispatcher(ViewConstant.VIEWS_LOGIN).forward(req, resp);
			break;
		case UrlConstant.URL_LOGOUT:
			HttpSession session = req.getSession();
			session.removeAttribute(AttributeConstant.SESSION_LOGIN);
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_LOGIN);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter(AttributeConstant.EMAIL);
		String password = req.getParameter(AttributeConstant.PASSWORD);
		
		User user = authService.login(email);
		
		if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
			req.setAttribute(AttributeConstant.MESSAGE, AttributeConstant.MESSAGE_LOGIN_FALSE);
			req.getRequestDispatcher(ViewConstant.VIEWS_LOGIN).forward(req, resp);
			return;
		}
		
		Role role = roleService.getRoleById(user.getId());
		
		UserLoginDto userLoginDto = new UserLoginDto(user.getId(),user.getEmail()
				,user.getPassword(),user.getFullname(),user.getAvatar(),role.getName(), role.getDescription());
		
		List<Task> taskList =  taskService.getAllTask();
		
		req.setAttribute(AttributeConstant.TASK_LIST, taskList);
		
		req.setAttribute(AttributeConstant.TASK_CHUA_BAT_DAU, taskService.getNumberOfStatus(taskList,1));
		req.setAttribute(AttributeConstant.TASK_DANG_THUC_HIEN, taskService.getNumberOfStatus(taskList,2));
		req.setAttribute(AttributeConstant.TASK_DA_HOAN_THANH, taskService.getNumberOfStatus(taskList,3));
		
		HttpSession session = req.getSession();
		session.setAttribute(AttributeConstant.USER_LOGIN_DTO, userLoginDto);
		session.setMaxInactiveInterval(6000);
		
		req.getRequestDispatcher(ViewConstant.VIEWS_HOME).forward(req, resp);
	}
}
