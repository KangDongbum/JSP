<%@ page contentType="text/html; charset=utf-8" %>
<%
	String userId = (String)session.getAttribute("userId");
	String userPass = (String)session.getAttribute("userPass");
%>
아이디 : <%=userId%><br>
비밀번호 : <%=userPass%><br>