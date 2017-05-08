# Todo List
> 해당 과제는 웹상에서 할 일을 관리(추가, 삭제, 완료처리)하는 ToDoList 서비스를 구현하는 것이다. 이 과제에서 사용하는 Todo List는 [TodoMVC App Template](https://github.com/tastejs/todomvc-app-template)을 기반으로 설명되어 있다.

## TASK
0. **환경 설정**
	- `TodoMVC App Template`을 fork한다.
	- `npm install`한다.
	- `/js/app.js`에 코드를 작성한다.
1. **할 일 등록하기**
	- 할 일을 등록하는 input box가 있고, 커서를 두고 입력한 후 엔터 key를 치면 글이 등록된다.
	- 빈 문자이면 등록되지 않는다.
	- 새로 고침을 해도 같아야 한다.
2. **할 일 리스트**
	- 등록된 할 일 리스트를 보여준다.
	- 최신 할 일이 앞에 보여준다.
3. **할 일 완료하기**
	- 버튼을 클릭 시 이 일은 완료한 일로 상태가 변경되며, 리스트에서 위 그림과 같이 취소선이 그어진다.
	- css의 `completed` 클래스를 활용한다.
	- 새로 고침을 해도 같아야 한다.
5. **할 일 삭제하기**
	- 리스트에서 텍스트에 마우스 오버하면 위 그림과 같이 삭제하기 버튼이 보인다. 
	- 이 버튼을 클릭 시 이 글을 삭제되어 리스트에서 아예 보이지 않는다.
	- 새로 고침을 해도 같아야 한다.
6. **할 일 전체 갯수 표시**
	- 아직 완료하지 못한 할 일의 갯수를 보여준다.
7. **할 일 리스트를 필터링**
	- 기본은 ALL로 모든 할 일이 보인다.
	- Active클릭 시 아직 완료하지 못한 일이 보인다
	- Completed를 클릭 시 완료한 일이 보인다.
	- hash가 url에 표현되면 안된다.
8. **완료한 일 삭제**
	- 클릭 시 이미 완료한 일을 리스트에서 삭제한다.
	- 새로 고침을 해도 같아야 한다.

## 조건

### FE
- 라이브러리는 jQuery만 사용한다.
- 파일은 uglify하지 않는다.