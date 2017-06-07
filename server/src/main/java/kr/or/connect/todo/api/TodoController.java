package kr.or.connect.todo.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.model.Todo;
import kr.or.connect.todo.service.TodoService;

@RestController
public class TodoController {
	private TodoService todoService;  //service 연결 (spring의 DI(의존성주입) 기능)

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	
//	@GetMapping("/api/todos")
//	public boolean getTodo() {
//		return true;
//	}

	@PostMapping("/api/todos")
	public boolean addTodo(Todo todo) {
		return todoService.addTodo(todo);
	}

}
