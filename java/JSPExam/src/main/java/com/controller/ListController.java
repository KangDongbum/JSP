package com.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

import com.dao.*;
import com.dto.*;
import com.core.*;

public class ListController extends HttpServlet{
		protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			res.setContentType("text/html; charset=utf-8");
			BoardDao dao = new BoardDao();
			ArrayList<Board> list = dao.getList(req);
			int total = dao.getTotal();
			
			Pagination pagination = new Pagination(req, total);
			String pagingHtml = pagination.getPageHtml();
			
			req.setAttribute("list",list);
			req.setAttribute("pagingHtml",pagingHtml);
			
			RequestDispatcher rd= req.getRequestDispatcher("/list.jsp");
			rd.forward(req, res);
		}
}
