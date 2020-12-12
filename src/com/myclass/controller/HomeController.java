package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.myclass.constant.AttributeConstant;
import com.myclass.constant.UrlConstant;
import com.myclass.constant.ViewConstant;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;
import com.myclass.service.IJobService;
import com.myclass.service.ITaskService;
import com.myclass.service.IUserService;

@WebServlet(urlPatterns = {
		UrlConstant.URL_HOME
})
public class HomeController extends HttpServlet{
	ITaskService taskService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		ApplicationContext context = (ApplicationContext) req.getAttribute(AttributeConstant.CONTEXT);
		
		taskService 	= (ITaskService) context.getBean(AttributeConstant.TASK_SERVICE);
		
		switch (path) {
		case UrlConstant.URL_HOME:
			
			List<Task> taskList =  taskService.getAllTask();
			
			req.setAttribute(AttributeConstant.TASK_LIST, taskList);
			
			req.setAttribute(AttributeConstant.TASK_CHUA_BAT_DAU, taskService.getNumberOfStatus(taskList,1));
			req.setAttribute(AttributeConstant.TASK_DANG_THUC_HIEN, taskService.getNumberOfStatus(taskList,2));
			req.setAttribute(AttributeConstant.TASK_DA_HOAN_THANH, taskService.getNumberOfStatus(taskList,3));
			
			req.getRequestDispatcher(ViewConstant.VIEWS_HOME).forward(req, resp);
			break;
		default:
			break;
		}
	}
}
