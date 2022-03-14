# dev web mvc

- 회원 웹 기능 - 홈 화면 추가

  HomeController

  ```
  package com.hello.hellospring.controller;
  
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.GetMapping;
  
  @Controller
  public class HomeController {
  
      @GetMapping("/")
      public String home() {
          return "home";
      }
  }
  ```

  home.html

  ```
  <!DOCTYPE HTML>
  <html xmlns:th="http://www.thymeleadf.org">
  <body>
  
  <div class"container">
      <div>
          <h1>Hello Spring</h1>
          <p>회원 기능</p>
          <p>
              <a href="/members/new">회원 가입</a>
              <a href="/members">회원 목록</a>
          </p>
      </div>
  </body>
  </html>
  ```

- 스프링 동작 순서
  1. url 요청 시, 컨트롤러에서 매핑된 것이 있는지 확인
  2. 있으면 템플릿 안에 있는 html로
  3. 없으면 정적 페이지 호출

- 회원 웹 기능 - 등록

  MemberController

  ```java
      @GetMapping("/members/new")
      public String createForm() {
          return "members/createMemberForm";
      }
  ```

  template/members/createMemberForm.html

  ```html
  <!DOCTYPE HTML>
  <html xmlns:th="http://www.thymelead.org">
  <body>
  
  <div class="container">
  
      <form action="/members/new" method="post">
          <div class="form-group">
              <label for="name">이름</label>
              <input type="text" id="name" name="name" placeholder="이름을 입력하세요">
          </div>
          <button type="submit">등록</button>
      </form>
  </div>
  </body>
  </html>
  ```

  MemberController

  ```java
  		@PostMapping("/members/new")
      public String create(MemberForm form) {
          Member member = new Member();
          member.setName(form.getName());
  
          memberService.join(member);
  
          return "redirect:/";
      }
  ```

  MemberForm

  ```java
  package com.hello.hellospring.controller;
  
  public class MemberForm {
      private String name;
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  }
  ```

  1. url : "members/new" 요청

  2. MemberController 에서 @GetMapping("/members/new")

  3. GetMapping("/members/new") 부분에서 return "members/createMemberForm"; => template/members/createMemberForm.html 반환

  4. createMemberForm.html 에서 이름을 입력 받고

     ```html
     <form action="/members/new" method="post">
     ```

     /members/new 로 post 방식으로 데이터 넘김

  5. MemberController 에서 @PostMapping("/members/new") 

  - tip

    - get : 서버로부터 정보를 조회할 때 사용

    - post : 리소스를 생성/변경 할 때 사용

      ​		   get과 달리 http body에 데이터를 담아서 전송

- 회원 웹 기능 조회

  memberList.html

  ```html
  <!DOCTYPE HTML>
  <html xmlns:th="http://www/thymeleaf.org">
  <body>
  
  <div class="contatiner">
      <div>
          <table>
              <thead>
              <tr>
                  <th>#</th>
                  <th>이름</th>
              </tr>
              </thead>
              <tbody>
              <tr th:each="member : ${members}">
                  <td th:text="${member.id}"></td>
                  <td th:text="${member.name}"></td>
              </tr>
              </tbody>
          </table>
      </div>
  </div>
  </body>
  </html>
  ```

  - table => 타임리프 동작
    - 타임리프 문법
      - tbody : 멤버 안에 있는 데이터들을 모두 출력시키도록 자동 반복 실행
        - member.id => getId() 동작 / member.name => getName() 동작
  - $ : 모델 안에서 데이터 가져오는 것