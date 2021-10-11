<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dto.Blog" %>
<%
	String blogpage = (String)request.getAttribute("blogpage");
	Blog blog = (Blog)request.getAttribute("blog");
	String action =(String)request.getAttribute("action");
%>
<c:set var="blog" value="<%=blog%>" />
<script src="<%=blogpage%>/public/js/form.js"></script>
<form name='posting' method="post" action="<%=action %>"  target='ifrm' autocomplete='off'>
	<c:if test='${blog != null }'>
		<input type='hidden' name='postNm' value="<c:out value='${blog.postNm}'/>"/>
	</c:if>
	<dl>
		<dt>제목</dt>
		<dd>
			<input type="text" name="postTitle" value="<c:out value='${blog.postTitle}'/>">
		</dd>
	</dl>
	<dl>
		<dt>작성자</dt>
		<dd>
			<input type="text" name="postWriter" value="<c:out value='${blog.postWriter}'/>">
		</dd>
	</dl>
	<dl>
		<dt>내용</dt>
		<dd>
			<textarea id='content' name="content">	<c:out value='${blog.content}'/>
			</textarea>
		</dd>
	</dl>
	<c:choose>
		<c:when test='${blog==null}'>
			<input type="submit" value="작성하기">
		</c:when>
		<c:otherwise>
			<input type="submit" value="수정하기">	
		</c:otherwise>
	</c:choose>
</form>