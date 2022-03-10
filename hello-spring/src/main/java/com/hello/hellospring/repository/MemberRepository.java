package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);                     // 회원 저장, 회원 정보 반
    Optional<Member> findById(Long id);             // Optional -> Java8의 기능, id로 회원 검색
    Optional<Member> findByName(String name);
    List<Member> findAll();                         // 저장된 모든 회원 반환
}
