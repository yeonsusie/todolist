package kr.or.connect.todo.model;

public class Todo {
	private Integer id;
	private String todo;
	private Integer completed;
	private String date;

	// 테스트 할 때 편의성을 위해 생성자를 추가한 것.
	public Todo() {

	}
	public Todo(Integer id, String todo, Integer completed) {
		super(); // ??
		this.id = id;
		this.todo = todo;
		this.completed = completed;

	}
	public Todo(String todo, Integer completed) {
		this.todo = todo;
		this.completed = completed;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public Integer getCompleted() {
		return completed;
	}

	public void setCompleted(Integer completed) {
		this.completed = completed;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	// 속성값 쉽게 확인할 수 있도록 재정의 해준것.
	public String toString() {
		return "Todo [id=" + id + ",todo=" + todo + ", completed=" + completed + "]";

	}

}
