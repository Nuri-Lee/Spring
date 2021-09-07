package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private  final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원 찾기
        Member member = memberRepository.findById(memberId);
        // 할인 정책에 회원 넘기기    :: memberId 만 넘겨도 되지만, 일의 확장을 위해 member 통으로 넘긴 것.
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // Order 클래스가 회원 id, 아이템명, 아이템 가격, 할인 가격을 OrderServiceImpl 에게 넘겨 줌. == 최초 생성된 주문 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 할인에 관해 Order 클래스는 모르고 DiscountPolicy 에게 책임을 전가한 후 결과만 받길 원한다.    -> 단일책임원칙을 잘 지킴.
}
