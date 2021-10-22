package com.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 *	/member/* 컨트롤러
 * 
 */
public class MemberController extends HttpServlet{
	
	private String httpMethod; // Http 요청 메소드, GET, POST
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		
		/** 요청 메소드 */
		httpMethod = req.getMethod().toUpperCase();
		if(httpMethod.equals("GET")){
			res.setContentType("text/html; charset=utf-8");
		} else { //GET이 아닌 경우 -> 유입된 엽력 양식 데이터 처
			req.setCharacterEncoding("utf-8");
		}
		
		String URI = req.getRequestURI();
		String mode = URI.substring(URI.lastIndexOf("/")+1);
		switch(mode) {
		case"join": //회원가입
			joinController(req,res);
			break;
		case"info": // 회원정보 수정
			infoController(req,res);
			break;
		case"login": //로그인
			loginController(req,res);
			break;
		case"findid": //아이디찾기
			findidController(req,res);
			break;
		case"findpw": //비밀번호 찾기
			findpwController(req,res);
			break;
		default: //없는 페이지
			RequestDispatcher rd =req.getRequestDispatcher("/view/error/404.jsp");
			rd.forward(req,res);
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		doGet(req,res);
	}
	
	/**
	 * 회원 가입 /member/join
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	private void joinController(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		if(httpMethod.equals("GET")) { //양식 출력
			RequestDispatcher rd = req.getRequestDispatcher("/view/member/form.jsp");
			rd.include(req, res);
		} else { //양식 처리
			
		}
	}
	
	/**
	 * 회원 정보 수정 /member/info
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	private void infoController(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			
		}

	/**
	 *  로그인 /member/login
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	private void loginController(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			
		}
	
	/**
	 * 아이디 찾기 /member/findid 
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	private void findidController(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			
		}
	
	/**
	 * 비밀번호 찾기 /member/findpw
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	private void findpwController(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			
		}
}