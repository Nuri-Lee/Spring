package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        //AppConfig 이용해서 구현
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();    // memberService 에는 Impl 이 들어가 있다.

        // ApplicationContext == 스프링 컨테이너
        // AppConfig 에 있는 객체들을 Spring Container 에 넣어서 관리해주는 것
        // @Bean 을 모두 호출한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 이름은 AppConfig 에서 지정한 메소드명과 일치시킨다.
        // 이름, 타입 :: memberService 라는 이름으로 MemberService.class 타입으로 꺼낼 거임.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
