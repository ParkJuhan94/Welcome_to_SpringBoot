package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component  //  컴포넌트스캔보다 SpringConfig에 @Bean 등록해주는게 더 좋긴 함.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")    //  hello.hellospring 패키지 하위에 다 적용한다는 것
    public Object execute(ProceedingJoinPoint joinPoint) throws  Throwable {
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

/*
* 해결
회원가입, 회원 조회등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리합니다.
시간을 측정하는 로직을 별도의 공통 로직으로 만들었습니다.
핵심 관심 사항을 깔끔하게 유지할 수 있습니다.
변경이 필요하면 이 로직만 변경하면 됩니다.
원하는 적용 대상을 선택할 수 있습니다
* */