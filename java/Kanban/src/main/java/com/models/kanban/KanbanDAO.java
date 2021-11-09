package com.models.kanban;

import java.util.*;
import javax.servlet.http.*;

import com.core.*;
import com.models.member.*;
import com.models.file.*;

public class KanbanDAO {
	
	private static KanbanDAO instance = new KanbanDAO();
	private ArrayList<FileInfo> attachFiles = null; // 첨부파일 목록
	
	private KanbanDAO() {};
	
	public static KanbanDAO getInstance() {
		if (instance == null) {
			instance = new KanbanDAO();
		}
		
		return instance;
	}
	
	/**
	 * 작업 목록 추가 
	 * 
	 * @param request
	 * @return
	 */
	public boolean add(HttpServletRequest request) throws Exception {
	
		HashMap<String, String> params = FileUpload.getInstance().upload(request).get();
		
		/** 유효성 검사 S */
		String[] required = {
				"status//작업구분을 선택하세요",
				"subject//제목을 입력하세요.",
				"content//작업내용을 입력하세요",
		};
		for (String s : required) {
			String[] param = s.split("//");
			String value = params.get(param[0]);
			if (value == null || value.trim().equals("")) {
				throw new Exception(param[1]);
			}
		}
		/** 유혀성 검사 E */
		
		int memNo = 0;
		if (request.getAttribute("member") != null) {
			Member member = (Member)request.getAttribute("member");
			memNo = member.getMemNo();
		}
		
		String sql = "INSERT INTO worklist (gid, memNo, status, subject, content) VALUES(?,?,?,?,?)";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Long", params.get("gid")));
		bindings.add(DB.setBinding("Integer", String.valueOf(memNo)));
		bindings.add(DB.setBinding("String", params.get("status")));
		bindings.add(DB.setBinding("String", params.get("subject")));
		bindings.add(DB.setBinding("String", params.get("content")));
		
		int rs = DB.executeUpdate(sql, bindings);
		
		return (rs  > 0)?true:false;
	}
	
	/**
	 * 작업 목록 조회 
	 * 
	 * @param status
	 * @return
	 */
	public ArrayList<Kanban> getList(String status) {
		ArrayList<DBField> bindings = new ArrayList<>();
		String sql = "SELECT a.*, b.memId, b.memNm FROM worklist a LEFT JOIN member b  ON a.memNo = b.memNo";
		if (status != null) {
			sql += "WHERE a.status = ?";
			bindings.add(DB.setBinding("String", status));
		}
		
		sql += " ORDER BY a.regDt DESC";
		
		ArrayList<Kanban> list = DB.executeQuery(sql, bindings, new Kanban());
		
		return list;
	}
	
	public ArrayList<Kanban> getList() {
		return getList(null);
	}
	
	/**
	 * 작업 상세
	 * 
	 * @param idx 작업 등록번호
	 * @return
	 */
	public Kanban get(int idx) {
		
		String sql = "SELECT a.*, b.memId, b.memNm FROM worklist a LEFT JOIN member b ON a.memNo = b.memNo WHERE a.idx=?";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer", String.valueOf(idx)));
		Kanban data = DB.executeQueryOne(sql, bindings, new Kanban());
		
		/** 첨부 파일 S */
		if(data != null) {
			long gid = data.getGid();
			attachFiles = FileUpload.getInstance().getFiles(gid);
		}
		/** 첨부 파일 E */
		return data;
	}
	
	public Kanban get(HttpServletRequest req) {
		int idx = 0;
		if(req.getParameter("idx") != null) {
			idx = Integer.valueOf(req.getParameter("idx"));
		}
		return get(idx);
	}
	
	/**
	 * 작업 상세 첨부파일 목록
	 * 
	 * @return
	 */
	public ArrayList<FileInfo> getAttachFiles(){
		
		return attachFiles;
	}
	
	/**
	 * 작업 삭제
	 * 
	 * @param idx 작업 등록번호
	 * @return
	 */
	public boolean delete(int idx) {
		/**
		 * 	0. 작업 정보
		 *  1. 첨부 파일 삭제 - gid
		 *  2. 작업 내용 삭제
		 */
		Kanban data = get(idx);
		if(data == null) {
			return false;
		}
		
		FileUpload fileUpload = FileUpload.getInstance();
		ArrayList<FileInfo> list = getAttachFiles();
		for(FileInfo file : list) {
			fileUpload.delete(file.getIdx());
		}
		
		String sql = "DELETE FROM worklist WHERE idx = ?";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer", String.valueOf(idx)));
		int rs = DB.executeUpdate(sql, bindings);
		
		return (rs > 0)?true:false;
	}
	
	public boolean delete(HttpServletRequest req) {
		int idx = 0;
		if(req.getParameter("idx") != null) {
			idx = Integer.valueOf(req.getParameter("idx"));
		}
		return delete(idx);
	}
}