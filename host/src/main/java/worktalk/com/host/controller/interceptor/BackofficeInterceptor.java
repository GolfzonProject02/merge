package worktalk.com.host.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Interceptor class for backoffice
 *
 */

public class BackofficeInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(BackofficeInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String sPath = request.getServletPath();
		logger.info("preHandle: {}",sPath);
		
		HttpSession session = request.getSession();
		String host_name = (String) session.getAttribute("host_name");
		logger.info("{}", host_name);
		
		if (sPath.equals("/backoffice/reservation/findByName.do")
				||sPath.equals("/backoffice/reservation/findByStatus.do")
				||sPath.equals("/backoffice/reservation/end.do")
				||sPath.equals("/backoffice/reservation/cancel.do")
				||sPath.equals("/backoffice/payment/findByName.do")
				||sPath.equals("/backoffice/payment/findByStatus.do")
				) {
			if (host_name == null) {
				response.sendRedirect("http://localhost:8200/host/login.do");
				return false;
			}
		}
		
		return true;
	}

}
