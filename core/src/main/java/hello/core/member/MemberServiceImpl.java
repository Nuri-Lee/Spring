package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 다형성에 의해 인터페이스인 MemberRepository 가 아닌 MemoryMemberRepository 가 호출된다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
