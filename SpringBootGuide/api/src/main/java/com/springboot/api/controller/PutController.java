package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/put-api")
public class PutController {

    // return type => text
    @PutMapping(value = "/member")
    public String putMember(@RequestBody Map<String, Object> putData) {
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    // return type => text
    @PutMapping(value = "/member1")
    public String putMemberDto1(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }

    // return type => json
    @PutMapping(value = "/member2")
    public MemberDto pustMemberDto2(@RequestBody MemberDto memberDto) {
        return memberDto;
    }

    // return type => json
    // http response를 더 구체적으로 정의
    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDto> putMemberDto3(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}
