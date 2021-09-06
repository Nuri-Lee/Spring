package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

/* 구현을 메모리에 함. ( 회원을 저장하는 역할 : MemberRepository ) */
//@Repository     // Spring Container 에 Repository 등록.
public class MemoryMemberRepository implements hello.hellospring.repository.MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    /** key 값 생성 */
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);  //id 생성 후 store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // null이 반환 될 가능성이 있으면 Optional로
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //values가 Map에 담겨있는 Member이다.
    }

    public void clearStore() {
        store.clear();  //store 을 싹 비우는 메소드
    }
}
