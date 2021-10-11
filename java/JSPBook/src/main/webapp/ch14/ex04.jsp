<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Cookie userId = new Cookie("userId", "admin");
	userId.setPath("/JSPBook/ch07");
	response.addCookie(userId);
%>
<c:import var="url" url="ex02.jsp" />
${url}