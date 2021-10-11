package com.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import com.core.DB;

public class SiteFilter implements Filter{
	private FilterConfig ficf;
	private String[] stdi = { "public" };
	public void init(FilterConfig ficf) throws ServletException{
		this.ficf = ficf;
		
		DB.init(ficf);
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
		throws ServletException, IOException{
		String blogpage = req.getServletContext().getContextPath();
		req.setAttribute("blogpage",blogpage);
		
		header(req,res);
		
		chain.doFilter(req, res);
		
		footer(req,res);
	}
	
	public void header(ServletRequest req, ServletResponse res) {
		try {
			if(output(req)) {
				res.setContentType("text/html; charset=utf-8");
				RequestDispatcher header = req.getRequestDispatcher("/outline/header.jsp");
				header.include(req,res);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void footer(ServletRequest req, ServletResponse res) {
		try {
			if(output(req)) {
				res.setContentType("text/html; charset=utf-8");
				RequestDispatcher footer = req.getRequestDispatcher("/outline/footer.jsp");
				footer.include(req,res);
			}
	 	} catch(ServletException | IOException e) {
	 		e.printStackTrace();
	 	}
	}
	
	
	public boolean output(ServletRequest req) {
		if(req instanceof HttpServletRequest) {
			HttpServletRequest hsreq = (HttpServletRequest)req;
			
			String method = hsreq.getMethod().toUpperCase();
			if(!method.equals("GET")) {
				return false;
			}
			
			String URI = hsreq.getRequestURI();
			for(String dir : stdi) {
				if(URI.indexOf("/" + dir) != -1) {
					return false;
					}
				}
			}
		return true;
	}
}