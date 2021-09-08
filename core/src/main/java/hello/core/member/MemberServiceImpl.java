package hello.core.member;

// 역할 : 실행하는 역할
public class MemberServiceImpl implements MemberService {

    // 다형성에 의해 인터페이스인 MemberRepository 가 아닌 MemoryMemberRepository 가 호출된다.
    //private final MemberRepository memberRepository = new MemoryMemberRepository();   // DIP, OCP 위반
    private final MemberRepository memberRepository;    // DIP 가 잘 지켜짐 == 인터페이스에만 의존
                                                        // -> 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중.

    // 생성자를 통해서 MemberRepository 에 들어가는 구현체를 갖고 온다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /* MemberServiceImpl 의 문제점
     *  MemberServiceImpl 은 MemberRepository 에 의존을 한다.
     *  그런데 실제 할당하는 부분이 MemoryMemberRepository 이다 보니
     *  MemoryMemberRepository 까지 의존하게 됐다. -> DIP 위반 */
}
