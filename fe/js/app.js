(function (window) {
	'use strict';  // Your starting point. Enjoy the ride!
	
	
	// 기능1-할일 등록하기
	
	$('.new-todo').on('keypress', function(event) {
		if (event.which === 13 && $('.new-todo').val().trim() != "") 
		{  // event.which는 'enter'가 눌릴때만 함수진행되도록 해주는 것.
			// val()함수는 값 가져오는 것, trim()은 공백제거함수 -> 빈 문자열 체크(아무것도 입력안된것)
			// 윤덕오빠의 값 가져오는 코드 var eventValue = event.currentTarget.value
			
			addTodo($('.new-todo').val()); // 가져온 문자값으로 addTodo
			$('.new-todo').val(""); // 입력부분을 공백으로
		}
		
	});
	
	
	
	// 할일등록함수
	
	function addTodo(todo) {
		$.ajax({  // ajax 기본형태
			type: 'POST',
			url: "/api/todos",
			contentType: 'application/json;charset=UTF-8', //'어플리/제이슨'을 쓴 경우 data에 꼭 stringify를 해줘야한다.
			dataType: 'json',
			data: JSON.stringify({  // JSON.stringify():json객체를 string으로 바꿔줌
				todo: todo
			}),
			success: function(response){
				if(response === true){
					var classType;  //새로 추가될 클래스의 타입
					classType = "<li>" + 
								"<div class='view'>" + 
								"<input class='toggle' type='checkbox'>" + 
								"<label>" + todo + "</label>" +
								"<button class='destroy'></button>" +
								"</div>" + "</li>";
					$('.todo-list').prepend(classType);  //상위에 한 줄 생성
				}
				else 
				  alert('todo 생성 실패');
			},
			error: error => {  // 서버로 넘기는데 오류가 있을 때
				  alert("error: " + error.status) 
			  }
		});
	}
				

			

				
/*			$.ajax({  // 데이터를 서버로 넘기는 과정 fe->be
				  type: "POST",
				  url: "/api/todos",
				  data: { 
					  text : event.currentTarget.value  // 입력된 문자 가져오는 것.
				  },
				  dataType: "json",
				  success: response => { // 제대로 넘겨졌을 때 함수
					  
					  if (response === true) { // 서비스,다오를 잘 거쳐서 반환값이 true로 잘
												// 돌아왔을 때
						  var co; // enter 후 추가할 클래스 속성 담는 변수
						  co = $('<li><div class="view"><input class="toggle" type="checkbox"><label>' +event.currentTarget.value+ '</label><button class="destroy"></button></div></li>')
						  $('.todo-list').prepend(co)
						  $('.new-todo').val('') // 입력부분을 공백으로 만드는 것.
					  } else {
						  alert('todo 생성 실패');
					  }
				  },
				  error: error => {  // 서버로 넘기는데 오류가 있을 때
					  alert("error: " + error.status) 
				  }
				});
			}
			else
				alert("다시입력")
		}*/
		
		
	//});
	
})(window);
