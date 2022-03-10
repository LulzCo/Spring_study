# dev backend (회원 관리 예제)

- 비즈니스 요구사항
  - 데이터 : 회원id, 이름
  - 기능 : 회원 등록, 조회
  - 아직 데이터 저장소가 선정되지 않음(가상 시나리오)
    - 개발은 시작해야하고 아직 데이터베이스가 선정되지 않은 상황

- 컨트롤러 : 웹 MVC 컨트롤러 역할

  서비스 : 핵심 비즈니스 로직 구현

  리포지토리 : 데이터베이스 접근, 도메인 객체를 DB에 저장하고 관리

  도메인 : 비즈니스 도메인



- 아직 데이터 저장소가 선정되지 않아서, 우선 인터페이스로 구현 클래스를 변경할 수 있게 설계

  데이터 저장소는 RDB, NoSQL 등 다양한 저장소를 고민하는 상황

  개발을 진행하기 위해 초기 개발 단계에서는구현체로 가벼운 메모리 기반 데이터 저장소 사용

-------

- domain : 비즈니스 로직이 들어가는 클래스

- repository : DB에 접근하는 메서드들을 사용하는 인터페이스

- domain.member

  ```
  package com.hello.hellospring.domain;
  
  public class Member {
  
      private Long id;
      private String name;
  
      public Long getId() {
          return id;
      }
  
      public void setId(Long id) {
          this.id = id;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  }
  ```

- repository.MemberRepository

  ```
  package com.hello.hellospring.repository;
  
  import com.hello.hellospring.domain.Member;
  
  import java.util.List;
  import java.util.Optional;
  
  public interface MemberRepository {
      Member save(Member member);                  // 회원 저장, 회원 정보 반
      Optional<Member> findById(Long id);          // Optional -> Java8의 기능, id로 회원 검색
      Optional<Member> findByName(String name);
      List<Member> findAll();                      // 저장된 모든 회원 반환
  }
  ```

- repository.MemoryMemberRepository

  ```
  package com.hello.hellospring.repository;
  
  import com.hello.hellospring.domain.Member;
  
  import java.sql.Array;
  import java.util.*;
  
  public class MemoryMemberRepository implements MemberRepository{
  
      private static Map<Long, Member> store = new HashMap<>();
      private static long sequence = 0L;
  
      @Override
      public Member save(Member member) {
          member.setId(++sequence);                       // id set
          store.put(member.getId(), member);              // store에 저장
          return member;
      }
  
      @Override
      public Optional<Member> findById(Long id) {
          return Optional.ofNullable(store.get(id));
      }
  
      @Override
      public Optional<Member> findByName(String name) {
          return store.values().stream()
                  .filter(member -> member.getName().equals(name))
                  .findAny();
      }
  
      @Override
      public List<Member> findAll() {
          return new ArrayList<>(store.values());
      }
  }
  
  ```

- null 값을 반환하는 경우 Optional로 감싸서 null 값을 반환
- findById 의 경우, 요즘 트렌드가 null값으로도 받아오는 경우가 종종 있으므로 Optional을 추가하여 반환
- 자바 실무에서 List 자주 사용

------

- 테스트 케이스

  - 개발한 기능을 실행해서 테스트할 때 자바의 main 메서드를 통해 실행하거나, 웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행 => 오래 걸리고, 여러 테스트를 한번에 실행하기 어렵다는 단점이 있음 => 따라서 테스트 케이스 실행

- src.test.java

  ```
  package com.hello.hellospring.repository;
  
  import com.hello.hellospring.domain.Member;
  import org.junit.jupiter.api.Assertions;
  import org.junit.jupiter.api.Test;
  
  public class MemoryMemberRepositoryTest {
  
      MemberRepository repository = new MemoryMemberRepository();
  
      @Test
      public void save() {
          Member member = new Member();
          member.setName("spring");
  
          repository.save(member);
  
          Member result = repository.findById(member.getId()).get();
  
          // 비교값 확인
  //        System.out.println("result = " + (result == member));     // 터미널 출력
          Assertions.assertEquals(member, result);        // param 순서 => db값, 추출값
  //        Assertions.assertThat(member).isEqualTo(result);
      }
  }
  ```

  - Assertions : 인텔리제이 내 터미널에서 오류가 있는지 없는지 확인 가능(초록불 성공, 빨간불 오류 발생)

    - 처음 오는 파라미터 : 기댓갑, 두번째 오는 파라미터 : 실제값

    - 종류 : junit, assetj

      - junit
    
        - ```
          Assertions.assertEquals(member, result);
          ```

      - assetj
    
        - ```
          Assertions.assertThat(member).isEqualTo(result);
          ```

- other ex)

  - ```
        @Test
        public void findByName() {
            // 회원가입
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);
    
            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);
    
            Member result = repository.findByName("spring2").get();
    
            // 가져온 값이 제대로 되었는지 확인
            assertThat(result).isEqualTo(member1);
    ```

    - 오류 발생

  - ```
        @Test
        public void findAll() {
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);
    
            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);
    
            List<Member> result = repository.findAll();
    				// 갯수가 맞는지 확인 
            assertThat(result.size()).isEqualTo(2);
        }
    ```

- 주의 사항

  - 위 모든 코드를 한번에 테스트할 경우 오류 발생

    - 이유 : 모든 테스트 함수가 같은 변수 명을 가지고 있다

      member1에 넣은 후에 다시 member1에 넣어서 테스트하려고 하면 중복되기 때문에 오류가 발생

    - 해결책 : 테스트 하나가 끝난 후에 데이터를 클리어 해줘야 한다.

      - MemoryMemberRepository

        ```
            public void clearStore() {
                store.clear();
            }
        ```

      - MemoryMemberRepositoryTest

        ```
        		// 테스트 하나가 끝날 때마다 실행해주는 콜백 함수
        		public void afterEach() {
                repository.clearStore();
            }
        ```

    - 테스트 같은 경우 서로서로 의존성이 없게 설계해야 한다

      따라서 위 코드를 추가해야 하는 것이다.

- Tip

  - 개발을 할 때에 메인을 만들고 테스트를 사용할 수 있지만

    역으로 테스트 케이스를 먼저 만들고 메인을 만들 수 있다.

