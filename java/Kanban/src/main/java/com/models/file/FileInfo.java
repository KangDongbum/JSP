package com.models.file;

import java.sql.*;
import javax.servlet.ServletRequest;
import java.io.*;

import com.models.*;

public class FileInfo extends Dto<FileInfo> {
	private int idx; // 파일 등록번호
	private Long gid; // 파일 그룹 ID
	private String originalName; // 원 파일명
	private String mimeType; // 파일 형식
	private String regDt; // 파일 업로드 일시
	private String uploadedPath; // 실제 업로드된 파일 경로
	private String uploadedUrl; //업로드된 파일 접속 URL
	private boolean isImage = false; // 이미지 여부
	private static ServletRequest request;
	
	public FileInfo() {}
	
	public FileInfo(int idx, Long gid, String originalName, String mimeType, String regDt) {
		this.idx = idx;
		this.gid = gid;
		this.originalName = originalName;
		this.mimeType = mimeType;
		this.regDt = regDt;
		
		isImage = (mimeType.indexOf("image") != -1)?true:false;
		
		String rootURL = (String)request.getAttribute("rootURL");
		String rootPath = (String)request.getAttribute("rootPath");
		int folder = idx % 10;
		StringBuilder sb = new StringBuilder();
		sb.append(rootPath);
		sb.append(File.separator);
		sb.append("resources");
		sb.append(File.separator);
		sb.append("upload");
		sb.append(File.separator);
		sb.append(folder);
		sb.append(File.separator);
		sb.append(idx);
		uploadedPath = sb.toString();
		uploadedUrl = rootURL + "/resources/upload/" + folder + "/" + idx;
	}
	
	public static void init(ServletRequest req) {
		FileInfo.request = req;
	}
	
	public FileInfo(ResultSet rs) throws SQLException{
		this(
				rs.getInt("idx"),
				rs.getLong("gid"),
				rs.getString("originalName"),
				rs.getString("mimeType"),
				rs.getString("regDt")
		);
	}
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getUploadedPath() {
		return uploadedPath;
	}

	public void setUploadedPath(String uploadedPath) {
		this.uploadedPath = uploadedPath;
	}

	public String getUploadedUrl() {
		return uploadedUrl;
	}

	public void setUploadedUrl(String uploadedUrl) {
		this.uploadedUrl = uploadedUrl;
	}

	public boolean isImage() {
		return isImage;
	}

	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}

	@Override
	public FileInfo setResultSet(ResultSet rs) throws SQLException {
		return new FileInfo(rs);
	}
}