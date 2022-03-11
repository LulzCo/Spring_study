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

-------

- 서비스를 만들기 위해서는 리포지토리 필수

sre.main.java.service.MemberService

```
package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import com.hello.hellospring.repository.Oprional;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /*
    * 회원가입
    * */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
    * id로 회원 조회
    * */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
```

- tip

  - Optional 단축키 cmd + opt + v

  - 로직 생성 후 ctrl + t => extract method : 로직을 함수로 만들어주고 명령까지 수행해주는 단축키

  - 서비스를 개발하는 경우에는 기획자들을 위해서 비즈니스 용어를 사용하도록 하자

    리포지토리 같은 경우에는 개발자스럽게 해도 상관없음

- test service

  - tip

    - cmd + shift + t : 테스트 코드 생성 단축키

      - junit5 선택, 테스트할 멤버 선택

    - 테스트 코드 같은 경우 한국어로 사용해도 상관없다.

      - 직관적으로 볼 수 있다는 장점
      - 실제 코드 반영 x

    - ```
      //given									값이 주어졌을 때
      
      //when									실행을 하면
      
      //then									데이터 검증
      ```

      테스트 할 경우, 위 주석으로 더 보기 좋게 코드를 작성할 수 있다.

    

    MemberServiceTesr

    ```
    package com.hello.hellospring.service;
    
    import com.hello.hellospring.domain.Member;
    import com.hello.hellospring.repository.MemberRepository;
    import com.hello.hellospring.repository.MemoryMemberRepository;
    import org.assertj.core.api.AbstractThrowableAssert;
    import org.assertj.core.api.Assertions;
    import org.junit.jupiter.api.AfterEach;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    
    import java.util.Optional;
    
    import static org.junit.jupiter.api.Assertions.*;
    
    class MemberServiceTest {
    
        MemberService memberService;
        MemoryMemberRepository memoryMemberRepository;
        // clearStore 를 이용하여 각 테스트 후에 메모리 클리어
        /*
        MemberService 안에서 이미 이 객체를 사용하고 있기 때문에 굳이 또 넣으면
        같은 클래스이지만 인스턴스가 두 개인 경우가 되어서 효율이 나오지 않는다.
        private final MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        */
    		
    		
    		/* 
    		afterEach() 메서드에서 clearStore를 사용하기 위해서 memoryMemberRepository를 가져온다.
    		그냥 가져오면 되는 것처럼 보이지만 memoryMemberRepository는 MemberService에서 이미 사용중이며
    		다시 선언하게 되는 경우 또 다른 인스턴스를 생성하게 되어 메모리 상에 오류가 생긴다.
    		(memoryMemberRepository는 DB를 조작하는 부분이기에 같은 인스턴스여야 한다.)
    		따라서, MemberService(다음 코드표)에서 MemoryMemberRepository를 가져오는 부분을 수정한다.
    		*/
        @BeforeEach
        public void beforeEach() {
            memoryMemberRepository = new MemoryMemberRepository();
            memberService = new MemberService(memoryMemberRepository);
        }
    
        @AfterEach
        public void afterEach() {
            memoryMemberRepository.clearStore();
        }
    
        void join() {
            //given
            Member member = new Member();
            member.setName("spring");
    
            //when
            Long saveId = memberService.join(member);
    
            //then
            Member findMember = memberService.findOne(saveId).get();
            Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
        }
    
        @Test
        public void 중복_회원_예외() {
            // given
            Member member1 = new Member();
            member1.setName("spring");
    
            Member member2 = new Member();
            member2.setName("spring");
    
            // when
            /*
            memberService.join(member1);
            try {
                memberService.join(member2);
                fail();
            } catch (IllegalStateException e) {
                // memberService에서 작성한 오류 내용과 비교
                Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            }
            */
    
            memberService.join(member1);
            // member2를 넣었을 때 발생하는 오류와 비교 하는 메서드
            assertThrows(IllegalStateException.class, () -> memberService.join(member2));
            // 오류 내용과 실제 오류 내용을 바교하는 방법
            IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    
            // then
    
        }
    
    
        @Test
        void findMembers() {
        }
    
        @Test
        void findOne() {
        }
    }
    ```

    MemberService

    ```
    public class MemberService {
    
    //	private final MemberRepository memberRepository = new MemoryMemberRepository();
    		
    // 다음과 같이 코드를 수정하여 serviceTest에서 memberRepository를 가져올 수 있도록 한다.
        private final MemberRepository memberRepository;
        
        public MemberService(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }
    ```

    - 클래스 구조체를 사용하여 객체가 의존하는 또 다른 객체를 외부에서 선언하고 이를 주입받아 사용해야 데이터가 문제 없이 주고받을 수 있다.

      => 이를 Dependency Injection이라고 한다. (의존성 주입)
