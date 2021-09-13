package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// Bean 을 등록하는 방법
// 1. 직접 Spring Bean 을 등록하는 방법  == appConfig.xml
// 2. Factory Method 를 사용하는 방법 == AppConfig.java
public class beanDefinitionTest {

    //ApplicationContext 를 type 으로 못 하는 이유는 getBeanDefinition 을 못 하기 때문이다.
   //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    // bean 에 대한 정확한 정보를 볼 수 있다.
    // factory 메소드가 빠져있다.
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");    // bean 에 대한 정확한 정보를 볼 수 있다.
                                                                                                          // factory 메소드가 빠져있다.
   @Test
   @DisplayName("빈 설정 메타정보 확인")
   void findApplicationBean() {
       String[] beanDefinitionNames = ac.getBeanDefinitionNames();
       for (String beanDefinitionName : beanDefinitionNames) {
           BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

           if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
               System.out.println("beanDefinitionName = " + beanDefinitionName +
                       " beanDefinition = " + beanDefinition);
           }
       }
   }
}