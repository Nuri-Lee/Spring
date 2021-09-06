package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  //메소드 실행이 끝날 때마다 동작함    ( = 콜백 메소드 )    -> 클래스 테스트를 진행할 때 순서에 상관없이 테스트를 진행할 수 있다.
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  //return 값이 Optional이면 .get() 으로 꺼내도 되는데, 좋은 방법은 아니다.
        //System.out.println("result = " + ( result == member));  //test 결과를 글자로 볼 수 있음
        //Assertions.assertEquals(result, member);  //결과값은 나타나지 않으나 초록불로 들어옴
        assertThat(member).isEqualTo(result);    //Assertions는 static이기 때문에 import 한 후로 지워도 된다.
    }
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
