package kr.or.connect.todo.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.todo.model.Todo;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;

	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("todo").usingGeneratedKeyColumns("id");
	}
	
	public List<Todo> selectTodoList() {
		Map<String, Object> parameter = new HashMap<>();
		List<Todo> list = jdbc.queryForList(TodoSqls.SELECT_ALL, parameter, Todo.class);
		return list;
		
	}

	//기능1 - 데이터 입력_INSERT
	
	public boolean insertTodo(Todo todo) { 
		
//		Map<String, Object> param = new HashMap<>();
//		param.put("id", todo.getId());
		
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		
		Integer newTodoId = (Integer) insertAction.executeAndReturnKey(params);
		
		if(newTodoId == null) {
			return false;
		}

		return true;

	}
}
