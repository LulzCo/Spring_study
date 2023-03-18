package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core",            // 컴포넌트 스캔 위치 지정 -> 런타임 최소화 가능
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)         // AppConfig를 제외시키기 위해, 예제 코드를 유지하기 위해 -> 원래는 잘 사용하지 않는다.
)
public class AutoAppConfig {


    ////////////////////////////////////////////////
    // 애플리케이션의 실제 코드와 관계가 없을 때 이렇게 필드 주입이 가능하다.
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    DiscountPolicy discountPolicy;
//    @Bean
//    OrderService orderService () {
//        return new OrderServiceImpl(memberRepository, discountPolicy);
//    }
    ////////////////////////////////////////////////
    // 하지만 올바르게 사용하려면은 아래처럼 사용해야한다
//    @Bean
//    OrderService orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        return new OrderServiceImpl(memberRepository, discountPolicy);
//    }

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
