package com.quiz.advice;





import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;



@Aspect
@Component
public class LoggingAdvice {
	
	Logger log = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Pointcut(value="execution(* com.quiz.*.*.*(..))")
	public void myPointCut() {
		
	}
	
	@Around("myPointCut()")
	public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String className = proceedingJoinPoint.getTarget().getClass().toString();
		String methodName = proceedingJoinPoint.getSignature().getName();
		Object [] args = proceedingJoinPoint.getArgs();
		log.info("method invoked"+ className+":"+ methodName+"()"+"args: "+ mapper.writeValueAsString(args));
		Object object = proceedingJoinPoint.proceed();
		log.info( className+":"+ methodName+"()"+"response: "+ mapper.writeValueAsString(object));
		return object;
	}

}



//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class LoggingAdvice {
//
//    private final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
//
//    @AfterReturning(pointcut = "execution(* com.quiz.*.*.*(..))", returning = "result")
//    public void logServiceMethods(JoinPoint joinPoint, Object result) {
//        logger.info("Method {} executed with result: {}", joinPoint.getSignature(), result);
//    }
//}

