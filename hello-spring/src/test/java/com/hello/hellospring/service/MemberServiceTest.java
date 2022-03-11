package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;
    // clearStore 를 이용하여 각 테스트 후에 메모리 클리어
    /*
    MemberService 안에서 이미 이 객체를 사용하고 있기 때문에 굳이 또 넣으면
    같은 클래스이지만 인스턴스가 두 개인 경우가 되어서 효율이 나오지 않는다.
    private final MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    */

    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        /*
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            // memberService에서 작성한 오류 내용과 비교
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        memberService.join(member1);
        // member2를 넣었을 때 발생하는 오류와 비교 하는 메서드
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 오류 내용과 실제 오류 내용을 바교하는 방법
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then

    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}