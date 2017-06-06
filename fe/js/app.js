(function (window) {
	'use strict';

	// Your starting point. Enjoy the ride!
	
	
	//enter 이벤트 등록
	
	$('.new-todo').on('keypress', event => {
		if (event.which === 13) {
			//alert(event.currentTarget.value)
			
			$.ajax({
				  type: "POST",
				  url: "/api/todos",
				  data: {
					  text : event.currentTarget.value
				  },
				  dataType: "json",
				  success: response => {
					  
					  if (response === true) {
						  var co;
						  co = $('<li><div class="view"><input class="toggle" type="checkbox"><label>' +event.currentTarget.value+ '</label><button class="destroy"></button></div></li>')
						  $('.todo-list').prepend(co)
						  $('.new-todo').val('')
					  } else {
						  alert('todo 생성 실패');
					  }
				  },
				  error: error => {
					  alert("error: " + error.status)
				  }
				});
		}
		
	});
	
})(window);
