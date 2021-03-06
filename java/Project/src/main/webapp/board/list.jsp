<%@ page contentType="text/html charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import = "com.dto.Blog" %>
<%@ page import = "com.dao.Blog_dao" %>
<% 
int p = 1;
if (request.getParameter("page") != null) {
	p = Integer.parseInt(request.getParameter("page").trim());
}
	Blog_dao dao = new Blog_dao(); 
	ArrayList<Blog> list =dao.getList(p);
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
<div id="next_number">
<span><a href="?page=1">1</a></span>
<span><a href="?page=2">2</a></span>
<span><a href="?page=3">3</a></span>
<span><a href="?page=4">4</a></span>
<span><a href="?page=5">5</a></span>
</div><br>
<a href='post'><input type='button' value='글 쓰기'></a>