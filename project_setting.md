# project setting

- start.spring.io : 과거에는 기반을 하나하나 코드를 작성하였으나 현재는 이 사이트에서 기반을 모두 가져옴



- maven project. gradle project : 필요한 라이브러리들을 가져와주고 관리해주는 툴



- dependencies : 필요한 라이브러리 가져와주는 것

  - spring web (web)
    - html 소스 자동 생성
  - thymeleaf (template engine)
    -  템플릿엔진은 회사마다 사용하는 것이 다름



- 구성

  - src 밑 main, test 나누어져있음
    - 개발 트렌드에 있어서 test 필수
  - build.gradle
    - 버전과 라이브러리 관리



-  main 실행 시 preference에서 실행을 gradle에서 intellij로 변경하여 할 것

  gradle로 실행 시 조금 느린 경향이 있음