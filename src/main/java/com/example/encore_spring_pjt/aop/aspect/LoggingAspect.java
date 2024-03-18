package com.example.encore_spring_pjt.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/*
AOP 용어정리 (횡단관심모듈(cross cutting)을 이용한 공통의 사항을 처리하는 것이 AOP)

Aspect : 공통의 기능을 가지고 있는 모듈
Target : Aspect 가 적용될 대상(클래스, 메서드 등이 해당)
Join Point : Aspect 가 적용될 시점(e.g., 메서드 실행 전, 후)
Advice : 시점에 실행코드
PointCut : Advice 를 적용할 메서드의 범위를 지정

시점에 관련된 어노테이션으로
@Before : 대상의 메서드가 실행되기 전에 실행
@After : 대상의 메서드가 실행된 후에 실행
@AfterReturning : 대상의 메서드가 정상으로 실행되고 반환된 후에 실행
@AfterThrowing : 대상의 메서드가 예외가 발생되었을 때 실행
@Around : 대상의 메서드가 실행 전, 후 그리고 예외 발생시에 실행
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.example.encore_spring_pjt.ctrl..*.*(..))")
    // @Pointcut("execution(com.example.encore_spring_pjt.aop.ctrl.AopTestController.test*(..))")
    private void cut() {

    }

    @Before("cut()")
    public void beforeLog(JoinPoint joinPoint) {
        String name = getMethod(joinPoint);
        System.out.println("debug >>> LoggingAspect beforeLog");
        System.out.println("debug >>> name , " + name);

        // 파라미터 정보를 얻어오기 위해서는 ...
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            log.info(" >>>> no parameter");
        } else {
            for (Object arg : args) {
                log.info("parameter type = {} ", arg.getClass().getSimpleName());
                log.info("parameter value = {} ", arg);
            }
        }
    }

    @After("cut()")
    public void afterLog(JoinPoint joinPoint) {
        System.out.println("debug >>> LoggingAspect afterLog");
    }

    @AfterReturning(pointcut = "cut()", returning = "param")
    public void returningLog(JoinPoint joinPoint, Object param) {
        String name = getMethod(joinPoint);
        System.out.println("debug >>> LoggingAspect returningLog ");
        System.out.println("debug >>> name , " + name);

        // 리턴타입과 리턴되는 값을 확인할 수 있음
        System.out.println("debug >>> return type , " + param.getClass().getSimpleName());
        System.out.println("debug >>> return value , " + param);
    }

    /*
    // 메서드 실행 전, 후, 그리고 예외 발생 시 Advice 실행
    @Around("cut()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("debug >>> around before , " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("debug >>> around after , " + joinPoint.getSignature().getName());
        return result;
    }
     */

    @AfterThrowing(pointcut = "cut()", throwing = "e")
    public void throwingLog(JoinPoint joinPoint, Throwable e) {
        System.out.println("debug >>> throwingLog method , " + joinPoint.getSignature().getName());
        System.out.println("debug >>> throwingLog msg , " + e.getMessage());
    }


    // joinPoint 관련 정보를 얻어오기 위해서
    public String getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getName();
    }

}
