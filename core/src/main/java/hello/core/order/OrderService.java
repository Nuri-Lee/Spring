package hello.core.order;

public interface OrderService {
    // 클라이언트가 MemberService 에게 '회원 id, 상품명, 상품 가격' 으로 주문을 생성하면 '주문 결과' 를 return 해준다.
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
