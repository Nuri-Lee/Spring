package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component      // AOP 의 경우 직접 Bean 을 등록해주는 것이 좋지만 여기서는 Component 로 등록해주겠다.
public class TimeTraceAop {

    @Around("execution(* com.hi.hispring..*(..))")  // * 패키지 명 클래스명 ( 파라미터 ) 로 원하는 조건을 넣을 수 있다.
                                                    // 이 같은 경우는 패키지 하위에 모두 적용하란 뜻이다.
                                                    // * com.hi.hispring.service..*(..)) 이렇게 하면 service 하위에만 적용된다. ( service 포함 )
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
