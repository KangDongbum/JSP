package com.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import com.core.Logger;
import com.models.member.*;

/**
 *	/member/* 컨트롤러
 * 
 */
public class MemberController extends HttpServlet{
	
	private String httpMethod; // Http 요청 메소드, GET, POST
	private PrintWriter out;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		
		/** 요청 메소드 */
		httpMethod = req.getMethod().toUpperCase();
		if(httpMethod.equals("GET")){
			res.setContentType("text/html; charset=utf-8");
		} else { //GET이 아닌 경우 -> 유입된 엽력 양식 데이터 처
			res.setContentType("text/html; charset=utf-8");
		}
		
		/** PrintWriter 공통 */
		out = res.getWriter();
		
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
		case"logout": //로그아웃
			logoutController(req,res);
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
		res.setContentType("text/html; charset=utf-8");
		
		if(httpMethod.equals("GET")) { //양식 출력
			RequestDispatcher rd = req.getRequestDispatcher("/view/member/form.jsp");
			rd.include(req, res);
		} else { //양식 처리
			res.setContentType("text/html; charset=utf-8");
			MemberDao dao = MemberDao.getInstance();
			try {
				boolean result = dao.join(req);
				System.out.println(result);
				if(result == false) { // 가입 실패
					throw new Exception("가입에 실패하였습니다.");
				}else {// 가입 성공 -> 로그인 페이지
					out.printf("<script>parent.location.replace('%s');</script>","../index.jsp");
				}
				
			} catch(Exception e) {
				res.setContentType("text/html; charset=utf-8");
				out.printf("<script>alert('%s');</script>",e.getMessage());
				Logger.log(e);
			}
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
		if(httpMethod.equals("GET")) {
			RequestDispatcher rd = req.getRequestDispatcher("/view/main/index.jsp");
			rd.include(req,res);
		}else {
				MemberDao dao = MemberDao.getInstance();
				try {
					dao.login(req);
					out.printf("<script>parent.location.replace('%s')</script>", "../kanban/work");
				}catch(Exception e) {
					Logger.log(e);
					out.printf("<script>alert('%s')</script>", e.getMessage());
				}
			}
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
			res.setContentType("text/html; charset=utf-8");
			
			if(httpMethod.equals("POST")) {
				MemberDao dao = MemberDao.getInstance();
				try {
					String memId = dao.findId(req);
					req.setAttribute("memId", memId);
				} catch (Exception e) {
					Logger.log(e);
					out.printf("<script>alert('%s');</script>", e.getMessage());
				}
			}
			
			RequestDispatcher rd = req.getRequestDispatcher("/view/member/findid.jsp");
			rd.include(req,res);		
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
			
			if(httpMethod.equals("GET")) { // 비밀번호 찾기 양식
				RequestDispatcher rd = req.getRequestDispatcher("/view/member/findpw.jsp");
				rd.include(req, res);
			}else { // 일치 하는 회원 검증 -> 비밀번호 초기화 페이지로 이동
				MemberDao dao = MemberDao.getInstance();
				dao.findPw(req);
			}
		}
	
	/**
	 * 로그아웃
	 * 
	 * @param req
	 * @param res
	 * @throws ServltException
	 * @throws IOException
	 */
	private void logoutController(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		MemberDao dao = MemberDao.getInstance();
		dao.logout(req);
		PrintWriter out = res.getWriter();
		out.printf("<script>location.replace('%s');</script>","../index.jsp");
	}
}