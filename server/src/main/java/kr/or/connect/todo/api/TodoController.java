package kr.or.connect.todo.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.model.Todo;
import kr.or.connect.todo.service.TodoService;


//ajax - get:데이터 가져오기 / post:데이터 보내기 / put:데이터 수정 / delete:데이터 삭제


@RestController
@RequestMapping("/api/todos")
public class TodoController {
	private final TodoService todoService;
	private final Logger log = LoggerFactory.getLogger(TodoController.class);
	
	//service 연결 (spring의 DI(의존성주입) 기능)
	@Autowired
	public TodoController(TodoService service) {
		this.todoService = service;
	}
	
	
	@GetMapping
	List<Todo> findAll() {
		return todoService.findAll();
	}
	
	@PostMapping
 	@ResponseStatus(HttpStatus.CREATED)
 	Todo add(@RequestBody Todo todo) {
 		return todoService.add(todo);
	}
	
	@PutMapping("/{id}")
 	@ResponseStatus(HttpStatus.NO_CONTENT)
	int updateCompleted(@PathVariable Integer id, @RequestBody Todo todo) {
 		return todoService.updateCompleted(id, todo.getCompleted());
 	}
	
	@DeleteMapping("/{id}")
 	@ResponseStatus(HttpStatus.NO_CONTENT)
 	void deleteById(@PathVariable Integer id) {
 		todoService.deleteById(id);
 	}
	
	@DeleteMapping("/completed")
 	@ResponseStatus(HttpStatus.NO_CONTENT)
 	void deleteByCompleted() {
 		todoService.deleteByCompleted();
 	}
}

	

