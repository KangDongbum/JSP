package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.model.dto.Board;
import com.model.dao.BoardDAO;

public class EditController extends HttpServlet{
	private static final long serialVersionUID = -1L;
	/** 게시글 수정 양식 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		if(req.getParameter("idx") == null) {
			out.print("<script>alert('잘못된 접근을 하셨습니다.');history.back();</script>");
			return;
		}
		int idx = Integer.parseInt(req.getParameter("idx")); // 게시글 번호
		BoardDAO dao = new BoardDAO();
		Board board = dao.get(idx);
		if(board == null) { // 없는 게시글 일때
			out.print("<scrip>alert('없는 게시글 입니다.');history.back();</script>");
			return;
		}
		
		req.setAttribute("board", board);
		req.setAttribute("action", "edit");
		
		RequestDispatcher rd = req.getRequestDispatcher("/board/form.jsp");
		rd.include(req, res);
	}
	
	/** 게시글 수정 처리 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		
		int idx = Integer.parseInt(req.getParameter("idx").trim());
		
		PrintWriter out = res.getWriter();
		
		BoardDAO dao = new BoardDAO();
		boolean result = dao.edit(req);
		
		if(result) { // 수정 성공
			out.print("<script>parent.location.href='view?idx="+idx+"';</script>");
		} else { // 수정 실패
			out.print("<script>alert('수정실패!!');</script>");
		}
	}
}
