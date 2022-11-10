package worktalk.com.user.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PaymentInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(PaymentInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String sPath = request.getServletPath();
		logger.info("preHandle: {}",sPath);
		
		HttpSession session = request.getSession();
		String user_name = (String) session.getAttribute("user_name");
		logger.info("{}", user_name);
		
		if (sPath.equals("/payment/prepaid_test.do")
				||sPath.equals("/payment/payment_test_getResult.do")
				||sPath.equals("/payment/verify.do")
				||sPath.equals("/payment/cancel.do")
				||sPath.equals("/payment/cancel_page.do")
				||sPath.equals("/payment/findHistoryByName.do")
				) {
			if (user_name == null) {
				response.sendRedirect("http://localhost:8100/user/login.do");
				return false;
			}
		}
		
		return true;
	}

}
