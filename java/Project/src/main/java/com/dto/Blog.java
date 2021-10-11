package com.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Blog {
	private int postNm;
	private String postTitle;
	private String postWriter;
	private String content;
	private String postregDt;
	
	public Blog() {}
	
	public Blog(ResultSet rs) throws SQLException {
		if(rs != null) {
			postNm = rs.getInt("postNm");
			postTitle = rs.getString("postTitle");
			postWriter = rs.getString("postWriter");
			content = rs.getString("content");
			Timestamp date = rs.getTimestamp("postregDt"); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			postregDt = sdf.format(date);
		}
	}
	
	public Blog(int postNm, String postTitle, String postWriter, String content, String postregDt) {
		this.postNm = postNm;
		this.postTitle = postTitle;
		this.postWriter = postWriter;
		this.content = content;
		this.postregDt = postregDt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPostNm() {
		return postNm;
	}

	public void setPostNm(int postNm) {
		this.postNm = postNm;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostWriter() {
		return postWriter;
	}

	public void setPostWriter(String postWriter) {
		this.postWriter = postWriter;
	}

	public String getPostregDt() {
		return postregDt;
	}

	public void setPostregDt(String postregDt) {
		this.postregDt = postregDt;
	}
}
