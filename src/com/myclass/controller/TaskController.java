package com.myclass.controller;

import java.io.IOException;

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
import com.myclass.service.IJobService;
import com.myclass.service.IStatusService;
import com.myclass.service.ITaskService;
import com.myclass.service.IUserService;
import com.myclass.service.StatusService;
@WebServlet(urlPatterns = {
		UrlConstant.URL_TASK,
		UrlConstant.URL_TASK_ADD,
		UrlConstant.URL_TASK_DELETE,
		UrlConstant.URL_TASK_EDIT
})
public class TaskController extends HttpServlet{
	ITaskService taskService;
	IJobService jobService;
	IUserService userService;
	IStatusService statusService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		ApplicationContext context = (ApplicationContext) req.getAttribute(AttributeConstant.CONTEXT);
		
		taskService 	= (ITaskService) context.getBean(AttributeConstant.TASK_SERVICE);
		jobService		= (IJobService) context.getBean(AttributeConstant.JOB_SERVICE);
		userService		= (IUserService) context.getBean(AttributeConstant.USER_SERVICE);
		statusService		= (IStatusService) context.getBean(AttributeConstant.STATUS_SERVICE);
		
		switch (path) {
		case UrlConstant.URL_TASK:
			req.setAttribute(AttributeConstant.TASK_DTO_LIST, taskService.getAllTaskWithFullName());
			
			req.getRequestDispatcher(ViewConstant.VIEWS_TASK).forward(req, resp);
			break;
		case UrlConstant.URL_TASK_ADD:
			req.setAttribute(AttributeConstant.JOB_LIST, jobService.getAllJob());
			req.setAttribute(AttributeConstant.USER_DTO_LIST, userService.getAllUserDto());
			req.getRequestDispatcher(ViewConstant.VIEWS_TASK_ADD).forward(req, resp);
			break;
		case UrlConstant.URL_TASK_DELETE:
			
			taskService.removeTaskById(Integer.parseInt(req.getParameter(AttributeConstant.ID)));
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_TASK);
			break;
		case UrlConstant.URL_TASK_EDIT:
			
			req.setAttribute(AttributeConstant.TASK, taskService.getTaskById(Integer.parseInt(req.getParameter(AttributeConstant.ID))));
			
			req.setAttribute(AttributeConstant.JOB_LIST, jobService.getAllJob());
			req.setAttribute(AttributeConstant.USER_DTO_LIST, userService.getAllUserDto());
			req.setAttribute(AttributeConstant.STATUS_LIST, statusService.getAllStatus());
			
			req.getRequestDispatcher(ViewConstant.VIEWS_TASK_EDIT).forward(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		Task task;
		
		switch (path) {
		case UrlConstant.URL_TASK_ADD:
			task = new Task();
			
			task.setName(req.getParameter(AttributeConstant.NAME));
			task.setStartDate(req.getParameter(AttributeConstant.START_DATE));
			task.setEndDate(req.getParameter(AttributeConstant.END_DATE));
			task.setUserId(Integer.parseInt(req.getParameter(AttributeConstant.USER_ID)));
			task.setJobId(Integer.parseInt(req.getParameter(AttributeConstant.JOB_ID)));
			task.setStatusId(1);
			
			taskService.addNewTask(task);
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_TASK);
			break;
		case UrlConstant.URL_TASK_EDIT:
			task = new Task();
			
			task.setName(req.getParameter(AttributeConstant.NAME));
			task.setStartDate(req.getParameter(AttributeConstant.START_DATE));
			task.setEndDate(req.getParameter(AttributeConstant.END_DATE));
			task.setUserId(Integer.parseInt(req.getParameter(AttributeConstant.USER_ID)));
			task.setJobId(Integer.parseInt(req.getParameter(AttributeConstant.JOB_ID)));
			task.setStatusId(Integer.parseInt(req.getParameter(AttributeConstant.STATUS_ID)));
			
			taskService.updateTaskById(task,Integer.parseInt(req.getParameter(AttributeConstant.ID)));
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_TASK);
			break;
		default:
			break;
		}
	}
}
