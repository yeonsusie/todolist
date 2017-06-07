package kr.or.connect.todo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.connect.todo.constant.TodoConstant;
import kr.or.connect.todo.model.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	private static Logger log = LoggerFactory.getLogger(TodoService.class); // ?????????
	
	//Dao와 연결 - spring의 의존성 주입 기능 이용
	private TodoDao todoDao;
	public TodoService(TodoDao todoDao) {
		this.todoDao = todoDao;
	}
	
	
	//기능1... 근데 윤덕오빠코드 이해가 어렵댜;;
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
	}


}
