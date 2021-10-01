package com.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SiteMainFilter implements Filter{
	/**
	 *  사이트 전역필터
	 *  
	 */
	@Override
	public void init(FilterConfig ficf) throws ServletException{
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		
		String method = null;
		if(req instanceof HttpServletRequest) {
			HttpServletRequest hsreq = (HttpServletRequest)req; 
			method = hsreq.getMethod().toUpperCase();
		}
	
		// 전처리
		if(method != null && method.equals("GET")) {
			RequestDispatcher header = req.getRequestDispatcher("/outline/header.jsp");
			header.include(req, res);
		}
		
		chain.doFilter(req, res);
		
		// 후처리
		if(method != null && method.equals("GET")) {
			RequestDispatcher footer = req.getRequestDispatcher("/outline/footer.jsp");
			footer.include(req, res);
		}
		
	}
}
