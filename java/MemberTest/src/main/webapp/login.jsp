<%@ page contentType="text/html; charset=utf-8" %>
<h1>로그인!</h1>
<form method="post" action="login">
	<dl>
		<dt>아이디</dt>
		<dd><input type="text" name="memId"></dd>
	</dl>
	<dl>
		<dt>비밀번호</dt>
		<dd><input type="password" name="memPw"></dd>
	</dl>
	<input type="submit" value="로그인">
</form>