package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        // memberId 생성
        Long memberId = 1L;
        // 메모리 저장하기 위해 new 생성   -> VIP 회원 생성
        Member member = new Member(memberId, "memberA", Grade.VIP);
        // 메모리 객체에 생성된 회원 저장    -> 그래야 주문에서 찾아 사용할 수 있다.
        memberService.join(member);

        // 주문 생성
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        // Order 클래스에 toString 을 해주었기 떄문에 출력하면 toString 으로 호출해준다.
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
