/**
* 레이어 팝업
* 
*/
const layer ={
	/**
		레이어 팝업열기
	 */
	popup : function(url,width,height){
		if($('#layer_dim').length == 0){ // layer_dim이 없는 경우만 동적으로 추가
			$("body").append("<div id='layer_dim'></div>");
		}
		
		$("#layer_dim").css({
			position:"fixed",
			width : "100%",
			height : "100%",
			background : "rgba(0,0,0,0.6)",
			zIndex : "100",
			top:"0",
			left:"0"
		});
		
	},
 /**
	레이어 팝업 닫기
 */
 	close : function(){
	
	}
};
