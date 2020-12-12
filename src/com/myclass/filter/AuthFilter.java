package com.myclass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.myclass.config.WebConfig;
import com.myclass.constant.AttributeConstant;
import com.myclass.constant.UrlConstant;
import com.myclass.dto.UserLoginDto;
@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		
		ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
		
		req.setAttribute(AttributeConstant.CONTEXT, context);
		
		String path = req.getServletPath();
		
		if(UrlConstant.URL_LOGIN.equals(path)) {
			chain.doFilter(request, response);
			return;
		}
		
		UserLoginDto userLoginDto = (UserLoginDto) req.getSession().getAttribute(AttributeConstant.USER_LOGIN_DTO);
		
		if( userLoginDto == null) {
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_LOGIN);
			return;
		}	
		
		String roleName = userLoginDto.getRole_name();
		
		if(path.startsWith(UrlConstant.URL_ROLE) && !AttributeConstant.ROLENAME_ADMIN.equals(roleName)) {
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_ERROR_403);
			return;
		}
		
		if(path.startsWith(UrlConstant.URL_USER) && !AttributeConstant.ROLENAME_ADMIN.equals(roleName)
				&&!AttributeConstant.ROLENAME_MANAGER.equals(roleName)) {
			resp.sendRedirect(req.getContextPath() + UrlConstant.URL_ERROR_403);
			return;
		}
		
		chain.doFilter(request, response);
		
	}

}
