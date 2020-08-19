package jp.co.interlineOZDemo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jp.co.interlineOZDemo.vo.UserInformVO;


//ログインインターセプター
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	//コントローラーのメソッドの実行前に処理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
		
		logger.debug("LoginInterceptor 実行");
		
		
		//セッションのログイン情報を取得
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		UserInformVO member=(UserInformVO)session.getAttribute("userInform");
		logger.debug("LoginInterceptor:{} ",loginId);
		//ログインをしなった場合、ログインページに移動
		if(loginId == null || member==null ) {
			System.out.println("session정보가 없습니다.");
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		System.out.println(request.getRequestURL());
		System.out.println("admin: "+request.getRequestURI().contains("/admin/"));//확인 바람!
		System.out.println("member: "+request.getRequestURI().contains("/member/"));//확인 바람!
		if (member.getAuthority().equals("u") && request.getRequestURI().contains("/admin/")) {		
			response.sendRedirect(request.getContextPath()+"/member/memberMain");
		}else if (member.getAuthority().equals("a") && request.getRequestURI().contains("/member/")) {
			response.sendRedirect(request.getContextPath()+"/admin/adminMain");
		}
		
		
		
		//ログインをした場合、リクエストした経路に実行
		return super.preHandle(request, response, handler);
		
		
		
		
	}
}
