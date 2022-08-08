package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@ApiOperation(value = "Get 에제")
@RequestMapping("/api/v1/get-api")
public class GetController {
/*로그 출력*/
    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        LOGGER.info("getHello 메서드 호출");
        return "Hello, World!!";
    }

    @GetMapping(value = "/name")
    public String getName() {
        return "Seongwon";
    }

    // 중광호 안에 값으로 들어감
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable(@PathVariable String variable) {
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
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
