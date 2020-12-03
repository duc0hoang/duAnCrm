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
import com.myclass.entity.Job;
import com.myclass.service.IJobService;
import com.myclass.service.ITaskService;
@WebServlet(urlPatterns = {
		UrlConstant.URL_JOB,
		UrlConstant.URL_JOB_ADD,
		UrlConstant.URL_JOB_DELETE,
		UrlConstant.URL_JOB_DETAILS,
		UrlConstant.URL_JOB_EDIT
})
public class JobController extends HttpServlet{
	IJobService jobService;
	ITaskService taskService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		ApplicationContext context = (ApplicationContext) req.getAttribute(AttributeConstant.CONTEXT);
		
		jobService		= (IJobService) context.getBean(AttributeConstant.JOB_SERVICE);
		taskService 	= (ITaskService) context.getBean(AttributeConstant.TASK_SERVICE);
		
		switch (path) {
		case UrlConstant.URL_JOB:
			
			req.setAttribute(AttributeConstant.JOB_LIST, jobService.getAllJob());
			
			req.getRequestDispatcher(ViewConstant.VIEWS_JOB).forward(req, resp);
			break;
		case UrlConstant.URL_JOB_ADD:
			
			req.getRequestDispatcher(ViewConstant.VIEWS_JOB_ADD).forward(req, resp);
			break;
		case UrlConstant.URL_JOB_EDIT:
			req.setAttribute(AttributeConstant.JOB, jobService.getJobById(Integer.parseInt(req.getParameter(AttributeConstant.ID))));
			
			req.getRequestDispatcher(ViewConstant.VIEWS_JOB_EDIT).forward(req, resp);
			break;
		case UrlConstant.URL_JOB_DELETE:
			
			jobService.removeJobById(Integer.parseInt(req.getParameter(AttributeConstant.ID)));
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_JOB);
			break;
		case UrlConstant.URL_JOB_DETAILS:
			
			req.setAttribute(AttributeConstant.TASK_LIST, taskService.getAllTaskOfJobId(Integer.parseInt(req.getParameter(AttributeConstant.ID))));
			
//			req.setAttribute(AttributeConstant.STATUS_LIST, statusService.getAllStatus());
			
			req.getRequestDispatcher(ViewConstant.VIEWS_JOB_DETAIL).forward(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		int id;
		Job job;
		
		switch (path) {
		case UrlConstant.URL_JOB_ADD:
			job = new Job();
			
			job.setName(req.getParameter(AttributeConstant.NAME));
			job.setStartDate(req.getParameter(AttributeConstant.START_DATE));
			job.setEndDate(req.getParameter(AttributeConstant.END_DATE));
			
			jobService.addNewJob(job);
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_JOB);
			break;
		case UrlConstant.URL_JOB_EDIT:
			id = Integer.parseInt(req.getParameter(AttributeConstant.ID));
			
			job = new Job();
			
			job.setName(req.getParameter(AttributeConstant.NAME));
			job.setStartDate(req.getParameter(AttributeConstant.START_DATE));
			job.setEndDate(req.getParameter(AttributeConstant.END_DATE));
			
			jobService.updateJobById(job,id);
			
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_JOB);
			break;
		default:
			break;
		}
	}
}
