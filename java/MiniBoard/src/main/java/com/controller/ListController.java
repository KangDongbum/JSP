package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;


import com.model.dao.BoardDAO;
import com.model.dto.Board;

public class ListController extends HttpServlet{
	private static final long serialVersionUID = -3027579283402981939L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException{
		
		BoardDAO dao = new BoardDAO();
		ArrayList<Board> list = dao.getList();
		req.setAttribute("list", list);
		
		RequestDispatcher rd = req.getRequestDispatcher("/board/list.jsp");
		rd.include(req, res);
	}
}
