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





