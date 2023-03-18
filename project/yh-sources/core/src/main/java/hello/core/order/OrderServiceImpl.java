package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
//import hello.core.member.MemoryMemberRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    // 이러한 필드 주입은 DI 프레임워크가 없으면 아무것도 할 수 없다 테스트 코드 작성할 때도 불편하므로 되도록이면 사용하지 않는 것이 좋다
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //    @Autowired(required = false)      -> 변경 가능성이 있는 의존관계일 경우에 사용, 주입할 대상이 없으면 오류가 발생하고 그 오류를 해결하기 위해 사용
    @Autowired      // 이처럼 생성자가 하나만 있는 경우에는 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    @Autowired      // 일반 함수로 의존관계 주입
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

//    @Autowired              // 생성자 없이 의존관계 주입 방법    => 수정자 주입 방법이라고 함
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired              // 생성자 없이 의존관계 주입 방법
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    // 필드 주입 사용 시, 사용해야할 의존관계 주입
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
}
