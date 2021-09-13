package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // 인터페이스로 조회 했기 떄문에 구현 클래스가 대상이 된다.
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //System.out.println("memberService = " + memberService);
        //System.out.println("memberService.getClass() = " + memberService.getClass());
        // memberService 가 MemberServiceImpl 의 인스턴스이다.
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // AppConfig 에 memberService 의 반환 타입을 MemberService 로 했고,
    // 스프링에 등록된 인스턴스 타입을 보고 결정하기 때문에 인터페이스인 MemberService 로 해도 된다.
    // 물론 구체적으로 적는 건 좋지 않다. -> 역할과 구현을 구분하고 역할에 의존하도록 해야 하기 때문이다.
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // xxxxx 로 등록한 빈이 없기 때문에 에러가 난다.
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        //ac.getBean("xxxxx", MemberService.class);
        //MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);

        // NoSuchBeanDefinitionException 이 터져야 성공한 것이다.
        // assertThrows :: org.junit.jupiter.api.Assertions 에서 사용 -> static 으로 해줌
       assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}

