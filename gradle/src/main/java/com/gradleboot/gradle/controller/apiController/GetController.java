package com.gradleboot.gradle.controller.apiController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/get-api")
public class GetController {

    private final Logger Logger = LoggerFactory.getLogger(GetController.class);


    @RequestMapping(value = "/hello")
    public String getHello() {
        Logger.info("getHello() 호출");
        return "Hello World!!";
    }

    @GetMapping(value = "/name")
    public String getName() {
        Logger.info("getName() 호출");
        return "Seongwon";
    }

    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        Logger.info("@PathVariable1 : {}", variable);
        return variable;
    }

    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

    @ApiOperation(value = "get method example", notes = "@RequestParam을 활용한 get method")
    @GetMapping(value = "/request1")
    public String getRequest1(
            @ApiParam(value = "name", required = true) @RequestParam String name,
            @ApiParam(value = "email", required = true) @RequestParam String email,
            @ApiParam(value = "organization", required = true) @RequestParam String organization
    ) {
        return name + email + organization;
    }

    @GetMapping(value = "/request2")
    public String getRequest2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.forEach((key, value) -> sb.append(key + " : " + value + "\n"));

        return sb.toString();
    }
}
