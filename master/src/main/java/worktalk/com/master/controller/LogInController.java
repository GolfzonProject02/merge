package worktalk.com.master.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import worktalk.com.master.domain.Master;
import worktalk.com.master.repository.MasterDAO;
import worktalk.com.master.service.LoginService;
import worktalk.com.master.service.MailSenderService;

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
	MasterDAO dao;
	@Autowired
	HttpSession session;
	
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(Master master) {
		logger.info("Welcome logout.do!");
		session.removeAttribute("master_name");
		logger.info("host_name: {}", session.getAttribute("master_name"));
		// Adding "redirect:" + request.getHeader("Referer") on progress....
		// Adding Interceptor is on progress....
		return "redirect:/";
	}
	
}
