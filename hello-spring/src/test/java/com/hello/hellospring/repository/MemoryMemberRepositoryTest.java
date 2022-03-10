package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 비교값 확인
//        System.out.println("result = " + (result == member));     // 터미널 출
        Assertions.assertEquals(member, result);        // param 순서 => db값, 추출값
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);

    }
}