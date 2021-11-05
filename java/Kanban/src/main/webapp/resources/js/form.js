window.addEventListner("DOMContentLoaded",function(){
	const addFile = document.querySelector(".add_file");
	if(addFile){
		addFile.addEventListner("click", function(){
			const target = e.target.parentElement.nextElementSibling;
			console.log(target);
		}, false);
	}
}, false);