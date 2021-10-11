<%@ page contentType="text/html; charset=utf-8" %>
<h3>파일 업로드</h3>
<form name='frm' method="post" action="upload" enctype="multipart/form-data"
target='ifrmHidden'>
	<input type ="file" name="file">
</form>

<script>
/*
	$(function(){
		$("input[name='file']").change(function(){
			frm.submit();
		})
	});
*/

window.addEventListener("DOMContentLoaded",function(){
	const file = document.getElementsByName("file")[0];
	file.addEventListener("change",function(){
			frm.submit();
	});
});
</script>