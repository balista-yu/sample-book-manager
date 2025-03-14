package com.book.manager.presentation.aop

import com.book.manager.usecase.security.BookManagerOperator
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

private val logger = LoggerFactory.getLogger(LoggingAdvice::class.java)

@Aspect
@Component
class LoggingAdvice {
    @Before("execution(* com.book.manager.presentation.controller..*.*(..))")
    fun beforeLog(joinPoint: JoinPoint) {
        val operator = SecurityContextHolder.getContext().authentication.principal as BookManagerOperator
        logger.info("Start: ${joinPoint.signature} operatorId=${operator.id}")
        logger.info("Class: ${joinPoint.target.javaClass}")
        logger.info(
            "Session: ${(RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.session.id}",
        )
    }

    @After("execution(* com.book.manager.presentation.controller..*.*(..))")
    fun afterLog(joinPoint: JoinPoint) {
        val operator = SecurityContextHolder.getContext().authentication.principal as BookManagerOperator
        logger.info("End: ${joinPoint.signature} operatorId=${operator.id}")
    }

    @Around("execution(* com.book.manager.presentation.controller..*.*(..))")
    fun aroundLog(joinPoint: ProceedingJoinPoint): Any? {
        val operator = SecurityContextHolder.getContext().authentication.principal as BookManagerOperator
        logger.info("Start Proceed: ${joinPoint.signature} operatorId=${operator.id}")

        val result = joinPoint.proceed()

        logger.info("End Proceed: ${joinPoint.signature} operatorId=${operator.id}")
        return result
    }

    @AfterReturning(
        "execution(* com.book.manager.presentation.controller..*.*(..))",
        returning = "returnValue",
    )
    fun afterReturningLog(joinPoint: JoinPoint, returnValue: Any?) {
        logger.info("End: ${joinPoint.signature} returnValue=$returnValue")
    }

    @AfterThrowing("execution(* com.book.manager.presentation.controller..*.*(..))", throwing = "e")
    fun afterThrowingLog(joinPoint: JoinPoint, e: Throwable) {
        logger.error("Exception: ${e.javaClass} signature=${joinPoint.signature} message=${e.message}")
    }

    @AfterThrowing("execution(* com.book.manager.presentation.controller..*.*(..))", throwing = "e")
    fun afterThrowingLog(joinPoint: JoinPoint, e: IllegalArgumentException) {
        logger.error("Exception: ${e.javaClass} signature=${joinPoint.signature} message=${e.message}")
    }
}
