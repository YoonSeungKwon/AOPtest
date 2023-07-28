package yoon.test.aopTest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import yoon.test.aopTest.domain.Members;

import java.util.Collection;
import java.util.List;

@Aspect
@Component
public class AdminCheckerAspect {
    @Before(value = "execution(* yoon.test.aopTest.controller.MainController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Before pointcut" + joinPoint);
    }

    @After(value = "execution(* yoon.test.aopTest.controller.MainController.*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        System.out.println("After pointcut" + joinPoint);
    }

    @Around(value = "execution(* yoon.test.aopTest.controller.MainController.*(..))")
    public Object beforeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around pointcut" + joinPoint);
        return joinPoint.proceed();
    }

}
