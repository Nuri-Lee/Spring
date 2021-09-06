package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    //MemberService memberService = new MemberService();
    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // new MemoryMemberRepository(); 로 하면 MemberService 에서 쓰는 인스턴스와 달라진다.
    // MemberService 에 Map 이 static 으로 되어있기 때문에 상관없을 수 있지만,
    // static 이 아니면 다른 DB가 되면서 문제가 발생한다.
    // 게다가 테스트는 같은 레파지토리를 이용해야 한다.

    // 테스트를 실행할 때마다 각각 생성해준다. 독립적으로 실행되어야 하기 때문이다.
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  // 테스트를 돌릴 때마다 DB 데이터가 축적되기 때문에 클리어를 해줘야 함.
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());    // Assertions.assertThat -> assertThan  :: static import 해준 것
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
        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}