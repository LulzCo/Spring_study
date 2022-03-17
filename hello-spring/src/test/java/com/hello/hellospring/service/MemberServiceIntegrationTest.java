package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
//@Transactional
class MemberServiceIntegrationTest {

//    MemberService memberService;
//    MemoryMemberRepository memoryMemberRepository;
    // clearStore 를 이용하여 각 테스트 후에 메모리 클리어
    /*
    MemberService 안에서 이미 이 객체를 사용하고 있기 때문에 굳이 또 넣으면
    같은 클래스이지만 인스턴스가 두 개인 경우가 되어서 효율이 나오지 않는다.
    private final MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    */

    // DB 연결 시, 이전처럼 직접 선언하지 않고, 스프링 컨테이너에서 직접 가져온다.
//    @BeforeEach
//    public void beforeEach() {
//        memoryMemberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memoryMemberRepository);
//    }

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    // AfterEach도 @Transactional에 의해서 필요없어짐
//짐   @AfterEach
//    public void afterEach() {
//        memoryMemberRepository.clearStore();
//    }
//

    @Test
    void 회원가입() {
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
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring2");

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