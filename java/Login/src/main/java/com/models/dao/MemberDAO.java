package com.models.dao;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

import com.core.*;
import com.exception.AlertException;
import org.mindrot.jbcrypt.*;

/**
 *  회원 Model
 *
 */
public class MemberDAO {
	
	/**
	 * 회원 가입 처리
	 * 
	 * @param req
	 * @return
	 */
	public boolean join(HttpServletRequest req) throws AlertException{
		
		// 입력 데이터 검증
		checkJoinData(req);
		
		String sql = "INSERT INTO member (memId, memPw, memNm) VALUES(?,?,?)";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			String memId = req.getParameter("memId");
			String memPw = req.getParameter("memPw");
			String memNm = req.getParameter("memNm");
			
			String hash = BCrypt.hashpw(memPw,BCrypt.gensalt(10));
			
			pstmt.setString(1, memId);
			pstmt.setString(2, hash);
			pstmt.setString(3, memNm);
			
			/*
			int result = pstmt.executeUpdate();
			if(result < 1) {
				return false;
			}
			
		
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) { // 추가된 회원 번호
				int memNo = rs.getInt(1);
			}
			rs.close();
			*/
			return true;
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 회원 가입 유효성 검사
	 * 
	 * @param req
	 * @throws AlertException
	 */
	public void checkJoinData(HttpServletRequest req) throws AlertException{
		/**
		 * 0. 필수 항목 체크(아이디, 비밀번호, 비밀번호 확인, 회원명)
		 * 1. 아이디 자리수(6~20), 영문, 숫자만 허용
		 * 2. 비밀번호 자리수(8자리 이상), 복잡성(최소 영문 1개 이상, 최소 숫자1개 이상, 최소 특수문자 1개 이상)
		 * 3. 아이디 중복 여부 체크
		 * 4. 비밀번호 확인시 일치 여부
		 */
		
		/** 필수 항목 체크 **/
		String[] required = {
				"memId//아이디를 입력하세요",
				"memPw//비밀번호를 입력하세요",
				"memPwRe//비밀번호를 확인해 주세요",
				"memNm//회원명을 입력하세요"
		};
		for(String s : required) {
			String[] re = s.split("//");
			if(req.getParameter(re[0]) == null || req.getParameter(re[0]).trim().equals("")){//필수 항목 누락일때
			 throw new AlertException(re[1]);		
			}
		}
		/** 필수 항목 체크 */
		/** 아이디 자리수 체크, 영문 숫자로만 구성 S **/
		String memId = req.getParameter("memId").trim();
		if(memId.length() < 6 || memId.length() > 20 || memId.matches("[^a-zA-z0-9]")){
			throw new AlertException("아이디는 영문자, 숫자 6자리 이상 20자리 이하로 입력하세요");
		}
		/** 아이디 자리수 체크 E **/
	}
}
