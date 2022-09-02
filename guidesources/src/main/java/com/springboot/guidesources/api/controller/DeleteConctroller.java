package com.springboot.guidesources.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteConctroller {

    @DeleteMapping(value = "/{variable}")
    public String deleteVariable(@PathVariable String variable) {
        return variable;
    }

    @DeleteMapping(value = "/request")
    public String deleteRequestParam(@RequestParam String var) {
        return "var : " + var;
    }
}
