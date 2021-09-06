package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller     // Spring Container 에 객체를 생성해서 넣어 둔다.
                // -> Spring Container 에서 Spring Bean 을 관리함. == 컴포넌트 스캔과 자동 의존관계 설정 ( @Controller 안에 @Component 가 있음. )
                // 컴포넌트 스캔은 hello.hellospring 패키지 내에 있는 컴포넌트들만 스캔된다.
public class MemberController {

    // new MemberService() 로 생성하지 않는 이유
    // Spring Container 에 등록되면 여기서 갖다 써야 하는데, 여기서 new 로 생성하면 다른 컨트롤러에서 이 MemberService 를 가져다 쓸 수 있음.
    // MemberService 에는 여러 인스턴스를 생성할 만큼 별 기능이 없다. 하나만 생성한 후 공유해야 함.
    private final MemberService memberService;      // 여기에 @Autowired 하는 것 == DI :: field 생성 주입 ( 별로 안 좋음. 바꿀 수 있는 방법이 없기 때문에 )

    @Autowired      // Spring Container 에 있는 MemberService 와 연결시켜 주는 역할
                    // -> 생성자를 통해서 MemberService 가 MemberController 에 주입이 된다. == DI :: 생성자 주입
                    // 생성자 주입을 가장 권장하는 이유 : 의존관계가 실행 중에 동적으로 변하는 경우가 거의(아예) 없기 때문이다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
     * // DI :: setter 주입 ( 필드 값에 final 빼야 함. )   -> 접근제한자가 public 이기 때문에 MemberService 가 변동될 수 있는 위험이 있다. ( 한 번 세팅이 되고 나면 바뀔 일이 업음. )
     * @Autowired
     * public void setMemberService(MemberService memberService) {
     *   this.memberService = memberService;
     * }
     * */

    // 회원가입 폼
    @GetMapping("/members/new")     // URL 입력 후 엔터 치는 건 다 GetMapping == 조회
    public String createForm() {
        return "members/createMemberForm";
    }

    // 회원가입 데이터 등록
    @PostMapping("/members/new")        // 데이터를 Form 같은 곳에 넣어서 전달.
    public String create( MemberForm form ) {
        Member member = new Member();
        member.setName(form.getName());

        //System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";    // redirect 로 가입이 끝나면 home 화면으로 보냄\
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);     // 메모리 안에 있기 떄문에 서버를 내리면 데이터가 다 사라진다.   -> DB에 저장해야 한다.

        return "members/memberList";
    }
}
