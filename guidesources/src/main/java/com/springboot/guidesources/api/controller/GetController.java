package com.springboot.guidesources.api.controller;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/request1")
    public String getRequestParam1(@RequestParam String name, @RequestParam String email, @RequestParam String org) {
        String result = "name : " + name + ", email : " + email + ", org : " + org;
        return result;
    }

//    @GetMapping("/request2")
//    public String getRequest2Param2()
}
