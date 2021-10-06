package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.core.FileManager;

/**
 * 파일 업로드 처리
 * 
 * GET - 업로드 양식
 * POST - 업로드 처리
 */
public class FileUploadController extends HttpServlet {
	private static final long serialVersionUID = 7549029732379909351L;

	/** 업로드 양식 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException{
		RequestDispatcher rd = req.getRequestDispatcher("/board/upload.jsp");
		rd.include(req, res);
	}
	
	/** 업로드 처리 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		String uploadedFiles = FileManager.upload(req);
		if(uploadedFiles == null) { // 업로드된 파일이 없는 경우, 이미지 파일이 아닌 경우
			out.print("<script>alert('업로드 실패!');</script>");
		} else{ // 업로드가 된 경우
			out.print("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>");
			out.printf("<script>parent.parent.callbackUploadImages('%s')</script>", uploadedFiles);
		}
	}
}
