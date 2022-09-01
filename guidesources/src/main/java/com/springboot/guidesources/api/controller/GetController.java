package com.springboot.guidesources.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }


    @GetMapping("/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        return variable;
    }

    @GetMapping("variable2/{variable}/{variable2}")
    public String getVariable2(@PathVariable("variable") String var, @PathVariable("variable2") String var2) {
        return var + " : " + var2;
    }
}
