package jp.co.interlineOZDemo.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

//예외처리용 클래스
@ControllerAdvice
public class ErrorHandler {
	private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

	//서버에러를 처리하는 메소드
	@ExceptionHandler(Exception.class) 
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ModelAndView AjaxhandleException(HttpServletRequest request
			,HandlerMethod handlerMethod
			,Exception e) {
		
		logger.error(""+e); //에러내용을 로그로 작성
		
		String check_ajax = request.getHeader("x-requested-with");
		ModelAndView mav =  new ModelAndView();
		
		//데이터 전송방식이 ajax인지 확인
		if(check_ajax == null) {
			mav.setViewName("/Error/500Error"); //전송방식이 ajax가 아닐경우 화면처리
		}
		  
		return mav; 
	}
}
