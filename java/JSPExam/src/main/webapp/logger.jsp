<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.core.Logger" %>
<%
	//Logger.init("dev","D:\s\logs");
	//Logger.log("테스트 로그1");
	//Logger.log("테스트 로그2");
	//Logger.log("테스트 로그3");
	
	Logger.setWriter(out);
	Logger.setPreventClosed(true);
	try{
		throw new Exception("에러발생");
	} catch(Exception e){
		Logger.log(e);
	}
%>