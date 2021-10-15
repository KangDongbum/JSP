package com.dao;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;

import com.core.*;
import com.dto.Blog;

public class Blog_dao {
	
	public int write(HttpServletRequest req) {
		int postNm =0;
		String sql ="INSERT INTO posting (postTitle, postWriter, content) VALUES(?,?,?)";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql,
			Statement.RETURN_GENERATED_KEYS)) {
			req.setCharacterEncoding("UTF-8");
			String postTitle = req.getParameter("postTitle");
			String postWriter = req.getParameter("postWriter");
			String content =  req.getParameter("content");

			pstmt.setString(1, postTitle);
			pstmt.setString(2, postWriter);
			pstmt.setString(3, content);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					postNm = rs.getInt(1);
				}
			
				rs.close();
			}
		}catch(IOException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return postNm;
	}
	
	public ArrayList<Blog> getList(){
		return getList(1);
	}
	public ArrayList<Blog> getList(int page) {
		ArrayList<Blog> list = new ArrayList<>();
		int limit = 5;
		
		page = (page == 0)?1:page;
		int offset = (page -1) * limit;
		
		String sql = "SELECT * FROM posting ORDER BY postNm DESC LIMIT ?, ?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1,offset);
			pstmt.setInt(2, limit);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Blog(rs));
			}
			rs.close();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return  list;
	}
	
	public boolean delete(int postNm) {
		
		String sql = "DELETE FROM posting WHERE postNm = ?";
		try(Connection conn = DB.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, postNm);
			
			int rs = pstmt.executeUpdate();
			if(rs > 0) {
				return true;
			}
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Blog get(int postNm) {
		
		Blog blog = null;
		String sql = "SELECT * FROM posting WHERE postNm = ?";
			try(Connection conn = DB.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)){
					pstmt.setInt(1, postNm);
					ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					blog = new Blog(rs);
				}
				rs.close();
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return blog;		
	}
	
public boolean edit(HttpServletRequest req) {
		
		if(req == null) {
			return false;
		}
	
		String sql = "UPDATE posting SET postTitle = ?, postWriter = ?, content=? WHERE postNm=?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			req.setCharacterEncoding("UTF-8");
			int postNm = Integer.parseInt(req.getParameter("postNm").trim());
			pstmt.setString(1, req.getParameter("postTitle"));
			pstmt.setString(2, req.getParameter("postWriter"));
			pstmt.setString(3, req.getParameter("content"));
			pstmt.setInt(4,postNm);
			
			int rs = pstmt.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}