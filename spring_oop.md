## 제어의 역전 - Inversion of Control(IoC)

기존 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성, 연결, 실행

-> 구현 객체가 프로그램의 제어 흐름을 스스로 조종

-> 자연스러운 흐름



AppConfig 등장 이후 구현 객체는 자신의 로직을 실행하는 역할만 담당

-> 프로그램의 제어 흐름은 AppConfig가 가져감

-> 구현 객체가 스스로 제어를 하는 것이 아니라 AppConfig가 구현 객체들을 제어



프레임워크 vs 라이브러리

- 프레임워크: 개발자가 작성한 코드를 제어하고 대신 실행
- 라이브러리: 개발자가 작성한 코드가 직접 제어의 흐름을 담당



## 의존관계 주입 - Dependency Injection(DI)

정적인 클래스 의존 관계와 실행 시점에 결정되는 동적인 객체 의존 관계로 분리



정적 클래스 의존 관계: import 코드만 보고도 알 수 있는 관계

<img src="/Users/seongwon/Library/Application Support/typora-user-images/image-20230228201231339.png" alt="image-20230228201231339" style="zoom:50%;" />

동적 클래스 의존 관계: 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존 관계 

<img src="/Users/seongwon/Library/Application Support/typora-user-images/image-20230228201252010.png" alt="image-20230228201252010" style="zoom:50%;" />



## IoC container, DI Container

AppConfig처럼 객체를 생성하고 관리하며 의존관계를 연결해주는 것









