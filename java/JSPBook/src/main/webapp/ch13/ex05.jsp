<%@ page contentType="text/html; charset=utf-8" %>
<%
	int time1 = session.getMaxInactiveInterval();
	out.print("세션 유효시간 : " + (time1 / 60)+"분<br>");
	
	session.setMaxInactiveInterval(60 * 60);
	int time2 = session.getMaxInactiveInterval();
	out.print("세션 유효시간 : " + (time2 / 30)+"분<br>");
%>