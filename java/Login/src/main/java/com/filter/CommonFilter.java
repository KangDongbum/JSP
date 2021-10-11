package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.core.DB;
/**
 * 사이트 공통 필터
 *
 */
public class CommonFilter implements Filter{

	// 정적 경로 -> 헤더, 푸터 출력 X
	private String[] staticDirs = {"public"};
	
	@Override
	public void init(FilterConfig ficf) {
		DB.init(ficf);
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
		throws ServletException, IOException{
		
		/** 기준 URI */
		String siteURL = req.getServletContext().getContextPath();
		req.setAttribute("siteURL", siteURL);
		
		// 헤더 출력
		printHeader(req,res);
		
		chain.doFilter(req, res); // 다음 필터로 넘어가거나 없으면 서블릿 넘어간다.

		// 푸터 출력
		printFooter(req,res);
	}

	/** 헤더 HTML 출력 */
	public void printHeader(ServletRequest req, ServletResponse res) 
		throws ServletException, IOException{
		if(isPrintOk(req)) { // 출력 가능 조건 일때만 출력
			res.setContentType("text/html; charset=utf-8");
		
			RequestDispatcher rd = req.getRequestDispatcher("/outline/header.jsp");
			rd.include(req, res);
		}
	}
	
	/** 푸터 HTML 출력 */
	public void printFooter(ServletRequest req, ServletResponse res)
		throws ServletException, IOException{
		if(isPrintOk(req)) {
			RequestDispatcher rd = req.getRequestDispatcher("/outline/footer.jsp");
			rd.include(req, res);
		}
	}
	
	/** 헤더, 푸터 를 출력해도 되는지 체크하는 메소드 */
	public boolean isPrintOk(ServletRequest req){
		/**
		 *  0. 요청 method이 GET이 아닌 경우 출력 제외(return false)
		 *  1. HttpServletRequest - getMethod()
		 *  2. 정적 경로인 경우 헤더 푸터 출력 제외(return false)
		 *  	URI에 정적 경로가 포함되어 있으면 false
		 *  	HttpServletRequest - getRequestURI();
		 */
		if(req instanceof  HttpServletRequest) {
			HttpServletRequest req2 = (HttpServletRequest)req;
			if(!req2.getMethod().toUpperCase().equals("GET")) {
				return false;
			}
			
			String URI = req2.getRequestURI();
			for(String dir : staticDirs) {
				if(URI.indexOf("/"+dir)!= -1) { // 정적 경로가 포함되어 있으면
					return false;
				}
			}
		}
		
		return true;
	}
}