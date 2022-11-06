package worktalk.com.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import worktalk.com.user.domain.User;
import worktalk.com.user.repository.UserDAO;
import worktalk.com.user.service.MailSenderService;
import worktalk.com.user.service.LoginService;

/**
 * author: Juhee Fred Lee (이주희)
 * Controller class for login and logout.
 */
@Controller
public class LogInController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);
	
	@Autowired
	LoginService loginService;
	@Autowired
	MailSenderService mailSender;
	@Autowired
	HttpSession session;
	
	/**
	 * requesting login form page
	 */
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String join() {
		logger.info("Welcome join.do!");
		return "login/login";
	}
	
	/**
	 * loggin in
	 * if successes => redirecting to previous page
	 * if fails => redirecting to login page
	 */
	
	@RequestMapping(value = "/loginOK.do", method = RequestMethod.POST)
	public String loginOK(User user, HttpServletRequest request) {
		logger.info("Welcome loginOK.do!");
		logger.info("user: {}", user);
		
		User user1 = loginService.login(user);
		
		if (user1 == null) {
			return "redirect:login.do";
		} else {
			session.setAttribute("user_name", user1.getName());
			return "redirect:/";
		}
	}
	
	/**
	 * logging out => redirecting to welcome-page
	 */
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(User user) {
		logger.info("Welcome logout.do!");
		session.removeAttribute("user_name");
		logger.info("user_name: {}", session.getAttribute("user_name"));
		// Adding "redirect:" + request.getHeader("Referer") on progress....
		// Adding Interceptor is on progress....
		return "redirect:home.do";
	}
	
}
