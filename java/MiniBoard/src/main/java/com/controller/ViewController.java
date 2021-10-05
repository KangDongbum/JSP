package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.model.dao.BoardDAO;
import com.model.dto.Board;
/**
 * 게시글 보기
 *
 */
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 2407700498643200315L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		// 게시글 번호가 없으면 메세지 출력 -> 게시글 목록 이동
		if(req.getParameter("idx") == null) {
			out.print("<script>alert('잘못된 접근을 하셨습니다.');location.href='list'</script>");
			return;
		}
		
		// 게시글 번호 -> 게시글 조회
		BoardDAO dao = new BoardDAO();
		int idx = Integer.parseInt(req.getParameter("idx"));
		Board board = dao.get(idx);
		req.setAttribute("board", board);
		
		RequestDispatcher rd = req.getRequestDispatcher("/board/view.jsp");
		rd.include(req, res);
	}
}