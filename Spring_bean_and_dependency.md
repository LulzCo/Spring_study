# Spring bean and dependency

- Service, Repository, Controller : 세가지 모두 의존관계이기 때문에 각 클래스를 작성할 때, 클래스 앞에서 어노테이션을 사용해주어야 한다.

  **@Service, @Repository, @Controller**

- 스프링 빈에 의하면

  helloController -> memberService -> memberRespository

  (화살표 방향은 의존을 하고 있다는 방향)

  의존을 연결할 때, **@Autowired** 사용

  이를, dependency injection 이라고 한다.

- Service, Repository, Controller 이들을 모두 **컴포넌트**라고 한다

  그래서 이렇게 의존 관계를 표현하는 것(어노테이션)을 **컴포넌트 스캔**이라고도 한다

  컴포넌트 스캔을 한다는 것은 스프링 컨테이너(스프링 빈) 에 담는다는 것을 의미한다.

- 어노테이션이 아니라 코드로 직접 스프링 컨테이너에 담기

  - src.main.java.SpringConfig

    ```
    package com.hello.hellospring;
    
    import com.hello.hellospring.repository.MemberRepository;
    import com.hello.hellospring.repository.MemoryMemberRepository;
    import com.hello.hellospring.service.MemberService;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    
    @Configuration
    public class SpringConfig {
    
        @Bean
        public MemberService memberService() {
            return new MemberService(memberRepository());
        }
    
        @Bean
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }
    }
    
    ```

  - 컨트롤러같은 경우에는 코드로 구현하는 것은 불가능

- 코드로 구현하는 것보다는 어노테이션을 이용하여 구현하는 것이 효율이 좋다

  또한 어노테이션을 이용하여 구현을 할 경우에 **생성자주입, setter 주입, 필드 주입** 세 가지 방법이 존재한다.

  그러나 생성자 주입 외에 것들을 사용하면 개발을 하는 데에 있어서 여러 변수가 생길 수 있어서 **생성자 주입**을 더 사용한다.

  - 생성자 주입 장점
    - 호출을 하고나서 수정이 불가능하다.
    - 생성자 주입 방식이 아닌 경우에는 누구나 호출을 하거나 수정을 하는 경우가 생길 수도 있기에 생성자 주입 방식을 사용한다.



이러한 외존관계와 스프링 빈이 중요한 이유

- 현재 데이터베이스가 어떤 것을 사용할지 정해져 있지가 않은 상황이다.

  그래서 리포지토리는 인터페이스로 구현이 되어 있다

  이를 의존관계를 이용하면 어떠한 코드의 수정도 없이 적용시킬 수 있다