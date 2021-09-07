package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        // long ( primitive type ) 으로 해도 상관없다. 다만, primitive type 으로 하면 null 을 넣을 수 없다.
        // 이후 DB 를 다루면서 null 이 들어갈 수 있기 떄문에, 객체 생성 단계에서 null 이 들어갈 수도 있으므로,
        // Long ( wrapper type ) 을 사용한 것이다.
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        // VIP 인 경우에 1000원이 할인되는 지 검증
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
