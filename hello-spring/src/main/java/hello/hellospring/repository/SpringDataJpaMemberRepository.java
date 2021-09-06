package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface 가 interface 를 받을 때는 extends 가 된다. 또한, interface 는 다중상속이 가능하다.
// interface 만 만들어 놓고, data.jpa 에서 제공하는 JpaRepository 만 만들어 놓으면
// spring data jpa 가 interface 에 대한 구현체를 스스로 기술을 가지고 구현체를 만들어 놓는다.
// 그리고 Spring Bean 에 등록해 놓는다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, hello.hellospring.repository.MemberRepository {

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
