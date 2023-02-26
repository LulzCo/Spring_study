# http Request and Response

header의 contents-type을 통해 어떤 형식의 데이터를 주고 받는지 확인할 수 있다.

ex) text인지 json인지 확인



## Http의 기본 구조

request

- request line: method, path, http version
- headers
  - accept: 어떤 타입을 받을지에 관한 요구 사항
- message body

response

- status line: http version, status code, status text
- headers
  - contents-type: 어떤 타입으로 body에 데이터가 담겨있는지
- message body
- 