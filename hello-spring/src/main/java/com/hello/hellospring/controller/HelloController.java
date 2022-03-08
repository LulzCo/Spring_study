package com.hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // get, post 할 때의 그 get을 의미
    @GetMapping("hello")
    // 웹 브라우저에서 /hello 요청을 받는다.
    public String hello(Model model) {
        // data : "hello!!"
        model.addAttribute("data", "hello!!");

        // resources/templates/hello.html 로 넘긴다.
        return "hello";
    }

    /*
    @RequestParam("name") url 상에서 name=??? 형식으로 파라미터 값을 받아온다.
    ex) /hello-mvc?name=spring!!!!!
    */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // 이전과 다르게 템플릿으로 값을 넘어가는 것이 html 소스로 넘어간다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

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


}

