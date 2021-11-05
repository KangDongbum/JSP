package com.models.kanban;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import com.core.*;
import com.models.member.*;

public class KanbanDAO {
	
	private static KanbanDAO instance = new KanbanDAO();
	private KanbanDAO() {};
	
	public static KanbanDAO getInstance() {
		if(instance == null) {
			instance = new KanbanDAO();
		}
		
		return instance;
	}
	
	/**
	 * 작업 목록 추가
	 * 
	 * @param req
	 * @return
	 */
	public boolean add(HttpServletRequest req) {
		
		HashMap<String, String> params = FileUpload.getInstance().upload(req).get();
		int memNo = 0;
		if(req.getAttribute("member") != null) {
			Member member = (Member)req.getAttribute("member");
			memNo = member.getMemNo();
		}
		
		String sql = "INSERT INTO worklist (gid, memNo, status, subject, content) VALUES(?,?,?,?,?)";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Long", params.get("gid")));
		bindings.add(DB.setBinding("Integer", String.valueOf(memNo)));
		bindings.add(DB.setBinding("String", params.get("status")));
		bindings.add(DB.setBinding("String", params.get("subject")));
		bindings.add(DB.setBinding("String", params.get("content")));
		
		int rs =DB.executeUpdate(sql, bindings);

		return (rs > 0)?true:false;
	}
}
