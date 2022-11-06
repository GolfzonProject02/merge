package worktalk.com.host.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import worktalk.com.host.domain.Host;
import worktalk.com.host.repository.HostDAO;
import worktalk.com.host.service.LoginService;
import worktalk.com.host.service.MailSenderService;

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
	HostDAO dao;
	@Autowired
	HttpSession session;
	
	/**
	 * requesting login form page
	 */
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
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
	public String loginOK(Host host, HttpServletRequest request) {
		logger.info("Welcome loginOK.do!");
		
		logger.info("host: {}", host);
		
		Host host1 = loginService.login(host);
		
		if (host1 == null) {
			logger.info("longin failed....");
			return "redirect:login.do";
		} else {
			if (host1.getRole() == 1) {
				logger.info("redirecting to host page....");
				session.setAttribute("host_name", host1.getName());
				logger.info("host_name: {}", session.getAttribute("host_name"));
				return "redirect:/main.do";
			} else {
				logger.info("redirecting to master page....");
				session.setAttribute("master_name", host1.getName());
				logger.info("master_name: {}", session.getAttribute("master_name"));
				return "redirect:http://localhost:8300/master/";
			}
		}
	}
	
	/**
	 * logging out => redirecting to welcome-page
	 */
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(Host host) {
		logger.info("Welcome logout.do!");
		session.removeAttribute("host_name");
		logger.info("host_name: {}", session.getAttribute("host_name"));
		// Adding "redirect:" + request.getHeader("Referer") on progress....
		// Adding Interceptor is on progress....
		return "redirect:/";
	}
	
}
