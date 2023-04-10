package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/* OrderServiceImplTemp 파일의 중간 최종 코드
* -> 주석들 모두 제거
* */

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // @Autowired를 사용하여 빈이 2개 이상일 때 충동하는 해결 방법 -> 클래스 이름이 겹치면 뒤에 변수값을 비교하는 Autowired의 특징
    //    @Autowired
//    private DiscountPolicy rateDiscountPolicy;


    // @Qualifier -> 빈을 조회할 때 객체가 이름이 겹칠 때 두번째로 구분할 수 있도록 도와주는 기능
    // Quilifier쓰는 애들끼리만 사용이 되며 되도록이면 사용하지 않는 것이 헷갈리지 않고 좋다
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
