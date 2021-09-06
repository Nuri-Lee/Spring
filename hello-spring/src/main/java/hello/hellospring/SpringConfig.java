package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기.
// Controller 는 어쩔 수 없이 컴포넌트 스캔으로 해야 한다.
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    /*private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    @Bean
    public MemberService memberService() {
        //return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

    //@Bean
    //public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    //}



    /* 객체 지향적인 설계가 좋다고 하는 이유
    *  다형성을 활용할 수 있다.   == Spring 장점
    *   인터페이스를 두고 구현체를 바꿔낄 수 있기 때문이다.
    *   이를 쉽게 구현할 수 있도록 Spring 에서 Spring Container 가 지원해준다.
    *
    * -> 개방-폐쇄 원칙 ( OCP, Open-Closed Principle )
    *       : 확장에는 열려있고, 수정(변경)에는 닫혀있다.
    *    Spring 의 DI 를 사용하면 '기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경' 할 수 있다.
    */

    // 정형화 되었다기 보다는 AOP 를 걸어서 사용하는구나를 알 수 있으므로
    // TimeTraceAop 클래스에서 @Component 를 사용하기 보다 Spring Bean 을 직접 등록해 주는 것이 좋다.
    /*@Bean
    public TimeTraceAop timeTraceAop()  {
        return new TimeTraceAop();
    }*/
}
