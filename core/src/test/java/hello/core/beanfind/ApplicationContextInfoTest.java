package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// jUnit5 부터는 public 을 안 적어줘도 된다.
class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // Spring Container 에 등록된 모든 Bean 출력하기
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 타입을 지정하지 않았기 때문에 Object 로 꺼내짐.
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    // 직접 등록한 Bean 들만 출력한다.
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // getBeanDefinition :: bean 하나하나에 대한 정보들 ( == 메타데이터 )
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // getRole :: Spring 내부에서 무언가 하기를 위해 등록한 Bean 이 아니라,
            // 애플리케이션을 개발하기 위해 등록한 Bean 들이라고 보면 된다. or 외부 라이브러리들
            // Role ROLE_APPLICATION :: 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE :: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
