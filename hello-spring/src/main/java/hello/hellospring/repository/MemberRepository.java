package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

/* 회원을 저장하는 역할 */

/** 회원 객체 저장 */
public interface MemberRepository {
    // 회원 저장
    Member save(Member member);
    // ID로 회원 찾기
    Optional<Member> findById(Long id);
    // Name으로 회원 찾기
    Optional<Member> findByName(String name);
    // 저장된 모든 회원 목록 반환
    List<Member> findAll();

    /* Optional :: 없으면 null 처리가 되는데 Optional로 한 번 감싸고 반환함   -> Java8애 들어가있는 것. 추후 코딩하면서 알아 봄 */
}
