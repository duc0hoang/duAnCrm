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
import com.myclass.service.IStatusService;
import com.myclass.service.ITaskService;
import com.myclass.service.IUserService;
@WebServlet(urlPatterns = {
		UrlConstant.URL_PROFILE,
		UrlConstant.URL_PROFILE_TASK,
		UrlConstant.URL_PROFILE_TASK_EDIT
})
public class ProfileController extends HttpServlet{
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
		statusService	= (IStatusService) context.getBean(AttributeConstant.STATUS_SERVICE);
		
		switch (path) {
		case UrlConstant.URL_PROFILE:
			
			req.getRequestDispatcher(ViewConstant.VIEWS_PROFILE).forward(req, resp);
			break;
		case UrlConstant.URL_PROFILE_TASK:
			if(req.getParameter(AttributeConstant.ID) == null) {
				resp.sendRedirect(req.getContextPath() + UrlConstant.URL_HOME);
				break;
			}
			
			List<TaskDto> taskDtoListWithUserId = taskService.getAllTaskDtoOfUserId(Integer.parseInt(req.getParameter(AttributeConstant.ID)));
			req.setAttribute(AttributeConstant.TASK_DTO_LIST, taskDtoListWithUserId);
			
			req.setAttribute(AttributeConstant.TASK_CHUA_BAT_DAU, taskService.getNumberOfStatusWithTaskDto(taskDtoListWithUserId,1));
			req.setAttribute(AttributeConstant.TASK_DANG_THUC_HIEN, taskService.getNumberOfStatusWithTaskDto(taskDtoListWithUserId,2));
			req.setAttribute(AttributeConstant.TASK_DA_HOAN_THANH, taskService.getNumberOfStatusWithTaskDto(taskDtoListWithUserId,3));
			
			req.getRequestDispatcher(ViewConstant.VIEWS_PROFILE_TASK).forward(req, resp);
			break;
		case UrlConstant.URL_PROFILE_TASK_EDIT:
			
			req.setAttribute(AttributeConstant.TASK, taskService.getTaskById(Integer.parseInt(req.getParameter(AttributeConstant.ID))));
			
			req.setAttribute(AttributeConstant.JOB_LIST, jobService.getAllJob());
			req.setAttribute(AttributeConstant.USER_DTO_LIST, userService.getAllUserDto());
			req.setAttribute(AttributeConstant.STATUS_LIST, statusService.getAllStatus());
			
			req.getRequestDispatcher(ViewConstant.VIEWS_PROFILE_TASK_EDIT).forward(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case UrlConstant.URL_PROFILE_TASK_EDIT:
			Task task = new Task();
			
			task.setName(req.getParameter(AttributeConstant.NAME));
			task.setStartDate(req.getParameter(AttributeConstant.START_DATE));
			task.setEndDate(req.getParameter(AttributeConstant.END_DATE));
			task.setUserId(Integer.parseInt(req.getParameter(AttributeConstant.USER_ID)));
			task.setJobId(Integer.parseInt(req.getParameter(AttributeConstant.JOB_ID)));
			task.setStatusId(Integer.parseInt(req.getParameter(AttributeConstant.STATUS_ID)));
			taskService.updateTaskById(task,Integer.parseInt(req.getParameter(AttributeConstant.ID)));
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_PROFILE_TASK);
			break;
		default:
			break;
		}
	}
}
