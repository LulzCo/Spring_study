# SOLID

좋은 객채 제향 설계의 5가지 원칙

## SRP

Single responsibility principle, 단일 책임 원칙

- 한 클래스는 하나의 책임만 가져야 한다
- 하나의 책임이라는 것
  - 클 수 있고, 작을 수 있다
  - 문맥과 상황에 따라 다르다
- 중요한 기준은 **변경**!!
  - 변경이 있을 때 파급 효과가 적을 수록 단일 책임을 잘 따른 것

## OCP

Open/closed principle, 개방/폐쇄 원칙

- 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다
- **다형성**을 활용
- 인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능 구현
- 역할과 구현의 분리

### OCP 문제점

- OCP 위반 코드

```java
public class MemberService {
//	private MemberRepository memberRepository = new MemoryMemberRepository();
	private MemberRepositoty memberRepository = new JdbcMemberRepository();
}
```

- 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다.
- 분명 다형성을 사용했지만 OCP 원칙을 지킬 수 없다.

## LSP

Liskov substitution principle, 리스코프 치환 원칙

- 프로그램의 객체는 프로그램의 정확성을 깨트리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다

- 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것, 다형성을 지원하기 위한 원칙, 인터페이스를 구현한 구현체는 믿고 사용하려면, 이 원칙 필요

- 단순히 컴파일에 성공하는 것을 넘어서는 이야기

- ex) 자동차 인터페이스의 액셀은 앞으로 가라는 기능

  뒤로 가게 구현하면 LSP 위반 -> 느리더라도 앞으로는 가야함

## ISP

Interface segregation principle, 인터페이스 분리 원칙

- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다
- 자동차 인터페이스 -> 운전 인터페이스, 정비 인터페이스로 분리
- 사용자 클라이언트 -> 운전자 클라이언트, 정비사 클라이언트로 분리
- 분리하면 인터페이스 자체가 변해도 운전자 클라이언트에 영향을 주지 않음
- 인터페이스가 명확해지고 대체 가능성이 높아진다

## DIP

Dependency inversion principle, 의존관계 역전 원칙

- 프로그래머는 '추상화에 의존해야지, 구체화에 의존하면 안된다' 의존성 주입은 이 원칙을 따르는 방법 중 하나
- 구현 클래스에 의존하지 말고, 인터페이스에 의존하라는 뜻
- **역할(Role)**에 의존하게 해야 한다는 것과 같은 이야기
- DIP 위반 코드

```java
public class MemberService {
//	private MemberRepository memberRepository = new MemoryMemberRepository();
	private MemberRepositoty memberRepository = new JdbcMemberRepository();
}
```



## 정리

객체 지향의 핵심은 다형성

다형성 만으로는 쉽게 부품을 갈아 끼우듯이 개발할 수 없다

다형성 만드로는 구현 객체를 변경할 때 클라이언트 코드도 함께 변경된다

다형성 만으로는 OCP, DIP를 지킬 수 없다

뭔가 더 필요하다......

### 스프링과 좋은 객체지향프로그래밍

자바 하나만으로 SOLID의 규칙을 지키면 하나의 프레임워크(스프링)가 만들어지게 된다

- Dependency Injection: 의존 관계, 의존성 주입

- DI 컨테이너

위 두가지 기능으로 스프링은 다형성과 + OCP, DIP를 가능하게 지원하게 해준다

### 실무에서 발생하는 고민

인터페이스를 사용하게 되면 추상화라는 비용이 발생한다

- 개발자 코드를 다시 확인해야한다
- 코드가 실행이 될 때 인터페이스가 실행이 되기 때문에 코드를 확인할 때 직접 들어가서 확인해봐야 한다
- 상황에 따라 인터페이스를 도입할지 결정한다
- 기능 확장 가능성이 있을 때는 사용, 단발성 프로젝트일 경우 인터페이스 사용X, 초반에는 사용을 하지 않더라도 추후 리펙터링 과정을 통해서 인터페이스를 사용할 수도 있음



