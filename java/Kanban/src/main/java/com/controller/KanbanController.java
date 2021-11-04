package com.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 		/kanban 컨트롤러
 *
 */
public class KanbanController extends HttpServlet{
	
	private String httpMethod; // 요청 메소드
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		
		String URI = req.getRequestURI();
		String mode = URI.substring(URI.lastIndexOf("/") +1);
		
		httpMethod = req.getMethod().toUpperCase(); // GET, POST
		
		if(httpMethod.equals("GET")) {
			res.setContentType("text/html; charset=utf-8");
		} else {
			req.setCharacterEncoding("utf-8"); 
		}
	
		switch(mode) {
			case"work": //작업목록
				workController(req,res);
				break;
			case"add": //작업등록
				addController(req,res);
				break;
			case"edit": //작업수정
				editController(req,res);
				break;
			case"remove": //작업 삭제
				removeController(req,res);
				break;
			default : // 없는 페이지
				RequestDispatcher rd = req.getRequestDispatcher("/view/error/404.jsp");
				rd.forward(req,res);
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		doGet(req, res);
	}
	
	/** 작업 목록 **/
	private void workController(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		RequestDispatcher rd = req.getRequestDispatcher("/view/kanban/main.jsp");
		rd.include(req, res);
	}
	
	/** 작업 추가 **/
	private void addController(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		if(httpMethod.equals("POST")) { //등록 처리
			
		} else { //등록 양식
			RequestDispatcher rd = req.getRequestDispatcher("/view/kanban/form.jsp");
			rd.forward(req, res);
		}
	}
	
	/** 작업 수정 **/
	private void editController(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		if(httpMethod.equals("POST")) {//수정 처리
			
		}else { //수정 양식
			RequestDispatcher rd = req.getRequestDispatcher("/view/kanban/form.jsp");
			rd.include(req, res);
		}
	}
	
	/** 작업 삭제 **/
	private void removeController(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			
	}
}
