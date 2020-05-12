package com.gcu;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
public class Tracer
{
    private static Logger logger = LoggerFactory.getLogger("GcuTracer");

    @Pointcut("within(com.gcu.controller..*)")
	public void inPresentationLayer()
	{
    	logger.info("------------------> Within Presentation Layer");
	}

    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable
	{
		logger.info("------------------> Entering...." + pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + "()");
		Object retVal = pjp.proceed();
		logger.info("------------------>   Leaving...." + pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + "()");
		return retVal;
	}
}