package hello.hellospring.repository;


import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA 는 EntityManager 로 모든 동작을 한다.
    // build.gradle 에서 data-jpa 라이브러리를 받으면, 스프링 부트가 자동으로 EntityManager 을 생성해준다. 현재 디비까지 연결해 줌.
    // 이렇게 만들어 준것을 injection 해서 쓰면 됨.
    // application.properties 에서 세팅해 둔 jpa 정보들과 DB Connection 정보를 합쳐서 스프링부트가 EntityManager 을 만들어 주는 것이다.
    // EntityManager 은 내부적으로 DataSource 를 가지고 있기 떄문에 DB 와 통신하는 역할을 한다.
    // 따라서 EntityManger 을 쓰려면 JPA 를 주입받아야 한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);     // JAP 가 Insert 쿼리 만들어서 다 집어넣고 id 까지 세팅해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)       // 테이블이 아닌 객체를 대상으로 쿼리를 날리는 것이다.
                .getResultList();
    }
}
