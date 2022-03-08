# web dev basic

## 정적 컨텐트(Static Contents)

src/resources/static/

해당 경로에 들어가는 모든 html은 이름 그대로 url 경로가 생성된다.

ex) src/resources/static/hello-static.html

​		=> localhost:8080/hello-static.html







## mvc and template engine

controller/HelloController.java

```
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
```

- hello-template.html 로 name 값을 넘긴다.

  @RequestParam("name") => url 상에서 name=??? 형식으로 파라미터 값을 받아온다.
  ex) /hello-mvc?name=spring!!!!!



controller/HelloController.java   

```
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
```

이전 컨트롤러 소스들과 다르게 템플릿으로 값을 넘겨주는 것이 아니라 html 소스 그대로 넘겨주는 것이다.

다음 url 요청 시localhost:8080/hello-string?name=spring!!!!

html 코드 - 태그가 존재하지 않는다.

```
hello spring!!!!
```



## api

controller/HelloController.java

```
    // api 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
```

/hello-api?name=spring!!! 으로 들어가면

{ "name" : "spring!!!"}

json 형식으로 html 출력



-----

- @ResponseBody : html 태그에서 Body에 반응하라는 의미의 어노테이션ㅇ 
- @GetMappring : url 생성 어노테이션
