package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest     // Spring Container 와 Test 를 함께 실행한다.
@Transactional
// DB 에는 기본적으로 Transaction 이라는 개념이 있다. DB 에 데이터를 insert query 를 해주고 commit 을 해야 DB 에 반영이 된다.
// 자동으로 commit 을 해주는 auto commit 이 있긴 하지만, 기본적으로는 commit 을 직접 해줘야 한다.
// test 끝나고 roll back 을 해버리면 db 에 데이터가 없어진다.

// @Transactional 을 test case 에 달면 test 를 실행할 때 transaction 을 먼저 실행하고 DB 에 데이터를 insert query 로 다 넣는다.
// 그리고 test 가 끝나면 roll back 을 해줌으로써 DB 에 데이터를 깔끔하게 삭제 해준다.
// -> DB 에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;      // @Autowired 를 했기 떄문에, @BeforeEach @AfterEach 을 지울 수 있는 것이다.

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplicateMemberException() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}