# view setting

welcome page

src/main/resource/static/index.html

```
<!DOCTYPE HTML>
<html>
<head>
    <title>Hello</title>
    <mate http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
Hello
<a href="/hello">hello</a>
</body>
</html>
```



- 스프링 부트가 제공하는 welcome page 기능
  - static/index.html 을 올려두면 welcome page 기능을 한다



src/main/java/com/hello/hellospring/controller/HelloController.java

```
package com.hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello";
    }
}

```

src/main/resources/templates/hello.html

```
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.orf">
<head>
    <title>Hello</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<p th:text="'안녕하세요. ' + ${data}" > 안녕하세요. 손님</p>
</body>
</html>
```

- model.addAttribute("data", "hello");

  ${data}

  hello.html의 data는 HelloController.java의 data를 의미

  data 값 : hello!!

