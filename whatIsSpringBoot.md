인프런 토비의 스프링부트 강의를 들으며 정리했습니다.

...끄적끄적



스프링 부트: **스프링을 기반**으로 실무 환경에 사용 가능한 수준의 **독립실행형 애플리케이션**을 복잡한 고민없이 빠르게 작성할 수 있게 도와주는 여러가지 도구의 모음



스프링 부트의 목표

- 매우 빠르고 광범위한 영역의 스프링 개발 경험 제공
- 강한 주장을 가지고 즉시 적용가능한 기술 조합을 제공하며 필요에 따라 원하는 방식으로 손쉽게 변형 가능
- 프로젝트에서 필요로 하는 다양한 비기능적인 기술(내장형 서버, 보안, 메트릭, 상태 체크, 외부 설정 방식 등) 제공
- 코드 생성이나 xml 설정을 필요로 하지 않음

-----

spring -> ioc container



Conteinerless, 컨테이너리스 웹 애플리케이션 아키텍처

-> serverless와 비슷한 느낌

- spring boot를 사용하면서 servelet container를 알아서 구축이 되게 하여 개발자는 편하게 코드만 작성하면 됨



웹을 개발한다는 것 -> web component를 만드는 것 -> web client가 필요

핵심적인 목표 -> dynamic content를 만드는 것 -> 클라이언트에게 요청을 받고 응답을 보내고



web container 안에 web component들이 있다

java에서는 component를 servelet이라 함



클라이언트가 요청을 하고 컨테이너는 맞는 서블렛에 매핑(=라우팅) 시켜줌



spring container는 ser velet container 뒤에서 동작



java가 실행히 되기 위해서는 반드시 servelet이 필요



![image-20230221142628860](/Users/seongwon/Library/Application Support/typora-user-images/image-20230221142628860.png)

-----

opinionated



스프링 프레임워크의 설계 철학

- 극단적인 유연함 추구
- 다양한 관점 수용
- Not opinionated



스프링 부트의 설계 철학

- Opinionated
- 일단 정해주는 대로 빠르게 개발하고 고민은 나중에
- 스프링을 잘 활용하는 뛰어난 방법 제공

스프링 부트의 오해와 한계

- 기능 코드만 잘 작성하면 된다는 것 -> 착각
- 스프링을 몰라도 개발할 수 있다는 것 -> 착각