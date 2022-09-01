package com.springboot.guidesources.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, String> postData) {
        StringBuilder sb = new StringBuilder();

        postData.forEach((key, value) ->
                sb.append(key + " : " + value + "\n"));

        return sb.toString();
    }
}
