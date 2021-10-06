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
/**
 *  사이트 전역필터
 *  
 */
public class SiteMainFilter implements Filter{
	private FilterConfig ficf;
	private String[] staticDris = { "resources" }; // 정적 디렉토리(헤더, 푸터 제외)
	@Override
	public void init(FilterConfig ficf) throws ServletException{
		this.ficf = ficf;
		
		/** 데이터 베이스 설정 초기화 */
		DB.init(ficf);
		
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	throws ServletException, IOException{
		// 사이트 root URL
		String siteURL = req.getServletContext().getContextPath();
		req.setAttribute("siteURL",siteURL);
		
		// 헤더 출력
		outlineHeader(req, res);
		
		chain.doFilter(req, res);
		
		// 푸터 출력
		outlineFooter(req, res);	
	}
	
	/**
	 * 공통 헤더 처리
	 * 
	 * @param req
	 * @param res
	 */
	
	public void outlineHeader(ServletRequest req, ServletResponse res)
	throws ServletException, IOException{
		if(isOutlineRequired(req)){ // 헤더 추가 조건일 때만 추가
			res.setContentType("text/html; charset=utf-8");
			String headerFile = isPopup(req)?"/outline/popup_header.jsp":"/outline/header.jsp";
			RequestDispatcher header = req.getRequestDispatcher(headerFile);
			header.include(req, res);
		}
	}
	
	/**
	 * 공통 푸터 처리
	 * 
	 * @param req
	 * @param res
	 */
	public void outlineFooter(ServletRequest req, ServletResponse res) 
		throws ServletException, IOException{
		if(isOutlineRequired(req)) { //푸터 추가조건일 때 추가
			String footerFile = isPopup(req)?"/outline/popup_footer.jsp":"/outline/footer.jsp";
			RequestDispatcher footer = req.getRequestDispatcher(footerFile);
			footer.include(req, res);
		}
	}
	
	/**
	 * 헤더, 푸터가 필요한지 여부 체크
	 * 
	 * @param req
	 * @return
	 */
	public boolean isOutlineRequired(ServletRequest req) {
		/**
		 * 1. GET 방식이 아닌 경우 false (HttpSErvletRequest - getMethod())
		 * 2. 정적 경로에 해당하는 URI 인 경우 false (HttpServletRequest - getRequestURI())
		 */
		if(req instanceof HttpServletRequest) {
			HttpServletRequest req2 = (HttpServletRequest)req;
			
			/** 메소드가 GET 방식이 아닌 경우 헤더, 푸터 제외 */
			String method = req2.getMethod().toUpperCase();
			if(!method.equals("GET")) {
				return false;
			}
			
			/** RequestURI에 정적 디렉토리가 포함되어 있으면 제외*/
			String URI = req2.getRequestURI();
			for(String dir : staticDris) {
				if(URI.indexOf("/" + dir) != -1) { // 정적 디렉토리가 포함된 경우
					return false;
				}
			}
					
		}
		return true;
	}
	/**
	 * 팝업 페이지인지 체크
	 * 
	 * @param req
	 * @return true - 팝업, false - 일반 페이지
	 */
	public boolean isPopup(ServletRequest req) {
		if(req instanceof HttpServletRequest) {
			HttpServletRequest req2 = (HttpServletRequest)req;
			String URI = req2.getRequestURI();
			if(URI.indexOf("/popup") != -1) { // URI 경로에 popup이 포함된 경우
				return true;
			}
		}
		return false;
	}
}