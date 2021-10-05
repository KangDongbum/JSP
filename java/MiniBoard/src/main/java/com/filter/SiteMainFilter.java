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

import com.core.*;

public class SiteMainFilter implements Filter{
	/**
	 *  사이트 전역필터
	 *  
	 */
	private FilterConfig ficf;
	@Override
	public void init(FilterConfig ficf) throws ServletException{
		this.ficf = ficf;
		
		/** 데이터 베이스 설정 초기화 */
		DB.init(ficf);
		
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		
		// 사이트 root URL
		String siteURL = req.getServletContext().getContextPath();
		req.setAttribute("siteURL",siteURL);
		
		String method = null;
		boolean isScriptCss = false;
		if(req instanceof HttpServletRequest) {
			HttpServletRequest hsreq = (HttpServletRequest)req; 
			method = hsreq.getMethod().toUpperCase();
			String URI = hsreq.getRequestURI();
			// js(자바스크립트), css(스타일시트) 헤더, 푸터가 추가되지 않도록 처리
			if(URI.indexOf(".js") != -1 || URI.indexOf(".css") != -1 || URI.indexOf(".png") != -1) {
				isScriptCss = true;
			}
		}
	
		// 전처리
		if(method != null && method.equals("GET") && !isScriptCss) {
			RequestDispatcher header = req.getRequestDispatcher("/outline/header.jsp");
			header.include(req, res);
		}
		
		chain.doFilter(req, res);
		
		// 후처리
		if(method != null && method.equals("GET") && !isScriptCss) {
			RequestDispatcher footer = req.getRequestDispatcher("/outline/footer.jsp");
			footer.include(req, res);
		}
		
	}
}
