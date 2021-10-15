<%@ page contentType="text/html; charset=utf-8" %>
<%
	String blogpage = (String)request.getAttribute("blogpage");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
		<link rel="stylesheet" type="text/css" href="<%=blogpage%>/public/css/style.css" />
		<script src="<%=blogpage %>/public/js/ckeditor/ckeditor.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="<%=blogpage %>/public/js/layer.js"></script>
		<script src="<%=blogpage %>/public/js/form.js"></script>
		<title>블로그!</title>
	</head>
	<body>	
	<div id=top_bar><a href="main">나만의 블로그</a></div>