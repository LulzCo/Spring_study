# spring db access tech

- H2 DB 설치

  - H2 DB : 개발이나 테스트 용도로 가벼고 편리한 DB 

  1. h2 사이트에서 all platform 설치

  2. 터미널에서 h2/bin 안에 있는 h2.sh 실행

     - 권한으로 인해서 실행 불가

     - 권한 설정 후 실행

       ```terminal
       chmod 755 h2.sh
       ./h2.sh
       ```

  3. localhost:8082/......(session key) 접속

     - url : 디비 저장 경로
     - 해당 경로에 db 파일 생성됨

  4. url 설정 후 연결

     jdbc:h2:~/dev/h2_db_test

  5. 설정을 tcp 소켓으로 다시 연결한다

     jdbc:h2:tcp://localhost/~/dev/h2_db_test

     - 웹 콘솔이기 때문에 소켓으로 접근하지 않으면 충돌이 일어날 수 있다.

  

