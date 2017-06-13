package kr.or.connect.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.model.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	private TodoDao dao;

	public TodoService(TodoDao dao) {
		this.dao = dao;
	}

	
	
	public List<Todo> findAll() {
		return dao.selectAll();
	}
	
	public Todo add(Todo todo) {
		todo.setId(dao.add(todo));
		return todo;
	}

 	public int updateCompleted(Integer id, Integer completed) {
 		return dao.update(id, completed);
 	}

	public int deleteById(Integer id) {
		return dao.deleteById(id);
 	}
	
	public int deleteByCompleted() {
		return dao.deleteByCompleted();
 	}
}


/*
	//기능1... 윤덕오빠코드
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

*/