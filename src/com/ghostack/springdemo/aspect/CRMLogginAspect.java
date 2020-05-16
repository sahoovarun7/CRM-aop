package com.ghostack.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLogginAspect {
	
	public static int pageCount = 0;
	@Pointcut("execution(* com.ghostack.springdemo.controller.*.*(..))")
	private void controllerPointCut(){}
	@Pointcut("execution(* com.ghostack.springdemo.service.*.*(..))")
	private void servicePointCut(){}
	@Pointcut("execution(* com.ghostack.springdemo.dao.*.*(..))")
	private void daoPointCut(){}
	@Pointcut("controllerPointCut() || servicePointCut() || daoPointCut() ")
	private void appLoggin(){}
	private Logger mylogger = Logger.getLogger(getClass().getName());
	@Pointcut("execution(* com.ghostack.springdemo.controller.*.getCustomerList(..))")
	private void getCustomerPageCount() {}
	@Pointcut("execution(* com.ghostack.springdemo.controller.*.searchCustomer(..))")
	private void getCustomerPageCountBySearch() {}
	
	@Before("appLoggin()")
	public void processLoggin(JoinPoint jp){
		 mylogger.info("=======> Before : "+jp.getSignature().toShortString());
		 Object[] arguments = jp.getArgs();
		 for (Object arg : arguments) {
			mylogger.info("Arguments : "+arg);
		}
	}
	@AfterReturning(pointcut="appLoggin()",returning="result")
	public void processLogginAfter(JoinPoint jp,Object result){
		mylogger.info("=======> After returning" + result);
	}
	@Before("getCustomerPageCount() || getCustomerPageCountBySearch()")
	public void pageHits() {
		pageCount++;
		mylogger.info("=============> PAGE COUNT MOVES TO "+pageCount);
	}
}
