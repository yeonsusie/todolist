package kr.or.connect.todo.persistence;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.todo.model.Todo;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Todo> rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);

	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingColumns("todo")
				.usingGeneratedKeyColumns("id");
	}
	
	public List<Todo> selectAll() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(TodoSqls.SELECT_ALL, params, rowMapper);
	}
	
	public Integer add(Todo todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
 		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public int update(Integer id, Integer completed) {
	 	MapSqlParameterSource params = new MapSqlParameterSource();
	 	params.addValue("id", id);
	 	params.addValue("completed", completed);
	 	return jdbc.update(TodoSqls.UPDATE_COMPLETED, params);
	}
	
	public int deleteById(Integer id) {
 		Map<String, ?> params = Collections.singletonMap("id", id);
 		return jdbc.update(TodoSqls.DELETE_BY_ID, params);
	}

	public int deleteByCompleted() {
 		Map<String, ?> params = Collections.singletonMap("completed", 1);
 		return jdbc.update(TodoSqls.DELETE_BY_COMPLETED, params);
	}
}




	

	
/* 윤덕오빠 코드
	//기능1 - 데이터 입력_INSERT
	
	public Integer insert(Todo todo){
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return insertAction.executeAndReturnKey(params).intValue();
		
	}
	
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
	
	
	//기능2 - 데이터 불러오기_SELECT
	
	public List<Todo> selectAll() {
		Map<String, Object> parameter = new HashMap<>();
		List<Todo> list = jdbc.queryForList(TodoSqls.SELECT_ALL, parameter, Todo.class);
		return list;
		
	}
}*/
