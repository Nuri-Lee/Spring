package hello.hellospring.domain;

// JPA 는 자바 진영의 표준 인터페이스이다.
// hibernate 으로 구현이 가능한데 구현은 업체별로 상이하다.
// ORM ( Object, Relational, Mapping ) : 객체와 Relational Database 를 매핑시켜주는 것이다.

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    /** 시스템이 정하는 ID 값 */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)     // 쿼리에 아이디를 넣는 것이 아니라, 디비에 값을 넣으면 디비가 아이디를 자동으로 생성해준다.
                                                                // == Identity 라고 함. ( Identity 전략 )
    private Long id;
    /** 사용자 이름 */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
