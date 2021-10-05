<%@ page contentType="text/html; charset=utf-8" %>
<form name="writeFrm" method="post" action = "write" target="ifrmHidden" autocomplete="off">
	<dl>
		<dt>제목</dt>
		<dd>
			<input type="text" name="subject">
		</dd>
	</dl>
	<dl>
		<dt>작성자</dt>
		<dd>
			<input type="text" name = "poster">
		</dd>
	</dl>
	<dl>
		<dt>내용</dt>
		<dd>
			<textarea id="content" name="content" width="700" height="500"></textarea>
			<span class='addImage'>[이미지 추가]</span>
		</dd>
	</dl>
	<input type="reset" value="취소하기">
	<input type="submit" value="작성하기">
</form>
<script>
$(function(){
	CKEDITOR.replace("content");
	CKEDITOR.config.height = 300;
	
	$(".addImage").click(function(){
		layer.popup("popup_upload", 350, 350);
	});
});
</script>