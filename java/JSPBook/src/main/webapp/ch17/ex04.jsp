<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<h1>ex04.jsp 상단</h1>
<c:import var="url" url="ex03.jsp"> </c:import>

<h1>ex04.jsp 하단</h1>
${url}
<c:redirect url="https://www.naver.com" ></c:redirect>