package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.Blog;
import com.dao.Blog_dao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ListController extends HttpServlet{
	private static final long serialVersionUID = -3027579283402981939L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");

		Blog_dao dao = new Blog_dao();
		ArrayList<Blog> list = dao.getList();
		req.setAttribute("list", list);
		
		RequestDispatcher rd = req.getRequestDispatcher("/board/list.jsp");
		rd.include(req, res);
	}

}
