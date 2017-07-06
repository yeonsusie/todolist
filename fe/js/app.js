//부스트캠프 과제 todolist (2017.06.06~13)
/*
기능1[리스트] : getAll()
기능2[할일 갯수] : countList()
기능3[할일 등록] : addTodo()-prependTodo()
기능4[할일 완료] : updateCompleted()
기능5&6[할일 삭제] : deleteTodo() / deleteByCompleted()
기능7[필터링]
*/


(function (window) {
	'use strict';
	
	
	// 기능1 - 리스트 불러오기_첫 화면
	getAll();
	
	
	
	// 할일 리스트 ajax 함수 (GET)
	
	function getAll() {
        $.ajax({
            url: '/api/todos',
            method: 'GET',  
            success: function(response) {
            	for(var i=0; i<response.length; i++) {
            		prependTodo(response[i].id, response[i].todo, response[i].completed);
            		//반환되는 response 수만큼 리스트 클래스 생성
				}
        		countList();  //할일 갯수 세는 함수
            }
            
        
        });
	}
	
	
	
	
	// 기능2 - 할일 갯수 
	//남은 todo리스트 수 세는 것 -> 전체리스트 불러와서 'completed===0' 카운트
	
    function countList() { 
        $.ajax({
            url: '/api/todos',
            method: 'GET',
            success: function(response) {
            	var countList = 0;  //매번 새로 카운트 될 수 있게 초기화
            	for(var i=0; i<response.length; i++) {
            		if (response[i].completed === 0) {
            			countList++;
					}
				}
            	$('.todo-count > strong').html(countList);
            	//'.todo-count > strong' : todo-count클래스 안의 strong태그 안의 값을 가리킴
        		//.html() : 해당 위치의 값을 괄호 안의 값으로 출력 -> 'countList'값 출력
            }
        });
    }
    
    
    
    
    
	// 기능3 - 할일 등록
	
	$('.new-todo').on('keypress', function (e) {
		if (e.which === 13 && $('.new-todo').val().trim() != "") {
			//'e.which===13' : enter가 쳐져야 함
			// val()는 해당 클래스 값 가져오는 것, trim()은 공백제거함수 -> 빈 문자열 체크(아무것도 입력안된것)
			// 윤덕오빠의 값 가져오는 코드 var eventValue = event.currentTarget.value
			
			addTodo($('.new-todo').val());  //입력 값을 인자로 할일 추가 ajax 함수 실행
			$('.new-todo').val("");  //new-todo 입력부분 다시 공백으로
		}
	});

	
	
	// 할일 추가 ajax 함수 (POST)
	
	function addTodo(Todo) {
		$.ajax({
			url: '/api/todos',
			method: 'POST',  
			contentType: 'application/json;charset=UTF-8',  //'어플리/제이슨'을 쓴 경우 data에 꼭 json.stringify()써줘야함
			dataType: 'json',
			data: JSON.stringify({   //JSON.stringify() : json객체를 string으로 바꿔주는 함수
				"todo": Todo,
				completed: 0     //"completed": 0 으로 안해도 상관없음
			}),
			success: function(response) {
				prependTodo(response.id, response.todo, response.completed);  //response값을 인자로 클래스 생성 함수 실행
                countList();   //할일 갯수 세는 함수
			},
			
		});
	}
	
	
	
	// 클래스 생성 함수
	
	function prependTodo(id, todo, completed) {
		var completedClass = "";  //completed 값 확인 변수
		var isChecked = "";  //checked 확인 변수

		if (completed == 1) {  //completed==1인 경우 완료된 todo
			completedClass = "completed";  
			isChecked = "checked"; 
		}

    	var str = "<li id='" + id + "' class='" + completedClass + "'>" +
			"<div class='view'>" +
			"<input class='toggle' type='checkbox' " + isChecked + ">" +
			"<label>" + todo + "</label>" +
			"<button class='destroy'></button>" +
			"</div>" +
			"</li>";
		$(".todo-list").prepend(str);   //'todo-list'클래스의 가장 상위 자식으로 추가-str의 형식으로
	}
	
	
	
	
	
	// 기능4 - 할일 완료_토글 변경_completed
	
	$('.todo-list').on("click", ".toggle", function (event) {
		var id = $(event.target).parents('li').attr("id");  //이벤트가 일어난 리스트의 부모 중 'li'태그의 'id'속성값을 변수에 저장
		var completed = $(event.target).parents('li').hasClass("completed") ? 0 : 1; //'li'태그에 'completed'클래스 있으면 0(1->0), 없으면 1
		
		updateCompleted(id, completed);
    });

	
	
	// completed 변경 ajax 함수 (PUT)
	
	function updateCompleted(id, completed) {
        $.ajax({
            url: '/api/todos/' + id,  //해당 id에만
            method: 'PUT',
            contentType: 'application/json;charset=UTF-8',  //'어플리/제이슨'을 쓴 경우 data에 꼭 json.stringify()써줘야함
            dataType: 'json',
            data: JSON.stringify({  //JSON.stringify() : json객체를 string으로 바꿔주는 함수
                "completed": completed
            }),
            success: function (response) {
            	if(completed == 1) {
                    $("#" + id).addClass("completed");
                  //'completed==1'인 경우 해당 id의 li태그에 'completed'클래스 추가
                   
                } else {
                    $("#" + id).removeClass("completed");
                    
				}
                countList();  //할일 갯수 세는 함수
            }
        });
	}

	
	
	
	
	// 기능5 - 할일 삭제_destroy버튼으로 삭제
	
    $('.todo-list').on("click", ".destroy", function (event) {
        var id = $(event.target).parents('li').attr("id");  //이벤트가 일어난 리스트의 부모 중 'li'태그의 'id'속성값을 변수에 저장
        deleteTodo(id);
    });

    
    
    // 할일 삭제_id_ajax 함수
    
    function deleteTodo(id) {
        $.ajax({
            url: '/api/todos/' + id,  //해당 id만
            method: 'DELETE',
            contentType: 'application/json;charset=UTF-8', 
            dataType: 'json',
            success: function (response) {
                $("#" + id).remove();  //해당 id의 li태그 제거          
                countList();  //할일 갯수 세는 함수
            }
        });
    }
    
    
    
    
    // 기능6 - 할일 삭제_clear-completed으로 삭제_완료리스트 전체 삭제
    
    $(".clear-completed").on("click", function (event) {
        deleteByCompleted();
    });
    
    
    
    // 할일 삭제_completed 전체 삭제_ajax 함수
    
    function deleteByCompleted() {
        $.ajax({
            url: '/api/todos/completed',   //url 주의!
            method: 'DELETE',
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            success: function (response) {
            	//todo-list 클래스 안의 리스트 다 없애고 다시 getAll로 전체 리스트 불러옴
            	$('.todo-list').empty();
            	getAll();
            }
        });
    }

    
    
    
    // 기능7 - 필터링_전체/active/completed
    
    $(document).on("click", ".filters > li", function (event) {
        var id = $(event.target).attr("id");
        
        //전체 selected 클래스 초기화 후 해당 이벤트의 필터에 selected 클래스 생성
        $("#filter-all").removeClass("selected");
        $("#filter-active").removeClass("selected");
        $("#filter-completed").removeClass("selected");
        $("#" + id).addClass("selected");

        if(id == "filter-all") {
            $(".todo-list").find('li').css("display", "block"); //block:보이기, none:가리기
		} 
        else if (id == "filter-active") {
            $(".todo-list").find('li').css("display", "block");
            $(".todo-list").find('li.completed').css("display", "none");
		} 
        else if (id == "filter-completed") {
            $(".todo-list").find('li').css("display", "none");
            $(".todo-list").find('li.completed').css("display", "block");
		}
    });

})(window);
