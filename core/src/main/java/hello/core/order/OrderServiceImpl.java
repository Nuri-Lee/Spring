package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //private  final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
   //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // DIP 위반 -> 추상 클래스와 구현 클래스 모두 의존하고 있다.
    // OCP 위반 -> FixDiscountPolicy 를 RateDiscountPolicy 로 변경하는 순간 OrderServiceImpl 의 소스도 함께 변경해야 한다.

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;  // DIP 는 지켰지만, 구현체가 없어 NullPointerException 이 발생한다. 해결방안은?
                                            // 해결방안 :: 관심사의 분리
                                            // new FixDiscountPolicy 는 마치 배우가 직접 상대 배우를 캐스팅하는 것처럼
                                            // OrderServiceImpl 도 구현체를 직접 선택하는 것이다.
                                            // 구현체가 뭐가 됐든 OrderServiceImpl 은 자신의 역할에 집중해야 한다.
                                            // -> AppConfig 등장

    // 생성자를 통해서 MemberRepository 와 DiscountPolicy 가 넘어가서 변수에 값이 할당된다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
