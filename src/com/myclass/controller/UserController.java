package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.ApplicationContext;

import com.myclass.constant.AttributeConstant;
import com.myclass.constant.UrlConstant;
import com.myclass.constant.ViewConstant;
import com.myclass.entity.Task;
import com.myclass.entity.User;
import com.myclass.service.IRoleService;
import com.myclass.service.IStatusService;
import com.myclass.service.ITaskService;
import com.myclass.service.IUserService;

@WebServlet(urlPatterns = {
		UrlConstant.URL_USER,
		UrlConstant.URL_USER_ADD,
		UrlConstant.URL_USER_DELETE,
		UrlConstant.URL_USER_DETAILS,
		UrlConstant.URL_USER_EDIT
})
public class UserController extends HttpServlet{
	IUserService 	userService;
	IRoleService 	roleService;
	ITaskService 	taskService;
	IStatusService 	statusService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		ApplicationContext context = (ApplicationContext) req.getAttribute(AttributeConstant.CONTEXT);
		
		userService 	= (IUserService) context.getBean(AttributeConstant.USER_SERVICE);
		roleService 	= (IRoleService) context.getBean(AttributeConstant.ROLE_SERVICE);
		taskService 	= (ITaskService) context.getBean(AttributeConstant.TASK_SERVICE);
		statusService 	= (IStatusService) context.getBean(AttributeConstant.STATUS_SERVICE);
		
		switch (path) {
		case UrlConstant.URL_USER_DETAILS:
			if(req.getParameter(AttributeConstant.ID) == null) {
				resp.sendRedirect(req.getContextPath() + UrlConstant.URL_USER);
				break;
			}
			req.setAttribute(AttributeConstant.USER, userService.getUserById(Integer.parseInt(req.getParameter(AttributeConstant.ID))));
			
			List<Task> taskListWithUserId = taskService.getAllTaskOfUserId(Integer.parseInt(req.getParameter(AttributeConstant.ID)));
			req.setAttribute(AttributeConstant.TASK_LIST, taskListWithUserId);
			
			req.setAttribute(AttributeConstant.TASK_CHUA_BAT_DAU, taskService.getNumberOfStatus(taskListWithUserId,1));
			req.setAttribute(AttributeConstant.TASK_DANG_THUC_HIEN, taskService.getNumberOfStatus(taskListWithUserId,2));
			req.setAttribute(AttributeConstant.TASK_DA_HOAN_THANH, taskService.getNumberOfStatus(taskListWithUserId,3));
			
			req.getRequestDispatcher(ViewConstant.VIEWS_USER_DETAIL).forward(req, resp);
			break;
		case UrlConstant.URL_USER:
			
			req.setAttribute(AttributeConstant.USER_DTO_LIST, userService.getAllUserDto());
			
			req.getRequestDispatcher(ViewConstant.VIEWS_USER).forward(req, resp);
			break;
		case UrlConstant.URL_USER_ADD:
			
			req.setAttribute(AttributeConstant.ROLE_LIST, roleService.getAllRole());
			
			req.getRequestDispatcher(ViewConstant.VIEWS_USER_ADD).forward(req, resp);
			break;
		case UrlConstant.URL_USER_EDIT:
						
			req.setAttribute(AttributeConstant.USER, userService.getUserById(Integer.parseInt(req.getParameter(AttributeConstant.ID))));
			
			req.setAttribute(AttributeConstant.ROLE_LIST, roleService.getAllRole());
			
			req.getRequestDispatcher(ViewConstant.VIEWS_USER_EDIT).forward(req, resp);
			break;
		case UrlConstant.URL_USER_DELETE:
			
			userService.removeUserById(Integer.parseInt(req.getParameter(AttributeConstant.ID)));
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_USER);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		User user;
		
		switch (path) {
		case UrlConstant.URL_USER_EDIT:			
			user = new User();
			
			user.setEmail(req.getParameter(AttributeConstant.EMAIL));
			user.setFullname(req.getParameter(AttributeConstant.FULLNAME));
			user.setAvatar(req.getParameter(AttributeConstant.AVATAR));
			user.setRole_id(Integer.parseInt(req.getParameter(AttributeConstant.ROLE_ID)));
			
			userService.updateUserById(user,Integer.parseInt(req.getParameter(AttributeConstant.ID)));
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_USER);
			break;
		case UrlConstant.URL_USER_ADD:
			user = new User();
			
			user.setEmail(req.getParameter(AttributeConstant.EMAIL));
			user.setFullname(req.getParameter(AttributeConstant.FULLNAME));
			
			String hashed = BCrypt.hashpw(req.getParameter(AttributeConstant.PASSWORD), BCrypt.gensalt());
			
			user.setPassword(hashed);
			user.setRole_id(Integer.parseInt(req.getParameter(AttributeConstant.ROLE_ID)));
			user.setAvatar(req.getParameter(AttributeConstant.AVATAR));
			
			userService.addNewUser(user);
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_USER);
			break;
		default:
			break;
		}
	}
}
