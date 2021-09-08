package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 애플리케이션의 실제 동작에 필요한 '구현 객체를 생성' 한다.
// 역할 : 객체 생성과 연결
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository()); // 생성자를 통해서 객체가 들어간다. == 생성자 주입
    }

    // orderService 를 호출하면 OrderServiceImpl 에 MemoryMemberRepository 와 FixDiscountPolicy 가 담겨 return 된다.
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
