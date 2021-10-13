<%@ page contentType="text/html charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import = "com.dto.Blog" %>
<%@ page import = "com.dao.Blog_dao" %>
<% 
	Blog_dao dao = new Blog_dao(); 
	ArrayList<Blog> list =dao.getList();
%>
<c:set var="list" value="<%=list%>" />
<h1>포스팅</h1>
<ul>
	<c:forEach var="chart" items="${list}">
		<li id='post_top' >
			<a href="delete?postNm=${chart.postNm}" onclick="return confirm('정말 삭제하시겠습니까?');">
				<i class='xi-close' ></i><br>
			</a>
			<a href='edit?postNm=${chart.postNm}'>
				<i class='xi-pen'></i>
			</a>
			<div id='post_Title'><c:out value="${chart.postTitle}"/></div>
			<div id='post_Writer'><c:out value="${chart.postWriter}"/></div>
			<div id='post_regDt'><c:out value="${chart.postregDt}"/></div>
		</li>
		<li id ='post_content'>
			<div id='post_Content'>${chart.content}</div>
		</li>
	</c:forEach>
</ul>
<a href='post'>
<input type='button' value='글 쓰기'></a>