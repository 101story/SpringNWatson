package com.jin.dev.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jin.dev.controller.Text2SpeechService;
import com.jin.dev.controller.Text2SpeechVO;


@Aspect
@Component
public class AopController {
	
	private static Logger logger = 
			LoggerFactory.getLogger(AopController.class);
	
	@Autowired
	private Text2SpeechService service;

	@Before("execution(* com.jin.dev.controller.Text2SpeechController.speaker(..))")
	public void ad_before_insert(JoinPoint joinpoint) {
		logger.info("=========================");
		logger.info(" speaker ad_before_insert 수행중"   );
		logger.info("=========================");
		Object[] args = joinpoint.getArgs();
		
		for(Object obj: args) {
			if(obj instanceof Text2SpeechVO) {
				Text2SpeechVO vo = (Text2SpeechVO)args[0];
				try {
					service.insertText2Speech(vo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	

	@Before("execution(* com.jin.dev.controller.Text2SpeechController.speaker(..))")
	public void ad_before(JoinPoint joinpoint) {
		logger.info("=========================");
		logger.info(" speaker before 수행중"   );
		logger.info("=========================");
		Object[] args = joinpoint.getArgs();
		
		for(Object obj: args) {
			logger.info("arg : "+obj);
			if(obj instanceof HttpSession) {
				HttpSession session = (HttpSession) obj;
				logger.info("userid : "+(String)session.getAttribute("userid"));
			}
		}
	}
	
	//에러 없이 return 됐을 떄 
	@AfterReturning("execution(* com.jin.dev.controller.Text2SpeechController.speaker(..))")
	public void ad_after_returning() {
		logger.info("=========================");
		logger.info(" speaker AfterReturning 수행중"   );
		logger.info("=========================");
	}
	
	//수행 전후 실행 join point 존재 
//	@Around("execution(* com.jin.dev.controller.Text2SpeechController.speaker(..))")
//	public void ad_around(ProceedingJoinPoint joinpoint) {
//		logger.info("=========================");
//		logger.info(" speaker Around before"   );
//		logger.info("=========================");
//		
//		Object obj = null;
//		try {
//			long start = System.currentTimeMillis();
//			obj = joinpoint.proceed();
//			long end = System.currentTimeMillis();
//			logger.info("소요시간 : "+((double)end-start)/1000 + "초" );
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		logger.info("=========================");
//		logger.info(" speaker Around after"   );
//		logger.info("=========================");
//		
//	}
}
