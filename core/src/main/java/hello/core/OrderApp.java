package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    // 메인 메소드로 테스트하는 것은 좋지 않다. 자동화 된 테스트로 실행해야 한다.
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        // memberId 생성
        Long memberId = 1L;
        // 메모리 저장하기 위해 new 생성   -> VIP 회원 생성
        Member member = new Member(memberId, "memberA", Grade.VIP);
        // 메모리 객체에 생성된 회원 저장    -> 그래야 주문에서 찾아 사용할 수 있다.
        memberService.join(member);

        // 주문 생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // Order 클래스에 toString 을 해주었기 떄문에 출력하면 toString 으로 호출해준다.
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
