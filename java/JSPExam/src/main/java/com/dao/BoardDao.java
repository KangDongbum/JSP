package com.dao;

import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.dto.*;
import com.core.*;

public class BoardDao {
	
	public int getTotal() {
		int total = 0;
		
		String sql = "SELECT COUNT(*) cnt FROM board";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt("cnt");
			}
			rs.close();
			
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return total;
	}
	
	public ArrayList<Board> getList(int page, int limit){
		ArrayList<Board> list = new ArrayList<>();
		page = (page <= 0)?1:page;
		limit = (limit <= 0)?15:limit;
		
		int offset=(page -1) * limit;
		
		String  sql = "SELECT * FROM board ORDER by idx DESC LIMIT ?,?";
		try(Connection conn = DB.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, offset);
			pstmt.setInt(2, limit);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Board(rs));
			}
			rs.close();
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<Board> getList(int page){
		
		return getList(page, 15);
	}
	
	public ArrayList<Board> getList(HttpServletRequest req){
		int page = 1;
		if(req.getParameter("page")!=null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		return getList(page);
	}
}