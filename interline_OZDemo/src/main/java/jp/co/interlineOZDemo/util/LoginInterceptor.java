package jp.co.interlineOZDemo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
		String login_id = (String) session.getAttribute("login_id");
		
		//ログインをしなった場合、ログインページに移動
		if(login_id == null) {
			
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		//ログインをした場合、リクエストした経路に実行
		return super.preHandle(request, response, handler);
	}
}
