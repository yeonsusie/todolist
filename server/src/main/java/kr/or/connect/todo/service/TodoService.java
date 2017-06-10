package kr.or.connect.todo.service;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.model.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	//private static Logger log = LoggerFactory.getLogger(TodoService.class); // ?????????
	
	//Dao와 연결 - spring의 의존성 주입 기능 이용
	private TodoDao todoDao;
	
	public TodoService(TodoDao todoDao) {
		this.todoDao = todoDao;
	}
	
	
	public Todo addTodo(Todo todo){
		todo.setId(todoDao.insert(todo));  //왜 이 코드가 들어갔을까..
		return todo;
	}
	
	
/*	//기능1... 윤덕오빠코드
	public boolean addTodo(Todo todo){ 
		boolean result = false;
		try {
			todo.setCompleted(0);
			todo.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(TodoConstant.DATE_FORMAT)));
			result = todoDao.insertTodo(todo);
		} catch (Exception e) {
			log.error("insert failure.", e);
		}
		return result;
	}*/


}
