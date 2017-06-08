(function (window) {
	'use strict';

	// Your starting point. Enjoy the ride!
	
	
	//enter 이벤트 등록
	
	$('.new-todo').on('keypress', event => {
		if (event.which === 13) {  //enter가 눌릴때만 함수진행되도록 해준것.
			//alert(event.currentTarget.value)
			var eventValue = event.currentTarget.value
			
			
			//빈 문자열 검색 함수
			var isEmpty = function(value){ 
				if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) )
				{ 
					//alert("문자를 입력하시오")
					return false
					}
				else{ 
						return true
						} 
				};
				
				
			var checked = isEmpty(eventValue)
			if(isEmpty(eventValue) === true){
				
			$.ajax({  //데이터를 서버로 넘기는 과정 fe->be
				  type: "POST",
				  url: "/api/todos",
				  data: { 
					  text : event.currentTarget.value  //입력된 문자 가져오는 것.
				  },
				  dataType: "json",
				  success: response => { //제대로 넘겨졌을 때 함수
					  
					  if (response === true) { //서비스,다오를 잘 거쳐서 반환값이 true로 잘 돌아왔을 때
						  var co; //enter 후 추가할 클래스 속성 담는 변수
						  co = $('<li><div class="view"><input class="toggle" type="checkbox"><label>' +event.currentTarget.value+ '</label><button class="destroy"></button></div></li>')
						  $('.todo-list').prepend(co)
						  $('.new-todo').val('') //입력부분을 공백으로 만드는 것.
					  } else {
						  alert('todo 생성 실패');
					  }
				  },
				  error: error => {  //서버로 넘기는데 오류가 있을 때
					  alert("error: " + error.status) 
				  }
				});
			}
			else
				alert("다시 입력")
		}
		
		
	});
	
})(window);
