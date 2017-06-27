package kr.or.connect.todo.persistence;
//패키지 명 하나 때문에 이 고생을 하다니..-.-
//TodoDao와 연결하려면 src/main/java안의 TodoDao.class가 있는 패키지명과 동일하게 만들어줘야함!

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.todo.model.Todo;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TodoDaoTest {
	
	@Autowired
	private TodoDao dao; //dao와 연결!얍!
	
	

	@Test
	public void shouldInsertAndSelect() { //insert,select 테스트
		//insert
		Todo todo = new Todo("todotest_inse",0);  //테스트용 todo 객체 생성
	
		Integer id = dao.insert(todo); //TodoDao의 insert함수를 사용,id값(=몇번째인지 인덱스값) 받아오기
		todo.setId(id); //그 id값을 아까 만든 테스트용  todo객체의 id에 넣어줌
		System.out.println(todo); //Todo.java에서 만든 toString()형식으로 출력 Todo [id=272,todo=todotest_inse, completed=0]
		
		//select
		Todo selected = dao.selectById(id); //id값으로 todo가져오기
		assertThat(selected.getTodo(),is("todotest_inse")); 
		//아까 저장한 todo객체에서 todo의 값 비교. 제대로 선택되었는지
		//is()함수를 쓰기 위해선 import static org.hamcrest.CoreMatchers.*; 와  import static org.junit.Assert.*;추가해줘야함
		//성공할 경우 트랜잭션 종료(roll back) 실패할 경우 다음 테스트로 넘어가지 못하고 멈춤 & 오류난 이유 콘솔창에 뜸
	
	}
	
	
	@Test
	public void shouldDelete() { //delete 테스트
		
		Todo todo = new Todo("todotest_dele",0); //테스트용 todo객체 생성
		
		Integer id = dao.insert(todo); //TodoDao의 insert함수를 사용,id값(=몇번째인지 인덱스값) 받아오기
		todo.setId(id); //그 id값을 아까 만든 테스트용  todo객체의 id에 넣어줌
		System.out.println(todo); //Todo.java에서 만든 toString()형식으로 출력 Todo [id=272,todo=todotest_inse, completed=0]

		// when
		int affected = dao.deleteById(id); //dao의 delete함수를 돌리고 그 반환값은 affected에 저장

		// Then
		assertThat(affected, is(1)); //반환값이 1인지 확인(제대로 수행되었다는 의미)
	}
	
	
	@Test
	public void shouldUpdate() { //update 테스트
		
		Todo todo = new Todo("todotest_upda",0);
		
		Integer id = dao.insert(todo); //TodoDao의 insert함수를 사용,id값(=몇번째인지 인덱스값) 받아오기
		todo.setId(id); //그 id값을 아까 만든 테스트용  todo객체의 id에 넣어줌
		System.out.println(todo); //Todo.java에서 만든 toString()형식으로 출력 Todo [id=272,todo=todotest_inse, completed=0]
		
		todo.setCompleted(1); //completed를 수정함.(초기값:0->1)
		
		//수정된 completed를 dao의 update()로 수정하고 잘 실행되었는지 체크!
		int affected = dao.update(id,todo.getCompleted()); 
		assertThat(affected, is(1));
		
		//한번 더 확인! -> id값으로 아까 생성한 todo객체 불러온 뒤 completed값이 1로 바뀌었는지 체크
		Todo updated = dao.selectById(id);
		System.out.println(updated);
		assertThat(updated.getCompleted(),is(1));

	}
}

