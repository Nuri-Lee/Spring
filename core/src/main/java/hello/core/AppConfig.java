package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 애플리케이션의 실제 동작에 필요한 '구현 객체를 생성' 한다.
// 역할 : 객체 생성과 연결   == 공연 기획자 -> SRP 단일 책임 원칙을 따름.
// 객체를 생성하고 관리하면서 의존관계를 연결해주는 것을 IoC 컨테이너 또는 DI 컨테이너라고 한다.
// AppConfig == 어셈블러 == 오브젝트 팩토리
public class AppConfig {

    // refactoring :: 역할과 구현 클래스가 한 눈에 들어온다, new MemoryMemberRepository 의 중복을 제거했다.
    // MemberService 의 역할
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // MemberRepository 의 역할
    public MemberRepository memberRepository() {    // return 값을 인터페이스로 해줘야 한다.
        return new MemoryMemberRepository();
    }

    // OrderService 의 역할
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // DiscountPolicy 의 역할
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();    // 할인 정책이 정액 할인 -> 정률% 할인 정책으로 변경되었을 때,
                                            // 다른 곳 건드릴 필요없이 여기서 객체만 변경해주면 된다.
                                            // 즉, "사용 영역"의 코드는 전혀 변경할 필요가 없고, "구성 영역"은 당연히 변경된다.
                                            // OCP 개방 패쇄 원칙 지킴 : 소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀있다.
    }

    // 이렇게 하면 MemoryMemberRepository 와 FixDiscountPolicy 의 역할이 눈에 보이지 않는다. -> 역할을 보이게 하는 것이 중요!
   /* public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository()); // 생성자를 통해서 객체가 들어간다. == 생성자 주입
    }

    // orderService 를 호출하면 OrderServiceImpl 에 MemoryMemberRepository 와 FixDiscountPolicy 가 담겨 return 된다.
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }*/
}
