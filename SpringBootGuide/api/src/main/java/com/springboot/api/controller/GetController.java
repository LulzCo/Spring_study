package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        return "Hello, World!!";
    }

    @GetMapping(value = "/name")
    public String getName() {
        return "Seongwon";
    }

    // 중광호 안에 값으로 들어감
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable(@PathVariable String variable) {
        return variable;
    }

    // 중괄호 매개변수를 지정해줘서 그 곳으로 값이 들어감
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

//    http://localhost:8080/api/v1/get-api/request1?name=seongwon&email=seongwon@test.com&organization=jnu
    // 키 값 선언
    @GetMapping(value = "/request1")
    public String getRequestParam1(@RequestParam String name, @RequestParam String email, @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

//    http://localhost:8080/api/v1/get-api/request2?key1=value1&key2=value2
    // 키 값 생성 가능
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        // 다음처럼도 사용 가능
//        param.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));

        return sb.toString();
    }

//    http://localhost:8080/api/v1/get-api/request3?name=seongwon&email=test@email.com&organization=jnu
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto) {
        return memberDto.toString();
    }
}
