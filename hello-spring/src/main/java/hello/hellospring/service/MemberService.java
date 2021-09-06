package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service    // Spring Container 에 MemberService 등록해줌.
@Transactional  // 데이터를 저장하거나 변경할 때 있어야 함.
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // MemberService 입장에서 봤을 때 MemberRepository 를 직접 유입하지 않음 = DI 로 볼 수 있다.
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        // 시작 시간
        //long start = System.currentTimeMillis();

        //try {
            validateDuplicateMember(member);    //중복 회원 검증증
            memberRepository.save(member);
            return member.getId();
        //} finally {
            // 종료 시간
            //long finish = System.currentTimeMillis();
            // 결과물
            //long timeMs = finish - start ;
            //System.out.println(" join = " + timeMs + "ms");
        //}

        // 같은 이름이 있는 중복 회원은 안 된다.
        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {     // m 은 member 임
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        /*validateDuplicateMember(member);    //중복 회원 검증증
        memberRepository.save(member);
        return member.getId();*/

    }

    private void validateDuplicateMember(Member member) {
        // result 에 바로 넣어서 깔끔한 코드를 작성할 수 있다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        //long start = System.currentTimeMillis();

        //try {
            return memberRepository.findAll();
        //} finally {
            //long finish = System.currentTimeMillis();
            //long timeMs = finish - start;
            //System.out.println("findMembers = " + timeMs + "ms");
        //}
        //return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /*
    * 현재 코드 문제점 ( AOP 들어가기 전 )
    * - 회원가입, 회원 조회에 시간을 측정하는 기능은 핵심 관심 사항이 아니다.
    * - 시간을 측정하는 로직은 공통 관심 사항이다.
    * - 시간을 측정하는 로직과 핵심 비즈니스 로직이 섞여있어 유지보수가 어렵다.
    * - 시간을 측정하는 로직을 별도의 공통 로직으로 만들기는 매우 어렵다.
    * - 시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 한다. */

    /*
    * AOP 가 필요한 상황
    * - 모든 메소드의 호출 시간을 측정하고 싶을 때
    * - 공통 관심 사항 ( cross-cutting concern ) vs 핵심 관심 사항 ( core concern )
    * - 회원 가입 시간, 회원 조회 시간을 측정하고 싶다면? */

    /*
    * AOP ( Aspect Oriented Programming ) 로 해결  -> TimeTraceAop.java
    * - 회원가입, 회원 조회는 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리한다.
    * - 시간을 측정하는 로직을 별도의 공통 로직으로 만들었다.
    * - 핵심 관심 사항을 깔끔하게 유지할 수 있다.
    * - 변경이 필요하면 이 로직만 변경하면 된다.
    * - 원하는 적용 대상을 선택할 수 있다.
    * */
}
