<%@ page contentType = "text/html; charset=utf-8" %>
<%
	Cookie userId = new Cookie("userId","admin");
	Cookie userPass = new Cookie("userPass", "1234");
	response.addCookie(userId);
	response.addCookie(userPass);
%>